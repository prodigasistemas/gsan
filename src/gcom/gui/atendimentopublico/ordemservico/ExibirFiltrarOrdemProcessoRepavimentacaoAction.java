package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class ExibirFiltrarOrdemProcessoRepavimentacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarOrdemProcessoRepavimentacao");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarOrdemProcessoRepavimentacaoActionForm form = (FiltrarOrdemProcessoRepavimentacaoActionForm) actionForm;
		
		
		String limpar = httpServletRequest.getParameter("limpar");
		if (limpar != null && limpar.equals("S")) {	
			
			form.setIdUnidadeResponsavel("");
			form.setSituacaoRetorno("");
			form.setPeriodoEncerramentoOsInicial("");
			form.setPeriodoEncerramentoOsFinal("");
			form.setPeriodoRetornoServicoInicial("");
			form.setPeriodoRetornoServicoFinal("");
			form.setEscolhaRelatorio("");
			
			
			form.setNumeroOS("");
			form.setIdMunicipio("");
			
			form.setIdBairro("");
			form.setCodigoBairro("");
			form.setBairroDescricao("");
			
			form.setIdLogradouro("");
			form.setLogradouroDescricao("");
		}		

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		
		//Verifica se o usuario é da unidade tipo = Repavimentadora caso seja faz a consulta dessa unidade.
		Collection colecaoUnidadesResponsaveis = null;
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		if ( usuario != null && usuario.getUnidadeOrganizacional() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo().getId() != null &&
				(usuario.getUnidadeOrganizacional().getUnidadeTipo().getId().intValue() == 
					UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue()) ) { 
			
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, 
					usuario.getUnidadeOrganizacional().getId()));
			
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO,
					UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID));

			colecaoUnidadesResponsaveis = 
				this.getFachada().pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) 
				Util.retonarObjetoDeColecao(colecaoUnidadesResponsaveis);
			
			form.setIdUnidadeResponsavel(unidadeOrganizacional.getId().toString());
			
			httpServletRequest.setAttribute("bloquearUnidade", true);
		
		} else {
			
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(
				    FiltroUnidadeOrganizacional.INDICADOR_USO, 
				    ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO,
					UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID));

			httpServletRequest.removeAttribute("bloquearUnidade");
			colecaoUnidadesResponsaveis = this.getFachada().pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		}
		
		//Setar Inicialmente a unidade de repavimentação subordinada à GML, ainda que existam outras a relacionar.
		//Correspondente a Recife - "GER. METR. NEG. LESTE".		
		
		httpServletRequest.setAttribute("colecaoUnidadesResponsaveis", colecaoUnidadesResponsaveis);
		
		
		Collection colecaoUnidadeMunicipio = new ArrayList();

		//2.2.4.	Município, município associado à unidade organizacional
		if(form.getIdUnidadeResponsavel() != null && !form.getIdUnidadeResponsavel().equals("")){
			FiltroUnidadeOrganizacionalMunicipio filtroUnidadeMunicipio = new FiltroUnidadeOrganizacionalMunicipio();
			filtroUnidadeMunicipio.adicionarParametro(
				new ParametroSimples(
					FiltroUnidadeOrganizacionalMunicipio.ID_UNIDADE_REPAVIMENTADORA, 
					form.getIdUnidadeResponsavel()));

			filtroUnidadeMunicipio.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO);
			
			colecaoUnidadeMunicipio = 
				this.getFachada().pesquisar(filtroUnidadeMunicipio, 
					UnidadeOrganizacionalMunicipio.class.getName());
			
			if(colecaoUnidadeMunicipio != null && colecaoUnidadeMunicipio.size() == 1){
				UnidadeOrganizacionalMunicipio unidadeOrganizacionalMunicipio =
					(UnidadeOrganizacionalMunicipio) Util.retonarObjetoDeColecao(colecaoUnidadeMunicipio);
				
				form.setIdMunicipio(unidadeOrganizacionalMunicipio.getIdMunicipio().getId().toString());
			}
		}
		httpServletRequest.setAttribute("colecaoUnidadeMunicipio", colecaoUnidadeMunicipio);
		
		// 2.2.5.	Bairro, bairro da ordem de serviço
		if (form.getCodigoBairro() != null && !form.getCodigoBairro().equals("")) {
			if (form.getIdMunicipio() != null && !form.getIdMunicipio().equals("")) {
				
				FiltroBairro filtroBairro = new FiltroBairro();
				filtroBairro.adicionarParametro(
					new ParametroSimples(
						FiltroBairro.CODIGO, 
						form.getCodigoBairro()));
				
				filtroBairro.adicionarParametro(
					new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, 
						form.getIdMunicipio()));
				
				
				Collection<Bairro> colecaoBairro = 
					this.getFachada().pesquisar(filtroBairro, Bairro.class.getName());
				
				if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
					
					httpServletRequest.setAttribute("bairroEncontrada", "true");
					
					Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
					
					form.setIdBairro(bairro.getId().toString());
					form.setBairroDescricao(bairro.getNome());
				} else {
					httpServletRequest.removeAttribute("bairroEncontrada");
					
					form.setIdBairro("");
					form.setCodigoBairro("");
					form.setBairroDescricao("Bairro inexistente");
				}
				
				
			} else {
				throw new ActionServletException("atencao.filtrar_informar_municipio");
			}
		}
		
		//2.2.6.	Logradouro, logradouro da ordem de serviço
		if (form.getIdLogradouro() != null && !form.getIdLogradouro().equals("")) {
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			filtroLogradouro.adicionarParametro(
				new ParametroSimples(
					FiltroLogradouro.ID, 
					form.getIdLogradouro()));
			
			Collection<Logradouro> colecaoLogradouro = 
				this.getFachada().pesquisar(filtroLogradouro, Logradouro.class.getName());
			
			if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {
				httpServletRequest.setAttribute("logradouroEncontrada", "true");
				Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(colecaoLogradouro);
				
				form.setLogradouroDescricao(logradouro.getNome());
			} else {
				httpServletRequest.removeAttribute("logradouroEncontrada");
				
				form.setIdLogradouro("");
				form.setLogradouroDescricao("Logradouro inexistente");
			}
		}
		
		return retorno;
	}

}

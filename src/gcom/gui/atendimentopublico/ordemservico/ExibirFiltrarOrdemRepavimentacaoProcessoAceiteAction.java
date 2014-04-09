package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarOrdemRepavimentacaoProcessoAceiteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarOrdemRepavimentacaoProcessoAceite");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form = (FiltrarOrdemRepavimentacaoProcessoAceiteActionForm) actionForm;

		String limpar = httpServletRequest.getParameter("limpar");
		if (limpar != null && limpar.equals("S")) {	
			
			form.setIdUnidadeResponsavel("");
			form.setSituacaoAceite("");
			form.setPeriodoAceiteServicoInicial("");
			form.setPeriodoAceiteServicoFinal("");
			form.setPeriodoRetornoServicoInicial("");
			form.setPeriodoRetornoServicoFinal("");
			form.setEscolhaRelatorio("");
			form.setIdUnidadeOrganizacional("");
			form.setDescricaoUnidadeOrganizacional("");
			form.setPeriodoRejeicaoInicial("");
			form.setPeriodoRejeicaoFinal("");
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
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, usuario.getUnidadeOrganizacional().getId()));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO,UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID));

			colecaoUnidadesResponsaveis = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) 
				Util.retonarObjetoDeColecao(colecaoUnidadesResponsaveis);
			form.setIdUnidadeResponsavel(unidadeOrganizacional.getId().toString());
			
			httpServletRequest.setAttribute("bloquearUnidade", true);
		
		} else {
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO,UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID));
			httpServletRequest.removeAttribute("bloquearUnidade");
			colecaoUnidadesResponsaveis = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		}		
		
		if (colecaoUnidadesResponsaveis == null || colecaoUnidadesResponsaveis.isEmpty()) {
			 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
		     "Unidade_Organizacional");
		 }else{
			 httpServletRequest.setAttribute("colecaoUnidadesResponsaveis", colecaoUnidadesResponsaveis);
		 }
		
		if (form.getIdUnidadeOrganizacional() != null && !form.getIdUnidadeOrganizacional().equals("")) {
			
			this.pesquisarUnidadeOrganizacional(httpServletRequest, form);
		}else{
			form.setDescricaoUnidadeOrganizacional("");
		}
		
		return retorno;
	}

	private void pesquisarUnidadeOrganizacional(HttpServletRequest httpServletRequest,
			FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form){
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdUnidadeOrganizacional()));
		
		Collection colecaoUnidadeOrganizacional = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getSimpleName());
		
		if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
			
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) 
				colecaoUnidadeOrganizacional.iterator().next();
			
			form.setIdUnidadeOrganizacional(unidadeOrganizacional.getId().toString());
			form.setDescricaoUnidadeOrganizacional(unidadeOrganizacional.getDescricao());
		} else {
			form.setIdUnidadeOrganizacional("");
			form.setDescricaoUnidadeOrganizacional("UNIDADE DE NEGOCIO INEXISTENTE.");
			httpServletRequest.setAttribute("unidadeOrganizacionalInexistente",true);
		}
	}
}

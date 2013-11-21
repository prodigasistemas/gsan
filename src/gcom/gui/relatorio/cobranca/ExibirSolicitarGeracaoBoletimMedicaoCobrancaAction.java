package gcom.gui.relatorio.cobranca;

import java.util.Collection;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaBoletimMedicao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaBoletimMedicao;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1153] Solicitar Geração/Emissão Boletim de Medição de Cobrança
 * 
 * @author Mariana Victor
 *
 * @date 21/03/2011
 */
public class ExibirSolicitarGeracaoBoletimMedicaoCobrancaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirSolicitarGeracaoBoletimMedicaoCobranca");
		
		GerarRelatorioBoletimMedicaoCobrancaForm form = 
			(GerarRelatorioBoletimMedicaoCobrancaForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
	
		Fachada fachada = Fachada.getInstancia();

        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
            
		if (form.getMesAnoReferencia() != null
				&& !form.getMesAnoReferencia().equals("")) {
			Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
			
			if (!Util.validarMesAno(form.getMesAnoReferencia())) {
				throw new ActionServletException("atencao.mes.ano.informado.invalido");
			}
			
			if (Util.compararAnoMesReferencia(anoMes, sistemaParametro.getAnoMesFaturamento(), ">")){
				throw new ActionServletException("atencao.mes.ano.informado.maior.faturamento.atual", null, sistemaParametro.getAnoMesFaturamento().toString());
			}
		}
		
		if (form.getTipoOperacao() != null
				&& form.getTipoOperacao().equals("1")) {
			
			Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
			Integer idGrupoCobranca = new Integer(form.getGrupoCobranca());
			
			// [FS0002] – Ações não encerradas no cronograma.
			Integer contador = fachada.pesquisarAcoesEncerradasCronograma(
					anoMes, idGrupoCobranca);

			if (contador != null && contador > 0){
				throw new ActionServletException("atencao.faltam.acoes.encerradas.cronograma.grupo");
			}
			
			//[FS0003] – Boletim já existe.
			FiltroCobrancaBoletimMedicao filtroCobrancaBoletimMedicao = new FiltroCobrancaBoletimMedicao();
			filtroCobrancaBoletimMedicao.adicionarParametro(
					new ParametroSimples(FiltroCobrancaBoletimMedicao.ANO_MES_REFERENCIA, anoMes));
			filtroCobrancaBoletimMedicao.adicionarParametro(
					new ParametroSimples(FiltroCobrancaBoletimMedicao.COBRANCA_GRUPO_ID, idGrupoCobranca));
			Collection colecaoCobrancaBoletimMedicao = fachada.pesquisar(
					filtroCobrancaBoletimMedicao, CobrancaBoletimMedicao.class.getName());
			
			if (colecaoCobrancaBoletimMedicao != null
					&& !colecaoCobrancaBoletimMedicao.isEmpty()) {
				throw new ActionServletException("atencao.boletim.ja.existe");
			}
			
			// Usuario logado no sistema
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");	
			
			//[UC1151] Gerar Boletim de Medição
			fachada.gerarBoletimMedicao(idGrupoCobranca,anoMes,usuarioLogado);
			
			retorno = actionMapping
			.findForward("telaSucesso");
			
			montarPaginaSucesso(httpServletRequest, " Gerar Boletim de Medição encaminhado para processamento. ","Voltar","/exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?menu=sim");
			
			return retorno;
			
		}

		// Tipo da Operação igual a Emitir Boletim
		if (form.getTipoOperacao() != null
				&& form.getTipoOperacao().equals("2")) {

			// Pesquisa Localidade Inicial
			if(form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("")
					&& (form.getDescricaoLocalidadeInicial() == null || form.getDescricaoLocalidadeInicial().equals(""))){
				
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeInicial()));
				Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				
				if(colecaoLocalidade.isEmpty()){
					form.setIdLocalidadeInicial("");
					form.setDescricaoLocalidadeInicial("Localidade Inexistente");
					httpServletRequest.setAttribute("idLocalidadeInicialNaoEncontrado","exception");
					httpServletRequest.setAttribute("nomeCampo","idLocalidadeInicial");
				}else{
					Localidade localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
					form.setIdLocalidadeInicial(localidade.getId().toString());
					form.setDescricaoLocalidadeInicial(localidade.getDescricao());
					if(form.getIdLocalidadeFinal() == null || form.getIdLocalidadeFinal().equals("")){
						form.setIdLocalidadeFinal(localidade.getId().toString());
						form.setDescricaoLocalidadeFinal(localidade.getDescricao());
					}
					httpServletRequest.setAttribute("nomeCampo","idLocalidadeFinal");
					
				}
			}

			// Pesquisa Localidade Final
			if(form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("")
					&&(form.getDescricaoLocalidadeFinal() == null || form.getDescricaoLocalidadeFinal().equals(""))){
				
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeFinal()));
				Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				
				if(colecaoLocalidade.isEmpty()){
					form.setIdLocalidadeFinal("");
					form.setDescricaoLocalidadeFinal("Localidade Inexistente");
					httpServletRequest.setAttribute("nomeCampo","idLocalidadeFinal");
					httpServletRequest.setAttribute("idLocalidadeFinalNaoEncontrado","exception");
				}else{
					Localidade localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
					form.setIdLocalidadeFinal(localidade.getId().toString());
					form.setDescricaoLocalidadeFinal(localidade.getDescricao());
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
				}
			}
		}

		if (httpServletRequest.getAttribute("colecaoCobrancaGrupo") == null
				|| httpServletRequest.getAttribute("colecaoCobrancaGrupo").equals("")){
			// Popular o combo-box de Grupo de Cobrança 
			this.pesquisarCobrancaGrupo(httpServletRequest);
		}

		if (httpServletRequest.getAttribute("colecaoGerenciaRegional") == null
				|| httpServletRequest.getAttribute("colecaoGerenciaRegional").equals("")){
			// Popular o combo-box de Gerência Regional
			this.pesquisarGerenciaRegional(httpServletRequest);
		}
		

		this.pesquisarUnidadeNegocio(httpServletRequest,form);
		
		return retorno;
	}
	
	private void pesquisarCobrancaGrupo(HttpServletRequest httpServletRequest){
		
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		
		filtroCobrancaGrupo.setConsultaSemLimites(true);
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
		filtroCobrancaGrupo.adicionarParametro(
				new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoCobrancaGrupo = 
			this.getFachada().pesquisar(filtroCobrancaGrupo,CobrancaGrupo.class.getName());

		if (colecaoCobrancaGrupo == null || colecaoCobrancaGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Grupo de cobrança");
		} else {
			httpServletRequest.setAttribute("colecaoCobrancaGrupo",colecaoCobrancaGrupo);
		}
	}
	
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());

		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}

	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioBoletimMedicaoCobrancaForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
					form.getGerenciaRegional()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Negócio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		}
	}
}

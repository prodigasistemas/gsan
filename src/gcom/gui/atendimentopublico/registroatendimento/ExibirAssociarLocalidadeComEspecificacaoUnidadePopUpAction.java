package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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

/**
 * [UC1091] Informar Associação de Localidade com Especificação e Unidade
 * 
 * @author Hugo Leonardo
 *
 * @date 29/11/2010
 */
public class ExibirAssociarLocalidadeComEspecificacaoUnidadePopUpAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAssociarLocalidadeComEspecificacaoUnidadePopUpAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form = 
			(ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm) actionForm;
		
		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");
		String idUnidade = httpServletRequest.getParameter("idCampoEnviarDados");
		String nomeUnidade = httpServletRequest.getParameter("descricaoCampoEnviarDados");
		String consulta = httpServletRequest.getParameter("consulta");
		String idLocalidade = httpServletRequest.getParameter("idLocalidade");
		
		if(idLocalidade != null && !idLocalidade.equals("")){
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));

			Collection<Localidade> localidadePesquisada = Fachada.getInstancia().pesquisar(
					filtroLocalidade, Localidade.class.getName());

			if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
				form.setIdLocalidade("" + localidade.getId());
				form.setNomeLocalidade( localidade.getDescricao());
			}
		}
		
		if(tipoConsulta != null && tipoConsulta.equals("unidadeOrganizacional") 
				&& Util.verificarNaoVazio(idUnidade) && Util.verificarNaoVazio(nomeUnidade)){
			
			form.setIdUnidadeAtendimento(idUnidade);
			form.setNomeUnidadeAtendimento(nomeUnidade);
		}
		
		// pesquisa Unidade de Atendimento
		if(Util.verificarNaoVazio(form.getIdUnidadeAtendimento())){
			this.pesquisarUnidadeAtendimento(httpServletRequest, form);
		}
		
		// pesquisa Tipo Solicitação
		if(sessao.getAttribute("colecaoTipoSolicitacao") == null){
			this.pesquisarTipoSolicitacao(httpServletRequest, sessao);
		}
		
		// pesquisa Tipo Especificação
		if(consulta != null && consulta.equals("especificacao") && form.getIdTipoSolicitacao() != null && !form.getIdTipoSolicitacao().equals("") 
				&& !form.getIdTipoSolicitacao().equals("-1") ){
			
			this.pesquisarSolicitacaoTipoEspecificacao(sessao, form);
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisar Solicitação Tipo
	 *
	 * @author Hugo Leonardo
	 * @date 29/11/2010
	 */
	private void pesquisarTipoSolicitacao(HttpServletRequest httpServletRequest, 
			HttpSession sessao) {

		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		filtroSolicitacaoTipo.setConsultaSemLimites(true);
		filtroSolicitacaoTipo.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);
		
		filtroSolicitacaoTipo.adicionarParametro( new ParametroSimples( 
				FiltroSolicitacaoTipo.INDICADOR_USO, ConstantesSistema.SIM));

		Collection colecaoTipoSolicitacao = Fachada.getInstancia().pesquisar(
				filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
		
		if (colecaoTipoSolicitacao == null || colecaoTipoSolicitacao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Solicitação Tipo");
		} else {
			sessao.setAttribute("colecaoTipoSolicitacao", colecaoTipoSolicitacao);
		}
	}
	
	/**
	 * Pesquisar Solicitação Tipo Especificação
	 *
	 * @author Hugo Leonardo
	 * @date 29/11/2010
	 */
	private void pesquisarSolicitacaoTipoEspecificacao(HttpSession sessao,
			ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form) {

		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);
		
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, form.getIdTipoSolicitacao()));
		
		filtroSolicitacaoTipoEspecificacao.adicionarParametro( new ParametroSimples( 
				FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.SIM));

		Collection colecaoSolicitacaoTipoEspecificacao = Fachada.getInstancia().pesquisar(
				filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
		
		if (colecaoSolicitacaoTipoEspecificacao == null || colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Solicitação Tipo Especificação");
		} else {
			sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", colecaoSolicitacaoTipoEspecificacao);
		}
	}
	
	/**
	 * Pesquisar Unidade Atendimento
	 *
	 * @author Hugo Leonardo
	 * @date 29/11/2010
	 */
	private void pesquisarUnidadeAtendimento(HttpServletRequest httpServletRequest, 
			ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form) {

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdUnidadeAtendimento()));
		
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.SIM));
		
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_TRAMITE, ConstantesSistema.SIM));

		Collection<UnidadeOrganizacional> unidadePesquisada = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if (unidadePesquisada != null && !unidadePesquisada.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(unidadePesquisada);
			form.setIdUnidadeAtendimento("" + unidadeOrganizacional.getId());
			form.setNomeUnidadeAtendimento( unidadeOrganizacional.getDescricao());

		} else {
			form.setIdUnidadeAtendimento("");
			form.setNomeUnidadeAtendimento("UNIDADE ATENDIMENTO INEXISTENTE");
			httpServletRequest.setAttribute("unidadeAtendimentoInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idUnidadeAtendimento");
		}
	}
	
}

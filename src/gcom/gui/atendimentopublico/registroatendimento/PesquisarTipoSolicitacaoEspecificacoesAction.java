package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 05/08/2006
 */
public class PesquisarTipoSolicitacaoEspecificacoesAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um novo material
	 * 
	 * [UC0381] Inserir Material
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 31/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("listaPesquisarTipoSolicitacaoEspecificacoesResultado");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarTipoSolicitacaoEspecificacoesActionForm pesquisarTipoSolicitacaoEspecificacoesActionForm = (PesquisarTipoSolicitacaoEspecificacoesActionForm) actionForm;

		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = pesquisarTipoSolicitacaoEspecificacoesActionForm
				.getDescricao();

		String solicitacaoTipoGrupo = pesquisarTipoSolicitacaoEspecificacoesActionForm
				.getSolicitacaoTipoGrupo();

		String indicadorFaltaAgua = pesquisarTipoSolicitacaoEspecificacoesActionForm
				.getIndicadorFaltaAgua();

		// Verifica se o campo codigo foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroSolicitacaoTipo.DESCRICAO, descricao));

		}

		// Verifica se o campo Grupo do Tipo da Solicitação foi informado

		if (solicitacaoTipoGrupo != null
				&& !solicitacaoTipoGrupo.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO_ID,
					solicitacaoTipoGrupo));

		}

		// Verifica se o campo Indicador de Falta D'Água foi informado

		if (indicadorFaltaAgua != null
				&& !indicadorFaltaAgua.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipo.INDICADOR_FALTA_AGUA,
							indicadorFaltaAgua));

		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroSolicitacaoTipo.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoGrupo");
		
		Collection colecaoSolicitacaoTipo = (Collection) fachada
				.pesquisar(filtroSolicitacaoTipo,
						SolicitacaoTipo.class.getName());

		if (colecaoSolicitacaoTipo == null
				|| colecaoSolicitacaoTipo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Solicitação de Especificações");
		}

		sessao.setAttribute("colecaoSolicitacaoTipo",
				colecaoSolicitacaoTipo);

		return retorno;
	}

}

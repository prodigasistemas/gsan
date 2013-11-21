package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtrar Solicitação Acesso Situação >>
 * 
 * @author: Wallace Thierre
 * @Data: 05/11/2010
 * 
 */
public class FiltrarSolicitacaoAcessoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterSolicitacaoAcessoSituacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarSolicitacaoAcessoSituacaoActionForm form = (FiltrarSolicitacaoAcessoSituacaoActionForm) actionForm;

		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();

		boolean peloMenosUmParametroInformado = false;

		String id = form.getId();
		String descricao = form.getDescricao();
		String indicadorUso = form.getIndicadorUso();
		String codigoSituacao = form.getCodigoSituacao();
		String tipoPesquisa = form.getTipoPesquisa();

		// Id
		if (id != null && !id.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoAcessoSituacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoAcessoSituacao.ID, id));
		}

		// Descrição
		if (descricao != null && !descricao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroSolicitacaoAcessoSituacao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroSolicitacaoAcessoSituacao.DESCRICAO,
								descricao));
			} else {

				filtroSolicitacaoAcessoSituacao
						.adicionarParametro(new ComparacaoTexto(
								FiltroSolicitacaoAcessoSituacao.DESCRICAO,
								descricao));
			}
		}

		// Indicador de Uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoAcessoSituacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoAcessoSituacao.INDICADOR_USO,
							indicadorUso));

		}

		// Indicador de situacao
		if (codigoSituacao != null && !codigoSituacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroSolicitacaoAcessoSituacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO,
							codigoSituacao));

		}

		Collection colecaoSolicitacaoAcessoSituacao = null;

		if (peloMenosUmParametroInformado == true) {

			colecaoSolicitacaoAcessoSituacao = fachada.pesquisar(
					filtroSolicitacaoAcessoSituacao,
					SolicitacaoAcessoSituacao.class.getName());

		}

		// Verificar a existencia de Solicitação Acesso Situação
		if (colecaoSolicitacaoAcessoSituacao == null
				|| colecaoSolicitacaoAcessoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Situação Solicitação Acesso");
		} else {
			httpServletRequest.setAttribute("colecaoSolicitacaoAcessoSituacao",
					colecaoSolicitacaoAcessoSituacao);

			SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = new SolicitacaoAcessoSituacao();

			solicitacaoAcessoSituacao = (SolicitacaoAcessoSituacao) Util
					.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);

			String idRegistroAtualizacao = solicitacaoAcessoSituacao.getId()
					.toString();

			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroSolicitacaoAcessoSituacao",
				filtroSolicitacaoAcessoSituacao);

		httpServletRequest.setAttribute("filtroSolicitacaoAcessoSituacao",
				filtroSolicitacaoAcessoSituacao);

		return retorno;
	}

}

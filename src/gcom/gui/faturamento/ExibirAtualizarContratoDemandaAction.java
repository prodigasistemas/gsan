package gcom.gui.faturamento;

import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.cadastro.imovel.Contrato;
import gcom.cadastro.imovel.FiltroContrato;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoMotivoCancelamento;
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
 * Permite atualizar um contrato de demanda [UC0513] Manter Contrato de Demanda
 * 
 * @author Rafael Corrêa
 * @since 31/03/2006
 */
public class ExibirAtualizarContratoDemandaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarContratoDemanda");

		AtualizarContratoDemandaActionForm atualizarContratoDemandaActionForm = (AtualizarContratoDemandaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroContratoMotivoCancelamento filtroContratoMotivoCancelamento = new FiltroContratoMotivoCancelamento(
				FiltroContratoMotivoCancelamento.DESCRICAO);

		Collection colecaoMotivoCancelamento = fachada.pesquisar(
				filtroContratoMotivoCancelamento,
				ContratoMotivoCancelamento.class.getName());
		
		sessao.setAttribute("colecaoMotivoCancelamento", colecaoMotivoCancelamento);

		// Recupera os valores da unidade do form
		String idImovel = atualizarContratoDemandaActionForm.getIdImovel();
		String inscricaoImovel = atualizarContratoDemandaActionForm
				.getInscricaoImovel();

		// Verifica se o usuário solicitou a pesquisa de unidade
		if (idImovel != null
				&& !idImovel.trim().equals("")
				&& (inscricaoImovel == null || inscricaoImovel.trim()
						.equals(""))) {

			Imovel imovel = fachada.pesquisarImovelDigitado(new Integer(
					idImovel));

			if (imovel != null) {

				atualizarContratoDemandaActionForm.setInscricaoImovel(imovel
						.getInscricaoFormatada());
				httpServletRequest.setAttribute("nomeCampo",
						"dataInicioContrato");

			} else {

				atualizarContratoDemandaActionForm
						.setInscricaoImovel("IMÓVEL INEXISTENTE");
				atualizarContratoDemandaActionForm.setIdImovel("");
				httpServletRequest.setAttribute("existeImovel", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idImovel");

			}

		} else if (inscricaoImovel != null
				&& !inscricaoImovel.trim().equals("")
				&& (idImovel == null || idImovel.trim().equals(""))) {
			atualizarContratoDemandaActionForm.setInscricaoImovel("");
		}

		// Verifica se o usuário está entrando pela primeira vez na tela ou se
		// ele selecionou a opção de desfazer
		if (sessao.getAttribute("contratoDemandaAtualizar") == null
				|| httpServletRequest.getParameter("desfazer") != null) {

			Contrato contratoDemanda = null;

			if (httpServletRequest.getParameter("desfazer") != null) {

				String idContratoDemanda = ((Contrato) sessao
						.getAttribute("contratoDemandaAtualizar")).getId()
						.toString();

				FiltroContrato filtroContratoDemanda = new FiltroContrato();
				filtroContratoDemanda.adicionarParametro(new ParametroSimples(
						FiltroContrato.ID, idContratoDemanda));

				filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
				filtroContratoDemanda
						.adicionarCaminhoParaCarregamentoEntidade("contratoMotivoCancelamento");

				Collection colecaoContratoDemanda = fachada.pesquisar(
						filtroContratoDemanda, Contrato.class.getName());

				if (colecaoContratoDemanda == null
						|| colecaoContratoDemanda.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				contratoDemanda = (Contrato) Util
						.retonarObjetoDeColecao(colecaoContratoDemanda);

				sessao
						.setAttribute("contratoDemandaAtualizar",
								contratoDemanda);

			} else {

				if (sessao.getAttribute("contratoDemanda") != null) {

					contratoDemanda = (Contrato) sessao
							.getAttribute("contratoDemanda");

					sessao.setAttribute("contratoDemandaAtualizar",
							contratoDemanda);
					sessao.removeAttribute("contratoDemanda");

					sessao.setAttribute("filtrar", true);

				} else {

					String idContratoDemanda = httpServletRequest
							.getParameter("contratoDemandaID");

					if (httpServletRequest.getParameter("inserir") != null) {
						sessao.setAttribute("inserir", true);
					} else {
						sessao.removeAttribute("filtrar");
						sessao.removeAttribute("inserir");
					}

					FiltroContrato filtroContratoDemanda = new FiltroContrato();
					filtroContratoDemanda
							.adicionarParametro(new ParametroSimples(
									FiltroContrato.ID, idContratoDemanda));

					filtroContratoDemanda
							.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
					filtroContratoDemanda
							.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
					filtroContratoDemanda
							.adicionarCaminhoParaCarregamentoEntidade("contratoMotivoCancelamento");

					Collection colecaoContratoDemanda = fachada.pesquisar(
							filtroContratoDemanda, Contrato.class
									.getName());

					if (colecaoContratoDemanda == null
							|| colecaoContratoDemanda.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					contratoDemanda = (Contrato) Util
							.retonarObjetoDeColecao(colecaoContratoDemanda);

					sessao.setAttribute("contratoDemandaAtualizar",
							contratoDemanda);

				}

			}

			atualizarContratoDemandaActionForm
					.setNumeroContrato(contratoDemanda.getNumeroContrato());
			atualizarContratoDemandaActionForm.setIdImovel(contratoDemanda
					.getImovel().getId().toString());
			atualizarContratoDemandaActionForm
					.setInscricaoImovel(contratoDemanda.getImovel()
							.getInscricaoFormatada());
			atualizarContratoDemandaActionForm.setDataInicioContrato(Util
					.formatarData(contratoDemanda.getDataContratoInicio()));
			atualizarContratoDemandaActionForm.setDataFimContrato(Util
					.formatarData(contratoDemanda.getDataContratoFim()));

			if (contratoDemanda.getDataContratoEncerrado() != null) {
				atualizarContratoDemandaActionForm.setDataEncerramento(Util
						.formatarData(contratoDemanda
								.getDataContratoEncerrado()));
			} else {
				atualizarContratoDemandaActionForm.setDataEncerramento("");
			}

			if (contratoDemanda.getContratoMotivoCancelamento() != null) {
				atualizarContratoDemandaActionForm
						.setIdMotivoCancelamento(contratoDemanda
								.getContratoMotivoCancelamento().getId()
								.toString());
			} else {
				atualizarContratoDemandaActionForm.setIdMotivoCancelamento(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			}

			httpServletRequest.setAttribute("nomeCampo", "numeroContrato");

		}

		return retorno;

	}

}

package gcom.gui.cobranca;

import gcom.cobranca.CobrancaCriterioLinha;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para adicionar a linha do criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 02/06/2006
 */
public class ExibirAtualizarCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarCriterioCobrancaLinha");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// parâmetro responsável pelo redirecionamento do adicionar criterio
		// cobrança linha
		// se retornarTela estiver o valor inserir retorna para o action de
		// inserir
		// se retornarTela estiver o valor atualizar retorna para o action de
		// atualizar
		String retornarTela = httpServletRequest.getParameter("retornarTela");
		sessao.setAttribute("retornarTela", retornarTela);

		String parmsImovelPerfil = httpServletRequest
				.getParameter("parmsImovelPerfilCobranca");
		String[] arrayImovelPerfilCategoria = parmsImovelPerfil.split(",");

		Integer idImovelPerfil = new Integer(arrayImovelPerfilCategoria[0]);
		Integer idCategoria = new Integer(arrayImovelPerfilCategoria[1]);

		if (sessao.getAttribute("colecaoCobrancaCriterioLinha") != null
				&& !sessao.getAttribute("colecaoCobrancaCriterioLinha").equals(
						"")) {
			Collection colecaoCobrancaCriterioLinha = (Collection) sessao
					.getAttribute("colecaoCobrancaCriterioLinha");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto
			// cobrança critério linha
			String vlMinDebito = "";
			String vlMaxDebito = "";
			String qtdMinContas = "";
			String qtdMaxContas = "";
			String vlMinDebCliente = "";
			String qtdMinConCliente = "";
			String vlMinConMes = "";
			String qdtParcelasMinimas = "";

			Iterator iteratorCobrancaCriterioLinha = colecaoCobrancaCriterioLinha
					.iterator();
			while (iteratorCobrancaCriterioLinha.hasNext()) {
				CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) iteratorCobrancaCriterioLinha
						.next();
				// se o id do imovel perfil que vem do request for igual ao id
				// imovel perfil a coleção de criterio linha e o id da categoria
				// que vem do request for igual ao id categoria a coleção de
				// criterio linha
				if (idImovelPerfil != null
						&& idImovelPerfil.equals(cobrancaCriterioLinha
								.getImovelPerfil().getId())) {
					if (idCategoria != null
							&& idCategoria.equals(cobrancaCriterioLinha
									.getCategoria().getId())) {
						// formata os valores para jogar no form
						if (cobrancaCriterioLinha.getValorMinimoDebito() != null
								&& !cobrancaCriterioLinha
										.getValorMinimoDebito().equals("")) {
							vlMinDebito = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMinimoDebito());
						}
						if (cobrancaCriterioLinha.getValorMaximoDebito() != null
								&& !cobrancaCriterioLinha
										.getValorMaximoDebito().equals("")) {
							vlMaxDebito = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMaximoDebito());
						}
						if (cobrancaCriterioLinha.getQuantidadeMinimaContas() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMinimaContas().equals("")) {
							qtdMinContas = ""
									+ cobrancaCriterioLinha
											.getQuantidadeMinimaContas();
						}
						if (cobrancaCriterioLinha.getQuantidadeMaximaContas() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMaximaContas().equals("")) {
							qtdMaxContas = ""
									+ cobrancaCriterioLinha
											.getQuantidadeMaximaContas();
						}
						if (cobrancaCriterioLinha
								.getValorMinimoDebitoDebitoAutomatico() != null
								&& !cobrancaCriterioLinha
										.getValorMinimoDebitoDebitoAutomatico()
										.equals("")) {
							vlMinDebCliente = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMinimoDebitoDebitoAutomatico());
						}
						if (cobrancaCriterioLinha
								.getQuantidadeMinimaContasDebitoAutomatico() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMinimaContasDebitoAutomatico()
										.equals("")) {
							qtdMinConCliente = ""
									+ cobrancaCriterioLinha
											.getQuantidadeMinimaContasDebitoAutomatico();
						}
						if (cobrancaCriterioLinha.getValorMinimoContaMes() != null
								&& !cobrancaCriterioLinha
										.getValorMinimoContaMes().equals("")) {
							vlMinConMes = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMinimoContaMes());
						}
						if (cobrancaCriterioLinha
								.getQuantidadeMinimaContasParcelamento() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMinimaContasParcelamento()
										.equals("")) {
							qdtParcelasMinimas = cobrancaCriterioLinha
									.getQuantidadeMinimaContasParcelamento()
									.toString();

						} else {
							qdtParcelasMinimas = "0";
						}
						// seta os valores da ultima linha da cobrança criterio
						criterioCobrancaActionForm
								.setValorDebitoMinimo(vlMinDebito);
						criterioCobrancaActionForm
								.setValorDebitoMaximo(vlMaxDebito);
						criterioCobrancaActionForm
								.setQtdContasMinima(qtdMinContas);
						criterioCobrancaActionForm
								.setQtdContasMaxima(qtdMaxContas);
						criterioCobrancaActionForm
								.setVlMinimoDebitoCliente(vlMinDebCliente);
						criterioCobrancaActionForm
								.setQtdMinContasCliente(qtdMinConCliente);
						criterioCobrancaActionForm
								.setVlMinimoContasMes(vlMinConMes);
						criterioCobrancaActionForm
								.setDescricaoImovelPerfil(cobrancaCriterioLinha
										.getImovelPerfil().getDescricao());
						criterioCobrancaActionForm
								.setDescricaoCategoria(cobrancaCriterioLinha
										.getCategoria().getDescricao());
						criterioCobrancaActionForm
								.setQuantidadeMinimaParcelasAtraso(qdtParcelasMinimas);

						sessao.setAttribute("cobrancaCriteriolinha",
								cobrancaCriterioLinha);
					}

				}

			}

			httpServletRequest.setAttribute("fechaPopup", "false");
		}

		return retorno;
	}
}

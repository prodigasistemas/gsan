package gcom.gui.cadastro.tarifasocial;

import java.util.Collection;
import java.util.List;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirInserirTarifaSocialDadosEconomiaAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega uma instancia do actionform
		DynaValidatorForm inserirTarifaSocialActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		int quantidadeEconomiasImovel = 0;

		if (inserirTarifaSocialActionForm.get("nomeRegistroAtendimento") != null
				&& !inserirTarifaSocialActionForm
						.get("nomeRegistroAtendimento").equals("")) {

			quantidadeEconomiasImovel = ((Integer) inserirTarifaSocialActionForm
					.get("qtdEconomia")).intValue();

		} else {
			String idRA = (String) inserirTarifaSocialActionForm
					.get("registroAtendimento");

			RegistroAtendimento registroAtendimento = fachada
					.verificarRegistroAtendimentoTarifaSocial(idRA);

			if (registroAtendimento != null) {

				String idImovel = registroAtendimento.getImovel().getId()
						.toString();

				inserirTarifaSocialActionForm.set("nomeRegistroAtendimento",
						registroAtendimento.getSolicitacaoTipoEspecificacao()
								.getDescricao());

				Collection clientesImoveis = fachada
						.pesquisarClienteImovelPeloImovelParaEndereco(new Integer(
								idImovel));

				if (!clientesImoveis.isEmpty()) {
					ClienteImovel clienteImovel = (ClienteImovel) ((List) clientesImoveis)
							.get(0);

					Imovel imovel = clienteImovel.getImovel();

					if (imovel.getImovelPerfil().getId().intValue() == ImovelPerfil.TARIFA_SOCIAL
							.intValue()) {
						// FS0002 - Verificar imóvel na tarifa social
						throw new ActionServletException(
								"atencao.imovel.associado.registro_atendimento.ja.tarifa_social",
								null, imovel.getId().toString());
					}

					// Obter a quantidade de economias do imóvel escolhido
					quantidadeEconomiasImovel = fachada
							.obterQuantidadeEconomias(imovel);

					// Seta no request
					sessao.setAttribute("clienteImovel", clienteImovel);
					sessao.setAttribute("quantEconomias", String
							.valueOf(quantidadeEconomiasImovel));
					inserirTarifaSocialActionForm.set("qtdEconomia", quantidadeEconomiasImovel);
					httpServletRequest.setAttribute("idImovelRA", idImovel);
					
				} else {
					// Matrícula inexistente
					// httpServletRequest.setAttribute("matriculaInvalida",
					// "Matrícula do imóvel " + idImovel + " inexistente");

					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Matrícula do imóvel " + idImovel);
				}
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Registro de Atendimento");
			}
		}

		// Dependendo da quantidade de economias do imovel, o action será
		// redirecionado para o
		// caso de uso correspondente
		if (quantidadeEconomiasImovel == 1) {
			// Chama o caso de uso [UC0065 - Inserir Dados Tarifa Social - Uma
			// Economia]
			sessao.removeAttribute("colecaoClienteImovelEconomia");
			retorno = actionMapping
					.findForward("inserirTarifaSocialDadosUmaEconomia");

		} else if (quantidadeEconomiasImovel > 1) {
			// Chama o caso de uso [UC0066 - Inserir Dados Tarifa Social - Mais
			// de Uma Economia]
			sessao.removeAttribute("clienteImovel");
			sessao.removeAttribute("colecaoDadosTarifaSocial");
			retorno = actionMapping
					.findForward("inserirTarifaSocialDadosMultiplasEconomias");
		}

		return retorno;
	}

}

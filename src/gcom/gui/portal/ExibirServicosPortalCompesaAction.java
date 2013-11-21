package gcom.gui.portal;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir os Serviços do Portal Da Compesa
 * 
 * @author Diogo Peixoto
 * @date 13/05/2011
 */
public class ExibirServicosPortalCompesaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String retorno = "exibirServicosPortalCompesaAction";

		ExibirServicosPortalCompesaActionForm form = (ExibirServicosPortalCompesaActionForm) actionForm;

		String method = httpServletRequest.getParameter("method");

		HttpSession sessao = httpServletRequest.getSession(false);

		if (method != null) {
			if (method.equalsIgnoreCase("servicos")) {

				String cpfDigitado = httpServletRequest.getParameter("vcpf");

				try {
					Integer matricula = Integer.valueOf(form.getMatricula());

					Integer matriculaExistente = this.getFachada().verificarExistenciaImovel(matricula);

					if (matriculaExistente == 1) {

						sessao.setAttribute("matricula", matricula);
						sessao.removeAttribute("cpfCnpj");

						// Caso o usuário já tenha informado o CPF / CNPJ
						if (Util.verificarNaoVazio(form.getCpfCnpjSolicitante()) && Util.verificarNaoVazio(cpfDigitado)) {
							boolean isValidCpfOrCnpj = false;
							if (form.getCpfCnpjSolicitante().length() == 11) {
								isValidCpfOrCnpj = Util.validacaoCPF(form.getCpfCnpjSolicitante());
							} else if (form.getCpfCnpjSolicitante().length() > 11) {
								isValidCpfOrCnpj = Util.validacaoCNPJ(form.getCpfCnpjSolicitante());
							}

							if (isValidCpfOrCnpj) {

								FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
								filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,
																							matricula));
								filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
								filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
								Collection<ClienteImovel> colecaoClienteImovel = this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

								for (ClienteImovel clienteImovel : colecaoClienteImovel) {

									if (form.getCpfCnpjSolicitante().equals(clienteImovel.getCliente().getCpf())
											|| form.getCpfCnpjSolicitante().equals(clienteImovel.getCliente().getCnpj())) {
										// Pesquisa o nome do usuário e coloca
										// na sessão
										String nomeUsuario = this.getFachada().consultarClienteUsuarioImovel(matricula);
										form.setNomeUsuario(nomeUsuario);
										sessao.setAttribute("nomeUsuario", nomeUsuario);
										sessao.setAttribute("cpfCnpj", form.getCpfCnpjSolicitante());

										httpServletRequest.removeAttribute("solicitarCpfCnpj");
										httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
										httpServletRequest.removeAttribute("cpfCnpjInvalido");

										retorno = "servicosPortalCompesaAction";

										break;
									} else {
										httpServletRequest.setAttribute("solicitarCpfCnpj", true);
										httpServletRequest.setAttribute("cpfCnpjNaoCadastrado", true);
										httpServletRequest.removeAttribute("cpfCnpjInvalido");
									}
								}
							} else {
								httpServletRequest.setAttribute("solicitarCpfCnpj", true);
								httpServletRequest.setAttribute("cpfCnpjInvalido", true);
								httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
							}
						} else {
							// Caso o usuário ainda não tenha informado o CPF /
							// CNPJ
							httpServletRequest.setAttribute("solicitarCpfCnpj", true);
						}
					} else {

						httpServletRequest.setAttribute("nomeCampo", "matricula");
						httpServletRequest.setAttribute("imovelInvalido", true);
					}
				} catch (NumberFormatException e) {
					httpServletRequest.setAttribute("nomeCampo", "matricula");
					httpServletRequest.setAttribute("imovelInvalido", true);
				}
			} else if (method.equalsIgnoreCase("declaracaoAnual")) {
				this.pesquisarAnosImovel((Integer) sessao.getAttribute("matricula"), httpServletRequest, form);
				httpServletRequest.setAttribute("voltarServicos", true);
				retorno = "servicoDeclaracaoAnual";

			} else if (method.equalsIgnoreCase("voltarServico")) {
				retorno = "servicosPortalCompesaAction";
			}
		} else {
			form.setCpfCnpjSolicitante(null);
			sessao.removeAttribute("cpfCnpj");
		}

		if (httpServletRequest.getAttribute("imovelSemQuitacaoAnual") != null) {
			retorno = "servicosPortalCompesaAction";
			httpServletRequest.removeAttribute("voltarServicos");
			httpServletRequest.removeAttribute("exibirDeclaracaoAnual");
		}

		return actionMapping.findForward(retorno);
	}

	/**
	 * @author Magno Gouveia
	 * @date 17/05/2011
	 * @param idImovel,
	 *            httpServletRequest
	 */
	private void pesquisarAnosImovel(Integer idImovel, HttpServletRequest httpServletRequest,
			ExibirServicosPortalCompesaActionForm form) {

		Collection<Object> colecaoAnosImovel = this.getFachada().pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(String.valueOf(idImovel));
		if (colecaoAnosImovel == null || colecaoAnosImovel.isEmpty()) {
			httpServletRequest.setAttribute("imovelSemQuitacaoAnual", true);

		} else {
			httpServletRequest.setAttribute("colecaoAnosImovel", colecaoAnosImovel);

			// Verifica se o usuário digitou o CPF / CNPJ
			httpServletRequest.removeAttribute("exibirDeclaracaoAnual");
			if (form.getCpfCnpjSolicitante() != null) {
				httpServletRequest.setAttribute("exibirDeclaracaoAnual", true);
			} else {
				httpServletRequest.setAttribute("exception", "CPF/CNPJ informado não corresponde com o do cliente");
			}
		}
	}
}
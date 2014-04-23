package gcom.gui.faturamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterGuiaPagamentoAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("manterGuiaPagamento");

		ManterGuiaPagamentoActionForm manterGuiaPagamentoActionForm = (ManterGuiaPagamentoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// -------Parte que trata do código quando o usuário tecla enter

		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
		filtroGuiaPagamento.setCampoOrderBy("anoMesReferenciaContabil",
				"debitoTipo.descricao", "dataVencimento");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAnterior");

		
		// Código do Cliente
		String codigoDigitadoClienteEnter = null;
		
		if (httpServletRequest.getParameter("idCliente") != null
				&& !httpServletRequest.getParameter("idCliente").equals("")) {
			codigoDigitadoClienteEnter = httpServletRequest
					.getParameter("idCliente");
			manterGuiaPagamentoActionForm.setCodigoCliente(codigoDigitadoClienteEnter);
		} else {
			codigoDigitadoClienteEnter = (String) manterGuiaPagamentoActionForm
					.getCodigoCliente();
		}

		// Matrícula do Imóvel
		String codigoDigitadoImovelEnter = null;
		
		if (httpServletRequest.getParameter("idImovel") != null
				&& !httpServletRequest.getParameter("idImovel").equals("")) {
			codigoDigitadoImovelEnter = httpServletRequest
					.getParameter("idImovel");
			manterGuiaPagamentoActionForm.setIdImovel(codigoDigitadoImovelEnter);
		} else {
			codigoDigitadoImovelEnter = (String) manterGuiaPagamentoActionForm
					.getIdImovel();
		}

		// Verifica se o código do imóvel foi digitado
		if (codigoDigitadoImovelEnter != null
				&& !codigoDigitadoImovelEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoImovelEnter) > 0) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, codigoDigitadoImovelEnter));

			Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());

			if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {
				// O imovel foi encontrado
				Imovel imovel = (Imovel) imovelEncontrado.iterator().next();

				FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();

				filtroImovelCobrancaSituacao
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
				filtroImovelCobrancaSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroImovelCobrancaSituacao.IMOVEL_ID, imovel
										.getId()));

				Collection imovelCobrancaSituacaoEncontrada = fachada
						.pesquisar(filtroImovelCobrancaSituacao,
								ImovelCobrancaSituacao.class.getName());

				if (imovel.getIndicadorExclusao() != null
						&& imovel.getIndicadorExclusao().equals(
								Imovel.IMOVEL_EXCLUIDO)) {
					throw new ActionServletException("atencao.imovel.excluido");
				}

				// Verifica se o imóvel tem débito em cobrança administrativa
				if (imovelCobrancaSituacaoEncontrada != null
						&& !imovelCobrancaSituacaoEncontrada.isEmpty()) {

					ImovelCobrancaSituacao imovelCobrancaSituacao = new ImovelCobrancaSituacao();

					sessao.setAttribute("imovelCobrancaSituacao",
							imovelCobrancaSituacao);

					if (imovelCobrancaSituacao.getCobrancaSituacao() != null) {

						if (imovelCobrancaSituacao
								.getCobrancaSituacao()
								.getId()
								.equals(
										CobrancaSituacao.COBRANCA_ADMINISTRATIVA)
								&& imovelCobrancaSituacao
										.getDataRetiradaCobranca() == null) {
							throw new ActionServletException(
									"atencao.pesquisa.imovel.cobranca_administrativa");
						}
					}
				}

				manterGuiaPagamentoActionForm.setIdImovel(""
						+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());
				manterGuiaPagamentoActionForm.setInscricaoImovel(""
						+ ((Imovel) ((List) imovelEncontrado).get(0))
								.getInscricaoFormatada());
				manterGuiaPagamentoActionForm
						.setSituacaoAgua(((Imovel) ((List) imovelEncontrado)
								.get(0)).getLigacaoAguaSituacao()
								.getDescricao());
				manterGuiaPagamentoActionForm
						.setSituacaoEsgoto(((Imovel) ((List) imovelEncontrado)
								.get(0)).getLigacaoEsgotoSituacao()
								.getDescricao());

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID,
						codigoDigitadoImovelEnter));
				Collection clientesImovelEncontrado = fachada.pesquisar(
						filtroClienteImovel, ClienteImovel.class.getName());
				ClienteImovel clienteImovel = null;

				if (clientesImovelEncontrado != null
						&& !clientesImovelEncontrado.isEmpty()) {
					// O cliente imovel foi encontrado

					Iterator clienteImovelEncontradoIterator = clientesImovelEncontrado
							.iterator();

					while (clienteImovelEncontradoIterator.hasNext()) {
						clienteImovel = (ClienteImovel) clienteImovelEncontradoIterator
								.next();

						if (clienteImovel.getClienteRelacaoTipo().getId()
								.equals(ClienteRelacaoTipo.USUARIO)) {
							break;
						}
					}
					manterGuiaPagamentoActionForm
							.setNomeClienteUsuario(clienteImovel.getCliente()
									.getNome());
				}

				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
						FiltroGuiaPagamento.IMOVEL_ID, imovel.getId()));
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
						FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
						DebitoCreditoSituacao.NORMAL));
				filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
				Collection guiasPagamentos = fachada.pesquisar(
						filtroGuiaPagamento, GuiaPagamento.class.getName());

				if (guiasPagamentos != null && !guiasPagamentos.isEmpty()) {

					SistemaParametro sistemaParametro = fachada
							.pesquisarParametrosDoSistema();
					
					if (sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() != null
							&& sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
					
					Collection<Integer> idsGuiasBloquear = fachada
						.verificarBloqueioGuiaPagamento(guiasPagamentos);
					
					Iterator iterator = guiasPagamentos.iterator();
					
					Collection guiasNaoBloqueadas = new ArrayList(); 
					
					while (iterator.hasNext()) {
						GuiaPagamento guiaPagamento = (GuiaPagamento) iterator.next();
						
						if (!idsGuiasBloquear.contains(guiaPagamento.getId())) {
							guiasNaoBloqueadas.add(guiaPagamento);
						}
					}

					if (guiasPagamentos != null && !guiasPagamentos.isEmpty()) {
						
						sessao.setAttribute("guiasPagamentos", guiasNaoBloqueadas);
						
					} else {
						
						throw new ActionServletException(
								"atencao.guia_pagamento.imovel.inexistente", null,
								"" + imovel.getId());
					}
				
					} else {
						
						sessao.setAttribute("guiasPagamentos", guiasPagamentos);
					}

				} else {
					throw new ActionServletException(
							"atencao.guia_pagamento.imovel.inexistente", null,
							"" + imovel.getId());
				}

			} else {
				manterGuiaPagamentoActionForm.setIdImovel("");
				throw new ActionServletException(
						"atencao.pesquisa.imovel.inexistente.guia");

			}
		}

		// Verifica se o do cliente código foi digitado
		if (codigoDigitadoClienteEnter != null
				&& !codigoDigitadoClienteEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoClienteEnter) > 0) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
			filtroCliente
					.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			filtroCliente
					.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, codigoDigitadoClienteEnter));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List) clienteEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",
							null, ""
									+ ((Cliente) ((List) clienteEncontrado)
											.get(0)).getId());
				}

				manterGuiaPagamentoActionForm
						.setNomeCliente(((Cliente) ((List) clienteEncontrado)
								.get(0)).getNome());

				if (((Cliente) ((List) clienteEncontrado).get(0))
						.getClienteTipo().getIndicadorPessoaFisicaJuridica()
						.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
					manterGuiaPagamentoActionForm
							.setCpf(((Cliente) ((List) clienteEncontrado)
									.get(0)).getCpfFormatado());
					manterGuiaPagamentoActionForm
							.setProfissao(((Cliente) ((List) clienteEncontrado)
									.get(0)).getProfissao() == null ? ""
									: ((Cliente) ((List) clienteEncontrado)
											.get(0)).getProfissao()
											.getDescricao());

				} else {
					manterGuiaPagamentoActionForm
							.setCpf(((Cliente) ((List) clienteEncontrado)
									.get(0)).getCnpjFormatado());
					manterGuiaPagamentoActionForm
							.setRamoAtividade(((Cliente) ((List) clienteEncontrado)
									.get(0)).getRamoAtividade() == null ? ""
									: ((Cliente) ((List) clienteEncontrado)
											.get(0)).getRamoAtividade()
											.getDescricao());
				}

				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
						FiltroGuiaPagamento.CLIENTE_ID,
						((Cliente) ((List) clienteEncontrado).get(0)).getId()));
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
						FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
						DebitoCreditoSituacao.NORMAL));
				filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
				Collection guiasPagamentos = fachada.pesquisar(
						filtroGuiaPagamento, GuiaPagamento.class.getName());

				if (guiasPagamentos != null && !guiasPagamentos.isEmpty()) {
					
					SistemaParametro sistemaParametro = fachada
						.pesquisarParametrosDoSistema();
					
					if (sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() != null
							&& sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
						
						Collection<Integer> idsGuiasBloquear = fachada
							.verificarBloqueioGuiaPagamento(guiasPagamentos);
						
						Iterator iterator = guiasPagamentos.iterator();
						
						Collection guiasNaoBloqueadas = new ArrayList(); 
						
						while (iterator.hasNext()) {
							GuiaPagamento guiaPagamento = (GuiaPagamento) iterator.next();
							
							if (!idsGuiasBloquear.contains(guiaPagamento.getId())) {
								guiasNaoBloqueadas.add(guiaPagamento);
							}
						}
		
						if (guiasPagamentos != null && !guiasPagamentos.isEmpty()) {
							
							sessao.setAttribute("guiasPagamentos", guiasNaoBloqueadas);
							
						} else {
							
							manterGuiaPagamentoActionForm.setCodigoCliente("");
							throw new ActionServletException(
									"atencao.guia_pagamento.cliente.inexistente", null,
									""
											+ ((Cliente) ((List) clienteEncontrado)
													.get(0)).getId());
						}
				
					} else {
						
						sessao.setAttribute("guiasPagamentos", guiasPagamentos);
					}

					
				} else {
					manterGuiaPagamentoActionForm.setCodigoCliente("");
					throw new ActionServletException(
							"atencao.guia_pagamento.cliente.inexistente", null,
							""
									+ ((Cliente) ((List) clienteEncontrado)
											.get(0)).getId());

				}
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.cliente.inexistente.guia");
			}

		}

		return retorno;
	}
}

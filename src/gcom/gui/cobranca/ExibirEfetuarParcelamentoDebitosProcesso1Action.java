package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * Permite efetuar o parcelamento dos débitos de um imóvel
 * 
 * [UC0214] Efetuar Parcelamento de Débitos
 * 
 * Pré-processamento da primeira página
 * 
 * @author Rodrigo Avellar/Roberta Costa
 * @date 11/02/2006
 */
public class ExibirEfetuarParcelamentoDebitosProcesso1Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo1");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// -----------------------------------------------------------
		// Verificar as permissões especiais da página
		verificarPermissoes(httpServletRequest, fachada, usuario);
		// -----------------------------------------------------------

		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;

		// Pega o codigo que o usuario digitou para a pesquisa direta de imovel
		String codigoImovel = (String) httpServletRequest.getParameter("matriculaImovel");
		String codigoImovelAntes = (String) efetuarParcelamentoDebitosActionForm.get("codigoImovelAntes");

		// Testa o CPF digitado
		String cpf = (String) efetuarParcelamentoDebitosActionForm.get("cpfClienteParcelamentoDigitado");

		// O usuário é pessoa física
		if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {

			boolean igual = true;
			Integer i = 0;

			Integer tam = cpf.length() - 1;

			while (i < tam) {
				if ((cpf.charAt(i)) != (cpf.charAt(i + 1))) {
					igual = false;
					break;
				} else {
					i++;
				}
			}

			if (igual) {
				throw new ActionServletException("atencao.cpf_invalido");
			}
		}

		// Pesquisa de Cliente
		String idClienteParcelamento = (String) efetuarParcelamentoDebitosActionForm.get("idClienteParcelamento");
		String cpfClienteParcelamento = (String) efetuarParcelamentoDebitosActionForm.get("cpfClienteParcelamento");
		if (idClienteParcelamento != null && !idClienteParcelamento.equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idClienteParcelamento));

			Collection clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());

			if (clientes != null && !clientes.isEmpty()) {
				Cliente cliente = (Cliente) ((List) clientes).get(0);

				efetuarParcelamentoDebitosActionForm.set("nomeClienteParcelamento", cliente.getNome());
				efetuarParcelamentoDebitosActionForm.set("foneClienteParcelamento", fachada.pesquisarClienteFonePrincipal(cliente.getId()));
				efetuarParcelamentoDebitosActionForm.set("cpfClienteParcelamento", cliente.getCpfFormatado());
				efetuarParcelamentoDebitosActionForm.set("cpfClienteParcelamentoDigitado", "");

				if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {

					httpServletRequest.setAttribute("cpfCliente", "true");
				}

			} else {
				httpServletRequest.setAttribute("clienteInexistente", "true");
				efetuarParcelamentoDebitosActionForm.set("idClienteParcelamento", "");
				efetuarParcelamentoDebitosActionForm.set("nomeClienteParcelamento", "CLIENTE INEXISTENTE");
				efetuarParcelamentoDebitosActionForm.set("cpfClienteParcelamento", "");
				efetuarParcelamentoDebitosActionForm.set("cpfClienteParcelamentoDigitado", "");

			}
		} else {
			efetuarParcelamentoDebitosActionForm.set("nomeClienteParcelamento", "");

			if (cpfClienteParcelamento != null && !cpfClienteParcelamento.equals("")) {
				efetuarParcelamentoDebitosActionForm.set("cpfClienteParcelamento", Util.formatarCPFApresentacao(cpfClienteParcelamento));
				Cliente clienteParcelamento = fachada.obterIdENomeCliente(cpfClienteParcelamento);

				if (clienteParcelamento != null) {
					httpServletRequest.setAttribute("cpfCliente", "true");
					efetuarParcelamentoDebitosActionForm.set("idClienteParcelamento", clienteParcelamento.getId().toString());
					efetuarParcelamentoDebitosActionForm.set("nomeClienteParcelamento", clienteParcelamento.getNome());
					efetuarParcelamentoDebitosActionForm.set("foneClienteParcelamento", fachada.pesquisarClienteFonePrincipal(clienteParcelamento.getId()));
				} else {
					efetuarParcelamentoDebitosActionForm.set("cpfClienteParcelamento", "");
					efetuarParcelamentoDebitosActionForm.set("cpfClienteParcelamentoDigitado", "");
					httpServletRequest.setAttribute("cpfInexistente", "CPF INEXISTENTE");
				}
			}
		}

		String inscricaoImovel = null;
		if (httpServletRequest.getParameter("inscricaoImovel") != null && !httpServletRequest.getParameter("inscricaoImovel").equals("")) {
			inscricaoImovel = (String) httpServletRequest.getParameter("inscricaoImovel");
		}
		if (codigoImovel != null && !codigoImovel.trim().equals("") && inscricaoImovel == null) {
			// Pesquisa os dados do cliente e do imóvel
			boolean existeImovel = pesquisarImovel(codigoImovel, actionForm, httpServletRequest, sessao, usuario);

			if (existeImovel) {
				// [FS0012] Verificar existência de parcelamento no mês
				Collection<Parcelamento> colecaoParcelamento = fachada.verificarParcelamentoMesImovel(new Integer(codigoImovel));

				if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
					throw new ActionServletException("atencao.debito.ja.parcelado.mes.faturamento.corrente");
				}

				FaturamentoGrupo grupo = fachada.pesquisarGrupoImovel(Integer.valueOf(codigoImovel));
				int referenciaCronograma = fachada.pesquisarMaiorAnoMesReferenciaCronogramaGrupoFaturamentoMensal(grupo.getId());

				// [UC0067] Obter Débito do Imóvel ou Cliente
				ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada.obterDebitoImovelOuCliente(1, // Indicador
																												// débito
																												// imóvel
						codigoImovel, // Matrícula do imóvel
						null, // Código do cliente
						null, // Tipo de relação do cliento com o
						// imóvel
						"000101", // Referência inicial do débito
						String.valueOf(referenciaCronograma), // Referência
																// final do
																// débito
						Util.converteStringParaDate("01/01/0001"), // Inicio
						// Vencimento
						Util.converteStringParaDate("31/12/9999"), // Final
						// Vencimento
						1, // Indicador pagamento
						1, // Indicador conta em revisão
						1, // Indicador débito a cobrar
						1, // Indicador crédito a realizar
						1, // Indicador notas promissórias
						1, // Indicador guias de pagamento
						1, // Indicador acréscimos por impontualidade
						null); // Indicador Contas

				// [FS0014] Verificar existência de débitos para o imóvel
				// Caso não exista débito
				if ((colecaoDebitoImovel.getColecaoContasValoresImovel() == null || colecaoDebitoImovel.getColecaoContasValoresImovel().size() == 0)
						&& (colecaoDebitoImovel.getColecaoContasValoresPreteritos() == null || colecaoDebitoImovel.getColecaoContasValoresPreteritos().size() == 0)
						&& (colecaoDebitoImovel.getColecaoGuiasPagamentoValores() == null || colecaoDebitoImovel.getColecaoGuiasPagamentoValores().size() == 0)
						&& (colecaoDebitoImovel.getColecaoDebitoACobrar() == null || colecaoDebitoImovel.getColecaoDebitoACobrar().size() == 0)) {
					throw new ActionServletException("atencao.imovel.sem.debitos", null, codigoImovel);
				}

				// [FS0015] Verificar existência de contas
				// Caso não existam contas para o imóvel deixar indisponível
				// o campo mês/ano
				// de referência inicial e mês/ano de referência final
				if ((colecaoDebitoImovel.getColecaoContasValoresImovel() == null || colecaoDebitoImovel.getColecaoContasValoresImovel().size() == 0)
						&& (colecaoDebitoImovel.getColecaoContasValoresPreteritos() == null || colecaoDebitoImovel.getColecaoContasValoresPreteritos().size() == 0)) {
					sessao.setAttribute("bloqueiaIntervaloParcelamento", "sim");
					efetuarParcelamentoDebitosActionForm.set("inicioIntervaloParcelamento", "");
					efetuarParcelamentoDebitosActionForm.set("fimIntervaloParcelamento", "");

				}

				// Para o cálculo do Débito Total Atualizado
				BigDecimal valorTotalContas = new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidade = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteServicosACobrar = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteServicosACobrarCurtoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteServicosACobrarLongoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalGuiasPagamento = new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidadeContas = new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidadeGuias = new BigDecimal("0.00");
				BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
				BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
				BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
				BigDecimal valorJurosMora = new BigDecimal("0.00");
				BigDecimal valorMulta = new BigDecimal("0.00");

				// Dados do Débito do Imóvel - Contas
				Collection<ContaValoresHelper> colecaoContasImovel = new ArrayList<ContaValoresHelper>();

				ContaValoresHelper contaRemovida = null;

				if ((colecaoDebitoImovel.getColecaoContasValoresImovel() != null && !colecaoDebitoImovel.getColecaoContasValoresImovel().isEmpty())
						|| (colecaoDebitoImovel.getColecaoContasValoresPreteritos() != null && !colecaoDebitoImovel.getColecaoContasValoresPreteritos()
								.isEmpty())) {

					colecaoContasImovel.addAll(colecaoDebitoImovel.getColecaoContasValoresImovel());
					colecaoContasImovel.addAll(colecaoDebitoImovel.getColecaoContasValoresPreteritos());

					Iterator contaValores = colecaoContasImovel.iterator();

					while (contaValores.hasNext()) {

						ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();

						if (verificaReferenciaIgualReferencialFaturamento(contaValoresHelper.getConta().getAnoMesReferenciaConta())) {
							contaRemovida = contaValoresHelper;
							continue;
						}

						valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());
						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(
									Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null && !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,
									Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,
									Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para cálculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper
								.getValorTotalContaValoresParcelamento());

					}

					sessao.setAttribute("colecaoContaValoresImovel", contaRemovida != null ? colecaoContasImovel.remove(contaRemovida) : colecaoContasImovel);

					efetuarParcelamentoDebitosActionForm.set("valorTotalContasImovel", Util.formatarMoedaReal(valorTotalContas));
				} else {

					efetuarParcelamentoDebitosActionForm.set("valorTotalContasImovel", "0,00");
				}

				// Guias de Pagamento
				Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresImovel = colecaoDebitoImovel.getColecaoGuiasPagamentoValores();
				Collection<GuiaPagamentoValoresHelper> guiasRemovidas = new ArrayList<GuiaPagamentoValoresHelper>();

				if (colecaoGuiaPagamentoValoresImovel != null && !colecaoGuiaPagamentoValoresImovel.isEmpty()) {
					Iterator guiaPagamentoValores = colecaoGuiaPagamentoValoresImovel.iterator();
					while (guiaPagamentoValores.hasNext()) {

						GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) guiaPagamentoValores.next();

						if (verificaReferenciaIgualReferencialFaturamento(Util.recuperaAnoMesDaData(guiaPagamentoValoresHelper.getGuiaPagamento()
								.getDataEmissao()))) {
							guiasRemovidas.add(guiaPagamentoValoresHelper);
							continue;
						}

						if (guiaPagamentoValoresHelper.getGuiaPagamento() != null && !guiaPagamentoValoresHelper.getGuiaPagamento().equals("")) {
							valorTotalGuiasPagamento.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalGuiasPagamento = valorTotalGuiasPagamento.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()
									.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null
								&& !guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(
									Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorJurosMora() != null && !guiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(guiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,
									Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorMulta() != null && !guiaPagamentoValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(guiaPagamentoValoresHelper.getValorMulta());
						}

						// Para cálculo do Acrescimo de Impontualidade
						if (guiaPagamentoValoresHelper.getValorAcrescimosImpontualidade() != null
								&& !guiaPagamentoValoresHelper.getValorAcrescimosImpontualidade().equals("")) {
							valorTotalAcrescimoImpontualidadeGuias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalAcrescimoImpontualidadeGuias = valorTotalAcrescimoImpontualidadeGuias.add(guiaPagamentoValoresHelper
									.getValorAcrescimosImpontualidade());
						}
					}

					if (!guiasRemovidas.isEmpty())
						colecaoGuiaPagamentoValoresImovel.removeAll(guiasRemovidas);

					sessao.setAttribute("colecaoGuiaPagamentoValoresImovel", colecaoGuiaPagamentoValoresImovel);

					efetuarParcelamentoDebitosActionForm.set("valorGuiasPagamentoImovel", Util.formatarMoedaReal(valorTotalGuiasPagamento));
				} else {
					efetuarParcelamentoDebitosActionForm.set("valorGuiasPagamentoImovel", "0,00");
				}

				// Acrescimos por Impotualidade
				BigDecimal retornoSoma = new BigDecimal("0.00");
				retornoSoma.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
				retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeGuias);

				sessao.setAttribute("valorAcrescimosImpontualidadeImovel", retornoSoma);
				efetuarParcelamentoDebitosActionForm.set("valorAcrescimosImpontualidadeImovel", Util.formatarMoedaReal(retornoSoma));

				// Para o cálculo do Débito Total Atualizado
				valorTotalAcrescimoImpontualidade = retornoSoma;

				// Debitos A Cobrar
				Collection colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();
				Collection<DebitoACobrar> debitosRemovidos = new ArrayList<DebitoACobrar>();

				if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
					Iterator debitoACobrarValores = colecaoDebitoACobrar.iterator();

					final int indiceCurtoPrazo = 0;
					final int indiceLongoPrazo = 1;

					while (debitoACobrarValores.hasNext()) {
						DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();

						if (verificaReferenciaIgualReferencialFaturamento(Util.recuperaAnoMesDaData(debitoACobrar.getGeracaoDebito()))) {
							debitosRemovidos.add(debitoACobrar);
							continue;
						}

						// [FS0022]-Verificar existência de juros sobre imóvel
						if (debitoACobrar.getDebitoTipo().getId() != null && !debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)) {

							// Debitos A Cobrar - Serviço
							if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.SERVICO_NORMAL)) {
								// [SB0001] Obter Valores de Curto e Longo Prazo
								valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();

								BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(debitoACobrar.getNumeroPrestacaoDebito(),
										debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(), valorRestanteACobrar);
								valorTotalRestanteServicosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteServicosACobrarCurtoPrazo = valorTotalRestanteServicosACobrarCurtoPrazo
										.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
								valorTotalRestanteServicosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteServicosACobrarLongoPrazo = valorTotalRestanteServicosACobrarLongoPrazo
										.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
							}

							// Debitos A Cobrar - Parcelamento
							if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
									|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
									|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
								// [SB0001] Obter Valores de Curto e Longo Prazo
								valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();

								BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(debitoACobrar.getNumeroPrestacaoDebito(),
										debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(), valorRestanteACobrar);
								valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo
										.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
								valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo
										.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
							}

						}

					}

					if (!debitosRemovidos.isEmpty())
						colecaoDebitoACobrar.removeAll(debitosRemovidos);

					sessao.setAttribute("colecaoDebitoACobrarImovel", colecaoDebitoACobrar);

					// Serviços
					valorTotalRestanteServicosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalRestanteServicosACobrar = valorTotalRestanteServicosACobrarCurtoPrazo.add(valorTotalRestanteServicosACobrarLongoPrazo);
					// Está no hidden no formulário
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoLongoPrazo",
							Util.formatarMoedaReal(valorTotalRestanteServicosACobrarLongoPrazo));
					// Está no hidden no formulário
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoCurtoPrazo",
							Util.formatarMoedaReal(valorTotalRestanteServicosACobrarCurtoPrazo));
					// Está no TEXT
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoImovel", Util.formatarMoedaReal(valorTotalRestanteServicosACobrar));
					// Parcelamentos
					valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo
							.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
					// Está no hidden no formulário
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoLongoPrazo",
							Util.formatarMoedaReal(valorTotalRestanteParcelamentosACobrarLongoPrazo));
					// Está no hidden no formulário
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoCurtoPrazo",
							Util.formatarMoedaReal(valorTotalRestanteParcelamentosACobrarCurtoPrazo));
					// Está no TEXT
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoImovel",
							Util.formatarMoedaReal(valorTotalRestanteParcelamentosACobrar));
				} else {
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoImovel", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoImovel", "0,00");
				}

				// Crédito A Realizar
				Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoImovel.getColecaoCreditoARealizar();
				Collection<CreditoARealizar> creditosRemovidos = new ArrayList<CreditoARealizar>();
				if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
					Iterator creditoARealizarValores = colecaoCreditoARealizar.iterator();
					while (creditoARealizarValores.hasNext()) {
						CreditoARealizar creditoARealizar = (CreditoARealizar) creditoARealizarValores.next();
						if (verificaReferenciaIgualReferencialFaturamento(Util.recuperaAnoMesDaData(creditoARealizar.getGeracaoCredito()))) {
							creditosRemovidos.add(creditoARealizar);
							continue;
						}
						valorCreditoARealizar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorCreditoARealizar = valorCreditoARealizar.add(creditoARealizar.getValorTotalComBonus());
					}

					if (!creditosRemovidos.isEmpty())
						colecaoCreditoARealizar.removeAll(creditosRemovidos);
					sessao.setAttribute("colecaoCreditoARealizarImovel", colecaoCreditoARealizar);

					efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizarImovel", Util.formatarMoedaReal(valorCreditoARealizar));
				} else {
					efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizarImovel", "0,00");
				}

				// Débito Total Atualizado
				BigDecimal debitoTotalAtualizado = new BigDecimal("0.00");

				debitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalContas);
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalGuiasPagamento);
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalAcrescimoImpontualidade);
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalRestanteServicosACobrar);
				debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalRestanteParcelamentosACobrar);
				debitoTotalAtualizado = debitoTotalAtualizado.subtract(valorCreditoARealizar);

				sessao.setAttribute("valorDebitoTotalAtualizadoImovel", debitoTotalAtualizado);
				efetuarParcelamentoDebitosActionForm.set("valorDebitoTotalAtualizadoImovel", Util.formatarMoedaReal(debitoTotalAtualizado));

				// Quando mudar de imóvel iniciar a data do parcelamento com a
				// data atual, limpar
				// a resolução de diretoria
				if (!codigoImovelAntes.equals(codigoImovel)) {
					// Reinicia a Data do Parcelamento
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					Calendar dataCorrente = new GregorianCalendar();

					efetuarParcelamentoDebitosActionForm.set("dataParcelamento", "" + formatoData.format(dataCorrente.getTime()));

					// Limpa Resolução de Diretoria
					efetuarParcelamentoDebitosActionForm.set("resolucaoDiretoria", "");

					efetuarParcelamentoDebitosActionForm.set("inicioIntervaloParcelamento", "");
					// Limpa fim do Intervalo do Parcelamento
					efetuarParcelamentoDebitosActionForm.set("fimIntervaloParcelamento", "");

					sessao.setAttribute("bloqueiaIntervaloParcelamento", "nao");

					// Limpa as perguntas
					efetuarParcelamentoDebitosActionForm.set("indicadorContasRevisao", "");
					efetuarParcelamentoDebitosActionForm.set("indicadorGuiasPagamento", "");
					efetuarParcelamentoDebitosActionForm.set("indicadorAcrescimosImpotualidade", "");
					efetuarParcelamentoDebitosActionForm.set("indicadorDebitosACobrar", "");
					efetuarParcelamentoDebitosActionForm.set("indicadorCreditoARealizar", "");
				}

				// Intervalo do Parcelamento
				if (colecaoContasImovel != null && colecaoContasImovel.size() != 0) {
					Iterator contaValores = colecaoContasImovel.iterator();
					int anoMesReferenciaColecao = 0;
					int menorAnoMesReferencia = 999999;
					int maiorAnoMesReferencia = 0;

					while (contaValores.hasNext()) {
						ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
						anoMesReferenciaColecao = contaValoresHelper.getConta().getReferencia();
						if (anoMesReferenciaColecao < menorAnoMesReferencia) {
							menorAnoMesReferencia = anoMesReferenciaColecao;
						}
						if (anoMesReferenciaColecao > maiorAnoMesReferencia) {
							maiorAnoMesReferencia = anoMesReferenciaColecao;
						}
					}

					// Quando não houver intervalo de parcelamento inicial e
					// final
					if (menorAnoMesReferencia != 0) {
						if ((efetuarParcelamentoDebitosActionForm.get("inicioIntervaloParcelamento") == null || efetuarParcelamentoDebitosActionForm.get(
								"inicioIntervaloParcelamento").equals(""))) {
							sessao.setAttribute("bloqueiaIntervaloParcelamento", "nao");
							efetuarParcelamentoDebitosActionForm.set("inicioIntervaloParcelamento", Util.formatarAnoMesParaMesAno(menorAnoMesReferencia));
						}
					}

					if (maiorAnoMesReferencia != 0) {
						if ((efetuarParcelamentoDebitosActionForm.get("fimIntervaloParcelamento") == null || efetuarParcelamentoDebitosActionForm.get(
								"fimIntervaloParcelamento").equals(""))) {
							efetuarParcelamentoDebitosActionForm.set("fimIntervaloParcelamento", Util.formatarAnoMesParaMesAno(maiorAnoMesReferencia));
							sessao.setAttribute("bloqueiaIntervaloParcelamento", "nao");
						}
					} else {
						efetuarParcelamentoDebitosActionForm.set("fimIntervaloParcelamento", "00/0000");
					}
				} else {
					// [FS0015] Verificar existência de contas
					// Caso não existam contas para o imóvel deixar indisponível
					// o campo mês/ano
					// de referência inicial e mês/ano de referência final
					efetuarParcelamentoDebitosActionForm.set("inicioIntervaloParcelamento", "");
					efetuarParcelamentoDebitosActionForm.set("fimIntervaloParcelamento", "");
					sessao.setAttribute("bloqueiaIntervaloParcelamento", "sim");
				}

				efetuarParcelamentoDebitosActionForm.set("valorAtualizacaoMonetariaImovel", Util.formatarMoedaReal(valorAtualizacaoMonetaria));
				efetuarParcelamentoDebitosActionForm.set("valorJurosMoraImovel", Util.formatarMoedaReal(valorJurosMora));
				efetuarParcelamentoDebitosActionForm.set("valorMultaImovel", Util.formatarMoedaReal(valorMulta));
				efetuarParcelamentoDebitosActionForm.set("matriculaImovel", codigoImovel);
			}

			// Atualizando o código do imóvel na varíavel hidden do formulário
			codigoImovelAntes = codigoImovel;
			efetuarParcelamentoDebitosActionForm.set("codigoImovelAntes", codigoImovelAntes);
		}

		// Coloca a data Atual na data de Parecelamento
		if (efetuarParcelamentoDebitosActionForm.get("dataParcelamento").equals("") || efetuarParcelamentoDebitosActionForm.get("dataParcelamento") == null) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataCorrente = new GregorianCalendar();

			efetuarParcelamentoDebitosActionForm.set("dataParcelamento", "" + formatoData.format(dataCorrente.getTime()));
		}

		// Pesquisa a Resolução de Diretoria
		Collection<ResolucaoDiretoria> colecaoResolucaoDiretoria = new ArrayList<ResolucaoDiretoria>();

		// [FS0016] - Verificar se o usuário possui autorização para utilizar a
		// RD
		if ((Boolean) httpServletRequest.getAttribute("temPermissaoResolucaoDiretoria")) {

			// Colocado por Raphael Rossiter em 12/11/2008 - Analista: Eduardo
			// Borges
			colecaoResolucaoDiretoria = fachada.pesquisarResolucaoDiretoriaMaiorDataVigenciaInicioPermissaoEspecial();

		} else {
			colecaoResolucaoDiretoria = fachada.pesquisarResolucaoDiretoriaMaiorDataVigenciaInicio();
		}

		// Verifica se existe Resolução de Diretoria
		if (colecaoResolucaoDiretoria != null && colecaoResolucaoDiretoria.isEmpty()) {
			throw new ActionServletException("atencao.resolucao_diretoria.inexistente");
		} else {
			httpServletRequest.setAttribute("colecaoResolucaoDiretoria", colecaoResolucaoDiretoria);
		}

		return retorno;
	}

	/**
	 * Verifica as permissões especiais para a primeira página de Efetuar
	 * Parcelamento Débitos
	 * 
	 * @author Rodrigo Silveira
	 * @date 07/11/2006
	 * 
	 * @param httpServletRequest
	 * @param fachada
	 * @param usuario
	 */
	private void verificarPermissoes(HttpServletRequest httpServletRequest, Fachada fachada, Usuario usuario) {

		boolean temPermissaoAcrescimoImpontualidade = fachada.verificarPermissaoEspecial(PermissaoEspecial.PARCELAR_SEM_INCLUIR_ACRESCIMOS_POR_IMPONTUALIDADE,
				usuario);

		boolean temPermissaoDebitoACobrar = fachada.verificarPermissaoEspecial(PermissaoEspecial.PARCELAR_SEM_INCLUIR_DEBITO_A_COBRAR, usuario);

		boolean temPermissaoResolucaoDiretoria = fachada.verificarPermissaoEspecial(PermissaoEspecial.USAR_PLANO_PAI_PARA_ORGAO_PUBLICO, usuario);

		httpServletRequest.setAttribute("temPermissaoAcrescimoImpontualidade", temPermissaoAcrescimoImpontualidade);
		httpServletRequest.setAttribute("temPermissaoDebitoACobrar", temPermissaoDebitoACobrar);
		httpServletRequest.setAttribute("temPermissaoResolucaoDiretoria", temPermissaoResolucaoDiretoria);

	}

	/**
	 * Pesquisa um imovel informado e prepara os dados para exibição na tela
	 * 
	 * @author Rodrigo Avellar/Roberta Costa
	 * @created 11/02/2006
	 */
	private boolean pesquisarImovel(String idImovel, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpSession sessao, Usuario usuario) {

		boolean existeImovel = true;

		Fachada fachada = Fachada.getInstancia();

		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;

		// Pesquisa o imovel na base
		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("areaConstruidaFaixa");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoTipo");

		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CONSUMO_TARIFA);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);
		Collection<Imovel> imovelPesquisado = fachada.pesquisarImovelEfetuarParcelamento(filtroImovel, usuarioLogado);

		// Verificar existêncioa da matrícula do imóvel
		if (imovelPesquisado == null || imovelPesquisado.isEmpty()) {
			efetuarParcelamentoDebitosActionForm.set("matriculaImovel", "");
			efetuarParcelamentoDebitosActionForm.set("inscricaoImovel", "MATRÍCULA INEXISTENTE");
			httpServletRequest.setAttribute("nomeCampo", "matriculaImovel");
			httpServletRequest.setAttribute("idImovelNaoEncontrado", "exception");
			existeImovel = false;
		} else {
			// Verifica se o Imovel está Excluído
			Imovel imovel = imovelPesquisado.iterator().next();
			if (imovel.getIndicadorExclusao() == Imovel.IMOVEL_EXCLUIDO) {
				efetuarParcelamentoDebitosActionForm.set("matriculaImovel", "");
				efetuarParcelamentoDebitosActionForm.set("inscricaoImovel", "MATRÍCULA INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "matriculaImovel");
				httpServletRequest.setAttribute("idImovelNaoEncontrado", "exception");
				existeImovel = false;
			} else {
				httpServletRequest.setAttribute("nomeCampo", "resolucaoDiretoria");
				// Verifica se Usuário está com débito em cobrança
				// administrativa

				// Verifica situação ligação de água e esgoto
				if ((imovel.getLigacaoAguaSituacao() != null)
						&& ((imovel.getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.POTENCIAL) || (imovel.getLigacaoEsgotoSituacao().getId() == LigacaoAguaSituacao.FACTIVEL))
						&& (imovel.getLigacaoEsgotoSituacao().getId() != LigacaoEsgotoSituacao.LIGADO)) {
					throw new ActionServletException("atencao.pesquisa.imovel.inativo");
				}

				// Pega a descrição da situação de água
				if (imovel.getLigacaoAguaSituacao() != null) {
					efetuarParcelamentoDebitosActionForm.set("situacaoAgua", imovel.getLigacaoAguaSituacao().getDescricao());
					efetuarParcelamentoDebitosActionForm.set("situacaoAguaId", "" + imovel.getLigacaoAguaSituacao().getId());
				}

				// Pega a descrição da situação de esgoto
				if (imovel.getLigacaoEsgotoSituacao() != null) {
					efetuarParcelamentoDebitosActionForm.set("situacaoEsgoto", imovel.getLigacaoEsgotoSituacao().getDescricao());
					efetuarParcelamentoDebitosActionForm.set("situacaoEsgotoId", "" + imovel.getLigacaoEsgotoSituacao().getId());
				}

				efetuarParcelamentoDebitosActionForm.set("perfilImovel", "" + imovel.getImovelPerfil().getId());

				efetuarParcelamentoDebitosActionForm.set("descricaoPerfilImovel", "" + imovel.getImovelPerfil().getDescricao());

				sessao.setAttribute("imovel", imovel);

				// Pega o endereço do Imovel
				String enderecoFormatado = "";
				try {
					enderecoFormatado = fachada.pesquisarEnderecoFormatado(new Integer(idImovel));
					efetuarParcelamentoDebitosActionForm.set("endereco", enderecoFormatado);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				// Pega a inscrição do Imovel
				efetuarParcelamentoDebitosActionForm.set("inscricaoImovel", imovel.getInscricaoFormatada());

				// Pega as Quantidades de Parcelamentos
				if (imovel.getNumeroParcelamento() != null) {
					efetuarParcelamentoDebitosActionForm.set("numeroParcelamento", "" + imovel.getNumeroParcelamento());
				} else {
					efetuarParcelamentoDebitosActionForm.set("numeroParcelamento", "0");
				}

				// Pega as Quantidades de Reparcelamentos
				if (imovel.getNumeroReparcelamento() != null) {
					efetuarParcelamentoDebitosActionForm.set("numeroReparcelamento", "" + imovel.getNumeroReparcelamento());
				} else {
					efetuarParcelamentoDebitosActionForm.set("numeroReparcelamento", "0");
				}

				// Pega as Quantidades de Reparcelamentos Consecutivos
				if (imovel.getNumeroReparcelamentoConsecutivos() != null) {
					efetuarParcelamentoDebitosActionForm.set("numeroReparcelamentoConsecutivos", "" + imovel.getNumeroReparcelamentoConsecutivos());
				} else {
					efetuarParcelamentoDebitosActionForm.set("numeroReparcelamentoConsecutivos", "0");
				}

				// Filtro para recuperar informação do cliente relacionado com o
				// imóvel
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
				filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

				Collection<ClienteImovel> clientesImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

				Cliente cliente = clientesImovel.iterator().next().getCliente();

				// Manda os dados do cliente para a página
				if (cliente != null) {
					sessao.setAttribute("idClienteImovel", cliente.getId());
					efetuarParcelamentoDebitosActionForm.set("nomeCliente", cliente.getNome());
					if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
						efetuarParcelamentoDebitosActionForm.set("cpfCnpj", cliente.getCpfFormatado());
					} else if (cliente.getCnpj() != null && !cliente.getCnpj().equals("")) {
						efetuarParcelamentoDebitosActionForm.set("cpfCnpj", cliente.getCnpjFormatado());
					} else {
						efetuarParcelamentoDebitosActionForm.set("cpfCnpj", "NÃO INFORMADO");
					}
				}
			}
		}

		if (existeImovel) {
			Imovel imovel = imovelPesquisado.iterator().next();

			if (imovel.getAreaConstruida() != null) {

				efetuarParcelamentoDebitosActionForm.set("areaConstruidaImovel", imovel.getAreaConstruida().toString());

			} else if (imovel.getAreaConstruidaFaixa() != null && imovel.getAreaConstruidaFaixa().getMaiorFaixa() != null) {

				efetuarParcelamentoDebitosActionForm.set("areaConstruidaImovel", imovel.getAreaConstruidaFaixa().getMaiorFaixa().toString());
			}
		}

		return existeImovel;
	}
}

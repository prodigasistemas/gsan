package gcom.gui.portal;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.bean.ParcelamentoRelatorioHelper;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroGuiaPagamentoItem;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gcom.relatorio.arrecadacao.pagamento.RelatorioEmitirGuiaPagamentoDetailBean;
import gcom.relatorio.cobranca.parcelamento.RelatorioParcelamentoDetalhamentoBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Classe responsável por criar o relatório dos documentos
 * de parcelamento para loja virtual (termo e a guia de pagamento)
 * Os métodos chamados foram os méotods já existentes do GSAN.
 * O arquivo .jasper é a junção dos relatórios já existentes
 * do GSAN. (Relatório Emitir Guia e o Termo de Parcelamento.)
 * 
 * @author Diogo Peixoto
 * @since 11/07/2011
 */
public class RelatorioDocumentosParcelamentoPortal extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioDocumentosParcelamentoPortal(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DOCUMENTOS_PARCELAMENTO_LOJA_VIRTUAL);
	}

	@Deprecated
	public RelatorioDocumentosParcelamentoPortal() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String idParcelamento = (String) getParametro("idParcelamento");
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("sistemaParametro");
		UnidadeOrganizacional unidadeUsuario = (UnidadeOrganizacional) getParametro("unidadeUsuario");
		Usuario usuarioLogado = (Usuario) getParametro("usuario");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		String descricaoUnidadeUsuario = "";
		if(unidadeUsuario != null && unidadeUsuario.getDescricao() != null){
			descricaoUnidadeUsuario = unidadeUsuario.getDescricao();
		}

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List<RelatorioDocumentosParcelamentoPortalBean> relatorioParcelamentosBeans = new ArrayList<RelatorioDocumentosParcelamentoPortalBean>();

		Fachada fachada = Fachada.getInstancia();

		RelatorioDocumentosParcelamentoPortalBean relatorioParcelamentoBean = null;

		ParcelamentoRelatorioHelper parcelamentoRelatorioHelper = fachada
		.pesquisarParcelamentoRelatorio(Integer.parseInt( idParcelamento ) );

		String idFuncionario = "";
		if (parcelamentoRelatorioHelper.getIdFuncionario() != null){
			idFuncionario = parcelamentoRelatorioHelper.getIdFuncionario().toString();
		}

		// se a coleção de parâmetros da analise não for vazia
		if (parcelamentoRelatorioHelper != null) {

			String idImovel = parcelamentoRelatorioHelper.getIdImovel().toString();
			String nomeCliente = "";

			if (Util.verificarNaoVazio(parcelamentoRelatorioHelper.getNomeClienteParcelamento())){
				nomeCliente = parcelamentoRelatorioHelper.getNomeClienteParcelamento();
			}else{
				nomeCliente = parcelamentoRelatorioHelper.getNomeCliente();
			}

			String cpfCnpjCliente = "";
			if (Util.verificarNaoVazio(parcelamentoRelatorioHelper.getCpfClienteParcelamento())){
				cpfCnpjCliente = parcelamentoRelatorioHelper.getCpfClienteParcelamento();
			}else{
				cpfCnpjCliente = parcelamentoRelatorioHelper.getCpfCnpj();
			}

			Collection colecaoRelatorioParcelamentoDetalhamentosBeans = new ArrayList();

			// Guardaremos o maior e menor ano mes de referencia do documento processado
			Integer maiorAnoMesReferenciaDocumento = null;
			Integer menorAnoMesReferenciaDocumento = null;			

			Collection colecaoRelatorioParcelamentoItens = fachada
			.pesquisarParcelamentoItemPorIdParcelamentoRelatorio(Integer.parseInt(idParcelamento));

			if (colecaoRelatorioParcelamentoItens != null && !colecaoRelatorioParcelamentoItens.isEmpty()) {
				Iterator colecaoRelatorioParcelamentoItensIterator = colecaoRelatorioParcelamentoItens.iterator();

				RelatorioParcelamentoDetalhamentoBean relatorioParcelamentoDetalhamentoBean = null;

				BigDecimal totalFaturas = new BigDecimal("0.00");
				BigDecimal totalServicos = new BigDecimal("0.00");
				BigDecimal totalGuias = new BigDecimal("0.00");
				BigDecimal totalCreditos = new BigDecimal("0.00");

				ParcelamentoItem parcelamentoItem = null;
				ParcelamentoItem parcelamentoItem2 = null;

				Object tipoAnterior = null;
				Object tipoAtual = null;

				while (colecaoRelatorioParcelamentoItensIterator.hasNext()) {
					if (tipoAnterior == null) {
						tipoAnterior = new Conta();
					} else {
						if (parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {
							tipoAnterior = parcelamentoItem.getContaGeral().getConta();
						} else if (parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() != null) {
							tipoAnterior = parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar();
						} else if (parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId() != null) {
							tipoAnterior = parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento();
						} else {
							tipoAnterior = parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar();
						}
					}

					if (parcelamentoItem2 != null && parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {
						parcelamentoItem = parcelamentoItem2;
						parcelamentoItem2 = null;
					} else {

						parcelamentoItem = (ParcelamentoItem) colecaoRelatorioParcelamentoItensIterator.next();
					}

					if (parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {
						tipoAtual = parcelamentoItem.getContaGeral().getConta();
					} else if (parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() != null) {
						tipoAtual = parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar();
					} else if (parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId() != null) {
						tipoAtual = parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento();
					} else {
						tipoAtual = parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getCreditoTipo();
					}

					if (tipoAnterior instanceof Conta) {
						if (!(tipoAtual instanceof Conta)) {
							relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

									// Matrícula do Imóvel
									idImovel,
									// Nome do Cliente
									nomeCliente,
									// Faturas em Aberto Referência 1
									"TOTAL",
									// Valor Fatura 1
									"",
									// Referência 2
									"",
									// Valor Fatura 2
									Util.formatarMoedaReal(totalFaturas),
									// Serviços a Cobrar
									// Código
									"",
									// Descrição
									"",
									// Valor
									"",
									// Guias de Pagamento
									// Número
									"",
									// Descrição
									"",
									// Valor
									"",
									// Créditos a Realizar
									// Código
									"",
									// Descrição
									"",
									// Valor
									""
							);

							// adiciona o bean a coleção
							colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
						}
					} else if (tipoAnterior instanceof DebitoACobrar) {
						if (!(tipoAtual instanceof DebitoACobrar)) {
							relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

									// Matrícula do Imóvel
									idImovel,
									// Nome do Cliente
									nomeCliente,
									// Faturas em Aberto
									// Referência 1
									"",
									// Valor Fatura 1
									"",
									// Referência 2
									"",
									// Valor Fatura 2
									"",
									// Serviços a Cobrar
									// Código
									"TOTAL",
									// Descrição
									"",
									// Valor
									Util.formatarMoedaReal(totalServicos),
									// Guias de Pagamento
									// Número
									"",
									// Descrição
									"",
									// Valor
									"",
									// Créditos a Realizar
									// Código
									"",
									// Descrição
									"",
									// Valor
									""
							);

							// adiciona o bean a coleção
							colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
						}
					} else if (tipoAnterior instanceof GuiaPagamento) {
						if (!(tipoAtual instanceof GuiaPagamento)) {
							relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

									// Matrícula do Imóvel
									idImovel,
									// Nome do Cliente
									nomeCliente,
									// Faturas em Aberto
									// Referência 1
									"",
									// Valor Fatura 1
									"",
									// Referência 2
									"",
									// Valor Fatura 2
									"",
									// Serviços a Cobrar
									// Código
									"",
									// Descrição
									"",
									// Valor
									"",
									// Guias de Pagamento
									// Número
									"TOTAL",
									// Descrição
									"",
									// Valor
									Util.formatarMoedaReal(totalGuias),
									// Créditos a Realizar
									// Código
									"",
									// Descrição
									"",
									// Valor
									""
							);

							// adiciona o bean a coleção
							colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
						}
					}

					if (parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {                     
						Conta conta = parcelamentoItem.getContaGeral().getConta();

						// Verificamos o maior ano mes de ferencia para o tipo conta
						if ( maiorAnoMesReferenciaDocumento == null || maiorAnoMesReferenciaDocumento < conta.getReferencia() ){
							maiorAnoMesReferenciaDocumento = conta.getReferencia() ;
						}

						// Verificamos o menor ano mes de ferencia para o tipo conta
						if ( menorAnoMesReferenciaDocumento == null || menorAnoMesReferenciaDocumento > conta.getReferencia() ){
							menorAnoMesReferenciaDocumento = conta.getReferencia() ;
						}                               


						totalFaturas = totalFaturas.add(conta.getValorTotal());

						if (colecaoRelatorioParcelamentoItensIterator.hasNext()) {

							parcelamentoItem2 = (ParcelamentoItem) colecaoRelatorioParcelamentoItensIterator.next();

							if (parcelamentoItem2.getContaGeral().getConta().getReferencia() != 0) {

								Conta conta2 = parcelamentoItem2.getContaGeral().getConta();

								// Verificamos o maior ano mes de ferencia para o tipo conta
								if ( maiorAnoMesReferenciaDocumento == null || maiorAnoMesReferenciaDocumento < conta2.getReferencia() ){
									maiorAnoMesReferenciaDocumento = conta2.getReferencia() ;
								}

								// Verificamos o menor ano mes de ferencia para o tipo conta
								if ( menorAnoMesReferenciaDocumento == null || menorAnoMesReferenciaDocumento > conta2.getReferencia() ){
									menorAnoMesReferenciaDocumento = conta2.getReferencia() ;
								}                               

								parcelamentoItem2 = null;

								totalFaturas = totalFaturas.add(conta2.getValorTotal());

								relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

										// Matrícula do Imóvel
										idImovel,
										// Nome do Cliente
										nomeCliente,
										// Faturas em Aberto
										// Referência 1
										conta.getFormatarAnoMesParaMesAno(),
										// Valor Fatura 1
										Util.formatarMoedaReal(conta.getValorTotal()),
										// Referência 2
										conta2.getFormatarAnoMesParaMesAno(),
										// Valor Fatura 2
										Util.formatarMoedaReal(conta2.getValorTotal()),
										// Serviços a Cobrar
										// Código
										"",
										// Descrição
										"",
										// Valor
										"",
										// Guias de Pagamento
										// Número
										"",
										// Descrição
										"",
										// Valor
										"",
										// Créditos a Realizar
										// Código
										"",
										// Descrição
										"",
										// Valor
										""
								);

								// adiciona o bean a coleção
								colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

							} else {
								relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

										// Matrícula do Imóvel
										idImovel,
										// Nome do Cliente
										nomeCliente,
										// Faturas em Aberto
										// Referência 1
										conta.getFormatarAnoMesParaMesAno(),
										// Valor Fatura 1
										Util.formatarMoedaReal(conta.getValorTotal()),
										// Referência 2
										"",
										// Valor Fatura 2
										"",
										// Serviços a Cobrar
										// Código
										"",
										// Descrição
										"",
										// Valor
										"",
										// Guias de Pagamento
										// Número
										"",
										// Descrição
										"",
										// Valor
										"",
										// Créditos a Realizar
										// Código
										"",
										// Descrição
										"",
										// Valor
										""
								);

								// adiciona o bean a coleção
								colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

							}
						} else {
							relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

									// Matrícula do Imóvel
									idImovel,
									// Nome do Cliente
									nomeCliente,
									// Faturas em Aberto
									// Referência 1
									conta.getFormatarAnoMesParaMesAno(),
									// Valor Fatura 1
									Util.formatarMoedaReal(conta.getValorTotal()),
									// Referência 2
									"",
									// Valor Fatura 2
									"",
									// Serviços a Cobrar
									// Código
									"",
									// Descrição
									"",
									// Valor
									"",
									// Guias de Pagamento
									// Número
									"",
									// Descrição
									"",
									// Valor
									"",
									// Créditos a Realizar
									// Código
									"",
									// Descrição
									"",
									// Valor
									""
							);

							// adiciona o bean a coleção
							colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
						}

					} else if (parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() != null) {
						DebitoACobrar debitoACobrar = (DebitoACobrar) parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar();
						totalServicos = totalServicos.add(debitoACobrar.getValorTotalComBonus());

						relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

								// Matrícula do Imóvel
								idImovel,
								// Nome do Cliente
								nomeCliente,
								// Faturas em Aberto
								// Referência 1
								"",
								// Valor Fatura 1
								"",
								// Referência 2
								"",
								// Valor Fatura 2
								"",
								// Serviços a Cobrar
								// Código
								debitoACobrar.getDebitoTipo().getId().toString(),
								// Descrição
								debitoACobrar.getDebitoTipo().getDescricao(),
								// Valor
								Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()),
								// Guias de Pagamento
								// Número
								"",
								// Descrição
								"",
								// Valor
								"",
								// Créditos a Realizar
								// Código
								"",
								// Descrição
								"",
								// Valor
								""
						);

						// adiciona o bean a coleção
						colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

					} else if (parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId() != null) {
						GuiaPagamento guiaPagamento = (GuiaPagamento) parcelamentoItem
						.getGuiaPagamentoGeral().getGuiaPagamento();

						totalGuias = totalGuias.add(guiaPagamento.getValorDebito());

						relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

								// Matrícula do Imóvel
								idImovel,
								// Nome do Cliente
								nomeCliente,
								// Faturas em Aberto
								// Referência 1
								"",
								// Valor Fatura 1
								"",
								// Referência 2
								"",
								// Valor Fatura 2
								"",
								// Serviços a Cobrar
								// Código
								"",
								// Descrição
								"",
								// Valor
								"",
								// Guias de Pagamento
								// Número
								guiaPagamento.getId().toString(),
								// Descrição
								guiaPagamento.getDebitoTipo().getDescricao(),
								// Valor
								Util.formatarMoedaReal(guiaPagamento.getValorDebito()),
								// Créditos a Realizar
								// Código
								"",
								// Descrição
								"",
								// Valor
								""
						);

						// adiciona o bean a coleção
						colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
					} else if (parcelamentoItem.getCreditoARealizarGeral()
							.getCreditoARealizar().getCreditoTipo().getId() != null) {
						CreditoARealizar creditoARealizar = (CreditoARealizar) parcelamentoItem
						.getCreditoARealizarGeral().getCreditoARealizar();

						totalCreditos = totalCreditos.add(creditoARealizar.getValorTotalComBonus());

						relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

								// Matrícula do Imóvel
								idImovel,
								// Nome do Cliente
								nomeCliente,
								// Faturas em Aberto
								// Referência 1
								"",
								// Valor Fatura 1
								"",
								// Referência 2
								"",
								// Valor Fatura 2
								"",
								// Serviços a Cobrar
								// Código
								"",
								// Descrição
								"",
								// Valor
								"",
								// Guias de Pagamento
								// Número
								"",
								// Descrição
								"",
								// Valor
								"",
								// Créditos a Realizar
								// Código
								creditoARealizar.getCreditoTipo().getId().toString(),
								// Descrição
								creditoARealizar.getCreditoTipo().getDescricao(),
								// Valor
								Util.formatarMoedaReal(creditoARealizar.getValorTotalComBonus())
						);

						// adiciona o bean a coleção
						colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

					}

				}

				if (tipoAtual instanceof Conta) {
					relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

							// Matrícula do Imóvel
							idImovel,
							// Nome do Cliente
							nomeCliente,
							// Faturas em Aberto
							// Referência 1
							"TOTAL",
							// Valor Fatura 1
							"",
							// Referência 2
							"",
							// Valor Fatura 2
							Util.formatarMoedaReal(totalFaturas),
							// Serviços a Cobrar
							// Código
							"",
							// Descrição
							"",
							// Valor
							"",
							// Guias de Pagamento
							// Número
							"",
							// Descrição
							"",
							// Valor
							"",
							// Créditos a Realizar
							// Código
							"",
							// Descrição
							"",
							// Valor
							""
					);

					// adiciona o bean a coleção
					colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
				} else if (tipoAtual instanceof DebitoACobrar) {
					relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

							// Matrícula do Imóvel
							idImovel,
							// Nome do Cliente
							nomeCliente,
							// Faturas em Aberto
							// Referência 1
							"",
							// Valor Fatura 1
							"",
							// Referência 2
							"",
							// Valor Fatura 2
							"",
							// Serviços a Cobrar
							// Código
							"TOTAL",
							// Descrição
							"",
							// Valor
							Util.formatarMoedaReal(totalServicos),
							// Guias de Pagamento
							// Número
							"",
							// Descrição
							"",
							// Valor
							"",
							// Créditos a Realizar
							// Código
							"",
							// Descrição
							"",
							// Valor
							""
					);

					// adiciona o bean a coleção
					colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

				} else if (tipoAtual instanceof GuiaPagamento) {
					relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

							// Matrícula do Imóvel
							idImovel,
							// Nome do Cliente
							nomeCliente,
							// Faturas em Aberto
							// Referência 1
							"",
							// Valor Fatura 1
							"",
							// Referência 2
							"",
							// Valor Fatura 2
							"",
							// Serviços a Cobrar
							// Código
							"",
							// Descrição
							"",
							// Valor
							"",
							// Guias de Pagamento
							// Número
							"TOTAL",
							// Descrição
							"",
							// Valor
							Util.formatarMoedaReal(totalGuias),
							// Créditos a Realizar
							// Código
							"",
							// Descrição
							"",
							// Valor
							""
					);

					// adiciona o bean a coleção
					colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

				} else if (tipoAtual instanceof CreditoARealizar) {

					relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

							// Matrícula do Imóvel
							idImovel,
							// Nome do Cliente
							nomeCliente,
							// Faturas em Aberto
							// Referência 1
							"",
							// Valor Fatura 1
							"",
							// Referência 2
							"",
							// Valor Fatura 2
							"",
							// Serviços a Cobrar
							// Código
							"",
							// Descrição
							"",
							// Valor
							"",
							// Guias de Pagamento
							// Número
							"",
							// Descrição
							"",
							// Valor
							"",
							// Créditos a Realizar
							// Código
							"TOTAL",
							// Descrição
							"",
							// Valor
							Util.formatarMoedaReal(totalCreditos)
					);

					// adiciona o bean a coleção
					colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
				}

			}
			
			String municipioData = parcelamentoRelatorioHelper.getLocalidade();
			Locale loc = new Locale("pt");
			DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, loc);
			municipioData += " - " + df.format(new Date());
			
			relatorioParcelamentoBean = new RelatorioDocumentosParcelamentoPortalBean(

					// Matrícula do Imóvel
					parcelamentoRelatorioHelper.getIdImovel().toString(),
					// Nome Cliente
					nomeCliente,
					// Endereço
					parcelamentoRelatorioHelper.getEndereco(),
					// CPF/CNPJ
					cpfCnpjCliente,
					// Telefone
					parcelamentoRelatorioHelper.getTelefone(),
					// Data Parcelamento
					Util.formatarData(parcelamentoRelatorioHelper.getDataParcelamento()),
					// Débitos
					// Faturas em Aberto
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorFaturasEmAberto()),
					// Serviços a Cobrar
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorServicosACobrar()),
					// Atualização Monetária
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorAtualizacaoMonetaria()),
					// Juros/Mora
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorJurosMora()),
					// Multa
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorMultas()),
					// Guia Pagamento
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorGuiaPagamento()),
					// Parcelamento a Cobrar
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcelamentoACobrar()),
					// Total Débitos
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDebitos()),
					// Descontos
					// Desconto de Acréscimos
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAcrescimo()),
					// Descontos de Antiguidade
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAntiguidade()),
					// Desconto de Inatividade
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoInatividade()),
					// Créditos a Realizar
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorCreditosRealizar()),
					// Total Descontos
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDescontos()),
					// Valor a Ser Negociado
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerNegociado()),
					// Condições de Negociação
					// Valor da Entrada
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorEntrada()),
					// Número de Parcelas
					parcelamentoRelatorioHelper.getNumeroParcelas().toString(),
					// Valor da Parcela
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcela()),
					// Valor a Ser Parcelado
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerParcelado()),
					// Solicitação de Restabelecimento
					parcelamentoRelatorioHelper.getSolicitacaoRestabelecimento(),
					// Município e Data
					municipioData,
					// Id do Parcelamento
					idParcelamento.toString(),
					// Unidade do Usuário
					descricaoUnidadeUsuario,
					// Id do Funcionário
					idFuncionario,
					// Nome do Cliente do Parcelamento
					parcelamentoRelatorioHelper.getNomeClienteParcelamento(),
					// Cpf do Cliente do Parcelamento
					parcelamentoRelatorioHelper.getCpfClienteParcelamento(),
					// Página
					"1",
					// Coleção de Detalhamentos
					colecaoRelatorioParcelamentoDetalhamentosBeans,
					//codigo da empresa
					sistemaParametro.getCodigoEmpresaFebraban().toString(),
					//rgCliente
					"",
					//nome usuario
					"",
					//matricula usuario
					"",
					//municipio
					"",
					//inicio parcelamento
					"",
					//final parcelamento
					"",
					parcelamentoRelatorioHelper.getTaxaJuros(),
					//Desconto de Sanções Regulamentares
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoSancoesRegulamentares()),
					//Desconto Tarifa Social
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoTarifaSocial()),
					// CPF do usuário logado
					usuarioLogado.getCpf(),
					parcelamentoRelatorioHelper.getNomeDiretorComercial(),
					parcelamentoRelatorioHelper.getCpfDiretorComercial(),
					parcelamentoRelatorioHelper.getProfissao(),
					parcelamentoRelatorioHelper.getNomeDevedor(),
					parcelamentoRelatorioHelper.getCnpjDevedor(),
					parcelamentoRelatorioHelper.getEnderecoDevedor(),
					parcelamentoRelatorioHelper.getTelefoneDevedor(),
					parcelamentoRelatorioHelper.getIndicadorPessoaJuridica(),
					maiorAnoMesReferenciaDocumento,
					menorAnoMesReferenciaDocumento,
					//nome do usuario que efetuou o parcelamento 
					parcelamentoRelatorioHelper.getNomeUsuarioParcelamento());

			
			//Adiociona os campos referentes a guia de pagamento.
			
			//adiciona o bean a coleção
			relatorioParcelamentosBeans.add(relatorioParcelamentoBean);     

		}

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("contrato", idParcelamento);
		// Empresa
		if (sistemaParametro.getNomeAbreviadoEmpresa() != null) {
			parametros.put("empresa", sistemaParametro.getNomeAbreviadoEmpresa());
		} else {
			parametros.put("empresa", "");
		}

		//Titulo Pagina
		if (sistemaParametro.getTituloPagina() != null) {
			parametros.put("tituloPagina", sistemaParametro.getTituloPagina());
		} else {
			parametros.put("tituloPagina", "");
		}

		// CNPJ da Empresa
		if (sistemaParametro.getCnpjEmpresa() != null) {

			String cnpjFormatado = sistemaParametro.getCnpjEmpresa().toString();
			String zeros = "";

			if (cnpjFormatado != null) {

				for (int a = 0; a < (14 - cnpjFormatado.length()); a++) {
					zeros = zeros.concat("0");
				}
				// concatena os zeros ao numero
				// caso o numero seja diferente de nulo
				cnpjFormatado = zeros.concat(cnpjFormatado);

				cnpjFormatado = cnpjFormatado.substring(0, 2) + "."
				+ cnpjFormatado.substring(2, 5) + "."
				+ cnpjFormatado.substring(5, 8) + "/"
				+ cnpjFormatado.substring(8, 12) + "-"
				+ cnpjFormatado.substring(12, 14);
			}

			parametros.put("cnpj", cnpjFormatado);

		} else {
			parametros.put("cnpj", "");
		}

		// Inscrição Estadual da Companhia de Água
		if (sistemaParametro.getInscricaoEstadual() != null) {
			parametros.put("inscricaoEstadual", sistemaParametro.getInscricaoEstadual().toString());
		} else {
			parametros.put("inscricaoEstadual", "");
		}

		String[] ids = (String[]) getParametro("ids");
		Collection<GuiaPagamentoRelatorioHelper> dadosRelatorio = fachada.pesquisarGuiaPagamentoRelatorio(ids);
		relatorioParcelamentosBeans.set(0, this.inicializarBeanRelatorio(dadosRelatorio, relatorioParcelamentosBeans.get(0)));
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String idUsuario = "";
		Usuario usuario = this.getUsuario();
		
		if (usuario != null) {
			idUsuario = usuario.getId().toString();
		} else {
			idUsuario = "INTERNET";
		}
		
		String nomeUsuario = "";
		if ( usuario != null ) {
			nomeUsuario = usuario.getNomeUsuario();
		}
		
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("nomeUsuario", nomeUsuario);
		Integer anoAtual = Util.getAno(new Date());
		parametros.put("anoGuiaPagamento", ""+anoAtual);
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		
		List listArrayJRDetail = new ArrayList();
		RelatorioDataSource arrayJRDetailSub = new RelatorioDataSource(listArrayJRDetail);
		parametros.put("arrayJRDetailSub", arrayJRDetailSub);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioParcelamentosBeans);
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_DOCUMENTOS_PARCELAMENTO_LOJA_VIRTUAL, parametros, ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_DOCUMENTOS_PARCELAMENTO_LOJA_VIRTUAL, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		return retorno;
	}

	private RelatorioDocumentosParcelamentoPortalBean inicializarBeanRelatorio(Collection<GuiaPagamentoRelatorioHelper> dadosRelatorio,
			RelatorioDocumentosParcelamentoPortalBean relatorio) {

		Collection<RelatorioEmitirGuiaPagamentoDetailBean> colecaoDetail = new ArrayList<RelatorioEmitirGuiaPagamentoDetailBean>();
		Iterator<GuiaPagamentoRelatorioHelper> iterator = dadosRelatorio.iterator();
		colecaoDetail.clear();

		while (iterator.hasNext()) {

			GuiaPagamentoRelatorioHelper guiaPagamentoRelatorioHelper = iterator.next();

			//nov parte de guia pagamento item
			//Flávio Leonardo
			//07/11/2008
			String descricaoServicosTarifas = "";
			String valor = "";
			RelatorioEmitirGuiaPagamentoDetailBean relatorioEmitirGuiaPagamentoDetailBean = null;

			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, 
					guiaPagamentoRelatorioHelper.getIdGuiaPagamento()));

			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});

			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem = Fachada.getInstancia().pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			Iterator<GuiaPagamentoItem> iteratorGuiaPagamentoitem = colecaoGuiaPagamentoItem.iterator();

			if(!colecaoGuiaPagamentoItem.isEmpty()){
				while(iteratorGuiaPagamentoitem.hasNext()){
					GuiaPagamentoItem guiaPagamentoItem = (GuiaPagamentoItem)iteratorGuiaPagamentoitem.next();
					descricaoServicosTarifas = guiaPagamentoItem
					.getDebitoTipo().getDescricao() + "     " +  guiaPagamentoRelatorioHelper.getPrestacaoFormatada();

					valor = Util.formatarMoedaReal(guiaPagamentoItem
							.getValorDebito());

					relatorioEmitirGuiaPagamentoDetailBean = new RelatorioEmitirGuiaPagamentoDetailBean(
							descricaoServicosTarifas, valor, false);

					colecaoDetail.add(relatorioEmitirGuiaPagamentoDetailBean);
				}
			}else{
				descricaoServicosTarifas = guiaPagamentoRelatorioHelper
				.getDescTipoDebito() + "     " +  guiaPagamentoRelatorioHelper.getPrestacaoFormatada();
				valor = Util.formatarMoedaReal(guiaPagamentoRelatorioHelper.getValorDebito());
				relatorioEmitirGuiaPagamentoDetailBean = new RelatorioEmitirGuiaPagamentoDetailBean(descricaoServicosTarifas, valor, false);
				colecaoDetail.add(relatorioEmitirGuiaPagamentoDetailBean);
			}

			String valorTotal = Util.formatarMoedaReal(guiaPagamentoRelatorioHelper.getValorDebito());

			String matricula = guiaPagamentoRelatorioHelper.getMatricula();
			String nomeCliente = "";
			if (guiaPagamentoRelatorioHelper.getNomeCliente() != null){
				nomeCliente = guiaPagamentoRelatorioHelper.getNomeCliente();
			}
			String dataVencimento = Util.formatarData(guiaPagamentoRelatorioHelper.getDataVencimento());
			String inscricao = guiaPagamentoRelatorioHelper.getInscricao();
			String enderecoImovel = guiaPagamentoRelatorioHelper.getEnderecoImovel();
			String enderecoClienteResponsavel = guiaPagamentoRelatorioHelper.getEnderecoClienteResponsavel();
			String representacaoNumericaCodBarraFormatada = guiaPagamentoRelatorioHelper.getRepresentacaoNumericaCodBarraFormatada();
			String representacaoNumericaCodBarraSemDigito = guiaPagamentoRelatorioHelper.getRepresentacaoNumericaCodBarraSemDigito();
			String dataValidade = guiaPagamentoRelatorioHelper.getDataValidade();
			String idGuiaPagamento = guiaPagamentoRelatorioHelper.getIdGuiaPagamento();

			String observacao = "";

			Short indicadorEmitirObservacao = guiaPagamentoRelatorioHelper.getIndicadorEmitirObservacao();
			if (indicadorEmitirObservacao != null && indicadorEmitirObservacao.equals(ConstantesSistema.SIM)) {
				observacao = guiaPagamentoRelatorioHelper.getObservacao();
			}

			String cpfCnpjCliente = "";

			if (Util.verificarIdNaoVazio(guiaPagamentoRelatorioHelper.getCpfCliente())){
				cpfCnpjCliente = Util.formatarCpf(guiaPagamentoRelatorioHelper.getCpfCliente());
			} else if (Util.verificarNaoVazio(guiaPagamentoRelatorioHelper.getCnpjCliente())) {
				cpfCnpjCliente = Util.formatarCnpj(guiaPagamentoRelatorioHelper.getCnpjCliente());
			}

			String idImovel = "";
			if (guiaPagamentoRelatorioHelper.getIdImovel() != null && !guiaPagamentoRelatorioHelper.getIdImovel().equals("")) {
				idImovel = guiaPagamentoRelatorioHelper.getIdImovel().toString();
			} else if (guiaPagamentoRelatorioHelper.getIdCliente() != null) {
				idImovel = guiaPagamentoRelatorioHelper.getIdCliente().toString();
			}

			String nossoNumero = guiaPagamentoRelatorioHelper.getNossoNumero();
			String sacadoParte01 = guiaPagamentoRelatorioHelper.getSacadoParte01();
			String sacadoParte02 = guiaPagamentoRelatorioHelper.getSacadoParte02();

			String subRelatorio = guiaPagamentoRelatorioHelper.getSubRelatorio();

			relatorio.inserirDadosRelatorioEmitirGuia(
					colecaoDetail, matricula, nomeCliente, dataVencimento,
					inscricao, enderecoImovel, enderecoClienteResponsavel,
					representacaoNumericaCodBarraFormatada,
					representacaoNumericaCodBarraSemDigito, dataValidade,
					valorTotal, idGuiaPagamento, observacao, cpfCnpjCliente, idImovel,
					nossoNumero, sacadoParte01, sacadoParte02, subRelatorio, colecaoDetail);

			colecaoDetail.clear();
		}
		return relatorio;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioParcelamento", this);
	}
}

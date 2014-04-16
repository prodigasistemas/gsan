package gcom.relatorio.cobranca;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o Relatório de Débitos
 * 
 * @author Rafael Corrêa
 * @date 14/02/2007
 * 
 */
public class RelatorioConsultarDebitosResumido extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioConsultarDebitosResumido(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_RESUMIDO);
	}

	@Deprecated
	public RelatorioConsultarDebitosResumido() {
		super(null, "");
	}

	private Collection<RelatorioConsultarDebitosResumidoBean> inicializarBeanRelatorio(
			Collection<ContaValoresHelper> colecaoContas,
			Collection<DebitoACobrar> colecaoDebitosACobrar,
			Collection<CreditoARealizar> colecaoCreditosARealizar,
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento) {

		Collection<RelatorioConsultarDebitosResumidoBean> retorno = new ArrayList<RelatorioConsultarDebitosResumidoBean>();

		String valorTotalDebitos = (String) getParametro("valorTotalDebitos");
		String valorTotalDebitosAtualizado = (String) getParametro("valorTotalDebitosAtualizado");

		Fachada fachada = Fachada.getInstancia();

		Integer idImovel = null;
		BigDecimal valorContas = new BigDecimal("0");
		BigDecimal valorGuias = new BigDecimal("0");
		BigDecimal valorAcrescimos = new BigDecimal("0");
		BigDecimal valorDebitos = new BigDecimal("0");
		BigDecimal valorCreditos = new BigDecimal("0");
		BigDecimal valorGuiasCliente = new BigDecimal("0");
		Collection<ContaValoresHelper> colecaoContasRemover = new ArrayList<ContaValoresHelper>();
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasRemover = new ArrayList<GuiaPagamentoValoresHelper>();
		Collection<DebitoACobrar> colecaoDebitosRemover = new ArrayList<DebitoACobrar>();
		Collection<CreditoARealizar> colecaoCreditoRemover = new ArrayList<CreditoARealizar>();
		int qtdeContas = 0;

		boolean iterarColecao = true;

		Collection<ContaValoresHelper> colecaoContasTemp = new ArrayList<ContaValoresHelper>();
		colecaoContasTemp.addAll(colecaoContas);

		// Ordenar a coleção pelo imóvel
		Collections.sort((List) colecaoContasTemp, new Comparator() {
			public int compare(Object a, Object b) {
				Integer imovel1 = new Integer(((ContaValoresHelper) a)
						.getConta().getImovel().getId());
				Integer imovel2 = new Integer(((ContaValoresHelper) b)
						.getConta().getImovel().getId());
				

				return imovel1.compareTo(imovel2);

			}
		});

		Collection<DebitoACobrar> colecaoDebitosACobrarTemp = new ArrayList<DebitoACobrar>();
		colecaoDebitosACobrarTemp.addAll(colecaoDebitosACobrar);

		Collection<CreditoARealizar> colecaoCreditosARealizarTemp = new ArrayList<CreditoARealizar>();
		colecaoCreditosARealizarTemp.addAll(colecaoCreditosARealizar);

		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoTemp = new ArrayList<GuiaPagamentoValoresHelper>();
		colecaoGuiasPagamentoTemp.addAll(colecaoGuiasPagamento);

		while (iterarColecao) {

			// Conta
			if (colecaoContasTemp != null && !colecaoContasTemp.isEmpty()) {

				Iterator colecaoContasIterator = colecaoContasTemp.iterator();

				while (colecaoContasIterator.hasNext()) {

					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) colecaoContasIterator
							.next();

					if (idImovel == null) {
						idImovel = contaValoresHelper.getConta().getImovel()
								.getId();
					}

					if (!contaValoresHelper.getConta().getImovel().getId()
							.equals(idImovel)) {
						break;
					}

					// Valor da Conta
					if (contaValoresHelper.getConta().getValorTotal() != null) {
						valorContas = valorContas.add(contaValoresHelper
								.getConta().getValorTotal());
						
						qtdeContas = qtdeContas + 1;

						colecaoContasRemover.add(contaValoresHelper);
					}

					// Valor dos Acréscimos por Impontualidade
					if (contaValoresHelper.getValorTotalContaValores() != null) {
						valorAcrescimos = valorAcrescimos
								.add(contaValoresHelper
										.getValorTotalContaValores());
					}
				}

				colecaoContasTemp.removeAll(colecaoContasRemover);

			}

			// Débito a Cobrar
			if (colecaoDebitosACobrarTemp != null
					&& !colecaoDebitosACobrarTemp.isEmpty()) {

				Iterator colecaoDebitosACobrarIterator = colecaoDebitosACobrarTemp
						.iterator();

				while (colecaoDebitosACobrarIterator.hasNext()) {

					DebitoACobrar debitoACobrar = (DebitoACobrar) colecaoDebitosACobrarIterator
							.next();

					if (idImovel == null
							|| idImovel.equals(debitoACobrar.getImovel()
									.getId())) {
						valorDebitos = valorDebitos.add(debitoACobrar
								.getValorTotalComBonus());

						colecaoDebitosRemover.add(debitoACobrar);
					}

					if (idImovel == null) {
						idImovel = debitoACobrar.getImovel().getId();
					}

				}

				colecaoDebitosACobrarTemp.removeAll(colecaoDebitosRemover);

			}

			// Crédito a Realizar
			if (colecaoCreditosARealizarTemp != null
					&& !colecaoCreditosARealizarTemp.isEmpty()) {

				Iterator colecaoCreditosARealizarIterator = colecaoCreditosARealizarTemp
						.iterator();

				while (colecaoCreditosARealizarIterator.hasNext()) {

					CreditoARealizar creditoARealizar = (CreditoARealizar) colecaoCreditosARealizarIterator
							.next();

					if (idImovel == null
							|| idImovel.equals(creditoARealizar.getImovel()
									.getId())) {
						valorCreditos = valorCreditos.add(creditoARealizar
								.getValorTotalComBonus());

						colecaoCreditoRemover.add(creditoARealizar);
					}

					if (idImovel == null) {
						idImovel = creditoARealizar.getImovel().getId();
					}

				}

				colecaoCreditosARealizarTemp.removeAll(colecaoCreditoRemover);

			}

			// Guia Pagamento
			if (colecaoGuiasPagamentoTemp != null
					&& !colecaoGuiasPagamentoTemp.isEmpty()) {

				Iterator colecaoGuiasPagamentoIterator = colecaoGuiasPagamentoTemp
						.iterator();

				while (colecaoGuiasPagamentoIterator.hasNext()) {

					GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiasPagamentoIterator
							.next();
					
					FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
					filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, guiaPagamentoValoresHelper.getGuiaPagamento().getId()));
					
					Collection colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
					
					GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);

					if (guiaPagamento.getImovel() != null) {
						if (idImovel == null
								|| guiaPagamento.getImovel() == null || idImovel
										.equals(guiaPagamento.getImovel()
												.getId())) {
							valorGuias = valorGuias
									.add(guiaPagamentoValoresHelper
											.getGuiaPagamento()
											.getValorDebito());
							valorAcrescimos = valorAcrescimos
									.add(guiaPagamentoValoresHelper
											.getValorAcrescimosImpontualidade());

							colecaoGuiasRemover.add(guiaPagamentoValoresHelper);
						}

					} else {
						valorGuiasCliente = valorGuiasCliente
									.add(guiaPagamentoValoresHelper
												.getGuiaPagamento()
												.getValorDebito());
						
						colecaoGuiasRemover.add(guiaPagamentoValoresHelper);
						
					}

					if (idImovel == null) {
						if (guiaPagamento.getImovel() != null) {
							idImovel = guiaPagamento.getImovel().getId();
						}
					}

				}

				colecaoGuiasPagamentoTemp.removeAll(colecaoGuiasRemover);

			}

			if ((colecaoContasTemp == null || colecaoContasTemp.isEmpty())
					&& (colecaoDebitosACobrarTemp == null || colecaoDebitosACobrarTemp
							.isEmpty())
					&& (colecaoCreditosARealizarTemp == null || colecaoCreditosARealizarTemp
							.isEmpty())
					&& (colecaoGuiasPagamentoTemp == null || colecaoGuiasPagamentoTemp
							.isEmpty())) {
				iterarColecao = false;
			}

			String inscricaoImovel = fachada.pesquisarInscricaoImovel(idImovel);
			
			String nomeUsuario = "";
			String cpfCnpjUsuario = "";
			String enderecoImovel = "";
			String descricaoLigacaoAguaSituacao = "";
			String descricaoLigacaoEsgotoSituacao = "";
			String descricaoCategoria = "";
			String imovelString = "";
			Object[] dadosCliente = null;
			
			if(idImovel != null){
				dadosCliente = fachada.consultarDadosClienteUsuarioImovel(idImovel+"");	

				if ( dadosCliente[0] != null ) {
					nomeUsuario = (String) dadosCliente[0];
				}
				
				if ( dadosCliente[1] != null ) {
					cpfCnpjUsuario = Util.formatarCpf((String)dadosCliente[1]);
				}else if ( dadosCliente[2] != null ) {
					cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
				}

				enderecoImovel = fachada.pesquisarEndereco(idImovel);
				
				LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(idImovel);
				descricaoLigacaoAguaSituacao = ligacaoAguaSituacao.getDescricaoAbreviado();
				
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(idImovel);
				descricaoLigacaoEsgotoSituacao = ligacaoEsgotoSituacao.getDescricaoAbreviado();

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				
				Categoria categoria = fachada.obterDescricoesCategoriaImovel(imovel);
				descricaoCategoria = categoria.getDescricao();
				
				imovelString = imovel.getId().toString();
			}
			
			BigDecimal totalAtualizado = valorContas.add(valorGuias).add(valorAcrescimos);
			BigDecimal totalGeral = totalAtualizado.add(valorDebitos).subtract(valorCreditos);
			

			RelatorioConsultarDebitosResumidoBean bean = new RelatorioConsultarDebitosResumidoBean(
			// Imóvel
					imovelString,

					// Inscrição
					inscricaoImovel,

					// Nome Usuário
					nomeUsuario,
					
					//Cpf/Cnpj do Usuario
					cpfCnpjUsuario,

					// Endereço
					enderecoImovel,

					// Situação da Ligação de Água
					descricaoLigacaoAguaSituacao,

					// Situação da Ligação de Esgoto
					descricaoLigacaoEsgotoSituacao,

					// Categoria
					descricaoCategoria,

					// Valor das Contas
					qtdeContas + " - R$" + Util.formatarMoedaReal(valorContas),

					// Valor das Guias de Pagamento
					"R$" + Util.formatarMoedaReal(valorGuias),

					// Valor dos Acréscimos
					"R$" + Util.formatarMoedaReal(valorAcrescimos),

					// Total Atualizado
					"R$" + Util.formatarMoedaReal(totalAtualizado),

					// Valor dos Débitos a Cobrar
					"R$" + Util.formatarMoedaReal(valorDebitos),

					// Valor dos Créditos a Realizar
					"R$" + Util.formatarMoedaReal(valorCreditos),

					// Total Geral
					"R$" + Util.formatarMoedaReal(totalGeral),

					// Valor Total dos Débitos
					null,

					// Valor Total dos Débitos Atualizado
					null,
					
					//Valor guias de pagamento do cliente sem imovel
					null);

			retorno.add(bean);

			idImovel = null;

			valorContas = new BigDecimal("0");
			valorGuias = new BigDecimal("0");
			valorAcrescimos = new BigDecimal("0");
			valorDebitos = new BigDecimal("0");
			valorCreditos = new BigDecimal("0");
			qtdeContas = 0;

		}

		// Ordenar a coleção pelo imóvel
		Collections.sort((List) retorno, new Comparator() {
			public int compare(Object a, Object b) {
				Integer imovel1 = new Integer(
						((RelatorioConsultarDebitosResumidoBean) a)
								.getIdImovel());
				Integer imovel2 = new Integer(
						((RelatorioConsultarDebitosResumidoBean) b)
								.getIdImovel());
				

				return imovel1.compareTo(imovel2);

			}
		});

		
		
		if (valorTotalDebitos != null) {

			
			RelatorioConsultarDebitosResumidoBean bean = new RelatorioConsultarDebitosResumidoBean(
			// Imóvel
					null,

					// Inscrição
					null,

					// Nome Usuário
					null,
					
					//CPF Usuário
					null,

					// Endereço
					null,

					// Situação da Ligação de Água
					null,

					// Situação da Ligação de Esgoto
					null,

					// Categoria
					null,

					// Valor das Contas
					null,

					// Valor das Guias de Pagamento
					null,

					// Valor dos Acréscimos
					null,

					// Total Atualizado
					null,

					// Valor dos Débitos a Cobrar
					null,

					// Valor dos Créditos a Realizar
					null,

					// Total Geral
					null,

					// Valor Total dos Débitos
					"R$" + valorTotalDebitos,

					// Valor Total dos Débitos Atualizado
					"R$" + valorTotalDebitosAtualizado,
					
					//Valor guias de pagamento do cliente sem imovel
					"R$" + Util.formatarMoedaReal(valorGuiasCliente));

			retorno.add(bean);
		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {

		Collection colecaoContaValores = (Collection) getParametro("colecaoContaValores");
		Collection colecaoDebitoACobrar = (Collection) getParametro("colecaoDebitoACobrar");
		Collection colecaoCreditoARealizar = (Collection) getParametro("colecaoCreditoARealizar");
		Collection colecaoGuiaPagamento = (Collection) getParametro("colecaoGuiasPagamento");
		String codigoCliente = (String) getParametro("codigoCliente");
		String nomeCliente = (String) getParametro("nomeCliente");
		ClienteRelacaoTipo tipoRelacao = (ClienteRelacaoTipo) getParametro("tipoRelacao");
		String periodoReferenciaInicial = (String) getParametro("periodoReferenciaInicial");
		String periodoReferenciaFinal = (String) getParametro("periodoReferenciaFinal");
		String dataVencimentoInicial = (String) getParametro("dataVencimentoInicial");
		String dataVencimentoFinal = (String) getParametro("dataVencimentoFinal");
		String cpfCnpj = (String) getParametro("cpfCnpj");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		
		Object[] dados = fachada.pesquisarQtdeImoveisEEconomiasCliente(new Integer(codigoCliente));
		
		int qtdeImoveis = 0;
		int qtdeEconomias = 0;
		if (dados != null){
		   qtdeImoveis = (Integer) dados[0];
			if (dados[1] != null){
				qtdeEconomias = (Integer) dados[1];
			}
		}
		
		String endereco = fachada.pesquisarEnderecoClienteAbreviado(new Integer(codigoCliente));
	
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, codigoCliente));
		
		Collection colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoClientes);

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("codigoCliente", codigoCliente);
		parametros.put("nomeCliente", nomeCliente);
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		//Usuario que emite o relatorio
		Usuario usuario = this.getUsuario();

		String nomeUsuario = "";
			nomeUsuario = usuario.getNomeUsuario();
	
		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		
		if ( cliente.getCpf() != null ){
			cpfCnpj = cliente.getCpfFormatado();
		} else if ( cliente.getCnpj() != null ){
			cpfCnpj = cliente.getCnpjFormatado();
		}
		if ( cpfCnpj != null && !cpfCnpj.equals("") ) {
			parametros.put("cpfCnpj", cpfCnpj);
		} else {
			cpfCnpj ="";
		}
		
		if (periodoReferenciaInicial != null
				&& !periodoReferenciaInicial.trim().equals("")) {
			parametros.put("periodoReferenciaDebito", periodoReferenciaInicial
					+ " a " + periodoReferenciaFinal);
		}

		if (dataVencimentoInicial != null
				&& !dataVencimentoInicial.trim().equals("")) {
			parametros.put("periodoVencimentoDebito", dataVencimentoInicial
					+ " a " + dataVencimentoFinal);
		}

		if (tipoRelacao != null) {
			parametros.put("tipoRelacao", tipoRelacao.getDescricao());
		}
		
		parametros.put("qtdeEconomias", "" + qtdeEconomias);
		parametros.put("qtdeMatriculas", "" + qtdeImoveis);
		parametros.put("tipoCliente", cliente.getClienteTipo().getDescricao());
		
		if (endereco != null) {
			parametros.put("endereco", endereco);
		}

		Collection<RelatorioConsultarDebitosResumidoBean> colecaoBean = this
				.inicializarBeanRelatorio(colecaoContaValores,
						colecaoDebitoACobrar, colecaoCreditoARealizar,
						colecaoGuiaPagamento);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_RESUMIDO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		// try {
		// persistirRelatorioConcluido(retorno,
		// Relatorio.MEDICAO_CONSUMO_LIGACAO_AGUA,
		// idFuncionalidadeIniciada);
		// } catch (ControladorException e) {
		// e.printStackTrace();
		// throw new TarefaException("Erro ao gravar relatório no sistema", e);
		// }
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarDebitosResumido",
				this);
	}
}

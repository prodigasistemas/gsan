package gcom.cobranca.contratoparcelamento;

import java.math.BigDecimal;
import java.util.Collection;

import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.util.ErroRepositorioException;


/**
 * @author Paulo Diniz
 * @created 18 de março de 2011
 */
public interface IRepositorioContratoParcelamento {
	
	/**
	 * Pesquisa ContratoParcelamentoRD por numero
	 * 
	 * [UC1133] Inserir Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param numero do contrato
	 * @throws ErroRepositorioException
	 */
	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorNumero(String numero)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa ContratoParcelamentoRD por id
	 * 
	 * [UC1134] Manter Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param numero do contrato
	 * @throws ErroRepositorioException
	 */
	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorID(Integer id) throws ErroRepositorioException;
	
	/**
	 * Verifica Resolução de Diretoria associada a um Contrato Parcelamento não Encerrado
	 * 
	 * [UC1134]  Atualizar Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 */
	public boolean verificaResolucaoDiretoriaAssociadaContratoParcelamentoNaoEncerrado(Integer id)
		throws ErroRepositorioException;
	
	/**
	 * Verifica Conta Vinculada a um Contrato Parcelamento por Cliente Item
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaContaVinculadaAContratoParcel(Integer idConta, Integer idContrato) 
		throws ErroRepositorioException;
	
	/**
	 * Consultar Contrato de Parcelamento por Cliente Com Pagamento jah efetuado
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaContratoParcelComPagamentoFeito(Integer idContrato) throws ErroRepositorioException;
	
	/**
	 * Consultar Se Prestacao Ja Esta Paga
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaPrestacaoPaga(Integer idPrestacao) throws ErroRepositorioException;
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] – Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamento(String numeroContrato) throws ErroRepositorioException;
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] – Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Integer pesquisarDadosContratoParcelamentoNumeroParcelasPagas(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [FS0005] – Verificar existência de contratos para o cliente
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Collection<Object[]> pesquisarDadosContratoParcelamentoPorCliente(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [SB0002] – Informar Dados do Pagamento
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamentoPorClienteSelecionado(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamento(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0005] – Gerar Pagamento Guias Juros de Parcelamento e Guias de Acréscimos
	 * 
	 * Seleciona a guia de juros ou de acréscimos sobre contrato de parcelamento correspondente à parcela paga
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public Object[] pesquisarGuiaDeJurosOuComAcrescimos(Integer idContrato, Integer debitoTipo, Integer idPrestacao) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0007] – Gerar Pagamento
	 * 
	 * Calcula o valor já pago para o item
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public BigDecimal pesquisarValorPagoItem(Integer idContrato, Integer idItem) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0011] – Atualizar Item da Negativação
	 * 
	 * Verifica se a negativação está excluída
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Integer pesquisarCodigoExclusaoNegativacao(Integer idItemNegativacao) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa itens de débito com valor pago a menor
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Object[] pesquisarItemDebitoValorPagoAMenor(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa a conta com valor pago a menor
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Object[] pesquisarContaValorPagoAMenor(Integer idItem) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa o valor da menor prestação
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Object[] pesquisarValoMinimoPrestacoes(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa os itens de débitos do tipo conta para geração de pagamento
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Collection<Object[]> pesquisarItensDebitoConta(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0003] – Efetuar Pagamento da Parcela do Contrato de Parcelamento Por Cliente
	 * 
	 * 1.8.1. Seleciona as guias de pagamento do contrato, atuais e sem pagamento 
	 * 
	 * @author Mariana Victor
	 * @data 08/06/2011
	 */
	public Collection<Object[]> pesquisarGuiasPagamentoContratoAtuaisSemPagamento(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Desvincula a guia de pagamento do contrato de parcelamento, 
	 * atualizando dados do contrato na tabela cobranca.CONTRATO_PARCEL_ITEM  
	 * 
	 * @author Mariana Victor
	 * @data 08/06/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarContratoParcelamentoItemDesvincularGuiaContrato(String[] idsGuias) throws ErroRepositorioException;

	/**
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * [SB0003] – Emitir Dados do Parcelamento
	 * 
	 * Pesquisa se a parcela já foi paga 
	 * 
	 * @author Mariana Victor
	 * @data 13/06/2011
	 */
	public Boolean pesquisarParcelaPaga(Integer idPrestacao) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta Os Clientes Vinculados a um ContratoParcelamento
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerClientesVinculadosAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta Os Itens de Débitos Atuais do Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerItensDebitosVinculadosAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta as PrestacaoContratoParcelamento vinculadas ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerPrestacoesVinculadasAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta as Guias de Pagamento de 'Acrescimos por Impontualidade' vinculadas ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerGuiasDePagtoDeAcrescImpontualidadeVinculadasAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta as Guias de Pagamento de 'Juros sobre Contrato' vinculadas ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerGuiasDePagtoJurosSobreContratoVinculadasAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta o id da RD relacionada ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 29/06/2011
	 */
	public Integer pesquisarRDContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados das contas vinculadas ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 02/07/2011
	 */
	public Collection<ContaValoresHelper> pesquisarDadosDasContasContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * [FS0008] – Verificar existência do contrato anterior
	 * 
	 * @author Mariana Victor
	 * @data 08/07/2011
	 */
	public Boolean verificarExistenciaContratoAnterior(String numeroContratoAnterior) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * [FS0037] - Verificar situação do contrato anterior
	 * 
	 * Retorna a situação de parcelamento do contrato
	 * 
	 * @author Mariana Victor
	 * @data 08/07/2011
	 */
	public Boolean verificarSituacaoContratoAnterior(String numeroContratoAnterior) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa o débito a cobrar com valor pago a menor
	 * 
	 * @author Mariana Victor
	 * @data 21/07/2011
	 */
	public Object[] pesquisarDebitoACobrarValorPagoAMenor(Integer idItem) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa os itens de débitos do tipo débito a cobrar para geração de pagamento
	 * 
	 * @author Mariana Victor
	 * @data 21/07/2011
	 */
	public Collection<Object[]> pesquisarItensDebitoACobrar(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados dos débitos a cobrar vinculados ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 26/07/2011
	 */
	public Collection<DebitoACobrar> pesquisarDadosDosDebitosACobrarContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0184] Manter Débito A Cobrar
	 * 
	 * Verifica se o débito a cobrar está vinculado a um Contrato Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @date 28/07/2011
	 */
	public boolean verificaDebitoACobrarVinculadoContratoParcelamentoCliente(Integer idDebitoACobrar) 
		throws ErroRepositorioException;

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 1. O sistema obtém os dados do contrato de parcelamento por cliente
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public ContratoParcelamento obterDadosContratoParcelamento(String numeroContratoParcelamento) 
		throws ErroRepositorioException;

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 2.2.	Dados do Parcelamento 
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public Collection<PrestacaoContratoParcelamento> obterDadosPrestacoesContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException;

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public CobrancaDocumentoItem obterDocumentoCobrancaPrestacao(Integer idPrestacao) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta o Documento de Cobrança vinculado ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 01/08/2011
	 */
	public Boolean removerDocumentoCobrancaVinculadoAContratoParcelamento(Integer idDocumentoCobranca) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta os Itens de Documento de Cobrança vinculados ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 01/08/2011
	 */
	public Boolean removerItemDocumentoCobrancaVinculadoAContratoParcelamento(Integer idItem) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * Retorna os dados do débito a cobrar caso exista algum pagamento para o mesmo.
	 * 
	 * @author Mariana Victor
	 * @data 03/08/2011
	 */
	public Object[] obterDadosDebitoACobrarPagoAMenor(Integer idDebitoACobrar) 
		throws ErroRepositorioException;

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamentoCompleto(Integer idContrato, String numeroContrato) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public Integer pesquisarIdClientecontrato(Integer idContratoParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(Integer idContratoParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 12/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(Integer idContratoParcelamento, 
			Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 15/08/2011
	 */
	public Collection<ContratoParcelamentoCliente> pesquisarClienteContrato(
			Integer idContratoParcelamento,
			Short indicadorClienteSuperior) 
		throws ErroRepositorioException;

	/**
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @created 29/08/2011
	 * 
	 * @param idContratoParcelamento
	 * @exception ErroRepositorioException
	 */
	public Collection<ContratoParcelamentoItem> pesquisarContratoParcelamentoItem(
			Integer idContratoParcelamento, Integer idDocumentoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 13/09/2011
	 */
	public CobrancaForma pesquisarFormaPagamentoRD(
			Integer idRD) 
		throws ErroRepositorioException;
	
}

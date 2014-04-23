package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Este caso de uso permite gerar o resumo das ações de cobrança com a 
 * atividade emitir já realizada e a atividade encerrar ainda não realizada 
 * e realizar a atividade encerrar das ações que estejam comandadas.
 *
 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
 *
 * [SB0004] - Atualizar Item do Documento de Cobrança
 * 
 * Acumula a quantidade e o valor do item, na situiaão de débito correspondente
 * Armazena a data da situação do débito do imte do documento de cobrança refrente a situação do débito do item do documento de cobrança
 *
 * Objeto composto de:
 * Id Situação do Débito
 * Quantidade de Ocorrencia das Situações de Débito
 * Valor do Item Cobrado
 * Data situação do Débito
 * 
 * @author Rafael Santos
 * @since 18/10/2006
 */
public class GerarResumoAcoesCobrancaCronogramaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Quantidade de Ocorrencia da Situaçãoi de Débito 
	 */
	private int quantidadeOcorrenciaSituacaoDebito;
	
	/**
	 * Valor do Item Pago
	 */
	private BigDecimal valorItemCobrado;

	/**
	 * Id da Situaçãoi do Débito
	 */
	private int idSituacaoDebito;
	
	/**
	 * Data da Situação do Débito
	 */
	private Date dataSituacaoDebito;

	/**
	 * @return Retorna o campo dataSituacaoDebito.
	 */
	public Date getDataSituacaoDebito() {
		return dataSituacaoDebito;
	}

	/**
	 * @param dataSituacaoDebito O dataSituacaoDebito a ser setado.
	 */
	public void setDataSituacaoDebito(Date dataSituacaoDebito) {
		this.dataSituacaoDebito = dataSituacaoDebito;
	}

	/**
	 * @return Retorna o campo idSituacaoDebito.
	 */
	public int getIdSituacaoDebito() {
		return idSituacaoDebito;
	}

	/**
	 * @param idSituacaoDebito O idSituacaoDebito a ser setado.
	 */
	public void setIdSituacaoDebito(int idSituacaoDebito) {
		this.idSituacaoDebito = idSituacaoDebito;
	}

	/**
	 * @return Retorna o campo quantidadeOcorrenciaSituacaoDebito.
	 */
	public int getQuantidadeOcorrenciaSituacaoDebito() {
		return quantidadeOcorrenciaSituacaoDebito;
	}

	/**
	 * @param quantidadeOcorrenciaSituacaoDebito O quantidadeOcorrenciaSituacaoDebito a ser setado.
	 */
	public void setQuantidadeOcorrenciaSituacaoDebito(
			int quantidadeOcorrenciaSituacaoDebito) {
		this.quantidadeOcorrenciaSituacaoDebito = quantidadeOcorrenciaSituacaoDebito;
	}

	/**
	 * @return Retorna o campo valorItemCobrado.
	 */
	public BigDecimal getValorItemCobrado() {
		return valorItemCobrado;
	}

	/**
	 * @param valorItemCobrado O valorItemCobrado a ser setado.
	 */
	public void setValorItemCobrado(BigDecimal valorItemCobrado) {
		this.valorItemCobrado = valorItemCobrado;
	}

	/**
	 * Construtor de GerarResumoAcoesCobrancaCronogramaHelper 
	 * 
	 * @param quantidadeOcorrenciaSituacaoDebito
	 * @param valorItemCobrado
	 * @param idSituacaoDebito
	 * @param dataSituacaoDebito
	 */
	public GerarResumoAcoesCobrancaCronogramaHelper(int quantidadeOcorrenciaSituacaoDebito, BigDecimal valorItemCobrado, int idSituacaoDebito, Date dataSituacaoDebito) {
		super();
		this.quantidadeOcorrenciaSituacaoDebito = quantidadeOcorrenciaSituacaoDebito;
		this.valorItemCobrado = valorItemCobrado;
		this.idSituacaoDebito = idSituacaoDebito;
		this.dataSituacaoDebito = dataSituacaoDebito;
	}
	
	/**
	 * Construtor de GerarResumoAcoesCobrancaCronogramaHelper 
	 * 
	 * @param quantidadeOcorrenciaSituacaoDebito
	 * @param valorItemCobrado
	 * @param idSituacaoDebito
	 * @param dataSituacaoDebito
	 */
	public GerarResumoAcoesCobrancaCronogramaHelper() {
		super();
	}	
}

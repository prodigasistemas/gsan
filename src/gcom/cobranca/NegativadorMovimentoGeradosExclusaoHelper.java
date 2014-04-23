package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * Classe utilizada na resposta do caso de uso 
 * [UC0673] - Gerar Movimento de Exclusão de Negativação  
 *
 * @author Thiago Toscano
 * @date 04/01/2008
 */
public class NegativadorMovimentoGeradosExclusaoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 private String descricaoNegativador = "";

	 private String nsa= "";

	 private String quantidadeRegistros= "";	 
	 	
	 private String valorDebito= ""; 
	 
	 private Date dataProcessamento;
	 
	 private Date horaProcessamento;

	/**
	 * @return Retorna o campo serialVersionUID.
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return Retorna o campo dataProcessamento.
	 */
	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	/**
	 * @param dataProcessamento O dataProcessamento a ser setado.
	 */
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	/**
	 * @return Retorna o campo descricaoNegativador.
	 */
	public String getDescricaoNegativador() {
		return descricaoNegativador;
	}

	/**
	 * @param descricaoNegativador O descricaoNegativador a ser setado.
	 */
	public void setDescricaoNegativador(String descricaoNegativador) {
		this.descricaoNegativador = descricaoNegativador;
	}

	/**
	 * @return Retorna o campo horaProcessamento.
	 */
	public Date getHoraProcessamento() {
		return horaProcessamento;
	}

	/**
	 * @param horaProcessamento O horaProcessamento a ser setado.
	 */
	public void setHoraProcessamento(Date horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}

	/**
	 * @return Retorna o campo nsa.
	 */
	public String getNsa() {
		return nsa;
	}

	/**
	 * @param nsa O nsa a ser setado.
	 */
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}

	/**
	 * @return Retorna o campo quantidadeRegistros.
	 */
	public String getQuantidadeRegistros() {
		return quantidadeRegistros;
	}

	/**
	 * @param quantidadeRegistros O quantidadeRegistros a ser setado.
	 */
	public void setQuantidadeRegistros(String quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public String getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
}

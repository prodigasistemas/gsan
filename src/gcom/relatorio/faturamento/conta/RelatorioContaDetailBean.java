package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UCXXXX] Gerar Contas
 * @author Rafael Corrêa
 * @date 25/07/2009
 */
public class RelatorioContaDetailBean implements RelatorioBean {
	
	private String descricaoServico;
	private BigDecimal valor;
	private String codigoServico;
	private Integer consumo;
	
	/**
	 * @return Retorna o campo codigoServico.
	 */
	public String getCodigoServico() {
		return codigoServico;
	}
	/**
	 * @param codigoServico O codigoServico a ser setado.
	 */
	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	/**
	 * @return Retorna o campo consumo.
	 */
	public Integer getConsumo() {
		return consumo;
	}
	/**
	 * @param consumo O consumo a ser setado.
	 */
	public void setConsumo(Integer consumo) {
		this.consumo = consumo;
	}
	/**
	 * @return Retorna o campo descricaoServico.
	 */
	public String getDescricaoServico() {
		return descricaoServico;
	}
	/**
	 * @param descricaoServico O descricaoServico a ser setado.
	 */
	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}
	/**
	 * @return Retorna o campo valor.
	 */
	public BigDecimal getValor() {
		return valor;
	}
	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	

}

package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

/**
 * [UCXXXX] Gerar Contas
 * @author Rafael Corrêa
 * @date 25/07/2009
 */
public class RelatorioContaConsumosAnterioresBean implements RelatorioBean {
	
	private String mesAno;
	private Integer consumo;
	
	/**
	 * @return Retorna o campo codigoServico.
	 */
	public String getMesAno() {
		return mesAno;
	}
	/**
	 * @param codigoServico O codigoServico a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
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
}

package gcom.relatorio.atendimentopublico.bean;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório Manter de Cuso de Pavimento.
 * 
 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 *
 * @date 12/01/2011
 */

public class RelatorioManterCustoPavimentoPorRepavimentadoraBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String idPavimento;

	private String descricaoPavimento;

	private BigDecimal valor;

	private String dataInicial;

	private String dataFinal;
	
	private String tipo;
	
	public RelatorioManterCustoPavimentoPorRepavimentadoraBean(){
		
	}

	public RelatorioManterCustoPavimentoPorRepavimentadoraBean(String idPavimento,
			String descricaoPavimento, BigDecimal valor, String dataInicial, String dataFinal) {
		
		this.idPavimento = idPavimento;
		this.descricaoPavimento = descricaoPavimento;
		this.valor = valor;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDescricaoPavimento() {
		return descricaoPavimento;
	}

	public void setDescricaoPavimento(String descricaoPavimento) {
		this.descricaoPavimento = descricaoPavimento;
	}

	public String getIdPavimento() {
		return idPavimento;
	}

	public void setIdPavimento(String idPavimento) {
		this.idPavimento = idPavimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}

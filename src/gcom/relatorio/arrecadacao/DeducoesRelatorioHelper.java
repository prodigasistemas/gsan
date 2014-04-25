package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;

public class DeducoesRelatorioHelper {
	
	private String tipo;
	private BigDecimal valorDeducao;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getValorDeducao() {
		return valorDeducao;
	}
	public void setValorDeducao(BigDecimal valorDeducao) {
		this.valorDeducao = valorDeducao;
	}
	
}

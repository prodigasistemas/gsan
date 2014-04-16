package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC ] 
 * @author Vivianne Sousa
 * @date 16/08/2007
 */
public class RelatorioDetailFatura2Bean implements RelatorioBean {
	
	private String nome;
	private String matricula;
	private String consumo;
	private String valor;
	
	public RelatorioDetailFatura2Bean(
			String nome,
			String matricula,
			String consumo,
			String valor) {
		
		this.nome = nome;
		this.matricula = matricula;
		this.consumo = consumo;
		this.valor = valor;
	}

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}

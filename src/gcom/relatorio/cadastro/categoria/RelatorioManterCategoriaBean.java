package gcom.relatorio.cadastro.categoria;

import gcom.relatorio.RelatorioBean;

public class RelatorioManterCategoriaBean implements RelatorioBean {

	private String codigo;

	private String descricao;

	private String descricaoAbreviada;

	private String consumoMinimo;

	private String consumoReferenciaAlto;

	private String consumoReferenciaBaixo;

	private String consumoReferenciaEstouro;

	private String fatorMultiplicacaoAlto;

	private String fatorMultiplicacaoEstouro;

	private String percentualBaixoConsumo;

	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioManterCategoriaBean(String codigo, String descricao,
			String descricaoAbreviada, String consumoMinimo,
			String consumoReferenciaAlto, String consumoReferenciaBaixo,
			String consumoReferenciaEstouro, String fatorMultiplicacaoAlto,
			String fatorMultiplicacaoEstouro, String percentualBaixoConsumo,
			String indicadorUso) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.consumoMinimo = consumoMinimo;
		this.consumoReferenciaAlto = consumoReferenciaAlto;
		this.consumoReferenciaBaixo = consumoReferenciaBaixo;
		this.consumoReferenciaEstouro = consumoReferenciaEstouro;
		this.fatorMultiplicacaoEstouro = fatorMultiplicacaoEstouro;
		this.fatorMultiplicacaoAlto = fatorMultiplicacaoAlto;
		this.percentualBaixoConsumo = percentualBaixoConsumo;
		this.indicadorUso = indicadorUso;

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String getConsumoReferenciaAlto() {
		return consumoReferenciaAlto;
	}

	public void setConsumoReferenciaAlto(String consumoReferenciaAlto) {
		this.consumoReferenciaAlto = consumoReferenciaAlto;
	}

	public String getConsumoReferenciaBaixo() {
		return consumoReferenciaBaixo;
	}

	public void setConsumoReferenciaBaixo(String consumoReferenciaBaixo) {
		this.consumoReferenciaBaixo = consumoReferenciaBaixo;
	}

	public String getConsumoReferenciaEstouro() {
		return consumoReferenciaEstouro;
	}

	public void setConsumoReferenciaEstouro(String consumoReferenciaEstouro) {
		this.consumoReferenciaEstouro = consumoReferenciaEstouro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getFatorMultiplicacaoAlto() {
		return fatorMultiplicacaoAlto;
	}

	public void setFatorMultiplicacaoAlto(String fatorMultiplicacaoAlto) {
		this.fatorMultiplicacaoAlto = fatorMultiplicacaoAlto;
	}

	public String getFatorMultiplicacaoEstouro() {
		return fatorMultiplicacaoEstouro;
	}

	public void setFatorMultiplicacaoEstouro(String fatorMultiplicacaoEstouro) {
		this.fatorMultiplicacaoEstouro = fatorMultiplicacaoEstouro;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getPercentualBaixoConsumo() {
		return percentualBaixoConsumo;
	}

	public void setPercentualBaixoConsumo(String percentualBaixoConsumo) {
		this.percentualBaixoConsumo = percentualBaixoConsumo;
	}

}

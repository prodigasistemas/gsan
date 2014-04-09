package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioFaixasFalsasLeituraBean implements RelatorioBean {

	private String grupo;

	private String empresa;

	private String localidade;
	
	private String idSetorComercial;

	private String setorComercial;
	
	private String indicador;

	private String inscricao;

	private String matricula;

	private String leiturista;
	
	private String faixaCorreta;

	private String faixaFalsa;

	private String leituraInformada;
	
	private String dataLeitura;
	
	private String anormalidadeLeitura;
	
	private String situacao;
	
	private String totalFaixas;

	public RelatorioFaixasFalsasLeituraBean(String grupo, String empresa,
			String localidade, String idSetorComercial, String setorComercial,
			String indicador, String inscricao, String matricula,
			String leiturista, String faixaCorreta, String faixaFalsa,
			String leituraInformada, String dataLeitura,
			String anormalidadeLeitura, String situacao, String totalFaixas) {
		
		this.grupo = grupo;
		this.empresa = empresa;
		this.localidade = localidade;
		this.idSetorComercial = idSetorComercial;
		this.setorComercial = setorComercial;
		this.indicador = indicador;
		this.inscricao = inscricao;
		this.matricula = matricula;
		this.leiturista = leiturista;
		this.faixaCorreta = faixaCorreta;
		this.faixaFalsa = faixaFalsa;
		this.leituraInformada = leituraInformada;
		this.dataLeitura = dataLeitura;
		this.anormalidadeLeitura = anormalidadeLeitura;
		this.situacao = situacao;
		this.totalFaixas = totalFaixas;
		
	}

	/**
	 * @return Retorna o campo anormalidadeLeitura.
	 */
	public String getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}

	/**
	 * @param anormalidadeLeitura O anormalidadeLeitura a ser setado.
	 */
	public void setAnormalidadeLeitura(String anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}

	/**
	 * @return Retorna o campo dataLeitura.
	 */
	public String getDataLeitura() {
		return dataLeitura;
	}

	/**
	 * @param dataLeitura O dataLeitura a ser setado.
	 */
	public void setDataLeitura(String dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo faixaCorreta.
	 */
	public String getFaixaCorreta() {
		return faixaCorreta;
	}

	/**
	 * @param faixaCorreta O faixaCorreta a ser setado.
	 */
	public void setFaixaCorreta(String faixaCorreta) {
		this.faixaCorreta = faixaCorreta;
	}

	/**
	 * @return Retorna o campo faixaFalsa.
	 */
	public String getFaixaFalsa() {
		return faixaFalsa;
	}

	/**
	 * @param faixaFalsa O faixaFalsa a ser setado.
	 */
	public void setFaixaFalsa(String faixaFalsa) {
		this.faixaFalsa = faixaFalsa;
	}

	/**
	 * @return Retorna o campo grupo.
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo O grupo a ser setado.
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo indicador.
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador O indicador a ser setado.
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Retorna o campo leituraInformada.
	 */
	public String getLeituraInformada() {
		return leituraInformada;
	}

	/**
	 * @param leituraInformada O leituraInformada a ser setado.
	 */
	public void setLeituraInformada(String leituraInformada) {
		this.leituraInformada = leituraInformada;
	}

	/**
	 * @return Retorna o campo leiturista.
	 */
	public String getLeiturista() {
		return leiturista;
	}

	/**
	 * @param leiturista O leiturista a ser setado.
	 */
	public void setLeiturista(String leiturista) {
		this.leiturista = leiturista;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo setorComercial.
	 */
	public String getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo totalFaixas.
	 */
	public String getTotalFaixas() {
		return totalFaixas;
	}

	/**
	 * @param totalFaixas O totalFaixas a ser setado.
	 */
	public void setTotalFaixas(String totalFaixas) {
		this.totalFaixas = totalFaixas;
	}
	
	


	
	
}

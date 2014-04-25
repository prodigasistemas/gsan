package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioRoteiroProgramacaoBean implements RelatorioBean {
	
	private String unidade;
	
	private String data;

	private String codigoEquipe;

	private String nomeEquipe;

	private String placa;

	private String encarregado;

	private String posicaoComponente;
	
	private String nomeComponente;
	
	private String posicaoComponente2;
	
	private String nomeComponente2;
	
	private String sequencial;

	private String numeroRA;

	private String numeroOS;

	private String tipoServico;

	private String endereco;
	
	private String observacao;

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
	public RelatorioRoteiroProgramacaoBean(String unidade, String data, String codigoEquipe, String nomeEquipe,
			String placa, String encarregado, String posicaoComponente,
			String nomeComponente, String posicaoComponente2, String nomeComponente2, String sequencial, String numeroRA,
			String numeroOS, String tipoServico, String endereco, String observacao) {
		this.unidade = unidade;
		this.data = data;
		this.codigoEquipe = codigoEquipe;
		this.nomeEquipe = nomeEquipe;
		this.placa = placa;
		this.encarregado = encarregado;
		this.posicaoComponente = posicaoComponente;
		this.nomeComponente = nomeComponente;
		this.posicaoComponente2 = posicaoComponente2;
		this.nomeComponente2 = nomeComponente2;
		this.sequencial = sequencial;
		this.numeroRA = numeroRA;
		this.nomeComponente = nomeComponente;
		this.sequencial = sequencial;
		this.numeroRA = numeroRA;
		this.numeroOS = numeroOS;
		this.tipoServico = tipoServico;
		this.endereco = endereco;
		this.observacao = observacao;
	}

	/**
	 * Gets e Sets the codigo attribute of the RelatorioRoteiroProgramacaoBean object
	 * 
	 * @return The codigo value
	 */

	public String getCodigoEquipe() {
		return codigoEquipe;
	}

	public void setCodigoEquipe(String codigoEquipe) {
		this.codigoEquipe = codigoEquipe;
	}

	public String getEncarregado() {
		return encarregado;
	}

	public void setEncarregado(String encarregado) {
		this.encarregado = encarregado;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeComponente() {
		return nomeComponente;
	}

	public void setNomeComponente(String nomeComponente) {
		this.nomeComponente = nomeComponente;
	}

	public String getNomeEquipe() {
		return nomeEquipe;
	}

	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getPosicaoComponente() {
		return posicaoComponente;
	}

	public void setPosicaoComponente(String posicaoComponente) {
		this.posicaoComponente = posicaoComponente;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getNomeComponente2() {
		return nomeComponente2;
	}

	public void setNomeComponente2(String nomeComponente2) {
		this.nomeComponente2 = nomeComponente2;
	}

	public String getPosicaoComponente2() {
		return posicaoComponente2;
	}

	public void setPosicaoComponente2(String posicaoComponente2) {
		this.posicaoComponente2 = posicaoComponente2;
	}

}

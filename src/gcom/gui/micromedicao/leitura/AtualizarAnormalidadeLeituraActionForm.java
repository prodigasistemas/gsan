package gcom.gui.micromedicao.leitura;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <p>
 * <b>[UC0191]</b> Manter Anormalidade de Leitura
 * </p>
 * 
 * <p>
 * Esta funcionalidade permite atualizar uma Anormalidade de Leitura
 * </p>
 * 
 * @author lms, Magno Gouveia
 * @since 06/07/2006, 23/08/2011
 */
public class AtualizarAnormalidadeLeituraActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricao;

	private String abreviatura;

	private String indicadorRelativoHidrometro;

	private String indicadorImovelSemHidrometro;

	private String usoRestritoSistema;

	private String perdaTarifaSocial;

	private String osAutomatico;

	private String tipoServico;

	private String consumoLeituraNaoInformado;

	private String consumoLeituraInformado;

	private String leituraLeituraNaoturaInformado;

	private String leituraLeituraInformado;

	private String dataUltimaAlteracao;

	private String indicadorUso;

	private String numeroFatorSemLeitura;

	private String numeroFatorComLeitura;

	private String indicadorLeitura;

	/**
	 * Quantidade de ocorrências para uma mesma anormalidade em um imóvel
	 * suspender a leitura
	 */
	private String numeroVezesSuspendeLeitura;

	/**
	 * Quantidade de meses que o imóvel ficará com a leitura suspensa em função
	 * de ocorrência de uma mesma anormalidade
	 */
	private String numeroMesesLeituraSuspensa;

	/**
	 * TODO : COSANPA Pamela Gatinho - 13/03/2012 Campo que identifica se a
	 * anormalidade será usada ou não no sistema de leitura e impressão
	 * simultanea.
	 */
	private String indicadorImpressaoSimultanea;

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getConsumoLeituraInformado() {
		return consumoLeituraInformado;
	}

	public void setConsumoLeituraInformado(String consumoLeituraInformado) {
		this.consumoLeituraInformado = consumoLeituraInformado;
	}

	public String getConsumoLeituraNaoInformado() {
		return consumoLeituraNaoInformado;
	}

	public void setConsumoLeituraNaoInformado(String consumoLeituraNaoInformado) {
		this.consumoLeituraNaoInformado = consumoLeituraNaoInformado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorImovelSemHidrometro() {
		return indicadorImovelSemHidrometro;
	}

	public void setIndicadorImovelSemHidrometro(
			String indicadorImovelSemHidrometro) {
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
	}

	public String getIndicadorRelativoHidrometro() {
		return indicadorRelativoHidrometro;
	}

	public void setIndicadorRelativoHidrometro(
			String indicadorRelativoHidrometro) {
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
	}

	public String getLeituraLeituraInformado() {
		return leituraLeituraInformado;
	}

	public void setLeituraLeituraInformado(String leituraLeituraInformado) {
		this.leituraLeituraInformado = leituraLeituraInformado;
	}

	public String getLeituraLeituraNaoturaInformado() {
		return leituraLeituraNaoturaInformado;
	}

	public void setLeituraLeituraNaoturaInformado(
			String leituraLeituraNaoturaInformado) {
		this.leituraLeituraNaoturaInformado = leituraLeituraNaoturaInformado;
	}

	public String getOsAutomatico() {
		return osAutomatico;
	}

	public void setOsAutomatico(String osAutomatico) {
		this.osAutomatico = osAutomatico;
	}

	public String getPerdaTarifaSocial() {
		return perdaTarifaSocial;
	}

	public void setPerdaTarifaSocial(String perdaTarifaSocial) {
		this.perdaTarifaSocial = perdaTarifaSocial;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getUsoRestritoSistema() {
		return usoRestritoSistema;
	}

	public void setUsoRestritoSistema(String usoRestritoSistema) {
		this.usoRestritoSistema = usoRestritoSistema;
	}

	public String getNumeroFatorSemLeitura() {
		return numeroFatorSemLeitura;
	}

	public void setNumeroFatorSemLeitura(String numeroFatorSemLeitura) {
		this.numeroFatorSemLeitura = numeroFatorSemLeitura;
	}

	public String getNumeroFatorComLeitura() {
		return numeroFatorComLeitura;
	}

	public void setNumeroFatorComLeitura(String numeroFatorComLeitura) {
		this.numeroFatorComLeitura = numeroFatorComLeitura;
	}

	public String getIndicadorLeitura() {
		return indicadorLeitura;
	}

	public void setIndicadorLeitura(String indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}

	public String getNumeroMesesLeituraSuspensa() {
		return numeroMesesLeituraSuspensa;
	}

	public void setNumeroMesesLeituraSuspensa(String numeroMesesLeituraSuspensa) {
		this.numeroMesesLeituraSuspensa = numeroMesesLeituraSuspensa;
	}

	public String getNumeroVezesSuspendeLeitura() {
		return numeroVezesSuspendeLeitura;
	}

	public void setNumeroVezesSuspendeLeitura(String numeroVezesSuspendeLeitura) {
		this.numeroVezesSuspendeLeitura = numeroVezesSuspendeLeitura;
	}

	public String getIndicadorImpressaoSimultanea() {
		return indicadorImpressaoSimultanea;
	}

	public void setIndicadorImpressaoSimultanea(
			String indicadorImpressaoSimultanea) {
		this.indicadorImpressaoSimultanea = indicadorImpressaoSimultanea;
	}
}

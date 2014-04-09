package gcom.gui.micromedicao.leitura;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descrição da classe>>
 * 
 * @author Thiago Tenorio
 * @date 26/07/2006
 */
public class InserirAnormalidadeLeituraActionForm extends ValidatorActionForm {

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

	private String IndicadorUso;

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

	public String getIndicadorUso() {
		return IndicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		IndicadorUso = indicadorUso;
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

	public void setIndicadorImovelSemHidrometro(String indicadorImovelSemHidrometro) {
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
	}

	public String getIndicadorRelativoHidrometro() {
		return indicadorRelativoHidrometro;
	}

	public void setIndicadorRelativoHidrometro(String indicadorRelativoHidrometro) {
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

	public void setLeituraLeituraNaoturaInformado(String leituraLeituraNaoturaInformado) {
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
}

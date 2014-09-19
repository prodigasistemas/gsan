package gcom.gui.micromedicao.leitura;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descrição da classe>>
 * 
 * @author lms
 * @date 26/07/2006
 */
public class FiltrarAnormalidadeLeituraActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String descricao;

	String indicadorRelativoHidrometro;

	String indicadorImovelSemHidrometro;

	String usoRestritoSistema;

	String perdaTarifaSocial;

	String osAutomatico;

	String tipoServico;

	String consumoLeituraNaoInformado;

	String consumoLeituraInformado;

	String leituraLeituraNaoturaInformado;

	String leituraLeituraInformado;

	String tipoPesquisa;

	String indicadorUso;

	private String indicadorAtualizar;

	/**
	 *  Pamela Gatinho - 13/03/2012 Campo que identifica se a
	 * anormalidade será usada ou não no sistema de leitura e impressão
	 * simultanea.
	 */
	String indicadorImpressaoSimultanea;

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
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

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getIndicadorImpressaoSimultanea() {
		return indicadorImpressaoSimultanea;
	}

	public void setIndicadorImpressaoSimultanea(
			String indicadorImpressaoSimultanea) {
		this.indicadorImpressaoSimultanea = indicadorImpressaoSimultanea;
	}
}

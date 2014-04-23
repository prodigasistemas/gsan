package gcom.gui.micromedicao.leitura;

import org.apache.struts.action.ActionForm;

public class PesquisarLeituraAnormalidadeActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String anormalidadeRelativaHidrometro;

	private String anormalidadeSemHidrometro;

	private String anormalidadeRestritoSistema;

	private String anormalidadePerdaTarifaSocial;

	private String anormalidadeOrdemServicoAutomatica;

	private String tipoServico;

	private String consumoCobradoLeituraNaoInformada;

	private String consumoCobradoLeituraInformada;

	private String leituraFaturamentoLeituraNaoInformada;

	private String leituraFaturamentoLeituraInformada;

	public String getAnormalidadeOrdemServicoAutomatica() {
		return anormalidadeOrdemServicoAutomatica;
	}

	public void setAnormalidadeOrdemServicoAutomatica(
			String anormalidadeOrdemServicoAutomatica) {
		this.anormalidadeOrdemServicoAutomatica = anormalidadeOrdemServicoAutomatica;
	}

	public String getAnormalidadePerdaTarifaSocial() {
		return anormalidadePerdaTarifaSocial;
	}

	public void setAnormalidadePerdaTarifaSocial(
			String anormalidadePerdaTarifaSocial) {
		this.anormalidadePerdaTarifaSocial = anormalidadePerdaTarifaSocial;
	}

	public String getAnormalidadeRelativaHidrometro() {
		return anormalidadeRelativaHidrometro;
	}

	public void setAnormalidadeRelativaHidrometro(
			String anormalidadeRelativaHidrometro) {
		this.anormalidadeRelativaHidrometro = anormalidadeRelativaHidrometro;
	}

	public String getAnormalidadeRestritoSistema() {
		return anormalidadeRestritoSistema;
	}

	public void setAnormalidadeRestritoSistema(
			String anormalidadeRestritoSistema) {
		this.anormalidadeRestritoSistema = anormalidadeRestritoSistema;
	}

	public String getAnormalidadeSemHidrometro() {
		return anormalidadeSemHidrometro;
	}

	public void setAnormalidadeSemHidrometro(String anormalidadeSemHidrometro) {
		this.anormalidadeSemHidrometro = anormalidadeSemHidrometro;
	}

	public String getConsumoCobradoLeituraInformada() {
		return consumoCobradoLeituraInformada;
	}

	public void setConsumoCobradoLeituraInformada(
			String consumoCobradoLeituraInformada) {
		this.consumoCobradoLeituraInformada = consumoCobradoLeituraInformada;
	}

	public String getConsumoCobradoLeituraNaoInformada() {
		return consumoCobradoLeituraNaoInformada;
	}

	public void setConsumoCobradoLeituraNaoInformada(
			String consumoCobradoLeituraNaoInformada) {
		this.consumoCobradoLeituraNaoInformada = consumoCobradoLeituraNaoInformada;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLeituraFaturamentoLeituraInformada() {
		return leituraFaturamentoLeituraInformada;
	}

	public void setLeituraFaturamentoLeituraInformada(
			String leituraFaturamentoLeituraInformada) {
		this.leituraFaturamentoLeituraInformada = leituraFaturamentoLeituraInformada;
	}

	public String getLeituraFaturamentoLeituraNaoInformada() {
		return leituraFaturamentoLeituraNaoInformada;
	}

	public void setLeituraFaturamentoLeituraNaoInformada(
			String leituraFaturamentoLeituraNaoInformada) {
		this.leituraFaturamentoLeituraNaoInformada = leituraFaturamentoLeituraNaoInformada;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

}

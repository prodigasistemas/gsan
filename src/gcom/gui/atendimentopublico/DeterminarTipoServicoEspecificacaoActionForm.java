package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

public class DeterminarTipoServicoEspecificacaoActionForm extends ValidatorActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String tiposSolicitacao;
	private String tiposEspecificacaoSolicitacao;
	private String locaisOcorrencia;
	private String pavimentoRua;
	private String pavimentoCalcada;
	private String tipoServico;
	private String descricaoTipoServico;
	
	public String getLocaisOcorrencia() {
		return locaisOcorrencia;
	}
	public void setLocaisOcorrencia(String locaisOcorrencia) {
		this.locaisOcorrencia = locaisOcorrencia;
	}
	public String getPavimentosCalcada() {
		return pavimentoCalcada;
	}
	public void setPavimentosCalcada(String pavimentosCalcada) {
		this.pavimentoCalcada = pavimentosCalcada;
	}
	public String getPavimentosRua() {
		return pavimentoRua;
	}
	public void setPavimentosRua(String pavimentosRua) {
		this.pavimentoRua = pavimentosRua;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getTiposEspecificacaoSolicitacao() {
		return tiposEspecificacaoSolicitacao;
	}
	public void setTiposEspecificacaoSolicitacao(
			String tiposEspecificacaoSolicitacao) {
		this.tiposEspecificacaoSolicitacao = tiposEspecificacaoSolicitacao;
	}
	public String getTiposSolicitacao() {
		return tiposSolicitacao;
	}
	public void setTiposSolicitacao(String tiposSolicitacao) {
		this.tiposSolicitacao = tiposSolicitacao;
	}
	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}
	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}
	public String getPavimentoRua() {
		return pavimentoRua;
	}
	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}
	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}
	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}	
}

package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Marcio Roberto
 * @date 26/01/2007
 */
public class InserirFaturamentoGrupoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    
	private String codigo;
	private String descricao;
	private String descricaoAbreviada;
	private String anoMesReferencia;
	private String IndicadorUso;
	private String diaVencimento;
	private String indicadorVencimentoMesFatura;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
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
	public String getDiaVencimento() {
		return diaVencimento;
	}
	public void setDiaVencimento(String diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	public String getIndicadorUso() {
		return IndicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		IndicadorUso = indicadorUso;
	}
	public String getIndicadorVencimentoMesFatura() {
		return indicadorVencimentoMesFatura;
	}
	public void setIndicadorVencimentoMesFatura(String indicadorVencimentoMesFatura) {
		this.indicadorVencimentoMesFatura = indicadorVencimentoMesFatura;
	}

}

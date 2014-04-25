package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Vinicius Medeiros
 * @date 14/05/2008
 */
public class InserirLigacaoEsgotoSituacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;

	String descricaoAbreviado;

	String volumeMinimoFaturamento;

	String indicadorFaturamentoSituacao;

	String indicadorExistenciaRede;

	String indicadorExistenciaLigacao;

	String IndicadorUso;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorUso() {
		return IndicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		IndicadorUso = indicadorUso;
	}

	public String getDescricaoAbreviado() {
		return descricaoAbreviado;
	}

	public void setDescricaoAbreviado(String descricaoAbreviado) {
		this.descricaoAbreviado = descricaoAbreviado;
	}

	public String getIndicadorExistenciaLigacao() {
		return indicadorExistenciaLigacao;
	}

	public void setIndicadorExistenciaLigacao(String indicadorExistenciaLigacao) {
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
	}

	public String getIndicadorExistenciaRede() {
		return indicadorExistenciaRede;
	}

	public void setIndicadorExistenciaRede(String indicadorExistenciaRede) {
		this.indicadorExistenciaRede = indicadorExistenciaRede;
	}

	public String getIndicadorFaturamentoSituacao() {
		return indicadorFaturamentoSituacao;
	}

	public void setIndicadorFaturamentoSituacao(
			String indicadorFaturamentoSituacao) {
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
	}

	public String getVolumeMinimoFaturamento() {
		return volumeMinimoFaturamento;
	}

	public void setVolumeMinimoFaturamento(String volumeMinimoFaturamento) {
		this.volumeMinimoFaturamento = volumeMinimoFaturamento;
	}
}

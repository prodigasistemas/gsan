package gcom.gui.atendimentopublico.ligacaoagua;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Vinicius Medeiros
 * @date 15/05/2008
 */
public class InserirLigacaoAguaSituacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;

	String descricaoAbreviado;

	String consumoMinimoFaturamento;

	String indicadorFaturamentoSituacao;

	String indicadorExistenciaRede;

	String indicadorExistenciaLigacao;

	String IndicadorUso;
	
	String IndicadorAbastecimento;
	
	String indicadorAguaAtiva;
	
	String indicadorAguaDesligada;
	
	String indicadorAguaCadastrada;
	
	String indicadorAnalizeAgua;

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

	public String getConsumoMinimoFaturamento() {
		return consumoMinimoFaturamento;
	}

	public void setConsumoMinimoFaturamento(String consumoMinimoFaturamento) {
		this.consumoMinimoFaturamento = consumoMinimoFaturamento;
	}

	public String getIndicadorAbastecimento() {
		return IndicadorAbastecimento;
	}

	public void setIndicadorAbastecimento(String indicadorAbastecimento) {
		IndicadorAbastecimento = indicadorAbastecimento;
	}

	public String getIndicadorAguaAtiva() {
		return indicadorAguaAtiva;
	}

	public void setIndicadorAguaAtiva(String indicadorAguaAtiva) {
		this.indicadorAguaAtiva = indicadorAguaAtiva;
	}

	public String getIndicadorAguaCadastrada() {
		return indicadorAguaCadastrada;
	}

	public void setIndicadorAguaCadastrada(String indicadorAguaCadastrada) {
		this.indicadorAguaCadastrada = indicadorAguaCadastrada;
	}

	public String getIndicadorAguaDesligada() {
		return indicadorAguaDesligada;
	}

	public void setIndicadorAguaDesligada(String indicadorAguaDesligada) {
		this.indicadorAguaDesligada = indicadorAguaDesligada;
	}

	public String getIndicadorAnalizeAgua() {
		return indicadorAnalizeAgua;
	}

	public void setIndicadorAnalizeAgua(String indicadorAnalizeAgua) {
		this.indicadorAnalizeAgua = indicadorAnalizeAgua;
	}
	


}

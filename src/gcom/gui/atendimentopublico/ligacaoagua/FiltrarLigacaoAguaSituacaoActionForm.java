package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0786]Filtrar Situacao de Ligacao de Agua 
 * 
 * @author Vinicius Medeiros
 * @date 15/05/2008
 */

public class FiltrarLigacaoAguaSituacaoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String consumoMinimoFaturamento;
	private String indicadorFaturamentoSituacao = ConstantesSistema.TODOS.toString();
	private String indicadorExistenciaRede = ConstantesSistema.TODOS.toString();
	private String indicadorExistenciaLigacao = ConstantesSistema.TODOS.toString();
	private String indicadorUso = ConstantesSistema.TODOS.toString();
	private String tipoPesquisa = ConstantesSistema.TIPO_PESQUISA_INICIAL.toString();
	private String indicadorAbastecimento = ConstantesSistema.TODOS.toString();
	private String indicadorAtualizar = ConstantesSistema.TODOS.toString();
	private String indicadorAguaAtiva = ConstantesSistema.TODOS.toString();
	private String indicadorAguaDesligada = ConstantesSistema.TODOS.toString();
	private String indicadorAguaCadastrada = ConstantesSistema.TODOS.toString();
	private String indicadorAnalizeAgua = ConstantesSistema.TODOS.toString();

	
	public String getConsumoMinimoFaturamento() {
		return consumoMinimoFaturamento;
	}

	public void setConsumoMinimoFaturamento(String consumoMinimoFaturamento) {
		this.consumoMinimoFaturamento = consumoMinimoFaturamento;
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

	public void setIndicadorFaturamentoSituacao(String indicadorFaturamentoSituacao) {
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getIndicadorUso() {
		return indicadorUso;
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

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getIndicadorAbastecimento() {
		return indicadorAbastecimento;
	}

	public void setIndicadorAbastecimento(String indicadorAbastecimento) {
		this.indicadorAbastecimento = indicadorAbastecimento;
	}
	
	

}

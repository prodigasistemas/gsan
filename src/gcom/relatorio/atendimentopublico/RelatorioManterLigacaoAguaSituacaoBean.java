package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterLigacaoAguaSituacaoBean implements RelatorioBean {
	
	private String codigo;

	private String descricao;
	
	private String descricaoAbreviada;
	
	private String consumoMinimo;
	
	private String indicadorFaturamentoSituacao;
	
	private String indicadorExistenciaRede;
	
	private String indicadorExistenciaLigacao;
	
	private String indicadorAguaAtiva;
	
	private String indicadorAguaDesligada;
	
	private String indicadorAguaCadastrada;
	
	private String indicadorAnalizeAgua;
	
	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioManterLigacaoAguaSituacaoBean
	 * 
	 * @param codigo
	 *            Descrição do parâmetro
	 * @param gerenciaRegional
	 *            Descrição do parâmetro
	 * @param nome
	 *            Descrição do parâmetro
	 * @param indicadorUso
	 *            Descrição do parâmetro
	 */

	public RelatorioManterLigacaoAguaSituacaoBean(String codigo, String descricao, String descricaoAbreviada,
			String consumoMinimo, String indicadorFaturamentoSituacao, String indicadorExistenciaRede, 
			String indicadorExistenciaLigacao, String indicadorAguaAtiva, String indicadorAguaDesligada,
			String indicadorAguaCadastrada, String indicadorAnalizeAgua, String indicadorUso) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.consumoMinimo = consumoMinimo;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.indicadorExistenciaRede = indicadorExistenciaRede;
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
		this.indicadorAguaAtiva = indicadorAguaAtiva;
		this.indicadorAguaCadastrada = indicadorAguaCadastrada;
		this.indicadorAguaDesligada = indicadorAguaDesligada;
		this.indicadorAnalizeAgua = indicadorAnalizeAgua;
		this.indicadorUso = indicadorUso;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
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

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
		
	
}

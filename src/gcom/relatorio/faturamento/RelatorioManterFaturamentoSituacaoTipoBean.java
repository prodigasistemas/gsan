package gcom.relatorio.faturamento;

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
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterFaturamentoSituacaoTipoBean implements RelatorioBean {
	
	private String codigo;

	private String descricao;

	private String indicadorParalisacaoFaturamento;
	
	private String indicadorParalisacaoLeitura;
	
	private String indicadorValidoAgua;
	
	private String indicadorValidoEsgoto;	

	/**
	 * Construtor da classe RelatorioManterBaciaBean
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param sistemaEsgoto
	 *            Descrição do parâmetro
	 */

	public RelatorioManterFaturamentoSituacaoTipoBean(String codigo,
			String descricao, String indicadorParalisacaoFaturamento, 
			String indicadorParalisacaoLeitura, String indicadorValidoAgua, String indicadorValidoEsgoto ) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.indicadorParalisacaoFaturamento= indicadorParalisacaoFaturamento;
		this.indicadorParalisacaoLeitura = indicadorParalisacaoLeitura;
		this.indicadorValidoAgua = indicadorValidoAgua;
		this.indicadorValidoEsgoto = indicadorValidoEsgoto;
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

	public String getIndicadorParalisacaoFaturamento() {
		return indicadorParalisacaoFaturamento;
	}

	public void setIndicadorParalisacaoFaturamento(
			String indicadorParalisacaoFaturamento) {
		this.indicadorParalisacaoFaturamento = indicadorParalisacaoFaturamento;
	}

	public String getIndicadorParalisacaoLeitura() {
		return indicadorParalisacaoLeitura;
	}

	public void setIndicadorParalisacaoLeitura(String indicadorParalisacaoLeitura) {
		this.indicadorParalisacaoLeitura = indicadorParalisacaoLeitura;
	}

	public String getIndicadorValidoAgua() {
		return indicadorValidoAgua;
	}

	public void setIndicadorValidoAgua(String indicadorValidoAgua) {
		this.indicadorValidoAgua = indicadorValidoAgua;
	}

	public String getIndicadorValidoEsgoto() {
		return indicadorValidoEsgoto;
	}

	public void setIndicadorValidoEsgoto(String indicadorValidoEsgoto) {
		this.indicadorValidoEsgoto = indicadorValidoEsgoto;
	}

	
	

	
}

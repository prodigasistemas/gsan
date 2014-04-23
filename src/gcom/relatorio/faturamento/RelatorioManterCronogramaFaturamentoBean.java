package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterCronogramaFaturamentoBean implements RelatorioBean {
	private String grupo;

	private String mesAno;

	private String atividade;

	private String predecessora;

	private String obrigatoria;

	private String dataPrevista;

	private String dataRealizacao;
	
	private String dataComando;

	/**
	 * Construtor da classe RelatorioManterCronogramaFaturamentoBean
	 * 
	 * @param grupo
	 *            Descrição do parâmetro
	 * @param mesAno
	 *            Descrição do parâmetro
	 * @param atividade
	 *            Descrição do parâmetro
	 * @param predecessora
	 *            Descrição do parâmetro
	 * @param obrigatoria
	 *            Descrição do parâmetro
	 * @param dataPrevista
	 *            Descrição do parâmetro
	 * @param dataRealizacao
	 *            Descrição do parâmetro
	 */

	public RelatorioManterCronogramaFaturamentoBean(String grupo,
			String mesAno, String atividade, String predecessora,
			String obrigatoria, String dataPrevista, String dataRealizacao, String dataComando) {
		this.grupo = grupo;
		this.mesAno = mesAno;
		this.atividade = atividade;
		this.predecessora = predecessora;
		this.obrigatoria = obrigatoria;
		this.dataPrevista = dataPrevista;
		this.dataRealizacao = dataRealizacao;
		this.dataComando = dataComando;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getObrigatoria() {
		return obrigatoria;
	}

	public void setObrigatoria(String obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	public String getPredecessora() {
		return predecessora;
	}

	public void setPredecessora(String predecessora) {
		this.predecessora = predecessora;
	}

	public String getDataComando() {
		return dataComando;
	}

	public void setDataComando(String dataComando) {
		this.dataComando = dataComando;
	}
}

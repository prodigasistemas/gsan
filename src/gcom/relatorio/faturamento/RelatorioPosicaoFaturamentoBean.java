package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Corrêa
 * @created 01/09/2008
 */
/**
 * @author Administrador
 *
 */
public class RelatorioPosicaoFaturamentoBean implements RelatorioBean {

	private String grupoFaturamento;

	private String mesAno;

	private String atividade;

	private String predecessora;
	
	private String obrigatoria;

	private String dataPrevisao;

	private String usuarioPrevisao;
	
	private String dataComando;

	private String usuarioComando;

	private String dataRealizacao;

	/**
	 * Construtor da classe RelatorioPosicaoFaturamentoBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioPosicaoFaturamentoBean(String grupoFaturamento,
			String mesAno, String atividade, String predecessora,
			String obrigatoria, String dataPrevisao, String usuarioPrevisao,
			String dataComando, String usuarioComando, String dataRealizacao) {
		this.grupoFaturamento = grupoFaturamento;
		this.mesAno = mesAno;
		this.atividade = atividade;
		this.predecessora = predecessora;
		this.obrigatoria = obrigatoria;
		this.dataPrevisao = dataPrevisao;
		this.usuarioPrevisao = usuarioPrevisao;
		this.dataComando = dataComando;
		this.usuarioComando = usuarioComando;
		this.dataRealizacao = dataRealizacao;

	}

	/**
	 * @return Retorna o campo atividade.
	 */
	public String getAtividade() {
		return atividade;
	}

	/**
	 * @param atividade O atividade a ser setado.
	 */
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	/**
	 * @return Retorna o campo dataComando.
	 */
	public String getDataComando() {
		return dataComando;
	}

	/**
	 * @param dataComando O dataComando a ser setado.
	 */
	public void setDataComando(String dataComando) {
		this.dataComando = dataComando;
	}

	/**
	 * @return Retorna o campo dataPrevisao.
	 */
	public String getDataPrevisao() {
		return dataPrevisao;
	}

	/**
	 * @param dataPrevisao O dataPrevisao a ser setado.
	 */
	public void setDataPrevisao(String dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	/**
	 * @return Retorna o campo dataRealizacao.
	 */
	public String getDataRealizacao() {
		return dataRealizacao;
	}

	/**
	 * @param dataRealizacao O dataRealizacao a ser setado.
	 */
	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	/**
	 * @return Retorna o campo grupoFaturamento.
	 */
	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	/**
	 * @param grupoFaturamento O grupoFaturamento a ser setado.
	 */
	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	/**
	 * @return Retorna o campo mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	/**
	 * @return Retorna o campo obrigatoria.
	 */
	public String getObrigatoria() {
		return obrigatoria;
	}

	/**
	 * @param obrigatoria O obrigatoria a ser setado.
	 */
	public void setObrigatoria(String obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	/**
	 * @return Retorna o campo predecessora.
	 */
	public String getPredecessora() {
		return predecessora;
	}

	/**
	 * @param predecessora O predecessora a ser setado.
	 */
	public void setPredecessora(String predecessora) {
		this.predecessora = predecessora;
	}

	/**
	 * @return Retorna o campo usuarioComando.
	 */
	public String getUsuarioComando() {
		return usuarioComando;
	}

	/**
	 * @param usuarioComando O usuarioComando a ser setado.
	 */
	public void setUsuarioComando(String usuarioComando) {
		this.usuarioComando = usuarioComando;
	}

	/**
	 * @return Retorna o campo usuarioPrevisao.
	 */
	public String getUsuarioPrevisao() {
		return usuarioPrevisao;
	}

	/**
	 * @param usuarioPrevisao O usuarioPrevisao a ser setado.
	 */
	public void setUsuarioPrevisao(String usuarioPrevisao) {
		this.usuarioPrevisao = usuarioPrevisao;
	}

	
}

package gcom.relatorio.micromedicao;

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

public class RelatorioRegistrarLeiturasAnormalidadesBean implements
		RelatorioBean {
	private String matriculaFuncionario;

	private String matriculaImovel;

	private String inscricaoImovel;

	private String tipoMedicao;

	private String dataLeitura;

	private String codigoAnormalidade;

	private String indicadorConfirmacao;

	private String errosTxt;
	
	private String leituraHidrometro;

	/**
	 * Construtor da classe RelatorioManterTarifaSocialBean
	 * 
	 * @param codigo
	 *            Descrição do parâmetro
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param indicadorValidade
	 *            Descrição do parâmetro
	 * @param maximoMeses
	 *            Descrição do parâmetro
	 * @param indicadorUso
	 *            Descrição do parâmetro
	 */
	public RelatorioRegistrarLeiturasAnormalidadesBean(
			String matriculaFuncionario, String matriculaImovel,
			String inscricaoImovel, String tipoMedicao, String dataLeitura,
			String codigoAnormalidade, String indicadorConfirmacao,
			String errosTxt,String leituraHidrometro) {
		this.matriculaFuncionario = matriculaFuncionario;
		this.matriculaImovel = matriculaImovel;
		this.inscricaoImovel = inscricaoImovel;
		this.tipoMedicao = tipoMedicao;
		this.dataLeitura = dataLeitura;
		this.codigoAnormalidade = codigoAnormalidade;
		this.indicadorConfirmacao = indicadorConfirmacao;
		this.errosTxt = errosTxt;
		this.leituraHidrometro = leituraHidrometro;
	}

	public String getCodigoAnormalidade() {
		return codigoAnormalidade;
	}

	public void setCodigoAnormalidade(String codigoAnormalidade) {
		this.codigoAnormalidade = codigoAnormalidade;
	}

	public String getDataLeitura() {
		return dataLeitura;
	}

	public void setDataLeitura(String dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	public String getErrosTxt() {
		return errosTxt;
	}

	public void setErrosTxt(String errosTxt) {
		this.errosTxt = errosTxt;
	}

	public String getIndicadorConfirmacao() {
		return indicadorConfirmacao;
	}

	public void setIndicadorConfirmacao(String indicadorConfirmacao) {
		this.indicadorConfirmacao = indicadorConfirmacao;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getLeituraHidrometro() {
		return leituraHidrometro;
	}

	public void setLeituraHidrometro(String leituraHidrometro) {
		this.leituraHidrometro = leituraHidrometro;
	}
	
	

}

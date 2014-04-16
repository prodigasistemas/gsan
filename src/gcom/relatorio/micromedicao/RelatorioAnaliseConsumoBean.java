package gcom.relatorio.micromedicao;

import gcom.micromedicao.bean.RelatorioAnaliseConsumoHelper;
import gcom.relatorio.RelatorioBean;

/**
 * @author Vivianne Sousa
 * @date 06/11/2007
 */
public class RelatorioAnaliseConsumoBean implements RelatorioBean {

	private String inscricaoImovel;
	private String matriculaImovel;
	private String rota;
	private String seqRota;
	private String endereco;
	private String qtdEconomias;
	private String categoria;
	private String usuario;
	private String hidrometro;
	private String leituraAnterior;
	private String leituraAtual;
	
	//******************************************************************************
	// autor: Ivan Sérgio
	// data: 24/07/2008
	// solicitante: Leonardo Vieira
	// Caso a Empresa selecionada NAO seja COMPESA nao informar a Leitura Atual;
	// Exibir a descricao da Leitura Anormalidade Informada;
	//******************************************************************************
	private String anormalidadeInformada;
	private String indicadorExibirLeituraAtual;
	
	
	public RelatorioAnaliseConsumoBean(RelatorioAnaliseConsumoHelper relatorioAnaliseConsumoHelper) {
	    	
		this.inscricaoImovel = relatorioAnaliseConsumoHelper.getInscricaoImovelFormatada();
		this.matriculaImovel = relatorioAnaliseConsumoHelper.getMatriculaImovel();
		this.rota = relatorioAnaliseConsumoHelper.getRota();
		this.seqRota = relatorioAnaliseConsumoHelper.getSeqRota();
		this.endereco = relatorioAnaliseConsumoHelper.getEndereco();
		this.qtdEconomias  = relatorioAnaliseConsumoHelper.getQtdEconomias();
		this.categoria  = relatorioAnaliseConsumoHelper.getCategoria();
		this.usuario = relatorioAnaliseConsumoHelper.getUsuario();
		this.hidrometro = relatorioAnaliseConsumoHelper.getHidrometro();
		this.leituraAnterior = relatorioAnaliseConsumoHelper.getLeituraAnterior();
		this.leituraAtual = relatorioAnaliseConsumoHelper.getLeituraAtual();
		
		this.anormalidadeInformada = relatorioAnaliseConsumoHelper.getDescricaoLeituraAnormalidadeInformada();
		this.indicadorExibirLeituraAtual = relatorioAnaliseConsumoHelper.getIndicadorExibirLeituraAtual();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSeqRota() {
		return seqRota;
	}

	public void setSeqRota(String seqRota) {
		this.seqRota = seqRota;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(String qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public String getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getAnormalidadeInformada() {
		return anormalidadeInformada;
	}

	public void setAnormalidadeInformada(String anormalidadeInformada) {
		this.anormalidadeInformada = anormalidadeInformada;
	}

	public String getIndicadorExibirLeituraAtual() {
		return indicadorExibirLeituraAtual;
	}

	public void setIndicadorExibirLeituraAtual(String indicadorExibirLeituraAtual) {
		this.indicadorExibirLeituraAtual = indicadorExibirLeituraAtual;
	}
	
	
}

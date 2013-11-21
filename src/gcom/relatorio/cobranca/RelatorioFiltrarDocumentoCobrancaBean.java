package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Descrição da classe
 * Classe responsável pela descricão dos beans do relatório
 * 
 * @author Anderson Italo
 * @date 17/08/2009
 */
public class RelatorioFiltrarDocumentoCobrancaBean implements RelatorioBean {
	
	private String matriculaImovel;
	private String inscricaoImovel;
	private String endereco;
	private String usuario;
	private Date emissao;
	private Integer ordemServico;
	private BigDecimal valor;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private Integer quatidadeItens;
	private String perfilImovel;
	private String situacaoAcao;
	private String categoria;
	private String situacaoDebito;
	
	public RelatorioFiltrarDocumentoCobrancaBean() { }

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
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

	public Integer getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(Integer ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public Integer getQuatidadeItens() {
		return quatidadeItens;
	}

	public void setQuatidadeItens(Integer quatidadeItens) {
		this.quatidadeItens = quatidadeItens;
	}

	public String getSituacaoAcao() {
		return situacaoAcao;
	}

	public void setSituacaoAcao(String situacaoAcao) {
		this.situacaoAcao = situacaoAcao;
	}

	public String getSituacaoDebito() {
		return situacaoDebito;
	}

	public void setSituacaoDebito(String situacaoDebito) {
		this.situacaoDebito = situacaoDebito;
	}

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}

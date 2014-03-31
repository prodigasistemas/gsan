package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;
import java.util.Date;

public class DadosTabelaAtualizacaoCadastralHelper implements Serializable{
	private static final long serialVersionUID = 7924233122387459185L;

	private Integer idTabelaAtualizacaoCadastral;
	
	private Integer idTabela;
	private String descricaoTabela;
	
	private Integer idTabelaColuna;
	private String descricaoColuna;
	private transient String nomeColuna; 
	private transient String complemento;
	
	private Integer idTabelaColunaAtualizacaoCadastral;
	private String colunaValorAnterior;
	private String colunaValorAtual = "";
	private Short indicadorAutorizado;
	private Date ultimaAtualizacao;
	
	private Integer idAlteracaoTipo;
	private String descricaoAlteracaoTipo;
	private Date dataValidacao;
	private String imovelSubcategoria;
	
	private String nomeUsuario;
	
	private transient Boolean informativo = false;

	private transient Short posicao = 9;
	
	private Boolean habilitaAlteracao = true;
	
	public String getColunaValorAnterior() {
		return colunaValorAnterior;
	}
	public void setColunaValorAnterior(String colunaValorAnterior) {
		this.colunaValorAnterior = colunaValorAnterior;
	}
	public String getColunaValorAtual() {
		return colunaValorAtual;
	}
	public void setColunaValorAtual(String colunaValorAtual) {
		this.colunaValorAtual = colunaValorAtual;
	}
	public String getDescricaoAlteracaoTipo() {
		return descricaoAlteracaoTipo;
	}
	public void setDescricaoAlteracaoTipo(String descricaoAlteracaoTipo) {
		this.descricaoAlteracaoTipo = descricaoAlteracaoTipo;
	}
	public String getDescricaoColuna() {
		return descricaoColuna;
	}
	public void setDescricaoColuna(String descricaoColuna) {
		this.descricaoColuna = descricaoColuna;
	}
	public String getDescricaoTabela() {
		return descricaoTabela;
	}
	public void setDescricaoTabela(String descricaoTabela) {
		this.descricaoTabela = descricaoTabela;
	}
	public Integer getIdAlteracaoTipo() {
		return idAlteracaoTipo;
	}
	public void setIdAlteracaoTipo(Integer idAlteracaoTipo) {
		this.idAlteracaoTipo = idAlteracaoTipo;
	}
	public Integer getIdTabela() {
		return idTabela;
	}
	public void setIdTabela(Integer idTabela) {
		this.idTabela = idTabela;
	}
	public Integer getIdTabelaAtualizacaoCadastral() {
		return idTabelaAtualizacaoCadastral;
	}
	public void setIdTabelaAtualizacaoCadastral(Integer idTabelaAtualizacaoCadastral) {
		this.idTabelaAtualizacaoCadastral = idTabelaAtualizacaoCadastral;
	}
	public Integer getIdTabelaColuna() {
		return idTabelaColuna;
	}
	public void setIdTabelaColuna(Integer idTabelaColuna) {
		this.idTabelaColuna = idTabelaColuna;
	}
	public Integer getIdTabelaColunaAtualizacaoCadastral() {
		return idTabelaColunaAtualizacaoCadastral;
	}
	public void setIdTabelaColunaAtualizacaoCadastral(Integer idTabelaColunaAtualizacaoCadastral) {
		this.idTabelaColunaAtualizacaoCadastral = idTabelaColunaAtualizacaoCadastral;
	}
	public Short getIndicadorAutorizado() {
		return indicadorAutorizado;
	}
	public void setIndicadorAutorizado(Short indicadorAutorizado) {
		this.indicadorAutorizado = indicadorAutorizado;
	}
	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}
	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	public Date getDataValidacao() {
		return dataValidacao;
	}
	public void setDataValidacao(Date dataValidacao) {
		this.dataValidacao = dataValidacao;
	}
	public String getImovelSubcategoria() {
		return imovelSubcategoria;
	}
	public void setImovelSubcategoria(String imovelSubcategoria) {
		this.imovelSubcategoria = imovelSubcategoria;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public Boolean getInformativo() {
		return informativo;
	}
	public void setInformativo(Boolean informativo) {
		this.informativo = informativo;
	}
	public String getNomeColuna() {
		return nomeColuna;
	}
	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Short getPosicao() {
		return posicao;
	}
	public void setPosicao(Short posicao) {
		this.posicao = posicao;
	}
	
	public Boolean getHabilitaAlteracao() {
		return !informativo && dataValidacao == null;
	}
	public void setHabilitaAlteracao(Boolean habilitaAlteracao) {
		this.habilitaAlteracao = habilitaAlteracao;
	}
	public String toString() {
		return "DadosTabelaAtualizacaoCadastralHelper [descricaoTabela=" + descricaoTabela + ", descricaoColuna=" + descricaoColuna + ", colunaValorAnterior="
				+ colunaValorAnterior + ", colunaValorAtual=" + colunaValorAtual + ", dataValidacao=" + dataValidacao + ", nomeUsuario=" + nomeUsuario + "]";
	}
}
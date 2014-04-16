package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * Bean do [UC0713] Emitir Ordem de Servico Seletiva 
 *
 * @author Ivan Sérgio
 * @date 10/11/2007
 */
public class RelatorioEmitirOrdemServicoSeletivaBean implements RelatorioBean {
	
	private String descricaoTipoServico;
	private String codigoElo;
	private String nomeElo;
	private String idLocalidade;
	private String nomeLocalidade;
	private String codigoSetorComercial;
	private String nomeSetorComercial;
	private String quadra;
	private String idFirma;
	private String nomeFirma;
	private String nomeProjeto;
	
	private String inscricao;
	private String matriculaImovel;
	private String endereco;
	private String numeroOrdem;
	private Integer total = 0;
	
	public String getCodigoElo() {
		return codigoElo;
	}
	public void setCodigoElo(String codigoElo) {
		this.codigoElo = codigoElo;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}
	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getIdFirma() {
		return idFirma;
	}
	public void setIdFirma(String idFirma) {
		this.idFirma = idFirma;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeElo() {
		return nomeElo;
	}
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}
	public String getNomeFirma() {
		return nomeFirma;
	}
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	public String getNumeroOrdem() {
		return numeroOrdem;
	}
	public void setNumeroOrdem(String numeroOrdem) {
		this.numeroOrdem = numeroOrdem;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	
	
}

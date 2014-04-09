package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
 * 
 * @author Rafael Pinto
 * @date 08/01/2008
 */
public class RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	
	private Integer unidadeNegocio;
	private String nomeUnidadeNegocio;
	
	private Integer gerenciaRegional;
	private String nomeGerenciaRegional;
	
	private Integer localidade;
	private String descricaoLocalidade;
	
	private Integer setorComercial;
	private String descricaoSetorComercial;
	
	private Short rota;
	private Integer sequencialRota;
	private Integer numeroQuadra;
	
	private String nomeCliente;
	private String endereco;
	private String matriculaImovel;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	private Integer subCategoria;
	private Short economias;
	
	private Integer referenciaInicial;
	private Integer referenciaFinal;
	
	private Integer quantidadeFaturasAtraso;
	private BigDecimal valorFaturasAtras;
	
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}
	
	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	
	public Integer getLocalidade() {
		return localidade;
	}
	
	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	
	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}
	
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}
	
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	
	public Short getRota() {
		return rota;
	}
	
	public void setRota(Short rota) {
		this.rota = rota;
	}
	
	public Integer getSequencialRota() {
		return sequencialRota;
	}
	
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	
	public Integer getSetorComercial() {
		return setorComercial;
	}
	
	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
	}
	
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	
	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}
	
	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Short getEconomias() {
		return economias;
	}

	public void setEconomias(Short economias) {
		this.economias = economias;
	}

	public Integer getQuantidadeFaturasAtraso() {
		return quantidadeFaturasAtraso;
	}

	public void setQuantidadeFaturasAtraso(Integer quantidadeFaturasAtraso) {
		this.quantidadeFaturasAtraso = quantidadeFaturasAtraso;
	}

	public Integer getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(Integer referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public Integer getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(Integer referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	public Integer getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(Integer subCategoria) {
		this.subCategoria = subCategoria;
	}

	public BigDecimal getValorFaturasAtras() {
		return valorFaturasAtras;
	}

	public void setValorFaturasAtras(BigDecimal valorFaturasAtras) {
		this.valorFaturasAtras = valorFaturasAtras;
	}	
	
	
}

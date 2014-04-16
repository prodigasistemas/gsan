package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de imoveis por Situação da Ligação da Água 
 *
 * @author Rafael Pinto
 * @date 30/11/2007
 */
public class RelatorioImoveisFaturasAtrasoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idImovel;

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
	
	private String nomeClienteUsuario;
	private String endereco;
	private String matriculaImovel;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;

	private Integer quantidadeFaturasAtraso;

	private Integer referenciaFaturasAtrasoInicial;
	private Integer referenciaFaturasAtrasoFinal;
	
	private BigDecimal valorFaturasAtrasoSemEncargos;
	private BigDecimal valorFaturasAtrasoComEncargos;

	private Date vencimento;
	
	private String cpfOuCnpjClienteUsuario;
	
	private String nomeCliente;
	private Integer idCliente;

	private Integer idConta;
	private Short indicadorCobrancaMultaConta;

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
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
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeCliente) {
		this.nomeClienteUsuario = nomeCliente;
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

	
	public Integer getReferenciaFaturasAtrasoFinal() {
	
		return referenciaFaturasAtrasoFinal;
	}

	
	public void setReferenciaFaturasAtrasoFinal(Integer referenciaFaturasAtrasoFinal) {
	
		this.referenciaFaturasAtrasoFinal = referenciaFaturasAtrasoFinal;
	}

	
	public Integer getReferenciaFaturasAtrasoInicial() {
	
		return referenciaFaturasAtrasoInicial;
	}

	
	public void setReferenciaFaturasAtrasoInicial(
			Integer referenciaFaturasAtrasoInicial) {
	
		this.referenciaFaturasAtrasoInicial = referenciaFaturasAtrasoInicial;
	}

	
	public Integer getQuantidadeFaturasAtraso() {
	
		return quantidadeFaturasAtraso;
	}

	
	public void setQuantidadeFaturasAtraso(Integer quantidadeFaturasAtraso) {
	
		this.quantidadeFaturasAtraso = quantidadeFaturasAtraso;
	}

	
	public BigDecimal getValorFaturasAtrasoSemEncargos() {
	
		return valorFaturasAtrasoSemEncargos;
	}

	
	public void setValorFaturasAtrasoSemEncargos(BigDecimal valorFaturasAtraso) {
	
		this.valorFaturasAtrasoSemEncargos = valorFaturasAtraso;
	}

	
	public String getSituacaoLigacaoEsgoto() {
	
		return situacaoLigacaoEsgoto;
	}

	
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
	
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getCpfOuCnpjClienteUsuario() {
		return cpfOuCnpjClienteUsuario;
	}

	public void setCpfOuCnpjClienteUsuario(String cpfOuCnpjClienteUsuario) {
		this.cpfOuCnpjClienteUsuario = cpfOuCnpjClienteUsuario;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeClienteResponsavel) {
		this.nomeCliente = nomeClienteResponsavel;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idClienteResponsavel) {
		this.idCliente = idClienteResponsavel;
	}

	public BigDecimal getValorFaturasAtrasoComEncargos() {
		return valorFaturasAtrasoComEncargos;
	}

	public void setValorFaturasAtrasoComEncargos(
			BigDecimal valorFaturasAtrasoComEncargos) {
		this.valorFaturasAtrasoComEncargos = valorFaturasAtrasoComEncargos;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public Short getIndicadorCobrancaMultaConta() {
		return indicadorCobrancaMultaConta;
	}

	public void setIndicadorCobrancaMultaConta(Short indicadorCobrancaMultaConta) {
		this.indicadorCobrancaMultaConta = indicadorCobrancaMultaConta;
	}
}

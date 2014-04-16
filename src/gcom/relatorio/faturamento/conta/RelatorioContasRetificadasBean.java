package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */
public class RelatorioContasRetificadasBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;

	private String dataRetificacao;
	private String idResponsavel;
	private String consumoRateioAgua;
	private String endereco;
	private String referencia;
	private String idMotivo;
	private String valorOriginal;
	private String valorNovo;
	private String idRA;
	private String inscricao;
	private String idLocalidade;
	private String nomeLocalidade;
	private String matricula;
	private String unidadeNegocio;
	private String idUnidadeNegocio;
	private String idGerenciaRegional;
	private String gerenciaRegional;
	
	private String ano;
	private BigDecimal valorNovoAno;
	private BigDecimal valorOriginalAno;
	private int quantidadeContasAno;
	
	
	
	public RelatorioContasRetificadasBean(){}
	
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public RelatorioContasRetificadasBean(
			String dataRetificacao,
			String idResponsavel,
			String endereco,
			String referencia,
			String idMotivo,
			String valorOriginal,
			String valorNovo,
			String idRA,
			String inscricao,
			String idLocalidade,
			String nomeLocalidade,
			String matricula) {
		
		this.dataRetificacao = dataRetificacao;
		this.idResponsavel = idResponsavel;
		this.endereco = endereco;
		this.referencia = referencia;
		this.idMotivo = idMotivo;
		this.valorOriginal = valorOriginal;
		this.valorNovo = valorNovo;
		this.idRA = idRA;
		this.nomeLocalidade = nomeLocalidade;
		this.inscricao = inscricao;
		this.idLocalidade = idLocalidade;
		this.matricula = matricula;

	}
	
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getDataRetificacao() {
		return dataRetificacao;
	}

	public void setDataRetificacao(String dataRetificacao) {
		this.dataRetificacao = dataRetificacao;
	}

	public String getConsumoRateioAgua() {
		return consumoRateioAgua;
	}

	public void setConsumoRateioAgua(String consumoRateioAgua) {
		this.consumoRateioAgua = consumoRateioAgua;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(String idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getIdRA() {
		return idRA;
	}

	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}

	public String getValorNovo() {
		return valorNovo;
	}

	public void setValorNovo(String valorNovo) {
		this.valorNovo = valorNovo;
	}

	public String getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public BigDecimal getValorNovoAno() {
		return valorNovoAno;
	}

	public void setValorNovoAno(BigDecimal valorNovoAno) {
		this.valorNovoAno = valorNovoAno;
	}

	public BigDecimal getValorOriginalAno() {
		return valorOriginalAno;
	}

	public void setValorOriginalAno(BigDecimal valorOriginalAno) {
		this.valorOriginalAno = valorOriginalAno;
	}

	public int getQuantidadeContasAno() {
		return quantidadeContasAno;
	}

	public void setQuantidadeContasAno(int quantidadeContasAno) {
		this.quantidadeContasAno = quantidadeContasAno;
	}
				
}

package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
/**
 * Bean responsável de enviar os campos(fields) que serão exibidos no detail do relatório
 * Pagamento para Entidades Beneficentes Analitico.
 * 
 * @author Daniel Alves
 * @created 25 de Janeiro de 2010
 */
public class RelatorioPagamentoEntidadesBeneficentesAnaliticoBean implements RelatorioBean{
	
	private String entidadeBeneficente;
	
	private String idEntidadeBeneficente;
	
	private String gerenciaRegional;
	
	private String idGerenciaRegional;
	
	private String unidadeNegocio;
	
	private String idUnidadeNegocio;
	
	private String localidade;
	
	private String idLocalidade;
	
	private String matricula;
	
	private String inscricao;
	
	private String nomeCliente;
	
	private String endereco;
	
	private String referencia;
	
	private BigDecimal valor;	
	
	public RelatorioPagamentoEntidadesBeneficentesAnaliticoBean(){}

	public RelatorioPagamentoEntidadesBeneficentesAnaliticoBean(String endereco,
			String entidadeBeneficente, String gerenciaRegional,
			String idEntidadeBeneficente, String idGerenciaRegional,
			String idLocalidade, String idUnidadeNegocio, String inscricao,
			String localidade, String matricula, String nomeCliente,
			String referencia, String unidadeNegocio, BigDecimal valor) {
		super();
		this.endereco = endereco;
		this.entidadeBeneficente = entidadeBeneficente;
		this.gerenciaRegional = gerenciaRegional;
		this.idEntidadeBeneficente = idEntidadeBeneficente;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.inscricao = inscricao;
		this.localidade = localidade;
		this.matricula = matricula;
		this.nomeCliente = nomeCliente;
		this.referencia = referencia;
		this.unidadeNegocio = unidadeNegocio;
		this.valor = valor;
	}
		
	public String getEntidadeBeneficente() {
		return entidadeBeneficente;
	}

	public void setEntidadeBeneficente(String entidadeBeneficente) {
		this.entidadeBeneficente = entidadeBeneficente;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getIdEntidadeBeneficente() {
		return idEntidadeBeneficente;
	}

	public void setIdEntidadeBeneficente(String idEntidadeBeneficente) {
		this.idEntidadeBeneficente = idEntidadeBeneficente;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	
	
}

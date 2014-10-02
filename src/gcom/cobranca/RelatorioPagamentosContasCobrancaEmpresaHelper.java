package gcom.cobranca;

import gcom.cadastro.empresa.Empresa;

import java.io.Serializable;

public class RelatorioPagamentosContasCobrancaEmpresaHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private Empresa empresa;

	private int referenciaPagamentoInicial;

	private int referenciaPagamentoFinal;

	private String opcaoTotalizacao;

	private Integer codigoLocalidade;

	private Integer codigoGerencia;

	private Integer unidadeNegocio;

	public Integer getCodigoGerencia() {
		return codigoGerencia;
	}

	public void setCodigoGerencia(Integer codigoGerencia) {
		this.codigoGerencia = codigoGerencia;
	}

	public Integer getCodigoLocalidade() {
		return codigoLocalidade;
	}

	public void setCodigoLocalidade(Integer codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public int getReferenciaPagamentoFinal() {
		return referenciaPagamentoFinal;
	}

	public void setReferenciaPagamentoFinal(int referenciaPagamentoFinal) {
		this.referenciaPagamentoFinal = referenciaPagamentoFinal;
	}

	public int getReferenciaPagamentoInicial() {
		return referenciaPagamentoInicial;
	}

	public void setReferenciaPagamentoInicial(int referenciaPagamentoInicial) {
		this.referenciaPagamentoInicial = referenciaPagamentoInicial;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * Construtor de RelatorioPagamentosContasCobrancaEmpresaHelper 
	 * 
	 * @param empresa
	 * @param referenciaPagamentoInicial
	 * @param referenciaPagamentoFinal
	 * @param opcaoEmissao
	 * @param tipoFormatoRelatorio
	 * @param opcaoTotalizacao
	 * @param codigoLocalidade
	 * @param codigoGerencia
	 * @param unidadeNegocio
	 */
	public RelatorioPagamentosContasCobrancaEmpresaHelper(Empresa empresa, int referenciaPagamentoInicial, int referenciaPagamentoFinal,  String opcaoTotalizacao, Integer codigoLocalidade, Integer codigoGerencia, Integer unidadeNegocio) {
		super();
		
		this.empresa = empresa;
		this.referenciaPagamentoInicial = referenciaPagamentoInicial;
		this.referenciaPagamentoFinal = referenciaPagamentoFinal;
		this.opcaoTotalizacao = opcaoTotalizacao;
		this.codigoLocalidade = codigoLocalidade;
		this.codigoGerencia = codigoGerencia;
		this.unidadeNegocio = unidadeNegocio;
	}

}

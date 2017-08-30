package gcom.cobranca;

import gcom.cadastro.empresa.Empresa;

import java.io.Serializable;
import java.util.Date;

public class RelatorioPagamentosContasCobrancaEmpresaHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private Empresa empresa;

	private String dataPagamentoInicial;

	private String dataPagamentoFinal;

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

	public String getDataPagamentoFinal() {
		return dataPagamentoFinal;
	}

	public void setReferenciaPagamentoFinal(String referenciaPagamentoFinal) {
		this.dataPagamentoFinal = referenciaPagamentoFinal;
	}

	public String getDataPagamentoInicial() {
		return dataPagamentoInicial;
	}

	public void setReferenciaPagamentoInicial(String referenciaPagamentoInicial) {
		this.dataPagamentoInicial = referenciaPagamentoInicial;
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
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param opcaoEmissao
	 * @param tipoFormatoRelatorio
	 * @param opcaoTotalizacao
	 * @param codigoLocalidade
	 * @param codigoGerencia
	 * @param unidadeNegocio
	 */
	public RelatorioPagamentosContasCobrancaEmpresaHelper(Empresa empresa, String dataPagamentoInicial, String dataPagamentoFinal,  String opcaoTotalizacao, Integer codigoLocalidade, Integer codigoGerencia, Integer unidadeNegocio) {
		super();
		
		this.empresa = empresa;
		this.dataPagamentoInicial = dataPagamentoInicial;
		this.dataPagamentoFinal = dataPagamentoFinal;
		this.opcaoTotalizacao = opcaoTotalizacao;
		this.codigoLocalidade = codigoLocalidade;
		this.codigoGerencia = codigoGerencia;
		this.unidadeNegocio = unidadeNegocio;
	}

}

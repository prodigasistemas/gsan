package gcom.relatorio.faturamento;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * classe responsável por criar o Relatório Devolução dos Pagamentos em Duplicidade.
 * 
 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2011
 */
public class RelatorioDevolucaoPagamentosDuplicidadeHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idLocalidade;
	private String nomeLocalidade;
	private String idGerencia;
	private String nomeGerencia;
	private String idUnidade;
	private String nomeUnidade;
	private String numeroRA;
	private String matricula;
	private String mesAnoPagamentoDuplicidade;
	private BigDecimal valorPagamentoDuplicidade;
	private String mesAnoConta;
	private BigDecimal valorConta;
	private BigDecimal creditoRealizado;
	private BigDecimal creditoARealizar;
	private String dataAtualizacao;
	
	public BigDecimal getCreditoARealizar() {
		return creditoARealizar;
	}
	
	public void setCreditoARealizar(BigDecimal creditoARealizar) {
		this.creditoARealizar = creditoARealizar;
	}

	public BigDecimal getCreditoRealizado() {
		return creditoRealizado;
	}

	public void setCreditoRealizado(BigDecimal creditoRealizado) {
		this.creditoRealizado = creditoRealizado;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getMesAnoPagamentoDuplicidade() {
		return mesAnoPagamentoDuplicidade;
	}

	public void setMesAnoPagamentoDuplicidade(String mesAnoPagamentoDuplicidade) {
		this.mesAnoPagamentoDuplicidade = mesAnoPagamentoDuplicidade;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public BigDecimal getValorPagamentoDuplicidade() {
		return valorPagamentoDuplicidade;
	}

	public void setValorPagamentoDuplicidade(BigDecimal valorPagamentoDuplicidade) {
		this.valorPagamentoDuplicidade = valorPagamentoDuplicidade;
	}

	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdGerencia() {
		return idGerencia;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RelatorioDevolucaoPagamentosDuplicidadeHelper)) {
			return false;
		} else {
			RelatorioDevolucaoPagamentosDuplicidadeHelper helper = (RelatorioDevolucaoPagamentosDuplicidadeHelper) obj;

			// Verificamos se todas as propriedades que identificam o objeto sao
			// iguais
//			return propriedadesIguais(this.numeroRA,helper.numeroRA) && 
//				propriedadesIguais(this.matricula, helper.matricula) && 
//				propriedadesIguais(this.mesAnoPagamentoDuplicidade, helper.mesAnoPagamentoDuplicidade);

			return propriedadesIguais(this.numeroRA,helper.numeroRA);

		}
	}
	
	/**
	 * Compara duas properiedades inteiras, comparando seus valores para
	 * descobrirmos se sao iguais
	 * 
	 * @param pro1
	 *            Primeira propriedade
	 * @param pro2
	 *            Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais(String pro1, String pro2) {
		if (pro2 != null) {
			if (!pro2.equals(pro1)) {
				return false;
			}
		} else if (pro1 != null) {
			return false;
		}

		// Se chegou ate aqui quer dizer que as propriedades sao iguais
		return true;
	}
	
	
	
	
}

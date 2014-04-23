package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 *[UC0958] - Gerar Relatorio de Juros, Multas e Debitos Cancelados.
 *
 * @author Marlon Patrick
 * @since 07/10/2009
 */
public class RelatorioJurosMultasDebitosCanceladosBean implements RelatorioBean, Comparable<RelatorioJurosMultasDebitosCanceladosBean>{

	private Date dataCancelamento;
	
	private String responsavel;
	
	private String inscricao;
	
	private String matricula;
	
	private String endereco;
	
	private String mesAnoReferencia;
	
	private String tipoDebito;
	
	private BigDecimal valorDebito;

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String anoMesReferencia) {
		this.mesAnoReferencia = anoMesReferencia;
	}

	public String getTipoDebito() {
		return tipoDebito;
	}

	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}	

	public int compareTo(RelatorioJurosMultasDebitosCanceladosBean o) {
		int retorno = this.getDataCancelamento().compareTo(o.getDataCancelamento());
		
		if(retorno != 0 ){
			return retorno;
		}
		
		retorno = new Integer(this.getMatricula()).compareTo(new Integer(o.getMatricula()));

		if(retorno != 0 ){
			return retorno;
		}

		return this.getMesAnoReferencia().compareTo(o.getMesAnoReferencia());
	}
}

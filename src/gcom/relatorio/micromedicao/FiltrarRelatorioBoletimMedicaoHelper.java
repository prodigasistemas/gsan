package gcom.relatorio.micromedicao;

import java.io.Serializable;


/**
 * [UC1054] - Gerar Relatório Boletim de Medição
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de Boletim de Medição
 * 
 * @author Hugo Leonardo
 * @date 04/08/2010
 */
public class FiltrarRelatorioBoletimMedicaoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	private String empresa;
	private String numeroContrato;
	private String gerenciaRegional;
	private String localidadeInicial;
	private String localidadeFinal;
	
	private String formaGeracao;
	
	
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	
	public String getLocalidadeFinal() {
		return localidadeFinal;
	}
	
	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}
	
	public String getLocalidadeInicial() {
		return localidadeInicial;
	}
	
	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}
	
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	
	public String getNumeroContrato() {
		return numeroContrato;
	}
	
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getFormaGeracao() {
		return formaGeracao;
	}

	public void setFormaGeracao(String formaGeracao) {
		this.formaGeracao = formaGeracao;
	}
	
}

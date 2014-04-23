package gcom.gui.relatorio.micromedicao;


import org.apache.struts.action.ActionForm;

/**
 * [UC1054] - Gerar Relatório Boletim de Medição
 * 
 * @author Hugo Leonardo
 *
 * @date 04/08/2010
 */

public class GerarRelatorioBoletimMedicaoForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	
	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	
	private String empresa;
	private String gerenciaRegional;
	private String numeroContrato;
	private String mesAnoReferencia;

	private String formaGeracao;
	

	public void reset(){
		this.localidadeInicial = null;
		this.nomeLocalidadeInicial = null;
		this.localidadeFinal = null;
		this.nomeLocalidadeFinal = null;
		this.empresa = null;
		this.gerenciaRegional = null;
		this.numeroContrato = null;
		this.mesAnoReferencia = null;
		this.formaGeracao = null;
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

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

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

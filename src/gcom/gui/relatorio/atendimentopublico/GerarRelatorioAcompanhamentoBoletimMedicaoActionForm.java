package gcom.gui.relatorio.atendimentopublico;

import org.apache.struts.action.ActionForm;

/**[UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
 * Formulário responsável por armazenar os dados da tela
 * relatorio_acompanhamento_boletim_medicao_gerar.jsp.
 * 
 * @author Diogo Peixoto
 * @since 17/06/2011
 */
public class GerarRelatorioAcompanhamentoBoletimMedicaoActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	private String dataReferencia;
	private String idContrato;
	
	public GerarRelatorioAcompanhamentoBoletimMedicaoActionForm(){
		
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(String dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}
}
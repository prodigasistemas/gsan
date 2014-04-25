package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da atualização do dados das atividades de uma OS
 * 
 * @author Raphael Rossiter
 * @date 15/09/2006
 */
public class ManterDadosAtividadesOrdemServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String numeroOSForm;
	
	private String dataRoteiroForm;
	
	private String dataEncerramentoForm;
	
	private String idAtividade;
	
	private String descricaoAtividade;
	
	private String caminhoRetorno;
	
	
	public String getCaminhoRetorno() {
		return caminhoRetorno;
	}

	public void setCaminhoRetorno(String caminhoRetorno) {
		this.caminhoRetorno = caminhoRetorno;
	}

	public String getDataEncerramentoForm() {
		return dataEncerramentoForm;
	}

	public void setDataEncerramentoForm(String dataEncerramentoForm) {
		this.dataEncerramentoForm = dataEncerramentoForm;
	}

	public String getDataRoteiroForm() {
		return dataRoteiroForm;
	}

	public void setDataRoteiroForm(String dataRoteiroForm) {
		this.dataRoteiroForm = dataRoteiroForm;
	}

	public String getNumeroOSForm() {
		return numeroOSForm;
	}

	public void setNumeroOSForm(String numeroOSForm) {
		this.numeroOSForm = numeroOSForm;
	}

	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

	public String getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}

	
}

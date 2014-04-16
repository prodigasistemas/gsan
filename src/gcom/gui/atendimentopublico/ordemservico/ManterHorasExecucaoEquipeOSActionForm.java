package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da atualização do dados das atividades de uma OS
 * (Horas)
 * 
 * @author Raphael Rossiter
 * @date 15/09/2006
 */
public class ManterHorasExecucaoEquipeOSActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String numeroOS;
	
	private String idAtividade;
	
	private String descricaoAtividade;
	
	private String dataExecucao;
	
	private String horaInicioExecucao;
	
	private String horaFimExecucao;
	
	private String idEquipeProgramada;
	
	private String idEquipeNaoProgramada;
	
	private String descricaoEquipeNaoProgramada;
	
	private String dataEncerramentoOS;
	
	
	

	public String getDataEncerramentoOS() {
		return dataEncerramentoOS;
	}

	public void setDataEncerramentoOS(String dataEncerramentoOS) {
		this.dataEncerramentoOS = dataEncerramentoOS;
	}

	public String getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getIdEquipeNaoProgramada() {
		return idEquipeNaoProgramada;
	}

	public void setIdEquipeNaoProgramada(String idEquipeNaoProgramada) {
		this.idEquipeNaoProgramada = idEquipeNaoProgramada;
	}

	public String getIdEquipeProgramada() {
		return idEquipeProgramada;
	}

	public void setIdEquipeProgramada(String idEquipeProgramada) {
		this.idEquipeProgramada = idEquipeProgramada;
	}

	public String getDescricaoEquipeNaoProgramada() {
		return descricaoEquipeNaoProgramada;
	}

	public void setDescricaoEquipeNaoProgramada(String descricaoEquipeNaoProgramada) {
		this.descricaoEquipeNaoProgramada = descricaoEquipeNaoProgramada;
	}

	public String getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

	public String getHoraFimExecucao() {
		return horaFimExecucao;
	}

	public void setHoraFimExecucao(String horaFimExecucao) {
		this.horaFimExecucao = horaFimExecucao;
	}

	public String getHoraInicioExecucao() {
		return horaInicioExecucao;
	}

	public void setHoraInicioExecucao(String horaInicioExecucao) {
		this.horaInicioExecucao = horaInicioExecucao;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

}

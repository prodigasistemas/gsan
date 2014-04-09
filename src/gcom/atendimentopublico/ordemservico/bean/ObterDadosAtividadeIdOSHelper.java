package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.Atividade;

/**
 * [UC0462] Obter Dados das Atividades da OS
 * 
 * Esta classe tem por finalidade obter ids das atividades da ordem de serviço. 
 * 
 * @author Leonardo Regis
 * @date 13/09/2006
 */
public class ObterDadosAtividadeIdOSHelper {

	private Atividade atividade;
	private Integer idOS;
	
	private boolean isMaterial = false;
	private boolean isPeriodo = false;

	public ObterDadosAtividadeIdOSHelper(){}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public boolean getMaterial() {
		return isMaterial;
	}

	public void setMaterial(boolean isMaterial) {
		this.isMaterial = isMaterial;
	}

	public boolean getPeriodo() {
		return isPeriodo;
	}

	public void setPeriodo(boolean isPeriodo) {
		this.isPeriodo = isPeriodo;
	}

	public Integer getIdOS() {
		return idOS;
	}

	public void setIdOS(Integer idOS) {
		this.idOS = idOS;
	}
}

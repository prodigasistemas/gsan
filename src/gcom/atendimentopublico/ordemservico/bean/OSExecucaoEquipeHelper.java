package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.OsExecucaoEquipe;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipeComponentes;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0461] Manter Dados das Atividades da Ordem de Serviço 
 * 
 * @author Raphael Rossiter
 * @date 18/08/2006
 */
public class OSExecucaoEquipeHelper {
	
	private OsExecucaoEquipe osExecucaoEquipe;
	
	private Collection<OsExecucaoEquipeComponentes> colecaoOsExecucaoEquipeComponentes;
	
	
	public OSExecucaoEquipeHelper(){}


	public Collection<OsExecucaoEquipeComponentes> getColecaoOsExecucaoEquipeComponentes() {
		return colecaoOsExecucaoEquipeComponentes;
	}


	public void setColecaoOsExecucaoEquipeComponentes(
			Collection<OsExecucaoEquipeComponentes> colecaoOsExecucaoEquipeComponentes) {
		this.colecaoOsExecucaoEquipeComponentes = colecaoOsExecucaoEquipeComponentes;
	}


	public OsExecucaoEquipe getOsExecucaoEquipe() {
		return osExecucaoEquipe;
	}


	public void setOsExecucaoEquipe(OsExecucaoEquipe osExecucaoEquipe) {
		this.osExecucaoEquipe = osExecucaoEquipe;
	}
	
	
	

}

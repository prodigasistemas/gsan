package gcom.gui.atendimentopublico.ordemservico;


import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeOSHelper;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class DetalharMaterialUtilizadoPopupActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	//Dados Gerais
	private String numeroOS;
	private String atividadeId;
    private Collection<ObterDadosAtividadeOSHelper> colecaoOSAtividade = new ArrayList<ObterDadosAtividadeOSHelper>();
    
	public String getAtividadeId() {
		return atividadeId;
	}
	public void setAtividadeId(String atividadeId) {
		this.atividadeId = atividadeId;
	}
	public Collection<ObterDadosAtividadeOSHelper> getColecaoOSAtividade() {
		return colecaoOSAtividade;
	}
	public void setColecaoOSAtividade(
			Collection<ObterDadosAtividadeOSHelper> colecaoOSAtividade) {
		this.colecaoOSAtividade = colecaoOSAtividade;
	}
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
    
}

package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.EquipeComponentes;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0471] Obter Dados da Equipe
 * 
 * @author Raphael Rossiter
 * @date 01/08/2006
 */
public class ObterDadosEquipe {
	
	private Equipe equipe;
	
	private Collection<EquipeComponentes> colecaoEquipeComponentes;
	
	public ObterDadosEquipe(){}

	public Collection<EquipeComponentes> getColecaoEquipeComponentes() {
		return colecaoEquipeComponentes;
	}

	public void setColecaoEquipeComponentes(
			Collection<EquipeComponentes> colecaoEquipeComponentes) {
		this.colecaoEquipeComponentes = colecaoEquipeComponentes;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	

}

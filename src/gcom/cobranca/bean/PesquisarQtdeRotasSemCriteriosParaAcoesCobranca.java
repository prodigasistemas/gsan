package gcom.cobranca.bean;

import java.util.Collection;

public class PesquisarQtdeRotasSemCriteriosParaAcoesCobranca {
	
	public Integer idGrupoCobranca;
	
	public Collection<Integer> idsRotas;
	public Collection<Integer> idsAcoesCobranca;
	
	public PesquisarQtdeRotasSemCriteriosParaAcoesCobranca() { }

	public Integer getIdGrupoCobranca() {
		return idGrupoCobranca; 
	}

	public void setIdGrupoCobranca(Integer idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public Collection<Integer> getIdsAcoesCobranca() {
		return idsAcoesCobranca;
	}

	public void setIdsAcoesCobranca(Collection<Integer> idsAcoesCobranca) {
		this.idsAcoesCobranca = idsAcoesCobranca;
	}

	public Collection<Integer> getIdsRotas() {
		return idsRotas;
	}

	public void setIdsRotas(Collection<Integer> idsRotas) {
		this.idsRotas = idsRotas;
	}

}

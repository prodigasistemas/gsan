package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.OrdemServicoAtividade;
import gcom.atendimentopublico.ordemservico.OsAtividadeMaterialExecucao;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0461] Manter Dados das Atividades da Ordem de Serviço 
 * 
 * @author Raphael Rossiter
 * @date 18/08/2006
 */
public class ManterDadosAtividadesOrdemServicoHelper {
	
	private OrdemServicoAtividade ordemServicoAtividade;
	
	private Collection<OsAtividadeMaterialExecucao> colecaoOsAtividadeMaterialExecucao;
	
	private Collection<OSAtividadePeriodoExecucaoHelper> colecaoOSAtividadePeriodoExecucaoHelper;
	
	
	public ManterDadosAtividadesOrdemServicoHelper(){}

	
	

	public Collection<OsAtividadeMaterialExecucao> getColecaoOsAtividadeMaterialExecucao() {
		return colecaoOsAtividadeMaterialExecucao;
	}

	public void setColecaoOsAtividadeMaterialExecucao(
			Collection<OsAtividadeMaterialExecucao> colecaoOsAtividadeMaterialExecucao) {
		this.colecaoOsAtividadeMaterialExecucao = colecaoOsAtividadeMaterialExecucao;
	}

	public Collection<OSAtividadePeriodoExecucaoHelper> getColecaoOSAtividadePeriodoExecucaoHelper() {
		return colecaoOSAtividadePeriodoExecucaoHelper;
	}

	public void setColecaoOSAtividadePeriodoExecucaoHelper(
			Collection<OSAtividadePeriodoExecucaoHelper> colecaoOSAtividadePeriodoExecucaoHelper) {
		this.colecaoOSAtividadePeriodoExecucaoHelper = colecaoOSAtividadePeriodoExecucaoHelper;
	}

	public OrdemServicoAtividade getOrdemServicoAtividade() {
		return ordemServicoAtividade;
	}

	public void setOrdemServicoAtividade(OrdemServicoAtividade ordemServicoAtividade) {
		this.ordemServicoAtividade = ordemServicoAtividade;
	}
	
	

}

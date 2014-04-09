package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.OsAtividadeMaterialExecucao;


/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0461] Manter Dados das Atividades da Ordem de Serviço 
 * 
 * @author Raphael Rossiter
 * @date 18/10/2006
 */
public class OsAtividadeMaterialExecucaoHelper {
	
	private OsAtividadeMaterialExecucao osAtividadeMaterialExecucao;
	
	private Integer quantidade;

	public OsAtividadeMaterialExecucao getOsAtividadeMaterialExecucao() {
		return osAtividadeMaterialExecucao;
	}

	public void setOsAtividadeMaterialExecucao(
			OsAtividadeMaterialExecucao osAtividadeMaterialExecucao) {
		this.osAtividadeMaterialExecucao = osAtividadeMaterialExecucao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	

}

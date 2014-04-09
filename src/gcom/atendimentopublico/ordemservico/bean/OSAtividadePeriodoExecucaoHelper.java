package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao;

import java.util.Collection;
import java.util.Date;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0461] Manter Dados das Atividades da Ordem de Serviço 
 * 
 * @author Raphael Rossiter
 * @date 18/08/2006
 */
public class OSAtividadePeriodoExecucaoHelper {
	
	private OsAtividadePeriodoExecucao osAtividadePeriodoExecucao;
	
	//Objetos utilizados apenas para facilitar a quebra na exibição
	private Date dataExecucaoParaQuebra;
	private String horaMinutoInicioParaQuebra;
	private String horaMinutoFimParaQuebra;
	
	private Collection<OSExecucaoEquipeHelper> colecaoOSExecucaoEquipeHelper;
	
	
	public OSAtividadePeriodoExecucaoHelper(){}

	

	public String getHoraMinutoFimParaQuebra() {
		return horaMinutoFimParaQuebra;
	}



	public void setHoraMinutoFimParaQuebra(String horaMinutoFimParaQuebra) {
		this.horaMinutoFimParaQuebra = horaMinutoFimParaQuebra;
	}



	public String getHoraMinutoInicioParaQuebra() {
		return horaMinutoInicioParaQuebra;
	}



	public void setHoraMinutoInicioParaQuebra(String horaMinutoInicioParaQuebra) {
		this.horaMinutoInicioParaQuebra = horaMinutoInicioParaQuebra;
	}



	public Date getDataExecucaoParaQuebra() {
		return dataExecucaoParaQuebra;
	}



	public void setDataExecucaoParaQuebra(Date dataExecucaoParaQuebra) {
		this.dataExecucaoParaQuebra = dataExecucaoParaQuebra;
	}



	public Collection<OSExecucaoEquipeHelper> getColecaoOSExecucaoEquipeHelper() {
		return colecaoOSExecucaoEquipeHelper;
	}


	public void setColecaoOSExecucaoEquipeHelper(
			Collection<OSExecucaoEquipeHelper> colecaoOSExecucaoEquipeHelper) {
		this.colecaoOSExecucaoEquipeHelper = colecaoOSExecucaoEquipeHelper;
	}


	public OsAtividadePeriodoExecucao getOsAtividadePeriodoExecucao() {
		return osAtividadePeriodoExecucao;
	}


	public void setOsAtividadePeriodoExecucao(
			OsAtividadePeriodoExecucao osAtividadePeriodoExecucao) {
		this.osAtividadePeriodoExecucao = osAtividadePeriodoExecucao;
	}
	
	

}

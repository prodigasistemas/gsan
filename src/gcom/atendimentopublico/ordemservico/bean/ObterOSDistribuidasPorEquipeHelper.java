package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.Date;

/**
 * [UC0460] Obter Carga de Trabalho
 * 
 * Esta classe tem por finalidade obter dados das os distribuídas para a
 * equipe. 
 * 
 * @author Leonardo Regis
 * @date 14/09/2006
 */
public class ObterOSDistribuidasPorEquipeHelper {

	private Integer idOS = ConstantesSistema.NUMERO_NAO_INFORMADO;
	Date dataFinalProgramacao = null;
	private Collection<Equipe> colecaoEquipe = null;
	
	public Date getDataFinalProgramacao() {
		return dataFinalProgramacao;
	}

	public void setDataFinalProgramacao(Date dataFinalProgramacao) {
		this.dataFinalProgramacao = dataFinalProgramacao;
	}

	public ObterOSDistribuidasPorEquipeHelper(){}

	public Collection<Equipe> getColecaoEquipe() {
		return colecaoEquipe;
	}

	public void setColecaoEquipe(Collection<Equipe> colecaoEquipe) {
		this.colecaoEquipe = colecaoEquipe;
	}

	public Integer getIdOS() {
		return idOS;
	}

	public void setIdOS(Integer idOS) {
		this.idOS = idOS;
	}

}

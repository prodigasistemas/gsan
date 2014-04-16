package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipe;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * [UC0462] Obter Dados das Atividades da OS
 * 
 * Esta classe tem por finalidade obter dados das atividades da ordem de serviço. 
 * 
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public class ObterDadosAtividadesOSHelper {

	private Atividade atividade;
	
	private boolean isMaterial = false;
	private Material material = null;
	private MaterialUnidade materialUnidade = null;
	private BigDecimal qtdeMaterial = null;
	
	private boolean isPeriodo = false;
	private Date dataInicio = null;
	private Date dataFim = null;
	private Equipe equipe = null;
	
	
	private Collection<OsExecucaoEquipe> osExecucaoEquipe = null;

	public ObterDadosAtividadesOSHelper(){}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public boolean isMaterial() {
		return isMaterial;
	}

	public void setMaterial(boolean isMaterial) {
		this.isMaterial = isMaterial;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public MaterialUnidade getMaterialUnidade() {
		return materialUnidade;
	}

	public void setMaterialUnidade(MaterialUnidade materialUnidade) {
		this.materialUnidade = materialUnidade;
	}

	public BigDecimal getQtdeMaterial() {
		return qtdeMaterial;
	}

	public void setQtdeMaterial(BigDecimal qtdeMaterial) {
		this.qtdeMaterial = qtdeMaterial;
	}

	public Collection<OsExecucaoEquipe> getOsExecucaoEquipe() {
		return osExecucaoEquipe;
	}

	public void setOsExecucaoEquipe(Collection<OsExecucaoEquipe> osExecucaoEquipe) {
		this.osExecucaoEquipe = osExecucaoEquipe;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public boolean isPeriodo() {
		return isPeriodo;
	}

	public void setPeriodo(boolean isPeriodo) {
		this.isPeriodo = isPeriodo;
	}
}

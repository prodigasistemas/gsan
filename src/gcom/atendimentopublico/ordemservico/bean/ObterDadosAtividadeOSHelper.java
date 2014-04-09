package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC0462] Obter Dados das Atividades da OS
 * 
 * Esta classe tem por finalidade obter dados das atividades da ordem de serviço. 
 * 
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public class ObterDadosAtividadeOSHelper {

	public final static Integer INDICADOR_MATERIAL = 1;
	public final static Integer INDICADOR_PERIODO = 2;
	
	private Atividade atividade;
	
	private boolean isMaterial = false;
	private Material materialUtilizado = null;
	private MaterialUnidade materialUnidade = null;
	private BigDecimal qtdeMaterial = null;
	
	private boolean isPeriodo = false;
	private Date dataInicio = null;
	private Date dataFim = null;
	private Equipe equipe = null;
	
	public ObterDadosAtividadeOSHelper(){}

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

	public Material getMaterialUtilizado() {
		return materialUtilizado;
	}

	public void setMaterialUtilizado(Material material) {
		this.materialUtilizado = material;
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

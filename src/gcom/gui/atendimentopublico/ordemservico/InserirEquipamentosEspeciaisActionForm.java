package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EquipeEquipamentosEspeciais;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

public class InserirEquipamentosEspeciaisActionForm extends ValidatorActionForm {
//	 Equipamentos Especiais
    private Collection<EquipeEquipamentosEspeciais> equipeEquipamentosEspeciais = new ArrayList<EquipeEquipamentosEspeciais>();
    private String indicadorEquipamentosEspeciais;
    private String descricao;
    private String descricaoAbreviada;
    private String indicadorUso;
    private Date ultimaAlteracao;
    private String quantidade;
    
    private String method = "";
    
    private String deleteEquipamento;
    private String TamanhoColecao;

	public String getTamanhoColecao() {
		return TamanhoColecao;
	}

	public void setTamanhoColecao(String tamanhoColecao) {
		TamanhoColecao = tamanhoColecao;
	}

	public String getDeleteEquipamento() {
		return deleteEquipamento;
	}

	public void setDeleteEquipamento(String deleteEquipamento) {
		this.deleteEquipamento = deleteEquipamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Collection<EquipeEquipamentosEspeciais> getEquipeEquipamentosEspeciais() {
		return equipeEquipamentosEspeciais;
	}

	public void setEquipeEquipamentosEspeciais(
			Collection<EquipeEquipamentosEspeciais> equipeEquipamentosEspeciais) {
		this.equipeEquipamentosEspeciais = equipeEquipamentosEspeciais;
	}

	public String getIndicadorEquipamentosEspeciais() {
		return indicadorEquipamentosEspeciais;
	}

	public void setIndicadorEquipamentosEspeciais(
			String indicadorEquipamentosEspeciais) {
		this.indicadorEquipamentosEspeciais = indicadorEquipamentosEspeciais;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	} 
    
    
	
}

package gcom.util.tabelaauxiliar.unidade;

import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * < <Descrição da Classe>>
 * 
 * @author Rômulo Aurélio
 */
public abstract class TabelaAuxiliarUnidade extends TabelaAuxiliarAbreviada {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MaterialUnidade materialUnidade;

	/**
	 * full constructor
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param descricaoAbreviada
	 *            Descrição do parâmetro
	 */
	

	public TabelaAuxiliarUnidade(Integer id, String descricao,
			String descricaoAbreviada, Short indicadorUso,MaterialUnidade materialUnidade,Date ultimaAlteracao) {
		super(id, descricao, descricaoAbreviada, indicadorUso, ultimaAlteracao);
		this.materialUnidade = materialUnidade;
	}

	/**
	 * default constructor
	 */
	public TabelaAuxiliarUnidade() {
	}

	/**
	 * @return Retorna o campo unidade.
	 */
	public MaterialUnidade getMaterialUnidade() {
		return materialUnidade;
	}

	/**
	 * @param unidade
	 *            O unidade a ser setado.
	 */
	public void setMaterialUnidade(MaterialUnidade materialUnidade) {
		this.materialUnidade = materialUnidade;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @return Descrição do retorno
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append(
				"descricao", getDescricao()).append("descricaoAbreviada",
				getDescricaoAbreviada()).append("MaterialUnidade", getMaterialUnidade())
				.toString();
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof TabelaAuxiliarUnidade)) {
			return false;
		}
		TabelaAuxiliarUnidade castOther = (TabelaAuxiliarUnidade) other;

		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.append(this.getDescricao(), castOther.getDescricao()).append(
						this.getDescricaoAbreviada(),
						castOther.getDescricaoAbreviada()).append(
						this.getMaterialUnidade(), castOther.getMaterialUnidade()).isEquals();
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @return Descrição do retorno
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getDescricao())
				.append(getDescricaoAbreviada()).toHashCode();
	}

}

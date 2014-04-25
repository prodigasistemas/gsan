package gcom.operacional;

import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 * @created 2 de Junho de 2004
 */
public class SetorFonteCaptacao extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

	private SetorFonteCaptacaoPK comp_id;

	private Date ultimaAlteracao;

	/**
	 * full constructor
	 * 
	 * @param comp_id Description of the Parameter
	 * @param ultimaAlteracao Description of the Parameter
	 */
	public SetorFonteCaptacao(
			SetorFonteCaptacaoPK comp_id, 
			Date ultimaAlteracao) {
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * default constructor
	 */
	public SetorFonteCaptacao() { }

	/**
	 * minimal constructor
	 * 
	 * @param comp_id
	 *            Description of the Parameter
	 * @param quantidadeEconomias
	 *            Description of the Parameter
	 */
	public SetorFonteCaptacao(SetorFonteCaptacaoPK comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * Gets the comp_id attribute of the SetorFonteCaptacao object
	 * 
	 * @return The comp_id value
	 */
	public SetorFonteCaptacaoPK getComp_id() {
		return this.comp_id;
	}

	/**
	 * Sets the comp_id attribute of the SetorFonteCaptacao object
	 * 
	 * @param comp_id
	 *            The new comp_id value
	 */
	public void setComp_id(SetorFonteCaptacaoPK comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * Gets the ultimaAlteracao attribute of the ImovelSubcategoria object
	 * 
	 * @return The ultimaAlteracao value
	 */
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	/**
	 * Sets the ultimaAlteracao attribute of the ImovelSubcategoria object
	 * 
	 * @param ultimaAlteracao
	 *            The new ultimaAlteracao value
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id())
				.toString();
	}

	/**
	 * Description of the Method
	 * 
	 * @param other
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ImovelSubcategoria)) {
			return false;
		}
		SetorFonteCaptacao castOther = (SetorFonteCaptacao) other;

		return new EqualsBuilder().append(this.getComp_id(),
				castOther.getComp_id()).isEquals();
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public int hashCode() {
		return this.ultimaAlteracao.hashCode();
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
}

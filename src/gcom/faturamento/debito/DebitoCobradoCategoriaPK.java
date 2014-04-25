package gcom.faturamento.debito;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DebitoCobradoCategoriaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer categoriaId;

	/** identifier field */
	private Integer debitoCobradoId;

	/** full constructor */
	public DebitoCobradoCategoriaPK(Integer categoriaId, Integer debitoCobradoId) {
		this.categoriaId = categoriaId;
		this.debitoCobradoId = debitoCobradoId;
	}

	/** default constructor */
	public DebitoCobradoCategoriaPK() {
	}

	public String toString() {
		return new ToStringBuilder(this)
				.append("categoriaId", getCategoriaId()).append(
						"debitoCobradoId", getDebitoCobradoId()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof DebitoCobradoCategoriaPK))
			return false;
		DebitoCobradoCategoriaPK castOther = (DebitoCobradoCategoriaPK) other;
		return new EqualsBuilder().append(this.getCategoriaId(),
				castOther.getCategoriaId()).append(this.getDebitoCobradoId(),
				castOther.getDebitoCobradoId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getCategoriaId()).append(
				getDebitoCobradoId()).toHashCode();
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Integer getDebitoCobradoId() {
		return debitoCobradoId;
	}

	public void setDebitoCobradoId(Integer debitoCobradoId) {
		this.debitoCobradoId = debitoCobradoId;
	}

}

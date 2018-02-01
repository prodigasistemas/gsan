package gcom.util.tabelaauxiliar;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class TabelaAuxiliarAbstrata extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	protected Integer id;

	protected Short indicadorUso;

	protected Date ultimaAlteracao;

	public TabelaAuxiliarAbstrata(Integer id, Short indicadorUso, Date ultimaAlteracao) {
		this.id = id;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public TabelaAuxiliarAbstrata() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof TabelaAuxiliarAbstrata)) {
			return false;
		}
		TabelaAuxiliarAbstrata castOther = (TabelaAuxiliarAbstrata) other;

		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroTabelaAuxiliar();
		filtro.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliar.ID, this.getId()));
		return filtro;
	}

	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}
}

package gcom.arrecadacao.aviso;

import gcom.arrecadacao.DeducaoTipo;
import gcom.arrecadacao.FiltroAvisoAcerto;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AvisoDeducoes  extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public Filtro retornaFiltro() {
		FiltroAvisoAcerto filtroAvisoAcerto = new FiltroAvisoAcerto();
        filtroAvisoAcerto.adicionarParametro(new ParametroSimples("deducaoTipo.id", this.getDeducaoTipo().getId()));
        filtroAvisoAcerto.adicionarParametro(new ParametroSimples("avisoBancario.id", this.getAvisoBancario().getId()));
        filtroAvisoAcerto.adicionarCaminhoParaCarregamentoEntidade("comp_id");
        filtroAvisoAcerto.adicionarCaminhoParaCarregamentoEntidade("avisoBancario");
		return filtroAvisoAcerto ;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] ids = {"deducaoTipo","avisoBancario"};
		return ids;
	}

	/** identifier field */
	private gcom.arrecadacao.aviso.AvisoDeducoesPK comp_id;

	/** nullable persistent field */
	private BigDecimal valorDeducao;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private DeducaoTipo deducaoTipo;

	/** nullable persistent field */
	private gcom.arrecadacao.aviso.AvisoBancario avisoBancario;

	/** full constructor */
	public AvisoDeducoes(gcom.arrecadacao.aviso.AvisoDeducoesPK comp_id,
			BigDecimal valorDeducao, Date ultimaAlteracao,
			DeducaoTipo deducaoTipo,
			gcom.arrecadacao.aviso.AvisoBancario avisoBancario) {
		this.comp_id = comp_id;
		this.valorDeducao = valorDeducao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.deducaoTipo = deducaoTipo;
		this.avisoBancario = avisoBancario;
	}

	/** default constructor */
	public AvisoDeducoes() {
	}

	/** minimal constructor */
	public AvisoDeducoes(gcom.arrecadacao.aviso.AvisoDeducoesPK comp_id) {
		this.comp_id = comp_id;
	}

	public gcom.arrecadacao.aviso.AvisoDeducoesPK getComp_id() {
		return this.comp_id;
	}

	public void setComp_id(gcom.arrecadacao.aviso.AvisoDeducoesPK comp_id) {
		this.comp_id = comp_id;
	}

	public BigDecimal getValorDeducao() {
		return this.valorDeducao;
	}

	public void setValorDeducao(BigDecimal valorDeducao) {
		this.valorDeducao = valorDeducao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public DeducaoTipo getDeducaoTipo() {
		return this.deducaoTipo;
	}

	public void setDeducaoTipo(DeducaoTipo deducaoTipo) {
		this.deducaoTipo = deducaoTipo;
	}

	public gcom.arrecadacao.aviso.AvisoBancario getAvisoBancario() {
		return this.avisoBancario;
	}

	public void setAvisoBancario(
			gcom.arrecadacao.aviso.AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id())
				.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof AvisoDeducoes))
			return false;
		AvisoDeducoes castOther = (AvisoDeducoes) other;
		return new EqualsBuilder().append(this.getComp_id(),
				castOther.getComp_id()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getComp_id()).toHashCode();
	}

}

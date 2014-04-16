package gcom.financeiro;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LancamentoResumo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** persistent field */
	private int anoMesReferencia;

	/** nullable persistent field */
	private BigDecimal valorResumo;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private SetorComercial setorComercial;

	/** persistent field */
	private gcom.financeiro.LancamentoResumoValorTipo lancamentoResumoValorTipo;

	/** persistent field */
	private Localidade localidade;

	/** persistent field */
	private GerenciaRegional gerenciaRegional;

	/** persistent field */
	private Categoria categoria;

	/** full constructor */
	public LancamentoResumo(
			Integer id,
			int anoMesReferencia,
			BigDecimal valorResumo,
			Date ultimaAlteracao,
			SetorComercial setorComercial,
			gcom.financeiro.LancamentoResumoValorTipo lancamentoResumoValorTipo,
			Localidade localidade, GerenciaRegional gerenciaRegional,
			Categoria categoria) {
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.valorResumo = valorResumo;
		this.ultimaAlteracao = ultimaAlteracao;
		this.setorComercial = setorComercial;
		this.lancamentoResumoValorTipo = lancamentoResumoValorTipo;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.categoria = categoria;
	}

	/** default constructor */
	public LancamentoResumo() {
	}

	/** minimal constructor */
	public LancamentoResumo(
			Integer id,
			int anoMesReferencia,
			SetorComercial setorComercial,
			gcom.financeiro.LancamentoResumoValorTipo lancamentoResumoValorTipo,
			Localidade localidade, GerenciaRegional gerenciaRegional,
			Categoria categoria) {
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.setorComercial = setorComercial;
		this.lancamentoResumoValorTipo = lancamentoResumoValorTipo;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.categoria = categoria;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAnoMesReferencia() {
		return this.anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public BigDecimal getValorResumo() {
		return this.valorResumo;
	}

	public void setValorResumo(BigDecimal valorResumo) {
		this.valorResumo = valorResumo;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public SetorComercial getSetorComercial() {
		return this.setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public gcom.financeiro.LancamentoResumoValorTipo getLancamentoResumoValorTipo() {
		return this.lancamentoResumoValorTipo;
	}

	public void setLancamentoResumoValorTipo(
			gcom.financeiro.LancamentoResumoValorTipo lancamentoResumoValorTipo) {
		this.lancamentoResumoValorTipo = lancamentoResumoValorTipo;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public GerenciaRegional getGerenciaRegional() {
		return this.gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}

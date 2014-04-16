package gcom.atendimentopublico.ligacaoesgoto;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LigacaoEsgotoPerfil extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private BigDecimal percentualEsgotoConsumidaColetada;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private Short indicadorUso;
	
	private Short indicadorPrincipal;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	public final static Integer CONVENCIONAL = new Integer("1");

	/** full constructor */
	public LigacaoEsgotoPerfil(String descricao, Short indicadorUso,
			Date ultimaAlteracao) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public LigacaoEsgotoPerfil() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo percentualEsgotoConsumidaColetada.
	 */
	public BigDecimal getPercentualEsgotoConsumidaColetada() {
		return percentualEsgotoConsumidaColetada;
	}

	/**
	 * @param percentualEsgotoConsumidaColetada
	 *            O percentualEsgotoConsumidaColetada a ser setado.
	 */
	public void setPercentualEsgotoConsumidaColetada(
			BigDecimal percentualEsgotoConsumidaColetada) {
		this.percentualEsgotoConsumidaColetada = percentualEsgotoConsumidaColetada;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public Filtro retornaFiltro() {
		FiltroTabelaAuxiliar filtro = new FiltroTabelaAuxiliar();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTabelaAuxiliar.ID, this.getId()));
		return filtro;	
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

	public Short getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(Short indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}


}

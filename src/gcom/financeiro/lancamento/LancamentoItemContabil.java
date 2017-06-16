package gcom.financeiro.lancamento;

import gcom.financeiro.ContaContabil;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LancamentoItemContabil extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	public final static Integer LIGACOES_AGUA = new Integer(1);
	public final static Integer ACRESCIMOS_POR_IMPONTUALIDADE = new Integer(2);
	public final static Integer RELIGACOES_E_SANCOES = new Integer(3);
	public final static Integer AFERICAO_DE_HIDROMETROS = new Integer(4);
	public final static Integer EXTENSOES_REDE_AGUA = new Integer(5);
	public final static Integer OUTROS_SERVICOS_AGUA = new Integer(6);
	public final static Integer LIGACOES_ESGOTO = new Integer(7);
	public final static Integer EXTENSOES_REDE_ESGOTO = new Integer(8);
	public final static Integer OUTROS_SERVICOS_ESGOTO = new Integer(9);
	public final static Integer TARIFA_DE_AGUA = new Integer(10);
	public final static Integer TARIFA_DE_ESGOTO = new Integer(11);
	public final static Integer CANCELAMENTO_PARCELAMENTO = new Integer(12); // TODO - QUAL ID? Manter o 12 e 13 da base?
	public final static Integer JUROS_SOBRE_CONTRATO_PARCELAMENTO = new Integer(2); // TODO - MANTER?????!?!?!?!?!?

	private Integer id;
	private String descricao;
	private String descricaoAbreviada;
	private Short sequenciaImpressao;
	private Date ultimaAlteracao;
	private Short indicadorUso;
	private LancamentoItem lancamentoItem;

	public LancamentoItemContabil(String descricao, String descricaoAbreviada, Short sequenciaImpressao, Date ultimaAlteracao, Short indicadorUso,
			gcom.financeiro.lancamento.LancamentoItem lancamentoItem) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.sequenciaImpressao = sequenciaImpressao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorUso = indicadorUso;
		this.lancamentoItem = lancamentoItem;

	}

	public LancamentoItemContabil() {
	}

	public LancamentoItemContabil(Integer id) {
		this.id = id;
	}

	public LancamentoItemContabil(Integer id, Short sequenciaImpressao) {
		this.id = id;
		this.sequenciaImpressao = sequenciaImpressao;
	}

	public LancamentoItemContabil(String descricao, String descricaoAbreviada, gcom.financeiro.lancamento.LancamentoItem lancamentoItem, ContaContabil contaContabil) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.lancamentoItem = lancamentoItem;

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

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Short getSequenciaImpressao() {
		return this.sequenciaImpressao;
	}

	public void setSequenciaImpressao(Short sequenciaImpressao) {
		this.sequenciaImpressao = sequenciaImpressao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.financeiro.lancamento.LancamentoItem getLancamentoItem() {
		return this.lancamentoItem;
	}

	public void setLancamentoItem(gcom.financeiro.lancamento.LancamentoItem lancamentoItem) {
		this.lancamentoItem = lancamentoItem;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Filtro retornaFiltro() {
		FiltroLancamentoItemContabil filtro = new FiltroLancamentoItemContabil();
		filtro.adicionarParametro(new ParametroSimples(FiltroLancamentoItemContabil.ID, this.getId()));
		return filtro;
	}

	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
		if (lancamentoItem != null) {
			getLancamentoItem();
		}
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}

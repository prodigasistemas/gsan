package gcom.financeiro;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ContaAReceberContabil implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private int anoMesReferencia;
    private int numeroSequenciaTipoLancamento;
    private int numeroSequenciaItemTipoLancamento;
    private BigDecimal valorItemLancamento;
    private LancamentoItem lancamentoItem;
    private GerenciaRegional gerenciaRegional;
    private Localidade localidade;
    private LancamentoTipo lancamentoTipo;
    private UnidadeNegocio unidadeNegocio;
    private Categoria categoria;
    private LancamentoItemContabil lancamentoItemContabil;
    private Date ultimaAlteracao;

    public ContaAReceberContabil(Integer id, int anoMesReferencia, int numeroSequenciaTipoLancamento, int numeroSequenciaItemTipoLancamento, BigDecimal valorItemLancamento, LancamentoItem lancamentoItem, GerenciaRegional gerenciaRegional, Localidade localidade, LancamentoTipo lancamentoTipo, UnidadeNegocio unidadeNegocio, Categoria categoria, LancamentoItemContabil lancamentoItemContabil) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.numeroSequenciaTipoLancamento = numeroSequenciaTipoLancamento;
        this.numeroSequenciaItemTipoLancamento = numeroSequenciaItemTipoLancamento;
        this.valorItemLancamento = valorItemLancamento;
        this.lancamentoItem = lancamentoItem;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
        this.lancamentoTipo = lancamentoTipo;
        this.unidadeNegocio = unidadeNegocio;
        this.categoria = categoria;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public ContaAReceberContabil() {
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

    public int getNumeroSequenciaTipoLancamento() {
        return this.numeroSequenciaTipoLancamento;
    }

    public void setNumeroSequenciaTipoLancamento(int numeroSequenciaTipoLancamento) {
        this.numeroSequenciaTipoLancamento = numeroSequenciaTipoLancamento;
    }

    public int getNumeroSequenciaItemTipoLancamento() {
        return this.numeroSequenciaItemTipoLancamento;
    }

    public void setNumeroSequenciaItemTipoLancamento(int numeroSequenciaItemTipoLancamento) {
        this.numeroSequenciaItemTipoLancamento = numeroSequenciaItemTipoLancamento;
    }

    public BigDecimal getValorItemLancamento() {
        return this.valorItemLancamento;
    }

    public void setValorItemLancamento(BigDecimal valorItemLancamento) {
        this.valorItemLancamento = valorItemLancamento;
    }

    public LancamentoItem getLancamentoItem() {
        return this.lancamentoItem;
    }

    public void setLancamentoItem(LancamentoItem lancamentoItem) {
        this.lancamentoItem = lancamentoItem;
    }

    public GerenciaRegional getGerenciaRegional() {
        return this.gerenciaRegional;
    }

    public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public LancamentoTipo getLancamentoTipo() {
        return this.lancamentoTipo;
    }

    public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
        this.lancamentoTipo = lancamentoTipo;
    }

    public UnidadeNegocio getUnidadeNegocio() {
        return this.unidadeNegocio;
    }

    public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
        this.unidadeNegocio = unidadeNegocio;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LancamentoItemContabil getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}

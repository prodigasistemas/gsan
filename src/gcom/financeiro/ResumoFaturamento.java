package gcom.financeiro;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;
import gcom.util.SequenciaRelatorioContabilidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ResumoFaturamento implements Serializable {
	private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer anoMesReferencia;
    private BigDecimal valorItemFaturamento;
    private Short sequenciaTipoLancamento;
    private Short sequenciaItemTipoLancamento;
    private Date ultimaAlteracao;
    private LancamentoItemContabil lancamentoItemContabil;
    private LancamentoTipo lancamentoTipo;
    private LancamentoItem lancamentoItem;
    private Localidade localidade;
    private GerenciaRegional gerenciaRegional;
    private Categoria categoria;
    private UnidadeNegocio unidadeNegocio;
    

    public static final short DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_ITEM_TIPO_LANCAMENTO = 90;
    public static final short FATURAMENTO_AGUA_SEQUENCIA_TIPO_LANCAMENTO = 100;
    public static final short FATURAMENTO_ESGOTO_SEQUENCIA_TIPO_LANCAMENTO = 200;
    public static final short FINANCIAMENTOS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 300;
    public static final short FINANCIAMENTOS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 400;
    public static final short JUROS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1 = 410;
    public static final short JUROS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2 = 420;
    public static final short GUIAS_PAGAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 500;
    public static final short INCLUSOES_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1 = 510;
    public static final short INCLUSOES_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2 = 599;
    public static final short FINANCIAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 700;
    public static final short FINANCIAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 800;
    public static final short PARCELAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 810;
    public static final short PARCELAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 820;
    public static final short CANCELAMENTOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1 = 900;
    public static final short CANCELAMENTOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2 = 1020;

    public static final short CREDITOS_A_REALIZAR_POR_COBRANCA_INDEVIDA_SEQUENCIA_TIPO_LANCAMENTO = 1030;
    public static final short VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 1030;
    
    public static final short DESCONTOS_INCONDICIONAIS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO = 1040;
    public static final short VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 1040;
    
    public static final short GUIAS_DE_DEVOLUCOES_DE_VALORES_COBRADOS_SEQUENCIA_TIPO_LANCAMENTO = 1050;
    public static final short DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1 = 1500;
    public static final short DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2 = 1600;
    public static final short VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_3 = 1610;
    public static final short DOACOES_COBRADAS_EM_CONTA_SEQUENCIA_TIPO_LANCAMENTO = 1800;
    public static final short VALORES_DEVOLVIDOS_SEQUENCIA_TIPO_LANCAMENTO = 2400;
    public static final short IMPOSTOS_DEDUZIDOS_SEQUENCIA_TIPO_LANCAMENTO = 2150;
    public static final short IMPOSTOS_CANCELADOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 2800;
    public static final short IMPOSTOS_INCLUIDOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 2900;
    public static final short OUTROS_CREDITOS_CANCELADOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 3000;
    public static final short OUTROS_CREDITOS_CONCEDIDOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO = 3100;
    public static final short CANCELAMENTOS_POR_PRESCRICAO_SEQUENCIA_TIPO_LANCAMENTO = 4200;
    
    
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Short sequenciaTipoLancamento, Short sequenciaItemTipoLancamento, Date ultimaAlteracao, LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;
        this.sequenciaTipoLancamento = sequenciaTipoLancamento;
        this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.lancamentoTipo = lancamentoTipo;
        this.lancamentoItem = lancamentoItem;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }
    
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Short sequenciaTipoLancamento, Short sequenciaItemTipoLancamento, Date ultimaAlteracao, LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;
        this.sequenciaTipoLancamento = sequenciaTipoLancamento;
        this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.lancamentoTipo = lancamentoTipo;
        this.lancamentoItem = lancamentoItem;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    public ResumoFaturamento() {
    }

    public ResumoFaturamento(LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria) {
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.lancamentoTipo = lancamentoTipo;
        this.lancamentoItem = lancamentoItem;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }
    
    public ResumoFaturamento(LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.lancamentoTipo = lancamentoTipo;
        this.lancamentoItem = lancamentoItem;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    /** constructor para [UC0155] encerrar faturamento do mês */
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;        
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }
    
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;        
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    /** constructor para [UC0155] encerrar faturamento do mês */
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, Short sequenciaItemTipoLancamento, LancamentoItemContabil lancamentoItemContabil, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;        
        this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;        
        this.lancamentoItemContabil = lancamentoItemContabil;              
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }
    
    /** constructor para [UC0155] encerrar faturamento do mês */
    public ResumoFaturamento(Integer anoMesReferencia, BigDecimal valorItemFaturamento, 
    		Short sequenciaItemTipoLancamento, LancamentoItemContabil lancamentoItemContabil, 
    		Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.anoMesReferencia = anoMesReferencia;
        this.valorItemFaturamento = valorItemFaturamento;        
        this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;        
        this.lancamentoItemContabil = lancamentoItemContabil;              
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public BigDecimal getValorItemFaturamento() {
        return this.valorItemFaturamento;
    }

    public void setValorItemFaturamento(BigDecimal valorItemFaturamento) {
        this.valorItemFaturamento = valorItemFaturamento;
    }

    public Short getSequenciaTipoLancamento() {
        return this.sequenciaTipoLancamento;
    }

    public void setSequenciaTipoLancamento(Short sequenciaTipoLancamento) {
    	if (sequenciaTipoLancamento == null) {
    		this.sequenciaTipoLancamento = SequenciaRelatorioContabilidade.getSequencia(getLancamentoTipo().getId());
    	} else {
    		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
    	}
    }

    public Short getSequenciaItemTipoLancamento() {
        return this.sequenciaItemTipoLancamento;
    }

    public void setSequenciaItemTipoLancamento(Short sequenciaItemTipoLancamento) {
    	if (sequenciaItemTipoLancamento == null) {
    		this.sequenciaItemTipoLancamento = SequenciaRelatorioContabilidade.getSequencia(getLancamentoItem().getId());
    	} else {
    		this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
    	}
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public LancamentoItemContabil getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public LancamentoTipo getLancamentoTipo() {
        return this.lancamentoTipo;
    }

    public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
        this.lancamentoTipo = lancamentoTipo;
    }

    public LancamentoItem getLancamentoItem() {
        return this.lancamentoItem;
    }

    public void setLancamentoItem(LancamentoItem lancamentoItem) {
        this.lancamentoItem = lancamentoItem;
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
    
    public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}

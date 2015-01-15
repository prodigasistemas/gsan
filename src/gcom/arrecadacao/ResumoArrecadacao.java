package gcom.arrecadacao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;
import gcom.util.SequenciaRelatorioContabilidade;

import java.math.BigDecimal;
import java.util.Date;

public class ResumoArrecadacao {

	private Integer id;
    private Integer anoMesReferencia;
    private Short sequenciaTipoLancamento;
    private Short sequenciaItemTipoLancamento;
    private Date ultimaAlteracao;
    private BigDecimal valorItemArrecadacao;

    private LancamentoItemContabil lancamentoItemContabil;
    private LancamentoTipo lancamentoTipo;
    private LancamentoItem lancamentoItem;
    private Localidade localidade;
    private GerenciaRegional gerenciaRegional;
    private Categoria categoria;
    private RecebimentoTipo recebimentoTipo;
    private UnidadeNegocio unidadeNegocio;
    
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_1 = 1200;
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_2 = 1300;
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_3 = 1400;
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_4 = 1500;
    public static final short RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 1550;
    public static final short RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 2000;
    public static final short RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2 = 4950;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1 = 4600;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2 = 4700;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_3 = 4800;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_4 = 4900;
    public static final short RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1 = 5400;
    
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LancamentoItem getLancamentoItem() {
		return lancamentoItem;
	}

	public void setLancamentoItem(LancamentoItem lancamentoItem) {
		this.lancamentoItem = lancamentoItem;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public LancamentoTipo getLancamentoTipo() {
		return lancamentoTipo;
	}

	public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public RecebimentoTipo getRecebimentoTipo() {
		return recebimentoTipo;
	}

	public void setRecebimentoTipo(RecebimentoTipo recebimentoTipo) {
		this.recebimentoTipo = recebimentoTipo;
	}

	public Short getSequenciaItemTipoLancamento() {
		return sequenciaItemTipoLancamento;
	}

	public void setSequenciaItemTipoLancamento(Short sequenciaItemTipoLancamento) {
		if (sequenciaItemTipoLancamento == null) {
    		this.sequenciaItemTipoLancamento = SequenciaRelatorioContabilidade.getSequencia(getLancamentoItem().getId());
    	} else {
    		this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
    	}
	}

	public Short getSequenciaTipoLancamento() {
		return sequenciaTipoLancamento;
	}

	public void setSequenciaTipoLancamento(Short sequenciaTipoLancamento) {
		if (sequenciaTipoLancamento == null) {
    		this.sequenciaTipoLancamento = SequenciaRelatorioContabilidade.getSequencia(getLancamentoTipo().getId());
    	} else {
    		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
    	}
	}
	
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorItemArrecadacao() {
		return valorItemArrecadacao;
	}

	public void setValorItemArrecadacao(BigDecimal valorItemArrecadacao) {
		this.valorItemArrecadacao = valorItemArrecadacao;
	}

	public ResumoArrecadacao() {
		
	}
	
	public ResumoArrecadacao(Integer id, Integer anoMesReferencia, Short sequenciaTipoLancamento, Short sequenciaItemTipoLancamento, BigDecimal valorItemArrecadacao, Date ultimaAlteracao, LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, RecebimentoTipo recebimentoTipo) {
		super();
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
		this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
		this.ultimaAlteracao = ultimaAlteracao;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.lancamentoTipo = lancamentoTipo;
		this.lancamentoItem = lancamentoItem;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.categoria = categoria;
		this.recebimentoTipo = recebimentoTipo;
		this.valorItemArrecadacao = valorItemArrecadacao;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	
}

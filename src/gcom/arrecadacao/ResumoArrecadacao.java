package gcom.arrecadacao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;

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
    
    public static final short RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_1 = 2000;
    
    public static final short RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_2 = 1550;
    
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_1 = 1200;
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_2 = 1300;
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_3 = 1400;
    public static final short IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_4 = 1500;
    
    public static final short RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1 = 5400;
    
    public static final short RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2 = 4950;
    
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1 = 4600;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2 = 4700;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_3 = 4800;
    public static final short IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_4 = 4900;

	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo lancamentoItem.
	 */
	public LancamentoItem getLancamentoItem() {
		return lancamentoItem;
	}

	/**
	 * @param lancamentoItem O lancamentoItem a ser setado.
	 */
	public void setLancamentoItem(LancamentoItem lancamentoItem) {
		this.lancamentoItem = lancamentoItem;
	}

	/**
	 * @return Retorna o campo lancamentoItemContabil.
	 */
	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	/**
	 * @param lancamentoItemContabil O lancamentoItemContabil a ser setado.
	 */
	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	/**
	 * @return Retorna o campo lancamentoTipo.
	 */
	public LancamentoTipo getLancamentoTipo() {
		return lancamentoTipo;
	}

	/**
	 * @param lancamentoTipo O lancamentoTipo a ser setado.
	 */
	public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo recebimentoTipo.
	 */
	public RecebimentoTipo getRecebimentoTipo() {
		return recebimentoTipo;
	}

	/**
	 * @param recebimentoTipo O recebimentoTipo a ser setado.
	 */
	public void setRecebimentoTipo(RecebimentoTipo recebimentoTipo) {
		this.recebimentoTipo = recebimentoTipo;
	}

	/**
	 * @return Retorna o campo sequenciaItemTipoLancamento.
	 */
	public Short getSequenciaItemTipoLancamento() {
		return sequenciaItemTipoLancamento;
	}

	/**
	 * @param sequenciaItemTipoLancamento O sequenciaItemTipoLancamento a ser setado.
	 */
	public void setSequenciaItemTipoLancamento(Short sequenciaItemTipoLancamento) {
		this.sequenciaItemTipoLancamento = sequenciaItemTipoLancamento;
	}

	/**
	 * @return Retorna o campo sequenciaTipoLancamento.
	 */
	public Short getSequenciaTipoLancamento() {
		return sequenciaTipoLancamento;
	}

	/**
	 * @param sequenciaTipoLancamento O sequenciaTipoLancamento a ser setado.
	 */
	public void setSequenciaTipoLancamento(Short sequenciaTipoLancamento) {
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo valorItemArrecadacao.
	 */
	public BigDecimal getValorItemArrecadacao() {
		return valorItemArrecadacao;
	}

	/**
	 * @param valorItemArrecadacao O valorItemArrecadacao a ser setado.
	 */
	public void setValorItemArrecadacao(BigDecimal valorItemArrecadacao) {
		this.valorItemArrecadacao = valorItemArrecadacao;
	}

	public ResumoArrecadacao() {
		
	}
	
	public ResumoArrecadacao(Integer id, Integer anoMesReferencia, Short sequenciaTipoLancamento, Short sequenciaItemTipoLancamento, BigDecimal valorItemArrecadacao, Date ultimaAlteracao, LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria, RecebimentoTipo recebimentoTipo) {
		super();
		// TODO Auto-generated constructor stub
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

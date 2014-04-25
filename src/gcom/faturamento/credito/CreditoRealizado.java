package gcom.faturamento.credito;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.faturamento.conta.Conta;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao("")
public class CreditoRealizado extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date creditoRealizado;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private Short numeroLote;

    /** nullable persistent field */
    private Short numeroSubLote;

    /** nullable persistent field */
    private Integer anoMesReferenciaCredito;

    /** nullable persistent field */
    private Integer anoMesCobrancaCredito;

    /** nullable persistent field */
    @ControleAlteracao("")
    private BigDecimal valorCredito;

    /** nullable persistent field */
    private Short numeroPrestacao;

    /** nullable persistent field */
    private Short numeroPrestacaoCredito;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private Short numeroParcelaBonus;
    
    /** persistent field */
    private Conta conta;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** nullable persistent field */    
    private gcom.faturamento.credito.CreditoTipo creditoTipo;

    /** persistent field */
    private LancamentoItemContabil lancamentoItemContabil;
    
    /** persistent field */
    private CreditoOrigem creditoOrigem;
    
    /** persistent field */
    private CreditoARealizarGeral creditoARealizarGeral;

    /** persistent field */
    private Set creditoRealizadoCategorias;

    /** full constructor */
    public CreditoRealizado(Date creditoRealizado, Integer codigoSetorComercial, Integer numeroQuadra, Short numeroLote, Short numeroSubLote, Integer anoMesReferenciaCredito, Integer anoMesCobrancaCredito, BigDecimal valorCredito, Short numeroPrestacao, Short numeroPrestacaoCredito, Date ultimaAlteracao, Conta conta, Quadra quadra, Localidade localidade, gcom.faturamento.credito.CreditoTipo creditoTipo, LancamentoItemContabil lancamentoItemContabil, Set creditoRealizadoCategorias, CreditoOrigem creditoOrigem) {
        this.creditoRealizado = creditoRealizado;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.numeroLote = numeroLote;
        this.numeroSubLote = numeroSubLote;
        this.anoMesReferenciaCredito = anoMesReferenciaCredito;
        this.anoMesCobrancaCredito = anoMesCobrancaCredito;
        this.valorCredito = valorCredito;
        this.numeroPrestacao = numeroPrestacao;
        this.numeroPrestacaoCredito = numeroPrestacaoCredito;
        this.ultimaAlteracao = ultimaAlteracao;
        this.conta = conta;
        this.quadra = quadra;
        this.localidade = localidade;
        this.creditoTipo = creditoTipo;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.creditoRealizadoCategorias = creditoRealizadoCategorias;
        this.creditoOrigem = creditoOrigem;
    }

    /** default constructor */
    public CreditoRealizado() {
    }

    /** minimal constructor */
    public CreditoRealizado(Date creditoRealizado, Conta conta, Quadra quadra, Localidade localidade, LancamentoItemContabil lancamentoItemContabil, Set creditoRealizadoCategorias) {
        this.creditoRealizado = creditoRealizado;
        this.conta = conta;
        this.quadra = quadra;
        this.localidade = localidade;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.creditoRealizadoCategorias = creditoRealizadoCategorias;
    }
    
    public CreditoRealizado(Integer anoMesReferenciaCredito, Short numeroPrestacaoCredito, Short numeroPrestacao,
			BigDecimal valorCredito, CreditoTipo creditoTipo) {
		this.anoMesReferenciaCredito = anoMesReferenciaCredito;
		this.numeroPrestacaoCredito = numeroPrestacaoCredito;
		this.numeroPrestacao = numeroPrestacao;
		this.valorCredito = valorCredito;
		this.creditoTipo = creditoTipo;
	}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreditoRealizado() {
        return this.creditoRealizado;
    }

    public void setCreditoRealizado(Date creditoRealizado) {
        this.creditoRealizado = creditoRealizado;
    }

    public Integer getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Integer codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public Integer getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(Integer numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public Short getNumeroLote() {
        return this.numeroLote;
    }

    public void setNumeroLote(Short numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Short getNumeroSubLote() {
        return this.numeroSubLote;
    }

    public void setNumeroSubLote(Short numeroSubLote) {
        this.numeroSubLote = numeroSubLote;
    }

    public Integer getAnoMesReferenciaCredito() {
        return this.anoMesReferenciaCredito;
    }

    public void setAnoMesReferenciaCredito(Integer anoMesReferenciaCredito) {
        this.anoMesReferenciaCredito = anoMesReferenciaCredito;
    }

    public Integer getAnoMesCobrancaCredito() {
        return this.anoMesCobrancaCredito;
    }

    public void setAnoMesCobrancaCredito(Integer anoMesCobrancaCredito) {
        this.anoMesCobrancaCredito = anoMesCobrancaCredito;
    }

    public BigDecimal getValorCredito() {
        return this.valorCredito;
    }

    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }

    public Short getNumeroPrestacao() {
        return this.numeroPrestacao;
    }

    public void setNumeroPrestacao(Short numeroPrestacao) {
        this.numeroPrestacao = numeroPrestacao;
    }

    public Short getNumeroPrestacaoCredito() {
        return this.numeroPrestacaoCredito;
    }

    public void setNumeroPrestacaoCredito(Short numeroPrestacaoCredito) {
        this.numeroPrestacaoCredito = numeroPrestacaoCredito;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Quadra getQuadra() {
        return this.quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public gcom.faturamento.credito.CreditoTipo getCreditoTipo() {
        return this.creditoTipo;
    }

    public void setCreditoTipo(gcom.faturamento.credito.CreditoTipo creditoTipo) {
        this.creditoTipo = creditoTipo;
    }

    public LancamentoItemContabil getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public Set getCreditoRealizadoCategorias() {
        return this.creditoRealizadoCategorias;
    }

    public void setCreditoRealizadoCategorias(Set creditoRealizadoCategorias) {
        this.creditoRealizadoCategorias = creditoRealizadoCategorias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo creditoOrigem.
	 */
	public CreditoOrigem getCreditoOrigem() {
		return creditoOrigem;
	}

	/**
	 * @param creditoOrigem O creditoOrigem a ser setado.
	 */
	public void setCreditoOrigem(CreditoOrigem creditoOrigem) {
		this.creditoOrigem = creditoOrigem;
	}
	
	@Override
	public Filtro retornaFiltro() {
		FiltroCreditoRealizado filtro = new FiltroCreditoRealizado();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoRealizado.CREDITO_TIPO);
		//filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoRealizado.LANCAMENTO_ITEM_CONTABIL);
	
		filtro.adicionarParametro(
				new ParametroSimples(FiltroCreditoRealizado.CODIGO, this.getId()));
		return filtro; 
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}	
	
	public String getDescricao(){
		String desc = "";
		if (this.getCreditoTipo() != null){
			desc = getCreditoTipo().getDescricao();
		} 
		return desc;
	}
	
	@Override
	public void initializeLazy() {
		this.getDescricao();
	}
	
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CreditoRealizado) ) return false;
        CreditoRealizado castOther = (CreditoRealizado) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }
    
    @Override
    public String getDescricaoParaRegistroTransacao() {
    	return this.getDescricao();
    }

    public Short getNumeroParcelaBonus() {
        return numeroParcelaBonus;
    }

    public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
        this.numeroParcelaBonus = numeroParcelaBonus;
    }
    

	public CreditoARealizarGeral getCreditoARealizarGeral() {
		return creditoARealizarGeral;
	}

	public void setCreditoARealizarGeral(CreditoARealizarGeral creditoARealizarGeral) {
		this.creditoARealizarGeral = creditoARealizarGeral;
	}

	/**
     * @author Vivianne Sousa
     * @created 05/03/2008
    */
    public short getNumeroTotalParcelasMenosBonus() {
        short retorno = getNumeroPrestacao();
        
        if (getNumeroParcelaBonus() != null){
            retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
        }
             
        return retorno;
    }
    
}

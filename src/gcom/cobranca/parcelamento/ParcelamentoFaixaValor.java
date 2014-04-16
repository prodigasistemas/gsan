package gcom.cobranca.parcelamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoFaixaValor extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
	
    private BigDecimal valorFaixa;
    
    private BigDecimal percentualFaixa;
    
    private Date ultimaAlteracao;

    private ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroParcelamentoFaixaValor filtroParcelamentoFaixaValor = new FiltroParcelamentoFaixaValor();
		
		filtroParcelamentoFaixaValor. adicionarCaminhoParaCarregamentoEntidade("parcelamentoQuantidadePrestacao");
		filtroParcelamentoFaixaValor. adicionarParametro(
				new ParametroSimples(FiltroParcelamentoFaixaValor.ID, this.getId()));
		return filtroParcelamentoFaixaValor; 
	}
    
    /** full constructor */
    public ParcelamentoFaixaValor(BigDecimal valorFaixa,
					    		  BigDecimal percentualFaixa,
					    		  Date ultimaAlteracao,
					    		  ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao) {
        this.valorFaixa = valorFaixa;
        this.percentualFaixa = percentualFaixa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.parcelamentoQuantidadePrestacao = parcelamentoQuantidadePrestacao;
    }

    /** default constructor */
    public ParcelamentoFaixaValor() {
    }

    /** minimal constructor */
    public ParcelamentoFaixaValor(ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao) {
        this.parcelamentoQuantidadePrestacao = parcelamentoQuantidadePrestacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ParcelamentoQuantidadePrestacao getParcelamentoQuantidadePrestacao() {
		return parcelamentoQuantidadePrestacao;
	}

	public void setParcelamentoQuantidadePrestacao(
			ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao) {
		this.parcelamentoQuantidadePrestacao = parcelamentoQuantidadePrestacao;
	}

	public BigDecimal getPercentualFaixa() {
		return percentualFaixa;
	}

	public void setPercentualFaixa(BigDecimal percentualFaixa) {
		this.percentualFaixa = percentualFaixa;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorFaixa() {
		return valorFaixa;
	}

	public void setValorFaixa(BigDecimal valorFaixa) {
		this.valorFaixa = valorFaixa;
	}

}

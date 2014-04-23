package gcom.cobranca.parcelamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoQuantidadePrestacao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short quantidadeMaximaPrestacao;

    /** nullable persistent field */
    private BigDecimal taxaJuros;

    /** nullable persistent field */
	private BigDecimal percentualMinimoEntrada;

	/** nullable persistent field */
	private BigDecimal percentualTarifaMinimaImovel;
	
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento parcelamentoQuantidadeReparcelamento;
    
    private BigDecimal percentualValorReparcelado;
    
    private Short quantidadeMaxPrestacaoEspecial;
    
    private BigDecimal percentualEntradaSugerida;
    
    private Integer fatorQuantidadePrestacoes;
    
    private short indicadorMediaValorContas;
    
    private Short indicadorValorUltimaContaEmAtraso;
    

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroParcelamentoQuantidadePrestacao filtroParcelamentoQuantidadePrestacao = new FiltroParcelamentoQuantidadePrestacao();
		
		filtroParcelamentoQuantidadePrestacao. adicionarCaminhoParaCarregamentoEntidade("parcelamentoQuantidadeReparcelamento");
		filtroParcelamentoQuantidadePrestacao. adicionarParametro(
				new ParametroSimples(FiltroParcelamentoQuantidadePrestacao.ID, this.getId()));
		return filtroParcelamentoQuantidadePrestacao; 
	}
    
    
    
    /** full constructor */
    public ParcelamentoQuantidadePrestacao(Short quantidadeMaximaPrestacao, BigDecimal taxaJuros, 
    		BigDecimal percentualMinimoEntrada, BigDecimal percentualTarifaMinimaImovel,
    		Date ultimaAlteracao, 
    		gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento parcelamentoQuantidadeReparcelamento,
    		BigDecimal percentualValorReparcelado, BigDecimal percentualEntradaSugerida) {
        this.quantidadeMaximaPrestacao = quantidadeMaximaPrestacao;
        this.taxaJuros = taxaJuros;
        this.percentualMinimoEntrada = percentualMinimoEntrada;
        this.percentualTarifaMinimaImovel = percentualTarifaMinimaImovel;
        this.ultimaAlteracao = ultimaAlteracao;
        this.parcelamentoQuantidadeReparcelamento = parcelamentoQuantidadeReparcelamento;
        this.percentualValorReparcelado = percentualValorReparcelado;
        this.percentualEntradaSugerida = percentualEntradaSugerida;
    }

    /** default constructor */
    public ParcelamentoQuantidadePrestacao() {
    }

    /** minimal constructor */
    public ParcelamentoQuantidadePrestacao(gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento parcelamentoQuantidadeReparcelamento) {
        this.parcelamentoQuantidadeReparcelamento = parcelamentoQuantidadeReparcelamento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getQuantidadeMaximaPrestacao() {
        return this.quantidadeMaximaPrestacao;
    }

    public void setQuantidadeMaximaPrestacao(Short quantidadeMaximaPrestacao) {
        this.quantidadeMaximaPrestacao = quantidadeMaximaPrestacao;
    }

    public BigDecimal getTaxaJuros() {
        return this.taxaJuros;
    }

    public void setTaxaJuros(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento getParcelamentoQuantidadeReparcelamento() {
        return this.parcelamentoQuantidadeReparcelamento;
    }

    public void setParcelamentoQuantidadeReparcelamento(gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento parcelamentoQuantidadeReparcelamento) {
        this.parcelamentoQuantidadeReparcelamento = parcelamentoQuantidadeReparcelamento;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }


	public BigDecimal getPercentualMinimoEntrada() {
		return percentualMinimoEntrada;
	}

	public void setPercentualMinimoEntrada(BigDecimal percentualMinimoEntrada) {
		this.percentualMinimoEntrada = percentualMinimoEntrada;
	}

	public BigDecimal getPercentualTarifaMinimaImovel() {
		return percentualTarifaMinimaImovel;
	}

	public void setPercentualTarifaMinimaImovel(
			BigDecimal percentualTarifaMinimaImovel) {
		this.percentualTarifaMinimaImovel = percentualTarifaMinimaImovel;
	}

	public BigDecimal getPercentualValorReparcelado() {
		return percentualValorReparcelado;
	}

	public void setPercentualValorReparcelado(BigDecimal percentualValorReparcelado) {
		this.percentualValorReparcelado = percentualValorReparcelado;
	}

	public Short getQuantidadeMaxPrestacaoEspecial() {
		return quantidadeMaxPrestacaoEspecial;
	}

	public void setQuantidadeMaxPrestacaoEspecial(
			Short quantidadeMaxPrestacaoEspecial) {
		this.quantidadeMaxPrestacaoEspecial = quantidadeMaxPrestacaoEspecial;
	}

	public BigDecimal getPercentualEntradaSugerida() {
		return percentualEntradaSugerida;
	}

	public void setPercentualEntradaSugerida(BigDecimal percentualEntradaSugerida) {
		this.percentualEntradaSugerida = percentualEntradaSugerida;
	}

	public short getIndicadorMediaValorContas() {
		return indicadorMediaValorContas;
	}

	public void setIndicadorMediaValorContas(short indicadorMediaValorContas) {
		this.indicadorMediaValorContas = indicadorMediaValorContas;
	}

	public Integer getFatorQuantidadePrestacoes() {
		return fatorQuantidadePrestacoes;
	}

	public void setFatorQuantidadePrestacoes(Integer fatorQuantidadePrestacoes) {
		this.fatorQuantidadePrestacoes = fatorQuantidadePrestacoes;
	}

	public Short getIndicadorValorUltimaContaEmAtraso() {
		return indicadorValorUltimaContaEmAtraso;
	}

	public void setIndicadorValorUltimaContaEmAtraso(
			Short indicadorValorUltimaContaEmAtraso) {
		this.indicadorValorUltimaContaEmAtraso = indicadorValorUltimaContaEmAtraso;
	}
	
	
}

package gcom.cobranca.parcelamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoQuantidadeReparcelamentoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Short quantidadeMaximaReparcelamento;
    private BigDecimal percentualEntradaSugerida;
    private Date ultimaAlteracao;
    private gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil;
    private String informacaoParcelamentoQtdeReparcelamento;
    private Collection collectionParcelamentoQuantidadePrestacaoHelper;

    
    
    public Collection getCollectionParcelamentoQuantidadePrestacaoHelper() {
		return collectionParcelamentoQuantidadePrestacaoHelper;
	}

	public void setCollectionParcelamentoQuantidadePrestacaoHelper(
			Collection collectionParcelamentoQuantidadePrestacaoHelper) {
		this.collectionParcelamentoQuantidadePrestacaoHelper = collectionParcelamentoQuantidadePrestacaoHelper;
	}

	/** full constructor */
    public ParcelamentoQuantidadeReparcelamentoHelper(Short quantidadeMaximaReparcelamento, BigDecimal valorMinimoPrestacao, Date ultimaAlteracao, gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil) {
        this.quantidadeMaximaReparcelamento = quantidadeMaximaReparcelamento;
        //this.valorMinimoPrestacao = valorMinimoPrestacao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.parcelamentoPerfil = parcelamentoPerfil;
    }

    /** default constructor */
    public ParcelamentoQuantidadeReparcelamentoHelper() {
    }

    /** minimal constructor */
    public ParcelamentoQuantidadeReparcelamentoHelper(gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil) {
        this.parcelamentoPerfil = parcelamentoPerfil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getQuantidadeMaximaReparcelamento() {
        return this.quantidadeMaximaReparcelamento;
    }

    public void setQuantidadeMaximaReparcelamento(Short quantidadeMaximaReparcelamento) {
        this.quantidadeMaximaReparcelamento = quantidadeMaximaReparcelamento;
    }



    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.parcelamento.ParcelamentoPerfil getParcelamentoPerfil() {
        return this.parcelamentoPerfil;
    }

    public void setParcelamentoPerfil(gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil) {
        this.parcelamentoPerfil = parcelamentoPerfil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String getInformacaoParcelamentoQtdeReparcelamento() {
		return informacaoParcelamentoQtdeReparcelamento;
	}

	public void setInformacaoParcelamentoQtdeReparcelamento(
			String informacaoParcelamentoQtdeReparcelamento) {
		this.informacaoParcelamentoQtdeReparcelamento = informacaoParcelamentoQtdeReparcelamento;
	}

	public BigDecimal getPercentualEntradaSugerida() {
		return percentualEntradaSugerida;
	}

	public void setPercentualEntradaSugerida(BigDecimal percentualEntradaSugerida) {
		this.percentualEntradaSugerida = percentualEntradaSugerida;
	}

}

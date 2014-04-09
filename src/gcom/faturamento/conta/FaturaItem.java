package gcom.faturamento.conta;

import gcom.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturaItem implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Integer itemSequencia;

    /** nullable persistent field */
    //private Integer indicadorUso;

    /** nullable persistent field */
    private BigDecimal valorConta;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    //private Integer flag;
    
    private Integer numeroConsumo;

    /** persistent field */
    private gcom.faturamento.conta.Fatura fatura;

    /** persistent field */
    private gcom.faturamento.conta.ContaGeral contaGeral;
    
    private Imovel imovel;
    
    /** nullable persistent field */
    private BigDecimal valorImposto;
    
    private Integer indicadorUsoAtivo;
    
    public final static Integer QTD_ITENS_RELATORIO_FATURA = new Integer(20);
    

    /** full constructor */
    public FaturaItem(Integer itemSequencia, BigDecimal valorConta, Date ultimaAlteracao, Integer numeroConsumo,  gcom.faturamento.conta.Fatura fatura, gcom.faturamento.conta.ContaGeral contaGeral, Imovel imovel) {
        this.itemSequencia = itemSequencia;
        //this.indicadorUso = indicadorUso;
        this.valorConta = valorConta;
        this.ultimaAlteracao = ultimaAlteracao;
    //    this.flag = flag;
        this.numeroConsumo = numeroConsumo;
        this.fatura = fatura;
        this.contaGeral = contaGeral;
        this.imovel = imovel;
        this.indicadorUsoAtivo = 1;
    }

    /** default constructor */
    public FaturaItem() {
    	this.indicadorUsoAtivo = 1;
    }

    /** minimal constructor */
    public FaturaItem(Integer itemSequencia, gcom.faturamento.conta.Fatura fatura, gcom.faturamento.conta.ContaGeral contaGeral) {
        this.itemSequencia = itemSequencia;
        this.fatura = fatura;
        this.contaGeral = contaGeral;
        this.indicadorUsoAtivo = 1;
    }

    /**
	 * @return Retorna o campo imovel.
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo numeroConsumo.
	 */
	public Integer getNumeroConsumo() {
		return numeroConsumo;
	}

	/**
	 * @param numeroConsumo O numeroConsumo a ser setado.
	 */
	public void setNumeroConsumo(Integer numeroConsumo) {
		this.numeroConsumo = numeroConsumo;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemSequencia() {
        return this.itemSequencia;
    }

    public void setItemSequencia(Integer itemSequencia) {
        this.itemSequencia = itemSequencia;
    }
//
//    public Integer getIndicadorUso() {
//        return this.indicadorUso;
//    }
//
//    public void setIndicadorUso(Integer indicadorUso) {
//        this.indicadorUso = indicadorUso;
//    }
//
    public BigDecimal getValorConta() {
        return this.valorConta;
    }

    public void setValorConta(BigDecimal valorConta) {
        this.valorConta = valorConta;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
/*
    public Integer getFlag() {
        return this.flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
*/
    public gcom.faturamento.conta.Fatura getFatura() {
        return this.fatura;
    }

    public void setFatura(gcom.faturamento.conta.Fatura fatura) {
        this.fatura = fatura;
    }

    public gcom.faturamento.conta.ContaGeral getContaGeral() {
        return this.contaGeral;
    }

    public void setContaGeral(gcom.faturamento.conta.ContaGeral contaGeral) {
        this.contaGeral = contaGeral;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public BigDecimal getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}

	public Integer getIndicadorUsoAtivo() {
		return indicadorUsoAtivo;
	}

	public void setIndicadorUsoAtivo(Integer indicadorUsoAtivo) {
		this.indicadorUsoAtivo = indicadorUsoAtivo;
	}

}

package gcom.cobranca;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CicloMeta implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    private CobrancaAcao cobrancaAcao;
    
    /** persistent field */
    private int metaTotal;

    /** nullable persistent field */
    private BigDecimal valorLimite;

    /** persistent field */
    private Date ultimaAlteracao;


    public CicloMeta(Integer id, int anoMesReferencia, CobrancaAcao cobrancaAcao, int metaTotal, BigDecimal valorLimite, Date ultimaAlteracao) {
		super();
		
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.cobrancaAcao = cobrancaAcao;
		this.metaTotal = metaTotal;
		this.valorLimite = valorLimite;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
    public CicloMeta() {
    }

    /** minimal constructor */
    public CicloMeta(Integer id, int anoMesReferencia, int metaTotal, Date ultimaAlteracao) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.metaTotal = metaTotal;
        this.ultimaAlteracao = ultimaAlteracao;
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

    public int getMetaTotal() {
        return this.metaTotal;
    }

    public void setMetaTotal(int metaTotal) {
        this.metaTotal = metaTotal;
    }

    public BigDecimal getValorLimite() {
        return this.valorLimite;
    }

    public void setValorLimite(BigDecimal valorLimite) {
        this.valorLimite = valorLimite;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo cobrancaAcao.
	 */
	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	/**
	 * @param cobrancaAcao O cobrancaAcao a ser setado.
	 */
	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

}

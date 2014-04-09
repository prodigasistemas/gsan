package gcom.faturamento.consumotarifa;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsumoTarifaVigencia implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataVigencia;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    private Set consumoTarifaCategorias;
    /** persistent field */
    private gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa;

    /** full constructor */
    public ConsumoTarifaVigencia(Date dataVigencia, Date ultimaAlteracao, gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa) {
        this.dataVigencia = dataVigencia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.consumoTarifa = consumoTarifa;
    }

    /** default constructor */
    public ConsumoTarifaVigencia() {
    }

    /** minimal constructor */
    public ConsumoTarifaVigencia(gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa) {
        this.consumoTarifa = consumoTarifa;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVigencia() {
        return this.dataVigencia;
    }

    public void setDataVigencia(Date dataVigencia) {
        this.dataVigencia = dataVigencia;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.consumotarifa.ConsumoTarifa getConsumoTarifa() {
        return this.consumoTarifa;
    }

    public void setConsumoTarifa(gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa) {
        this.consumoTarifa = consumoTarifa;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getConsumoTarifaCategorias() {
		return consumoTarifaCategorias;
	}

	public void setConsumoTarifaCategorias(Set consumoTarifaCategorias) {
		this.consumoTarifaCategorias = consumoTarifaCategorias;
	}

}

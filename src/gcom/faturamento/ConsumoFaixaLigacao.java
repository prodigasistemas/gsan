package gcom.faturamento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsumoFaixaLigacao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorHidrometro;

    /** persistent field */
    private int numeroFaixaInicio;

    /** persistent field */
    private int numeroFaixaFim;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public ConsumoFaixaLigacao(Integer id, short indicadorHidrometro, int numeroFaixaInicio, int numeroFaixaFim, Date ultimaAlteracao) {
        this.id = id;
        this.indicadorHidrometro = indicadorHidrometro;
        this.numeroFaixaInicio = numeroFaixaInicio;
        this.numeroFaixaFim = numeroFaixaFim;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ConsumoFaixaLigacao() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getIndicadorHidrometro() {
        return this.indicadorHidrometro;
    }

    public void setIndicadorHidrometro(short indicadorHidrometro) {
        this.indicadorHidrometro = indicadorHidrometro;
    }

    public int getNumeroFaixaInicio() {
        return this.numeroFaixaInicio;
    }

    public void setNumeroFaixaInicio(int numeroFaixaInicio) {
        this.numeroFaixaInicio = numeroFaixaInicio;
    }

    public int getNumeroFaixaFim() {
        return this.numeroFaixaFim;
    }

    public void setNumeroFaixaFim(int numeroFaixaFim) {
        this.numeroFaixaFim = numeroFaixaFim;
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

}

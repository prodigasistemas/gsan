package gcom.faturamento;

import gcom.cadastro.imovel.Categoria;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsumoFaixaCategoria implements Serializable {

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int numeroFaixaInicio;

    /** persistent field */
    private int numeroFaixaFim;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Categoria categoria;

    /** full constructor */
    public ConsumoFaixaCategoria(Integer id, int numeroFaixaInicio, int numeroFaixaFim, Date ultimaAlteracao, Categoria categoria) {
        this.id = id;
        this.numeroFaixaInicio = numeroFaixaInicio;
        this.numeroFaixaFim = numeroFaixaFim;
        this.ultimaAlteracao = ultimaAlteracao;
        this.categoria = categoria;
    }

    /** default constructor */
    public ConsumoFaixaCategoria() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}

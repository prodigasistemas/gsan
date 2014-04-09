package gcom.seguranca.acesso;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Modulo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoModulo;

    /** nullable persistent field */
    private String descricaoAbreviado;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private Short numeroOrdemMenu;
    

    /** full constructor */
    public Modulo(String descricaoModulo, String descricaoAbreviado, Date ultimaAlteracao) {
        this.descricaoModulo = descricaoModulo;
        this.descricaoAbreviado = descricaoAbreviado;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public Modulo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoModulo() {
        return this.descricaoModulo;
    }

    public void setDescricaoModulo(String descricaoModulo) {
        this.descricaoModulo = descricaoModulo;
    }

    public String getDescricaoAbreviado() {
        return this.descricaoAbreviado;
    }

    public void setDescricaoAbreviado(String descricaoAbreviado) {
        this.descricaoAbreviado = descricaoAbreviado;
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

	public Short getNumeroOrdemMenu() {
		return numeroOrdemMenu;
	}

	public void setNumeroOrdemMenu(Short numeroOrdemMenu) {
		this.numeroOrdemMenu = numeroOrdemMenu;
	}
    
    

}

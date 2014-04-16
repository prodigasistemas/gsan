package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DbVersaoImplementada implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataImplementacao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public DbVersaoImplementada(Integer id, Date dataImplementacao, Date ultimaAlteracao) {
        this.id = id;
        this.dataImplementacao = dataImplementacao;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public DbVersaoImplementada() {
    }

    public Integer getid() {
        return this.id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public Date getdataImplementacao() {
        return this.dataImplementacao;
    }

    public void setdataImplementacao(Date dataImplementacao) {
        this.dataImplementacao = dataImplementacao;
    }

    public Date getultimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setultimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getid())
            .toString();
    }

}

package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DbVersaoBase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;
	
    /** identifier field */
    private String nomeEmpresa;
    
    /** persistent field */
    private String nomeDataBase;
	
    /** persistent field */
    private Date versaoDataBase;
    
    /** persistent field */
    private String nomeLoginBase;

    /** persistent field */
    private Date ultimaAlteracao;

    /** default constructor */
    public DbVersaoBase() {
    }

    /** full constructor */
    public DbVersaoBase(Integer id, String nomeEmpresa, String nomeDataBase, 
    		Date versaoDataBase, String nomeLoginBase, Date ultimaAlteracao) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeDataBase = nomeDataBase;
        this.versaoDataBase = versaoDataBase;
        this.nomeLoginBase = nomeLoginBase;
        this.ultimaAlteracao = ultimaAlteracao;
       
    }

   

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeDataBase() {
		return nomeDataBase;
	}

	public void setNomeDataBase(String nomeDataBase) {
		this.nomeDataBase = nomeDataBase;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeLoginBase() {
		return nomeLoginBase;
	}

	public void setNomeLoginBase(String nomeLoginBase) {
		this.nomeLoginBase = nomeLoginBase;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Date getVersaoDataBase() {
		return versaoDataBase;
	}

	public void setVersaoDataBase(Date versaoDataBase) {
		this.versaoDataBase = versaoDataBase;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}

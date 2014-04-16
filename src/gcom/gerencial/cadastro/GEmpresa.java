package gcom.gerencial.cadastro;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GEmpresa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String nomeEmpresa;

    /** nullable persistent field */
    private String descricaoEmail;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private short indicadorEmpresaPrincipal;

    /** nullable persistent field */
    private String nomeAbreviadoEmpresa;

    /** full constructor */
    public GEmpresa(Integer id, String nomeEmpresa, String descricaoEmail, Short indicadorUso, Date ultimaAlteracao, short indicadorEmpresaPrincipal, String nomeAbreviadoEmpresa) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.descricaoEmail = descricaoEmail;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
        this.nomeAbreviadoEmpresa = nomeAbreviadoEmpresa;
    }

    /** default constructor */
    public GEmpresa() {
    }

    /** minimal constructor */
    public GEmpresa(Integer id, Date ultimaAlteracao, short indicadorEmpresaPrincipal) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getDescricaoEmail() {
        return this.descricaoEmail;
    }

    public void setDescricaoEmail(String descricaoEmail) {
        this.descricaoEmail = descricaoEmail;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public short getIndicadorEmpresaPrincipal() {
        return this.indicadorEmpresaPrincipal;
    }

    public void setIndicadorEmpresaPrincipal(short indicadorEmpresaPrincipal) {
        this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
    }

    public String getNomeAbreviadoEmpresa() {
        return this.nomeAbreviadoEmpresa;
    }

    public void setNomeAbreviadoEmpresa(String nomeAbreviadoEmpresa) {
        this.nomeAbreviadoEmpresa = nomeAbreviadoEmpresa;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}

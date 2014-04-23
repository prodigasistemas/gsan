package gcom.gerencial.cadastro.unidade;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GUnidadeOrganizacional implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;
    
    private String descricaoSiglaUnidade;

    /** persistent field */
    private Date ultimaAlteracao;
    
    public GUnidadeOrganizacional(){}
    
	public GUnidadeOrganizacional(String descricao, String descricaoSiglaUnidade, Date ultimaAlteracao) {
		super();
		
		this.descricao = descricao;
		this.descricaoSiglaUnidade = descricaoSiglaUnidade;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricaoSiglaUnidade() {
		return descricaoSiglaUnidade;
	}
	public void setDescricaoSiglaUnidade(String descricaoSiglaUnidade) {
		this.descricaoSiglaUnidade = descricaoSiglaUnidade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
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

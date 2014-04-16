package gcom.cadastro.sistemaparametro;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;



/** @author Hibernate CodeGenerator */
public class SistemaAlteracaoHistorico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String nome;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private Date data;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.seguranca.acesso.Funcionalidade funcionalidade;

    /** full constructor */
    public SistemaAlteracaoHistorico(Integer id, String nome, String descricao, Date data, Date ultimaAlteracao, gcom.seguranca.acesso.Funcionalidade funcionalidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.ultimaAlteracao = ultimaAlteracao;
        this.funcionalidade = funcionalidade;
    }

    /** default constructor */
    public SistemaAlteracaoHistorico() {
    }
  

    public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public gcom.seguranca.acesso.Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(gcom.seguranca.acesso.Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}

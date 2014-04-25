package gcom.seguranca.acesso.usuario;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UsuarioAcao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	/** Operacao de teste */
	public static final UsuarioAcao USUARIO_ACAO_EFETUOU_OPERACAO;

	/** Operacao de teste */
	public static final UsuarioAcao USUARIO_ACAO_RESPONSAVEL_INFORMACAO;
	
	static {
		USUARIO_ACAO_EFETUOU_OPERACAO = new UsuarioAcao();
		USUARIO_ACAO_EFETUOU_OPERACAO.setId(new Integer(1));
		
		USUARIO_ACAO_RESPONSAVEL_INFORMACAO = new UsuarioAcao();
		USUARIO_ACAO_RESPONSAVEL_INFORMACAO.setId(new Integer(2));
	}
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** full constructor */
    public UsuarioAcao(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public UsuarioAcao() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
	public boolean equals(Object arg) {		
		if (arg == null){
			return false;
		}
		if (!(arg instanceof UsuarioAcao)){
			return false;
		} 		
		return this.id.intValue() == ((UsuarioAcao) arg).getId().intValue();
	}
}

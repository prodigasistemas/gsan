package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoColetiva implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataGeracao;

    /** nullable persistent field */
    private String comentario;

    /** nullable persistent field */
    private Date dataEncerramento;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private String nomeFiscalizacaoColetiva;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalAbertura;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalEncerramento;
    
    /** persistent field */
    private Usuario UsuarioDaAbertura;
    
    /** persistent field */
    private Usuario UsuarioDeEncerramento;

    /** full constructor */
    public FiscalizacaoColetiva(Date dataGeracao, String comentario, Date dataEncerramento, Date ultimaAlteracao, String nomeFiscalizacaoColetiva, 
    		UnidadeOrganizacional unidadeOrganizacionalAbertura, UnidadeOrganizacional unidadeOrganizacionalEncerramento, Usuario UsuarioDaAbertura, 
    		Usuario UsuarioDeEncerramento) {
        this.dataGeracao = dataGeracao;
        this.comentario = comentario;
        this.dataEncerramento = dataEncerramento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.nomeFiscalizacaoColetiva = nomeFiscalizacaoColetiva;
        this.unidadeOrganizacionalAbertura = unidadeOrganizacionalAbertura;
        this.unidadeOrganizacionalEncerramento = unidadeOrganizacionalEncerramento;
        this.UsuarioDaAbertura = UsuarioDaAbertura;
        this.UsuarioDeEncerramento = UsuarioDeEncerramento;
    }

    /** default constructor */
    public FiscalizacaoColetiva() {
    }

    /** minimal constructor */
    public FiscalizacaoColetiva(Date dataGeracao, Date ultimaAlteracao) {
        this.dataGeracao = dataGeracao;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataGeracao() {
        return this.dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getDataEncerramento() {
        return this.dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
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

	/**
	 * @return Retorna o campo nomeFiscalizacaoColetiva.
	 */
	public String getNomeFiscalizacaoColetiva() {
		return nomeFiscalizacaoColetiva;
	}

	/**
	 * @param nomeFiscalizacaoColetiva O nomeFiscalizacaoColetiva a ser setado.
	 */
	public void setNomeFiscalizacaoColetiva(String nomeFiscalizacaoColetiva) {
		this.nomeFiscalizacaoColetiva = nomeFiscalizacaoColetiva;
	}

	/**
	 * @return Retorna o campo unidadeOrganizacionalAbertura.
	 */
	public UnidadeOrganizacional getUnidadeOrganizacionalAbertura() {
		return unidadeOrganizacionalAbertura;
	}

	/**
	 * @param unidadeOrganizacionalAbertura O unidadeOrganizacionalAbertura a ser setado.
	 */
	public void setUnidadeOrganizacionalAbertura(
			UnidadeOrganizacional unidadeOrganizacionalAbertura) {
		this.unidadeOrganizacionalAbertura = unidadeOrganizacionalAbertura;
	}

	/**
	 * @return Retorna o campo unidadeOrganizacionalEncerramento.
	 */
	public UnidadeOrganizacional getUnidadeOrganizacionalEncerramento() {
		return unidadeOrganizacionalEncerramento;
	}

	/**
	 * @param unidadeOrganizacionalEncerramento O unidadeOrganizacionalEncerramento a ser setado.
	 */
	public void setUnidadeOrganizacionalEncerramento(
			UnidadeOrganizacional unidadeOrganizacionalEncerramento) {
		this.unidadeOrganizacionalEncerramento = unidadeOrganizacionalEncerramento;
	}

	/**
	 * @return Retorna o campo usuarioDaAbertura.
	 */
	public Usuario getUsuarioDaAbertura() {
		return UsuarioDaAbertura;
	}

	/**
	 * @param usuarioDaAbertura O usuarioDaAbertura a ser setado.
	 */
	public void setUsuarioDaAbertura(Usuario usuarioDaAbertura) {
		UsuarioDaAbertura = usuarioDaAbertura;
	}

	/**
	 * @return Retorna o campo usuarioDeEncerramento.
	 */
	public Usuario getUsuarioDeEncerramento() {
		return UsuarioDeEncerramento;
	}

	/**
	 * @param usuarioDeEncerramento O usuarioDeEncerramento a ser setado.
	 */
	public void setUsuarioDeEncerramento(Usuario usuarioDeEncerramento) {
		UsuarioDeEncerramento = usuarioDeEncerramento;
	}

}

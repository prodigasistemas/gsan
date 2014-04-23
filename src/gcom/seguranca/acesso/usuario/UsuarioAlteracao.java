package gcom.seguranca.acesso.usuario;

//import gcom.seguranca.transacao.TransacaoEfetuada;
import gcom.cadastro.empresa.Empresa;
import gcom.seguranca.acesso.OperacaoEfetuada;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class UsuarioAlteracao implements Serializable {

	private static final long serialVersionUID = 1L;

	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada;

    /** persistent field */
    private gcom.seguranca.acesso.usuario.UsuarioAcao usuarioAcao;
    
    private Empresa empresa;

    private String ipAlteracao;
    
    public UsuarioAlteracao(Integer id, Date ultimaAlteracao, Usuario usuario, OperacaoEfetuada operacaoEfetuada, UsuarioAcao usuarioAcao, Empresa empresa) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.usuario = usuario;
        this.operacaoEfetuada = operacaoEfetuada;
        this.usuarioAcao = usuarioAcao;
        this.empresa = empresa;
    }

    /** default constructor */
    public UsuarioAlteracao() {
    }

    /** minimal constructor */
    public UsuarioAlteracao(Integer id, Usuario usuario, OperacaoEfetuada operacaoEfetuada, UsuarioAcao usuarioAcao) {
        this.id = id;
        this.usuario = usuario;
        this.operacaoEfetuada = operacaoEfetuada;
        this.usuarioAcao = usuarioAcao;
    }

    /**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the operacaoEfetuada.
	 */
	public OperacaoEfetuada getOperacaoEfetuada() {
		return operacaoEfetuada;
	}

	/**
	 * @param operacaoEfetuada The operacaoEfetuada to set.
	 */
	public void setOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada) {
		this.operacaoEfetuada = operacaoEfetuada;
	}

	/**
	 * @return Returns the ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao The ultimaAlteracao to set.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Returns the usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario The usuario to set.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return Returns the usuarioAcao.
	 */
	public UsuarioAcao getUsuarioAcao() {
		return usuarioAcao;
	}

	/**
	 * @param usuarioAcao The usuarioAcao to set.
	 */
	public void setUsuarioAcao(UsuarioAcao usuarioAcao) {
		this.usuarioAcao = usuarioAcao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getIpAlteracao() {
		return ipAlteracao;
	}

	public void setIpAlteracao(String ipAlteracao) {
		this.ipAlteracao = ipAlteracao;
	}

	
	
	
}

package gcom.seguranca.acesso.usuario;

import gcom.seguranca.acesso.Grupo;
import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class SolicitacaoAcessoGrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK comp_id;
	
	/** identifier field */
	private SolicitacaoAcesso solicitacaoAcesso;

	/** persistent field */
	private Grupo grupo;
	
	/** persistent field */
	private Date ultimaAlteracao;

	/** default constructor */
	public SolicitacaoAcessoGrupo() {
		
	}
	
    /** full constructor */
    public SolicitacaoAcessoGrupo(gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK comp_id, 
    		Date ultimaAlteracao, Grupo grupo, SolicitacaoAcesso solicitacaoAcesso) {
    	
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.solicitacaoAcesso = solicitacaoAcesso;
        this.grupo = grupo;
    }
    
    /** minimal constructor */
    public SolicitacaoAcessoGrupo(gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

	public gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK getComp_id() {
		return this.comp_id ;
	}

	public void setComp_id(
			gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupoPK comp_id) {
		this.comp_id = comp_id;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.seguranca.acesso.Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(gcom.seguranca.acesso.Grupo grupo) {
		this.grupo = grupo;
	}

	public SolicitacaoAcesso getSolicitacaoAcesso() {
		return solicitacaoAcesso;
	}

	public void setSolicitacaoAcesso(SolicitacaoAcesso solicitacaoAcesso) {
		this.solicitacaoAcesso = solicitacaoAcesso;
	}

}

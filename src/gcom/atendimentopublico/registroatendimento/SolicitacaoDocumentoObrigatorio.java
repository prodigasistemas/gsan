package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;

/**
 * Descrição da classe
 * 
 * @author Hugo Leonardo
 * @date 01/09/2010
 */
public class SolicitacaoDocumentoObrigatorio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorioPK comp_id;
	
	/** identifier field */
	private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;

	/** persistent field */
	private MeioSolicitacao meioSolicitacao;
	
	/** persistent field */
	private Date ultimaAlteracao;

	/** default constructor */
	public SolicitacaoDocumentoObrigatorio() {
		
	}
	
    /** full constructor */
    public SolicitacaoDocumentoObrigatorio(gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorioPK comp_id, Date ultimaAlteracao, gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao, MeioSolicitacao meioSolicitacao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
        this.meioSolicitacao = meioSolicitacao;
    }
    
    /** minimal constructor */
    public SolicitacaoDocumentoObrigatorio(gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorioPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

	public gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorioPK getComp_id() {
		return this.comp_id ;
	}

	public void setComp_id(
			gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorioPK comp_id) {
		this.comp_id = comp_id;
	}

	public MeioSolicitacao getMeioSolicitacao() {
		return this.meioSolicitacao;
	}

	public void setMeioSolicitacao(MeioSolicitacao meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return this.solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
    
}

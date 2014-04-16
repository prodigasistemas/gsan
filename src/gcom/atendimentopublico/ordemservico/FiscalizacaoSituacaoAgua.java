package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoAgua implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private FiscalizacaoSituacaoAguaPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private short indicadorConsumoFixado;

    /** nullable persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacaoByLastId;

    /** nullable persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacaoByLastIdnova;
    
    /** persistent field */
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
    
    /** persistent field */
    private Short numeroMesesFatura;
    
    public final static short INDICADOR_SIM = new Short("1");


    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }


	/**
	 * @return Retorna o campo comp_id.
	 */
	public FiscalizacaoSituacaoAguaPK getComp_id() {
		return comp_id;
	}


	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(FiscalizacaoSituacaoAguaPK comp_id) {
		this.comp_id = comp_id;
	}


	/**
	 * @return Retorna o campo fiscalizacaoSituacao.
	 */
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}


	/**
	 * @param fiscalizacaoSituacao O fiscalizacaoSituacao a ser setado.
	 */
	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}


	/**
	 * @return Retorna o campo ligacaoAguaSituacaoByLastId.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacaoByLastId() {
		return ligacaoAguaSituacaoByLastId;
	}


	/**
	 * @param ligacaoAguaSituacaoByLastId O ligacaoAguaSituacaoByLastId a ser setado.
	 */
	public void setLigacaoAguaSituacaoByLastId(
			LigacaoAguaSituacao ligacaoAguaSituacaoByLastId) {
		this.ligacaoAguaSituacaoByLastId = ligacaoAguaSituacaoByLastId;
	}


	/**
	 * @return Retorna o campo ligacaoAguaSituacaoByLastIdnova.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacaoByLastIdnova() {
		return ligacaoAguaSituacaoByLastIdnova;
	}


	/**
	 * @param ligacaoAguaSituacaoByLastIdnova O ligacaoAguaSituacaoByLastIdnova a ser setado.
	 */
	public void setLigacaoAguaSituacaoByLastIdnova(
			LigacaoAguaSituacao ligacaoAguaSituacaoByLastIdnova) {
		this.ligacaoAguaSituacaoByLastIdnova = ligacaoAguaSituacaoByLastIdnova;
	}


	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public short getIndicadorConsumoFixado() {
		return indicadorConsumoFixado;
	}


	public void setIndicadorConsumoFixado(short indicadorConsumoFixado) {
		this.indicadorConsumoFixado = indicadorConsumoFixado;
	}


	/**
	 * @return Retorna o campo solicitacaoTipoEspecificacao.
	 */
	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}


	/**
	 * @param solicitacaoTipoEspecificacao O solicitacaoTipoEspecificacao a ser setado.
	 */
	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}


	public Short getNumeroMesesFatura() {
		return numeroMesesFatura;
	}


	public void setNumeroMesesFatura(Short numeroMesesFatura) {
		this.numeroMesesFatura = numeroMesesFatura;
	}
	
	
	
	
}

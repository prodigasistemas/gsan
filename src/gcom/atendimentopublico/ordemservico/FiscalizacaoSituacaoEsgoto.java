package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoEsgoto implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private FiscalizacaoSituacaoEsgotoPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private short indicadorConsumoFixado;

    /** nullable persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacaoByLestId;

    /** nullable persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacaoByLestIdnova;
    
    /** persistent field */
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
    
    /** persistent field */
    private Short numeroMesesFatura;
    
    
    public final static short INDICADOR_SIM = new Short("1");

	/**
	 * @return Retorna o campo comp_id.
	 */
	public FiscalizacaoSituacaoEsgotoPK getComp_id() {
		return comp_id;
	}

	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(FiscalizacaoSituacaoEsgotoPK comp_id) {
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
	 * @return Retorna o campo ligacaoEsgotoSituacaoByLestId.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacaoByLestId() {
		return ligacaoEsgotoSituacaoByLestId;
	}

	/**
	 * @param ligacaoEsgotoSituacaoByLestId O ligacaoEsgotoSituacaoByLestId a ser setado.
	 */
	public void setLigacaoEsgotoSituacaoByLestId(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacaoByLestId) {
		this.ligacaoEsgotoSituacaoByLestId = ligacaoEsgotoSituacaoByLestId;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacaoByLestIdnova.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacaoByLestIdnova() {
		return ligacaoEsgotoSituacaoByLestIdnova;
	}

	/**
	 * @param ligacaoEsgotoSituacaoByLestIdnova O ligacaoEsgotoSituacaoByLestIdnova a ser setado.
	 */
	public void setLigacaoEsgotoSituacaoByLestIdnova(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacaoByLestIdnova) {
		this.ligacaoEsgotoSituacaoByLestIdnova = ligacaoEsgotoSituacaoByLestIdnova;
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

package gcom.cobranca.bean;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;

import java.util.Collection;

public class CancelarDocumentosCobrancaHelper {
	
	private CobrancaAcao cobrancaAcao;
	private Collection<Integer> idsDocumentosCobranca;
	private Integer anoMesReferencia;
	private Integer idGrupo;
	
	private CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;
	private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;

	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}

	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}

	public CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
		return cobrancaAcaoAtividadeCronograma;
	}

	public void setCobrancaAcaoAtividadeCronograma(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}

	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}
	
	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}
	
	public Collection<Integer> getIdsDocumentosCobranca() {
		return idsDocumentosCobranca;
	}
	
	public void setIdsDocumentosCobranca(Collection<Integer> idsDocumentosCobranca) {
		this.idsDocumentosCobranca = idsDocumentosCobranca;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	
}

package gcom.gui.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


/**
 * [UC1056] ? Gerar Relatório de Acompanhamento dos Registros de Atendimento
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de Acompanhamento dos Registros de Atendimento.
 * 
 * @author Hugo Leonardo
 * @date 28/09/2010
 */

public class FiltrarAcompanhamentoRegistroAtendimentoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String situacaoRA;
	private String situacaoRAAbertos;
	private Date periodoAtendimentoInicial;
	private Date periodoAtendimentoFinal;
	private Date periodoEncerramentoInicial;
	private Date periodoEncerramentoFinal;
	private String idUnidadeAtendimento;
	private Collection<Integer> idsMotivoEncerramentoSelecionados;
	private Collection<Integer> municipiosAssociados;
	
	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}
	
	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}
	
	public String getSituacaoRA() {
		return situacaoRA;
	}
	
	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}
	
	public String getSituacaoRAAbertos() {
		return situacaoRAAbertos;
	}
	
	public void setSituacaoRAAbertos(String situacaoRAAbertos) {
		this.situacaoRAAbertos = situacaoRAAbertos;
	}

	public Date getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}

	public void setPeriodoAtendimentoFinal(Date periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}

	public Date getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}

	public void setPeriodoAtendimentoInicial(Date periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}

	public Date getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}

	public void setPeriodoEncerramentoFinal(Date periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}

	public Date getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}

	public void setPeriodoEncerramentoInicial(Date periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}

	public Collection<Integer> getIdsMotivoEncerramentoSelecionados() {
		return idsMotivoEncerramentoSelecionados;
	}

	public void setIdsMotivoEncerramentoSelecionados(
			Collection<Integer> idsMotivoEncerramentoSelecionados) {
		this.idsMotivoEncerramentoSelecionados = idsMotivoEncerramentoSelecionados;
	}

	public Collection<Integer> getMunicipiosAssociados() {
		return municipiosAssociados;
	}

	public void setMunicipiosAssociados(Collection<Integer> municipiosAssociados) {
		this.municipiosAssociados = municipiosAssociados;
	}
}

package gcom.gui.atendimentopublico.registroatendimento;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * Filtrar Acompanhamento Registro Atendimento
 * 
 * @author Flávio Ferreira
 */
public class FiltrarAcompanhamentoRegistroAtendimentoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String situacaoRA;
	private String situacaoRAAbertos;
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	private String unidadeAtendimentoId;
	private String unidadeAtendimentoDescricao;	
	private Integer[] idsMotivoEncerramentoDisponiveis; 
	private Integer[] idsMotivoEncerramentoSelecionados;
	private String opcaoRelatorio;
	private String[] municipiosAssociados;
	
	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}

	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}

	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}

	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}

	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}

	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}

	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}

	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}

	public String getUnidadeAtendimentoDescricao() {
		return unidadeAtendimentoDescricao;
	}

	public void setUnidadeAtendimentoDescricao(String unidadeAtendimentoDescricao) {
		this.unidadeAtendimentoDescricao = unidadeAtendimentoDescricao;
	}

	public String getUnidadeAtendimentoId() {
		return unidadeAtendimentoId;
	}

	public void setUnidadeAtendimentoId(String unidadeAtendimentoId) {
		this.unidadeAtendimentoId = unidadeAtendimentoId;
	}

	public Integer[] getIdsMotivoEncerramentoDisponiveis() {
		return idsMotivoEncerramentoDisponiveis;
	}

	public void setIdsMotivoEncerramentoDisponiveis(
			Integer[] idsMotivoEncerramentoDisponiveis) {
		this.idsMotivoEncerramentoDisponiveis = idsMotivoEncerramentoDisponiveis;
	}

	public Integer[] getIdsMotivoEncerramentoSelecionados() {
		return idsMotivoEncerramentoSelecionados;
	}

	public void setIdsMotivoEncerramentoSelecionados(
			Integer[] idsMotivoEncerramentoSelecionados) {
		this.idsMotivoEncerramentoSelecionados = idsMotivoEncerramentoSelecionados;
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

	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}

	public String[] getMunicipiosAssociados() {
		return municipiosAssociados;
	}

	public void setMunicipiosAssociados(String[] municipiosAssociados) {
		this.municipiosAssociados = municipiosAssociados;
	}
}

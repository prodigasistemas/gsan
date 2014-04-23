package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.action.ActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da pesquisa de Observação do Registro Atendimento (Solicitação da CAER)
 *
 * @author Rafael Pinto
 * @date 14/03/2007
 */
public class PesquisarObservacaoRegistroAtendimentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String matriculaImovel;
	private String inscricaoImovel;
	
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	
	private String observacao;
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	
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

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	
	
	
}

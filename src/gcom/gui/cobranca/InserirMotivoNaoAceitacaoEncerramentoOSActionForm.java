package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1171] Inserir Motivos de Não Aceitação de Encerramento de OS
 * 
 * Classe responsável por guardar os valores do formulário de
 * motivo_nao_aceitacao_encerramento_os_inserir.jsp
 * 
 * @author Diogo Peixoto
 * @since 20/05/2011
 * 
 */
public class InserirMotivoNaoAceitacaoEncerramentoOSActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private String multiplicadorValorServicoDescontarCorteSupressao;
	private String multiplicadorValorServicoDescontarNaoExecutados;
	private String percentualMultaAplicar;

	public InserirMotivoNaoAceitacaoEncerramentoOSActionForm(){
		
	}
	
	public void limparCampos(){
		this.descricao = null;
		this.multiplicadorValorServicoDescontarCorteSupressao = null;
		this.multiplicadorValorServicoDescontarNaoExecutados = null;
		this.percentualMultaAplicar = null;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getMultiplicadorValorServicoDescontarCorteSupressao() {
		return multiplicadorValorServicoDescontarCorteSupressao;
	}
	public void setMultiplicadorValorServicoDescontarCorteSupressao(
			String multiplicadorValorServicoDescontarCorteSupressao) {
		this.multiplicadorValorServicoDescontarCorteSupressao = multiplicadorValorServicoDescontarCorteSupressao;
	}
	public String getMultiplicadorValorServicoDescontarNaoExecutados() {
		return multiplicadorValorServicoDescontarNaoExecutados;
	}
	public void setMultiplicadorValorServicoDescontarNaoExecutados(
			String multiplicadorValorServicoDescontarNaoExecutados) {
		this.multiplicadorValorServicoDescontarNaoExecutados = multiplicadorValorServicoDescontarNaoExecutados;
	}
	public String getPercentualMultaAplicar() {
		return percentualMultaAplicar;
	}
	public void setPercentualMultaAplicar(String percentualMultaAplicar) {
		this.percentualMultaAplicar = percentualMultaAplicar;
	}
}

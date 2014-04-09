package gcom.gui.arrecadacao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da filtragem dos movimentos dos arrecadadores 
 *
 * @author Raphael Rossiter
 * @date 23/02/2006
 */
public class FiltrarMovimentoArrecadadoresActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String banco;
	private String descricaoBanco;
	private String remessa;
	private String identificacaoServico;
	private String nsa;
	private String dataGeracaoMovimentoInicio;
	private String dataGeracaoMovimentoFim;
	private String dataProcessamentoMovimentoInicio;
	private String dataProcessamentoMovimentoFim;
	private String movimentoItemOcorrencia;
	private String movimentoItemAceito;
	private String movimentoAbertoFechado;
	private String formaArrecadacao;
	private String indicadorRelatorio;
  
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getDataGeracaoMovimentoFim() {
		return dataGeracaoMovimentoFim;
	}
	public void setDataGeracaoMovimentoFim(String dataGeracaoMovimentoFim) {
		this.dataGeracaoMovimentoFim = dataGeracaoMovimentoFim;
	}
	public String getDataGeracaoMovimentoInicio() {
		return dataGeracaoMovimentoInicio;
	}
	public void setDataGeracaoMovimentoInicio(String dataGeracaoMovimentoInicio) {
		this.dataGeracaoMovimentoInicio = dataGeracaoMovimentoInicio;
	}
	public String getDataProcessamentoMovimentoFim() {
		return dataProcessamentoMovimentoFim;
	}
	public void setDataProcessamentoMovimentoFim(
			String dataProcessamentoMovimentoFim) {
		this.dataProcessamentoMovimentoFim = dataProcessamentoMovimentoFim;
	}
	public String getDataProcessamentoMovimentoInicio() {
		return dataProcessamentoMovimentoInicio;
	}
	public void setDataProcessamentoMovimentoInicio(
			String dataProcessamentoMovimentoInicio) {
		this.dataProcessamentoMovimentoInicio = dataProcessamentoMovimentoInicio;
	}
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	public String getMovimentoAbertoFechado() {
		return movimentoAbertoFechado;
	}
	public void setMovimentoAbertoFechado(String movimentoAbertoFechado) {
		this.movimentoAbertoFechado = movimentoAbertoFechado;
	}
	public String getMovimentoItemAceito() {
		return movimentoItemAceito;
	}
	public void setMovimentoItemAceito(String movimentoItemAceito) {
		this.movimentoItemAceito = movimentoItemAceito;
	}
	public String getMovimentoItemOcorrencia() {
		return movimentoItemOcorrencia;
	}
	public void setMovimentoItemOcorrencia(String movimentoItemOcorrencia) {
		this.movimentoItemOcorrencia = movimentoItemOcorrencia;
	}
	public String getNsa() {
		return nsa;
	}
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}
	public String getRemessa() {
		return remessa;
	}
	public void setRemessa(String remessa) {
		this.remessa = remessa;
	}
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	
	public String getDescricaoBanco() {
		return descricaoBanco;
	}
	public void setDescricaoBanco(String descricaoBanco) {
		this.descricaoBanco = descricaoBanco;
	}
	/**
	 * @return Retorna o campo formaArrecadacao.
	 */
	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}
	/**
	 * @param formaArrecadacao O formaArrecadacao a ser setado.
	 */
	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}
	public String getIndicadorRelatorio() {
		return indicadorRelatorio;
	}
	public void setIndicadorRelatorio(String indicadorRelatorio) {
		this.indicadorRelatorio = indicadorRelatorio;
	}
	
}

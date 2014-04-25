package gcom.gui.arrecadacao.aviso;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0611] Movimentar Pagamentos/ Devoluções entre Avisos Bancários 
 * 
 * @author Ana Maria
 *
 * @date 07/06/2007
 */
public class MovimentarPagamentosDevolucoesAvisoBancarioActionForm extends ValidatorActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String arrecadacaoInformadoAntesOrigem;
	private String arrecadacaoCalculadoAntesOrigem;
	private String devolucaoInformadoAntesOrigem;
	private String devolucaoCalculadoAntesOrigem;
	private String arrecadacaoInformadoDepoisOrigem;
	private String arrecadacaoCalculadoDepoisOrigem;
	private String devolucaoInformadoDepoisOrigem;
	private String devolucaoCalculadoDepoisOrigem;
	
	private String arrecadacaoInformadoAntesDestino;
	private String arrecadacaoCalculadoAntesDestino;
	private String devolucaoInformadoAntesDestino;
	private String devolucaoCalculadoAntesDestino;
	private String arrecadacaoInformadoDepoisDestino;
	private String arrecadacaoCalculadoDepoisDestino;
	private String devolucaoInformadoDepoisDestino;
	private String devolucaoCalculadoDepoisDestino;
	
    private String[] idRegistrosRemocaoPagamento;	
    private String[] idRegistrosRemocaoDevolucao;    
	/**
	 * @return Retorna o campo arrecadacaoCalculadoAntesDestino.
	 */
	public String getArrecadacaoCalculadoAntesDestino() {
		return arrecadacaoCalculadoAntesDestino;
	}
	/**
	 * @param arrecadacaoCalculadoAntesDestino O arrecadacaoCalculadoAntesDestino a ser setado.
	 */
	public void setArrecadacaoCalculadoAntesDestino(
			String arrecadacaoCalculadoAntesDestino) {
		this.arrecadacaoCalculadoAntesDestino = arrecadacaoCalculadoAntesDestino;
	}
	/**
	 * @return Retorna o campo arrecadacaoCalculadoAntesOrigem.
	 */
	public String getArrecadacaoCalculadoAntesOrigem() {
		return arrecadacaoCalculadoAntesOrigem;
	}
	/**
	 * @param arrecadacaoCalculadoAntesOrigem O arrecadacaoCalculadoAntesOrigem a ser setado.
	 */
	public void setArrecadacaoCalculadoAntesOrigem(
			String arrecadacaoCalculadoAntesOrigem) {
		this.arrecadacaoCalculadoAntesOrigem = arrecadacaoCalculadoAntesOrigem;
	}
	/**
	 * @return Retorna o campo arrecadacaoCalculadoDepoisDestino.
	 */
	public String getArrecadacaoCalculadoDepoisDestino() {
		return arrecadacaoCalculadoDepoisDestino;
	}
	/**
	 * @param arrecadacaoCalculadoDepoisDestino O arrecadacaoCalculadoDepoisDestino a ser setado.
	 */
	public void setArrecadacaoCalculadoDepoisDestino(
			String arrecadacaoCalculadoDepoisDestino) {
		this.arrecadacaoCalculadoDepoisDestino = arrecadacaoCalculadoDepoisDestino;
	}
	/**
	 * @return Retorna o campo arrecadacaoCalculadoDepoisOrigem.
	 */
	public String getArrecadacaoCalculadoDepoisOrigem() {
		return arrecadacaoCalculadoDepoisOrigem;
	}
	/**
	 * @param arrecadacaoCalculadoDepoisOrigem O arrecadacaoCalculadoDepoisOrigem a ser setado.
	 */
	public void setArrecadacaoCalculadoDepoisOrigem(
			String arrecadacaoCalculadoDepoisOrigem) {
		this.arrecadacaoCalculadoDepoisOrigem = arrecadacaoCalculadoDepoisOrigem;
	}
	/**
	 * @return Retorna o campo arrecadacaoInformadoAntesDestino.
	 */
	public String getArrecadacaoInformadoAntesDestino() {
		return arrecadacaoInformadoAntesDestino;
	}
	/**
	 * @param arrecadacaoInformadoAntesDestino O arrecadacaoInformadoAntesDestino a ser setado.
	 */
	public void setArrecadacaoInformadoAntesDestino(
			String arrecadacaoInformadoAntesDestino) {
		this.arrecadacaoInformadoAntesDestino = arrecadacaoInformadoAntesDestino;
	}
	/**
	 * @return Retorna o campo arrecadacaoInformadoAntesOrigem.
	 */
	public String getArrecadacaoInformadoAntesOrigem() {
		return arrecadacaoInformadoAntesOrigem;
	}
	/**
	 * @param arrecadacaoInformadoAntesOrigem O arrecadacaoInformadoAntesOrigem a ser setado.
	 */
	public void setArrecadacaoInformadoAntesOrigem(
			String arrecadacaoInformadoAntesOrigem) {
		this.arrecadacaoInformadoAntesOrigem = arrecadacaoInformadoAntesOrigem;
	}
	/**
	 * @return Retorna o campo arrecadacaoInformadoDepoisDestino.
	 */
	public String getArrecadacaoInformadoDepoisDestino() {
		return arrecadacaoInformadoDepoisDestino;
	}
	/**
	 * @param arrecadacaoInformadoDepoisDestino O arrecadacaoInformadoDepoisDestino a ser setado.
	 */
	public void setArrecadacaoInformadoDepoisDestino(
			String arrecadacaoInformadoDepoisDestino) {
		this.arrecadacaoInformadoDepoisDestino = arrecadacaoInformadoDepoisDestino;
	}
	/**
	 * @return Retorna o campo arrecadacaoInformadoDepoisOrigem.
	 */
	public String getArrecadacaoInformadoDepoisOrigem() {
		return arrecadacaoInformadoDepoisOrigem;
	}
	/**
	 * @param arrecadacaoInformadoDepoisOrigem O arrecadacaoInformadoDepoisOrigem a ser setado.
	 */
	public void setArrecadacaoInformadoDepoisOrigem(
			String arrecadacaoInformadoDepoisOrigem) {
		this.arrecadacaoInformadoDepoisOrigem = arrecadacaoInformadoDepoisOrigem;
	}
	/**
	 * @return Retorna o campo devolucaoCalculadoAntesDestino.
	 */
	public String getDevolucaoCalculadoAntesDestino() {
		return devolucaoCalculadoAntesDestino;
	}
	/**
	 * @param devolucaoCalculadoAntesDestino O devolucaoCalculadoAntesDestino a ser setado.
	 */
	public void setDevolucaoCalculadoAntesDestino(
			String devolucaoCalculadoAntesDestino) {
		this.devolucaoCalculadoAntesDestino = devolucaoCalculadoAntesDestino;
	}
	/**
	 * @return Retorna o campo devolucaoCalculadoAntesOrigem.
	 */
	public String getDevolucaoCalculadoAntesOrigem() {
		return devolucaoCalculadoAntesOrigem;
	}
	/**
	 * @param devolucaoCalculadoAntesOrigem O devolucaoCalculadoAntesOrigem a ser setado.
	 */
	public void setDevolucaoCalculadoAntesOrigem(
			String devolucaoCalculadoAntesOrigem) {
		this.devolucaoCalculadoAntesOrigem = devolucaoCalculadoAntesOrigem;
	}
	/**
	 * @return Retorna o campo devolucaoCalculadoDepoisDestino.
	 */
	public String getDevolucaoCalculadoDepoisDestino() {
		return devolucaoCalculadoDepoisDestino;
	}
	/**
	 * @param devolucaoCalculadoDepoisDestino O devolucaoCalculadoDepoisDestino a ser setado.
	 */
	public void setDevolucaoCalculadoDepoisDestino(
			String devolucaoCalculadoDepoisDestino) {
		this.devolucaoCalculadoDepoisDestino = devolucaoCalculadoDepoisDestino;
	}
	/**
	 * @return Retorna o campo devolucaoCalculadoDepoisOrigem.
	 */
	public String getDevolucaoCalculadoDepoisOrigem() {
		return devolucaoCalculadoDepoisOrigem;
	}
	/**
	 * @param devolucaoCalculadoDepoisOrigem O devolucaoCalculadoDepoisOrigem a ser setado.
	 */
	public void setDevolucaoCalculadoDepoisOrigem(
			String devolucaoCalculadoDepoisOrigem) {
		this.devolucaoCalculadoDepoisOrigem = devolucaoCalculadoDepoisOrigem;
	}
	/**
	 * @return Retorna o campo devolucaoInformadoAntesDestino.
	 */
	public String getDevolucaoInformadoAntesDestino() {
		return devolucaoInformadoAntesDestino;
	}
	/**
	 * @param devolucaoInformadoAntesDestino O devolucaoInformadoAntesDestino a ser setado.
	 */
	public void setDevolucaoInformadoAntesDestino(
			String devolucaoInformadoAntesDestino) {
		this.devolucaoInformadoAntesDestino = devolucaoInformadoAntesDestino;
	}
	/**
	 * @return Retorna o campo devolucaoInformadoAntesOrigem.
	 */
	public String getDevolucaoInformadoAntesOrigem() {
		return devolucaoInformadoAntesOrigem;
	}
	/**
	 * @param devolucaoInformadoAntesOrigem O devolucaoInformadoAntesOrigem a ser setado.
	 */
	public void setDevolucaoInformadoAntesOrigem(
			String devolucaoInformadoAntesOrigem) {
		this.devolucaoInformadoAntesOrigem = devolucaoInformadoAntesOrigem;
	}
	/**
	 * @return Retorna o campo devolucaoInformadoDepoisDestino.
	 */
	public String getDevolucaoInformadoDepoisDestino() {
		return devolucaoInformadoDepoisDestino;
	}
	/**
	 * @param devolucaoInformadoDepoisDestino O devolucaoInformadoDepoisDestino a ser setado.
	 */
	public void setDevolucaoInformadoDepoisDestino(
			String devolucaoInformadoDepoisDestino) {
		this.devolucaoInformadoDepoisDestino = devolucaoInformadoDepoisDestino;
	}
	/**
	 * @return Retorna o campo devolucaoInformadoDepoisOrigem.
	 */
	public String getDevolucaoInformadoDepoisOrigem() {
		return devolucaoInformadoDepoisOrigem;
	}
	/**
	 * @param devolucaoInformadoDepoisOrigem O devolucaoInformadoDepoisOrigem a ser setado.
	 */
	public void setDevolucaoInformadoDepoisOrigem(
			String devolucaoInformadoDepoisOrigem) {
		this.devolucaoInformadoDepoisOrigem = devolucaoInformadoDepoisOrigem;
	}
	/**
	 * @return Retorna o campo idRegistrosRemocaoDevolucao.
	 */
	public String[] getIdRegistrosRemocaoDevolucao() {
		return idRegistrosRemocaoDevolucao;
	}
	/**
	 * @param idRegistrosRemocaoDevolucao O idRegistrosRemocaoDevolucao a ser setado.
	 */
	public void setIdRegistrosRemocaoDevolucao(String[] idRegistrosRemocaoDevolucao) {
		this.idRegistrosRemocaoDevolucao = idRegistrosRemocaoDevolucao;
	}
	/**
	 * @return Retorna o campo idRegistrosRemocaoPagamento.
	 */
	public String[] getIdRegistrosRemocaoPagamento() {
		return idRegistrosRemocaoPagamento;
	}
	/**
	 * @param idRegistrosRemocaoPagamento O idRegistrosRemocaoPagamento a ser setado.
	 */
	public void setIdRegistrosRemocaoPagamento(String[] idRegistrosRemocaoPagamento) {
		this.idRegistrosRemocaoPagamento = idRegistrosRemocaoPagamento;
	}
	
}

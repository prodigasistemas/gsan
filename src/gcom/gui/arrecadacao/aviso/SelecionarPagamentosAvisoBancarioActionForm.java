package gcom.gui.arrecadacao.aviso;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0611] Movimentar Pagamentos/ Devoluções entre Avisos Bancários 
 * 
 * @author Ana Maria
 *
 * @date 07/06/2007
 */
public class SelecionarPagamentosAvisoBancarioActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
    private String codigoAgenteArrecadadorO;    
    private String dataLancamentoAvisoO;
    private String numeroSequencialAvisoO;
	private String avisoBancarioO;
    private String codigoAgenteArrecadadorD;    
    private String dataLancamentoAvisoD;    
    private String numeroSequencialAvisoD;
	private String avisoBancarioD;
	private String dataDevolucao;
	private String dataPagamento;
	private String idArrecadacaoForma;
	/**
	 * @return Retorna o campo avisoBancarioD.
	 */
	public String getAvisoBancarioD() {
		return avisoBancarioD;
	}
	/**
	 * @param avisoBancarioD O avisoBancarioD a ser setado.
	 */
	public void setAvisoBancarioD(String avisoBancarioD) {
		this.avisoBancarioD = avisoBancarioD;
	}
	/**
	 * @return Retorna o campo avisoBancarioO.
	 */
	public String getAvisoBancarioO() {
		return avisoBancarioO;
	}
	/**
	 * @param avisoBancarioO O avisoBancarioO a ser setado.
	 */
	public void setAvisoBancarioO(String avisoBancarioO) {
		this.avisoBancarioO = avisoBancarioO;
	}
	/**
	 * @return Retorna o campo codigoAgenteArrecadadorD.
	 */
	public String getCodigoAgenteArrecadadorD() {
		return codigoAgenteArrecadadorD;
	}
	/**
	 * @param codigoAgenteArrecadadorD O codigoAgenteArrecadadorD a ser setado.
	 */
	public void setCodigoAgenteArrecadadorD(String codigoAgenteArrecadadorD) {
		this.codigoAgenteArrecadadorD = codigoAgenteArrecadadorD;
	}
	/**
	 * @return Retorna o campo codigoAgenteArrecadadorO.
	 */
	public String getCodigoAgenteArrecadadorO() {
		return codigoAgenteArrecadadorO;
	}
	/**
	 * @param codigoAgenteArrecadadorO O codigoAgenteArrecadadorO a ser setado.
	 */
	public void setCodigoAgenteArrecadadorO(String codigoAgenteArrecadadorO) {
		this.codigoAgenteArrecadadorO = codigoAgenteArrecadadorO;
	}
	/**
	 * @return Retorna o campo dataDevolucao.
	 */
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	/**
	 * @param dataDevolucao O dataDevolucao a ser setado.
	 */
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	/**
	 * @return Retorna o campo dataLancamentoAvisoD.
	 */
	public String getDataLancamentoAvisoD() {
		return dataLancamentoAvisoD;
	}
	/**
	 * @param dataLancamentoAvisoD O dataLancamentoAvisoD a ser setado.
	 */
	public void setDataLancamentoAvisoD(String dataLancamentoAvisoD) {
		this.dataLancamentoAvisoD = dataLancamentoAvisoD;
	}
	/**
	 * @return Retorna o campo dataLancamentoAvisoO.
	 */
	public String getDataLancamentoAvisoO() {
		return dataLancamentoAvisoO;
	}
	/**
	 * @param dataLancamentoAvisoO O dataLancamentoAvisoO a ser setado.
	 */
	public void setDataLancamentoAvisoO(String dataLancamentoAvisoO) {
		this.dataLancamentoAvisoO = dataLancamentoAvisoO;
	}
	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public String getDataPagamento() {
		return dataPagamento;
	}
	/**
	 * @param dataPagamento O dataPagamento a ser setado.
	 */
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	/**
	 * @return Retorna o campo idArrecadacaoForma.
	 */
	public String getIdArrecadacaoForma() {
		return idArrecadacaoForma;
	}
	/**
	 * @param idArrecadacaoForma O idArrecadacaoForma a ser setado.
	 */
	public void setIdArrecadacaoForma(String idArrecadacaoForma) {
		this.idArrecadacaoForma = idArrecadacaoForma;
	}
	/**
	 * @return Retorna o campo numeroSequencialAvisoD.
	 */
	public String getNumeroSequencialAvisoD() {
		return numeroSequencialAvisoD;
	}
	/**
	 * @param numeroSequencialAvisoD O numeroSequencialAvisoD a ser setado.
	 */
	public void setNumeroSequencialAvisoD(String numeroSequencialAvisoD) {
		this.numeroSequencialAvisoD = numeroSequencialAvisoD;
	}
	/**
	 * @return Retorna o campo numeroSequencialAvisoO.
	 */
	public String getNumeroSequencialAvisoO() {
		return numeroSequencialAvisoO;
	}
	/**
	 * @param numeroSequencialAvisoO O numeroSequencialAvisoO a ser setado.
	 */
	public void setNumeroSequencialAvisoO(String numeroSequencialAvisoO) {
		this.numeroSequencialAvisoO = numeroSequencialAvisoO;
	}

}

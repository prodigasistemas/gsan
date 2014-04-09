package gcom.gui.arrecadacao.pagamento;

import org.apache.struts.action.ActionForm;

public class PagamentosFaturasEspeciaisActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idAvisoBancario;
	private String codigoAgenteArrecadador;
	private String dataLancamentoAviso;
	private String numeroSequencialAviso;
	
	private String dataPagamento;
	private String idFormaArrecadacao;
	private String descricaoFormaArrecadacao;
//	private String tipoInclusao;
	private String tipoLeitura;			
	private String valorPagamento;
	private String idTipoDocumento;
	
//	private String idImovel;
//	private String descricaoImovel;
//						
//	private String idGuiaPagamento;
//	private String descricaoGuiaPagamento;
//	private String valorGuiaPagamento;
//	
//	private String idDebitoACobrar;
//	private String descricaoDebitoACobrar;
//	private String valorDebitoACobrar;
//				
//	private String idLocalidade;
//	private String descricaoLocalidade;
//	
//	private String referenciaConta;
//	private String descricaoReferenciaConta;
//	
//	private String idCliente;			
//	private String nomeCliente;
//	
//	private String idTipoDebito;
//	private String descricaoTipoDebito;
	
	private String codigoBarraPorLeituraOtica;			
	private String codigoBarraDigitadoCampo1;
	private String codigoBarraDigitadoDigitoVerificadorCampo1;
	private String codigoBarraDigitadoCampo2;
	private String codigoBarraDigitadoDigitoVerificadorCampo2;
	private String codigoBarraDigitadoCampo3;
	private String codigoBarraDigitadoDigitoVerificadorCampo3;
	private String codigoBarraDigitadoCampo4;
	private String codigoBarraDigitadoDigitoVerificadorCampo4;
	
	public PagamentosFaturasEspeciaisActionForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagamentosFaturasEspeciaisActionForm(String idAvisoBancario, String codigoAgenteArrecadador, String dataLancamentoAviso, String numeroSequencialAviso, String dataPagamento, String idFormaArrecadacao, String descricaoFormaArrecadacao, String tipoLeitura, String valorPagamento, String idTipoDocumento, String codigoBarraPorLeituraOtica, String codigoBarraDigitadoCampo1, String codigoBarraDigitadoDigitoVerificadorCampo1, String codigoBarraDigitadoCampo2, String codigoBarraDigitadoDigitoVerificadorCampo2, String codigoBarraDigitadoCampo3, String codigoBarraDigitadoDigitoVerificadorCampo3, String codigoBarraDigitadoCampo4, String codigoBarraDigitadoDigitoVerificadorCampo4) {
		super();
		// TODO Auto-generated constructor stub
		this.idAvisoBancario = idAvisoBancario;
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
		this.dataLancamentoAviso = dataLancamentoAviso;
		this.numeroSequencialAviso = numeroSequencialAviso;
		this.dataPagamento = dataPagamento;
		this.idFormaArrecadacao = idFormaArrecadacao;
		this.descricaoFormaArrecadacao = descricaoFormaArrecadacao;
		this.tipoLeitura = tipoLeitura;
		this.valorPagamento = valorPagamento;
		this.idTipoDocumento = idTipoDocumento;
		this.codigoBarraPorLeituraOtica = codigoBarraPorLeituraOtica;
		this.codigoBarraDigitadoCampo1 = codigoBarraDigitadoCampo1;
		this.codigoBarraDigitadoDigitoVerificadorCampo1 = codigoBarraDigitadoDigitoVerificadorCampo1;
		this.codigoBarraDigitadoCampo2 = codigoBarraDigitadoCampo2;
		this.codigoBarraDigitadoDigitoVerificadorCampo2 = codigoBarraDigitadoDigitoVerificadorCampo2;
		this.codigoBarraDigitadoCampo3 = codigoBarraDigitadoCampo3;
		this.codigoBarraDigitadoDigitoVerificadorCampo3 = codigoBarraDigitadoDigitoVerificadorCampo3;
		this.codigoBarraDigitadoCampo4 = codigoBarraDigitadoCampo4;
		this.codigoBarraDigitadoDigitoVerificadorCampo4 = codigoBarraDigitadoDigitoVerificadorCampo4;
	}

	public String getCodigoAgenteArrecadador() {
		return codigoAgenteArrecadador;
	}
	public void setCodigoAgenteArrecadador(String codigoAgenteArrecadador) {
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
	}
	public String getCodigoBarraDigitadoCampo1() {
		return codigoBarraDigitadoCampo1;
	}
	public void setCodigoBarraDigitadoCampo1(String codigoBarraDigitadoCampo1) {
		this.codigoBarraDigitadoCampo1 = codigoBarraDigitadoCampo1;
	}
	public String getCodigoBarraDigitadoCampo2() {
		return codigoBarraDigitadoCampo2;
	}
	public void setCodigoBarraDigitadoCampo2(String codigoBarraDigitadoCampo2) {
		this.codigoBarraDigitadoCampo2 = codigoBarraDigitadoCampo2;
	}
	public String getCodigoBarraDigitadoCampo3() {
		return codigoBarraDigitadoCampo3;
	}
	public void setCodigoBarraDigitadoCampo3(String codigoBarraDigitadoCampo3) {
		this.codigoBarraDigitadoCampo3 = codigoBarraDigitadoCampo3;
	}
	public String getCodigoBarraDigitadoCampo4() {
		return codigoBarraDigitadoCampo4;
	}
	public void setCodigoBarraDigitadoCampo4(String codigoBarraDigitadoCampo4) {
		this.codigoBarraDigitadoCampo4 = codigoBarraDigitadoCampo4;
	}
	public String getCodigoBarraDigitadoDigitoVerificadorCampo1() {
		return codigoBarraDigitadoDigitoVerificadorCampo1;
	}
	public void setCodigoBarraDigitadoDigitoVerificadorCampo1(
			String codigoBarraDigitadoDigitoVerificadorCampo1) {
		this.codigoBarraDigitadoDigitoVerificadorCampo1 = codigoBarraDigitadoDigitoVerificadorCampo1;
	}
	public String getCodigoBarraDigitadoDigitoVerificadorCampo2() {
		return codigoBarraDigitadoDigitoVerificadorCampo2;
	}
	public void setCodigoBarraDigitadoDigitoVerificadorCampo2(
			String codigoBarraDigitadoDigitoVerificadorCampo2) {
		this.codigoBarraDigitadoDigitoVerificadorCampo2 = codigoBarraDigitadoDigitoVerificadorCampo2;
	}
	public String getCodigoBarraDigitadoDigitoVerificadorCampo3() {
		return codigoBarraDigitadoDigitoVerificadorCampo3;
	}
	public void setCodigoBarraDigitadoDigitoVerificadorCampo3(
			String codigoBarraDigitadoDigitoVerificadorCampo3) {
		this.codigoBarraDigitadoDigitoVerificadorCampo3 = codigoBarraDigitadoDigitoVerificadorCampo3;
	}
	public String getCodigoBarraDigitadoDigitoVerificadorCampo4() {
		return codigoBarraDigitadoDigitoVerificadorCampo4;
	}
	public void setCodigoBarraDigitadoDigitoVerificadorCampo4(
			String codigoBarraDigitadoDigitoVerificadorCampo4) {
		this.codigoBarraDigitadoDigitoVerificadorCampo4 = codigoBarraDigitadoDigitoVerificadorCampo4;
	}
	public String getCodigoBarraPorLeituraOtica() {
		return codigoBarraPorLeituraOtica;
	}
	public void setCodigoBarraPorLeituraOtica(String codigoBarraPorLeituraOtica) {
		this.codigoBarraPorLeituraOtica = codigoBarraPorLeituraOtica;
	}
	public String getDataLancamentoAviso() {
		return dataLancamentoAviso;
	}
	public void setDataLancamentoAviso(String dataLancamentoAviso) {
		this.dataLancamentoAviso = dataLancamentoAviso;
	}
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getDescricaoFormaArrecadacao() {
		return descricaoFormaArrecadacao;
	}
	public void setDescricaoFormaArrecadacao(String descricaoFormaArrecadacao) {
		this.descricaoFormaArrecadacao = descricaoFormaArrecadacao;
	}
	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}
	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}
	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}
	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}
	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getNumeroSequencialAviso() {
		return numeroSequencialAviso;
	}
	public void setNumeroSequencialAviso(String numeroSequencialAviso) {
		this.numeroSequencialAviso = numeroSequencialAviso;
	}
	public String getTipoLeitura() {
		return tipoLeitura;
	}
	public void setTipoLeitura(String tipoLeitura) {
		this.tipoLeitura = tipoLeitura;
	}
	public String getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

}


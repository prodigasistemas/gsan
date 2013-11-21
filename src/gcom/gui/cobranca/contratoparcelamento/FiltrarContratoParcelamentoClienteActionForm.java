package gcom.gui.cobranca.contratoparcelamento;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * Form que será utilizado na filtragem de contratos de parcelamento
 * 
 * @author Hugo Azevedo
 * @date 13/05/2011
 */
public class FiltrarContratoParcelamentoClienteActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idContratoParcelamento;
	
	private String atualizarFiltro;
	
	private String idContratoAnterior;
	
	private String idClienteContrato;
	
	private String autocompleteCliente;
	
	private String clienteAutocompleteCNPJ;
	
	private String contratoClienteDescricaoFiltro;
	
	private String loginUsuario = "";
	
	private String nomeUsuario = "";
	
	private String periodoNegociacaoInicial;
	
	private String periodoNegociacaoFinal;
	
	private String periodoImplantacaoInicial;
	
	private String periodoImplantacaoFinal;
	
	private String situacaoPagamento;
	
	private String situacaoCancelamento;
	
	private String periodoCancelamentoInicial;
	
	private String periodoCancelamentoFinal;
	
	private String[] colecaoContratoMotivoCancelamento;
	


	public FiltrarContratoParcelamentoClienteActionForm() {
		this.situacaoPagamento = "0";
		this.situacaoCancelamento = "0";
	}

	public String getIdContratoParcelamento() {
		return idContratoParcelamento;
	}

	public void setIdContratoParcelamento(String idContratoParcelamento) {
		this.idContratoParcelamento = idContratoParcelamento;
	}

	public String getSituacaoPagamento() {
		return situacaoPagamento;
	}

	public void setSituacaoPagamento(String situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}

	public String[] getColecaoContratoMotivoCancelamento() {
		return colecaoContratoMotivoCancelamento;
	}

	public void setColecaoContratoMotivoCancelamento(
			String[] colecaoContratoMotivoCancelamento) {
		this.colecaoContratoMotivoCancelamento = colecaoContratoMotivoCancelamento;
	}

	public String getPeriodoCancelamentoFinal() {
		return periodoCancelamentoFinal;
	}

	public void setPeriodoCancelamentoFinal(String periodoCancelamentoFinal) {
		this.periodoCancelamentoFinal = periodoCancelamentoFinal;
	}

	public String getPeriodoCancelamentoInicial() {
		return periodoCancelamentoInicial;
	}

	public void setPeriodoCancelamentoInicial(String periodoCancelamentoInicial) {
		this.periodoCancelamentoInicial = periodoCancelamentoInicial;
	}

	public String getSituacaoCancelamento() {
		return situacaoCancelamento;
	}

	public void setSituacaoCancelamento(String situacaoCancelamento) {
		this.situacaoCancelamento = situacaoCancelamento;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAtualizarFiltro() {
		return atualizarFiltro;
	}

	public void setAtualizarFiltro(String atualizarFiltro) {
		this.atualizarFiltro = atualizarFiltro;
	}

	public String getContratoClienteDescricaoFiltro() {
		return contratoClienteDescricaoFiltro;
	}

	public void setContratoClienteDescricaoFiltro(
			String contratoClienteDescricaoFiltro) {
		this.contratoClienteDescricaoFiltro = contratoClienteDescricaoFiltro;
	}

	public String getIdClienteContrato() {
		return idClienteContrato;
	}

	public void setIdClienteContrato(String idClienteContrato) {
		this.idClienteContrato = idClienteContrato;
	}

	public String getIdContratoAnterior() {
		return idContratoAnterior;
	}

	public void setIdContratoAnterior(String idContratoAnterior) {
		this.idContratoAnterior = idContratoAnterior;
	}

	public String getPeriodoImplantacaoFinal() {
		return periodoImplantacaoFinal;
	}

	public void setPeriodoImplantacaoFinal(String periodoImplantacaoFinal) {
		this.periodoImplantacaoFinal = periodoImplantacaoFinal;
	}

	public String getPeriodoImplantacaoInicial() {
		return periodoImplantacaoInicial;
	}

	public void setPeriodoImplantacaoInicial(String periodoImplantacaoInicial) {
		this.periodoImplantacaoInicial = periodoImplantacaoInicial;
	}

	public String getPeriodoNegociacaoFinal() {
		return periodoNegociacaoFinal;
	}

	public void setPeriodoNegociacaoFinal(String periodoNegociacaoFinal) {
		this.periodoNegociacaoFinal = periodoNegociacaoFinal;
	}

	public String getPeriodoNegociacaoInicial() {
		return periodoNegociacaoInicial;
	}

	public void setPeriodoNegociacaoInicial(String periodoNegociacaoInicial) {
		this.periodoNegociacaoInicial = periodoNegociacaoInicial;
	}

	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		super.reset(arg0, arg1);
	}

	public String getClienteAutocomplete() {
		return autocompleteCliente;
	}

	public void setClienteAutocomplete(String clienteAutocomplete) {
		this.autocompleteCliente = clienteAutocomplete;
	}


	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getClienteAutocompleteCNPJ() {
		return clienteAutocompleteCNPJ;
	}

	public void setClienteAutocompleteCNPJ(String clienteAutocompleteCNPJ) {
		this.clienteAutocompleteCNPJ = clienteAutocompleteCNPJ;
	}
	
	
	
	

}

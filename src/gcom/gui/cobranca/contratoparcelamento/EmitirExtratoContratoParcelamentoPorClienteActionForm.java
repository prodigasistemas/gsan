package gcom.gui.cobranca.contratoparcelamento;

import org.apache.struts.action.ActionForm;

/**
 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
 * 
 * Este caso de uso permite emitir o extrato de uma ou todas as prestações 
 *  do contrato de parcelamento por cliente.
 * 
 * @author Mariana Victor
 * @since 29/07/2011
 */
public class EmitirExtratoContratoParcelamentoPorClienteActionForm  extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String numeroContrato;
	
	private String codigoCliente;
	
	private String nomeCliente;
	
	private String cnpjCliente;
	
	private String tipoRelacao;
	
	private String dataImplantacao;
	
	private String parcelaEmissao;
	
	private String inicioParcelas;
	
	private String fimParcelas;

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getDataImplantacao() {
		return dataImplantacao;
	}

	public void setDataImplantacao(String dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public String getFimParcelas() {
		return fimParcelas;
	}

	public void setFimParcelas(String fimParcelas) {
		this.fimParcelas = fimParcelas;
	}

	public String getInicioParcelas() {
		return inicioParcelas;
	}

	public void setInicioParcelas(String inicioParcelas) {
		this.inicioParcelas = inicioParcelas;
	}

	public String getParcelaEmissao() {
		return parcelaEmissao;
	}

	public void setParcelaEmissao(String parcelaEmissao) {
		this.parcelaEmissao = parcelaEmissao;
	}
	
}

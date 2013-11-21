package gcom.gui.cobranca.contratoparcelamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * Form que será utilizado na filtragem de contratos de parcelamento
 * 
 * @author Hugo Azevedo
 * @date 13/05/2011
 */
public class EmitirComprovantePagamentoContratoParcelamentoClienteActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idNumeroContrato;
	
	private String idCliente;
	
	private String nome;
	
	private String cnpj;
	
	private String nomeCNPJ;
	
	private String tipoRelacao;
	
	private String dataImplantacaoContrato;
	
	private String ocultarParcela;
	
	private String parcelaEmissao;
	
	private String inicioParcelas;
	
	public EmitirComprovantePagamentoContratoParcelamentoClienteActionForm(){
		this.ocultarParcela = "0";
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDataImplantacaoContrato() {
		return dataImplantacaoContrato;
	}

	public void setDataImplantacaoContrato(String dataImplantacaoContrato) {
		this.dataImplantacaoContrato = dataImplantacaoContrato;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdNumeroContrato() {
		return idNumeroContrato;
	}

	public void setIdNumeroContrato(String idNumeroContrato) {
		this.idNumeroContrato = idNumeroContrato;
	}

	public String getInicioParcelas() {
		return inicioParcelas;
	}

	public void setInicioParcelas(String inicioParcelas) {
		this.inicioParcelas = inicioParcelas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCNPJ() {
		return nomeCNPJ;
	}

	public void setNomeCNPJ(String nomeCNPJ) {
		this.nomeCNPJ = nomeCNPJ;
	}

	public String getOcultarParcela() {
		return ocultarParcela;
	}

	public void setOcultarParcela(String ocultarParcela) {
		this.ocultarParcela = ocultarParcela;
	}

	public String getParcelaEmissao() {
		return parcelaEmissao;
	}

	public void setParcelaEmissao(String parcelaEmissao) {
		this.parcelaEmissao = parcelaEmissao;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}
	
	
	
	
	
	
	
	
	
	

}

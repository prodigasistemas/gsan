package gcom.gui.cadastro.cliente;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action Form do [UC0474]Consultar Cliente
 * 
 * @author Rafael Santos
 * @since 11/09/2006
 */
public class ConsultarClienteActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoCliente;
	private String nomeCliente;	
	private String nomeAbreviado;
	private String dataVencimentoContas;
	private String indicaorExecucao;
	private String tipoCliente;
	private String email;
	private String cpfCliente;
	private String rgCliente;
	private String dataEmissaoRGCliente;
	private String orgaoEmissorRGCliente;
	private String dataNascimentoCliente;
	private String sexoCliente;
	private String profissaoCliente;
	private String cnpjCliente;
	private String ramoAtividadeCliente;
	private String clienteNumeroNIS;
	private String recusaSubsidio;

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
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

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getDataEmissaoRGCliente() {
		return dataEmissaoRGCliente;
	}

	public void setDataEmissaoRGCliente(String dataEmissaoRGCliente) {
		this.dataEmissaoRGCliente = dataEmissaoRGCliente;
	}

	public String getDataNascimentoCliente() {
		return dataNascimentoCliente;
	}

	public void setDataNascimentoCliente(String dataNascimentoCliente) {
		this.dataNascimentoCliente = dataNascimentoCliente;
	}

	public String getDataVencimentoContas() {
		return dataVencimentoContas;
	}

	public void setDataVencimentoContas(String dataVencimentoContas) {
		this.dataVencimentoContas = dataVencimentoContas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getOrgaoEmissorRGCliente() {
		return orgaoEmissorRGCliente;
	}

	public void setOrgaoEmissorRGCliente(String orgaoEmissorRGCliente) {
		this.orgaoEmissorRGCliente = orgaoEmissorRGCliente;
	}

	public String getProfissaoCliente() {
		return profissaoCliente;
	}

	public void setProfissaoCliente(String profissaoCliente) {
		this.profissaoCliente = profissaoCliente;
	}

	public String getRamoAtividadeCliente() {
		return ramoAtividadeCliente;
	}

	public void setRamoAtividadeCliente(String ramoAtividadeCliente) {
		this.ramoAtividadeCliente = ramoAtividadeCliente;
	}

	public String getRgCliente() {
		return rgCliente;
	}

	public void setRgCliente(String rgCliente) {
		this.rgCliente = rgCliente;
	}

	public String getSexoCliente() {
		return sexoCliente;
	}

	public void setSexoCliente(String sexoCliente) {
		this.sexoCliente = sexoCliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getIndicaorExecucao() {
		return indicaorExecucao;
	}

	public void setIndicaorExecucao(String indicaorExecucao) {
		this.indicaorExecucao = indicaorExecucao;
	}
	
	public String getClienteNumeroNIS() {
		return clienteNumeroNIS; 
	}
	
	public void setClienteNumeroNIS(String clienteNumeroNIS) {
		this.clienteNumeroNIS = clienteNumeroNIS;
	}

	public String getRecusaSubsidio() {
		return recusaSubsidio;
	}

	public void setRecusaSubsidio(String recusaSubsidio) {
		this.recusaSubsidio = recusaSubsidio;
	}

}

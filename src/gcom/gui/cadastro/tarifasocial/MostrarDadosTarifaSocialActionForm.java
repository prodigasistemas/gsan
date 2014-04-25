package gcom.gui.cadastro.tarifasocial;

import gcom.gui.ControladorInclusaoGcomActionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 *  asda
 * @author thiago
 * @created 22/12/2005
 */
public class MostrarDadosTarifaSocialActionForm extends ControladorInclusaoGcomActionForm {
	private static final long serialVersionUID = 1L;
	private String idRegistroAtualizacao = null;
	private String nomeCliente = null;
	private String complementoEndereco = null;
	private String cpf = null;
	private String rg = null;
	private String dataEmissao = null;
	private String orgaoExpedidor = null;
	private String uf = null;
	

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
        idRegistroAtualizacao = "";
    	nomeCliente = "";
    	complementoEndereco = "";
    	cpf = "";
    	rg = "";
    	dataEmissao = "";
    	orgaoExpedidor = "";
    	uf = "";
    	
    }
	public String getComplementoEndereco() {
		return complementoEndereco;
	}



	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getDataEmissao() {
		return dataEmissao;
	}



	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}



	public String getNomeCliente() {
		return nomeCliente;
	}



	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}



	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}



	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}



	public String getRg() {
		return rg;
	}



	public void setRg(String rg) {
		this.rg = rg;
	}



	public String getUf() {
		return uf;
	}



	public void setUf(String uf) {
		this.uf = uf;
	}



	public String getIdRegistroAtualizacao() {
		return idRegistroAtualizacao;
	}

	public void setIdRegistroAtualizacao(String idRegistroAtualizacao) {
		this.idRegistroAtualizacao = idRegistroAtualizacao;
	}

}

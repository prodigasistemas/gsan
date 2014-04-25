package gcom.gui.arrecadacao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class PesquisarGuiaDevolucaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoCliente;
	
	private String codigoImovel;
	
	private String inscricaoImovel;
	
	private String dataEmissaoGuiaInicio;
	
	private String dataEmissaoGuiaFim;
	
	private String dataValidadeGuiaInicio;
	
	private String dataValidadeGuiaFim;
	
	private String[] situacaoGuia;
	
	private String[] tipoCredito;
	
	private String[] tipoDocumento;
	
	private String cpfCnpj;
	
	private String nomeCliente;
	
	private String inscricao;
	
	private String situacaoAgua;
	
	private String profissao;
	
	private String ramoAtividade;
	
	private String situacaoEsgoto;
	
	private String enderecoCliente;
	
	private String clienteFone;
	
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
    	// comentado para nao interferir qdo volta para exibir a colecao do controle de paginacao
    	/*codigoCliente = null;
    	
    	codigoImovel = null;
    	
    	inscricaoImovel = null;
    	
    	dataEmissaoGuiaInicio = null;
    	
    	dataEmissaoGuiaFim = null;
    	
    	dataValidadeGuiaInicio = null;
    	
    	dataValidadeGuiaFim = null;
    	
    	situacaoGuia = null;
    	
    	tipoCredito = null;
    	
    	tipoDocumento = null;
    	
    	cpfCnpj = null;
    	
    	nomeCliente = null;
    	
    	inscricao = null;
    	
    	situacaoAgua = null;
    	
    	profissao = null;
    	
    	ramoAtividade = null;
    	
    	situacaoEsgoto = null;*/     
    }	
	
	public String getDataEmissaoGuiaFim() {
		return dataEmissaoGuiaFim;
	}
	public void setDataEmissaoGuiaFim(String dataEmissaoGuiaFim) {
		this.dataEmissaoGuiaFim = dataEmissaoGuiaFim;
	}
	public String getDataEmissaoGuiaInicio() {
		return dataEmissaoGuiaInicio;
	}
	public void setDataEmissaoGuiaInicio(String dataEmissaoGuiaInicio) {
		this.dataEmissaoGuiaInicio = dataEmissaoGuiaInicio;
	}
	public String getDataValidadeGuiaFim() {
		return dataValidadeGuiaFim;
	}
	public void setDataValidadeGuiaFim(String dataValidadeGuiaFim) {
		this.dataValidadeGuiaFim = dataValidadeGuiaFim;
	}
	public String getDataValidadeGuiaInicio() {
		return dataValidadeGuiaInicio;
	}
	public void setDataValidadeGuiaInicio(String dataValidadeGuiaInicio) {
		this.dataValidadeGuiaInicio = dataValidadeGuiaInicio;
	}
	public String[] getSituacaoGuia() {
		return situacaoGuia;
	}
	public void setSituacaoGuia(String[] situacaoGuia) {
		this.situacaoGuia = situacaoGuia;
	}
	public String[] getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String[] tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String[] getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String[] tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoImovel() {
		return codigoImovel;
	}
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getClienteFone() {
		return clienteFone;
	}

	public void setClienteFone(String clienteFone) {
		this.clienteFone = clienteFone;
	}
}

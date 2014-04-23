package gcom.gui.cadastro.tarifasocial;

import gcom.gui.ControladorInclusaoGcomActionForm;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author thiago toscano 
 * @created 27/12/2005
 */ 
public class AtualizarDadosTarifaSocialClienteActionForm extends ControladorInclusaoGcomActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovel = null;
	private String idCliente = null;
	private String nomeCliente = null;
	private String forward = null;
	private String clienteRelacaoTipo = null;
	private String dataInicioRelacao = null;
	private String dataFimRelacao = Util.formatarData(new Date(System.currentTimeMillis()));
	private String clienteImovelFimRelacaoMotivo = null;
	private String clienteNome = null;
	private String complementoEndereco = null;
	private String idImovelEconomia = null;
	private String nomeConta;

	private String[] posicaoParaRemover= null;
	
	private Collection collClienteRelacaoTipo = null;
	private Collection collClienteImovelFimRelacaoMotivo = null;


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

    	this.idImovel = "";
    	this.idCliente = "";
    	this.nomeCliente = "";
    	this.forward = "";
    	this.clienteRelacaoTipo = "";
    	this.dataInicioRelacao = "";
    	this.dataFimRelacao = Util.formatarData(new Date(System.currentTimeMillis()));
    	this.collClienteRelacaoTipo = new Vector();
    	this.collClienteImovelFimRelacaoMotivo = new Vector();
    	this.clienteImovelFimRelacaoMotivo = "";
    	this.nomeConta = "";
    	this.clienteNome = "";
    	this.complementoEndereco = "";

    }

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public Collection getCollClienteImovelFimRelacaoMotivo() {
		return collClienteImovelFimRelacaoMotivo;
	}

	public void setCollClienteImovelFimRelacaoMotivo(
			Collection collClienteImovelFimRelacaoMotivo) {
		this.collClienteImovelFimRelacaoMotivo = collClienteImovelFimRelacaoMotivo;
	}

	public String getClienteImovelFimRelacaoMotivo() {
		return clienteImovelFimRelacaoMotivo;
	}

	public void setClienteImovelFimRelacaoMotivo(
			String clienteImovelFimRelacaoMotivo) {
		this.clienteImovelFimRelacaoMotivo = clienteImovelFimRelacaoMotivo;
	}

	public String[] getPosicaoParaRemover() {
		return posicaoParaRemover;
	}

	public void setPosicaoParaRemover(String[] posicaoParaRemover) {
		this.posicaoParaRemover = posicaoParaRemover;
	}

	public String getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(String clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public String getDataFimRelacao() {
		return dataFimRelacao;
	}

	public void setDataFimRelacao(String dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}

	public String getDataInicioRelacao() {
		return dataInicioRelacao;
	}

	public void setDataInicioRelacao(String dataInicioRelacao) {
		this.dataInicioRelacao = dataInicioRelacao;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public Collection getCollClienteRelacaoTipo() {
		return collClienteRelacaoTipo;
	}

	public void setCollClienteRelacaoTipo(Collection collClienteRelacaoTipo) {
		this.collClienteRelacaoTipo = collClienteRelacaoTipo;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getIdImovelEconomia() {
		return idImovelEconomia;
	}

	public void setIdImovelEconomia(String idImovelEconomia) {
		this.idImovelEconomia = idImovelEconomia;
	}

	/**
	 * @return Retorna o campo nomeConta.
	 */
	public String getNomeConta() {
		return nomeConta;
	}

	/**
	 * @param nomeConta O nomeConta a ser setado.
	 */
	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}
    
    
    
}

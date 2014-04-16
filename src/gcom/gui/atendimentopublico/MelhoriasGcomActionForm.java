package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 15 de Fevereiro de 2007
 */
public class MelhoriasGcomActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idAssunto;

    private String loginUsuario;

    private String nomeUsuario;

    private String unidadeLotacao;

    private String telefone;

    private String email;
    
    private String matirculaImovel;
    
    private String codigoCliente;
    
    private String numeroRA;
    
    private String numeroOS;
    
    private String detalhamento;
    
    private String indicadorSenhaPendente;

	public String getIndicadorSenhaPendente() {
		return indicadorSenhaPendente;
	}

	public void setIndicadorSenhaPendente(String indicadorSenhaPendente) {
		this.indicadorSenhaPendente = indicadorSenhaPendente;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdAssunto() {
		return idAssunto;
	}

	public void setIdAssunto(String idAssunto) {
		this.idAssunto = idAssunto;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getMatirculaImovel() {
		return matirculaImovel;
	}

	public void setMatirculaImovel(String matirculaImovel) {
		this.matirculaImovel = matirculaImovel;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUnidadeLotacao() {
		return unidadeLotacao;
	}

	public void setUnidadeLotacao(String unidadeLotacao) {
		this.unidadeLotacao = unidadeLotacao;
	}
    
    

   
}

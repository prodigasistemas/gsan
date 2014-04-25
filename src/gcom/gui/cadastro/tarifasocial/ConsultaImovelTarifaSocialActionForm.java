package gcom.gui.cadastro.tarifasocial;

import java.util.Collection;
import java.util.Vector;

import gcom.gui.ControladorConsultaGcomActionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * Controlador ActionForm 
 * 
 * @author thiago
 * @created 20/12/2005
 */ 
public class ConsultaImovelTarifaSocialActionForm extends ControladorConsultaGcomActionForm {
	private static final long serialVersionUID = 1L;
	
    private String idLocalidade = "";
	private String localidadeDescricao = "";
    private String idSetorComercial = "";
    private String setorComercialDescricao = "";
    private String idQuadra = "";
    private String lote = "";
    private String subLote = "";
    private String codigoCliente = "";
    private String idMunicipio = "";
    private String cep = "";
    private String idBairro = "";
    private String logradouro = "";
    private String indicadorUso = "";
    private String matricula = "";    
    private String quadraDescricao = "";
    private String nomeCliente = "";
    private String bairro = "";
    private String municipio = "";
    
    private String idCliente = "";

    private String[] idRegistrosRemocao = new String[0];
	private String tarifaSocialExclusaoMotivo = "";
	private Collection collTarifaSocialExclusaoMotivo = new Vector();
	
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        this.idLocalidade = "";
        this.idSetorComercial = "";
        this.idQuadra = "";
        this.lote = "";
        this.subLote = "";
        this.codigoCliente = "";
        this.idMunicipio = "";
        this.cep = "";
        this.idBairro = "";
        this.logradouro = "";
        this.indicadorUso = "";
        this.matricula = "";
        this.localidadeDescricao = "";
        this.quadraDescricao = "";
        this.nomeCliente = "";
        this.bairro = "";
        this.municipio = "";
        
        this.idCliente="";
        
        this.idRegistrosRemocao = new String[0];
        
        this.tarifaSocialExclusaoMotivo = "";
    	this.collTarifaSocialExclusaoMotivo = new Vector();
        
    }

	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

	public Collection getCollTarifaSocialExclusaoMotivo() {
		return collTarifaSocialExclusaoMotivo;
	}

	public void setCollTarifaSocialExclusaoMotivo(
			Collection collTarifaSocialExclusaoMotivo) {
		this.collTarifaSocialExclusaoMotivo = collTarifaSocialExclusaoMotivo;
	}

	public String getTarifaSocialExclusaoMotivo() {
		return tarifaSocialExclusaoMotivo;
	}

	public void setTarifaSocialExclusaoMotivo(String tarifaSocialExclusaoMotivo) {
		this.tarifaSocialExclusaoMotivo = tarifaSocialExclusaoMotivo;
	}
	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getQuadraDescricao() {
		return quadraDescricao;
	}

	public void setQuadraDescricao(String quadraDescricao) {
		this.quadraDescricao = quadraDescricao;
	}

	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSubLote() {
		return subLote;
	}

	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}
    
    
}

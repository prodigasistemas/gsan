package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InserirLocalidadeActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String classeID;

    private String eloID;

    private String email;

    private String fax;

    //private String gerenciaID;
    private String idUnidadeNegocio;

    private String indicadorUso;

    private String localidadeID;

    private String localidadeNome;

    private String menorConsumo;

    private String porteID;

    private String ramal;

    private String telefone;

    private String icms;

    private String centroCusto;
    
    private String informatizada;
    
    private String gerenteLocalidade;
        
    private String nomeGerente;
    
    private String hidrometroLocalArmazenagem;

    private String[] localidadeSelectID;
    
    private String sede;
    
    private String centroCustoEsgoto;

    private String municipio;
    
    private String descricaoMunicipio;
    
    public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getClasseID() {
        return classeID;
    }

    public void setClasseID(String classeID) {
        this.classeID = classeID;
    }

    public String getEloID() {
        return eloID;
    }

    public void setEloID(String eloID) {
        this.eloID = eloID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getIndicadorUso() {
        return indicadorUso;
    }

    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public String getLocalidadeID() {
        return localidadeID;
    }

    public void setLocalidadeID(String localidadeID) {
        this.localidadeID = localidadeID;
    }

    public String getLocalidadeNome() {
        return localidadeNome;
    }

    public void setLocalidadeNome(String localidadeNome) {
        this.localidadeNome = localidadeNome;
    }

    public String getMenorConsumo() {
        return menorConsumo;
    }

    public void setMenorConsumo(String menorConsumo) {
        this.menorConsumo = menorConsumo;
    }

    public String getPorteID() {
        return porteID;
    }

    public void setPorteID(String porteID) {
        this.porteID = porteID;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

    public String[] getLocalidadeSelectID() {
        return localidadeSelectID;
    }

    public void setLocalidadeSelectID(String[] localidadeSelectID) {
        this.localidadeSelectID = localidadeSelectID;
    }

	/**
	 * @return Retorna o campo centroCusto.
	 */
	public String getCentroCusto() {
		return centroCusto;
	}

	/**
	 * @param centroCusto O centroCusto a ser setado.
	 */
	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	/**
	 * @return Retorna o campo icms.
	 */
	public String getIcms() {
		return icms;
	}

	/**
	 * @param icms O icms a ser setado.
	 */
	public void setIcms(String icms) {
		this.icms = icms;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getGerenteLocalidade() {
		return gerenteLocalidade;
	}

	public void setGerenteLocalidade(String gerenteLocalidade) {
		this.gerenteLocalidade = gerenteLocalidade;
	}

	public String getInformatizada() {
		return informatizada;
	}

	public void setInformatizada(String informatizada) {
		this.informatizada = informatizada;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public String getHidrometroLocalArmazenagem() {
		return hidrometroLocalArmazenagem;
	}

	public void setHidrometroLocalArmazenagem(String hidrometroLocalArmazenagem) {
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
	}

	public String getCentroCustoEsgoto() {
		return centroCustoEsgoto;
	}

	public void setCentroCustoEsgoto(String centroCustoEsgoto) {
		this.centroCustoEsgoto = centroCustoEsgoto;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}
}

package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/***
 * @author Administrador, Ivan Sergio, Anderson Italo
 * @date 16/02/2009
 * @alteracao 16/02/2009 - CRC1178 - Adicionado o Indicador de Incremento do Lote
 * @alteracao 30/09/2009 - Adicionado o Indicador de Bloqueio para Alteração de Imóveis
 */
public class AtualizarQuadraActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String baciaID;

    private String distritoOperacionalDescricao;

    private String distritoOperacionalID;

    private String indicadorRedeAguaAux;

    private String indicadorRedeEsgotoAux;

    private String localidadeID;

    private String localidadeNome;

    private String perfilQuadra;

    private String quadraNM;

    private String quadraID;

    private String rotaID;
    
    private String codigoRota;

    private String rotaMensagem;

    private String setorCensitarioDescricao;

    private String setorCensitarioID;

    private String setorComercialCD;

    private String setorComercialID;

    private String setorComercialNome;

    private String sistemaEsgotoID;

    private String zeisID;

    private String indicadorUso;

    private String areaTipoID;
    
    private String indicadorIncrementoLote;
    
    private String indicadorBloqueio;
    
    private String bairroID;

    private String bairroDescricao;
    
    private String municipioID;
    
    private String indicadorRelacionamentoQuadraBairro;

	public String getAreaTipoID() {
		return areaTipoID;
	}

	public void setAreaTipoID(String areaTipoID) {
		this.areaTipoID = areaTipoID;
	}

	public String getBaciaID() {
        return baciaID;
    }

    public void setBaciaID(String baciaID) {
        this.baciaID = baciaID;
    }

    public String getDistritoOperacionalDescricao() {
        return distritoOperacionalDescricao;
    }

    public void setDistritoOperacionalDescricao(
            String distritoOperacionalDescricao) {
        this.distritoOperacionalDescricao = distritoOperacionalDescricao;
    }

    public String getDistritoOperacionalID() {
        return distritoOperacionalID;
    }

    public void setDistritoOperacionalID(String distritoOperacionalID) {
        this.distritoOperacionalID = distritoOperacionalID;
    }

    public String getIndicadorRedeAguaAux() {
        return indicadorRedeAguaAux;
    }

    public void setIndicadorRedeAguaAux(String indicadorRedeAguaAux) {
        this.indicadorRedeAguaAux = indicadorRedeAguaAux;
    }

    public String getIndicadorRedeEsgotoAux() {
        return indicadorRedeEsgotoAux;
    }

    public void setIndicadorRedeEsgotoAux(String indicadorRedeEsgotoAux) {
        this.indicadorRedeEsgotoAux = indicadorRedeEsgotoAux;
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
   
    public String getPerfilQuadra() {
        return perfilQuadra;
    }

    public void setPerfilQuadra(String perfilQuadra) {
        this.perfilQuadra = perfilQuadra;
    }

    public String getQuadraNM() {
        return quadraNM;
    }

    public void setQuadraNM(String quadraNM) {
        this.quadraNM = quadraNM;
    }

    public String getRotaID() {
        return rotaID;
    }

    public void setRotaID(String rotaID) {
        this.rotaID = rotaID;
    }

    public String getRotaMensagem() {
        return rotaMensagem;
    }

    public void setRotaMensagem(String rotaMensagem) {
        this.rotaMensagem = rotaMensagem;
    }

    public String getSetorCensitarioDescricao() {
        return setorCensitarioDescricao;
    }

    public void setSetorCensitarioDescricao(String setorCensitarioDescricao) {
        this.setorCensitarioDescricao = setorCensitarioDescricao;
    }

    public String getSetorCensitarioID() {
        return setorCensitarioID;
    }

    public void setSetorCensitarioID(String setorCensitarioID) {
        this.setorCensitarioID = setorCensitarioID;
    }

    public String getSetorComercialCD() {
        return setorComercialCD;
    }

    public void setSetorComercialCD(String setorComercialCD) {
        this.setorComercialCD = setorComercialCD;
    }

    public String getSetorComercialID() {
        return setorComercialID;
    }

    public void setSetorComercialID(String setorComercialID) {
        this.setorComercialID = setorComercialID;
    }

    public String getSetorComercialNome() {
        return setorComercialNome;
    }

    public void setSetorComercialNome(String setorComercialNome) {
        this.setorComercialNome = setorComercialNome;
    }

    public String getSistemaEsgotoID() {
        return sistemaEsgotoID;
    }

    public void setSistemaEsgotoID(String sistemaEsgotoID) {
        this.sistemaEsgotoID = sistemaEsgotoID;
    }

    public String getZeisID() {
        return zeisID;
    }

    public void setZeisID(String zeisID) {
        this.zeisID = zeisID;
    }

    public String getIndicadorUso() {
        return indicadorUso;
    }

    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

    public String getQuadraID() {
        return quadraID;
    }

    public void setQuadraID(String quadraID) {
        this.quadraID = quadraID;
    }

	/**
	 * @return Retorna o campo codigoRota.
	 */
	public String getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Retorna o campo indicadorIncrementoLote.
	 */
	public String getIndicadorIncrementoLote() {
		return indicadorIncrementoLote;
	}

	/**
	 * @param indicadorIncrementoLote O indicadorIncrementoLote a ser setado.
	 */
	public void setIndicadorIncrementoLote(String indicadorIncrementoLote) {
		this.indicadorIncrementoLote = indicadorIncrementoLote;
	}
	
	public String getIndicadorBloqueio() {
		return indicadorBloqueio;
	}

	public void setIndicadorBloqueio(String indicadorBloqueio) {
		this.indicadorBloqueio = indicadorBloqueio;
	}

	public String getBairroDescricao() {
		return bairroDescricao;
	}

	public void setBairroDescricao(String bairroDescricao) {
		this.bairroDescricao = bairroDescricao;
	}

	public String getBairroID() {
		return bairroID;
	}

	public void setBairroID(String bairroID) {
		this.bairroID = bairroID;
	}

	public String getIndicadorRelacionamentoQuadraBairro() {
		return indicadorRelacionamentoQuadraBairro;
	}

	public void setIndicadorRelacionamentoQuadraBairro(
			String indicadorRelacionamentoQuadraBairro) {
		this.indicadorRelacionamentoQuadraBairro = indicadorRelacionamentoQuadraBairro;
	}

	public String getMunicipioID() {
		return municipioID;
	}

	public void setMunicipioID(String municipioID) {
		this.municipioID = municipioID;
	}

}

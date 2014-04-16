package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PesquisarAtualizarSetorComercialActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String indicadorUso;

    private String localidadeID;
    private String localidadeNome;

    private String municipioID;
    private String municipioNome;

    private String setorComercialCD;
    private String setorComercialID;
    private String setorComercialNome;
    
    private String tipoPesquisaDescricao;
    private String tipoPesquisaMunicipio;
    
    private String fonteCaptacao;
    private String descricaoFonteCaptacao;
    
    private String indicadorSetorAlternativo;
    
    private String indicadorBloqueio;


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

    public String getMunicipioID() {
        return municipioID;
    }

    public void setMunicipioID(String municipioID) {
        this.municipioID = municipioID;
    }

    public String getMunicipioNome() {
        return municipioNome;
    }

    public void setMunicipioNome(String municipioNome) {
        this.municipioNome = municipioNome;
    }

    public String getSetorComercialCD() {
        return setorComercialCD;
    }

    public void setSetorComercialCD(String setorComercialCD) {
        this.setorComercialCD = setorComercialCD;
    }

    public String getSetorComercialNome() {
        return setorComercialNome;
    }

    public void setSetorComercialNome(String setorComercialNome) {
        this.setorComercialNome = setorComercialNome;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

    public String getSetorComercialID() {
        return setorComercialID;
    }

    public void setSetorComercialID(String setorComercialID) {
        this.setorComercialID = setorComercialID;
    }

	/**
	 * @return Retorna o campo tipoPesquisaDescricao.
	 */
	public String getTipoPesquisaDescricao() {
		return tipoPesquisaDescricao;
	}

	/**
	 * @param tipoPesquisaDescricao O tipoPesquisaDescricao a ser setado.
	 */
	public void setTipoPesquisaDescricao(String tipoPesquisaDescricao) {
		this.tipoPesquisaDescricao = tipoPesquisaDescricao;
	}

	/**
	 * @return Retorna o campo tipoPesquisaMunicipio.
	 */
	public String getTipoPesquisaMunicipio() {
		return tipoPesquisaMunicipio;
	}

	/**
	 * @param tipoPesquisaMunicipio O tipoPesquisaMunicipio a ser setado.
	 */
	public void setTipoPesquisaMunicipio(String tipoPesquisaMunicipio) {
		this.tipoPesquisaMunicipio = tipoPesquisaMunicipio;
	}

	public String getDescricaoFonteCaptacao() {
		return descricaoFonteCaptacao;
	}

	public void setDescricaoFonteCaptacao(String descricaoFonteCaptacao) {
		this.descricaoFonteCaptacao = descricaoFonteCaptacao;
	}

	public String getFonteCaptacao() {
		return fonteCaptacao;
	}

	public void setFonteCaptacao(String fonteCaptacao) {
		this.fonteCaptacao = fonteCaptacao;
	}

	public String getIndicadorSetorAlternativo() {
		return indicadorSetorAlternativo;
	}

	public void setIndicadorSetorAlternativo(String indicadorSetorAlternativo) {
		this.indicadorSetorAlternativo = indicadorSetorAlternativo;
	}
	
	public String getIndicadorBloqueio() {
		return indicadorBloqueio;
	}

	public void setIndicadorBloqueio(String indicadorBloqueio) {
		this.indicadorBloqueio = indicadorBloqueio;
	}
	
	
}

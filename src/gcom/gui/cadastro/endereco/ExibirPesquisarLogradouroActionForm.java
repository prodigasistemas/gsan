package gcom.gui.cadastro.endereco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ExibirPesquisarLogradouroActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoMunicipio;

    private String nome;

    private String nomeMunicipio;

    private String tipoLogradouro;

    private String titulo;
    
    private String nomePopular;
    
    private String cep;
    
    private String descricaoCep;
    
    private String codigoBairro;
    
    private String nomeBairro;
    
    private String tipoPesquisa;
    
    private String tipoPesquisaPopular;

    public String getCodigoBairro() {
		return codigoBairro;
	}

	public void setCodigoBairro(String codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getDescricaoCep() {
		return descricaoCep;
	}

	public void setDescricaoCep(String descricaoCep) {
		this.descricaoCep = descricaoCep;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getTipoPesquisaPopular() {
		return tipoPesquisaPopular;
	}

	public void setTipoPesquisaPopular(String tipoPesquisaPopular) {
		this.tipoPesquisaPopular = tipoPesquisaPopular;
	}

	/**
	 * @return Retorna o campo tipoLogradouro.
	 */
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	/**
	 * @param tipoLogradouro O tipoLogradouro a ser setado.
	 */
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
}

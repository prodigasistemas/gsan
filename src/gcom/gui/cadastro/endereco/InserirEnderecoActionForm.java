package gcom.gui.cadastro.endereco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InserirEnderecoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String bairro;

    private String cep;
    
    private String cepDescricao;

    private String cepUnico;
    
    // Usado para exibir a mensagem no java script
    // Fica em um hidden no jsp endereco_inserir_novo
    private String codigoCepUnico;

    private String complemento;

    private String enderecoReferencia;

    private String flagReload;

    private String logradouro;

    private String logradouroDescricao;

    private String numero;

    private String tipo;
    
    private String cepSelecionado;
    
    private String associacaoExistente;
    
    private String tipoAcao;
    
    private String objetoClienteEnderecoSelecionado;
    
    private String enderecoCorrespondencia;
    
    private String flagCepSelecionado;
    
    private String idPerimetroInicial;
    
    private String descricaoPerimetroInicial;
    
    private String idPerimetroFinal;
    
    private String descricaoPerimetroFinal;
    
    private boolean mostrarPerimetro;
    
    private String tipoPesquisaTela;
    
    /**
	 * @return Retorna o campo mostrarPerimetro.
	 */
	public boolean getMostrarPerimetro() {
		return mostrarPerimetro;
	}

	/**
	 * @param mostrarPerimetro O mostrarPerimetro a ser setado.
	 */
	public void setMostrarPerimetro(boolean mostrarPerimetro) {
		this.mostrarPerimetro = mostrarPerimetro;
	}

	/**
	 * @return Retorna o campo idPerimetroFinal.
	 */
	public String getIdPerimetroFinal() {
		return idPerimetroFinal;
	}

	/**
	 * @param idPerimetroFinal O idPerimetroFinal a ser setado.
	 */
	public void setIdPerimetroFinal(String idPerimetroFinal) {
		this.idPerimetroFinal = idPerimetroFinal;
	}

	/**
	 * @return Retorna o campo idPerimetroInicial.
	 */
	public String getIdPerimetroInicial() {
		return idPerimetroInicial;
	}

	/**
	 * @param idPerimetroInicial O idPerimetroInicial a ser setado.
	 */
	public void setIdPerimetroInicial(String idPerimetroInicial) {
		this.idPerimetroInicial = idPerimetroInicial;
	}

	public String getEnderecoCorrespondencia() {
		return enderecoCorrespondencia;
	}

	public void setEnderecoCorrespondencia(String enderecoCorrespondencia) {
		this.enderecoCorrespondencia = enderecoCorrespondencia;
	}

	public String getFlagCepSelecionado() {
		return flagCepSelecionado;
	}

	public void setFlagCepSelecionado(String flagCepSelecionado) {
		this.flagCepSelecionado = flagCepSelecionado;
	}

	public String getObjetoClienteEnderecoSelecionado() {
		return objetoClienteEnderecoSelecionado;
	}

	public void setObjetoClienteEnderecoSelecionado(
			String objetoClienteEnderecoSelecionado) {
		this.objetoClienteEnderecoSelecionado = objetoClienteEnderecoSelecionado;
	}

	public String getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(String tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public String getAssociacaoExistente() {
		return associacaoExistente;
	}

	public void setAssociacaoExistente(String associacaoExistente) {
		this.associacaoExistente = associacaoExistente;
	}

	public String getCepSelecionado() {
		return cepSelecionado;
	}

	public void setCepSelecionado(String cepSelecionado) {
		this.cepSelecionado = cepSelecionado;
	}

	public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCepUnico() {
        return cepUnico;
    }

    public void setCepUnico(String cepUnico) {
        this.cepUnico = cepUnico;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEnderecoReferencia() {
        return enderecoReferencia;
    }

    public void setEnderecoReferencia(String enderecoReferencia) {
        this.enderecoReferencia = enderecoReferencia;
    }

    public String getFlagReload() {
        return flagReload;
    }

    public void setFlagReload(String flagReload) {
        this.flagReload = flagReload;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLogradouroDescricao() {
        return logradouroDescricao;
    }

    public void setLogradouroDescricao(String logradouroDescricao) {
        this.logradouroDescricao = logradouroDescricao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

	public String getCepDescricao() {
		return cepDescricao;
	}

	public void setCepDescricao(String cepDescricao) {
		this.cepDescricao = cepDescricao;
	}

	/**
	 * @return Retorna o campo codigoCepUnico.
	 */
	public String getCodigoCepUnico() {
		return codigoCepUnico;
	}

	/**
	 * @param codigoCepUnico O codigoCepUnico a ser setado.
	 */
	public void setCodigoCepUnico(String codigoCepUnico) {
		this.codigoCepUnico = codigoCepUnico;
	}

	/**
	 * @return Retorna o campo descricaoPerimetroFinal.
	 */
	public String getDescricaoPerimetroFinal() {
		return descricaoPerimetroFinal;
	}

	/**
	 * @param descricaoPerimetroFinal O descricaoPerimetroFinal a ser setado.
	 */
	public void setDescricaoPerimetroFinal(String descricaoPerimetroFinal) {
		this.descricaoPerimetroFinal = descricaoPerimetroFinal;
	}

	/**
	 * @return Retorna o campo descricaoPerimetroInicial.
	 */
	public String getDescricaoPerimetroInicial() {
		return descricaoPerimetroInicial;
	}

	/**
	 * @param descricaoPerimetroInicial O descricaoPerimetroInicial a ser setado.
	 */
	public void setDescricaoPerimetroInicial(String descricaoPerimetroInicial) {
		this.descricaoPerimetroInicial = descricaoPerimetroInicial;
	}

	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public String getTipoPesquisaTela() {
		return tipoPesquisaTela;
	}

	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisaTela(String tipoPesquisaTela) {
		this.tipoPesquisaTela = tipoPesquisaTela;
	}
}

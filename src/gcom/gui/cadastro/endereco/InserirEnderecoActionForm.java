/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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
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
package gcom.cadastro.cliente;

import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;

import java.io.Serializable;
import java.util.Date;

/**
 * bean simplificado responsável para trazer só o necessário
 * 
 * @author Sávio Luiz
 * @created 17 de Maio de 2004
 */

public class ClienteImovelSimplificado implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * persistent field
     */
    private gcom.cadastro.imovel.Imovel imovel;

    /**
     * persistent field
     */
    private gcom.cadastro.cliente.Cliente cliente;

    /**
     * persistent field
     */   
    private Date dataFimRelacao;

    /**
     * Constructor for the ClienteImovelSimplificado object
     * 
     * @param idImovel
     *            Description of the Parameter
     * @param numeroImovel
     *            Description of the Parameter
     * @param cep
     *            Description of the Parameter
     * @param logradouro
     *            Description of the Parameter
     * @param nomeCliente
     *            Description of the Parameter
     */
	public ClienteImovelSimplificado(Integer idImovel, String numeroImovel, LogradouroCep logradouroCep, 
			                         LogradouroBairro logradouroBairro, Quadra quadra, EnderecoReferencia enderecoReferencia, 
			                         String complementoEndereco, String nomeCliente, Integer idCliente, 
			                         SetorComercial setorComercial,Localidade localidade, Date dataFimRelacao) {
		this.imovel = new Imovel(idImovel, numeroImovel, logradouroCep, 
				                 logradouroBairro, quadra, enderecoReferencia, 
				                 complementoEndereco, setorComercial, localidade);
		this.cliente = new Cliente(nomeCliente, idCliente);
		this.dataFimRelacao = dataFimRelacao; 
	}

    /**
     * Constructor for the ClienteImovelSimplificado object
     * 
     * @param idImovel
     *            Description of the Parameter
     * @param numeroImovel
     *            Description of the Parameter
     * @param cep
     *            Description of the Parameter
     * @param logradouro
     *            Description of the Parameter
     * @param nomeCliente
     *            Description of the Parameter
     */
	public ClienteImovelSimplificado(Integer idImovel, String numeroImovel,
			LogradouroCep logradouroCep, LogradouroBairro logradouroBairro, Quadra quadra,
			EnderecoReferencia enderecoReferencia, String complementoEndereco,
			String nomeCliente, Integer idCliente, SetorComercial setorComercial,Localidade localidade, Date dataFimRelacao, Date ultimaAlteracao) {
		this.imovel = new Imovel(idImovel, numeroImovel, logradouroCep, logradouroBairro, quadra,
				enderecoReferencia, complementoEndereco, setorComercial,localidade,ultimaAlteracao);
		this.cliente = new Cliente(nomeCliente, idCliente);
		this.dataFimRelacao = dataFimRelacao; 
	}	
	
	/**
     * Gets the cliente attribute of the ClienteImovelSimplificado object
     * 
     * @return The cliente value
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the cliente attribute of the ClienteImovelSimplificado object
     * 
     * @param cliente
     *            The new cliente value
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Gets the imovel attribute of the ClienteImovelSimplificado object
     * 
     * @return The imovel value
     */
    public Imovel getImovel() {
        return imovel;
    }

    /**
     * Sets the imovel attribute of the ClienteImovelSimplificado object
     * 
     * @param imovel
     *            The new imovel value
     */
    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }
    
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ClienteImovelSimplificado)) {
            return false;
        }
        ClienteImovelSimplificado castOther = (ClienteImovelSimplificado) other;

        return (this.getImovel().getId().equals(castOther.getImovel().getId()));
    }

	public Date getDataFimRelacao() {
		return dataFimRelacao;
	}

	public void setDataFimRelacao(Date dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}


}

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
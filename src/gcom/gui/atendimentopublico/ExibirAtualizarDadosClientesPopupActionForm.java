/**
 * 
 */
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
package gcom.gui.atendimentopublico;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;

import java.util.Collection;
import java.util.HashMap;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Daniel Alves
 * @date 26/07/2010
 */
public class ExibirAtualizarDadosClientesPopupActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String matricula;
	
	private String inscricao;

	//private String nomeConta;
	
	private String endereco;
	
	//codigo do clienteImovel a ser atualizado
	private String codClienteImovel;
	
	//codigo do clienteImovel usuário
	private String codClienteImovelUsuario;
	
	//ClienteModificado
	private String idCliente;	
    
	private String novoNomeCliente;
	
	private String tipoPessoa;
	
	private String cpfCnpjCliente;
	
	// Categorias, Subcategorias e Economias
	private Collection categoriaSubcategoriaEconomia;
	
	private String totalEconomias;

	// Cliente
	private Collection<ClienteImovel> colecaoClienteImovel;

	private Collection<ClienteFone> clienteFone;
	
	private Collection<ClienteFone> removerClienteFone;
	
	private HashMap telefonePrincipal;
	
	private String fecharPopup;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	/*public String getNomeConta() {
		return nomeConta;
	}
	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}*/
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Collection getCategoriaSubcategoriaEconomia() {
		return categoriaSubcategoriaEconomia;
	}
	public void setCategoriaSubcategoriaEconomia(
			Collection categoriaSubcategoriaEconomia) {
		this.categoriaSubcategoriaEconomia = categoriaSubcategoriaEconomia;
	}		
	public Collection<ClienteImovel> getColecaoClienteImovel() {
		return colecaoClienteImovel;
	}
	public void setColecaoClienteImovel(
			Collection<ClienteImovel> colecaoClienteImovel) {
		this.colecaoClienteImovel = colecaoClienteImovel;
	}
	public String getNovoNomeCliente() {
		return novoNomeCliente;
	}
	public void setNovoNomeCliente(String novoNomeCliente) {
		this.novoNomeCliente = novoNomeCliente;
	}
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public Collection<ClienteFone> getClienteFone() {
		return clienteFone;
	}
	public void setClienteFone(Collection<ClienteFone> clienteFone) {
		this.clienteFone = clienteFone;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getTotalEconomias() {
		return totalEconomias;
	}
	public void setTotalEconomias(String totalEconomias) {
		this.totalEconomias = totalEconomias;
	}
	public Collection<ClienteFone> getRemoverClienteFone() {
		return removerClienteFone;
	}
	public void setRemoverClienteFone(Collection<ClienteFone> removerClienteFone) {
		this.removerClienteFone = removerClienteFone;
	}
	public String getCodClienteImovel() {
		return codClienteImovel;
	}
	public void setCodClienteImovel(String codClienteImovel) {
		this.codClienteImovel = codClienteImovel;
	}
	public String getFecharPopup() {
		return fecharPopup;
	}
	public void setFecharPopup(String fecharPopup) {
		this.fecharPopup = fecharPopup;
	}
	public String getCodClienteImovelUsuario() {
		return codClienteImovelUsuario;
	}
	public void setCodClienteImovelUsuario(String codClienteImovelUsuario) {
		this.codClienteImovelUsuario = codClienteImovelUsuario;
	}
	public HashMap getTelefonePrincipal() {
		return telefonePrincipal;
	}
	public void setTelefonePrincipal(HashMap telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}
	
}
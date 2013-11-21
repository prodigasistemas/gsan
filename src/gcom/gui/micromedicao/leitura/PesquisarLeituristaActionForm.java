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
package gcom.gui.micromedicao.leitura;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Form utilizado na pesquisa de contas de um imóvel 
 *
 * @author Francisco Nascimento 
 * @date 03/08/2007
 */
public class PesquisarLeituristaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String empresa;
	private String idFuncionario;
	private String nomeFuncionario;
	private String idCliente;
	private String nomeCliente;
	private String DDDTelefone;
	private String numeroTelefone;
	/**
	 * @return Returns the dDDTelefone.
	 */
	public String getDDDTelefone() {
		return DDDTelefone;
	}
	/**
	 * @param telefone The dDDTelefone to set.
	 */
	public void setDDDTelefone(String telefone) {
		DDDTelefone = telefone;
	}
	/**
	 * @return Returns the empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa The empresa to set.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return Returns the idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente The idCliente to set.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	/**
	 * @return Returns the idFuncionario.
	 */
	public String getIdFuncionario() {
		return idFuncionario;
	}
	/**
	 * @param idFuncionario The idFuncionario to set.
	 */
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Returns the nomeFuncionario.
	 */
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	/**
	 * @param nomeFuncionario The nomeFuncionario to set.
	 */
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	/**
	 * @return Returns the numeroTelefone.
	 */
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	/**
	 * @param numeroTelefone The numeroTelefone to set.
	 */
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}


}

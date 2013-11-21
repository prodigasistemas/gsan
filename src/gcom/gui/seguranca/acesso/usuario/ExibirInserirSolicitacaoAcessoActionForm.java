/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.seguranca.acesso.usuario;


import org.apache.struts.action.ActionForm;

/**
 * [UC1092] Inserir Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 03/11/2010
 */

public class ExibirInserirSolicitacaoAcessoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idFuncionario; 
	private String nomeFuncionario;
	private String idFuncionarioSolicitante; //
	private String nomeFuncionarioSolicitante;
	private String idFuncionarioSuperior; //
	private String nomeFuncionarioSuperior;
	private String[] idsGrupo; //
	private String idTipoUsuario;
	private String idEmpresa;
	private String nomeUsuario; //
	private String cpf; //
	private String dataNascimento; //
	private String idLotacao; //
	private String nomeLotacao;
	private String login; //
	private String email; //
	private String dataInicial; //
	private String dataFinal; //
	private String icNotificar; //

	public void reset(){
		
		this.idFuncionario = null;
		this.nomeFuncionario = null;
		this.idFuncionarioSolicitante = null;
		this.nomeFuncionarioSolicitante = null;
		this.idFuncionarioSuperior = null;
		this.nomeFuncionarioSuperior = null;
		this.idsGrupo = null;
		this.idTipoUsuario = null;
		this.idEmpresa = null;
		this.nomeUsuario = null;
		this.cpf = null;
		this.dataNascimento = null;
		this.idLotacao = null;
		this.nomeLotacao = null;
		this.login = null;
		this.email = null;
		this.dataInicial = null;
		this.dataFinal = null;
		this.icNotificar = null;
		
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getIdFuncionarioSolicitante() {
		return idFuncionarioSolicitante;
	}

	public void setIdFuncionarioSolicitante(String idFuncionarioSolicitante) {
		this.idFuncionarioSolicitante = idFuncionarioSolicitante;
	}

	public String getNomeFuncionarioSolicitante() {
		return nomeFuncionarioSolicitante;
	}

	public void setNomeFuncionarioSolicitante(String nomeFuncionarioSolicitante) {
		this.nomeFuncionarioSolicitante = nomeFuncionarioSolicitante;
	}

	public String getIdFuncionarioSuperior() {
		return idFuncionarioSuperior;
	}

	public void setIdFuncionarioSuperior(String idFuncionarioSuperior) {
		this.idFuncionarioSuperior = idFuncionarioSuperior;
	}

	public String getNomeFuncionarioSuperior() {
		return nomeFuncionarioSuperior;
	}

	public void setNomeFuncionarioSuperior(String nomeFuncionarioSuperior) {
		this.nomeFuncionarioSuperior = nomeFuncionarioSuperior;
	}

	public String[] getIdsGrupo() {
		return idsGrupo;
	}

	public void setIdsGrupo(String[] idsGrupo) {
		this.idsGrupo = idsGrupo;
	}

	public String getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(String idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdLotacao() {
		return idLotacao;
	}

	public void setIdLotacao(String idLotacao) {
		this.idLotacao = idLotacao;
	}

	public String getNomeLotacao() {
		return nomeLotacao;
	}

	public void setNomeLotacao(String nomeLotacao) {
		this.nomeLotacao = nomeLotacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getIcNotificar() {
		return icNotificar;
	}

	public void setIcNotificar(String icNotificar) {
		this.icNotificar = icNotificar;
	}
	
}
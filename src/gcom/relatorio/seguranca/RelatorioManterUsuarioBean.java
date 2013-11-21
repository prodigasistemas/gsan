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
package gcom.relatorio.seguranca;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Arthur Carvalho
 * @created 09/04/2008
 */
public class RelatorioManterUsuarioBean implements RelatorioBean {

	private String nome;

	private String tipo;

	private String unidadeOrganizacional;

	private String situacao;
	
	private String abrangenciaAcesso;

	private String login;

	private String dataCadastroFim;

	private String grupo;

	/**
	 * Construtor da classe RelatorioManterUsuarioBean
	 */
	public RelatorioManterUsuarioBean(String nome, String tipo,
			String unidadeOrganizacional, String situacao,
			String abrangenciaAcesso, String login,
			String dataCadastroFim, String grupo) {
		this.nome = nome;
		this.tipo = tipo;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.situacao = situacao;
		this.abrangenciaAcesso = abrangenciaAcesso;
		this.login = login;
		this.dataCadastroFim = dataCadastroFim;
		this.grupo = grupo;
	}

	/**
	 * @return Retorna o campo abrangenciaAcesso.
	 */
	public String getAbrangenciaAcesso() {
		return abrangenciaAcesso;
	}

	/**
	 * @param abrangenciaAcesso O abrangenciaAcesso a ser setado.
	 */
	public void setAbrangenciaAcesso(String abrangenciaAcesso) {
		this.abrangenciaAcesso = abrangenciaAcesso;
	}

	/**
	 * @return Retorna o campo login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login O login a ser setado.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Retorna o campo dataCadastroFim.
	 */
	public String getDataCadastroFim() {
		return dataCadastroFim;
	}

	/**
	 * @param dataCadastroFim O dataCadastroFim a ser setado.
	 */
	public void setDataCadastroFim(String dataCadastroFim) {
		this.dataCadastroFim = dataCadastroFim;
	}

	/**
	 * @return Retorna o campo grupo.
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo O grupo a ser setado.
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return Retorna o campo nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome O nome a ser setado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo O tipo a ser setado.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Retorna o campo unidadeOrganizacional.
	 */
	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	/**
	 * @param unidadeOrganizacional O unidadeOrganizacional a ser setado.
	 */
	public void setUnidadeOrganizacional(String unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}


}

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
package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Thiago Toscano
 */
public class FiltroUsuarioGrupo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroUsuarioGrupo() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroUsuarioGrupo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String ID = "id";

	public final static String USUARIO = "usuario";

	public final static String USUARIO_ID = "usuario.id";

	public final static String GRUPO = "grupo";

	public final static String GRUPO_ID = "grupo.id";

	public final static String USUARIO_TIPO_ID = "usuario.usuarioTipo.id";

	public final static String USUARIO_EMPRESA_ID = "usuario.empresa.id";

	public final static String USUARIO_FUNCIONARIO_ID = "usuario.funcionario.id";

	public final static String USUARIO_NOME = "usuario.nomeUsuario";

	public final static String USUARIO_LOTACAO_ID = "usuario.unidadeOrganizacional.id";

	public final static String USUARIO_SITUACAO_ID = "usuario.usuarioSituacao.id";

	public final static String USUARIO_ABRANGENCIA_ID = "usuario.usuarioAbrangencia.id";

	public final static String USUARIO_GERENCIA_REGIONAL_ID = "usuario.gerenciaRegional.id";
	
	public final static String USUARIO_UNIDADE_NEGOCIO_ID = "usuario.unidadeNegocio.id";

	public final static String USUARIO_ELO_ID = "usuario.localidadeElo.id";

	public final static String USUARIO_LOCALIDADE_ID = "usuario.localidade.id";

	public final static String USUARIO_DATA_CADASTRO_INICIO = "usuario.dataCadastroInicio";

	public final static String USUARIO_DATA_CADASTRO_FIM = "usuario.dataCadastroFim";

	public final static String USUARIO_DATA_EXPIRACAO = "usuario.dataExpiracaoAcesso";

	public final static String USUARIO_DATA_CADASTRAMENTO = "usuario.dataCadastroAcesso";
	
	public final static String USUARIO_LOGIN = "usuario.login";
	
	public final static String CPF = "usuario.cpf";
	
	public final static String DATA_NASCIMENTO = "usuario.dataNascimento";
	
	public final static String CPF_FUNCIONARIO = "usuario.funcionario.numeroCpf";
	
	public final static String DATA_NASCIMENTO_FUNCIONARIO = "usuario.funcionario.dataNascimento";

}
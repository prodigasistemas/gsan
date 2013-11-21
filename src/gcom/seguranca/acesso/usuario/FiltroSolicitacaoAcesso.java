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
 * Filtro Solicitacao Acesso
 *
 * @author Hugo Leonardo
 * @date 12/11/2010
 */
public class FiltroSolicitacaoAcesso extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

    /**
     * Description of the Field
     */
	public final static String ID = "id";
	
	public final static String CODIGO = "comp_id";
    
    public final static String SOLICITACAO_ACESSO = "comp_id.solicitacaoAcesso";
    
    public final static String GRUPO = "comp_id.grupo";
    
    public final static String SOLICITACAO_ACESSO_ID = "comp_id.solicitacaoAcesso.id";
    
    public final static String GRUPO_ID = "comp_id.grupo.id";
    
    public final static String LOGIN = "login";
    
    public final static String FUNCIONARIO_SOLICITANTE = "funcionarioSolicitante";
    
    public final static String FUNCIONARIO_SOLICITANTE_ID = "funcionarioSolicitante.id";
    
    public final static String FUNCIONARIO_RESPONSAVEL = "funcionarioResponsavel";
    
    public final static String FUNCIONARIO_RESPONSAVEL_ID = "funcionarioResponsavel.id";
    
    public final static String UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional";
    
    public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
    
    public final static String EMAIL = "email";
    
    public final static String EMPRESA = "empresa";
    
    public final static String EMPRESA_ID = "empresa.id";
    
    public final static String FUNCIONARIO = "funcionario";
    
    public final static String FUNCIONARIO_ID = "funcionario.id";
    
    public final static String USUARIO_NOME = "nomeUsuario";
    
    public final static String SOLICITACAO_ACESSO_SITUACAO = "solicitacaoAcessoSituacao";
    
    public final static String SOLICITACAO_ACESSO_SITUACAO_ID = "solicitacaoAcessoSituacao.id";
    
    public final static String PERIODO_INICIO = "periodoInicial";
    
    public final static String PERIODO_FINAL = "periodoFinal";
    
    public final static String DATA_SOLICITACAO = "dataSolicitacao";
    
    public final static String DATA_AUTORIZACAO = "dataAutorizacao";
    
    public final static String DATA_CADASTRAMENTO = "dataCadastramento";
    
	/**
     * Constructor for the FiltroSolicitacaoAcesso object
     */
    public FiltroSolicitacaoAcesso() {
    }

    /**
     * Constructor for the FiltroSolicitacaoAcesso object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroSolicitacaoAcesso(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
}

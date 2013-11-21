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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.RamoAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirRamoAtividadeAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

	        //Seta o retorno
	        ActionForward retorno = actionMapping.findForward("telaSucesso");
	
	        InserirRamoAtividadeActionForm form = (InserirRamoAtividadeActionForm) actionForm;
	
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
	
	        //------------ REGISTRAR TRANSAÇÃO ----------------
	        
	        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_RAMO_ATIVIDADE_INSERIR,
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	        
	        Operacao operacao = new Operacao();
	        operacao.setId(Operacao.OPERACAO_RAMO_ATIVIDADE_INSERIR);
	
	        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
	        operacaoEfetuada.setOperacao(operacao);
	        
	        //------------ REGISTRAR TRANSAÇÃO ----------------
	
	        Short codigo = form.getCodigo();
	        String descricao = form.getDescricao();
	        
	        
	        if(codigo == null || codigo.equals("")){
	        	
	        	//Descrição não informada
	        	throw new ActionServletException("atencao.codigo_ramo_atividade_nao_informado");
	        } else if(descricao == null || descricao.equals("")){
	        
	        	//Descrição não informada
	        	throw new ActionServletException("atencao.descricao_sistema_abastecimento_nao_informado");
	        } else{
	        	
	        	//Criar o objeto ramoAtividade que será inserido na base
	        	RamoAtividade ramoAtividade = new RamoAtividade();
	        	
	        	ramoAtividade.setCodigo(codigo);
	        	ramoAtividade.setDescricao(descricao);
	        	ramoAtividade.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
	        	ramoAtividade.setUltimaAlteracao(new Date());
	        	
	        	//------------ REGISTRAR TRANSAÇÃO ----------------
	        	ramoAtividade.setOperacaoEfetuada(operacaoEfetuada);
	        	ramoAtividade.adicionarUsuario(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
	        	registradorOperacao.registrarOperacao(ramoAtividade);
	        	//------------ REGISTRAR TRANSAÇÃO ----------------
	        	
	        	Integer codigoRamoAtividadeInserido = 
	          		(Integer) this.getFachada().inserir(ramoAtividade);
	        	
	        	montarPaginaSucesso(httpServletRequest,
	            		"Ramo de Atividade de código " + ramoAtividade.getCodigo() 
	                    	+ " - "  + ramoAtividade.getDescricao().toUpperCase() 
	                    	+ " inserido com sucesso.",
	                    	"Inserir outro Ramo de Atividade",
	                    	"exibirInserirRamoAtividadeAction.do?menu=sim",
	                    	"exibirAtualizarRamoAtividadeAction.do?menu=sim&idRegistroAtualizacao=" + 
	                    	codigoRamoAtividadeInserido, "Atualizar Ramo de Atividade Inserido");
	        	
	       }
	
	        //devolve o mapeamento de retorno
	        return retorno;
	    }

    }

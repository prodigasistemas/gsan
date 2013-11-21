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
package gcom.gui.micromedicao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.AtualizarItemServicoActionForm;
import gcom.micromedicao.FiltroItemServico;
import gcom.micromedicao.ItemServico;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Atualizar Item de Servico
 *
 * @author Rodrigo de Abreu Cabral	
 * @date 04/08/2010
 */


public class AtualizarItemServicoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarItemServicoActionForm form = (AtualizarItemServicoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
				
		String id = form.getId();
		String descricao = form.getDescricao();
        String descricaoAbreviada = form.getDescricaoAbreviada();
        String codigoItem = form.getCodigoItem();
		
		if(descricao == null || descricao.equals("")){
        	
        	//Descrição não informada
        	throw new ActionServletException("atencao.descricao_sistema_abastecimento_nao_informado");
        } else{
        	//[FS0005] - Verificar descrição do item já informado.
        	FiltroItemServico filtroItemServico = new FiltroItemServico();
        	filtroItemServico.adicionarParametro(
        			new ParametroSimples(FiltroItemServico.DESCRICAO, descricao));
        	
        	Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
        			filtroItemServico, ItemServico.class.getName());
        	
        	
        	if ( colecaoItemServico != null  && !colecaoItemServico.isEmpty()) {

        		ItemServico itemServico = (ItemServico) Util.retonarObjetoDeColecao(colecaoItemServico);
        		
        		if (!itemServico.getId().toString().equals(id) ) {
        		
        			throw new ActionServletException("atencao.descricao_existente",null,descricao);
        		}
        	}
      
        	
        }
		if(descricaoAbreviada == null || descricaoAbreviada.equals("")){
        	
        	//Descrição Abreviada não informada
        	throw new ActionServletException("atencao.descricao_abreviada_nao_informada");
        //[FS0006] - Verificar descrição abreviada do item já informado.
		} else if (descricaoAbreviada != null && !descricaoAbreviada.equals("")){
        	FiltroItemServico filtroItemServico = new FiltroItemServico();
        	filtroItemServico.adicionarParametro(
        			new ParametroSimples(FiltroItemServico.DESCRICAO_ABREVIADA, descricaoAbreviada));
        	
        	Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
        			filtroItemServico, ItemServico.class.getName());
        	if ( colecaoItemServico != null && !colecaoItemServico.isEmpty() ) {
        		
        		ItemServico itemServico = (ItemServico) Util.retonarObjetoDeColecao(colecaoItemServico);
        		
        		if (!itemServico.getId().toString().equals(id) ) {
        			throw new ActionServletException("atencao.descricao_abreviada_tipo_debito_ja_existente",null,descricaoAbreviada);	        	
        		}
        	}
        } 
                 
        //[FS0007] - Verificar código do item já informado.
        if(codigoItem != null && !codigoItem.equals("")){
        	FiltroItemServico filtroItemServico = new FiltroItemServico();
        	filtroItemServico.adicionarParametro(
        			new ParametroSimples(FiltroItemServico.CODIGO_ITEM, codigoItem));
        	
        	Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
        			filtroItemServico, ItemServico.class.getName());
        	if ( colecaoItemServico != null && !colecaoItemServico.isEmpty()) {
        		
        		ItemServico itemServico = (ItemServico) Util.retonarObjetoDeColecao(colecaoItemServico);
        		
        		if (!itemServico.getId().toString().equals(id) ) {
        			throw new ActionServletException("atencao.codigo_existente",null,form.getCodigoItem());	        	
        		}
        	}
        } 
		
		ItemServico itemServico = (ItemServico) sessao.getAttribute("atualizarItemServico");
		
		//Atualiza a entidade com os valores do formulário
		itemServico.setDescricao(form.getDescricao());
		itemServico.setDescricaoAbreviada(form.getDescricaoAbreviada());
		itemServico.setIndicadorUso(new Short(form.getIndicadorUso()));	
		
		if (form.getCodigoConstanteCalculo() != null && !form.getCodigoConstanteCalculo().equals("")){
		itemServico.setCodigoConstanteCalculo(new Integer(form.getCodigoConstanteCalculo()));
		}
		
		if (codigoItem != null && !codigoItem.equals("")){
			itemServico.setCodigoItem(new Long(codigoItem));
		}
		
		itemServico.setUltimaAlteracao(new Date());
		
		//------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
			Operacao.OPERACAO_MANTER_ITEM_SERVICO,
			itemServico.getId(),itemServico.getId(),
			new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
		registradorOperacao.registrarOperacao(itemServico);
		//------------ REGISTRAR TRANSAÇÃO ----------------
    	
		//atualiza na base de dados de Item de Serviço
		 fachada.atualizar(itemServico);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Item de Contrato " + itemServico.getDescricao() + 
				" atualizado com sucesso.", 
				"Realizar outra Manutenção de Item de Contrato",
				"exibirFiltrarItemServicoAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    




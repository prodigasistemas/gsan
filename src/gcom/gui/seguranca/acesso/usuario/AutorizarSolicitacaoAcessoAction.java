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
package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1093] Manter Solicitação de Acesso.
 * 
 * @author Hugo Leonardo
 * @date 17/11/2010
 */
public class AutorizarSolicitacaoAcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosAutorizar();

		// mensagem de erro quando o usuário tenta Atualizar sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
		
		Collection idsSolicitacaoAcesso = new ArrayList(ids.length);
		
		for (int i = 0; i < ids.length; i++) {
			idsSolicitacaoAcesso.add(new Integer(ids[i]));
		}
		
		filtroSolicitacaoAcesso.adicionarParametro(
				new ParametroSimplesIn(FiltroSolicitacaoAcesso.ID, idsSolicitacaoAcesso));

		Collection coletionSolicitacaoAcesso = Fachada.getInstancia().pesquisar(filtroSolicitacaoAcesso,
				SolicitacaoAcesso.class.getName());
		
		Iterator itera = coletionSolicitacaoAcesso.iterator();
		
		while(itera.hasNext()){
			
			SolicitacaoAcesso solicitacaoAcesso = (SolicitacaoAcesso) itera.next();
			
			FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
			
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, 
							SolicitacaoAcessoSituacao.AG_CADASTRAMENTO));
			filtroSolicitacaoAcessoSituacao.adicionarParametro(
					new ParametroSimples(FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
			
			Collection colecaoSolicitacaoAcessoSituacao = 
				this.getFachada().pesquisar(filtroSolicitacaoAcessoSituacao, 
					SolicitacaoAcessoSituacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoSolicitacaoAcessoSituacao)){
				
				SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = 
					(SolicitacaoAcessoSituacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);
				
				solicitacaoAcesso.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
			}else{
				
				throw new ActionServletException("atencao.solicitacao_acesso_situacao.ag_cadastramento");
			}
			
			solicitacaoAcesso.setUltimaAlteracao(new Date());
			solicitacaoAcesso.setDataAutorizacao(new Date());
			
			Fachada.getInstancia().atualizar(solicitacaoAcesso);
		}

		Integer idQt = ids.length;

		montarPaginaSucesso(httpServletRequest, idQt.toString()
				+ " Solicitações de Acesso(s) Autorizada(s) com sucesso.",
				"Manter outra Solicitação de Acesso",
				"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim");
		
		if(sessao.getAttribute("objeto") != null){
			String objeto = (String) sessao.getAttribute("objeto");
			
			if(objeto.equals("atualizar")){
				
				montarPaginaSucesso(httpServletRequest, idQt.toString()
						+ " Solicitações de Acesso(s) Autorizada(s) com sucesso.",
						"Manter outra Solicitação de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=atualizar");
				
			}else if(objeto.equals("autorizar")){
				
				montarPaginaSucesso(httpServletRequest, idQt.toString()
						+ " Solicitações de Acesso(s) Autorizada(s) com sucesso.",
						"Manter outra Solicitação de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=autorizar");
	
			}else if(objeto.equals("cadastrar")){
				
				montarPaginaSucesso(httpServletRequest, idQt.toString()
						+ " Solicitações de Acesso(s) Autorizada(s) com sucesso.",
						"Manter outra Solicitação de Acesso",
						"exibirFiltrarSolicitacaoAcessoAction.do?menu=sim&objeto=cadastrar");
			}
		}

		return retorno;
	}

}

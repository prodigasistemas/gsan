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
package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 *
 */
public class PesquisarColunaTabelaAction extends GcomAction{
	
	/**
	 * Este caso de uso efetua pesquisa de coluna de tabela
	 * 
	 * [UC0308] Pesquisar Coluna da Tabela
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 19/04/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
		.findForward("exibirResultadoPesquisaColunaTabelaAction");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarColunaTabelaActionForm form = (PesquisarColunaTabelaActionForm) actionForm;
		
		FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
		
		Fachada fachada = Fachada.getInstancia();
		
		boolean peloMenosUmParametroInformado = false;
		
		String codigo = form.getCodigo();
		
		String nome = form.getNome();
		
		String idTabela=  form.getIdTabela();
		
		String tipoPesquisa = form.getTipoPesquisa();
		
		// Verifica se o campo nome foi informado
		
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
		
			peloMenosUmParametroInformado = true;	
			
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroTabelaColuna.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroTabelaColuna.DESCRICAO_COLUNA, nome));
			} else {
				filtroTabelaColuna.adicionarParametro(new ComparacaoTexto(
						FiltroTabelaColuna.DESCRICAO_COLUNA, nome));
			}
			
		
		}
		
		// Verifica se o campo codigo foi informado
		
		if (codigo != null
				&& !codigo.trim().equalsIgnoreCase("")) {
		
			peloMenosUmParametroInformado = true;
		
			filtroTabelaColuna.adicionarParametro(new ParametroSimples(
					FiltroTabelaColuna.ID,codigo));
		
		}
		
		
		//Verifica se o código da Tabela foi digitado
        if (idTabela != null
				&& !idTabela.trim().equals("")
				&& Integer.parseInt(idTabela) > 0) {

        	FiltroTabela filtroTabela = new FiltroTabela();
        	
        	filtroTabela.adicionarParametro(new ParametroSimples(
        			FiltroTabela.ID, idTabela));
			
			Collection<Tabela> colecaoTabela = fachada.pesquisar(filtroTabela,
					Tabela.class.getName());
			
			if(Util.isVazioOrNulo(colecaoTabela)){
				throw new ActionServletException("atencao.tabela.inexistente");
			}

			filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.TABELA_ID,idTabela));
			filtroTabelaColuna.adicionarCaminhoParaCarregamentoEntidade("tabela");
			peloMenosUmParametroInformado = true;
			
        }
        filtroTabelaColuna.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaColuna.TABELA);
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		
		if (sessao.getAttribute("podeRetificarContaAction") != null
				&& !sessao.getAttribute("podeRetificarContaAction").equals("")
				&& sessao.getAttribute("podeRetificarContaAction").equals("SIM")) {
			filtroTabelaColuna.adicionarParametro(
					new ParametroSimples(FiltroTabelaColuna.INDICADOR_PODE_RETIFICAR_CONTA, ConstantesSistema.SIM));
		}
		
			Collection colecaoTabelaColuna = fachada.pesquisar(filtroTabelaColuna,TabelaColuna.class.getName());
		
			if(colecaoTabelaColuna !=null && !colecaoTabelaColuna.isEmpty()){
		
				sessao.setAttribute("colecaoTabelaColuna",colecaoTabelaColuna);
			}else{
				throw new ActionServletException("atencao.naocadastrado",null,"Tabela Coluna");
			}
		
		return retorno;
		
		}

}

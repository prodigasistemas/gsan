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
package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de tipos débitos 
 * 
 * @author Pedro Alexandre
 * @created 09 de março de 2006
 */
public class ExibirPesquisarTipoDebitoAction extends GcomAction {
	
	/**
	 * consiste em pesquisar os tipos de débitos cadastrados no sistema
	 *
	 * [UC0303] Pesquisar Tipo de Débito
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Administrador
	 * @date 09/03/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		//seta o mapeamento de retorno para a tela de pesquisar tipos de débitos
		ActionForward retorno = actionMapping.findForward("pesquisarTipoDebito");
		
		//cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		  
		//se essa variavel tiver algum valor, isso indica que apenas do Tipo de Financimento SERVIÇO deve ser carregado na colecao
		String tipoFinanciamentoServico = "";
		
		if (httpServletRequest.getParameter("tipoFinanciamentoServico") != null &&
				!httpServletRequest.getParameter("tipoFinanciamentoServico").equals("")){
			tipoFinanciamentoServico = httpServletRequest.getParameter("tipoFinanciamentoServico");
			sessao.setAttribute("tipoFinanciamentoServico",httpServletRequest.getParameter("tipoFinanciamentoServico"));
		}else if(sessao.getAttribute("tipoFinanciamentoServico")!= null &&
				!sessao.getAttribute("tipoFinanciamentoServico").equals("")){
			tipoFinanciamentoServico = (String)sessao.getAttribute("tipoFinanciamentoServico");
		}
		
		
		PesquisarTipoDebitoActionForm pesquisarTipoDebitoActionForm = (PesquisarTipoDebitoActionForm) actionForm;
        if ((httpServletRequest.getParameter("limparForm") != null
				&& httpServletRequest.getParameter("limparForm").equalsIgnoreCase("1")) 
				|| (httpServletRequest.getParameter("objetoConsulta") == null
						&& httpServletRequest.getParameter("tipoConsulta") == null
						&& httpServletRequest.getParameter("voltarPesquisa") == null)){
        	
        	pesquisarTipoDebitoActionForm.setIdTipoDebito("");
        	pesquisarTipoDebitoActionForm.setDescricao("");
        	pesquisarTipoDebitoActionForm.setIdTipoFinanciamento(null);
        	pesquisarTipoDebitoActionForm.setIdItemLancamentoContabil(null);
        	pesquisarTipoDebitoActionForm.setIntervaloValorLimiteInicial("");
        	pesquisarTipoDebitoActionForm.setIntervaloValorLimiteFinal("");
        	pesquisarTipoDebitoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        	
        }
        
		//cria o filtro de lançamentos de item contábil para pesquisa  
		FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();

		//seta a ordenação do resultado da pesquisa de lançamentos de item contábil pela descrição
		filtroLancamentoItemContabil.setCampoOrderBy(FiltroLancamentoItemContabil.DESCRICAO);
		
		//pesquisa a coleção de lançamentos de item contábil no sistema
		Collection colecaoLancamentoItemContabil = fachada.pesquisar(filtroLancamentoItemContabil, LancamentoItemContabil.class.getName());
		
		//se nenhum lançamento de item contábil cadastrado no sistema
        if(colecaoLancamentoItemContabil == null || colecaoLancamentoItemContabil.isEmpty()){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Lançamento de Item Contábil");
        	
        }else{
        	//se existir lançamento de item contábil cadastrado(s) no sistema, manda 
        	//a coleção pesquisada no request para a página de pesquisar item de lançamento contábil
        	httpServletRequest.setAttribute("colecaoLancamentoItemContabil",colecaoLancamentoItemContabil);
        }
        
        //cria o filtro de tipo de financiamento para pesquisa  
        FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();

		//seta para pesquisar apenas o tipo de financiamento SERVIÇO
		if(tipoFinanciamentoServico != null && !tipoFinanciamentoServico.equals("")){
			filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(FiltroFinanciamentoTipo.ID,FinanciamentoTipo.SERVICO_NORMAL));
		}
        //seta a ordenação do resultado da pesquisa de tipo de financiamento pela descrição  
        filtroFinanciamentoTipo.setCampoOrderBy(FiltroFinanciamentoTipo.DESCRICAO);
        
        //pesquisa a coleção de tipo(s) de financiamento no sistema
        Collection colecaoFinanciamentoTipo = fachada.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
        
        //se nenhum tipo de financiamento cadastrado no sistema
        if(colecaoFinanciamentoTipo == null || colecaoFinanciamentoTipo.isEmpty()){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Tipo de Financiamento");
        	
        }else{
        	//se existir tipo(s) de financiamento cadastrado(s) no sistema, manda 
        	//a coleção pesquisada no request para a página de pesquisar tipo de financiamento
        	httpServletRequest.setAttribute("colecaoFinanciamentoTipo",colecaoFinanciamentoTipo);
        }
        
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoDebito") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaTipoDebito",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaTipoDebito"));

		}
        

        
        //retorna o mapeamento contido na variável "retorno"
		return retorno;
	}
}

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
package gcom.gui.faturamento.conta;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisar contas do imóvel 
 *
 * @author Pedro Alexandre
 * @date 02/03/2006
 */
public class ExibirPesquisarContaAction extends GcomAction {
	
	/**
	 * Pesquisa as contas existentes para o imóvel 
	 *
	 * [UC0248] Pesquisar Contas do Imóvel
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Pedro Alexandre
	 * @date 02/03/2006
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

		//Seta o mapeamento de retorno para a tela de pesquisar contas do imóvel
		ActionForward retorno = actionMapping.findForward("pesquisarConta");
		
		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o código do imóvel do request
		String idImovel = httpServletRequest.getParameter("idImovel");
		
		//PesquisarContaActionForm pesquisarContaActionForm = (PesquisarContaActionForm) actionForm;
		
				
		//Caso o código do imóvel tenha sido informado pelo caso de uso que chamou a tela de pesquisar contas,
		//Caso contrário levanta a exceção para o usuário indicando que o imóvel não foi informado
		if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			
			//Pesquisa se o imóvel informado esta cadastrado no sistema,
			//Caso contrário, levanta a exceção para o usuário indicando que o imóvel não existe
			FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());

        	//Caso o imóvel informado não tenha sido cadastrado no sistema
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Imóvel");
        	}
		}else{
			throw new ActionServletException("atencao.naoinformado",null, "Imóvel");
		}
	
		//Cria o filtro para pesquisar as situações de conta no sistema
		FiltroDebitoCreditoSituacao filtroDebitoCreditoSituacao = new FiltroDebitoCreditoSituacao();
		filtroDebitoCreditoSituacao.setCampoOrderBy(FiltroDebitoCreditoSituacao.DESCRICAO);
		
		
		Collection colecaoSituacaoConta = null;
		if (httpServletRequest.getParameter("situacaoConta") != null){
			// so mostras as situações : normal, retificada e incluida
			// utilizado a partir do inserir Pagamento (Manual)
			sessao.setAttribute("situacaoConta",httpServletRequest.getParameter("situacaoConta"));	
			
		}else{
			//Pesquisa todas as situações de conta cadastradas no sistema
			colecaoSituacaoConta = fachada.pesquisar(filtroDebitoCreditoSituacao, DebitoCreditoSituacao.class.getName());
			sessao.removeAttribute("situacaoConta");
		}
		
	
		//[FS0005] Caso não exista nenhuma situação de conta no sistema, levanta a exceção para o usuário indicando que 
		//nenhuma situação de conta está cadastrada no sistema.
		//Caso contrário, manda as situações de contas cadastradas no request e o código do imóvel
        if((colecaoSituacaoConta == null || colecaoSituacaoConta.isEmpty())&& httpServletRequest.getParameter("situacaoConta") == null ){
        	throw new ActionServletException("atencao.naocadastrado",null, "Situação de Conta");
        }else{
        	httpServletRequest.setAttribute("colecaoSituacaoConta",colecaoSituacaoConta);
        	httpServletRequest.setAttribute("idImovel",idImovel);
        }
        
        
        if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaConta") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaConta",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaConta"));
		}
        
        
        //Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}

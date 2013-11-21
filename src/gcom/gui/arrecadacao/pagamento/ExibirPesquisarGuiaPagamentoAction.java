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
package gcom.gui.arrecadacao.pagamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoTipo;
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
 * Action que define o pré-processamento da página de pesquisa de guia de pagamento para cliente ou imóvel
 *
 * @author Pedro Alexandre
 * @date 07/03/2006
 */
public class ExibirPesquisarGuiaPagamentoAction extends GcomAction {
	
	/**
	 * Pesquisa as guias de pagamento existentes para o imóvel ou cliente 
	 *
	 * [UC0249] Pesquisar Guia de Pagamento
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
	 * @date 07/03/2006
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

		//Seta o mapeamento de retorno para a tela de pesquisar guia de pagamento
		ActionForward retorno = actionMapping.findForward("pesquisarGuiaPagamento");
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o código do imóvel e o do cliente do request
		String idImovel = httpServletRequest.getParameter("idImovel");
		String idCliente = httpServletRequest.getParameter("idCliente");
		
		//PesquisarGuiaPagamentoActionForm pesquisarGuiaPagamentoActionForm = (PesquisarGuiaPagamentoActionForm) actionForm;
			
		//Caso o código do imóvel tenha sido informado pelo caso de uso que chamou a tela de pesquisar guia de pagamento,
		//Caso contrário verifica se o cliente foi informado
		if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			
			//Pesquisa se o imóvel informado esta cadastrado no sistema,
			//Caso contrário, levanta a exceção para o usuário indicando que o imóvel não existe
			FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	
        	//Caso o imóvel informado não tenha sido cadastrado no sistema
        	//Caso contrário manda o código do imóvel no request e o código do cliente como nulo
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Imóvel");
        	}else{
        		httpServletRequest.setAttribute("idImovel",idImovel);
        		httpServletRequest.setAttribute("idCliente","");
        	}
        	
        	//Caso o código do cliente tenha sido informado pelo caso de uso que chamou a tela de pesquisar guia de pagamento,
    		//Caso contrário levanta aexceção para o usuário indicando que o imóvel ou o cliente tem que ser informado
		}else if(idCliente != null && !idCliente.trim().equalsIgnoreCase("")){
			
			//Pesquisa se o cliente informado esta cadastrado no sistema,
			//Caso contrário, levanta a exceção para o usuário indicando que o cliente não existe
			FiltroCliente filtroCliente = new FiltroCliente();
        	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
        	Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
        	
        	//Caso o cliente informado não tenha sido cadastrado no sistema
        	//Caso contrário manda o código do cliente no request e o código do imóvel como nulo
        	if (colecaoCliente == null || colecaoCliente.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
        	}else{
        		httpServletRequest.setAttribute("idImovel","");
        		httpServletRequest.setAttribute("idCliente",idCliente);
        	}
        	
		}else{
			//Indica que o usuário tem de informar o imóvel ou o cliente
			throw new ActionServletException("atencao.naoinformado",null, "Imóvel ou Cliente");
		}
		
		//Cria o filtro para pesquisar as situações de guia de pagamento no sistema
		//e seta a ordenação do resultado da pesquisa pela descrição da situação
		FiltroDebitoCreditoSituacao filtroDebitoCreditoSituacao = new FiltroDebitoCreditoSituacao();
		filtroDebitoCreditoSituacao.setCampoOrderBy(FiltroDebitoCreditoSituacao.DESCRICAO);
		
		//Pesquisa as situações de guia de pagamento no sistema  
		Collection colecaoSituacaoGuiaPagamento = fachada.pesquisar(filtroDebitoCreditoSituacao, DebitoCreditoSituacao.class.getName());
		
		//Caso nenhuma situação de guia de pagamento cadastrada no sistema
        if(colecaoSituacaoGuiaPagamento == null || colecaoSituacaoGuiaPagamento.isEmpty()){
        	//Levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Situação de Guia de Pagamento");
        	
        }else{
        	//Manda a coleção de situação de guia de pagamento no request para a página de pesquisar
        	httpServletRequest.setAttribute("colecaoSituacaoGuiaPagamento",colecaoSituacaoGuiaPagamento);
        }
        
        //Cria o filtro para pesquisar ao tipos de débito sem limite de registros de guia de pagamento no sistema
		//e seta a ordenação do resultado da pesquisa pela descrição do tipo de débito
        FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        filtroDebitoTipo.setConsultaSemLimites(true);
        filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
        
        //Pesquisa os tipos de débito no sistema 
        Collection colecaoTipoDebito = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
        
        //Caso nenhum tipo de débito 
        if(colecaoTipoDebito == null || colecaoTipoDebito.isEmpty()){
        	//Levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Tipo de Débito");
        	
        }else{
        	//Manda a coleção de tipo de débito de guia de pagamento no request para a página de pesquisar
        	httpServletRequest.setAttribute("colecaoTipoDebito",colecaoTipoDebito);
        }
        
        
        
        if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaGuiaPagamento") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaGuiaPagamento",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaGuiaPagamento"));
		}
        
        
        
        //Retorna o mapeamento contido na variável retorno 
		return retorno;
	}
}

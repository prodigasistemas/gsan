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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 07 de Março de 2006
 */
public class ExibirManterDevolucaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterDevolucao");

		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoDevolucoes = null;

		if (sessao.getAttribute("colecaoImoveisDevolucoes") != null) {
			colecaoDevolucoes = (Collection) sessao
					.getAttribute("colecaoImoveisDevolucoes");
		} else if (sessao.getAttribute("colecaoClientesDevolucoes") != null) {
			colecaoDevolucoes = (Collection) sessao
					.getAttribute("colecaoClientesDevolucoes");
		} else if (sessao.getAttribute("colecaoAvisosBancariosDevolucoes") != null) {
			colecaoDevolucoes = (Collection) sessao
					.getAttribute("colecaoAvisosBancariosDevolucoes");
		}

		sessao.setAttribute("colecaoDevolucoes",colecaoDevolucoes);
		
		Collection devolucoes = null;
		//Parte da verificação do filtro
        FiltroDevolucao filtroDevolucao = new FiltroDevolucao();
        
		// Verifica se o filtro foi informado pela página de filtragem de devolucao
        if(httpServletRequest.getAttribute("filtroDevolucao") != null){
            filtroDevolucao = (FiltroDevolucao) httpServletRequest
                    .getAttribute("filtroDevolucao");
            
        }else if(sessao.getAttribute("filtroDevolucao")!= null){
        	filtroDevolucao = (FiltroDevolucao) sessao
            		.getAttribute("filtroDevolucao");
    	}else{
            //Se o limite de registros foi atingido, a página de filtragem é chamada
            retorno = actionMapping.findForward("exibirManterDevolucao");
        }
		
        if(retorno.getName().equalsIgnoreCase("exibirManterDevolucao")){
            //Seta a ordenação desejada do filtro
            filtroDevolucao.setCampoOrderBy(FiltroDevolucao.DATA_DEVOLUCAO);
            
            //Informa ao filtro para ele buscar objetos associados a Devolucao
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
            filtroDevolucao
            	.adicionarCaminhoParaCarregamentoEntidade("imovel");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
            filtroDevolucao
            	.adicionarCaminhoParaCarregamentoEntidade("cliente");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");

            // Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroDevolucao, Devolucao.class.getName());
			devolucoes = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			if (devolucoes == null || devolucoes.isEmpty()) {
				//Nenhuma Devolucao cadastrada
                throw new ActionServletException("atencao.pesquisa.nenhumresultado");
            }
            
            sessao.setAttribute("colecaoDevolucoes", devolucoes);
            
            sessao.setAttribute("filtroDevolucao", filtroDevolucao);
            sessao.setAttribute("telaManter","telaManter");
        }
		sessao.removeAttribute("colecaoImoveisDevolucoes");
		sessao.removeAttribute("colecaoClientesDevolucoes");
		sessao.removeAttribute("colecaoAvisosBancariosDevolucoes");
		
		sessao.setAttribute("tela","manterDevolucao");
		
		return retorno;
	}
}
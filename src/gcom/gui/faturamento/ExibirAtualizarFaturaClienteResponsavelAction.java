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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.FiltroFaturaItem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Flávio Leonardo
 * @date 26/11/2008
 */
public class ExibirAtualizarFaturaClienteResponsavelAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarFaturaClienteResponsavel");

		FiltrarFaturaClienteResponsavelActionForm form = (FiltrarFaturaClienteResponsavelActionForm) actionForm;

		// Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroFaturaItem filtroFaturaItem = (FiltroFaturaItem) sessao
			.getAttribute("filtroFaturaItem");
		
		Collection<FaturaItem> colecaoFaturaItem = null;

		if(sessao.getAttribute("colecaoFaturaItem") == null){
			colecaoFaturaItem = fachada.pesquisar(filtroFaturaItem, FaturaItem.class.getName());
		}else{
			colecaoFaturaItem = (Collection)sessao.getAttribute("colecaoFaturaItem");
		}
		

		if(colecaoFaturaItem.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}else{
			sessao.setAttribute("colecaoFaturaItem", colecaoFaturaItem);
			
			FaturaItem faturaItem = (FaturaItem)colecaoFaturaItem.iterator().next();
			
			Fatura fatura = faturaItem.getFatura();
			sessao.setAttribute("fatura", fatura);
			form.setValorFatura(Util.formatarMoedaReal(fatura.getDebito()));
			form.setDataVencimentoFatura(Util.formatarData(fatura.getVencimento()));
		}
		
		
		String[] faturaItemRemoverId = form.getIdRegistrosRemocao();

		Collection colecaoFaturaItemRemover = null;
		if(sessao.getAttribute("colecaoFaturaItemRemover") != null){
			colecaoFaturaItemRemover = (Collection)sessao.getAttribute("colecaoFaturaItemRemover");
		}else{
			colecaoFaturaItemRemover = new ArrayList();
		}
		
		if(faturaItemRemoverId != null && !faturaItemRemoverId.equals("")){
			
			for(int i=0; i< faturaItemRemoverId.length; i++){
				Iterator iterator = colecaoFaturaItem.iterator();
					
				while(iterator.hasNext()){
					FaturaItem faturaItemRemover = (FaturaItem) iterator.next();
					if(faturaItemRemover.getImovel().getId() != null && 
							faturaItemRemover.getImovel().getId().equals(new Integer(faturaItemRemoverId[i]))){
						colecaoFaturaItemRemover.add(faturaItemRemover);
						iterator.remove();
						break;
					}
				}
					
			}
			
			sessao.setAttribute("colecaoFaturaItemRemover",colecaoFaturaItemRemover);
		}
		
		if(!colecaoFaturaItemRemover.isEmpty()){
			Iterator iteratorRemovidos = colecaoFaturaItemRemover.iterator();
			
			while(iteratorRemovidos.hasNext()){
				FaturaItem faturaItemRemovido = (FaturaItem)iteratorRemovidos.next();
				Iterator iterator = colecaoFaturaItem.iterator();
				
				while(iterator.hasNext()){
					FaturaItem faturaItemRemover = (FaturaItem) iterator.next();
					if(faturaItemRemover.getImovel().getId() != null && faturaItemRemovido.getImovel().getId() != null
						&& faturaItemRemover.getImovel().getId().equals(faturaItemRemovido.getImovel().getId())){
						iterator.remove();
						break;
					}
				}
			}
		}
		
		if(colecaoFaturaItem.isEmpty()){
			throw new ActionServletException("atencao.fatura.cliente.responsavel.conter.uma");
		}else{
			Iterator iteratorSoma = colecaoFaturaItem.iterator();
			BigDecimal valorTotal = BigDecimal.ZERO;
			
			while(iteratorSoma.hasNext()){
				FaturaItem faturaItemSoma = (FaturaItem) iteratorSoma.next();
				
				valorTotal = valorTotal.add(faturaItemSoma.getValorConta());
				
				form.setValorFatura(Util.formatarMoedaReal(valorTotal));
			}
		}

		return retorno;
	}
}
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
package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdicionarPercentualFaixaValorPopupAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("inserirPrestacoesParcelamentoPerfilPercentualFaixaValorPopup");

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instância do formulário que está sendo utilizado
        InserirPrestacoesParcelamentoPerfilActionForm inserirPrestacoesParcelamentoPerfilActionForm = 
        	(InserirPrestacoesParcelamentoPerfilActionForm) actionForm;
        
        //Parãmetros recebidos para adicionar um Percentual Por Faixa de Valor
        String valorMaximo = inserirPrestacoesParcelamentoPerfilActionForm.getValorMaxPercFaixaValor();
        String percentual = inserirPrestacoesParcelamentoPerfilActionForm.getPercentualPercFaixaValor();
        
    	Collection collectionParcelamentoFaixaValor = null;
    	ParcelamentoFaixaValor parcelamentoFaixaValor = new ParcelamentoFaixaValor();
        
        if (sessao.getAttribute("collectionParcelamentoFaixaValor") != null) {
        	collectionParcelamentoFaixaValor = (Collection) sessao
                    .getAttribute("collectionParcelamentoFaixaValor");
        } else {
        	collectionParcelamentoFaixaValor = new ArrayList();
        }
        
        //Validação dos campos recebidos
        if (valorMaximo == null || valorMaximo.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.campo_texto.obrigatorio", null, "Valor Máximo");
        }
        parcelamentoFaixaValor.setValorFaixa(Util.formatarMoedaRealparaBigDecimal(valorMaximo));
        
        if (percentual == null || percentual.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.campo_texto.obrigatorio", null, "Percentual");
        }
        
		if (collectionParcelamentoFaixaValor != null && !collectionParcelamentoFaixaValor.isEmpty()){
			// se coleção não estiver vazia
			// verificar se  Valor Máximo ja foi informada	
			ParcelamentoFaixaValor parcelamentoFaixaValorAntigo = null;
			Iterator iterator = collectionParcelamentoFaixaValor.iterator();
		
			while (iterator.hasNext()) {
				parcelamentoFaixaValorAntigo = (ParcelamentoFaixaValor) iterator.next();
				
				if ((Util.formatarMoedaRealparaBigDecimal(valorMaximo)).equals
						(parcelamentoFaixaValorAntigo.getValorFaixa())){
			        //Limpa o formulário que adiciona Percentual Por Faixa de Valor
			        inserirPrestacoesParcelamentoPerfilActionForm.setValorMaxPercFaixaValor("");
			        inserirPrestacoesParcelamentoPerfilActionForm.setPercentualPercFaixaValor("");
					
					// Valor Máximo já informado.
					throw new ActionServletException(
					"atencao.valor_maximo_ja_informado");
				}
			}
		}
        
        parcelamentoFaixaValor.setPercentualFaixa(Util.formatarMoedaRealparaBigDecimal(percentual));

        parcelamentoFaixaValor.setUltimaAlteracao(new Date());
        
        collectionParcelamentoFaixaValor.add(parcelamentoFaixaValor);
        
        
        //Ordena a coleção pelo valor máximo
		Collections.sort((List) collectionParcelamentoFaixaValor, new Comparator() {
			public int compare(Object a, Object b) {
				BigDecimal valorMax1 = new BigDecimal(((ParcelamentoFaixaValor) a).getValorFaixa().toString()) ;
				BigDecimal valorMax2 = new BigDecimal(((ParcelamentoFaixaValor) b).getValorFaixa().toString()) ;
		
				return valorMax1.compareTo(valorMax2);

			}
		});
				
        
        
        sessao.setAttribute("collectionParcelamentoFaixaValor",collectionParcelamentoFaixaValor);
        
        if (collectionParcelamentoFaixaValor == null || 
        		collectionParcelamentoFaixaValor.size() == 0){
			sessao.setAttribute("collectionParcelamentoFaixaValorVazia","1");
        }else{
			sessao.setAttribute("collectionParcelamentoFaixaValorVazia","2");
        }
        
        //Limpa o formulário que adiciona Percentual Por Faixa de Valor
        inserirPrestacoesParcelamentoPerfilActionForm.setValorMaxPercFaixaValor("");
        inserirPrestacoesParcelamentoPerfilActionForm.setPercentualPercFaixaValor("");
        
        return retorno;
    }

}

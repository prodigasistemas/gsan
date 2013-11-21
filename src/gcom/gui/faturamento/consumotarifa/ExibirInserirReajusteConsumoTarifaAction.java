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
package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

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
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirReajusteConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirReajusteConsumoTarifa");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String idRecuperado = httpServletRequest.getParameter("id_r");

		String[] ids = idRecuperado.split(",");
		
		if ( ids == null || ids[0].equals("") ) {
			throw new ActionServletException("atencao.selecione_ao_menos_uma_tarifa_consumo");
		}

		// CRIACOES DE COLECOES....

		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		Collection colecaoCategoria = (Collection) fachada.pesquisar(filtroCategoria, Categoria.class.getName());
		
		Collection colecaoIdsConsumoTarifa = new ArrayList();
		
		//dentre todas as vigencia, ira procurar se existe alguma com o mesmo Consumo TArifa
		for (int i = 0; i < ids.length; i++) {
			String idConsumoTarifaVigencia = ids[i];
			
			FiltroConsumoTarifaVigencia filtroConsumoTarifaVigencia = new FiltroConsumoTarifaVigencia();
			filtroConsumoTarifaVigencia.adicionarParametro(new ParametroSimples(FiltroConsumoTarifaVigencia.ID,idConsumoTarifaVigencia));
			filtroConsumoTarifaVigencia.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigencia.CONSUMO_TARIFA);
			
			Collection colecaoConsumoTarifaVigencia = (Collection) fachada.pesquisar(filtroConsumoTarifaVigencia, ConsumoTarifaVigencia.class.getName());	

			ConsumoTarifaVigencia consumoTarifaVigencia = (ConsumoTarifaVigencia) colecaoConsumoTarifaVigencia.iterator().next();
			
			
			adicionarConsumoTarifa(colecaoIdsConsumoTarifa,consumoTarifaVigencia.getConsumoTarifa().getId().toString());
			
			colecaoIdsConsumoTarifa.add(consumoTarifaVigencia.getConsumoTarifa().getId().toString());
		}
		
		
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
		sessao.setAttribute("ids", ids);
		
		
		return retorno;
	}
	
	/**
	 * 
	 * UC00169 - Manter Tarifa de Consumo
	 *
	 * [FS0010] - Verificar reajuster para mais de uma vigêmcia da mesma tarifa
	 *
	 * @author Rafael Santos
	 * @date 13/07/2006
	 *
	 * @param colecaoIdsConsumoTarifa
	 * @param idConsumoTarifa
	 */
	public void adicionarConsumoTarifa(Collection colecaoIdsConsumoTarifa,String idConsumoTarifa){

		int existe = 0;
		
		if(colecaoIdsConsumoTarifa != null && !colecaoIdsConsumoTarifa.isEmpty()){
			
			Iterator iteratorColecaoIdsConsumoTarifa = colecaoIdsConsumoTarifa.iterator();
			
			
			while (iteratorColecaoIdsConsumoTarifa.hasNext()) {
				String id = (String) iteratorColecaoIdsConsumoTarifa.next();
				
				if(id.equalsIgnoreCase(idConsumoTarifa)){
					existe  = 1 + 1;
		            

				}
			}
			
		}
		
		if(existe > 1){
			throw new ActionServletException("atencao.consumo_tarifa_vigencia.mesma.consumo_tarifa");
		}
		
	
	}
	
}
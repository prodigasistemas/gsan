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

import gcom.arrecadacao.bean.ManterDebitoAutomaticoHelper;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0802] - Filtrar Debito Automático
 * 
 * @author Bruno Barros
 * @since 11/06/2008
 */
public class ExibirManterDebitoAutomaticoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterDebitoAutomatico");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recuperamos o filtro que foi passado e realizamos a pesquisa
		FiltroDebitoAutomatico filtro = (FiltroDebitoAutomatico) sessao
				.getAttribute("filtroDebitoAutomatico");
		
		// Adicionamos no filtro o caminho de banco e de agencia,
		// pois precisaremos de algumas informações desses objetos
		filtro.adicionarCaminhoParaCarregamentoEntidade( "agencia.banco" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel" );
		
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Collection colecaoDebitoAutomatico = new ArrayList();
		if(filtro != null && !filtro.equals("")){
			Map resultado = 
				controlarPaginacao(
						httpServletRequest, 
						retorno,
						filtro, 
						DebitoAutomatico.class.getName() );
			
			colecaoDebitoAutomatico = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		
		// Criamos o helper que será carregado na tela de manter debito automatico
		Iterator iteDebitoAutomatico = colecaoDebitoAutomatico.iterator();
		Collection<ManterDebitoAutomaticoHelper> colHelper = new ArrayList();
		
		while ( iteDebitoAutomatico.hasNext() ){
			DebitoAutomatico debito = (DebitoAutomatico) iteDebitoAutomatico.next();
			ManterDebitoAutomaticoHelper helper = new ManterDebitoAutomaticoHelper();
			
			helper.setIdDebitoAutomatico( debito.getId()+"" );
			helper.setSiglaBanco( debito.getAgencia().getBanco().getDescricaoAbreviada() );
			helper.setAgencia( debito.getAgencia().getCodigoAgencia() );
			
			Cliente cliente = Fachada.getInstancia().pesquisarClienteUsuarioImovel( debito.getImovel().getId() );
			helper.setNomeCliente( cliente.getNome() );
			helper.setMatriculaImovel( debito.getImovel().getId()+"" );
			helper.setMatriculaImovelFormatada( Util.retornaMatriculaImovelFormatada( debito.getImovel().getId() ) );
			
			colHelper.add( helper );
		}
		
		httpServletRequest.setAttribute("colecaoDebitoAutomaticoHelper", colHelper);

		return retorno;
	}
}

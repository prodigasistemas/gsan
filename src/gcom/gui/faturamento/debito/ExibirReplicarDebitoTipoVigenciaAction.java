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
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0985] Inserir tipo de débito com vigência
 * 
 * Este caso de uso permite a inclusão de um novo debito tipo vigencia 
 *
 * @author Josenildo Neves
 * @date 22/02/2010
 */
public class ExibirReplicarDebitoTipoVigenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("replicarDebitoTipoVigencia");

		ReplicarDebitoTipoVigenciaActionForm replicarDebitoTipoVigenciaActionForm = (ReplicarDebitoTipoVigenciaActionForm) actionForm;

		retorno = this.getDebitoTipoVigencia(replicarDebitoTipoVigenciaActionForm,httpServletRequest, retorno);
		
		return retorno;
		
	}
	
	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e valor
	 * 
	 * Este método exibe a coleção de Débito Tipo Vigência que tem a última data de vigência. 
	 *
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	private ActionForward getDebitoTipoVigencia(ReplicarDebitoTipoVigenciaActionForm form, HttpServletRequest httpServletRequest, ActionForward retorno) {
		
		Collection<DebitoTipoVigencia> colecaoDebitoTipoVigencia = null;
		
		// 1º Passo - Pegar o total de registros através de um count da
		// consulta que aparecerá na tela
		Integer totalRegistros = Fachada.getInstancia().pesquisarDebitoTipoVigenciaUltimaVigenciaTotal();

		// 2º Passo - Chamar a função de Paginação passando o total de
		// registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);
		
		if (httpServletRequest.getParameter("page.offset") != null) {
			colecaoDebitoTipoVigencia = Fachada.getInstancia().pesquisarDebitoTipoVigenciaUltimaVigencia(Integer.parseInt(httpServletRequest.getParameter("page.offset")) - 1);
		
		}else{
			colecaoDebitoTipoVigencia = Fachada.getInstancia().pesquisarDebitoTipoVigenciaUltimaVigencia(0);
		
		}
		
		if (colecaoDebitoTipoVigencia != null && !colecaoDebitoTipoVigencia.isEmpty()) {
			
			form.setMensagem(true);
			form.setCollDebitoTipoVigencia(colecaoDebitoTipoVigencia);			
			
		} else {
			form.setMensagem(false);
			form.setCollDebitoTipoVigencia(null);
		}
		
		return retorno;
	}
	
}
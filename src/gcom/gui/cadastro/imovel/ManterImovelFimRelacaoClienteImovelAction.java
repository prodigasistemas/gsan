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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rafael Santos
 * @since 17/04/2006
 */
public class ManterImovelFimRelacaoClienteImovelAction extends GcomAction {
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

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping
				.findForward("atualizarImovelCliente");
		// Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		FimRelacaoClienteImovelActionForm fimRelacaoClienteImovelActionForm = (FimRelacaoClienteImovelActionForm) actionForm;

		Collection colecaoClientesImoveisFimRelacao = (Collection) sessao
				.getAttribute("colecaoClientesImoveisFimRelacao");
		
		Collection colecaoClientesImoveisRemovidos = (Collection)
			sessao.getAttribute("colecaoClientesImoveisRemovidos");
		
        Collection imovelClientesNovos = (Collection) sessao
        .getAttribute("imovelClientesNovos");		
		
		// verifica se tem algum cliente imovel que precisa ser
		// atualizado com a data de termino da
		// relação e o motivo.
		if (colecaoClientesImoveisFimRelacao != null
				&& !colecaoClientesImoveisFimRelacao.isEmpty()) {
			Iterator clienteImovelIterator = colecaoClientesImoveisFimRelacao
					.iterator();
			String idMotivoFimRelacao = fimRelacaoClienteImovelActionForm
					.getIdMotivo();

			ClienteImovelFimRelacaoMotivo clienteImovelFimRelacao = new ClienteImovelFimRelacaoMotivo();
			// seta o id do motivo do fim da relação
			clienteImovelFimRelacao.setId(new Integer(idMotivoFimRelacao));

			String dataFimRelacaoForm = fimRelacaoClienteImovelActionForm
					.getDataTerminoRelacao();

			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

			Date dataFimRelacao = null;
			try {
				dataFimRelacao = dataFormato.parse(dataFimRelacaoForm);
			} catch (ParseException ex) {
				dataFimRelacao = null;
			}

			Date dataCorrente = null;
			Calendar a = Calendar.getInstance();
			a.set(Calendar.SECOND, 0);
			a.set(Calendar.MILLISECOND, 0);
			a.set(Calendar.HOUR, 0);
			a.set(Calendar.MINUTE, 0);
			dataCorrente = a.getTime();

			if (dataFimRelacao.after(dataCorrente)) {
				throw new ActionServletException(
						"atencao.data_fim_relacao_cliente_imovel");
			}

			while (clienteImovelIterator.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) clienteImovelIterator
						.next();
				if (dataFimRelacao.before(clienteImovel
						.getDataInicioRelacao())) {
					throw new ActionServletException(
							"atencao.data_fim_relacao_cliente_imovel_menor_inicial");

				}

			}

			// caso a data não seja menor que a atual então
			// seta a data final no cliente imovel
			clienteImovelIterator = colecaoClientesImoveisFimRelacao
					.iterator();

			while (clienteImovelIterator.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) clienteImovelIterator
						.next();
				clienteImovel
						.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacao);
				clienteImovel.setDataFimRelacao(dataFimRelacao);

             	// verifica se o tipo do cliente é usuário
                 if (clienteImovel
                         .getClienteRelacaoTipo()
                         .getId().shortValue() == 
                         ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO.shortValue()) {
                     if(sessao.getAttribute(
                             "idClienteImovelUsuario") != null){
                    	 sessao.removeAttribute("idClienteImovelUsuario");	 
                     }
                	 
                 }
                 
                 // verifica se o tipo do cliente é responsavel
                 if ((clienteImovel.getClienteRelacaoTipo().getId().shortValue()
                          == ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL.shortValue())) {
                     if(sessao.getAttribute(
                     	"idClienteImovelResponsavel") != null){
                    	 sessao.removeAttribute("idClienteImovelResponsavel");	 
                     }
                 }
                
             	
                if(!(colecaoClientesImoveisRemovidos.contains(clienteImovel))){
                	fachada.verificaRestricaoDaTabelaClienteImovel(clienteImovel);
                	colecaoClientesImoveisRemovidos.add(clienteImovel);	
             	}
                 
             	imovelClientesNovos.remove(clienteImovel);
             	
                 //clienteImovelIterator.remove();
			}

		}
		
		sessao.setAttribute("imovelClientesNovos", imovelClientesNovos);
		sessao.setAttribute("colecaoClientesImoveisFimRelacao", null);
		sessao.setAttribute("colecaoClientesImoveisRemovidos", colecaoClientesImoveisRemovidos);
		httpServletRequest.setAttribute("reloadPage", "OK");
		
		return retorno;
	}
	
}

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
package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirProcessoUmInserirAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirAvisoBancarioProcessoUm");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirAvisoBancarioActionForm form = (InserirAvisoBancarioActionForm) actionForm;

		// Pesquisa de Arrecadador
		String idArrecadador = form.getCodigoArrecadador();
		String dataLancamento = form.getDataLancamento();
		Arrecadador arrecadador = null;
		Collection<Arrecadador> collectionArrecadador = null;
		
		Arrecadador arrecadadorVolta = (Arrecadador)sessao.getAttribute("arrecadador");
		String data = (String)sessao.getAttribute("data");
		if((sessao.getAttribute("arrecadador") != null 
				&&  !arrecadadorVolta.getId().toString().equalsIgnoreCase(idArrecadador)) 
				|| (sessao.getAttribute("data") != null  && !data.equalsIgnoreCase("")
						&&  !data.equalsIgnoreCase(dataLancamento)))
		{
			form.setTipoAviso("");
			form.setNumeroDocumento("");
			form.setDataRealizacao("");
			form.setValorArrecadacao("");
			form.setValorDevolucao("");
			form.setValorDeducoes("");
			form.setValorAviso("");
		}
		
		if (idArrecadador != null && !idArrecadador.trim().equals("")) {

			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE, new Integer(idArrecadador)));
			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			collectionArrecadador = fachada.pesquisar(filtroArrecadador,
					Arrecadador.class.getName());

			if (!collectionArrecadador.isEmpty()) {
				Iterator iterator = collectionArrecadador.iterator();
				if (iterator.hasNext())
				arrecadador = (Arrecadador) iterator.next();
				form.setNomeArrecadador(arrecadador.getCliente().getNome());
				sessao.setAttribute("arrecadador", arrecadador);
				
				FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
				filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
						FiltroArrecadadorContrato.ARRECADADOR_CODIGO_AGENTE, idArrecadador));
				filtroArrecadadorContrato.adicionarParametro(new ParametroNulo(
						FiltroArrecadadorContrato.DATA_CONTRATO_ENCERRAMENTO));
				filtroArrecadadorContrato
						.adicionarCaminhoParaCarregamentoEntidade("contaBancariaDepositoArrecadacao.agencia.banco");

				Collection colecaoArrecadadorContrato = fachada.pesquisar(
						filtroArrecadadorContrato, ArrecadadorContrato.class
								.getName());

				if (colecaoArrecadadorContrato != null
						&& !colecaoArrecadadorContrato.isEmpty()) {
					
					httpServletRequest.setAttribute("colecaoArrecadadorContrato", colecaoArrecadadorContrato);
				}else{
					throw new ActionServletException("atencao.arrecadador.sem.contrato");
				}
			} else {
				httpServletRequest.setAttribute("arrecadadorInexistente", true);
				form.setCodigoArrecadador("");
				form.setNomeArrecadador("ARRECADADOR INEXISTENTE");
			}
		}
		
		sessao.setAttribute("data", dataLancamento);
		return retorno;
	}

}

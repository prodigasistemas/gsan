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

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0536]FILTRAR ARRECADADOR
 * 
 * @author Marcio Roberto
 * @date 01/02/2007
 */

public class FiltrarArrecadadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterArrecadador");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarArrecadadorActionForm filtrarArrecadadorActionForm = (FiltrarArrecadadorActionForm) actionForm;

		// Recupera todos os campos da página para ser colocada no filtro posteriormente

		String idAgente = filtrarArrecadadorActionForm.getIdAgente();
		String idCliente = filtrarArrecadadorActionForm.getIdCliente();
		String idImovel = filtrarArrecadadorActionForm.getIdImovel();
		String inscricaoEstadual = filtrarArrecadadorActionForm.getInscricaoEstadual();
		String indicadorUso = filtrarArrecadadorActionForm.getIndicadorUso();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("indicadorAtualizar");
		}

		boolean peloMenosUmParametroInformado = false;
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

		// Código do Arrecadador
		if (idAgente != null && !idAgente.trim().equals("")) {
			// [FS0003] - Verificando a existência do Agente
			boolean achou = fachada.verificarExistenciaAgente(new Integer(idAgente));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, idAgente));
			}
		}

		// Cliente
		if (idCliente != null && !idCliente.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CLIENTE_ID, idCliente));
		}

		// Imovel
		if (idImovel != null && !idImovel.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.IMOVEL_ID, idImovel));
		}

		// Inscricao Estadual
		if (inscricaoEstadual != null && !inscricaoEstadual.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.INSCRICAO_ESTATAL, inscricaoEstadual));
		}
		
		//Indicador de Uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.INDICADOR_USO, indicadorUso));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("imovel");

		Collection<Arrecadador> colecaoArrecadador = fachada.pesquisar(
				filtroArrecadador, Arrecadador.class.getName());

		if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Arrecadador");
		} else {
			httpServletRequest.setAttribute("colecaoArrecadador",colecaoArrecadador);
			Arrecadador arrecadador = new Arrecadador();
			arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadador);
			String idRegistroAtualizacao = arrecadador.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroArrecadador", filtroArrecadador);

		httpServletRequest.setAttribute("filtroArrecadador", filtroArrecadador);

		return retorno;

	}
}

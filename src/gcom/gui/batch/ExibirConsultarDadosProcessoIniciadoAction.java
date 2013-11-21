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
package gcom.gui.batch;

import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.ProcessoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de dados do processo inserido
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */
public class ExibirConsultarDadosProcessoIniciadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarDadosProcessoIniciado");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		
		Integer idProcessoIniciado = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			idProcessoIniciado = Integer.parseInt(httpServletRequest
					.getParameter("idRegistroAtualizacao"));
			sessao.setAttribute("idProcessoIniciado", idProcessoIniciado);
		} else {
			idProcessoIniciado = (Integer) sessao
					.getAttribute("idProcessoIniciado");
		}
		
		
		
		
		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada(
				FiltroFuncionalidadeIniciada.SEQUENCIAL_EXECUCAO);
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidadeIniciada.PROCESSO_INICIADO_ID,
				idProcessoIniciado));

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processoSituacao");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processo");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.funcionalidade");

		Collection<FuncionalidadeIniciada> colecaoFuncionalidadeIniciada = fachada
				.pesquisar(filtroFuncionalidadeIniciada,
						FuncionalidadeIniciada.class.getName());

		if (colecaoFuncionalidadeIniciada == null
				|| colecaoFuncionalidadeIniciada.isEmpty()) {
			throw new ActionServletException(
					"atencao.processo_iniciado.funcionalidade_iniciada.inexistente");

		}
		
		// Abre o popup de informacao para informar o usuário sobre a
		// Orientacao para o operador continuar ou reiniciar o batch.
		if(httpServletRequest.getParameter("informacaoPopup") != null && 
				httpServletRequest.getParameter("informacaoPopup").equalsIgnoreCase("sim")){
			
			String orientacaoProcessoFunc = colecaoFuncionalidadeIniciada.iterator().
			next().getProcessoFuncionalidade().getOrientacao();
			
			httpServletRequest.setAttribute("tituloInformacao", "Orientação para continuar ou reiniciar o batch");
			httpServletRequest.setAttribute("descricaoInformacao",orientacaoProcessoFunc);
			
			
			return actionMapping.findForward("telaInformacao");
		}
		

		httpServletRequest
				.setAttribute("mesAnoReferencia", Util
						.formatarAnoMesParaMesAno(fachada
								.pesquisarParametrosDoSistema()
								.getAnoMesFaturamento()));

		httpServletRequest.setAttribute("dataCorrente", new Date());

		httpServletRequest.setAttribute("processoIniciado",
				((FuncionalidadeIniciada) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadeIniciada))
						.getProcessoIniciado());
		
		httpServletRequest.setAttribute("concluido",
				new Integer(ProcessoSituacao.CONCLUIDO));
		httpServletRequest.setAttribute("concluidoComErro",
				new Integer(ProcessoSituacao.CONCLUIDO_COM_ERRO));
		httpServletRequest.setAttribute("execucaoCancelada",
				new Integer(ProcessoSituacao.EXECUCAO_CANCELADA));

		httpServletRequest.setAttribute("colecaoFuncionalidadeIniciada",
				colecaoFuncionalidadeIniciada);
		
		// Verificar as permissão especial para reiniciar o batch
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		boolean temPermissaoReiniciarBatch = fachada.verificarPermissaoReiniciarBatch(usuario);
		httpServletRequest.setAttribute("temPermissaoReiniciarBatch", temPermissaoReiniciarBatch);


		return retorno;
	}

}

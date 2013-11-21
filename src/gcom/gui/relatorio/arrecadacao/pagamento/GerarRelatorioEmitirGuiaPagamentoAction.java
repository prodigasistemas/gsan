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
package gcom.gui.relatorio.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.CategoriaActionForm;
import gcom.gui.faturamento.ManterGuiaPagamentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.pagamento.RelatorioEmitirGuiaPagamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
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
 * [UC0379] Emitir Guia de Pagamento
 * @author Vivianne Sousa
 * @date 22/09/2006
 */

public class GerarRelatorioEmitirGuiaPagamentoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		 String[] ids = null;

		if (actionForm instanceof ManterGuiaPagamentoActionForm) {
			// tela de Cancelar Guia de Parcelamento
			ManterGuiaPagamentoActionForm manterGuiaPagamentoActionForm = (ManterGuiaPagamentoActionForm) actionForm;
			ids = manterGuiaPagamentoActionForm.getIdRegistrosRemocao();
		} else if (httpServletRequest.getParameter("idGuiaPagamento") != null) {

			// tela de efetuar parcelamento
			ids = new String[1];
			String idGuiaPagamento = (String) httpServletRequest
					.getParameter("idGuiaPagamento");
			ids[0] = idGuiaPagamento;

		} else if (actionForm instanceof CategoriaActionForm) {
			// tela de Inserir
			// ids = new String[1];
			// String idGuiaPagamento =
			// (String)httpServletRequest.getParameter("idGuiaPagamento");
			// ids[0] = idGuiaPagamento;

			ids = (String[]) sessao.getAttribute("idGuiaPagamento");

		} else if (sessao.getAttribute("idGuiaPagamento") != null) {
			ids = (String[]) sessao.getAttribute("idGuiaPagamento");
		} else {
			// tela de Consultar Parcelamento
			String idParcelamento = (String) sessao
					.getAttribute("idParcelamento");

			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
					FiltroGuiaPagamento.PARCELAMENTO_ID, new Integer(
							idParcelamento)));

			Collection collectionGuiaPagamento = fachada.pesquisar(
					filtroGuiaPagamento, GuiaPagamento.class.getName());
			GuiaPagamento guiaPagamento = (GuiaPagamento) Util
					.retonarObjetoDeColecao(collectionGuiaPagamento);
			String idGuiaPagamento = "" + guiaPagamento.getId();
			ids = new String[1];
			ids[0] = idGuiaPagamento;
		}
		 
		 
		// Parte que vai mandar o relatório para a tela
		// cria uma instância da classe do relatório
		RelatorioEmitirGuiaPagamento relatorioEmitirGuiaPagamento = new RelatorioEmitirGuiaPagamento((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioEmitirGuiaPagamento.addParametro("ids",ids);
		//String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		//if (tipoRelatorio == null) {
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		//}

		relatorioEmitirGuiaPagamento.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		try {
			retorno = processarExibicaoRelatorio(relatorioEmitirGuiaPagamento,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}

	

	}

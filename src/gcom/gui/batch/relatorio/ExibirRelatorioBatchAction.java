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
package gcom.gui.batch.relatorio;

import gcom.batch.RelatorioGerado;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.relatorio.FiltroRelatorioGerado;
import gcom.relatorio.RelatorioVazioException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.IoUtil;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe respsável por montar a apresentação dos relatórios armazenados em
 * batch
 * 
 * 
 * @author Rodrigo Silveira
 * @date 26/10/2006
 */
public class ExibirRelatorioBatchAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		int idFuncionalidadeIniciada = converterStringToInt(httpServletRequest
				.getParameter("idFuncionalidadeIniciada"));

		FiltroRelatorioGerado filtroRelatorioGerado = new FiltroRelatorioGerado();
		filtroRelatorioGerado.adicionarParametro(new ParametroSimples(
				FiltroRelatorioGerado.FUNCIONALIDADE_INICIADA_ID,
				idFuncionalidadeIniciada));
		filtroRelatorioGerado
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeIniciada");

		RelatorioGerado relatorioGerado = (RelatorioGerado) Util
				.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(
						filtroRelatorioGerado, RelatorioGerado.class.getName()));

		OutputStream out = null;
		try {

			TarefaRelatorio tarefaRelatorio = (TarefaRelatorio) IoUtil
					.transformarBytesParaObjeto(relatorioGerado
							.getFuncionalidadeIniciada().getTarefaBatch());

			// httpServletResponse.addHeader("Content-Disposition","attachment;
			// filename=relatorio");

			String mimeType = null;
			switch ((Integer) tarefaRelatorio
					.getParametro("tipoFormatoRelatorio")) {
			case TarefaRelatorio.TIPO_PDF:
				httpServletResponse.addHeader("Content-Disposition",
						"attachment; filename=relatorio.pdf");
				mimeType = "application/pdf";
				break;

			case TarefaRelatorio.TIPO_RTF:
				httpServletResponse.addHeader("Content-Disposition",
						"attachment; filename=relatorio.rtf");

				mimeType = "application/rtf";
				break;
			case TarefaRelatorio.TIPO_XLS:
				httpServletResponse.addHeader("Content-Disposition",
						"attachment; filename=relatorio.xls");

				mimeType = "application/vnd.ms-excel";
				break;
			case TarefaRelatorio.TIPO_HTML:
				httpServletResponse.addHeader("Content-Disposition",
						"attachment; filename=relatorio.zip");

				mimeType = "application/zip";
				break;
			}

			httpServletResponse.setContentType(mimeType);
			out = httpServletResponse.getOutputStream();
			// out.write((byte[])
			// Util.retonarObjetoDeColecao(relatorioRetorno.values()));
			out.write(relatorioGerado.getArquivoRelatorio());
			out.flush();
			out.close();
		} catch (IOException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
		} catch (ClassNotFoundException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

}
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
package gcom.relatorio;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.SistemaException;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe respsável por montar a apresentação do Relatorio Processado
 * 
 * 
 * @author Thiago Toscano
 * @date 25/05/2006
 */
public class ExibidorProcessamentoTarefaRelatorio extends GcomAction {

	public ActionForward processarExibicaoRelatorio(
			TarefaRelatorio tarefaRelatorio, int tipoRelatorio,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, ActionMapping actionMapping) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		String valorConfirmacao = httpServletRequest.getParameter("confirmado");
		
		if (valorConfirmacao == null || valorConfirmacao.equals("")) {
			int quantidadeRegistroGerado = tarefaRelatorio.calcularTotalRegistrosRelatorio();

			String nomeClasseRelatorio = tarefaRelatorio.getClass().getSimpleName();

			int quantidadeMaximaOnLineRelatorio = ConstantesExecucaoRelatorios.get(nomeClasseRelatorio);

			// se a quantidade a ser processada for maior que a permitida
			if (quantidadeMaximaOnLineRelatorio == ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA
					|| quantidadeRegistroGerado > quantidadeMaximaOnLineRelatorio) {
				
				httpServletRequest
					.setAttribute("caminhoActionConclusao", httpServletRequest.getContextPath().toString() + httpServletRequest.getServletPath().toString());
				
				httpServletRequest.setAttribute("tipoRelatorio", ""+tipoRelatorio);
				
				
				//Fazer lógica de controle
				return montarPaginaConfirmacao("atencao.numero.registro.excedeu.limite.online", httpServletRequest, actionMapping);
				
			}else if(httpServletRequest.getAttribute("telaSucessoRelatorio") != null 
					&& quantidadeRegistroGerado <= quantidadeMaximaOnLineRelatorio){
				
				 sessao.setAttribute("tipoRelatorio", ""+tipoRelatorio);
				 
					// manda o erro para a página no request atual
					reportarErros(httpServletRequest, "erro.sistema");

					// seta o mapeamento de retorno para a tela de erro de popup
					retorno = actionMapping.findForward("telaErroPopup");

				 RelatorioProcessado relatorioProcessado = null;	
					
				 try {
					 relatorioProcessado = GerenciadorExecucaoTarefaRelatorio.analisarExecucao(tarefaRelatorio, tipoRelatorio);
				 } catch (ControladorException ex) {
					 ActionServletException exception = new ActionServletException(ex.getMessage());
					 exception.setUrlBotaoVoltar("telaPrincipal.do");
					 throw exception;
				 }
				 
				 
				 sessao.setAttribute("relatorioProcessado", relatorioProcessado);
				 
				 httpServletRequest.setAttribute("telaSucessoRelatorio", true);
				 
				 montarPaginaSucesso(httpServletRequest, "Relatório Gerado com Sucesso.", "", "", "", "");
				 
				 retorno = actionMapping.findForward("telaSucesso");
			}		
			else {
				retorno = processarRelatorio(tarefaRelatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping, retorno);
			}
			
		} else {
			retorno = processarRelatorio(tarefaRelatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping, retorno);
		}

		return retorno;
		
		
	}

	protected ActionForward processarRelatorio(TarefaRelatorio tarefaRelatorio, int tipoRelatorio, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ActionMapping actionMapping, ActionForward retorno) {
		
		try {
			RelatorioProcessado relatorioProcessado = GerenciadorExecucaoTarefaRelatorio
					.analisarExecucao(tarefaRelatorio, tipoRelatorio);

			if (relatorioProcessado == null) {

				retorno = actionMapping.findForward("telaApresentacaoBatch");

			} else {
				OutputStream out = null;

				// httpServletResponse.addHeader("Content-Disposition","attachment;
				// filename=relatorio");

				String mimeType = null;
				switch (tipoRelatorio) {
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
				out.write(relatorioProcessado.getDados());
				out.flush();
				out.close();

			}
			
			
		} catch (ControladorException ex) {
			 ActionServletException exception = new ActionServletException(ex.getMessage());
			 exception.setUrlBotaoVoltar("telaPrincipal.do");
			 throw exception;
			
		} catch (IOException ex) {
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
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

	public ActionForward processarExibicaoRelatorio(TarefaRelatorio tarefaRelatorio, String tipoRelatorio,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, ActionMapping actionMapping) {

		int tipoIntRelatorio = TarefaRelatorio.TIPO_PDF;
		
		try {
			tipoIntRelatorio = Integer.parseInt(tipoRelatorio);

			switch (tipoIntRelatorio) {
			case TarefaRelatorio.TIPO_HTML:
				break;
			case TarefaRelatorio.TIPO_RTF:
				break;
			case TarefaRelatorio.TIPO_XLS:
				break;
			case TarefaRelatorio.TIPO_PDF:
				break;
			default:
				tipoIntRelatorio = TarefaRelatorio.TIPO_PDF;
				break;
			}

		} catch (Exception e) {
		}

		return processarExibicaoRelatorio(tarefaRelatorio, tipoIntRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
	}
}
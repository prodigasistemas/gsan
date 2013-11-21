/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.relatorio.seguranca;

import gcom.gui.ActionServletException;
import gcom.gui.seguranca.acesso.usuario.FiltrarSolicitacaoAcessoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.FiltrarRelatorioSolicitacaoAcessoHelper;
import gcom.relatorio.seguranca.RelatorioSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1093] Gerar Relatório Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 22/11/2010
 */

public class GerarRelatorioSolicitacaoAcessoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		FiltrarSolicitacaoAcessoActionForm form = 
			(FiltrarSolicitacaoAcessoActionForm) sessao.getAttribute("filtroForm");
		
		FiltrarRelatorioSolicitacaoAcessoHelper filtroHelper = this.setFiltroHelper(form);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		 
		TarefaRelatorio relatorio = new RelatorioSolicitacaoAcesso((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtroSolicitacaoAcesso", filtroHelper);
		
		String periodo = "";
		if(filtroHelper.getDataInicial() != null && filtroHelper.getDataFinal() != null){
			
			periodo = filtroHelper.getDataInicial() + " a " + filtroHelper.getDataFinal();
		}
		relatorio.addParametro("periodo", periodo);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
	/**
	 * Preenche objeto FiltrarRelatorioSolicitacaoAcessoHelper
	 * 
	 * @author Hugo Leonardo
	 * @date 23/11/2010
	 * 
	 * @param FiltrarSolicitacaoAcessoActionForm
	 * @return FiltrarRelatorioSolicitacaoAcessoHelper
	 */
	private FiltrarRelatorioSolicitacaoAcessoHelper setFiltroHelper(FiltrarSolicitacaoAcessoActionForm form) {
		
		FiltrarRelatorioSolicitacaoAcessoHelper filtroHelper = new FiltrarRelatorioSolicitacaoAcessoHelper();
		
		// funcionário solicitante
		if(Util.verificarNaoVazio(form.getIdFuncionarioSolicitante())){
			
			filtroHelper.setIdFuncionarioSolicitante(form.getIdFuncionarioSolicitante());
		}
		
		//Período Inicial e Período Final
		if (form.getDataInicial() != null && !form.getDataInicial().equals("")
				&& form.getDataFinal() != null && !form.getDataFinal().equals("")){

			filtroHelper.setDataInicial(form.getDataInicial());
			filtroHelper.setDataFinal(form.getDataFinal());
		}
		
		// funcionário responsável
		if(Util.verificarNaoVazio(form.getIdFuncionarioSuperior())){
			
			filtroHelper.setIdFuncionarioSolicitante(form.getIdFuncionarioSuperior());
		}
		
		// Empresa
		if(Util.verificarNaoVazio(form.getIdEmpresa())){
			
			filtroHelper.setIdEmpresa(form.getIdEmpresa());
		}
		
		// funcionário usuário
		if(Util.verificarNaoVazio(form.getIdFuncionario())){
	
			filtroHelper.setIdFuncionario(form.getIdFuncionario());
		}
		
		// Nome Usuário
		if(Util.verificarNaoVazio(form.getNomeUsuario())){
	
			filtroHelper.setNomeUsuario(form.getNomeUsuario());
		}
		
		// Unidade de Lotação
		if(Util.verificarNaoVazio(form.getIdLotacao())){
			
			filtroHelper.setIdLotacao(form.getIdLotacao());
		}

		// Situação
		if (form.getIdsSituacao() != null && !form.getIdsSituacao().equals("-1") ){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsSituacao();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				filtroHelper.setIdsSituacao(colecao);
			}
		}

		return filtroHelper;
	}

	
}
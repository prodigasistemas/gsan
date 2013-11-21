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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper;
import gcom.relatorio.seguranca.RelatorioFuncionalidadesOperacoesPorGrupo;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1039] Gerar Relatório de Funcionalidades e Operacoes por Grupo.
 * 
 * @author Hugo Leonardo
 *
 * @date 15/07/2010
 */

public class GerarRelatorioFuncionalidadesOperacoesPorGrupoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioFuncionalidadesOperacoesPorGrupoActionForm form = 
			(GerarRelatorioFuncionalidadesOperacoesPorGrupoActionForm) actionForm;
		
		FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper helper = 
			new FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper();
		
		Fachada fachada = Fachada.getInstancia();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		// Grupo
		String[] idsGrupos = null;
		
		// caso seja a ação do botão imprimir 
		String  botao = httpServletRequest.getParameter("botao");
		
		Collection<Integer> colecaoGrupos = new ArrayList<Integer>();
		
		if(botao != null && !botao.equals("")){
			
			if(form.getIds() != null && form.getIds().length > 0){
				idsGrupos = form.getIds();
			}

			if (idsGrupos != null) {
				
				for (int i = 0; i < idsGrupos.length; i++) {
					if (!idsGrupos[i].equals("")) {
						FiltroGrupo filtroGrupo = new FiltroGrupo();
						filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID, idsGrupos[i]));
						
						Collection colecaoGrupo = fachada.pesquisar(filtroGrupo, Grupo.class.getName());
						
						if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {
							Grupo grupo = (Grupo) Util.retonarObjetoDeColecao(colecaoGrupo);
							colecaoGrupos.add(grupo.getId());
						}
					}
				}		
			}
		}else{
			String idGrupo = httpServletRequest.getParameter("idRegistroGrupo");
			if(idGrupo != null && !idGrupo.equals("")){
				
				colecaoGrupos.add(new Integer(idGrupo));
			}	
		}
		
		if(colecaoGrupos.size() > 0){
			helper.setGrupo(colecaoGrupos);
		}
 
		TarefaRelatorio relatorio = null;
		
		relatorio = new RelatorioFuncionalidadesOperacoesPorGrupo((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper", helper);
		
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
}
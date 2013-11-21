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
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.FiltrarRelatorioAcessosUsuariosHelper;
import gcom.relatorio.seguranca.RelatorioAcessosPorUsuarios;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1040] Gerar Relatório de Acessos por Usuário
 * 
 * @author Hugo Leonardo
 *
 * @date 12/07/2010
 */

public class GerarRelatorioAcessosUsuariosAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioAcessosUsuariosActionForm form = (GerarRelatorioAcessosUsuariosActionForm) actionForm;
		
		FiltrarRelatorioAcessosUsuariosHelper helper = new FiltrarRelatorioAcessosUsuariosHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		// Unidade Organizacional
		if (form.getIdsUnidadeOrganizacional() != null && !form.getIdsUnidadeOrganizacional().equals("-1") ){
			//&& form.getIdsUnidadeOrganizacional().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsUnidadeOrganizacional();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setUnidadeOrganizacional(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Grupo Acesso
		if(form.getIdsGrupoAcesso() != null && !form.getIdsGrupoAcesso().equals("-1") ){
				//&& form.getIdsGrupoAcesso().length > 0){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsGrupoAcesso();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			
			if(colecao.size() > 0){
				helper.setGrupoAcesso(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Modulo
		if ( form.getModulo() != null && 
				!form.getModulo().equals("-1")) {
			
			helper.setModulo(form.getModulo());
			peloMenosUmParametroInformado = true;
		}
		
		// situação usuário
		if (form.getIdsSituacaoUsuario() != null && !form.getIdsSituacaoUsuario().equals("-1") ){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsSituacaoUsuario();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setSituacaoUsuario(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// funcionalidade
		if ( form.getIdFuncionalidade() != null && 
				!form.getIdFuncionalidade().equals("")){
			
			helper.setFuncionalidade(form.getIdFuncionalidade());
			peloMenosUmParametroInformado = true;
		}
		
		// Operacao
		if ( form.getOperacao() != null && 
				!form.getOperacao().equals("-1")) {
			
			helper.setOperacao(form.getOperacao());
			peloMenosUmParametroInformado = true;
		}
		
		// Usuario Tipo
		if(form.getIdsUsuarioTipo() != null && !form.getIdsUsuarioTipo().equals("-1") ){
				//&& form.getIdsUsuarioTipo().length > 0){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsUsuarioTipo();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setUsuarioTipo(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
	
		// Usuario
		if(form.getIdUsuario() != null && !form.getIdUsuario().equals("")){
			helper.setUsuario(form.getIdUsuario());
			peloMenosUmParametroInformado = true;
		}
		
		// Permissão Especial
		if (form.getIdsPermissaoEspecial() != null && !form.getIdsPermissaoEspecial().equals("-1") ){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsPermissaoEspecial();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setPermissaoEspecial(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		 
		TarefaRelatorio relatorio = new RelatorioAcessosPorUsuarios((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtrarRelatorioAcessosUsuariosHelper", helper);
		
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
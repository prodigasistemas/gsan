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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarAlteracaoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		ActionForward retorno = actionMapping
				.findForward("filtrarAlteracaoAtualizacaoCadastral");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;
        
        Collection<ConsultarMovimentoAtualizacaoCadastralHelper> colecaoConsultarMovimentoAtualizacaoCadastralHelper = new ArrayList();
        
		boolean peloMenosUmParametroInformado = false;
        
        if(form.getIdEmpresa() != null && !form.getIdEmpresa().trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	peloMenosUmParametroInformado = true;
        }
        
        if(form.getIdLeiturista() != null && !form.getIdLeiturista().equals("")){        	      	
        	peloMenosUmParametroInformado = true;
        }
        
        if(form.getIdArquivo() != null && !form.getIdArquivo().equals("")){        	      	
        	peloMenosUmParametroInformado = true;
        }
        
        if(form.getExibirCampos() != null && !form.getExibirCampos().equals("")){
        	peloMenosUmParametroInformado = true;
        }
        
        if (form.getColunaImoveisSelecionados() != null && !form.getColunaImoveisSelecionados().equals("")){
        	peloMenosUmParametroInformado = true;
        }
        
        Collection colunaImoveisSelecionados = new ArrayList();
        
        if (form.getColunaImoveisSelecionados() != null && !form.getColunaImoveisSelecionados().equals("-1")){
	        String[] colunaSelecionados = form.getColunaImoveisSelecionados();
	        for (int i = 0; i < colunaSelecionados.length ; i++){
	        	colunaImoveisSelecionados.add(colunaSelecionados[i]);
	        }
        }
        
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		colecaoConsultarMovimentoAtualizacaoCadastralHelper = fachada.pesquisarMovimentoAtualizacaoCadastral(form.getIdArquivo(), form.getIdEmpresa(), form.getIdLeiturista(), form.getExibirCampos(), colunaImoveisSelecionados);
        
        if( colecaoConsultarMovimentoAtualizacaoCadastralHelper == null || colecaoConsultarMovimentoAtualizacaoCadastralHelper.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", "exibirFiltrarAlteracaoAtualizacaoCadastralAction.do", null, new String[] {});
        }
        sessao.setAttribute("colecaoConsultarMovimentoAtualizacaoCadastralHelper",colecaoConsultarMovimentoAtualizacaoCadastralHelper);

		return retorno;
	}

}

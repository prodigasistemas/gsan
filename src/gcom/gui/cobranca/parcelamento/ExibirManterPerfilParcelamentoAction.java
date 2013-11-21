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
package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter o Perfil de Parcelamento
 * @author Vivianne Sousa
 * @created 11/05/2006
 */

public class ExibirManterPerfilParcelamentoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterPerfilParcelamento");
		HttpSession sessao = httpServletRequest.getSession(false);
		//Fachada fachada = Fachada.getInstancia();
        Collection collectionParcelamentoPerfil = null;

		// Parte da verificação do filtro
        FiltroParcelamentoPerfil filtroParcelamentoPerfil = null;

		// Verifica se o filtro foi informado pela página de filtragem de Perfil de Parcelamento
		if (sessao.getAttribute("filtroParcelamentoPerfil") != null) {
			filtroParcelamentoPerfil = (FiltroParcelamentoPerfil) sessao
					.getAttribute("filtroParcelamentoPerfil");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterPerfilParcelamento"))) {
	
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("categoria");
			
			filtroParcelamentoPerfil.setCampoOrderBy(FiltroParcelamentoPerfil.RESOLUCAO_DIRETORIA_NUMERO);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroParcelamentoPerfil, ParcelamentoPerfil.class.getName());
			collectionParcelamentoPerfil = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0004] Nenhum registro encontrado				
			if (collectionParcelamentoPerfil == null || collectionParcelamentoPerfil.isEmpty()) {
				// Nenhuma Perfil de Parcelamento cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (collectionParcelamentoPerfil.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarPerfilParcelamento");
				ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil)collectionParcelamentoPerfil.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (parcelamentoPerfil.getId()).toString());
			}else{
				sessao.setAttribute("collectionParcelamentoPerfil", collectionParcelamentoPerfil);
			}

		}
		
		sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
		sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");
        return retorno;
        
    }

}
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
package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviadatipo.FiltroTabelaAuxiliarAbreviadaTipo;
import gcom.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipo;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class PesquisarTabelaAuxiliarAbreviadaTipoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornoPesquisaTabelaAuxiliarAbreviadaTipo");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o parametro passado no request
		DadosTelaTabelaAuxiliarAbreviadaTipo dados = (DadosTelaTabelaAuxiliarAbreviadaTipo) sessao
				.getAttribute("dados");

		// Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String descricao = (String) pesquisarActionForm.get("descricao");
		String descricaoAbreviada = (String) pesquisarActionForm.get("descricaoAbreviada");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisaDescricao");
		

		// cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo = new FiltroTabelaAuxiliarAbreviadaTipo();

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviadaTipo.ID, id));
		}
		
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && 
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAO, descricao));
			} else {
				filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(
					new ComparacaoTexto(FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAO, descricao));
			}
		}		

		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ComparacaoTexto(
							FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAOABREVIADA,
							descricaoAbreviada));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		Collection colecaoTabelaAuxiliarAbreviadaTipo = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		TabelaAuxiliarAbreviadaTipo tabelaAuxiliarAbreviadaTipo = (TabelaAuxiliarAbreviadaTipo) dados
				.getTabelaAuxiliar();

		// Faz a busca das empresas
		colecaoTabelaAuxiliarAbreviadaTipo = fachada.pesquisar(
				filtroTabelaAuxiliarAbreviadaTipo, tabelaAuxiliarAbreviadaTipo
						.getClass().getName());

		if (colecaoTabelaAuxiliarAbreviadaTipo == null
				|| colecaoTabelaAuxiliarAbreviadaTipo.isEmpty()) {
			// Nenhum municipio cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, dados.getTitulo());
		} else{
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTabelaAuxiliarAbreviadaTipo, tabelaAuxiliarAbreviadaTipo.getClass().getName());
			colecaoTabelaAuxiliarAbreviadaTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			sessao.setAttribute("colecaoTabelaAuxiliarAbreviadaTipo", colecaoTabelaAuxiliarAbreviadaTipo);
		}

		// Repassa o tipo da pesquisa para a tela de resultado
		httpServletRequest.setAttribute("tipoPesquisa", httpServletRequest
				.getParameter("tipoPesquisa"));

		// Repassa o caminho do resultado para a tela de resultado
		httpServletRequest.setAttribute("caminhoRetorno", httpServletRequest
				.getParameter("caminhoRetorno"));

		// Devolve o mapeamento de retorno
		return retorno;

	}
}
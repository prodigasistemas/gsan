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
package gcom.gui.relatorio.financeiro;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pré-processamento da primeira página de 
 * [UC0718] Gerar Relatório da Evolução do Contas a Receber Contábil
 * 
 */
public class ExibirGerarRelatorioEvolucaoContasAReceberContabilAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioEvolucaoContasAReceberContabil");

		Fachada fachada = Fachada.getInstancia();
		//ActionForm
		GerarRelatorioEvolucaoContasAReceberContabilActionForm form = 
			(GerarRelatorioEvolucaoContasAReceberContabilActionForm) actionForm;
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		Collection<GerenciaRegional> gerenciasRegionais = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());

		httpServletRequest.setAttribute("colecaoGerenciaRegional",
				gerenciasRegionais);
		
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade"); 
		// Pesquisando a Localidade pelo código que o usuário digitou
		if(pesquisarLocalidade != null && pesquisarLocalidade.equalsIgnoreCase("OK")){
			String codigoLocalidade = form.getCodigoLocalidade();
			if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
				pesquisarLocalidade(httpServletRequest, form);
			}
			// Localidade
			if (form.getCodigoLocalidade() != null && 
					!form.getCodigoLocalidade().equals("") &&
					form.getDescricaoLocalidade() != null && 
					!form.getDescricaoLocalidade().equals("")) {
				httpServletRequest.setAttribute("localidadeEncontrada", true);
			}
		}
		String pesquisarMunicipio = httpServletRequest.getParameter("pesquisarMunicipio");
		//Pesquisando o município pelo código que o usuário digitou
		if(pesquisarMunicipio != null && pesquisarMunicipio.equalsIgnoreCase("OK")){
			String codigoMunicipio = form.getCodigoMunicipio();
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				pesquisarMunicipio(httpServletRequest, form);
			}
			// Município
			if (form.getCodigoMunicipio() != null && 
					!form.getCodigoMunicipio().equals("") &&
					form.getDescricaoMunicipio() != null && 
					!form.getDescricaoMunicipio().equals("")) {
				httpServletRequest.setAttribute("municipioEncontrado", true);
			}
		}

		return retorno;
	}
	
	/*
	 * Métodos que farão a pesquisa da localidade ou do município 
	 * informado pelo usuário (Pressionando a telca ENTER).
	 */
	private void pesquisarLocalidade(HttpServletRequest request, 
			GerarRelatorioEvolucaoContasAReceberContabilActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getCodigoLocalidade()));
		
		Collection pesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(pesquisa);
			
			form.setCodigoLocalidade("" + localidade.getId());
			form.setDescricaoLocalidade(localidade.getDescricao());
		} else {
			form.setCodigoLocalidade("");
			form.setDescricaoLocalidade("Localidade Inexistente");
		}
	}
	
	private void pesquisarMunicipio(HttpServletRequest request, 
			GerarRelatorioEvolucaoContasAReceberContabilActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, form.getCodigoMunicipio()));
		
		Collection pesquisa = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(pesquisa);
			
			form.setCodigoMunicipio("" + municipio.getId());
			form.setDescricaoMunicipio(municipio.getNome());
		} else {
			form.setCodigoMunicipio("");
			form.setDescricaoMunicipio("Município Inexistente");
		}
	}
}

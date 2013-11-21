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
package gcom.gui.cadastro;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.tarifasocial.bean.CriterioSelecaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vivianne Sousa
 * @data 23/03/2011
 */
public class ExibirGerarComandoCartasTarifaSocialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirGerarComandoCartasTarifaSocial");

		Fachada fachada = Fachada.getInstancia();
		GerarComandoCartasTarifaSocialActionForm form = (GerarComandoCartasTarifaSocialActionForm) actionForm;
		 
		if(httpServletRequest.getParameter("menu") != null){
			limparForm(form);
		}
		
		//pesquisa Gerência Regional
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		Collection<GerenciaRegional> gerenciasRegionais = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());
		httpServletRequest.setAttribute("colecaoGerenciaRegional",gerenciasRegionais);
		
		//pesquisa Unidade de Negócio
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		
		montarTabelaCriteriosSelecao(httpServletRequest);
		
		//Pega os codigos que o usuario digitou para a pesquisa direta de localidade
		String codigoLocalidade = httpServletRequest.getParameter("codigoLocalidade");

		if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
			pesquisarLocalidade(codigoLocalidade, httpServletRequest);

		}
		
		if(form.getTipoCarta() != null && !form.getTipoCarta().equals("")){
			httpServletRequest.setAttribute("tipoCarta", (new Integer(form.getTipoCarta())).intValue());
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa uma localidade informada e prepara os dados para exibição na tela
	 */
	private void pesquisarLocalidade(String idLocalidade,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhuma localidade for encontrada a mensagem é enviada para a página
		if (localidadePesquisada == null || localidadePesquisada.isEmpty()) {
			// [FS0001 - Verificar existência de dados]
			httpServletRequest.setAttribute("codigoLocalidade", "");
			httpServletRequest.setAttribute("descricaoLocalidade","Localidade Inexistente".toUpperCase());
		}

		// obtem o imovel pesquisado
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = localidadePesquisada.iterator().next();
			// Manda a Localidade pelo request
			httpServletRequest.setAttribute("codigoLocalidade", idLocalidade);
			httpServletRequest.setAttribute("descricaoLocalidade", localidade.getDescricao());
		}

	}
	
	
	private void montarTabelaCriteriosSelecao(HttpServletRequest httpServletRequest) {

		CriterioSelecaoHelper helper = new CriterioSelecaoHelper();
		Collection colecaoCriterios = new ArrayList();
		
		helper.setCodigoCriterio(new Integer(1));
		helper.setDescricaoCriterio("Cliente sem CPF cadastrado");
		colecaoCriterios.add(helper);
		
		helper = new CriterioSelecaoHelper();
		helper.setCodigoCriterio(new Integer(2));
		helper.setDescricaoCriterio("Cliente sem o número da identidade");
		colecaoCriterios.add(helper);
		
		helper = new CriterioSelecaoHelper();
		helper.setCodigoCriterio(new Integer(3));
		helper.setDescricaoCriterio("Imóvel sem o número do contrato de energia");
		colecaoCriterios.add(helper);
		
		helper = new CriterioSelecaoHelper();
		helper.setCodigoCriterio(new Integer(4));
		helper.setDescricaoCriterio("Imóvel com pendência nos dados do contrato de energia");
		colecaoCriterios.add(helper);
		
		helper = new CriterioSelecaoHelper();
		helper.setCodigoCriterio(new Integer(5));
		helper.setDescricaoCriterio("Cliente com cartão do tipo programa social");
		colecaoCriterios.add(helper);
		
		helper = new CriterioSelecaoHelper();
		helper.setCodigoCriterio(new Integer(6));
		helper.setDescricaoCriterio("Cliente com validade do cartão seguro desemprego vencido");
		colecaoCriterios.add(helper);
		
		helper = new CriterioSelecaoHelper();
		helper.setCodigoCriterio(new Integer(7));
		helper.setDescricaoCriterio("Cliente com renda comprovada");
		colecaoCriterios.add(helper);
		
		helper = new CriterioSelecaoHelper();
		helper.setCodigoCriterio(new Integer(8));
		helper.setDescricaoCriterio("Cliente com renda declarada");
		colecaoCriterios.add(helper);
		
		helper = new CriterioSelecaoHelper();
		helper.setCodigoCriterio(new Integer(9));
		helper.setDescricaoCriterio("Imóvel com mais de uma economia");
		colecaoCriterios.add(helper);
		
		helper = new CriterioSelecaoHelper();
		helper.setCodigoCriterio(new Integer(10));
		helper.setDescricaoCriterio("Cliente sem recadastramento há mais de 02(dois) anos");
		colecaoCriterios.add(helper);
		
		httpServletRequest.setAttribute("colecaoCriterios",colecaoCriterios);

	}
	
	private void limparForm(GerarComandoCartasTarifaSocialActionForm form) {

		form.setCodigoLocalidade("");
		
		form.setDescricaoLocalidade("");
		
		form.setUnidadeNegocioId("");
		
		form.setGerenciaRegionalId("");
		
	    form.setTipoCarta("");

	    form.setQtdeDiasAtraso("");
	    
	    form.setPrazoComparecerCompesa("");
	    
	    form.setAnoMesPesquisaInicial("");
	    
	    form.setAnoMesPesquisaFinal("");

	}
}

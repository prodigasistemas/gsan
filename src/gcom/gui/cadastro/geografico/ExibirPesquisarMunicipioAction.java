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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o pré-processamento da página de pesquisa de responsavel
 * superior
 * 
 * @author Sávio Luiz
 * @created 26 de Abril de 2005
 */

public class ExibirPesquisarMunicipioAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarMunicipio");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("objetoConsulta") == null) {
			pesquisarActionForm.set("nomeMunicipio", "");
			pesquisarActionForm.set("idRegiaoDesenvolvimento", null);
			pesquisarActionForm.set("idRegiao", null);
			pesquisarActionForm.set("idMicrorregiao", null);
			pesquisarActionForm.set("idUnidadeFederacao", null);

		}
		
		// Verifica se o pesquisar município foi chamado a partir do inserir município
		// e em caso afirmativo recebe o parâmetro e manda-o pela sessão para
		// ser verificado no municipio_resultado_pesquisa e depois retirado da
		// sessão no ExibirFiltrarMunicipioAction
		if (httpServletRequest.getParameter("consulta") != null) {
			String consulta = httpServletRequest.getParameter("consulta");
			sessao.setAttribute("consulta", consulta);
		}else{
			sessao.removeAttribute("consulta");
		}

		
		// cria os filtros para pesquisar os objetos
		FiltroRegiaoDesenvolvimento filtroRegiaoDesenvolvimento = new FiltroRegiaoDesenvolvimento();

		FiltroRegiao filtroRegiao = new FiltroRegiao();

		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();

		// inicializa a coleção
		Collection regiaoDesenvolvimentos = null;

		// inicializa a coleção
		Collection regioes = null;

		// inicializa a coleção
		Collection microrregioes = null;

		// inicializa a coleção
		Collection unidadesFederacao = null;

		// INICIO-------Parte que trata do pesquisa de dependencia

		// caso seja escolhido a região
		Integer idRegiao = (Integer) pesquisarActionForm.get("idRegiao");

		if (idRegiao != null
				&& idRegiao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

			filtroMicrorregiao.adicionarParametro(new ParametroSimples(
					FiltroMicrorregiao.REGIAO_ID, idRegiao));
			
			filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);

			if( httpServletRequest.getParameter("indicadorUsoTodos") == null){
				sessao.removeAttribute("indicadorUsoTodos");
				filtroMicrorregiao.adicionarParametro(new ParametroSimples(
						FiltroMicrorregiao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			}

			microrregioes = fachada.pesquisar(filtroMicrorregiao,
					Microrregiao.class.getName());

			if (microrregioes == null || microrregioes.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"microrregioes");
			} else {
				sessao.setAttribute("microrregioes", microrregioes);
				httpServletRequest.setAttribute("nomeCampo", "idMicrorregiao");
			}

			// FIM-------Parte que trata do pesquisa de dependencia
		} else {
			if( httpServletRequest.getParameter("indicadorUsoTodos") == null){
				sessao.removeAttribute("indicadorUsoTodos");
				filtroRegiaoDesenvolvimento
						.adicionarParametro(new ParametroSimples(
								FiltroRegiaoDesenvolvimento.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
			}	
			
			filtroRegiaoDesenvolvimento.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);
			
			regiaoDesenvolvimentos = fachada.pesquisar(
					filtroRegiaoDesenvolvimento, RegiaoDesenvolvimento.class
							.getName());

			if (regiaoDesenvolvimentos == null
					|| regiaoDesenvolvimentos.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"regiao desenvolvimento");
			} else {
				sessao.setAttribute("regiaoDesenvolvimentos",
						regiaoDesenvolvimentos);
			}

			filtroRegiao.adicionarParametro(new ParametroSimples(
					FiltroRegiao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroRegiao.setCampoOrderBy(FiltroRegiao.DESCRICAO);
			
			regioes = fachada.pesquisar(filtroRegiao, Regiao.class.getName());

			if (regioes == null || regioes.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null, "regiao");
			} else {
				sessao.setAttribute("regioes", regioes);
			}

			filtroMicrorregiao.adicionarParametro(new ParametroSimples(
					FiltroMicrorregiao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);
			microrregioes = fachada.pesquisar(filtroMicrorregiao,
					Microrregiao.class.getName());

			if (microrregioes == null || microrregioes.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"microrregiao");
			} else {
				sessao.setAttribute("microrregioes", microrregioes);
			}

			filtroUnidadeFederacao.setCampoOrderBy(FiltroUnidadeFederacao.DESCRICAO);
			
			unidadesFederacao = fachada.pesquisar(filtroUnidadeFederacao,
					UnidadeFederacao.class.getName());

			if (unidadesFederacao == null || unidadesFederacao.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"unidade federacao");
			} else {
				sessao.setAttribute("unidadesFederacao", unidadesFederacao);
			}

			// envia uma flag que será verificado no
			// municipio_resultado_pesquisa.jsp
			// para saber se irá usar o enviar dados ou o enviar dados
			// parametros
			if (httpServletRequest
					.getParameter("caminhoRetornoTelaPesquisaMunicipio") != null) {
				sessao
						.setAttribute(
								"caminhoRetornoTelaPesquisaMunicipio",
								httpServletRequest
										.getParameter("caminhoRetornoTelaPesquisaMunicipio"));
				if(sessao.getAttribute("caminhoRetornoTelaPesquisaBairro") != null)
				{
					sessao.setAttribute("caminho",sessao.getAttribute("caminhoRetornoTelaPesquisaBairro")); 
				}
			}else{
				
				if(sessao.getAttribute("caminhoRetornoTelaPesquisaMunicipio") != null){
					sessao.setAttribute("caminho",sessao.getAttribute("caminhoRetornoTelaPesquisaMunicipio")); 
				}else{
					sessao
					.removeAttribute("caminhoRetornoTelaPesquisaMunicipio");
				}
			}
		}
		
		pesquisarActionForm.set("tipoPesquisa", ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		if( httpServletRequest.getParameter("indicadorUsoTodos") != null){
			sessao.setAttribute("indicadorUsoTodos",
				httpServletRequest.getParameter("indicadorUsoTodos"));
		}
		return retorno;
	}
}

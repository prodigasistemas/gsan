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
package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço - Exibir
 * 
 * @author Vivianne Sousa
 *
 * @date 12/04/2011
 */
public class ExibirGerarRelatorioOSExecutadasPrestadoraServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioOSExecutadasPrestadoraServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		GerarRelatorioOSExecutadasPrestadoraServicoActionForm form = 
			(GerarRelatorioOSExecutadasPrestadoraServicoActionForm) actionForm;
		
		String menu = httpServletRequest.getParameter("menu");
		if (menu != null && !menu.equalsIgnoreCase("")){
			pesquisarRegiao(sessao);
			pesquisarMicroregiao(sessao);
			pesquisarMunicipio(sessao);
			
			
			form.setPeriodoEncerramentoInicial("");
			form.setPeriodoEncerramentoFinal("");
			form.setEmpresa("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			form.setIdGerencia("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			form.setIdUnidadeNegocio("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			form.setCodigoLocalidade("");
			form.setDescricaoLocalidade("");
			form.setColecaoRegiao(null);
			form.setColecaoMicrorregiao(null);
			form.setColecaoMunicipio(null);
			form.setOpcaoRelatorio("2");
			
			
		}
		
		pesquisarEmpresa(form);
		pesquisarGerenciaRegional(httpServletRequest);
		pesquisarUnidadeNegocio(httpServletRequest,form);
		
		String codigoLocalidade = httpServletRequest.getParameter("codigoLocalidade");

		if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
			pesquisarLocalidade(codigoLocalidade, httpServletRequest);

		}
		return retorno;
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarEmpresa(GerarRelatorioOSExecutadasPrestadoraServicoActionForm form) {
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, new Integer(13)));
//		filtroEmpresa.adicionarParametro(new ParametroSimples(
//				FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection<Empresa> colecaoEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());
		Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresa);
		// [FS0001 - Verificar Existencia de dados]
		if ( (colecaoEmpresa == null) || (colecaoEmpresa.size() == 0) ) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
		}else {
			form.setEmpresa(empresa.getDescricao());
		}
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());


		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioOSExecutadasPrestadoraServicoActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(form.getIdGerencia() != null && 
			!form.getIdGerencia().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, form.getIdGerencia()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Negócio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		}
	}

	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarRegiao(HttpSession sessao){
		
		FiltroRegiao filtroRegiao = new FiltroRegiao();

		filtroRegiao.setCampoOrderBy(FiltroRegiao.DESCRICAO);

		Collection<Regiao> colecaoRegiao = this.getFachada().pesquisar(filtroRegiao,Regiao.class.getName());

		if (colecaoRegiao == null || colecaoRegiao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Região");
		} else {
			sessao.setAttribute("colecaoRegiao",colecaoRegiao);
		}
		
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarMicroregiao(HttpSession sessao){
		
		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

		filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);

		Collection<Microrregiao> colecaoMicrorregiao = this.getFachada().pesquisar(filtroMicrorregiao,Microrregiao.class.getName());

		if (colecaoMicrorregiao == null || colecaoMicrorregiao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Microregião");
		} else {
			sessao.setAttribute("colecaoMicrorregiao",colecaoMicrorregiao);
		}

	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarMunicipio(HttpSession sessao){
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.setCampoOrderBy(FiltroMunicipio.NOME);
		
		Collection<Municipio> colecaoMunicipio = this.getFachada().pesquisar(filtroMunicipio,Municipio.class.getName());

		if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Município");
		} else {
			sessao.setAttribute("colecaoMunicipio",colecaoMunicipio);
		}

	}

	/**
	 * Pesquisa uma localidade informada e prepara os dados para exibição na tela
	 * @author Vivianne Sousa
	 * @date 13/04/2011
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
	
}
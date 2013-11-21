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


package gcom.gui.relatorio.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
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
 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2011
 */

public class ExibirGerarRelatorioDevolucaoPagamentosDuplicidadeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioDevolucaoPagamentosDuplicidadeAction");
		
		GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form = 
			(GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Primeira vez que carrega a pagina
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")) {
			
			//form.setSituacaoUsuario("0");
		}
		
		this.pesquisarGerencia(httpServletRequest,form);
		this.pesquisarUnidadeNegocio(httpServletRequest,form);
		this.pesquisarPerfilImovel(httpServletRequest,form);
		this.pesquisarCategoriaImovel(httpServletRequest,form);

		// Seta os request´s encontrados
		if ( httpServletRequest.getParameter("validar") != null && 
				httpServletRequest.getParameter("validar").equals("localidade")) {
			
			this.validarLocalidade(httpServletRequest, form, sessao);
		}
		
		// Pega os codigos que o usuario digitou para a pesquisa direta da Localidade
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().trim().equals("")) {
			
			this.pesquisarLocalidade( httpServletRequest, form);
		}
		
		return retorno;
	}
	
	private void pesquisarGerencia(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples( FiltroGerenciaRegional.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);		

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

		if ( Util.isVazioOrNulo(colecaoGerenciaRegional)) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Gerência Regional");
		} else {
			
			httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		}
	}
	
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		if(form.getIdGerencia() != null && !form.getIdGerencia().equals("-1")){
			
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.GERENCIA, 
					new Integer(form.getIdGerencia())));
		}
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade Negócio");
		} else {
			
			httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}
	}

	private void validarLocalidade(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form, HttpSession sessao){
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidade()));

		Collection<Localidade> localidadePesquisada = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if ( !Util.isVazioOrNulo(localidadePesquisada)) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
		
			if(form.getIdGerencia() != null && !form.getIdGerencia().equals("-1") 
					&& localidade.getGerenciaRegional().getId().compareTo(new Integer(form.getIdGerencia())) != 0){
				
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples( FiltroGerenciaRegional.ID, 
						form.getIdGerencia()));		
	
				Collection colecaoGerencia = 
					this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
				
				GerenciaRegional gerenciaRegional = (GerenciaRegional)Util.retonarObjetoDeColecao(colecaoGerencia);
				
				form.setIdLocalidade("");
				form.setNomeLocalidade("");
				httpServletRequest.setAttribute("limpar", "localidade");
				
				throw new ActionServletException("atencao.localidade_nao_percente_gerencia_regional", null, gerenciaRegional.getNome());
			}
			
			if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("-1") 
					&& localidade.getUnidadeNegocio().getId().compareTo(new Integer(form.getIdUnidadeNegocio())) != 0){
				
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				
				filtroUnidadeNegocio.adicionarParametro(
						new ParametroSimples(FiltroUnidadeNegocio.ID, form.getIdUnidadeNegocio()));		
	
				Collection colecaoUnidadeNegocio = 
					this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
				
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio)Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
				
				form.setIdLocalidade("");
				form.setNomeLocalidade("");
				httpServletRequest.setAttribute("limpar", "localidade");
				
				throw new ActionServletException("atencao.localidade_nao_percente_unidade_negocio", null, unidadeNegocio.getNome());
			}
		}
	}
	
	private void pesquisarPerfilImovel(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		
		filtroImovelPerfil.setConsultaSemLimites(true);
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

		filtroImovelPerfil.adicionarParametro(
				new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoImovelPerfil = 
			this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

		if ( Util.isVazioOrNulo(colecaoImovelPerfil)) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Imóvel Perfil");
		} else {
			
			httpServletRequest.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		}
	}
	
	private void pesquisarCategoriaImovel(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form){
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

		filtroCategoria.adicionarParametro(
				new ParametroSimples( FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());

		if ( Util.isVazioOrNulo(colecaoCategoria)) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Categoria");
		} else {
			
			httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);
		}
	}
	
	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, 
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form) {

		// Pesquisa a usuário na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidade()));
		
		if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("-1")){
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID, 
					new Integer(form.getIdUnidadeNegocio())));
		}
		
		if(form.getIdGerencia() != null && !form.getIdGerencia().equals("-1")){
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, 
					new Integer(form.getIdGerencia())));
		}

		Collection<Localidade> localidadePesquisada = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if ( !Util.isVazioOrNulo(localidadePesquisada)) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			
			form.setIdLocalidade("" + localidade.getId());
			form.setNomeLocalidade( localidade.getDescricao());

		} else {
			form.setIdLocalidade("");
			form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
			httpServletRequest.setAttribute("localidadeInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
		}
	}
}

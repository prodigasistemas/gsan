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
package gcom.gui.cobranca.spcserasa;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.Negativador;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade receber os parâmetros que servirão para
 * informar os dados para geração de relatório/consulta
 * 
 * @author Marcio Roberto
 * @date 21/02/2008
 */
public class InformarDadosParaConsultaNegativacaoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("consultarResumoNegativacaoParametros");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"consultaNegativacaoWizardAction",
				"exibirInformarDadosConsultaNegativacaoAction",
				"cancelarConsultarResumoNegativacaoAction",
				"exibirInformarDadosConsultaNegativacaoAction",
				"informarDadosParaConsultaNegativacaoAction.do");

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ParametrosPrimeiraAbaA.gif",
						"ParametrosPrimeiraAbaD.gif",
						"exibirDadosGeracaoConsultaNegativacaoAction", ""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "ResumoUltimaAbaA.gif", "ResumoUltimaAbaD.gif",
						"exibirConsultarResumoNegativacaoAction", ""));

		// manda o statusWizard para a sessão
		sessao.setAttribute("statusWizard", statusWizard);
		// ResumoConsultaNegativacaoActionForm
		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;
		
		DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper = 
			(DadosConsultaNegativacaoHelper)sessao.getAttribute("dadosConsultaNegativacaoHelper");
		
		// [Fluxo Principal] 4.1.1
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 13/01/2011
		//********************************************************
		//form.setIdNegativador(dadosConsultaNegativacaoHelper.getIdNegativador().toString());
		String nomeNegativador = null;
		//if(form.getIdNegativador() != null
		//		&& !form.getIdNegativador().equals("")){
		if(form.getArrayNegativador() != null
				&& form.getArrayNegativador().length > 0){
			
			Collection colecaoNegativador = new ArrayList();
			for (int x = 0; x < form.getArrayNegativador().length; x++) {
				colecaoNegativador.add(form.getArrayNegativador()[x]);
			}
			
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			
			filtroNegativador.adicionarParametro(new ParametroSimplesIn(
					FiltroNegativador.ID, colecaoNegativador));
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			Collection colecaoNegativadorPesquisa = fachada.pesquisar(
					filtroNegativador, Negativador.class.getName());

			if (colecaoNegativadorPesquisa != null && !colecaoNegativadorPesquisa.isEmpty()) {
				Iterator iColecaoNegativadorPesquisa = colecaoNegativadorPesquisa.iterator();
				boolean primeiro = true;
				nomeNegativador = "";
				
				while (iColecaoNegativadorPesquisa.hasNext()) {
					Negativador negativador = (Negativador) iColecaoNegativadorPesquisa.next();
					nomeNegativador = nomeNegativador + negativador.getCliente().getNome();
					
					if (colecaoNegativadorPesquisa.size() > 1 && primeiro) {
						nomeNegativador = nomeNegativador + " / ";
						primeiro = false;
					}
				}
				form.setNomeNegativador(nomeNegativador);
			}
		}
		//*******************************************************************
		
		// [Fluxo Principal] 4.1.3
		if (dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoInicio() != null){
			form.setPeriodoEnvioNegativacaoInicio(dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoInicio().toString());
		}
		if (dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoFim() != null){
			form.setPeriodoEnvioNegativacaoFim(dadosConsultaNegativacaoHelper.getPeriodoEnvioNegativacaoFim().toString());
		}
		
		// [Fluxo Principal] 4.1.4
		if (dadosConsultaNegativacaoHelper.getTituloComando() != null){
			form.setTituloComando(dadosConsultaNegativacaoHelper.getTituloComando());
		}

		// [Fluxo Principal] 4.1.5
		if (dadosConsultaNegativacaoHelper.getIdEloPolo() != null){
			form.setIdEloPolo(dadosConsultaNegativacaoHelper.getIdEloPolo().toString());
			if (dadosConsultaNegativacaoHelper.getDescricaoEloPolo() != null){
				form.setDescricaoEloPolo(dadosConsultaNegativacaoHelper.getDescricaoEloPolo());
			}
		}

		// [Fluxo Principal] 4.1.6
		if (dadosConsultaNegativacaoHelper.getIdLocalidade() != null){
			form.setIdLocalidade(dadosConsultaNegativacaoHelper.getIdLocalidade().toString());
			if (dadosConsultaNegativacaoHelper.getDescricaoLocalidade() != null){
				form.setDescricaoLocalidade(dadosConsultaNegativacaoHelper.getDescricaoLocalidade());
			}
		}
		
		// [Fluxo Principal] 4.1.7 
		if (dadosConsultaNegativacaoHelper.getIdSetorComercial() != null){
			form.setIdSetorComercial(dadosConsultaNegativacaoHelper.getIdSetorComercial().toString());
			if (dadosConsultaNegativacaoHelper.getDescricaoSetorComercial() != null){
				form.setDescricaoSetorComercial(dadosConsultaNegativacaoHelper.getDescricaoSetorComercial());
			}
		}
		
		// [Fluxo Principal] 4.1.8 
		if (dadosConsultaNegativacaoHelper.getIdQuadra() != null){
			form.setIdQuadra(dadosConsultaNegativacaoHelper.getIdQuadra().toString());
			if (dadosConsultaNegativacaoHelper.getDescricaoQuadra() != null){
				form.setDescricaoQuadra(dadosConsultaNegativacaoHelper.getDescricaoQuadra());
			}
		}


		// INICIO GRUPO DE COBRANCA ////////////////////////////////////////////////////////////////////////////////////////		
		CobrancaGrupo cobrancaGrupoColecao = new CobrancaGrupo();
		cobrancaGrupoColecao.setId(-1);
		Collection colecaoCobrancaGrupo = new ArrayList();
		if (!dadosConsultaNegativacaoHelper.getColecaoCobrancaGrupo().isEmpty()) { // cobrancaGrupo != null
			colecaoCobrancaGrupo.addAll(dadosConsultaNegativacaoHelper.getColecaoCobrancaGrupo());
//			cobrancaGrupoColecao.setDescricao("OPÇÕES SELECIONADAS");
//			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
//
//			Iterator colecaoCobrancaGrupoIterator = dadosConsultaNegativacaoHelper.getColecaoCobrancaGrupo().iterator(); 
//			while(colecaoCobrancaGrupoIterator.hasNext()){
//				CobrancaGrupo cobrancaGrupoObjeto = (CobrancaGrupo) colecaoCobrancaGrupoIterator.next();
//
//				FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
//				
//				filtroCobrancaGrupo
//				.adicionarParametro(new ParametroSimples(
//						FiltroCobrancaGrupo.ID, cobrancaGrupoObjeto.getId()));
//
//				filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
//
//				Collection colecaoCobrancaGrupoPesquisa = fachada.pesquisar(
//						filtroCobrancaGrupo, CobrancaGrupo.class.getName());
//
//				if (colecaoCobrancaGrupoPesquisa != null
//						&& !colecaoCobrancaGrupoPesquisa.isEmpty()) {
//					colecaoCobrancaGrupo.addAll(colecaoCobrancaGrupoPesquisa);
//				}
//			}
			
		} else {
			cobrancaGrupoColecao.setDescricao("TODOS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
		}
		sessao.setAttribute("colecaoCobrancaGrupoResultado",
						colecaoCobrancaGrupo);
		// FIM GRUPO DE COBRANCA ////////////////////////////////////////////////////////////////////////////////////////		
		
// INICIO GERENCIA REGIONAL /////////////////////////////////////////////////////////////////////////////////////////		
		GerenciaRegional gerenciaRegionalColecao = new GerenciaRegional();
		gerenciaRegionalColecao.setId(-1);
		Collection colecaoGerenciaRegional = new ArrayList();
		if (!dadosConsultaNegativacaoHelper.getColecaoGerenciaRegional().isEmpty()) { // gerenciaRegional != null
			colecaoGerenciaRegional.addAll(dadosConsultaNegativacaoHelper.getColecaoGerenciaRegional());
//			gerenciaRegionalColecao.setNomeAbreviado("OPÇÕES SELECIONADAS");
//			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
//			
//			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
//
//			filtroGerenciaRegional
//			.adicionarParametro(new ParametroSimples(
//					FiltroGerenciaRegional.ID, gerenciaRegional));
//			
//			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
//			Collection colecaoGerenciaRegionalPesquisa = fachada.pesquisar(
//					filtroGerenciaRegional, GerenciaRegional.class.getName());
//			if (colecaoGerenciaRegionalPesquisa != null
//					&& !colecaoGerenciaRegionalPesquisa.isEmpty()) {
//				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);
//			}
		} else {
			gerenciaRegionalColecao.setNomeAbreviado("TODOS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
		}
		sessao.setAttribute("colecaoGerenciaRegionalResultado",
						colecaoGerenciaRegional);
// FIM GERENCIA REGIONAL ///////////////////////////////////////////////////////////////////////////////////////////		

// UNIDADE DE NEGOCIO ///////////////////////////////////////////////////////////////////////////////////////////
		UnidadeNegocio unidadeNegocioColecao = new UnidadeNegocio();
		unidadeNegocioColecao.setId(-1);
		Collection colecaoUnidadeNegocio = new ArrayList();
		if (!dadosConsultaNegativacaoHelper.getColecaoUnidadeNegocio().isEmpty()) { // unidadeNegocio != null
			colecaoUnidadeNegocio.addAll(dadosConsultaNegativacaoHelper.getColecaoUnidadeNegocio());
//			unidadeNegocioColecao.setNomeAbreviado("OPÇÕES SELECIONADAS");
//			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
//			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
//
//			filtroUnidadeNegocio
//			.adicionarParametro(new ParametroSimples(
//					FiltroImovelPerfil.ID, imovelPerfil));
//			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
//			Collection colecaoUnidadeNegocioPesquisa = fachada.pesquisar(
//					filtroUnidadeNegocio, UnidadeNegocio.class.getName());
//
//			if (colecaoUnidadeNegocioPesquisa != null
//					&& !colecaoUnidadeNegocioPesquisa.isEmpty()) {
//				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
//			}
		} else {
			unidadeNegocioColecao.setNomeAbreviado("TODOS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
		}
		sessao.setAttribute("colecaoUnidadeNegocioResultado",
				colecaoUnidadeNegocio);

// FIM UNIDADE DE NEGOCIO ///////////////////////////////////////////////////////////////////////////////////////////		
		
// INICIO PERFIL IMOVEL /////////////////////////////////////////////////////////////////////////////////////////		
		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);
		Collection colecaoImovelPerfil = new ArrayList();
		if (!dadosConsultaNegativacaoHelper.getColecaoImovelPerfil().isEmpty()) { // imovelPerfil != null
			colecaoImovelPerfil.addAll(dadosConsultaNegativacaoHelper.getColecaoImovelPerfil());
//			imovelPerfilColecao.setDescricao("OPÇÕES SELECIONADAS");
//			colecaoImovelPerfil.add(imovelPerfilColecao);
//			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
//
//			filtroImovelPerfil
//			.adicionarParametro(new ParametroSimples(
//					FiltroImovelPerfil.ID, imovelPerfil));
//			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
//			Collection colecaoImovelPerfilPesquisa = fachada.pesquisar(
//					filtroImovelPerfil, ImovelPerfil.class.getName());
//
//			if (colecaoImovelPerfilPesquisa != null
//					&& !colecaoImovelPerfilPesquisa.isEmpty()) {
//				colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
//			}
		} else {
			imovelPerfilColecao.setDescricao("TODOS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
		}
		sessao.setAttribute("colecaoImovelPerfilResultado",
						colecaoImovelPerfil);
// FIM PERFIL IMOVEL /////////////////////////////////////////////////////////////////////////////////////		

// INICIO CATEGORIA  /////////////////////////////////////////////////////////////////////////////////////		
		Categoria categoriaColecao = new Categoria();
		categoriaColecao.setId(-1);
		Collection colecaoCategoria = new ArrayList();
		if (!dadosConsultaNegativacaoHelper.getColecaoCategoria().isEmpty()) { // categoria != null
			colecaoCategoria.addAll(dadosConsultaNegativacaoHelper.getColecaoCategoria());
//			categoriaColecao.setDescricao("OPÇÕES SELECIONADAS");
//			colecaoCategoria.add(categoriaColecao);
//			FiltroCategoria filtroCategoria = new FiltroCategoria();
//
//			filtroCategoria
//			.adicionarParametro(new ParametroSimples(
//					FiltroCategoria.CODIGO, categoria));
//
//			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
//
//			Collection colecaoCategoriaPesquisa = fachada.pesquisar(
//					filtroCategoria, Categoria.class.getName());
//
//			if (colecaoCategoriaPesquisa != null
//					&& !colecaoCategoriaPesquisa.isEmpty()) {
//				colecaoCategoria.addAll(colecaoCategoriaPesquisa);
//			}
		} else {
			categoriaColecao.setDescricao("TODOS");
			colecaoCategoria.add(categoriaColecao);
		}
		sessao.setAttribute("colecaoCategoriaResultado", colecaoCategoria);
// FIM CATEGORIA  /////////////////////////////////////////////////////////////////////////////////////////

// INICIO ESFERA DE PODER  ////////////////////////////////////////////////////////////////////////////////
		EsferaPoder esferaPoderColecao = new EsferaPoder();
		esferaPoderColecao.setId(-1);
		Collection colecaoEsferaPoder = new ArrayList();
		if (!dadosConsultaNegativacaoHelper.getColecaoEsferaPoder().isEmpty()) { // esferaPoder != null
			colecaoEsferaPoder.addAll(dadosConsultaNegativacaoHelper.getColecaoEsferaPoder()); 
//			esferaPoderColecao.setDescricao("OPÇÕES SELECIONADAS");
//			colecaoEsferaPoder.add(esferaPoderColecao);
//			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
//
//			filtroEsferaPoder
//			.adicionarParametro(new ParametroSimples(
//					FiltroEsferaPoder.ID, esferaPoder));
//
//			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
//
//			Collection colecaoEsferaPoderPesquisa = fachada.pesquisar(
//					filtroEsferaPoder, EsferaPoder.class.getName());
//
//			if (colecaoEsferaPoderPesquisa != null
//					&& !colecaoEsferaPoderPesquisa.isEmpty()) {
//				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
//			}
		} else {
			esferaPoderColecao.setDescricao("TODOS");
			colecaoEsferaPoder.add(esferaPoderColecao);
		}
		sessao.setAttribute("colecaoEsferaPoderResultado", colecaoEsferaPoder);
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 13/01/2011
		//********************************************************
		LigacaoAguaSituacao ligacaoAguaSituacaoColecao = new LigacaoAguaSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);
		Collection colecaoligacaoAguaSituacao = new ArrayList();
		if (!dadosConsultaNegativacaoHelper.getColecaoLigacaoAguaSituacao().isEmpty()) {
			colecaoligacaoAguaSituacao.addAll(dadosConsultaNegativacaoHelper.getColecaoLigacaoAguaSituacao()); 
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoligacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}
		sessao.setAttribute("colecaoLigacaoAguaSituacaoResultado", colecaoligacaoAguaSituacao);
		
		LigacaoEsgotoSituacao ligacaoEsgotoSituacaoColecao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacaoColecao.setId(-1);
		Collection colecaoligacaoEsgotoSituacao = new ArrayList();
		if (!dadosConsultaNegativacaoHelper.getColecaoLigacaoEsgotoSituacao().isEmpty()) {
			colecaoligacaoEsgotoSituacao.addAll(dadosConsultaNegativacaoHelper.getColecaoLigacaoEsgotoSituacao()); 
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoligacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}
		sessao.setAttribute("colecaoLigacaoEsgotoSituacaoResultado", colecaoligacaoEsgotoSituacao);
		//********************************************************
		
		
//		 INICIO TIPO DE CLIENTE  ////////////////////////////////////////////////////////////////////////////////
		ClienteTipo clienteTipoColecao = new ClienteTipo();  
		clienteTipoColecao.setId(-1);
		Collection colecaoClienteTipo = new ArrayList();
		if (!dadosConsultaNegativacaoHelper.getColecaoClienteTipo().isEmpty()) {  
			colecaoClienteTipo.addAll(dadosConsultaNegativacaoHelper.getColecaoClienteTipo()); 
//			esferaPoderColecao.setDescricao("OPÇÕES SELECIONADAS");
//			colecaoEsferaPoder.add(esferaPoderColecao);
//			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
	//
//			filtroEsferaPoder
//			.adicionarParametro(new ParametroSimples(
//					FiltroEsferaPoder.ID, esferaPoder));
	//
//			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
	//
//			Collection colecaoEsferaPoderPesquisa = fachada.pesquisar(
//					filtroEsferaPoder, EsferaPoder.class.getName());
	//
//			if (colecaoEsferaPoderPesquisa != null
//					&& !colecaoEsferaPoderPesquisa.isEmpty()) {
//				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
//			}
		} else {
			clienteTipoColecao.setDescricao("TODOS");
			colecaoClienteTipo.add(clienteTipoColecao);
		}
		sessao.setAttribute("colecaoClienteTipoResultado", colecaoClienteTipo);
//	FIM TIPO DE CLIENTE  ////////////////////////////////////////////////////////////////////////////////			
		return retorno;
	}
//	FIM ESFERA DE PODER  ////////////////////////////////////////////////////////////////////////////////
	

	
}

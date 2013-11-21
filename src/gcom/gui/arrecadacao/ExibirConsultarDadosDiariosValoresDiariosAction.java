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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.FuncionalidadeSituacao;
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
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 * @created 25 de Maio de 2006
**/
public class ExibirConsultarDadosDiariosValoresDiariosAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDadosDiariosValoresDiarios");
		
		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
        /** filtro para verificar se a funcionalidade de gerar dados diários de arrecadação esta executando */
        FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
        filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_ID,Funcionalidade.GERAR_DADOS_DIARIOS_ARRECADACAO));
        filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_SITUACAO,FuncionalidadeSituacao.EM_ESPERA, ConectorOr.CONECTOR_OR, 2));
        filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_SITUACAO,FuncionalidadeSituacao.EM_PROCESSAMENTO));
        
        Collection colecaoFuncionalidadeEmProcessamento = Fachada.getInstancia().pesquisar(filtroFuncionalidadeIniciada,FuncionalidadeIniciada.class.getName());
        
        /*
         * Caso a funcionalidade esteja emprocessamento ou em espera
         * envia uma mensagem ao usuário negando o acesso a consulta.  
         */
        if(colecaoFuncionalidadeEmProcessamento != null && !colecaoFuncionalidadeEmProcessamento.isEmpty()){
        	throw new ActionServletException("atencao.funcionalidade.processando");
        }
		
		String referencia = httpServletRequest.getParameter("referencia");
		
		String idGerencia = httpServletRequest.getParameter("idGerencia");
		
		String idUnidadeNegocio = httpServletRequest.getParameter("idUnidadeNegocio");
		
		String idPerfil = httpServletRequest.getParameter("idPerfil");
		
		//String idDocumento = httpServletRequest.getParameter("idDocumento");
		
		String idArrecadadorPopup = httpServletRequest.getParameter("idArrecadadorPopup");
		httpServletRequest.setAttribute("idArrecadadorPopup", idArrecadadorPopup);

		String idCategoria = httpServletRequest.getParameter("idCategoria");
		
		String idEloPopup = httpServletRequest.getParameter("idEloPopup");
		
		String idLocalidade = httpServletRequest.getParameter("idLocalidade");
		
		String idDocumentoTipoPopup = httpServletRequest.getParameter("idDocumentoTipoPopup");
		
		String idDocumentoTipoAgregador = httpServletRequest.getParameter("idDocumentoTipoAgregador");
		
		String idArrecadacaoForma = httpServletRequest.getParameter("idArrecadacaoForma");
		httpServletRequest.setAttribute("idArrecadacaoForma",idArrecadacaoForma);
		        
		String mostraUnidadeGerencia = httpServletRequest.getParameter("mostraUnidadeGerencia");
		sessao.setAttribute("mostraUnidadeGerencia",mostraUnidadeGerencia);
		

		httpServletRequest.setAttribute("nomeAgente", httpServletRequest.getParameter("nomeAgente"));
			
		
		Fachada fachada = Fachada.getInstancia();
		
		//String descricao = "";
		//String nomeAgente = "";
		
		//Collection colecaoArrecadacaoDadosDiariosValoresDiarios = new ArrayList();

		Integer anoMesAnterior = Util.subtrairMesDoAnoMes(new Integer(referencia).intValue(), 1);
		Integer idCateg = null;
		if ( idCategoria != null && !idCategoria.equals("-1")) {
			idCateg = Util.converterStringParaInteger(idCategoria);
		}
		BigDecimal faturamentoCobradoEmConta = fachada.pesquisarFaturamentoCobradoEmContaComQuebra(anoMesAnterior, null, idCateg );
		sessao.setAttribute("faturamentoCobradoEmConta", Util.formatarMoedaReal(faturamentoCobradoEmConta));
		
		FiltroConsultarDadosDiariosArrecadacao filtro = (FiltroConsultarDadosDiariosArrecadacao)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacao");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");
		
		if (filtro != null){
			
			filtro = filtro.clone();
			
			filtro.setAgrupamento(GROUP_BY.DATA);
			
			filtro.setAnoMesArrecadacao(referencia);
			if (idGerencia != null && !idGerencia.equals("") && !idGerencia.equals("-1")){
				filtro.setIdGerenciaRegional(idGerencia);
				
				// pesquisar na base a gerencia Regional
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional ();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,
						idGerencia));
				
				Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional,
						GerenciaRegional.class.getName());
				
				GerenciaRegional gerenciaRegional = (GerenciaRegional) 
					Util.retonarObjetoDeColecao(colecaoGerenciaRegional); 
				sessao.setAttribute("nomeGerencia",gerenciaRegional.getNome());
				
			} else {
				sessao.removeAttribute("nomeGerencia");
			}
			if (idUnidadeNegocio != null && !idUnidadeNegocio.equals("") && 
				!idUnidadeNegocio.equals("-1")){
				filtro.setIdUnidadeNegocio(idUnidadeNegocio);	
				
				//pesquisar na base a gerencia
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,
						idUnidadeNegocio));
				
				Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
						UnidadeNegocio.class.getName());
				
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) 
					Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
				sessao.setAttribute("nomeUnidadeNegocio",unidadeNegocio.getNome());					
								
			} else {
				sessao.removeAttribute("nomeUnidadeNegocio");
			}
			if (idPerfil != null && !idPerfil.equals("") && !idPerfil.equals("-1")){
				String[] idsImovelPerfil = {idPerfil};
				filtro.setIdsImovelPerfil(idsImovelPerfil);
				
				//pesquisar na base o Imovel Perfil
				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,
						idPerfil));
				
				Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
						ImovelPerfil.class.getName());

				ImovelPerfil imovelPerfil = (ImovelPerfil) 
					Util.retonarObjetoDeColecao(colecaoImovelPerfil);
				sessao.setAttribute("nomePerfil", imovelPerfil.getDescricao());
				
			} else {
				sessao.removeAttribute("nomePerfil");
			}
			if (idArrecadadorPopup != null && !idArrecadadorPopup.equals("") 
				&& !idArrecadadorPopup.equals("-1")){
				filtro.setIdArrecadador(idArrecadadorPopup);
				
				//pesquisar na base o arrecadador
				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
				filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID,
						idArrecadadorPopup));
				filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
				
				Collection colecaoArrecadador = fachada.pesquisar(filtroArrecadador,
						Arrecadador.class.getName());
				
				Arrecadador arrecadador = (Arrecadador)Util.retonarObjetoDeColecao(colecaoArrecadador);
				sessao.setAttribute("nomeAgente", arrecadador.getCliente().getNome());
				
			} else {
				sessao.removeAttribute("nomeAgente");
			}
			if (idCategoria != null && !idCategoria.equals("") && !idCategoria.equals("-1")){
				String[] idsCategoria = {idCategoria};
				filtro.setIdsCategoria(idsCategoria);
				
				//pesquisar na base a Categoria
				FiltroCategoria filtroCategoria = new FiltroCategoria();
				filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
						idCategoria));
				
				Collection colecaoCategorias = fachada.pesquisar(filtroCategoria,
						Categoria.class.getName());
				
				Categoria categoria = (Categoria) Util.retonarObjetoDeColecao(colecaoCategorias);
				sessao.setAttribute("nomeCategoria", categoria.getDescricao());

			} else {
				sessao.removeAttribute("nomeCategoria");
			}
			if (idEloPopup != null && !idEloPopup.equals("") && !idEloPopup.equals("-1")){
				filtro.setIdElo(idEloPopup);
				
				// pesquisar na base a localidade
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples("localidade.id",
					idEloPopup));
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
				
				Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
						Localidade.class.getName());
				
				Localidade localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
				sessao.setAttribute("descricaoElo", localidade.getLocalidade().getDescricao());
				
			} else {
				sessao.removeAttribute("descricaoElo");
			}
			if (idLocalidade != null && !idLocalidade.equals("") && !idLocalidade.equals("-1")){
				filtro.setIdLocalidade(idLocalidade);
				
				// pesquisar na base a localidade
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
					idLocalidade));
				
				Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
						Localidade.class.getName());
				
				Localidade localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
				sessao.setAttribute("descricaoLocalidade", localidade.getDescricao());
				
			} else {
				sessao.removeAttribute("descricaoLocalidade");
			}
			if (idDocumentoTipoAgregador != null && !idDocumentoTipoAgregador.equals("") &&
				!idDocumentoTipoAgregador.equals("-1")){
				String[] idsDocumentoTipoAgregador = {idDocumentoTipoAgregador};
				filtro.setIdsDocumentoTipoAgregador(idsDocumentoTipoAgregador);
				
				//pesquisar na base o Documento Tipo
				FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
				filtroDocumentoTipo.adicionarParametro(new ParametroSimples(FiltroDocumentoTipo.ID,
						idDocumentoTipoAgregador));
				
				Collection colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo,
						DocumentoTipo.class.getName());
				
				DocumentoTipo documentoTipo = (DocumentoTipo) Util.retonarObjetoDeColecao(colecaoDocumentoTipo);
				
				if (documentoTipo == null){
					documentoTipo = new DocumentoTipo();
					documentoTipo.setDescricaoDocumentoTipo("Sem tipo de documento");
					documentoTipo.setId(0);							
				}
			
				sessao.setAttribute("nomeDocumento", documentoTipo.getDescricaoDocumentoTipo());

			} else {
				sessao.removeAttribute("nomeDocumento");
			}
			if (idDocumentoTipoPopup != null && !idDocumentoTipoPopup.equals("") &&
				!idDocumentoTipoPopup.equals("-1")){
				filtro.setIdDocumentoTipo(idDocumentoTipoPopup);
				
				//pesquisar na base o Documento Tipo
				FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
				filtroDocumentoTipo.adicionarParametro(new ParametroSimples(FiltroDocumentoTipo.ID,
						idDocumentoTipoPopup));
				
				Collection colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo,
						DocumentoTipo.class.getName());
				
				DocumentoTipo documentoTipo = (DocumentoTipo) Util.retonarObjetoDeColecao(colecaoDocumentoTipo);
				
				if (documentoTipo == null){
					documentoTipo = new DocumentoTipo();
					documentoTipo.setDescricaoDocumentoTipo("Sem tipo de documento");
					documentoTipo.setId(0);							
				}
			
				sessao.setAttribute("nomeDocumento", documentoTipo.getDescricaoDocumentoTipo());
				
			} else {
				sessao.removeAttribute("nomeDocumento");
			}
			if (idArrecadacaoForma != null && !idArrecadacaoForma.equals("") 
					&& !idArrecadacaoForma.equals("-1")){
				filtro.setIdFormaArrecadacao(idArrecadacaoForma);
				
				//pesquisar na base o arrecadacao forma
				FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
				filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO,
						idArrecadacaoForma));
				
				Collection colecaoArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma,
						ArrecadacaoForma.class.getName());
				
				ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoArrecadacaoForma);
				
				if(arrecadacaoForma == null){
					arrecadacaoForma = new ArrecadacaoForma();
					arrecadacaoForma.setId(0);
					arrecadacaoForma.setDescricao("Sem forma de arrecadação");
				}
				
				sessao.setAttribute("nomeArrecadacaoForma", arrecadacaoForma.getDescricao());
			} else {
				sessao.removeAttribute("nomeArrecadacaoForma");
			}
				
			
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacao(
				periodoArrecadacaoInicial,
				periodoArrecadacaoFinal,
				filtro);
						
        	BigDecimal valorTotal = new BigDecimal(0.0);
        	
        	Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = 
        		mapDadosDiariosAnoMes.get(new Integer(referencia));
        	
        	for (FiltrarDadosDiariosArrecadacaoHelper helper : colecaoDadosDiarios){
        		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());
        	}
        	
	        sessao.setAttribute("colecaoDadosDiarios", colecaoDadosDiarios);
	        
			sessao.setAttribute("valorTotal", valorTotal);
			
			Date dataMesInformado = fachada.pesquisarDataProcessamentoMes(new Integer(referencia));
	    	Date dataAtual = fachada.pesquisarDataProcessamentoMes(this.getSistemaParametro().getAnoMesArrecadacao());

	    		
	    	if(dataMesInformado!=null){ 			
	    		sessao
					.setAttribute("dadosMesInformado", 
						Util.formatarDataComHora(dataMesInformado));
	    	} else {
	    		sessao.removeAttribute("dadosMesInformado");
	    	}
	    	if(dataAtual!=null){   			
	    		sessao
					.setAttribute("dadosAtual", 
						Util.formatarDataComHora(dataAtual));
	    	} else {
	    		sessao.removeAttribute("dadosAtual");
	    	}
						
		} else {
    		sessao.removeAttribute("colecaoDadosDiarios");
    		sessao.removeAttribute("valorTotal");
    		sessao.removeAttribute("dadosMesInformado");
    		sessao.removeAttribute("dadosAtual");
    	}
		

//		BigDecimal valorTotal = new BigDecimal("0.00");
//			
//        Collection colecaoArrecadacaoDadosDiarios = (Collection) sessao
//			.getAttribute("colecaoArrecadacaoDadosDiarios");
//
//        
//        ArrecadacaoDadosDiariosAcumuladorHelper acumuladorHelper = new  ArrecadacaoDadosDiariosAcumuladorHelper(
//        		ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_DATA);
//        
//        idArrecadadorPopup = idArrecadadorPopup != null && idArrecadadorPopup.equals("") ? null : idArrecadadorPopup; 
//        
//        Collection resultadoFiltro = acumuladorHelper.aplicarFiltroEAcumularValores(        		
//        		colecaoArrecadacaoDadosDiarios, 
//        		referencia, idLocalidade, idEloPopup, idGerencia, idUnidadeNegocio, idArrecadadorPopup,
//        		idArrecadacaoForma, idCategoria, idPerfil, idDocumentoTipoPopup, idDocumentoTipoAgregador, false, false, false,false, false);
//			
//		if((idGerencia != null) && (idLocalidade != null)  && (idEloPopup != null)){
//				ArrecadacaoDadosDiarios dadosArrecadacao = fachada.consultarDadosDiarios(new Integer(idGerencia).intValue(),new Integer(idLocalidade).intValue(),new Integer(idEloPopup).intValue());
//				
//				if (dadosArrecadacao != null && dadosArrecadacao.getGerenciaRegional() != null && dadosArrecadacao.getGerenciaRegional().getNome() != null
//						&& !dadosArrecadacao.getGerenciaRegional().getNome().equals("")) {
//	
//					httpServletRequest.setAttribute("nomeGerencia",dadosArrecadacao.getGerenciaRegional().getNome());
//				}
//				if (dadosArrecadacao != null &&  dadosArrecadacao.getLocalidade() != null && dadosArrecadacao.getLocalidade().getDescricao() != null
//						&& !dadosArrecadacao.getLocalidade().getDescricao().equals("")) {
//	
//					httpServletRequest.setAttribute("descricaoLocalidade",dadosArrecadacao.getLocalidade().getDescricao());
//				}
//				if (dadosArrecadacao != null &&  dadosArrecadacao.getLocalidade() != null && dadosArrecadacao.getLocalidade().getLocalidade() != null && dadosArrecadacao.getLocalidade().getLocalidade().getDescricao() != null
//						&& !dadosArrecadacao.getLocalidade().getLocalidade().getDescricao().equals("")) {
//	
//					httpServletRequest.setAttribute("descricaoElo",dadosArrecadacao.getLocalidade().getLocalidade().getDescricao());
//				}
//		}
//		httpServletRequest.setAttribute("nomeDocumento", httpServletRequest.getParameter("nomeDocumento"));
//		
//		Collections.sort((List) resultadoFiltro,
//				new Comparator() {
//					public int compare(Object a, Object b) {
//						Date data1 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getData();
//						Date data2 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//							.getData();						
//						if (data1 == null || data2 == null) {
//							return -1;
//						} else {
//							return data1.compareTo(data2);
//						}
//					}
//				});
//		
//		sessao.setAttribute("colecaoDadosDiarios", resultadoFiltro);
		sessao.setAttribute("referencia",referencia);
//		sessao.setAttribute("valorTotal", acumuladorHelper.getValorLiquidoTotal()); //valorTotal);
		return retorno;
	}
	
}
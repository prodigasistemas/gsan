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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.ImovelRamoAtividadePK;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Classe responável pelo recebimento dos dados das categorias e subcategoria do imóvel 
 *
 * @author Raphael Rossiter
 * @date 12/05/2009
 */
public class ExibirAtualizarImovelSubCategoriaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarImovelSubCategoria");

		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.removeAttribute("gis");
		
		DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

		Collection colecaoImovelSubCategorias = null;
		Collection colecaoImovelRamoAtividade = null;

		Fachada fachada = Fachada.getInstancia();

		Collection categorias = new HashSet();
		Collection ramosAtividades = null;
		Collection subCategorias = new HashSet();

		String botaoAdicionar = httpServletRequest.getParameter("botaoAdicionar");
		String botaoAdicionar1 = httpServletRequest.getParameter("botaoAdicionar1");
		
		if (botaoAdicionar == null && httpServletRequest.getParameter("confirmado") != null) {
			botaoAdicionar = (String) sessao.getAttribute("botaoAdicionar");
		} 
		else {
			sessao.removeAttribute("botaoAdicionar");
		}
		
		if (botaoAdicionar1 == null && httpServletRequest.getParameter("confirmado") != null) {
			botaoAdicionar1 = (String) sessao.getAttribute("botaoAdicionar1");
		}else {
			sessao.removeAttribute("botaoAdicionar1");
		}
		
		if (sessao.getAttribute("colecaoImovelRamosAtividade") != null) {
			
			colecaoImovelRamoAtividade = (Collection) sessao.getAttribute("colecaoImovelRamosAtividade");
		} 
		else {
			colecaoImovelRamoAtividade = new ArrayList();
		}		
		
		if(sessao.getAttribute("ramosAtividades") != null){
			ramosAtividades = (Collection) sessao.getAttribute("ramosAtividades");
		}

		FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria(FiltroSubCategoria.DESCRICAO);

		filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");

		Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");

		if (sessao.getAttribute("categorias") == null) {
			
			FiltroCategoria filtroCategoriaNova = new FiltroCategoria();

			filtroCategoriaNova.adicionarParametro(new ParametroSimples(
			FiltroCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			if (imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
				
				filtroCategoriaNova.adicionarParametro(new ParametroSimples(
				FiltroCategoria.CODIGO, ConstantesSistema.INDICADOR_CATEGORIA_RESIDENCIA));
				
				filtroSubCategoria.adicionarParametro(new ParametroSimples(
				FiltroSubCategoria.CATEGORIA_ID,ConstantesSistema.INDICADOR_CATEGORIA_RESIDENCIA));
			}

			categorias = fachada.pesquisar(filtroCategoriaNova, Categoria.class.getName());
			
		} 
		else {
			categorias = (Collection) sessao.getAttribute("categorias");
		}

		if (sessao.getAttribute("colecaoImovelSubCategorias") != null) {
			
			colecaoImovelSubCategorias = (Collection) sessao
			.getAttribute("colecaoImovelSubCategorias");
		} 
		else {
			colecaoImovelSubCategorias = new ArrayList();
		}

		if (botaoAdicionar != null && !botaoAdicionar.equals("")) {
			
			short quantidadeEconomia = new Short(inserirImovelActionForm.get(
			"quantidadeEconomia").toString()).shortValue();
			
			Short qtdUniadePrivativa = null;
			Short qtdUniadeColetiva = null;
			
			//QUANTIDADE DE UNIDADES COM INSTALAÇÕES PRIVATIVAS
			if (inserirImovelActionForm.get("qtdUnidadePrivativa") != null &&
				!((inserirImovelActionForm.get("qtdUnidadePrivativa").toString())).equals("")){
				
				qtdUniadePrivativa = new Short(inserirImovelActionForm.get(
				"qtdUnidadePrivativa").toString()).shortValue();
			}
			
			//QUANTIDADE DE UNIDADES COM INSTALAÇÕES COLETIVAS
			if (inserirImovelActionForm.get("qtdUnidadeColetiva") != null &&
				!((inserirImovelActionForm.get("qtdUnidadeColetiva").toString())).equals("")){
				
				qtdUniadeColetiva = new Short(inserirImovelActionForm.get(
				"qtdUnidadeColetiva").toString()).shortValue();
			}

			FiltroCategoria filtroCategoria = new FiltroCategoria();
			
			filtroCategoria.adicionarParametro(new ParametroSimples(
			FiltroCategoria.CODIGO, inserirImovelActionForm.get("idCategoria")));

			Collection colecaoCategoria = fachada.pesquisar(filtroCategoria,
			Categoria.class.getName());
			
			Categoria categoriaNaBase = (Categoria) Util
			.retonarObjetoDeColecao(colecaoCategoria);

			//[FS0018] - Verificar quantidade de economias
			if (categoriaNaBase.getConsumoMaximoEconomiasValidacao() < new Integer(quantidadeEconomia)) {
				
				if (httpServletRequest.getParameter("confirmado") == null) {

					httpServletRequest.setAttribute("destino", "4");
					sessao.setAttribute("botaoAdicionar", botaoAdicionar);
					
					retorno = montarPaginaConfirmacaoWizard(
					"atencao.usuario.limite_ultrapassado_economias_validacao",
					httpServletRequest, actionMapping, ""+categoriaNaBase.getConsumoMaximoEconomiasValidacao());

				} 
				else if (httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {

						adicionaObjetoColecao(inserirImovelActionForm, sessao,
						colecaoImovelSubCategorias, quantidadeEconomia,
						fachada, filtroSubCategoria, qtdUniadePrivativa, qtdUniadeColetiva);

						sessao.removeAttribute(botaoAdicionar);

				}
			} 
			else {
				
				adicionaObjetoColecao(inserirImovelActionForm, sessao,
				colecaoImovelSubCategorias, quantidadeEconomia, fachada, 
				filtroSubCategoria, qtdUniadePrivativa, qtdUniadeColetiva);

			}

		} 
		else {
			
			if (inserirImovelActionForm.get("idCategoria") != null && 
				!inserirImovelActionForm.get("idCategoria").equals("0") && 
				!inserirImovelActionForm.get("idCategoria").equals("")) {
				
				String idCategoria = (String) inserirImovelActionForm.get("idCategoria");
				String idSubCategoria = (String) inserirImovelActionForm.get("idSubCategoria");

				filtroSubCategoria.adicionarParametro(new ParametroSimples(
				FiltroSubCategoria.CATEGORIA_ID, idCategoria));
				
				filtroSubCategoria.adicionarParametro(new ParametroSimples(
				FiltroSubCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

				subCategorias = fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());

				//[FS0001] - Verificar existência de dados (SUBCATEGORIA)
				if (subCategorias == null || subCategorias.isEmpty()) {
					
					throw new ActionServletException("atencao.categoria.sub_categoria.inexistente");
				} 
				else {
					
					Iterator isubCategorias = subCategorias.iterator();
					
					while (isubCategorias.hasNext()) {
						
						Subcategoria sub = (Subcategoria) isubCategorias.next();

						if (sub.getId().toString().equals(idSubCategoria)) {
							
							inserirImovelActionForm.set("textoSelecionadoSubCategoria", sub.getDescricao());
						}
					}

					sessao.setAttribute("subCategorias", subCategorias);
					sessao.setAttribute("categorias", categorias);
				}
				// Serve para setar a categoria quando o usuario selecionar só a
				// sub categoria
			} 
			else if (inserirImovelActionForm.get("idCategoria").toString().trim().equalsIgnoreCase("0") && 
					!inserirImovelActionForm.get("idSubCategoria").toString().trim().equalsIgnoreCase("0") && 
					(httpServletRequest.getParameter("subCategoriaEscolhida") != null && 
					httpServletRequest.getParameter("subCategoriaEscolhida").trim().equalsIgnoreCase("1"))) {

				filtroSubCategoria.adicionarParametro(new ParametroSimples(
				FiltroSubCategoria.ID, inserirImovelActionForm.get("idSubCategoria").toString()));
				
				filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
				
				Collection subCategoriasTeste = fachada.pesquisar(
				filtroSubCategoria, Subcategoria.class.getName());
				
				Subcategoria subCategoria = (Subcategoria) subCategoriasTeste.iterator().next();

				filtroSubCategoria.limparListaParametros();
				
				filtroSubCategoria.adicionarParametro(new ParametroSimples(
				FiltroSubCategoria.CATEGORIA_ID, subCategoria.getCategoria().getId()));
				
				subCategorias = fachada.pesquisar(filtroSubCategoria,
				Subcategoria.class.getName());
				
				sessao.setAttribute("subCategorias", subCategorias);
				
				inserirImovelActionForm.set("idCategoria", subCategoria.getCategoria().getId().toString());
				inserirImovelActionForm.set("textoSelecionadoCategoria",subCategoria.getCategoria().getDescricao());

			} 
			else if (inserirImovelActionForm.get("idCategoria").equals("")
					|| inserirImovelActionForm.get("idCategoria").equals("0")
					|| inserirImovelActionForm.get("idCategoria") == null) {
				
				//CARREGAMENTO INICIAL DA ABA DE CATEGORIAS E SUBCATEGORIAS
				FiltroCategoria filtroCategoria = new FiltroCategoria(
				FiltroCategoria.DESCRICAO);

				filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				if (imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
					
					filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO, ConstantesSistema.INDICADOR_CATEGORIA_RESIDENCIA));
					
					filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CATEGORIA_ID, ConstantesSistema.INDICADOR_CATEGORIA_RESIDENCIA));
				}

				categorias = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
				
				//[FS0001] - Verificar existência de dados (CATEGORIA)
				if (categorias == null || categorias.equals("")) {
					
					throw new ActionServletException("atencao.naocadastrado", null, "categoria");
				} 
				else {
					
					if (inserirImovelActionForm.get("textoSelecionadoCategoria") == null || 
						inserirImovelActionForm.get("textoSelecionadoCategoria").equals("")) {
						
						inserirImovelActionForm.set("textoSelecionadoCategoria",
						((Categoria) ((List) categorias).get(0)).getDescricao());
					} 
					else {
						
						inserirImovelActionForm.set("textoSelecionadoCategoria", "");
					}

					filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
					
					subCategorias = fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());

					//[FS0001] - Verificar existência de dados (SUBCATEGORIA)
					if (subCategorias == null || subCategorias.equals("")) {
						
						throw new ActionServletException("atencao.naocadastrado", null, "sub categoria");
					} 
					else {

						if (inserirImovelActionForm.get("textoSelecionadoSubCategoria") == null || 
							inserirImovelActionForm.get("textoSelecionadoSubCategoria").equals("")) {
							
							inserirImovelActionForm.set("textoSelecionadoSubCategoria",
							((Subcategoria) ((List) subCategorias).get(0)).getDescricao());
						} 
						else {
							
							inserirImovelActionForm.set("textoSelecionadoSubCategoria", "");
							inserirImovelActionForm.set("idSubCategoria", "");
						}
					}

					sessao.setAttribute("categorias", categorias);
					sessao.setAttribute("subCategorias", subCategorias);

				}
			}
		}

		sessao.setAttribute("colecaoImovelSubCategorias", colecaoImovelSubCategorias);
		
		Collection ramosAtividades1 = null;
		boolean carregou = false;
		if(ramosAtividades == null || ramosAtividades.isEmpty()){
			FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade(FiltroRamoAtividade.DESCRICAO);

			filtroRamoAtividade.adicionarParametro(new ParametroSimples(
			FiltroRamoAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			ramosAtividades = fachada.pesquisar(filtroRamoAtividade, RamoAtividade.class.getName());
			if (ramosAtividades == null || ramosAtividades.equals("")) {
				
				throw new ActionServletException("atencao.naocadastrado",
				null, "ramo de atividade");
			}else{
				if (inserirImovelActionForm.get("textoSelecionadoRamoAtividade") == null || 
						inserirImovelActionForm.get("textoSelecionadoRamoAtividade").equals("")) {
						
						inserirImovelActionForm.set("textoSelecionadoRamoAtividade",
						((RamoAtividade) ((List) ramosAtividades).get(0)).getDescricao());
			    }else{
			    	    inserirImovelActionForm.set("textoSelecionadoRamoAtividade", "");
			    	    inserirImovelActionForm.set("idRamoAtividade", "");
			    }
			}
			
			carregou = true;
			sessao.setAttribute("ramosAtividades", ramosAtividades);
		}
		
		if (botaoAdicionar1 != null && !botaoAdicionar1.equals("")) {
			if (inserirImovelActionForm.get("idRamoAtividade") != null
					&& !inserirImovelActionForm.get("idRamoAtividade").equals("0")
					&& !inserirImovelActionForm.get("idRamoAtividade").equals("")) {
					
					String idRamoAtividade = (String) inserirImovelActionForm.get("idRamoAtividade");
					
					FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade(
					FiltroRamoAtividade.DESCRICAO);

					filtroRamoAtividade.adicionarParametro(new ParametroSimples(
					FiltroRamoAtividade.ID,idRamoAtividade));

					filtroRamoAtividade.adicionarParametro(new ParametroSimples(
					FiltroRamoAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

					ramosAtividades1 = fachada.pesquisar(filtroRamoAtividade, RamoAtividade.class.getName());

					if (ramosAtividades1 == null || ramosAtividades1.isEmpty()) {
						
						throw new ActionServletException("atencao.ramos.atividade.inexistente");
					} 
					else {

						Iterator iramosAtividades = ramosAtividades1.iterator();
						
						while (iramosAtividades.hasNext()) {
						
							RamoAtividade rat = (RamoAtividade) iramosAtividades.next();

							if (rat.getId().toString().equals(idRamoAtividade)) {
								
								inserirImovelActionForm.set("textoSelecionadoRamoAtividade", rat.getDescricao());
								adicionaObjetoColecao(inserirImovelActionForm,sessao,colecaoImovelRamoAtividade);
								break;
							}

						}
						sessao.setAttribute("ramosAtividades", ramosAtividades);
					}
					
				}
			
			
		}else{ 
			
			if((inserirImovelActionForm.get("idRamoAtividade").equals("") 
				|| inserirImovelActionForm.get("idRamoAtividade").equals("0")
				|| inserirImovelActionForm.get("idRamoAtividade") == null) && !carregou){
			
			FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade(FiltroRamoAtividade.DESCRICAO);

			filtroRamoAtividade.adicionarParametro(new ParametroSimples(
			FiltroRamoAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			ramosAtividades = fachada.pesquisar(filtroRamoAtividade, RamoAtividade.class.getName());
			if (ramosAtividades == null || ramosAtividades.equals("")) {
				
				throw new ActionServletException("atencao.naocadastrado",
				null, "ramo de atividade");
			}else{
				if (inserirImovelActionForm.get("textoSelecionadoRamoAtividade") == null || 
						inserirImovelActionForm.get("textoSelecionadoRamoAtividade").equals("")) {
						
						inserirImovelActionForm.set("textoSelecionadoRamoAtividade",
						((RamoAtividade) ((List) ramosAtividades).get(0)).getDescricao());
			    }else{
			    	    inserirImovelActionForm.set("textoSelecionadoRamoAtividade", "");
			    	    inserirImovelActionForm.set("idRamoAtividade", "");
			    }
			}
		   }else if(inserirImovelActionForm.get("idRamoAtividade").equals("") 
				|| inserirImovelActionForm.get("idRamoAtividade").equals("0")
				|| inserirImovelActionForm.get("idRamoAtividade") == null){
			   
			   
			   
		   }
			
			sessao.setAttribute("ramosAtividades", ramosAtividades);
		}

		sessao.setAttribute("colecaoImovelRamosAtividade",colecaoImovelRamoAtividade);
		sessao.setAttribute("botaoAdicionar1",null);


		return retorno;
	}

	/**
	 * Este método retorna o id temporário baseado no timestamp do objeto para
	 * exibição na memória, enquanto o usuário estiver inserindo
	 * 
	 * @param objeto
	 * @return
	 */
	public static long obterTimestampIdImovelSubcategoria(
			ImovelSubcategoria imovelSubcategoria) {
		long retorno = 0L;

		Integer idImovel = null;
		if (imovelSubcategoria != null
				&& imovelSubcategoria.getComp_id() != null
				&& imovelSubcategoria.getComp_id().getImovel() != null) {
			idImovel = imovelSubcategoria.getComp_id().getImovel().getId();
		}

		Integer idSubcategoria = null;
		if (imovelSubcategoria != null
				&& imovelSubcategoria.getComp_id() != null
				&& imovelSubcategoria.getComp_id().getSubcategoria() != null) {
			idSubcategoria = imovelSubcategoria.getComp_id().getSubcategoria()
					.getId();
		}

		Date ultimaAlteracao = imovelSubcategoria.getUltimaAlteracao();

		retorno = ultimaAlteracao.getTime();

		if (idImovel != null) {
			retorno = retorno + idImovel;
		}

		if (idSubcategoria != null) {
			retorno = retorno + idSubcategoria;
		}

		return retorno;
	}
	
	public static long obterTimestampIdImovelRamoAtividade(
			ImovelRamoAtividade imovelRamoAtividade) {
		long retorno = 0L;

		Integer idImovel = null;
		if (imovelRamoAtividade != null
				&& imovelRamoAtividade.getComp_id() != null
				&& imovelRamoAtividade.getComp_id().getImovel() != null) {
			idImovel = imovelRamoAtividade.getComp_id().getImovel().getId();
		}

		Integer idRamoAtividade = null;
		if (imovelRamoAtividade != null
				&& imovelRamoAtividade.getComp_id() != null
				&& imovelRamoAtividade.getComp_id().getRamo_atividade() != null) {
			idRamoAtividade = imovelRamoAtividade.getComp_id().getRamo_atividade()
					.getId();
		}

		Date ultimaAlteracao = imovelRamoAtividade.getUltimaAlteracao();

		retorno = ultimaAlteracao.getTime();

		if (idImovel != null) {
			retorno = retorno + idImovel;
		}

		if (idRamoAtividade != null) {
			retorno = retorno + idRamoAtividade;
		}

		return retorno;
	}

	private void adicionaObjetoColecao(
			DynaValidatorForm inserirImovelActionForm, HttpSession sessao,
			Collection colecaoImovelSubCategorias, short quantidadeEconomia,
			Fachada fachada, FiltroSubCategoria filtroSubCategoria,
			Short quantidadeUnidadesPrivativas, Short quantidadeUnidadesColetivas) {

		filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");
		
		filtroSubCategoria.adicionarParametro(new ParametroSimples(
		FiltroSubCategoria.ID, inserirImovelActionForm.get("idSubCategoria").toString()));
		
		Collection SubCategoriasDescricao = fachada.pesquisar(
		filtroSubCategoria, Subcategoria.class.getName());
		
		Subcategoria subCategoria = (Subcategoria) SubCategoriasDescricao.iterator().next();

		ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK(
		null, subCategoria);

		ImovelSubcategoria imovelSubCategoria = new ImovelSubcategoria(
		imovelSubcategoriaPK, quantidadeEconomia, quantidadeUnidadesPrivativas, 
		quantidadeUnidadesColetivas, new Date());
		
		boolean existe = false;
		
		if (colecaoImovelSubCategorias != null && !colecaoImovelSubCategorias.isEmpty()) {
			
			Iterator icolecaoImovelSubCategorias = colecaoImovelSubCategorias.iterator();
			
			while (icolecaoImovelSubCategorias.hasNext()) {
				
				ImovelSubcategoria imovelSubcategoria2 = 
				(ImovelSubcategoria) icolecaoImovelSubCategorias.next();
				
				if (imovelSubcategoria2.getComp_id().getSubcategoria().getId()
					.intValue() == imovelSubCategoria.getComp_id()
					.getSubcategoria().getId().intValue()) {
					
					existe = true;
				}
			}
		}
		
		if (!existe) {

			colecaoImovelSubCategorias.add(imovelSubCategoria);

			inserirImovelActionForm.set("idSubCategoriaImovel",
			imovelSubCategoria.getComp_id().getSubcategoria().getId().toString());

			// manda para a sessão a coleção
			sessao.setAttribute("colecaoImovelSubCategorias",
			colecaoImovelSubCategorias);

		} 
		else {
			
			throw new ActionServletException("atencao.ja_cadastradado.sub_categoria");
		}

		inserirImovelActionForm.set("quantidadeEconomia", null);
		inserirImovelActionForm.set("qtdUnidadePrivativa", null);
		inserirImovelActionForm.set("qtdUnidadeColetiva", null);
		inserirImovelActionForm.set("idCategoria", "0");
		inserirImovelActionForm.set("idSubCategoria", "-1");
		
	}
	
	private void adicionaObjetoColecao(
			DynaValidatorForm inserirImovelActionForm, HttpSession sessao,
			Collection colecaoImoveisRamoAtividades) {

		RamoAtividade ramoAtividade = new RamoAtividade();

		ramoAtividade.setId(new Integer(inserirImovelActionForm.get("idRamoAtividade").toString()));
		ramoAtividade.setDescricao(new String(inserirImovelActionForm.get("textoSelecionadoRamoAtividade").toString()));

		
		ImovelRamoAtividadePK imovelRamoAtividadePK = new ImovelRamoAtividadePK(null, ramoAtividade);

		ImovelRamoAtividade imovelRamoAtividade = new ImovelRamoAtividade(
		imovelRamoAtividadePK, new Date());

		Iterator iteratorColecaoImoveisRamoAtiviades = colecaoImoveisRamoAtividades.iterator();
		ImovelRamoAtividade imoRamAti = new ImovelRamoAtividade();
		
		while ( iteratorColecaoImoveisRamoAtiviades.hasNext() ) {
			imoRamAti = (ImovelRamoAtividade) iteratorColecaoImoveisRamoAtiviades.next();
			if (imoRamAti.getComp_id().getRamo_atividade().getId().toString().equals(
					imovelRamoAtividade.getComp_id().getRamo_atividade().getId().toString())){
			
				throw new ActionServletException("atencao.ja_cadastradado.ramo.atividade");
			}
		}

		colecaoImoveisRamoAtividades.add(imovelRamoAtividade);

		inserirImovelActionForm.set("idRamoAtividade",
		imovelRamoAtividade.getComp_id().getRamo_atividade().getId().toString());

		sessao.setAttribute("colecaoImovelRamosAtividade",
		colecaoImoveisRamoAtividades);

		sessao.removeAttribute("botaoAdicionar1");
	}

}

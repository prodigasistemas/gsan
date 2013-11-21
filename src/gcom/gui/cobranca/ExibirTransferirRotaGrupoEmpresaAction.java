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
* Anderson Italo Felinto de Lima
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

package gcom.gui.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.FiltroLeituraTipo;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**			
 * @date 20/10/09
 * @author Anderson Italo
 * Pre-Processamento do UC0960 Transferir Rotas Entre Grupos e/ou Empresas
 */
public class ExibirTransferirRotaGrupoEmpresaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
	
		
		ActionForward retorno = actionMapping
				.findForward("filtrarTransferirRotaGrupoEmpresa");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		TransferirRotasGruposEmpresasActionForm form = (TransferirRotasGruposEmpresasActionForm) actionForm;
		
		//carrega os dados do formulário
    	//grupo de cobrança
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
							FiltroCobrancaGrupo.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
		Collection colecaoCobrancaGrupo = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
		
		if (colecaoCobrancaGrupo == null || colecaoCobrancaGrupo.isEmpty()){
			throw new ActionServletException("Atencao.Sem_dados_para_selecao");
		}
		
		httpServletRequest.setAttribute("colecaoGrupoCobrancaFiltro",colecaoCobrancaGrupo);
		
		//grupo de faturamento
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		if (colecaoFaturamentoGrupo == null || colecaoFaturamentoGrupo.isEmpty()){
			throw new ActionServletException("Atencao.Sem_dados_para_selecao");
		}
		
		httpServletRequest.setAttribute("colecaoGrupoFaturamentoFiltro",colecaoFaturamentoGrupo);
		
		//empresa de faturamento/cobrança
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.INDICADORUSO , ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()){
			throw new ActionServletException("Atencao.Sem_dados_para_selecao");
		}
		
		httpServletRequest.setAttribute("colecaoEmpresaFaturamentoFiltro",colecaoEmpresa);
		
		//gerencia regional
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		
		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()){
			throw new ActionServletException("Atencao.Sem_dados_para_selecao");
		}
		
		httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		
		//unidade de negocio
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()){
			throw new ActionServletException("Atencao.Sem_dados_para_selecao");
		}
		
		httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		
		//Tipo Leitura
		FiltroLeituraTipo filtroLeituraTipo = new FiltroLeituraTipo();
		
		filtroLeituraTipo.adicionarParametro(new ParametroSimples(
				FiltroLeituraTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroLeituraTipo.setCampoOrderBy(FiltroLeituraTipo.DESCRICAO);
		
		Collection<LeituraTipo> colecaoLeituraTipo = 
			fachada.pesquisar(filtroLeituraTipo, LeituraTipo.class.getName());
		
		httpServletRequest.setAttribute("colecaoTipoLeitura",colecaoLeituraTipo);
		
		if (httpServletRequest.getParameter("selecionar") != null
                && httpServletRequest.getParameter("selecionar").equalsIgnoreCase("1")) {
			
			//limpa dados seleção caso estejam preenchidos
			limparDadosSelecao(form,httpServletRequest, sessao);
			
			//o usuário ciclou em selecionar então faz a seleção das rotas
			efetuarFiltroRotas(form, httpServletRequest, fachada, sessao);
        	
        }else{
        	
        	if (httpServletRequest.getParameter("desfazer") != null
                    && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")){
        		//limpa os dados do formulário
        		form.setIdLocalidadeInicial(null);
        		form.setDescricaoLocalidadeInicial(null);
        		form.setIdLocalidadeFinal(null);
        		form.setDescricaoLocalidadeFinal(null);
        		form.setIdLocalidadeInicial(null);
        		form.setDescricaoLocalidadeFinal(null);
        		form.setIdSetorComercialInicial(null);
        		form.setDescricaoSetorComercialInicial(null);
        		form.setIdSetorComercialFinal(null);
        		form.setDescricaoSetorComercialFinal(null);
        		form.setIdRotaInicial(null);
        		form.setIdRotaFinal(null);
        		form.setIdGrupoCobrancaFiltro(null);
        		form.setIdGrupoFaturamentoFiltro(null);
        		form.setIdEmpresaCobrancaFiltro(null);
        		form.setIdEmpresaFaturamentoFiltro(null);
        		form.setIdLeiturista(null);
        		form.setNomeLeiturista(null);
        		form.setIdTipoLeitura(null);
        	}
        	
        	//limpa dados seleção caso estejam preenchidos
        	limparDadosSelecao(form, httpServletRequest, sessao);
       
    		
    		//localidade inicial
    		//-------Parte que trata do código quando o usuário tecla enter        
            String idDigitadoEnterLocalidadeInicial = form.getIdLocalidadeInicial();
            if (form.getIdLocalidadeInicial() != null 
            		&& !form.getIdLocalidadeInicial().equals("")){
    	        if (idDigitadoEnterLocalidadeInicial != null 
    	    			&& !idDigitadoEnterLocalidadeInicial.equalsIgnoreCase("")
    	    			&& Util.validarValorNaoNumerico(idDigitadoEnterLocalidadeInicial)){
    				//Localidade não numérico.
    				httpServletRequest.setAttribute("nomeCampo","idLocalidadeInicial");
    				throw new ActionServletException("atencao.nao.numerico",
    						null,"Localidade Inicial ");		
    			}
            }
        	
            verificaExistenciaCodLocalidade(idDigitadoEnterLocalidadeInicial,form,
           			httpServletRequest,fachada,sessao, false);
            
            //localidade final
            String idDigitadoEnterLocalidadeFinal = "";
            if (form.getIdLocalidadeFinal() != null 
            		&& !form.getIdLocalidadeFinal().equals("")){
    	        idDigitadoEnterLocalidadeFinal = form.getIdLocalidadeFinal();
    	    	if (idDigitadoEnterLocalidadeFinal != null 
    	    			&& !idDigitadoEnterLocalidadeFinal.equalsIgnoreCase("")
    	    			&& Util.validarValorNaoNumerico(idDigitadoEnterLocalidadeFinal)){
    				//Localidade não numérico.
    				httpServletRequest.setAttribute("nomeCampo","idLocalidadeFinal");
    				throw new ActionServletException("atencao.nao.numerico",
    						null,"Localidade Final ");		
    			}
    	    	
    	    	if (idDigitadoEnterLocalidadeInicial == null
    	    			|| idDigitadoEnterLocalidadeInicial.equals("")
    	    			|| (new Integer(idDigitadoEnterLocalidadeFinal).intValue() < new Integer(idDigitadoEnterLocalidadeInicial).intValue())){
    	    		throw new ActionServletException(
					"atencao.localidade.final.maior.localidade.inicial", null, "Localidade Final");
    	    	}
        	
    	    	verificaExistenciaCodLocalidade(idDigitadoEnterLocalidadeFinal,form,
            			httpServletRequest,fachada,sessao, true);
            }
            
            if (form.getIdLocalidadeInicial() != null 
            		&& form.getIdLocalidadeInicial().equals(form.getIdLocalidadeFinal())){
    	        //setor comercial inicial
    	        String idDigitadoEnterSetorComercialInicial = form.getIdSetorComercialInicial();
    	        if (form.getIdSetorComercialInicial() != null
    	        		&& !form.getIdSetorComercialInicial().equals("")){
    		    	if(idDigitadoEnterSetorComercialInicial != null &&
    		    			!idDigitadoEnterSetorComercialInicial.equalsIgnoreCase("")&&
    		    			Util.validarValorNaoNumerico(idDigitadoEnterSetorComercialInicial)){
    					//Setor Comercial não numérico.
    					httpServletRequest.setAttribute("nomeCampo","idSetorComercialInicial");
    					throw new ActionServletException("atencao.nao.numerico",
    							null,"Setor Comercial Inicial ");		
    				}
    	    	
    		    	verificaExistenciaCodSetorComercial(idDigitadoEnterLocalidadeInicial,idDigitadoEnterSetorComercialInicial,form,
    	       			httpServletRequest,fachada,sessao, false);
    	        }
    	        
    	        //setor comercial final
    	        String idDigitadoEnterSetorComercialFinal = "";
    	        if (form.getIdSetorComercialFinal() != null
    	        		&& !form.getIdSetorComercialFinal().equals("")){
    	        	
    	        	idDigitadoEnterSetorComercialFinal = form.getIdSetorComercialFinal();
    	        	if(idDigitadoEnterSetorComercialFinal != null &&
    	         			!idDigitadoEnterSetorComercialFinal.equalsIgnoreCase("")&&
    	         			Util.validarValorNaoNumerico(idDigitadoEnterSetorComercialFinal)){
    	     			//Setor Comercial não numérico.
    	     			httpServletRequest.setAttribute("nomeCampo","idSetorComercialFinal");
    	     			throw new ActionServletException("atencao.nao.numerico",
    	     					null,"Setor Comercial Final ");		
    	     		}
    	        	
    	        	if (idDigitadoEnterSetorComercialInicial == null
        	    			|| idDigitadoEnterSetorComercialInicial.equals("")
        	    			|| (new Integer(idDigitadoEnterSetorComercialFinal).intValue() < new Integer(idDigitadoEnterSetorComercialInicial).intValue())){
        	    		throw new ActionServletException(
    					"atencao.setor.comercial.final.maior.setor.comercial.inicial", null, "Setor Comercial Final");
        	    	}
    	         	
    	         	 verificaExistenciaCodSetorComercial(idDigitadoEnterLocalidadeFinal,idDigitadoEnterSetorComercialFinal,form,
    	            			httpServletRequest,fachada,sessao, true);
    	        }
            }else if (form.getIdLocalidadeInicial() != null
            		&& !form.getIdLocalidadeInicial().equals(form.getIdLocalidadeFinal())){
            	//limpa campos
            	form.setIdSetorComercialInicial("");
    			form.setDescricaoSetorComercialInicial("");
    			form.setIdRotaInicial("");
    			form.setIdSetorComercialFinal("");
    			form.setDescricaoSetorComercialFinal("");
    			form.setIdRotaFinal("");
            }
        	
        }
		
		if ( httpServletRequest.getParameter("reloadPage") == null ||  
				!httpServletRequest.getParameter("reloadPage").equals("")){
		
			verificaExistenciaCodLeiturista(form.getIdLeiturista(), form,httpServletRequest); 	
		}
		
       return retorno;

	}
	
	/**			
	 * @date 20/10/09
	 * @author Anderson Italo
	 * limpa dados da seleção de rotas
	 */
	private void limparDadosSelecao(TransferirRotasGruposEmpresasActionForm form, 
			HttpServletRequest httpServletRequest,
			HttpSession sessao) {
		
		form.setQuantidadeRotas(null);
		
		form.setIdGrupoCobrancaSelecao(null);
		form.setIdGrupoCobrancaDestino(null);
		form.setIdGrupoFaturamentoSelecao(null);
		form.setIdGrupoFaturamentoDestino(null);
		form.setIdEmpresaCobrancaSelecao(null);
		form.setIdEmpresaCobrancaDestino(null);
		form.setIdEmpresaFaturamentoSelecao(null);
		form.setIdEmpresaFaturamentoDestino(null);
		
		sessao.removeAttribute("rotasSelecionadas");
		httpServletRequest.removeAttribute("colecaoEmpresaCobrancaSelecao");
		httpServletRequest.removeAttribute("colecaoEmpresaFaturamentoSelecao");
		httpServletRequest.removeAttribute("colecaoGrupoCobrancaSelecao");
		httpServletRequest.removeAttribute("colecaoGrupoFaturamentoSelecao");
		httpServletRequest.removeAttribute("colecaoCobrancaGrupoDestino");
		httpServletRequest.removeAttribute("colecaoFaturamentoGrupoDestino");
		httpServletRequest.removeAttribute("colecaoEmpresaDestino");
		httpServletRequest.removeAttribute("desabilitaFiltroESelecao");
	}
	
	/**			
	 * @date 20/10/09
	 * @author Anderson Italo
	 * pesquisar da localidade digitada
	 */
	private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, 
			 	TransferirRotasGruposEmpresasActionForm formulario,
				HttpServletRequest httpServletRequest,
				Fachada fachada,
				HttpSession sessao,
				boolean indicadorFinal) {

			//Verifica se o código da Localidade foi digitado
			if (idDigitadoEnterLocalidade != null
			&& !idDigitadoEnterLocalidade.trim().equals("")
			&& Integer.parseInt(idDigitadoEnterLocalidade) > 0) {
			
				//Recupera a localidade informada pelo usuário
				Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));
				
				//Caso a localidade informada pelo usuário esteja cadastrada no sistema
				//Seta os dados da localidade no form
				//Caso contrário seta as informações da localidade para vazio 
				//e indica ao usuário que a localidade não existe 
				
				if (localidadeEncontrada != null) {
					//a localidade foi encontrada
					if (!indicadorFinal){
						formulario.setIdLocalidadeInicial("" + (localidadeEncontrada.getId()));
						formulario.setDescricaoLocalidadeInicial(localidadeEncontrada.getDescricao());
						
						if (formulario.getIdLocalidadeFinal() == null || formulario.getIdLocalidadeFinal().equals("")
								|| formulario.getIdLocalidadeFinal().equals(localidadeEncontrada.getId().toString())){
							formulario.setIdLocalidadeFinal("" + (localidadeEncontrada.getId()));
							formulario.setDescricaoLocalidadeFinal(localidadeEncontrada.getDescricao());
							httpServletRequest.setAttribute("desabilitaSetor","false");
						}else{
							//limpa campos do setor comercial
							formulario.setIdSetorComercialFinal("");
							formulario.setDescricaoSetorComercialFinal("");
							formulario.setIdRotaFinal("");
							httpServletRequest.setAttribute("desabilitaSetor","true");
						}
						
						httpServletRequest.setAttribute("idLocalidadeInicialNaoEncontrada","true");
						httpServletRequest.setAttribute("nomeCampo","idSetorComercialNaoEncontrada");
					}else{
						formulario.setIdLocalidadeFinal("" + (localidadeEncontrada.getId()));
						formulario.setDescricaoLocalidadeFinal(localidadeEncontrada.getDescricao());
						httpServletRequest.setAttribute("idLocalidadeFinalNaoEncontrada","true");
						httpServletRequest.setAttribute("nomeCampo","idSetorComercialNaoEncontrada");
					}
				
				} else {
					//a localidade não foi encontrada
					if (!indicadorFinal){
						formulario.setIdLocalidadeInicial("");
						httpServletRequest.setAttribute("idLocalidadeInicialNaoEncontrada","exception");
						formulario.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
						httpServletRequest.setAttribute("nomeCampo","idLocalidadeInicial");
					}else{
						formulario.setIdLocalidadeFinal("");
						httpServletRequest.setAttribute("idLocalidadeFinalNaoEncontrada","exception");
						formulario.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
						httpServletRequest.setAttribute("nomeCampo","idLocalidadeFinal");
					}
				}
				
				
			}
		
	}


	/**			
	 * @date 20/10/09
	 * @author Anderson Italo
	 * pesquisar do setor comercial informado
	 */
	private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade,
					String idDigitadoEnterSetorComercial, 
					TransferirRotasGruposEmpresasActionForm formulario,
					HttpServletRequest httpServletRequest,
					Fachada fachada,
					HttpSession sessao,
					boolean indicadorFinal) {
		
	    	//	Verifica se o código do Setor Comercial foi digitado
		    if ((idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.toString()
					.trim().equalsIgnoreCase(""))
					&& (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.toString().trim()
							.equalsIgnoreCase(""))) {
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		    	if (idDigitadoEnterLocalidade != null
						&& !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {
					
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE, new Integer(
									idDigitadoEnterLocalidade)));
				}
		    	
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
								idDigitadoEnterSetorComercial)));
		   	
				Collection<SetorComercial> setorComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());
				
				
				if (setorComerciais != null && !setorComerciais.isEmpty()) {
					//o setor comercial foi encontrado
					SetorComercial setorComercialEncontrado = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
		
					if (!indicadorFinal){
						//setor inicial
						formulario.setIdSetorComercialInicial( "" +  (setorComercialEncontrado.getCodigo()));
						formulario.setDescricaoSetorComercialInicial(setorComercialEncontrado.getDescricao());
						formulario.setIdRotaInicial("");
						
						if (formulario.getIdLocalidadeInicial().equals(formulario.getIdLocalidadeInicial())
								&& (formulario.getIdSetorComercialFinal() == null 
										|| formulario.getIdSetorComercialFinal().equals(""))){
							formulario.setIdSetorComercialFinal( "" +  (setorComercialEncontrado.getCodigo()));
							formulario.setDescricaoSetorComercialFinal(setorComercialEncontrado.getDescricao());
							formulario.setIdRotaFinal("");
						}
						
						httpServletRequest.setAttribute("idSetorComercialInicialNaoEncontrado","true");
						httpServletRequest.setAttribute("nomeCampo","idSetorComercialInicial");
					}else{
						//setor final
						formulario.setIdSetorComercialFinal( "" +  (setorComercialEncontrado.getCodigo()));
						formulario.setDescricaoSetorComercialFinal(setorComercialEncontrado.getDescricao());
						httpServletRequest.setAttribute("idSetorComercialFinalNaoEncontrado","true");
						httpServletRequest.setAttribute("nomeCampo","idSetorComercialFinal");
					}
					
				} else {
					//o setor comercial não foi encontrado
					if (!indicadorFinal){
						formulario.setIdSetorComercialInicial("");
						formulario.setDescricaoSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
						httpServletRequest.setAttribute("idSetorComercialInicialNaoEncontrado","exception");
						httpServletRequest.setAttribute("nomeCampo","idSetorComercialInicial");
					}else{
						formulario.setIdSetorComercialFinal("");
						formulario.setDescricaoSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
						httpServletRequest.setAttribute("idSetorComercialFinalNaoEncontrado","exception");
						httpServletRequest.setAttribute("nomeCampo","idSetorComercialFinal");
					}
				}
				
				
			}
   	}
	
	/**			
	 * @date 20/10/09
	 * @author Anderson Italo
	 * [FS0006] - Obter Lista de Rotas
	 */
	private void efetuarFiltroRotas(TransferirRotasGruposEmpresasActionForm formulario,
			HttpServletRequest httpServletRequest,
			Fachada fachada,
			HttpSession sessao){
		
		boolean informouParametro = false;
		Collection rotasSelecionadas = null;
		FiltroRota filtroRota = null;
		
		//localidade
		if (formulario.getIdLocalidadeInicial() != null && !formulario.getIdLocalidadeInicial().equals("")){
			
			//[FS0003] - Verificar existência da localidade
			//Recupera a localidade inicial informada pelo usuário
			Localidade localidadeInicialEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(formulario.getIdLocalidadeInicial()));
			
			if (localidadeInicialEncontrada == null){
				throw new ActionServletException("pesquisa.localidade.inexistente",
     					null,"Localidade Inicial ");	
			}
			
			//Recupera a localidade final informada pelo usuário
			Localidade localidadeFinalEncontrada = null;
			if (formulario.getIdLocalidadeFinal() != null && !formulario.getIdLocalidadeFinal().equals("")){
				localidadeFinalEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(formulario.getIdLocalidadeFinal()));
				
				if (localidadeFinalEncontrada == null){
					throw new ActionServletException("pesquisa.localidade.inexistente",
	     					null,"Localidade Final ");
				}else{
					//valida intervalo de localidade
					if (localidadeFinalEncontrada.getId().intValue() < localidadeInicialEncontrada.getId().intValue() ){
						throw new ActionServletException("atencao.localidade.final.maior.localidade.inicial",
		     					null,"Localidade Final ");	
					}
				}
			}else{
				localidadeFinalEncontrada = localidadeInicialEncontrada;
			}
			
			
			//setor comercial
			if (formulario.getIdSetorComercialInicial() != null && !formulario.getIdSetorComercialInicial().equals("")){
				
				//[FS0004] - Verificar existência do setor
				//Recupera o setor inicial informada pelo usuário
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeInicialEncontrada.getId()));
			
	    	
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
								formulario.getIdSetorComercialInicial())));
		   	
				Collection<SetorComercial> setorComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());
				
				SetorComercial setorInicialEncontrado = null;
				if (setorComerciais == null || setorComerciais.isEmpty() ){
					throw new ActionServletException("atencao.setor_comercial.inexistente",
	     					null,"Setor Comercial Inicial ");	
				}else{
					setorInicialEncontrado = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
				}
				
				//Recupera o setor final informada pelo usuário
				SetorComercial setorFinalEncontrado = null;
				if (formulario.getIdSetorComercialFinal() != null && !formulario.getIdSetorComercialFinal().equals("")){
					
					filtroSetorComercial = new FiltroSetorComercial();
					
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.ID_LOCALIDADE, localidadeFinalEncontrada.getId()));
				
		    	
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
									formulario.getIdSetorComercialFinal())));
			   	
					Collection<SetorComercial> setorComerciaisAux = fachada.pesquisar(
							filtroSetorComercial, SetorComercial.class.getName());
					
					if (setorComerciaisAux == null || setorComerciaisAux.isEmpty() ){
						throw new ActionServletException("atencao.setor_comercial.inexistente",
		     					null,"Setor Comercial Final ");	
					}else{
						setorFinalEncontrado =(SetorComercial) Util.retonarObjetoDeColecao(setorComerciaisAux);
						
						//valida intervalo de setor comercial
						if (setorFinalEncontrado.getId().intValue() < setorInicialEncontrado.getId().intValue() ){
							throw new ActionServletException("atencao.setor.comercial.final.maior.setor.comercial.inicial",
			     					null,"Setor Comercial Final ");	
						}
					}
					
				}else{
					setorFinalEncontrado = setorInicialEncontrado;
				}
				
				//rota
				/*1.	Caso tenha informado o intervalo de rotas, 
				 * seleciona as rotas do intervalo (a partir da tabela ROTA com ROTA_ID BETWEEN 
				 * Id da Rota Inicial e Id da Rota Final) e retorna a lista de rotas para o passo 
				 * que chamou este subfluxo.*/
				if (formulario.getIdRotaInicial() != null && !formulario.getIdRotaInicial().equals("")){
					
					//[FS0005] - Verificar existência de rotas
					//Recupera a rota inicial informada pelo usuário
					filtroRota = new FiltroRota();
					
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.LOCALIDADE_ID, localidadeInicialEncontrada.getId()));
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.SETOR_COMERCIAL_ID, setorInicialEncontrado.getId()));
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.CODIGO_ROTA, new Integer(formulario.getIdRotaInicial())));
					
					Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
					
					Rota rotaInicialEncontrada = null;
					if (colecaoRotas == null || colecaoRotas.isEmpty()){
						throw new ActionServletException("atencao.pesquisa.rota_inexistente",
		     					null,"Rota Inicial ");	
					}else{
						rotaInicialEncontrada = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
					}
					
					//Recupera a rota final informada pelo usuário
					Rota rotaFinalEncontrada = null;
					if (formulario.getIdRotaFinal() != null && formulario.getIdRotaFinal().equals("")){
						filtroRota = new FiltroRota();
						
						filtroRota.adicionarParametro(new ParametroSimples(
								FiltroRota.LOCALIDADE_ID, localidadeFinalEncontrada.getId()));
						filtroRota.adicionarParametro(new ParametroSimples(
								FiltroRota.SETOR_COMERCIAL_ID, setorFinalEncontrado.getId()));
						filtroRota.adicionarParametro(new ParametroSimples(
								FiltroRota.CODIGO_ROTA, new Integer(formulario.getIdRotaFinal())));
						
						Collection colecaoRotasAux = fachada.pesquisar(filtroRota, Rota.class.getName());
						
						if (colecaoRotas == null || colecaoRotas.isEmpty()){
							throw new ActionServletException("atencao.pesquisa.rota_inexistente",
			     					null,"Rota Final ");	
						}else{
							rotaFinalEncontrada = (Rota) Util.retonarObjetoDeColecao(colecaoRotasAux);
						}
					}else{
						rotaFinalEncontrada = rotaInicialEncontrada;
					}
					
					if (rotaInicialEncontrada != null){
						
						if (rotaFinalEncontrada.getId().intValue() < rotaInicialEncontrada.getId().intValue() ){
							
							formulario.setIdRotaFinal("");
							throw new ActionServletException("atencao.rota.final.maior.rota.inicial",
			     					null,"Rota Final ");
						}
						
						informouParametro = true;
						filtroRota = new FiltroRota();
						filtroRota.adicionarParametro(new Intervalo(
									FiltroRota.ID_ROTA, 
									rotaInicialEncontrada.getId(), 
									rotaFinalEncontrada.getId()));
					}
					
				}else{
					/*2. 	Caso contrário, caso tenha informado o intervalo de setores, 
					 * [seleciona as rotas de cada um dos setores do intervalo (ROTA_ID da 
					 * tabela ROTA com STCM_ID BETWEEN Id do Setor Comercial Inicial e Id 
					 * do Setor Comercial Inicial).*/
					
					informouParametro = true;
					filtroRota = new FiltroRota();
					filtroRota.adicionarParametro(new Intervalo(
								FiltroRota.SETOR_COMERCIAL_ID, 
								setorInicialEncontrado.getId(), 
								setorFinalEncontrado.getId()));
				}
			}else{
				/*3.	Caso contrário, caso tenha informado o intervalo de localidade, seleciona 
				 * as rotas dos setores de cada uma das localidades do intervalo (ROTA_ID da 
				 * tabela ROTA com STCM_ID=STCM_ID da tabela SETOR_COMERCIAL com LOCA_ID=LOCA_ID 
				 * da tabela LOCALIDADE com LOCA_ID BETWEEN Id da Localidade Inicial 
				 * e Id da Localidade Inicial).*/
				
				informouParametro = true;
				filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new Intervalo(
							FiltroRota.LOCALIDADE_ID, 
							localidadeInicialEncontrada.getId(), 
							localidadeFinalEncontrada.getId()));
			}
		
		}else if (formulario.getIdUnidadeNegocio() != null && formulario.getIdUnidadeNegocio().length > 0){
				
			/*4.	Caso contrário, caso tenha informado a Unidade de Negócio, seleciona as rotas 
			 * dos setores das localidades da Unidade de Negócio (ROTA_ID da tabela ROTA com 
			 * STCM_ID=STCM_ID da tabela SETOR_COMERCIAL com LOCA_ID=LOCA_ID da tabela 
			 * LOCALIDADE com UNEG_ID=Id da Unidade de Negócio).*/
			
			Collection idsUnidadeNegocio = new ArrayList();
			
			for (int i = 0; i < formulario.getIdUnidadeNegocio().length; i++) {
				if (!formulario.getIdUnidadeNegocio()[i].equals("-1") && !formulario.getIdUnidadeNegocio()[i].equals("")){
					idsUnidadeNegocio.add(new Integer(formulario.getIdUnidadeNegocio()[i]));
				}
			}
			
			if (!idsUnidadeNegocio.isEmpty()){
				
				informouParametro = true;
				filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimplesIn(FiltroRota.UNIDADE_NEGOCIO_ID, idsUnidadeNegocio));
			}
			
		}else if (formulario.getIdGerenciaRegional() != null && formulario.getIdGerenciaRegional().length > 0){
			/*5.	Caso contrário, caso tenha informado a gerência, seleciona 
			 * as rotas dos setores das localidades da gerência (ROTA_ID da tabela 
			 * ROTA com STCM_ID=STCM_ID da tabela SETOR_COMERCIAL com LOCA_ID=LOCA_
			 * ID da tabela LOCALIDADE com UNEG_ID = UNEG_ID da tabela UNIDADE_NEGOCIO  
			 * com GREG_ID=Id da Gerência).*/
			
			Collection idsGerencia = new ArrayList();
			
			for (int i = 0; i < formulario.getIdGerenciaRegional().length; i++) {
				if (!formulario.getIdGerenciaRegional()[i].equals("-1") && !formulario.getIdGerenciaRegional()[i].equals("")){
					idsGerencia.add(new Integer(formulario.getIdGerenciaRegional()[i]));
				}
			}
			
			if (!idsGerencia.isEmpty()){
				
				informouParametro = true;
				filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimplesIn(FiltroRota.GERENCIA_REGIONAL_ID, idsGerencia));
			}
			
		}
		
		if (formulario.getIdGrupoFaturamentoFiltro() != null && formulario.getIdGrupoFaturamentoFiltro().length > 0){
			/*6.	Caso tenha informado grupo(s) de faturamento, seleciona as rotas 
			 * pertencentes ao(s) grupo(s) de faturamento (ROTA_ID da tabela ROTA com 
			 * FTGR_ID = FTGR_ID do(s) grupo(s)  informado(s)).*/
			
			Collection idsGrupoFaturamentoFiltro = new ArrayList();
			
			for (int i = 0; i < formulario.getIdGrupoFaturamentoFiltro().length; i++) {
				if (!formulario.getIdGrupoFaturamentoFiltro()[i].equals("-1") && !formulario.getIdGrupoFaturamentoFiltro()[i].equals("")){
					idsGrupoFaturamentoFiltro.add(new Integer(formulario.getIdGrupoFaturamentoFiltro()[i]));
				}
			}
			
			if (!idsGrupoFaturamentoFiltro.isEmpty()){
				
				informouParametro = true;
				
				if (filtroRota == null){
					filtroRota = new FiltroRota();
				}
				
				filtroRota.adicionarParametro(new ParametroSimplesIn(FiltroRota.FATURAMENTO_GRUPO_ID, idsGrupoFaturamentoFiltro));
			}
			
		}
		
		if (formulario.getIdGrupoCobrancaFiltro() != null && formulario.getIdGrupoCobrancaFiltro().length > 0){
			/*7.	Caso tenha informado grupo(s) de cobrança, seleciona as rotas pertencentes 
			 * ao(s) grupo(s) de cobrança  (ROTA_ID da tabela ROTA com CBGR_ID = CBGR_ID do(s) 
			 * grupo(s)  informado(s)).*/
			
			Collection idsGrupoCobrancaFiltro = new ArrayList();
			
			for (int i = 0; i < formulario.getIdGrupoCobrancaFiltro().length; i++) {
				if (!formulario.getIdGrupoCobrancaFiltro()[i].equals("-1") && !formulario.getIdGrupoCobrancaFiltro()[i].equals("")){
					idsGrupoCobrancaFiltro.add(new Integer(formulario.getIdGrupoCobrancaFiltro()[i]));
				}
			}
			
			if (!idsGrupoCobrancaFiltro.isEmpty()){
				
				informouParametro = true;
				
				if (filtroRota == null){
					filtroRota = new FiltroRota();
				}
				
				filtroRota.adicionarParametro(new ParametroSimplesIn(FiltroRota.COBRANCA_GRUPO_ID, idsGrupoCobrancaFiltro));
			}
			
		}
		
		if (formulario.getIdEmpresaFaturamentoFiltro() != null && formulario.getIdEmpresaFaturamentoFiltro().length > 0){
			/*8.	Caso tenha informado empresa(s) de faturamento, seleciona 
			 * as rotas vinculadas a esta(s) empresa(s) de faturamento (ROTA_ID 
			 * da tabela ROTA com EMPR_ID = EMPR_ID da(s) empresa(s)  de 
			 * faturamento informada(s)).*/
			
			Collection idsEmpresaFaturamentoFiltro = new ArrayList();
			
			for (int i = 0; i < formulario.getIdEmpresaFaturamentoFiltro().length; i++) {
				if (!formulario.getIdEmpresaFaturamentoFiltro()[i].equals("-1") && !formulario.getIdEmpresaFaturamentoFiltro()[i].equals("")){
					idsEmpresaFaturamentoFiltro.add(new Integer(formulario.getIdEmpresaFaturamentoFiltro()[i]));
				}
			}
			
			if (!idsEmpresaFaturamentoFiltro.isEmpty()){
				
				informouParametro = true;
				
				if (filtroRota == null){
					filtroRota = new FiltroRota();
				}
				
				filtroRota.adicionarParametro(new ParametroSimplesIn(FiltroRota.EMPRESA_ID, idsEmpresaFaturamentoFiltro));
			}
			
		}
		
		if (formulario.getIdEmpresaCobrancaFiltro() != null && formulario.getIdEmpresaCobrancaFiltro().length > 0){
			/*9.	Caso tenha informado empresa(s) de cobrança, seleciona as rotas 
			 * vinculadas a esta(s) empresa(s) de cobrança (ROTA_ID da tabela ROTA 
			 * com EMPR_IDCOBRANCA = EMPR_ID da(s) empresa(s)  de cobrança  
			 * informada(s)).*/
			
			Collection idsEmpresaCobrancaFiltro = new ArrayList();
			
			for (int i = 0; i < formulario.getIdEmpresaCobrancaFiltro().length; i++) {
				if (!formulario.getIdEmpresaCobrancaFiltro()[i].equals("-1") && !formulario.getIdEmpresaCobrancaFiltro()[i].equals("")){
					idsEmpresaCobrancaFiltro.add(new Integer(formulario.getIdEmpresaCobrancaFiltro()[i]));
				}
			}
			
			if (!idsEmpresaCobrancaFiltro.isEmpty()){
				
				informouParametro = true;
				
				if (filtroRota == null){
					filtroRota = new FiltroRota();
				}
				
				filtroRota.adicionarParametro(new ParametroSimplesIn(FiltroRota.EMPRESA_COBRANCA_ID, idsEmpresaCobrancaFiltro));
			}
			
		}
		
		if (formulario.getIdLeiturista() != null && !formulario.getIdLeiturista().toString().equals("")){
				
				informouParametro = true;
				
				if (filtroRota == null){
					filtroRota = new FiltroRota();
				}
				
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LEITURISTA_ID, new Integer(formulario.getIdLeiturista())));
		}
		
		if (formulario.getIdTipoLeitura() != null && !formulario.getIdTipoLeitura().equals("-1")){
			
			informouParametro = true;
			
			if (filtroRota == null){
				filtroRota = new FiltroRota();
			}
			
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LEITURA_TIPO_ID, new Integer(formulario.getIdTipoLeitura())));
	}
			
		
		
		if (informouParametro){
			/*10.	O sistema retorna a coleção de rotas que satisfaçam 
			 * a todos os parâmetros informados*/
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO));
			rotasSelecionadas = fachada.pesquisar(filtroRota, Rota.class.getName());
			
		}else{
			//11.	Caso não tenha informado nenhum parâmetro seleciona todas as rotas.
			filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO));
			rotasSelecionadas = fachada.pesquisar(filtroRota, Rota.class.getName());
		}
		
		//[FS0005] - Verificar existência de rotas
		if (rotasSelecionadas != null && !rotasSelecionadas.isEmpty()){
			//4.1.	A quantidade total de rotas no universo selecionado
			formulario.setQuantidadeRotas(String.valueOf(rotasSelecionadas.size()));
			sessao.setAttribute("rotasSelecionadas", rotasSelecionadas);
			httpServletRequest.setAttribute("desabilitaFiltroESelecao", true);
			
			/*4.2.	Os grupos de faturamento aos quais pertencem as rotas 
			 * (Obter na tabela ROTA a partir de FTGR_ID aos grupos de faturamento
			 *  aos quais pertencem as rotas da selecionadas*/
			Collection idsGrupoFaturamento = new ArrayList();
			
			for (Iterator iter = rotasSelecionadas.iterator(); iter.hasNext();) {
				Rota rota = (Rota) iter.next();
				idsGrupoFaturamento.add(rota.getFaturamentoGrupo().getId());
			}
			
			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimplesIn(FiltroFaturamentoGrupo.ID , idsGrupoFaturamento));
			filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
			Collection colecaoGrupoFaturamento = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			httpServletRequest.setAttribute("colecaoGrupoFaturamentoSelecao", colecaoGrupoFaturamento);
			
			/*4.3.	Os grupos de cobrança aos quais pertencem as rotas 
			 * (Obter na tabela ROTA a partir de CBGR_ID aos grupos de cobrança 
			 * aos quais pertencem as rotas da selecionadas.*/
			Collection idsGrupoCobranca = new ArrayList();
			
			for (Iterator iter = rotasSelecionadas.iterator(); iter.hasNext();) {
				Rota rota = (Rota) iter.next();
				idsGrupoCobranca.add(rota.getCobrancaGrupo().getId());
			}
			
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimplesIn(FiltroCobrancaGrupo.ID , idsGrupoCobranca));
			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
			Collection colecaoGrupoCobranca = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			httpServletRequest.setAttribute("colecaoGrupoCobrancaSelecao", colecaoGrupoCobranca);
			
			/*4.4.	As empresas de faturamento às quais pertencem 
			 * as rotas (Obter na tabela ROTA a partir de EMPR_ID as 
			 * empresas de faturamento às quais estão vinculadas as 
			 * rotas da selecionadas)*/
			Collection idsEmpresaFaturamento = new ArrayList();
			
			for (Iterator iter = rotasSelecionadas.iterator(); iter.hasNext();) {
				Rota rota = (Rota) iter.next();
				idsEmpresaFaturamento.add(rota.getEmpresa().getId());
			}
			
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimplesIn(FiltroEmpresa.ID , idsEmpresaFaturamento));
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			Collection colecaoEmpresaFaturamento = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			httpServletRequest.setAttribute("colecaoEmpresaFaturamentoSelecao", colecaoEmpresaFaturamento);
			
			/*4.5.	As empresas de cobrança às quais pertencem as rotas (Obter 
			 * na tabela ROTA a partir de EMPR_IDCOBRANCA as empresas de cobrança 
			 * às quais estão vinculadas as rotas da selecionadas)*/
			Collection idsEmpresaCobranca = new ArrayList();
			
			for (Iterator iter = rotasSelecionadas.iterator(); iter.hasNext();) {
				Rota rota = (Rota) iter.next();
				idsEmpresaCobranca.add(rota.getEmpresaCobranca().getId());
			}
			
			filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimplesIn(FiltroEmpresa.ID , idsEmpresaCobranca));
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			Collection colecaoEmpresaCobranca = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			httpServletRequest.setAttribute("colecaoEmpresaCobrancaSelecao", colecaoEmpresaCobranca);
			
			/*5.1.	Grupo de Cobrança (seleciona a partir da tabela COBRANCA_GRUPO) 
			 * [FS0001 - Verificar existência de dados].*/
			filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
			Collection colecaoCobrancaGrupoDestino = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			
			if (colecaoCobrancaGrupoDestino == null || colecaoCobrancaGrupoDestino.isEmpty()){
				throw new ActionServletException("Atencao.Sem_dados_para_selecao");
			}
			
			httpServletRequest.setAttribute("colecaoCobrancaGrupoDestino", colecaoCobrancaGrupoDestino);
			
			/*5.2.	Grupo de Faturamento (seleciona a partir da tabela FATURAMENTO_GRUPO) 
			 * [FS0001 - Verificar existência de dados].*/
			filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
			Collection colecaoFaturamentoGrupoDestino = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			
			if (colecaoFaturamentoGrupoDestino == null || colecaoFaturamentoGrupoDestino.isEmpty()){
				throw new ActionServletException("Atencao.Sem_dados_para_selecao");
			}
			
			httpServletRequest.setAttribute("colecaoFaturamentoGrupoDestino",colecaoFaturamentoGrupoDestino);
			
			/*5.3.	Empresa de Faturamento (seleciona a partir da tabela EMPRESA)
			 * [FS0001 - Verificar existência de dados];
			 * 5.4.	Empresa de Cobrança (seleciona a partir da tabela EMPRESA) 
			 * [FS0001 - Verificar existência de dados];*/
			
			filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO , ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			Collection colecaoEmpresaDestino = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			if (colecaoEmpresaDestino == null || colecaoEmpresaDestino.isEmpty()){
				throw new ActionServletException("Atencao.Sem_dados_para_selecao");
			}
			
			httpServletRequest.setAttribute("colecaoEmpresaDestino",colecaoEmpresaDestino);
			
		}else{
			
			throw new ActionServletException("atencao.rotas_inexistentes_criterio_informado",
 					null,"Pesquisa Nenhum Resultado");
		}
		
		
	}
	
	
	 private void verificaExistenciaCodLeiturista(String idDigitadoEnterLeiturista, 
	            TransferirRotasGruposEmpresasActionForm form,
	            HttpServletRequest httpServletRequest) {
	        
	        //Verifica se o código do Leiturista foi digitado
	        if (idDigitadoEnterLeiturista != null
	            && !idDigitadoEnterLeiturista.trim().equals("")
	            && Integer.parseInt(idDigitadoEnterLeiturista) > 0) {
	            
	            //Recupera o leiturista informado pelo usuário
	            FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
	            filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
	            filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
	            filtroLeiturista.adicionarParametro(new ParametroSimples(
	                    FiltroLeiturista.ID, idDigitadoEnterLeiturista));
	            filtroLeiturista.adicionarParametro(new ParametroSimples(
	                    FiltroLeiturista.INDICADOR_USO,
	                    ConstantesSistema.INDICADOR_USO_ATIVO));
	            
	            if(form.getIdEmpresaCobrancaFiltro()!=null){
	            	Collection<Integer> idsEmpresasCobranca = new ArrayList<Integer>();
	            	for (int i = 0; i < form.getIdEmpresaCobrancaFiltro().length; i++) {
						Integer idEmpresaCobraca = new Integer(form.getIdEmpresaCobrancaFiltro()[i]);
						idsEmpresasCobranca.add(idEmpresaCobraca);
					}
	            	
	            	filtroLeiturista.adicionarParametro(new ParametroSimplesIn(FiltroLeiturista.EMPRESA_ID, idsEmpresasCobranca));
	            }
	            
	            if(form.getIdEmpresaFaturamentoFiltro()!=null){
	            	Collection<Integer> idsEmpresasFaturamento = new ArrayList<Integer>();
	            	for (int i = 0; i < form.getIdEmpresaFaturamentoFiltro().length; i++) {
						Integer idEmpresaCobraca = new Integer(form.getIdEmpresaFaturamentoFiltro()[i]);
						idsEmpresasFaturamento.add(idEmpresaCobraca);
					}
	            	
	            	filtroLeiturista.adicionarParametro(new ParametroSimplesIn(FiltroLeiturista.EMPRESA_ID, idsEmpresasFaturamento));
	            }


	            Collection leituristaEncontrado = this.getFachada().pesquisar(filtroLeiturista,
	                    Leiturista.class.getName());
	            
	            //Caso o leiturista informado pelo usuário esteja cadastrado no sistema
	            //Seta os dados do leiturista no form
	            //Caso contrário seta as informações de leiturista para vazio 
	            //e indica ao usuário que o leiturista não existe 
	            
	            if (leituristaEncontrado != null && leituristaEncontrado.size() > 0) {
	                //leiturista foi encontrado
	                Leiturista leiturista = (Leiturista) ((List) leituristaEncontrado).get(0); 
	                form.setIdLeiturista("" + 
	                    leiturista.getId());
	                if (leiturista.getFuncionario() != null){
	                    form.setNomeLeiturista(leiturista.getFuncionario().getNome());                 
	                } else if (leiturista.getCliente() != null){
	                    form.setNomeLeiturista(leiturista.getCliente().getNome());
	                }
	                httpServletRequest.setAttribute("idLeituristaEncontrado","true");
	                httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
	                httpServletRequest.setAttribute( "idLeituristaEncontrado", "" );
	            } else {
	                //o leiturista não foi encontrado
	                form.setIdLeiturista("");
	                form.setNomeLeiturista("LEITURISTA INEXISTENTE");
	                httpServletRequest.removeAttribute("idLeituristaEncontrado");
	                httpServletRequest.setAttribute("nomeCampo","idLeiturista");
	            }
	        }       
	    }  
	
	
	

}

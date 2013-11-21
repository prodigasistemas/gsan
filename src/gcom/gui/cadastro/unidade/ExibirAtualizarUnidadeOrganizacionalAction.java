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
package gcom.gui.cadastro.unidade;

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

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
 * @author Ana Maria
 * @date 22/11/2006
 */
public class ExibirAtualizarUnidadeOrganizacionalAction extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarUnidadeOrganizacional");

        //Obtém o action form
        AtualizarUnidadeOrganizacionalActionForm form = (AtualizarUnidadeOrganizacionalActionForm) actionForm;

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

        String idUnidadeOrganizacional = httpServletRequest.getParameter("idRegistroAtualizacao");
        
        if (idUnidadeOrganizacional == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				idUnidadeOrganizacional = (String) sessao.getAttribute("idRegistroAtualizacao");
			}else{
				idUnidadeOrganizacional = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		}/* else {
			sessao.setAttribute("i", true);
		}*/

        //Obtém a facahda
        Fachada fachada = Fachada.getInstancia();

        //Verifica se os objetos estão na sessão
        if (idUnidadeOrganizacional != null && !idUnidadeOrganizacional.equals("")) {         
            
            UnidadeOrganizacional unidadeOrganizacional = fachada.pesquisarUnidadeOrganizacional(new Integer(idUnidadeOrganizacional));

            if (unidadeOrganizacional != null && !unidadeOrganizacional.equals("")) {               
                sessao.setAttribute("unidadeOrganizacional", unidadeOrganizacional);
                form.setId(formatarResultado(""+unidadeOrganizacional.getId()));
                form.setUnidadeTipo(formatarResultado(""+ unidadeOrganizacional.getUnidadeTipo().getId()));
                if(unidadeOrganizacional.getLocalidade() != null && !unidadeOrganizacional.getLocalidade().equals("")){
                  form.setIdLocalidade(formatarResultado(""+unidadeOrganizacional.getLocalidade().getId()));
                  form.setDescricaoLocalidade(formatarResultado(unidadeOrganizacional.getLocalidade().getDescricao()));
                }
                if(unidadeOrganizacional.getGerenciaRegional() != null && !unidadeOrganizacional.getGerenciaRegional().equals("")){
                	if(unidadeOrganizacional.getGerenciaRegional().getNomeAbreviado() != null && !unidadeOrganizacional.getGerenciaRegional().getNomeAbreviado().equals("")){
                		form.setGerenciaRegional(formatarResultado(unidadeOrganizacional.getGerenciaRegional().getNomeAbreviado()+"-"+unidadeOrganizacional.getGerenciaRegional().getNome()));
                	}else{
                		form.setGerenciaRegional(formatarResultado(unidadeOrganizacional.getGerenciaRegional().getNome()));
                	}                		
                }
                if((unidadeOrganizacional.getLocalidade() != null && !unidadeOrganizacional.getLocalidade().equals("")) ||
                		(unidadeOrganizacional.getGerenciaRegional() != null && !unidadeOrganizacional.getGerenciaRegional().equals(""))){
                	sessao.setAttribute("naomodificar", "naomodificar");
                }
                form.setDescricao(formatarResultado(unidadeOrganizacional.getDescricao()));
                form.setSigla(formatarResultado(unidadeOrganizacional.getSigla()));                
                if(unidadeOrganizacional.getEmpresa() != null && !unidadeOrganizacional.getEmpresa().equals("")){
                  form.setIdEmpresa(formatarResultado(""+unidadeOrganizacional.getEmpresa().getId()));
                }
                if (unidadeOrganizacional.getUnidadeTipo().getCodigoTipo() != null && !unidadeOrganizacional.getUnidadeTipo().getCodigoTipo().equals("")) {
					if (!unidadeOrganizacional.getUnidadeTipo().getCodigoTipo().equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)) {
						form.setEmpresa(formatarResultado(unidadeOrganizacional.getEmpresa().getDescricao()));
						sessao.setAttribute("naoModificarEmpresa", "naoModificarEmpresa");
					}
				}
                
                if (unidadeOrganizacional.getUnidadeNegocio() != null) {
                	form.setUnidadeNegocio(unidadeOrganizacional.getDescricao());
                }
                
                if(unidadeOrganizacional.getUnidadeSuperior() != null && !unidadeOrganizacional.getUnidadeSuperior().equals("")){
                  form.setIdUnidadeSuperior(formatarResultado(""+unidadeOrganizacional.getUnidadeSuperior().getId()));
                  form.setDescricaoUnidadeSuperior(formatarResultado(unidadeOrganizacional.getUnidadeSuperior().getDescricao()));
                }
                if(unidadeOrganizacional.getUnidadeCentralizadora() != null && !unidadeOrganizacional.getUnidadeCentralizadora().equals("")){
                  form.setIdUnidadeCentralizadora(""+unidadeOrganizacional.getUnidadeCentralizadora().getId());
                }
                form.setUnidadeEsgoto(formatarResultado(""+unidadeOrganizacional.getIndicadorEsgoto()));
                form.setUnidadeAbreRegistro(formatarResultado(""+unidadeOrganizacional.getIndicadorAberturaRa()));
                form.setUnidadeAceita(formatarResultado(""+unidadeOrganizacional.getIndicadorTramite()));              
                if(unidadeOrganizacional.getMeioSolicitacao() != null && !unidadeOrganizacional.getMeioSolicitacao().equals("")){                
                  form.setMeioSolicitacao(formatarResultado(""+unidadeOrganizacional.getMeioSolicitacao().getId()));
                }
                form.setIndicadorUso(""+unidadeOrganizacional.getIndicadorUso());
                
                if (unidadeOrganizacional.getUnidadeRepavimentadora() != null){
                	form.setIdUnidadeRepavimentadora(unidadeOrganizacional.getUnidadeRepavimentadora().getId().toString());
                }
            }
            else
            {
            	throw new ActionServletException("atencao.atualizacao.timestamp");
            }

            //Filtro de tipo de unidade
            FiltroUnidadeTipo filtroUnidadeTipo = new FiltroUnidadeTipo();

            filtroUnidadeTipo.adicionarParametro(new ParametroSimples(
            		FiltroUnidadeTipo.INDICADOR_USO,
                            ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroUnidadeTipo.setCampoOrderBy(FiltroUnidadeTipo.DESCRICAO);

            Collection colecaoUnidadeTipo = fachada.pesquisar(
            		filtroUnidadeTipo, UnidadeTipo.class.getName());
           
            //Filtro de empresa
            FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
            filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO,
            		ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
            
            Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName()); 
            
            //Filtro de unidade centralizadora
            FiltroUnidadeOrganizacional filtroUnidadeCentralizadora = new FiltroUnidadeOrganizacional();
            filtroUnidadeCentralizadora.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO,
            		ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroUnidadeCentralizadora.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA));
            filtroUnidadeCentralizadora.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);
            
            Collection colecaoUnidadeCentralizadora = fachada.pesquisar(filtroUnidadeCentralizadora, 
            					UnidadeOrganizacional.class.getName());
            
            //Filtro de unidade repavimentadora
        	//...........................................................................................
			// 06/03/2008 - Alteração solicitada por Fabíola Araújo. 
			// Yara Taciane de Souza.
			//8.0 -  Inclusão de opção de tratamento pra Unidade Repavimentadora.	           
            FiltroUnidadeOrganizacional filtroUnidadeRepavimentadora = new FiltroUnidadeOrganizacional();
            filtroUnidadeRepavimentadora.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO,
            		ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroUnidadeRepavimentadora.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA));
            filtroUnidadeRepavimentadora.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);
            
            Collection colecaoUnidadeRepavimentadora = fachada.pesquisar(filtroUnidadeRepavimentadora, 
            					UnidadeOrganizacional.class.getName());
//          .............................................................................................
            
            //Filtro meio de solicitação
            FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
            filtroMeioSolicitacao.adicionarParametro(new ParametroSimples(FiltroMeioSolicitacao.INDICADOR_USO,
            								ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroMeioSolicitacao.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
            
            Collection colecaoMeioSolicitacao = fachada.pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());                        

            //Envia as coleções na sessão
            sessao.setAttribute("colecaoUnidadeTipo", colecaoUnidadeTipo);
            sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
            sessao.setAttribute("colecaoUnidadeCentralizadora", colecaoUnidadeCentralizadora);
            sessao.setAttribute("colecaoUnidadeRepavimentadora", colecaoUnidadeRepavimentadora);
            sessao.setAttribute("colecaoMeioSolicitacao", colecaoMeioSolicitacao);
        }
        
        //Consulta a unidade superior
		consultarUnidadeSuperior(httpServletRequest, form, fachada);

		sessao.removeAttribute("idRegistroAtualizacao");

		//Consulta o Municipio
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("1")) {
			//Seta no form os valores da pesquisa feita pela localidade
			String idMunicipio = httpServletRequest.getParameter("idMunicipio");
			String descricaoMunicipio = httpServletRequest.getParameter("descricaoMunicipio");
			form.setIdMunicipio(idMunicipio);
			form.setDescricaoMunicipio(descricaoMunicipio);
			consultaMunicipio(form, fachada, httpServletRequest);
		}
		
		//Caso volte a funcionalidade e seja feito uma nova pesquisa, limpar as tarifas que ficam na sessão
		//e não entrar novamente nesse metodo, a nao ser que seja a primeira vez que carregue a pagina.
		if ( sessao.getAttribute("menu") != null &&sessao.getAttribute("menu").equals("sim") ) {
			sessao.setAttribute("menu", "nao");
			sessao.setAttribute("colecaoMunicipioSelecionado", null);
		}

		Collection colecaoMunicipioSelecionado = null;
		colecaoMunicipioSelecionado = controlaColecaoMunicipio(form, fachada, sessao, httpServletRequest);
		sessao.setAttribute("colecaoMunicipioSelecionado", colecaoMunicipioSelecionado );
		
		
		
		return retorno;
    }

	private void consultarUnidadeSuperior(HttpServletRequest httpServletRequest, AtualizarUnidadeOrganizacionalActionForm form, Fachada fachada) {
		
		String consultaUnidadeSuperior = (String) httpServletRequest.getParameter("consultaUnidadeSuperior");
		
		if (consultaUnidadeSuperior != null
				&& !consultaUnidadeSuperior.trim().equalsIgnoreCase("")
				&& (Integer.parseInt(consultaUnidadeSuperior)) == 1) {
			
			// Filtro para obter o Unidade Superior ativo de id informado
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, new Integer(form.getIdUnidadeSuperior()),
							ParametroSimples.CONECTOR_AND));

			// Pesquisa de acordo com os parâmetros informados no filtro
			Collection colecaoUnidadeSuperior = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoUnidadeSuperior != null && !colecaoUnidadeSuperior.isEmpty()) {

				// Obtém o objeto da coleção pesquisada
				UnidadeOrganizacional unidadeSuperior = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidadeSuperior);

				// Exibe o código e a descrição pesquisa na página
				httpServletRequest.setAttribute("corUnidadeSuperior", "valor");
				form.setIdUnidadeSuperior(unidadeSuperior.getId().toString());
				form.setDescricaoUnidadeSuperior(unidadeSuperior.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "fixo");
			} else {
				// Exibe mensagem de código inexiste e limpa o campo de código
				httpServletRequest.setAttribute("corUnidadeSuperior","exception");
				form.setIdUnidadeSuperior("");
				form.setDescricaoUnidadeSuperior("UNIDADE ORGANIZACIONAL INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idUnidadeSuperior");	
			}
		}
	}

    private String formatarResultado(String parametro) {
        if (parametro != null && !parametro.trim().equals("")) {
            if (parametro.equals("null")) {
                return "";
            } else {
                return parametro.trim();
            }
        } else {
            return "";
        }
    }
    
    /** 
	 * Controla as manipulações da coleção de Municipio da Unidade Organizacional
	 * @author Arthur Carvalho
	 * @date 09/04/2010
	 * 
	 */
	private ArrayList controlaColecaoMunicipio(AtualizarUnidadeOrganizacionalActionForm 
			form, Fachada fachada, HttpSession sessao, HttpServletRequest httpServletRequest){
		
		ArrayList colecaoMunicipioSelecionado = null;
		Municipio municipio = new Municipio();
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		
		//Caso a colecao ja possua municipios
        if (sessao.getAttribute("colecaoMunicipioSelecionado") != null){
        	
        	colecaoMunicipioSelecionado = (ArrayList) sessao.getAttribute("colecaoMunicipioSelecionado");
        }else{
        	colecaoMunicipioSelecionado = new ArrayList();
        }
        
        
        //Verifica se a unidade tem algum municipio já cadastrado
        if(  sessao.getAttribute("colecaoMunicipioSelecionado") == null ) {

        	FiltroUnidadeOrganizacionalMunicipio filtro = new FiltroUnidadeOrganizacionalMunicipio();
	        filtro.adicionarParametro(new ParametroSimples(
	        		FiltroUnidadeOrganizacionalMunicipio.ID_UNIDADE_REPAVIMENTADORA, form.getId() ) );
	        filtro.adicionarParametro(new ParametroNulo(FiltroUnidadeOrganizacionalMunicipio.DATA_DESVINCULACAO));
	        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO);
	        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalMunicipio.ID_UNIDADE_REPAVIMENTADORA);

	        Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtro, 
	        		UnidadeOrganizacionalMunicipio.class.getName());
	        Iterator iteratorColecaoUnidadeOrganizacional = colecaoUnidadeOrganizacional.iterator(); 
	        while ( iteratorColecaoUnidadeOrganizacional.hasNext() ){
	        	UnidadeOrganizacionalMunicipio unidade = (UnidadeOrganizacionalMunicipio) 
	        		iteratorColecaoUnidadeOrganizacional.next();
	        	
	        	Municipio municipioJaCadastrado = unidade.getIdMunicipio();
	        	colecaoMunicipioSelecionado.add(municipioJaCadastrado);
	        }
			
		} else {
			colecaoMunicipioSelecionado = (ArrayList) sessao.getAttribute("colecaoMunicipioSelecionado");
		}
        
		
        //Verifica se o usuario clicou no botao adicionar
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("adicionar") ) {        	
			
	        // [FS0014] – Verificar vinculação do município a outra unidade repavimentadora
	        FiltroUnidadeOrganizacionalMunicipio filtro = new FiltroUnidadeOrganizacionalMunicipio();
	        filtro.adicionarParametro(new ParametroSimples(
	        		FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO, 
	        		form.getIdMunicipio() ) );
	        filtro.adicionarParametro(new ParametroNulo(
	        		FiltroUnidadeOrganizacionalMunicipio.DATA_DESVINCULACAO));
	        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO);
	        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalMunicipio.ID_UNIDADE_REPAVIMENTADORA);
	        
	        Collection<UnidadeOrganizacionalMunicipio> colecaoUnidOrgMunicipio = fachada.pesquisar(
	        		filtro, UnidadeOrganizacionalMunicipio.class.getName());
	        
	        if ( colecaoUnidOrgMunicipio != null && !colecaoUnidOrgMunicipio.isEmpty() ) {
	    
	        	UnidadeOrganizacionalMunicipio unidadeMunicipio = (UnidadeOrganizacionalMunicipio) 
    				Util.retonarObjetoDeColecao(colecaoUnidOrgMunicipio);
    	
	        	municipio = pesquisarMunicipio(unidadeMunicipio.getIdMunicipio().getId().toString(), municipio, fachada);
	        	
	        	unidadeOrganizacional = pesquisarUnidadeOrganizacional(unidadeMunicipio.getIdUnidadeRepavimentadora(), 
	        			fachada, unidadeOrganizacional );
	        	
	        	throw new ActionServletException("atencao.monicipio_reposabilidade_da_unidade", 
	        			municipio.getNome(), unidadeOrganizacional.getDescricao() );
	        } 
	        
	        //Pesquisa o Municipio a ser adicionado 
	        municipio = pesquisarMunicipio(form.getIdMunicipio().toString(), municipio, fachada);
	        
	        //[FS0013] – Verificar existência do município na lista
	        Iterator iteratorMunicipio = colecaoMunicipioSelecionado.iterator();
    		while (iteratorMunicipio.hasNext()) {
    			Municipio municipioJaCadastrado = (Municipio) iteratorMunicipio.next();
    			
    			if ( municipioJaCadastrado.getId().intValue() == municipio.getId().intValue() ) {
    				
    				throw new ActionServletException("atencao.municipio_cadastrado", null, 
    						municipioJaCadastrado.getNome());
    			} 
        	
    		}
    		
    		colecaoMunicipioSelecionado.add(municipio);
			
	        form.setIdMunicipio("");
	        form.setDescricaoMunicipio("");
			
        }
        
        //Remover os municipios
        Collection colecaoMunicipiosDesvinculados = new ArrayList();
        if (sessao.getAttribute("colecaoMunicipiosDesvinculados") != null && 
        		!sessao.getAttribute("colecaoMunicipiosDesvinculados").equals("") ) {
        
        	colecaoMunicipiosDesvinculados = (ArrayList) sessao.getAttribute("colecaoMunicipiosDesvinculados");
        }
        
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("remover") ) {
        	int obj = new Integer(httpServletRequest.getParameter("id")).intValue();
        	
        	if (colecaoMunicipioSelecionado.size() >= obj) {
        		Municipio municipioRemovido = (Municipio) colecaoMunicipioSelecionado.get(obj -1);
        		colecaoMunicipioSelecionado.remove(obj-1);
        		
        		//Verifica se o municipio ja existe na base.
        		boolean naoExisteNaBase = verificaSeMunicipioExisteNaBase(municipioRemovido, fachada);
        		
        		 // [FS0014] – Verificar vinculação do município a outra unidade repavimentadora
    	        FiltroUnidadeOrganizacionalMunicipio filtro = new FiltroUnidadeOrganizacionalMunicipio();
    	        filtro.adicionarParametro(new ParametroSimples(
    	        		FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO, municipioRemovido.getId() ) );
    	        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO);
    	        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalMunicipio.ID_UNIDADE_REPAVIMENTADORA);
    	   
    	        Collection<UnidadeOrganizacionalMunicipio> colecaoUnidOrgMunicipio = fachada.pesquisar(
    	        		filtro, UnidadeOrganizacionalMunicipio.class.getName());
    	        
    	        UnidadeOrganizacionalMunicipio unidadeMunicipio = new UnidadeOrganizacionalMunicipio(); 
    	        unidadeMunicipio = (UnidadeOrganizacionalMunicipio) 
    	        	Util.retonarObjetoDeColecao(colecaoUnidOrgMunicipio);
    	        
    	        if(naoExisteNaBase) {
	    	        colecaoMunicipiosDesvinculados.add( unidadeMunicipio );
	    	        sessao.setAttribute("colecaoMunicipiosDesvinculados", colecaoMunicipiosDesvinculados);
    	        }
        	}
        	
        }
        
        if ( httpServletRequest.getParameter("desfazer") != null &&
        		httpServletRequest.getParameter("desfazer").equals("S") ) {
        
        	colecaoMunicipioSelecionado.clear();
        	colecaoMunicipioSelecionado = desfazerColecaoMunicipio(form, fachada, colecaoMunicipioSelecionado);
        }

		return colecaoMunicipioSelecionado;
	}
	
	private Municipio pesquisarMunicipio( String idMunicipio, Municipio municipio, Fachada fachada ){
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
		Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName()); 
		
		if ( colecaoMunicipio != null && !colecaoMunicipio.isEmpty() ) {
		
			municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
		} else {
			//[FS0012] – Verificar existência do município
			throw new ActionServletException("atencao.municipio.inexistente");
		}
		
		return municipio;
	}
	
	private UnidadeOrganizacional pesquisarUnidadeOrganizacional( UnidadeOrganizacional unidadeRepavimentadora, Fachada fachada,
			UnidadeOrganizacional unidadeOrganizacional){
		
		FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeRepavimentadora.getId()));
		
		Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = fachada.pesquisar(
				filtro, UnidadeOrganizacional.class.getName()); 
		
		unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
		
		return unidadeOrganizacional;
	}
	
	private void consultaMunicipio( AtualizarUnidadeOrganizacionalActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, form.getIdMunicipio()));
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class
                    .getName());
		
		Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
		
		if ( municipio != null && !municipio.equals("") ) {
			// O municipio foi encontrado
			form.setIdMunicipio(municipio.getId().toString());
			form.setDescricaoMunicipio(municipio.getNome());
			httpServletRequest.setAttribute("idMunicipio", "true");

		} else {
			
			form.setIdMunicipio("");
			httpServletRequest.setAttribute("idMunicipio", "exception");
			form.setDescricaoMunicipio("Município inexistente");

		}
	}

	private boolean verificaSeMunicipioExisteNaBase(Municipio municipioRemovido, Fachada fachada){
			
			boolean naoExisteNaBase = false;
			FiltroUnidadeOrganizacionalMunicipio filtro = new FiltroUnidadeOrganizacionalMunicipio();
	        filtro.adicionarParametro(new ParametroSimples(
	        		FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO, 
	        		municipioRemovido.getId() ) );
	        filtro.adicionarParametro(new ParametroNulo(
	        		FiltroUnidadeOrganizacionalMunicipio.DATA_DESVINCULACAO));
	        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO);
	        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalMunicipio.ID_UNIDADE_REPAVIMENTADORA);
	        
	        Collection<UnidadeOrganizacionalMunicipio> colecaoUnidOrgMunicipio = fachada.pesquisar(
	        		filtro, UnidadeOrganizacionalMunicipio.class.getName());
	        
	        if ( colecaoUnidOrgMunicipio != null && !colecaoUnidOrgMunicipio.isEmpty() ) {
	    
	        	naoExisteNaBase = true;
	        }
	       return naoExisteNaBase;
	}

	public ArrayList desfazerColecaoMunicipio(AtualizarUnidadeOrganizacionalActionForm form, Fachada fachada, ArrayList colecaoMunicipioSelecionado){
		
		FiltroUnidadeOrganizacionalMunicipio filtroUnidadeOrganizacionalMunicipio = 
			new FiltroUnidadeOrganizacionalMunicipio();
    	filtroUnidadeOrganizacionalMunicipio.adicionarParametro(
			new ParametroSimples(
					FiltroUnidadeOrganizacionalMunicipio.ID_UNIDADE_REPAVIMENTADORA, 
				form.getId()));
    	filtroUnidadeOrganizacionalMunicipio.adicionarParametro(
				new ParametroNulo(
						FiltroUnidadeOrganizacionalMunicipio.DATA_DESVINCULACAO));
    	filtroUnidadeOrganizacionalMunicipio.adicionarCaminhoParaCarregamentoEntidade(
    			FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO);
    	filtroUnidadeOrganizacionalMunicipio.adicionarCaminhoParaCarregamentoEntidade(
    			FiltroUnidadeOrganizacionalMunicipio.ID_UNIDADE_REPAVIMENTADORA);
        
		Collection colecaoUnidadeMunicipioCadastrado = fachada.pesquisar(
				filtroUnidadeOrganizacionalMunicipio,  UnidadeOrganizacionalMunicipio.class.getName());
		Iterator iteratorUnidadeMunicipioCadastrado = colecaoUnidadeMunicipioCadastrado.iterator();
		while ( iteratorUnidadeMunicipioCadastrado.hasNext() ) {
			UnidadeOrganizacionalMunicipio unidade = (UnidadeOrganizacionalMunicipio) 
				iteratorUnidadeMunicipioCadastrado.next();
			
			Municipio mun = new Municipio(); 
			mun = pesquisarMunicipio(unidade.getIdMunicipio().getId().toString(), mun, fachada );

			colecaoMunicipioSelecionado.add(mun);
		}
		return colecaoMunicipioSelecionado;
	}
}

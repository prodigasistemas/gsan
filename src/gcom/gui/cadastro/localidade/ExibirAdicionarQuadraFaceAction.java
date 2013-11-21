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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGrauDificuldadeExecucao;
import gcom.cadastro.localidade.FiltroGrauIntermitencia;
import gcom.cadastro.localidade.FiltroGrauRiscoSegurancaFisica;
import gcom.cadastro.localidade.FiltroNivelPressao;
import gcom.cadastro.localidade.GrauDificuldadeExecucao;
import gcom.cadastro.localidade.GrauIntermitencia;
import gcom.cadastro.localidade.GrauRiscoSegurancaFisica;
import gcom.cadastro.localidade.NivelPressao;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroBacia;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe responsável pela exibição da tela de cadastro das faces de uma quadra 
 *
 * @author Raphael Rossiter
 * @date 31/03/2009
 * * @alteracao 28/04/2010 - CRC4066 - Adicionado o Grau de Dificuladade de Execução, o Grau de Risco Segurança Física, 
 * 									o Nível de Pressão e o Grau de Intermitência. 
 */
public class ExibirAdicionarQuadraFaceAction extends GcomAction{
	
	private Collection colecaoPesquisa;

	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        ActionForward retorno = actionMapping.findForward("exibirAdicionarQuadraFace");

	        Fachada fachada = Fachada.getInstancia();
	        
	        HttpSession sessao = httpServletRequest.getSession(false);

	        AdicionarQuadraFaceActionForm adicionarQuadraFaceActionForm = (AdicionarQuadraFaceActionForm) actionForm;
	        
	        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

	        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

	            switch (Integer.parseInt(objetoConsulta)) {
	            
	            //Distrito Operacional
	            case 5:

	                //DISTRITO OPERACIONAL INFORMADO
	                String distritoOperacionalID = adicionarQuadraFaceActionForm
	                .getDistritoOperacionalID();

	                FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

	                filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
	                FiltroDistritoOperacional.ID, distritoOperacionalID));

	                filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
	                FiltroDistritoOperacional.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

	                colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
	                DistritoOperacional.class.getName());

	                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	                    
	                	//DISTRITO OPERACIONAL NÃO ENCONTRADO
	                	adicionarQuadraFaceActionForm.setDistritoOperacionalID("");
	                	adicionarQuadraFaceActionForm.setDistritoOperacionalDescricao("Distrito operacional inexistente.");
	                    httpServletRequest.setAttribute("corDistritoOperacional", "exception");
	                    
	                    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
	                } 
	                else {
	                    
	                	DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
	                    .retonarObjetoDeColecao(colecaoPesquisa);
	                    
	                	adicionarQuadraFaceActionForm.setDistritoOperacionalID(String
	                    .valueOf(objetoDistritoOperacional.getId()));
	                	adicionarQuadraFaceActionForm.setDistritoOperacionalDescricao(objetoDistritoOperacional
	                    .getDescricao());
	                    httpServletRequest.setAttribute("corDistritoOperacional", "valor");
	                    
	                    httpServletRequest.setAttribute("nomeCampo", "setorCensitarioID");
	                }

	                break;
	                
	            //Bacia
	            case 7:
	                
	            	//SISTEMA DE ESGOTO INFORMADO
	            	String sistemaEsgotoID = adicionarQuadraFaceActionForm.getSistemaEsgotoID();

	                if (sistemaEsgotoID != null && !sistemaEsgotoID
	                    .equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

	                    FiltroBacia filtroBacia = new FiltroBacia();

	                    filtroBacia.adicionarParametro(new ParametroSimples(
	                    FiltroBacia.SISTEMA_ESGOTO_ID, sistemaEsgotoID));

	                    filtroBacia.adicionarParametro(new ParametroSimples(
	                    FiltroBacia.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

	                    colecaoPesquisa = fachada.pesquisar(filtroBacia, Bacia.class.getName());

	                    sessao.setAttribute("colecaoBacia", colecaoPesquisa);

	                } 
	                else {
	                    
	                	adicionarQuadraFaceActionForm.setBaciaID(String
	                    .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
	                    
	                	sessao.removeAttribute("colecaoBacia");
	                }

	                break;
	            default:

	                break;
	            }
	        }
	        
	        //VINDO DO POPUP DE DISTRITO OPERACIONAL
	        if (httpServletRequest.getParameter("idCampoEnviarDados") != null){
	        	
	        	adicionarQuadraFaceActionForm.setDistritoOperacionalID(
	        	httpServletRequest.getParameter("idCampoEnviarDados"));
	        	
	        	adicionarQuadraFaceActionForm.setDistritoOperacionalDescricao(
	    	    httpServletRequest.getParameter("descricaoCampoEnviarDados"));
	        }
	        
	        
	        httpServletRequest.setAttribute("nomeCampo", "numeroFace");
	        
	        //PREPARANDO O FORMULÁRIO PARA INSERIR
	        prepararFormularioInserir(httpServletRequest, sessao, fachada, adicionarQuadraFaceActionForm);
	   			 
	        //PREPARANDO O FORMULÁRIO PARA ATUALIZAR
	        prepararFormularioAtualizar(httpServletRequest, adicionarQuadraFaceActionForm, sessao, fachada);
	        
	        //MONTANDO URL DE RETORNO
			if (httpServletRequest.getParameter("telaRetorno") != null){
	    		sessao.setAttribute("telaRetorno", 
	    		(String.valueOf(httpServletRequest.getParameter("telaRetorno"))) + ".do?retornoQuadraFace=OK");
	    	}
			
			//PESQUISAR DISTRITO OPERACIONAL PELO POPUP
			if (httpServletRequest.getParameter("pesquisarDistrito") != null){
				
				retorno = actionMapping.findForward("pesquisarDistritoOperacional");
			}
	        
	        return retorno;
	 }
	 
	 private void prepararFormularioAtualizar(HttpServletRequest httpServletRequest,
			 AdicionarQuadraFaceActionForm adicionarQuadraFaceActionForm, HttpSession sessao, Fachada fachada){
		 
		 if (httpServletRequest.getParameter("acao") != null
		     && httpServletRequest.getParameter("acao").equalsIgnoreCase("atualizar")){
			 
			 adicionarQuadraFaceActionForm.setAcao("atualizar");
			 
			 Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");
			 
			 Integer numeroQuadraFaceParaAtualizar = Integer.valueOf(
			 httpServletRequest.getParameter("numeroQuadraFace"));
			    		
			 Iterator it = colecaoQuadraFace.iterator();
			 QuadraFace quadraFace = null;
							
			while (it.hasNext()){
								
				quadraFace = (QuadraFace) it.next();
								
				if (quadraFace.getNumeroQuadraFace().equals(numeroQuadraFaceParaAtualizar)){
								
					break;
				}
			}
			
			// CARREGANDO AS INFORMAÇOES DA FACE DA QUADRA NO FORMULARIO
            if(quadraFace.getGrauDificuldadeExecucao() != null){
            	adicionarQuadraFaceActionForm.setGrauDificuldadeExecucaoID(String.valueOf(quadraFace
                        .getGrauDificuldadeExecucao().getId()));
            }else{
            	adicionarQuadraFaceActionForm.setGrauDificuldadeExecucaoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            }
            
            if(quadraFace.getGrauRiscoSegurancaFisica() != null){
            	adicionarQuadraFaceActionForm.setGrauRiscoSegurancaFisicaID(String.valueOf(quadraFace
                        .getGrauRiscoSegurancaFisica().getId()));
            }else{
            	adicionarQuadraFaceActionForm.setGrauRiscoSegurancaFisicaID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            }
            
            if(quadraFace.getCondicaoAbastecimentoAgua() != null){
            	adicionarQuadraFaceActionForm.setNivelPressaoID(String.valueOf(quadraFace
                        .getCondicaoAbastecimentoAgua().getNivelPressao().getId()));
            	
            	adicionarQuadraFaceActionForm.setGrauIntermitenciaID(String.valueOf(quadraFace
                        .getCondicaoAbastecimentoAgua().getGrauIntermitencia().getId()));
            }else{
            	adicionarQuadraFaceActionForm.setNivelPressaoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            	adicionarQuadraFaceActionForm.setGrauIntermitenciaID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            }
            
			
			//CARREGANDO AS INFORMAÇOES DA FACE DA QUADRA NO FORMULARIO
			adicionarQuadraFaceActionForm.setNumeroFace(quadraFace.getNumeroQuadraFace().toString());
			adicionarQuadraFaceActionForm.setIndicadorRedeAguaAux(quadraFace.getIndicadorRedeAgua().toString());
			adicionarQuadraFaceActionForm.setIndicadorRedeEsgotoAux(quadraFace.getIndicadorRedeEsgoto().toString());

			//GRAU DIFICULDADE EXECUÇÂO
			 FiltroGrauDificuldadeExecucao filtroGrauDificuldadeExecucao = new FiltroGrauDificuldadeExecucao();

			 filtroGrauDificuldadeExecucao.adicionarParametro(new ParametroSimples(
					 FiltroGrauDificuldadeExecucao.INDICADOR_GRAU_DIFICULDADE_EXECUCAO, ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoGrauDificuldade = null;
			 
			 colecaoGrauDificuldade = fachada.pesquisar(filtroGrauDificuldadeExecucao,
					 GrauDificuldadeExecucao.class.getName());

			 if (colecaoGrauDificuldade == null || colecaoGrauDificuldade.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Grau_Dificuldade_Execucao");
			 } 
			 else {
				 sessao.setAttribute("colecaoGrauDificuldadeExecucao", colecaoGrauDificuldade);
			 }
			 
			//GRAU DE RISCO SEGURANÇA FÍSICA
			 FiltroGrauRiscoSegurancaFisica filtroGrauRiscoSegurancaFisica = new FiltroGrauRiscoSegurancaFisica();

			 filtroGrauRiscoSegurancaFisica.adicionarParametro(new ParametroSimples(
					 FiltroGrauRiscoSegurancaFisica.INDICADOR_GRAU_RISCO_SEGURANCA_FISICA, ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoGrauRisco = null;
			 colecaoGrauRisco = fachada.pesquisar(filtroGrauRiscoSegurancaFisica,
					 GrauRiscoSegurancaFisica.class.getName());

			 if (colecaoGrauRisco == null || colecaoGrauRisco.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Grau_Risco_Seguranca_Fisica");
			 } 
			 else {
				 sessao.setAttribute("colecaoGrauRiscoSegurancaFisica", colecaoGrauRisco);
			 }
			 
			 //NÍVEL DE PRESSÃO
			 FiltroNivelPressao filtroNivelPressao = new FiltroNivelPressao();

			 filtroNivelPressao.adicionarParametro(new ParametroSimples(
					 FiltroNivelPressao.INDICADOR_NIVEL_PRESSAO, ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoNivelPressao = null;
			 colecaoNivelPressao = fachada.pesquisar(filtroNivelPressao,
					 NivelPressao.class.getName());

			 if (colecaoNivelPressao == null || colecaoNivelPressao.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Nivel_Pressao");
			 } 
			 else {
				 sessao.setAttribute("colecaoNivelPressao", colecaoNivelPressao);
			 }
			 			 
			 //GRAU DE INTERMITÊNCIA
			 FiltroGrauIntermitencia filtroGrauIntermitencia = new FiltroGrauIntermitencia();

			 filtroGrauIntermitencia.adicionarParametro(new ParametroSimples(
					 FiltroGrauIntermitencia.INDICADOR_GRAU_INTERMITENCIA, ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoIntermitencia = null;
			 colecaoIntermitencia = fachada.pesquisar(filtroGrauIntermitencia,
					 GrauIntermitencia.class.getName());

			 if (colecaoIntermitencia == null || colecaoIntermitencia.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Grau_Intermitencia");
			 } 
			 else {
				 sessao.setAttribute("colecaoGrauIntermitencia", colecaoIntermitencia);
			 }
			
			//SISTEMA_ESGOTO
			 FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();

			 filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(
			 FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			 colecaoPesquisa = fachada.pesquisar(filtroSistemaEsgoto,
			 SistemaEsgoto.class.getName());

			 if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Sistema_Esgoto");
			 } 
			 else {
				 sessao.setAttribute("colecaoSistemaEsgoto", colecaoPesquisa);
			 }
			
			if (quadraFace.getBacia() != null){
				
				FiltroBacia filtroBacia = new FiltroBacia();

                filtroBacia.adicionarParametro(new ParametroSimples(
                FiltroBacia.SISTEMA_ESGOTO_ID, quadraFace.getBacia().getSistemaEsgoto().getId()));

                filtroBacia.adicionarParametro(new ParametroSimples(
                FiltroBacia.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroBacia, Bacia.class.getName());

                sessao.setAttribute("colecaoBacia", colecaoPesquisa);
                
				adicionarQuadraFaceActionForm.setSistemaEsgotoID(quadraFace.getBacia().getSistemaEsgoto().getId().toString());
				adicionarQuadraFaceActionForm.setBaciaID(quadraFace.getBacia().getId().toString());
			}
			
			if (quadraFace.getDistritoOperacional() != null){
				adicionarQuadraFaceActionForm.setDistritoOperacionalID(quadraFace.getDistritoOperacional().getId().toString());
				adicionarQuadraFaceActionForm.setDistritoOperacionalDescricao(quadraFace.getDistritoOperacional().getDescricao());
			}
		 }
	}
	 
	 private void prepararFormularioInserir(HttpServletRequest httpServletRequest, HttpSession sessao,
			 Fachada fachada, AdicionarQuadraFaceActionForm adicionarQuadraFaceActionForm){
	    	
	    if (httpServletRequest.getParameter("acao") != null
	    		&& httpServletRequest.getParameter("acao").equalsIgnoreCase("inserir")){
	            // -------------- bt DESFAZER ---------------
	        
	    	adicionarQuadraFaceActionForm.setAcao("inserir");
	    	
	    	adicionarQuadraFaceActionForm.setNumeroFace("");
	    		
	    	adicionarQuadraFaceActionForm.setIndicadorRedeAguaAux(Quadra.COM_REDE.toString());
	    	adicionarQuadraFaceActionForm.setIndicadorRedeEsgotoAux(Quadra.COM_REDE.toString());

	    	adicionarQuadraFaceActionForm.setBaciaID("");
	    	adicionarQuadraFaceActionForm.setDistritoOperacionalDescricao("");
	    	adicionarQuadraFaceActionForm.setDistritoOperacionalID("");
	    	adicionarQuadraFaceActionForm.setSistemaEsgotoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
	    	
	    	adicionarQuadraFaceActionForm.setGrauDificuldadeExecucaoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
	    	adicionarQuadraFaceActionForm.setGrauRiscoSegurancaFisicaID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
	    	adicionarQuadraFaceActionForm.setNivelPressaoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
	    	adicionarQuadraFaceActionForm.setGrauIntermitenciaID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
	    	
	    	//SISTEMA_ESGOTO
			 FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();

			 filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(
			 FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			 colecaoPesquisa = fachada.pesquisar(filtroSistemaEsgoto,
			 SistemaEsgoto.class.getName());

			 if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Sistema_Esgoto");
			 } 
			 else {
				 sessao.setAttribute("colecaoSistemaEsgoto", colecaoPesquisa);
			 }
			 
			 //GRAU DIFICULDADE EXECUÇÂO
			 FiltroGrauDificuldadeExecucao filtroGrauDificuldadeExecucao = new FiltroGrauDificuldadeExecucao();

			 filtroGrauDificuldadeExecucao.adicionarParametro(new ParametroSimples(
					 FiltroGrauDificuldadeExecucao.INDICADOR_GRAU_DIFICULDADE_EXECUCAO, ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoGrauDificuldade = null;
			 
			 colecaoGrauDificuldade = fachada.pesquisar(filtroGrauDificuldadeExecucao,
					 GrauDificuldadeExecucao.class.getName());

			 if (colecaoGrauDificuldade == null || colecaoGrauDificuldade.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Grau_Dificuldade_Execucao");
			 } 
			 else {
				 sessao.setAttribute("colecaoGrauDificuldadeExecucao", colecaoGrauDificuldade);
			 }
			 
			 //GRAU DE RISCO SEGURANÇA FÍSICA
			 FiltroGrauRiscoSegurancaFisica filtroGrauRiscoSegurancaFisica = new FiltroGrauRiscoSegurancaFisica();

			 filtroGrauRiscoSegurancaFisica.adicionarParametro(new ParametroSimples(
					 FiltroGrauRiscoSegurancaFisica.INDICADOR_GRAU_RISCO_SEGURANCA_FISICA, ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoGrauRisco = null;
			 colecaoGrauRisco = fachada.pesquisar(filtroGrauRiscoSegurancaFisica,
					 GrauRiscoSegurancaFisica.class.getName());

			 if (colecaoGrauRisco == null || colecaoGrauRisco.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Grau_Risco_Seguranca_Fisica");
			 } 
			 else {
				 sessao.setAttribute("colecaoGrauRiscoSegurancaFisica", colecaoGrauRisco);
			 }
			 
			 //NÍVEL DE PRESSÃO
			 FiltroNivelPressao filtroNivelPressao = new FiltroNivelPressao();

			 filtroNivelPressao.adicionarParametro(new ParametroSimples(
					 FiltroNivelPressao.INDICADOR_NIVEL_PRESSAO, ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoNivelPressao = null;
			 colecaoNivelPressao = fachada.pesquisar(filtroNivelPressao,
					 NivelPressao.class.getName());

			 if (colecaoNivelPressao == null || colecaoNivelPressao.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Nivel_Pressao");
			 } 
			 else {
				 sessao.setAttribute("colecaoNivelPressao", colecaoNivelPressao);
			 }
			 
			 //GRAU DE INTERMITÊNCIA
			 FiltroGrauIntermitencia filtroGrauIntermitencia = new FiltroGrauIntermitencia();

			 filtroGrauIntermitencia.adicionarParametro(new ParametroSimples(
					 FiltroGrauIntermitencia.INDICADOR_GRAU_INTERMITENCIA, ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoIntermitencia = null;
			 colecaoIntermitencia = fachada.pesquisar(filtroGrauIntermitencia,
					 GrauIntermitencia.class.getName());

			 if (colecaoIntermitencia == null || colecaoIntermitencia.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "Grau_Risco_Seguranca_Fisica");
			 } 
			 else {
				 sessao.setAttribute("colecaoGrauIntermitencia", colecaoIntermitencia);
			 }
 
	    }
	 }
}

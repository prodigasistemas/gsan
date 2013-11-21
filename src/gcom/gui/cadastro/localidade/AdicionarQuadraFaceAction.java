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

import gcom.cadastro.localidade.CondicaoAbastecimentoAgua;
import gcom.cadastro.localidade.FiltroCondicaoAbastecimentoAgua;
import gcom.cadastro.localidade.FiltroGrauDificuldadeExecucao;
import gcom.cadastro.localidade.FiltroGrauRiscoSegurancaFisica;
import gcom.cadastro.localidade.GrauDificuldadeExecucao;
import gcom.cadastro.localidade.GrauRiscoSegurancaFisica;
import gcom.cadastro.localidade.QuadraFace;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroBacia;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdicionarQuadraFaceAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("adicionarQuadraFace");

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        AdicionarQuadraFaceActionForm adicionarQuadraFaceActionForm = (AdicionarQuadraFaceActionForm) actionForm;
        
        //COLEÇÃO PARA APRESENTAÇÃO NA TELA
        Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");
        
        //PARA INSERIR
        inserirQuadraFace(httpServletRequest, sessao, fachada, adicionarQuadraFaceActionForm,
    	colecaoQuadraFace);
        
        //PARA ATUALIZAR
        atualizarQuadraFace(httpServletRequest, sessao, fachada, adicionarQuadraFaceActionForm,
        colecaoQuadraFace);
        
        return retorno;
	}
	
	private void inserirQuadraFace(HttpServletRequest httpServletRequest, HttpSession sessao, Fachada fachada,
			AdicionarQuadraFaceActionForm adicionarQuadraFaceActionForm,
			Collection colecaoQuadraFace){
		
		if (adicionarQuadraFaceActionForm.getAcao() != null
	        && adicionarQuadraFaceActionForm.getAcao().equalsIgnoreCase("inserir")){
			
			QuadraFace quadraFace = new QuadraFace();
			
			//NÚMERO DA FACE
			quadraFace.setNumeroQuadraFace(Integer.valueOf(adicionarQuadraFaceActionForm.getNumeroFace()));
			
			//INDICADOR REDE DE ÁGUA
			quadraFace.setIndicadorRedeAgua(Short.valueOf(adicionarQuadraFaceActionForm.getIndicadorRedeAguaAux()));
			
			//INDICADOR REDE DE ESGOTO
			quadraFace.setIndicadorRedeEsgoto(Short.valueOf(adicionarQuadraFaceActionForm.getIndicadorRedeEsgotoAux()));
			
			//BACIA
			if (adicionarQuadraFaceActionForm.getBaciaID() != null &&
				!adicionarQuadraFaceActionForm.getBaciaID().equals("")){
				
				FiltroBacia filtroBacia = new FiltroBacia();
				
				filtroBacia.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgoto");
				
				filtroBacia.adicionarParametro(new ParametroSimples(FiltroBacia.ID, 
				Integer.valueOf(adicionarQuadraFaceActionForm.getBaciaID())));
				
				Collection colecaoBacia = fachada.pesquisar(filtroBacia, Bacia.class.getName());
				
				Bacia bacia = (Bacia) Util.retonarObjetoDeColecao(colecaoBacia);
				
				quadraFace.setBacia(bacia);
			}
			
			//DISTRITO_OPERACIONAL
			if (adicionarQuadraFaceActionForm.getDistritoOperacionalID() != null &&
				!adicionarQuadraFaceActionForm.getDistritoOperacionalID().equals("")){
				
				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
				
				filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, 
				Integer.valueOf(adicionarQuadraFaceActionForm.getDistritoOperacionalID())));
				
				Collection colecaoDistritoOperacional = fachada.pesquisar(filtroDistritoOperacional, 
				DistritoOperacional.class.getName());
				
				DistritoOperacional distritoOperacional = 
				(DistritoOperacional) Util.retonarObjetoDeColecao(colecaoDistritoOperacional);
				
				quadraFace.setDistritoOperacional(distritoOperacional);
			}
			
			// GRAU DE DIFICULDADE DE EXECUÇÃO
			if(adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID() != null
					&& !adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID().equals("-1")){
				
				FiltroGrauDificuldadeExecucao filtroGrauDificuldadeExecucao = new FiltroGrauDificuldadeExecucao();
				
				filtroGrauDificuldadeExecucao.adicionarParametro(new ParametroSimples(
						FiltroGrauDificuldadeExecucao.ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID())));
				
				Collection colecaoGrauDificuldadeExecucao = fachada.pesquisar(filtroGrauDificuldadeExecucao, 
						GrauDificuldadeExecucao.class.getName());
						
				GrauDificuldadeExecucao grauDificuldadeExecucao = 
						(GrauDificuldadeExecucao) Util.retonarObjetoDeColecao(colecaoGrauDificuldadeExecucao);
						
				quadraFace.setGrauDificuldadeExecucao(grauDificuldadeExecucao);
				
			}
			
			// GRAU DE RISCO DE SEGURANÇA FÍSICA
			if(adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID() != null
					&& !adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID().equals("-1")){
				
				FiltroGrauRiscoSegurancaFisica filtroGrauRiscoSegurancaFisica = new FiltroGrauRiscoSegurancaFisica();
				
				filtroGrauRiscoSegurancaFisica.adicionarParametro(new ParametroSimples(
						FiltroGrauRiscoSegurancaFisica.ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID())));
				
				Collection colecaoGrauRiscoSegurancaFisica = fachada.pesquisar(filtroGrauRiscoSegurancaFisica, 
						GrauRiscoSegurancaFisica.class.getName());
						
				GrauRiscoSegurancaFisica grauRiscoSegurancaFisica = 
						(GrauRiscoSegurancaFisica) Util.retonarObjetoDeColecao(colecaoGrauRiscoSegurancaFisica);
						
				quadraFace.setGrauRiscoSegurancaFisica(grauRiscoSegurancaFisica);
				
			}
			
			// NÍVEL DE PRESSÃO
			if(adicionarQuadraFaceActionForm.getNivelPressaoID() != null
					&& !adicionarQuadraFaceActionForm.getNivelPressaoID().equals("-1")){
				
				// GRAU INTERMITÊNCIA
				if(adicionarQuadraFaceActionForm.getGrauIntermitenciaID() != null
						&& !adicionarQuadraFaceActionForm.getGrauIntermitenciaID().equals("-1")){
					
					FiltroCondicaoAbastecimentoAgua filtroCondicaoAbastecimentoAgua = new FiltroCondicaoAbastecimentoAgua();
					
					filtroCondicaoAbastecimentoAgua.adicionarParametro(new ParametroSimples(
							FiltroCondicaoAbastecimentoAgua.GRAU_INTERMITENCIA_ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauIntermitenciaID())));

					filtroCondicaoAbastecimentoAgua.adicionarParametro(new ParametroSimples(
							FiltroCondicaoAbastecimentoAgua.NIVEL_PRESSAO_ID, Integer.valueOf(adicionarQuadraFaceActionForm.getNivelPressaoID())));
					
					Collection colecaoCondicaoAbastecimentoAgua = fachada.pesquisar(filtroCondicaoAbastecimentoAgua, 
							CondicaoAbastecimentoAgua.class.getName());
							
					CondicaoAbastecimentoAgua condicaoAbastecimentoAgua = 
							(CondicaoAbastecimentoAgua) Util.retonarObjetoDeColecao(colecaoCondicaoAbastecimentoAgua);
							
					quadraFace.setCondicaoAbastecimentoAgua(condicaoAbastecimentoAgua);
					
				}
			}			
			
			//INDICADOR DE USO
			quadraFace.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			
			//VALIDAÇÃO DOS DADOS INFORMADOS PARA CADASTRO DE FACE DA QUADRA
			fachada.validarQuadraFace(quadraFace, colecaoQuadraFace, true);
			
			//ACRESCENTANDO A FACE DA QUADRA NA COLEÇÃO DE APRESENTAÇÃO
			if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()){
				colecaoQuadraFace.add(quadraFace);
			}
			else{
				
				colecaoQuadraFace = new ArrayList();
				colecaoQuadraFace.add(quadraFace);
			}
			
			//ORDENANDO AS FACES DA QUADRA PELO NUMERO
			Collections.sort((List) colecaoQuadraFace, new Comparator() {
				public int compare(Object a, Object b) {
					Integer numeroQuadraFace1 = ((QuadraFace) a)
					.getNumeroQuadraFace();
					Integer numeroQuadraFace2 = ((QuadraFace) b)
					.getNumeroQuadraFace();

					return numeroQuadraFace1.compareTo(numeroQuadraFace2);
				}
			});
			
			sessao.setAttribute("colecaoQuadraFace", colecaoQuadraFace);
			httpServletRequest.setAttribute("reloadPage", "OK");
		}
	}
	
	private void atualizarQuadraFace(HttpServletRequest httpServletRequest, HttpSession sessao, Fachada fachada,
			AdicionarQuadraFaceActionForm adicionarQuadraFaceActionForm,
			Collection colecaoQuadraFace){
		
		if (adicionarQuadraFaceActionForm.getAcao() != null
	        && adicionarQuadraFaceActionForm.getAcao().equalsIgnoreCase("atualizar")){
			
			Integer numeroQuadraFaceParaAtualizar = Integer.valueOf(
			adicionarQuadraFaceActionForm.getNumeroFace());
					    		
			Iterator it = colecaoQuadraFace.iterator();
			QuadraFace quadraFace = null;
									
			while (it.hasNext()){
										
				quadraFace = (QuadraFace) it.next();
										
				if (quadraFace.getNumeroQuadraFace().equals(numeroQuadraFaceParaAtualizar)){
					
					colecaoQuadraFace.remove(quadraFace);
					break;
				}
			}
			
			//INDICADOR REDE DE ÁGUA
			quadraFace.setIndicadorRedeAgua(Short.valueOf(adicionarQuadraFaceActionForm.getIndicadorRedeAguaAux()));
			
			//INDICADOR REDE DE ESGOTO
			quadraFace.setIndicadorRedeEsgoto(Short.valueOf(adicionarQuadraFaceActionForm.getIndicadorRedeEsgotoAux()));
			
			//BACIA
			if (adicionarQuadraFaceActionForm.getBaciaID() != null &&
				!adicionarQuadraFaceActionForm.getBaciaID().equals("")){
				
				FiltroBacia filtroBacia = new FiltroBacia();
				
				filtroBacia.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgoto");
				
				filtroBacia.adicionarParametro(new ParametroSimples(FiltroBacia.ID, 
				Integer.valueOf(adicionarQuadraFaceActionForm.getBaciaID())));
				
				Collection colecaoBacia = fachada.pesquisar(filtroBacia, Bacia.class.getName());
				
				Bacia bacia = (Bacia) Util.retonarObjetoDeColecao(colecaoBacia);
				
				quadraFace.setBacia(bacia);
			}
			else{
				quadraFace.setBacia(null);
			}
			
			//DISTRITO_OPERACIONAL
			if (adicionarQuadraFaceActionForm.getDistritoOperacionalID() != null &&
				!adicionarQuadraFaceActionForm.getDistritoOperacionalID().equals("")){
				
				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
				
				filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, 
				Integer.valueOf(adicionarQuadraFaceActionForm.getDistritoOperacionalID())));
				
				Collection colecaoDistritoOperacional = fachada.pesquisar(filtroDistritoOperacional, 
				DistritoOperacional.class.getName());
				
				DistritoOperacional distritoOperacional = 
				(DistritoOperacional) Util.retonarObjetoDeColecao(colecaoDistritoOperacional);
				
				quadraFace.setDistritoOperacional(distritoOperacional);
			}
			else{
				quadraFace.setDistritoOperacional(null);
			}

			// GRAU DE DIFICULDADE DE EXECUÇÃO
			if(adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID() != null
					&& !adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID().equals("-1")){
				
				FiltroGrauDificuldadeExecucao filtroGrauDificuldadeExecucao = new FiltroGrauDificuldadeExecucao();
				
				filtroGrauDificuldadeExecucao.adicionarParametro(new ParametroSimples(
						FiltroGrauDificuldadeExecucao.ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauDificuldadeExecucaoID())));
				
				Collection colecaoGrauDificuldadeExecucao = fachada.pesquisar(filtroGrauDificuldadeExecucao, 
						GrauDificuldadeExecucao.class.getName());
						
				GrauDificuldadeExecucao grauDificuldadeExecucao = 
						(GrauDificuldadeExecucao) Util.retonarObjetoDeColecao(colecaoGrauDificuldadeExecucao);
						
				quadraFace.setGrauDificuldadeExecucao(grauDificuldadeExecucao);
				
			}
			
			// GRAU DE RISCO DE SEGURANÇA FÍSICA
			if(adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID() != null
					&& !adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID().equals("-1")){
				
				FiltroGrauRiscoSegurancaFisica filtroGrauRiscoSegurancaFisica = new FiltroGrauRiscoSegurancaFisica();
				
				filtroGrauRiscoSegurancaFisica.adicionarParametro(new ParametroSimples(
						FiltroGrauRiscoSegurancaFisica.ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauRiscoSegurancaFisicaID())));
				
				Collection colecaoGrauRiscoSegurancaFisica = fachada.pesquisar(filtroGrauRiscoSegurancaFisica, 
						GrauRiscoSegurancaFisica.class.getName());
						
				GrauRiscoSegurancaFisica grauRiscoSegurancaFisica = 
						(GrauRiscoSegurancaFisica) Util.retonarObjetoDeColecao(colecaoGrauRiscoSegurancaFisica);
						
				quadraFace.setGrauRiscoSegurancaFisica(grauRiscoSegurancaFisica);
				
			}
			
			// NÍVEL DE PRESSÃO
			if(adicionarQuadraFaceActionForm.getNivelPressaoID() != null
					&& !adicionarQuadraFaceActionForm.getNivelPressaoID().equals("-1")){
				
				// GRAU INTERMITÊNCIA
				if(adicionarQuadraFaceActionForm.getGrauIntermitenciaID() != null
						&& !adicionarQuadraFaceActionForm.getGrauIntermitenciaID().equals("-1")){
					
					FiltroCondicaoAbastecimentoAgua filtroCondicaoAbastecimentoAgua = new FiltroCondicaoAbastecimentoAgua();
					
					filtroCondicaoAbastecimentoAgua.adicionarParametro(new ParametroSimples(
							FiltroCondicaoAbastecimentoAgua.GRAU_INTERMITENCIA_ID, Integer.valueOf(adicionarQuadraFaceActionForm.getGrauIntermitenciaID())));

					filtroCondicaoAbastecimentoAgua.adicionarParametro(new ParametroSimples(
							FiltroCondicaoAbastecimentoAgua.NIVEL_PRESSAO_ID, Integer.valueOf(adicionarQuadraFaceActionForm.getNivelPressaoID())));
					
					Collection colecaoCondicaoAbastecimentoAgua = fachada.pesquisar(filtroCondicaoAbastecimentoAgua, 
							CondicaoAbastecimentoAgua.class.getName());
							
					CondicaoAbastecimentoAgua condicaoAbastecimentoAgua = 
							(CondicaoAbastecimentoAgua) Util.retonarObjetoDeColecao(colecaoCondicaoAbastecimentoAgua);
							
					quadraFace.setCondicaoAbastecimentoAgua(condicaoAbastecimentoAgua);
					
				}
			}

			//INDICADOR DE USO
			quadraFace.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			
			//VALIDAÇÃO DOS DADOS INFORMADOS PARA CADASTRO DE FACE DA QUADRA
			fachada.validarQuadraFace(quadraFace, colecaoQuadraFace, false);
			
			//ACRESCENTANDO A FACE DA QUADRA ATUALIZADA NA COLEÇÃO DE APRESENTAÇÃO
			colecaoQuadraFace.add(quadraFace);
			
			//ORDENANDO AS FACES DA QUADRA PELO NUMERO
			Collections.sort((List) colecaoQuadraFace, new Comparator() {
				public int compare(Object a, Object b) {
					Integer numeroQuadraFace1 = ((QuadraFace) a)
					.getNumeroQuadraFace();
					Integer numeroQuadraFace2 = ((QuadraFace) b)
					.getNumeroQuadraFace();

					return numeroQuadraFace1.compareTo(numeroQuadraFace2);
				}
			});
			
			sessao.setAttribute("colecaoQuadraFace", colecaoQuadraFace);
			httpServletRequest.setAttribute("reloadPage", "OK");
		}
	}

}

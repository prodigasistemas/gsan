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

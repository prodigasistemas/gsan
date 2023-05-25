package gcom.gui.cadastro.localidade;

import gcom.cadastro.Dmc;
import gcom.cadastro.FiltroDmc;
import gcom.cadastro.ControladorCadastro;
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
 * Classe responsï¿½vel pela exibiï¿½ï¿½o da tela de cadastro das faces de uma quadra 
 *
 * @author Raphael Rossiter
 * @date 31/03/2009
 * * @alteracao 28/04/2010 - CRC4066 - Adicionado o Grau de Dificuladade de Execuï¿½ï¿½o, o Grau de Risco Seguranï¿½a Fï¿½sica, 
 * 									o Nï¿½vel de Pressï¿½o e o Grau de Intermitï¿½ncia. 
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
	                    
	                	//DISTRITO OPERACIONAL Nï¿½O ENCONTRADO
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
	        
	        //PREPARANDO O FORMULï¿½RIO PARA INSERIR
	        prepararFormularioInserir(httpServletRequest, sessao, fachada, adicionarQuadraFaceActionForm);
	   			 
	        //PREPARANDO O FORMULï¿½RIO PARA ATUALIZAR
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
			
			// CARREGANDO AS INFORMAï¿½OES DA FACE DA QUADRA NO FORMULARIO
			if(quadraFace.getDmc() != null){
            	adicionarQuadraFaceActionForm.setDmcID(String.valueOf(quadraFace.getDmc().getId()));
            }else{
            	adicionarQuadraFaceActionForm.setDmcID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            }
			
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
            	
            
			
			//CARREGANDO AS INFORMAï¿½OES DA FACE DA QUADRA NO FORMULARIO
			adicionarQuadraFaceActionForm.setNumeroFace(quadraFace.getNumeroQuadraFace().toString());
			adicionarQuadraFaceActionForm.setIndicadorRedeAguaAux(quadraFace.getIndicadorRedeAgua().toString());
			adicionarQuadraFaceActionForm.setIndicadorRedeEsgotoAux(quadraFace.getIndicadorRedeEsgoto().toString());

			
			 FiltrarQuadraActionForm filtrarQuadraActionForm = (FiltrarQuadraActionForm) sessao.getAttribute("FiltrarQuadraActionForm");
			
			 FiltroDmc filtroDmc = new FiltroDmc();

			 filtroDmc.adicionarParametro(new ParametroSimples( FiltroDmc.LOCALIDADE_ID, filtrarQuadraActionForm.getLocalidadeID()));
			 filtroDmc.adicionarParametro(new ParametroSimples( FiltroDmc.SETORCOMERCIAL_ID , filtrarQuadraActionForm.getSetorComercialCD()));
			 filtroDmc.adicionarParametro(new ParametroSimples( FiltroDmc.INDICADORUSO , ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoDmc= null;
			 colecaoDmc = fachada.pesquisar(filtroDmc, Dmc.class.getName());

			 if (colecaoDmc == null || colecaoDmc.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "DMC");
			 } 
			 else {
				 sessao.setAttribute("colecaoDmc", colecaoDmc);
			 }
			
			 //GRAU DIFICULDADE EXECUï¿½ï¿½O
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
			 
			//GRAU DE RISCO SEGURANï¿½A Fï¿½SICA
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
			 
			 //Nï¿½VEL DE PRESSï¿½O
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
			 			 
			 //GRAU DE INTERMITï¿½NCIA
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
	    	adicionarQuadraFaceActionForm.setDmcID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);

	    	
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
			 
			 //GRAU DIFICULDADE EXECUï¿½ï¿½O
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
			 
			 //GRAU DE RISCO SEGURANï¿½A Fï¿½SICA
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
			 
			 //Nï¿½VEL DE PRESSï¿½O
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
			 
			 //GRAU DE INTERMITï¿½NCIA
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
			 
			 FiltrarQuadraActionForm filtrarQuadraActionForm = (FiltrarQuadraActionForm) sessao.getAttribute("FiltrarQuadraActionForm");
				
			 FiltroDmc filtroDmc = new FiltroDmc();
			 //IF para definir cenário: Se a insercao da QuadraFace for em uma quadra já existente, deve-ser delimitar o DMC referente 
			 //a localidade e setor já atribuido a essa quadra. (Paulo Almeida - 19-05-2023)
			 if(filtrarQuadraActionForm != null) {
				 filtroDmc.adicionarParametro(new ParametroSimples( FiltroDmc.LOCALIDADE_ID, filtrarQuadraActionForm.getLocalidadeID()));
				 filtroDmc.adicionarParametro(new ParametroSimples( FiltroDmc.SETORCOMERCIAL_ID , filtrarQuadraActionForm.getSetorComercialCD()));
				 }
			 	 filtroDmc.adicionarParametro(new ParametroSimples( FiltroDmc.INDICADORUSO , ConstantesSistema.INDICADOR_USO_ATIVO));

			 Collection colecaoDmc= null;
			 colecaoDmc = fachada.pesquisar(filtroDmc, Dmc.class.getName());

			 if (colecaoDmc == null || colecaoDmc.isEmpty()) {
				 throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
			     "DMC");
			 } 
			 else {
				 sessao.setAttribute("colecaoDmc", colecaoDmc);
			 }
			
 
	    }
	 }
}

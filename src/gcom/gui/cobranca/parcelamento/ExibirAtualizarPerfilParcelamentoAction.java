package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.FiltroParcDesctoInativVista;
import gcom.cobranca.parcelamento.FiltroParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.FiltroParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.FiltroParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.FiltroParcelamentoQuantidadePrestacao;
import gcom.cobranca.parcelamento.FiltroParcelamentoQuantidadeReparcelamento;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de Exibir Atualizar Perfil de Parcelamento
 * @author Vivianne Sousa
 * @created 12/05/2006
 */

public class ExibirAtualizarPerfilParcelamentoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	    	
    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("atualizarPerfilParcelamento");
        AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm = (AtualizarParcelamentoPerfilActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);

        // volta da msg de  Perfil de Parcelamento j� utilizado, n�o pode ser alterado nem exclu�do.
        String confirmado = httpServletRequest.getParameter("confirmado");
        
        String idPerfilParcelamento = null;
       
        
//      Pesquisando ContaMotivoRevisao
        FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
        filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.INDICADOR_USO, 
        ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroContaMotivoRevisao.setCampoOrderBy(FiltroContaMotivoRevisao.DESCRICAO);
        Collection<ContaMotivoRevisao> collectionContaMotivoRevisao = fachada.pesquisar(filtroContaMotivoRevisao, 
                ContaMotivoRevisao.class.getName());
        
        sessao.setAttribute("collectionContaMotivoRevisao", collectionContaMotivoRevisao);
        
        
        
       if (httpServletRequest.getParameter("reload") == null
    		   || httpServletRequest.getParameter("reload").equalsIgnoreCase("")
    		   && (confirmado == null || confirmado.equals(""))){
           //Recupera o id do PerfilParcelamento que vai ser atualizado
           
           if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
           	idPerfilParcelamento = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
           	//Definindo a volta do bot�o Voltar p Filtrar Perfil de Parcelamento
   	    	httpServletRequest.setAttribute("voltar", "filtrar");
   	    	sessao.setAttribute("idRegistroAtualizacao",idPerfilParcelamento);
           }else if(httpServletRequest.getParameter("idRegistroAtualizacao") == null){
           	idPerfilParcelamento = (String)sessao.getAttribute("idRegistroAtualizacao");
   			//Definindo a volta do bot�o Voltar p Filtrar Perfil de Parcelamento
   	    	httpServletRequest.setAttribute("voltar", "filtrar");
           }else if (httpServletRequest.getParameter("idRegistroAtualizacao")!= null) {
           	idPerfilParcelamento = httpServletRequest.getParameter("idRegistroAtualizacao");
           	//Definindo a volta do bot�o Voltar p Manter Perfil de Parcelamento
           	httpServletRequest.setAttribute("voltar", "manter");
           	sessao.setAttribute("idRegistroAtualizacao",idPerfilParcelamento);
           }
       }else{
    	   idPerfilParcelamento = (String)sessao.getAttribute("idRegistroAtualizacao");
       }
    	   
        FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		Collection colecaoSistemaParametros = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros.iterator().next();
		String  percentualMaximoAbatimento = "" + sistemaParametro.getPercentualMaximoAbatimento();
		httpServletRequest.setAttribute("percentualMaximoAbatimento",percentualMaximoAbatimento);
		
        
        //Verifica se o usu�rio est� selecionando o Perfil de Parcelamento da p�gina de manter 
        //Caso contr�rio o usu�rio est� teclando enter na p�gina de atualizar
		if ((idPerfilParcelamento != null && !idPerfilParcelamento.equals(""))&& 
			(httpServletRequest.getParameter("desfazer") == null)&&	(httpServletRequest.getParameter("reload") == null
 		       || httpServletRequest.getParameter("reload").equalsIgnoreCase(""))) {
			exibirPerfilParcelamento(idPerfilParcelamento, atualizarParcelamentoPerfilActionForm,fachada,sessao,httpServletRequest);
			
		}
		
        if (httpServletRequest.getParameter("adicionarParcelamentoQtdePerfil") != null
                && httpServletRequest.getParameter("adicionarParcelamentoQtdePerfil").equalsIgnoreCase("S")
                && atualizarParcelamentoPerfilActionForm.getQtdeMaximaReparcelamento() != null) {
        	
        	//-------------- bt adicionarParcelamentoQtdePerfil ---------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);

        	httpServletRequest.removeAttribute("adicionarParcelamentoQtdePerfil");

        }
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoAntiguidade") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoAntiguidade").equalsIgnoreCase("S")
                && !atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoAntiguidade ------------------------
   
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	        	
        	adicionarParcelamentoDescontoAntiguidade(atualizarParcelamentoPerfilActionForm, sessao,fachada);
        	
        	httpServletRequest.removeAttribute("adicionarParcelamentoDescontoAntiguidade");
        }
        
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividade") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividade").equalsIgnoreCase("S")
                && !atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoInatividade ------------------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	adicionarParcelamentoDescontoInatividade(atualizarParcelamentoPerfilActionForm,sessao,fachada);
        	
        	httpServletRequest.removeAttribute("adicionarParcelamentoDescontoInatividade");
        }
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividadeAVista") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividadeAVista").equalsIgnoreCase("S")
                && !atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoInatividadeAVista ------------------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	adicionarParcelamentoDescontoInatividadeAVista(atualizarParcelamentoPerfilActionForm,sessao);
        	
        	httpServletRequest.removeAttribute("adicionarParcelamentoDescontoInatividadeAVista");
        	
        }
		
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------
        	//sessao.removeAttribute("collectionRotaAcaoCriterio");
        	//collectionRotaAcaoCriterio = null;
        	exibirPerfilParcelamento(idPerfilParcelamento, atualizarParcelamentoPerfilActionForm,fachada,sessao,httpServletRequest);
        	sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");
    		sessao.removeAttribute("collectionContaMotivoRevisao");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");
        }

        
       
    	// [FS0002]Verificar se perfil de parcelamento j� foi utilizado
		
//    	FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
//    	filtroParcelamento.adicionarParametro(new ParametroSimples(
//    			FiltroParcelamento.PARCELAMENTO_PERFIL_ID, idPerfilParcelamento));
//		Collection	colecaoParcelamento = fachada.pesquisar(filtroParcelamento,
//				Parcelamento.class.getName());
//
//		if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
//			//Perfil de Parcelamento j� utilizado, n�o pode ser alterado nem exclu�do.
////			String confirmado = httpServletRequest.getParameter("?confirmado");
//			httpServletRequest.setAttribute("readOnly",true);
//			
//			if ((confirmado == null || confirmado.equals(""))
//					&& (httpServletRequest.getParameter("reload") == null
//				     		   || httpServletRequest.getParameter("reload").equalsIgnoreCase(""))){
//				
//				String numeroRD = httpServletRequest.getParameter("numeroRd");
//				
//				httpServletRequest.setAttribute(
//						"caminhoActionConclusao",
//						"/gsan/exibirAtualizarPerfilParcelamentoAction.do" );					
//				
//				/*httpServletRequest.setAttribute(
//						"caminhoActionConclusao",
//						"/gsan/exibirAtualizarPerfilParcelamentoAction.do?idRegistroAtualizacao="+ idPerfilParcelamento + "&" );							
//				*/
//				return montarPaginaConfirmacao(
//						"atencao.perfil_parcelamento_usado.confirma",
//						httpServletRequest, actionMapping, 
//						numeroRD);
//			}
//		}

		
		
       sessao.removeAttribute("caminhoRetornoTelaPesquisa");
		// DEFINI��O QUE IR� AUXILIAR O RETORNO DOS POPUPS
       sessao.setAttribute("UseCase", "ATUALIZARPERFIL");
       
       
       if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") == null || 
		((Collection) sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper")).size() == 0){
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","1");
		}else{
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","2");
		}
       
       
       return retorno;  
    
    }
    
    
    private void exibirPerfilParcelamento(String idPerfilParcelamento, 
    		AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm,
			Fachada fachada,HttpSession sessao,
			HttpServletRequest httpServletRequest) {
    	
    	//Cria a vari�vel que vai armazenar o ParcelamentoPerfil para ser atualizado
    	ParcelamentoPerfil parcelamentoPerfil = null;
        
    	//Cria o filtro de ParcelamentoPerfil e seta o id do ParcelamentoPerfil para ser atualizado no filtro
		//e indica quais objetos devem ser retornados pela pesquisa
        FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
        filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.ID, idPerfilParcelamento));
        
        filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("categoria");
		
		Collection<ParcelamentoPerfil> collectionParcelamentoPerfil = 
			fachada.pesquisar(filtroParcelamentoPerfil, ParcelamentoPerfil.class.getName()) ;	
			
		//Caso a pesquisa tenha retornado o ParcelamentoPerfil
		if (collectionParcelamentoPerfil != null && !collectionParcelamentoPerfil.isEmpty()){
			
			//Recupera da cole��o o ParcelamentoPerfil que vai ser atualizado
			parcelamentoPerfil = (ParcelamentoPerfil) Util.retonarObjetoDeColecao(collectionParcelamentoPerfil);

			//Seta no form os dados de ParcelamentoPerfil
			
			atualizarParcelamentoPerfilActionForm.setNumeroResolucaoDiretoria("" + 
					parcelamentoPerfil.getResolucaoDiretoria().getNumeroResolucaoDiretoria());
			atualizarParcelamentoPerfilActionForm.setImovelSituacaoTipo("" +
					parcelamentoPerfil.getImovelSituacaoTipo().getDescricaoImovelSituacaoTipo());
			
			if (parcelamentoPerfil.getSubcategoria() != null &&
					!parcelamentoPerfil.getSubcategoria().equals("")){
				atualizarParcelamentoPerfilActionForm.setSubcategoria("" + 
						parcelamentoPerfil.getSubcategoria().getDescricao());
			}else {
				atualizarParcelamentoPerfilActionForm.setSubcategoria("");
			}
			
			if (parcelamentoPerfil.getCategoria() != null &&
					!parcelamentoPerfil.getCategoria().equals("")){
				atualizarParcelamentoPerfilActionForm.setCategoria("" + 
						parcelamentoPerfil.getCategoria().getDescricao());
			}else {
				atualizarParcelamentoPerfilActionForm.setCategoria("");
			}
			
			if (parcelamentoPerfil.getImovelPerfil() != null &&
					!parcelamentoPerfil.getImovelPerfil().equals("")){
				atualizarParcelamentoPerfilActionForm.setImovelPerfil("" + 
					parcelamentoPerfil.getImovelPerfil().getDescricao());
			}else{
				atualizarParcelamentoPerfilActionForm.setImovelPerfil("");
			}
			
			//atualizarParcelamentoPerfilActionForm.setUltimaAlteracao("" + 
				//	parcelamentoPerfil.getUltimaAlteracao());
			
        	if ( httpServletRequest.getParameter("reload") == null
        		    		   || httpServletRequest.getParameter("reload").equalsIgnoreCase("")){
        		
        		/*
        		 *
        		 */
        		if (parcelamentoPerfil.getPercentualDescontoAcrescimoMulta() != null){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoMulta("" +
        					parcelamentoPerfil.getPercentualDescontoAcrescimoMulta().toString().replace(".", ","));
        		}
        		
        		if (parcelamentoPerfil.getPercentualDescontoAcrescimoJurosMora() != null){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoJurosMora("" +
        					parcelamentoPerfil.getPercentualDescontoAcrescimoJurosMora().toString().replace(".", ","));
        		}
        		
        		if (parcelamentoPerfil.getPercentualDescontoAcrescimoAtualizacaoMonetaria() != null){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoAtualizacaoMonetaria("" +
        					parcelamentoPerfil.getPercentualDescontoAcrescimoAtualizacaoMonetaria().toString().replace(".", ","));
        		}
        		// fim altera��o
        		
        		if (parcelamentoPerfil.getPercentualDescontoPagamentoAVista() != null){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista("" +
        					parcelamentoPerfil.getPercentualDescontoPagamentoAVista().toString().replace(".", ","));
        		}
        		
        		if (parcelamentoPerfil.getPercentualTarifaMinimaPrestacao()!= null){
        			atualizarParcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao("" +
        					parcelamentoPerfil.getPercentualTarifaMinimaPrestacao().toString().replace(".", ","));
        		}
        		
        		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido(
        				"" + parcelamentoPerfil.getIndicadorChequeDevolvido());
        		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta(
        				"" + parcelamentoPerfil.getIndicadorSancoesUnicaConta());
        		
        		if (parcelamentoPerfil.getNumeroConsumoMinimo()!= null &&
        				!parcelamentoPerfil.getNumeroConsumoMinimo().equals("")){
        			atualizarParcelamentoPerfilActionForm.setConsumoMinimo("" + parcelamentoPerfil.getNumeroConsumoMinimo());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setConsumoMinimo("");
        		}
        		if (parcelamentoPerfil.getPercentualVariacaoConsumoMedio()!= null &&
        				!parcelamentoPerfil.getPercentualVariacaoConsumoMedio().equals("")){
        			atualizarParcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio(
        					Util.formatarMoedaReal(parcelamentoPerfil.getPercentualVariacaoConsumoMedio()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio("");
        		}
        		
        		if (parcelamentoPerfil.getNumeroConsumoEconomia()!= null &&
        				!parcelamentoPerfil.getNumeroConsumoEconomia().equals("")){
        			atualizarParcelamentoPerfilActionForm.setNumeroConsumoEconomia(
        					parcelamentoPerfil.getNumeroConsumoEconomia().toString());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setNumeroConsumoEconomia("");
        		}
        		
        		if (parcelamentoPerfil.getNumeroAreaConstruida()!= null &&
        				!parcelamentoPerfil.getNumeroAreaConstruida().equals("")){
        			atualizarParcelamentoPerfilActionForm.setNumeroAreaConstruida(
        					Util.formatarMoedaReal(parcelamentoPerfil.getNumeroAreaConstruida()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setNumeroAreaConstruida("");
        		}
        		
        		if (parcelamentoPerfil.getAnoMesReferenciaLimiteInferior()!= null &&
        				!parcelamentoPerfil.getAnoMesReferenciaLimiteInferior().equals("")){
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior(
        					Util.formatarAnoMesParaMesAno(parcelamentoPerfil.getAnoMesReferenciaLimiteInferior()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior("");
        		}
        		
        		if (parcelamentoPerfil.getAnoMesReferenciaLimiteSuperior()!= null &&
        				!parcelamentoPerfil.getAnoMesReferenciaLimiteSuperior().equals("")){
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior(
        					Util.formatarAnoMesParaMesAno(parcelamentoPerfil.getAnoMesReferenciaLimiteSuperior()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior("");
        		}
        		
        		if (parcelamentoPerfil.getPercentualDescontoTarifaSocial()!= null &&
        				!parcelamentoPerfil.getPercentualDescontoTarifaSocial().equals("")){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoTarifaSocial(
        					Util.formatarMoedaReal(parcelamentoPerfil.getPercentualDescontoTarifaSocial()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoTarifaSocial("");
        		}
        		 
        		if (parcelamentoPerfil.getParcelaQuantidadeMinimaFatura()!= null &&
        				!parcelamentoPerfil.getParcelaQuantidadeMinimaFatura().equals("")){
        			atualizarParcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura(
        					parcelamentoPerfil.getParcelaQuantidadeMinimaFatura().toString());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura("");
        		}
        		
        		if (parcelamentoPerfil.getPercentualDescontoSancao()!= null &&
        				!parcelamentoPerfil.getPercentualDescontoSancao().equals("")){
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoSancao(
        					Util.formatarMoedaReal(parcelamentoPerfil.getPercentualDescontoSancao()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setPercentualDescontoSancao("");
        		}
        		
        		if (parcelamentoPerfil.getQuantidadeEconomias()!= null &&
        				!parcelamentoPerfil.getQuantidadeEconomias().equals("")){
        			atualizarParcelamentoPerfilActionForm.setQuantidadeEconomias(
        					parcelamentoPerfil.getQuantidadeEconomias().toString());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setQuantidadeEconomias("");
        		}
        		
        		atualizarParcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial(
        				"" + parcelamentoPerfil.getIndicadorRetroativoTarifaSocial());
        		
        		atualizarParcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima(
        				"" + parcelamentoPerfil.getIndicadorAlertaParcelaMinima());
        		
        		atualizarParcelamentoPerfilActionForm.setIndicadorEntradaMinima(
        				"" + parcelamentoPerfil.getIndicadorEntradaMinima());
        		
        		atualizarParcelamentoPerfilActionForm.setCapacidadeHidrometro(
        				"" + parcelamentoPerfil.getCapacidadeHidrometro());
        		
        		
        		if (parcelamentoPerfil.getQuantidadeMaximaReparcelamento()!= null &&
        				!parcelamentoPerfil.getQuantidadeMaximaReparcelamento().equals("")){
        			atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento(
            				"" + parcelamentoPerfil.getQuantidadeMaximaReparcelamento());
        		}else{
        			atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento("");
        		}
        		
        		
        		if (parcelamentoPerfil.getDataLimiteDescontoPagamentoAVista()!= null &&
        				!parcelamentoPerfil.getDataLimiteDescontoPagamentoAVista().equals("")){
        			atualizarParcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista(
            				Util.formatarData(parcelamentoPerfil.getDataLimiteDescontoPagamentoAVista()));
        		}else{
        			atualizarParcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista("");
        		}
        		
        		
        	
        	}
						
			atualizarParcelamentoPerfilActionForm.setUltimaAlteracao(Util.formatarDataComHora(parcelamentoPerfil.getUltimaAlteracao()));   
			
			if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper")== null
					|| (httpServletRequest.getParameter("desfazer") != null
			                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
				//recupera a cole��o de ParcelamentoQuantidadeReparcelamento	
				// e tranforma numa cole��o de ParcelamentoQuantidadeReparcelamentoHelper
				FiltroParcelamentoQuantidadeReparcelamento filtroParcelamentoQuantidadeReparcelamento = new FiltroParcelamentoQuantidadeReparcelamento(); 
				
				filtroParcelamentoQuantidadeReparcelamento.adicionarParametro(new ParametroSimples(
						FiltroParcelamentoDescontoAntiguidade.PARCELAMENTO_PERFIL,idPerfilParcelamento));
				
				Collection<ParcelamentoQuantidadeReparcelamento> collectionParcelamentoQuantidadeReparcelamento = fachada
				.pesquisar(filtroParcelamentoQuantidadeReparcelamento,ParcelamentoQuantidadeReparcelamento.class.getName());
				
				Iterator iterator = collectionParcelamentoQuantidadeReparcelamento.iterator();
				Collection 	collectionParcelamentoQuantidadeReparcelamentoHelper = new ArrayList();
				while (iterator.hasNext()) {
					ParcelamentoQuantidadeReparcelamento parcelamentoQtdeReparcelamento = (ParcelamentoQuantidadeReparcelamento) iterator.next();
					
					ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQtdeReparcelamentoHelper = new ParcelamentoQuantidadeReparcelamentoHelper();

					//recupera a cole��o de ParcelamentoQuantidadePrestacao
					// e seta no objeto ParcelamentoQtdeReparcelamentoHelper
					FiltroParcelamentoQuantidadePrestacao filtroParcelamentoQuantidadePrestacao = new FiltroParcelamentoQuantidadePrestacao();
					filtroParcelamentoQuantidadePrestacao.adicionarParametro(new ParametroSimples(
							FiltroParcelamentoQuantidadePrestacao.PARCELAMENTO_QUANTIDADE_REPARCELAMENTO,parcelamentoQtdeReparcelamento.getId()));
					Collection<ParcelamentoQuantidadePrestacao> collectionParcelamentoQuantidadePrestacao = fachada
					.pesquisar(filtroParcelamentoQuantidadePrestacao,ParcelamentoQuantidadePrestacao.class.getName());
					
					
					
					////////// n posso setar em CollectionParcelamentoQuantidadePrestacaoHelper uma CollectionParcelamentoQuantidadePrestacao
					
					Collection collectionParcelamentoQuantidadePrestacaoHelper = new ArrayList();
					Iterator iteratorParcelamentoFaixaValor = collectionParcelamentoQuantidadePrestacao.iterator();
					
					while (iteratorParcelamentoFaixaValor.hasNext()) {
						ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao = 
							(ParcelamentoQuantidadePrestacao) iteratorParcelamentoFaixaValor.next();
						
						FiltroParcelamentoFaixaValor filtroParcelamentoFaixaValor = new FiltroParcelamentoFaixaValor();
						
		//				filtroParcelamentoFaixaValor
		//						.adicionarCaminhoParaCarregamentoEntidade("parcelamentoQuantidadePrestacao");
						filtroParcelamentoFaixaValor
								.adicionarParametro(new ParametroSimples(
										FiltroParcelamentoFaixaValor.PARCELAMENTO_QUANTIDADE_PRESTACAO,
										parcelamentoQuantidadePrestacao.getId()));
						filtroParcelamentoFaixaValor.setCampoOrderBy(FiltroParcelamentoFaixaValor.VALOR_FAIXA);
		
						Collection collectionParcelamentoFaixaValor = (Collection)
							fachada.pesquisar(filtroParcelamentoFaixaValor,
							ParcelamentoFaixaValor.class.getName());
						
						
						
						ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper =
							new ParcelamentoQuantidadePrestacaoHelper();
						
						parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
						parcelamentoQuantidadePrestacaoHelper.setCollectionParcelamentoFaixaValor(collectionParcelamentoFaixaValor);
						collectionParcelamentoQuantidadePrestacaoHelper.add(parcelamentoQuantidadePrestacaoHelper);
					}
					
					parcelamentoQtdeReparcelamentoHelper.setCollectionParcelamentoQuantidadePrestacaoHelper
								(collectionParcelamentoQuantidadePrestacaoHelper);

					parcelamentoQtdeReparcelamentoHelper.setId(parcelamentoQtdeReparcelamento.getId());
					parcelamentoQtdeReparcelamentoHelper.setQuantidadeMaximaReparcelamento(parcelamentoQtdeReparcelamento.getQuantidadeMaximaReparcelamento());
					parcelamentoQtdeReparcelamentoHelper.setPercentualEntradaSugerida(
							parcelamentoQtdeReparcelamento.getPercentualEntradaSugerida());
					parcelamentoQtdeReparcelamentoHelper.setInformacaoParcelamentoQtdeReparcelamento("INFORMADAS");
					parcelamentoQtdeReparcelamentoHelper.setUltimaAlteracao(parcelamentoQtdeReparcelamento.getUltimaAlteracao());
					collectionParcelamentoQuantidadeReparcelamentoHelper.add(parcelamentoQtdeReparcelamentoHelper);

				}
				sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper",collectionParcelamentoQuantidadeReparcelamentoHelper);
	
			}
						
			if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") == null
					|| (httpServletRequest.getParameter("desfazer") != null
			                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
				//recupera a cole��o de ParcelamentoDescontoAntiguidade
				FiltroParcelamentoDescontoAntiguidade filtroParcelamentoDescontoAntiguidade = new FiltroParcelamentoDescontoAntiguidade();
				
				filtroParcelamentoDescontoAntiguidade.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
				
				filtroParcelamentoDescontoAntiguidade.adicionarParametro(new ParametroSimples(
						FiltroParcelamentoDescontoAntiguidade.PARCELAMENTO_PERFIL,idPerfilParcelamento));

				Collection<ParcelamentoDescontoAntiguidade> collectionParcelamentoDescontoAntiguidade = fachada
				.pesquisar(filtroParcelamentoDescontoAntiguidade,ParcelamentoDescontoAntiguidade.class.getName());
				//httpServletRequest.setAttribute("collectionParcelamentoDescontoAntiguidade",collectionParcelamentoDescontoAntiguidade);
				sessao.setAttribute("collectionParcelamentoDescontoAntiguidade",collectionParcelamentoDescontoAntiguidade);				
			}

			
			if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") == null
					|| (httpServletRequest.getParameter("desfazer") != null
			                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
				//recupera a cole��o de ParcelamentoDescontoInatividade
				FiltroParcelamentoDescontoInatividade filtroParcelamentoDescontoInatividade = new FiltroParcelamentoDescontoInatividade();
				
				filtroParcelamentoDescontoInatividade.adicionarParametro(new ParametroSimples(
						FiltroParcelamentoDescontoInatividade.PARCELAMENTO_PERFIL,idPerfilParcelamento));
	
				Collection<ParcelamentoDescontoInatividade> collectionParcelamentoDescontoInatividade = fachada
				.pesquisar(filtroParcelamentoDescontoInatividade,ParcelamentoDescontoInatividade.class.getName());
				//httpServletRequest.setAttribute("collectionParcelamentoDescontoInatividade",collectionParcelamentoDescontoInatividade);
				sessao.setAttribute("collectionParcelamentoDescontoInatividade",collectionParcelamentoDescontoInatividade);
			}
			
			if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") == null
					|| (httpServletRequest.getParameter("desfazer") != null
			                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
				//recupera a cole��o de ParcelamentoDescontoInatividadeAVista
				FiltroParcDesctoInativVista filtroParcDesctoInativVista = new FiltroParcDesctoInativVista();
				
				filtroParcDesctoInativVista.adicionarParametro(new ParametroSimples(
						FiltroParcelamentoDescontoInatividade.PARCELAMENTO_PERFIL,idPerfilParcelamento));
	
				Collection<ParcDesctoInativVista> collectionParcelamentoDescontoInatividadeAVista = fachada
				.pesquisar(filtroParcDesctoInativVista,ParcDesctoInativVista.class.getName());

				sessao.setAttribute("collectionParcelamentoDescontoInatividadeAVista",collectionParcelamentoDescontoInatividadeAVista);
			}
		}
    	
    }
    
	private void adicionarParcelamentoDescontoAntiguidade(
						AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm,
						HttpSession sessao,Fachada fachada){
		
		if (atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito()== null ||
				atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe Qtde. M�nima Meses de D�bito p/ Desconto
					"atencao.required", null, " Qtde. M�nima Meses de D�bito p/ Desconto");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito())){
			throw new ActionServletException(
					// Qtde. M�nima Meses de D�bito p/ Desconto deve ser numerico
					"atencao.integer", null, " Qtde. M�nima Meses de D�bito p/ Desconto");
		 }
		
		Collection<ParcelamentoDescontoAntiguidade> collectionParcelamentoDescontoAntiguidade = null;
		
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null) {
			collectionParcelamentoDescontoAntiguidade = (Collection) sessao
		    .getAttribute("collectionParcelamentoDescontoAntiguidade");
		} else {
			collectionParcelamentoDescontoAntiguidade = new ArrayList();
		}
	    
	
		Integer quantidadeMinimaMesesDebito = new Integer(atualizarParcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito());
		
		//[FS0006]Verificar percentual de desconto
		/*FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		
		Collection colecaoSistemaParametros = fachada.pesquisar(
		filtroSistemaParametro, SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
		.iterator().next();
		
		BigDecimal percentualMaximoAbatimento = sistemaParametro.getPercentualMaximoAbatimento();
	*/
	
	
		BigDecimal percentualDescontoSemRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{	
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
				//Percentual de Desconto Sem Restabelecimento � superior ao 
				//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
				throw new ActionServletException(
				"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimento);
    		}*/
		}
	
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
				//Percentual de Desconto Com Restabelecimento � superior ao 
				//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
				throw new ActionServletException(
				"atencao.percentual_desconto_com_rest_superior_percentual_max", null,"" + percentualMaximoAbatimento);
    		}*/
        }
        
        BigDecimal percentualDescontoAtivo = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoAtivo()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoAtivo().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Ativo
					"atencao.required", null, "  Percentual de Desconto Ativo");	
        
        }else{
        	percentualDescontoAtivo = Util.formatarMoedaRealparaBigDecimal
        		(atualizarParcelamentoPerfilActionForm.getPercentualDescontoAtivo());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoAtivo.compareTo(percentualMaximoAbatimento) > 0  ){
				//Percentual de Desconto Ativo � superior ao 
				//Percentual M�ximo de Desconto de << percentualMaximoAbatimento  >> permitido para Financiamneto
				throw new ActionServletException(
				"atencao.percentual_desconto_ativo_superior_percentual_max",null,"" + percentualMaximoAbatimento);
    		}*/
		}
	
	
		ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeNovo = 
								new ParcelamentoDescontoAntiguidade();
		
		if (collectionParcelamentoDescontoAntiguidade != null && !collectionParcelamentoDescontoAntiguidade.isEmpty()){
			// se cole��o n�o estiver vazia
			// verificar se a qtd m�xima de presta��es do parcelamento ja foi informada	
			ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoAntiguidade.iterator();
			
			while (iterator.hasNext()) {
				parcelamentoDescontoAntiguidadeAntigo = (ParcelamentoDescontoAntiguidade) iterator.next();
				
				// Verificar quantidade m�nima meses de debito para desconto
				if (quantidadeMinimaMesesDebito.equals
						(parcelamentoDescontoAntiguidadeAntigo.getQuantidadeMinimaMesesDebito())){
					//Quantidade M�nima Meses de Debito para Desconto j� informada
					throw new ActionServletException(
					"atencao.qtde_minima_meses_debito_desconto_ja_informado");
				}
			}
		}
		
		if (atualizarParcelamentoPerfilActionForm.getIdContaMotivoRevisao() != null && 
			!atualizarParcelamentoPerfilActionForm.getIdContaMotivoRevisao().equalsIgnoreCase("") &&
			!atualizarParcelamentoPerfilActionForm.getIdContaMotivoRevisao().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
	        	
			FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
		        
			filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.ID, 
			new Integer(atualizarParcelamentoPerfilActionForm.getIdContaMotivoRevisao())));
		        
		    Collection<ContaMotivoRevisao> collectionContaMotivoRevisao = fachada.pesquisar(filtroContaMotivoRevisao, 
		    ContaMotivoRevisao.class.getName());
		        
		    ContaMotivoRevisao contaMotivoRevisao = (ContaMotivoRevisao)
		    Util.retonarObjetoDeColecao(collectionContaMotivoRevisao);
		        
		    parcelamentoDescontoAntiguidadeNovo.setContaMotivoRevisao(contaMotivoRevisao);
		}
		
	
		parcelamentoDescontoAntiguidadeNovo.setQuantidadeMinimaMesesDebito(quantidadeMinimaMesesDebito);
		parcelamentoDescontoAntiguidadeNovo.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimento);
		parcelamentoDescontoAntiguidadeNovo.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimento);
		parcelamentoDescontoAntiguidadeNovo.setPercentualDescontoAtivo(percentualDescontoAtivo);
		parcelamentoDescontoAntiguidadeNovo.setUltimaAlteracao(new Date());
		
		collectionParcelamentoDescontoAntiguidade.add(parcelamentoDescontoAntiguidadeNovo);
		
		atualizarParcelamentoPerfilActionForm.setQuantidadeMinimaMesesDebito("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoAntiguidade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoAntiguidade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		atualizarParcelamentoPerfilActionForm.setIdContaMotivoRevisao(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		
		//Ordena a cole��o pela Qtde. M�nima Meses de D�bito p/ Desconto
		Collections.sort((List) collectionParcelamentoDescontoAntiguidade, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoDescontoAntiguidade) a).getQuantidadeMinimaMesesDebito().toString()) ;
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoDescontoAntiguidade) b).getQuantidadeMinimaMesesDebito().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		//manda para a sess�o a cole��o de ParcelamentoDescontoAntiguidade
		sessao.setAttribute("collectionParcelamentoDescontoAntiguidade", collectionParcelamentoDescontoAntiguidade);

	}

	private void adicionarParcelamentoDescontoInatividade(AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm,
				HttpSession sessao,Fachada fachada){
		
		if (atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade() == null ||
				atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Qtde. M�xima Meses de Inatividade da Lig. de �gua
					"atencao.required", null, " Qtde. M�xima Meses de Inatividade da Lig. de �gua");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade())){
			throw new ActionServletException(
					// Qtde. M�xima Meses de Inatividade da Lig. de �gua deve ser numerico
					"atencao.integer", null, " Qtde. M�xima Meses de Inatividade da Lig. de �gua");
		}
		
		Collection<ParcelamentoDescontoInatividade> collectionParcelamentoDescontoInatividade = null;
		
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null) {
			collectionParcelamentoDescontoInatividade = (Collection) sessao
		    .getAttribute("collectionParcelamentoDescontoInatividade");
		} else {
			collectionParcelamentoDescontoInatividade = new ArrayList();
		}
		    
		Integer quantidadeMaximaMesesInatividade = new Integer(atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade());
		
		/*FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		
		Collection colecaoSistemaParametros = fachada.pesquisar(
		filtroSistemaParametro, SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
		.iterator().next();
		
		BigDecimal percentualMaximoAbatimento = sistemaParametro.getPercentualMaximoAbatimento();*/
		
		BigDecimal percentualDescontoSemRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade());

        	//[FS0006]Verificar percentual de desconto
    		/*if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
    			//Percentual de Desconto Sem Restabelecimento � superior ao 
    			//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
    			throw new ActionServletException(
    				"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimento);
    			
    		}*/
		}
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
    			//Percentual de Desconto Com Restabelecimento � superior ao 
    			//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
    			throw new ActionServletException(
    				"atencao.percentual_desconto_com_rest_superior_percentual_max", null,"" + percentualMaximoAbatimento);
    			
    		}*/
		}		
		
		ParcelamentoDescontoInatividade parcelamentoDescontoInatividadeNovo = 
								new ParcelamentoDescontoInatividade();
		
		if (collectionParcelamentoDescontoInatividade != null && !collectionParcelamentoDescontoInatividade.isEmpty()){
			// se cole��o n�o estiver vazia
			// verificar se a qtd m�xima de presta��es do parcelamento ja foi informada	
			ParcelamentoDescontoInatividade parcelamentoDescontoInatividadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoInatividade.iterator();
			
			while (iterator.hasNext()) {
				parcelamentoDescontoInatividadeAntigo = (ParcelamentoDescontoInatividade) iterator.next();
				
				//[FS0003] Verificar quantidade m�nima meses de debito
				if (quantidadeMaximaMesesInatividade.equals
						(parcelamentoDescontoInatividadeAntigo.getQuantidadeMaximaMesesInatividade())){
					//Quantidade M�xima Meses de Inatividade de Liga��o de �gua j� informada
					throw new ActionServletException(
					"atencao.qtde_maxima_meses_inatividade_ja_informado");
				}
			}
		}			
		
		
		parcelamentoDescontoInatividadeNovo.setQuantidadeMaximaMesesInatividade(quantidadeMaximaMesesInatividade);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setUltimaAlteracao(new Date());
		
		collectionParcelamentoDescontoInatividade.add(parcelamentoDescontoInatividadeNovo);
		
		atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividade("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		//Ordena a cole��o pela Qtde. M�xima Meses de Inatividade da Lig. de �gua
		Collections.sort((List) collectionParcelamentoDescontoInatividade, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoDescontoInatividade) a).getQuantidadeMaximaMesesInatividade().toString() );
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoDescontoInatividade) b).getQuantidadeMaximaMesesInatividade().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		//manda para a sess�o a cole��o de ParcelamentoDescontoInatividade
		sessao.setAttribute("collectionParcelamentoDescontoInatividade", collectionParcelamentoDescontoInatividade);
		
	}

	
	 private void adicionarParcelamentoDescontoInatividadeAVista(
			 AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm,HttpSession sessao){
	    	
    	if (atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista() == null ||
    			atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Qtde. M�xima Meses de Inatividade da Lig. de �gua
					"atencao.required", null, " Qtde. M�xima Meses de Inatividade da Lig. de �gua");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista())){
			throw new ActionServletException(
					// Qtde. M�xima Meses de Inatividade da Lig. de �gua deve ser numerico
					"atencao.integer", null, " Qtde. M�xima Meses de Inatividade da Lig. de �gua");
		 }
    	
    	Collection<ParcDesctoInativVista> collectionParcelamentoDescontoInatividadeAVista = null;
        
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null) {
        	collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividadeAVista");
        } else {
        	collectionParcelamentoDescontoInatividadeAVista = new ArrayList();
        }
    	            
        Integer quantidadeMaximaMesesInatividade = new Integer(atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista());

        BigDecimal percentualDescontoSemRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(atualizarParcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista());

		}
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista()== null
        		|| atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(atualizarParcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista());
        	
		}
        						
		
        ParcDesctoInativVista parcelamentoDescontoInatividadeNovo = new ParcDesctoInativVista();
		 
		if (collectionParcelamentoDescontoInatividadeAVista != null && !collectionParcelamentoDescontoInatividadeAVista.isEmpty()){
			// se cole��o n�o estiver vazia
			// verificar se a qtd m�xima de presta��es do parcelamento ja foi informada	
			ParcDesctoInativVista parcelamentoDescontoInatividadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoInatividadeAVista.iterator();
		
			while (iterator.hasNext()) {
			parcelamentoDescontoInatividadeAntigo = (ParcDesctoInativVista) iterator.next();
				
				//[FS0003] Verificar quantidade m�nima meses de debito
				if (quantidadeMaximaMesesInatividade.equals
						(parcelamentoDescontoInatividadeAntigo.getQuantidadeMaximaMesesInatividade())){
					//Quantidade M�xima Meses de Inatividade de Liga��o de �gua j� informada
					throw new ActionServletException(
					"atencao.qtde_maxima_meses_inatividade_ja_informado");
				}
			}
		}			


		parcelamentoDescontoInatividadeNovo.setQuantidadeMaximaMesesInatividade(quantidadeMaximaMesesInatividade);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimento);
		parcelamentoDescontoInatividadeNovo.setUltimaAlteracao(new Date());
		
		collectionParcelamentoDescontoInatividadeAVista.add(parcelamentoDescontoInatividadeNovo);

		atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividadeAVista("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividadeAVista("");
		atualizarParcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividadeAVista("");
//			parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		
		//Ordena a cole��o pela Qtde. M�xima Meses de Inatividade da Lig. de �gua
		Collections.sort((List) collectionParcelamentoDescontoInatividadeAVista, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcDesctoInativVista) a).getQuantidadeMaximaMesesInatividade().toString() );
				Integer valorMinPrestacao2 = new Integer(((ParcDesctoInativVista) b).getQuantidadeMaximaMesesInatividade().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		 //manda para a sess�o a cole��o de ParcelamentoDescontoInatividadeAVista
        sessao.setAttribute("collectionParcelamentoDescontoInatividadeAVista", collectionParcelamentoDescontoInatividadeAVista);

    }

	private void atualizaColecoesNaSessao(HttpSession sessao,
					HttpServletRequest httpServletRequest){
	
		// collectionParcelamentoDescontoAntiguidade
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null
		&& !sessao.getAttribute("collectionParcelamentoDescontoAntiguidade").equals("")) {
		
			Collection collectionParcelamentoDescontoAntiguidade = (Collection) sessao
				.getAttribute("collectionParcelamentoDescontoAntiguidade");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoAntiguidade
			String vlSemRestAntiguidade = null;
			String vlComRestAntiguidade = null;
			String vlDescontoAtivo = null;
			
			
			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade
				.iterator();
			
			while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {
				ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) iteratorParcelamentoDescontoAntiguidade
						.next();
				long valorTempo = parcelamentoDescontoAntiguidade.getUltimaAlteracao()
						.getTime();
				vlSemRestAntiguidade = (String) httpServletRequest.getParameter("vlSemRestAntiguidade"
						+ valorTempo);
				vlComRestAntiguidade = httpServletRequest.getParameter("vlComRestAntiguidade"
						+ valorTempo);
				vlDescontoAtivo = httpServletRequest.getParameter("vlDescontoAtivo"
						+ valorTempo);
			
				// inseri essas vari�veis no objeto ParcelamentoDescontoAntiguidade
				BigDecimal percentualDescontoSemRestabelecimentoAntiguidade  = null;
				if (vlSemRestAntiguidade != null 
						&& !vlSemRestAntiguidade.equals("")) {
					percentualDescontoSemRestabelecimentoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestAntiguidade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoAntiguidade = null;
				if (vlComRestAntiguidade != null 
						&& !vlComRestAntiguidade.equals("")) {
					percentualDescontoComRestabelecimentoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestAntiguidade);
				}
				
				BigDecimal percentualDescontoAtivoAntiguidade = null;
				if (vlDescontoAtivo != null 
						&& !vlDescontoAtivo.equals("")) {
					percentualDescontoAtivoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlDescontoAtivo);
				}
			
				parcelamentoDescontoAntiguidade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.
					setPercentualDescontoAtivo(percentualDescontoAtivoAntiguidade);
			
			}
			
		}
			
		
		//collectionParcelamentoDescontoInatividade
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null
		&& !sessao.getAttribute("collectionParcelamentoDescontoInatividade").equals("")) {
		
			Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
				.getAttribute("collectionParcelamentoDescontoInatividade");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;
			
			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade
				.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = 
						(ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade
						.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao()
						.getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividade"
						+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividade"
						+ valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null 
						&& !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null 
						&& !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}
				
				parcelamentoDescontoInatividade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);
			}
		}	        	
		
    	//collectionParcelamentoDescontoInatividadeAVista
    	if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null
				&& !sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista").equals("")) {
	

			Collection collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividadeAVista.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcDesctoInativVista parcelamentoDescontoInatividade = 
						(ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao().getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividadeAVista"+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividadeAVista"+ valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}

				parcelamentoDescontoInatividade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);


			}

        }	 
		
		//collectionParcelamentoQuantidadeReparcelamentoHelper
		/*
		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null
		&& !sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper").equals(
				"")) {
		
			Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
				.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoQuantidadeReparcelamentoHelper
			String vlMinPrest = null;
			
			Iterator iteratorParcelamentoQuantidadeReparcelamentoHelper = collectionParcelamentoQuantidadeReparcelamentoHelper
				.iterator();
			
			while (iteratorParcelamentoQuantidadeReparcelamentoHelper.hasNext()) {
				ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = 
						(ParcelamentoQuantidadeReparcelamentoHelper) iteratorParcelamentoQuantidadeReparcelamentoHelper
						.next();
				long valorTempo = parcelamentoQuantidadeReparcelamentoHelper.getUltimaAlteracao()
						.getTime();
				vlMinPrest = (String) httpServletRequest.getParameter("vlMinPrest"
						+ valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoQuantidadeReparcelamentoHelper
				BigDecimal valorMinimoPrestacao  = null;
				if (vlMinPrest != null 
						&& !vlMinPrest.equals("")) {
					valorMinimoPrestacao = Util.formatarMoedaRealparaBigDecimal(vlMinPrest);
				}
				
					parcelamentoQuantidadeReparcelamentoHelper.setValorMinimoPrestacao(valorMinimoPrestacao);
			}
		}	*/
	}
    
}

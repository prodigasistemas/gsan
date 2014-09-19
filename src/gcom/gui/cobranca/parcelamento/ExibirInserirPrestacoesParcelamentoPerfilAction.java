package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

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
 * Action que define o pré-processamento da página de 
 * inserir Informações do Parcelamento por Quantidade de Reparcelamentos
 * 
 * @author Vivianne Sousa
 * @created 03/05/2006
 */

public class ExibirInserirPrestacoesParcelamentoPerfilAction  extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclusão de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 03/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirPrestacoesParcelamentoPerfil");
        InserirPrestacoesParcelamentoPerfilActionForm inserirPrestacoesParcelamentoPerfilActionForm = (InserirPrestacoesParcelamentoPerfilActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
        sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
        
        String quantidadeReparcelamento = (String)httpServletRequest.getParameter("qtdeMaximaReparcelamento");
        String percentualEntradaSugerida = (String)httpServletRequest.getParameter("percentualEntradaSugerida");
        
        String readOnly = (String) httpServletRequest.getParameter("readOnly");
        httpServletRequest.setAttribute("readOnly",readOnly);
        
        Boolean percentualValorReparceladoReadOnly = false;
        
        //filtro para descobrir o percentual mínimo de entrada permitido para financiamento
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection colecaoSistemaParametros;

			colecaoSistemaParametros = fachada.pesquisar(
					filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();
		String percentualFinanciamentoEntradaMinima = "" + sistemaParametro.getPercentualFinanciamentoEntradaMinima();
		httpServletRequest.setAttribute("percentualFinanciamentoEntradaMinima",percentualFinanciamentoEntradaMinima);
                
		
        if (httpServletRequest.getParameter("adicionarPrestacao") != null
                && httpServletRequest.getParameter("adicionarPrestacao").equalsIgnoreCase("S")
                && inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao() != null) {
        	
        	// --------------------  bt adicionarPrestacao ----------------------
    	
        	atualizaColecaoParcelamentoQuantidadePrestacaoNaSessao(sessao,httpServletRequest);
        	
        	atualizaColecaoParcelamentoFaixaValorNaSessao( sessao, httpServletRequest);
        	
        	adicionarParcelamentoQuantidadePrestacao ( sessao, fachada,
        			inserirPrestacoesParcelamentoPerfilActionForm,percentualValorReparceladoReadOnly);        	        	
        	
        }else {
        	 if (quantidadeReparcelamento != null && !quantidadeReparcelamento.equals("")
             		&& httpServletRequest.getParameter("primeiraVez").equals("S")){
             	
             	atualizaColecoesNaSessao(sessao,httpServletRequest);

             	if (httpServletRequest.getParameter("adicionarReparcelamento") != null &&
             			httpServletRequest.getParameter("adicionarReparcelamento").equals("S")	){
             		adicionarParcelamentoQuantidadeReparcelamentoHelper(
             				quantidadeReparcelamento, sessao, percentualEntradaSugerida);
             	}
             	atualizaFormNaSessao(sessao,httpServletRequest);
             	
             	//qd entra na tela pela primeira vez
             	limparTela(sessao,inserirPrestacoesParcelamentoPerfilActionForm);
             	
             	sessao.setAttribute("qtdeMaxReparcelamento",quantidadeReparcelamento);
             	
             	 if (quantidadeReparcelamento!= null && quantidadeReparcelamento.equals("0")){
                	//só liberar Percentual Valor Reparcelado para informação 
                 //caso a qtde de reparcelamentos consecutivos seja maior que zero(0)
                 	percentualValorReparceladoReadOnly = true;
                 	sessao.setAttribute("quantidadeReparcelamento",quantidadeReparcelamento);
             	 }
                 sessao.setAttribute("percentualValorReparceladoReadOnly",percentualValorReparceladoReadOnly);
//                 httpServletRequest.setAttribute("percentualValorReparceladoReadOnly","" + percentualValorReparceladoReadOnly);
                 
             	
     	    }
        	 
        	 Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
             .getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
         
 			Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
 			
 			while (iterator.hasNext()) {
 				ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = 
 					(ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();
 			
 				if (parcelamentoQuantidadeReparcelamentoHelper.getQuantidadeMaximaReparcelamento().toString()
 						.equals((String)sessao.getAttribute("qtdeMaxReparcelamento"))) {
 					
 					Collection collectionParcelamentoQuantidadePrestacaoHelper = null;
 					
 					if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null){
 						//volta do RemoverParcelamentoQuantidadePrestacaoAction 
 						collectionParcelamentoQuantidadePrestacaoHelper = (Collection)sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
 					}else{
 						//chamado a partir da tela do inserir ou atualizar Perfil de Parcelamento
 						collectionParcelamentoQuantidadePrestacaoHelper = parcelamentoQuantidadeReparcelamentoHelper.getCollectionParcelamentoQuantidadePrestacaoHelper();
 					}
 					 						
 					//parcelamentoQuantidadeReparcelamentoHelper.getCollectionParcelamentoQuantidadePrestacao();

 					if (collectionParcelamentoQuantidadePrestacaoHelper == null || collectionParcelamentoQuantidadePrestacaoHelper.isEmpty()) {
 						parcelamentoQuantidadeReparcelamentoHelper.
 						setInformacaoParcelamentoQtdeReparcelamento("NÃO INFORMADAS");
 					}
 					
 					httpServletRequest.setAttribute("collectionParcelamentoQuantidadePrestacaoHelper",collectionParcelamentoQuantidadePrestacaoHelper);
 					sessao.setAttribute("collectionParcelamentoQuantidadePrestacaoHelper",collectionParcelamentoQuantidadePrestacaoHelper);
 				}				
 			}
 

        }
        	
               
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")){
        	// limpar tela         	
        	limparTela(sessao,inserirPrestacoesParcelamentoPerfilActionForm);
        }
        
        if (httpServletRequest.getParameter("fechar") != null
                && httpServletRequest.getParameter("fechar").equalsIgnoreCase("S")){
        	//antes de fechar a tela 
        	//atualiza a colecão collectionParcelamentoQuantidadePrestacaoHelper na sessao 
         	atualizaColecaoParcelamentoQuantidadePrestacaoNaSessao(sessao,httpServletRequest);    
         	if (sessao.getAttribute("UseCase")!= null &&
	    			sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
				httpServletRequest.setAttribute("reloadPage","FECHARINSERIR");
	    	}else{
	    		httpServletRequest.setAttribute("reloadPage","FECHARATUALIZAR");
	    	}
        }
        
        httpServletRequest.getAttribute("reloadPage");
        return retorno;
    }
    
    private void limparTela(HttpSession sessao,
    		InserirPrestacoesParcelamentoPerfilActionForm inserirPrestacoesParcelamentoPerfilActionForm){
    	
    	inserirPrestacoesParcelamentoPerfilActionForm.setPercentualMinimoEntrada("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setPercentualTarifaMinimaImovel("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setQuantidadeMaximaPrestacao("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setTaxaJuros("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setPercentualValorReparcelado("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setQuantidadeMaxPrestacaoEspecial("");
    	
    	inserirPrestacoesParcelamentoPerfilActionForm.setFatorQuantidadePrestacoes("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorMediaValorContas(ConstantesSistema.NAO.toString());
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorValorUltimaContaEmAtraso(ConstantesSistema.NAO.toString());
    	
    	sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
    	sessao.removeAttribute("collectionParcelamentoFaixaValor");
    }
    
    private void adicionarParcelamentoQuantidadePrestacao (HttpSession sessao,Fachada fachada,
    		InserirPrestacoesParcelamentoPerfilActionForm inserirPrestacoesParcelamentoPerfilActionForm,
    		Boolean percentualValorReparceladoReadOnly){

    	if (inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao() == null ||
    			inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Quantidade Máxima de Prestações
					"atencao.required", null, " Quantidade Máxima de Prestações");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao())){
			throw new ActionServletException(
					// Quantidade Máxima de Prestações deve ser numerico
					"atencao.integer", null, " Quantidade Máxima de Prestações");
		 }
    	
    	Collection<ParcelamentoQuantidadePrestacaoHelper> collectionParcelamentoQuantidadePrestacaoHelper = null;
        
        if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null) {
        	collectionParcelamentoQuantidadePrestacaoHelper = (Collection) sessao
                    .getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
        } else {
        	collectionParcelamentoQuantidadePrestacaoHelper = new ArrayList();
        }
    	
		Short qtdeMaximaPrestacao = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaximaPrestacao());
		Short qtdeMaxPrestacaoPermissaoEspecial = null;
		
		if (inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaxPrestacaoEspecial() != null && 
				!inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaxPrestacaoEspecial().equals("")){
			qtdeMaxPrestacaoPermissaoEspecial = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getQuantidadeMaxPrestacaoEspecial());
		}
		
		Integer fatorQuantidadePrestacoes = null;
//	    if (inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes() == null
//	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes().equalsIgnoreCase("")){
	    	//Informe Fator Cálculo Qtd. Prestações Parc.
//	    	throw new ActionServletException(					
//					"atencao.required", null, "  Fator Cálculo Qtd. Prestações Parc.");	
//	    }else{	
	    	 if (inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes() != null
	    		    	&& !inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes().equalsIgnoreCase("")){
	    	
	    	fatorQuantidadePrestacoes = new Integer(inserirPrestacoesParcelamentoPerfilActionForm.getFatorQuantidadePrestacoes());
		}
	    
		BigDecimal taxaJuros = null;
	    if (inserirPrestacoesParcelamentoPerfilActionForm.getTaxaJuros()== null
	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getTaxaJuros().equalsIgnoreCase("")){
	    	//Informe Taxa de Juros a.m.
	    	throw new ActionServletException(					
					"atencao.required", null, "  Taxa de Juros a.m.");	
	    }else{	
			taxaJuros = Util.formatarMoedaRealparaBigDecimal(inserirPrestacoesParcelamentoPerfilActionForm.getTaxaJuros());
		}
	    
	    Short indicadorMediaValorContas = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getIndicadorMediaValorContas());
	    Short indicadorValorUltimaContaEmAtraso = new Short(inserirPrestacoesParcelamentoPerfilActionForm.getIndicadorValorUltimaContaEmAtraso());
	    
	    BigDecimal percentualMinimoEntrada = null;
	    BigDecimal percentualTarifaMinimaImovel = null;
	    BigDecimal percentualValorReparcelado = null;
	    
	    if ((inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada()== null
	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada().equalsIgnoreCase("")
	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada().equals(0))
	    	&&(inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel()== null
	    	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel().equalsIgnoreCase("")
	    	    	|| inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel().equals(0))){
	    	
		    percentualMinimoEntrada = new BigDecimal(0);
		    percentualTarifaMinimaImovel = new BigDecimal(0);

	    }else{
	    	
	    	 if (inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada()!= null
	    		    	&& !inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada().equalsIgnoreCase("")){
	    		 
	    		 percentualMinimoEntrada = Util.formatarMoedaRealparaBigDecimal
	    		 		(inserirPrestacoesParcelamentoPerfilActionForm.getPercentualMinimoEntrada());
	    		 
	    	 }else if (inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel()!= null
		    	    	&& !inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel().equalsIgnoreCase("")){
	    		 
	    		 percentualTarifaMinimaImovel = Util.formatarMoedaRealparaBigDecimal
	    		 		(inserirPrestacoesParcelamentoPerfilActionForm.getPercentualTarifaMinimaImovel());
	    	 }
			
		}
		
	   if (inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado() == null ||
		   inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado().equals("") ||
		   inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado().equals("0,00")){
		   percentualValorReparcelado = new BigDecimal(0);
	   }else{
			percentualValorReparcelado =Util.formatarMoedaRealparaBigDecimal(
					inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado());
	   }
	   
	    // vivi !!!!!!! aceita zero???? 
	    // insere como null ou zero??
//		if (inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado() != null
//				&& !inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado().equals("")
//				&& !inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado().equals(0)){
//			percentualValorReparcelado =Util.formatarMoedaRealparaBigDecimal(
//					inserirPrestacoesParcelamentoPerfilActionForm.getPercentualValorReparcelado());
//		}
	    
	    ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacaoNovo = 
										new ParcelamentoQuantidadePrestacao();
		 
		if (collectionParcelamentoQuantidadePrestacaoHelper != null && !collectionParcelamentoQuantidadePrestacaoHelper.isEmpty()){
			// se coleção não estiver vazia
			// verificar se a qtd máxima de prestações do parcelamento ja foi informada	
			ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoAntigoHelper = null;
			Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper.iterator();
		
			while (iterator.hasNext()) {
				parcelamentoQuantidadePrestacaoAntigoHelper = (ParcelamentoQuantidadePrestacaoHelper) iterator.next();
				
				if (qtdeMaximaPrestacao.equals
						(parcelamentoQuantidadePrestacaoAntigoHelper.getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao())){
					//Quantidade Máxima de Prestações já informada
					throw new ActionServletException(
					"atencao.qtde_maxima_prestacoes_ja_informado");
				}
			}
		}
		
		parcelamentoQuantidadePrestacaoNovo.setQuantidadeMaximaPrestacao(qtdeMaximaPrestacao);
		parcelamentoQuantidadePrestacaoNovo.setQuantidadeMaxPrestacaoEspecial(qtdeMaxPrestacaoPermissaoEspecial);
		parcelamentoQuantidadePrestacaoNovo.setTaxaJuros(taxaJuros);
		parcelamentoQuantidadePrestacaoNovo.setPercentualMinimoEntrada(percentualMinimoEntrada);
		parcelamentoQuantidadePrestacaoNovo.setPercentualTarifaMinimaImovel(percentualTarifaMinimaImovel);
		parcelamentoQuantidadePrestacaoNovo.setPercentualValorReparcelado(percentualValorReparcelado);
		parcelamentoQuantidadePrestacaoNovo.setUltimaAlteracao(new Date());
		
		parcelamentoQuantidadePrestacaoNovo.setFatorQuantidadePrestacoes(fatorQuantidadePrestacoes);
		parcelamentoQuantidadePrestacaoNovo.setIndicadorMediaValorContas(indicadorMediaValorContas);
		parcelamentoQuantidadePrestacaoNovo.setIndicadorValorUltimaContaEmAtraso(indicadorValorUltimaContaEmAtraso);
		
		ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelperNovo =
			new ParcelamentoQuantidadePrestacaoHelper();
		
		parcelamentoQuantidadePrestacaoHelperNovo.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacaoNovo);
		
		
		if (sessao.getAttribute("collectionParcelamentoFaixaValor")!= null){
			Collection collectionParcelamentoFaixaValor = (Collection) sessao.getAttribute("collectionParcelamentoFaixaValor");
			parcelamentoQuantidadePrestacaoHelperNovo.setCollectionParcelamentoFaixaValor(collectionParcelamentoFaixaValor);
			sessao.removeAttribute("collectionParcelamentoFaixaValor");
		}
		
		collectionParcelamentoQuantidadePrestacaoHelper.add(parcelamentoQuantidadePrestacaoHelperNovo);

		
		
		
		inserirPrestacoesParcelamentoPerfilActionForm.setQuantidadeMaximaPrestacao("");
		inserirPrestacoesParcelamentoPerfilActionForm.setQuantidadeMaxPrestacaoEspecial("");
		inserirPrestacoesParcelamentoPerfilActionForm.setTaxaJuros("");
		inserirPrestacoesParcelamentoPerfilActionForm.setPercentualMinimoEntrada("");
		inserirPrestacoesParcelamentoPerfilActionForm.setPercentualTarifaMinimaImovel("");
		inserirPrestacoesParcelamentoPerfilActionForm.setPercentualValorReparcelado("");
		
		inserirPrestacoesParcelamentoPerfilActionForm.setFatorQuantidadePrestacoes("");
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorMediaValorContas(ConstantesSistema.NAO.toString());
    	inserirPrestacoesParcelamentoPerfilActionForm.setIndicadorValorUltimaContaEmAtraso(ConstantesSistema.NAO.toString());
    	
		//Ordena a coleção pela Qtde. Máxima Meses de Inatividade da Lig. de Água
		Collections.sort((List) collectionParcelamentoQuantidadePrestacaoHelper, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoQuantidadePrestacaoHelper) a).getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao().toString()) ;
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoQuantidadePrestacaoHelper) b).getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
				
		 //manda para a sessão a coleção de ParcelamentoQuantidadePrestacao
        sessao.setAttribute("collectionParcelamentoQuantidadePrestacaoHelper", collectionParcelamentoQuantidadePrestacaoHelper);

    }
    
    private void atualizaColecaoParcelamentoQuantidadePrestacaoNaSessao(HttpSession sessao,
		HttpServletRequest httpServletRequest){

		//collectionParcelamentoQuantidadePrestacaoHelper
		if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null
				&& !sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper").equals(
						"")) {
	
			Collection collectionParcelamentoQuantidadePrestacaoHelper = (Collection) sessao
					.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoQuantidadePrestacao
			String txJuros = null;
			String percMinEntrada = null;
			String percTarMinImovel = null;
			String percVlReparcelado = null;
			
			String fatCalculo = null;
			String indMediaConta = null;
			String indValorUltConta = null;
			
			Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper
					.iterator();
			
			while (iterator.hasNext()) {
				ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = 
						(ParcelamentoQuantidadePrestacaoHelper) iterator.next();
				
				ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao = 
					parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
				
				long valorTempo = parcelamentoQuantidadePrestacao.getUltimaAlteracao()
						.getTime();
				
				txJuros = (String) httpServletRequest.getParameter("txJuros" + valorTempo);
				percMinEntrada = (String) httpServletRequest.getParameter("percMinEntrada" + valorTempo);
				percTarMinImovel = (String)httpServletRequest.getParameter("percTarMinImovel" + valorTempo);
				percVlReparcelado = (String)httpServletRequest.getParameter("percVlReparcelado" + valorTempo);
				
				fatCalculo = (String) httpServletRequest.getParameter("fatQtdPrest" + valorTempo);
				indMediaConta = (String) httpServletRequest.getParameter("indMedVlCnta" + valorTempo);
				indValorUltConta = (String) httpServletRequest.getParameter("indValorUltConta" + valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoQuantidadePrestacao
				BigDecimal taxaJuros  = null;
				if (txJuros != null 
						&& !txJuros.equals("")) {
					taxaJuros = Util.formatarMoedaRealparaBigDecimal(txJuros);
				}
				
				Integer fatorCalculo  = null;
				if (fatCalculo != null && !fatCalculo.equals("")) {
					fatorCalculo = new Integer(fatCalculo);
				}
				
				Short indicadorMediaConta  = ConstantesSistema.NAO;
				if (indMediaConta != null && !indMediaConta.equals("")) {
					indicadorMediaConta = new Short(indMediaConta);
				}
				
				Short indicadorValorUltConta  = ConstantesSistema.NAO;
				if (indValorUltConta!= null && !indValorUltConta.equals("")) {
					indicadorValorUltConta = new Short(indValorUltConta);
				}
				
				
				BigDecimal percentualMinimoEntrada  = null;
				BigDecimal percentualTarifaMinimaImovel = null;
				BigDecimal percentualValorReparcelado = null;	

				if ((percMinEntrada == null || percMinEntrada.equals("") || percMinEntrada.equals("0,00"))
				    		&& (percTarMinImovel == null || percTarMinImovel.equals("") || percTarMinImovel.equals("0,00"))){
				    	 percentualMinimoEntrada = new BigDecimal(0);
						 percentualTarifaMinimaImovel = new BigDecimal(0);
			    }else{
			    	
			    	if (percMinEntrada != null && !percMinEntrada.equals("")){
			    		percentualMinimoEntrada = Util.formatarMoedaRealparaBigDecimal(percMinEntrada);
			    	}else if (percTarMinImovel != null && !percTarMinImovel.equals("")){
			    		percentualTarifaMinimaImovel = Util.formatarMoedaRealparaBigDecimal(percTarMinImovel);
			    	}
			   }
				
			   if (percVlReparcelado == null || percVlReparcelado.equals("") || percVlReparcelado.equals("0,00")){
				    percentualValorReparcelado = new BigDecimal(0);
			   }else{
					percentualValorReparcelado =Util.formatarMoedaRealparaBigDecimal(percVlReparcelado);
			   }
				
				parcelamentoQuantidadePrestacao.setTaxaJuros(taxaJuros);
				parcelamentoQuantidadePrestacao.setPercentualMinimoEntrada(percentualMinimoEntrada);
				parcelamentoQuantidadePrestacao.setPercentualTarifaMinimaImovel(percentualTarifaMinimaImovel);
				parcelamentoQuantidadePrestacao.setPercentualValorReparcelado(percentualValorReparcelado);
				
				parcelamentoQuantidadePrestacao.setFatorQuantidadePrestacoes(fatorCalculo);
				parcelamentoQuantidadePrestacao.setIndicadorMediaValorContas(indicadorMediaConta);
				parcelamentoQuantidadePrestacao.setIndicadorValorUltimaContaEmAtraso(indicadorValorUltConta);
				
				parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
			}
	    }	
    }

    private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	     	
		// collectionParcelamentoDescontoAntiguidade
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null
		&& !sessao.getAttribute("collectionParcelamentoDescontoAntiguidade").equals(
			"")) {
		
			Collection collectionParcelamentoDescontoAntiguidade = (Collection) sessao
			.getAttribute("collectionParcelamentoDescontoAntiguidade");
			// cria as variáveis para recuperar os parâmetros do request e jogar
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
					
				// inseri essas variáveis no objeto ParcelamentoDescontoAntiguidade
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
		&& !sessao.getAttribute("collectionParcelamentoDescontoInatividade").equals(
			"")) {
		
			Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
			.getAttribute("collectionParcelamentoDescontoInatividade");
			// cria as variáveis para recuperar os parâmetros do request e jogar
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
				
				// insere essas variáveis no objeto ParcelamentoDescontoInatividade
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
			// cria as variáveis para recuperar os parâmetros do request e jogar
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
				
				// insere essas variáveis no objeto ParcelamentoDescontoInatividade
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


		
    }

    private void atualizaFormNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	
    	if(sessao.getAttribute("UseCase").equals("INSERIRPERFIL") ){
    		ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm)sessao.getAttribute("ParcelamentoPerfilActionForm");	  		
    		
    		parcelamentoPerfilActionForm.setResolucaoDiretoria(httpServletRequest.getParameter("resolucaoDiretoria"));
    		parcelamentoPerfilActionForm.setImovelSituacaoTipo(httpServletRequest.getParameter("imovelSituacaoTipo"));
    		parcelamentoPerfilActionForm.setImovelPerfil(httpServletRequest.getParameter("imovelPerfil"));
    		parcelamentoPerfilActionForm.setSubcategoria(httpServletRequest.getParameter("subcategoria"));
    		parcelamentoPerfilActionForm.setCategoria(httpServletRequest.getParameter("categoria"));
    		
    		/*
    		 *
    		 */
    		parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoMulta(httpServletRequest.getParameter("percentualDescontoAcrescimoMulta"));
    		parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoJurosMora(httpServletRequest.getParameter("percentualDescontoAcrescimoJurosMora"));
    		parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoAtualizacaoMonetaria(httpServletRequest.getParameter("percentualDescontoAcrescimoAtualizacaoMonetaria"));
    		// fim
    		parcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao(httpServletRequest.getParameter("percentualTarifaMinimaPrestacao"));
    		parcelamentoPerfilActionForm.setConsumoMinimo(httpServletRequest.getParameter("consumoMinimo"));
    		parcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio(httpServletRequest.getParameter("percentualVariacaoConsumoMedio"));
    		parcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido(httpServletRequest.getParameter("indicadorParcelarChequeDevolvido"));
    		parcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta(httpServletRequest.getParameter("indicadorParcelarSancoesMaisDeUmaConta"));
    		
    		parcelamentoPerfilActionForm.setNumeroConsumoEconomia(httpServletRequest.getParameter("numeroConsumoEconomia"));
			parcelamentoPerfilActionForm.setNumeroAreaConstruida(httpServletRequest.getParameter("numeroAreaConstruida"));    
			parcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial(httpServletRequest.getParameter("indicadorRetroativoTarifaSocial"));
			parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior(httpServletRequest.getParameter("anoMesReferenciaLimiteInferior"));
			parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior(httpServletRequest.getParameter("anoMesReferenciaLimiteSuperior"));
			parcelamentoPerfilActionForm.setPercentualDescontoTarifaSocial(httpServletRequest.getParameter("percentualDescontoTarifaSocial"));
			parcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura(httpServletRequest.getParameter("parcelaQuantidadeMinimaFatura"));
			parcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima(httpServletRequest.getParameter("indicadorAlertaParcelaMinima"));
			parcelamentoPerfilActionForm.setPercentualDescontoSancao(httpServletRequest.getParameter("percentualDescontoSancao"));
			parcelamentoPerfilActionForm.setQuantidadeEconomias(httpServletRequest.getParameter("quantidadeEconomias"));
			parcelamentoPerfilActionForm.setCapacidadeHidrometro(httpServletRequest.getParameter("capacidadeHidrometro")); 
			parcelamentoPerfilActionForm.setIndicadorEntradaMinima(httpServletRequest.getParameter("indicadorEntradaMinima"));
			parcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento(httpServletRequest.getParameter("quantidadeMaximaReparcelamento"));
			parcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista(httpServletRequest.getParameter("dataLimiteDescontoPagamentoAVista"));
			
			parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoAcrescimoPagamentoAVista"));
			parcelamentoPerfilActionForm.setIdContaMotivoRevisao(httpServletRequest.getParameter("idContaMotivoRevisao"));
    		    		
    		sessao.setAttribute("ParcelamentoPerfilActionForm",parcelamentoPerfilActionForm);
    		
    	}else if(sessao.getAttribute("UseCase").equals("ATUALIZARPERFIL")){
    		AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm = (AtualizarParcelamentoPerfilActionForm)sessao.getAttribute("AtualizarParcelamentoPerfilActionForm");
    		
    		/*
    		 *
    		 */
    		atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoMulta(httpServletRequest.getParameter("percentualDescontoAcrescimoMulta"));
    		atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoJurosMora(httpServletRequest.getParameter("percentualDescontoAcrescimoJurosMora"));
    		atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoAtualizacaoMonetaria(httpServletRequest.getParameter("percentualDescontoAcrescimoAtualizacaoMonetaria"));
    		// fim
    		atualizarParcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao(httpServletRequest.getParameter("percentualTarifaMinimaPrestacao"));
    		atualizarParcelamentoPerfilActionForm.setConsumoMinimo(httpServletRequest.getParameter("consumoMinimo"));
    		atualizarParcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio(httpServletRequest.getParameter("percentualVariacaoConsumoMedio"));
    		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido(httpServletRequest.getParameter("indicadorParcelarChequeDevolvido"));
    		atualizarParcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta(httpServletRequest.getParameter("indicadorParcelarSancoesMaisDeUmaConta"));
    	
    		atualizarParcelamentoPerfilActionForm.setNumeroConsumoEconomia(httpServletRequest.getParameter("numeroConsumoEconomia"));
			atualizarParcelamentoPerfilActionForm.setNumeroAreaConstruida(httpServletRequest.getParameter("numeroAreaConstruida"));    
			atualizarParcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial(httpServletRequest.getParameter("indicadorRetroativoTarifaSocial"));
			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior(httpServletRequest.getParameter("anoMesReferenciaLimiteInferior"));
			atualizarParcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior(httpServletRequest.getParameter("anoMesReferenciaLimiteSuperior"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoTarifaSocial(httpServletRequest.getParameter("percentualDescontoTarifaSocial"));
			atualizarParcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura(httpServletRequest.getParameter("parcelaQuantidadeMinimaFatura"));
			atualizarParcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima(httpServletRequest.getParameter("indicadorAlertaParcelaMinima"));
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoSancao(httpServletRequest.getParameter("percentualDescontoSancao"));
			atualizarParcelamentoPerfilActionForm.setQuantidadeEconomias(httpServletRequest.getParameter("quantidadeEconomias"));
			atualizarParcelamentoPerfilActionForm.setCapacidadeHidrometro(httpServletRequest.getParameter("capacidadeHidrometro")); 
			atualizarParcelamentoPerfilActionForm.setIndicadorEntradaMinima(httpServletRequest.getParameter("indicadorEntradaMinima"));
			atualizarParcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento(httpServletRequest.getParameter("quantidadeMaximaReparcelamento"));
			atualizarParcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista(httpServletRequest.getParameter("dataLimiteDescontoPagamentoAVista"));
			
			atualizarParcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista(httpServletRequest.getParameter("percentualDescontoAcrescimoPagamentoAVista"));
			atualizarParcelamentoPerfilActionForm.setIdContaMotivoRevisao(httpServletRequest.getParameter("idContaMotivoRevisao"));
			
			sessao.setAttribute("AtualizarParcelamentoPerfilActionForm",atualizarParcelamentoPerfilActionForm);
    	}
    	
    }
    
    
    private void adicionarParcelamentoQuantidadeReparcelamentoHelper(
    		String quantidadeReparcelamento,
			HttpSession sessao,
			String percentualEntradaSugerida){

		if (quantidadeReparcelamento== null || quantidadeReparcelamento.equalsIgnoreCase("")){
			//Informe Qtde. Máxima Reparcelamentos Consecutivos
			throw new ActionServletException("atencao.required", null, " Qtde. Máxima Reparcelamentos Consecutivos");	
		}else if( Util.validarValorNaoNumericoComoBigDecimal(quantidadeReparcelamento)){
			//Valor Mínimo da Prestação deve ser numerico
			throw new ActionServletException("atencao.integer", null, "  Qtde. Máxima Reparcelamentos Consecutivos");
		}
		
		Collection<ParcelamentoQuantidadeReparcelamentoHelper> collectionParcelamentoQuantidadeReparcelamentoHelper = null;
		
		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null) {
		collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
		} else {
		collectionParcelamentoQuantidadeReparcelamentoHelper = new ArrayList();
		}
		
		Short qtdeReparcelamentoShort = new Short(quantidadeReparcelamento);
		
		BigDecimal percentualEntradaSugeridaBigDecimal = null;
		if (percentualEntradaSugerida != null &&
				!percentualEntradaSugerida.equals("")){
			
			percentualEntradaSugeridaBigDecimal = Util.formatarMoedaRealparaBigDecimal(percentualEntradaSugerida);
			
			 //[FS0010]-Verificar Percentual Máximo
		     verificarPercentualMaximo(percentualEntradaSugeridaBigDecimal);

		}
		
		ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQtdeReparcelamentoHelperNovo = 
								new ParcelamentoQuantidadeReparcelamentoHelper();
		
		if (collectionParcelamentoQuantidadeReparcelamentoHelper != null && !collectionParcelamentoQuantidadeReparcelamentoHelper.isEmpty()){
			// se coleção não estiver vazia
			// verificar se a qtd máxima de prestações do parcelamento ja foi informada	
			ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQtdeReparcelamentoHelperAntigo = null;
			Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
			
			while (iterator.hasNext()) {
			parcelamentoQtdeReparcelamentoHelperAntigo = (ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();
			
				//[FS0003] Verificar quantidade máxima de reparcelamentos consecutivos 
				if (qtdeReparcelamentoShort.equals(parcelamentoQtdeReparcelamentoHelperAntigo.getQuantidadeMaximaReparcelamento())){
					//Quantidade Máxima de Reparcelamentos Consecutivos já informada
					throw new ActionServletException("atencao.qtde_maxima_reparcelamento_ja_informado");
				}
			}
		}			
		
		parcelamentoQtdeReparcelamentoHelperNovo.setQuantidadeMaximaReparcelamento(qtdeReparcelamentoShort);
		parcelamentoQtdeReparcelamentoHelperNovo.setPercentualEntradaSugerida(percentualEntradaSugeridaBigDecimal);
		parcelamentoQtdeReparcelamentoHelperNovo.setInformacaoParcelamentoQtdeReparcelamento("NÃO INFORMADA");
		parcelamentoQtdeReparcelamentoHelperNovo.setUltimaAlteracao(new Date());
		collectionParcelamentoQuantidadeReparcelamentoHelper.add(parcelamentoQtdeReparcelamentoHelperNovo);
		
		//Ordena a coleção pela qunatidade de reparcelamento
		Collections.sort((List) collectionParcelamentoQuantidadeReparcelamentoHelper, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoQuantidadeReparcelamentoHelper) a).getQuantidadeMaximaReparcelamento().toString()) ;
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoQuantidadeReparcelamentoHelper) b).getQuantidadeMaximaReparcelamento().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		
		//manda para a sessão a coleção de ParcelamentoQuantidadeReparcelamentoHelper
		sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper", collectionParcelamentoQuantidadeReparcelamentoHelper);
		}
    
    
    private void atualizaColecaoParcelamentoFaixaValorNaSessao(HttpSession sessao,
    		HttpServletRequest httpServletRequest){

    	if(sessao.getAttribute("collectionParcelamentoFaixaValor") != null
				&& !sessao.getAttribute("collectionParcelamentoFaixaValor").equals(
				"")){
			
			Collection collectionParcelamentoFaixaValor = (Collection) sessao
			.getAttribute("collectionParcelamentoFaixaValor");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoFaixaValor
			String perc = null;

			Iterator iteratorParcelamentoFaixaValor = collectionParcelamentoFaixaValor
			.iterator();
		
			while (iteratorParcelamentoFaixaValor.hasNext()) {
				ParcelamentoFaixaValor parcelamentoFaixaValor = (ParcelamentoFaixaValor) iteratorParcelamentoFaixaValor
					.next();
				long valorTempo = parcelamentoFaixaValor.getUltimaAlteracao()
					.getTime();
				
				perc = (String) httpServletRequest.getParameter("perc" + valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoFaixaValor
				BigDecimal percentual  = null;
				if (perc != null 
					&& !perc.equals("")) {
					percentual = Util.formatarMoedaRealparaBigDecimal(perc);
				}
			
				parcelamentoFaixaValor.setPercentualFaixa(percentual);
			
			}
			
		}
    }
    //[FS0010]-Verificar Percentual Máximo
    private void verificarPercentualMaximo(BigDecimal percentual){
    	
    	BigDecimal percentualMaximo = new BigDecimal("100");
    	
    	if (percentual.compareTo(percentualMaximo) == 1){
    		throw new ActionServletException(
					"atencao.percentual_maior_percentual_maximo");	
    	}
    	
    }
}

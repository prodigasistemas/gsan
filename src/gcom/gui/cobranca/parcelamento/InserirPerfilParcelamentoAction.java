package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel por inserir um Perfil de Parcelamento
 * 
 * @author Vivianne Sousa
 * @created 02/05/2006
 */

public class InserirPerfilParcelamentoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    //Obt�m a inst�ncia da fachada
    Fachada fachada = Fachada.getInstancia();

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        HttpSession sessao = httpServletRequest.getSession(false);
        ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm)actionForm;
        
        String idResolucaoDiretoria = parcelamentoPerfilActionForm.getResolucaoDiretoria();
        String idImovelSituacaoTipo = parcelamentoPerfilActionForm.getImovelSituacaoTipo();
		
        String idImovelPerfil = null;
        if (parcelamentoPerfilActionForm.getImovelPerfil() != null &&
        		!parcelamentoPerfilActionForm.getImovelPerfil().equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idImovelPerfil = parcelamentoPerfilActionForm.getImovelPerfil();	
        }
        
        String idSubcategoria = null;
        if (parcelamentoPerfilActionForm.getSubcategoria() != null &&
        		!parcelamentoPerfilActionForm.getSubcategoria().equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idSubcategoria = parcelamentoPerfilActionForm.getSubcategoria();
        }
        
        String idCategoria = null;
        if (parcelamentoPerfilActionForm.getCategoria() != null &&
        		!parcelamentoPerfilActionForm.getCategoria().equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idCategoria = parcelamentoPerfilActionForm.getCategoria();
        }
		
        /*
         *
         */
		String percentualDescontoAcrescimoMulta = null ;
		if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoMulta() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoMulta().equalsIgnoreCase("")) {
			percentualDescontoAcrescimoMulta = parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoMulta().toString().replace(",", ".");
		}
		
		String percentualDescontoAcrescimoJurosMora = null ;
		if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoJurosMora() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoJurosMora().equalsIgnoreCase("")) {
			percentualDescontoAcrescimoJurosMora = parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoJurosMora().toString().replace(",", ".");
		}
		
		String percentualDescontoAcrescimoAtualizacaoMonetaria = null ;
		if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoAtualizacaoMonetaria() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoAtualizacaoMonetaria().equalsIgnoreCase("")) {
			percentualDescontoAcrescimoAtualizacaoMonetaria = parcelamentoPerfilActionForm
					.getPercentualDescontoAcrescimoAtualizacaoMonetaria().toString().replace(",", ".");
		}
		// fim altera��o
		
		String percentualDescontoAcrescimoPagamentoAVista = null ;
		if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista() != null
			&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista().equalsIgnoreCase("")){
			percentualDescontoAcrescimoPagamentoAVista = parcelamentoPerfilActionForm
			.getPercentualDescontoAcrescimoPagamentoAVista().toString().replace(",", ".");
		}
		
		String percentualTarifaMinimaPrestacao = null ;
		if (parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao() != null
				&& !parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().equalsIgnoreCase("")){
			percentualTarifaMinimaPrestacao = parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().toString().
			replace(",", ".");
		}	
             
        atualizaColecoesNaSessao(sessao,httpServletRequest);
        
    	//collectionParcelamentoQuantidadeReparcelamentoHelper
		Collection collectionParcelamentoQuantidadeReparcelamentoHelper = null;	       
        if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null) {
        	collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
                    .getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
        }
        
    	//collectionParcelamentoDescontoInatividade
		Collection collectionParcelamentoDescontoInatividade = null;	       
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null) {
        	collectionParcelamentoDescontoInatividade = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividade");
        }
        
    	//collectionParcelamentoDescontoAntiguidade
		Collection collectionParcelamentoDescontoAntiguidade = null;	       
        if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null) {
        	collectionParcelamentoDescontoAntiguidade = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoAntiguidade");
        }
        
        //collectionParcelamentoDescontoInatividade
		Collection collectionParcelamentoDescontoInatividadeAVista = null;	       
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null) {
        	collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividadeAVista");
        }
        
        validacaoFinal( idResolucaoDiretoria,idImovelSituacaoTipo,idImovelPerfil ,
        		idSubcategoria ,
        		percentualDescontoAcrescimoMulta,
        		percentualDescontoAcrescimoJurosMora,
        		percentualDescontoAcrescimoAtualizacaoMonetaria,
        		httpServletRequest,
        		collectionParcelamentoQuantidadeReparcelamentoHelper,
        		collectionParcelamentoDescontoAntiguidade,
			    collectionParcelamentoDescontoInatividade,
			    percentualTarifaMinimaPrestacao,
			    collectionParcelamentoDescontoInatividadeAVista,
			    percentualDescontoAcrescimoPagamentoAVista);
        
        
        ParcelamentoPerfil parcelamentoPerfilNova =  new ParcelamentoPerfil();
        
        ResolucaoDiretoria resolucaoDiretoria = null;
        if(idResolucaoDiretoria != null && !idResolucaoDiretoria.equals("")){
        	resolucaoDiretoria = new ResolucaoDiretoria();
        	resolucaoDiretoria.setId(new Integer (idResolucaoDiretoria));
        	parcelamentoPerfilNova.setResolucaoDiretoria(resolucaoDiretoria);
		}
        
        ImovelSituacaoTipo imovelSituacaoTipo = null;
        if(idImovelSituacaoTipo != null && !idImovelSituacaoTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	imovelSituacaoTipo = new ImovelSituacaoTipo();
        	imovelSituacaoTipo.setId(new Integer (idImovelSituacaoTipo));
        	parcelamentoPerfilNova.setImovelSituacaoTipo(imovelSituacaoTipo);
		}

        ImovelPerfil imovelPerfil = null;
        if(idImovelPerfil != null && !idImovelPerfil.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	imovelPerfil = new ImovelPerfil();
        	imovelPerfil.setId(new Integer (idImovelPerfil));
		}
        parcelamentoPerfilNova.setImovelPerfil(imovelPerfil);
        
        Subcategoria subcategoria = null;
        if(idSubcategoria != null && !idSubcategoria.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	subcategoria = new Subcategoria();
        	subcategoria.setId(new Integer (idSubcategoria));
		}
        parcelamentoPerfilNova.setSubcategoria(subcategoria);
        
        /*
         *
         */
        if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoMulta() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoMulta().equalsIgnoreCase("")){
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimoMulta(new BigDecimal(percentualDescontoAcrescimoMulta));	
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimoMulta(new BigDecimal(0));
        }
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoJurosMora() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoJurosMora().equalsIgnoreCase("")){
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimoJurosMora(new BigDecimal(percentualDescontoAcrescimoJurosMora));	
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimoJurosMora(new BigDecimal(0));
        }
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoAtualizacaoMonetaria() != null
				&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoAtualizacaoMonetaria().equalsIgnoreCase("")){
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimoAtualizacaoMonetaria(new BigDecimal(percentualDescontoAcrescimoAtualizacaoMonetaria));	
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoAcrescimoAtualizacaoMonetaria(new BigDecimal(0));
        }
        // fim altera��o
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista() != null
			&& !parcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista().equalsIgnoreCase("")){
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVista(new BigDecimal(percentualDescontoAcrescimoPagamentoAVista));	
        }else{
        	parcelamentoPerfilNova.setPercentualDescontoPagamentoAVista(new BigDecimal(0));
        }
                
        if (parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao() != null
				&& !parcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().equalsIgnoreCase("")){
        	parcelamentoPerfilNova.setPercentualTarifaMinimaPrestacao(new BigDecimal(percentualTarifaMinimaPrestacao));	
        }else{
        	parcelamentoPerfilNova.setPercentualTarifaMinimaPrestacao(new BigDecimal(0));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido() != null
         		 && !parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido().equals("")){
         	parcelamentoPerfilNova.setIndicadorChequeDevolvido(
         			new Short(parcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido()));
       }else{
   		throw new ActionServletException(
   				"atencao.campo_selecionado.obrigatorio", null, 
   				"N�o parcelar com cheque devolvido");
       }
        
        if (parcelamentoPerfilActionForm.getConsumoMinimo() != null
        		 && !parcelamentoPerfilActionForm.getConsumoMinimo().equals("")){
        	parcelamentoPerfilNova.setNumeroConsumoMinimo(new Integer
        			(parcelamentoPerfilActionForm.getConsumoMinimo()));
        }
        
        
        if (parcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio() != null
       		 && !parcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio().equals("")){
       	parcelamentoPerfilNova.setPercentualVariacaoConsumoMedio(
       			Util.formatarMoedaRealparaBigDecimal(parcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta() != null
          		 && !parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta().equals("")){
          	parcelamentoPerfilNova.setIndicadorSancoesUnicaConta(
          			new Short(parcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta()));
        }else{
    		throw new ActionServletException(
    				"atencao.campo_selecionado.obrigatorio", null, 
    				"N�o parcelar com san��es em mais de uma conta");
        }
        
        
        /////////////////////////////////////////////
        Categoria categoria = null;
        if(idCategoria != null && !idCategoria.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	categoria = new Categoria();
        	categoria.setId(new Integer (idCategoria));
		}
        parcelamentoPerfilNova.setCategoria(categoria);
        
        if (parcelamentoPerfilActionForm.getNumeroConsumoEconomia() != null && 
        		!parcelamentoPerfilActionForm.getNumeroConsumoEconomia().trim().equals("")){
        	parcelamentoPerfilNova.setNumeroConsumoEconomia(
        			new Integer(parcelamentoPerfilActionForm.getNumeroConsumoEconomia()));
        }
        
        if (parcelamentoPerfilActionForm.getNumeroAreaConstruida() != null && 
        		!parcelamentoPerfilActionForm.getNumeroAreaConstruida().trim().equals("")){
        	parcelamentoPerfilNova.setNumeroAreaConstruida(
        			Util.formatarMoedaRealparaBigDecimal(parcelamentoPerfilActionForm.getNumeroAreaConstruida()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial() != null
         		 && !parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial().equals("")){
         	parcelamentoPerfilNova.setIndicadorRetroativoTarifaSocial(
         			new Short(parcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial()));
       }else{
	   		throw new ActionServletException(
	   				"atencao.campo_selecionado.obrigatorio", null, 
	   				"Indicador de retroativo de tarifa social");
       }
        
        if (parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior() != null && 
        		!parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior().trim().equals("")){
        	parcelamentoPerfilNova.setAnoMesReferenciaLimiteInferior(new Integer(
        			Util.formatarMesAnoParaAnoMesSemBarra(
        					parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior())));
        } 
        
        if (parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior() != null && 
        		!parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior().trim().equals("")){
        	parcelamentoPerfilNova.setAnoMesReferenciaLimiteSuperior(new Integer(
        			Util.formatarMesAnoParaAnoMesSemBarra(
        					parcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior())));
        } 
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoTarifaSocial() != null && 
        		!parcelamentoPerfilActionForm.getPercentualDescontoTarifaSocial().trim().equals("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(
        			parcelamentoPerfilActionForm.getPercentualDescontoTarifaSocial());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoTarifaSocial(percentual);
        }
        
        if (parcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura() != null && 
        		!parcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura().trim().equals("")){
        	parcelamentoPerfilNova.setParcelaQuantidadeMinimaFatura(
        			new Integer(parcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima() != null
        		 && !parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima().equals("")){
        	parcelamentoPerfilNova.setIndicadorAlertaParcelaMinima(
        			new Short(parcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima()));
        }else{
	   		throw new ActionServletException(
	   				"atencao.campo_selecionado.obrigatorio", null, 
	   				"Indicador de alerta de parcela m�nima");
        }
        
        if (parcelamentoPerfilActionForm.getPercentualDescontoSancao() != null && 
        		!parcelamentoPerfilActionForm.getPercentualDescontoSancao().trim().equals("")){
        	BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(
        			parcelamentoPerfilActionForm.getPercentualDescontoSancao());
        	verificarPercentualMaximo(percentual);
        	parcelamentoPerfilNova.setPercentualDescontoSancao(percentual);
        }
        
        if (parcelamentoPerfilActionForm.getQuantidadeEconomias()  != null && 
        		!parcelamentoPerfilActionForm.getQuantidadeEconomias().trim().equals("")){
        	parcelamentoPerfilNova.setQuantidadeEconomias(
        			new Integer(parcelamentoPerfilActionForm.getQuantidadeEconomias()));
        }
        
        if (parcelamentoPerfilActionForm.getCapacidadeHidrometro()  != null && 
        		!parcelamentoPerfilActionForm.getCapacidadeHidrometro().trim().equals("")){
        	parcelamentoPerfilNova.setCapacidadeHidrometro(
        			new Short(parcelamentoPerfilActionForm.getCapacidadeHidrometro()));
        }
        
        if (parcelamentoPerfilActionForm.getIndicadorEntradaMinima() != null
       		 && !parcelamentoPerfilActionForm.getIndicadorEntradaMinima().equals("")){
       	parcelamentoPerfilNova.setIndicadorEntradaMinima(
       			new Short(parcelamentoPerfilActionForm.getIndicadorEntradaMinima()));
       }else{
	   		throw new ActionServletException(
	   				"atencao.campo_selecionado.obrigatorio", null, 
	   				"Indicador de entrada m�nima");
       }
        
        if (parcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento()  != null && 
        		!parcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento().trim().equals("")){
        	parcelamentoPerfilNova.setQuantidadeMaximaReparcelamento(
        			new Integer(parcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento()));
        }

        if (parcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()  != null && 
        		!parcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista().trim().equals("")){
        	parcelamentoPerfilNova.setDataLimiteDescontoPagamentoAVista(
        			Util.converteStringParaDate(parcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()));
        }
        
        parcelamentoPerfilNova.setUltimaAlteracao(new Date());
        
        Integer idPerfilParcelamento = fachada.inserirPerfilParcelamento(parcelamentoPerfilNova,
        		collectionParcelamentoQuantidadeReparcelamentoHelper,
        		collectionParcelamentoDescontoInatividade,
        		collectionParcelamentoDescontoAntiguidade,
        		this.getUsuarioLogado(httpServletRequest),
        		collectionParcelamentoDescontoInatividadeAVista);
        
    	sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
    	sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
    	sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
    	sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
    	
    	FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
		filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
				FiltroResolucaoDiretoria.CODIGO,idResolucaoDiretoria));
        Collection<ResolucaoDiretoria> collectionResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
        String numeroResolucaoDiretoria = ((ResolucaoDiretoria)Util.retonarObjetoDeColecao(collectionResolucaoDiretoria)).getNumeroResolucaoDiretoria();
    	
		montarPaginaSucesso(httpServletRequest, "Perfil de Parcelamento da RD de n�mero " + numeroResolucaoDiretoria + " inserido com sucesso.",
				"Inserir outro Perfil de Parcelamento",
                "exibirInserirPerfilParcelamentoAction.do?desfazer=S",
				"exibirAtualizarPerfilParcelamentoAction.do?idRegistroInseridoAtualizar="
						+ idPerfilParcelamento,
				"Atualizar Perfil de Parcelamento Inserido");
        
        //devolve o mapeamento de retorno
        return retorno;

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
			
			
			Iterator iteratorParcelamentoDescontoAntiguidade = 
				collectionParcelamentoDescontoAntiguidade.iterator();
			
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
		&& !sessao.getAttribute("collectionParcelamentoDescontoInatividade").equals(
			"")) {
		
			Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
			.getAttribute("collectionParcelamentoDescontoInatividade");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;
			
			Iterator iteratorParcelamentoDescontoInatividade = 
				collectionParcelamentoDescontoInatividade.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
			
				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = 
				(ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade.next();
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
			
			Iterator iteratorParcelamentoQuantidadeReparcelamentoHelper = 
				collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
			
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

    
    private void validacaoFinal(String numeroResolucaoDiretoria,
			String imovelSituacaoTipo,
			String imovelPerfil ,
			String subcategoria ,
			String percentualDescontoAcrescimoMulta,
			String percentualDescontoAcrescimoJurosMora,
			String percentualDescontoAcrescimoAtualizacaoMonetaria,
		    HttpServletRequest httpServletRequest,
		    Collection collectionParcelamentoQuantidadeReparcelamentoHelper,
		    Collection collectionParcelamentoDescontoAntiguidade,
		    Collection collectionParcelamentoDescontoInatividade,
		    String percentualTarifaMinimaPrestacao,
		    Collection collectionParcelamentoDescontoInatividadeAVista,
		    String percentualDescontoAcrescimoPagamentoAVista){
							    	
    	if ( !Util.verificarNaoVazio(numeroResolucaoDiretoria)){
    		httpServletRequest.setAttribute("nomeCampo","numeroResolucaoDiretoria");
    		// Informe Numero da RD.
    		throw new ActionServletException("atencao.numero_rd_nao_informado");
    	}
    	
		if ((imovelSituacaoTipo == null)
				|| (imovelSituacaoTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","imovelSituacaoTipo");
			//Informe Tipo da Situa��o do Im�vel
			throw new ActionServletException("atencao.tipo_situacao_imovel_nao_informado");
		}
		/*
		if ((imovelPerfil == null)
				|| (imovelPerfil.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","imovelPerfil");
			//Informe Perfil do Im�vel
			throw new ActionServletException("atencao.perfil_imovel_nao_informado");
		}
		
		if ((subcategoria == null)
				|| (subcategoria.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","subcategoria");
			//Informe Subcategoria
			throw new ActionServletException("atencao.subcategoria_nao_informado");
		}
		
    	if (percentualDescontoAcrescimo == null ||
    			percentualDescontoAcrescimo.equalsIgnoreCase("")){
    		httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimo");
    		//Informe Percentual de Desconto sobre os Acr�scimos  por Impontualidade
    		throw new ActionServletException("atencao.percentual_desconto_nao_informado");
    	}
    	*/
		
		/*
		 *
		 */
    	if (percentualDescontoAcrescimoMulta == null ||
    			percentualDescontoAcrescimoMulta.equalsIgnoreCase("")){
    		httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimoMulta");
    		//Informe Percentual de Desconto sobre Multa
    		throw new ActionServletException("atencao.required", null, " Percentual de Desconto sobre Multa");
    	}
    	
    	if (percentualDescontoAcrescimoJurosMora == null ||
    			percentualDescontoAcrescimoJurosMora.equalsIgnoreCase("")){
    		httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimoJurosMora");
    		//Informe Percentual de Desconto sobre Juros Mora
    		throw new ActionServletException("atencao.required", null, " Percentual de Desconto sobre Juros Mora");
    	}
    	
    	if (percentualDescontoAcrescimoAtualizacaoMonetaria == null ||
    			percentualDescontoAcrescimoAtualizacaoMonetaria.equalsIgnoreCase("")){
    		httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimoAtualizacaoMonetaria");
    		//Informe Percentual de Desconto sobre Atualiza��o Monet�ria
    		throw new ActionServletException("atencao.required", null, " Percentual de Desconto sobre Atualiza��o Monet�ria");
    	}
    	
    	if (percentualDescontoAcrescimoPagamentoAVista == null ||
    			percentualDescontoAcrescimoPagamentoAVista.equalsIgnoreCase("")){
    		httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimoPagamentoAVista");
    		//Informe Percentual de Desconto sobre os Acr�scimos por Impontualidade para pagamento � vista
    		throw new ActionServletException("atencao.required", null, " Percentual de Desconto sobre os Acr�scimos por Impontualidade para pagamento � vista");
    	}
    	// fim altera��o
		
		if (percentualTarifaMinimaPrestacao == null || percentualTarifaMinimaPrestacao.equalsIgnoreCase("")){
    		httpServletRequest.setAttribute("nomeCampo","percentualTarifaMinimaPrestacao");
    		//Informe  Percentual da Tarifa M�nima para C�lculo do Valor M�nimo da Presta��o
    		throw new ActionServletException("atencao.required", null, " Percentual da Tarifa M�nima para C�lculo do Valor M�nimo da Presta��o");
    	}
		
    	// [FS0008]Verificar exist�ncia do perfil de parcelamento
    	FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.RESOLUCAO_DIRETORIA_ID, numeroResolucaoDiretoria));
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.IMOVEL_SITUACAO_TIPO_ID, imovelSituacaoTipo));
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
    	
    	if (imovelPerfil == null){
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroNulo(FiltroParcelamentoPerfil.IMOVEL_PERFIL_ID));	
    	}else{
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.IMOVEL_PERFIL_ID, imovelPerfil));
    	}
    
    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
    	if (subcategoria == null){
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroNulo(FiltroParcelamentoPerfil.SUBCATEGORIA_ID));
    	}else{
    		filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.SUBCATEGORIA_ID, subcategoria));
    	}
    	
		Collection	colecaoParcelamentoPerfil = fachada.pesquisar(filtroParcelamentoPerfil,	ParcelamentoPerfil.class.getName());

		if (colecaoParcelamentoPerfil != null && !colecaoParcelamentoPerfil.isEmpty()) {
			throw new ActionServletException("atencao.perfil_parcelamento_ja_existe");
		}
		
		
		if (collectionParcelamentoQuantidadeReparcelamentoHelper == null ||
				collectionParcelamentoQuantidadeReparcelamentoHelper.isEmpty()){
			//Informe os dados de Reparcelamento Consecutivo
			throw new ActionServletException("atencao.required", null, "Reparcelamento Consecutivo");
		}else{
			Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();

			 while (iterator.hasNext()) {
				
				 ParcelamentoQuantidadeReparcelamentoHelper  parcelamentoQuantidadeReparcelamentoHelper = 
					 			(ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();
				/*
				 if (parcelamentoQuantidadeReparcelamentoHelper.getValorMinimoPrestacao() == null){
					 //Informe Valor M�nimo da Presta��o	
					 throw new ActionServletException(
								"atencao.required", null, "Valor M�nimo da Presta��o");	
				 }*/
				 
				Collection collectionParcelamentoQuantidadePrestacaoHelper = 
					parcelamentoQuantidadeReparcelamentoHelper.getCollectionParcelamentoQuantidadePrestacaoHelper();
				 
				if (collectionParcelamentoQuantidadePrestacaoHelper == null ||
						collectionParcelamentoQuantidadePrestacaoHelper.isEmpty()){
					//Informa��es do Parcelamento por Quantidade de Reparcelamentos deve ser informado
					throw new ActionServletException("atencao.campo.informado", null, "Informa��es do Parcelamento por Quantidade de Reparcelamentos");
				}
			 }
		}
		
		//filtro para descobrir o percentual m�ximo de desconto permitido para financiamento
		/*FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection colecaoSistemaParametros;

			colecaoSistemaParametros = fachada.pesquisar(
					filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();
		BigDecimal percentualMaximoAbatimentoPermitido = sistemaParametro.getPercentualMaximoAbatimento();*/
		
		if (collectionParcelamentoDescontoAntiguidade != null &&
				!collectionParcelamentoDescontoAntiguidade.isEmpty()){
			
			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade.iterator();

			 while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {
				
				 ParcelamentoDescontoAntiguidade  parcelamentoDescontoAntiguidade = 
					 (ParcelamentoDescontoAntiguidade) iteratorParcelamentoDescontoAntiguidade.next();
				 
				 if (parcelamentoDescontoAntiguidade.getPercentualDescontoSemRestabelecimento() == null){
					 //Percentual de Desconto Sem Restabelecimento
						throw new ActionServletException("atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
				 }else{
						//[FS0006]Verificar percentual de desconto
						//BigDecimal percentualDescontoSemRestabelecimento = parcelamentoDescontoAntiguidade.getPercentualDescontoSemRestabelecimento();
						/*if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimentoPermitido) > 0 ){
							//Percentual de Desconto Sem Restabelecimento � superior ao 
							//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
							throw new ActionServletException(
							"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
						}	*/
				}
				 
				 if (parcelamentoDescontoAntiguidade.getPercentualDescontoComRestabelecimento() == null){
					 //Informe  Percentual de Desconto Com Restabelecimento
						throw new ActionServletException("atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
				 }else{
						//[FS0006]Verificar percentual de desconto
						//BigDecimal percentualDescontoComRestabelecimento = parcelamentoDescontoAntiguidade.getPercentualDescontoComRestabelecimento();
						/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimentoPermitido) > 0  ){
							//Percentual de Desconto Cem Restabelecimento � superior ao 
							//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
							throw new ActionServletException(
							"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
						}*/	
				}
				 
				 if (parcelamentoDescontoAntiguidade.getPercentualDescontoAtivo() == null){
						throw new ActionServletException(
								// Informe  Percentual de Desconto Ativo
								"atencao.required", null, "  Percentual de Desconto Ativo");	
				 }else{
						//[FS0006]Verificar percentual de desconto
						//BigDecimal percentualDescontoAtivo = parcelamentoDescontoAntiguidade.getPercentualDescontoAtivo();
						/*if (percentualDescontoAtivo.compareTo(percentualMaximoAbatimentoPermitido) > 0  ){
							//Percentual de Desconto Ativo � superior ao 
							//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
							throw new ActionServletException(
							"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
						}	*/
				 }
				
			 }
			
		}

		 if (collectionParcelamentoDescontoInatividade != null &&
					!collectionParcelamentoDescontoInatividade.isEmpty()){
				
				Iterator iteratorParcelamentoDescontoInatividade = 
					collectionParcelamentoDescontoInatividade.iterator();
	
				 while (iteratorParcelamentoDescontoInatividade.hasNext()) {
					
					 ParcelamentoDescontoInatividade  parcelamentoDescontoInatividade = 
						 (ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade.next();
					 
					 if (parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento() == null){
							throw new ActionServletException(
									//  Percentual de Desconto Sem Restabelecimento
									"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
					 }//else{
							//[FS0006]Verificar percentual de desconto
							//BigDecimal percentualDescontoSemRestabelecimento = parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento();
							/*if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimentoPermitido) > 0  ){
								//Percentual de Desconto Sem Restabelecimento � superior ao 
								//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
								throw new ActionServletException(
								"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
							}	*/
					// }
					 
					 if (parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento() == null){
							throw new ActionServletException(
									// Informe  Percentual de Desconto Com Restabelecimento
									"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
					 }//else{
							//[FS0006]Verificar percentual de desconto
							//BigDecimal percentualDescontoComRestabelecimento = parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento();
							/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimentoPermitido) > 0  ){
								//Percentual de Desconto Cem Restabelecimento � superior ao 
								//Percentual M�ximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
								throw new ActionServletException(
								"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimentoPermitido);
							}*/	
					// }
					
				 }
		}
		 
		 if (collectionParcelamentoDescontoInatividadeAVista != null &&
					!collectionParcelamentoDescontoInatividadeAVista.isEmpty()){
				
			Iterator iteratorParcelamentoDescontoInatividade = 
				collectionParcelamentoDescontoInatividadeAVista.iterator();

			 while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				
				 ParcDesctoInativVista  parcelamentoDescontoInatividade = 
					 (ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();
				 
				 if (parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento() == null){
						throw new ActionServletException(
								//  Percentual de Desconto Sem Restabelecimento
								"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
				 }
				 
				 if (parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento() == null){
						throw new ActionServletException(
								// Informe  Percentual de Desconto Com Restabelecimento
								"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
				 }
				
			 }
		}
		 
		 
		
    }
    
    
    
    //[FS0010]-Verificar Percentual M�ximo
    private void verificarPercentualMaximo(BigDecimal percentual){
    	
    	BigDecimal percentualMaximo = new BigDecimal("100");
    	
    	if (percentual.compareTo(percentualMaximo) == 1){
    		throw new ActionServletException(
					"atencao.percentual_maior_percentual_maximo");	
    	}
    	
    }
    

}

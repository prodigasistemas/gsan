package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelSituacaoTipo;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
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
 * Action que define o pré-processamento da página de inserir perfil de parcelamento
 * 
 * @author Vivianne Sousa
 * @created 02/05/2006
 */
public class ExibirInserirPerfilParcelamentoAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclusão de um novo perfil de parcelamento
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
    //Obtém a instância da fachada
    Fachada fachada = Fachada.getInstancia();

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("inserirPerfilParcelamento");
        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm) actionForm;
        
        FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
        
		Collection colecaoSistemaParametros = fachada.pesquisar(
				filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();
		String  percentualMaximoAbatimento = "" + sistemaParametro.getPercentualMaximoAbatimento();
		httpServletRequest.setAttribute("percentualMaximoAbatimento",percentualMaximoAbatimento);
	
        //Pesquisando Resolucao Diretoria
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
		filtroResolucaoDiretoria.setCampoOrderBy(FiltroResolucaoDiretoria.NUMERO);
        Collection<ResolucaoDiretoria> collectionResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
        sessao.setAttribute("collectionResolucaoDiretoria", collectionResolucaoDiretoria);
        //Fim de pesquisando Resolucao Diretoria
        
        //Pesquisando Tipo da Situacao do Imóvel
        FiltroImovelSituacaoTipo filtroImovelSituacaoTipo = new FiltroImovelSituacaoTipo();
        filtroImovelSituacaoTipo.setCampoOrderBy(FiltroImovelSituacaoTipo.DESCRICAO_IMOVEL_SITUACAO_TIPO);
        Collection<ImovelSituacaoTipo> collectionImovelSituacaoTipo = fachada.pesquisar(filtroImovelSituacaoTipo, ImovelSituacaoTipo.class.getName());
        sessao.setAttribute("collectionImovelSituacaoTipo", collectionImovelSituacaoTipo);
        //Fim de pesquisando Tipo da Situacao do Imóvel

        //Pesquisando Perfil do Imóvel
        FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
        filtroImovelPerfil.adicionarParametro(new ParametroSimples(
        		FiltroImovelPerfil.INDICADOR_USO, new Short("1")));
        filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        Collection<ImovelPerfil> collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
        sessao.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
        //Fim de pesquisando Perfil do Imóvel
        
        //Pesquisando SubCategoria
        FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
        filtroSubCategoria.adicionarParametro(new ParametroSimples(
        		FiltroSubCategoria.INDICADOR_USO, new Short("1")));
        filtroSubCategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
        Collection<Subcategoria> collectionSubcategoria = fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
        sessao.setAttribute("collectionSubcategoria", collectionSubcategoria);
        //Fim de pesquisando SubCategoria
        
        //Pesquisando Categoria
        FiltroCategoria filtroCategoria = new FiltroCategoria();
        filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, new Short("1")));
        filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
        Collection<Categoria> collectionCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
        sessao.setAttribute("collectionCategoria", collectionCategoria);
        //Fim de pesquisando Categoria
        
        //Pesquisando ContaMotivoRevisao
        FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
        filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.INDICADOR_USO, 
        ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroContaMotivoRevisao.setCampoOrderBy(FiltroContaMotivoRevisao.DESCRICAO);
        Collection<ContaMotivoRevisao> collectionContaMotivoRevisao = fachada.pesquisar(filtroContaMotivoRevisao, 
        ContaMotivoRevisao.class.getName());
        sessao.setAttribute("collectionContaMotivoRevisao", collectionContaMotivoRevisao);
        //Fim de pesquisando ContaMotivoRevisao
        
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")
        ) {
        	
        	//-------------- bt DESFAZER ---------------
        	
        	parcelamentoPerfilActionForm.setResolucaoDiretoria("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setImovelSituacaoTipo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setSubcategoria("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setImovelPerfil("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoMulta("");
        	parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoJurosMora("");
        	parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoAtualizacaoMonetaria("");
        	parcelamentoPerfilActionForm.setPercentualDescontoAcrescimoPagamentoAVista("");
        	parcelamentoPerfilActionForm.setPercentualTarifaMinimaPrestacao("");
         	parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoAntiguidade("");
        	parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoAntiguidade("");
        	parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
        	parcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividade("");
        	parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividade("");
        	parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividade("");
        	parcelamentoPerfilActionForm.setQtdeMaximaReparcelamento("");
        	parcelamentoPerfilActionForm.setPercentualEntradaSugerida("");
        	parcelamentoPerfilActionForm.setQuantidadeMinimaMesesDebito("");
        	
        	parcelamentoPerfilActionForm.setConsumoMinimo("");
        	parcelamentoPerfilActionForm.setPercentualVariacaoConsumoMedio("");
        	parcelamentoPerfilActionForm.setIndicadorParcelarChequeDevolvido("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setIndicadorParcelarSancoesMaisDeUmaConta("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	
        	parcelamentoPerfilActionForm.setCategoria("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setNumeroConsumoEconomia("");
        	parcelamentoPerfilActionForm.setNumeroAreaConstruida("");    
        	parcelamentoPerfilActionForm.setIndicadorRetroativoTarifaSocial("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteInferior("");
        	parcelamentoPerfilActionForm.setAnoMesReferenciaLimiteSuperior("");
        	parcelamentoPerfilActionForm.setPercentualDescontoTarifaSocial(""); 
        	parcelamentoPerfilActionForm.setParcelaQuantidadeMinimaFatura("");
        	parcelamentoPerfilActionForm.setIndicadorAlertaParcelaMinima("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setPercentualDescontoSancao("");
        	parcelamentoPerfilActionForm.setQuantidadeEconomias(""); 
        	parcelamentoPerfilActionForm.setCapacidadeHidrometro(""); 
        	parcelamentoPerfilActionForm.setIndicadorEntradaMinima("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	parcelamentoPerfilActionForm.setQuantidadeMaximaReparcelamento(""); 
        	parcelamentoPerfilActionForm.setDataLimiteDescontoPagamentoAVista(""); 
        	
        	sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
        	sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
        	sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
        	sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
    		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");
        }
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoQtdePerfil") != null
                && httpServletRequest.getParameter("adicionarParcelamentoQtdePerfil").equalsIgnoreCase("S")
                && parcelamentoPerfilActionForm.getQtdeMaximaReparcelamento() != null) {
        	
        	//-------------- bt adicionarParcelamentoQtdePerfil ---------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	httpServletRequest.setAttribute("adicionarParcelamentoQtdePerfil","N");
        }
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoAntiguidade") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoAntiguidade").equalsIgnoreCase("S")
                && !parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoAntiguidade ------------------------
   
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	        	
        	adicionarParcelamentoDescontoAntiguidade(parcelamentoPerfilActionForm, sessao);
        	
        	httpServletRequest.setAttribute("adicionarParcelamentoDescontoAntiguidade" , "N");
        }
        
        
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividade") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividade").equalsIgnoreCase("S")
                && !parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoInatividade ------------------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	adicionarParcelamentoDescontoInatividade(parcelamentoPerfilActionForm,sessao);
        	
        	httpServletRequest.setAttribute("adicionarParcelamentoDescontoInatividade","N");
        }
        
        if (httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividadeAVista") != null
                && httpServletRequest.getParameter("adicionarParcelamentoDescontoInatividadeAVista").equalsIgnoreCase("S")
                && !parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista().equalsIgnoreCase("")){
        	
        	// ------------------ bt adicionarParcelamentoDescontoInatividadeAVista ------------------------
        	
        	atualizaColecoesNaSessao(sessao,httpServletRequest);
        	
        	adicionarParcelamentoDescontoInatividadeAVista(parcelamentoPerfilActionForm,sessao);
        	
        	httpServletRequest.setAttribute("adicionarParcelamentoDescontoInatividadeAVista","N");
        }
        
		// DEFINIÇÃO QUE IRÁ AUXILIAR O RETORNO DOS POPUPS
		sessao.setAttribute("UseCase", "INSERIRPERFIL");
		
		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") == null || 
		((Collection) sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper")).size() == 0){
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","1");
		}else{
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","2");
		}
		
        //devolve o mapeamento de retorno
        return retorno;

    }
        
    private void adicionarParcelamentoDescontoAntiguidade(ParcelamentoPerfilActionForm parcelamentoPerfilActionForm,
    														HttpSession sessao){
    	
    	if (parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito()== null ||
    			parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe Qtde. Mínima Meses de Débito p/ Desconto
					"atencao.required", null, " Qtde. Mínima Meses de Débito p/ Desconto");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito())){
			throw new ActionServletException(
					// Qtde. Mínima Meses de Débito p/ Desconto deve ser numerico
					"atencao.integer", null, " Qtde. Mínima Meses de Débito p/ Desconto");
		 }
    	
    	Collection<ParcelamentoDescontoAntiguidade> collectionParcelamentoDescontoAntiguidade = null;
        
        if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null) {
        	collectionParcelamentoDescontoAntiguidade = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoAntiguidade");
        } else {
        	collectionParcelamentoDescontoAntiguidade = new ArrayList();
        }
    	            

        Integer quantidadeMinimaMesesDebito = new Integer(parcelamentoPerfilActionForm.getQuantidadeMinimaMesesDebito());
        
		//[FS0006]Verificar percentual de desconto
		/*FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		Collection colecaoSistemaParametros = fachada.pesquisar(
				filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();

		BigDecimal percentualMaximoAbatimento = sistemaParametro.getPercentualMaximoAbatimento();*/
		

        
        BigDecimal percentualDescontoSemRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{	
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoAntiguidade());
        	
        	/*//[FS0006]Verificar percentual de desconto
        	if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
				//Percentual de Desconto Sem Restabelecimento é superior ao 
				//Percentual Máximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
				throw new ActionServletException(
				"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimento);
    		}*/
		}

        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoAntiguidade());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
				//Percentual de Desconto Com Restabelecimento é superior ao 
				//Percentual Máximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
				throw new ActionServletException(
				"atencao.percentual_desconto_com_rest_superior_percentual_max", null,"" + percentualMaximoAbatimento);
    		}*/
        }
        
        BigDecimal percentualDescontoAtivo = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoAtivo()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoAtivo().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Ativo
					"atencao.required", null, "  Percentual de Desconto Ativo");	
        
        }else{
        	percentualDescontoAtivo = Util.formatarMoedaRealparaBigDecimal
        		(parcelamentoPerfilActionForm.getPercentualDescontoAtivo());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoAtivo.compareTo(percentualMaximoAbatimento) > 0  ){
				//Percentual de Desconto Ativo é superior ao 
				//Percentual Máximo de Desconto de << percentualMaximoAbatimento  >> permitido para Financiamneto
				throw new ActionServletException(
				"atencao.percentual_desconto_ativo_superior_percentual_max",null,"" + percentualMaximoAbatimento);
    		}*/
		}

		
        ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeNovo = 
										new ParcelamentoDescontoAntiguidade();
		 
		if (collectionParcelamentoDescontoAntiguidade != null && !collectionParcelamentoDescontoAntiguidade.isEmpty()){
			// se coleção não estiver vazia
			// verificar se a qtd máxima de prestações do parcelamento ja foi informada	
			ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoAntiguidade.iterator();
		
			while (iterator.hasNext()) {
				parcelamentoDescontoAntiguidadeAntigo = (ParcelamentoDescontoAntiguidade) iterator.next();
				
				// Verificar quantidade mínima meses de debito para desconto
				if (quantidadeMinimaMesesDebito.equals
						(parcelamentoDescontoAntiguidadeAntigo.getQuantidadeMinimaMesesDebito())){
					//Quantidade Mínima Meses de Debito para Desconto já informada
					throw new ActionServletException(
					"atencao.qtde_minima_meses_debito_desconto_ja_informado");
				}
			}
		}
		
		if (parcelamentoPerfilActionForm.getIdContaMotivoRevisao() != null && 
			!parcelamentoPerfilActionForm.getIdContaMotivoRevisao().equalsIgnoreCase("") &&
			!parcelamentoPerfilActionForm.getIdContaMotivoRevisao().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
			FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
	        
			filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.ID, 
			new Integer(parcelamentoPerfilActionForm.getIdContaMotivoRevisao())));
	        
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

		parcelamentoPerfilActionForm.setQuantidadeMinimaMesesDebito("");
		parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoAntiguidade("");
		parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoAntiguidade("");
		parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		parcelamentoPerfilActionForm.setIdContaMotivoRevisao(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		
		
		//Ordena a coleção pela Qtde. Mínima Meses de Débito p/ Desconto
		Collections.sort((List) collectionParcelamentoDescontoAntiguidade, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoDescontoAntiguidade) a).getQuantidadeMinimaMesesDebito().toString()) ;
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoDescontoAntiguidade) b).getQuantidadeMinimaMesesDebito().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		
		
		 //manda para a sessão a coleção de ParcelamentoDescontoAntiguidade
        sessao.setAttribute("collectionParcelamentoDescontoAntiguidade", collectionParcelamentoDescontoAntiguidade);
    }
    
    private void adicionarParcelamentoDescontoInatividade(ParcelamentoPerfilActionForm parcelamentoPerfilActionForm,
							HttpSession sessao){
    	
    	if (parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade() == null ||
    			parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Qtde. Máxima Meses de Inatividade da Lig. de Água
					"atencao.required", null, " Qtde. Máxima Meses de Inatividade da Lig. de Água");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade())){
			throw new ActionServletException(
					// Qtde. Máxima Meses de Inatividade da Lig. de Água deve ser numerico
					"atencao.integer", null, " Qtde. Máxima Meses de Inatividade da Lig. de Água");
		 }
    	
    	Collection<ParcelamentoDescontoInatividade> collectionParcelamentoDescontoInatividade = null;
        
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null) {
        	collectionParcelamentoDescontoInatividade = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividade");
        } else {
        	collectionParcelamentoDescontoInatividade = new ArrayList();
        }
    	            
        Integer quantidadeMaximaMesesInatividade = new Integer(parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividade());


		/*FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		Collection colecaoSistemaParametros = fachada.pesquisar(
				filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();

		BigDecimal percentualMaximoAbatimento = sistemaParametro.getPercentualMaximoAbatimento();*/

		
        BigDecimal percentualDescontoSemRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividade());

        	//[FS0006]Verificar percentual de desconto
    		/*if (percentualDescontoSemRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
    			//Percentual de Desconto Sem Restabelecimento é superior ao 
    			//Percentual Máximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
    			throw new ActionServletException(
    				"atencao.percentual_desconto_sem_rest_superior_percentual_max", null, "" + percentualMaximoAbatimento);
    			
    		}*/
		}
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividade());
        	
        	//[FS0006]Verificar percentual de desconto
        	/*if (percentualDescontoComRestabelecimento.compareTo(percentualMaximoAbatimento) > 0  ){
    			//Percentual de Desconto Com Restabelecimento é superior ao 
    			//Percentual Máximo de Desconto de << percentualMaximoAbatimento >> permitido para Financiamneto
    			throw new ActionServletException(
    				"atencao.percentual_desconto_com_rest_superior_percentual_max", null,"" + percentualMaximoAbatimento);
    			
    		}*/
		}
        						
		
        ParcelamentoDescontoInatividade parcelamentoDescontoInatividadeNovo = 
										new ParcelamentoDescontoInatividade();
		 
		if (collectionParcelamentoDescontoInatividade != null && !collectionParcelamentoDescontoInatividade.isEmpty()){
			// se coleção não estiver vazia
			// verificar se a qtd máxima de prestações do parcelamento ja foi informada	
			ParcelamentoDescontoInatividade parcelamentoDescontoInatividadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoInatividade.iterator();
		
			while (iterator.hasNext()) {
			parcelamentoDescontoInatividadeAntigo = (ParcelamentoDescontoInatividade) iterator.next();
				
				//[FS0003] Verificar quantidade mínima meses de debito
				if (quantidadeMaximaMesesInatividade.equals
						(parcelamentoDescontoInatividadeAntigo.getQuantidadeMaximaMesesInatividade())){
					//Quantidade Máxima Meses de Inatividade de Ligação de Água já informada
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

		parcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividade("");
		parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividade("");
		parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividade("");
		parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		
		//Ordena a coleção pela Qtde. Máxima Meses de Inatividade da Lig. de Água
		Collections.sort((List) collectionParcelamentoDescontoInatividade, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcelamentoDescontoInatividade) a).getQuantidadeMaximaMesesInatividade().toString() );
				Integer valorMinPrestacao2 = new Integer(((ParcelamentoDescontoInatividade) b).getQuantidadeMaximaMesesInatividade().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		 //manda para a sessão a coleção de ParcelamentoDescontoInatividade
        sessao.setAttribute("collectionParcelamentoDescontoInatividade", collectionParcelamentoDescontoInatividade);

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

    	
    	//collectionParcelamentoQuantidadeReparcelamentoHelper
    	/*
    	if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null
				&& !sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper").equals(
						"")) {

			Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
					.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
			// cria as variáveis para recuperar os parâmetros do request e jogar
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
				
				// insere essas variáveis no objeto ParcelamentoQuantidadeReparcelamentoHelper
				BigDecimal valorMinimoPrestacao  = null;
				if (vlMinPrest != null 
						&& !vlMinPrest.equals("")) {
					valorMinimoPrestacao = Util.formatarMoedaRealparaBigDecimal(vlMinPrest);
				}

				parcelamentoQuantidadeReparcelamentoHelper.setValorMinimoPrestacao(valorMinimoPrestacao);

			}
        }	*/
    	
    }
    
    private void adicionarParcelamentoDescontoInatividadeAVista(
    		ParcelamentoPerfilActionForm parcelamentoPerfilActionForm,HttpSession sessao){
    	
    	if (parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista() == null ||
    			parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista().equalsIgnoreCase("")){
    			throw new ActionServletException(
					// Informe  Qtde. Máxima Meses de Inatividade da Lig. de Água
					"atencao.required", null, " Qtde. Máxima Meses de Inatividade da Lig. de Água");	
    	}else if( Util.validarValorNaoNumericoComoBigDecimal(parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista())){
			throw new ActionServletException(
					// Qtde. Máxima Meses de Inatividade da Lig. de Água deve ser numerico
					"atencao.integer", null, " Qtde. Máxima Meses de Inatividade da Lig. de Água");
		 }
    	
    	Collection<ParcDesctoInativVista> collectionParcelamentoDescontoInatividadeAVista = null;
        
        if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null) {
        	collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
                    .getAttribute("collectionParcelamentoDescontoInatividadeAVista");
        } else {
        	collectionParcelamentoDescontoInatividadeAVista = new ArrayList();
        }
    	            
        Integer quantidadeMaximaMesesInatividade = new Integer(parcelamentoPerfilActionForm.getQuantidadeMaximaMesesInatividadeAVista());

        BigDecimal percentualDescontoSemRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Sem Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Sem Restabelecimento");	
        	
        }else{
        	percentualDescontoSemRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        		(parcelamentoPerfilActionForm.getPercentualDescontoSemRestabelecimentoInatividadeAVista());

		}
        
        BigDecimal percentualDescontoComRestabelecimento = null;
        if (parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista()== null
        		|| parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista().equalsIgnoreCase("")){
        	throw new ActionServletException(
					// Informe  Percentual de Desconto Com Restabelecimento
					"atencao.required", null, "  Percentual de Desconto Com Restabelecimento");	
        	
        }else{
        	percentualDescontoComRestabelecimento = Util.formatarMoedaRealparaBigDecimal
        	(parcelamentoPerfilActionForm.getPercentualDescontoComRestabelecimentoInatividadeAVista());
        	
		}
        						
		
        ParcDesctoInativVista parcelamentoDescontoInatividadeNovo = new ParcDesctoInativVista();
		 
		if (collectionParcelamentoDescontoInatividadeAVista != null && !collectionParcelamentoDescontoInatividadeAVista.isEmpty()){
			// se coleção não estiver vazia
			// verificar se a qtd máxima de prestações do parcelamento ja foi informada	
			ParcDesctoInativVista parcelamentoDescontoInatividadeAntigo = null;
			Iterator iterator = collectionParcelamentoDescontoInatividadeAVista.iterator();
		
			while (iterator.hasNext()) {
			parcelamentoDescontoInatividadeAntigo = (ParcDesctoInativVista) iterator.next();
				
				//[FS0003] Verificar quantidade mínima meses de debito
				if (quantidadeMaximaMesesInatividade.equals
						(parcelamentoDescontoInatividadeAntigo.getQuantidadeMaximaMesesInatividade())){
					//Quantidade Máxima Meses de Inatividade de Ligação de Água já informada
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

		parcelamentoPerfilActionForm.setQuantidadeMaximaMesesInatividadeAVista("");
		parcelamentoPerfilActionForm.setPercentualDescontoSemRestabelecimentoInatividadeAVista("");
		parcelamentoPerfilActionForm.setPercentualDescontoComRestabelecimentoInatividadeAVista("");
//		parcelamentoPerfilActionForm.setPercentualDescontoAtivo("");
		
		
		//Ordena a coleção pela Qtde. Máxima Meses de Inatividade da Lig. de Água
		Collections.sort((List) collectionParcelamentoDescontoInatividadeAVista, new Comparator() {
			public int compare(Object a, Object b) {
				Integer valorMinPrestacao1 = new Integer(((ParcDesctoInativVista) a).getQuantidadeMaximaMesesInatividade().toString() );
				Integer valorMinPrestacao2 = new Integer(((ParcDesctoInativVista) b).getQuantidadeMaximaMesesInatividade().toString()) ;
		
				return valorMinPrestacao1.compareTo(valorMinPrestacao2);

			}
		});
		
		 //manda para a sessão a coleção de ParcelamentoDescontoInatividadeAVista
        sessao.setAttribute("collectionParcelamentoDescontoInatividadeAVista", collectionParcelamentoDescontoInatividadeAVista);

    }

}

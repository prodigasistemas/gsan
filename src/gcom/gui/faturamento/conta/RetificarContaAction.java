package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.FiltroContaMotivoRetificacao;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetificarContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        // Mudar isso quando tiver esquema de segurança
        HttpSession sessao = this.getSessao(httpServletRequest);        
        
        //Carregando a conta com os dados atuais
        Conta contaAtual = (Conta) sessao.getAttribute("contaRetificar");        
        
        String valorConfirmacao = httpServletRequest.getParameter("confirmado");
        
        // Instância do formulário que está sendo utilizado
		RetificarContaActionForm retificarContaActionForm = (RetificarContaActionForm) actionForm;

		// Recebendo os dados enviados pelo formulário
		String imovelIdJSP = retificarContaActionForm.getIdImovel();
		String mesAnoContaJSP = retificarContaActionForm.getMesAnoConta();
		String vencimentoContaJSP = retificarContaActionForm.getVencimentoConta();
		Integer situacaoAguaContaJSP = new Integer(retificarContaActionForm.getSituacaoAguaConta());
		Integer situacaoEsgotoContaJSP = new Integer(retificarContaActionForm.getSituacaoEsgotoConta());
		Integer motivoRetificacaoContaJSP = new Integer(retificarContaActionForm.getMotivoRetificacaoID());
		
		String consumoAguaJSP = retificarContaActionForm.getConsumoAgua();
		String consumoEsgotoJSP = retificarContaActionForm.getConsumoEsgoto();
		String percentualEsgotoJSP = retificarContaActionForm.getPercentualEsgoto();
		
		
		/*
		 * Adicionar a lista de clientes vinculados à conta, e indicar qual deles
		 * será impresso na 2ª via da conta 
		 */
		
		String agua = null;
		String consumoAguaAntes = null;
		String esgoto = null;
		String consumoEsgotoAntes = null;
		
		if (consumoAguaJSP == null){
			agua = "1";
		} else{
			agua = consumoAguaJSP;
		}
		
		if (sessao.getAttribute("consumoAguaAntes") == null){
			consumoAguaAntes = "1";
		}else{
			consumoAguaAntes = sessao.getAttribute("consumoAguaAntes").toString();
		}
		
		if (consumoEsgotoJSP == null){
			esgoto = "1";
		}else{
			esgoto = consumoEsgotoJSP;
		}
		
		if (sessao.getAttribute("consumoEsgotoAntes") == null){
			consumoEsgotoAntes = "1";
		}else{
			consumoEsgotoAntes = sessao.getAttribute("consumoEsgotoAntes").toString();
		} 
		
		String percentualEsgotoConta = null;
		
		if(percentualEsgotoJSP != null){
			percentualEsgotoConta = percentualEsgotoJSP.toString().replace(".","");
			percentualEsgotoConta = percentualEsgotoConta.replace(",","");
		} else{
			percentualEsgotoConta = "1";
		}
		
		Boolean flag = true;
		if (situacaoAguaContaJSP.toString().equals(sessao.getAttribute("situacaoAguaContaAntes")) && 
			situacaoEsgotoContaJSP.toString().equals(sessao.getAttribute("situacaoEsgotoContaAntes")) && 
			agua.equals(consumoAguaAntes) && 
			esgoto.equals(consumoEsgotoAntes) && 
			percentualEsgotoConta.equals(sessao.getAttribute("percentualEsgotoAntes")) && 
			!vencimentoContaJSP.equals(sessao.getAttribute("vencimentoContaAntes"))) {
			flag = false;
		}
		
		// Carrega as coleções de acordo com os objetos da sessão
		Collection colecaoDebitoCobrado = new Vector();
		if (sessao.getAttribute("colecaoDebitoCobrado") != null) {
			colecaoDebitoCobrado = 
				(Collection) sessao.getAttribute("colecaoDebitoCobrado");
		}
		
		
		Collection colecaoCreditoRealizado = new Vector();
		if (sessao.getAttribute("colecaoCreditoRealizado") != null) {
			colecaoCreditoRealizado = 
				(Collection) sessao.getAttribute("colecaoCreditoRealizado");
		}

		//Alterado por Raphael Rossiter em 17/04/2007
		SistemaParametro sistemaParametro = 
			this.getFachada().pesquisarParametrosDoSistema();
		
		Collection colecaoCategoriaOUSubcategoria = new Vector();
		Collection colecaoCategoriaOUSubcategoriaInicial = new Vector();
		
		if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)){
    		
			colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoSubcategoria");
			
			if (sessao.getAttribute("colecaoSubcategoriaInicial") != null) {
    			
    			colecaoCategoriaOUSubcategoriaInicial = 
    				(Collection) sessao.getAttribute("colecaoSubcategoriaInicial");
    			
    			if (colecaoCategoriaOUSubcategoria == null || 
    				colecaoCategoriaOUSubcategoriaInicial.size() != colecaoCategoriaOUSubcategoria.size() ||
    				!colecaoCategoriaOUSubcategoriaInicial.containsAll(colecaoCategoriaOUSubcategoria)){
    				
    				flag = true;
    			}
    			
    			//Atualizando a quantidade de economias por subcategoria de acordo com o informado pelo usuário
    	        //-------------------------------------------------------------------------------------------
    	        Iterator colecaoCategoriaOUSubcategoriaInicialIt = colecaoCategoriaOUSubcategoriaInicial.iterator();
    	        Subcategoria subcategoria;
    	        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
    	        String qtdPorEconomia;
    	        
    	        while (colecaoCategoriaOUSubcategoriaInicialIt.hasNext()) {
    	        	subcategoria = (Subcategoria) colecaoCategoriaOUSubcategoriaInicialIt.next();

    				if (requestMap.get("subcategoria"+subcategoria.getId().intValue()) != null) {

    					qtdPorEconomia = (requestMap.get("subcategoria" + subcategoria.getId().intValue()))[0];
    					
    					if(!subcategoria.getQuantidadeEconomias().toString().equals(qtdPorEconomia)){
    						flag = true;
    					}

    					if (qtdPorEconomia == null || qtdPorEconomia.equalsIgnoreCase("")) {
    						throw new ActionServletException(
    							"atencao.campo_texto.obrigatorio", 
    							null,
    							"Quantidade de economias");
    					}

    					subcategoria.setQuantidadeEconomias(new Integer(qtdPorEconomia));
    				}
    	        }
    		}
    	} else{
    		
    		colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoCategoria");
    	
    		if (sessao.getAttribute("colecaoCategoriaInicial") != null) {
    			
    			colecaoCategoriaOUSubcategoriaInicial = 
    				(Collection) sessao.getAttribute("colecaoCategoriaInicial");
    			
    			if (colecaoCategoriaOUSubcategoria == null || 
    				colecaoCategoriaOUSubcategoriaInicial.size() != colecaoCategoriaOUSubcategoria.size() ||
    				!colecaoCategoriaOUSubcategoriaInicial.containsAll(colecaoCategoriaOUSubcategoria)) {
    				
    				flag = true;
    			}
    			
    			//Atualizando a quantidade de economias por categoria de acordo com o informado pelo usuário
    	        //-------------------------------------------------------------------------------------------
    	        Iterator colecaoCategoriaOUSubcategoriaInicialIt = colecaoCategoriaOUSubcategoriaInicial.iterator();
    	        Categoria categoria;
    	        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
    	        String qtdPorEconomia;
    	        
    	        while (colecaoCategoriaOUSubcategoriaInicialIt.hasNext()) {
    	        	categoria = (Categoria) colecaoCategoriaOUSubcategoriaInicialIt.next();

    				if (requestMap.get("categoria"+categoria.getId().intValue()) != null) {

    					qtdPorEconomia = (requestMap.get("categoria" + categoria.getId().intValue()))[0];
    					if(!categoria.getQuantidadeEconomiasCategoria().toString().equals(qtdPorEconomia)){
    						flag = true;
    					}

    					if (qtdPorEconomia == null || qtdPorEconomia.equalsIgnoreCase("")) {
    						throw new ActionServletException(
    							"atencao.campo_texto.obrigatorio", 
    							null,
    							"Quantidade de economias");
    					}

    					categoria.setQuantidadeEconomiasCategoria(new Integer(qtdPorEconomia));
    				}
    	        }
    		}
    	}
		
		Collection colecaoDebito = new Vector();
		if (sessao.getAttribute("colecaoDebitoCobradoInicial") != null) {
			
			colecaoDebito = 
				(Collection) sessao.getAttribute("colecaoDebitoCobradoInicial");
			
			if (sessao.getAttribute("colecaoDebitoCobrado") == null || 
				colecaoDebito.size() != ((Collection) sessao.getAttribute("colecaoDebitoCobrado")).size() ||
				!colecaoDebito.containsAll((Collection) sessao.getAttribute("colecaoDebitoCobrado")) ){
				
				flag = true;
			}

			//Atualizando o valor do debito de acordo com o informado pelo usuário
	        //-------------------------------------------------------------------------------------------
	        Iterator colecaoDebitoIt = colecaoDebito.iterator();
	        DebitoCobrado debitoCobrado;
	        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
	        String valor;
	        BigDecimal valor2 = new BigDecimal ("0.00"); 
	        
	        while (colecaoDebitoIt.hasNext()) {
	        	debitoCobrado = (DebitoCobrado) colecaoDebitoIt.next();

				if (requestMap.get("debitoCobrado"
						+ GcomAction.obterTimestampIdObjeto(debitoCobrado)) != null) {

					valor = (requestMap.get("debitoCobrado" + GcomAction.obterTimestampIdObjeto(debitoCobrado)))[0];
					if(!debitoCobrado.getValorPrestacao().toString().equals(valor)){
						flag = true;
					}
					if (valor == null
							|| valor.equalsIgnoreCase("")) {
						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Débito Cobrado");
					}
					else{
    					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
    				}

					debitoCobrado.setValorPrestacao(valor2);
				}
	        }
	        //------------------------------------------------------------------------------------------
		}
				
		Collection colecaoCredito = new Vector();
		if (sessao.getAttribute("colecaoCreditoRealizadoInicial") != null) {
			colecaoCredito = (Collection) sessao
					.getAttribute("colecaoCreditoRealizadoInicial");
			
			if (sessao.getAttribute("colecaoCreditoRealizado") == null || 
					colecaoCredito.size() != ((Collection) sessao.getAttribute("colecaoCreditoRealizado")).size()){
				flag = true;
			}
			
			//Atualizando o valor do credito de acordo com o informado pelo usuário
	        //-------------------------------------------------------------------------------------------
	        Iterator colecaoCreditoIt = colecaoCredito.iterator();
	        CreditoRealizado creditoRealizado;
	        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
	        String valor;
	        BigDecimal valor2 = new BigDecimal ("0.00"); 
	        
	        while (colecaoCreditoIt.hasNext()) {
	        	creditoRealizado = (CreditoRealizado) colecaoCreditoIt.next();

				if (requestMap.get("creditoRealizado"
						+ GcomAction.obterTimestampIdObjeto(creditoRealizado)) != null) {

					valor = (requestMap.get("creditoRealizado" + GcomAction.obterTimestampIdObjeto(creditoRealizado)))[0];
					if(!creditoRealizado.getValorCredito().toString().equals(valor)){
						flag = true;
					}
					if (valor == null
							|| valor.equalsIgnoreCase("")) {
						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Crédito Realizado");
					}
					else{
    					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
    				}

					creditoRealizado.setValorCredito(valor2);
				}
	        }

		}
		// [FS0015] - Verificar se foi efetuado o cálculo da conta

		// [SF0001] - Determinar Valores para Faturamento de Água e/ou Esgoto
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        // Adicionado por Bruno Barros, 25/07/2008
        // Verificar se foi informado a tarifa de consumo da conta no retificar
        // Caso sim, calcular com o novo ConsumoTarifa informado, senão, 
        // utilizar o da conta
        
        Integer idConsumoTarifa = contaAtual.getConsumoTarifa().getId();
        
        if ( retificarContaActionForm.getIdConsumoTarifa() != null &&
             !retificarContaActionForm.getIdConsumoTarifa().equals( ConstantesSistema.NUMERO_NAO_INFORMADO ) ){
           idConsumoTarifa = Integer.parseInt( retificarContaActionForm.getIdConsumoTarifa() );            
        }
        //RM4132 - adicionado por Vivianne Sousa - 17/02/2011 - analista:Jeferson Pedrosa
		boolean temPermissaoParaRetificarContaNorma = 
			this.getFachada().verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, usuarioLogado);	
	    verificarOcorrenciasMesmoMotivoAno(motivoRetificacaoContaJSP,new Integer(imovelIdJSP), 
    			 sessao, sistemaParametro, temPermissaoParaRetificarContaNorma);
        verificarValoresLeituraAnteriorEAtualPoco(retificarContaActionForm.getLeituraAnteriorPoco(),
    		retificarContaActionForm.getLeituraAtualPoco(), retificarContaActionForm.getConsumoFaturadoPoco(),sistemaParametro);
        validarCompetencia(retificarContaActionForm.getMotivoRetificacaoID(), sistemaParametro,
    			 usuarioLogado, contaAtual, retificarContaActionForm.getConsumoFaturadoPoco(), 
    			 consumoAguaJSP,temPermissaoParaRetificarContaNorma);
                
        Collection<CalcularValoresAguaEsgotoHelper> valoresConta = 
            this.getFachada().calcularValoresConta(
                    mesAnoContaJSP, 
                    imovelIdJSP,
                    situacaoAguaContaJSP, 
                    situacaoEsgotoContaJSP,
                    colecaoCategoriaOUSubcategoria, 
                    consumoAguaJSP, 
                    consumoEsgotoJSP,
                    percentualEsgotoJSP, 
                    idConsumoTarifa, 
                    usuarioLogado);        
        
        // Fim alterador por Bruno Barros, 25/07/2008
        
        //Cálcula o valor total dos débitos de uma conta de acordo com o informado pelo usuário
        BigDecimal valorTotalDebitosConta = 
        	this.getFachada().calcularValorTotalDebitoConta(colecaoDebitoCobrado,
        httpServletRequest.getParameterMap());
        
        //Cálcula o valor total dos créditos de uma conta de acordo com o informado pelo usuário
        BigDecimal valorTotalCreditosConta = 
        	this.getFachada().calcularValorTotalCreditoConta(colecaoCreditoRealizado,
        httpServletRequest.getParameterMap());
		
		//Totalizando os valores de água e esgoto
        BigDecimal valorTotalAgua = new BigDecimal("0");
        BigDecimal valorTotalEsgoto = new BigDecimal("0");
        
        if (valoresConta != null){
            for(CalcularValoresAguaEsgotoHelper valor : valoresConta){
                //Valor Faturado de Água
                if (valor.getValorFaturadoAguaCategoria() != null){
                    valorTotalAgua = valorTotalAgua.add(valor.getValorFaturadoAguaCategoria());
                }
                
                //Valor Faturado de Esgoto
                if (valor.getValorFaturadoEsgotoCategoria() != null){
                    valorTotalEsgoto = valorTotalEsgoto.add(valor.getValorFaturadoEsgotoCategoria());
                }                
            }
         }
        
        BigDecimal valorTotalConta = new BigDecimal("0");
        
        //Setar valor do rateio  atualizado
		if (contaAtual.getValorRateioAgua() != null) {
			valorTotalAgua = valorTotalAgua.add(contaAtual.getValorRateioAgua());
		}

		if (contaAtual.getValorRateioEsgoto() != null) {
			valorTotalEsgoto = valorTotalEsgoto.add(contaAtual.getValorRateioEsgoto());
		}
        
        valorTotalConta = valorTotalConta.add(valorTotalAgua);
        valorTotalConta = valorTotalConta.add(valorTotalDebitosConta);
        valorTotalConta = valorTotalConta.add(valorTotalEsgoto);
        valorTotalConta = valorTotalConta.subtract(valorTotalCreditosConta);
        
        if (valorTotalConta.compareTo(BigDecimal.ZERO) < 0 ){	
        	throw new ActionServletException("atencao.valor_conta_negativo");
        }

		
        // [FS0022] - Retificação de Conta Retifivada.
        //-------------------------------------------------------------------------------------------
		// Alterado por :  Yara Taciane  - data : 19/06/2008 
		// Analista :  Denys Guimarães
        //-------------------------------------------------------------------------------------------
        //Caso a conta de origem seja RETIFICADA.
		if( contaAtual.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.RETIFICADA) ){
			// E o valor total da nova conta esteja menor que o valor total da conta retificada original.
			if(valorTotalConta.doubleValue() < contaAtual.getValorTotalContaBigDecimal().doubleValue() ){
				// E o usuário não tenha permissão especial. 
				boolean temPermissaoParaRetificarParaMenorContaRetificadora = 
					this.getFachada().verificarPermissaoEspecial(
						PermissaoEspecial.RETIFICAR_PARA_MENOR_CONTA_RETIFICADA,
						usuarioLogado);
				
				if(temPermissaoParaRetificarParaMenorContaRetificadora == false){
					throw new ActionServletException("atencao.necessario_permissao_especial_para_retificar_para_menor");
				}
			}
		}
		//-------------------------------------------------------------------------------------------
        
		//Invertendo o formato para yyyyMM (sem a barra)
		mesAnoContaJSP = Util.formatarMesAnoParaAnoMesSemBarra(mesAnoContaJSP);
		
		//-------------------------------------------------------------------------------------------
		// Alterado por :  Hugo Leonardo  - data : 10/08/2010 
		// Analista :  Aryed Lins
		//-------------------------------------------------------------------------------------------	  

		// Data de vencimento da conta
		Date dataVencimentoConta = 
			Util.converteStringParaDate(vencimentoContaJSP);
		//Integer idImovel = Util.converterStringParaInteger(imovelIdJSP);
		Integer idConta = contaAtual.getId();
		

		// [FS0007] - Validar data de vencimento.			
		// if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
			
			//verifica se vencimento da conta foi alterado
			if(!retificarContaActionForm.getVencimentoConta().equals(retificarContaActionForm.getVencimentoContaNaBase())){
				
				//Caso data corrente seja posterior a data corrente mais a quantidade de dias parametro.
				Date dataCorrente = new Date();	
				
				Integer diasAdicionais = 0;
	        	
	        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
	        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
	        	}
				
				Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dataCorrente, diasAdicionais.intValue());
				//Date dataUltimoDiaMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente), Util.getAno(dataCorrente));
				
				//E o usuário não tenha permissão especial.	
				boolean temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao = 
					this.getFachada().verificarPermissaoEspecial(
						PermissaoEspecial.RETIFICAR_DATA_VENCIMENTO_ALEM_PRAZO_PADRAO,
						usuarioLogado);		
					
				//	1 se a dataVencimentoConta for maior que a dataCorrenteComDias.
				if(Util.compararData(dataVencimentoConta, dataCorrenteComDias) == 1 && 
					temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao == false){
					
					throw new ActionServletException("atencao.necessario_permissao_especial_para_data_vencimento_posterior_permitido");
				}
			
				Calendar data1985 = new GregorianCalendar(1985, 1, 1);
				if(Util.compararData(dataVencimentoConta, data1985.getTime()) == -1){
					throw new ActionServletException("atencao.data_vencimento_menor_1985");
				}				
			}
		//}	
		//-------------------------------------------------------------------------------------------

		// LigacaoAguaSituacao
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();		
		filtroLigacaoAguaSituacao.adicionarParametro(
			new ParametroSimples(FiltroLigacaoAguaSituacao.ID, 
				situacaoAguaContaJSP));		
		
		Collection colecaoLigacaoAguaSituacao = 
			this.getFachada().pesquisar(filtroLigacaoAguaSituacao, 
				LigacaoAguaSituacao.class.getName());	
		
		LigacaoAguaSituacao ligacaoAguaSituacao = 
			(LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
        
        if (retorno.getName().equalsIgnoreCase("telaSucesso") ) {
            
        	boolean telaConfirmacao = 
        		!consumoAguaAntes.equals( consumoAguaJSP ) && 
                ligacaoAguaSituacao.getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM ) && 
                valorConfirmacao == null ;                
            
            // se for para ir para a tela de confirmação
            if (telaConfirmacao) {
                httpServletRequest.setAttribute("caminhoActionConclusao",
                        "/gsan/retificarContaAction.do");
                httpServletRequest.setAttribute("cancelamento", "TRUE");
                httpServletRequest.setAttribute("nomeBotao1", "Sim");
                httpServletRequest.setAttribute("nomeBotao2", "Não");

                return montarPaginaConfirmacao("atencao.consumo_novo_substituir_consumo_calculo_media_ano_mes",
                        httpServletRequest, actionMapping);
            }
        }        
        
		// LigacaoEsgotoSituacao
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		
		filtroLigacaoEsgotoSituacao.adicionarParametro(
			new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, 
					situacaoEsgotoContaJSP));
		
		Collection colecaoLigacaoEsgotoSituacao = 
			this.getFachada().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
		
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);

		
		// Motivo da Retificação da conta
		ContaMotivoRetificacao contaMotivoRetificacao = new ContaMotivoRetificacao();
		contaMotivoRetificacao.setId(motivoRetificacaoContaJSP);
		if(!flag)
		{
			throw new ActionServletException("atencao.so_vencimento_alterado_para_retificar_conta");
		}
		
		// -------------------------------------------------------------------------------------------
		// Alterado por :  Hugo Leonardo - data : 02/07/2010 
		// Analista :  Fabiola Araujo.
		//-------------------------------------------------------------------------------------------
		if(!vencimentoContaJSP.equals(sessao.getAttribute("vencimentoContaAntes"))){
			contaAtual.setIndicadorAlteracaoVencimento(new Short("1"));
		}else{
			contaAtual.setIndicadorAlteracaoVencimento(new Short("2"));
		}
		//-------------------------------------------------------------------------------------------
		//CRC4202 - adicionado por Vivianne Sousa - 21/09/2010 - analista:Adriana Ribeiro
		Integer leituraAnterior = null;
		if(retificarContaActionForm.getLeituraAnteriorAgua() != null && !retificarContaActionForm.getLeituraAnteriorAgua().trim().equalsIgnoreCase("")) {	
			leituraAnterior = new Integer(retificarContaActionForm.getLeituraAnteriorAgua());
		}
		Integer leituraAtual = null;
		if(retificarContaActionForm.getLeituraAtualAgua() != null && !retificarContaActionForm.getLeituraAtualAgua().trim().equalsIgnoreCase("")) {	
			leituraAtual = new Integer(retificarContaActionForm.getLeituraAtualAgua());
		}
		
		Integer leituraAnteriorPoco = null;
		if(retificarContaActionForm.getLeituraAnteriorPoco() != null && !retificarContaActionForm.getLeituraAnteriorPoco().trim().equalsIgnoreCase("")) {	
			leituraAnteriorPoco = new Integer(retificarContaActionForm.getLeituraAnteriorPoco());
		}
		Integer leituraAtualPoco = null;
		if(retificarContaActionForm.getLeituraAtualPoco() != null && !retificarContaActionForm.getLeituraAtualPoco().trim().equalsIgnoreCase("")) {	
			leituraAtualPoco = new Integer(retificarContaActionForm.getLeituraAtualPoco());
		}
		Integer volumePoco = null;
		if(retificarContaActionForm.getConsumoFaturadoPoco() != null && !retificarContaActionForm.getConsumoFaturadoPoco().trim().equalsIgnoreCase("")) {	
			volumePoco = new Integer(retificarContaActionForm.getConsumoFaturadoPoco());
		}
		BigDecimal percentualColeta = null;
		if(retificarContaActionForm.getPercentualColeta() != null && !retificarContaActionForm.getPercentualColeta().trim().equalsIgnoreCase("")) {	
			percentualColeta = Util.formatarMoedaRealparaBigDecimal(retificarContaActionForm.getPercentualColeta());
		}
		
		
		boolean atualizarMediaConsumoHistorico = false;
		if (valorConfirmacao != null)
			if (valorConfirmacao.equals("ok"))
				atualizarMediaConsumoHistorico = true;
		
		String retornoUrlBotaoVoltar = "retificarConta";
		
		/*(for (CalcularValoresAguaEsgotoHelper conta : valoresConta) {
		     conta.setValorFaturadoAguaCategoria(valorTotalConta);  	 
		}*/
		
		Integer consumoMedidoProporcional = obterConsumoMedidoProporcional(leituraAtual, leituraAnterior, retificarContaActionForm, 
				colecaoCategoriaOUSubcategoriaInicial, idConsumoTarifa, usuarioLogado);
		
        idConta = getFachada().retificarConta(new Integer(mesAnoContaJSP), 
        		contaAtual,
        		contaAtual.getImovel(), 
        		colecaoDebitoCobrado, 
        		colecaoCreditoRealizado,
        		ligacaoAguaSituacao, 
        		ligacaoEsgotoSituacao, 
        		colecaoCategoriaOUSubcategoria, 
        		consumoAguaJSP,
        		consumoEsgotoJSP, 
        		percentualEsgotoJSP, 
        		dataVencimentoConta, 
        		valoresConta,
        		contaMotivoRetificacao, 
        		httpServletRequest.getParameterMap(), 
        		usuarioLogado, 
        		idConsumoTarifa.toString(), 
        		atualizarMediaConsumoHistorico,
        		leituraAnterior,leituraAtual,true,
        		retornoUrlBotaoVoltar,leituraAnteriorPoco,
        		leituraAtualPoco,volumePoco,percentualColeta, consumoMedidoProporcional);
        
		getFachada().encerrarRA(contaAtual.getImovel().getId(), usuarioLogado);
				
		montarPaginaSucesso(httpServletRequest, 
				"Conta " + Util.formatarAnoMesParaMesAno(new Integer(mesAnoContaJSP).intValue()) + 
				" do imóvel " + contaAtual.getImovel().getId().intValue() + " retificada com sucesso.",
				"Realizar outra Manutençao de Conta",
				"exibirManterContaAction.do?menu=sim","gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&idConta="+ idConta.toString(), 
				"Emitir 2ª Via de Conta");

		return retorno;
    }
    
    private Integer obterConsumoMedidoProporcional(Integer leituraAtual,Integer leituraAnterior, RetificarContaActionForm form, 
    		Collection colecaoCategoriaOUSubcategoria, Integer idConsumoTarifa, Usuario usuarioLogado) {
    	Integer consumoMedidoProporcional = new Integer("0");

    	if (leituraAtual != null && leituraAnterior != null) {
    		Integer consumoAguaMedido = leituraAtual - leituraAnterior;
    		
    		Collection<CalcularValoresAguaEsgotoHelper> valoresConta = 
    				this.getFachada().calcularValoresConta(
    						form.getMesAnoConta(), 
    						form.getIdImovel(),
    						new Integer(form.getSituacaoAguaConta()), 
    						new Integer(form.getSituacaoEsgotoConta()),
    						colecaoCategoriaOUSubcategoria, 
    						consumoAguaMedido.toString(), 
    						form.getConsumoEsgoto(),
    						form.getPercentualEsgoto(), 
    						idConsumoTarifa, 
    						usuarioLogado);
    		
    		if (valoresConta != null && !valoresConta.isEmpty()) {
    			
    			for (CalcularValoresAguaEsgotoHelper item : valoresConta) {
    				if (item.getConsumoFaturadoAguaCategoria() != null) {
    					consumoMedidoProporcional = consumoMedidoProporcional + item.getConsumoFaturadoAguaCategoria();
    				}
    			}
    		}
    	}
		
    	return consumoMedidoProporcional; 
    }
	
	/*[SB0012] – Determinar competência de retificação de consumo
	 * Vivianne Sousa - 16/02/2011
	 * RM4132 - analista responsável:Jeferson Pedrosa
	 */
	public BigDecimal determinarCompatenciaRetificacaoConsumo(String idMotivoSelecionado,
			SistemaParametro sistemaParametro,Usuario usuarioLogado,boolean temPermissaoParaRetificarContaNorma){
		
		BigDecimal competenciaRetificacao = null;
		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			//Caso a Empresa esteja na Norma de Retificação de Conta 
			
			if(idMotivoSelecionado != null && !idMotivoSelecionado.equalsIgnoreCase("")
				&& !idMotivoSelecionado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				FiltroContaMotivoRetificacao filtroContaMotivoRetificacao = new FiltroContaMotivoRetificacao();
				filtroContaMotivoRetificacao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRetificacao.CODIGO, idMotivoSelecionado));
				
				ContaMotivoRetificacao contaMotivoRetificacao = null;
				Collection colecaoContaMotivoRetificacao = getFachada().pesquisar(
						filtroContaMotivoRetificacao, ContaMotivoRetificacao.class.getName());
				if (colecaoContaMotivoRetificacao != null && !colecaoContaMotivoRetificacao.isEmpty()) {
					contaMotivoRetificacao = (ContaMotivoRetificacao)Util.retonarObjetoDeColecao(colecaoContaMotivoRetificacao);
					
					if(!temPermissaoParaRetificarContaNorma && contaMotivoRetificacao != null && 
							contaMotivoRetificacao.getIndicadorCompetenciaConsumo().equals(ConstantesSistema.SIM)){
						
						Collection colecaoGrupo = getFachada().pesquisarGrupoUsuario(usuarioLogado.getId());
						if(colecaoGrupo != null && !colecaoGrupo.isEmpty()){
							Grupo grupo = (Grupo)Util.retonarObjetoDeColecao(colecaoGrupo);
							competenciaRetificacao = grupo.getCompetenciaRetificacao();
							
						}else{
							competenciaRetificacao = getFachada().pesquisarMaiorCompetenciaRetificacaoGrupo();
							
							if(competenciaRetificacao == null || competenciaRetificacao.equals(new BigDecimal("0.00"))){
								throw new ActionServletException("atencao.fator_competencia_precisa_ser_informado");
							}
						}
						
					}
				}
			
			}
					
		}
		return competenciaRetificacao;
	}

	
	/* [FS0041] – Validar competência Consumo Água
	 * Vivianne Sousa - 17/02/2011
	 * RM4132 - analista responsável:Jeferson Pedrosa
	 */
	public void validarCompetenciaConsumoAgua(BigDecimal competenciaRetificacao,Conta contaSelecao,
			String consumoAgua,boolean temPermissaoParaRetificarContaNorma){
		
		if(!temPermissaoParaRetificarContaNorma){
			FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
	        
	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
	        		FiltroConsumoHistorico.LIGACAO_TIPO_ID, ConsumoHistorico.INDICADOR_FATURAMENTO_FATURAR_AGUA));
	        
	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
	        		FiltroConsumoHistorico.ANO_MES_FATURAMENTO, contaSelecao.getReferencia()));
	        
	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
	        		FiltroConsumoHistorico.IMOVEL_ID, contaSelecao.getImovel().getId()));
	        
	        Collection colecaoConsumoHistorico = getFachada().pesquisar(filtroConsumoHistorico,	ConsumoHistorico.class.getName());
	        
	        if(!Util.isVazioOrNulo(colecaoConsumoHistorico)){
	        	
	        	ConsumoHistorico consumoHistorico = (ConsumoHistorico) colecaoConsumoHistorico.iterator().next();
	        	
	        	if(consumoHistorico.getConsumoMedio() != null){
	        		
	        		BigDecimal consumoMedio = new BigDecimal(consumoHistorico.getConsumoMedio());
//	 	        	BigDecimal divisao = competenciaRetificacao.divide(new BigDecimal(100));
	 	        	BigDecimal valorComparacao = consumoMedio.multiply(competenciaRetificacao);
	 	        	BigDecimal consumoAguaBigDecimal = new BigDecimal(consumoAgua);
	 	        	
	 	        	if(consumoAguaBigDecimal.compareTo(valorComparacao) < 0){
	 	        		throw new ActionServletException("atencao.comsumo_agua_abaixo_competencia_permitida");
	 	        	}
	        	}
	        	
	        }
		}
	}

	
	/* [FS0042] – Validar competência Volume Poço
	 * Vivianne Sousa - 17/02/2011
	 * RM4132 - analista responsável:Jeferson Pedrosa
	 */
	public void validarCompetenciaVolumePoco(BigDecimal competenciaRetificacao,
			Conta contaSelecao,String volumePoco,boolean temPermissaoParaRetificarContaNorma){
		
		if(!temPermissaoParaRetificarContaNorma && volumePoco != null && !volumePoco.equals("")){
			
			FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
 	        
 	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
 	        		FiltroConsumoHistorico.LIGACAO_TIPO_ID, ConsumoHistorico.INDICADOR_FATURAMENTO_FATURAR_ESGOTO));
 	        
 	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
 	        		FiltroConsumoHistorico.ANO_MES_FATURAMENTO, contaSelecao.getReferencia()));
 	        
 	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
 	        		FiltroConsumoHistorico.IMOVEL_ID, contaSelecao.getImovel().getId()));
 	        
 	        Collection colecaoConsumoHistorico = getFachada().pesquisar(filtroConsumoHistorico,	ConsumoHistorico.class.getName());
 	        
 	        if(!Util.isVazioOrNulo(colecaoConsumoHistorico)){
 	        	
 	        	ConsumoHistorico consumoHistorico = (ConsumoHistorico) colecaoConsumoHistorico.iterator().next();
 	        	
 	        	if(consumoHistorico.getConsumoMedio() != null){
 	        		
 	        		BigDecimal consumoMedio = new BigDecimal(consumoHistorico.getConsumoMedio());
// 	 	        	BigDecimal divisao = competenciaRetificacao.divide(new BigDecimal(100));
 	 	        	BigDecimal valorComparacao = consumoMedio.multiply(competenciaRetificacao);
 	 	        	BigDecimal volumePocoBigDecimal = new BigDecimal(volumePoco);
 	 	        	
 	 	        	if(volumePocoBigDecimal.compareTo(valorComparacao) < 0){
 	 	        		throw new ActionServletException("atencao.volume_poco_abaixo_competencia_permitida");
 	 	        	}
 	        	}
 	        	
 	        }
		}
		
	}

	/* Vivianne Sousa - 17/02/2011
	 * RM4132 - analista responsável:Jeferson Pedrosa
	 */
	public void validarCompetencia(String idMotivoSelecionado,SistemaParametro sistemaParametro,
			Usuario usuarioLogado,Conta contaSelecao,String volumePoco,String consumoAgua,boolean temPermissaoParaRetificarContaNorma){
		
		BigDecimal competenciaRetificacao = determinarCompatenciaRetificacaoConsumo(idMotivoSelecionado,
				sistemaParametro, usuarioLogado,temPermissaoParaRetificarContaNorma);
		
		if(competenciaRetificacao != null){
			
			validarCompetenciaConsumoAgua(competenciaRetificacao, contaSelecao, consumoAgua,temPermissaoParaRetificarContaNorma);
			
			validarCompetenciaVolumePoco(competenciaRetificacao, contaSelecao, volumePoco,temPermissaoParaRetificarContaNorma);
		}
	}
	
	 /* [FS0039] – Verificar valores de Leitura Anterior e Atual do Poço
	  * Vivianne Sousa - 17/02/2011
	  * RM4132 - analista responsável:Jeferson Pedrosa
	 */
	public void verificarValoresLeituraAnteriorEAtualPoco(String leituraAnteriorPocoString,
			String leituraAtualPocoString, String consumoFaturadoPocoString,SistemaParametro sistemaParametro) {
		
		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			
			if(leituraAnteriorPocoString != null && leituraAtualPocoString != null && consumoFaturadoPocoString != null &&
					!leituraAnteriorPocoString.equalsIgnoreCase("") && !leituraAtualPocoString.equalsIgnoreCase("") 
					&& !consumoFaturadoPocoString.equalsIgnoreCase("")){
				//Caso exista informação do Volume de Poço, da Leitura Anterior Poço e da Leitura Atual Poço:
				
				Integer leituraAnteriorPoco = new Integer(leituraAnteriorPocoString);
				Integer leituraAtualPoco =  new Integer(leituraAtualPocoString);
				Integer consumoFaturadoPoco = new Integer(consumoFaturadoPocoString);
				
				int diferencaLeituraAnteriorELeituraAtual = 0;

				if(leituraAtualPoco.intValue() > leituraAnteriorPoco.intValue()){
					diferencaLeituraAnteriorELeituraAtual = leituraAtualPoco.intValue() - leituraAnteriorPoco.intValue();
				}else if(leituraAnteriorPoco.intValue() > leituraAtualPoco.intValue()){
					diferencaLeituraAnteriorELeituraAtual = leituraAnteriorPoco.intValue() - leituraAtualPoco.intValue();
				}
				
				if(diferencaLeituraAnteriorELeituraAtual != consumoFaturadoPoco.intValue()){
					throw new ActionServletException("atencao.leitura_poco_fora_faixa");
				}
			}
		}
	
	}
	
	/*[FS0037]-Verificar ocorrências mesmo motivo no ano
	 * Vivianne Sousa - 11/02/2011
	 */
	public void verificarOcorrenciasMesmoMotivoAno(Integer idMotivoSelecionado,Integer idImovel, 
			HttpSession sessao,SistemaParametro sistemaParametro,boolean temPermissaoParaRetificarContaNorma){

		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			//Caso a Empresa esteja na Norma de Retificação de Conta 
			
			ContaMotivoRetificacao contaMotivoRetificacao = getFachada().pesquisaContaMotivoRetificacao(idMotivoSelecionado);
			if (contaMotivoRetificacao != null && contaMotivoRetificacao.getNumeroOcorrenciasNoAno() != null &&
				contaMotivoRetificacao.getNumeroOcorrenciasNoAno().compareTo(new Integer("0")) == 1 ){
				
				Integer numeroOcorrenciasNoAno = contaMotivoRetificacao.getNumeroOcorrenciasNoAno();
				
				Integer qtdeContaEContaHistoricoRetificadaMotivo = getFachada().
				pesquisaQtdeContaEContaHistoricoRetificadaMotivo(idMotivoSelecionado,idImovel);
				
				if(!temPermissaoParaRetificarContaNorma && 
						qtdeContaEContaHistoricoRetificadaMotivo.compareTo(numeroOcorrenciasNoAno) > -1){
					//Limite de Retificações para o mesmo Motivo excedido!
					throw new ActionServletException("atencao.limite_retificacoes_excedido");
					
				}
				
			}
			
		}
		
	}
	
}


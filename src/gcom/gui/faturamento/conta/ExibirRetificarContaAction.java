package gcom.gui.faturamento.conta;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.FiltroMotivoRetificacaoConta;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirRetificarContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirRetificarConta");

        Fachada fachada = Fachada.getInstancia();
        
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instância do formulário que está sendo utilizado
        RetificarContaActionForm retificarContaActionForm = (RetificarContaActionForm) actionForm;
        
        String confirmado = httpServletRequest.getParameter("confirmado");
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
		//verifica se o usuário tem permissão especial.
		boolean temPermissaoParaRetificarContaNorma = 
			fachada.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, usuarioLogado);	
        
        /*
		 * Colocado por Raphael Rossiter em 16/04/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		// httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
		httpServletRequest.setAttribute("indicadorTarifaCategoria", sistemaParametro.getIndicadorTarifaCategoria().toString());
		
        //Conta selecionada        
        String reloadPage = httpServletRequest.getParameter("reloadPage");
        String selecionouMotivo = httpServletRequest.getParameter("selecionouMotivo");
        String idConta = null;
        if(httpServletRequest.getParameter("contaID") != null){
        	idConta = httpServletRequest.getParameter("contaID");
        	retificarContaActionForm.setMotivoRetificacaoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        }else
        {
        	idConta = sessao.getAttribute("contaID").toString();
        }
        
        if(httpServletRequest.getParameter("idImovel") != null){
			/*
			 * Colocado por Ana Maria em 22/04/2009				
			 */
			if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado(usuarioLogado)){
				Boolean imovelBloqueado = fachada.verificarImovelPerfilBloqueado(new Integer(httpServletRequest.getParameter("idImovel")));
				if(imovelBloqueado){						
	                throw new ActionServletException(
	                        "atencao.perfil_imovel_nao_permite_operacao");
				}
			}
    		sessao.setAttribute("idImovel",httpServletRequest.getParameter("idImovel"));
    	}
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
       // Collection colecaoTiposClientes = null;
        
        
        //Alterado por Sávio Luiz Analista:Aryed Lins
        //Data:06/12/2007
        Integer idContaInt = Util.converterStringParaInteger(idConta);
        Pagamento pagamento = null;
        if(idContaInt != null){
        	pagamento = fachada.pesquisarPagamentoDeConta(idContaInt);	
        }
         
        if(reloadPage == null || reloadPage.equals("")){
	        if(pagamento != null && !pagamento.equals("")){
	        
		        if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
	            
					httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/exibirRetificarContaAction.do");
					
					Integer anoMesReferenciaConta = null;
					// Monta a página de confirmação para perguntar se o usuário
					// quer inserir
					// a devolução mesmo com o valor da devolução sendo superior ao da guia da devolução
					Collection colecaoContas = (Collection)sessao.getAttribute("colecaoContaImovel");
					if(colecaoContas != null && !colecaoContas.isEmpty()){
						Iterator ite = colecaoContas.iterator();
						while (ite.hasNext()){
							Conta conta = (Conta)ite.next();
							if(conta.getId().equals(idContaInt)){
								anoMesReferenciaConta = conta.getReferencia();	
								break;
							}
						}
					}
						
					sessao.setAttribute("contaID",idConta);
					return montarPaginaConfirmacao(
							"atencao.conta_paga",
							httpServletRequest, actionMapping,Util.formatarAnoMesParaMesAno(anoMesReferenciaConta));
				}	
	        }
        }
        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
        
        //DEFINIÇÃO QUE IRÁ AUXILIAR O RETORNO DOS POPUPS
        sessao.setAttribute("UseCase", "RETIFICARCONTA");
        
        Collection colecaoMotivoRetificacaoConta, colecaoSituacaoLigacaoAgua, colecaoSituacaoLigacaoEsgoto;
        
        Collection colecaoMedicaoHistorico, colecaoConsumoHistorico;
        
        //Removendo coleções da sessão
        if (idConta != null && !idConta.equalsIgnoreCase("") && reloadPage == null){
        	sessao.removeAttribute("contaRetificar");
        	sessao.removeAttribute("colecaoCategoria");
        	sessao.removeAttribute("colecaoDebitoCobrado");
        	sessao.removeAttribute("colecaoCreditoRealizado");
        	sessao.removeAttribute("colecaoAdicionarCategoria");
        	sessao.removeAttribute("colecaoAdicionarDebitoTipo");
        	sessao.removeAttribute("colecaoAdicionarDebitoTipo");
        	sessao.removeAttribute("colecaoTiposClientes");
        	sessao.removeAttribute("colecaoClientesVinculados");
        }
        
        
        
        //Carregar a data corrente do sistema
        //====================================
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        //Ultimo dia do mês corrente.        
        Date ultimaDataMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente.getTime()), Util.getAno(dataCorrente.getTime()));        
        httpServletRequest.setAttribute("ultimaDataMes", formatoData.format(ultimaDataMes));
        
        //httpServletRequest.setAttribute("indicadorCalculaVencimento", sistemaParametro.getIndicadorCalculaVencimento());
        sessao.setAttribute("indicadorCalculaVencimento", sistemaParametro.getIndicadorCalculaVencimento());
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData
        .format(dataCorrente.getTime()));
        
        //Data Corrente + 60 dias
        dataCorrente.add(Calendar.DATE, 60);
        httpServletRequest.setAttribute("dataAtual60", formatoData
        .format(dataCorrente.getTime()));
        
        
        /*
         * Costante que informa o ano limite para o campo anoMesReferencia da conta
         */
        httpServletRequest.setAttribute("anoLimite", ConstantesSistema.ANO_LIMITE);
        
        
        /*Motivo da Retificação (Carregar coleção) e remover as coleções que ainda estão na sessão
        ====================================================================== */
        if (sessao.getAttribute("colecaoMotivoRetificacaoConta") == null) {
        	
        	
        	FiltroMotivoRetificacaoConta filtroMotivoRetificacaoConta = new FiltroMotivoRetificacaoConta(
        			FiltroMotivoRetificacaoConta.DESCRICAO_MOTIVO_RETIFICACAO_CONTA);

        	filtroMotivoRetificacaoConta.adicionarParametro(new ParametroSimples(
        			FiltroMotivoRetificacaoConta.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

        	colecaoMotivoRetificacaoConta = fachada.pesquisar(filtroMotivoRetificacaoConta,
        			ContaMotivoRetificacao.class.getName());

            if (colecaoMotivoRetificacaoConta == null
                    || colecaoMotivoRetificacaoConta.isEmpty()) {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "MOTIVO_RETIFICACAO_CONTA");
            } else {
                sessao.setAttribute("colecaoMotivoRetificacaoConta",
                		colecaoMotivoRetificacaoConta);
            }
        }
        //====================================================================
        
        
        
        /*Situação Ligação Água (Carregar coleção)
        ====================================================================== */
        if (sessao.getAttribute("colecaoSituacaoLigacaoAgua") == null) {

        	FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(
        			FiltroLigacaoAguaSituacao.DESCRICAO);

        	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
        			FiltroLigacaoAguaSituacao.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

        	colecaoSituacaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao,
        			LigacaoAguaSituacao.class.getName());

            if (colecaoSituacaoLigacaoAgua == null
                    || colecaoSituacaoLigacaoAgua.isEmpty()) {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "LIGACAO_AGUA_SITUACAO");
            } else {
                sessao.setAttribute("colecaoSituacaoLigacaoAgua",
                		colecaoSituacaoLigacaoAgua);
            }
        }
        //=====================================================================
        
        
        
        /*Situação Ligação Esgoto (Carregar coleção)
         ====================================================================== */
        if (sessao.getAttribute("colecaoSituacaoLigacaoEsgoto") == null) {

        	FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(
        			FiltroLigacaoEsgotoSituacao.DESCRICAO);

        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
        			FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

        	colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
        			LigacaoEsgotoSituacao.class.getName());

            if (colecaoSituacaoLigacaoEsgoto == null
                    || colecaoSituacaoLigacaoEsgoto.isEmpty()) {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "LIGACAO_ESGOTO_SITUACAO");
            } else {
                sessao.setAttribute("colecaoSituacaoLigacaoEsgoto",
                		colecaoSituacaoLigacaoEsgoto);
            }
        }
        //====================================================================
        
        
        
        
        /* Pesquisando a conta a partir do id recebido
         ===================================================================== */
        Conta contaSelecao = null;
        
        if (idConta != null && !idConta.equalsIgnoreCase("")){
        	
        	contaSelecao = fachada.pesquisarContaRetificacao(new Integer(idConta));
        
        	if (contaSelecao == null) {
        		throw new ActionServletException(
                    "atencao.pesquisa.conta.inexistente");
        	}
        	
        	//Colocando o objeto conta selecionado na sessão
        	sessao.setAttribute("contaRetificar", contaSelecao);
        
        }
        else if (sessao.getAttribute("contaRetificar") != null){
        	contaSelecao = (Conta) sessao.getAttribute("contaRetificar");
        }
        else{
        	throw new ActionServletException(
            "atencao.pesquisa.conta.inexistente");
        }
        
        // Indicador imovel condominio
        if(contaSelecao.getImovel() != null && contaSelecao.getImovel().getImovelCondominio() != null){
		   httpServletRequest.setAttribute("idImovelCondominio",
				   contaSelecao.getImovel().getImovelCondominio().getId());
        }
        
        
        //====================================================================
        
        if (idConta != null && !idConta.equalsIgnoreCase("") &&
            (reloadPage == null || reloadPage.equalsIgnoreCase(""))){
        
	        /*Pesquisar o imóvel a partir da matrícula do imóvel
	        ====================================================================== */
        	String idImovel = null;
        	
        	if(httpServletRequest.getParameter("idImovel") != null){
        		idImovel = httpServletRequest.getParameter("idImovel");
        		sessao.setAttribute("idImovel",httpServletRequest.getParameter("idImovel"));
        	}else{
        		idImovel = (String)sessao.getAttribute("idImovel");
        	}
        	
            Imovel objetoImovel = contaSelecao.getImovel();
	        //====================================================================
            
            /*
    		 * Colocado por Raphael Rossiter em 09/11/2007
    		 * OBJ: Retificar uma conta sem RA
    		 */
			boolean temPermissaoRetificarContaSemRA = 
			fachada.verificarPermissaoRetificarContaSemRA(usuario);
			
			if (!temPermissaoRetificarContaSemRA){
				
				//[FS0001] - Verificar Existência de RA
	            fachada.verificarExistenciaRegistroAtendimento(new Integer(idImovel), 
	            "atencao.conta_existencia_registro_atendimento",EspecificacaoTipoValidacao.ALTERACAO_CONTA);
			
			}
        	
			
            /*Pesquisar o cliente usuário do imóvel selecionado
	        ====================================================================== */
            String nomeClienteUsuarioImovel = fachada.consultarClienteUsuarioImovel(contaSelecao.getImovel().getId());
	        //====================================================================
        
        
            //PREPARANDO OS OBJETOS PARA SEREM EXIBIDOS PELO FORMULÁRIO
	        //===================================================================
	        
            if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)){
        		
        		//[UC0108] - Quantidade de economias por categoria
	        	Collection colecaoSubcategoria = fachada
	        	.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(contaSelecao);
	        	
	        	sessao.setAttribute("colecaoSubcategoria", colecaoSubcategoria);
	        	
	        	Collection colecaoSubcategoriaInicial = new ArrayList();
		        colecaoSubcategoriaInicial.addAll(colecaoSubcategoria);
		        sessao.setAttribute("colecaoSubcategoriaInicial", colecaoSubcategoriaInicial);
        	}
        	else{
        		
        		//[UC0108] - Quantidade de economias por categoria
        		Collection colecaoCategoria = fachada.obterQuantidadeEconomiasContaCategoria(contaSelecao);
    	        
        		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
	        	
	        	Collection colecaoCategoriaInicial = new ArrayList();
		        colecaoCategoriaInicial.addAll(colecaoCategoria);
		        sessao.setAttribute("colecaoCategoriaInicial", colecaoCategoriaInicial);
        	}
            
            
	        //Obtendo os débitos cobrados da conta
	        Collection colecaoDebitoCobrado = fachada.obterDebitosCobradosConta(contaSelecao);
	        sessao.setAttribute("colecaoDebitoCobrado", colecaoDebitoCobrado);
	        
	        Collection colecaoDebitoCobradoInicial = new ArrayList();
	        colecaoDebitoCobradoInicial.addAll(colecaoDebitoCobrado);
	        sessao.setAttribute("colecaoDebitoCobradoInicial", colecaoDebitoCobradoInicial);
	        
	        //Obtendo os créditos realizados da conta
	        Collection colecaoCreditoRealizado = fachada.obterCreditosRealizadosConta(contaSelecao);
	        sessao.setAttribute("colecaoCreditoRealizado", colecaoCreditoRealizado);
	        
	        Collection colecaoCreditoRealizadoInicial = new ArrayList();
	        colecaoCreditoRealizadoInicial.addAll(colecaoCreditoRealizado);
	        sessao.setAttribute("colecaoCreditoRealizadoInicial", colecaoCreditoRealizadoInicial);
	        
	        
	        //Totalizando o valor dos créditos
	        BigDecimal valorTotalCredito = new BigDecimal("0.00");
	        if (colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()){
	        	
	        	Iterator colecaoCreditoRealizadoIt = colecaoCreditoRealizado.iterator();
	        	CreditoRealizado creditoRealizadoColecao;
				
	        	while (colecaoCreditoRealizadoIt.hasNext()) {
	        		creditoRealizadoColecao = (CreditoRealizado) colecaoCreditoRealizadoIt
							.next();
					
	        		valorTotalCredito = valorTotalCredito.add(creditoRealizadoColecao.getValorCredito());
				}
	        }
	        
	        
	        //Totalizando o valor dos débitos
	        BigDecimal valorTotalDebito = new BigDecimal("0.00");
	        if (colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()){
	        	
	        	Iterator colecaoDebitoCobradoIt = colecaoDebitoCobrado.iterator();
	        	DebitoCobrado debitoCobradoColecao;
				
	        	while (colecaoDebitoCobradoIt.hasNext()) {
	        		debitoCobradoColecao = (DebitoCobrado) colecaoDebitoCobradoIt
							.next();
					
	        		valorTotalDebito = valorTotalDebito.add(debitoCobradoColecao.getValorPrestacao());
				}
	        }
	        
	        
	        //Consumo de Água
	        /*String consumoAgua = null;
	        if (contaSelecao.getConsumoAgua() != null){
	        	consumoAgua = String.valueOf(contaSelecao.getConsumoAgua().intValue());
	        }
	        
	        //Consumo de Esgoto
	        String consumoEsgoto = null;
	        if (contaSelecao.getConsumoEsgoto() != null){
	        	consumoEsgoto = String.valueOf(contaSelecao.getConsumoEsgoto().intValue());
	        }
	        
	        //Percentual de Esgoto
	        String percentualEsgoto = null;
	        if (objetoImovel.getLigacaoEsgoto() != null){
	        	if (objetoImovel.getLigacaoEsgoto().getPercentual() != null){
	        		percentualEsgoto = objetoImovel.getLigacaoEsgoto().getPercentual().toString();
	        	}
	        }*/
	        
	      
	        
	        //[SF0001] - Determinar Valores para Faturamento de Água e/ou Esgoto
	        /*
	        Collection<CalcularValoresAguaEsgotoHelper> valoresConta = fachada.calcularValoresConta(Util.formatarMesAnoReferencia(contaSelecao.getReferencia()), String.valueOf(objetoImovel.getId()),
	        contaSelecao.getLigacaoAguaSituacao().getId(), contaSelecao.getLigacaoEsgotoSituacao().getId(), colecaoCategoria, consumoAgua, consumoEsgoto, percentualEsgoto);
	        
	        
	        //Totalizando os valores de água e esgoto
	        BigDecimal valorTotalAgua = new BigDecimal("0");
	        BigDecimal valorTotalEsgoto = new BigDecimal("0");
	        
	        if (valoresConta != null && valoresConta.isEmpty()){
	        	
	        	Iterator valoresContaIt = valoresConta.iterator();
	        	CalcularValoresAguaEsgotoHelper valoresContaObjeto = null;
	        	
	        	while (valoresContaIt.hasNext()){
	        		
	        		valoresContaObjeto = (CalcularValoresAguaEsgotoHelper) valoresContaIt.next();
	        		
	        		//Valor Faturado de Água
	        		if (valoresContaObjeto.getValorFaturadoAguaCategoria() != null){
	        			valorTotalAgua = valorTotalAgua.add(valoresContaObjeto.getValorFaturadoAguaCategoria());
	        		}
	        		
	        		//Valor Faturado de Esgoto
	        		if (valoresContaObjeto.getValorFaturadoEsgotoCategoria() != null){
	        			valorTotalEsgoto = valorTotalEsgoto.add(valoresContaObjeto.getValorFaturadoEsgotoCategoria());
	        		}
	     
	        	}
	            
	        } */
	        
	        BigDecimal valorTotalAgua = new BigDecimal("0.00");
	        BigDecimal valorTotalEsgoto = new BigDecimal("0.00");
	        
	        if (contaSelecao.getValorAgua() != null){
	        	valorTotalAgua = contaSelecao.getValorAgua();
	        }
	        
	        if (contaSelecao.getValorEsgoto() != null){
	        	valorTotalEsgoto = contaSelecao.getValorEsgoto();
	        }
	        
	        
	        BigDecimal valorTotalConta = new BigDecimal("0.00");
	        valorTotalConta = contaSelecao.getValorTotal(); 
	        
	        //Arredondando os valores obtidos para duas casas decimais
			valorTotalAgua.setScale(2, BigDecimal.ROUND_HALF_UP);
			valorTotalEsgoto.setScale(2, BigDecimal.ROUND_HALF_UP);
	        valorTotalDebito.setScale(2, BigDecimal.ROUND_HALF_UP);
			valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
	        
	        
			
			//Dados da conta
	        retificarContaActionForm.setMesAnoConta(Util.formatarMesAnoReferencia(contaSelecao.getReferencia()));
	        if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
	        	Date dtCorrente = new Date();		
	        	
	        	Integer diasAdicionais = 0;
	        	
	        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
	        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
	        	}
	        	
				Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dtCorrente, diasAdicionais.intValue());
				
				// [FS0035] - Validar Vencimento Automatico
				if (contaSelecao.getDataVencimentoConta().before(dataCorrenteComDias)) {
		        	retificarContaActionForm.setVencimentoConta(Util.formatarData(dataCorrenteComDias));
		        	retificarContaActionForm.setVencimentoContaNaBase(Util.formatarData(contaSelecao.getDataVencimentoConta()));
				}else {
					retificarContaActionForm.setVencimentoConta(Util.formatarData(contaSelecao.getDataVencimentoConta()));
		        	retificarContaActionForm.setVencimentoContaNaBase(Util.formatarData(contaSelecao.getDataVencimentoConta()));
				}
	        }else{
	        	retificarContaActionForm.setVencimentoConta(Util.formatarData(contaSelecao.getDataVencimentoConta()));
	        	retificarContaActionForm.setVencimentoContaNaBase(Util.formatarData(contaSelecao.getDataVencimentoConta()));
	        }
	        
	        /**
	         * Autor: Hugo Leonardo
	         * CRC_4795
	         * Data: 04/11/2010
	         * 
	         */
	        
        	if(contaSelecao.getPercentualColeta() != null && Util.verificarNaoVazio(contaSelecao.getPercentualColeta().toString())){
        		
        		retificarContaActionForm.setPercentualColeta(contaSelecao.getPercentualColeta().toString());
        	}else{
        		
        		 FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
     	        
     	        filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade(
         			"imovel.ligacaoEsgotoSituacao");
     	        
     	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
     	        		FiltroConsumoHistorico.LIGACAO_TIPO_ID, ConsumoHistorico.INDICADOR_FATURAMENTO_FATURAR_ESGOTO));
     	        
     	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
     	        		FiltroConsumoHistorico.ANO_MES_FATURAMENTO, contaSelecao.getReferencia()));
     	        
     	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
     	        		FiltroConsumoHistorico.IMOVEL_ID, contaSelecao.getImovel().getId()));
     	        
     	        filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
     	        		"imovel.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao", ConstantesSistema.SIM));
     	        
     	        colecaoConsumoHistorico = fachada.pesquisar(filtroConsumoHistorico,
     	        		ConsumoHistorico.class.getName());
     	        
     	        if(!Util.isVazioOrNulo(colecaoConsumoHistorico)){
     	        	
     	        	ConsumoHistorico consumoHistorico = (ConsumoHistorico) colecaoConsumoHistorico.iterator().next();
     	        	
     	        	if(consumoHistorico.getPercentualColeta() != null && Util.verificarNaoVazio(consumoHistorico.getPercentualColeta().toString())){
     	        		
     	        		retificarContaActionForm.setPercentualColeta(Util.formatarMoedaReal(consumoHistorico.getPercentualColeta()));
     	        	}else{
     	        		
     	        		retificarContaActionForm.setPercentualColeta("");
     	        	}
     	        }else{
     	        	
     	        	retificarContaActionForm.setPercentualColeta("");
     	        }
        	}
	        
	        FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
	        filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade(
	        		"imovel.ligacaoEsgotoSituacao");
	        
	        filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
	        		FiltroMedicaoHistorico.MEDICAO_TIPO_ID, ConstantesSistema.NAO));
	        
	        filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
	        		"imovel.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao", ConstantesSistema.SIM));
	        
	        filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
	        		FiltroMedicaoHistorico.IMOVEL_ID, contaSelecao.getImovel().getId()));
	        
	        filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
	        		FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO, contaSelecao.getReferencia()));
	        
	        colecaoMedicaoHistorico = fachada.pesquisar(filtroMedicaoHistorico,
	        		MedicaoHistorico.class.getName());
	        if(contaSelecao.getNumeroVolumePoco() != null && Util.verificarNaoVazio(contaSelecao.getNumeroVolumePoco().toString())){
        		
        		retificarContaActionForm.setConsumoFaturadoPoco(contaSelecao.getNumeroVolumePoco().toString());
        	}else if(!Util.isVazioOrNulo(colecaoMedicaoHistorico)){
    	        	
	        	MedicaoHistorico medicaoHistorico = (MedicaoHistorico) colecaoMedicaoHistorico.iterator().next();
	        	
	        	if(medicaoHistorico.getNumeroConsumoMes() != null && Util.verificarNaoVazio(medicaoHistorico.getNumeroConsumoMes().toString())){
	        		
	        		retificarContaActionForm.setConsumoFaturadoPoco(medicaoHistorico.getNumeroConsumoMes().toString());
	        	}else{
	        		
	        		retificarContaActionForm.setConsumoFaturadoPoco("");
	        	}
    	    }else{
    	        	
	        	retificarContaActionForm.setConsumoFaturadoPoco("");
    	    }
	        
	        if(contaSelecao.getNumeroLeituraAnteriorPoco() != null && Util.verificarNaoVazio(contaSelecao.getNumeroLeituraAnteriorPoco().toString())){
        		
        		retificarContaActionForm.setLeituraAnteriorPoco(contaSelecao.getNumeroLeituraAnteriorPoco().toString());
        		
        	}else if(!Util.isVazioOrNulo(colecaoMedicaoHistorico)){
    	        	
	        	MedicaoHistorico medicaoHistorico = (MedicaoHistorico) colecaoMedicaoHistorico.iterator().next();
	        	
	        	if(medicaoHistorico.getLeituraAnteriorInformada() != null && Util.verificarNaoVazio(medicaoHistorico.getLeituraAnteriorInformada().toString())){
	        		
	        		retificarContaActionForm.setLeituraAnteriorPoco(medicaoHistorico.getLeituraAnteriorInformada().toString());
	        	}else{
	        		
	        		retificarContaActionForm.setLeituraAnteriorPoco("");
	        	}
    	    }else{
    	        	
	        	retificarContaActionForm.setLeituraAnteriorPoco("");
    	    }
	        
	        if(contaSelecao.getNumeroLeituraAtualPoco() != null && Util.verificarNaoVazio(contaSelecao.getNumeroLeituraAtualPoco().toString())){
        		
        		retificarContaActionForm.setLeituraAtualPoco(contaSelecao.getNumeroLeituraAtualPoco().toString());
        		
        	}else if(!Util.isVazioOrNulo(colecaoMedicaoHistorico)){
    	        	
	        	MedicaoHistorico medicaoHistorico = (MedicaoHistorico) colecaoMedicaoHistorico.iterator().next();
	        	
	        	if(medicaoHistorico.getLeituraAtualInformada() != null && Util.verificarNaoVazio(medicaoHistorico.getLeituraAtualInformada().toString())){
	        		
	        		retificarContaActionForm.setLeituraAtualPoco(medicaoHistorico.getLeituraAtualInformada().toString());
	        	}else{
	        		
	        		retificarContaActionForm.setLeituraAtualPoco("");
	        	}
    	    }else{
    	        	
	        	retificarContaActionForm.setLeituraAtualPoco("");
    	    }
	        /**********************/
	        
	        retificarContaActionForm.setSituacaoAguaConta(String.valueOf(contaSelecao.getLigacaoAguaSituacao().getId().intValue()));
	        retificarContaActionForm.setSituacaoEsgotoConta(String.valueOf(contaSelecao.getLigacaoEsgotoSituacao().getId().intValue()));
	        retificarContaActionForm.setPercentualEsgoto(Util.formatarMoedaReal(contaSelecao.getPercentualEsgoto()));
	        
	        //Consumo de água (Conta)
	        if ((contaSelecao.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.LIGADO)
	        	|| contaSelecao.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.CORTADO)) &&
	        	contaSelecao.getConsumoAgua() != null){
	        	retificarContaActionForm.setConsumoAgua(String.valueOf(contaSelecao.getConsumoAgua().intValue()));
	        }
	        if(contaSelecao.getNumeroLeituraAnterior() != null){
	        	retificarContaActionForm.setLeituraAnteriorAgua(contaSelecao.getNumeroLeituraAnterior().toString());
	        }	
	        if(contaSelecao.getNumeroLeituraAtual() != null){
	        	retificarContaActionForm.setLeituraAtualAgua(contaSelecao.getNumeroLeituraAtual().toString());
	        }	
	        
	        //Consumo de esgoto (Conta)
	        if (contaSelecao.getLigacaoEsgotoSituacao().getId().equals(LigacaoAguaSituacao.LIGADO)
	        	&& contaSelecao.getConsumoEsgoto() != null){
	        	retificarContaActionForm.setConsumoEsgoto(String.valueOf(contaSelecao.getConsumoEsgoto().intValue()));
	        }
	        
	        //Percentual Esgoto (Conta)
	        if (contaSelecao.getLigacaoEsgotoSituacao().getId().equals(LigacaoAguaSituacao.LIGADO)
	            && contaSelecao.getPercentualEsgoto() != null){
	            	retificarContaActionForm.setPercentualEsgoto(Util.formatarMoedaReal(contaSelecao.getPercentualEsgoto()));
	        }
	        
	        
	        
	        //Exibindo os valores calculados
			retificarContaActionForm.setValorAgua(Util.formatarMoedaReal(valorTotalAgua));
			retificarContaActionForm.setValorEsgoto(Util.formatarMoedaReal(valorTotalEsgoto));
			retificarContaActionForm.setValorDebito(Util.formatarMoedaReal(valorTotalDebito));
			retificarContaActionForm.setValorTotal(Util.formatarMoedaReal(valorTotalConta)); 
			
	        
	        //Valor total dos créditos
	        retificarContaActionForm.setValorCredito(Util.formatarMoedaReal(valorTotalCredito));
	        
	        
	        //Dados do imóvel
	        retificarContaActionForm.setIdImovel(String.valueOf(objetoImovel.getId().intValue()));
	        retificarContaActionForm.setInscricaoImovel(objetoImovel.getInscricaoFormatada());
	        //retificarContaActionForm.setNomeClienteUsuario(objetoClienteImovel.getCliente().getNome());
	        retificarContaActionForm.setNomeClienteUsuario(nomeClienteUsuarioImovel);
	        retificarContaActionForm.setSituacaoAguaImovel(objetoImovel.getLigacaoAguaSituacao().getDescricao());
	        retificarContaActionForm.setSituacaoEsgotoImovel(objetoImovel.getLigacaoEsgotoSituacao().getDescricao());
	        retificarContaActionForm.setSituacaoAguaConta(String.valueOf(contaSelecao.getLigacaoAguaSituacao().getId().intValue()));
	        retificarContaActionForm.setSituacaoEsgotoConta(String.valueOf(contaSelecao.getLigacaoEsgotoSituacao().getId().intValue()));
	        
	        /*
	        //adicionado por Vivianne Sousa 26/11/208
	        //analista: Adriano Britto
	        BigDecimal percentualColeta = fachada.
	        	obterPercentualAguaConsumidaColetadaImovel(objetoImovel.getId());
	        
	        if(percentualColeta != null){
	        	retificarContaActionForm.setPercentualColeta(percentualColeta.toString());
	        }else{
	        	retificarContaActionForm.setPercentualColeta(null);
	        }
	        */
	        
        }
        
        if(sessao.getAttribute("colecaoCategoria") != null)
        {
        	Collection colecao = (Collection) sessao.getAttribute("colecaoCategoria");
        	Iterator iteratorColecaoCategoria = colecao.iterator();
        	
        	Categoria categoria = null;
        	String quantidadeEconomia = null;
        	int valor = 0;
        	while (iteratorColecaoCategoria.hasNext()) {
    			categoria = (Categoria) iteratorColecaoCategoria.next();
    			valor++;
    			if (requestMap.get("categoria"
    					+ categoria.getId()) != null) {

    				quantidadeEconomia = (requestMap.get("categoria" + categoria.getId()))[0];

    				if (quantidadeEconomia == null
    						|| quantidadeEconomia.equalsIgnoreCase("")) {
    					throw new ActionServletException(
    							"atencao.required", null,
    							"Quantidade de Economias");
    				}

    				categoria.setQuantidadeEconomiasCategoria(new Integer(quantidadeEconomia));
    			}
        	}
        	if(valor == 0)
    		{
    			sessao.setAttribute("existeColecao","nao");
    		}else{
    			sessao.removeAttribute("existeColecao");
			}
        }
        if(sessao.getAttribute("colecaoSubcategoria") != null)
        {
        	Collection colecao = (Collection) sessao.getAttribute("colecaoSubcategoria");
        	Iterator iteratorColecaoSubcategoria = colecao.iterator();
        	
        	Subcategoria subcategoria = null;
        	String quantidadeEconomia = null;
        	int valor = 0;
    		while (iteratorColecaoSubcategoria.hasNext()) {
    			subcategoria = (Subcategoria) iteratorColecaoSubcategoria.next();
    			valor++;
    			if (requestMap.get("subcategoria"
    					+ subcategoria.getId()) != null) {

    				quantidadeEconomia = (requestMap.get("subcategoria" + subcategoria.getId()))[0];

    				if (quantidadeEconomia == null
    						|| quantidadeEconomia.equalsIgnoreCase("")) {
    					throw new ActionServletException(
    							"atencao.required", null,
    							"Quantidade de Economias");
    				}

    				subcategoria.setQuantidadeEconomias(new Integer(quantidadeEconomia));
    			}
        	}
    		if(valor == 0)
    		{
    			sessao.setAttribute("existeColecao","nao");
    		}else{
    			sessao.removeAttribute("existeColecao");
			}
        }
        if(sessao.getAttribute("colecaoDebitoCobrado") != null)
        {
        	Collection colecaoDebito = (Collection) sessao.getAttribute("colecaoDebitoCobrado");
        	Iterator iteratorColecaoDebito = colecaoDebito.iterator();
        	
        	DebitoCobrado debitoCobrado = null;
        	String valor = null;
        	BigDecimal valor2 = new BigDecimal ("0.00"); 
    		
        	while (iteratorColecaoDebito.hasNext()) {
    			debitoCobrado = (DebitoCobrado) iteratorColecaoDebito.next();
    			// valor minimo		
    
    			if (requestMap.get("debitoCobrado"
    					+ GcomAction.obterTimestampIdObjeto(debitoCobrado)) != null) {

    				valor = (requestMap.get("debitoCobrado" + GcomAction.obterTimestampIdObjeto(debitoCobrado)))[0];
    				
    				if (valor == null
    						|| valor.equalsIgnoreCase("")) {
    					throw new ActionServletException(
    							"atencao.required", null,
    							"Débito Cobrado");
    				}
    				else{
    					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
    				}
    				
    				debitoCobrado.setValorPrestacao(valor2);
    			}
        	}
        }
        if(sessao.getAttribute("colecaoCreditoRealizado") != null)
        {
        	Collection colecaoCredito = (Collection) sessao.getAttribute("colecaoCreditoRealizado");
        	Iterator iteratorColecaoCredito = colecaoCredito.iterator();
        	
        	CreditoRealizado creditoRealizado = null;
        	String valor = null;
        	BigDecimal valor2 = new BigDecimal ("0.00"); 
    		
        	while (iteratorColecaoCredito.hasNext()) {
    			creditoRealizado = (CreditoRealizado) iteratorColecaoCredito.next();
    			// valor minimo
    			if (requestMap.get("creditoRealizado"
    					+ GcomAction.obterTimestampIdObjeto(creditoRealizado)) != null) {

    				valor = (requestMap.get("creditoRealizado" + GcomAction.obterTimestampIdObjeto(creditoRealizado)))[0];
    				
    				if (valor == null
    						|| valor.equalsIgnoreCase("")) {
    					throw new ActionServletException(
    							"atencao.required", null,
    							"Crédito Realizado");
    				}
    				else{
    					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
    				}
    				
    				creditoRealizado.setValorCredito(valor2);
    			}
        	}
        }
        if(sessao.getAttribute("situacaoAguaContaAntes") == null){
        	sessao.setAttribute("situacaoAguaContaAntes",retificarContaActionForm.getSituacaoAguaConta());
        }
        if(sessao.getAttribute("situacaoEsgotoContaAntes") == null){
        	sessao.setAttribute("situacaoEsgotoContaAntes",retificarContaActionForm.getSituacaoEsgotoConta());
        }
        if(sessao.getAttribute("consumoEsgotoAntes") == null){
        	sessao.setAttribute("consumoEsgotoAntes",retificarContaActionForm.getConsumoEsgoto());
        }
        if(sessao.getAttribute("consumoAguaAntes") == null){
        	sessao.setAttribute("consumoAguaAntes",retificarContaActionForm.getConsumoAgua());
        }
        if(sessao.getAttribute("percentualEsgotoAntes") == null){
        	String percentualEsgotoAntes = null;
        	if(retificarContaActionForm.getPercentualEsgoto() != null){
        	percentualEsgotoAntes = retificarContaActionForm.getPercentualEsgoto().toString().replace(",","");
        	percentualEsgotoAntes = percentualEsgotoAntes.toString().replace(".","");
        	}else{percentualEsgotoAntes = "1";
        	}
        	sessao.setAttribute("percentualEsgotoAntes",percentualEsgotoAntes);
        }
        if(sessao.getAttribute("vencimentoContaAntes") == null){
        	//sessao.setAttribute("vencimentoContaAntes",retificarContaActionForm.getVencimentoConta());
        	sessao.setAttribute("vencimentoContaAntes",Util.formatarData(contaSelecao.getDataVencimentoConta()));
        }
        sessao.setAttribute("contaID",idConta);
        
        // Adicionado por Bruno Barros, 25/07/2008
        // Carregamos todas as tarifas de consumo ativas
        if ( sessao.getAttribute( "colecaoConsumoTarifa" ) == null ){
            FiltroConsumoTarifa filtro = new FiltroConsumoTarifa();
            filtro.adicionarParametro( new ParametroSimples( FiltroConsumoTarifa.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ) );
            Collection<ConsumoTarifa> colConsumoTarifa = Fachada.getInstancia().pesquisar( filtro, ConsumoTarifa.class.getName() );
            
            sessao.setAttribute( "colecaoConsumoTarifa", colConsumoTarifa );
        }
        
        // Verificamos se a conta possue tarifa de consumo
        if ( contaSelecao.getConsumoTarifa() != null ){
            retificarContaActionForm.setIdConsumoTarifa( contaSelecao.getConsumoTarifa().getId()+"" );
        }     
        
        // Verificar a permissão especial da tarifa de consumo
        boolean temPermissaoAlterarTarifaConsumo = fachada.verificarPermissaoEspecial( PermissaoEspecial.ALTERAR_TARIFA_CONSUMO_RETIFICAR_CONTA , (Usuario) sessao.getAttribute("usuarioLogado") );
        httpServletRequest.setAttribute("temPermissaoAlterarTarifaConsumo",temPermissaoAlterarTarifaConsumo);
        
        boolean alterarValorSugeridoEmTipoDebito = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO,
						this.getUsuarioLogado(httpServletRequest));

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
				alterarValorSugeridoEmTipoDebito);
        
        // Fim por Bruno Barros, 25/07/2008
        
        //Limpando os campos após remoção ou inserção de categorias ou débitos
        if (reloadPage != null && !reloadPage.equalsIgnoreCase("")){
        	
        	if(selecionouMotivo != null && !selecionouMotivo.equalsIgnoreCase("")){
        		 verificarOcorrenciasMesmoMotivoAno(new Integer(retificarContaActionForm.getMotivoRetificacaoID()),
        				new Integer(retificarContaActionForm.getIdImovel()), sessao,sistemaParametro,temPermissaoParaRetificarContaNorma);
        	}else{
            	retificarContaActionForm.setValorAgua("");
            	retificarContaActionForm.setValorEsgoto("");
            	retificarContaActionForm.setValorDebito("");
            	retificarContaActionForm.setValorCredito("");
            	retificarContaActionForm.setValorTotal("");
        	}

        }
        
        //CRC4795 - Vivianne Sousa 23/09/2010 - analista:Nelson Carvalho
        boolean temPermissaoNaoVerificarConsumoEsgoto = fachada.
        	verificarPermissaoRetificarContaSemVerificarConsumoEsgoto(usuarioLogado);
        if(temPermissaoNaoVerificarConsumoEsgoto){
        	 httpServletRequest.setAttribute("temPermissaoNaoVerificarConsumoEsgoto",1);
        }else{
        	 httpServletRequest.setAttribute("temPermissaoNaoVerificarConsumoEsgoto",2);
        }
        
        //[FS0038]-habilitar/desabilitar campos para retificação
        habilitarCampos(retificarContaActionForm.getMotivoRetificacaoID(),httpServletRequest,sistemaParametro,temPermissaoParaRetificarContaNorma);
        
        return retorno;
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
	
	/*[FS0038]-habilitar/desabilitar campos para retificação
	 * Vivianne Sousa - 14/02/2011
	 * RM4132 - analista responsável:Jeferson Pedrosa
	 */
	public void habilitarCampos(String idMotivoSelecionado,HttpServletRequest httpServletRequest,
			SistemaParametro sistemaParametro,boolean temPermissaoParaRetificarContaNorma){
		
		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			//Caso a Empresa esteja na Norma de Retificação de Conta 
			
			String habilitaSituacaoAgua = "2";
			String habilitaConsumoAgua = "2";
			String habilitaLeituraAnterior = "2";
			String habilitaLeituraAtual = "2";
			String habilitaSituacaoEsgoto = "2";
			String habilitaConsumoEsgoto = "2";
			String habilitaPercentualEsgoto = "2";
			String habilitaPercentualColeta = "2";
			String habilitaVolumePoco = "2";
			String habilitaLeituraAnteriorPoco = "2";
			String habilitaLeituraAtualPoco = "2";
			String habilitaListaCategoriasEQuantidadesEconomias = "2";
			String habilitaListaDebitos = "2";
			String habilitaListaCreditos = "2";
			
			if(idMotivoSelecionado != null && !idMotivoSelecionado.equalsIgnoreCase("")
				&& !idMotivoSelecionado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				Collection colecaoTabelaColuna = getFachada().pesquisaTabelaColunaContaMotivoRetificacaoColuna(new Integer(idMotivoSelecionado));		
				
				Iterator iterTabelaColuna = colecaoTabelaColuna.iterator();
				
				while (iterTabelaColuna.hasNext()) {
					TabelaColuna tabelaColuna = (TabelaColuna) iterTabelaColuna.next();
					
					String nomeAbreviado = tabelaColuna.getNomeAbreviado();
					
					if (nomeAbreviado != null) {
						
						if(nomeAbreviado.equalsIgnoreCase("SITAGU")){
							habilitaSituacaoAgua = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("CONAGU")){
							habilitaConsumoAgua = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("LANAGU")){
							habilitaLeituraAnterior = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("LATAGU")){
							habilitaLeituraAtual = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("SITESG")){
							habilitaSituacaoEsgoto = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("CONESG")){
							habilitaConsumoEsgoto = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("PERESG")){
							habilitaPercentualEsgoto = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("PERCOL")){
							habilitaPercentualColeta = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("VOLPOC")){
							habilitaVolumePoco = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("LANPOC")){
							habilitaLeituraAnteriorPoco = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("LATPOC")){
							habilitaLeituraAtualPoco = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("CATECO")){
							habilitaListaCategoriasEQuantidadesEconomias = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("DEBITO")){
							habilitaListaDebitos = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("CREDIT")){
							habilitaListaCreditos = "1";
						}
					}
				}
			}
					
			httpServletRequest.setAttribute("habilitaSituacaoAgua",habilitaSituacaoAgua);
			httpServletRequest.setAttribute("habilitaConsumoAgua",habilitaConsumoAgua);
			httpServletRequest.setAttribute("habilitaLeituraAnterior",habilitaLeituraAnterior);
			httpServletRequest.setAttribute("habilitaLeituraAtual",habilitaLeituraAtual);
			httpServletRequest.setAttribute("habilitaSituacaoEsgoto",habilitaSituacaoEsgoto);
			httpServletRequest.setAttribute("habilitaConsumoEsgoto",habilitaConsumoEsgoto);
			httpServletRequest.setAttribute("habilitaPercentualEsgoto",habilitaPercentualEsgoto);
			httpServletRequest.setAttribute("habilitaPercentualColeta",habilitaPercentualColeta);
			httpServletRequest.setAttribute("habilitaVolumePoco",habilitaVolumePoco);
			httpServletRequest.setAttribute("habilitaLeituraAnteriorPoco",habilitaLeituraAnteriorPoco);
			httpServletRequest.setAttribute("habilitaLeituraAtualPoco",habilitaLeituraAtualPoco);
			httpServletRequest.setAttribute("habilitaListaCategoriasEQuantidadesEconomias",habilitaListaCategoriasEQuantidadesEconomias);
			httpServletRequest.setAttribute("habilitaListaDebitos",habilitaListaDebitos);
			httpServletRequest.setAttribute("habilitaListaCreditos",habilitaListaCreditos);

		}
		
	}
	
}



package gcom.gui.portal;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ConcluirParcelamentoDebitosHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe responsável por efetuar o parcelamento dos débitos
 * de um imóvel através da loja virtual. Os métodos chamados  
 * são os métodos já existentes do GSAN
 * 
 * @author Diogo Peixoto
 *
 */
public class EfetuarParcelamentoDebitosPortalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse httpServletResponse) {
	
		ActionForward retorno = actionMapping.findForward("recarregarPagina");
        HttpSession sessao = request.getSession(false);
		EfetuarParcelamentoDebitosPortalActionForm form = (EfetuarParcelamentoDebitosPortalActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		String parcelamentoEfetuado = (String)sessao.getAttribute("parcelamentoEfetuado");
		if(parcelamentoEfetuado == null){
			//Usuário responsável por fazer o parcelamneto no portão será por padrão usuário internet.
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, "INTERNET"));
			Usuario usuarioLogado = (Usuario) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName()));
			
	        String codigoImovel = form.getMatriculaImovel();
	        Date dataParcelamento = new Date();
	        String indicadorRestabelecimento = form.getIndicadorRestabelecimento();
	        String indicadorContasRevisao = form.getIndicadorContasRevisao();
	        String indicadorGuiasPagamento = form.getIndicadorGuiasPagamento();
	        String indicadorAcrescimosImpotualidade = form.getIndicadorAcrescimosImpotualidade();
	        String indicadorDebitosACobrar = form.getIndicadorDebitosACobrar();
	        String indicadorCreditoARealizar = form.getIndicadorCreditoARealizar();
	        String cpfClienteParcelamentoDigitado = usuarioLogado.getCpf();
	        String indicadorDividaAtiva = form.getIndicadorDividaAtiva();
			
	        /*
	         * Recupera da sessão as opções de parcelamento para verificar se foi selecionada
	         * alguma opção de parcelamento.
	         */
	        Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = (Collection<OpcoesParcelamentoHelper>) 
				sessao.getAttribute("colecaoOpcoesParcelamento");

	        //Variáveis que irão armazenar os valores de acordo com a opção de parcelamento selecionada.
	        Short numeroPrestacoes = new Short("0");
			BigDecimal valorPrestacao = new BigDecimal("0.00");
			BigDecimal valorEntradaMinima = new BigDecimal("0.00");
			BigDecimal taxaJuros = new BigDecimal("0.00");

			//Variáveis para o valor a ser parcelado
			BigDecimal valorASerParcelado = new BigDecimal("0.00");
			
			if(!Util.isVazioOrNulo(colecaoOpcoesParcelamento) ){
				//Rótulo do loop de parcelamento
				if(Util.verificarNaoVazio(form.getIndicadorQuantidadeParcelas())){
					loopOpcaoParcelamento:
						for(OpcoesParcelamentoHelper opcao : colecaoOpcoesParcelamento){
							/*
							 * Verifica se o usuário escolheu uma opção de parcelamento, caso tenha escolhido configura
							 * os valores do parcelamento e sai do loop.
							 */
							if((form.getIndicadorQuantidadeParcelas()).equals(opcao.getQuantidadePrestacao().toString()) ){
								//valorJurosParcelamento = opcoesParcelamentoHelper.getTaxaJuros(); 
								numeroPrestacoes = opcao.getQuantidadePrestacao();
								valorPrestacao = opcao.getValorPrestacao();
								valorEntradaMinima = opcao.getValorEntradaMinima();
								taxaJuros = opcao.getTaxaJuros();
								valorASerParcelado = opcao.getValorPrestacao().multiply(new BigDecimal(opcao.getQuantidadePrestacao()));
								break loopOpcaoParcelamento;
							}
						}
				}else{
					request.setAttribute("opcaoParcelamentoNaoInformada", true);
					request.setAttribute("erroEfetuarParcelamento", "SIM");
					return retorno;
				}
			}else{
				request.setAttribute("opcaoParcelamentoNaoInformada", true);
				request.setAttribute("erroEfetuarParcelamento", "SIM");
				return retorno;
			}
			
			//---------------------- Recupera os dados do formulário. ----------------------//
			BigDecimal valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal(form.getValorEntradaInformado());
			
			BigDecimal valorDebitoTotalAtualizado = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorDebitoTotalAtualizado())){
				valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoTotalAtualizado());
			}
			
			if(valorEntradaInformado.doubleValue() != valorEntradaMinima.doubleValue()){
				request.setAttribute("recalcularOpcaoParcelamento", true);
				request.setAttribute("erroEfetuarParcelamento", "SIM");
				return retorno;
			}
			
			//verificar valor da entrada mínima permitida
			if (valorEntradaInformado != null && valorEntradaInformado.compareTo(valorEntradaMinima.setScale(
					Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)) == -1 && valorEntradaInformado.intValue() != 0) {
				request.setAttribute("entradaInformadaMenorMinima", true);
				request.setAttribute("entradaMinima", valorEntradaMinima);
				request.setAttribute("erroEfetuarParcelamento", "SIM");
				return retorno;
			}
			
			BigDecimal valorTotalContaValores = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorTotalContaValores())){
				valorTotalContaValores = Util.formatarMoedaRealparaBigDecimal(form.getValorTotalContaValores());
			}
			BigDecimal valorGuiasPagamento = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorGuiasPagamento())){
				valorGuiasPagamento = Util.formatarMoedaRealparaBigDecimal(form.getValorGuiasPagamento());
			}
			
			BigDecimal valorDebitoACobrarServico = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorDebitoACobrarServico())){
				valorDebitoACobrarServico = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarServico());
			}
			
			BigDecimal valorDebitoACobrarParcelamento = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorDebitoACobrarParcelamento())){
				valorDebitoACobrarParcelamento = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamento());
			}
			
			BigDecimal valorCreditoARealizar = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorCreditoARealizar())){
				valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal(form.getValorCreditoARealizar());
			}
			
			BigDecimal valorCreditosAnterioresCurtoPrazo = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorCreditosAnterioresCurtoPrazo())){
				valorCreditosAnterioresCurtoPrazo = Util.formatarMoedaRealparaBigDecimal(form.getValorCreditosAnterioresCurtoPrazo());
			}
			
			BigDecimal valorCreditosAnterioresLongoPrazo = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorCreditosAnterioresLongoPrazo())){
				valorCreditosAnterioresLongoPrazo = Util.formatarMoedaRealparaBigDecimal(form.getValorCreditosAnterioresLongoPrazo());
			}
			
			BigDecimal valorTotalCreditosAnteriores = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorTotalCreditosAnteriores())){
				valorTotalCreditosAnteriores = Util.formatarMoedaRealparaBigDecimal(form.getValorTotalCreditosAnteriores());
			}
			
			BigDecimal valorAtualizacaoMonetaria = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorAtualizacaoMonetaria())){
				valorAtualizacaoMonetaria = Util.formatarMoedaRealparaBigDecimal(form.getValorAtualizacaoMonetaria());
			}
			
			BigDecimal valorJurosMora = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorJurosMora())){
				valorJurosMora = Util.formatarMoedaRealparaBigDecimal(form.getValorJurosMora());
			}
			
			BigDecimal valorMulta = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorMulta())){
				valorMulta = Util.formatarMoedaRealparaBigDecimal(form.getValorMulta());
			}
			
			BigDecimal descontoAcrescimosImpontualidade = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getDescontoAcrescimosImpontualidade())){
				descontoAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal(form.getDescontoAcrescimosImpontualidade());
			}
			
			BigDecimal descontoSancoesRDEspecial = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getDescontoSancoesRDEspecial())){
				descontoSancoesRDEspecial = Util.formatarMoedaRealparaBigDecimal(form.getDescontoSancoesRDEspecial());
			}
			
			
			BigDecimal descontoTarifaSocialRDEspecial = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getDescontoTarifaSocialRDEspecial())){
				descontoTarifaSocialRDEspecial = Util.formatarMoedaRealparaBigDecimal(form.getDescontoTarifaSocialRDEspecial());
			}
			
			BigDecimal descontoAntiguidadeDebito = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getDescontoAntiguidadeDebito())){
				descontoAntiguidadeDebito = Util.formatarMoedaRealparaBigDecimal(form.getDescontoAntiguidadeDebito());
			}
			
			BigDecimal descontoInatividadeLigacaoAgua = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getDescontoInatividadeLigacaoAgua())){ 
				descontoInatividadeLigacaoAgua = Util.formatarMoedaRealparaBigDecimal(form.getDescontoInatividadeLigacaoAgua());
			}

			BigDecimal percentualDescontoAcrescimosImpontualidade = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getPercentualDescontoAcrescimosImpontualidade())){
				percentualDescontoAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoAcrescimosImpontualidade());
			}
			
			BigDecimal percentualDescontoAntiguidadeDebito = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getPercentualDescontoAntiguidadeDebito())){
				percentualDescontoAntiguidadeDebito = Util.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoAntiguidadeDebito());
			}

			BigDecimal percentualDescontoInatividadeLigacaoAgua = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getPercentualDescontoInatividadeLigacaoAgua())){
				percentualDescontoInatividadeLigacaoAgua = Util.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoInatividadeLigacaoAgua());
			}
			
			BigDecimal valorAcrescimosImpontualidade = BigDecimal.ZERO;
			if(Util.verificarNaoVazio(form.getValorAcrescimosImpontualidade())){
				valorAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal(form.getValorAcrescimosImpontualidade());
			}

			Integer parcelamentoPerfilId = new Integer(form.getParcelamentoPerfilId());

			
			BigDecimal valorDebitoACobrarServicoLongoPrazo = new BigDecimal("0.00");
			if(Util.verificarNaoVazio(form.getValorDebitoACobrarServicoLongoPrazo())){
				valorDebitoACobrarServicoLongoPrazo = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarServicoLongoPrazo());
			}	
			
			BigDecimal valorDebitoACobrarServicoCurtoPrazo = new BigDecimal("0.00");
			if(Util.verificarNaoVazio(form.getValorDebitoACobrarServicoCurtoPrazo())){
				valorDebitoACobrarServicoCurtoPrazo = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarServicoCurtoPrazo());
			}
			
			BigDecimal valorDebitoACobrarParcelamentoLongoPrazo = new BigDecimal("0.00");
			if(Util.verificarNaoVazio(form.getValorDebitoACobrarParcelamentoLongoPrazo())){
				valorDebitoACobrarParcelamentoLongoPrazo = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamentoLongoPrazo());
			}
			
			BigDecimal valorDebitoACobrarParcelamentoCurtoPrazo = new BigDecimal("0.00");
			if(Util.verificarNaoVazio(form.getValorDebitoACobrarParcelamentoCurtoPrazo())){
				valorDebitoACobrarParcelamentoCurtoPrazo = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamentoCurtoPrazo());
			}
			//---------------------- Fim da recuperação dos dados do formulário. ----------------------//
			
			
			// Valor a ser Negociado
			BigDecimal valorASerNegociado = new BigDecimal("0.00");
			BigDecimal valorDesconto = new BigDecimal("0.00");
			valorDesconto = valorDesconto.add(descontoAcrescimosImpontualidade);
			valorDesconto = valorDesconto.add(descontoAntiguidadeDebito);
			valorDesconto = valorDesconto.add(descontoInatividadeLigacaoAgua);
			form.setValorDesconto(Util.formatarMoedaReal(valorDesconto));
			valorASerNegociado = valorDebitoTotalAtualizado.subtract(valorDesconto);
			
			
			// Montar o objeto Imovel para as inserções no banco
			Imovel imovel = null;
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, codigoImovel));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CONSUMO_TARIFA);
			Collection<Imovel> imovelPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());

			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				imovel = imovelPesquisado.iterator().next();
			}
			
			//--------------- Recupera as coleções de contas, guiaPagamentos, debitos e creditos ---------------//
			Collection<ContaValoresHelper> colecaoContaValores = new ArrayList<ContaValoresHelper>();
			if (sessao.getAttribute("colecaoContaValores")!= null){
				colecaoContaValores = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores");
			}
			
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = new ArrayList<GuiaPagamentoValoresHelper>();
			if (sessao.getAttribute("colecaoGuiaPagamentoNegociacao") != null  || indicadorGuiasPagamento.equals("1")){
				colecaoGuiaPagamentoValores = (Collection<GuiaPagamentoValoresHelper>) sessao.getAttribute("colecaoGuiaPagamentoNegociacao");
			}
			
			Collection<DebitoACobrar> colecaoDebitoACobrar = new ArrayList<DebitoACobrar>();
			if(sessao.getAttribute("colecaoDebitoACobrar") != null || indicadorDebitosACobrar.equals("1")){
				colecaoDebitoACobrar = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitoACobrar");
			}

			Collection<CreditoARealizar> colecaoCreditoARealizar = new ArrayList<CreditoARealizar>();
			if (sessao.getAttribute("colecaoCreditoARealizar") != null || indicadorCreditoARealizar.equals("1")){
				colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizar");
			}
			//--------------- Fim da recuperação das coleções de contas, guiaPagamentos, debitos e creditos ---------------//
			
			/*Configura o cliente responsável pelo parcelamento que 
			 * será o cliente cujo CPF foi digitado na tela de 
			 * parcelamentos_debitos_portal_efetuar.jsp
			 */
			Cliente cliente = null;
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, form.getCpfCliente()));
			Collection<Cliente> clients = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			if(!Util.isVazioOrNulo(clients)){
				cliente = (Cliente) Util.retonarObjetoDeColecao(clients);
			}
			
			/*
			 * Colocado por Raphael Rossiter em 25/08/2008 - Analista: Rosana Carvalho
			 * 
			 * O sistema verifica se o parcelamento é para ser incluído obrigatoriamente já confirmado
			 */
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			String indicadorConfirmacaoParcelamento = null;
			if (sistemaParametro.getIndicadorParcelamentoConfirmado() == ConstantesSistema.SIM.shortValue()){
				indicadorConfirmacaoParcelamento = ConstantesSistema.SIM.toString();
			}else{
				indicadorConfirmacaoParcelamento = ConstantesSistema.NAO.toString();
			}
			
			//CARREGANDO INFORMAÇÕES PARA CONCLUSÃO DO PARCELAMENTO
			//=============================================================================================================
			NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = (NegociacaoOpcoesParcelamentoHelper) sessao.getAttribute("opcoesParcelamento");
			
			Collection colecaoContasEmAntiguidade = null;
			
			if (opcoesParcelamento != null){
				colecaoContasEmAntiguidade = opcoesParcelamento.getColecaoContasEmAntiguidade();
			}
			
			ConcluirParcelamentoDebitosHelper concluirParcelamentoDebitosHelper = new ConcluirParcelamentoDebitosHelper(
					colecaoContaValores, 
					colecaoGuiaPagamentoValores,
					colecaoDebitoACobrar, 
					colecaoCreditoARealizar, 
					indicadorRestabelecimento,
					indicadorContasRevisao, 
					indicadorGuiasPagamento, 
					indicadorAcrescimosImpotualidade,
					indicadorDebitosACobrar, 
					indicadorCreditoARealizar, 
					indicadorDividaAtiva,
					imovel, 
					valorEntradaInformado,
					valorASerNegociado, 
					valorASerParcelado, 
					dataParcelamento, 
					valorTotalContaValores,
					valorGuiasPagamento, 
					valorDebitoACobrarServico, 
					valorDebitoACobrarParcelamento,
					valorCreditoARealizar, 
					valorAtualizacaoMonetaria, 
					valorJurosMora, 
					valorMulta,
					valorDebitoTotalAtualizado,
					new BigDecimal(0.00),
					descontoAcrescimosImpontualidade, 
					descontoAntiguidadeDebito,
					descontoInatividadeLigacaoAgua, 
					percentualDescontoAcrescimosImpontualidade, 
					percentualDescontoAntiguidadeDebito, 
					percentualDescontoInatividadeLigacaoAgua, 
					parcelamentoPerfilId, 
					valorAcrescimosImpontualidade, 
					valorDebitoACobrarServicoLongoPrazo,
					valorDebitoACobrarServicoCurtoPrazo, 
					valorDebitoACobrarParcelamentoLongoPrazo,
					valorDebitoACobrarParcelamentoCurtoPrazo, 
					numeroPrestacoes, 
					valorPrestacao,
					valorEntradaMinima,
					taxaJuros,
					indicadorConfirmacaoParcelamento,
					cliente,
					usuarioLogado,
					cpfClienteParcelamentoDigitado,
					descontoSancoesRDEspecial,
					descontoTarifaSocialRDEspecial, colecaoContasEmAntiguidade,
					valorCreditosAnterioresCurtoPrazo, valorCreditosAnterioresLongoPrazo, valorTotalCreditosAnteriores);
			//=============================================================================================================
			
			try{
				Integer idParcelamento = fachada.concluirParcelamentoDebitosPortal(concluirParcelamentoDebitosHelper, usuarioLogado);
				sessao.setAttribute("idParcelamento", idParcelamento.toString());

				//[FS0013] - Verificar sucesso da transação
				// Monta a página de sucesso
				FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.PARCELAMENTO_ID,new Integer(idParcelamento)));
				
				Collection<GuiaPagamento> colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoGuiaPagamento)){
					if(indicadorRestabelecimento.equals("1")){
						request.setAttribute("mensagemReativacao", true);
					}else{
						retorno = actionMapping.findForward("gerarDocumentos");
					}
					GuiaPagamento guiaPagamento = (GuiaPagamento)Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
					String idGuiaPagamento = guiaPagamento .getId().toString();
					sessao.setAttribute("idGuiaPagamento", idGuiaPagamento);
				}else if (retorno.getName().equalsIgnoreCase("recarregarPagina")) {
					retorno = actionMapping.findForward("gerarDocumentos");
				}
			}catch (FachadaException e) {
				request.setAttribute("parcelamentoJaEfetuado", true);
			}
		}else{
			request.setAttribute("parcelamentoJaEfetuado", true);
		}
		return retorno;
	}
	
	//Vivianne Sousa 10/07/2008
	public boolean verificarValorEntradaMinimaPermitida(Short numeroPrestacoesSelecionada, BigDecimal valorEntrada, 
			BigDecimal valorDebitoTotalAtualizado, ParcelamentoPerfil parcelamentoPerfil){
		boolean valorMinimoPermitido = true; 
		//caso o indicador de entrada mínima seja SIM
		if (parcelamentoPerfil.getIndicadorEntradaMinima() != null && parcelamentoPerfil.getIndicadorEntradaMinima().equals(ConstantesSistema.SIM)){
			BigDecimal valorPrestacao = Util.dividirArredondando(valorDebitoTotalAtualizado, new BigDecimal(numeroPrestacoesSelecionada));
			valorPrestacao = valorPrestacao.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
			
			/*
			 * O sistema verifica se a entrada informada está menor que o valor 
			 * da prestação calculada da opção de quantidade de parcelas selecionada 
			 */
			if(valorPrestacao.compareTo(valorEntrada) == 1){
				valorMinimoPermitido = false;
			}
		}
		return valorMinimoPermitido;
	}
}

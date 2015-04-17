package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.Cliente;
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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * UC0214] Efetuar Parcelamento de D�bitos
 * 
 * @author Roberta Costa
 * @date 08/03/2006  
 */
public class ConcluirEfetuarParcelamentoDebitosAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        
    	ActionForward retorno = actionMapping.findForward("telaSucesso");

        HttpSession sessao = httpServletRequest.getSession(false);

		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
        // ABA 1 - 6.1 Caso o usuario confirme o parecelamento
        String codigoImovel = (String) efetuarParcelamentoDebitosActionForm.get("matriculaImovel");
		Integer situacaoAguaId = new Integer ((String)(efetuarParcelamentoDebitosActionForm.get("situacaoAguaId")));
        Date dataParcelamento = Util.converteStringParaDate((String) efetuarParcelamentoDebitosActionForm.get("dataParcelamento"));
        String resolucaoDiretoria = (String) efetuarParcelamentoDebitosActionForm.get("resolucaoDiretoria");
        String fimIntervaloParcelamento = (String) efetuarParcelamentoDebitosActionForm.get("fimIntervaloParcelamento");
        String indicadorRestabelecimento = (String) efetuarParcelamentoDebitosActionForm.get("indicadorRestabelecimento");
        String indicadorContasRevisao = (String) efetuarParcelamentoDebitosActionForm.get("indicadorContasRevisao");
        String indicadorGuiasPagamento = (String) efetuarParcelamentoDebitosActionForm.get("indicadorGuiasPagamento");
        String indicadorAcrescimosImpotualidade = (String) efetuarParcelamentoDebitosActionForm.get("indicadorAcrescimosImpotualidade");
        String indicadorDebitosACobrar = (String) efetuarParcelamentoDebitosActionForm.get("indicadorDebitosACobrar");
        String indicadorCreditoARealizar = (String) efetuarParcelamentoDebitosActionForm.get("indicadorCreditoARealizar");
        String indicadorConfirmacaoParcelamento = (String) efetuarParcelamentoDebitosActionForm.get("indicadorConfirmacaoParcelamento");
        String cpfClienteParcelamentoDigitado = (String) efetuarParcelamentoDebitosActionForm.get("cpfClienteParcelamentoDigitado");
        String indicadorDividaAtiva = (String) efetuarParcelamentoDebitosActionForm.get("indicadorDividaAtiva");
        ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil)sessao.getAttribute("parcelamentoPerfil");
        
        // Caso o fim do parcelamento n�o seja informado
		if( fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals("")){
			fimIntervaloParcelamento = "12/9999";
		}
		
        // [FS0009] - Verificar preenchimento dos campos
        if( codigoImovel == null || codigoImovel.trim().equals("") ){
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Matricula do Im�vel");
		}
        if( dataParcelamento == null || dataParcelamento.equals("") ){
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Data do Parcelamento");
		}
        if( resolucaoDiretoria == null || resolucaoDiretoria.trim().equals("") ){
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "RD do Parcelamento");
		}
        if (situacaoAguaId.equals(LigacaoAguaSituacao.SUPRIMIDO)
				|| situacaoAguaId.equals(LigacaoAguaSituacao.SUPR_PARC)
				|| situacaoAguaId.equals(LigacaoAguaSituacao.SUPR_PARC_PEDIDO)) {
	        if( indicadorRestabelecimento == null || indicadorRestabelecimento.trim().equals("") ){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Com Restabelecimento?");
			}
        }
        if( indicadorContasRevisao == null || indicadorContasRevisao.trim().equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Considerar Contas em Revis�o?");
		}
        if( indicadorGuiasPagamento == null || indicadorGuiasPagamento.trim().equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Considerar Guias de Pagamento?");
		}
        if( indicadorAcrescimosImpotualidade == null || indicadorAcrescimosImpotualidade.trim().equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Considerar Acr�scimos por Impontualidade?");
		}
        if( indicadorDebitosACobrar == null || indicadorDebitosACobrar.trim().equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Considerar D�bitos a Cobrar?");
		}

        if( indicadorCreditoARealizar == null || indicadorCreditoARealizar.trim().equals("") ){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "'Considerar Cr�ditos a Realizar?'");
		}
        
        // ABA 3 - Verifica se foi escolhido alguma op��o de parcelamento
        Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = (Collection<OpcoesParcelamentoHelper>)
			sessao.getAttribute("colecaoOpcoesParcelamento");

        Short numeroPrestacoes = new Short("0");
		BigDecimal valorPrestacao = new BigDecimal("0.00");
		BigDecimal valorEntradaMinima = new BigDecimal("0.00");
		BigDecimal taxaJuros = new BigDecimal("0.00");
		if( colecaoOpcoesParcelamento != null && ! colecaoOpcoesParcelamento.isEmpty() ){
			Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
			boolean opcaoMarcada = false;
			while(opcoesParcelamentoValores.hasNext()) {
				OpcoesParcelamentoHelper opcoesParcelamentoHelper = 
					(OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
				if( ((String)efetuarParcelamentoDebitosActionForm.get("indicadorQuantidadeParcelas"))
						.equals(opcoesParcelamentoHelper.getQuantidadePrestacao().toString()) ){
					opcaoMarcada = true;
					//valorJurosParcelamento = opcoesParcelamentoHelper.getTaxaJuros(); 
					numeroPrestacoes = opcoesParcelamentoHelper.getQuantidadePrestacao();
					valorPrestacao = opcoesParcelamentoHelper.getValorPrestacao();
					valorEntradaMinima = opcoesParcelamentoHelper.getValorEntradaMinima();
					taxaJuros = opcoesParcelamentoHelper.getTaxaJuros();
				}
			}
			if( ! opcaoMarcada ){
				throw new ActionServletException(
					"atencao.pelo.menos.uma.opcao.parcelamento.marcada");
			}	
		}else{
			throw new ActionServletException(
				"atencao.parametros.obrigatorios.nao.selecionados");
		}
		
//		//[FS0012] Verificar exist�ncia de parcelamento no m�s
//		Collection<Parcelamento> colecaoParcelamento = fachada.verificarParcelamentoMesImovel(new Integer(codigoImovel));
//
//		if (colecaoParcelamento != null	&& !colecaoParcelamento.isEmpty()) {
//			throw new ActionServletException("atencao.debito.ja.parcelado.mes.faturamento.corrente",null);
//		}

		
        // Pega os dados do formul�rio
		BigDecimal valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal(
				(String)(efetuarParcelamentoDebitosActionForm.get("valorEntradaInformado")));
		
		BigDecimal valorDebitoTotalAtualizado = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado").equals("")){
			valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorDebitoTotalAtualizado")));
		}
		
		//verificar valor da entrada m�nima permitida
		verificarValorEntradaMinimaPermitida( numeroPrestacoes,
				valorEntradaInformado,  valorDebitoTotalAtualizado, parcelamentoPerfil);
		
		BigDecimal valorTotalContaValores = BigDecimal.ZERO;
		if (efetuarParcelamentoDebitosActionForm.get("valorTotalContaValores") != null
				&& !efetuarParcelamentoDebitosActionForm.get("valorTotalContaValores").equals("")){
			valorTotalContaValores = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorTotalContaValores")));
		}
		BigDecimal valorGuiasPagamento = BigDecimal.ZERO;
		if (efetuarParcelamentoDebitosActionForm.get("valorGuiasPagamento") !=null
				&& !efetuarParcelamentoDebitosActionForm.get("valorGuiasPagamento").equals("")){
			valorGuiasPagamento = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorGuiasPagamento")));
		}
		
		BigDecimal valorDebitoACobrarServico = BigDecimal.ZERO;
		if (efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServico") != null
				&& !efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServico").equals("")){
			valorDebitoACobrarServico = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServico")));
		}
		
		BigDecimal valorDebitoACobrarParcelamento = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamento")!=null
				&& !efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamento").equals("")){
			valorDebitoACobrarParcelamento = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamento")));
		}
		
		BigDecimal valorCreditoARealizar = BigDecimal.ZERO;
		if (efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar") != null
				&& !efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar").equals("")){
			valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar")));
		}
		
		BigDecimal valorAtualizacaoMonetaria = BigDecimal.ZERO;
		if (efetuarParcelamentoDebitosActionForm.get("valorAtualizacaoMonetaria")!= null
				&& !efetuarParcelamentoDebitosActionForm.get("valorAtualizacaoMonetaria").equals("")){
			valorAtualizacaoMonetaria = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorAtualizacaoMonetaria")));
		}
		
		BigDecimal valorJurosMora = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("valorJurosMora") != null
				&& !efetuarParcelamentoDebitosActionForm.get("valorJurosMora").equals("")){
			valorJurosMora = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorJurosMora")));
		}
		
		BigDecimal valorMulta = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("valorMulta") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("valorMulta").equals("")){
			valorMulta = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorMulta")));
		}
		
		BigDecimal descontoAcrescimosImpontualidade = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("descontoAcrescimosImpontualidade") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("descontoAcrescimosImpontualidade").equals("")){
			descontoAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("descontoAcrescimosImpontualidade")));
		}
		
		BigDecimal descontoSancoesRDEspecial = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("descontoSancoesRDEspecial") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("descontoSancoesRDEspecial").equals("")){
			descontoSancoesRDEspecial = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("descontoSancoesRDEspecial")));
		}
		
		
		BigDecimal descontoTarifaSocialRDEspecial = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("descontoTarifaSocialRDEspecial") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("descontoTarifaSocialRDEspecial").equals("")){
			descontoTarifaSocialRDEspecial = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("descontoTarifaSocialRDEspecial")));
		}
		
		BigDecimal descontoAntiguidadeDebito = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("descontoAntiguidadeDebito") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("descontoAntiguidadeDebito").equals("")){
			descontoAntiguidadeDebito = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("descontoAntiguidadeDebito")));
		}
		
		BigDecimal descontoInatividadeLigacaoAgua = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("descontoInatividadeLigacaoAgua") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("descontoInatividadeLigacaoAgua").equals("")){
			descontoInatividadeLigacaoAgua = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("descontoInatividadeLigacaoAgua")));
		}

		BigDecimal percentualDescontoAcrescimosImpontualidade = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("percentualDescontoAcrescimosImpontualidade") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("percentualDescontoAcrescimosImpontualidade").equals("")){
			percentualDescontoAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("percentualDescontoAcrescimosImpontualidade")));
		}
		
		BigDecimal percentualDescontoAntiguidadeDebito = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("percentualDescontoAntiguidadeDebito") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("percentualDescontoAntiguidadeDebito").equals("")){
			percentualDescontoAntiguidadeDebito = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("percentualDescontoAntiguidadeDebito")));
		}

		BigDecimal percentualDescontoInatividadeLigacaoAgua = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("percentualDescontoInatividadeLigacaoAgua") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("percentualDescontoInatividadeLigacaoAgua").equals("")){
			percentualDescontoInatividadeLigacaoAgua = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("percentualDescontoInatividadeLigacaoAgua")));
		}
		
		BigDecimal valorAcrescimosImpontualidade = BigDecimal.ZERO;
		if(efetuarParcelamentoDebitosActionForm.get("valorAcrescimosImpontualidade") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("valorAcrescimosImpontualidade").equals("")){
			valorAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorAcrescimosImpontualidade")));
		}

		Integer parcelamentoPerfilId = new Integer(
				(String)(efetuarParcelamentoDebitosActionForm.get("parcelamentoPerfilId")));

		
		BigDecimal valorDebitoACobrarServicoLongoPrazo = new BigDecimal("0.00");
		if( efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServicoLongoPrazo") != null
				&& !efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServicoLongoPrazo").equals("")){
			valorDebitoACobrarServicoLongoPrazo = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServicoLongoPrazo")));
		}	
		
		BigDecimal valorDebitoACobrarServicoCurtoPrazo = new BigDecimal("0.00");
		if( efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServicoCurtoPrazo") != null
				&& !efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServicoCurtoPrazo").equals("")){
			valorDebitoACobrarServicoCurtoPrazo = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServicoCurtoPrazo")));
		}
		
		BigDecimal valorDebitoACobrarParcelamentoLongoPrazo = new BigDecimal("0.00");
		if( efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoLongoPrazo") != null
				&& !efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoLongoPrazo").equals("")){
			valorDebitoACobrarParcelamentoLongoPrazo = Util.formatarMoedaRealparaBigDecimal(
				(String)(efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoLongoPrazo")));
		}
		
		BigDecimal valorDebitoACobrarParcelamentoCurtoPrazo = new BigDecimal("0.00");
		if( efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoCurtoPrazo") != null
				&& !efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoCurtoPrazo").equals("")){
			valorDebitoACobrarParcelamentoCurtoPrazo = Util.formatarMoedaRealparaBigDecimal(
					(String)(efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamentoCurtoPrazo")));
		}
		
		// Valor a ser Negociado
		BigDecimal valorASerParcelado = new BigDecimal("0.00");
		BigDecimal valorASerNegociado = new BigDecimal("0.00");
//		boolean existeValor = false;
//		if( efetuarParcelamentoDebitosActionForm.get("valorASerParcelado") != null 
//				&& !efetuarParcelamentoDebitosActionForm.get("valorASerParcelado").equals("") ){
//			valorASerParcelado = Util.formatarMoedaRealparaBigDecimal(
//					(String)(efetuarParcelamentoDebitosActionForm.get("valorASerParcelado")));
////			existeValor = true;
//    	}	
//		if( efetuarParcelamentoDebitosActionForm.get("valorASerParcelado") != null 
//				&& !efetuarParcelamentoDebitosActionForm.get("valorASerParcelado").equals("") ){
//			valorASerNegociado = Util.formatarMoedaRealparaBigDecimal(
//					(String)(efetuarParcelamentoDebitosActionForm.get("valorNegociado")));
////			existeValor = true;
//    	}
//		if( !existeValor ){
			if( colecaoOpcoesParcelamento != null && ! colecaoOpcoesParcelamento.isEmpty() ){
				Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
				while(opcoesParcelamentoValores.hasNext()) {
					OpcoesParcelamentoHelper opcoesParcelamento = 
						(OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
					if( ((String)efetuarParcelamentoDebitosActionForm.get("indicadorQuantidadeParcelas"))
							.equals(opcoesParcelamento.getQuantidadePrestacao().toString()) ){
						valorASerParcelado = opcoesParcelamento.getValorPrestacao()
							.multiply(new BigDecimal(opcoesParcelamento.getQuantidadePrestacao())); 
					}
				}
			}
			
			BigDecimal valorDesconto = new BigDecimal("0.00");
			valorDesconto = valorDesconto.add(descontoAcrescimosImpontualidade);
			valorDesconto = valorDesconto.add(descontoAntiguidadeDebito);
			valorDesconto = valorDesconto.add(descontoInatividadeLigacaoAgua);
			
			efetuarParcelamentoDebitosActionForm
				.set("valorDesconto",Util.formatarMoedaReal(valorDesconto));
			
			valorASerNegociado = valorDebitoTotalAtualizado.subtract(valorDesconto);
//		}	
		
		// Montar o objeto Imovel para as inser��es no banco
		Imovel imovel = null;
		
		FiltroImovel filtroImovel = new FiltroImovel();
		
		filtroImovel
			.adicionarParametro(new ParametroSimples(FiltroImovel.ID, codigoImovel));
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtroImovel
		.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CONSUMO_TARIFA);

		Collection<Imovel> imovelPesquisado = fachada
			.pesquisar(filtroImovel, Imovel.class.getName());

		if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
			imovel = imovelPesquisado.iterator().next();
		}

		
		Collection<ContaValoresHelper> colecaoContaValores = new ArrayList();
		if (sessao.getAttribute("colecaoContaValores")!= null){
			colecaoContaValores = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores");
		}else{
			 colecaoContaValores = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValoresImovel");
		}
		
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = new ArrayList();
		if (sessao.getAttribute("colecaoGuiaPagamentoValores") != null  || indicadorGuiasPagamento.equals("2")){
			colecaoGuiaPagamentoValores = (Collection<GuiaPagamentoValoresHelper>) 
			sessao.getAttribute("colecaoGuiaPagamentoValores");
		}else{
			colecaoGuiaPagamentoValores = (Collection<GuiaPagamentoValoresHelper>) 
			sessao.getAttribute("colecaoGuiaPagamentoValoresImovel");
		}
		
		Collection<DebitoACobrar> colecaoDebitoACobrar = new ArrayList();
		if(sessao.getAttribute("colecaoDebitoACobrar") != null || indicadorDebitosACobrar.equals("2")){
			colecaoDebitoACobrar = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitoACobrar");
		}else{
			colecaoDebitoACobrar = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitoACobrarImovel");
		}

		Collection<CreditoARealizar> colecaoCreditoARealizar = new ArrayList();
		if (sessao.getAttribute("colecaoCreditoARealizar") != null || indicadorCreditoARealizar.equals("2")){
			colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizar");
		}else{
			colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizarImovel");
		}
		
		
		Cliente cliente = new Cliente();
		Integer idCliente = 0;
		//Id do cliente respons�vel pelo parcelamento, se tiver sido informado
		//caso contr�rio Id do Cliente usu�rio do im�vel
		if (efetuarParcelamentoDebitosActionForm.get("idClienteParcelamento") != null 
				&& !efetuarParcelamentoDebitosActionForm.get("idClienteParcelamento").equals("")){
			idCliente = new Integer ((String)efetuarParcelamentoDebitosActionForm.get("idClienteParcelamento"));
		}else{
			idCliente = (Integer)sessao.getAttribute("idClienteImovel");
		}
		cliente.setId(idCliente);
		
		/*
		 * Colocado por Raphael Rossiter em 25/08/2008 - Analista: Rosana Carvalho
		 * 
		 * O sistema verifica se o parcelamento � para ser inclu�do obrigatoriamente j� confirmado
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getIndicadorParcelamentoConfirmado() == 
			ConstantesSistema.SIM.shortValue()){
			
			indicadorConfirmacaoParcelamento = ConstantesSistema.SIM.toString();
		}
		
		//CARREGANDO INFORMA��ES PARA CONCLUS�O DO PARCELAMENTO
		//=============================================================================================================
		NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = 
		(NegociacaoOpcoesParcelamentoHelper) sessao.getAttribute("opcoesParcelamento");
		
		Collection colecaoContasEmAntiguidade = null;
		
		if (opcoesParcelamento != null){
			colecaoContasEmAntiguidade = opcoesParcelamento.getColecaoContasEmAntiguidade();
		}
		
		ConcluirParcelamentoDebitosHelper concluirParcelamentoDebitosHelper = 
		new	ConcluirParcelamentoDebitosHelper(colecaoContaValores, 
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
				descontoTarifaSocialRDEspecial, colecaoContasEmAntiguidade);
		
		//Log
		System.out.println(" ====== ConcluirEfetuarParcelamentoDebitosAction ====== "
							+ "\n Matricula: " + imovel.getId()
							+ "\n Acr�scimos de Impontualidade: " + valorAcrescimosImpontualidade
							+ "\n Descontos de acr�scimos de impontualidade " + descontoAcrescimosImpontualidade);
		
		//=============================================================================================================
		
		Integer idParcelamento = fachada.concluirParcelamentoDebitos(
		concluirParcelamentoDebitosHelper, usuarioLogado);
		
		sessao.setAttribute("idParcelamento", idParcelamento.toString());

		
		//[FS0013] - Verificar sucesso da transa��o
		// Monta a p�gina de sucesso
		Collection idsContaEP = (Collection)sessao.getAttribute("idsContaEP");
		if (idsContaEP != null && !idsContaEP.isEmpty()){
			montarPaginaSucesso(httpServletRequest, "Parcelamento de D�bitos do Im�vel "+codigoImovel+" efetuado com sucesso.",
                    "Efetuar outro Parcelamento de D�bitos", "exibirEfetuarParcelamentoDebitosAction.do?menu=sim",
                    "gerarRelatorioParcelamentoAction.do", "Imprimir Termo",
                    "Imprimir Contas EP" ,"gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N" );
			
		}else{
			
			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples
	        			(FiltroGuiaPagamento.PARCELAMENTO_ID,new Integer(idParcelamento)));
			 
	       	Collection<GuiaPagamento> collectionGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
	       	sessao.setAttribute("usuarioLogado", usuarioLogado);
	    	String idGuiaPagamento = "" ;
	       	if (collectionGuiaPagamento != null &&
	       			!collectionGuiaPagamento.isEmpty()){
	       		GuiaPagamento guiaPagamento = (GuiaPagamento)Util.retonarObjetoDeColecao(collectionGuiaPagamento);
	       		idGuiaPagamento = "" + guiaPagamento .getId();
	       		
	       		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
		            montarPaginaSucesso(httpServletRequest, "Parcelamento de D�bitos do Im�vel "+codigoImovel+" efetuado com sucesso.",
	                    "Efetuar outro Parcelamento de D�bitos", "exibirEfetuarParcelamentoDebitosAction.do?menu=sim",
	                    "gerarRelatorioParcelamentoAction.do", "Imprimir Termo",
	                    "Imprimir Guia Pagto Entrada",
	                    "gerarRelatorioEmitirGuiaPagamentoActionInserir.do?idGuiaPagamento=" + idGuiaPagamento);
		        }
	       		
	       	}else if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
		            montarPaginaSucesso(httpServletRequest, "Parcelamento de D�bitos do Im�vel "+codigoImovel+" efetuado com sucesso.",
	                    "Efetuar outro Parcelamento de D�bitos", "exibirEfetuarParcelamentoDebitosAction.do?menu=sim",
	                    "gerarRelatorioParcelamentoAction.do", "Imprimir Termo");
		    }
		}
        
		return retorno;
    }
    
	//Vivianne Sousa 10/07/2008
	public void verificarValorEntradaMinimaPermitida(Short numeroPrestacoesSelecionada,
    		BigDecimal valorEntrada, BigDecimal valorDebitoTotalAtualizado,
    		ParcelamentoPerfil parcelamentoPerfil){
		
		//caso o indicador de entrada m�nima seja SIM
		if (parcelamentoPerfil.getIndicadorEntradaMinima() != null &&
				parcelamentoPerfil.getIndicadorEntradaMinima().equals(ConstantesSistema.SIM)){
			
			BigDecimal valorPrestacao = Util.dividirArredondando(
					valorDebitoTotalAtualizado, new BigDecimal(numeroPrestacoesSelecionada));
			
			valorPrestacao = valorPrestacao.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
			
			//o sistema verificar� se a entrada informada est� menor 
			//que o valor da presta��o calculada da op��o de qtde de parcelas selecionada
			if(valorPrestacao.compareTo(valorEntrada) == 1){
				throw new ActionServletException("atencao.valor.entrada.menor.possivel");
			}
			
		}
		
	}
}

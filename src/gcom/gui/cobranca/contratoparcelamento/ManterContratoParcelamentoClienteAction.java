package gcom.gui.cobranca.contratoparcelamento;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroCobrancaForma;
import gcom.cobranca.contratoparcelamento.ComparatorParcela;
import gcom.cobranca.contratoparcelamento.ComparatorPrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroPrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.FiltroPrestacaoItemContratoParcelamento;
import gcom.cobranca.contratoparcelamento.FiltroQuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.FiltroTipoRelacao;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.PrestacaoItemContratoParcelamento;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.TipoRelacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de Manter Contrato Parcelamento por Cliente
 * 
 * @author Paulo Diniz
 * @created 16/05/2011
 */
public class ManterContratoParcelamentoClienteAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um contrato parcelamento por cliente
	 * 
	 * [UC11343 Manter Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 04/04/2011
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
	    ActionForward retorno = actionMapping.findForward("exibirManter");
	    String idClienteContrato = httpServletRequest.getParameter("idClienteContrato");
	    
	    HttpSession sessao = httpServletRequest.getSession(false);
		
	    Collection collTipoRelacao = (Collection) sessao.getAttribute("collTipoRelacao");
	    if(collTipoRelacao == null){
	    	FiltroTipoRelacao filtroTipoRelacao = new FiltroTipoRelacao();
	    	filtroTipoRelacao.adicionarParametro(new ParametroSimples(FiltroTipoRelacao.INDICADOR_USO, 1));
	    	collTipoRelacao = fachada.pesquisar(filtroTipoRelacao, TipoRelacao.class.getName()); 
	    }
        sessao.setAttribute("collTipoRelacao", collTipoRelacao);
	    
        Collection collRelacaoCliente = (Collection) sessao.getAttribute("collRelacaoCliente");
        if(collRelacaoCliente == null){
        	FiltroClienteRelacaoTipo filtroClienteRelacTipo = new FiltroClienteRelacaoTipo("descricao"); 
        	filtroClienteRelacTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO, 1));
        	collRelacaoCliente = fachada.pesquisar(filtroClienteRelacTipo, ClienteRelacaoTipo.class.getName()); 
        }
        sessao.setAttribute("collRelacaoCliente", collRelacaoCliente);
        
        if(sessao.getAttribute("collResolucoesDiretoria") == null){
			FiltroContratoParcelamentoRD filtro = new FiltroContratoParcelamentoRD(FiltroContratoParcelamentoRD.NUMERO);
			Collection<ContratoParcelamentoRD> resolucoesDiretoria = fachada.pesquisar(filtro, ContratoParcelamentoRD.class.getName());
			sessao.setAttribute("collResolucoesDiretoria", resolucoesDiretoria);
		}
		
		if(sessao.getAttribute("collFormaPgto") == null){
			FiltroCobrancaForma filtroCobranca = new FiltroCobrancaForma();
	        filtroCobranca.adicionarParametro(new ParametroSimples(FiltroCobrancaForma.IC_USO_CONTRATO_PARCEL_CLIENTE, "1"));
			Collection coll = fachada.pesquisar(filtroCobranca, CobrancaForma.class.getName()); 
			sessao.setAttribute("collFormaPgto", coll);
		}
        
        FiltroContratoParcelamentoCliente filtroContrato = new FiltroContratoParcelamentoCliente();
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("cliente");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.contratoAnterior");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.parcelamentoSituacao");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.usuarioResponsavel");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoAnterior");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoCliente");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.resolucaoDiretoria");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.cobrancaForma");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.motivoDesfazer");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.qtdPrestacoesDaRDEscolhida");

        filtroContrato.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoCliente.ID, idClienteContrato));
        
        Collection collClienteContrato = fachada.pesquisar(filtroContrato, ContratoParcelamentoCliente.class.getName());
        ContratoParcelamentoCliente contratoClienteManter = (ContratoParcelamentoCliente) collClienteContrato.iterator().next();
        
        ContratoParcelamento contratoManter = (ContratoParcelamento) contratoClienteManter.getContrato();
        
		////////////////////////////////////////////////////////////// CARREGAR VALORES
        if(contratoClienteManter.getIndicadorClienteSuperior().intValue() == 2){
        	sessao.setAttribute("clienteContrato", contratoClienteManter.getCliente());
        	sessao.setAttribute("contratoClienteManter", contratoClienteManter);
        	sessao.setAttribute("tipoConsulta", "cliente"); 
        }else{
        	sessao.setAttribute("clienteContrato", contratoClienteManter.getCliente());
        	sessao.setAttribute("contratoClienteManter", contratoClienteManter);
			sessao.setAttribute("tipoConsulta", "clienteSuperior");
        }
		
        if(contratoManter.getResolucaoDiretoria() != null){
        	FiltroQuantidadePrestacoes filtroQtdPrestacoes = new FiltroQuantidadePrestacoes(FiltroQuantidadePrestacoes.QTD_FATURAS_PARCELADAS);
        	filtroQtdPrestacoes.adicionarParametro(new ComparacaoTexto(
        			FiltroQuantidadePrestacoes.CONTRATO_PARCELAMENTO_RD_NUMERO, contratoManter.getResolucaoDiretoria().getNumero().toUpperCase()));
        	List<QuantidadePrestacoes> parcelas = new ArrayList<QuantidadePrestacoes>(fachada.pesquisar(filtroQtdPrestacoes,QuantidadePrestacoes.class.getName()));
        	Collections.sort(parcelas, new ComparatorParcela());
        	sessao.setAttribute("parcelas", parcelas);
        	
        	if(parcelas == null || parcelas.size() <= 0){
        		sessao.removeAttribute("parcelas");
        	}
        }
        
        sessao.setAttribute("contratoManter", contratoManter);
        sessao.setAttribute("clienteContrato", contratoClienteManter.getCliente());
        boolean desabilitaAtualizar = fachada.verificaContratoParcelComPagamentoFeito(contratoManter.getId());
        sessao.setAttribute("desabilitaAtualizar", desabilitaAtualizar);
        
        FiltroPrestacaoContratoParcelamento filtroPrestacao = new FiltroPrestacaoContratoParcelamento();
        filtroPrestacao.adicionarParametro(new ParametroSimples(FiltroPrestacaoContratoParcelamento.CONTRATO_PARCELAMENTO_ID, contratoManter.getId()));
        
        List<PrestacaoContratoParcelamento> collParcelas = new ArrayList<PrestacaoContratoParcelamento>
			(fachada.pesquisar(filtroPrestacao, PrestacaoContratoParcelamento.class.getName()));
        List<PrestacaoContratoParcelamento> collParcelasPagas = new ArrayList<PrestacaoContratoParcelamento>();
        for (PrestacaoContratoParcelamento prestacao : collParcelas) {
        	if(fachada.verificaPrestacaoPaga(prestacao.getId())){
        		prestacao.setPrestacaoPaga(true);
        		collParcelasPagas.add(prestacao);
        	}else{
        		prestacao.setPrestacaoPaga(false);
        	}
        	
        	FiltroPrestacaoItemContratoParcelamento filtroPrestItem = new FiltroPrestacaoItemContratoParcelamento();
        	filtroPrestItem.adicionarParametro(new ParametroSimples(FiltroPrestacaoItemContratoParcelamento.PRESTACAO_ID, prestacao.getId().intValue()));
        	Collection<PrestacaoItemContratoParcelamento> itensPagos = fachada.pesquisar(filtroPrestItem, PrestacaoItemContratoParcelamento.class.getName());
        	if(itensPagos != null && itensPagos.size() != 0){
        		prestacao.setItensPagos(itensPagos.size());
        	}else{
        		prestacao.setItensPagos(0);
        	}
        	
        }
        
        Collections.sort(collParcelas, new ComparatorPrestacaoContratoParcelamento());
        
        sessao.setAttribute("collParcelas", collParcelas);
        sessao.setAttribute("collParcelasPagas", collParcelasPagas);
        
        FiltroContratoParcelamentoItem filtroItem = new FiltroContratoParcelamentoItem();
        filtroItem.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.CONTA_GERAL);
        filtroItem.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.DOCUMENTO_TIPO);
        filtroItem.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.CONTA_GERAL_CONTA);
        filtroItem.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.CONTA_GERAL_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL);
        filtroItem.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.CONTRATO_ID, contratoManter.getId()));
        filtroItem.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.DOCUMENTO_TIPO, DocumentoTipo.CONTA));
        Collection<ContratoParcelamentoItem> colecaoContaItens = fachada.pesquisar(filtroItem, ContratoParcelamentoItem.class.getName());
        BigDecimal valorConta = new BigDecimal("0.00");
        BigDecimal valorAcrescimo = new BigDecimal("0.00");
        BigDecimal valorAcrescimoTotal = new BigDecimal("0.00");
        BigDecimal valorAgua = new BigDecimal("0.00");
        BigDecimal valorEsgoto = new BigDecimal("0.00");
        BigDecimal valorDebito = new BigDecimal("0.00");
        BigDecimal valorCredito = new BigDecimal("0.00");
        BigDecimal valorImposto = new BigDecimal("0.00");
        BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
		BigDecimal valorTotalDebito = new BigDecimal("0.00");
        for (ContratoParcelamentoItem contratoParcelamentoItem : colecaoContaItens) {
        	
        	valorAcrescimo = BigDecimal.ZERO;

        	if (contratoParcelamentoItem.getContaGeral().getConta() != null
        			&& contratoParcelamentoItem.getContaGeral().getConta().getId() != null) {
        		
        		valorConta = valorConta.add(contratoParcelamentoItem.getContaGeral().getConta().getValorTotal());
        		valorAcrescimo = valorAcrescimo.add(contratoParcelamentoItem.getValarAcrescImpont());
        		valorAgua = valorAgua.add(contratoParcelamentoItem.getContaGeral().getConta().getValorAgua());
        		valorEsgoto = valorEsgoto.add(contratoParcelamentoItem.getContaGeral().getConta().getValorEsgoto());
        		valorDebito = valorDebito.add(contratoParcelamentoItem.getContaGeral().getConta().getDebitos());
        		valorCredito = valorCredito.add(contratoParcelamentoItem.getContaGeral().getConta().getValorCreditos());
        		
        		if(contratoParcelamentoItem.getContaGeral().getConta().getValorImposto() != null){
        			valorImposto = valorImposto.add(contratoParcelamentoItem.getContaGeral().getConta().getValorImposto());
        		}
        	} else if (contratoParcelamentoItem.getContaGeral().getContaHistorico() != null
            			&& contratoParcelamentoItem.getContaGeral().getContaHistorico().getId() != null) {
        		
        		valorConta = valorConta.add(contratoParcelamentoItem.getContaGeral().getContaHistorico().getValorTotal());
    			valorAgua = valorAgua.add(contratoParcelamentoItem.getContaGeral().getContaHistorico().getValorAgua());
    			valorEsgoto = valorEsgoto.add(contratoParcelamentoItem.getContaGeral().getContaHistorico().getValorEsgoto());
    			valorDebito = valorDebito.add(contratoParcelamentoItem.getContaGeral().getContaHistorico().getValorDebitos());
    			valorCredito = valorCredito.add(contratoParcelamentoItem.getContaGeral().getContaHistorico().getValorCreditos());
    			
    			if(contratoParcelamentoItem.getContaGeral().getContaHistorico().getValorImposto() != null){
    				valorImposto = valorImposto.add(contratoParcelamentoItem.getContaGeral().getContaHistorico().getValorImposto());
    			}
        	}
        	
        	valorAcrescimoTotal = valorAcrescimoTotal.add(valorAcrescimo);
        	
			contratoParcelamentoItem.setValorJurosMora(valorAcrescimo);
			contratoParcelamentoItem.setValorAtualizacaoMonetaria(BigDecimal.ZERO);
			contratoParcelamentoItem.setValorMulta(BigDecimal.ZERO);
			
		}
		valorTotalDebito = valorTotalDebito.add(valorConta);
        
        FiltroContratoParcelamentoItem filtroItemDebitoACobrar = new FiltroContratoParcelamentoItem();
        filtroItemDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade(
        		FiltroContratoParcelamentoItem.DEBITO_A_COBRAR_GERAL);
        filtroItemDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade(
        		FiltroContratoParcelamentoItem.DEBITO_A_COBRAR_GERAL_DEBITO);
        filtroItemDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade(
        		FiltroContratoParcelamentoItem.DEBITO_A_COBRAR_GERAL_DEBITO_IMOVEL);
        filtroItemDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade(
        		FiltroContratoParcelamentoItem.DEBITO_A_COBRAR_GERAL_DEBITO_TIPO);
        filtroItemDebitoACobrar.adicionarParametro(new ParametroSimples(
        		FiltroContratoParcelamentoItem.CONTRATO_ID, contratoManter.getId()));
        filtroItemDebitoACobrar.adicionarParametro(new ParametroSimples(
        		FiltroContratoParcelamentoItem.DOCUMENTO_TIPO, DocumentoTipo.DEBITO_A_COBRAR));
        
        Collection<ContratoParcelamentoItem> colecaoItensDebitoACobrar = 
        	fachada.pesquisar(filtroItemDebitoACobrar, ContratoParcelamentoItem.class.getName());
        
        for (ContratoParcelamentoItem contratoParcelamentoItem : colecaoItensDebitoACobrar) {

        	valorDebitoACobrar = valorDebitoACobrar.add(contratoParcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getValorTotal());
        }
		valorTotalDebito = valorTotalDebito.add(valorDebitoACobrar);
        
        sessao.setAttribute("colecaoContaItens", colecaoContaItens);
        sessao.setAttribute("colecaoItensDebitoACobrar", colecaoItensDebitoACobrar);
        
        sessao.setAttribute("valorAgua", Util.formatarMoedaReal(valorAgua));
		sessao.setAttribute("valorEsgoto", Util.formatarMoedaReal(valorEsgoto));
		sessao.setAttribute("valorDebito", Util.formatarMoedaReal(valorDebito));
		sessao.setAttribute("valorCredito", Util.formatarMoedaReal(valorCredito));
		sessao.setAttribute("valorConta", Util.formatarMoedaReal(valorConta));
		sessao.setAttribute("valorAcrescimo", Util.formatarMoedaReal(valorAcrescimoTotal));
		sessao.setAttribute("valorImposto", Util.formatarMoedaReal(valorImposto));
		sessao.setAttribute("valorContaAcrescimo", Util.formatarMoedaReal(valorConta.add(valorAcrescimoTotal)));
		
		// [SB0002] ? Apresentar Dados do Débito Parcelado
		// 1.8.1. Total do Débito
		// [SB0003] ? Apresentar Dados das Condições do Parcelamento
		// 1.3.1. Valor
		sessao.setAttribute("valorTotalDebito", Util.formatarMoedaReal(valorTotalDebito));
		
		// [SB0002] ? Apresentar Dados do Débito Parcelado
		// 1.8.2. Total do Débito Atualizado 
		// [SB0003] ? Apresentar Dados das Condições do Parcelamento
		// 1.3.3. Débito com Acréscimo 
		sessao.setAttribute("valorTotalDebitoAtualizado", Util.formatarMoedaReal(valorTotalDebito.add(valorAcrescimoTotal)));

		// [SB0003] ? Apresentar Dados das Condições do Parcelamento
		// 1.2.3. Débito com Acréscimos 
		sessao.setAttribute("valorDebitoACobrar", valorDebitoACobrar);
		// 1.3.2. Acréscimos por Impontualidade 
		sessao.setAttribute("valorTotalAcrescimo", valorAcrescimoTotal);
		
		//////////////////////////////////////////////////////////////CARREGAR VALORES	
			
		
	    return retorno;
	}

}

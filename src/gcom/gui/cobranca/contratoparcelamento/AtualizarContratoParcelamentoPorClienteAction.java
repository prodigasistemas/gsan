package gcom.gui.cobranca.contratoparcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroCobrancaForma;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.contratoparcelamento.ComparatorParcela;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamento;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.FiltroQuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.InserirContratoParcelamentoValoresParcelasHelper;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoesRDHelper;
import gcom.cobranca.contratoparcelamento.TipoRelacao;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
 * 
 * Form que Inseri uma ContratoParcelamentoPorCliente
 * 
 * @author Paulo Otávio
 * @date 04/04/2011
 */
public class AtualizarContratoParcelamentoPorClienteAction extends GcomAction {
	
	/**
	 * [UC1139] Atualizar Contratos de Parcelamento por Cliente
	 * 
	 * Este caso de uso permite a atualização de um contrato de parcelamento por cliente
	 * 
	 * @author Paulo Diniz, Mariana Victor
	 * @date 16/03/2011, 27/07/2011
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

		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		AtualizarContratoParcelamentoPorClienteActionForm form = (AtualizarContratoParcelamentoPorClienteActionForm) actionForm;
		
		carregarCamposDoFormulario(form, sessao);
		validarCampos(form, sessao);
		
		ContratoParcelamento contratoAtualizar = (ContratoParcelamento) sessao.getAttribute("contratoAtualizar");
		List<PrestacaoContratoParcelamento> listaDeParcelas = (List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelas");
		
		String numeroParcelasPopUp = httpServletRequest.getParameter("numeroParcelasPopUp");
		String valorParceladoFinal = httpServletRequest.getParameter("valorParceladoFinal");
		
		if (numeroParcelasPopUp != null && !numeroParcelasPopUp.equals("")) {
			contratoAtualizar.setNumeroPrestacoes(new Integer(numeroParcelasPopUp));
		}
		
		if (valorParceladoFinal != null && !valorParceladoFinal.equals("")) {
			String valorParcelado = valorParceladoFinal.replace(".", "");
			valorParcelado = valorParcelado.replace(",", ".");
			BigDecimal valorParceladoBigDec = new BigDecimal(valorParcelado);
			contratoAtualizar.setValorParcelamentoACobrar(valorParceladoBigDec);
		}

		//[FS0029]
		if(contratoAtualizar.getResolucaoDiretoria() != null){
			verificaNumeroParcelasComRD(listaDeParcelas, contratoAtualizar.getResolucaoDiretoria(), form, sessao, "terceira");
		}else{
			verificaNumeroParcelasSemRD(listaDeParcelas, form, sessao, "terceira");
		}
		
		BigDecimal valorConta = (BigDecimal) sessao.getAttribute("valorContaSelecao");
		contratoAtualizar.setValorTotalConta(valorConta);
		
		BigDecimal valorAcrescimo = (BigDecimal) sessao.getAttribute("valorAcrescimoSelecao");
		if(form.getIndicadorDebitoAcresc() != null && form.getIndicadorDebitoAcresc().equals("1")){
			contratoAtualizar.setValorTotalAcrescImpontualidade(valorAcrescimo);
		}
		
		List<DebitosClienteHelper> listaDebitos = new ArrayList<DebitosClienteHelper>();
		
		List<DebitosClienteHelper> listaDebitosContas = (List<DebitosClienteHelper>) sessao.getAttribute("listaDebitos");
		List<DebitosClienteHelper> listaDebitosACobrar = (List<DebitosClienteHelper>) sessao.getAttribute("listaDebitosACobrar");

		if (listaDebitosContas != null && !listaDebitosContas.isEmpty()) {
			listaDebitos.addAll(listaDebitosContas);
		}
		if (listaDebitosACobrar != null && !listaDebitosACobrar.isEmpty()) {
			listaDebitos.addAll(listaDebitosACobrar);
		}
		
		contratoAtualizar.setQtdFaturasParceladas(listaDebitos.size());
		
		contratoAtualizar.setIndicadorContasRevisao(new Short("1"));
		contratoAtualizar.setIndicadorDebitoACobrar(new Short("2"));
		for (DebitosClienteHelper debitosClienteHelper : listaDebitos) {
			if(debitosClienteHelper.getTipoDocumento() == DocumentoTipo.CONTA){
				contratoAtualizar.setIndicadorContasRevisao(new Short("1"));		
			} 
			
			if(debitosClienteHelper.getTipoDocumento() == DocumentoTipo.DEBITO_A_COBRAR){
				contratoAtualizar.setIndicadorDebitoACobrar(new Short("1"));		
			} 
			
			if(debitosClienteHelper.getTipoDocumento() == DocumentoTipo.CREDITO_A_REALIZAR){
				contratoAtualizar.setIndicadorCreditoARealizar(new Short("1"));		
			} 
		}
		
		BigDecimal valorParcelado = null;
		BigDecimal jurosVal = BigDecimal.ZERO;
		if(contratoAtualizar.getIndicadorParcelamentoJuros().intValue() == 1 && form.getTaxaJurosAdd() != null && !form.getTaxaJurosAdd().trim().equals("")){
			String juros = null;
			if(form.getTaxaJurosAdd() != null && !form.getTaxaJurosAdd().equals("")){
				juros = form.getTaxaJurosAdd();
			}
			
			BigDecimal jurosFormatado = new BigDecimal(juros.replace(".", "").replace(",", "."));
			
			if (juros != null) {
				contratoAtualizar.setTaxaJuros(jurosFormatado);
			} else {
				contratoAtualizar.setTaxaJuros(null);
			}
			
			if(juros == null || juros.equals("")){
				juros = "0";
			}
			String indicadorDebitoAcresc = form.getIndicadorDebitoAcresc();

			BigDecimal valorContaSelecao = (BigDecimal) sessao.getAttribute("valorContaSelecao");
			BigDecimal acrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecao");

			Integer numeroParcelas = null;
			if (form.getPacerlaAdd() != null
					&& !form.getPacerlaAdd().equals("")) {
				numeroParcelas = new Integer (form.getPacerlaAdd());
			} else if (form.getNumeroParcelasPopUp() != null
					&& !form.getNumeroParcelasPopUp().equals("")) {
				numeroParcelas = new Integer (form.getNumeroParcelasPopUp());
			}
			
			InserirContratoParcelamentoValoresParcelasHelper helper = Fachada.getInstancia()
				.calcularValoresParcelasContratoParcelamento(valorContaSelecao, acrescimo, 
					contratoAtualizar.getIndicadorDebitosAcrescimos().toString(), 
					contratoAtualizar.getIndicadorParcelamentoJuros().toString(), 
					jurosFormatado, 1, numeroParcelas.intValue());
			
			valorParcelado = helper.getValorParcelado();

			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			if (new Short(contratoAtualizar.getIndicadorParcelamentoJuros()).compareTo(ConstantesSistema.SIM) == 0
					&& sistemaParametro.getIndicadorTabelaPrice() != null 
					&& sistemaParametro.getIndicadorTabelaPrice().equals(ConstantesSistema.SIM)) {
				jurosVal = valorParcelado.subtract(acrescimo);
			} else if(Integer.parseInt(indicadorDebitoAcresc) == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()){
				BigDecimal valorContaComAcrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecao");
				jurosVal = valorContaComAcrescimo.multiply(new BigDecimal(juros.replace(",", "."))).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP); 
			} else {
				jurosVal = valorConta.multiply(new BigDecimal(juros.replace(",", "."))).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP); 
			}
			
			if(jurosVal != null && jurosVal.floatValue() > 0.0){
				contratoAtualizar.setValorDebitoAtualizado(valorParcelado.subtract(jurosVal));
			}else{
				contratoAtualizar.setValorDebitoAtualizado(valorParcelado);
			}
			
		}else{
			String indicadorDebitoAcresc = form.getIndicadorDebitoAcresc();
			if(Integer.parseInt(indicadorDebitoAcresc) == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()){
				BigDecimal valorContaComAcrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecao");
				valorParcelado = valorContaComAcrescimo;
			}else{
				valorParcelado = valorConta;
			}
			contratoAtualizar.setValorDebitoAtualizado(valorParcelado);
		}
		
		contratoAtualizar.setValorJurosParcelamento(jurosVal);
		
		Integer anoMesReferenciaFaturamento = Util.formataAnoMes(new Date());
		Integer anoMesFatuSistemaParametro = this.getSistemaParametro().getAnoMesFaturamento();
		if(anoMesReferenciaFaturamento.intValue() < anoMesFatuSistemaParametro.intValue()){
			anoMesReferenciaFaturamento = anoMesFatuSistemaParametro;
		}else{
			for (DebitosClienteHelper debitosClienteHelper : listaDebitos) {
				if(debitosClienteHelper.getTipoDocumento().intValue() == DocumentoTipo.CONTA.intValue()){
					Integer anoMesConta = fachada.pesquisarReferenciaContaPorId(debitosClienteHelper.getIdentificadorItem());
					
					if(anoMesReferenciaFaturamento.intValue() < anoMesConta.intValue()){
						anoMesReferenciaFaturamento = anoMesConta;
					}
				}
			}
		}
		
		contratoAtualizar.setAnoMesReferenciaFaturamento(anoMesReferenciaFaturamento);
		
		contratoAtualizar.setDataImplantacao(new Date());
		contratoAtualizar.setUltimaAlteracao(new Date());
		
		if(contratoAtualizar.getResolucaoDiretoria() != null 
				&& form.getParcelaSelecao() != null 
				&& !form.getParcelaSelecao().equals("")){
			List<QuantidadePrestacoesRDHelper> colecaoQuantidadePrestacoesRDHelper = (List<QuantidadePrestacoesRDHelper>) sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper");
			 
			for (QuantidadePrestacoesRDHelper quantidadePrestacoesRDHelper : colecaoQuantidadePrestacoesRDHelper) {
				if(quantidadePrestacoesRDHelper.getIdQuantidadePrestacoes().intValue() ==  Integer.parseInt(form.getParcelaSelecao())){
					BigDecimal valorContaSelecao = (BigDecimal) sessao.getAttribute("valorContaSelecao");
					BigDecimal valorContaComAcrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecao");
					
					FiltroQuantidadePrestacoes filtroQuantidadePrestacoes = new FiltroQuantidadePrestacoes();
					filtroQuantidadePrestacoes.adicionarParametro(new ParametroSimples(FiltroQuantidadePrestacoes.ID, quantidadePrestacoesRDHelper.getIdQuantidadePrestacoes()));
					Collection<QuantidadePrestacoes> colecaoQuantidadePrestacoes = fachada.pesquisar(filtroQuantidadePrestacoes, QuantidadePrestacoes.class.getName());
					QuantidadePrestacoes quantidadePrestacoes = (QuantidadePrestacoes) Util.retonarObjetoDeColecao(colecaoQuantidadePrestacoes);
					
					InserirContratoParcelamentoValoresParcelasHelper helper = fachada
					.calcularValoresParcelasContratoParcelamentoRD(valorContaSelecao, valorContaComAcrescimo, 
							form.getIndicadorDebitoAcresc(), form.getIndicadorParcelJuros(), contratoAtualizar, 
							quantidadePrestacoes);

					valorParcelado = helper.getValorParcelado();
					contratoAtualizar = helper.getContratoParcelamento();
					listaDeParcelas = helper.getListaDeParcelas();
					
					contratoAtualizar.setNumeroPrestacoes(
							quantidadePrestacoesRDHelper.getQuantidadeParcelas());
					
					if (contratoAtualizar.getValorJurosParcelamento() == null 
							|| contratoAtualizar.getValorJurosParcelamento().compareTo(BigDecimal.ZERO) == 0) {
						contratoAtualizar.setValorJurosParcelamento(jurosVal);
					}
				}
			}
			
			verificaValorParceladoTotal(listaDeParcelas, contratoAtualizar.getValorTotalParcelado());
			
		}else{
			contratoAtualizar.setIndicadorParcelaInformadaPeloUsuario(new Short("1"));
			
			//[FS0025]
			verificaValorParceladoTotal(listaDeParcelas, contratoAtualizar.getValorTotalParcelado());
		}
		
		ParcelamentoSituacao parcelamentoSituacao = new ParcelamentoSituacao();
		parcelamentoSituacao.setId(ParcelamentoSituacao.NORMAL);
		contratoAtualizar.setParcelamentoSituacao(parcelamentoSituacao);
		
		
		ContratoParcelamentoCliente clienteContrato = (ContratoParcelamentoCliente) sessao.getAttribute("clienteContrato");
		ContratoParcelamentoCliente clienteSuperiorContrato = (ContratoParcelamentoCliente) sessao.getAttribute("clienteSuperiorContrato");
		
		if(contratoAtualizar.getNumeroPrestacoes() == null){
			contratoAtualizar.setNumeroPrestacoes(Integer.parseInt(form.getNumeroParcelasPopUp()));
		}
		
				
		List<DebitosClienteHelper> listaDebitosAnterior = carregarListaDebitosAnterior(form, sessao);
		fachada.atualizaContratoParcelamentoPorCliente(contratoAtualizar, usuarioLogado, clienteContrato, clienteSuperiorContrato, listaDebitos, listaDebitosAnterior, listaDeParcelas);
		
		String idClienteContrato = "";
		if(clienteContrato != null){
			idClienteContrato = clienteContrato.getId().intValue() + "";
		}else{
			idClienteContrato = clienteSuperiorContrato.getId().intValue() + "";
		}
		
		montarPaginaSucesso(
				httpServletRequest,
				"Contrato de parcelamento por cliente - "+contratoAtualizar.getNumero()+" - atualizado com sucesso",
				"Atualizar outro Contrato de Parcelamento por Cliente",
				"exibirFiltrarContratoParcelamentoClienteAction.do?menu=sim",
				"manterContratoParcelamentoClienteAction.do?idClienteContrato="+idClienteContrato,
		"Voltar");
		
		
		return retorno;
	}
	
	/**Inicio dos metodos Privados**/
	private void carregarCamposDoFormulario(AtualizarContratoParcelamentoPorClienteActionForm form, HttpSession sessao){
		ContratoParcelamento contratoAtualizar = (ContratoParcelamento) sessao.getAttribute("contratoAtualizar");

		Fachada fachada = Fachada.getInstancia();
		
		if (form.getNumeroContratoAnt() != null && !"".equals(form.getNumeroContratoAnt())){
			FiltroContratoParcelamento filtro = new FiltroContratoParcelamento();
			filtro.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamento.NUMERO, form.getNumeroContratoAnt()));
			Collection<ContratoParcelamento> contratos = fachada.pesquisar(filtro, ContratoParcelamento.class.getName());
			if(contratos != null && contratos.size() == 1){
				contratoAtualizar.setContratoAnterior(contratos.iterator().next());
			}
			
			if(form.getRelacaoAnterior() != null && !form.getRelacaoAnterior().equals("")
					 && !form.getRelacaoAnterior().equals("-1")){
				TipoRelacao relacao = new TipoRelacao();
				relacao.setId(Integer.parseInt(form.getRelacaoAnterior()));
				contratoAtualizar.setRelacaoAnterior(relacao);
			} else {
				contratoAtualizar.setRelacaoAnterior(null);
			}
		}else{
			contratoAtualizar.setContratoAnterior(null);
			contratoAtualizar.setRelacaoAnterior(null);
		}
		
		if (form.getDataContrato() != null && !"".equals(form.getDataContrato())){
			contratoAtualizar.setDataContrato(Util.converteStringParaDate(form.getDataContrato()));
		}else{
			contratoAtualizar.setDataContrato(null);
		}

		if (form.getLoginUsuario() != null && !form.getLoginUsuario().toString().trim().equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLoginUsuario()));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				contratoAtualizar.setUsuarioResponsavel(usuario);
			} else {
				contratoAtualizar.setUsuarioResponsavel(null);
			}
			
		}else{
			contratoAtualizar.setUsuarioResponsavel(null);
		}
		
		if (form.getAutocompleteCliente() != null && !"".equals(form.getAutocompleteCliente())
				&& form.getAutocompleteCliente().contains("-")){
			ContratoParcelamentoCliente clienteContrato = new ContratoParcelamentoCliente();
			int id = Integer.parseInt(form.getAutocompleteCliente().split(" - ")[0].trim());
			Cliente cliente = fachada.pesquisarClienteDigitado(id);
			clienteContrato.setCliente(cliente);
			sessao.setAttribute("clienteContrato", clienteContrato);
			sessao.setAttribute("tipoConsulta", "cliente");
			form.setAutocompleteCliente("");
		}else{
			sessao.removeAttribute("clienteContrato");
			sessao.removeAttribute("tipoConsulta");
		}
		
		if (form.getAutocompleteClienteSuperior() != null && !"".equals(form.getAutocompleteClienteSuperior())
				&& form.getAutocompleteClienteSuperior().contains("-")){
			ContratoParcelamentoCliente clienteSuperiorContrato = new ContratoParcelamentoCliente();
			int id = Integer.parseInt(form.getAutocompleteClienteSuperior().split(" - ")[0].trim());
			Cliente cliente = fachada.pesquisarClienteDigitado(id);
			clienteSuperiorContrato.setCliente(cliente);
			sessao.setAttribute("clienteSuperiorContrato", clienteSuperiorContrato);
			sessao.setAttribute("tipoConsulta", "clienteSuperior");
			form.setAutocompleteClienteSuperior("");
		}else{
			sessao.removeAttribute("clienteSuperiorContrato");
			sessao.removeAttribute("tipoConsulta");
		}
		
		if(form.getRelacaoCliente() != null && !form.getRelacaoCliente().equals("") ){
			ClienteRelacaoTipo relacaoCliente = new ClienteRelacaoTipo();
			if(form.getRelacaoCliente().equals("" + ClienteRelacaoTipo.PROPRIETARIO)){
				relacaoCliente.setId(ClienteRelacaoTipo.PROPRIETARIO.intValue());
			}else if(form.getRelacaoCliente().equals("" + ClienteRelacaoTipo.RESPONSAVEL)){
				relacaoCliente.setId(ClienteRelacaoTipo.RESPONSAVEL.intValue());
			}else if(form.getRelacaoCliente().equals("" + ClienteRelacaoTipo.USUARIO)){
				relacaoCliente.setId(ClienteRelacaoTipo.USUARIO.intValue());
			}
			contratoAtualizar.setRelacaoCliente(relacaoCliente);
		}else{
			contratoAtualizar.setRelacaoCliente(null);
		}
		
		if(form.getIndicadorResponsavel() != null && !form.getIndicadorResponsavel().equals("")){
			if(form.getIndicadorResponsavel().equals("" + ContratoParcelamento.RESP_ATUAL_DO_IMOVEL)){
				contratoAtualizar.setIndicadorResponsavel(ContratoParcelamento.RESP_ATUAL_DO_IMOVEL);
			}else if(form.getIndicadorResponsavel().equals("" + ContratoParcelamento.RESP_INDICADOR_NA_CONTA)){
				contratoAtualizar.setIndicadorResponsavel(ContratoParcelamento.RESP_INDICADOR_NA_CONTA);
			}else if(form.getIndicadorResponsavel().equals("" + ContratoParcelamento.RESP_TODOS)){
				contratoAtualizar.setIndicadorResponsavel(ContratoParcelamento.RESP_TODOS);
			} 
		}
		
		if(form.getDataVencimentoInicio() != null && !form.getDataVencimentoInicio().equals("")){
			Date dataVencimentoInicio = Util.converteStringParaDate(form.getDataVencimentoInicio());
			contratoAtualizar.setDataVencimentoInicio(dataVencimentoInicio);
		}else{
			contratoAtualizar.setDataVencimentoInicio(null);
		}
		
		if(form.getDataVencimentoFinal() != null && !form.getDataVencimentoFinal().equals("")){
			Date dataVencimentoFinal = Util.converteStringParaDate(form.getDataVencimentoFinal());
			contratoAtualizar.setDataVencimentoFinal(dataVencimentoFinal);
		}else{
			contratoAtualizar.setDataVencimentoFinal(null);
		}
		
		if(form.getAnoMesDebitoInicio() != null && !form.getAnoMesDebitoInicio().equals("")){
			boolean mesAnoValido = Util.validarMesAno(form.getAnoMesDebitoInicio());
			if(mesAnoValido){
				String anoMes = form.getAnoMesDebitoInicio().replace("/", "");
				anoMes = anoMes.substring(2,6) + anoMes.substring(0,2);
				contratoAtualizar.setAnoMesDebitoInicio(Integer.parseInt(anoMes));
			}
		}else{
			contratoAtualizar.setAnoMesDebitoInicio(null);
		}
		
		if(form.getAnoMesDebitoFinal() != null && !form.getAnoMesDebitoFinal().equals("")){
			boolean mesAnoValido = Util.validarMesAno(form.getAnoMesDebitoFinal());
			if(mesAnoValido){
				String anoMes = form.getAnoMesDebitoFinal().replace("/", "");
				anoMes = anoMes.substring(2,6) + anoMes.substring(0,2);
				contratoAtualizar.setAnoMesDebitoFinal(Integer.parseInt(anoMes));
			}
		}else{
			contratoAtualizar.setAnoMesDebitoFinal(null);
		}
		
		if(form.getObservacao() != null && !form.getObservacao().equals("")){
			contratoAtualizar.setObservacao(form.getObservacao());
		}else{
			contratoAtualizar.setObservacao(null);
		}
		
		if(form.getContasSelecao() != null && form.getContasSelecao().length > 0){
			
			Collection<ContaValoresHelper> colecaoContaValores = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
			BigDecimal valorConta = new BigDecimal(0);
			BigDecimal valorAcresc = new BigDecimal(0);
			
			List<DebitosClienteHelper> listaDebitos = new ArrayList<DebitosClienteHelper>();
			
			if(colecaoContaValores != null){
				for (ContaValoresHelper contaValoresHelper : colecaoContaValores) {
					if(form.verificaContaSelecionada(contaValoresHelper.getConta().getId().intValue())){
						DebitosClienteHelper debito = new DebitosClienteHelper();
						debito.setIdentificadorItem(contaValoresHelper.getConta().getId());
						debito.setTipoDocumento(DocumentoTipo.CONTA);
						debito.setValorItem(contaValoresHelper.getConta().getValorTotal());
						debito.setValorAcrescImpont(contaValoresHelper.getValorTotalContaValores());
						listaDebitos.add(debito);
						valorConta = valorConta.add(contaValoresHelper.getConta().getValorTotal());
						valorAcresc = valorAcresc.add(contaValoresHelper.getValorTotalContaValores());
					}
				}
				sessao.setAttribute("listaDebitos",listaDebitos);
				sessao.setAttribute("valorContasSelecao",valorConta);
				sessao.setAttribute("valorAcrescSelecao",valorAcresc);
				sessao.setAttribute("valorContaComAcrescimoSelecao",valorAcresc.add(valorConta));
			}
		}else{
			sessao.removeAttribute("listaDebitos");
			sessao.setAttribute("valorContasSelecao",BigDecimal.ZERO);
			sessao.setAttribute("valorAcrescSelecao",BigDecimal.ZERO);
			sessao.setAttribute("valorContaComAcrescimoSelecao",BigDecimal.ZERO);
		}

		
		if(form.getDebitosSelecao() != null && form.getDebitosSelecao().length > 0){
			
			Collection<DebitoACobrar> colecaoDebitoACobrarTotal = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
			
			List<DebitosClienteHelper> listaDebitosACobrar = new ArrayList<DebitosClienteHelper>();
			
			BigDecimal valorDebitosACobrar = new BigDecimal(0);
			
			if(colecaoDebitoACobrarTotal != null){
				for (DebitoACobrar debitoACobrar : colecaoDebitoACobrarTotal) {
					if(form.verificaDebitoACobrarSelecionada(debitoACobrar.getId().intValue())){
						DebitosClienteHelper debito = new DebitosClienteHelper();
						debito.setIdentificadorItem(debitoACobrar.getId());
						debito.setTipoDocumento(DocumentoTipo.DEBITO_A_COBRAR);
						debito.setValorItem(debitoACobrar.getValorTotal());
						debito.setValorAcrescImpont(null);
						listaDebitosACobrar.add(debito);
						valorDebitosACobrar = valorDebitosACobrar.add(debitoACobrar.getValorTotal());
					}
				}			
				
				sessao.setAttribute("listaDebitosACobrar",listaDebitosACobrar);
				sessao.setAttribute("valorDebitosACobrarSelecao",valorDebitosACobrar);
			}
				
		}else{
			sessao.removeAttribute("listaDebitosACobrar");
			sessao.setAttribute("valorDebitosACobrarSelecao",BigDecimal.ZERO);
		}
		
		if(form.getResolucaoDiretoria() != null && !form.getResolucaoDiretoria().equals("")){
			
			ContratoParcelamentoRD resolucao = fachada.pesquisarContratoParcelamentoRDPorNumero(form.getResolucaoDiretoria());
			contratoAtualizar.setResolucaoDiretoria(resolucao);
			
			contratoAtualizar.setIndicadorDebitosAcrescimos(resolucao.getIndicadorDebitoAcrescimo());
			contratoAtualizar.setIndicadorParcelamentoJuros(resolucao.getIndicadorParcelamentoJuros());
			contratoAtualizar.setIndicadorPermiteInformarValorParcel(resolucao.getIndicadorInformarParcela());
			contratoAtualizar.setCobrancaForma(resolucao.getCobrancaForma());
			
			List<QuantidadePrestacoes> parcelas = null;
			FiltroQuantidadePrestacoes filtroQtdPrestacoes = new FiltroQuantidadePrestacoes(FiltroQuantidadePrestacoes.QTD_FATURAS_PARCELADAS);
			filtroQtdPrestacoes.adicionarParametro(new ComparacaoTexto(
					FiltroQuantidadePrestacoes.CONTRATO_PARCELAMENTO_RD_NUMERO, contratoAtualizar.getResolucaoDiretoria().getNumero().toUpperCase()));
			parcelas = new ArrayList<QuantidadePrestacoes>(fachada.pesquisar(filtroQtdPrestacoes,QuantidadePrestacoes.class.getName()));
			Collections.sort(parcelas, new ComparatorParcela());
			
			if(parcelas == null || parcelas.size() <= 0){
				sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
			} else {
				Iterator iterator = parcelas.iterator();

				BigDecimal valorContaSelecao = (BigDecimal) sessao.getAttribute("valorContaSelecao");
				BigDecimal valorContaComAcrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecao");
				
				Collection<QuantidadePrestacoesRDHelper> colecaoQuantidadePrestacoesRDHelper = 
					new ArrayList<QuantidadePrestacoesRDHelper>();
				
				while(iterator.hasNext()) {
					QuantidadePrestacoes quantidadePrestacoes = (QuantidadePrestacoes) iterator.next();
					QuantidadePrestacoesRDHelper quantidadePrestacoesRDHelper = new QuantidadePrestacoesRDHelper();
					
					InserirContratoParcelamentoValoresParcelasHelper helper = fachada
						.calcularValoresParcelasContratoParcelamentoRD(valorContaSelecao, valorContaComAcrescimo, 
								form.getIndicadorDebitoAcresc(), form.getIndicadorParcelJuros(), contratoAtualizar, 
								quantidadePrestacoes);
					
					quantidadePrestacoesRDHelper.setIdQuantidadePrestacoes(quantidadePrestacoes.getId());
					quantidadePrestacoesRDHelper.setTaxaJuros(quantidadePrestacoes.getTaxaJuros());
					quantidadePrestacoesRDHelper.setQuantidadeParcelas(quantidadePrestacoes.getQtdFaturasParceladas());
					quantidadePrestacoesRDHelper.setValorDaParcela(
							helper.getValorParcelado().divide(new BigDecimal(
									quantidadePrestacoes.getQtdFaturasParceladas())));
					
					colecaoQuantidadePrestacoesRDHelper.add(quantidadePrestacoesRDHelper);
				}

				sessao.setAttribute("colecaoQuantidadePrestacoesRDHelper", colecaoQuantidadePrestacoesRDHelper);
			}
			
		}else{
			contratoAtualizar.setResolucaoDiretoria(null);
			sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
			
			if(form.getIndicadorDebitoAcresc() != null && !form.getIndicadorDebitoAcresc().equals("")){
				contratoAtualizar.setIndicadorDebitosAcrescimos(Short.parseShort(form.getIndicadorDebitoAcresc()));
			}
			
			if(form.getIndicadorParcelJuros() != null && !form.getIndicadorParcelJuros().equals("")){
				contratoAtualizar.setIndicadorParcelamentoJuros(Short.parseShort(form.getIndicadorParcelJuros()));
			}
			
			if(form.getIndicadorInfoVlParcel() != null && !form.getIndicadorInfoVlParcel().equals("")){
				contratoAtualizar.setIndicadorPermiteInformarValorParcel(Short.parseShort(form.getIndicadorInfoVlParcel()));
			}
			
			if(form.getFormaPgto() != null && !form.getFormaPgto().equals("")){
				FiltroCobrancaForma filtroCobranca = new FiltroCobrancaForma();
				filtroCobranca.adicionarParametro(new ParametroSimples(FiltroCobrancaForma.ID, form.getFormaPgto()));
				Collection<CobrancaForma> formasPagto = fachada.pesquisar(filtroCobranca, CobrancaForma.class.getName());
				for (CobrancaForma formaPgto : formasPagto) {
					if(formaPgto.getId() == Integer.parseInt(form.getFormaPgto())){
						contratoAtualizar.setCobrancaForma(formaPgto);
					}
				}
			}else{
				contratoAtualizar.setCobrancaForma(null);
			}
		}
		
		if(form.getDataVencimentoPrimParcela() != null && !form.getDataVencimentoPrimParcela().equals("")){
			Date dataVencimentoPrimParcela = Util.converteStringParaDate(form.getDataVencimentoPrimParcela());
			contratoAtualizar.setDataVencimentoPrimParcela(dataVencimentoPrimParcela);
		}
		
		if(form.getNumeroEntreVencParcelas() != null && !form.getNumeroEntreVencParcelas().equals("")){
			contratoAtualizar.setNumeroDiasEntreVencimentoParcel(Integer.parseInt(form.getNumeroEntreVencParcelas()));
		}else{
			contratoAtualizar.setNumeroDiasEntreVencimentoParcel(30);
		}
		
		List<QuantidadePrestacoesRDHelper> colecaoQuantidadePrestacoesRDHelper = (List<QuantidadePrestacoesRDHelper>) sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper");
		if(form.getPacerlaAdd() != null && !form.getPacerlaAdd().equals("")
				&& (colecaoQuantidadePrestacoesRDHelper == null || colecaoQuantidadePrestacoesRDHelper.size() == 0)){
			contratoAtualizar.setNumeroPrestacoes(Integer.parseInt(form.getPacerlaAdd()));
		}else{
			contratoAtualizar.setNumeroPrestacoes(null);
		}
		
		if(form.getTaxaJurosAdd() != null && !form.getTaxaJurosAdd().equals("")
				&& (colecaoQuantidadePrestacoesRDHelper == null || colecaoQuantidadePrestacoesRDHelper.size() == 0)){
			String juros = form.getTaxaJurosAdd().replace(".", ",");
			juros = juros.replace(",", ".");
			try{
				BigDecimal jurosBigDec = new BigDecimal(juros);
				contratoAtualizar.setTaxaJuros(jurosBigDec);
			}catch (Exception e) {
				contratoAtualizar.setTaxaJuros(null);
			}
		}else{
			contratoAtualizar.setTaxaJuros(null);
		}
		
		sessao.setAttribute("contratoAtualizar", contratoAtualizar);
	}
	
	private void validarCampos(AtualizarContratoParcelamentoPorClienteActionForm form, HttpSession sessao) throws ActionServletException{
		ActionServletException ex = null;

		Fachada fachada = Fachada.getInstancia();
		
		if (ex == null && form.getNumeroContratoAnt() != null && !"".equals(form.getNumeroContratoAnt()) ){
			FiltroContratoParcelamento filtro = new FiltroContratoParcelamento();
			filtro.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamento.NUMERO, form.getNumeroContratoAnt()));
			Collection<ContratoParcelamento> contratos = fachada.pesquisar(filtro, ContratoParcelamento.class.getName());
			if(contratos == null || contratos.size() == 0){
				ex = new ActionServletException(
						"atencao.numero.contrato.nao.existe",null, "");	
			}else if(contratos != null && contratos.size() == 1){ 
				ContratoParcelamento contrato = contratos.iterator().next();
				if(contrato.getParcelamentoSituacao().getId() == ParcelamentoSituacao.NORMAL){
					ex = new ActionServletException(
							"atencao.contrato.parcelamento.anterior.nao.encerrado",null, "");	
				}
				
				if(ex == null && (form.getRelacaoAnterior() == null || "".equals(form.getRelacaoAnterior()))){
					ex = new ActionServletException(
							"atencao.informe.a.relacao.anterior.contrato",null, "");	
				}
			}
		}
		
		if (ex == null && (form.getDataContrato() == null || "".equals(form.getDataContrato()))){
			ex = new ActionServletException(
					"atencao.required", null, "Data do Contrato");
		}else if(ex == null && !"".equals(form.getDataContrato())){
			boolean dtValida = Util.validarDiaMesAnoSemBarraNovo(form.getDataContrato().replace("/", ""));
			if(!dtValida){
				ex = new ActionServletException(
						"atencao.data.invalida",null, "Data");
			}else if(Util.converteStringParaDate(form.getDataContrato()).after(new Date())){
					ex = new ActionServletException(
							"atencao.contrato.superio.data.corrente", null, Util.formatarData(new Date()));
			}
		}
		
		
		if (ex == null && (form.getLoginUsuario() == null || "".equals(form.getLoginUsuario()))){
			ex = new ActionServletException(
					"atencao.required", null, "Usuário Responsável");
		}
		
		if(ex == null && (form.getDataVencimentoInicio()!= null && !"".equals(form.getDataVencimentoInicio())
				&& !Util.validarDiaMesAnoSemBarraNovo(form.getDataVencimentoInicio().replace("/", "")))){
				ex = new ActionServletException(
						"atencao.data.invalida",null, "Data");
		} 
		if (ex == null && form.getDataVencimentoFinal()!= null && !"".equals(form.getDataVencimentoFinal()) && !Util.validarDiaMesAnoSemBarraNovo(form.getDataVencimentoFinal().replace("/", ""))){
				ex = new ActionServletException(
						"atencao.data.invalida",null, "Data");
		}else{
			if (ex == null 
					&& form.getDataVencimentoFinal()!= null 
					&& !form.getDataVencimentoFinal().toString().trim().equals("")
					&& Util.converteStringParaDate(form.getDataVencimentoFinal()).after(new Date())){
						
					ex = new ActionServletException(
							"atencao.data.vencimento.final.superior.data.atual" ,null,Util.formatarData(new Date()));
			}
		}
		
		if (ex == null && form.getDataVencimentoFinal()!= null && !"".equals(form.getDataVencimentoFinal())){
			Date dataInicial = Util.converteStringParaDate(form.getDataVencimentoInicio());
			Date dataFinal = Util.converteStringParaDate(form.getDataVencimentoFinal());
			
			if(dataInicial != null && dataFinal != null){
				if(dataFinal.before(dataInicial)){
					ex = new ActionServletException(
							"atencao.data.intervalo.invalido",null, "");
				}
			}
		}
		
		if(ex == null && form.getAnoMesDebitoInicio()!= null && !"".equals(form.getAnoMesDebitoInicio())){
			boolean mesAnoValido = Util.validarMesAno(form.getAnoMesDebitoInicio());
			if(!mesAnoValido){
				ex = new ActionServletException(
						"atencao.referencia.debito.invalida",null, "");
			}
		}
		
		if(ex == null && form.getAnoMesDebitoFinal()!= null && !"".equals(form.getAnoMesDebitoFinal())){
			boolean mesAnoValido = Util.validarMesAno(form.getAnoMesDebitoFinal());
			if(!mesAnoValido){
				ex = new ActionServletException(
						"atencao.referencia.debito.invalida",null, "");
			}else{
				boolean anoMesFinalMenor = (Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesDebitoFinal()) <= Util.recuperaAnoMesDaData(new Date()));
				if(!anoMesFinalMenor){
					ex = new ActionServletException(
							"atencao.referencia.final.superior.data.atual",null, Util.formatarMesAnoReferencia(Util.recuperaAnoMesDaData(new Date())));
				}
			}
			
		}
		
		if(ex == null && form.getAnoMesDebitoInicio()!= null && !"".equals(form.getAnoMesDebitoInicio())
				&& form.getAnoMesDebitoFinal()!= null && !"".equals(form.getAnoMesDebitoFinal())){
			int anoMesIni = Integer.parseInt(form.getAnoMesDebitoInicio().substring(3,7) + form.getAnoMesDebitoInicio().substring(0,2));
			int anoMesFim = Integer.parseInt(form.getAnoMesDebitoFinal().substring(3,7) + form.getAnoMesDebitoFinal().substring(0,2));
	
			if(anoMesFim < anoMesIni){
				ex = new ActionServletException(
						"atencao.ano.mes.intervalo.invalido",null, "");
			}
		}
		
		ContratoParcelamentoCliente clienteContrato = (ContratoParcelamentoCliente) sessao.getAttribute("clienteContrato");
		if(ex == null && clienteContrato != null && clienteContrato.getCliente() != null){
				if(clienteContrato.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica().intValue() != ClienteTipo.INDICADOR_PESSOA_JURIDICA.intValue()){
					ex = new ActionServletException(
							"atencao.cliente.nao.juridico",null, "");
				}else if(clienteContrato.getCliente().getCnpj() == null || clienteContrato.getCliente().getCnpj().equals("")){
					ex = new ActionServletException(
							"atencao.cliente.sem.cnpj", null, "");	
				}
		}
		
		ContratoParcelamentoCliente clienteContratoSuperior = (ContratoParcelamentoCliente) sessao.getAttribute("clienteSuperiorContrato");
			if (ex == null && clienteContratoSuperior != null && clienteContratoSuperior.getCliente() != null){
					if(clienteContratoSuperior.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica().intValue() != ClienteTipo.INDICADOR_PESSOA_JURIDICA.intValue()){
						ex = new ActionServletException(
								"atencao.cliente.superior.nao.juridico",null, "");
					}else if(clienteContratoSuperior.getCliente().getCnpj() == null || clienteContratoSuperior.getCliente().getCnpj().equals("")){
						ex = new ActionServletException(
								"atencao.cliente.superior.sem.cnpj", null, "");	
					}
					
			}
		
		if(ex == null &&  (clienteContratoSuperior == null || clienteContratoSuperior.getCliente() == null) 
				&& (clienteContrato == null || clienteContrato.getCliente() == null)){
			ex = new ActionServletException(
					"atencao.informe.cliente.ou.clientesuperior", null, "");	
		}
		
		if(ex == null && (form.getDataVencimentoPrimParcela() == null || form.getDataVencimentoPrimParcela().equals("") )){
			ex = new ActionServletException(
					"atencao.data.invalida",null, "Data");
		}else{
			int dataVencPrimParcel = Integer.parseInt(Util.recuperaAnoMesDiaDaData(Util.converteStringParaDate(form.getDataVencimentoPrimParcela())));
			int dataAtual = Integer.parseInt(Util.recuperaAnoMesDiaDaData(new Date()));
			
			if(dataVencPrimParcel < dataAtual){
				ex = new ActionServletException(
						"atencao.data.vencimento.primeira.parcel.anterior.corrente",null, form.getDataVencimentoPrimParcela());
				sessao.setAttribute("etapa", "terceira");
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				throw ex;
			}
		}
		
		if(ex != null){
			ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
			sessao.setAttribute("etapa", "primeira");
			throw ex;
		}
		
	}
	
	//[FS0029]
	private void verificaNumeroParcelasComRD(List<PrestacaoContratoParcelamento> listaDeParcelas, ContratoParcelamentoRD resolucao, AtualizarContratoParcelamentoPorClienteActionForm form,  HttpSession sessao, String etapa){
		
		if(etapa.equals("terceira")){
			if( (listaDeParcelas != null && listaDeParcelas.size() > resolucao.getQtdFaturasParceladas().intValue()) || 
					(form.getPacerlaAdd() != null && !form.getPacerlaAdd().equals("") 
							&& Integer.parseInt(form.getPacerlaAdd()) > resolucao.getQtdFaturasParceladas().intValue())){
				ActionServletException ex = new ActionServletException("atencao.numero.parcelas.informado.superior.rd", null, resolucao.getQtdFaturasParceladas()+"");
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;	
			}
		}else {
			if((listaDeParcelas != null && listaDeParcelas.size() > resolucao.getQtdFaturasParceladas().intValue()) || 
					(form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().equals("") 
							&& Integer.parseInt(form.getNumeroParcelasPopUp()) > resolucao.getQtdFaturasParceladas().intValue())){
				ActionServletException ex = new ActionServletException("atencao.numero.parcelas.informado.superior.rd", null, resolucao.getQtdFaturasParceladas()+"");
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;	
			}
		}
		
	}
	
	//[FS0029]
	private void verificaNumeroParcelasSemRD(List<PrestacaoContratoParcelamento> listaDeParcelas, AtualizarContratoParcelamentoPorClienteActionForm form, HttpSession sessao, String etapa){
		int numeroMax = 0;
		try{
			numeroMax = this.getSistemaParametro().getNumeroMaximoParcelasContratosParcelamento();
		}catch (Exception e) {
			ActionServletException ex = new ActionServletException(
					"atencao.numero.maximo.parcela.sistema.parametros.nao.cadastrado", null, "");	
			ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
			sessao.setAttribute("etapa", etapa);
			throw ex;
		}
		if(etapa.equals("terceira")){
			if((listaDeParcelas != null && listaDeParcelas.size() >  numeroMax) || 
					(form.getPacerlaAdd() != null && !form.getPacerlaAdd().equals("") 
							&& Integer.parseInt(form.getPacerlaAdd()) > numeroMax)){
				ActionServletException ex = new ActionServletException(
						"antecao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;
			}
		}else {
			if((listaDeParcelas != null && listaDeParcelas.size() >  numeroMax) || 
					(form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().equals("") 
							&& Integer.parseInt(form.getNumeroParcelasPopUp()) > numeroMax)){
				ActionServletException ex = new ActionServletException(
						"antecao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;
			}
		}
	}
	
	//[FS0025]
	private void verificaValorParceladoTotal(List<PrestacaoContratoParcelamento> listaDeParcelas, BigDecimal valorParceladoTotal){
		
		if(listaDeParcelas != null && listaDeParcelas.size() != 0
				&& valorParceladoTotal != null){
			
			BigDecimal valorParcelTotal = new BigDecimal("0");
			for (PrestacaoContratoParcelamento prestacaoContratoParcelamento : listaDeParcelas) {
				valorParcelTotal = valorParcelTotal.add(prestacaoContratoParcelamento.getValor());
			}
			
			valorParceladoTotal = valorParceladoTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			if(valorParcelTotal.compareTo(valorParceladoTotal) != 0){
				ActionServletException ex = new ActionServletException("atencao.lista.parcelas.valor.total.nao.corresponde", Util.formatarMoedaReal(valorParcelTotal), Util.formatarMoedaReal(valorParceladoTotal));
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				throw ex;
			}
		}
	}
	
	private List<DebitosClienteHelper> carregarListaDebitosAnterior(AtualizarContratoParcelamentoPorClienteActionForm form, HttpSession sessao){

		Fachada fachada = Fachada.getInstancia();
		
		ContratoParcelamento contratoAtualizar = (ContratoParcelamento) sessao.getAttribute("contratoAtualizar");
		Collection<ContratoParcelamentoItem> colecaoContaItens = (Collection<ContratoParcelamentoItem>) sessao.getAttribute("colecaoContaItens");
		Collection<ContratoParcelamentoItem> colecaoDebitosItens = (Collection<ContratoParcelamentoItem>) sessao.getAttribute("colecaoDebitosItens");
		
		//Contas
		if(colecaoContaItens == null){
			FiltroContratoParcelamentoItem filtroItem = new FiltroContratoParcelamentoItem();
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.CONTA_GERAL);
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.DOCUMENTO_TIPO);
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.CONTA_GERAL_CONTA);
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.CONTA_GERAL_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL);
		    filtroItem.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.CONTRATO_ID, contratoAtualizar.getId()));
		    filtroItem.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.DOCUMENTO_TIPO, DocumentoTipo.CONTA));
		    colecaoContaItens = fachada.pesquisar(filtroItem, ContratoParcelamentoItem.class.getName());
		}
		
		String[] contasSelcionadas = new String[colecaoContaItens.size()];
		int indiceVez = 0;
		for (ContratoParcelamentoItem contratoParcelamentoItem : colecaoContaItens) {
			contasSelcionadas[indiceVez] = contratoParcelamentoItem.getContaGeral().getConta().getId().intValue()+"";
			indiceVez++;
		}
		
		Collection<ContaValoresHelper> colecaoContaValores = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
		List<DebitosClienteHelper> listaDebitos = new ArrayList<DebitosClienteHelper>();
		
		for (ContaValoresHelper contaValoresHelper : colecaoContaValores) {
			if(verificaContaSelecionada(contaValoresHelper.getConta().getId().intValue(), contasSelcionadas)){
				DebitosClienteHelper debito = new DebitosClienteHelper();
				debito.setIdentificadorItem(contaValoresHelper.getConta().getId());
				debito.setTipoDocumento(DocumentoTipo.CONTA);
				debito.setValorItem(contaValoresHelper.getConta().getValorTotal());
				debito.setValorAcrescImpont(contaValoresHelper.getValorTotalContaValores());
				listaDebitos.add(debito);
			}
		}
		
		//Débitos
		if(colecaoDebitosItens == null){
			FiltroContratoParcelamentoItem filtroItem = new FiltroContratoParcelamentoItem();
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(
		    		FiltroContratoParcelamentoItem.DEBITO_A_COBRAR_GERAL);
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(
		    		FiltroContratoParcelamentoItem.DOCUMENTO_TIPO);
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(
		    		FiltroContratoParcelamentoItem.DEBITO_A_COBRAR_GERAL_DEBITO);
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(
		    		FiltroContratoParcelamentoItem.DEBITO_A_COBRAR_GERAL_DEBITO_TIPO);
		    filtroItem.adicionarParametro(new ParametroSimples(
		    		FiltroContratoParcelamentoItem.CONTRATO_ID, contratoAtualizar.getId()));
		    filtroItem.adicionarParametro(new ParametroSimples(
		    		FiltroContratoParcelamentoItem.DOCUMENTO_TIPO, DocumentoTipo.DEBITO_A_COBRAR));
		    colecaoDebitosItens = fachada.pesquisar(filtroItem, ContratoParcelamentoItem.class.getName());
		}
		
		String[] debitosSelcionados = new String[colecaoDebitosItens.size()];
		indiceVez = 0;
		for (ContratoParcelamentoItem contratoParcelamentoItem : colecaoDebitosItens) {
			debitosSelcionados[indiceVez] = contratoParcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getId().intValue()+"";
			indiceVez++;
		}
		
		Collection<DebitoACobrar> colecaoDebitoACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
		
		for (DebitoACobrar debitoACobrar : colecaoDebitoACobrar) {
			if(verificaContaSelecionada(debitoACobrar.getId().intValue(), debitosSelcionados)){
				DebitosClienteHelper debito = new DebitosClienteHelper();
				debito.setIdentificadorItem(debitoACobrar.getId());
				debito.setTipoDocumento(DocumentoTipo.DEBITO_A_COBRAR);
				debito.setValorItem(debitoACobrar.getValorTotal());
				debito.setValorAcrescImpont(null);
				listaDebitos.add(debito);
			}
		}
		
		return listaDebitos;
	}
	
	private boolean verificaContaSelecionada(int idConta, String[] contasSelecao){
		boolean retorno = false;
		
		for (String conta : contasSelecao) {
			
			if(idConta == Integer.parseInt(conta)){
				return true;
			}
		}
		
		return retorno;
	}

}

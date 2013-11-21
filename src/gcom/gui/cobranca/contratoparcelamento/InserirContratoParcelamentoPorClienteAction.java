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
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamento;
import gcom.cobranca.contratoparcelamento.FiltroQuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.InserirContratoParcelamentoValoresParcelasHelper;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoesRDHelper;
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
 * Form que Inseri um ContratoParcelamentoPorCliente
 * 
 * @author Paulo Otávio
 * @date 04/04/2011
 */
public class InserirContratoParcelamentoPorClienteAction extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclusão de um ContratoParcelamentoPorCliente
	 * 
	 * [UC1136] Inserir Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
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
		
		ActionForward retorno = actionMapping.findForward("exibirInserir");
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		InserirContratoParcelamentoPorClienteActionForm form = (InserirContratoParcelamentoPorClienteActionForm) actionForm;
		
		carregarCamposDoFormulario(form, sessao);
		validarCampos(form, sessao);
		
		ContratoParcelamento contratoCadastrar = (ContratoParcelamento) sessao.getAttribute("contratoCadastrar");
		List<PrestacaoContratoParcelamento> listaDeParcelas = (List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelas");

		//[FS0029]
		if(contratoCadastrar.getResolucaoDiretoria() != null){
			verificaNumeroParcelasComRD(listaDeParcelas, contratoCadastrar.getResolucaoDiretoria(), form, sessao, "terceira");
		}else{
			verificaNumeroParcelasSemRD(listaDeParcelas, form, sessao, "terceira");
		}
		
		BigDecimal valorConta = (BigDecimal) sessao.getAttribute("valorContaSelecao");
		contratoCadastrar.setValorTotalConta(valorConta);
		
		BigDecimal valorContaDebitosACobrar = (BigDecimal) sessao.getAttribute("valorContaDebitosACobrar");
		contratoCadastrar.setValorTotalDebitosCobrar(valorContaDebitosACobrar);
		
		BigDecimal valorAcrescimo = (BigDecimal) sessao.getAttribute("valorAcrescimoSelecao");
		if(form.getIndicadorDebitoAcresc() != null && form.getIndicadorDebitoAcresc().equals("1")){
			contratoCadastrar.setValorTotalAcrescImpontualidade(valorAcrescimo);
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
		
		contratoCadastrar.setQtdFaturasParceladas(listaDebitos.size());
		
		contratoCadastrar.setIndicadorContasRevisao(new Short("2"));
		contratoCadastrar.setIndicadorDebitoACobrar(new Short("2"));
		contratoCadastrar.setIndicadorCreditoARealizar(new Short("2"));
		for (DebitosClienteHelper debitosClienteHelper : listaDebitos) {
			if(debitosClienteHelper.getTipoDocumento() == DocumentoTipo.CONTA){
				contratoCadastrar.setIndicadorContasRevisao(new Short("1"));		
			} 
			
			if(debitosClienteHelper.getTipoDocumento() == DocumentoTipo.DEBITO_A_COBRAR){
				contratoCadastrar.setIndicadorDebitoACobrar(new Short("1"));		
			} 
			
			if(debitosClienteHelper.getTipoDocumento() == DocumentoTipo.CREDITO_A_REALIZAR){
				contratoCadastrar.setIndicadorCreditoARealizar(new Short("1"));		
			} 
		}
		
		BigDecimal valorParcelado = null;
		BigDecimal jurosVal = BigDecimal.ZERO;
		if(contratoCadastrar.getIndicadorParcelamentoJuros().intValue() == 1 
				&& form.getTaxaJurosAdd() != null && !form.getTaxaJurosAdd().trim().equals("")){
			String juros = null;
			if (form.getTaxaJurosAdd() != null && !form.getTaxaJurosAdd().equals("")) {
				contratoCadastrar.setTaxaJuros(new BigDecimal(form.getTaxaJurosAdd().replace(",", ".")));
				juros = form.getTaxaJurosAdd();
			} else {
				contratoCadastrar.setTaxaJuros(null);
			}
			
			if(juros.equals("")){
				juros = "0";
			}
			String indicadorDebitoAcresc = form.getIndicadorDebitoAcresc();

			BigDecimal valorContaSelecaoTotal = (BigDecimal) sessao.getAttribute("valorContaSelecaoTotal");
			BigDecimal valorContaComAcrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecaoTotal");
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			InserirContratoParcelamentoValoresParcelasHelper helper = Fachada.getInstancia()
				.calcularValoresParcelasContratoParcelamento(valorContaSelecaoTotal, valorContaComAcrescimo, 
					indicadorDebitoAcresc, contratoCadastrar.getIndicadorParcelamentoJuros().toString(), 
					new BigDecimal(juros.replace(",", ".")), 1, Integer.parseInt(form.getPacerlaAdd()));

			valorParcelado = helper.getValorParcelado();
			
			if (new Short(contratoCadastrar.getIndicadorParcelamentoJuros()).compareTo(ConstantesSistema.SIM) == 0
					&& sistemaParametro.getIndicadorTabelaPrice() != null 
					&& sistemaParametro.getIndicadorTabelaPrice().equals(ConstantesSistema.SIM)) {
				jurosVal = valorParcelado.subtract(valorContaComAcrescimo);
			} else if(Integer.parseInt(indicadorDebitoAcresc) == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()){
				jurosVal = valorContaComAcrescimo.multiply(new BigDecimal(juros.replace(",", "."))).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP); 
			} else {
				jurosVal = valorConta.multiply(new BigDecimal(juros.replace(",", "."))).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP); 
			}
			
			contratoCadastrar.setValorJurosParcelamento(jurosVal);
			
			if(jurosVal != null && jurosVal.floatValue() > 0.0){
				contratoCadastrar.setValorDebitoAtualizado(valorParcelado.subtract(jurosVal));
			}else{
				contratoCadastrar.setValorDebitoAtualizado(valorParcelado);
			}
			
		}else{
			String indicadorDebitoAcresc = form.getIndicadorDebitoAcresc();
			if(Integer.parseInt(indicadorDebitoAcresc) == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()){
				BigDecimal valorContaComAcrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecaoTotal");
				valorParcelado = valorContaComAcrescimo;
			}else{
				valorParcelado = valorConta;
			}
			contratoCadastrar.setValorDebitoAtualizado(valorParcelado);
		}
		
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
		
		contratoCadastrar.setAnoMesReferenciaFaturamento(anoMesReferenciaFaturamento);
		
		contratoCadastrar.setDataImplantacao(new Date());
		contratoCadastrar.setUltimaAlteracao(new Date());
		
		if(contratoCadastrar.getResolucaoDiretoria() != null 
				&& form.getParcelaSelecao() != null 
				&& !form.getParcelaSelecao().equals("")){
			List<QuantidadePrestacoesRDHelper> colecaoQuantidadePrestacoesRDHelper = (List<QuantidadePrestacoesRDHelper>) sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper");
			
			for (QuantidadePrestacoesRDHelper quantidadePrestacoesRDHelper : colecaoQuantidadePrestacoesRDHelper) {
				if(quantidadePrestacoesRDHelper.getIdQuantidadePrestacoes().intValue() ==  Integer.parseInt(form.getParcelaSelecao())){

					FiltroQuantidadePrestacoes filtroQuantidadePrestacoes = new FiltroQuantidadePrestacoes();
					filtroQuantidadePrestacoes.adicionarParametro(new ParametroSimples(FiltroQuantidadePrestacoes.ID, quantidadePrestacoesRDHelper.getIdQuantidadePrestacoes()));
					Collection<QuantidadePrestacoes> colecaoQuantidadePrestacoes = fachada.pesquisar(filtroQuantidadePrestacoes, QuantidadePrestacoes.class.getName());
					QuantidadePrestacoes quantidadePrestacoes = (QuantidadePrestacoes) Util.retonarObjetoDeColecao(colecaoQuantidadePrestacoes);
					
					BigDecimal valorContaComAcrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecaoTotal");
					BigDecimal valorContaSelecaoTotal = (BigDecimal) sessao.getAttribute("valorContaSelecaoTotal");
					
					InserirContratoParcelamentoValoresParcelasHelper helper = fachada
						.calcularValoresParcelasContratoParcelamentoRD(valorContaSelecaoTotal, valorContaComAcrescimo, 
								form.getIndicadorDebitoAcresc(), form.getIndicadorParcelJuros(), contratoCadastrar, 
								quantidadePrestacoes);

					if (contratoCadastrar.getValorJurosParcelamento() == null 
							|| contratoCadastrar.getValorJurosParcelamento().compareTo(BigDecimal.ZERO) == 0) {
						contratoCadastrar.setValorJurosParcelamento(jurosVal);
					}
					
					valorParcelado = helper.getValorParcelado();
					contratoCadastrar = helper.getContratoParcelamento();
					listaDeParcelas = helper.getListaDeParcelas();
					
				}
			}
			verificaValorParceladoTotal(listaDeParcelas, contratoCadastrar.getValorTotalParcelado());
			
		}else{
			contratoCadastrar.setIndicadorParcelaInformadaPeloUsuario(new Short("1"));
			
			//[FS0025]
			verificaValorParceladoTotal(listaDeParcelas, contratoCadastrar.getValorTotalParcelado());
		}
		
		ParcelamentoSituacao parcelamentoSituacao = new ParcelamentoSituacao();
		parcelamentoSituacao.setId(ParcelamentoSituacao.NORMAL);
		contratoCadastrar.setParcelamentoSituacao(parcelamentoSituacao);
		
		
		ContratoParcelamentoCliente clienteContrato = (ContratoParcelamentoCliente) sessao.getAttribute("clienteContrato");
		ContratoParcelamentoCliente clienteSuperiorContrato = (ContratoParcelamentoCliente) sessao.getAttribute("clienteSuperiorContrato");
		
		if(contratoCadastrar.getNumeroPrestacoes() == null){
			if (form.getPacerlaAdd() != null && !form.getPacerlaAdd().trim().equals("")) {

				contratoCadastrar.setNumeroPrestacoes(Integer.parseInt(form.getPacerlaAdd()));
				
			} else if (form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().trim().equals("")) {
				contratoCadastrar.setNumeroPrestacoes(Integer.parseInt(form.getNumeroParcelasPopUp()));
			}
		}
		
		fachada.inserirContratoParcelamentoPorCliente(contratoCadastrar, usuarioLogado, clienteContrato, clienteSuperiorContrato, listaDebitos, listaDeParcelas);
		
		retorno = actionMapping.findForward("telaSucesso");
		
		montarPaginaSucessoDoisRelatorios(
				httpServletRequest,
				"Contrato de parcelamento por cliente - "+contratoCadastrar.getNumero()+" - inserido com sucesso",
				"Inserir Contrato de Parcelamento por Cliente",
				"exibirInserirContratoParcelamentoClienteAction.do?menu=sim",
				"exibirAtualizarContratoParcelamentoClienteAction.do?contratoAtualizarNumero="+contratoCadastrar.getNumero(),
				"Atualizar Contrato de Parcelamento por Cliente",
				"Emitir Extrato de Contrato de Parcelamento por Cliente",
				"exibirEmitirExtratoContratoParcelamentoPorClienteAction.do?inserirContrato=sim&contratoAtualizarNumero="+contratoCadastrar.getNumero(),
				"Emitir Contrato de Parcelamento por Cliente",
				"gerarRelatorioEmitirContratoParcelamentoPorClienteAction.do?inserirContrato=sim&contratoNumero="+contratoCadastrar.getNumero());
		
		return retorno;
	}
	
	/**Inicio dos metodos Privados**/
	private void carregarCamposDoFormulario(InserirContratoParcelamentoPorClienteActionForm form, HttpSession sessao){
		ContratoParcelamento contratoCadastrar = (ContratoParcelamento) sessao.getAttribute("contratoCadastrar");
		
		if (contratoCadastrar == null) {
			contratoCadastrar = new ContratoParcelamento();
		}
		
		if (form.getNumeroContrato() != null && !"".equals(form.getNumeroContrato())){
			contratoCadastrar.setNumero(form.getNumeroContrato());
		}else{
			contratoCadastrar.setNumero(null);
		}
		
		if (form.getNumeroContratoAnt() != null && !"".equals(form.getNumeroContratoAnt())){
			FiltroContratoParcelamento filtro = new FiltroContratoParcelamento();
			filtro.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamento.NUMERO, form.getNumeroContratoAnt()));
			Collection<ContratoParcelamento> contratos = fachada.pesquisar(filtro, ContratoParcelamento.class.getName());
			if(contratos != null && contratos.size() == 1){
				contratoCadastrar.setContratoAnterior(contratos.iterator().next());
			}
		}else{
			contratoCadastrar.setContratoAnterior(null);
		}
		
		if (form.getDataContrato() != null && !"".equals(form.getDataContrato())){
			contratoCadastrar.setDataContrato(Util.converteStringParaDate(form.getDataContrato()));
		}else{
			contratoCadastrar.setDataContrato(null);
		}
		

		if (form.getLoginUsuario() != null && !form.getLoginUsuario().toString().trim().equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLoginUsuario()));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				contratoCadastrar.setUsuarioResponsavel(usuario);
			} else {
				contratoCadastrar.setUsuarioResponsavel(null);
			}
			
		}else{
			contratoCadastrar.setUsuarioResponsavel(null);
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
			contratoCadastrar.setRelacaoCliente(relacaoCliente);
		}else{
			contratoCadastrar.setRelacaoCliente(null);
		}
		
		if(form.getIndicadorResponsavel() != null && !form.getIndicadorResponsavel().equals("")){
			if(form.getIndicadorResponsavel().equals("" + ContratoParcelamento.RESP_ATUAL_DO_IMOVEL)){
				contratoCadastrar.setIndicadorResponsavel(ContratoParcelamento.RESP_ATUAL_DO_IMOVEL);
			}else if(form.getIndicadorResponsavel().equals("" + ContratoParcelamento.RESP_INDICADOR_NA_CONTA)){
				contratoCadastrar.setIndicadorResponsavel(ContratoParcelamento.RESP_INDICADOR_NA_CONTA);
			}else if(form.getIndicadorResponsavel().equals("" + ContratoParcelamento.RESP_TODOS)){
				contratoCadastrar.setIndicadorResponsavel(ContratoParcelamento.RESP_TODOS);
			} 
		}
		
		if(form.getDataVencimentoInicio() != null && !form.getDataVencimentoInicio().equals("")){
			Date dataVencimentoInicio = Util.converteStringParaDate(form.getDataVencimentoInicio());
			contratoCadastrar.setDataVencimentoInicio(dataVencimentoInicio);
		}else{
			contratoCadastrar.setDataVencimentoInicio(null);
		}
		
		if(form.getDataVencimentoFinal() != null && !form.getDataVencimentoFinal().equals("")){
			Date dataVencimentoFinal = Util.converteStringParaDate(form.getDataVencimentoFinal());
			contratoCadastrar.setDataVencimentoFinal(dataVencimentoFinal);
		}else{
			contratoCadastrar.setDataVencimentoFinal(null);
		}
		
		if(form.getAnoMesDebitoInicio() != null && !form.getAnoMesDebitoInicio().equals("")){
			boolean mesAnoValido = Util.validarMesAno(form.getAnoMesDebitoInicio());
			if(mesAnoValido){
				String anoMes = form.getAnoMesDebitoInicio().replace("/", "");
				anoMes = anoMes.substring(2,6) + anoMes.substring(0,2);
				contratoCadastrar.setAnoMesDebitoInicio(Integer.parseInt(anoMes));
			}
		}else{
			contratoCadastrar.setAnoMesDebitoInicio(null);
		}
		
		if(form.getAnoMesDebitoFinal() != null && !form.getAnoMesDebitoFinal().equals("")){
			boolean mesAnoValido = Util.validarMesAno(form.getAnoMesDebitoFinal());
			if(mesAnoValido){
				String anoMes = form.getAnoMesDebitoFinal().replace("/", "");
				anoMes = anoMes.substring(2,6) + anoMes.substring(0,2);
				contratoCadastrar.setAnoMesDebitoFinal(Integer.parseInt(anoMes));
			}
		}else{
			contratoCadastrar.setAnoMesDebitoFinal(null);
		}
		
		if(form.getObservacao() != null && !form.getObservacao().equals("")){
			contratoCadastrar.setObservacao(form.getObservacao());
		}else{
			contratoCadastrar.setObservacao(null);
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
				sessao.setAttribute("valorContaSelecao",valorConta);
				sessao.setAttribute("valorAcrescimoSelecao",valorAcresc);
				sessao.setAttribute("valorContaAcrescimoSelecao",valorAcresc.add(valorConta));
			}
		}else{
			sessao.removeAttribute("listaDebitos");
			sessao.removeAttribute("valorContaSelecao");
			sessao.removeAttribute("valorAcrescimoSelecao");
			sessao.removeAttribute("valorContaAcrescimoSelecao");
		}
		
		if(form.getDebitosACobrar() != null && form.getDebitosACobrar().length > 0){
			
			Collection<DebitoACobrar> colecaoDebitoACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
			BigDecimal valorContaDebitosACobrar = new BigDecimal(0);
			
			List<DebitosClienteHelper> listaDebitosACobrar = new ArrayList<DebitosClienteHelper>();
			
			if(colecaoDebitoACobrar != null){
				for (DebitoACobrar debitoACobrar : colecaoDebitoACobrar) {
					if(form.verificaDebitoACobrarSelecionado(debitoACobrar.getId().intValue())){
						DebitosClienteHelper debito = new DebitosClienteHelper();
						
						debito.setIdentificadorItem(debitoACobrar.getId());
						debito.setTipoDocumento(DocumentoTipo.DEBITO_A_COBRAR);
						debito.setValorItem(debitoACobrar.getValorTotal());
						debito.setValorAcrescImpont(null);
						
						listaDebitosACobrar.add(debito);
						
						valorContaDebitosACobrar = valorContaDebitosACobrar.add(debitoACobrar.getValorTotal());
					}
				}
				
				sessao.setAttribute("listaDebitosACobrar",listaDebitosACobrar);
				sessao.setAttribute("valorContaDebitosACobrar",valorContaDebitosACobrar);
			}
		}else{
			sessao.removeAttribute("listaDebitosACobrar");
			sessao.removeAttribute("valorContaDebitosACobrar");
		}
		
		
		if(form.getResolucaoDiretoria() != null && !form.getResolucaoDiretoria().equals("")){
			
			ContratoParcelamentoRD resolucao = fachada.pesquisarContratoParcelamentoRDPorNumero(form.getResolucaoDiretoria());
			contratoCadastrar.setResolucaoDiretoria(resolucao);
			
			contratoCadastrar.setIndicadorDebitosAcrescimos(resolucao.getIndicadorDebitoAcrescimo());
			contratoCadastrar.setIndicadorParcelamentoJuros(resolucao.getIndicadorParcelamentoJuros());
			contratoCadastrar.setIndicadorPermiteInformarValorParcel(resolucao.getIndicadorInformarParcela());
			contratoCadastrar.setCobrancaForma(resolucao.getCobrancaForma());
			
			List<QuantidadePrestacoes> parcelas = null;
			FiltroQuantidadePrestacoes filtroQtdPrestacoes = new FiltroQuantidadePrestacoes(FiltroQuantidadePrestacoes.QTD_FATURAS_PARCELADAS);
			filtroQtdPrestacoes.adicionarParametro(new ComparacaoTexto(
					FiltroQuantidadePrestacoes.CONTRATO_PARCELAMENTO_RD_NUMERO, contratoCadastrar.getResolucaoDiretoria().getNumero().toUpperCase()));
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
								form.getIndicadorDebitoAcresc(), form.getIndicadorParcelJuros(), contratoCadastrar, 
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
			contratoCadastrar.setResolucaoDiretoria(null);
			sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
			
			if(form.getIndicadorDebitoAcresc() != null && !form.getIndicadorDebitoAcresc().equals("")){
				contratoCadastrar.setIndicadorDebitosAcrescimos(Short.parseShort(form.getIndicadorDebitoAcresc()));
			}
			
			if(form.getIndicadorParcelJuros() != null && !form.getIndicadorParcelJuros().equals("")){
				contratoCadastrar.setIndicadorParcelamentoJuros(Short.parseShort(form.getIndicadorParcelJuros()));
			}
			
			if(form.getIndicadorInfoVlParcel() != null && !form.getIndicadorInfoVlParcel().equals("")){
				contratoCadastrar.setIndicadorPermiteInformarValorParcel(Short.parseShort(form.getIndicadorInfoVlParcel()));
			}
			
			if(form.getFormaPgto() != null && !form.getFormaPgto().equals("")){
				FiltroCobrancaForma filtroCobranca = new FiltroCobrancaForma();
				filtroCobranca.adicionarParametro(new ParametroSimples(FiltroCobrancaForma.ID, form.getFormaPgto()));
				Collection<CobrancaForma> formasPagto = fachada.pesquisar(filtroCobranca, CobrancaForma.class.getName());
				for (CobrancaForma formaPgto : formasPagto) {
					if(formaPgto.getId() == Integer.parseInt(form.getFormaPgto())){
						contratoCadastrar.setCobrancaForma(formaPgto);
					}
				}
			}else{
				contratoCadastrar.setCobrancaForma(null);
			}
		}
		
		if(form.getDataVencimentoPrimParcela() != null && !form.getDataVencimentoPrimParcela().equals("")){
			Date dataVencimentoPrimParcela = Util.converteStringParaDate(form.getDataVencimentoPrimParcela());
			contratoCadastrar.setDataVencimentoPrimParcela(dataVencimentoPrimParcela);
		}
		
		if(form.getNumeroEntreVencParcelas() != null && !form.getNumeroEntreVencParcelas().equals("")){
			contratoCadastrar.setNumeroDiasEntreVencimentoParcel(Integer.parseInt(form.getNumeroEntreVencParcelas()));
		}else{
			contratoCadastrar.setNumeroDiasEntreVencimentoParcel(30);
		}
		
		List<QuantidadePrestacoesRDHelper> colecaoQuantidadePrestacoesRDHelper = (List<QuantidadePrestacoesRDHelper>) sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper");
		if(form.getPacerlaAdd() != null && !form.getPacerlaAdd().equals("")
				&& (colecaoQuantidadePrestacoesRDHelper == null || colecaoQuantidadePrestacoesRDHelper.size() == 0)){
			contratoCadastrar.setNumeroPrestacoes(Integer.parseInt(form.getPacerlaAdd()));
		} else if (form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().equals("")){
			contratoCadastrar.setNumeroPrestacoes(Integer.parseInt(form.getNumeroParcelasPopUp()));
		} else {
			contratoCadastrar.setNumeroPrestacoes(null);
		}
		
		if(form.getTaxaJurosAdd() != null && !form.getTaxaJurosAdd().equals("")
				&& (colecaoQuantidadePrestacoesRDHelper == null || colecaoQuantidadePrestacoesRDHelper.size() == 0)){
			String juros = form.getTaxaJurosAdd().replace(".", ",");
			juros = juros.replace(",", ".");
			contratoCadastrar.setTaxaJuros(new BigDecimal(juros));
		} else{
			contratoCadastrar.setTaxaJuros(null);
		}
		
		sessao.setAttribute("contratoCadastrar", contratoCadastrar);
	}
	
	private void validarCampos(InserirContratoParcelamentoPorClienteActionForm form, HttpSession sessao) throws ActionServletException{
		ActionServletException ex = null;
		
		if (form.getNumeroContrato() == null || "".equals(form.getNumeroContrato())){
			ex = new ActionServletException(
					"atencao.required", null, "Número");
		}else{
			FiltroContratoParcelamento filtro = new FiltroContratoParcelamento();
			filtro.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamento.NUMERO, form.getNumeroContrato()));
			Collection<ContratoParcelamento> contratos = fachada.pesquisar(filtro, ContratoParcelamento.class.getName());
			if(contratos != null && contratos.size() > 0){
				ex = new ActionServletException(
						"atencao.numero.contrato.existente",null, "");	
			}
		}
		
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
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				throw ex;
			}
		}
		
		if(ex != null){
			ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
			sessao.setAttribute("etapa", "primeira");
			throw ex;
		}
		
	}
	
	//[FS0029]
	private void verificaNumeroParcelasComRD(List<PrestacaoContratoParcelamento> listaDeParcelas, ContratoParcelamentoRD resolucao, InserirContratoParcelamentoPorClienteActionForm form,  HttpSession sessao, String etapa){
		
		if(etapa.equals("terceira")){
			if( (listaDeParcelas != null && listaDeParcelas.size() > resolucao.getQtdFaturasParceladas().intValue()) || 
					(form.getPacerlaAdd() != null && !form.getPacerlaAdd().equals("") 
							&& Integer.parseInt(form.getPacerlaAdd()) > resolucao.getQtdFaturasParceladas().intValue())){
				ActionServletException ex = new ActionServletException("atencao.numero.parcelas.informado.superior.rd", null, resolucao.getQtdFaturasParceladas()+"");
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;	
			}
		}else {
			if((listaDeParcelas != null && listaDeParcelas.size() > resolucao.getQtdFaturasParceladas().intValue()) || 
					(form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().equals("") 
							&& Integer.parseInt(form.getNumeroParcelasPopUp()) > resolucao.getQtdFaturasParceladas().intValue())){
				ActionServletException ex = new ActionServletException("atencao.numero.parcelas.informado.superior.rd", null, resolucao.getQtdFaturasParceladas()+"");
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;	
			}
		}
		
	}
	
	//[FS0029]
	private void verificaNumeroParcelasSemRD(List<PrestacaoContratoParcelamento> listaDeParcelas, InserirContratoParcelamentoPorClienteActionForm form, HttpSession sessao, String etapa){
		int numeroMax = 0;
		try{
			numeroMax = this.getSistemaParametro().getNumeroMaximoParcelasContratosParcelamento();
		}catch (Exception e) {
			ActionServletException ex = new ActionServletException(
					"atencao.numero.maximo.parcela.sistema.parametros.nao.cadastrado", null, "");	
			ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
			sessao.setAttribute("etapa", etapa);
			throw ex;
		}
		if(etapa.equals("terceira")){
			if((listaDeParcelas != null && listaDeParcelas.size() >  numeroMax) || 
					(form.getPacerlaAdd() != null && !form.getPacerlaAdd().equals("") 
							&& Integer.parseInt(form.getPacerlaAdd()) > numeroMax)){
				ActionServletException ex = new ActionServletException(
						"antecao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;
			}
		}else {
			if((listaDeParcelas != null && listaDeParcelas.size() >  numeroMax) || 
					(form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().equals("") 
							&& Integer.parseInt(form.getNumeroParcelasPopUp()) > numeroMax)){
				ActionServletException ex = new ActionServletException(
						"antecao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
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
			
			if(valorParcelTotal.floatValue() != valorParceladoTotal.floatValue()){
				ActionServletException ex = new ActionServletException("atencao.lista.parcelas.valor.total.nao.corresponde", Util.formatarMoedaReal(valorParcelTotal), Util.formatarMoedaReal(valorParceladoTotal));
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				throw ex;
			}
		}
	}

}

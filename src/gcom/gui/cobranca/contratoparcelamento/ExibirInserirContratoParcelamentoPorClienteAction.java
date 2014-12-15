package gcom.gui.cobranca.contratoparcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroCobrancaForma;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.contratoparcelamento.ComparatorParcela;
import gcom.cobranca.contratoparcelamento.ComparatorPrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamento;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroQuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.FiltroTipoRelacao;
import gcom.cobranca.contratoparcelamento.InserirContratoParcelamentoValoresParcelasHelper;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoesRDHelper;
import gcom.cobranca.contratoparcelamento.TipoRelacao;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de Inserir Contrato Parcelamento por Cliente
 * 
 * @author Paulo Diniz
 * @created 16/03/2011
 */
public class ExibirInserirContratoParcelamentoPorClienteAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um contrato parcelamento por cliente
	 * 
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz, Mariana Victor
	 * @date 04/04/2011, 30/06/2011
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
	
	    //Seta o retorno
	    ActionForward retorno = actionMapping.findForward("exibirInserir");
	    
	    String method = httpServletRequest.getParameter("method");
	    
	    HttpSession sessao = httpServletRequest.getSession(false);
	    
	    Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

	    //Obtém a instância da fachada
	    Fachada fachada = Fachada.getInstancia();

		InserirContratoParcelamentoPorClienteActionForm form = (InserirContratoParcelamentoPorClienteActionForm) actionForm;

        sessao.removeAttribute("mensagemAlerta");
        
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
        
    	boolean permissaoEspecial = Fachada.getInstancia().verificarPermissaoEspecial(
				PermissaoEspecial.CONSULTAR_DEBITOS_ATUAL_DO_IMOVEL_OU_TODOS,
				usuarioLogado);
    	sessao.setAttribute("permissaoEspecial", permissaoEspecial);
        
    	boolean permissaoEspecialDebitoAcrescimos = Fachada.getInstancia().verificarPermissaoEspecial(
				PermissaoEspecial.PARCELAR_SEM_INCLUIR_ACRESCIMOS_POR_IMPONTUALIDADE,
				usuarioLogado);
    	sessao.setAttribute("permissaoEspecialDebitoAcrescimos", permissaoEspecialDebitoAcrescimos);
    	
        ContratoParcelamento contratoCadastrar = (ContratoParcelamento) sessao.getAttribute("contratoCadastrar");
		
		if (contratoCadastrar == null) {
			sessao.setAttribute("etapa", "primeira");
			
			contratoCadastrar = new ContratoParcelamento();
			contratoCadastrar.setDataContrato(new Date());
			
			contratoCadastrar.setAnoMesDebitoFinal(this.getSistemaParametro().getAnoMesArrecadacao());
			
			GregorianCalendar dataVencPrimeiraParc = new GregorianCalendar();
			if(dataVencPrimeiraParc.get(Calendar.DAY_OF_MONTH) > 28){
				dataVencPrimeiraParc.set(Calendar.MONTH, dataVencPrimeiraParc.get(Calendar.MONTH) + 1);
				dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
			}else{
				dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
			}
			
			contratoCadastrar.setIndicadorDebitosAcrescimos(new Short("1"));
			contratoCadastrar.setIndicadorParcelamentoJuros(new Short("1"));
			contratoCadastrar.setIndicadorPermiteInformarValorParcel(new Short("2"));
			
			contratoCadastrar.setDataVencimentoPrimParcela(dataVencPrimeiraParc.getTime());
		}
		
		if (httpServletRequest.getParameter("consulta") != null
				&& httpServletRequest.getParameter("consulta").toString().trim().equalsIgnoreCase("usuario")) {

			carregarCamposDoFormulario(form, sessao);
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLoginUsuario()));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			// [FS0009] - Verificar existência do usuário
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				form.setNomeUsuario(usuario.getNomeUsuario());
				form.setLoginUsuario(usuario.getLogin());
				contratoCadastrar.setUsuarioResponsavel(usuario);
				sessao.setAttribute("contratoCadastrar", contratoCadastrar);
				
				sessao.setAttribute("usuarioEncontrado","true");
			} else {

				sessao.removeAttribute("usuarioEncontrado");
				form.setLoginUsuario("");
				form.setNomeUsuario("Usuário Inexistente");
				
				contratoCadastrar.setUsuarioResponsavel(null);
				sessao.setAttribute("contratoCadastrar", contratoCadastrar);
			}
			sessao.setAttribute("etapa", "primeira");
			
		} else if (httpServletRequest.getParameter("consulta") != null
				&& httpServletRequest.getParameter("consulta").toString().trim().equalsIgnoreCase("contratoAnterior")) {

			carregarCamposDoFormulario(form, sessao);
			
			FiltroContratoParcelamento filtro = new FiltroContratoParcelamento();
			filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");
			filtro.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamento.NUMERO, form.getNumeroContratoAnt()));
			Collection<ContratoParcelamento> contratos = fachada.pesquisar(filtro, ContratoParcelamento.class.getName());
			
			// [FS0008] – Verificar existência do contrato anterior
			if(contratos == null || contratos.isEmpty()){
				form.setNumeroContratoAnt("");
				contratoCadastrar.setContratoAnterior(null);
				sessao.setAttribute("contratoCadastrar", contratoCadastrar);
				
				throw new ActionServletException(
						"atencao.numero.contrato.nao.existe");	
				
			} else { 
				
				// [FS0036] - Verificar situação do contrato anterior
				ContratoParcelamento contrato = (ContratoParcelamento) Util.retonarObjetoDeColecao(contratos);
				
				if(contrato.getParcelamentoSituacao().getId().compareTo(ParcelamentoSituacao.NORMAL) == 0){
					throw new ActionServletException(
							"atencao.contrato.parcelamento.anterior.nao.encerrado");	
				}
				
				contratoCadastrar.setContratoAnterior(contrato);
				sessao.setAttribute("contratoCadastrar", contratoCadastrar);
				
			}

			sessao.setAttribute("etapa", "primeira");
			
		} else if(method == null || method.equals("")){
			sessao.setAttribute("contratoCadastrar", contratoCadastrar);
		} else if(method != null &&  method.equals("desfazerContrato")){
			
			sessao.removeAttribute("clienteContrato");
			sessao.removeAttribute("clienteSuperiorContrato");
			
			sessao.removeAttribute("listaDeParcelas");
			sessao.removeAttribute("listaValoresDistintos");
			sessao.removeAttribute("listaDeParcelasPopUp");
			sessao.removeAttribute("listaValoresDistintosPopUp");
			sessao.removeAttribute("numeroParcelasPopUp");
			sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
			sessao.removeAttribute("finalizou");
			sessao.setAttribute("etapa", "primeira");
			
			form.setLoginUsuario("");
			form.setNomeUsuario("");
			
			contratoCadastrar = new ContratoParcelamento();
			contratoCadastrar.setDataContrato(new Date());
			
			contratoCadastrar.setAnoMesDebitoFinal(this.getSistemaParametro().getAnoMesArrecadacao());
			
			GregorianCalendar dataVencPrimeiraParc = new GregorianCalendar();
			if(dataVencPrimeiraParc.get(Calendar.DAY_OF_MONTH) > 28){
				dataVencPrimeiraParc.set(Calendar.MONTH, dataVencPrimeiraParc.get(Calendar.MONTH) + 1);
				dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
			}else{
				dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
			}
			
			contratoCadastrar.setIndicadorDebitosAcrescimos(new Short("1"));
			contratoCadastrar.setIndicadorParcelamentoJuros(new Short("1"));
			contratoCadastrar.setIndicadorPermiteInformarValorParcel(new Short("2"));
			contratoCadastrar.setDataVencimentoPrimParcela(dataVencPrimeiraParc.getTime());
			sessao.setAttribute("contratoCadastrar", contratoCadastrar);
			
		}else if(method != null && method.equals("mostrarPrimeiraEtapa")){
		    
			carregarCamposDoFormulario(form, sessao);
			//validarCampos(form, sessao, method);

			sessao.setAttribute("etapa", "primeira");
			
		}else if(method != null && method.equals("mostrarSegundaEtapa")){
		    
			carregarCamposDoFormulario(form, sessao);
			validarCampos(form, sessao, method);

			calcularDebitosCliente(sessao);
			sessao.setAttribute("etapa", "segunda");
			
		} else if (httpServletRequest.getParameter("method") != null
				&& httpServletRequest.getParameter("method")
					.toString().trim().equalsIgnoreCase("limparTotalizacaoParcelas")) {

			carregarCamposDoFormulario(form, sessao);
			this.removerValoresParcelas(sessao, form, false);
		
		} else if (httpServletRequest.getParameter("method") != null
				&& httpServletRequest.getParameter("method")
					.toString().trim().equalsIgnoreCase("limparListaParcelas")) {

			carregarCamposDoFormulario(form, sessao);
			this.removerValoresParcelas(sessao, form, true);
			
		} else if(method != null && method.equals("mostrarTerceiraEtapa")){
			
			carregarCamposDoFormulario(form, sessao);

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
			
			if(sessao.getAttribute("finalizou") == null){
				sessao.setAttribute("finalizou", true);
			}
			
			List<QuantidadePrestacoesRDHelper> colecaoQuantidadePrestacoesRDHelper = (List<QuantidadePrestacoesRDHelper>) sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper");
			if(form.getPacerlaAdd() != null && !form.getPacerlaAdd().equals("") && 
					(colecaoQuantidadePrestacoesRDHelper == null || colecaoQuantidadePrestacoesRDHelper.size() == 0)){
				
				List<PrestacaoContratoParcelamento> listaDeParcelas = (List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelas");
				//[FS0029]
				if(contratoCadastrar.getResolucaoDiretoria() != null){
					verificaNumeroParcelasComRD(listaDeParcelas, contratoCadastrar.getResolucaoDiretoria(), form, sessao, "terceira");
				}else{
					verificaNumeroParcelasSemRD(listaDeParcelas, form, sessao, "terceira");
				}
				
				calcularListaParcelasDiretoTela(form, sessao);
			}else{
				sessao.removeAttribute("listaDeParcelas");
				sessao.removeAttribute("listaValoresDistintos");
			}
			
			String numeroParcelasPopUp = httpServletRequest.getParameter("numeroParcelasPopUp");
			if(numeroParcelasPopUp != null && !numeroParcelasPopUp.equals("") ){
				sessao.setAttribute("numeroParcelasPopUp", form.getNumeroParcelasPopUp());
				sessao.setAttribute("etapa", "informarParcela");
			}
			
			String indicadorTela = httpServletRequest.getParameter("indicadorTela");
			if(indicadorTela != null && indicadorTela.equals("popup")){
				sessao.setAttribute("etapa", "informarParcela");
				
				sessao.removeAttribute("listaDeParcelasPopUp");
				sessao.removeAttribute("listaValoresDistintosPopUp");
				sessao.setAttribute("valorParcelTotal", new BigDecimal(0));
				
			}else{
				sessao.setAttribute("etapa", "terceira");
			}
			
			String escolheuRD = httpServletRequest.getParameter("escolheuRD");
			if(escolheuRD != null && !escolheuRD.equals("") && escolheuRD.equals("true")){
				contratoCadastrar.setValorTotalParcelado(null);
				contratoCadastrar.setValorParcelamentoACobrar(null);
			}
	        
			
		}else if(method != null && method.equals("inserirParcela")){
			
			carregarCamposDoFormulario(form, sessao);
			
			String parcelaInicial = httpServletRequest.getParameter("parcelaInicial");
			String parcelaFinal = httpServletRequest.getParameter("parcelaFinal");
			String valorParcela = httpServletRequest.getParameter("valorParcela");
			String numeroParcelasPopUp = httpServletRequest.getParameter("numeroParcelasPopUp");
			
			List<PrestacaoContratoParcelamento> listaDeParcelasPopUp = (List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelasPopUp");
			if(listaDeParcelasPopUp  == null){
				listaDeParcelasPopUp = new ArrayList<PrestacaoContratoParcelamento>();
			}
			
			//[FS0024]
			verificaNumeroParcelas(numeroParcelasPopUp, parcelaInicial, parcelaFinal, listaDeParcelasPopUp, sessao);
			
			List<Float> listaValoresDistintosPopUp = (List<Float>) sessao.getAttribute("listaValoresDistintosPopUp");
			
			if(listaValoresDistintosPopUp  == null){
				listaValoresDistintosPopUp = new ArrayList<Float>();
			}
			
			if(!listaDeParcelasPopUp.isEmpty()){
				
				//verifica se a(s) parcelas a serem adicionas estao na sequencia correta, comparando com a ultima parcela da lista
				if(listaDeParcelasPopUp.get(listaDeParcelasPopUp.size()-1).getNumero() +1 == Integer.parseInt(parcelaInicial)){
					
					int numeroParcelInicial = Integer.parseInt(parcelaInicial);
					int numeroParcelFinal = Integer.parseInt(parcelaFinal);
					
					valorParcela = valorParcela.replace(".", "");
					valorParcela = valorParcela.replace(",", ".");
					for(;numeroParcelInicial <= numeroParcelFinal; numeroParcelInicial++){
						PrestacaoContratoParcelamento prestacao = new PrestacaoContratoParcelamento();
						prestacao.setNumero(numeroParcelInicial);
						BigDecimal valorParcelaBigDec = new BigDecimal("0");
						try{
							valorParcelaBigDec = new BigDecimal(valorParcela);
						}catch (Exception e) {
							ActionServletException ex = new ActionServletException(
									"atencao.campo.numero.parcelas.invalido", null, "");	
							ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
							sessao.setAttribute("etapa", "terceira");
							throw ex;
						}
						prestacao.setValor(valorParcelaBigDec);
						listaDeParcelasPopUp.add(prestacao);
					}
					
					if(listaValoresDistintosPopUp.isEmpty() || new BigDecimal(valorParcela).floatValue() != listaValoresDistintosPopUp.get(listaValoresDistintosPopUp.size()-1)){
						listaValoresDistintosPopUp.add(new BigDecimal(valorParcela).floatValue());
					}
					
				}else{
					ActionServletException ex = new ActionServletException("atencao.lista.parcelas.descontinuas",null, "");
					ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
					sessao.setAttribute("etapa", "informarParcela");
					throw ex;
				}
			}else{
				int numeroParcelInicial = Integer.parseInt(parcelaInicial);
				int numeroParcelFinal = Integer.parseInt(parcelaFinal);
				
				if(numeroParcelInicial != 1){
					ActionServletException ex = new ActionServletException("atencao.lista.parcelas.descontinuas",null, "");
					ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
					sessao.setAttribute("etapa", "informarParcela");
					throw ex;
				}
				
				valorParcela = valorParcela.replace(".", "");
				valorParcela = valorParcela.replace(",", ".");
				for(;numeroParcelInicial <= numeroParcelFinal; numeroParcelInicial++){
					PrestacaoContratoParcelamento prestacao = new PrestacaoContratoParcelamento();
					prestacao.setNumero(numeroParcelInicial);
					BigDecimal valorParcelaBigDec = new BigDecimal("0");
					try{
						valorParcelaBigDec = new BigDecimal(valorParcela);
					}catch (Exception e) {
						ActionServletException ex = new ActionServletException(
								"atencao.campo.numero.parcelas.invalido", null, "");	
						ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
						sessao.setAttribute("etapa", "terceira");
						throw ex;
					}
					prestacao.setValor(valorParcelaBigDec);
					listaDeParcelasPopUp.add(prestacao);
				}
				
				listaValoresDistintosPopUp.add(new BigDecimal(valorParcela).floatValue());
			}
			
			Collections.sort(listaDeParcelasPopUp, new ComparatorPrestacaoContratoParcelamento());
			
			BigDecimal valorParcelTotal = new BigDecimal(0);
			for (PrestacaoContratoParcelamento prestacaoContratoParcelamento : listaDeParcelasPopUp) {
				valorParcelTotal = valorParcelTotal.add(prestacaoContratoParcelamento.getValor());
			}
			
			sessao.setAttribute("listaDeParcelasPopUp", listaDeParcelasPopUp);
			sessao.setAttribute("numeroParcelasPopUp", numeroParcelasPopUp);
			sessao.setAttribute("listaValoresDistintosPopUp", listaValoresDistintosPopUp);
			sessao.setAttribute("etapa", "informarParcela");
			sessao.setAttribute("valorParcelTotal", valorParcelTotal);
			
		}else if(method != null && method.equals("cancelarParcela")){
			
			carregarCamposDoFormulario(form, sessao);
			
			sessao.removeAttribute("listaDeParcelasPopUp");
			sessao.removeAttribute("listaValoresDistintosPopUp");
			sessao.removeAttribute("numeroParcelasPopUp");
			sessao.setAttribute("valorParcelTotal", new BigDecimal(0));
			sessao.setAttribute("etapa", "terceira");
			
		}else if(method != null && method.equals("removerParcela")){
			
			carregarCamposDoFormulario(form, sessao);
			
			String parcelaInicial = httpServletRequest.getParameter("parcelaInicial");
			String parcelaFinal = httpServletRequest.getParameter("parcelaFinal");
			String grupoValoresRemover = httpServletRequest.getParameter("grupoValoresRemover");
			
			List<PrestacaoContratoParcelamento> listaDeParcelasPopUp = (List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelasPopUp");
			int numInicial = Integer.parseInt(parcelaInicial);
			int numFinal = Integer.parseInt(parcelaFinal);
			
			for(int i = 0; i < listaDeParcelasPopUp.size(); i++){
				if(listaDeParcelasPopUp.get(i).getNumero().intValue() >= numInicial 
					&& listaDeParcelasPopUp.get(i).getNumero().intValue() <= numFinal){
					listaDeParcelasPopUp.remove(i);
					i--;
				}
			}

			List<Float> listaValoresDistintosPopUp = (List<Float>) sessao.getAttribute("listaValoresDistintosPopUp");
			listaValoresDistintosPopUp.remove(Integer.parseInt(grupoValoresRemover));
			
			Collections.sort(listaDeParcelasPopUp, new ComparatorPrestacaoContratoParcelamento());
			
			BigDecimal valorParcelTotal = new BigDecimal(0);
			for (PrestacaoContratoParcelamento prestacaoContratoParcelamento : listaDeParcelasPopUp) {
				valorParcelTotal = valorParcelTotal.add(prestacaoContratoParcelamento.getValor());
			}
			
			sessao.setAttribute("listaDeParcelasPopUp", listaDeParcelasPopUp);
			sessao.setAttribute("listaValoresDistintosPopUp", listaValoresDistintosPopUp);
			sessao.setAttribute("etapa", "informarParcela");
			sessao.setAttribute("valorParcelTotal", valorParcelTotal);
			
		}else if(method != null && method.equals("informarParcela")){
			
			carregarCamposDoFormulario(form, sessao);
			
			List<PrestacaoContratoParcelamento> listaDeParcelasPopUp = (List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelasPopUp");
			List<Float> listaValoresDistintosPopUp = (List<Float>) sessao.getAttribute("listaValoresDistintosPopUp");
			
			BigDecimal valorParcelado = null;
			valorParcelado = (BigDecimal) sessao.getAttribute("valorContaSelecaoTotal");
			
			//[FS0025]
			verificaValorParceladoTotal(listaDeParcelasPopUp, valorParcelado);
			
			//[FS0026]
			verificaContinuidadeParcelas(listaDeParcelasPopUp);
			
			//[FS0029]
			if(contratoCadastrar.getResolucaoDiretoria() != null){
				verificaNumeroParcelasComRD(listaDeParcelasPopUp, contratoCadastrar.getResolucaoDiretoria(), form, sessao, "informarParcela");
			}else{
				verificaNumeroParcelasSemRD(listaDeParcelasPopUp, form, sessao, "informarParcela");
			}
			
			contratoCadastrar.setValorTotalParcelado(valorParcelado);
			contratoCadastrar.setValorParcelamentoACobrar(valorParcelado);

			sessao.setAttribute("listaDeParcelas", listaDeParcelasPopUp);
			sessao.setAttribute("listaValoresDistintos", listaValoresDistintosPopUp);
			sessao.setAttribute("etapa", "informouParcelas");
		}
        
        if(form.getSelecionouContas() == null || !form.getSelecionouContas().trim().equalsIgnoreCase("sim")) {
	    	form.setContasSelecao(null);
	    	sessao.removeAttribute("listaDebitos");
	    }
	    
	    if(form.getSelecionouDebitosACobrar() == null || !form.getSelecionouDebitosACobrar().trim().equalsIgnoreCase("sim")) {
	    	form.setDebitosACobrar(null);
	    	sessao.removeAttribute("listaDebitosACobrar");
	    }
		
		if (contratoCadastrar != null 
				&& (contratoCadastrar.getResolucaoDiretoria() != null 
						&& sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper") != null
						&& !sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper").equals(""))) {
			sessao.setAttribute("numeroParcelasDesabilitada", true);
		} else {
			sessao.removeAttribute("numeroParcelasDesabilitada");
		}
	    
	    if (contratoCadastrar.getIndicadorParcelamentoJuros() == null 
	    		|| contratoCadastrar.getIndicadorParcelamentoJuros().compareTo(ConstantesSistema.NAO) == 0
    		|| (contratoCadastrar.getResolucaoDiretoria() != null && contratoCadastrar.getResolucaoDiretoria().getId() != null
    				&& sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper") != null && !sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper").toString().trim().equals(""))) { 
	    	
	    	sessao.setAttribute("taxaJurosDesabilitada", true);
	    	
	    } else {
	    	
	    	sessao.removeAttribute("taxaJurosDesabilitada");
	    	
	    }
	    
	    return retorno;
	}

	/**Inicio dos metodos privados**/
	private void carregarCamposDoFormulario(InserirContratoParcelamentoPorClienteActionForm form, HttpSession sessao){
		ContratoParcelamento contratoCadastrar = (ContratoParcelamento) sessao.getAttribute("contratoCadastrar");

	    Fachada fachada = Fachada.getInstancia();

        if(form.getSelecionouContas() == null || !form.getSelecionouContas().trim().equalsIgnoreCase("sim")) {
	    	form.setContasSelecao(null);
	    	sessao.removeAttribute("listaDebitos");
	    }
	    
	    if(form.getSelecionouDebitosACobrar() == null || !form.getSelecionouDebitosACobrar().trim().equalsIgnoreCase("sim")) {
	    	form.setDebitosACobrar(null);
	    	sessao.removeAttribute("listaDebitosACobrar");
	    }
	    
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
			
			if(form.getRelacaoAnterior() != null && !form.getRelacaoAnterior().equals("")){
				TipoRelacao relacao = new TipoRelacao();
				relacao.setId(Integer.parseInt(form.getRelacaoAnterior()));
				contratoCadastrar.setRelacaoAnterior(relacao);
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

		//[SB0002] ? Informar Débitos do Cliente
		// 3. Usuário seleciona os itens que irão compor o débito a ser negociado
		// 3.1. O sistema cria uma lista de débitos com os itens selecionados pelo usuário 
		//  para composição do débito a ser negociado com os seguintes dados:

		BigDecimal valorContaSelecaoTotal = BigDecimal.ZERO;
		BigDecimal valorContaAcrescimoSelecaoTotal = BigDecimal.ZERO;
		
		if(form.getContasSelecao() != null && form.getContasSelecao().length > 0){
			
			Collection<ContaValoresHelper> colecaoContaValores = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
			BigDecimal valorConta = new BigDecimal(0);
			BigDecimal valorAcresc = new BigDecimal(0);
			
			List<DebitosClienteHelper> listaDebitos = new ArrayList<DebitosClienteHelper>();
			String contasPagasAMenor = "";
				
			if(colecaoContaValores != null){
				for (ContaValoresHelper contaValoresHelper : colecaoContaValores) {
					if(form.verificaContaSelecionada(contaValoresHelper.getConta().getId().intValue())){

						if (contaValoresHelper.getValorPago() != null && contaValoresHelper.getValorPago().compareTo(BigDecimal.ZERO) > 0) {
								
							contasPagasAMenor += contaValoresHelper.getConta().getImovel().getId()
										+ " - " + contaValoresHelper.getConta().getReferenciaFormatada() + ", ";
							
						}
						
						DebitosClienteHelper debito = new DebitosClienteHelper();
						
						//3.1.1. Tipo do Documento
						debito.setTipoDocumento(DocumentoTipo.CONTA);
						//3.1.2. Identificação do Item
						debito.setIdentificadorItem(contaValoresHelper.getConta().getId());
						//3.1.3. Valor do Item
						debito.setValorItem(contaValoresHelper.getConta().getValorTotal());
						//3.1.4. Valor Atualização Monetária do Item
						debito.setValorAcrescImpont(contaValoresHelper.getValorTotalContaValores());
						
						listaDebitos.add(debito);
						
						valorConta = valorConta.add(contaValoresHelper.getConta().getValorTotal());
						valorAcresc = valorAcresc.add(contaValoresHelper.getValorTotalContaValores());
					}
				}

				if (contasPagasAMenor != null && !contasPagasAMenor.trim().equals("")) {

					contasPagasAMenor = contasPagasAMenor.substring(0, contasPagasAMenor.length() - 2);
					
					throw new ActionServletException(
							"atencao.contas.pagas.a.menor", contasPagasAMenor);
					
				}
				
				valorContaSelecaoTotal = valorContaSelecaoTotal.add(valorConta);
				valorContaAcrescimoSelecaoTotal = valorContaAcrescimoSelecaoTotal.add(valorAcresc.add(valorConta));
				
				sessao.setAttribute("listaDebitos",listaDebitos);
				sessao.setAttribute("valorContaSelecao",valorConta);
				sessao.setAttribute("valorAcrescimoSelecao",valorAcresc);
				sessao.setAttribute("valorContaAcrescimoSelecao",valorAcresc.add(valorConta));
			}
		}else{
			sessao.removeAttribute("listaDebitos");
			sessao.setAttribute("valorContaSelecao",BigDecimal.ZERO);
			sessao.setAttribute("valorAcrescimoSelecao",BigDecimal.ZERO);
			sessao.setAttribute("valorContaAcrescimoSelecao",BigDecimal.ZERO);
		}
		
		if(form.getDebitosACobrar() != null && form.getDebitosACobrar().length > 0){
			
			Collection<DebitoACobrar> colecaoDebitoACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
			BigDecimal valorDebitosACobrarSelecao = new BigDecimal(0);
			
			List<DebitosClienteHelper> listaDebitosACobrar = new ArrayList<DebitosClienteHelper>();
			
			if(colecaoDebitoACobrar != null){
				
				String debitosPagosAMenor = "";
				
				for (DebitoACobrar debitoACobrar : colecaoDebitoACobrar) {
					if(form.verificaDebitoACobrarSelecionado(debitoACobrar.getId().intValue())){
						Object[] dadosDebitoPagoAMenor = fachada.obterDadosDebitoACobrarPagoAMenor(
								debitoACobrar.getId());
						if (dadosDebitoPagoAMenor != null) {
							
							debitosPagosAMenor += dadosDebitoPagoAMenor[0]
										+ " - " + dadosDebitoPagoAMenor[1] + ", ";
							
						}
						
						DebitosClienteHelper debito = new DebitosClienteHelper();
						
						//3.1.1. Tipo do Documento
						debito.setTipoDocumento(DocumentoTipo.DEBITO_A_COBRAR);
						//3.1.2. Identificação do Item
						debito.setIdentificadorItem(debitoACobrar.getId());
						//3.1.3. Valor do Item
						debito.setValorItem(debitoACobrar.getValorTotal());
						//3.1.4. Valor Atualização Monetária do Item
						debito.setValorAcrescImpont(null);
						
						listaDebitosACobrar.add(debito);
						
						valorDebitosACobrarSelecao = valorDebitosACobrarSelecao.add(debitoACobrar.getValorTotal());
					}
				}
				valorContaSelecaoTotal = valorContaSelecaoTotal.add(valorDebitosACobrarSelecao);
				valorContaAcrescimoSelecaoTotal = valorContaAcrescimoSelecaoTotal.add(valorDebitosACobrarSelecao);
				
				sessao.setAttribute("listaDebitosACobrar",listaDebitosACobrar);
				sessao.setAttribute("valorDebitosACobrarSelecao",valorDebitosACobrarSelecao);
				
				if (debitosPagosAMenor != null && !debitosPagosAMenor.trim().equals("")) {

					debitosPagosAMenor = debitosPagosAMenor.substring(0, debitosPagosAMenor.length() - 2);
					
					throw new ActionServletException(
							"atencao.debito_a_cobrar.pagos.a.menor", debitosPagosAMenor);
					
				}
				
			}
			
		} else {
			sessao.removeAttribute("listaDebitosACobrar");
			sessao.setAttribute("valorDebitosACobrarSelecao",BigDecimal.ZERO);
		}
		
		sessao.setAttribute("valorContaSelecaoTotal",valorContaSelecaoTotal);
		sessao.setAttribute("valorContaAcrescimoSelecaoTotal",valorContaAcrescimoSelecaoTotal);
		
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

				BigDecimal valorContaSelecao = (BigDecimal) sessao.getAttribute("valorContaSelecaoTotal");
				BigDecimal valorContaComAcrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecaoTotal");
				
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
			
			sessao.removeAttribute("listaDeParcelas");
			sessao.removeAttribute("listaValoresDistintos");
			
			
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
			contratoCadastrar.setNumeroDiasEntreVencimentoParcel(null);
		}
		
		List<QuantidadePrestacoesRDHelper> colecaoQuantidadePrestacoesRDHelper = (List<QuantidadePrestacoesRDHelper>) sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper");
		if(form.getPacerlaAdd() != null && !form.getPacerlaAdd().equals("")
				&& (colecaoQuantidadePrestacoesRDHelper == null || colecaoQuantidadePrestacoesRDHelper.size() == 0)){
			contratoCadastrar.setNumeroPrestacoes(Integer.parseInt(form.getPacerlaAdd()));
		}else{
			contratoCadastrar.setNumeroPrestacoes(null);
		}
		
		if(form.getTaxaJurosAdd() != null && !form.getTaxaJurosAdd().equals("")
				&& (colecaoQuantidadePrestacoesRDHelper == null || colecaoQuantidadePrestacoesRDHelper.size() == 0)){
			String juros = form.getTaxaJurosAdd().replace(".", ",");
			juros = juros.replace(",", ".");
			try{
				BigDecimal jurosBigDec = new BigDecimal(juros);
				contratoCadastrar.setTaxaJuros(jurosBigDec);
			}catch (Exception e) {
				contratoCadastrar.setTaxaJuros(null);
			}
		}else{
			contratoCadastrar.setTaxaJuros(null);
		}
		
		sessao.setAttribute("contratoCadastrar", contratoCadastrar);
	}
	
	private void calcularDebitosCliente(HttpSession sessao) throws ActionServletException{

	    Fachada fachada = Fachada.getInstancia();
	    
		ArrayList<ObterDebitoImovelOuClienteHelper> colecaoClientesDebitosImoveis = new ArrayList<ObterDebitoImovelOuClienteHelper>();
		
		ContratoParcelamentoCliente clienteContrato = (ContratoParcelamentoCliente) sessao.getAttribute("clienteContrato");
		ContratoParcelamentoCliente clienteSuperiorContrato = (ContratoParcelamentoCliente) sessao.getAttribute("clienteSuperiorContrato");
		ContratoParcelamento contratoCadastrar = (ContratoParcelamento) sessao.getAttribute("contratoCadastrar");
		
		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;
		try {
			dataVencimentoDebitoI = formatoData.parse(contratoCadastrar.getDataVencimentoInicioFormatada());
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(contratoCadastrar.getDataVencimentoFinalFormatada());
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		
		String anoMesInicial = contratoCadastrar.getAnoMesDebitoInicio()+"";
		String anoMesFinal = contratoCadastrar.getAnoMesDebitoFinal()+"";
		
		if(contratoCadastrar.getAnoMesDebitoInicio() == null || contratoCadastrar.getAnoMesDebitoFinal() == null){
			String mesInicial = referenciaInicial.substring(0, 2);
			String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
			// Para auxiliar na formatação de uma data
			String mesFinal = referenciaFinal.substring(0, 2);
			String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
			
			anoMesInicial = anoInicial + mesInicial;
			anoMesFinal = anoFinal + mesFinal;
		}
		
		if(dataVencimentoDebitoF == null || dataVencimentoDebitoI == null){
			try {
				dataVencimentoDebitoI = formatoData.parse("01/01/0001");
			} catch (ParseException ex) {
				dataVencimentoDebitoI = null;
			}
			try {
				dataVencimentoDebitoF = formatoData.parse("31/12/9999");
			} catch (ParseException ex) {
				dataVencimentoDebitoF = null;
			}
		}
		
		// seta valores constantes para chamar o metodo que consulta debitos do imovel
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorContaRevisao = new Integer(1);
		Integer indicadorDebito = new Integer(1);
		Integer indicadorCredito = new Integer(2);//Modificado
		Integer indicadorNotas = new Integer(2);//Modificado
		Integer indicadorGuias = new Integer(2);//Modificado
		Integer indicadorAtualizar = new Integer(1);
		Short relacaoTipo = null;
		Integer tipoImovel = null;
		
		if(clienteContrato != null){
			

			if(contratoCadastrar.getRelacaoCliente() != null ){
				if(contratoCadastrar.getRelacaoCliente().getId().intValue() == ClienteRelacaoTipo.PROPRIETARIO){
					relacaoTipo = ClienteRelacaoTipo.PROPRIETARIO;
				}else if(contratoCadastrar.getRelacaoCliente().getId().intValue() == ClienteRelacaoTipo.RESPONSAVEL){
					relacaoTipo = ClienteRelacaoTipo.RESPONSAVEL;
				}else if(contratoCadastrar.getRelacaoCliente().getId().intValue() == ClienteRelacaoTipo.USUARIO){
					relacaoTipo = ClienteRelacaoTipo.USUARIO;
				}
			}
			
			if(relacaoTipo == null || relacaoTipo.equals(ClienteRelacaoTipo.RESPONSAVEL)){
				if(contratoCadastrar.getIndicadorResponsavel() != null && contratoCadastrar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_TODOS){
					tipoImovel = new Integer(4);	
				}else if(contratoCadastrar.getIndicadorResponsavel() != null && contratoCadastrar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_ATUAL_DO_IMOVEL){
					tipoImovel = new Integer(3);	
				}else{
					tipoImovel = new Integer(2);	
				}
			}else{
				tipoImovel = new Integer(2);	
			}
			
			// Obtendo os débitos do imovel
			if(contratoCadastrar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_INDICADOR_NA_CONTA
					|| contratoCadastrar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_ATUAL_DO_IMOVEL
					|| contratoCadastrar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_TODOS){
				ObterDebitoImovelOuClienteHelper colecao = fachada.obterDebitoImovelOuCliente(
						tipoImovel.intValue(), null, "" + clienteContrato.getCliente().getId(), relacaoTipo,
						anoMesInicial, anoMesFinal, dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento.intValue(),
						indicadorContaRevisao.intValue(), indicadorDebito.intValue(),
						indicadorCredito.intValue(), indicadorNotas.intValue(),
						indicadorGuias.intValue(), indicadorAtualizar.intValue(),
						null);

				colecaoClientesDebitosImoveis.add(colecao);
				
			}
		}else{
			
			if(contratoCadastrar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_ATUAL_DO_IMOVEL){
				tipoImovel = new Integer(3);//Modificado
				
				ObterDebitoImovelOuClienteHelper colecao = fachada.obterDebitoImovelOuCliente(
						tipoImovel.intValue(), null, ""+clienteSuperiorContrato.getCliente().getId(), new Short("99"),
						anoMesInicial, anoMesFinal, dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento.intValue(),
						indicadorContaRevisao.intValue(), indicadorDebito.intValue(),
						indicadorCredito.intValue(), indicadorNotas.intValue(),
						indicadorGuias.intValue(), indicadorAtualizar.intValue(),
						null);	
				
					colecaoClientesDebitosImoveis.add(colecao);
				
			}
		}
		
		/////////////////////////////////////////CARREGA VALORES DEFAULT PARA TODAS VARIAVEIS ////////////////////////////////////////////
		//Criando uma lista para adicao de todas colecoesContaValores para todos Clientes (Caso selecione Cliente Superior)
		Collection<ContaValoresHelper> colecaoContaValoresTotal = new ArrayList<ContaValoresHelper>();
		//Criando uma lista para adicao de todas colecaoDebitoACobrar para todos Clientes (Caso selecione Cliente Superior)
		Collection<DebitoACobrar> colecaoDebitoACobrarTotal = new ArrayList<DebitoACobrar>();
		//Criando uma lista para adicao de todas colecaoCreditoARealizar para todos Clientes (Caso selecione Cliente Superior)
		Collection<CreditoARealizar> colecaoCreditoARealizarTotal = new ArrayList<CreditoARealizar>();
		//Criando uma lista para adicao de todas colecaoGuiaPagamentoValores para todos Clientes (Caso selecione Cliente Superior)
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresTotal = new ArrayList<GuiaPagamentoValoresHelper>();
		//pesquisa todas as situações de cobrança ativa do imovel
		Collection colecaoCobrancaSituacao = new ArrayList<CobrancaSituacao>();
		
		ContaValoresHelper dadosConta = null;
		BigDecimal valorConta = new BigDecimal("0.00");
		BigDecimal valorAcrescimo = new BigDecimal("0.00");
		BigDecimal valorAgua = new BigDecimal("0.00");
		BigDecimal valorEsgoto = new BigDecimal("0.00");
		BigDecimal valorDebito = new BigDecimal("0.00");
		BigDecimal valorCredito = new BigDecimal("0.00");
		BigDecimal valorImposto = new BigDecimal("0.00");
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");
		BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
		BigDecimal valorDebitoACobrarSemJurosParcelamento = new BigDecimal("0.00");
		DebitoACobrar dadosDebito = null;
		BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
		BigDecimal valorTotalDebito = new BigDecimal("0.00");
		int indiceCurtoPrazo = 0;
		int indiceLongoPrazo = 1;
		BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
		BigDecimal valorCreditoARealizarSemDescontosParcelamento = new BigDecimal("0.00");
		CreditoARealizar dadosCredito = null;
		BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
		GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;
		/////////////////////////////////////////FIM - CARREGA VALORES DEFAULT PARA TODAS VARIAVEIS ////////////////////////////////////////////
		
		for (ObterDebitoImovelOuClienteHelper colecaoDebitoImovel : colecaoClientesDebitosImoveis) {
		
			//////////////////////ITERACAO NA COLECAO DE CONTAVALORES////////////////////////////////////
			Collection<ContaValoresHelper> colecaoContaValores = new ArrayList<ContaValoresHelper>();
			if(colecaoDebitoImovel.getColecaoContasValores() != null){
				colecaoContaValoresTotal.addAll(colecaoDebitoImovel.getColecaoContasValores());
				colecaoContaValores.addAll(colecaoDebitoImovel.getColecaoContasValores());
			}
			
			if (!colecaoContaValores.isEmpty()) {
				java.util.Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores.iterator();
				// percorre a colecao de conta somando o valor para obter um valor total
				while (colecaoContaValoresIterator.hasNext()) {
					
					dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();
					valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
					valorAcrescimo = valorAcrescimo.add(dadosConta.getValorTotalContaValores());
					valorAgua = valorAgua.add(dadosConta.getConta().getValorAgua());
					valorEsgoto = valorEsgoto.add(dadosConta.getConta().getValorEsgoto());
					valorDebito = valorDebito.add(dadosConta.getConta().getDebitos());
					valorCredito = valorCredito.add(dadosConta.getConta().getValorCreditos());
					valorImposto = valorImposto.add(dadosConta.getConta().getValorImposto());
					
					if (dadosConta.getValorAtualizacaoMonetaria() != null && !dadosConta.getValorAtualizacaoMonetaria().equals("")) {
						valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosConta.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
					if (dadosConta.getValorJurosMora() != null	&& !dadosConta.getValorJurosMora().equals("")) {
						valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorJurosMora = valorJurosMora.add(dadosConta.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
					if (dadosConta.getValorMulta() != null && !dadosConta.getValorMulta().equals("")) {
						valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorMulta = valorMulta.add(dadosConta.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
				}
				
			}
			valorTotalDebito = valorTotalDebito.add(valorConta);
			//////////////////////FIM ITERACAO NA COLECAO DE CONTAVALORES////////////////////////////////////	
			
			
			//////////////////////ITERACAO NA COLECAO DE DEBITOACOBRAR////////////////////////////////////	
			Collection<DebitoACobrar> colecaoDebitoACobrar = new ArrayList<DebitoACobrar>();
			if(colecaoDebitoImovel.getColecaoDebitoACobrar() != null){
				colecaoDebitoACobrar.addAll(colecaoDebitoImovel.getColecaoDebitoACobrar());
				colecaoDebitoACobrarTotal.addAll(colecaoDebitoImovel.getColecaoDebitoACobrar());
			}
			
			if (!colecaoDebitoACobrar.isEmpty()) {
				java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
				// percorre a colecao de debito a cobrar somando o valor para obter um valor total
				while (colecaoDebitoACobrarIterator.hasNext()) {
					
					dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
					valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
					
					if (dadosDebito.getDebitoTipo() != null &&
							!dadosDebito.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
						valorDebitoACobrarSemJurosParcelamento = valorDebitoACobrarSemJurosParcelamento.add(dadosDebito.getValorTotalComBonus());
					}
					
					//Debitos A Cobrar - Parcelamento
					if (dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
							|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
							|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
						
						// [SB0001] Obter Valores de Curto e Longo Prazo
						valorRestanteACobrar = dadosDebito.getValorTotalComBonus();
						
						BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
								dadosDebito.getNumeroPrestacaoDebito(),	
								dadosDebito.getNumeroPrestacaoCobradasMaisBonus(),
								valorRestanteACobrar);
						valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
						valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
					}
					
				}
			}
			valorTotalDebito = valorTotalDebito.add(valorDebitoACobrar);
			//////////////////////FIM ITERACAO NA COLECAO DE DEBITOACOBRAR////////////////////////////////////	
			
			
			//////////////////////ITERACAO NA COLECAO DE CREDITOAREALIZAR////////////////////////////////////	
			Collection<CreditoARealizar> colecaoCreditoARealizar = new ArrayList<CreditoARealizar>();
			if(colecaoDebitoImovel.getColecaoCreditoARealizar() != null){
				colecaoCreditoARealizar.addAll(colecaoDebitoImovel.getColecaoCreditoARealizar());
				colecaoCreditoARealizarTotal.addAll(colecaoDebitoImovel.getColecaoCreditoARealizar());
			}
			
			if (!colecaoCreditoARealizar.isEmpty()) {
				java.util.Iterator<CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar.iterator();
				// percorre a colecao de credito a realizar somando o valor para obter um valor total
				while (colecaoCreditoARealizarIterator.hasNext()) {
					
					dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator.next();
					valorCreditoARealizar = valorCreditoARealizar.add(dadosCredito.getValorTotalComBonus());
					
					if (dadosCredito.getCreditoOrigem() != null && 
							!dadosCredito.getCreditoOrigem().getId().equals(CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO)){
						valorCreditoARealizarSemDescontosParcelamento = valorCreditoARealizarSemDescontosParcelamento.add(dadosCredito.getValorTotalComBonus());
					}
				}
			}
			//////////////////////FIM ITERACAO NA COLECAO DE CREDITOAREALIZAR////////////////////////////////////	
			
			//////////////////////ITERACAO NA COLECAO DE GUIASPAGAMENTOVALORES////////////////////////////////////	
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = new ArrayList<GuiaPagamentoValoresHelper>();
			if(colecaoDebitoImovel.getColecaoGuiasPagamentoValores() != null){
				colecaoGuiaPagamentoValores.addAll(colecaoDebitoImovel.getColecaoGuiasPagamentoValores());
				colecaoGuiaPagamentoValoresTotal.addAll(colecaoDebitoImovel.getColecaoGuiasPagamentoValores());
			}
			
			if (!colecaoGuiaPagamentoValores.isEmpty()) {
				java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores.iterator();
				// percorre a colecao de guia de pagamento somando o valor para obter um valor total
				while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {
					
					dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
					valorGuiaPagamento = valorGuiaPagamento.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
					
					if (dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
						valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
					if (dadosGuiaPagamentoValoresHelper.getValorJurosMora() != null && !dadosGuiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
						valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorJurosMora = valorJurosMora.add(dadosGuiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
					if (dadosGuiaPagamentoValoresHelper.getValorMulta() != null	&& !dadosGuiaPagamentoValoresHelper.getValorMulta().equals("")) {
						valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorMulta = valorMulta.add(dadosGuiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
				}
			}
			//////////////////////FIM ITERACAO NA COLECAO DE GUIASPAGAMENTOVALORES////////////////////////////////////	
			
			if(dadosConta != null){
				Collection<CobrancaSituacao> situacoesCobranca = fachada.pesquisarImovelCobrancaSituacaoPorImovel(dadosConta.getConta().getImovel().getId());
				if(situacoesCobranca != null && !situacoesCobranca.isEmpty()){
					colecaoCobrancaSituacao.addAll(situacoesCobranca);
				}
			}

		}
		
		
		if (colecaoContaValoresTotal.isEmpty() && colecaoDebitoACobrarTotal.isEmpty()
				&& colecaoCreditoARealizarTotal.isEmpty() && colecaoGuiaPagamentoValoresTotal.isEmpty()) {
			if (colecaoContaValoresTotal.isEmpty()){
				sessao.removeAttribute("colecaoContaValores");
				sessao.removeAttribute("valorConta");
				sessao.removeAttribute("valorAcrescimo");
				sessao.removeAttribute("valorAgua");
				sessao.removeAttribute("valorEsgoto");
				sessao.removeAttribute("valorDebito");
				sessao.removeAttribute("valorCredito");
				sessao.removeAttribute("valorContaAcrescimo");
				sessao.removeAttribute("valorImposto");
				sessao.removeAttribute("valorTotalSemAcrescimo");
				sessao.removeAttribute("valorTotalComAcrescimo");
				sessao.removeAttribute("valorToralSemAcrescimoESemJurosParcelamento");							
			} 
			if (colecaoDebitoACobrarTotal.isEmpty()){
				sessao.removeAttribute("colecaoDebitoACobrar");
				sessao.removeAttribute("valorDebitoACobrar");							
			}
			if (colecaoCreditoARealizarTotal.isEmpty()){
				sessao.removeAttribute("colecaoCreditoARealizar");
				sessao.removeAttribute("valorCreditoARealizar");
				sessao.removeAttribute("valorCreditoARealizarSemDescontosParcelamento");							
			}
			if (colecaoGuiaPagamentoValoresTotal.isEmpty()){
				sessao.removeAttribute("colecaoGuiaPagamentoValores");
				sessao.removeAttribute("valorGuiaPagamento");							
			}
			
			if(colecaoCobrancaSituacao.isEmpty()){
				sessao.removeAttribute("colecaoCobrancaSituacao");
			}
			
			sessao.removeAttribute("valorTotalDebito");
			sessao.removeAttribute("valorTotalDebitoAtualizado");		
			
			ActionServletException ex = new ActionServletException(
					"atencao.cliente.sem.debitos", null, "");
			ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
			sessao.setAttribute("etapa", "primeira");
			throw ex;
//			if(colecaoDadosNegativacaoRetorno == null){
//				sessao.removeAttribute("colecaoDadosNegativacaoRetorno");
//			}
			
		} else {
			// Manda a colecao pelo request
			sessao.setAttribute("colecaoContaValores",colecaoContaValoresTotal);

			// Manda a colecao e os valores total de conta pelo request
			sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrarTotal);
			sessao.setAttribute("valorConta", Util.formatarMoedaReal(valorConta));
			sessao.setAttribute("valorAcrescimo", Util.formatarMoedaReal(valorAcrescimo));
			sessao.setAttribute("valorAgua", Util.formatarMoedaReal(valorAgua));
			sessao.setAttribute("valorEsgoto", Util.formatarMoedaReal(valorEsgoto));
			sessao.setAttribute("valorDebito", Util.formatarMoedaReal(valorDebito));
			sessao.setAttribute("valorCredito", Util.formatarMoedaReal(valorCredito));
			sessao.setAttribute("valorContaAcrescimo", Util.formatarMoedaReal(valorConta.add(valorAcrescimo)));
			sessao.setAttribute("valorImposto", Util.formatarMoedaReal(valorImposto));

			// Manda a colecao e o valor total de DebitoACobrar pelo request
			sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrarTotal);
			sessao.setAttribute("valorDebitoACobrar", Util.formatarMoedaReal(valorDebitoACobrar));

			// Manda a colecao e o valor total de CreditoARealizar pelo request
			sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizarTotal);
			sessao.setAttribute("valorCreditoARealizar", Util.formatarMoedaReal(valorCreditoARealizar));
			sessao.setAttribute("valorCreditoARealizarSemDescontosParcelamento",Util.formatarMoedaReal(valorCreditoARealizarSemDescontosParcelamento));

			// Manda a colecao e o valor total de GuiaPagamento pelo request
			sessao.setAttribute("colecaoGuiaPagamentoValores",colecaoGuiaPagamentoValoresTotal);
			sessao.setAttribute("valorGuiaPagamento", Util.formatarMoedaReal(valorGuiaPagamento));

			// 1.10.1. Total do Débito
			sessao.setAttribute("valorTotalDebito", Util.formatarMoedaReal(valorTotalDebito));
			// 1.10.2. Total do Débito Atualizado
			sessao.setAttribute("valorTotalDebitoAtualizado", Util.formatarMoedaReal(valorTotalDebito.add(valorAcrescimo)));
			
			// Soma o valor total dos debitos e subtrai dos creditos
			BigDecimal valorTotalSemAcrescimo = valorConta.add(valorDebitoACobrar);
			valorTotalSemAcrescimo = valorTotalSemAcrescimo.add(valorGuiaPagamento);
			valorTotalSemAcrescimo = valorTotalSemAcrescimo.subtract(valorCreditoARealizar);

			BigDecimal valorTotalComAcrescimo = valorTotalSemAcrescimo.add(valorAcrescimo);
			
			BigDecimal valorToralSemAcrescimoESemJurosParcelamento = 
				valorConta.add(valorDebitoACobrarSemJurosParcelamento);
			
			valorToralSemAcrescimoESemJurosParcelamento = 
				valorToralSemAcrescimoESemJurosParcelamento.add(valorGuiaPagamento);
			
			
			sessao.setAttribute("valorTotalSemAcrescimo", Util
					.formatarMoedaReal(valorTotalSemAcrescimo));
			sessao.setAttribute("valorTotalComAcrescimo", Util
					.formatarMoedaReal(valorTotalComAcrescimo));
			sessao.setAttribute("valorToralSemAcrescimoESemJurosParcelamento", 
					Util.formatarMoedaReal(valorToralSemAcrescimoESemJurosParcelamento));
			
			sessao.setAttribute("colecaoCobrancaSituacao", colecaoCobrancaSituacao);
			
		}
	}
	
	
	
	private void validarCampos(InserirContratoParcelamentoPorClienteActionForm form, HttpSession sessao, String method) throws ActionServletException{
		ActionServletException ex = null;

	    Fachada fachada = Fachada.getInstancia();
	    
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
			filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");
			filtro.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamento.NUMERO, form.getNumeroContratoAnt()));
			Collection<ContratoParcelamento> contratos = fachada.pesquisar(filtro, ContratoParcelamento.class.getName());
			if(contratos == null || contratos.size() == 0){
				ex = new ActionServletException(
						"atencao.numero.contrato.nao.existe",null, "");	
			}else if(contratos != null && contratos.size() == 1){ 
				ContratoParcelamento contrato = contratos.iterator().next();
				if(contrato.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.NORMAL.intValue()){
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
					Integer qtdClientes = fachada.pesquisarQtdClientesAssociadosResponsavelNaoJuridica(clienteContratoSuperior.getCliente().getId().intValue());
					if(ex == null && qtdClientes != 0 && !method.equals("mostrarPrimeiraEtapa") ){
						sessao.setAttribute("etapa", "segunda");
						sessao.setAttribute("mensagemAlerta", 
								"Há clientes vinculados ao cliente "
									+ clienteContratoSuperior.getCliente().getNome() 
									+ " que não são pessoas jurídicas e/ou que não têm o CNPJ informado no sistema.");
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
	
	//[FS0024]
	private void verificaNumeroParcelas(String numeroParcelasPopUp, String parcelaInicial, String parcelaFinal, List<PrestacaoContratoParcelamento> listaDeParcelas, HttpSession sessao){
		
			
		int total = Integer.parseInt(numeroParcelasPopUp);
		int parcelIni = Integer.parseInt(parcelaInicial);
		int parcelFim = Integer.parseInt(parcelaFinal);
		
		int novas = parcelFim - parcelIni + 1;
		
		if(novas + listaDeParcelas.size() > total ){
			sessao.setAttribute("etapa", "informarParcela");
			ActionServletException ex = new ActionServletException("atencao.lista.parcelas.quantidade.nao.corresponde",novas + listaDeParcelas.size()+"", total+"");
			ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
			throw ex;
		}
	}
	
	//[FS0026]
	private void verificaContinuidadeParcelas(List<PrestacaoContratoParcelamento> listaDeParcelas){
		
		ActionServletException ex = new ActionServletException("atencao.lista.parcelas.descontinuas", null, "");
		ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
		
		if(listaDeParcelas.size() > 1){

			for (int i = 1; i < listaDeParcelas.size(); i++) {
				if(listaDeParcelas.get(i).getNumero().intValue() != listaDeParcelas.get(i - 1).getNumero().intValue() + 1){
					throw ex;	
				}
			}
			
		}else{
			if(listaDeParcelas.get(0).getNumero().intValue() != 1){
				throw ex;
			}
		}
	}
	
	//[FS0025]
	private void verificaValorParceladoTotal(List<PrestacaoContratoParcelamento> listaDeParcelas, BigDecimal valorParceladoTotal){
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
						"atencao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;
			}
		}else {
			if((listaDeParcelas != null && listaDeParcelas.size() >  numeroMax) || 
					(form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().equals("") 
							&& Integer.parseInt(form.getNumeroParcelasPopUp()) > numeroMax)){
				ActionServletException ex = new ActionServletException(
						"atencao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;
			}
		}
	}
	
	private void calcularListaParcelasDiretoTela(InserirContratoParcelamentoPorClienteActionForm form, HttpSession sessao) throws ActionServletException{

		List<PrestacaoContratoParcelamento> listaDeParcelas = (List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelas");
		String etapa = (String) sessao.getAttribute("etapa") + "";
		
		if(listaDeParcelas != null && etapa.equals("informouEtapa")){
			ActionServletException ex = new ActionServletException(
					"atencao.lista.parcelas.ja.informadas", null, "");	
			ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
			sessao.setAttribute("etapa", "terceira");
			throw ex;
		}else{
			
			List<Float> listaValoresDistintos = new ArrayList<Float>();
			
			listaDeParcelas = new ArrayList<PrestacaoContratoParcelamento>();
			int numeroParcelInicial = 1;
			int numeroParcelFinal = Integer.parseInt(form.getPacerlaAdd());
			
			if(numeroParcelFinal <= 0){
				ActionServletException ex = new ActionServletException(
						"atencao.numero.parcelas.invalido", null, "");	
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", "terceira");
				throw ex;
			}
			int numeroMax = 0;
			try{
				numeroMax = this.getSistemaParametro().getNumeroMaximoParcelasContratosParcelamento();
			}catch (Exception e) {
				ActionServletException ex = new ActionServletException(
						"atencao.numero.maximo.parcela.sistema.parametros.nao.cadastrado", null, "");	
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", "terceira");
				throw ex;
			}
			if(numeroParcelFinal >  numeroMax){
				ActionServletException ex = new ActionServletException(
						"atencao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", "terceira");
				throw ex;
			}
			String juros = form.getTaxaJurosAdd();
			juros = juros.replace(",", ".");
			BigDecimal jurosBigDec = new BigDecimal("0");
			
			if((form.getResolucaoDiretoria() != null && !form.getResolucaoDiretoria().equals("")
					&& (form.getTaxaJurosAdd() == null || form.getTaxaJurosAdd().equals("")))
					|| (juros == null || juros.trim().equals(""))){
				juros = "0";
			}
			
			try{
				jurosBigDec = new BigDecimal(juros);
			}catch (Exception e) {
				ActionServletException ex = new ActionServletException(
						"atencao.campo.taxa.juros.invalido", null, "");	
				ex.setUrlBotaoVoltar("/gsan/exibirInserirContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", "terceira");
				throw ex;
			}
			String indicadorDebitoAcresc = form.getIndicadorDebitoAcresc();
			String indicadorParcelJuros = form.getIndicadorParcelJuros();
			
			if (indicadorParcelJuros != null 
					&& indicadorParcelJuros.trim().equalsIgnoreCase(ConstantesSistema.SIM.toString())
					&& (jurosBigDec == null || jurosBigDec.compareTo(BigDecimal.ZERO) == 0)) {
				
				throw new ActionServletException(
						"atencao.campo_selecionado.obrigatorio", "Taxa de Juros");
				
			}
			
			BigDecimal valorContaSelecaoTotal = (BigDecimal) sessao.getAttribute("valorContaSelecaoTotal");
			BigDecimal acrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecaoTotal");
			BigDecimal valorParcelado = BigDecimal.ZERO;
			
			InserirContratoParcelamentoValoresParcelasHelper helper = Fachada.getInstancia()
				.calcularValoresParcelasContratoParcelamento(valorContaSelecaoTotal, acrescimo, 
					indicadorDebitoAcresc, indicadorParcelJuros, jurosBigDec, numeroParcelInicial, numeroParcelFinal);
			
			valorParcelado = helper.getValorParcelado();
			listaDeParcelas = helper.getListaDeParcelas();
			listaValoresDistintos = helper.getListaValoresDistintos();
			
			ContratoParcelamento contratoCadastrar = (ContratoParcelamento) sessao.getAttribute("contratoCadastrar");
			contratoCadastrar.setValorTotalParcelado(valorParcelado);
			contratoCadastrar.setValorParcelamentoACobrar(valorParcelado);
			
			sessao.setAttribute("listaValoresDistintos", listaValoresDistintos);
			sessao.setAttribute("listaDeParcelas", listaDeParcelas);
			sessao.setAttribute("etapa","informouParcelas");
			
			sessao.setAttribute("contratoCadastrar", contratoCadastrar);
			
		}
		
		
	}
	
	private void removerValoresParcelas(HttpSession sessao, 
			InserirContratoParcelamentoPorClienteActionForm  form,
			boolean removerApenasListas){

		if (!removerApenasListas) {
			ContratoParcelamento contratoCadastrar = (ContratoParcelamento) sessao.getAttribute("contratoCadastrar");
			contratoCadastrar.setTaxaJuros(null);
			contratoCadastrar.setNumeroPrestacoes(null);
			sessao.setAttribute("contratoCadastrar", contratoCadastrar);
			
			sessao.removeAttribute("numeroParcelasPopUp");
			form.setPacerlaAdd(null);
			form.setTaxaJurosAdd(null);
			form.setNumeroParcelasPopUp(null);
		}
		
		sessao.removeAttribute("listaDeParcelas");
		sessao.removeAttribute("listaValoresDistintos");
		sessao.removeAttribute("listaDeParcelasPopUp");
		sessao.removeAttribute("listaValoresDistintosPopUp");
		sessao.removeAttribute("valorParcelTotal");
		
	}
	
}

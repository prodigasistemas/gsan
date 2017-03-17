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
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroPrestacaoContratoParcelamento;
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
import gcom.faturamento.conta.ContaMotivoRevisao;
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
import java.util.Arrays;
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
 * Action que define o pré-processamento da página de Atualizar Contrato Parcelamento por Cliente
 * 
 * @author Paulo Diniz, Mariana Victor
 * @created 16/03/2011, 27/06/2011
 */
public class ExibirAtualizarContratoParcelamentoPorClienteAction extends GcomAction {

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Este caso de uso permite a alteração de um contrato parcelamento por cliente
	 * 
	 * @author Paulo Diniz, Mariana Victor
	 * @date 04/04/2011, 27/06/2011
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
	    ActionForward retorno = actionMapping.findForward("exibirAtualizar");
	    
	    HttpSession sessao = httpServletRequest.getSession(false);
	    
	    Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
	    AtualizarContratoParcelamentoPorClienteActionForm form = (AtualizarContratoParcelamentoPorClienteActionForm) actionForm;

	    String contratoAtualizarNumero = httpServletRequest.getParameter("contratoAtualizarNumero");
	    
	    Fachada fachada = Fachada.getInstancia();
	    
	    this.carregarObjetosSessao(sessao, usuarioLogado);
    	
	    String method = httpServletRequest.getParameter("method");
	    
        ContratoParcelamento contratoAtualizar = (ContratoParcelamento) sessao.getAttribute("contratoAtualizar");
        
        sessao.removeAttribute("mensagemAlerta");
        
        if (httpServletRequest.getParameter("consulta") != null
        		&& httpServletRequest.getParameter("consulta").trim().equalsIgnoreCase("contratoAnt")
        		&& form.getNumeroContratoAnt() != null
        		&& !form.getNumeroContratoAnt().equals("")) {
        	
        	// [FS0008 - Verificar existência do contrato anterior]
        	if (!fachada.verificarExistenciaContratoAnterior(form.getNumeroContratoAnt())) {
				throw new ActionServletException(
						"atencao.nao_existe.numero.contrato_parcelamento");
        	}
        	
        	// [FS0037 - Verificar situação do contrato anterior]
        	if (fachada.verificarSituacaoContratoAnterior(form.getNumeroContratoAnt())) {
				throw new ActionServletException(
						"atencao.contrato_parcelamento_anterior.nao_esta_encerrado");
        	}
        	
        	if (contratoAtualizar != null) {
	        	ContratoParcelamento contratoParcelamentoAnt = new ContratoParcelamento();
	        	contratoParcelamentoAnt.setNumero(form.getNumeroContratoAnt());
	        	contratoAtualizar.setContratoAnterior(contratoParcelamentoAnt);
        	}

			sessao.setAttribute("etapa", "primeira");
			
        } else if (httpServletRequest.getParameter("consulta") != null
				&& httpServletRequest.getParameter("consulta").toString().trim().equalsIgnoreCase("usuario")) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLoginUsuario()));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			// [FS0009] - Verificar existência do usuário
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				form.setNomeUsuario(usuario.getNomeUsuario());
				form.setLoginUsuario(usuario.getLogin());
				contratoAtualizar.setUsuarioResponsavel(usuario);
				
				sessao.setAttribute("usuarioEncontrado","true");
			} else {

				sessao.removeAttribute("usuarioEncontrado");
				form.setLoginUsuario("");
				form.setNomeUsuario("Usuário Inexistente");
				
				contratoAtualizar.setUsuarioResponsavel(null);
			}
			sessao.setAttribute("etapa", "primeira");
			
		} else if (contratoAtualizar == null || (contratoAtualizarNumero != null && !contratoAtualizarNumero.equals(""))) {
			// Se a requisição veio do Consultar Contrato ou do Inserir Contrato

		    this.apagarDadosSessao(sessao, form);
		    
			this.carregarDadosPrimeiraEtapa(sessao, form, httpServletRequest, 
					contratoAtualizarNumero, contratoAtualizar);
			
			this.atualizarValoresForm(sessao, form);
			
		} else if(method != null &&  method.equals("desfazerContrato")){

			// Se o usuário clicou no botão "Desfazer"
		    
		    this.apagarDadosSessao(sessao, form);
		    
		    contratoAtualizarNumero = this.getContrato(sessao, form).getNumero();
		    contratoAtualizar = null;
			
			this.carregarDadosPrimeiraEtapa(sessao, form, httpServletRequest, 
					contratoAtualizarNumero, contratoAtualizar);
			
			this.atualizarValoresForm(sessao, form);
			
		}else if(method != null && method.equals("mostrarPrimeiraEtapa")){

			validarCampos(form, sessao, method, contratoAtualizar);
			
			sessao.setAttribute("etapa", "primeira");
			
		} else if(method != null && method.equals("mostrarSegundaEtapa")) {
			

			if((sessao.getAttribute("alterouCamposPrimeiraEtapa") == null
					|| !sessao.getAttribute("alterouCamposPrimeiraEtapa").toString().equalsIgnoreCase("sim"))){
			
				if (this.verificarCampoAlteradoPrimeiraEtapa(sessao, form, contratoAtualizar)) {
					
					// 2ª etapa
					this.removerListaDebitos(sessao, form);
					this.removerDebitos(sessao, form);
					
					// 3ª etapa
					this.removerValoresParcelas(sessao, form);
					
					form.setFormaPgto("-1");
					form.setResolucaoDiretoria("");
					contratoAtualizar.setResolucaoDiretoria(null);
					sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
					form.setParcelaSelecao(null);
	
					GregorianCalendar dataVencPrimeiraParc = new GregorianCalendar();
					if(dataVencPrimeiraParc.get(Calendar.DAY_OF_MONTH) > 28){
						dataVencPrimeiraParc.set(Calendar.MONTH, dataVencPrimeiraParc.get(Calendar.MONTH) + 1);
						dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
					}else{
						dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
					}
					
					form.setDataVencimentoPrimParcela(Util.formatarData(dataVencPrimeiraParc.getTime()));
					contratoAtualizar.setDataVencimentoPrimParcela(dataVencPrimeiraParc.getTime());
					
					if (form.getAutocompleteCliente() != null && !"".equals(form.getAutocompleteCliente())
							&& form.getAutocompleteCliente().contains("-")){
						int id = Integer.parseInt(form.getAutocompleteCliente().split(" - ")[0].trim());

						Cliente cliente = fachada.pesquisarDadosCliente(id);

						ContratoParcelamento contratoParcelamento = fachada.
							pesquisarContratoParcelamentoCompleto(contratoAtualizar.getId(), null);
						
						ContratoParcelamentoCliente clienteContrato = new ContratoParcelamentoCliente();
						clienteContrato.setCliente(cliente);
						clienteContrato.setContrato(contratoParcelamento);
						
						sessao.setAttribute("clienteContrato", clienteContrato);
						sessao.setAttribute("tipoConsulta", "cliente");
					}else{
						sessao.removeAttribute("clienteContrato");
						sessao.removeAttribute("tipoConsulta");
					}
					
					if (form.getAutocompleteClienteSuperior() != null && !"".equals(form.getAutocompleteClienteSuperior())
							&& form.getAutocompleteClienteSuperior().contains("-")){
						int id = Integer.parseInt(form.getAutocompleteClienteSuperior().split(" - ")[0].trim());
						
						Cliente cliente = fachada.pesquisarDadosCliente(id);
						
						ContratoParcelamento contratoParcelamento = fachada.
							pesquisarContratoParcelamentoCompleto(contratoAtualizar.getId(), null);
						
						ContratoParcelamentoCliente clienteSuperiorContrato = new ContratoParcelamentoCliente();
						clienteSuperiorContrato.setCliente(cliente);
						clienteSuperiorContrato.setContrato(contratoParcelamento);
						
						sessao.setAttribute("clienteSuperiorContrato", clienteSuperiorContrato);
						sessao.setAttribute("tipoConsulta", "clienteSuperior");
					}else{
						sessao.removeAttribute("clienteSuperiorContrato");
						sessao.removeAttribute("tipoConsulta");
					}
					
					this.validarCampos(form, sessao, method, contratoAtualizar);
					this.calcularDebitosCliente(sessao, form, contratoAtualizar, false);
					
				}
			}
			sessao.setAttribute("etapa", "segunda");
			
		} else if (httpServletRequest.getParameter("limparTotalizacaoParcelas") != null
				&& httpServletRequest.getParameter("limparTotalizacaoParcelas")
					.toString().trim().equalsIgnoreCase("sim")) {
			
			this.removerValoresParcelas(sessao, form);
			
		} else if (httpServletRequest.getParameter("limparListaParcelas") != null
				&& httpServletRequest.getParameter("limparListaParcelas")
					.toString().trim().equalsIgnoreCase("sim")) {
			
			this.removerValoresListasParcelas(sessao, form);
			
		} else if(method != null && method.equals("mostrarTerceiraEtapa")) {

			
			if(sessao.getAttribute("finalizou") == null){
				sessao.setAttribute("finalizou", true);
			}
			
			if((sessao.getAttribute("alterouCamposPrimeiraEtapa") == null
					|| !sessao.getAttribute("alterouCamposPrimeiraEtapa").toString().equalsIgnoreCase("sim"))
					&&(sessao.getAttribute("alterouCamposSegundaEtapa") == null
						|| !sessao.getAttribute("alterouCamposSegundaEtapa").toString().equalsIgnoreCase("sim"))
						&& this.verificarCampoAlteradoPrimeiraEtapa(sessao, form, contratoAtualizar)) {
				
				// 2ª etapa
				this.removerListaDebitos(sessao, form);
				
				
				// 3ª etapa
				this.removerValoresParcelas(sessao, form);
				
				form.setFormaPgto("-1");
				form.setResolucaoDiretoria("");
				contratoAtualizar.setResolucaoDiretoria(null);
				sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
				form.setParcelaSelecao(null);

				GregorianCalendar dataVencPrimeiraParc = new GregorianCalendar();
				if(dataVencPrimeiraParc.get(Calendar.DAY_OF_MONTH) > 28){
					dataVencPrimeiraParc.set(Calendar.MONTH, dataVencPrimeiraParc.get(Calendar.MONTH) + 1);
					dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
				}else{
					dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
				}
				
				form.setDataVencimentoPrimParcela(Util.formatarData(dataVencPrimeiraParc.getTime()));
				contratoAtualizar.setDataVencimentoPrimParcela(dataVencPrimeiraParc.getTime());
				
				
			} else if ((sessao.getAttribute("alterouCamposSegundaEtapa") == null
					|| !sessao.getAttribute("alterouCamposSegundaEtapa").toString().equalsIgnoreCase("sim"))
					&& this.verificarCampoAlteradoSegundaEtapa(sessao, form)){ 
				
				// 3ª etapa
				this.removerValoresParcelas(sessao, form);
				
				form.setFormaPgto("-1");
				form.setResolucaoDiretoria("");
				contratoAtualizar.setResolucaoDiretoria(null);
				sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
				form.setParcelaSelecao(null);

				GregorianCalendar dataVencPrimeiraParc = new GregorianCalendar();
				if(dataVencPrimeiraParc.get(Calendar.DAY_OF_MONTH) > 28){
					dataVencPrimeiraParc.set(Calendar.MONTH, dataVencPrimeiraParc.get(Calendar.MONTH) + 1);
					dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
				}else{
					dataVencPrimeiraParc.set(Calendar.DAY_OF_MONTH, 28);
				}
				
				form.setDataVencimentoPrimParcela(Util.formatarData(dataVencPrimeiraParc.getTime()));
				contratoAtualizar.setDataVencimentoPrimParcela(dataVencPrimeiraParc.getTime());
				
			}
			
			boolean carregarForm = true, atualizarListasParcelas = true;
			
			if (form.getPacerlaAdd() != null && !form.getPacerlaAdd().toString().trim().equals("")) {
				
				sessao.removeAttribute("numeroParcelasPopUp");
				form.setNumeroParcelasPopUp(null);
				
				sessao.setAttribute("etapa", "terceira");
			} else {
				String numeroParcelasPopUp = httpServletRequest.getParameter("numeroParcelasPopUp");
				if(numeroParcelasPopUp != null && !numeroParcelasPopUp.equals("") ){
					sessao.setAttribute("numeroParcelasPopUp", form.getNumeroParcelasPopUp());
					sessao.setAttribute("etapa", "informarParcela");
					carregarForm = false;
					atualizarListasParcelas = false;
				}
				
				String indicadorTela = httpServletRequest.getParameter("indicadorTela");
				if(indicadorTela != null && indicadorTela.equals("popup")){
					sessao.setAttribute("etapa", "informarParcela");
					carregarForm = false;
					atualizarListasParcelas = false;
				}else{
					sessao.setAttribute("etapa", "terceira");
				}
			}
			
			String escolheuRD = httpServletRequest.getParameter("escolheuRD");
			if(escolheuRD != null && !escolheuRD.equals("") && escolheuRD.equals("true")){
				contratoAtualizar.setValorTotalParcelado(null);
				contratoAtualizar.setValorParcelamentoACobrar(null);
			}
			
			if (carregarForm) {
				this.carregarCamposDoFormulario(form, sessao, 
						httpServletRequest);
			}
			
			this.carregarDadosTerceiraEtapa(sessao, form, 
					httpServletRequest, contratoAtualizar, true, false, atualizarListasParcelas);
			
			// se alterou o número de parcelas/taxa de juros
			if (!atualizarListasParcelas) {
				sessao.removeAttribute("listaDeParcelas");
				sessao.removeAttribute("listaValoresDistintos");
				sessao.removeAttribute("listaDeParcelasPopUp");
				sessao.removeAttribute("listaValoresDistintosPopUp");
			}

		} else if(method != null && method.equals("inserirParcela")) {

			sessao.setAttribute("etapa", "informarParcela");
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
					BigDecimal valorParcelaBigDec = new BigDecimal("0");
					try{
						valorParcelaBigDec = new BigDecimal(valorParcela);
					}catch (Exception e) {
						ActionServletException ex = new ActionServletException(
								"atencao.campo.numero.parcelas.invalido", null, "");	
						ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
						sessao.setAttribute("etapa", "terceira");
						throw ex;
					}
					for(;numeroParcelInicial <= numeroParcelFinal; numeroParcelInicial++){
						PrestacaoContratoParcelamento prestacao = new PrestacaoContratoParcelamento();
						prestacao.setNumero(numeroParcelInicial);
						prestacao.setValor(valorParcelaBigDec);
						listaDeParcelasPopUp.add(prestacao);
					}
					
					if(listaValoresDistintosPopUp.isEmpty() 
							|| new BigDecimal(valorParcela).floatValue() != listaValoresDistintosPopUp.get(listaValoresDistintosPopUp.size()-1)){
						listaValoresDistintosPopUp.add(new BigDecimal(valorParcela).floatValue());
					}
					
				}else{
					ActionServletException ex = new ActionServletException("atencao.lista.parcelas.descontinuas",null, "");
					ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
					sessao.setAttribute("etapa", "informarParcela");
					throw ex;
				}
			}else{
				int numeroParcelInicial = Integer.parseInt(parcelaInicial);
				int numeroParcelFinal = Integer.parseInt(parcelaFinal);
				
				if(numeroParcelInicial != 1){
					ActionServletException ex = new ActionServletException("atencao.lista.parcelas.descontinuas",null, "");
					ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
					sessao.setAttribute("etapa", "informarParcela");
					throw ex;
				}

				valorParcela = valorParcela.replace(".", "");
				valorParcela = valorParcela.replace(",", ".");
				BigDecimal valorParcelaBigDec = new BigDecimal("0");
				try{
					valorParcelaBigDec = new BigDecimal(valorParcela);
				}catch (Exception e) {
					ActionServletException ex = new ActionServletException(
							"atencao.campo.numero.parcelas.invalido", null, "");	
					ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
					sessao.setAttribute("etapa", "terceira");
					throw ex;
				}
				
				for(;numeroParcelInicial <= numeroParcelFinal; numeroParcelInicial++){
					PrestacaoContratoParcelamento prestacao = new PrestacaoContratoParcelamento();
					prestacao.setNumero(numeroParcelInicial);
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
			sessao.setAttribute("listaDeParcelas", listaDeParcelasPopUp);
			sessao.setAttribute("listaValoresDistintos", listaValoresDistintosPopUp);
			sessao.setAttribute("etapa", "informarParcela");
			sessao.setAttribute("valorParcelTotal", valorParcelTotal);
			
		}else if(method != null && method.equals("cancelarParcela")){
			
			carregarCamposDoFormulario(form, sessao, httpServletRequest);

			sessao.removeAttribute("listaDeParcelasPopUp");
			sessao.removeAttribute("listaValoresDistintosPopUp");
			sessao.removeAttribute("listaDeParcelas");
			sessao.removeAttribute("listaValoresDistintos");
			sessao.removeAttribute("numeroParcelasPopUp");
			sessao.setAttribute("valorParcelTotal", new BigDecimal(0));
			sessao.setAttribute("etapa", "terceira");
			
		}else if(method != null && method.equals("removerParcela")){
			
			carregarCamposDoFormulario(form, sessao, httpServletRequest);
			
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
			sessao.setAttribute("listaDeParcelas", listaDeParcelasPopUp);
			sessao.setAttribute("listaValoresDistintos", listaValoresDistintosPopUp);
			sessao.setAttribute("etapa", "informarParcela");
			sessao.setAttribute("valorParcelTotal", valorParcelTotal);
			
		}else if(method != null && method.equals("informarParcela")){
			
			carregarCamposDoFormulario(form, sessao, httpServletRequest);
			
			List<PrestacaoContratoParcelamento> listaDeParcelasPopUp = (List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelasPopUp");
			List<Float> listaValoresDistintosPopUp = (List<Float>) sessao.getAttribute("listaValoresDistintosPopUp");
			
			String indicadorDebitoAcresc = form.getIndicadorDebitoAcresc();
			BigDecimal valorParcelado = null;
			if(Integer.parseInt(indicadorDebitoAcresc) == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()){
				BigDecimal acrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecao");
				valorParcelado = acrescimo;
			}else{
				BigDecimal valorConta = (BigDecimal) sessao.getAttribute("valorContaSelecao");
				valorParcelado = valorConta;
			}
			
			//[FS0025]
			verificaValorParceladoTotal(listaDeParcelasPopUp, valorParcelado);
			
			//[FS0026]
			verificaContinuidadeParcelas(listaDeParcelasPopUp);
			
			//[FS0029]
			if(contratoAtualizar.getResolucaoDiretoria() != null){
				verificaNumeroParcelasComRD(listaDeParcelasPopUp, contratoAtualizar.getResolucaoDiretoria(), form, sessao,
						"informarParcela", listaDeParcelasPopUp.size());
			}else{
				verificaNumeroParcelasSemRD(listaDeParcelasPopUp, form, sessao, "informarParcela");
			}
			
			sessao.setAttribute("listaDeParcelas", listaDeParcelasPopUp);
			sessao.setAttribute("listaValoresDistintos", listaValoresDistintosPopUp);
			sessao.setAttribute("etapa", "informouParcelas");
		}
        
        if(form.getSelecionouContas() == null || !form.getSelecionouContas().trim().equalsIgnoreCase("sim")) {
	    	sessao.removeAttribute("contasSelecionadas");
	    	
	    	form.setContasSelecao(null);
	    	form.setContasSelecionadas(null);
	    }
	    
	    if(form.getSelecionouDebitosACobrar() == null || !form.getSelecionouDebitosACobrar().trim().equalsIgnoreCase("sim")) {
	    	sessao.removeAttribute("debitosACobrarSelecionados");
	    	
	    	form.setDebitosSelecao(null);
	    	form.setDebitosACobrarSelecionados(null);
	    }
	    
	    
		String[] contasSelecionadas = form.getContasSelecao();
		
		if (contasSelecionadas != null && contasSelecionadas.length > 0) {
			form.setContasSelecionadas(Arrays.toString(contasSelecionadas).replace("[","").replace("]",""));
			sessao.setAttribute("contasSelecionadas",form.getContasSelecionadas());
		} else {
			form.setContasSelecionadas(null);
			sessao.removeAttribute("contasSelecionadas");
		}
		
		String[] debitosACobrarSelecionados = form.getDebitosSelecao();
		
		if (debitosACobrarSelecionados != null && debitosACobrarSelecionados.length > 0) {
			form.setDebitosACobrarSelecionados(Arrays.toString(debitosACobrarSelecionados).replace("[","").replace("]",""));
			sessao.setAttribute("debitosACobrarSelecionados",form.getDebitosACobrarSelecionados());
		} else {
			form.setDebitosACobrarSelecionados(null);
			sessao.removeAttribute("debitosACobrarSelecionados");
		}
		
		if (contratoAtualizar != null 
				&& ((contratoAtualizar.getIndicadorParcelamentoJuros() != null
						&& contratoAtualizar.getIndicadorParcelamentoJuros().compareTo(ConstantesSistema.NAO) == 0)
				|| (contratoAtualizar.getResolucaoDiretoria() != null 
						&& sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper") != null
						&& !sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper").equals("")))) {
			sessao.setAttribute("taxaJurosDesabilitada", true);
		} else {
			sessao.removeAttribute("taxaJurosDesabilitada");
		}
		
		if (contratoAtualizar != null 
				&& (contratoAtualizar.getResolucaoDiretoria() != null 
						&& sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper") != null
						&& !sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper").equals(""))) {
			sessao.setAttribute("numeroParcelasDesabilitada", true);
		} else {
			sessao.removeAttribute("numeroParcelasDesabilitada");
		}
		
		if (contratoAtualizar != null 
				&& contratoAtualizar.getResolucaoDiretoria() != null
				&& contratoAtualizar.getResolucaoDiretoria().getId() != null) {
			
			CobrancaForma cobrancaForma = fachada.pesquisarFormaPagamentoRD(
					contratoAtualizar.getResolucaoDiretoria().getId());
			form.setFormaPagamentoRD(cobrancaForma.getDescricao());
			sessao.setAttribute("formaPagamentoDesabilitada", true);
			
		} else {
			sessao.removeAttribute("formaPagamentoDesabilitada");
		}
		
	    return retorno;
	    
	}

	private void carregarCamposDoFormulario(AtualizarContratoParcelamentoPorClienteActionForm form, HttpSession sessao,
			HttpServletRequest httpServletRequest){
		ContratoParcelamento contratoAtualizar = this.getContrato(sessao, form);

	    Fachada fachada = Fachada.getInstancia();
	    
	    boolean pegarValoresDoForm = false;
	    
		if (form.getNumeroContratoAnt() != null && !"".equals(form.getNumeroContratoAnt())){
			ContratoParcelamento contratoAnterior = fachada.
					pesquisarContratoParcelamentoCompleto(null, form.getNumeroContratoAnt());
			if(contratoAnterior != null){
				contratoAtualizar.setContratoAnterior(contratoAnterior);
			}
			
			if(form.getRelacaoAnterior() != null && !form.getRelacaoAnterior().equals("")){
				TipoRelacao relacao = new TipoRelacao();
				relacao.setId(Integer.parseInt(form.getRelacaoAnterior()));
				contratoAtualizar.setRelacaoAnterior(relacao);
			}
		}else{
			contratoAtualizar.setContratoAnterior(null);
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
			if (sessao.getAttribute("clienteContrato") == null
					|| sessao.getAttribute("clienteContrato").toString().trim().equals("")) {
				int id = Integer.parseInt(form.getAutocompleteCliente().split(" - ")[0].trim());
	
				ContratoParcelamentoCliente clienteContrato = fachada.
					pesquisarClienteContrato(contratoAtualizar.getId(), id);
				
				sessao.setAttribute("clienteContrato", clienteContrato);
				sessao.setAttribute("tipoConsulta", "cliente");
			}
		}else{
			sessao.removeAttribute("clienteContrato");
			sessao.removeAttribute("tipoConsulta");
		}
		
		if (form.getAutocompleteClienteSuperior() != null && !"".equals(form.getAutocompleteClienteSuperior())
				&& form.getAutocompleteClienteSuperior().contains("-")){
			
			if (sessao.getAttribute("clienteSuperiorContrato") == null
					|| sessao.getAttribute("clienteSuperiorContrato").toString().trim().equals("")) {
				int id = Integer.parseInt(form.getAutocompleteClienteSuperior().split(" - ")[0].trim());
			
				ContratoParcelamentoCliente clienteSuperiorContrato = fachada.
					pesquisarClienteContrato(contratoAtualizar.getId(), id);
				
				sessao.setAttribute("clienteSuperiorContrato", clienteSuperiorContrato);
				sessao.setAttribute("tipoConsulta", "clienteSuperior");
			}
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
		
		if(form.getResolucaoDiretoria() != null && !form.getResolucaoDiretoria().equals("")){
			
			ContratoParcelamentoRD resolucao = fachada.pesquisarContratoParcelamentoRDPorNumero(form.getResolucaoDiretoria());
			contratoAtualizar.setResolucaoDiretoria(resolucao);
			
			contratoAtualizar.setIndicadorDebitosAcrescimos(resolucao.getIndicadorDebitoAcrescimo());
			contratoAtualizar.setIndicadorParcelamentoJuros(resolucao.getIndicadorParcelamentoJuros());
			contratoAtualizar.setIndicadorPermiteInformarValorParcel(resolucao.getIndicadorInformarParcela());
			contratoAtualizar.setCobrancaForma(resolucao.getCobrancaForma());
			
			form.setFormaPgto(resolucao.getCobrancaForma().getId().toString());
			
			List<QuantidadePrestacoes> parcelas = null;
			FiltroQuantidadePrestacoes filtroQtdPrestacoes = new FiltroQuantidadePrestacoes(FiltroQuantidadePrestacoes.QTD_FATURAS_PARCELADAS);
			filtroQtdPrestacoes.adicionarParametro(new ComparacaoTexto(
					FiltroQuantidadePrestacoes.CONTRATO_PARCELAMENTO_RD_NUMERO, 
					contratoAtualizar.getResolucaoDiretoria().getNumero().toUpperCase()));
			parcelas = new ArrayList<QuantidadePrestacoes>(fachada.pesquisar(filtroQtdPrestacoes,QuantidadePrestacoes.class.getName()));
			Collections.sort(parcelas, new ComparatorParcela());
			
			if(parcelas == null || parcelas.size() <= 0){
				sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
				form.setParcelaSelecao(null);
			} else {
				QuantidadePrestacoes quantidadePrestacoesRD = contratoAtualizar.getQtdPrestacoesDaRDEscolhida();
				
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
					
					if (form.getParcelaSelecao() != null 
							&& form.getParcelaSelecao().compareTo(
									quantidadePrestacoes.getId().toString()) == 0) {
						form.setValorDaParcela(Util.formatarMoedaReal(
								helper.getValorParcelado().divide(new BigDecimal(
										quantidadePrestacoes.getQtdFaturasParceladas()))));
					}
				}
				

				sessao.setAttribute("colecaoQuantidadePrestacoesRDHelper", colecaoQuantidadePrestacoesRDHelper);

				fachada
					.calcularValoresParcelasContratoParcelamentoRD(valorContaSelecao, valorContaComAcrescimo, 
							form.getIndicadorDebitoAcresc(), form.getIndicadorParcelJuros(), contratoAtualizar, 
							quantidadePrestacoesRD);
			}
			
			Integer idRDNaBase = null;
			
			if (sessao.getAttribute("ultimaRD") != null 
					&& !sessao.getAttribute("ultimaRD").toString().trim().equals("")) {
				idRDNaBase = new Integer(sessao.getAttribute("ultimaRD").toString());
			} else if (sessao.getAttribute("ultimaRD") == null 
					|| !sessao.getAttribute("ultimaRD").toString().trim().equals("0")){
				idRDNaBase = fachada.pesquisarRDContratoParcelamento(contratoAtualizar.getId());
			}

			// remover as coleções apenas se a RD for alterada
			if (idRDNaBase == null || idRDNaBase.compareTo(resolucao.getId()) != 0) {
				this.removerValoresParcelas(sessao, form);
				
				sessao.setAttribute("alterouRD", "sim");
				sessao.setAttribute("ultimaRD", resolucao.getId());
			}
			
		}else{
			contratoAtualizar.setResolucaoDiretoria(null);
			
			sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
			form.setParcelaSelecao(null);
			Integer idRDNaBase = fachada.pesquisarRDContratoParcelamento(contratoAtualizar.getId());
			
			if (idRDNaBase != null 
					&& (sessao.getAttribute("alterouRD") == null 
							|| !sessao.getAttribute("alterouRD").toString().trim().equals("sim")
							|| (sessao.getAttribute("ultimaRD") != null 
									&& !sessao.getAttribute("ultimaRD").toString().trim().equals("0")))) {
				this.removerValoresParcelas(sessao, form);
				
				sessao.setAttribute("alterouRD", "sim");
				sessao.setAttribute("ultimaRD", 0);
				
				pegarValoresDoForm = true;
			}
			
			
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
		
		this.carregaValoresContas(sessao, form, contratoAtualizar, pegarValoresDoForm, httpServletRequest, true);
		
		if(form.getDataVencimentoPrimParcela() != null && !form.getDataVencimentoPrimParcela().equals("")){
			Date dataVencimentoPrimParcela = Util.converteStringParaDate(form.getDataVencimentoPrimParcela());
			contratoAtualizar.setDataVencimentoPrimParcela(dataVencimentoPrimParcela);
		}
		
		List<QuantidadePrestacoesRDHelper> colecaoQuantidadePrestacoesRDHelper = 
			(List<QuantidadePrestacoesRDHelper>) sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper");
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
	
	private void calcularDebitosCliente(HttpSession sessao, 
			AtualizarContratoParcelamentoPorClienteActionForm form,
			ContratoParcelamento contratoAtualizar,
			boolean pegarContasContrato) throws ActionServletException{

	    Fachada fachada = Fachada.getInstancia();
	    
		ArrayList<ObterDebitoImovelOuClienteHelper> colecaoClientesDebitosImoveis = new ArrayList<ObterDebitoImovelOuClienteHelper>();
		
		ContratoParcelamentoCliente clienteContrato = (ContratoParcelamentoCliente) sessao.getAttribute("clienteContrato");
		ContratoParcelamentoCliente clienteSuperiorContrato = (ContratoParcelamentoCliente) sessao.getAttribute("clienteSuperiorContrato");
		if (contratoAtualizar == null || contratoAtualizar.getId() == null) {
			contratoAtualizar = this.getContrato(sessao, form);
		}
		
		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;
		try {
			dataVencimentoDebitoI = formatoData.parse(contratoAtualizar.getDataVencimentoInicioFormatada());
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(contratoAtualizar.getDataVencimentoFinalFormatada());
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		
		String anoMesInicial = contratoAtualizar.getAnoMesDebitoInicio()+"";
		String anoMesFinal = contratoAtualizar.getAnoMesDebitoFinal()+"";
		
		if(contratoAtualizar.getAnoMesDebitoInicio() == null || contratoAtualizar.getAnoMesDebitoFinal() == null){
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
		Integer indicadorDebito = new Integer(1);//Modificado
		Integer indicadorCredito = new Integer(2);//Modificado
		Integer indicadorNotas = new Integer(2);//Modificado
		Integer indicadorGuias = new Integer(2);//Modificado
		Integer indicadorAtualizar = new Integer(1);
		Short relacaoTipo = null;
		Integer tipoImovel = null;
		
		if(clienteContrato != null){
			

			if(contratoAtualizar.getRelacaoCliente() != null ){
				if(contratoAtualizar.getRelacaoCliente().getId().intValue() == ClienteRelacaoTipo.PROPRIETARIO){
					relacaoTipo = ClienteRelacaoTipo.PROPRIETARIO;
				}else if(contratoAtualizar.getRelacaoCliente().getId().intValue() == ClienteRelacaoTipo.RESPONSAVEL){
					relacaoTipo = ClienteRelacaoTipo.RESPONSAVEL;
				}else if(contratoAtualizar.getRelacaoCliente().getId().intValue() == ClienteRelacaoTipo.USUARIO){
					relacaoTipo = ClienteRelacaoTipo.USUARIO;
				}
			}
			
			if(relacaoTipo == null || relacaoTipo.equals(ClienteRelacaoTipo.RESPONSAVEL)){
				if(contratoAtualizar.getIndicadorResponsavel() != null && contratoAtualizar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_TODOS){
					tipoImovel = new Integer(4);	
				}else if(contratoAtualizar.getIndicadorResponsavel() != null && contratoAtualizar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_ATUAL_DO_IMOVEL){
					tipoImovel = new Integer(3);	
				}else{
					tipoImovel = new Integer(2);	
				}
			}else{
				tipoImovel = new Integer(2);	
			}
			
			// Obtendo os débitos do imovel
			if(contratoAtualizar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_INDICADOR_NA_CONTA
					|| contratoAtualizar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_ATUAL_DO_IMOVEL
					|| contratoAtualizar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_TODOS){
				
				ObterDebitoImovelOuClienteHelper colecao = fachada.obterDebitoImovelOuCliente(
						tipoImovel.intValue(), null, "" + clienteContrato.getCliente().getId(), relacaoTipo,
						anoMesInicial, anoMesFinal, dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento.intValue(),
						indicadorContaRevisao.intValue(), indicadorDebito.intValue(),
						indicadorCredito.intValue(), indicadorNotas.intValue(),
						indicadorGuias.intValue(), indicadorAtualizar.intValue(),
						null, 3);
				
				List<DebitosClienteHelper> listaDebitos = new ArrayList<DebitosClienteHelper>();
				
				Collection<ContaValoresHelper> contaValoresFinal = new ArrayList<ContaValoresHelper>();
				Collection<ContaValoresHelper> contaValoresFinalAuxiliar = new ArrayList<ContaValoresHelper>();
				
				List<Integer> idsContas = new ArrayList();
				
				for (ContaValoresHelper contaValoresHelper : colecao.getColecaoContasValores()) {
					if(form.verificaContaSelecionada(contaValoresHelper.getConta().getId().intValue())){
						DebitosClienteHelper debito = new DebitosClienteHelper();
						debito.setIdentificadorItem(contaValoresHelper.getConta().getId());
						debito.setTipoDocumento(DocumentoTipo.CONTA);
						debito.setValorItem(contaValoresHelper.getConta().getValorTotal());
						debito.setValorAcrescImpont(contaValoresHelper.getValorTotalContaValores());
						listaDebitos.add(debito);
						contaValoresFinalAuxiliar.add(contaValoresHelper);
						idsContas.add(contaValoresHelper.getConta().getId());
					}else {
						if(contaValoresHelper.getConta().getDataRevisao() == null){
							contaValoresFinalAuxiliar.add(contaValoresHelper);
							idsContas.add(contaValoresHelper.getConta().getId());
						}else{
							if(contaValoresHelper.getConta().getContaMotivoRevisao().getId().intValue() != ContaMotivoRevisao.CONTA_EM_CONTRATO_PARCELAMENTO){
								contaValoresFinalAuxiliar.add(contaValoresHelper);
								idsContas.add(contaValoresHelper.getConta().getId());
							}
						}
					}
				}
				
				if (pegarContasContrato) {
					Collection<ContaValoresHelper> colecaoContaValoresHelper = 
						fachada.pesquisarDadosDasContasContratoParcelamento(contratoAtualizar.getId());
	
					// 3. Caso já existam contas vinculadas ao contrato  
					if (colecaoContaValoresHelper != null && !colecaoContaValoresHelper.isEmpty()) {
						Iterator iterator = colecaoContaValoresHelper.iterator();
						
						while(iterator.hasNext()) {
							ContaValoresHelper helper = (ContaValoresHelper) iterator.next();
							
							//E que não existam na lista de contas retornada pelo UC0067
							if (!idsContas.contains(helper.getConta().getId())) {
								contaValoresFinal.add(helper);
								
								DebitosClienteHelper debito = new DebitosClienteHelper();
								debito.setIdentificadorItem(helper.getConta().getId());
								debito.setTipoDocumento(DocumentoTipo.CONTA);
								debito.setValorItem(helper.getConta().getValorTotal());
								debito.setValorAcrescImpont(helper.getValorTotalContaValores());
								
								//adiciona a conta na lista de contas que devem estar selecionadas
								listaDebitos.add(debito);
								
							} else {
								continue;
							}
						}
						
					}
				}
				
				contaValoresFinal.addAll(contaValoresFinalAuxiliar);
				
				colecao.setColecaoContasValores(contaValoresFinal);
				
				sessao.setAttribute("listaDebitos",listaDebitos);
				form.setContasSelecionadas(this.retornarContasDebitosACobrarSelecionados(listaDebitos));
				sessao.setAttribute("contasSelecionadas",form.getContasSelecionadas());

				colecaoClientesDebitosImoveis.add(colecao);
				
			}
		} else {
			
			if(contratoAtualizar.getIndicadorResponsavel().intValue() == ContratoParcelamento.RESP_ATUAL_DO_IMOVEL){
				tipoImovel = new Integer(3);//Modificado
				
				ObterDebitoImovelOuClienteHelper colecao = fachada.obterDebitoImovelOuCliente(
						tipoImovel.intValue(), null, ""+clienteSuperiorContrato.getCliente().getId(), new Short("99"),
						anoMesInicial, anoMesFinal, dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento.intValue(),
						indicadorContaRevisao.intValue(), indicadorDebito.intValue(),
						indicadorCredito.intValue(), indicadorNotas.intValue(),
						indicadorGuias.intValue(), indicadorAtualizar.intValue(),
						null, 3);	
				
				List<DebitosClienteHelper> listaDebitos = new ArrayList<DebitosClienteHelper>();
				
				Collection<ContaValoresHelper> contaValoresFinal = new ArrayList<ContaValoresHelper>();
				Collection<ContaValoresHelper> contaValoresFinalAuxiliar = new ArrayList<ContaValoresHelper>();
				
				List<Integer> idsContas = new ArrayList();
				
				for (ContaValoresHelper contaValoresHelper : colecao.getColecaoContasValores()) {
					if(form.verificaContaSelecionada(contaValoresHelper.getConta().getId().intValue())){
						DebitosClienteHelper debito = new DebitosClienteHelper();
						debito.setIdentificadorItem(contaValoresHelper.getConta().getId());
						debito.setTipoDocumento(DocumentoTipo.CONTA);
						debito.setValorItem(contaValoresHelper.getConta().getValorTotal());
						debito.setValorAcrescImpont(contaValoresHelper.getValorTotalContaValores());
						listaDebitos.add(debito);
						contaValoresFinalAuxiliar.add(contaValoresHelper);
						idsContas.add(contaValoresHelper.getConta().getId());
					}else {
						if(contaValoresHelper.getConta().getDataRevisao() == null){
							contaValoresFinalAuxiliar.add(contaValoresHelper);
							idsContas.add(contaValoresHelper.getConta().getId());
						}else{
							if(contaValoresHelper.getConta().getContaMotivoRevisao().getId().intValue() != ContaMotivoRevisao.CONTA_EM_CONTRATO_PARCELAMENTO){
								contaValoresFinalAuxiliar.add(contaValoresHelper);
								idsContas.add(contaValoresHelper.getConta().getId());
							}
						}
					}
				}
				
				if (pegarContasContrato) {
					Collection<ContaValoresHelper> colecaoContaValoresHelper = 
						fachada.pesquisarDadosDasContasContratoParcelamento(contratoAtualizar.getId());
	
					// 3. Caso já existam contas vinculadas ao contrato  
					if (colecaoContaValoresHelper != null && !colecaoContaValoresHelper.isEmpty()) {
						Iterator iterator = colecaoContaValoresHelper.iterator();
						
						while(iterator.hasNext()) {
							ContaValoresHelper helper = (ContaValoresHelper) iterator.next();
							
							//E que não existam na lista de contas retornada pelo UC0067
							if (!idsContas.contains(helper.getConta().getId())) {
								contaValoresFinal.add(helper);
								
								DebitosClienteHelper debito = new DebitosClienteHelper();
								debito.setIdentificadorItem(helper.getConta().getId());
								debito.setTipoDocumento(DocumentoTipo.CONTA);
								debito.setValorItem(helper.getConta().getValorTotal());
								debito.setValorAcrescImpont(helper.getValorTotalContaValores());
								listaDebitos.add(debito);
								
							} else {
								continue;
							}
						}
						
					}
				}
				
				contaValoresFinal.addAll(contaValoresFinalAuxiliar);
				
				colecao.setColecaoContasValores(contaValoresFinal);
				colecaoClientesDebitosImoveis.add(colecao);
				
				sessao.setAttribute("listaDebitos",listaDebitos);
				form.setContasSelecionadas(this.retornarContasDebitosACobrarSelecionados(listaDebitos));
				sessao.setAttribute("contasSelecionadas",form.getContasSelecionadas());
			}
		}
		
		/////////////////////////////////////////CARREGA VALORES DEFAULT PARA TODAS VARIAVEIS ////////////////////////////////////////////
		//Criando uma lista para adicao de todas colecoesContaValores para todos Clientes (Caso selecione Cliente Superior)
		Collection<ContaValoresHelper> colecaoContaValoresTotal = new ArrayList<ContaValoresHelper>();
		//Criando uma lista para adicao de todas colecaoDebitoACobrar para todos Clientes (Caso selecione Cliente Superior)
		Collection<DebitoACobrar> colecaoDebitoACobrarTotal = new ArrayList<DebitoACobrar>();
		Collection<DebitoACobrar> colecaoDebitoACobrarTotalFinal = new ArrayList<DebitoACobrar>();
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
					if (dadosConta.getConta().getValorTotal() != null) {
						valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
					}
					if (dadosConta.getValorTotalContaValores() != null) {
						valorAcrescimo = valorAcrescimo.add(dadosConta.getValorTotalContaValores());
					}
					if (dadosConta.getConta().getValorAgua() != null) {
						valorAgua = valorAgua.add(dadosConta.getConta().getValorAgua());
					}
					if (dadosConta.getConta().getValorEsgoto() != null) {
						valorEsgoto = valorEsgoto.add(dadosConta.getConta().getValorEsgoto());
					}
					if (dadosConta.getConta().getDebitos() != null) {
						valorDebito = valorDebito.add(dadosConta.getConta().getDebitos());
					}
					if (dadosConta.getConta().getValorCreditos() != null) {
						valorCredito = valorCredito.add(dadosConta.getConta().getValorCreditos());
					}
					if (dadosConta.getConta().getValorImposto() != null) {
						valorImposto = valorImposto.add(dadosConta.getConta().getValorImposto());
					}
					
					if (dadosConta.getValorAtualizacaoMonetaria() != null && !dadosConta.getValorAtualizacaoMonetaria().equals("")) {
						valorAtualizacaoMonetaria.setScale(
								Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						
						valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(
								dadosConta.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
					if (dadosConta.getValorJurosMora() != null	&& !dadosConta.getValorJurosMora().equals("")) {
						valorJurosMora.setScale(
								Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						
						valorJurosMora = valorJurosMora.add(
								dadosConta.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
					if (dadosConta.getValorMulta() != null && !dadosConta.getValorMulta().equals("")) {
						valorMulta.setScale(
								Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						
						valorMulta = valorMulta.add(
								dadosConta.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
				}
			}
			//////////////////////FIM ITERACAO NA COLECAO DE CONTAVALORES////////////////////////////////////	
			
			
			//////////////////////ITERACAO NA COLECAO DE DEBITOACOBRAR////////////////////////////////////	
			Collection<DebitoACobrar> colecaoDebitoACobrar = new ArrayList<DebitoACobrar>();
			List<Integer> idsDebitoACobrar = new ArrayList();
			List<DebitosClienteHelper> listaDebitosACobrar = new ArrayList<DebitosClienteHelper>();
			if(colecaoDebitoImovel.getColecaoDebitoACobrar() != null){
				colecaoDebitoACobrar.addAll(colecaoDebitoImovel.getColecaoDebitoACobrar());
				colecaoDebitoACobrarTotal.addAll(colecaoDebitoImovel.getColecaoDebitoACobrar());
			}
			
			if (!colecaoDebitoACobrar.isEmpty()) {
				java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
				// percorre a colecao de debito a cobrar somando o valor para obter um valor total
				while (colecaoDebitoACobrarIterator.hasNext()) {
					
					dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
					valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotal());
					idsDebitoACobrar.add(dadosDebito.getId());

					DebitosClienteHelper debito = new DebitosClienteHelper();
					debito.setIdentificadorItem(dadosDebito.getId());
					debito.setTipoDocumento(DocumentoTipo.DEBITO_A_COBRAR);
					debito.setValorItem(dadosDebito.getValorTotal());
					debito.setValorAcrescImpont(null);
					listaDebitosACobrar.add(debito);
					
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
						
						BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorCurtoELongoPrazo(
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
			//////////////////////FIM ITERACAO NA COLECAO DE DEBITOACOBRAR////////////////////////////////////	

			//////////////////////CONSULTA DE DEBITOACOBRAR DO CONTRATO////////////////////////////////////
			if (pegarContasContrato) {
				Collection<DebitoACobrar> colecaoDebitoACobrarContrato = 
					fachada.pesquisarDadosDosDebitosACobrarContratoParcelamento(contratoAtualizar.getId());
				
				// 3. Caso já existam contas vinculadas ao contrato  
				if (colecaoDebitoACobrarContrato != null && !colecaoDebitoACobrarContrato.isEmpty()) {
					Iterator iterator = colecaoDebitoACobrarContrato.iterator();
					
					while(iterator.hasNext()) {
						DebitoACobrar debitoACobrar = (DebitoACobrar) iterator.next();
						
						//E que não existam na lista de contas retornada pelo UC0067
						if (!idsDebitoACobrar.contains(debitoACobrar.getId())) {
							colecaoDebitoACobrarTotalFinal.add(debitoACobrar);
							valorDebitoACobrar = valorDebitoACobrar.add(debitoACobrar.getValorTotal());
							
							DebitosClienteHelper debito = new DebitosClienteHelper();
							debito.setIdentificadorItem(debitoACobrar.getId());
							debito.setTipoDocumento(DocumentoTipo.DEBITO_A_COBRAR);
							debito.setValorItem(debitoACobrar.getValorTotal());
							debito.setValorAcrescImpont(null);
							
							//adiciona a conta na lista de débitos que devem estar selecionadas
							listaDebitosACobrar.add(debito);
							
						} else {
							continue;
						}
					}
					
				}
			}
			
			if (colecaoDebitoACobrarTotal != null && !colecaoDebitoACobrarTotal.isEmpty()) {
				colecaoDebitoACobrarTotalFinal.addAll(colecaoDebitoACobrarTotal);
			}
			sessao.setAttribute("listaDebitosACobrar",listaDebitosACobrar);
			form.setDebitosACobrarSelecionados(this.retornarContasDebitosACobrarSelecionados(listaDebitosACobrar));
			sessao.setAttribute("debitosACobrarSelecionados",form.getDebitosACobrarSelecionados());
			//////////////////////FIM CONSULTA DE DEBITOACOBRAR DO CONTRATO////////////////////////////////////
			
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
		
		
		if (colecaoContaValoresTotal.isEmpty() && colecaoDebitoACobrarTotalFinal.isEmpty()
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
			if (colecaoDebitoACobrarTotalFinal.isEmpty()){
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
			
			ActionServletException ex = new ActionServletException(
					"atencao.cliente.sem.debitos", null, "");
			ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do?contratoAtualizarNumero="+contratoAtualizar.getNumero());
			sessao.setAttribute("etapa", "primeira");
			throw ex;

			
		} else {
			// Manda a colecao pelo request
			sessao.setAttribute("colecaoContaValores",colecaoContaValoresTotal);

			// Manda a colecao e os valores total de conta pelo request
			sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrarTotalFinal);
			sessao.setAttribute("valorConta", Util.formatarMoedaReal(valorConta));
			sessao.setAttribute("valorAcrescimo", Util.formatarMoedaReal(valorAcrescimo));
			sessao.setAttribute("valorAgua", Util.formatarMoedaReal(valorAgua));
			sessao.setAttribute("valorEsgoto", Util.formatarMoedaReal(valorEsgoto));
			sessao.setAttribute("valorDebito", Util.formatarMoedaReal(valorDebito));
			sessao.setAttribute("valorCredito", Util.formatarMoedaReal(valorCredito));
			sessao.setAttribute("valorContaAcrescimo", Util.formatarMoedaReal(valorConta.add(valorAcrescimo)));
			sessao.setAttribute("valorImposto", Util.formatarMoedaReal(valorImposto));

			// Manda a colecao e o valor total de DebitoACobrar pelo request
			sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrarTotalFinal);
			sessao.setAttribute("valorDebitoACobrar", Util.formatarMoedaReal(valorDebitoACobrar));

			// Manda a colecao e o valor total de CreditoARealizar pelo request
			sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizarTotal);
			sessao.setAttribute("valorCreditoARealizar", Util.formatarMoedaReal(valorCreditoARealizar));
			sessao.setAttribute("valorCreditoARealizarSemDescontosParcelamento",Util.formatarMoedaReal(valorCreditoARealizarSemDescontosParcelamento));

			// Manda a colecao e o valor total de GuiaPagamento pelo request
			sessao.setAttribute("colecaoGuiaPagamentoValores",colecaoGuiaPagamentoValoresTotal);
			sessao.setAttribute("valorGuiaPagamento", Util.formatarMoedaReal(valorGuiaPagamento));

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
	

	private void validarCampos(AtualizarContratoParcelamentoPorClienteActionForm form, HttpSession sessao, String method, ContratoParcelamento contratoAtualizar) throws ActionServletException{
		ActionServletException ex = null;

	    Fachada fachada = Fachada.getInstancia();
	    
		if (ex == null && form.getNumeroContratoAnt() != null && !"".equals(form.getNumeroContratoAnt()) ){
			ContratoParcelamento contratoAnterior = fachada.
					pesquisarContratoParcelamentoCompleto(null, form.getNumeroContratoAnt());
			
			if(contratoAnterior == null || contratoAnterior.getId() == null){
				ex = new ActionServletException(
						"atencao.numero.contrato.nao.existe",null, "");	
			}else if(contratoAnterior != null && contratoAnterior.getId() != null){ 
				if(contratoAnterior.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.NORMAL.intValue()){
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
					clienteContratoSuperior.setCliente(fachada.pesquisarDadosCliente(clienteContratoSuperior.getCliente().getId()));
					
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
			
			String etapa = (String) sessao.getAttribute("etapa");
			if(dataVencPrimParcel < dataAtual && !etapa.equals("primeira") && !etapa.equals("segunda")){
				ex = new ActionServletException(
						"atencao.data.vencimento.primeira.parcel.anterior.corrente",null, form.getDataVencimentoPrimParcela());
				sessao.setAttribute("etapa", "terceira");
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				throw ex;
			}
		}
			
		if(ex != null){
			ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do?contratoAtualizarNumero="+contratoAtualizar.getNumero());
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
			ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
			throw ex;
		}
	}
	
	//[FS0026]
	private void verificaContinuidadeParcelas(List<PrestacaoContratoParcelamento> listaDeParcelas){
		
		ActionServletException ex = new ActionServletException("atencao.lista.parcelas.descontinuas", null, "");
		ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
		
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
			ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
			throw ex;
		}
		
	}
	
	//[FS0029]
	private void verificaNumeroParcelasComRD(List<PrestacaoContratoParcelamento> listaDeParcelas, ContratoParcelamentoRD resolucao,
			AtualizarContratoParcelamentoPorClienteActionForm form,  HttpSession sessao, String etapa,
			Integer numeroParcelas){
		
		if( (listaDeParcelas != null && listaDeParcelas.size() > resolucao.getQtdFaturasParceladas().intValue()) || 
				(numeroParcelas != null && numeroParcelas > resolucao.getQtdFaturasParceladas().intValue())){
			ActionServletException ex = new ActionServletException("atencao.numero.parcelas.informado.superior.rd", null, resolucao.getQtdFaturasParceladas()+"");
			ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
			sessao.setAttribute("etapa", etapa);
			throw ex;	
		}
		
	}
	
	//[FS0029]
	private void verificaNumeroParcelasSemRD(List<PrestacaoContratoParcelamento> listaDeParcelas, AtualizarContratoParcelamentoPorClienteActionForm form, HttpSession sessao, String etapa){
		int numeroMax = 0;
		try{
			numeroMax = this.getFachada().pesquisarParametrosDoSistema().getNumeroMaximoParcelasContratosParcelamento();
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
						"atencao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;
			}
		}else {
			if((listaDeParcelas != null && listaDeParcelas.size() >  numeroMax) || 
					(form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().equals("") 
							&& Integer.parseInt(form.getNumeroParcelasPopUp()) > numeroMax)){
				ActionServletException ex = new ActionServletException(
						"atencao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", etapa);
				throw ex;
			}
		}
	}
	
	private void calcularListaParcelasDiretoTela(AtualizarContratoParcelamentoPorClienteActionForm form,
			HttpSession sessao, ContratoParcelamento contratoAtualizar) throws ActionServletException{
		
		List<PrestacaoContratoParcelamento> listaDeParcelas = (List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelas");
		String etapa = (String) sessao.getAttribute("etapa") + "";
		
		if(listaDeParcelas != null && etapa.equals("informouEtapa")){
			ActionServletException ex = new ActionServletException(
					"atencao.lista.parcelas.ja.informadas", null, "");	
			ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
			sessao.setAttribute("etapa", "terceira");
			throw ex;
		}else{
			boolean popup = false;
			String numeroParcelas = null;
			String juros = null;
			
			if (form.getPacerlaAdd() != null && !form.getPacerlaAdd().toString().trim().equals("")) {
				numeroParcelas = form.getPacerlaAdd();
			} else if (form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().toString().trim().equals("")) {
				popup = true;
				numeroParcelas = form.getNumeroParcelasPopUp();
			}
			
			if (form.getTaxaJurosAdd() != null && !form.getTaxaJurosAdd().toString().trim().equals("")) {
				juros = form.getTaxaJurosAdd();
			} 
			
			List<Float> listaValoresDistintos = new ArrayList<Float>();
			
			listaDeParcelas = new ArrayList<PrestacaoContratoParcelamento>();
			int numeroParcelInicial = 1;
			int numeroParcelFinal = Integer.parseInt(numeroParcelas);
			
			if(numeroParcelFinal <= 0){
				ActionServletException ex = new ActionServletException(
						"atencao.numero.parcelas.invalido", null, "");	
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", "terceira");
				throw ex;
			}
			int numeroMax = 0;
			try{
				numeroMax = this.getFachada().pesquisarParametrosDoSistema().getNumeroMaximoParcelasContratosParcelamento();
			}catch (Exception e) {
				ActionServletException ex = new ActionServletException(
						"atencao.numero.maximo.parcela.sistema.parametros.nao.cadastrado", null, "");	
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", "terceira");
				throw ex;
			}
			if(numeroParcelFinal >  numeroMax){
				ActionServletException ex = new ActionServletException(
						"atencao.numero.parcelas.superior.ao.maximo", null, numeroMax+"");	
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", "terceira");
				throw ex;
			}
			
			if (juros == null) {
				juros = "0";
			}
			
			juros = juros.replace(",", ".");
			if(form.getResolucaoDiretoria() != null && !form.getResolucaoDiretoria().equals("")
					&& (form.getTaxaJurosAdd() == null || form.getTaxaJurosAdd().equals(""))){
				juros = "0";
			}
			BigDecimal jurosBigDec = new BigDecimal("0");
			try{
				jurosBigDec = new BigDecimal(juros);
			}catch (Exception e) {
				ActionServletException ex = new ActionServletException(
						"atencao.campo.taxa.juros.invalido", null, "");	
				ex.setUrlBotaoVoltar("/gsan/exibirAtualizarContratoParcelamentoClienteAction.do");
				sessao.setAttribute("etapa", "terceira");
				throw ex;
			}
			
			String indicadorDebitoAcresc = form.getIndicadorDebitoAcresc();
			
			if(indicadorDebitoAcresc == null || indicadorDebitoAcresc.equals("")) {
				if (contratoAtualizar.getResolucaoDiretoria() != null) {
					indicadorDebitoAcresc = ConstantesSistema.SIM.toString();
				} else {
					indicadorDebitoAcresc = contratoAtualizar.getIndicadorDebitosAcrescimos().toString();
				}
			}
			
			String indicadorParcelJuros = form.getIndicadorParcelJuros();

			
			if(indicadorParcelJuros == null || indicadorParcelJuros.equals("")) {
				indicadorParcelJuros = contratoAtualizar.getIndicadorParcelamentoJuros().toString();
			}
			
			if (indicadorParcelJuros != null 
					&& indicadorParcelJuros.trim().equalsIgnoreCase(ConstantesSistema.SIM.toString())
					&& (jurosBigDec == null || jurosBigDec.compareTo(BigDecimal.ZERO) == 0)) {

				sessao.removeAttribute("taxaJurosDesabilitada");
				
				throw new ActionServletException(
						"atencao.campo_selecionado.obrigatorio", "Taxa de Juros");
				
			}
			
			BigDecimal valorParcelado = null;
			BigDecimal valorContaSelecao = (BigDecimal) sessao.getAttribute("valorContaSelecao");
			BigDecimal acrescimo = (BigDecimal) sessao.getAttribute("valorContaAcrescimoSelecao");
			
			if (!popup) {
				InserirContratoParcelamentoValoresParcelasHelper helper = Fachada.getInstancia()
					.calcularValoresParcelasContratoParcelamento(valorContaSelecao, acrescimo, 
						indicadorDebitoAcresc, indicadorParcelJuros, jurosBigDec, 
						numeroParcelInicial, numeroParcelFinal);
				
				valorParcelado = helper.getValorParcelado();
				listaDeParcelas = helper.getListaDeParcelas();
				listaValoresDistintos = helper.getListaValoresDistintos();
				
				contratoAtualizar.setValorTotalParcelado(valorParcelado);
				contratoAtualizar.setValorParcelamentoACobrar(valorParcelado);
				
				sessao.setAttribute("listaValoresDistintos", listaValoresDistintos);
				sessao.setAttribute("listaDeParcelas", listaDeParcelas);
			}
			sessao.setAttribute("etapa","informouParcelas");
			
			sessao.setAttribute("contratoAtualizar", contratoAtualizar);
		}
		
		
	}
	
	/**
	 * Retorna um array com os ids das contas da lista passada como parâmetro
	 * 
	 * @author Mariana Victor
	 * @date 04/07/2011
	 * */
	private String retornarContasDebitosACobrarSelecionados(List<DebitosClienteHelper> listaDebitos){

		if (listaDebitos != null && !listaDebitos.isEmpty()){
			String[] contasSelecionadas = new String[listaDebitos.size()];
			
			Iterator iterator = listaDebitos.iterator();
			int i = 0;
			while(iterator.hasNext()) {
				DebitosClienteHelper helper = (DebitosClienteHelper) iterator.next();
				contasSelecionadas[i] = helper.getIdentificadorItem().toString();
				i++;
			}
			return Arrays.toString(contasSelecionadas).replace("[","").replace("]","");
		}
		
		return null;
	}
	
	private FiltroContratoParcelamentoCliente carregarFiltroContratoParcelamentoCliente(FiltroContratoParcelamentoCliente filtro){

		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato");
		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato.contratoAnterior");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato.parcelamentoSituacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato.usuarioResponsavel");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoAnterior");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoCliente");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato.resolucaoDiretoria");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato.cobrancaForma");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato.motivoDesfazer");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contrato.qtdPrestacoesDaRDEscolhida");
		
		return filtro;
		
	}
	
	private void removerValoresParcelas(HttpSession sessao, AtualizarContratoParcelamentoPorClienteActionForm form){

		sessao.removeAttribute("listaDeParcelas");
		sessao.removeAttribute("listaValoresDistintos");
		sessao.removeAttribute("listaDeParcelasPopUp");
		sessao.removeAttribute("listaValoresDistintosPopUp");
		
		sessao.removeAttribute("pacerlaAdd");
		sessao.removeAttribute("taxaJurosAdd");
		sessao.removeAttribute("numeroParcelasPopUp");
		sessao.removeAttribute("valorParcelTotal");
		form.setPacerlaAdd(null);
		form.setTaxaJurosAdd(null);
		form.setNumeroParcelasPopUp(null);
		
	}
	
	private void removerValoresListasParcelas(HttpSession sessao, AtualizarContratoParcelamentoPorClienteActionForm form){

		sessao.removeAttribute("listaDeParcelas");
		sessao.removeAttribute("listaValoresDistintos");
		sessao.removeAttribute("listaDeParcelasPopUp");
		sessao.removeAttribute("listaValoresDistintosPopUp");
		
		sessao.removeAttribute("valorParcelTotal");
		
	}
	
	private void removerListaDebitos(HttpSession sessao, AtualizarContratoParcelamentoPorClienteActionForm form){

		sessao.removeAttribute("contasSelecionadas");
		
		form.setContasSelecao(null);
		form.setContasSelecionadas(null);
		
		sessao.removeAttribute("debitosACobrarSelecionados");
		
		form.setDebitosSelecao(null);
		form.setDebitosACobrarSelecionados(null);
		
	}
	
	private void removerDebitos(HttpSession sessao, AtualizarContratoParcelamentoPorClienteActionForm form){

		sessao.removeAttribute("colecaoContaValores");
		sessao.removeAttribute("colecaoDebitoACobrar");
		sessao.removeAttribute("listaDebitos");
		sessao.removeAttribute("listaDebitosACobrar");
		
	}
	
	private void carregarObjetosSessao(HttpSession sessao, Usuario usuarioLogado) {

	    Fachada fachada = Fachada.getInstancia();
	    
	    // objetos utilizados na primeira etapa
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
        
        
        // objetos utilizados na terceira etapa
    	boolean permissaoEspecial = Fachada.getInstancia().verificarPermissaoEspecial(
				PermissaoEspecial.CONSULTAR_DEBITOS_ATUAL_DO_IMOVEL_OU_TODOS,
				usuarioLogado);
    	sessao.setAttribute("permissaoEspecial", permissaoEspecial);
        
    	boolean permissaoEspecialDebitoAcrescimos = Fachada.getInstancia().verificarPermissaoEspecial(
				PermissaoEspecial.PARCELAR_SEM_INCLUIR_ACRESCIMOS_POR_IMPONTUALIDADE,
				usuarioLogado);
    	sessao.setAttribute("permissaoEspecialDebitoAcrescimos", permissaoEspecialDebitoAcrescimos);
    	
    	if(sessao.getAttribute("collResolucoesDiretoria") == null){
			FiltroContratoParcelamentoRD filtro = new FiltroContratoParcelamentoRD(
					FiltroContratoParcelamentoRD.NUMERO);
			Collection<ContratoParcelamentoRD> resolucoesDiretoria = fachada.pesquisar(
					filtro, ContratoParcelamentoRD.class.getName());
			sessao.setAttribute("collResolucoesDiretoria", resolucoesDiretoria);
		}
		
		if(sessao.getAttribute("collFormaPgto") == null){
			FiltroCobrancaForma filtroCobranca = new FiltroCobrancaForma();
		    filtroCobranca.adicionarParametro(new ParametroSimples(FiltroCobrancaForma.IC_USO_CONTRATO_PARCEL_CLIENTE, "1"));
			Collection coll = fachada.pesquisar(filtroCobranca, CobrancaForma.class.getName()); 
			sessao.setAttribute("collFormaPgto", coll);
		}
	}

	/**
	 * Carrega os dados referentes à 1ª, 2ª e 3ª etapa
	 * */
	private void carregarDadosPrimeiraEtapa(HttpSession sessao, 
			AtualizarContratoParcelamentoPorClienteActionForm form, 
			HttpServletRequest httpServletRequest,
			String contratoAtualizarNumero,
			ContratoParcelamento contratoAtualizar){

	    Fachada fachada = Fachada.getInstancia();
		
		if (contratoAtualizarNumero == null || contratoAtualizarNumero.trim().equals("")) {
			contratoAtualizarNumero = contratoAtualizar.getNumero();
		}
		
		FiltroContratoParcelamentoCliente filtroClienteContratoSuperior = new FiltroContratoParcelamentoCliente();
		filtroClienteContratoSuperior = this.carregarFiltroContratoParcelamentoCliente(filtroClienteContratoSuperior);
		filtroClienteContratoSuperior.adicionarParametro(new ComparacaoTexto(
        		FiltroContratoParcelamentoCliente.NUMERO_CONTRATO, contratoAtualizarNumero));
		filtroClienteContratoSuperior.adicionarParametro(new ParametroSimples(
        		FiltroContratoParcelamentoCliente.INDICADOR_CLIENTE_SUPERIOR, ConstantesSistema.SIM));
        
        Collection collClienteContratoSuperior = fachada.pesquisar(
        		filtroClienteContratoSuperior, ContratoParcelamentoCliente.class.getName());
        
        // 1.6.	Cliente Superior
        // caso o contrato tenha sido implantado para um cliente superior
        if (collClienteContratoSuperior != null && !collClienteContratoSuperior.isEmpty()) {
            ContratoParcelamentoCliente contratoClienteAtualizar = (ContratoParcelamentoCliente) collClienteContratoSuperior.iterator().next();
            contratoAtualizar = (ContratoParcelamento) contratoClienteAtualizar.getContrato();
        	
            sessao.setAttribute("clienteSuperiorContrato", contratoClienteAtualizar);
			sessao.setAttribute("tipoConsulta", "clienteSuperior");
			sessao.setAttribute("clienteContrato", null);
            sessao.setAttribute("idClienteContratoConsultar", contratoClienteAtualizar.getId());
        } else {
        	// 1.8.	Cliente
        	// caso o contrato tenha sido implantado para um cliente
        	FiltroContratoParcelamentoCliente filtroClienteContrato = new FiltroContratoParcelamentoCliente();
        	filtroClienteContrato = this.carregarFiltroContratoParcelamentoCliente(filtroClienteContrato);
        	filtroClienteContrato.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamentoCliente.NUMERO_CONTRATO,contratoAtualizarNumero));
            
            Collection<ContratoParcelamentoCliente>  collClienteContrato = fachada.pesquisar(
            		filtroClienteContrato, ContratoParcelamentoCliente.class.getName());
            
            ContratoParcelamentoCliente contratoClienteAtualizar = (ContratoParcelamentoCliente) collClienteContrato.iterator().next();
            contratoAtualizar = (ContratoParcelamento) contratoClienteAtualizar.getContrato();
            
            sessao.setAttribute("clienteContrato", contratoClienteAtualizar);
        	sessao.setAttribute("tipoConsulta", "cliente"); 
        	sessao.setAttribute("clienteSuperiorContrato", null);
            sessao.setAttribute("idClienteContratoConsultar", contratoClienteAtualizar.getId());
        }
        
        sessao.setAttribute("contratoAtualizar", contratoAtualizar);
        sessao.setAttribute("contratoAtualizarBase", contratoAtualizar);

        this.carregarDadosSegundaEtapa(sessao, contratoAtualizar, form, httpServletRequest);
        
        List<Float> listaValoresDistintos = new ArrayList<Float>();
		FiltroPrestacaoContratoParcelamento filtroPrestacao = new FiltroPrestacaoContratoParcelamento();
		filtroPrestacao.adicionarParametro(new ParametroSimples(
				FiltroPrestacaoContratoParcelamento.CONTRATO_PARCELAMENTO_ID, contratoAtualizar.getId()));
		List<PrestacaoContratoParcelamento> listaDeParcelas = new ArrayList<PrestacaoContratoParcelamento>
				(fachada.pesquisar(filtroPrestacao, PrestacaoContratoParcelamento.class.getName()));

        Collections.sort(listaDeParcelas, new ComparatorPrestacaoContratoParcelamento());
        
		int indice = 0;
		for (PrestacaoContratoParcelamento prestacao : listaDeParcelas) {
			
			if(indice == 0){
				listaValoresDistintos.add(prestacao.getValor().floatValue());
			}else{
				if(prestacao.getValor().floatValue() != listaDeParcelas.get(indice-1).getValor().floatValue()){
					listaValoresDistintos.add(prestacao.getValor().floatValue());
				}
			}
			
			indice++;
		}
		
		if (contratoAtualizar.getNumeroPrestacoes() != null) {
			form.setPacerlaAdd(contratoAtualizar.getNumeroPrestacoes().toString());
		}
		
		if (contratoAtualizar.getTaxaJuros() != null && contratoAtualizar.getTaxaJuros().compareTo(BigDecimal.ZERO) > 0) {
			form.setTaxaJurosAdd(Util.formatarMoedaReal(contratoAtualizar.getTaxaJuros()));
		}
		
		sessao.setAttribute("listaDeParcelas", listaDeParcelas);
		sessao.setAttribute("listaValoresDistintos", listaValoresDistintos);
		
		this.carregarDadosTerceiraEtapa(sessao, form, httpServletRequest, contratoAtualizar, false, true, true);
		
		sessao.setAttribute("etapa", "primeira");
		
		if ((httpServletRequest.getParameter("method") != null
				&& httpServletRequest.getParameter("method")
					.toString().trim().equals("desfazerContrato"))
				|| (httpServletRequest.getParameter("contratoAtualizarNumero") != null
						&& !httpServletRequest.getParameter("contratoAtualizarNumero")
							.toString().trim().equals(""))) {
			if (form.getContasSelecao() != null && form.getContasSelecao().length > 0) {
				form.setSelecionouContas("sim");
			}
			
			if (form.getDebitosSelecao() != null && form.getDebitosSelecao().length > 0) {
				form.setSelecionouDebitosACobrar("sim");
			}
		}
	}
	
	/**
	 * Carrega os dados referentes à 2ª etapa
	 * */
	private void carregarDadosSegundaEtapa(HttpSession sessao,
			ContratoParcelamento contratoAtualizar,
			AtualizarContratoParcelamentoPorClienteActionForm form,
			HttpServletRequest httpServletRequest) {

	    Fachada fachada = Fachada.getInstancia();
	    
		Collection<ContratoParcelamentoItem> colecaoContaItens = (Collection<ContratoParcelamentoItem>) sessao.getAttribute("colecaoContaItens");
		if(colecaoContaItens == null){
			FiltroContratoParcelamentoItem filtroItem = new FiltroContratoParcelamentoItem();
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(
		    		FiltroContratoParcelamentoItem.CONTA_GERAL);
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(
		    		FiltroContratoParcelamentoItem.DOCUMENTO_TIPO);
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(
		    		FiltroContratoParcelamentoItem.CONTA_GERAL_CONTA);
		    filtroItem.adicionarCaminhoParaCarregamentoEntidade(
		    		FiltroContratoParcelamentoItem.CONTA_GERAL_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL);
		    filtroItem.adicionarParametro(new ParametroSimples(
		    		FiltroContratoParcelamentoItem.CONTRATO_ID, contratoAtualizar.getId()));
		    filtroItem.adicionarParametro(new ParametroSimples(
		    		FiltroContratoParcelamentoItem.DOCUMENTO_TIPO, DocumentoTipo.CONTA));
		    colecaoContaItens = fachada.pesquisar(filtroItem, ContratoParcelamentoItem.class.getName());
		}
		if (colecaoContaItens != null && !colecaoContaItens.isEmpty()) {
			// carrega as contas que já devem vir selecionadas
			String[] contasSelecionadas = new String[colecaoContaItens.size()];
			int indiceVez = 0;
			for (ContratoParcelamentoItem contratoParcelamentoItem : colecaoContaItens) {
				contasSelecionadas[indiceVez] = contratoParcelamentoItem.getContaGeral().getConta().getId().intValue()+"";
				indiceVez++;
			}
			form.setContasSelecao(contasSelecionadas);
		}
		
		Collection<ContratoParcelamentoItem> colecaoDebitosItens = (Collection<ContratoParcelamentoItem>) sessao.getAttribute("colecaoDebitosItens");
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
		
		if (colecaoDebitosItens != null && !colecaoDebitosItens.isEmpty()) {
			// carrega os débitos a cobrar que já devem vir selecionados
			String[] debitosSelecionadas = new String[colecaoDebitosItens.size()];
			int indiceVez = 0;
			for (ContratoParcelamentoItem contratoParcelamentoItem : colecaoDebitosItens) {
				debitosSelecionadas[indiceVez] = contratoParcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getId().intValue()+"";
				indiceVez++;
			}
			form.setDebitosSelecao(debitosSelecionadas);
		}
		
		calcularDebitosCliente(sessao, form, contratoAtualizar, true);
		sessao.setAttribute("finalizou", true);
	}
	
	/**
	 * Carrega os dados referentes à 3ª etapa
	 * */
	private void carregarDadosTerceiraEtapa(HttpSession sessao, 
			AtualizarContratoParcelamentoPorClienteActionForm form, 
			HttpServletRequest httpServletRequest,
			ContratoParcelamento contratoAtualizar,
			boolean pegarValoresDoForm, boolean icPrimeiraVez,
			boolean atualizarListasParcelas){
		
		List<PrestacaoContratoParcelamento> listaDeParcelas = 
			(List<PrestacaoContratoParcelamento>) sessao.getAttribute("listaDeParcelas");

		//if (!icPrimeiraVez) {
			this.carregaValoresContas(sessao, form, contratoAtualizar, pegarValoresDoForm, httpServletRequest, atualizarListasParcelas);
		//}
		
		Integer numeroParcelas = null;
		
		if (form.getPacerlaAdd() != null && !form.getPacerlaAdd().equals("")) {
			numeroParcelas = new Integer(form.getPacerlaAdd());
		} else if (form.getNumeroParcelasPopUp() != null && !form.getNumeroParcelasPopUp().equals("")) {
			numeroParcelas = new Integer(form.getNumeroParcelasPopUp());
		} 
		
		if(numeroParcelas != null){
			
			//[FS0029]
			if(contratoAtualizar.getResolucaoDiretoria() != null){
				verificaNumeroParcelasComRD(listaDeParcelas, contratoAtualizar.getResolucaoDiretoria(),
						form, sessao, "terceira", numeroParcelas);
			}else{
				verificaNumeroParcelasSemRD(listaDeParcelas, form, sessao, "terceira");
			}

			List<Float> listaValoresDistintos = (List<Float>) sessao.getAttribute("listaValoresDistintos");
			List<QuantidadePrestacoesRDHelper> colecaoQuantidadePrestacoesRDHelper = (List<QuantidadePrestacoesRDHelper>) sessao.getAttribute("colecaoQuantidadePrestacoesRDHelper");
			
			if (atualizarListasParcelas 
					&& (listaValoresDistintos == null || listaValoresDistintos.size() <= 2)
					&& (contratoAtualizar.getResolucaoDiretoria() == null
							|| contratoAtualizar.getResolucaoDiretoria().getId() == null)
							|| ((colecaoQuantidadePrestacoesRDHelper == null || colecaoQuantidadePrestacoesRDHelper.isEmpty()) && !icPrimeiraVez)) {
				calcularListaParcelasDiretoTela(form, sessao, contratoAtualizar);
			}
			
		} else {
			sessao.removeAttribute("listaDeParcelas");
			sessao.removeAttribute("listaValoresDistintos");
		}
			
		this.carregaValoresContas(sessao, form, contratoAtualizar, pegarValoresDoForm, httpServletRequest, atualizarListasParcelas);
		
	}
	
	private void carregaValoresContas(HttpSession sessao, 
			AtualizarContratoParcelamentoPorClienteActionForm form,
			ContratoParcelamento contratoAtualizar,
			boolean pegarValoresDoForm,
			HttpServletRequest httpServletRequest,
			boolean atualizarListasParcelas){

		if (!(httpServletRequest.getParameter("method") != null
				&& httpServletRequest.getParameter("method")
					.toString().trim().equals("desfazerContrato"))
				&& !(httpServletRequest.getParameter("contratoAtualizarNumero") != null
						&& !httpServletRequest.getParameter("contratoAtualizarNumero")
							.toString().trim().equals(""))) {
	        
			if(form.getSelecionouContas() == null || !form.getSelecionouContas().trim().equalsIgnoreCase("sim")) {
		    	sessao.removeAttribute("contasSelecionadas");
		    	
		    	form.setContasSelecao(null);
		    	form.setContasSelecionadas(null);
		    }
		    
		    if(form.getSelecionouDebitosACobrar() == null || !form.getSelecionouDebitosACobrar().trim().equalsIgnoreCase("sim")) {
		    	sessao.removeAttribute("debitosACobrarSelecionados");
		    	
		    	form.setDebitosSelecao(null);
		    	form.setDebitosACobrarSelecionados(null);
		    }
		    
		}
	    
		BigDecimal valorConta = BigDecimal.ZERO;
		BigDecimal valorAcresc = BigDecimal.ZERO;
		BigDecimal valorDebitosACobrar = BigDecimal.ZERO;

		//2.4. Número de Parcelas 
		Integer numeroParcelasPopup = new Integer(0);
		
		if(form.getContasSelecao() != null && form.getContasSelecao().length > 0){
			
			Collection<ContaValoresHelper> colecaoContaValores = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
			
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
				form.setContasSelecionadas(this.retornarContasDebitosACobrarSelecionados(listaDebitos));
				sessao.setAttribute("contasSelecionadas",form.getContasSelecionadas());

			}
				
		}else{
			sessao.removeAttribute("listaDebitos");
			sessao.setAttribute("valorContasSelecao",BigDecimal.ZERO);
			sessao.setAttribute("valorAcrescSelecao",BigDecimal.ZERO);
			sessao.setAttribute("valorContaComAcrescimoSelecao",BigDecimal.ZERO);
			form.setContasSelecionadas(null);
			sessao.removeAttribute("contasSelecionadas");
		}
		
		if(form.getDebitosSelecao() != null && form.getDebitosSelecao().length > 0){
			
			Collection<DebitoACobrar> colecaoDebitoACobrarTotal = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");

			List<DebitosClienteHelper> listaDebitosACobrar = new ArrayList<DebitosClienteHelper>();
				
			if(colecaoDebitoACobrarTotal != null){
				for (DebitoACobrar debitoACobrar : colecaoDebitoACobrarTotal) {
					if(form.verificaDebitoACobrarSelecionada(debitoACobrar.getId().intValue())){
						DebitosClienteHelper debito = new DebitosClienteHelper();
						debito.setIdentificadorItem(debitoACobrar.getId());
						debito.setTipoDocumento(DocumentoTipo.DEBITO_A_COBRAR);
						debito.setValorItem(debitoACobrar.getValorTotal());
						debito.setValorAcrescImpont(null);
						listaDebitosACobrar.add(debito);
						valorConta = valorConta.add(debitoACobrar.getValorTotal());
						valorDebitosACobrar = valorDebitosACobrar.add(debitoACobrar.getValorTotal());
					}
				}			
				
				sessao.setAttribute("listaDebitosACobrar",listaDebitosACobrar);
				form.setDebitosACobrarSelecionados(this.retornarContasDebitosACobrarSelecionados(listaDebitosACobrar));
				sessao.setAttribute("debitosACobrarSelecionados",form.getDebitosACobrarSelecionados());
				sessao.setAttribute("valorDebitosACobrarSelecao",valorDebitosACobrar);

			}
		}else{
			sessao.removeAttribute("listaDebitosACobrar");
			form.setDebitosACobrarSelecionados(null);
			sessao.removeAttribute("debitosACobrarSelecionados");
			sessao.setAttribute("valorDebitosACobrarSelecao",BigDecimal.ZERO);
		}
		
		if((form.getContasSelecao() != null && form.getContasSelecao().length > 0)
				|| (form.getDebitosSelecao() != null && form.getDebitosSelecao().length > 0)){
			
			List<Float> listaValoresDistintos = (List<Float>) sessao.getAttribute("listaValoresDistintos");
			
			Integer numPrest = null;
			BigDecimal juros = null;
			Short icDebitosAcrescimos = null;
			
			if(pegarValoresDoForm) {
				
				if (form.getPacerlaAdd() != null
						&& !form.getPacerlaAdd().equals("")) {
					numPrest = new Integer(form.getPacerlaAdd());
					contratoAtualizar.setNumeroPrestacoes(numPrest);
				} else if (form.getNumeroParcelasPopUp() != null
						&& !form.getNumeroParcelasPopUp().equals("")) {
					numPrest = new Integer(form.getNumeroParcelasPopUp());
				} 
				
				if (form.getTaxaJurosAdd() != null
						&& !form.getTaxaJurosAdd().equals("")) {
					juros = new BigDecimal(form.getTaxaJurosAdd().replace(".", ",").replace(",", "."));
					contratoAtualizar.setTaxaJuros(juros);
				} 
				
				if (form.getIndicadorDebitoAcresc() != null
						&& !form.getIndicadorDebitoAcresc().equals("")) {
					icDebitosAcrescimos = new Short(form.getIndicadorDebitoAcresc());
				}
				
				contratoAtualizar.setIndicadorDebitosAcrescimos(icDebitosAcrescimos);
				
			} else  {
				
				if (contratoAtualizar.getNumeroPrestacoes() != null) {
					numPrest = contratoAtualizar.getNumeroPrestacoes();
				}
				if (contratoAtualizar.getTaxaJuros() != null) {
					juros = contratoAtualizar.getTaxaJuros();
					form.setTaxaJurosAdd(Util.formatarMoedaReal(juros));
				} 
				if (contratoAtualizar.getIndicadorDebitosAcrescimos() != null){
					icDebitosAcrescimos = contratoAtualizar.getIndicadorDebitosAcrescimos();
					form.setIndicadorDebitoAcresc(icDebitosAcrescimos.toString());
				}
			}
			
			// caso a lista de parcelas não esteja vazia
			if (numPrest != null) {
				
				// atribuir o campo número de parcelas da lista ao campo "Número de Parcelas"
				numeroParcelasPopup = numPrest;
				
				String numeroParcelasPopUp = null;
				if(sessao.getAttribute("numeroParcelasPopUp") != null
						&& !sessao.getAttribute("numeroParcelasPopUp").toString().trim().equals("")){
					numeroParcelasPopUp = sessao.getAttribute("numeroParcelasPopUp").toString();
				}
				
				if ((numeroParcelasPopUp != null && !numeroParcelasPopUp.equals(""))
						|| (listaValoresDistintos != null && listaValoresDistintos.size() > 2)) {
	
					form.setNumeroParcelasPopUp(numeroParcelasPopup.toString());
					sessao.setAttribute("numeroParcelasPopUp", numeroParcelasPopup);
					
					form.setPacerlaAdd(null);
					form.setTaxaJurosAdd(null);
					sessao.removeAttribute("pacerlaAdd");
					sessao.removeAttribute("taxaJurosAdd");
					
				} else {
	
					form.setPacerlaAdd(numeroParcelasPopup.toString());
					sessao.setAttribute("pacerlaAdd", numeroParcelasPopup);
					
					form.setNumeroParcelasPopUp(null);
					sessao.removeAttribute("numeroParcelasPopUp");
				}
				
				if ((sessao.getAttribute("listaDeParcelasPopUp") == null
						|| sessao.getAttribute("listaDeParcelasPopUp").equals(""))
						&& sessao.getAttribute("listaDeParcelas") != null
						&& !sessao.getAttribute("listaDeParcelas").equals("")) {
				
					List<PrestacaoContratoParcelamento> listaDeParcelasPopUp = (ArrayList) sessao.getAttribute("listaDeParcelas");
			        List<Float> listaValoresDistintosPopUp = new ArrayList<Float>();
					
			        Collections.sort(listaDeParcelasPopUp, new ComparatorPrestacaoContratoParcelamento());
			        
					int indice = 0;
					
					for (PrestacaoContratoParcelamento prestacao : listaDeParcelasPopUp) {
						
						if(indice == 0){
							listaValoresDistintosPopUp.add(prestacao.getValor().floatValue());
						}else{
							if(prestacao.getValor().floatValue() != listaDeParcelasPopUp.get(indice-1).getValor().floatValue()){
								listaValoresDistintosPopUp.add(prestacao.getValor().floatValue());
							}
						}
						
						indice++;
					}
					
					
					BigDecimal valorParcelTotal = new BigDecimal(0);
					for (PrestacaoContratoParcelamento prestacaoContratoParcelamento : listaDeParcelasPopUp) {
						valorParcelTotal = valorParcelTotal.add(prestacaoContratoParcelamento.getValor());
					}
					
					if (atualizarListasParcelas) {
						if (form.getPacerlaAdd() == null || form.getPacerlaAdd().toString().trim().equals("")) {
							sessao.setAttribute("listaDeParcelasPopUp", listaDeParcelasPopUp);
							sessao.setAttribute("listaValoresDistintosPopUp", listaValoresDistintosPopUp);
						} 
						sessao.setAttribute("listaDeParcelas", listaDeParcelasPopUp);
						sessao.setAttribute("listaValoresDistintos", listaValoresDistintosPopUp);
					}
					
					sessao.setAttribute("valorParcelTotal", valorParcelTotal);
					
				}
				
			}
			
			// 2.3.	Valor Parcelado
			BigDecimal valorParcelado = BigDecimal.ZERO;
			
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
			
			if (valorContaSelecao != null && numeroParcelas != null) {
				if (juros == null) {
					juros = BigDecimal.ZERO;
				}
				
				InserirContratoParcelamentoValoresParcelasHelper helper = Fachada.getInstancia()
					.calcularValoresParcelasContratoParcelamento(valorContaSelecao, acrescimo, 
						contratoAtualizar.getIndicadorDebitosAcrescimos().toString(), 
						contratoAtualizar.getIndicadorParcelamentoJuros().toString(), 
						juros, 1, numeroParcelas);
				
				valorParcelado = helper.getValorParcelado();
			}
			
			contratoAtualizar.setValorTotalParcelado(valorParcelado);
			sessao.setAttribute("valorParceladoPopUp",Util.formatarMoedaReal(valorParcelado));
			form.setValorParceladoPopUp(Util.formatarMoedaReal(valorParcelado));
			sessao.setAttribute("valorContaSelecao",valorConta);
			sessao.setAttribute("valorAcrescimoSelecao",valorAcresc);
			sessao.setAttribute("valorContaAcrescimoSelecao",valorAcresc.add(valorConta));
		} else {
			contratoAtualizar.setValorTotalParcelado(null);
			form.setValorParceladoPopUp(null);
			sessao.removeAttribute("valorContaSelecao");
			sessao.removeAttribute("valorAcrescimoSelecao");
			sessao.removeAttribute("valorContaAcrescimoSelecao");
			sessao.removeAttribute("valorParceladoPopUp");
		}
		
	}
	
	/**
	 * Método que verifica se algum dos campos da 1ª aba que influenciam nos valores da 3ª aba foram alterados
	 * */
	private boolean verificarCampoAlteradoPrimeiraEtapa(HttpSession sessao, 
			AtualizarContratoParcelamentoPorClienteActionForm form,
			ContratoParcelamento contratoAtualizar){
		boolean retorno = false;
		
		Fachada fachada = Fachada.getInstancia();
		
		if(sessao.getAttribute("contratoAtualizarBase") != null
				&& !sessao.getAttribute("contratoAtualizarBase").toString().trim().equals("")) {
			
			ContratoParcelamento contratoAtualizarBase = (ContratoParcelamento) sessao.getAttribute("contratoAtualizarBase");
			
			Collection colecaoContratoParcelamentoClienteSuperior = fachada.pesquisarClienteContrato(
					contratoAtualizarBase.getId(),  ConstantesSistema.SIM);
			ContratoParcelamentoCliente clienteSuperiorContrato = (ContratoParcelamentoCliente) 
					Util.retonarObjetoDeColecao(colecaoContratoParcelamentoClienteSuperior);

			Collection colecaoContratoParcelamentoCliente = fachada.pesquisarClienteContrato(
					contratoAtualizarBase.getId(),  ConstantesSistema.NAO);

			ArrayList<Integer> idsClienteContrato = new ArrayList<Integer>();
			if (colecaoContratoParcelamentoCliente != null && !colecaoContratoParcelamentoCliente.isEmpty()) {
				Iterator iterator = colecaoContratoParcelamentoCliente.iterator();
				while(iterator.hasNext()) {
					idsClienteContrato.add(((ContratoParcelamentoCliente)iterator.next()).getCliente().getId());
				}
			}
			
			if ((contratoAtualizarBase.getContratoAnterior() == null
					&& form.getNumeroContratoAnt() != null
					&& !form.getNumeroContratoAnt().toString().trim().equals(""))
					|| (contratoAtualizarBase.getContratoAnterior() != null
							&& contratoAtualizarBase.getContratoAnterior().getNumero() != null
							&& !contratoAtualizarBase.getContratoAnterior().getNumero().toString().trim().equals("")
							&& form.getNumeroContratoAnt() == null)) {
				// No. Contrato Anterior
				retorno = true;
				
			} else if (contratoAtualizarBase.getContratoAnterior() != null
					&& form.getNumeroContratoAnt() != null
					&& !compararStrings(contratoAtualizarBase.getContratoAnterior().getNumero(),
							form.getNumeroContratoAnt())){
				// No. Contrato Anterior
				retorno = true;
				
			} else if ((form.getRelacaoAnterior() == null || form.getRelacaoAnterior().equals(""))
					&& (contratoAtualizarBase.getRelacaoAnterior() != null
							&& contratoAtualizarBase.getRelacaoAnterior().getId() != null)){
				// Tipo Relação (Contrato Anterior)
				retorno = true;
				
			} else if ((form.getRelacaoAnterior() != null && !form.getRelacaoAnterior().equals(""))
					&& (contratoAtualizarBase.getRelacaoAnterior() == null
							|| contratoAtualizarBase.getRelacaoAnterior().getId() == null)){
				// Tipo Relação (Contrato Anterior)
				retorno = true;
				
			} else if (contratoAtualizarBase.getRelacaoAnterior() != null
					&& contratoAtualizarBase.getRelacaoAnterior().getId() != null
					&& form.getRelacaoAnterior() != null
					&& !compararStrings(contratoAtualizarBase.getRelacaoAnterior().getId().toString(),
					form.getRelacaoAnterior())) {
				// Tipo Relação (Contrato Anterior)
				retorno = true;
				
			} else if ((form.getDataContrato() == null || form.getDataContrato().equals(""))
					&& (contratoAtualizarBase.getDataContrato() != null)){
				// Data do Contrato
				retorno = true;
				
			} else if ((form.getDataContrato() != null && !form.getDataContrato().equals(""))
					&& (contratoAtualizarBase.getDataContrato() == null)){
				// Data do Contrato
				retorno = true;
				
			}else if (form.getDataContrato() != null
					&& !form.getDataContrato().equals("")
					&& contratoAtualizarBase.getDataContrato() != null
					&& !Util.datasIguais(contratoAtualizarBase.getDataContrato(),
							Util.converteStringParaDate(form.getDataContrato()))){
				// Data do Contrato
				retorno = true;
				
			} else if ((form.getLoginUsuario() == null || form.getLoginUsuario().equals(""))
					&& (contratoAtualizarBase.getUsuarioResponsavel() != null
							&& contratoAtualizarBase.getUsuarioResponsavel().getId() != null)){
				// Usuário Responsável
				retorno = true;
				
			} else if ((form.getLoginUsuario() != null && !form.getLoginUsuario().equals(""))
					&& (contratoAtualizarBase.getUsuarioResponsavel() == null
							|| contratoAtualizarBase.getUsuarioResponsavel().getId() == null)){
				// Usuário Responsável
				retorno = true;
				
			} else if (form.getLoginUsuario() != null 
					&& !form.getLoginUsuario().equals("")
					&& contratoAtualizarBase.getUsuarioResponsavel() != null
					&& contratoAtualizarBase.getUsuarioResponsavel().getId() != null
					&& !compararStrings(contratoAtualizarBase.getUsuarioResponsavel().getLogin(),
						form.getLoginUsuario())) {
				// Usuário Responsável
				retorno = true;
				
			} else if ((form.getAutocompleteClienteSuperior() == null || form.getAutocompleteClienteSuperior().equals(""))
					&& (clienteSuperiorContrato != null 
							&& clienteSuperiorContrato.getCliente() != null
							&& clienteSuperiorContrato.getId() != null)){
				// Cliente Superior
				retorno = true;
				
			} else if ((form.getAutocompleteClienteSuperior() != null && !form.getAutocompleteClienteSuperior().equals(""))
					&& (clienteSuperiorContrato == null 
							|| clienteSuperiorContrato.getCliente() == null
							|| clienteSuperiorContrato.getCliente().getId() == null)){
				// Cliente Superior
				retorno = true;
				
			} else if (form.getAutocompleteClienteSuperior() != null 
					&& !form.getAutocompleteClienteSuperior().equals("")
					&& clienteSuperiorContrato != null 
					&& clienteSuperiorContrato.getCliente() != null
					&& clienteSuperiorContrato.getId() != null
					&& !compararStrings(clienteSuperiorContrato.getCliente().getId() 
						+ " - " + clienteSuperiorContrato.getCliente().getNome(),
						form.getAutocompleteClienteSuperior())) {
				// Cliente Superior
				retorno = true;
				
			} else if ((form.getAutocompleteCliente() == null || form.getAutocompleteCliente().equals(""))
					&& (idsClienteContrato != null 
							&& idsClienteContrato.size() > 0)
							&& (clienteSuperiorContrato == null 
								|| clienteSuperiorContrato.getCliente() == null
								|| clienteSuperiorContrato.getId() == null)){
				// Cliente Superior
				retorno = true;
				
			} else if ((form.getAutocompleteCliente() != null && !form.getAutocompleteCliente().equals(""))
					&& (clienteSuperiorContrato == null 
							|| clienteSuperiorContrato.getCliente() == null
							|| clienteSuperiorContrato.getCliente().getId() == null)){
				// Cliente Superior
				retorno = true;
				
			} else if (form.getAutocompleteCliente() != null 
					&& !form.getAutocompleteCliente().equals("")
					&& clienteSuperiorContrato != null 
					&& clienteSuperiorContrato.getCliente() != null
					&& clienteSuperiorContrato.getId() != null
					&& !compararStrings(clienteSuperiorContrato.getCliente().getId() 
						+ " - " + clienteSuperiorContrato.getCliente().getNome(),
						form.getAutocompleteCliente())) {
				// Cliente Superior
				retorno = true;
				
			} else if ((form.getRelacaoCliente() == null || form.getRelacaoCliente().equals(""))
					&& (contratoAtualizarBase.getRelacaoCliente() != null
							&& contratoAtualizarBase.getRelacaoCliente().getId() != null)){
				// Tipo da Relação (Cliente)
				retorno = true;
				
			} else if ((form.getRelacaoCliente() != null && !form.getRelacaoCliente().equals(""))
					&& (contratoAtualizarBase.getRelacaoCliente() == null
							|| contratoAtualizarBase.getRelacaoCliente().getId() == null)){
				// Tipo da Relação (Cliente)
				retorno = true;
				
			} else if (contratoAtualizarBase.getRelacaoCliente() != null
					&& contratoAtualizarBase.getRelacaoCliente().getId() != null
					&& form.getRelacaoCliente() != null
					&& !compararStrings(contratoAtualizarBase.getRelacaoCliente().getId().toString(),
					form.getRelacaoCliente())) {
				// Tipo da Relação (Cliente)
				retorno = true;
				
			} else if (!compararStrings(contratoAtualizarBase.getAnoMesDebitoInicioFormatado(),
					form.getAnoMesDebitoInicio())){
				// Período de Referência do Débito (Inicial)
				retorno = true;
				
			} else if (!compararStrings(contratoAtualizarBase.getAnoMesDebitoFinalFormatado(),
					form.getAnoMesDebitoFinal())){
				// Período de Referência do Débito (Final)
				retorno = true;
				
			} else if (!compararStrings(contratoAtualizarBase.getDataVencimentoInicioFormatada(),
					form.getDataVencimentoInicio())){
				// Período de Vencimento do Débito (Inicial)
				retorno = true;
				
			} else if (!compararStrings(contratoAtualizarBase.getDataVencimentoFinalFormatada(),
					form.getDataVencimentoFinal())){
				// Período de Vencimento do Débito (Final)
				retorno = true;
				
			} else if ((form.getObservacao() == null || form.getObservacao().equals(""))
					&& (contratoAtualizarBase.getObservacao() != null
							&& !contratoAtualizarBase.getObservacao().equals(""))){
				// Observação
				retorno = true;
				
			} else if ((form.getObservacao() != null && !form.getObservacao().equals(""))
					&& (contratoAtualizarBase.getObservacao() == null
							|| contratoAtualizarBase.getObservacao().equals(""))){
				// Observação
				retorno = true;
				
			} else if (form.getObservacao() != null && !form.getObservacao().equals("") 
					&& contratoAtualizarBase.getObservacao() != null
					&& !contratoAtualizarBase.getObservacao().equals("")
					&& !compararStrings(contratoAtualizarBase.getObservacao(),
					form.getObservacao())){
				// Observação
				retorno = true;
				
			}
			
		}
		
		if (retorno) {
			sessao.setAttribute("alterouCamposPrimeiraEtapa", "sim");
		}
		
		return retorno;
	}
	
	/**
	 * Método que verifica se algum dos campos da 2ª aba que influenciam nos valores da 3ª aba foram alterados
	 * */
	private boolean verificarCampoAlteradoSegundaEtapa(HttpSession sessao, 
			AtualizarContratoParcelamentoPorClienteActionForm form){
		boolean retorno = false;
		
		List<DebitosClienteHelper> listaDebitos = (List<DebitosClienteHelper>) sessao.getAttribute("listaDebitos");
		List<DebitosClienteHelper> listaDebitosACobrar = (List<DebitosClienteHelper>) sessao.getAttribute("listaDebitosACobrar");
		
		String[] contasSelecionadas = form.getContasSelecao();
		String[] debitosSelecionados = form.getDebitosSelecao();
		

		if (listaDebitos != null && !listaDebitos.isEmpty()
				&& (contasSelecionadas == null || contasSelecionadas.length < 1)) {
			retorno = true;
		} else if ((listaDebitos == null || listaDebitos.isEmpty())
				&& contasSelecionadas != null && contasSelecionadas.length > 0) {
			retorno = true;
		} else if (listaDebitos != null && !listaDebitos.isEmpty()
				&& contasSelecionadas != null && contasSelecionadas.length > 0
				&& listaDebitos.size() != contasSelecionadas.length){
			retorno = true;
		} else if (listaDebitos != null && !listaDebitos.isEmpty()) {
			Iterator iterator = listaDebitos.iterator();
			
			ArrayList<Integer> contas = new ArrayList<Integer>();
			for (int i = 0; i < contasSelecionadas.length; i++) {
				contas.add(new Integer(contasSelecionadas[i]));
			}
			
			while(iterator.hasNext()) {
				DebitosClienteHelper helper = (DebitosClienteHelper) iterator.next();
				
				if (!contas.contains(helper.getIdentificadorItem())) {
					retorno = true;
				}
			}
		} 
		
		if (!retorno) {
			if (listaDebitosACobrar != null && !listaDebitosACobrar.isEmpty()
					&& (debitosSelecionados == null || debitosSelecionados.length < 1)) {
				retorno = true;
			} else if ((listaDebitosACobrar == null || listaDebitosACobrar.isEmpty())
					&& debitosSelecionados != null && debitosSelecionados.length > 0) {
				retorno = true;
			} else if (listaDebitosACobrar != null && !listaDebitosACobrar.isEmpty()
					&& debitosSelecionados != null && debitosSelecionados.length > 0
					&& listaDebitosACobrar.size() != debitosSelecionados.length){
				retorno = true;
			} else if (listaDebitosACobrar != null && !listaDebitosACobrar.isEmpty()) {
				Iterator iterator = listaDebitosACobrar.iterator();
				
				ArrayList<Integer> debitosACobrar = new ArrayList<Integer>();
				for (int i = 0; i < debitosSelecionados.length; i++) {
					debitosACobrar.add(new Integer(debitosSelecionados[i]));
				}
				
				while(iterator.hasNext()) {
					DebitosClienteHelper helper = (DebitosClienteHelper) iterator.next();
					
					if (!debitosACobrar.contains(helper.getIdentificadorItem())) {
						retorno = true;
					}
				}
			}
		}
		
		if (retorno) {
			sessao.setAttribute("alterouCamposSegundaEtapa", "sim");
		}
		
		return retorno;
	}
	
	private boolean compararStrings(String string01, String string02) {
		if ((string01 == null || string01.equals(""))
				&& (string02 != null && !string02.equals(""))) {
			return false;
		} 
		if ((string01 != null && !string01.equals(""))
				&& (string02 == null || string02.equals(""))) {
			return false;
		}
		if (!(string01.trim().equals(
				string02.trim()))){
			return false;
		}
		
		return true;
	}
	
	private void apagarDadosSessao(HttpSession sessao, 
			AtualizarContratoParcelamentoPorClienteActionForm form){
		
		this.removerValoresParcelas(sessao, form);
		
		sessao.removeAttribute("indicadorTela");
		sessao.removeAttribute("escolheuRD");
		sessao.removeAttribute("tipoConsulta");
		
		sessao.removeAttribute("clienteContrato");
		sessao.removeAttribute("clienteSuperiorContrato");
		
		sessao.removeAttribute("contasSelecionadas");
		sessao.removeAttribute("listaDebitos");
		sessao.removeAttribute("colecaoQuantidadePrestacoesRDHelper");
		
		sessao.removeAttribute("valorContaSelecao");
		sessao.removeAttribute("valorAcrescimoSelecao");
		sessao.removeAttribute("valorContaAcrescimoSelecao");
		sessao.removeAttribute("valorParceladoPopUp");
		
		sessao.removeAttribute("colecaoContaValores");
		sessao.removeAttribute("colecaoDebitoACobrar");

		sessao.removeAttribute("valorConta");
		sessao.removeAttribute("valorAcrescimo");
		sessao.removeAttribute("valorAgua");
		sessao.removeAttribute("valorEsgoto");

		sessao.removeAttribute("alterouRD");
		sessao.removeAttribute("alterouCamposPrimeiraEtapa");
		sessao.removeAttribute("alterouCamposSegundaEtapa");
		sessao.removeAttribute("ultimaRD");
	}

	/**
	 * Retorna um objeto de contrato de parcelamento a partir dos valores do form
	 * */
	private ContratoParcelamento getContrato(HttpSession sessao,
			AtualizarContratoParcelamentoPorClienteActionForm form){
		
		ContratoParcelamento contratoParcelamento = (ContratoParcelamento) sessao.getAttribute("contratoAtualizar");
		Fachada fachada = Fachada.getInstancia();
		
		// 1ª aba
		if (form.getNumeroContrato() != null
				&& !form.getNumeroContrato().equals("")) {
			contratoParcelamento.setNumero(form.getNumeroContrato());
		} else {
			contratoParcelamento.setNumero(null);
		}
		
		if (form.getNumeroContratoAnt() != null
				&& !form.getNumeroContratoAnt().equals("")) {
			
			ContratoParcelamento contratoAnterior = fachada.
					pesquisarContratoParcelamentoCompleto(null, form.getNumeroContratoAnt());
	
			if (contratoAnterior != null) {
				contratoParcelamento.setContratoAnterior(contratoAnterior);
			} else {
				contratoParcelamento.setContratoAnterior(null);
			}
		} else {
			contratoParcelamento.setContratoAnterior(null);
		}

		if (form.getRelacaoAnterior() != null
				&& !form.getRelacaoAnterior().equals("")) {
			
			FiltroTipoRelacao filtroTipoRelacao = new FiltroTipoRelacao();
			filtroTipoRelacao.adicionarParametro(new ParametroSimples(
					FiltroTipoRelacao.ID, form.getRelacaoAnterior()));
			Collection colecaoTipoRelacao = fachada.pesquisar(filtroTipoRelacao, TipoRelacao.class.getName());
			
			if (colecaoTipoRelacao != null && !colecaoTipoRelacao.isEmpty()) {
				contratoParcelamento.setRelacaoAnterior(
						(TipoRelacao) Util.retonarObjetoDeColecao(colecaoTipoRelacao));
			} else {
				contratoParcelamento.setRelacaoAnterior(null);
			}
		} else {
			contratoParcelamento.setRelacaoAnterior(null);
		}
		
		if (form.getDataContrato() != null
				&& !form.getDataContrato().equals("")) {
			contratoParcelamento.setDataContrato(
					Util.converteStringParaDate(form.getDataContrato()));
		} else {
			contratoParcelamento.setDataContrato(null);
		}
		
		if (form.getRelacaoCliente() != null
				&& !form.getRelacaoCliente().equals("")) {
			
			FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
			filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID, form.getRelacaoCliente()));
			Collection colecaoClienteRelacaoTipo = fachada.pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());
			
			if (colecaoClienteRelacaoTipo != null && !colecaoClienteRelacaoTipo.isEmpty()) {
				contratoParcelamento.setRelacaoCliente(
						(ClienteRelacaoTipo) Util.retonarObjetoDeColecao(colecaoClienteRelacaoTipo));
			} else {
				contratoParcelamento.setRelacaoCliente(null);
			}
		} else {
			contratoParcelamento.setRelacaoCliente(null);
		}
		
		if (form.getAnoMesDebitoInicio() != null
				&& !form.getAnoMesDebitoInicio().equals("")) {
			contratoParcelamento.setAnoMesDebitoInicio(
					Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesDebitoInicio()));
		} else {
			contratoParcelamento.setAnoMesDebitoInicio(null);
		}
		if (form.getAnoMesDebitoFinal() != null
				&& !form.getAnoMesDebitoFinal().equals("")) {
			contratoParcelamento.setAnoMesDebitoFinal(
					Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesDebitoFinal()));
		} else {
			contratoParcelamento.setAnoMesDebitoFinal(null);
		}
		
		if (form.getDataVencimentoInicio() != null
				&& !form.getDataVencimentoInicio().equals("")) {
			contratoParcelamento.setDataVencimentoInicio(
					Util.converteStringParaDate(form.getDataVencimentoInicio()));
		} else {
			contratoParcelamento.setDataVencimentoInicio(null);
		}
		if (form.getDataVencimentoFinal() != null
				&& !form.getDataVencimentoFinal().equals("")) {
			contratoParcelamento.setDataVencimentoFinal(
					Util.converteStringParaDate(form.getDataVencimentoFinal()));
		} else {
			contratoParcelamento.setDataVencimentoFinal(null);
		}
		
		if (form.getObservacao() != null
				&& !form.getObservacao().equals("")) {
			contratoParcelamento.setObservacao(form.getObservacao());
		} else {
			contratoParcelamento.setObservacao(null);
		}
		
		//3ª aba
		if (form.getResolucaoDiretoria() != null
				&& !form.getResolucaoDiretoria().equals("")) {
			
			FiltroContratoParcelamentoRD filtroContratoParcelamentoRD = new FiltroContratoParcelamentoRD();
			filtroContratoParcelamentoRD.adicionarParametro(new ParametroSimples(
					FiltroContratoParcelamentoRD.NUMERO, form.getResolucaoDiretoria()));
			Collection colecaoContratoParcelamentoRD = fachada.pesquisar(filtroContratoParcelamentoRD, ContratoParcelamentoRD.class.getName());
			
			if (colecaoContratoParcelamentoRD != null && !colecaoContratoParcelamentoRD.isEmpty()) {
				contratoParcelamento.setResolucaoDiretoria(
						(ContratoParcelamentoRD) Util.retonarObjetoDeColecao(colecaoContratoParcelamentoRD));
			} else {
				contratoParcelamento.setResolucaoDiretoria(null);
			}
		} else {
			contratoParcelamento.setResolucaoDiretoria(null);
		}

		if (form.getIndicadorDebitoAcresc() != null
				&& !form.getIndicadorDebitoAcresc().equals("")) {
			contratoParcelamento.setIndicadorDebitosAcrescimos(
					new Short(form.getIndicadorDebitoAcresc()));
		} else {
			contratoParcelamento.setIndicadorDebitosAcrescimos(null);
		}

		if (form.getIndicadorParcelJuros() != null
				&& !form.getIndicadorParcelJuros().equals("")) {
			contratoParcelamento.setIndicadorParcelamentoJuros(
					new Short(form.getIndicadorParcelJuros()));
		} else {
			contratoParcelamento.setIndicadorParcelamentoJuros(null);
		}

		if (form.getIndicadorInfoVlParcel() != null
				&& !form.getIndicadorInfoVlParcel().equals("")) {
			contratoParcelamento.setIndicadorPermiteInformarValorParcel(
					new Short(form.getIndicadorInfoVlParcel()));
		} else {
			contratoParcelamento.setIndicadorPermiteInformarValorParcel(null);
		}
		
		if (form.getFormaPgto() != null
				&& !form.getFormaPgto().equals("")) {
			
			FiltroCobrancaForma filtroCobrancaForma = new FiltroCobrancaForma();
			filtroCobrancaForma.adicionarParametro(new ParametroSimples(
					FiltroCobrancaForma.ID, form.getFormaPgto()));
			Collection colecaoCobrancaForma = fachada.pesquisar(filtroCobrancaForma, CobrancaForma.class.getName());
			
			if (colecaoCobrancaForma != null && !colecaoCobrancaForma.isEmpty()) {
				contratoParcelamento.setCobrancaForma(
						(CobrancaForma) Util.retonarObjetoDeColecao(colecaoCobrancaForma));
			} else {
				contratoParcelamento.setCobrancaForma(null);
			}
		} else {
			contratoParcelamento.setCobrancaForma(null);
		}
		
		if (form.getDataVencimentoPrimParcela() != null
				&& !form.getDataVencimentoPrimParcela().equals("")) {
			contratoParcelamento.setDataVencimentoPrimParcela(
					Util.converteStringParaDate(form.getDataVencimentoPrimParcela()));
		} else {
			contratoParcelamento.setDataVencimentoPrimParcela(null);
		}
		
		if (form.getPacerlaAdd() != null
				&& !form.getPacerlaAdd().equals("")) {
			contratoParcelamento.setNumeroPrestacoes(
					new Integer(form.getPacerlaAdd()));
		} else {
			contratoParcelamento.setNumeroPrestacoes(null);
		}
		
		if (form.getTaxaJurosAdd() != null
				&& !form.getTaxaJurosAdd().equals("")) {

			String juros = form.getTaxaJurosAdd().replace(".", ",");
			juros = juros.replace(",", ".");
			BigDecimal jurosBigDec = new BigDecimal(juros);
				
			contratoParcelamento.setTaxaJuros(jurosBigDec);
		} else {
			contratoParcelamento.setTaxaJuros(null);
		}
		
		return contratoParcelamento;
	}
	
	/**
	 * Carrega no form os valores do contrato de parcelamento
	 * */
	private void atualizarValoresForm(HttpSession sessao,
			AtualizarContratoParcelamentoPorClienteActionForm form){
		
		ContratoParcelamento contratoParcelamento = (ContratoParcelamento) sessao.getAttribute("contratoAtualizar");
		
		// 1ª aba
		if (contratoParcelamento.getNumero() != null
				&& !contratoParcelamento.getNumero().equals("")) {
			form.setNumeroContrato(contratoParcelamento.getNumero());
		} else {
			form.setNumeroContrato(null);
		}
		
		if (contratoParcelamento.getContratoAnterior() != null
				&& contratoParcelamento.getContratoAnterior().getNumero() != null) {
			form.setNumeroContratoAnt(contratoParcelamento.getContratoAnterior().getNumero());
		} else {
			form.setNumeroContratoAnt(null);
		}
		
		if (contratoParcelamento.getRelacaoAnterior() != null
				&& contratoParcelamento.getRelacaoAnterior().getId() != null) {
			form.setRelacaoAnterior(contratoParcelamento.getRelacaoAnterior().getId().toString());
		} else {
			form.setRelacaoAnterior(null);
		}
		
		if (contratoParcelamento.getDataContrato() != null) {
			form.setDataContrato(
					Util.formatarData(contratoParcelamento.getDataContrato()));
		} else {
			form.setDataContrato(null);
		}
		
		if (contratoParcelamento.getUsuarioResponsavel() != null
				&& contratoParcelamento.getUsuarioResponsavel().getLogin() != null) {
			form.setLoginUsuario(contratoParcelamento.getUsuarioResponsavel().getLogin());
			form.setNomeUsuario(contratoParcelamento.getUsuarioResponsavel().getNomeUsuario());
			sessao.setAttribute("usuarioEncontrado","true");
		} else {
			form.setLoginUsuario(null);
			form.setNomeUsuario(null);
		}
		
		if (contratoParcelamento.getRelacaoCliente() != null
				&& contratoParcelamento.getRelacaoCliente().getId() != null) {
			form.setRelacaoCliente(contratoParcelamento.getRelacaoCliente().getId().toString());
		} else {
			form.setRelacaoCliente(null);
		}
		
		if (contratoParcelamento.getAnoMesDebitoInicio() != null
				&& contratoParcelamento.getAnoMesDebitoInicio().compareTo(new Integer(0)) != 0) {
			form.setAnoMesDebitoInicio(
					Util.formatarAnoMesParaMesAno(contratoParcelamento.getAnoMesDebitoInicio()));
		} else {
			form.setAnoMesDebitoInicio(null);
		}
		if (contratoParcelamento.getAnoMesDebitoFinal() != null
				&& contratoParcelamento.getAnoMesDebitoFinal().compareTo(new Integer(0)) != 0) {
			form.setAnoMesDebitoFinal(
					Util.formatarAnoMesParaMesAno(contratoParcelamento.getAnoMesDebitoFinal()));
		} else {
			form.setAnoMesDebitoFinal(null);
		}
		
		if (contratoParcelamento.getDataVencimentoInicio() != null) {
			form.setDataVencimentoInicio(
					Util.formatarData(contratoParcelamento.getDataVencimentoInicio()));
		} else {
			form.setDataVencimentoInicio(null);
		}
		if (contratoParcelamento.getDataVencimentoFinal() != null) {
			form.setDataVencimentoFinal(
					Util.formatarData(contratoParcelamento.getDataVencimentoFinal()));
		} else {
			form.setDataVencimentoFinal(null);
		}
		
		if (contratoParcelamento.getObservacao() != null
				&& !contratoParcelamento.getObservacao().equals("")) {
			form.setObservacao(contratoParcelamento.getObservacao());
		} else {
			form.setObservacao(null);
		}
		
		//3ª aba
		if (contratoParcelamento.getResolucaoDiretoria() != null
				&& contratoParcelamento.getResolucaoDiretoria().getNumero() != null) {
			form.setResolucaoDiretoria(contratoParcelamento.getResolucaoDiretoria().getNumero());
			if(contratoParcelamento.getQtdPrestacoesDaRDEscolhida() != null){
				form.setParcelaSelecao(contratoParcelamento.getQtdPrestacoesDaRDEscolhida().getId().toString());
			}
		} else {
			form.setResolucaoDiretoria(null);
		}

		if (contratoParcelamento.getIndicadorDebitosAcrescimos() != null
				&& contratoParcelamento.getIndicadorDebitosAcrescimos().compareTo(new Short("0")) != 0) {
			form.setIndicadorDebitoAcresc(
					contratoParcelamento.getIndicadorDebitosAcrescimos().toString());
		} else {
			form.setIndicadorDebitoAcresc(null);
		}

		if (contratoParcelamento.getIndicadorParcelamentoJuros() != null
				&& contratoParcelamento.getIndicadorParcelamentoJuros().compareTo(new Short("0")) != 0) {
			form.setIndicadorParcelJuros(
					contratoParcelamento.getIndicadorParcelamentoJuros().toString());
		} else {
			form.setIndicadorParcelJuros(null);
		}

		if (contratoParcelamento.getIndicadorPermiteInformarValorParcel() != null
				&& contratoParcelamento.getIndicadorPermiteInformarValorParcel().compareTo(new Short("0")) != 0) {
			form.setIndicadorInfoVlParcel(
					contratoParcelamento.getIndicadorPermiteInformarValorParcel().toString());
		} else {
			form.setIndicadorInfoVlParcel(null);
		}

		if (contratoParcelamento.getCobrancaForma() != null
				&& contratoParcelamento.getCobrancaForma().getId() != null) {
			form.setFormaPgto(contratoParcelamento.getCobrancaForma().getId().toString());
		} else {
			form.setFormaPgto(null);
		}
		
		if (contratoParcelamento.getDataVencimentoPrimParcela() != null) {
			form.setDataVencimentoPrimParcela(
					Util.formatarData(contratoParcelamento.getDataVencimentoPrimParcela()));
		} else {
			form.setDataVencimentoPrimParcela(null);
		}
		
		if (contratoParcelamento.getNumeroPrestacoes() != null
				&& contratoParcelamento.getNumeroPrestacoes().compareTo(new Integer(0)) != 0) {
			form.setPacerlaAdd(
					contratoParcelamento.getNumeroPrestacoes().toString());
		} else {
			form.setPacerlaAdd(null);
		}
		
		if (contratoParcelamento.getTaxaJuros() != null
				&& contratoParcelamento.getTaxaJuros().compareTo(BigDecimal.ZERO) != 0) {
			form.setTaxaJurosAdd(Util.formatarMoedaReal(contratoParcelamento.getTaxaJuros()));
		} else {
			form.setTaxaJurosAdd(null);
		}
		
	}

}

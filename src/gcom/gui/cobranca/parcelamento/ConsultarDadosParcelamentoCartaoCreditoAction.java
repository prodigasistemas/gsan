package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cobranca.bean.ParcelamentoDadosCartaoCreditoDebitoHelper;
import gcom.cobranca.parcelamento.FiltroParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarDadosParcelamentoCartaoCreditoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento para a tela de
		// consultar débito cobrado
		ActionForward retorno = actionMapping
				.findForward("consultarDadosParcelamentoCartaoCredito");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		ParcelamentoCartaoConfirmarForm parcelamentoCartaoConfirmarForm = (ParcelamentoCartaoConfirmarForm) actionForm;

		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		String parcelamentoId = (String) sessao.getAttribute("parcelamentoCartaCredito");
		String codigoParcelamento = httpServletRequest.getParameter("numeroParcelamento");

		
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		String idDigitadoEnterCliente = parcelamentoCartaoConfirmarForm.getIdCliente();
		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");
		String atualizado = httpServletRequest.getParameter("atualizado");
		String carregamentoInicial = httpServletRequest.getParameter("carregamentoInicial");
		
		if (carregamentoInicial != null && carregamentoInicial.equals("ok")){
			
			FiltroParcelamentoPagamentoCartaoCredito filtroParcelamento = new FiltroParcelamentoPagamentoCartaoCredito();

			filtroParcelamento.adicionarParametro(new ParametroSimples(FiltroParcelamentoPagamentoCartaoCredito.ID_PARCELAMENTO, parcelamentoId));

			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("usuarioConfirmacao");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("arrecadador");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("arrecadador.cliente");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("parcelamento");

			Collection<ParcelamentoPagamentoCartaoCredito> colecaoParcelamento = fachada.pesquisar(filtroParcelamento,
			ParcelamentoPagamentoCartaoCredito.class.getName());

			sessao.setAttribute("colecaoParcelamentos", colecaoParcelamento);
		}
		
		if(atualizado != null && !atualizado.equals("")){
			httpServletRequest.setAttribute("atualizado", "ok");
			httpServletRequest.removeAttribute("dados");
		}
		
		
		if(tipoConsulta != null &&
				tipoConsulta.equals("cliente")){
			idDigitadoEnterCliente = httpServletRequest.getParameter("idCampoEnviarDados");
			codigoParcelamento = (String)sessao.getAttribute("ParcelamemtoSelecionado");
		}
		
		if(objetoConsulta != null && objetoConsulta.equals("cliente")){
			this.pesquisarCliente(actionMapping, actionForm, httpServletRequest,
					httpServletResponse, codigoParcelamento, fachada, usuarioLogado, idDigitadoEnterCliente);
		}
		
		if (objetoConsulta != null && objetoConsulta.equals("selecionar")) {
			this.selecionar(actionMapping, actionForm, httpServletRequest,
					httpServletResponse, codigoParcelamento, fachada, usuarioLogado);
		}
		if(tipoConsulta != null && tipoConsulta.equals("cliente")){
			this.pesquisarCliente(actionMapping, actionForm, httpServletRequest,
					httpServletResponse, codigoParcelamento, fachada, usuarioLogado, idDigitadoEnterCliente);
		}
		
		if (objetoConsulta != null && objetoConsulta.equals("pesquisarClientePopup")) {
			
			this.pesquisarClientePopup(actionMapping, actionForm, httpServletRequest,
					httpServletResponse);
			
			return actionMapping.findForward("pesquisarClientePopup");
		}		
		
		// retorna o mapeamento contido na variável retorno
		return retorno;
	}
	
	public void selecionar(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String codigoParcelamento,
			Fachada fachada, Usuario usuarioLogado) {
		
		ParcelamentoCartaoConfirmarForm parcelamentoCartaoConfirmarForm = (ParcelamentoCartaoConfirmarForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ParcelamentoPagamentoCartaoCredito parcelamentoAtualizado = null;
		String numeroCartaoDecrypt = null;
		
		Collection colecaoParcelamentoPagamentoCartaoCredito = (Collection) sessao.getAttribute("colecaoParcelamentos");
		boolean formularioCarregado = false;
		
		if (colecaoParcelamentoPagamentoCartaoCredito != null && !colecaoParcelamentoPagamentoCartaoCredito.isEmpty()){
			
			Iterator iterator = colecaoParcelamentoPagamentoCartaoCredito.iterator();
			
			while(iterator.hasNext()){
				
				ParcelamentoPagamentoCartaoCredito parcelamentoPagamentoCartaoCreditoColecao = (ParcelamentoPagamentoCartaoCredito)
				iterator.next();
				
				if (parcelamentoPagamentoCartaoCreditoColecao.getId().equals(Integer.valueOf(codigoParcelamento))){
					
					parcelamentoAtualizado = parcelamentoPagamentoCartaoCreditoColecao;
					
					parcelamentoCartaoConfirmarForm.setDocumentoCartao(parcelamentoPagamentoCartaoCreditoColecao.getDocumentoCartaoCredito().toString());
					
					parcelamentoCartaoConfirmarForm.setAutorizacaoCartao(parcelamentoPagamentoCartaoCreditoColecao.getNumeroAutorizacao().toString());

					numeroCartaoDecrypt = Util.decrypt(parcelamentoPagamentoCartaoCreditoColecao.getNumeroCartaoCredito());

					String numeroCartao = numeroCartaoDecrypt.substring(0, 4);

					for (int i = 0; i < (numeroCartaoDecrypt.length() - 4); i++) {
						numeroCartao = numeroCartao + "*";
					}

					parcelamentoCartaoConfirmarForm.setCartaoCredito(parcelamentoPagamentoCartaoCreditoColecao.getArrecadador().getCliente().getNome());
					
					parcelamentoCartaoConfirmarForm.setNumeroCartao(numeroCartao);
					
					parcelamentoCartaoConfirmarForm.setValidadeCartao(Util.formatarAnoMesParaMesAno(parcelamentoPagamentoCartaoCreditoColecao.getAnoMesValidade().toString()));
					
					parcelamentoCartaoConfirmarForm.setIdCliente(parcelamentoPagamentoCartaoCreditoColecao.getCliente().getId().toString());
					
					parcelamentoCartaoConfirmarForm.setNomeCliente(parcelamentoPagamentoCartaoCreditoColecao.getCliente().getNome());
					
					parcelamentoCartaoConfirmarForm.setUsuarioConfirmacao(parcelamentoPagamentoCartaoCreditoColecao.getUsuarioConfirmacao().getNomeUsuario());
					
					parcelamentoCartaoConfirmarForm.setNumeroIdentificacaoTransacao(parcelamentoPagamentoCartaoCreditoColecao.getIdentificacaoTransacao());
					
					parcelamentoCartaoConfirmarForm.setQtdParcelas(parcelamentoPagamentoCartaoCreditoColecao.getQuantidadeParcelas().toString());
					
					parcelamentoCartaoConfirmarForm.setValorTransacao(Util.formatarMoedaReal(parcelamentoPagamentoCartaoCreditoColecao.getValorParcelado()));
					
					parcelamentoCartaoConfirmarForm.setDataOperadora(Util.formatarData(parcelamentoPagamentoCartaoCreditoColecao.getDataConfirmacao()));
					
					formularioCarregado = true;
					break;
				}
			}
		}
		
		if (!formularioCarregado){
			
			FiltroParcelamentoPagamentoCartaoCredito filtro = new FiltroParcelamentoPagamentoCartaoCredito();

			filtro.adicionarParametro(new ParametroSimples(FiltroParcelamentoPagamentoCartaoCredito.ID, codigoParcelamento));

			filtro.adicionarCaminhoParaCarregamentoEntidade("usuarioConfirmacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtro.adicionarCaminhoParaCarregamentoEntidade("arrecadador");
			filtro.adicionarCaminhoParaCarregamentoEntidade("arrecadador.cliente");

			Collection<ParcelamentoPagamentoCartaoCredito> colecao = fachada.pesquisar(filtro,
			ParcelamentoPagamentoCartaoCredito.class.getName());

			parcelamentoAtualizado = (ParcelamentoPagamentoCartaoCredito) Util.retonarObjetoDeColecao(colecao);

			parcelamentoCartaoConfirmarForm.setDocumentoCartao(parcelamentoAtualizado.getDocumentoCartaoCredito().toString());
			
			parcelamentoCartaoConfirmarForm.setAutorizacaoCartao(parcelamentoAtualizado.getNumeroAutorizacao().toString());

			numeroCartaoDecrypt = Util.decrypt(parcelamentoAtualizado.getNumeroCartaoCredito());

			String numeroCartao = numeroCartaoDecrypt.substring(0, 4);

			for (int i = 0; i < (numeroCartaoDecrypt.length() - 4); i++) {
				numeroCartao = numeroCartao + "*";
			}

			parcelamentoCartaoConfirmarForm.setCartaoCredito(parcelamentoAtualizado.getArrecadador().getCliente().getNome());
			
			parcelamentoCartaoConfirmarForm.setNumeroCartao(numeroCartao);
			
			parcelamentoCartaoConfirmarForm.setValidadeCartao(Util.formatarAnoMesParaMesAno(parcelamentoAtualizado.getAnoMesValidade().toString()));
			
			parcelamentoCartaoConfirmarForm.setIdCliente(parcelamentoAtualizado.getCliente().getId().toString());
			
			parcelamentoCartaoConfirmarForm.setNomeCliente(parcelamentoAtualizado.getCliente().getNome());
			
			parcelamentoCartaoConfirmarForm.setUsuarioConfirmacao(parcelamentoAtualizado.getUsuarioConfirmacao().getNomeUsuario());
			
			parcelamentoCartaoConfirmarForm.setNumeroIdentificacaoTransacao(parcelamentoAtualizado.getIdentificacaoTransacao());
			
			parcelamentoCartaoConfirmarForm.setQtdParcelas(parcelamentoAtualizado.getQuantidadeParcelas().toString());
			
			parcelamentoCartaoConfirmarForm.setValorTransacao(Util.formatarMoedaReal(parcelamentoAtualizado.getValorParcelado()));
			
			parcelamentoCartaoConfirmarForm.setDataOperadora(Util.formatarData(parcelamentoAtualizado.getDataConfirmacao()));
		}
		
		
		//UC 0927 
		//Desenvolvedor: Flávio Cordeiro - Analista: Rafael Rossiter - 01/02/2010
		//4 - Caso o usuário tenha permissão especial para alterar dados das informações 
		// de confirmação pagamento com cartão de crédito/débito e o imóvel selecionado tenha
		// estas modalidades de pagamento informdas(IMOV_ID existindo nas tabelas 
		//PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO PAGAMENTO_CARTAO_DEBITO), o sistema deverá permitir alteração 
		//[SB0005 - Alterar dados cofirmações].
		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuarioLogado.getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.ALTERAR_DADOS_CONFIRMACAO_CARTAO_CREDITO_DEBITO));
								
		Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
		if(!colecaoUsuarioPermisao.isEmpty()){
			httpServletRequest.setAttribute("temPermissaoAlterarDadosCartao", "s");
			parcelamentoCartaoConfirmarForm.setNumeroCartao(numeroCartaoDecrypt);
		}
		
		//Carregar Help
		ParcelamentoDadosCartaoCreditoDebitoHelper helper = new ParcelamentoDadosCartaoCreditoDebitoHelper(
				parcelamentoCartaoConfirmarForm);
		
		sessao.setAttribute("ParcelamemtoSelecionado",codigoParcelamento);
		httpServletRequest.setAttribute("dados", parcelamentoCartaoConfirmarForm);
		
		sessao.setAttribute("dadosFormulario", helper);
		
		sessao.setAttribute("parcelamentoAtualizacao", parcelamentoAtualizado);
	}
	
	public void pesquisarCliente(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,String codigoParcelamento,
			Fachada fachada, Usuario usuarioLogado, String idDigitadoEnterCliente) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ParcelamentoCartaoConfirmarForm form =  (ParcelamentoCartaoConfirmarForm)actionForm;
		ParcelamentoDadosCartaoCreditoDebitoHelper helper = 
			(ParcelamentoDadosCartaoCreditoDebitoHelper)sessao.getAttribute("dadosFormulario");
		
		//-------------------------------------
		form.setDocumentoCartao(helper.getDocumentoCartao());
		form.setAutorizacaoCartao(helper.getAutorizacaoCartao());
		form.setCartaoCredito(helper.getCartaoCredito());
		form.setNumeroCartao(helper.getNumeroCartao());
		form.setValidadeCartao(helper.getValidadeCartao());
		form.setIdCliente(helper.getIdCliente());
		form.setNomeCliente(helper.getNomeCliente());
		form.setUsuarioConfirmacao(helper.getUsuarioConfirmacao());
		form.setNumeroIdentificacaoTransacao(helper.getNumeroIdentificacaoTransacao());
		form.setQtdParcelas(helper.getQtdParcelas());
		form.setValorTransacao(helper.getValorTransacao());
		form.setDataOperadora(helper.getDataOperadora());
		
		//----------------------------------
		
		ParcelamentoPagamentoCartaoCredito parcelamento = 
			(ParcelamentoPagamentoCartaoCredito)sessao.getAttribute("parcelamentoAtualizacao");
		
		
		//UC 0927 
		//Desenvolvedor: Flávio Cordeiro - Analista: Rafael Rossiter - 01/02/2010
		//4 - Caso o usuário tenha permissão especial para alterar dados das informações 
		// de confirmação pagamento com cartão de crédito/débito e o imóvel selecionado tenha
		// estas modalidades de pagamento informdas(IMOV_ID existindo nas tabelas 
		//PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO PAGAMENTO_CARTAO_DEBITO), o sistema deverá permitir alteração 
		//[SB0005 - Alterar dados cofirmações].
		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuarioLogado.getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.ALTERAR_DADOS_CONFIRMACAO_CARTAO_CREDITO_DEBITO));
								
		Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
		if(!colecaoUsuarioPermisao.isEmpty()){
			httpServletRequest.setAttribute("temPermissaoAlterarDadosCartao", "s");
		}
		
		sessao.setAttribute("ParcelamemtoSelecionado",codigoParcelamento);
		httpServletRequest.setAttribute("dados",
				form);
		
		sessao.setAttribute("parcelamentoAtualizacao", parcelamento);
		
		//verifica se o codigo do cliente foi digitado
		if (idDigitadoEnterCliente != null
				&& !idDigitadoEnterCliente.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterCliente) > 0) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				form.setIdCliente(((Cliente) ((List) clienteEncontrado).get(0)).getId()+"");
				form.setNomeCliente(((Cliente) ((List) clienteEncontrado).get(0)).getNome());
				httpServletRequest.setAttribute("idClienteNaoEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "cep");

			} else {
				form.setIdCliente("");
				httpServletRequest.setAttribute("codigoClienteNaoEncontrado",
						"exception");
				form.setNomeCliente("Cliente inexistente");

				httpServletRequest.setAttribute("nomeCampo", "idCliente");

			}
			
			//Carregar Help
			ParcelamentoDadosCartaoCreditoDebitoHelper helperSessao = new ParcelamentoDadosCartaoCreditoDebitoHelper(
					form);
			
			httpServletRequest.setAttribute("dados", form);
			sessao.setAttribute("dadosFormulario", helperSessao);
			httpServletRequest.setAttribute("temPermissaoAlterarDadosCartao", "s");

		}
	}
	
	public void pesquisarClientePopup(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		HttpSession sessao = httpServletRequest.getSession(false);
		ParcelamentoCartaoConfirmarForm form = (ParcelamentoCartaoConfirmarForm) actionForm;
		
		ParcelamentoDadosCartaoCreditoDebitoHelper helper = new ParcelamentoDadosCartaoCreditoDebitoHelper(form);
		
		sessao.setAttribute("dadosFormulario", helper);
		
		httpServletRequest.setAttribute("limparForm", "s");
		
	}
	
}

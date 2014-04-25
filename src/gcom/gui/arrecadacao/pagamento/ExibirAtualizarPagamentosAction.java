package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa a página de atualizar pagamentoa
 * 
 * @author Pedro Alexandre
 * @date 22/03/2006
 */
public class ExibirAtualizarPagamentosAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Pedro Alexandre
	 * @date 22/03/2006
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

		// Seta o mapeamento de retorno para a página de atualizar pagamento
		ActionForward retorno = actionMapping.findForward("atualizarPagamento");

		// Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));

		// Recupera o código do pagamento que vai ser atualizado
		// String codigoPagamento =
		// httpServletRequest.getParameter("idRegistroAtualizacao");

		// Cria a variável que vai armazenar o pagamento para ser atualizado
		// Pagamento pagamento = (Pagamento) sessao.getAttribute("pagamento");

		// recupera o form de manter pagamentos
		ManterPagamentoActionForm manterPagamentoActionForm = (ManterPagamentoActionForm) actionForm;

		// Cria o filtro de tipo de documento, e seta no filtro quais os tipo de
		// documentos necessários
		// para pesquisar os tipos de documento de conta, guia de pagamento e
		// débito a cobrar
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.CONTA,
				ParametroSimples.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.GUIA_PAGAMENTO,
				ParametroSimples.CONECTOR_OR));
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.ID, DocumentoTipo.DEBITO_A_COBRAR));
		Collection<DocumentoTipo> colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		// [FS0002] - Verificar existência de dados
		// Caso a coleção de tipo de documento estiver nula ou vazia, levanta
		// uma
		// exceção para o usuário indicando que nenhum tipo de documento está
		// cadastrado
		// Caso contrário manda os tipos de documentos pesquisados pela sessão
		if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Documento");
		} 
		
		sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);

		String idPagamento = null;
		if (httpServletRequest.getParameter("reloadPage") == null
				|| httpServletRequest.getParameter("reloadPage").equals("")) {
			// Recupera o id de Pagamento que vai ser atualizado
			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
				idPagamento = httpServletRequest
						.getParameter("idRegistroInseridoAtualizar");
				// Definindo a volta do botão Voltar p Filtrar Pagamento
				sessao.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao", idPagamento);
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				idPagamento = "" + sessao.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Filtrar Pagamento
				sessao.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idPagamento = httpServletRequest
						.getParameter("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Manter Pagamento
				sessao.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao", idPagamento);
			}

			exibirPagamento(idPagamento, manterPagamentoActionForm, fachada,
					sessao, httpServletRequest);

		} else {
			idPagamento = (String) sessao.getAttribute("idRegistroAtualizacao");
		}
		
		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------
			exibirPagamento(idPagamento, manterPagamentoActionForm, fachada,
					sessao, httpServletRequest);

		} else {

		// -------Parte que trata do código quando o usuário tecla enter
		if (httpServletRequest.getParameter("reloadPage") != null
				&& !httpServletRequest.getParameter("reloadPage").equals("")) {

			// [FS0003] - Verificar existência da localidade
			// Recupera o código da localidade digitado pelo usuário
			String codigoLocalidadeDigitadoEnter = manterPagamentoActionForm.getIdLocalidade();

			// Caso o código da localidade informado não estiver vazio
			if (codigoLocalidadeDigitadoEnter != null
					&& !codigoLocalidadeDigitadoEnter.trim().equalsIgnoreCase(
							"")) {

				// Recupera a localidade informada pelo usuário
				Localidade localidadeEncontrada = fachada
						.pesquisarLocalidadeDigitada(new Integer(
								codigoLocalidadeDigitadoEnter));

				// Caso a localidade informada pelo usuário esteja cadastrada no
				// sistema
				// Seta os dados da localidade no form
				// Caso contrário seta as informações da localidade para vazio
				// e indica ao usuário que a localidade não existe
				if (localidadeEncontrada != null) {
					manterPagamentoActionForm.setIdLocalidade(""
							+ localidadeEncontrada.getId());
					manterPagamentoActionForm
							.setDescricaoLocalidade(localidadeEncontrada
									.getDescricao());
					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrada", "true");

				} else {
					manterPagamentoActionForm.setIdLocalidade("");
					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrada", "exception");
					manterPagamentoActionForm
							.setDescricaoLocalidade("Localidade inexistente");

				}
			}

			// [FS0004] - Verificar existência da matrícula do imóvel
			String codigoImovelDigitadoEnter = manterPagamentoActionForm.getIdImovel();

			// Caso o código do imóvel informado não estiver vazio
			if (codigoImovelDigitadoEnter != null
					&& !codigoImovelDigitadoEnter.trim().equalsIgnoreCase("")) {

				// Recupera o imóvel informado pelo usuário
				Imovel imovelEncontrado = fachada
						.pesquisarImovelDigitado(new Integer(
								codigoImovelDigitadoEnter));

				// Caso o imóvel informado pelo usuário esteja cadastrado no
				// sistema
				// Seta os dados o imóvel no form
				// Caso contrário seta as informações o imóvel para vazio
				// e indica ao usuário que o imóvel informado não existe
				if (imovelEncontrado != null) {
					manterPagamentoActionForm.setIdImovel(""
							+ imovelEncontrado.getId());
					manterPagamentoActionForm.setDescricaoImovel(""
							+ imovelEncontrado.getInscricaoFormatada());
					httpServletRequest.setAttribute("idImovelNaoEncontrado",
							"true");

					// Recupera a localidade do imóvel,caso o mesmo exista na
					// base
					Localidade localidadeImovel = imovelEncontrado
							.getLocalidade();

					// Caso o usuário tenha informado a localidade
					if (codigoLocalidadeDigitadoEnter != null
							&& !codigoLocalidadeDigitadoEnter.trim()
									.equalsIgnoreCase("")) {
						// [FS0005] - Verificar localidade da matrícula do
						// imóvel
						if (!fachada
								.verificarLocalidadeMatriculaImovel(
										codigoLocalidadeDigitadoEnter,
										imovelEncontrado)) {
							manterPagamentoActionForm.setIdImovel("");
							httpServletRequest.setAttribute(
									"idImovelNaoEncontrado", "exception");
							manterPagamentoActionForm
									.setDescricaoImovel("A Localidade da Matrícula "
											+ localidadeImovel.getId()
											+ " é diferente da localidade informada "
											+ codigoLocalidadeDigitadoEnter);
						}
					} else {
						manterPagamentoActionForm.setIdLocalidade(""
								+ localidadeImovel.getId());
						manterPagamentoActionForm
								.setDescricaoLocalidade(localidadeImovel
										.getDescricao());
						httpServletRequest.setAttribute(
								"idLocalidadeNaoEncontrada", "true");
					}
				} else {
					manterPagamentoActionForm.setIdImovel("");
					httpServletRequest.setAttribute("idImovelNaoEncontrado",
							"exception");
					manterPagamentoActionForm
							.setDescricaoImovel("Matrícula inexistente");
				}
			}

			// [FS0007] - Verificar existência do código do cliente
			String codigoClienteDigitadoEnter = manterPagamentoActionForm.getIdCliente();

			// Recupera a metrícula do imóvel e o código do cliente do form de
			// manter pagamento
			String codigoImovel = codigoImovelDigitadoEnter;
			String codigoCliente = manterPagamentoActionForm.getIdCliente();

			// Caso o usuário tenha informado o código do cliente
			if (codigoClienteDigitadoEnter != null
					&& !codigoClienteDigitadoEnter.trim().equalsIgnoreCase("")) {

				// Recupera o cliente ,caso o mesmo exista na base
				Cliente clienteEncontrado = fachada
						.pesquisarClienteDigitado(new Integer(
								codigoClienteDigitadoEnter));

				// Caso o cliente esteja cadastrado no sistema
				// Seta no form todos os dados do cliente
				// Caso contrário seta os dados do cliente para vazio e informa
				// que
				// o cliente não existe
				if (clienteEncontrado != null) {
					manterPagamentoActionForm.setIdCliente(""
							+ clienteEncontrado.getId());
					manterPagamentoActionForm.setNomeCliente(clienteEncontrado
							.getNome());
					httpServletRequest.setAttribute("idClienteNaoEncontrado",
							"true");

				} else {
					manterPagamentoActionForm.setIdCliente("");
					httpServletRequest.setAttribute("idClienteNaoEncontrado",
							"exception");
					manterPagamentoActionForm
							.setNomeCliente("Código inexistente");
				}
			}

			// [FS0008] - Verificar existência da conta
			String referenciaContaDigitadoEnter = manterPagamentoActionForm.getReferenciaConta();

			// Caso o usuário tenha informado a referência da conta
			if (referenciaContaDigitadoEnter != null
					&& !referenciaContaDigitadoEnter.trim()
							.equalsIgnoreCase("")) {

				// Caso o usuário não tenha informado a matrícula do imóvel
				// Levanta uma exceção para o usuário indicado que ele não
				// informou
				// a matrícula do imóvel
				if (codigoImovel == null
						|| codigoImovel.trim().equalsIgnoreCase("")) {

					throw new ActionServletException("atencao.naoinformado",
							null, "Imóvel");
				}

				// Recupera a conta do imóvel com a referência informada
				Conta contaEncontrada = fachada.pesquisarContaDigitada(
						codigoImovel, referenciaContaDigitadoEnter);

				// Caso a conta esteja cadastrada no sistema
				// Seta todas as informações da conta no form
				// Caso contrário seta as informações da conta para nulo
				// e indica ao usuário que não existe conta para o imóel
				// informadocom a referência indicada
				if (contaEncontrada != null) {
					manterPagamentoActionForm.setReferenciaConta(""
							+ referenciaContaDigitadoEnter);
					manterPagamentoActionForm.setDescricaoReferenciaConta(""
							+ contaEncontrada.getValorTotalConta());
					manterPagamentoActionForm.setValorPagamento(""
							+ contaEncontrada.getValorTotalConta());
					httpServletRequest.setAttribute(
							"referenciaContaNaoEncontrada", "true");

				} else {
					manterPagamentoActionForm.setReferenciaConta(""
							+ referenciaContaDigitadoEnter);
					httpServletRequest.setAttribute(
							"referenciaContaNaoEncontrada", "exception");
					manterPagamentoActionForm
							.setDescricaoReferenciaConta("Não há Conta com a referência "
									+ referenciaContaDigitadoEnter
									+ " para o imóvel " + codigoImovel);
					manterPagamentoActionForm.setValorPagamento("");
				}
			}

			// [FS0022] - Verificar existência da guia de pagamento
			String codigoGuiaPagamentoDigitadoEnter = manterPagamentoActionForm.getIdGuiaPagamento();

			// Caso o usuário tenha informado o código da guia de pagamento
			if (codigoGuiaPagamentoDigitadoEnter != null
					&& !codigoGuiaPagamentoDigitadoEnter.trim()
							.equalsIgnoreCase("")) {

				// Caso o usuário não tenha informado a matrícula do imóvel
				// Levanta uma exceção para o usuário indicado que ele não
				// informou
				// a matrícula do imóvel
				if ( !Util.verificarNaoVazio(codigoImovel)
						&& !Util.verificarNaoVazio(codigoCliente)) {
					throw new ActionServletException("atencao.naoinformado",
							null, "Imóvel ou Cliente");
				}

				// Pesquisa a guia de pagamento para o imóvel informado
				GuiaPagamento guiaPagamentoEncontrada = fachada
						.pesquisarGuiaPagamentoDigitada(codigoImovel,
								codigoCliente, codigoGuiaPagamentoDigitadoEnter);

				// Caso a guia de pagamento esteja cadastrada no sistema
				// Seta os dados da guia de pagamento no form
				// Caso contrário seta os dados da guia para nulo e informa ao
				// usuário que não existe
				// guia de pagamento cadastrada no sistema
				if (guiaPagamentoEncontrada != null) {
					manterPagamentoActionForm.setIdGuiaPagamento(""
							+ guiaPagamentoEncontrada.getId());
					manterPagamentoActionForm.setDescricaoGuiaPagamento(""
							+ guiaPagamentoEncontrada.getDebitoTipo()
									.getDescricao());
					manterPagamentoActionForm.setValorGuiaPagamento(""
							+ guiaPagamentoEncontrada.getValorDebito());
					httpServletRequest.setAttribute(
							"idGuiaPagamentoNaoEncontrado", "true");

				} else {
					manterPagamentoActionForm.setIdGuiaPagamento("");
					manterPagamentoActionForm
							.setDescricaoGuiaPagamento("Guia de Pagamento inexistente");
					manterPagamentoActionForm.setValorGuiaPagamento("");
					httpServletRequest.setAttribute(
							"idGuiaPagamentoNaoEncontrado", "exception");
				}
			}

			// [FS0024] - Verificar existência do débito a cobrar
			String codigoDebitoACobrarDigitadoEnter = manterPagamentoActionForm.getIdDebitoACobrar();

			// Caso o usuário tenha informado o código do débito a cobrar
			if (codigoDebitoACobrarDigitadoEnter != null
					&& !codigoDebitoACobrarDigitadoEnter.trim()
							.equalsIgnoreCase("")) {

				// Caso o usuário não tenha informado a matrícula do imóvel
				// Levanta uma exceção para o usuário indicado que ele não
				// informou
				// a matrícula do imóvel
				if (codigoImovel == null
						|| codigoImovel.trim().equalsIgnoreCase("")) {
					throw new ActionServletException("atencao.naoinformado",
							null, "Imóvel");
				}

				// Pesquisa o débito a cobrar para o imóvel informado
			/*	DebitoACobrar debitoACobrarEncontrado = fachada
						.pesquisarDebitoACobrarDigitado(codigoImovel,
								codigoDebitoACobrarDigitadoEnter);*/
				
				// Pesquisa o débito a cobrar para o imóvel informado
				DebitoACobrarGeral debitoACobrarGeralEncontrado = fachada
						.pesquisarDebitoACobrarGeralDigitado(codigoImovel,
								codigoDebitoACobrarDigitadoEnter);
				
				

				// Caso o débito a cobrar esteja cadastrado no sistema
				// Seta os dados do débito a cobrar no form
				// Caso contrário seta os dados do débito para nulo e informa ao
				// usuário que não existe
				// débito a cobrar cadastrado no sistema
				if (debitoACobrarGeralEncontrado != null) {
					
					if(debitoACobrarGeralEncontrado.getDebitoACobrar()!=null){
					
					manterPagamentoActionForm.setIdDebitoACobrar(""
							+ debitoACobrarGeralEncontrado.getDebitoACobrar().getId());
					manterPagamentoActionForm.setDescricaoDebitoACobrar(""
							+ debitoACobrarGeralEncontrado.getDebitoACobrar().getDebitoTipo()
									.getDescricao());
					manterPagamentoActionForm.setValorDebitoACobrar(""
							+ debitoACobrarGeralEncontrado.getDebitoACobrar().getValorDebito());
					httpServletRequest.setAttribute(
							"idDebitoACobrarNaoEncontrado", "true");
					}else{
						manterPagamentoActionForm.setIdDebitoACobrar(""
								+ debitoACobrarGeralEncontrado.getDebitoACobrarHistorico().getId());
						manterPagamentoActionForm.setDescricaoDebitoACobrar(""
								+ debitoACobrarGeralEncontrado.getDebitoACobrarHistorico().getDebitoTipo()
										.getDescricao());
						manterPagamentoActionForm.setValorDebitoACobrar(""
								+ debitoACobrarGeralEncontrado.getDebitoACobrarHistorico().getValorDebito());
						httpServletRequest.setAttribute(
								"idDebitoACobrarNaoEncontrado", "true");
					}

				} else {
					manterPagamentoActionForm.setIdDebitoACobrar("");
					manterPagamentoActionForm
							.setDescricaoDebitoACobrar("Débito a Cobrar inexistente");
					manterPagamentoActionForm.setDescricaoDebitoACobrar("");
					httpServletRequest.setAttribute(
							"idDebitoACobrarNaoEncontrado", "exception");
				}
			}

			// }

			// [FS0020] - Verificar existência do tipo de débito
			String codigoTipoDebitoDigitadoEnter = manterPagamentoActionForm.getIdTipoDebito();

			// Caso o usuário tenha informado o código do tipo de débito
			if (codigoTipoDebitoDigitadoEnter != null
					&& !codigoTipoDebitoDigitadoEnter.trim().equalsIgnoreCase(
							"")) {

				// Recupera o tipo de débito ,caso o mesmo exista na base
				DebitoTipo tipoDebitoEncontrado = fachada
						.pesquisarTipoDebitoDigitado(new Integer(
								codigoTipoDebitoDigitadoEnter));

				// Caso o tipo de débito esteja cadastrado no sistema
				// Seta no form todos os dados do tipo de débito
				// Caso contrário seta os dados do tipo de débito para vazio e
				// informa que o tipo de débito não existe
				if (tipoDebitoEncontrado != null) {
					manterPagamentoActionForm.setIdTipoDebito(""
							+ tipoDebitoEncontrado.getId());
					manterPagamentoActionForm
							.setDescricaoTipoDebito(tipoDebitoEncontrado
									.getDescricao());
					httpServletRequest.setAttribute(
							"idTipoDebitoNaoEncontrado", "true");

				} else {
					manterPagamentoActionForm.setIdTipoDebito("");
					httpServletRequest.setAttribute(
							"idTipoDebitoNaoEncontrado", "exception");
					manterPagamentoActionForm
							.setDescricaoTipoDebito("Tipo de Débito inexistente");
				}
			}

		}
		}
		// -------Parte que trata do código quando o usuário tecla enter

		// Seta na sessão o form de pagamento e o pagamento que vai ser
		// atualizado
		sessao.setAttribute("ManterPagamentoActionForm",
				manterPagamentoActionForm);
		// sessao.setAttribute("pagamento", pagamento);

		// Retorna o mapeamento contido na variável retorno
		return retorno;
	}

	private void exibirPagamento(String idPagamento,
			ManterPagamentoActionForm manterPagamentoActionForm,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		Pagamento pagamento = null;

		PagamentoSituacao pagamentoSituacaoAtual = null;

		if (idPagamento != null && !idPagamento.equalsIgnoreCase("")) {

			// Cria o filtro de pagamento e seta o código do pagamento para ser
			// atualizado no filtro
			// e indicxa quais objetos devem ser retornados pela pesquisa
			FiltroPagamento filtroPagamento = new FiltroPagamento();
			filtroPagamento.adicionarParametro(new ParametroSimples(
					FiltroPagamento.ID, idPagamento));
			
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("avisoBancario");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("arrecadacaoForma");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("pagamentoSituacaoAtual");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("pagamentoSituacaoAnterior");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador.cliente");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("contaGeral");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("contaGeral.conta");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamento");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar");
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico");

			// Pesquisa o pagamento no sistema com os parâmetros informados no
			// filtro
			Collection<Pagamento> colecaoPagamentos = fachada.pesquisar(filtroPagamento,
					Pagamento.class.getName());

			// Caso a pesquisa tenha retornado o pagamento
			if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()) {
				// Recupera da coleção o pagamento que vai ser atualizado
				pagamento = (Pagamento) Util
						.retonarObjetoDeColecao(colecaoPagamentos);

				// Cria a variável que vai armazenar a coleção de situações
				// atuais de pagamento
				Collection<PagamentoSituacao> colecaoSituacaoAtualPagamento = null;

				// Recupera a situação atual e anterior do pagamento
				pagamentoSituacaoAtual = pagamento.getPagamentoSituacaoAtual();

				// Caso a situação atual do pagamento esteja preenchida
				if (pagamentoSituacaoAtual != null) {
					// Caso a situação atual do pagamento seja igual a "Fatura
					// Inexistente" ou
					// "Pagamento em Duplicidade" ou igual a valor em excesso
					if (pagamentoSituacaoAtual.getId().equals(
							PagamentoSituacao.FATURA_INEXISTENTE)
							|| pagamentoSituacaoAtual.getId().equals(
									PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE)
							|| pagamentoSituacaoAtual.getId().equals(
									PagamentoSituacao.VALOR_EM_EXCESSO)
						
                            /* Alterado dia 12/09/2008
                               Author: Bruno Barros
                               Descrição:
                                   Alteração do caso de uso para que quando a situação atual do pagamento for igual a
                                   “Valor Não Confere” e valor excedente do pagamento com valor maior que zero */ 
                            || ( pagamentoSituacaoAtual.getId().equals(
                                    PagamentoSituacao.VALOR_NAO_CONFERE ) && 
                                    pagamento.getValorExcedente() != null && 
                                    pagamento.getValorExcedente().floatValue() > 0 ) ) {
                            // Fim alteração Bruno Barros
                        
						// Cria o filtro de situação de pagamento e seta no
						// filtro para retornar somente a situação
						// igual a valor a baixar
						FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();
						filtroPagamentoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroPagamentoSituacao.CODIGO,
										PagamentoSituacao.VALOR_A_BAIXAR));
						colecaoSituacaoAtualPagamento = fachada.pesquisar(
								filtroPagamentoSituacao,
								PagamentoSituacao.class.getName());

						// [FS0002] - Verificar existência de dados
						// Caso a situação de valor a baixar não esteja
						// cadastrada no sistema
						// levante uma exceção para o usuário indicando que a
						// situação não está cadastrada
						if (colecaoSituacaoAtualPagamento == null
								|| colecaoSituacaoAtualPagamento.isEmpty()) {
							throw new ActionServletException(
									"atencao.naocadastrado", null,
									"Situação de Pagamento");
						}
					}
				}

				// Seta na sessãoa coleção de situação de pagamentos, para o
				// campo de situação de pagamento atual
				sessao.setAttribute("colecaoSituacaoAtualPagamento",
						colecaoSituacaoAtualPagamento);

				// Seta no form os dados de aviso bancário
				manterPagamentoActionForm.setIdAvisoBancario(""
						+ pagamento.getAvisoBancario().getId());
				manterPagamentoActionForm.setCodigoAgenteArrecadador(""
						+ pagamento.getAvisoBancario().getArrecadador()
								.getCodigoAgente());
				manterPagamentoActionForm.setDataLancamentoAviso(Util
						.formatarData(pagamento.getAvisoBancario()
								.getDataLancamento()));
				manterPagamentoActionForm.setNumeroSequencialAviso(""
						+ pagamento.getAvisoBancario().getNumeroSequencial());
				
//				 Seta no form os dados de arrecadação
				if (pagamento.getArrecadacaoForma() != null){
					manterPagamentoActionForm.setIdFormaArrecadacao(""
							+ pagamento.getArrecadacaoForma().getId());
					manterPagamentoActionForm.setDescricaoFormaArrecadacao(""
							+ pagamento.getArrecadacaoForma().getDescricao());
				}

				// Seta no form os dados de tipo de documento
				manterPagamentoActionForm.setIdTipoDocumento(""
						+ pagamento.getDocumentoTipo().getId());

				// Seta no form os dados de localidade
				if (pagamento.getLocalidade() != null) {
					manterPagamentoActionForm.setIdLocalidade(""
							+ pagamento.getLocalidade().getId());
					manterPagamentoActionForm.setDescricaoLocalidade(pagamento
							.getLocalidade().getDescricao());
				}

				// Seta no form os dados de imóvel
				if (pagamento.getImovel() != null) {
					manterPagamentoActionForm.setIdImovel(""
							+ pagamento.getImovel().getId());
					manterPagamentoActionForm.setDescricaoImovel(""
							+ pagamento.getImovel().getInscricaoFormatada());
				}

				// Seta no form os dados de cliente
				if (pagamento.getCliente() != null) {
					manterPagamentoActionForm.setIdCliente(""
							+ pagamento.getCliente().getId());
					manterPagamentoActionForm.setNomeCliente(pagamento
							.getCliente().getNome());
				}

				// Seta no form os dados de referência da conta
				if (pagamento.getAnoMesReferenciaPagamento() != null
						&& pagamento.getAnoMesReferenciaPagamento() != 0) {
					manterPagamentoActionForm.setReferenciaConta(Util
							.formatarAnoMesParaMesAno(pagamento
									.getAnoMesReferenciaPagamento()));
				}

				// Seta no form os dados da guia de pagamento
				if (pagamento.getGuiaPagamento() != null) {
					manterPagamentoActionForm.setIdGuiaPagamento(""
							+ pagamento.getGuiaPagamento().getId());
					if (pagamento.getGuiaPagamento().getDebitoTipo() != null) {
						manterPagamentoActionForm.setDescricaoGuiaPagamento(""
								+ pagamento.getGuiaPagamento().getDebitoTipo()
										.getDescricao());
					}
					manterPagamentoActionForm.setValorGuiaPagamento(""
							+ Util.formatarMoedaReal(pagamento
									.getGuiaPagamento().getValorDebito()));
				}

				// Seta no form os dados do débito a cobrar
				if (pagamento.getDebitoACobrarGeral() != null) {
					
					manterPagamentoActionForm.setIdDebitoACobrar(""+pagamento.getDebitoACobrarGeral().getId());
					
					if ( pagamento.getDebitoACobrarGeral().getDebitoACobrar() != null ){
                    
                        if ( pagamento.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo() != null) {
    						manterPagamentoActionForm.setDescricaoDebitoACobrar(""
    							+ pagamento.getDebitoACobrarGeral().getDebitoACobrar().
    								getDebitoTipo().getDescricao());
    					}
    					manterPagamentoActionForm.setValorDebitoACobrar("" + 
    						Util.formatarMoedaReal(pagamento.getDebitoACobrarGeral().getDebitoACobrar().getValorDebito()));
                    }else{
                    	 if ( pagamento.getDebitoACobrarGeral().getDebitoACobrarHistorico().getDebitoTipo() != null) {
     						manterPagamentoActionForm.setDescricaoDebitoACobrar(""
     							+ pagamento.getDebitoACobrarGeral().getDebitoACobrarHistorico().
     								getDebitoTipo().getDescricao());
     					}
     					manterPagamentoActionForm.setValorDebitoACobrar("" + 
     						Util.formatarMoedaReal(pagamento.getDebitoACobrarGeral().getDebitoACobrarHistorico().getValorDebito()));
                    }
				}

				if (pagamento.getGuiaPagamento() == null && 
					pagamento.getDebitoACobrarGeral() == null && pagamento.getAnoMesReferenciaPagamento() == null) {
					
					if (pagamento.getDebitoTipo() != null) {
						
						manterPagamentoActionForm.setIdTipoDebito(""+ pagamento.getDebitoTipo().getId());
						manterPagamentoActionForm.setDescricaoTipoDebito(pagamento.getDebitoTipo().getDescricao());
					}
				}

				// Seta no form a data de pagamento
				manterPagamentoActionForm.setDataPagamento(Util
						.formatarData(pagamento.getDataPagamento()));

				// Seta no form o valor de pagamento
				manterPagamentoActionForm.setValorPagamento(""
						+ pagamento.getValorPagamento());

				// Seta no form os dados da situação atual do pagamento
				if (pagamento.getPagamentoSituacaoAtual() != null) {
					manterPagamentoActionForm.setIdSituacaoAtualPagamento(""
							+ pagamento.getPagamentoSituacaoAtual().getId());
				}
				
				if (pagamento.getAvisoBancario().getArrecadador() != null &&
						pagamento.getAvisoBancario().getArrecadador().getCliente() != null){
					
					manterPagamentoActionForm.setNomeClienteArrecadador(
							pagamento.getAvisoBancario().getArrecadador().getCliente().getNome());
				}
				manterPagamentoActionForm.setUltimaAlteracaoPagamento(Util.formatarData(pagamento.getUltimaAlteracao()));
				
			}
		}

		// Cria a flag que vai indicar se o campo de valor de pagamento vai
		// estar habilitado ou não
		String habilitarValorPagamento = null;

		// Caso a situação atual e a anterior da guia não estiverem preenchidas
		// indica na flag que o campo do valor de pagamento vai estar habilitado
		// na página de atualizar
		// Caso contrário indica na flag que o campo do valor de pagamento NÃO
		// vai estar habilitado na página de atualizar
		//Retirado por ordem de aryed,por Sávio Luiz data:22/03/2007
		/*if ((pagamentoSituacaoAtual == null)
				&& (pagamentoSituacaoAnterior == null)) {
		*/	habilitarValorPagamento = "true";
		/*} else {
			habilitarValorPagamento = "false";
		}*/

		// Seta no request a flag que indica se o campo de valor de pagamento
		// vai estar habilitado ou não
		sessao.setAttribute("habilitarValorPagamento", habilitarValorPagamento);

		sessao.setAttribute("pagamento", pagamento); // ?????

	}

}

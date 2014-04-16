package gcom.gui.arrecadacao;

import gcom.arrecadacao.GuiaDevolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Fernanda Paiva
 * @date 10/03/2006
 */
public class InserirGuiaDevolucaoAction extends GcomAction {

	/**
	 * Permite a Inclusao de Guia de Devolucoes
	 * 
	 * [UC0271] Inserir Devolucoes
	 * 
	 * @author Fernanda Paiva
	 * @date 10/03/2006
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Recupera a variável para indicar se o usuário apertou o botão de
		// confirmar da tela de
		// confirmação do wizard
		//String confirmado = httpServletRequest.getParameter("confirmado");

		InserirGuiaDevolucaoActionForm inserirGuiaDevolucaoActionForm = (InserirGuiaDevolucaoActionForm) actionForm;

		String idRegistroAtendimento = inserirGuiaDevolucaoActionForm.getIdRegistroAtendimento();
		String idOrdemServico = inserirGuiaDevolucaoActionForm.getIdOrdemServico();
		String tipoDocumento = inserirGuiaDevolucaoActionForm.getDocumentoTipoHidden();
		String idLocalidade = inserirGuiaDevolucaoActionForm.getIdLocalidadeHidden();
		String referenciaConta = inserirGuiaDevolucaoActionForm.getReferenciaConta();
		String idGuiaPagamento = inserirGuiaDevolucaoActionForm.getIdGuiaPagamento();
		String idDebitoACobrar = inserirGuiaDevolucaoActionForm.getIdDebitoACobrar();
		String idDebitoTipo = inserirGuiaDevolucaoActionForm.getIdTipoDebitoHidden();
		String valorDevolucao = inserirGuiaDevolucaoActionForm.getValorDevolucao();

		String idFuncionarioAnalisa = inserirGuiaDevolucaoActionForm.getIdFuncionarioAnalista();
		String idFuncionarioAutorizador = inserirGuiaDevolucaoActionForm.getIdFuncionarioAutorizador();

		GuiaDevolucao guiaDevolucao = new GuiaDevolucao();
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();

		DocumentoTipo documentoTipo = new DocumentoTipo();

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			OrdemServico ordemServico = new OrdemServico();

			ordemServico = 
				Fachada.getInstancia().recuperaOSPorId(new Integer(idOrdemServico));

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (ordemServico != null) {
				
				Fachada.getInstancia().validarExibirInserirGuiaDevolucao(null,ordemServico);
				
				RegistroAtendimento ra = ordemServico.getRegistroAtendimento();
				
				idRegistroAtendimento = ""+ra.getId();

				if(ra.getImovel() != null){

					if (ra.getImovel().getLocalidade() != null) {
						idLocalidade = ra.getImovel().getLocalidade().getId().toString();
					}
				}
			}else{
				throw new ActionServletException("atencao.naocadastrado", null,"Ordem de Serviço");
			}
			
			guiaDevolucao.setOrdemServico(ordemServico);
		}

		if (idRegistroAtendimento != null && !idRegistroAtendimento.trim().equals("")) {
			
			ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimentoHelper = 
				Fachada.getInstancia().obterDadosRegistroAtendimento(new Integer(idRegistroAtendimento));
			
			if (obterDadosRegistroAtendimentoHelper != null && 
				obterDadosRegistroAtendimentoHelper.getRegistroAtendimento() != null) {

				registroAtendimento = obterDadosRegistroAtendimentoHelper.getRegistroAtendimento();
				
				fachada.validarExibirInserirGuiaDevolucao(registroAtendimento,null);
				
				if(registroAtendimento.getImovel() != null){

					if (registroAtendimento.getImovel().getLocalidade() != null) {
						idLocalidade = registroAtendimento.getImovel().getLocalidade().getId().toString();
					}
				}
				
				
			}else{
				throw new ActionServletException("atencao.naocadastrado", null,"Registro Atendimento");				
			}			
			
			guiaDevolucao.setRegistroAtendimento(registroAtendimento);
			
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,"Registro de Atendimento");
		}

		if (tipoDocumento != null && !tipoDocumento.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			documentoTipo.setId(new Integer(tipoDocumento));
			guiaDevolucao.setDocumentoTipo(documentoTipo);
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,"Tipo de Documento");
		}

		if (idLocalidade != null && !idLocalidade.equals("")) {
			
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(idLocalidade));
			
			guiaDevolucao.setLocalidade(localidade);
		}

		if (referenciaConta != null && !referenciaConta.trim().equals("")) {
			Conta conta = new Conta();
			if (Util.validarAnoMes(referenciaConta)) {
				new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",
					null, referenciaConta);
			}
			String referenciaContaFormatada = Util.formatarMesAnoParaAnoMesSemBarra(referenciaConta);
			conta.setReferencia(new Integer(referenciaContaFormatada).intValue());
			guiaDevolucao.setConta(conta);
		}

		if (idGuiaPagamento != null && !idGuiaPagamento.trim().equals("")) {
			
			GuiaPagamento guiaPagamento = new GuiaPagamento();
			guiaPagamento.setId(new Integer(idGuiaPagamento));
			
			guiaDevolucao.setGuiaPagamento(guiaPagamento);
		}

		if (idDebitoACobrar != null && !idDebitoACobrar.trim().equals("")) {
			
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			debitoACobrarGeral.setId(new Integer(idDebitoACobrar));
			
			guiaDevolucao.setDebitoACobrarGeral(debitoACobrarGeral);
		}

		if (idDebitoTipo != null && !idDebitoTipo.trim().equals("")) {
			
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(new Integer(idDebitoTipo));
			
			guiaDevolucao.setDebitoTipo(debitoTipo);
		}

		if (valorDevolucao != null && !valorDevolucao.trim().equals("")) {
			
			BigDecimal valorDevolucaoFormatado = 
				new BigDecimal(valorDevolucao.replace(".", "").replace(",", "."));
			
			if (valorDevolucaoFormatado.equals(new BigDecimal("0.00")) || 
				valorDevolucaoFormatado.equals(new BigDecimal("0"))) {
				
				throw new ActionServletException("atencao.Informe_entidade",null, "Valor da Devolução");
			} else {
				guiaDevolucao.setValorDevolucao(valorDevolucaoFormatado);
			}
		}
		
		if (idFuncionarioAnalisa != null && !idFuncionarioAnalisa.trim().equals("")) {
			
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(idFuncionarioAnalisa));
			
			guiaDevolucao.setFuncionarioAnalista(funcionario);
		}
		
		if (idFuncionarioAutorizador != null && !idFuncionarioAutorizador.trim().equals("")) {
			
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(idFuncionarioAutorizador));
			
			guiaDevolucao.setFuncionarioAutorizador(funcionario);
		}

		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		filtroRegistroAtendimento.adicionarParametro(
			new ParametroSimples(FiltroRegistroAtendimento.ID, idRegistroAtendimento));

		Collection colecaoRegistroAtendimento = 
			fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());

		if (colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()) {

			//RegistroAtendimento registroAtendimentoVerificacao = (RegistroAtendimento) colecaoRegistroAtendimento
				//	.iterator().next();

			/*--<merge>--if (registroAtendimentoVerificacao.getCliente() != null
					&& documentoTipo.getId().equals(
							DocumentoTipo.DEVOLUCAO_VALOR)) {

				if (idDebitoTipo != null && !idDebitoTipo.trim().equals("")) {

					FiltroGuiaDevolucao filtroGuiaDevolucao = new FiltroGuiaDevolucao();
					filtroGuiaDevolucao
							.adicionarParametro(new ParametroSimples(
									FiltroGuiaDevolucao.CLIENTE_ID,
									registroAtendimentoVerificacao.getCliente()
											.getId()));
					filtroGuiaDevolucao
							.adicionarParametro(new ParametroSimples(
									FiltroGuiaDevolucao.DOCUMENTO_TIPO_ID,
									DocumentoTipo.DEVOLUCAO_VALOR));
					filtroGuiaDevolucao
							.adicionarParametro(new ParametroSimples(
									FiltroGuiaDevolucao.DEBITO_TIPO_ID,
									idDebitoTipo));

					Collection colecaoGuiaDevolucao = fachada.pesquisar(
							filtroGuiaDevolucao, GuiaDevolucao.class.getName());

					if (colecaoGuiaDevolucao != null
							&& !colecaoGuiaDevolucao.isEmpty()) {
						
						// Caso o usuário não tenha passado pela página de
						// confirmação
						// do
						// wizard

						if (confirmado == null
								|| !confirmado.trim().equalsIgnoreCase("ok")) {
							
							FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
							filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, idDebitoTipo));
							
							Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());

							DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();
							
							httpServletRequest.setAttribute(
									"caminhoActionConclusao",
									"/gsan/inserirGuiaDevolucaoAction.do");
							// Monta a página de confirmação do wizard para
							// perguntar se
							// o
							// usuário quer inserir
							// o pagamento mesmo sem a conta existir
							// para a referência e o
							// imóvel informados
							return montarPaginaConfirmacao(
									"atencao.ja.existe.guia.devolucao",
									httpServletRequest, actionMapping, registroAtendimentoVerificacao.getCliente()
									.getId().toString(), debitoTipo.getDescricao());
						}

					}
				}
			}*/

		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente",null, "Registro de Atendimento");
		}

        /** alterado por pedro alexandre dia 21/11/2006 
         * Recupera o usuário logado para passar no metódo de inserir guia de devolução 
         * para verificar se o usuário tem abrangência para inserir a guia de devolução
         * para o imóvel informado.
         */
        HttpSession sessao = httpServletRequest.getSession(false);
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
		//Integer id = fachada.inserirGuiaDevolucao(guiaDevolucao);
        Integer id = fachada.inserirGuiaDevolucao(guiaDevolucao, usuarioLogado);
        
//		montarPaginaSucesso(httpServletRequest, "Guia de Devolução " + id
//				+ " inserida com sucesso.", "Inserir outra Guia de Devolução",
//				"exibirInserirGuiaDevolucaoAction.do?menu=sim",
//				"exibirAtualizarGuiaDevolucaoAction.do?guiaDevolucaoID=" + id,
//				"Atualizar Guia de Devolução inserida",
//				"Emitir Guia de Devolução",
//				"gerarRelatorioGuiaDevolucaoActionInserir.do?idGuiaDevolucao=" + id);
		
		montarPaginaSucesso(httpServletRequest, "Guia de Devolução " + id
				+ " inserida com sucesso.", "Inserir outra Guia de Devolução",
				"exibirInserirGuiaDevolucaoAction.do?menu=sim",
				"gerarRelatorioGuiaDevolucaoActionInserir.do?idGuiaDevolucao=" + id,
				"Emitir Guia de Devolução");
		
		return retorno;

	}

}

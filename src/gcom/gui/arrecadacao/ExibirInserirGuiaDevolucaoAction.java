package gcom.gui.arrecadacao;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
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
 * Permite inserir uma guia de devolução 
 * [UC0322] Inserir Guia de Devolução
 * 
 * @author Rafael Corrêa
 * @since 19/04/2006
 */
public class ExibirInserirGuiaDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno

		ActionForward retorno = actionMapping.findForward("exibirInserirGuiaDevolucao");

		InserirGuiaDevolucaoActionForm inserirGuiaDevolucaoActionForm = (InserirGuiaDevolucaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.adicionarParametro(
			new ParametroSimples(FiltroDocumentoTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
			FiltroDocumentoTipo.ID, DocumentoTipo.CONTA,ConectorOr.CONECTOR_OR, 4));
		
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
			FiltroDocumentoTipo.ID, DocumentoTipo.GUIA_PAGAMENTO,ConectorOr.CONECTOR_OR));
		
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
			FiltroDocumentoTipo.ID, DocumentoTipo.DEBITO_A_COBRAR,ConectorOr.CONECTOR_OR));
		
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
			FiltroDocumentoTipo.ID, DocumentoTipo.DEVOLUCAO_VALOR,ConectorOr.CONECTOR_OR));
		
		filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);

		Collection colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		if (colecaoDocumentoTipo != null && !colecaoDocumentoTipo.isEmpty()) {
			sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Documento");
		}

		// Parte que trata do código quando o usuário tecla enter
		String idRegistroAtendimento = inserirGuiaDevolucaoActionForm.getIdRegistroAtendimento();
		String idOrdemServico = inserirGuiaDevolucaoActionForm.getIdOrdemServico();
		String idLocalidade = inserirGuiaDevolucaoActionForm.getIdLocalidadeHidden();
		String referenciaConta = inserirGuiaDevolucaoActionForm.getReferenciaConta();
		String idGuiaPagamento = inserirGuiaDevolucaoActionForm.getIdGuiaPagamento();
		String idDebitoACobrar = inserirGuiaDevolucaoActionForm.getIdDebitoACobrar();
		String idDebitoTipo = inserirGuiaDevolucaoActionForm.getIdTipoDebitoHidden();

		String idImovel = inserirGuiaDevolucaoActionForm.getIdImovel();
		String idCliente = inserirGuiaDevolucaoActionForm.getCodigoCliente();
		
		String idFuncionarioAnalisa = inserirGuiaDevolucaoActionForm.getIdFuncionarioAnalista();
		String idFuncionarioAutorizador = inserirGuiaDevolucaoActionForm.getIdFuncionarioAutorizador();

		RegistroAtendimento registroAtendimento = null;
		OrdemServico ordemServico = null;

		
		//REGISTRO_ATENDIMENTO
		if (idRegistroAtendimento != null && !idRegistroAtendimento.trim().equals("")) {

			// Faz a consulta de Registro Atendimento
			registroAtendimento = this.pesquisarRegistroAtendimento(idRegistroAtendimento);
			
			if (registroAtendimento != null) {

				inserirGuiaDevolucaoActionForm.setIdRegistroAtendimento(registroAtendimento.getId().toString());
				inserirGuiaDevolucaoActionForm.setNomeRegistroAtendimento(
				registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
				
				if(registroAtendimento.getImovel() != null){

					inserirGuiaDevolucaoActionForm.setIdImovel(registroAtendimento.getImovel().getId().toString());
					inserirGuiaDevolucaoActionForm.setDescricaoImovel(registroAtendimento.getImovel().getInscricaoFormatada());

					httpServletRequest.setAttribute("bloqueioLocalidade", true);

					if (registroAtendimento.getImovel().getLocalidade() != null) {
						idLocalidade = registroAtendimento.getImovel()
								.getLocalidade().getId().toString();
					}
					
					idImovel = ""+registroAtendimento.getImovel().getId();
					
				}
				RegistroAtendimentoSolicitante raSolicitante = 
					pesquisarRegistroAtendimentoSolicitante(idRegistroAtendimento);					

				if(raSolicitante != null){
					
					if(raSolicitante.getCliente() != null){
						idCliente = ""+raSolicitante.getCliente().getId();
						inserirGuiaDevolucaoActionForm.setCodigoCliente(idCliente);
						inserirGuiaDevolucaoActionForm.setNomeCliente(raSolicitante.getCliente().getNome());
					}
					
					httpServletRequest.setAttribute("nomeCampo","documentoTipo");
				}
			} else {
				
				inserirGuiaDevolucaoActionForm.setIdRegistroAtendimento("");
				inserirGuiaDevolucaoActionForm.setNomeRegistroAtendimento("Registro Atendimento inexistente");

				httpServletRequest.setAttribute("nomeCampo","idRegistroAtendimento");
				
			}

		} else {
			httpServletRequest.setAttribute("nomeCampo","idRegistroAtendimento");
			inserirGuiaDevolucaoActionForm.setNomeRegistroAtendimento("");
		}

		
		//ORDEM_SERVICO
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			
			ordemServico = this.pesquisarOrdemServico(idOrdemServico);

			if (ordemServico != null) {

				inserirGuiaDevolucaoActionForm.setIdOrdemServico(""+ordemServico.getId());
				inserirGuiaDevolucaoActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				
				RegistroAtendimento ra = ordemServico.getRegistroAtendimento();
					
				inserirGuiaDevolucaoActionForm.setIdRegistroAtendimento(""+ra.getId());
				inserirGuiaDevolucaoActionForm.setNomeRegistroAtendimento(ra.getSolicitacaoTipoEspecificacao().getDescricao());

				if(ra.getImovel() != null){

					inserirGuiaDevolucaoActionForm.setIdImovel(ra.getImovel().getId().toString());
					inserirGuiaDevolucaoActionForm.setDescricaoImovel(ra.getImovel().getInscricaoFormatada());

					httpServletRequest.setAttribute("bloqueioLocalidade", true);

					if (ra.getImovel().getLocalidade() != null) {
						idLocalidade = ra.getImovel()
								.getLocalidade().getId().toString();
					}
					
					idImovel = ""+ra.getImovel().getId();
					
				}
				
				RegistroAtendimentoSolicitante raSolicitante = 
					pesquisarRegistroAtendimentoSolicitante(""+ra.getId());					

				if(raSolicitante != null){
					
					if(raSolicitante.getCliente() != null){
						idCliente = ""+raSolicitante.getCliente().getId();
						inserirGuiaDevolucaoActionForm.setCodigoCliente(idCliente);
						inserirGuiaDevolucaoActionForm.setNomeCliente(raSolicitante.getCliente().getNome());
					}					
				}
				
				/*if (ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getDebitoTipo() != null) {
					
					idDebitoTipo = ordemServico.getServicoTipo().getDebitoTipo().getId().toString();
					httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
					
					inserirGuiaDevolucaoActionForm.setDocumentoTipo(""+DocumentoTipo.DEVOLUCAO_VALOR);
					inserirGuiaDevolucaoActionForm.setDocumentoTipoHidden(""+DocumentoTipo.DEVOLUCAO_VALOR);
					httpServletRequest.setAttribute("bloqueioDocumentoTipo",true);
				}*/
				
			} else {
				
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				
				inserirGuiaDevolucaoActionForm.setIdOrdemServico("");
				inserirGuiaDevolucaoActionForm.setNomeOrdemServico("Ordem de Serviço inexistente");
			}

		}
		
		if (ordemServico != null && registroAtendimento != null) {
			if (ordemServico.getRegistroAtendimento() != null
					&& !ordemServico.getRegistroAtendimento().getId().equals(
							registroAtendimento.getId())) {
				throw new ActionServletException(
						"atencao.ordem.servico.diferente.registro.atendimento",
						null, idRegistroAtendimento);
			}
		}

		
		//LOCALIDADE
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade
						.iterator().next();

				inserirGuiaDevolucaoActionForm.setIdLocalidade(idLocalidade);
				inserirGuiaDevolucaoActionForm
						.setIdLocalidadeHidden(idLocalidade);
				inserirGuiaDevolucaoActionForm
						.setDescricaoLocalidade(localidade.getDescricao());

			} else {
				
				httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
				
				inserirGuiaDevolucaoActionForm.setIdLocalidade("");
				inserirGuiaDevolucaoActionForm.setIdLocalidadeHidden("");
				inserirGuiaDevolucaoActionForm.setDescricaoLocalidade("Localidade inexistente");
				
				httpServletRequest.setAttribute("localidadeInexistente", true);
			}
		} else {
			inserirGuiaDevolucaoActionForm.setDescricaoLocalidade("");
		}

		
		// [FS0010] - Verificar existência da guia de pagamento
		if (idGuiaPagamento != null && !idGuiaPagamento.trim().equals("")) {

			// Caso o usuário não tenha informado a matrícula do imóvel
			// Levanta uma exceção para o usuário indicado que ele não informou
			// a matrícula do imóvel
			if (idImovel == null || idImovel.trim().equalsIgnoreCase("")) {
				
				if (idCliente == null || idCliente.trim().equalsIgnoreCase("")) {
					throw new ActionServletException("atencao.naoinformado",null, "Imóvel ou Cliente");
				}
			}

			//PESQUISANDO A GUIA DE PAGAMENTO (GUIA_PAGAMENTO E GUIA_PAGAMENTO_HISTORICO)
			Collection colecaoGuiaPagamento = fachada.pesquisarGuiaPagamentoParaGuiaDevolucao(
			new Integer(idImovel), new Integer(idCliente), new Integer(idGuiaPagamento));
			
			//[FS0011] - Verificar imóvel ou cliente da guia de Pagamento
			GuiaPagamentoGeral guiaPagamentoGeral = fachada.verificarGuiaPagamentoParaGuiaDevolucao(
			colecaoGuiaPagamento, registroAtendimento, new Integer(idCliente), 
			new Integer(idLocalidade), ordemServico);

			
			Localidade localidadeGuiaPagamento = null;
			BigDecimal valorDebito = null;
			DebitoTipo debitoTipoGuiaPagamento = null;
			
			if (guiaPagamentoGeral.getGuiaPagamento() != null){
				
				localidadeGuiaPagamento = guiaPagamentoGeral.getGuiaPagamento().getLocalidade();
				valorDebito = guiaPagamentoGeral.getGuiaPagamento().getValorDebito();
				debitoTipoGuiaPagamento = guiaPagamentoGeral.getGuiaPagamento().getDebitoTipo();
			}
			else{
				
				localidadeGuiaPagamento = guiaPagamentoGeral.getGuiaPagamentoHistorico().getLocalidade();
				valorDebito = guiaPagamentoGeral.getGuiaPagamentoHistorico().getValorDebito();
				debitoTipoGuiaPagamento = guiaPagamentoGeral.getGuiaPagamentoHistorico().getDebitoTipo();
			}
				
			//DEBITO_TIPO
			inserirGuiaDevolucaoActionForm.setDescricaoGuiaPagamento(debitoTipoGuiaPagamento.getDescricao());
			
			//SETA A LOCALIDADE DA GUIA DE PAGAMENTO
			inserirGuiaDevolucaoActionForm.setIdLocalidade(localidadeGuiaPagamento.getId().toString());
			inserirGuiaDevolucaoActionForm.setDescricaoLocalidade(localidadeGuiaPagamento.getDescricao());

			if (httpServletRequest.getAttribute("RegistroAtendimentoSemImovel") == null &&
				valorDebito != null) {

				inserirGuiaDevolucaoActionForm.setValorDevolucao(Util.formatarMoedaReal(valorDebito));
				inserirGuiaDevolucaoActionForm.setValorGuiaPagamento("" + Util.formatarMoedaReal(valorDebito));
					
			}

			httpServletRequest.setAttribute("guiaPagamentoInexistente", false);
		} 
		else {
			
			inserirGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
			inserirGuiaDevolucaoActionForm.setValorGuiaPagamento("");
		}

		// [FS0017] - Verificar existência do tipo de débito
		if (idDebitoTipo != null && !idDebitoTipo.equals("")) {
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID, idDebitoTipo));

			Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
					DebitoTipo.class.getName());

			if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {
				DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo
						.iterator().next();
				inserirGuiaDevolucaoActionForm.setIdTipoDebito(idDebitoTipo);
				inserirGuiaDevolucaoActionForm
						.setIdTipoDebitoHidden(idDebitoTipo);
				inserirGuiaDevolucaoActionForm
						.setDescricaoTipoDebito(debitoTipo.getDescricao());
			} else {
				httpServletRequest.setAttribute("nomeCampo", "idTipoDebito");
				inserirGuiaDevolucaoActionForm.setIdTipoDebito("");
				inserirGuiaDevolucaoActionForm.setIdTipoDebitoHidden("");
				inserirGuiaDevolucaoActionForm
						.setDescricaoTipoDebito("TIPO DE DÉBITO INEXISTENTE");
				httpServletRequest.setAttribute("tipoDebitoInexistente", true);
			}
		} else {
			inserirGuiaDevolucaoActionForm.setDescricaoTipoDebito("");
		}

		// [FS0008] - Verificar existência da conta

		// Caso o usuário tenha informado a referência da conta
		if (referenciaConta != null && !referenciaConta.trim().equalsIgnoreCase("")) {

			// Valida a referência da conta
			if (!Util.validarAnoMes(referenciaConta)) {

				/*
				 * Caso o usuário não tenha informado a matrícula do imóvel.
				 */
				if (registroAtendimento.getImovel() == null) {
					throw new ActionServletException("atencao.naoinformado", null, "Imóvel");
				}

				//PESQUISANDO A CONTA (CONTA E CONTA_HISTORICO)
				Collection colecaoConta = fachada.pesquisarContaParaGuiaDevolucao(registroAtendimento.getImovel(), 
				new Integer(Util.formatarMesAnoParaAnoMesSemBarra(referenciaConta)));
				
				//[FS0009] - Verificar localidade da conta
				ContaGeral contaGeral = fachada.retornaContaGeral(colecaoConta);
				BigDecimal valorTotal = null;
				
				if (contaGeral.getConta() != null){
					valorTotal = contaGeral.getConta().getValorTotal();
				}
				else{
					valorTotal = contaGeral.getContaHistorico().getValorTotal();
				}
				
				inserirGuiaDevolucaoActionForm.setDescricaoConta("" + Util.formatarMoedaReal(valorTotal));
				inserirGuiaDevolucaoActionForm.setValorDevolucao(Util.formatarMoedaReal(valorTotal));
				httpServletRequest.setAttribute("contaInexistente", false);

			} 
			else {
				throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",
				null, referenciaConta);
			}
		} 
		else {
			
			inserirGuiaDevolucaoActionForm.setDescricaoConta("");
		}

		// [FS0013] - Verificar existência do débito a cobrar

		// Caso o usuário tenha informado o código do débito a cobrar
		if (idDebitoACobrar != null && !idDebitoACobrar.trim().equalsIgnoreCase("")) {

			/*
			 * Caso o usuário não tenha informado a matrícula do imóvel.
			 */
			if (registroAtendimento.getImovel() == null) {
				throw new ActionServletException("atencao.naoinformado", null, "Imóvel");
			}

			//PESQUISANDO O DEBITO A COBRAR (DEBITO_A_COBRAR E DEBITO_A_COBRAR_HISTORICO)
			Collection colecaoDebitoACobrar = fachada.pesquisarDebitoACobrarParaGuiaDevolucao(
			registroAtendimento.getImovel(), new Integer(idDebitoACobrar));
			
			/*
			 * [FS0014] - Verificar imóvel do débito a cobrar
			 * [FS0015] - Verificar localidade do débito a cobrar
			 */
			DebitoACobrarGeral debitoACobrarGeral = fachada.verificarDebitoACobrarParaGuiaDevolucao(
			colecaoDebitoACobrar, registroAtendimento.getImovel().getId(), registroAtendimento.getImovel()
			.getLocalidade().getId(), ordemServico);

			BigDecimal valorDevolucao = null;
			DebitoTipo debitoTipo = null;
			
			if (debitoACobrarGeral.getDebitoACobrar() != null){
				valorDevolucao = debitoACobrarGeral.getDebitoACobrar().getValorTotal();
				debitoTipo = debitoACobrarGeral.getDebitoACobrar().getDebitoTipo();
			}
			else{
				valorDevolucao = debitoACobrarGeral.getDebitoACobrarHistorico().getValorTotal();
				debitoTipo = debitoACobrarGeral.getDebitoACobrarHistorico().getDebitoTipo();
			}
			
			if (valorDevolucao != null) {
				
				inserirGuiaDevolucaoActionForm.setValorDevolucao(Util
				.formatarMoedaReal(valorDevolucao));
				inserirGuiaDevolucaoActionForm.setValorDebitoACobrar(Util
				.formatarMoedaReal(valorDevolucao));
			}

			inserirGuiaDevolucaoActionForm.setDescricaoDebitoACobrar(debitoTipo.getDescricao());

		} 
		else {
			
			inserirGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
			inserirGuiaDevolucaoActionForm.setValorDebitoACobrar("");
		}

		String documentoTipo = inserirGuiaDevolucaoActionForm.getDocumentoTipo();
		
		if(idImovel == null || idImovel.equals("")){
			if (documentoTipo != null &&
				(documentoTipo.equals(""+DocumentoTipo.CONTA) || 
				documentoTipo.equals(""+DocumentoTipo.DEBITO_A_COBRAR))) {
						
				throw new ActionServletException("atencao.registro.atendimento.sem.imovel", null,idRegistroAtendimento);
			}
		}

		// [SB0003] - Habilitar/Desabilitar Campos

		// Parte que faz as verificações para habilitar e desabilitar alguns
		// campos.

		if (inserirGuiaDevolucaoActionForm.getDocumentoTipo() != null
				&& !inserirGuiaDevolucaoActionForm.getDocumentoTipo().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			if (inserirGuiaDevolucaoActionForm.getDocumentoTipo().equals(
					DocumentoTipo.CONTA.toString())) {
				inserirGuiaDevolucaoActionForm.setIdGuiaPagamento("");
				inserirGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
				inserirGuiaDevolucaoActionForm.setValorGuiaPagamento("");
				inserirGuiaDevolucaoActionForm.setIdDebitoACobrar("");
				inserirGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
				inserirGuiaDevolucaoActionForm.setValorDebitoACobrar("");

				if (referenciaConta == null
						|| referenciaConta.trim().equals("")) {

					inserirGuiaDevolucaoActionForm.setValorDevolucao("");

				}

				if (ordemServico == null
						|| ordemServico.getServicoTipo() == null
						|| ordemServico.getServicoTipo().getDebitoTipo() == null) {
					inserirGuiaDevolucaoActionForm.setIdTipoDebito("");
					inserirGuiaDevolucaoActionForm.setIdTipoDebitoHidden("");
					inserirGuiaDevolucaoActionForm.setDescricaoTipoDebito("");
				}
				httpServletRequest.setAttribute("bloqueioLocalidade", true);
				httpServletRequest.setAttribute("bloqueioGuia", true);
				httpServletRequest.setAttribute("bloqueioDebitoACobrar", true);
				httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
			} else if (inserirGuiaDevolucaoActionForm.getDocumentoTipo()
					.equals(DocumentoTipo.GUIA_PAGAMENTO.toString())) {
				inserirGuiaDevolucaoActionForm.setReferenciaConta("");
				inserirGuiaDevolucaoActionForm.setDescricaoConta("");
				inserirGuiaDevolucaoActionForm.setIdDebitoACobrar("");
				inserirGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
				inserirGuiaDevolucaoActionForm.setValorDebitoACobrar("");

				if (idGuiaPagamento == null
						|| idGuiaPagamento.trim().equals("")) {
					inserirGuiaDevolucaoActionForm.setValorDevolucao("");
				}

				if (ordemServico == null
						|| ordemServico.getServicoTipo() == null
						|| ordemServico.getServicoTipo().getDebitoTipo() == null) {
					inserirGuiaDevolucaoActionForm.setIdTipoDebito("");
					inserirGuiaDevolucaoActionForm.setIdTipoDebitoHidden("");
					inserirGuiaDevolucaoActionForm.setDescricaoTipoDebito("");
				}

				httpServletRequest.setAttribute("bloqueioLocalidade", true);
				httpServletRequest.setAttribute("bloqueioDebitoACobrar", true);
				httpServletRequest.setAttribute("bloqueioConta", true);
				httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
			} else if (inserirGuiaDevolucaoActionForm.getDocumentoTipo()
					.equals(DocumentoTipo.DEBITO_A_COBRAR.toString())) {
				inserirGuiaDevolucaoActionForm.setReferenciaConta("");
				inserirGuiaDevolucaoActionForm.setDescricaoConta("");
				inserirGuiaDevolucaoActionForm.setIdGuiaPagamento("");
				inserirGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
				inserirGuiaDevolucaoActionForm.setValorGuiaPagamento("");
				if (idDebitoACobrar == null
						|| idDebitoACobrar.trim().equals("")) {
					inserirGuiaDevolucaoActionForm.setValorDevolucao("");
				}

				if (ordemServico == null
						|| ordemServico.getServicoTipo() == null
						|| ordemServico.getServicoTipo().getDebitoTipo() == null) {
					inserirGuiaDevolucaoActionForm.setIdTipoDebito("");
					inserirGuiaDevolucaoActionForm.setIdTipoDebitoHidden("");
					inserirGuiaDevolucaoActionForm.setDescricaoTipoDebito("");
				}

				httpServletRequest.setAttribute("bloqueioLocalidade", true);
				httpServletRequest.setAttribute("bloqueioGuia", true);
				httpServletRequest.setAttribute("bloqueioConta", true);
				httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
			} else if (inserirGuiaDevolucaoActionForm.getDocumentoTipo()
					.equals(DocumentoTipo.DEVOLUCAO_VALOR.toString())) {
				inserirGuiaDevolucaoActionForm.setReferenciaConta("");
				inserirGuiaDevolucaoActionForm.setDescricaoConta("");
				inserirGuiaDevolucaoActionForm.setIdGuiaPagamento("");
				inserirGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
				inserirGuiaDevolucaoActionForm.setValorGuiaPagamento("");
				inserirGuiaDevolucaoActionForm.setIdDebitoACobrar("");
				inserirGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
				inserirGuiaDevolucaoActionForm.setValorDebitoACobrar("");
				inserirGuiaDevolucaoActionForm.setValorDevolucao("");

				httpServletRequest.setAttribute("bloqueioDebitoACobrar", true);
				httpServletRequest.setAttribute("bloqueioGuia", true);
				httpServletRequest.setAttribute("bloqueioConta", true);
			}
		} else {
			inserirGuiaDevolucaoActionForm.setReferenciaConta("");
			inserirGuiaDevolucaoActionForm.setDescricaoConta("");
			inserirGuiaDevolucaoActionForm.setIdGuiaPagamento("");
			inserirGuiaDevolucaoActionForm.setDescricaoGuiaPagamento("");
			inserirGuiaDevolucaoActionForm.setIdDebitoACobrar("");
			inserirGuiaDevolucaoActionForm.setDescricaoDebitoACobrar("");
			inserirGuiaDevolucaoActionForm.setValorDevolucao("");

			if (ordemServico == null || ordemServico.getServicoTipo() == null
					|| ordemServico.getServicoTipo().getDebitoTipo() == null) {
				inserirGuiaDevolucaoActionForm.setIdTipoDebito("");
				inserirGuiaDevolucaoActionForm.setIdTipoDebitoHidden("");
				inserirGuiaDevolucaoActionForm.setDescricaoTipoDebito("");
			}

			httpServletRequest.setAttribute("bloqueioLocalidade", true);
			httpServletRequest.setAttribute("bloqueioGuia", true);
			httpServletRequest.setAttribute("bloqueioConta", true);
			httpServletRequest.setAttribute("bloqueioDebitoTipo", true);
			httpServletRequest.setAttribute("bloqueioDebitoACobrar", true);

		}

		//[UC0234] - Pesquisar Funcionário
		if(idFuncionarioAnalisa != null && !idFuncionarioAnalisa.equals("")){
			Funcionario funcionario = null;
			
			funcionario = this.pesquisarFuncionario(idFuncionarioAnalisa);
			
			if(funcionario != null){
				inserirGuiaDevolucaoActionForm.setIdFuncionarioAnalista(""+funcionario.getId());
				inserirGuiaDevolucaoActionForm.setNomeFuncionarioAnalista(funcionario.getNome());
			}else{
				inserirGuiaDevolucaoActionForm.setIdFuncionarioAnalista("");
				inserirGuiaDevolucaoActionForm.setNomeFuncionarioAnalista("Funcionário inexistente");
			}
		}
		
		//[UC0234] - Pesquisar Funcionário
		if(idFuncionarioAutorizador != null && !idFuncionarioAutorizador.equals("")){
			Funcionario funcionario = null;
			
			funcionario = this.pesquisarFuncionario(idFuncionarioAutorizador);
			
			if(funcionario != null){
				inserirGuiaDevolucaoActionForm.setIdFuncionarioAutorizador(""+funcionario.getId());
				inserirGuiaDevolucaoActionForm.setNomeFuncionarioAutorizador(funcionario.getNome());
			}else{
				inserirGuiaDevolucaoActionForm.setIdFuncionarioAutorizador("");
				inserirGuiaDevolucaoActionForm.setNomeFuncionarioAutorizador("Funcionário inexistente");
			}
		}

		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,inserirGuiaDevolucaoActionForm);
		
		
		return retorno;

	}
	
	
	/**
	 * Pesquisa Registro Atendimento 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private RegistroAtendimento pesquisarRegistroAtendimento(String idRa) {
		
		RegistroAtendimento registroAtendimento = null;

		// Pesquisa de acordo com os parâmetros informados no filtro
		ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimentoHelper = 
			Fachada.getInstancia().obterDadosRegistroAtendimento(new Integer(idRa));

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (obterDadosRegistroAtendimentoHelper != null && 
			obterDadosRegistroAtendimentoHelper.getRegistroAtendimento() != null ) {

			registroAtendimento = obterDadosRegistroAtendimentoHelper.getRegistroAtendimento();

			Fachada.getInstancia().validarExibirInserirGuiaDevolucao(registroAtendimento,null);
		} 
		
		return registroAtendimento;
	}
	
	/**
	 * Pesquisa Registro Atendimento 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private RegistroAtendimentoSolicitante pesquisarRegistroAtendimentoSolicitante(String idRa) {
		
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = null;
		
		FiltroRegistroAtendimentoSolicitante filtro = new FiltroRegistroAtendimentoSolicitante();
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, new Integer(idRa)));
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimentoSolicitante.INDICADOR_SOLICITANTE_PRINCIPAL, ConstantesSistema.SIM));

		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecao = 
			Fachada.getInstancia().pesquisar(filtro, RegistroAtendimentoSolicitante.class.getName());

		// Caso exista o débito a cobrar para o imóvel informado cadastrado no
		// sistema
		// Retorna para o usuário o débito a cobrar retornado pela pesquisa
		// Caso contrário retorna um objeto nulo
		if (colecao != null && !colecao.isEmpty()) {
			registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(colecao);
		}
		
		return registroAtendimentoSolicitante;
	}
	/**
	 * Pesquisa Ordem Serviço 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private OrdemServico pesquisarOrdemServico(String idOrdemServico) {
		
		// Pesquisa de acordo com os parâmetros informados no filtro
		OrdemServico ordemServico = 
			Fachada.getInstancia().recuperaOSPorId(new Integer(idOrdemServico));

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (ordemServico != null) {
			Fachada.getInstancia().validarExibirInserirGuiaDevolucao(null,ordemServico);
		}
		
		return ordemServico;
	}
	
	/**
	 * Pesquisa Ordem Serviço 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private Funcionario pesquisarFuncionario(String idFuncionario) {
		
		// Pesquisa de acordo com os parâmetros informados no filtro
		Funcionario funcionario = null;

		FiltroFuncionario filtro = new FiltroFuncionario();
		filtro.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, new Integer(idFuncionario)));

		Collection colecao = 
			Fachada.getInstancia().pesquisar(filtro, Funcionario.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecao);
		}
		
		return funcionario;
	}
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,InserirGuiaDevolucaoActionForm form){
		
		//Registro Atendimento
		if(form.getIdRegistroAtendimento() != null && !form.getIdRegistroAtendimento().equals("") && 
			form.getNomeRegistroAtendimento() != null && !form.getNomeRegistroAtendimento().equals("")){
					
			httpServletRequest.setAttribute("numeroRAEncontrada","true");
		}
		
		//Ordem de Servico
		if(form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("") && 
			form.getNomeOrdemServico() != null && !form.getNomeOrdemServico().equals("")){

			httpServletRequest.setAttribute("ordemServicoEncontrada","true");
		}

		//Funcionário Analista
		if(form.getIdFuncionarioAnalista() != null && !form.getIdFuncionarioAnalista().equals("") && 
			form.getNomeFuncionarioAnalista() != null && !form.getNomeFuncionarioAnalista().equals("")){

			httpServletRequest.setAttribute("funcionarioAnalistaEncontrado","true");
		}
		
		//Funcionário Autorizador
		if(form.getIdFuncionarioAutorizador() != null && !form.getIdFuncionarioAutorizador().equals("") && 
			form.getNomeFuncionarioAutorizador() != null && !form.getNomeFuncionarioAutorizador().equals("")){

			httpServletRequest.setAttribute("funcionarioAutorizadorEncontrado","true");
		}
		
	}
}

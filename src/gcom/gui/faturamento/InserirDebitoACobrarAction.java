package gcom.gui.faturamento;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaForma;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir Débito A Cobrar do Imovel
 * 
 * @author Rafael Santos
 * @since 21/12/2005 [UC0183] Inserir Débito a Cobrar
 * 
 */
public class InserirDebitoACobrarAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		if ( "confirmaEncerramentoRA".equals( httpServletRequest.getParameter("tipoRelatorio") ) ){
            sessao.setAttribute( "confirmaEncerramentoRA", httpServletRequest.getParameter( "confirmado" ) );
        }
		
		String valorConfirmacaoEncerramentoRA = (String) sessao.getAttribute( "confirmaEncerramentoRA" );
		
		// Instância do formulário que está sendo utilizado
		InserirDebitoACobrarActionForm inserirDebitoACobrarActionForm = (InserirDebitoACobrarActionForm) actionForm;
		
		Imovel imovel = null;
		if (sessao.getAttribute("imovelPesquisado") != null) {
			imovel = (Imovel) sessao.getAttribute("imovelPesquisado");
		}
		
		Integer numeroPrestacoes = new Integer(inserirDebitoACobrarActionForm
				.getNumeroPrestacoes().replace(',', '.'));
		/*String valorTotalServicoAParcelar = inserirDebitoACobrarActionForm
		 .getValorTotalServicoAParcelar();*/
		BigDecimal valorTotalServico = Util
		.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
				.getValorTotalServico());
		BigDecimal percentualAbatimento = null;
		
		if (inserirDebitoACobrarActionForm.getPercentualAbatimento() != null
				&& !inserirDebitoACobrarActionForm.getPercentualAbatimento()
				.equals("")) {
			
			percentualAbatimento = Util
			.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
					.getPercentualAbatimento());
		} else {
			percentualAbatimento = new BigDecimal("0.00");
		}
		
		BigDecimal valorEntrada = null;
		if (inserirDebitoACobrarActionForm.getValorEntrada() != null
				&& !inserirDebitoACobrarActionForm.getValorEntrada().equals("")) {
			
			valorEntrada = Util
			.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
					.getValorEntrada());
		}
		
		String idDebitoTipo = inserirDebitoACobrarActionForm.getIdTipoDebito();
		
		// [FS0008] Verificar existência de débito a cobrar para o registro de
		// atendimento - valida se o débito já estiver associado ao RA passado
		//mostra uma mensagem avisando que o débito a cobrar já foi cadastrado para esse registro de atendimento
		if ((idDebitoTipo != null && !idDebitoTipo.equals(""))
				&& (inserirDebitoACobrarActionForm.getRegistroAtendimento() != null && !inserirDebitoACobrarActionForm
						.getRegistroAtendimento().equals(""))) {
			
			FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
			filtroDebitoACobrar.adicionarParametro(new ParametroSimples(
					FiltroDebitoACobrar.ID_REGISTRO_ATENDIMENTO,
					inserirDebitoACobrarActionForm.getRegistroAtendimento()));
			
			filtroDebitoACobrar.adicionarParametro(new ParametroSimples(
					FiltroDebitoACobrar.DEBITO_TIPO_ID, idDebitoTipo));
			
			filtroDebitoACobrar
			.adicionarParametro(new ParametroSimplesDiferenteDe(
					FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
					DebitoCreditoSituacao.CANCELADA));
			
			Collection colecaoDebitoACobrar = fachada.pesquisar(
					filtroDebitoACobrar, DebitoACobrar.class.getName());
			
			if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty() && (valorConfirmacaoEncerramentoRA == null || "".equals(valorConfirmacaoEncerramentoRA))) {
				throw new ActionServletException("atencao.debito_a_cobrar.ja.cadastrado","exibirInserirDebitoACobrarAction.do",new Exception(),"");
			}
		}
		
		// validar debito tipo
		if ((inserirDebitoACobrarActionForm.getOrdemServico() != null && !inserirDebitoACobrarActionForm
				.getOrdemServico().equals(""))
				&& (idDebitoTipo != null && !idDebitoTipo.equals(""))) {
			
			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(new ParametroSimples(
					FiltroOrdemServico.ID, inserirDebitoACobrarActionForm
					.getOrdemServico()));
			
			filtroOrdemServico
			.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.DEBITO_TIPO);
			
			Collection colecaoOrdemServico = fachada.pesquisar(
					filtroOrdemServico, OrdemServico.class.getName());
			if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {
				
				OrdemServico ordemServico = (OrdemServico) colecaoOrdemServico
				.iterator().next();
				if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
					idDebitoTipo = ordemServico.getServicoTipo()
					.getDebitoTipo().getId().toString();
				}
			}
		}
		
		DebitoACobrar debitoACobrar = new DebitoACobrar();
		debitoACobrar.setGeracaoDebito(new Date());
		
		debitoACobrar.setValorDebito(Util
				.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
						.getValorTotalServicoAParcelar()));
		debitoACobrar.setNumeroPrestacaoDebito(new Short(
				inserirDebitoACobrarActionForm.getNumeroPrestacoes())
				.shortValue());
		
		debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));
		debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial()
				.getCodigo());
		debitoACobrar.setNumeroQuadra(new Integer(imovel.getQuadra()
				.getNumeroQuadra()));
		debitoACobrar.setNumeroLote(imovel.getLote());
		debitoACobrar.setNumeroSubLote(imovel.getSubLote());
		BigDecimal valorTaxa = new BigDecimal("0.00");
		
		if (inserirDebitoACobrarActionForm.getTaxaJurosFinanciamento() != null
				&& !inserirDebitoACobrarActionForm.getTaxaJurosFinanciamento()
				.equalsIgnoreCase("")) {
			
			valorTaxa = Util
			.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm
					.getTaxaJurosFinanciamento());
		}
		debitoACobrar.setPercentualTaxaJurosFinanciamento(valorTaxa);
		
		// registro de atendimento
		RegistroAtendimento registroAtendimento = null;
		if (inserirDebitoACobrarActionForm.getRegistroAtendimento() != null
				&& !inserirDebitoACobrarActionForm.getRegistroAtendimento()
				.trim().equals("")) {
			registroAtendimento = new RegistroAtendimento();
			registroAtendimento.setId(new Integer(
					inserirDebitoACobrarActionForm.getRegistroAtendimento()));
		}
		
		debitoACobrar.setRegistroAtendimento(registroAtendimento);
		
		// ordem de servico
		if (inserirDebitoACobrarActionForm.getOrdemServico() != null
				&& !inserirDebitoACobrarActionForm.getOrdemServico().equals("")) {
			
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(new Integer(inserirDebitoACobrarActionForm
					.getOrdemServico()));
			
			debitoACobrar.setOrdemServico(ordemServico);
		} else {
			debitoACobrar.setOrdemServico(null);
		}
		
		debitoACobrar.setImovel(imovel);
		debitoACobrar.setQuadra(imovel.getQuadra());
		debitoACobrar.setLocalidade(imovel.getLocalidade());
		
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		/*filtroDebitoTipo
		 .adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoTipo.FINANCIAMENTO_TIPO);*/
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				FiltroDebitoTipo.ID, idDebitoTipo));
		/*filtroDebitoTipo
		 .adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoTipo.LANCAMENTO_ITEM_CONTABIL_ID);*/
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		
		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
				DebitoTipo.class.getName());
		DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator()
		.next();
		debitoTipo.setId(new Integer(idDebitoTipo));
		
		/*
		 * Autor: Hugo Leonardo
		 * Data: 08/10/2010
		 * CRC_4540
		 * Analista: Ana Cristina
		 * 
		 * */
		
		// [FS0006] - Verificar tipo de financaimento do tipo de débito
		if (debitoTipo.getFinanciamentoTipo().getIndicadorInclusao().shortValue() == FinanciamentoTipo.INDICADOR_INCLUSAO_NAO.shortValue() 
				|| debitoTipo.getIndicadorGeracaoAutomatica().shortValue() == 1) {
			
			throw new ActionServletException(
					"atencao.tipo_financiamento.tipo_debito.nao.permite.inserir.debito_a_cobrar",
					debitoTipo.getFinanciamentoTipo().getDescricao(),
					debitoTipo.getDescricao());
		}
		
		// obter o financiamento tipo do tipo de debito
		FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
		filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(
				FiltroFinanciamentoTipo.ID, debitoTipo.getFinanciamentoTipo()
				.getId()));
		
		Collection colecaoFinaciamentoTipo = fachada.pesquisar(
				filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
		
		FinanciamentoTipo financiamentoTipo = null;
		if (colecaoFinaciamentoTipo != null
				&& !colecaoFinaciamentoTipo.isEmpty()) {
			financiamentoTipo = (FinanciamentoTipo) colecaoFinaciamentoTipo
			.iterator().next();
		}
		
		debitoACobrar.setFinanciamentoTipo(financiamentoTipo);
		debitoACobrar.setDebitoTipo(debitoTipo);
		debitoACobrar.setLancamentoItemContabil(debitoTipo
				.getLancamentoItemContabil());
		
		CobrancaForma cobrancaForma = new CobrancaForma();
		cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);
		debitoACobrar.setCobrancaForma(cobrancaForma);
		debitoACobrar.setUltimaAlteracao(new Date());
		
		/**
		 * alterado por pedro alexandre dia 21/11/2006 Recupera o usuário logado
		 * para passar no metódo de inserir débito a cobrar para verificar se o
		 * usuário tem abrangência para inserir um débito a cobrar para o imóvel
		 * informado.
		 */
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		 		
		
		//De acordo com o UC0183 após o débito ter sido incluído no sistema, se o débito a cobrar
		//tiver usado um RA, é preciso perguntar após a inclusão do débito - a qual ocorreu na chamada
		//do método acima se o usuário deseja encerrar o registro de atendimento
		
		//verifica se o objeto que representa o débito a cobrar a ser incluso é nulo
		if(debitoACobrar != null ){
			//verifica se o débito a cobrar foi incluso associado a um RA para realizar as validações
			//do UC0183 descritas acima
			if(valorConfirmacaoEncerramentoRA == null || "".equals(valorConfirmacaoEncerramentoRA) ){
				fachada.inserirDebitoACobrar(numeroPrestacoes, debitoACobrar,
						valorTotalServico, imovel, percentualAbatimento, valorEntrada,
						usuarioLogado);
				
				//caso o registro de atendimento do debito a cobrar seja diferente de nulo
				if(debitoACobrar.getRegistroAtendimento() != null){
					//caso o registro de atendimento da ordem de serviço do debito a cobrar seja diferente de nulo
					
						httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/inserirDebitoACobrarAction.do");
						httpServletRequest.setAttribute("cancelamento", "TRUE");
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao2", "Não");
						httpServletRequest.setAttribute( "tipoRelatorio", "confirmaEncerramentoRA");
						
						
						//monta a Pagina de confirmação do encerramento do RA da OS caso a OS esteja associada a um RA					
						return montarPaginaConfirmacao("atencao.encerrar_RA_utilizado_no_inserir_debito_a_cobrar",
								httpServletRequest, actionMapping);
					
				}
				
			}else if(valorConfirmacaoEncerramentoRA.equals("ok")){
				//Se entrou aqui, é porque o usuario clicou em Sim, para o encerramento do Registro de Atendimento
				//associado à ordem de serviço, dessa forma, esse código, trata todas as questões pertinentes
				//ao encerramento do registro e por fim, encerra o RA
				
				//Pega o registro de atendimento do débito a cobrar para ser encerrado
				RegistroAtendimento ra = debitoACobrar.getRegistroAtendimento();
				
				AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
				atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);
				atendimentoMotivoEncerramento.setIndicadorExecucao(AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM);
				
				ra.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
				ra.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
				
				ra.setDataEncerramento(new Date());
				
				ra.setObservacao("ENCERRADO NA INCLUSÃO DO DÉBITO A COBRAR");
				ra.setParecerEncerramento("RA ENCERRADO NA INCLUSÃO DO DÉBITO A COBRAR");
				
				ra.setUltimaAlteracao(new Date());
				
				//cria o objeto para a inserção do registro de atendimento
				//unidade
				RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
				registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
				
				if (usuarioLogado.getUnidadeOrganizacional() != null && 
						!usuarioLogado.getUnidadeOrganizacional().equals("")) {
					
					registroAtendimentoUnidade.setUnidadeOrganizacional(
							usuarioLogado.getUnidadeOrganizacional());
				}
				registroAtendimentoUnidade.setUsuario(usuarioLogado);
				registroAtendimentoUnidade.setUltimaAlteracao(new Date());
				
				AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
				atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
				
				registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
				
				//booleano false para não gerar um novo RA
				Boolean confirmadoGeracaoNovoRA = false;
				
				//encerra o RA		
				
				fachada.encerrarRegistroAtendimento(
							ra,
							registroAtendimentoUnidade, 
							usuarioLogado, 
							null, 
							null, 
							null, 
							null, 
							confirmadoGeracaoNovoRA,"exibirInserirDebitoACobrarAction.do?menu=sim",true);					
				

			}
			
		}
		
		if (sessao.getAttribute("imovelPesquisado") != null) {
			sessao.removeAttribute("imovelPesquisado");
		}
		
		/*
		 * montarPaginaSucesso(httpServletRequest, "Débito a cobrar " +
		 * debitoTipo.getDescricao() +" para o imóvel " + imovel.getId() +"
		 * incluída com sucesso", "Inserir outro Débito a Cobrar",
		 * "exibirInserirDebitoACobrarAction.do?menu=sim");
		 */
		
		// 6.7 caso o valor da enrada tenha sido informadao
		if (inserirDebitoACobrarActionForm.getValorEntrada() != null
				&& !inserirDebitoACobrarActionForm.getValorEntrada().equals("")) {
			
			StringBuffer parametros = new StringBuffer("?menu=sim");
			
			parametros.append("&idImovel=" + imovel.getId());
			
			if (debitoACobrar.getOrdemServico() != null) {
				parametros.append("&ordemServico="
						+ debitoACobrar.getOrdemServico().getId());
			} else if(debitoACobrar.getRegistroAtendimento() != null) {
				parametros.append("&registroAtendimento="
						+ registroAtendimento.getId());
			}
			
			parametros.append("&idTipoDebito=" + idDebitoTipo);
			parametros.append("&dataVencimento="
					+ Util.formatarData(new Date()));
			parametros.append("&valorDebito="
					+ inserirDebitoACobrarActionForm.getValorEntrada());
			
			montarPaginaSucesso(httpServletRequest, "Débito a cobrar "
					+ debitoTipo.getDescricao() + " para o imóvel "
					+ imovel.getId() + " incluído com sucesso",
					"Inserir outro Débito a Cobrar",
					"exibirInserirDebitoACobrarAction.do?menu=sim",
					"exibirManterDebitoACobrarAction.do?idRegistroInseridoManter="
					+ imovel.getId(),
					"Cancelar Débito(s) a Cobrar do imóvel " + imovel.getId(),
					"Inserir Guia de Pagamento",
					"exibirInserirGuiaPagamentoAction.do"
					+ parametros.toString());
			
		} else {
			
			montarPaginaSucesso(httpServletRequest, "Débito a cobrar "
					+ debitoTipo.getDescricao() + " para o imóvel "
					+ imovel.getId() + " incluído com sucesso",
					"Inserir outro Débito a Cobrar",
					"exibirInserirDebitoACobrarAction.do?menu=sim",
					"exibirManterDebitoACobrarAction.do?idRegistroInseridoManter="
					+ imovel.getId(),
					"Cancelar Débito(s) a Cobrar do imóvel " + imovel.getId());
			
		}
		
		return retorno;
	}
	
}

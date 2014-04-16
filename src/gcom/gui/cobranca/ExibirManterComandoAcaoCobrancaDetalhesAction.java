package gcom.gui.cobranca;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0244] Manter Comando de Ação de Conbrança Visualiza os Dados do Comando
 * Ação Cobrançan para atualizar
 * 
 * @author Rafael Santos
 * @since 24/03/2006
 */
public class ExibirManterComandoAcaoCobrancaDetalhesAction extends GcomAction {

	private Collection colecaoPesquisa = null;

	private String localidadeID = null;

	// private String gerenciaRegionalID = null;

	private String setorComercialCD = null;

	private HttpSession sessao;

	/**
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
		ActionForward retorno = actionMapping
				.findForward("exibirManterComandoAcaoCobrancaDetalhes");

		// Mudar isso quando implementar a parte de segurança
		sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String validarCriterio = (String) httpServletRequest
				.getParameter("validarCriterio");

		String validar = (String) httpServletRequest.getParameter("validar");

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");

		String idCobrancaAcaoAtividadeComando = (String) httpServletRequest
				.getParameter("idCobrancaAcaoAtividadeComando");
		String cobrancaAcaoAtividadeComandoPesquisado = (String) httpServletRequest
				.getParameter("cobrancaAcaoAtividadeComandoPesquisado");

		ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm = (ManterComandoAcaoCobrancaDetalhesActionForm) actionForm;

		// String idCobrancaAcao = manterComandoAcaoCobrancaDetalhesActionForm
		// .getCobrancaAcao();

		String idCobrancaAtividae = manterComandoAcaoCobrancaDetalhesActionForm
				.getCobrancaAtividade();

		// consultar o cobranca ação atividade comando selecionada
		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = fachada
				.consultarCobrancaAcaoAtividadeComando(idCobrancaAcaoAtividadeComando);

		// carregar os dados na tela da cobrança ação atividade comando
		if (cobrancaAcaoAtividadeComando != null) {

			manterComandoAcaoCobrancaDetalhesActionForm
					.setCobrancaAcao(cobrancaAcaoAtividadeComando
							.getCobrancaAcao().getId().toString());
			manterComandoAcaoCobrancaDetalhesActionForm
					.setCobrancaAtividade(cobrancaAcaoAtividadeComando
							.getCobrancaAtividade().getId().toString());

			// cobranca grupo
			if (cobrancaAcaoAtividadeComando.getCobrancaGrupo() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setCobrancaGrupo(cobrancaAcaoAtividadeComando
								.getCobrancaGrupo().getId().toString());
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setCobrancaGrupo("");
			}
			// gerencia regional
			if (cobrancaAcaoAtividadeComando.getGerenciaRegional() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setGerenciaRegional(cobrancaAcaoAtividadeComando
								.getGerenciaRegional().getId().toString());
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setGerenciaRegional("");
			}
			// unidade Negocio
			if (cobrancaAcaoAtividadeComando.getUnidadeNegocio() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setUnidadeNegocio(cobrancaAcaoAtividadeComando
								.getUnidadeNegocio().getId().toString());
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setUnidadeNegocio("");
			}

			// localidade inicial
			if (cobrancaAcaoAtividadeComando.getLocalidadeInicial() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeOrigemID(cobrancaAcaoAtividadeComando
								.getLocalidadeInicial().getId().toString());
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, cobrancaAcaoAtividadeComando
								.getLocalidadeInicial().getId()));
				Collection colecaoLocalidadesIniciais = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());

				Localidade localidadeInicial = (Localidade) colecaoLocalidadesIniciais
						.iterator().next();
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeOrigem(localidadeInicial
								.getDescricao());
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeOrigemID("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeOrigem("");
			}
			// localidade final
			if (cobrancaAcaoAtividadeComando.getLocalidadeFinal() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeDestinoID(cobrancaAcaoAtividadeComando
								.getLocalidadeFinal().getId().toString());
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, cobrancaAcaoAtividadeComando
								.getLocalidadeFinal().getId()));
				Collection colecaoLocalidadesFinais = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());

				Localidade localidadeFinal = (Localidade) colecaoLocalidadesFinais
						.iterator().next();
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeDestino(localidadeFinal
								.getDescricao());
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeDestinoID("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeDestino("");
			}
			// setor comercial inicial
			if (cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setSetorComercialOrigemCD(cobrancaAcaoAtividadeComando
								.getCodigoSetorComercialInicial().toString());
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						cobrancaAcaoAtividadeComando
								.getCodigoSetorComercialInicial().toString()));
				Collection colecaoSetorComercialIniciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				SetorComercial setorComercialInicial = (SetorComercial) colecaoSetorComercialIniciais
						.iterator().next();
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeSetorComercialOrigem(setorComercialInicial
								.getDescricao());
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setSetorComercialOrigemCD("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeSetorComercialOrigem("");
			}
			// setor comercial final
			if (cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setSetorComercialDestinoCD(cobrancaAcaoAtividadeComando
								.getCodigoSetorComercialFinal().toString());
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						cobrancaAcaoAtividadeComando
								.getCodigoSetorComercialFinal().toString()));
				Collection colecaoSetorComercialFinais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				SetorComercial setorComercialFinal = (SetorComercial) colecaoSetorComercialFinais
						.iterator().next();
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeSetorComercialOrigem(setorComercialFinal
								.getDescricao());
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setSetorComercialDestinoCD("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeSetorComercialOrigem("");
			}
			boolean carregou = false;
			// rota inicial
			if (cobrancaAcaoAtividadeComando.getRotaInicial() != null) {
				// carregarRota(manterComandoAcaoCobrancaDetalhesActionForm,fachada,cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial().toString());
				manterComandoAcaoCobrancaDetalhesActionForm
						.setRotaInicial(cobrancaAcaoAtividadeComando
								.getRotaInicial().getCodigo().toString());
				carregou = true;
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm.setRotaInicial("");
				// sessao.setAttribute("colecaoRota", null);
				manterComandoAcaoCobrancaDetalhesActionForm
						.setRotaInicial(null);
			}
			
			// quadra inicial
			if (cobrancaAcaoAtividadeComando.getNumeroQuadraInicial() != null) {
				
				manterComandoAcaoCobrancaDetalhesActionForm.setNumeroQuadraInicial(cobrancaAcaoAtividadeComando.getNumeroQuadraInicial().toString());
				carregou = true;
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm.setNumeroQuadraInicial(null);
			}
			
			// quadra final
			if (cobrancaAcaoAtividadeComando.getNumeroQuadraFinal() != null) {
				
				manterComandoAcaoCobrancaDetalhesActionForm.setNumeroQuadraFinal(cobrancaAcaoAtividadeComando.getNumeroQuadraFinal().toString());
				carregou = true;
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm.setNumeroQuadraFinal(null);
			}
			
			// rota final
			if (cobrancaAcaoAtividadeComando.getRotaFinal() != null) {
				if (!carregou) {
					// carregarRota(manterComandoAcaoCobrancaDetalhesActionForm,fachada,cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal().toString());
				}

				manterComandoAcaoCobrancaDetalhesActionForm
						.setRotaFinal(cobrancaAcaoAtividadeComando
								.getRotaFinal().getCodigo().toString());
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm.setRotaFinal("");
				// sessao.setAttribute("colecaoRota", null);
				manterComandoAcaoCobrancaDetalhesActionForm.setRotaFinal(null);
			}
			// cliente
			if (cobrancaAcaoAtividadeComando.getCliente() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setIdCliente(cobrancaAcaoAtividadeComando.getCliente()
								.getId().toString());
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, cobrancaAcaoAtividadeComando
								.getCliente().getId().toString()));
				Collection colecaoCliente = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeCliente(cliente.getNome());

			} else {
				manterComandoAcaoCobrancaDetalhesActionForm.setNomeCliente("");
				manterComandoAcaoCobrancaDetalhesActionForm.setIdCliente("");
			}
			
			// cliente superior
			if (cobrancaAcaoAtividadeComando.getSuperior() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setCodigoClienteSuperior(cobrancaAcaoAtividadeComando.getSuperior()
								.getId().toString());
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, cobrancaAcaoAtividadeComando
								.getSuperior().getId().toString()));
				Collection colecaoCliente = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeClienteSuperior(cliente.getNome());

			} else {
				manterComandoAcaoCobrancaDetalhesActionForm.setNomeClienteSuperior("");
				manterComandoAcaoCobrancaDetalhesActionForm.setCodigoClienteSuperior("");
			}
			
			// cliente relacao tipo
			if (cobrancaAcaoAtividadeComando.getClienteRelacaoTipo() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setClienteRelacaoTipo(cobrancaAcaoAtividadeComando
								.getClienteRelacaoTipo().getId().toString());
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setClienteRelacaoTipo("");
			}
			// ano mes conta inicial
			if (cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaInicial() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setPeriodoInicialConta(Util
								.formatarAnoMesParaMesAno(Util
										.adicionarZerosEsquedaNumero(
												6,
												cobrancaAcaoAtividadeComando
														.getAnoMesReferenciaContaInicial()
														.toString())
										+ ""));
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setPeriodoInicialConta("");
			}

			// ano mes conta final
			if (cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaFinal() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setPeriodoFinalConta(Util
								.formatarAnoMesParaMesAno(Util
										.adicionarZerosEsquedaNumero(
												6,
												cobrancaAcaoAtividadeComando
														.getAnoMesReferenciaContaFinal()
														.toString())
										+ ""));
			} else {
				if (cobrancaAcaoAtividadeComandoPesquisado != null
						&& cobrancaAcaoAtividadeComandoPesquisado
								.equals("true")) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoFinalConta("");
				} else {
					// caso não esteja preenchido pelo registro, é preenchido
					// com o
					// dado do sistema
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoFinalConta(fachada
									.consultarPeriodoFinalContaCobrancaAcaoAtividadeComando());
				}
			}
			// data vencimento conta inicial
			if (cobrancaAcaoAtividadeComando.getDataVencimentoContaInicial() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setPeriodoVencimentoContaInicial(Util
								.formatarData(cobrancaAcaoAtividadeComando
										.getDataVencimentoContaInicial()));
			} else {
				if (cobrancaAcaoAtividadeComandoPesquisado != null
						&& cobrancaAcaoAtividadeComandoPesquisado
								.equals("true")) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoVencimentoContaFinal("");
				} else {
					// caso não esteja preenchido pelo registro, é preenchido
					// com o
					// dado do sistema
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoVencimentoContaFinal(fachada
									.consultarPeriodoVencimentoContaFinalCobrancaAcaoAtividadeComando());
				}
			}
			// data vencimento conta final
			if (cobrancaAcaoAtividadeComando.getDataVencimentoContaFinal() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setPeriodoVencimentoContaFinal(Util
								.formatarData(cobrancaAcaoAtividadeComando
										.getDataVencimentoContaFinal()));
			} else {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setPeriodoVencimentoContaFinal("");
			}

			if (cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null) {
				cobrancaAcaoAtividadeComando
						.setCobrancaCriterio(cobrancaAcaoAtividadeComando
								.getCobrancaCriterio());
				sessao.setAttribute("cobrancaAcaoAtividadeComando",
						cobrancaAcaoAtividadeComando);
			}

			if (cobrancaAcaoAtividadeComando.getDescricaoTitulo() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setTitulo(cobrancaAcaoAtividadeComando
								.getDescricaoTitulo());
			}

			if (cobrancaAcaoAtividadeComando.getDescricaoSolicitacao() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setDescricaoSolicitacao(cobrancaAcaoAtividadeComando
								.getDescricaoSolicitacao());
			}

			if (cobrancaAcaoAtividadeComando.getQuantidadeDiasRealizacao() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm.setPrazoExecucao(""
						+ cobrancaAcaoAtividadeComando
								.getQuantidadeDiasRealizacao());

			}

			if (cobrancaAcaoAtividadeComando.getQuantidadeMaximaDocumentos() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setQuantidadeMaximaDocumentos(""
								+ cobrancaAcaoAtividadeComando
										.getQuantidadeMaximaDocumentos());
			}
			if (cobrancaAcaoAtividadeComando.getValorLimiteObrigatoria() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setValorLimiteObrigatoria(""
								+ cobrancaAcaoAtividadeComando
										.getValorLimiteObrigatoria().toString().replace(".", ","));
			}
			if (cobrancaAcaoAtividadeComando.getIndicadorBoletim() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setIndicadorGerarBoletimCadastro(""
								+ cobrancaAcaoAtividadeComando
										.getIndicadorBoletim());
			}
			if (cobrancaAcaoAtividadeComando.getIndicadorDebito() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setIndicadorImoveisDebito(""
								+ cobrancaAcaoAtividadeComando
										.getIndicadorDebito());
			}

			if (cobrancaAcaoAtividadeComando.getRealizacao() != null) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setDataRealizacao(""
								+ cobrancaAcaoAtividadeComando.getRealizacao());
			}

		}

		// indicador de criterio
		if (cobrancaAcaoAtividadeComando != null
				&& cobrancaAcaoAtividadeComando.getIndicadorCriterio() != null) {
			if (cobrancaAcaoAtividadeComando.getIndicadorCriterio()
					.shortValue() == 1) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setCobrancaAtividadeIndicadorExecucao("1");
				// manterComandoAcaoCobrancaDetalhesActionForm.setIndicador("Rota");
			} else if (cobrancaAcaoAtividadeComando.getIndicadorCriterio()
					.shortValue() == 2) {
				manterComandoAcaoCobrancaDetalhesActionForm
						.setCobrancaAtividadeIndicadorExecucao("2");
				// manterComandoAcaoCobrancaDetalhesActionForm.setIndicador("Comando");
			}
		}

		String idComandoSelecionado = httpServletRequest
				.getParameter("idComandoSelecionado");
		if (idComandoSelecionado != null) {
			if (cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null) {
				cobrancaAcaoAtividadeComando.getCobrancaCriterio().setId(
						new Integer(idComandoSelecionado));
				sessao.setAttribute("cobrancaAcaoAtividadeComando",
						cobrancaAcaoAtividadeComando);
			}
		}
		if(cobrancaAcaoAtividadeComando.getConsumoMedioInicial()!=null){
			manterComandoAcaoCobrancaDetalhesActionForm
				.setConsumoMedioInicial(cobrancaAcaoAtividadeComando.getConsumoMedioInicial().toString());
		}else{
			manterComandoAcaoCobrancaDetalhesActionForm
				.setConsumoMedioInicial("");
		}
		if(cobrancaAcaoAtividadeComando.getConsumoMedioFinal()!=null){
			manterComandoAcaoCobrancaDetalhesActionForm
				.setConsumoMedioFinal(cobrancaAcaoAtividadeComando.getConsumoMedioFinal().toString());
		}else{
			manterComandoAcaoCobrancaDetalhesActionForm
			.setConsumoMedioFinal("");
		}
		if(cobrancaAcaoAtividadeComando.getTipoConsumo()!=null){
			manterComandoAcaoCobrancaDetalhesActionForm
				.setTipoConsumo(cobrancaAcaoAtividadeComando.getTipoConsumo().toString());
		}else{
			manterComandoAcaoCobrancaDetalhesActionForm
				.setTipoConsumo("1");
		}
		
		if(cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao()!=null){
			manterComandoAcaoCobrancaDetalhesActionForm
				.setPeriodoInicialFiscalizacao(Util.formatarData(cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao()));
		}else{
			manterComandoAcaoCobrancaDetalhesActionForm
				.setPeriodoInicialFiscalizacao("");
		}
		
		if(cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()!=null){
			manterComandoAcaoCobrancaDetalhesActionForm
				.setPeriodoFinalFiscalizacao(Util.formatarData(cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()));
		}else{
			manterComandoAcaoCobrancaDetalhesActionForm
				.setPeriodoFinalFiscalizacao("");
		}
		
		if (sessao.getAttribute("colecaoFiscalizacaoSituacao") == null) {
			
			FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
			
			filtroFiscalizacaoSituacao.setCampoOrderBy(FiltroFiscalizacaoSituacao.DESCRICAO);
			
			Collection colecaoFiscalizacaoSituacao = (Collection) fachada.pesquisar(
					filtroFiscalizacaoSituacao, FiscalizacaoSituacao.class.getName());
			if (colecaoFiscalizacaoSituacao != null && !colecaoFiscalizacaoSituacao.isEmpty()) {
				// carregar ação de cobrança
				sessao.setAttribute("colecaoFiscalizacaoSituacao", colecaoFiscalizacaoSituacao);
			}
		}
		
		FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao filtroCobrancaAcaoFisc
			= new FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao();
		
		filtroCobrancaAcaoFisc.adicionarParametro(
				new ParametroSimples(
					FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao.COBRANCA_ACAO_ATIVIDADE_COMANDO_ID,
					cobrancaAcaoAtividadeComando.getId()));
		
		Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> colecaoCobrancaAcaoFisc =
			fachada.pesquisar(filtroCobrancaAcaoFisc, 
					CobrancaAcaoAtividadeComandoFiscalizacaoSituacao.class.getName());
		
		
		String[] fiscalizacaoSituacoes = null;
		if(!Util.isVazioOrNulo(colecaoCobrancaAcaoFisc)){
		
			fiscalizacaoSituacoes = new String[colecaoCobrancaAcaoFisc.size()];
			int cont = 0;
			
			for (CobrancaAcaoAtividadeComandoFiscalizacaoSituacao helper : colecaoCobrancaAcaoFisc) {
				
				fiscalizacaoSituacoes[cont] = helper.getFiscalizacaoSituacao().getId().toString();
				cont++;
				
			}
			
		}
		manterComandoAcaoCobrancaDetalhesActionForm.setSituacaoFiscalizacao(fiscalizacaoSituacoes);

		// valdiar os criteorios de rota e comando para o usuário selecionar
		if (validarCriterio != null && !validarCriterio.equals("")) {
			// validar a atividade selecionada
			if (validar != null && validar.equals("Atividade")) {
				if (idCobrancaAtividae != null
						&& !idCobrancaAtividae.equals("")) {

					CobrancaAtividade cobrancaAtividade = fachada
							.obterCobrancaAtividade(idCobrancaAtividae);

					if (cobrancaAtividade != null) {
						if (cobrancaAtividade.getIndicadorExecucao() != null) {
							manterComandoAcaoCobrancaDetalhesActionForm
									.setCobrancaAtividadeIndicadorExecucao(cobrancaAtividade
											.getIndicadorExecucao().toString());
						} else {
							manterComandoAcaoCobrancaDetalhesActionForm
									.setCobrancaAtividadeIndicadorExecucao("");
						}
					} else {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setCobrancaAtividadeIndicadorExecucao("");
					}
				}
			}
		}

		// CARREGAR AS COBRANÇAS GRUPO
		if (sessao.getAttribute("colecaoCobrancaGrupo") == null) {
			sessao.setAttribute("colecaoCobrancaGrupo", fachada
					.obterColecaoCobrancaGrupo());
		}

		// CARREGAR AS COBRANÇAS ATIVIDADE
		if (sessao.getAttribute("colecaoCobrancaAtividade") == null) {
			sessao.setAttribute("colecaoCobrancaAtividade", fachada
					.obterColecaoCobrancaAtividade());
		}

		// CARREGAR AS COBRANÇAS ACAO
		if (sessao.getAttribute("colecaoCobrancaAcao") == null) {
			sessao.setAttribute("colecaoCobrancaAcao", fachada
					.obterColecaoCobrancaAcao());
		}

		// CARREGAR AS GERENCIAIS REGIONAIS
		if (sessao.getAttribute("colecaoGerenciaRegional") == null) {
			sessao.setAttribute("colecaoGerenciaRegional", fachada
					.obterColecaoGerenciaRegional());
		}

		// CARREGAR AS UNIDADES NEGOCIOS
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			sessao.setAttribute("colecaoUnidadeNegocio", fachada
					.obterColecaoUnidadeNegocio());
		}

		// CARREGAR OS CLIENTE RELACAO TIPO
		if (sessao.getAttribute("colecaoClienteRelacaoTipo") == null) {
			sessao.setAttribute("colecaoClienteRelacaoTipo", fachada
					.obterColecaoClienteRelacaoTipo());
		}

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
						manterComandoAcaoCobrancaDetalhesActionForm, fachada,
						httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,
						manterComandoAcaoCobrancaDetalhesActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						manterComandoAcaoCobrancaDetalhesActionForm, fachada,
						httpServletRequest);

				break;
			case 3:
				pesquisarCliente(inscricaoTipo,
						manterComandoAcaoCobrancaDetalhesActionForm, fachada,
						httpServletRequest);
				break;

			default:
				break;
			}
		}

		if (cobrancaAcaoAtividadeComando != null) {
			sessao.setAttribute("cobrancaAcaoAtividadeComando",
					cobrancaAcaoAtividadeComando);
		}

		return retorno;
	}

	/**
	 * Pesquisa a Localidade
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarLocalidade(
			String inscricaoTipo,
			ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			manterComandoAcaoCobrancaDetalhesActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) manterComandoAcaoCobrancaDetalhesActionForm
					.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeOrigemID("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeOrigem("Localidade Inexistente");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeOrigemID(String.valueOf(objetoLocalidade
								.getId()));
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");

				String localidadeDestinoID = (String) manterComandoAcaoCobrancaDetalhesActionForm
						.getLocalidadeDestinoID();
				// verifica o valor das localidades, origem e final
				if (localidadeDestinoID != null) {

					if (localidadeDestinoID.equals("")) {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						manterComandoAcaoCobrancaDetalhesActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
					} else {
						int localidadeDestino = new Integer(localidadeDestinoID)
								.intValue();
						int localidadeOrigem = objetoLocalidade.getId()
								.intValue();
						if (localidadeOrigem > localidadeDestino) {
							manterComandoAcaoCobrancaDetalhesActionForm
									.setLocalidadeDestinoID(String
											.valueOf(objetoLocalidade.getId()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setNomeLocalidadeDestino(objetoLocalidade
											.getDescricao());
						}
					}
				}
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) manterComandoAcaoCobrancaDetalhesActionForm
					.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			manterComandoAcaoCobrancaDetalhesActionForm
					.setInscricaoTipo("destino");

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeDestinoID("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeDestino("Localidade inexistente");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);

				int localidadeDestino = objetoLocalidade.getId().intValue();

				String localidade = (String) manterComandoAcaoCobrancaDetalhesActionForm
						.getLocalidadeOrigemID();

				if (localidade != null && !localidade.equals("")) {

					int localidadeOrigem = new Integer(localidade).intValue();
					if (localidadeDestino < localidadeOrigem) {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setLocalidadeDestinoID("");
						manterComandoAcaoCobrancaDetalhesActionForm
								.setNomeLocalidadeDestino("Loc. Final maior que a Inicial");
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"exception");

					} else {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						manterComandoAcaoCobrancaDetalhesActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"valor");
					}
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setLocalidadeDestinoID(String
									.valueOf(objetoLocalidade.getId()));
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeLocalidadeDestino(objetoLocalidade
									.getDescricao());
					httpServletRequest.setAttribute("corLocalidadeDestino",
							"valor");
				}
			}
		}

	}

	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			manterComandoAcaoCobrancaDetalhesActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) manterComandoAcaoCobrancaDetalhesActionForm
					.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) manterComandoAcaoCobrancaDetalhesActionForm
						.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemCD("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemID("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeSetorComercialOrigem("Setor inexistente");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaInicial(null);
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaFinal(null);

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					// setorComercialID =
					// objetoSetorComercial.getId().toString();
					// setorComercialOrigem
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"valor");

					String setorComercialDestinoCD = (String) manterComandoAcaoCobrancaDetalhesActionForm
							.getSetorComercialDestinoCD();

					// verifica o valor dos setores comerciais, origem e final
					if (setorComercialDestinoCD != null) {

						if (setorComercialDestinoCD.equals("")) {

							// setorComercialDestino
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());

							Collection colecaRotas = fachada
									.obterColecaoRota(objetoSetorComercial
											.getId().toString());

							sessao.setAttribute("colecaoRota", colecaRotas);
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaInicial("rota");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaFinal("rota");
						} else {

							int setorDestino = new Integer(
									setorComercialDestinoCD).intValue();
							int setorOrigem = objetoSetorComercial.getCodigo();
							if (setorOrigem > setorDestino) {

								// setorComercialDestino
								manterComandoAcaoCobrancaDetalhesActionForm
										.setSetorComercialDestinoCD(String
												.valueOf(objetoSetorComercial
														.getCodigo()));
								manterComandoAcaoCobrancaDetalhesActionForm
										.setSetorComercialDestinoID(String
												.valueOf(objetoSetorComercial
														.getId()));
								manterComandoAcaoCobrancaDetalhesActionForm
										.setNomeSetorComercialDestino(objetoSetorComercial
												.getDescricao());

								Collection colecaRotas = fachada
										.obterColecaoRota(objetoSetorComercial
												.getId().toString());

								sessao.setAttribute("colecaoRota", colecaRotas);
								manterComandoAcaoCobrancaDetalhesActionForm
										.setRotaInicial("rota");
								manterComandoAcaoCobrancaDetalhesActionForm
										.setRotaFinal("rota");
							}
						}
					}
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				manterComandoAcaoCobrancaDetalhesActionForm
						.setSetorComercialOrigemCD("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
			}
		} else {

			manterComandoAcaoCobrancaDetalhesActionForm
					.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) manterComandoAcaoCobrancaDetalhesActionForm
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) manterComandoAcaoCobrancaDetalhesActionForm
						.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialDestinoCD("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialDestinoID("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeSetorComercialDestino("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaInicial(null);
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaFinal(null);

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);

					int setorDestino = objetoSetorComercial.getCodigo();

					String setor = (String) manterComandoAcaoCobrancaDetalhesActionForm
							.getSetorComercialOrigemCD();

					if (setor != null && !setor.equals("")) {

						int setorOrigem = new Integer(setor).intValue();
						if (setorDestino < setorOrigem) {

							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoCD("");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoID("");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setNomeSetorComercialDestino("Setor Final maior que Inicial");
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "exception");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaInicial(null);
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaFinal(null);

						} else {
							// rota
							Collection colecaRotas = fachada
									.obterColecaoRota(objetoSetorComercial
											.getId().toString());

							sessao.setAttribute("colecaoRota", colecaRotas);
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaInicial("rota");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaFinal("rota");

							// setor comercial destino
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "valor");
						}
					} else {

						Collection colecaRotas = fachada
								.obterColecaoRota(objetoSetorComercial.getId()
										.toString());

						sessao.setAttribute("colecaoRota", colecaRotas);
						manterComandoAcaoCobrancaDetalhesActionForm
								.setRotaInicial("rota");
						manterComandoAcaoCobrancaDetalhesActionForm
								.setRotaFinal("rota");

						// setor comercial destino
						manterComandoAcaoCobrancaDetalhesActionForm
								.setSetorComercialDestinoCD(String
										.valueOf(objetoSetorComercial
												.getCodigo()));
						manterComandoAcaoCobrancaDetalhesActionForm
								.setSetorComercialDestinoID(String
										.valueOf(objetoSetorComercial.getId()));
						manterComandoAcaoCobrancaDetalhesActionForm
								.setNomeSetorComercialDestino(objetoSetorComercial
										.getDescricao());
						httpServletRequest.setAttribute(
								"corSetorComercialDestino", "valor");
					}
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				manterComandoAcaoCobrancaDetalhesActionForm
						.setSetorComercialDestinoCD("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
			}
		}

	}

	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarCliente(
			String inscricaoTipo,
			ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		String idCliente = null;
		if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
			idCliente = manterComandoAcaoCobrancaDetalhesActionForm
					.getCodigoClienteSuperior();
		} else {
			idCliente = manterComandoAcaoCobrancaDetalhesActionForm
					.getIdCliente();
		}

		// -------Parte que trata do código quando o usuário tecla enter
		// se o id do cliente for diferente de nulo
		if (idCliente != null
				&& !idCliente.toString().trim().equalsIgnoreCase("")) {

			FiltroCliente filtroCliente = new FiltroCliente();
			Collection clientes = null;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, new Integer(idCliente)));
			clientes = fachada
					.pesquisar(filtroCliente, Cliente.class.getName());
			if (clientes != null && !clientes.isEmpty()) {
				// O cliente foi encontrado
				if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setCodigoClienteSuperior(((Cliente) ((List) clientes)
									.get(0)).getId().toString());
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeClienteSuperior(((Cliente) ((List) clientes)
									.get(0)).getNome());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setIdCliente(((Cliente) ((List) clientes).get(0))
									.getId().toString());
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeCliente(((Cliente) ((List) clientes).get(0))
									.getNome());
				}

				Cliente cliente = new Cliente();

				cliente = (Cliente) clientes.iterator().next();
				sessao.setAttribute("clienteObj", cliente);
			} else {
				if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
					httpServletRequest.setAttribute(
							"codigoClienteSuperiorNaoEncontrado", "true");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeClienteSuperior("");
					httpServletRequest.setAttribute("nomeCampo",
							"codigoClienteSuperior");
				} else {
					httpServletRequest.setAttribute(
							"codigoClienteNaoEncontrado", "true");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeCliente("");
					httpServletRequest.setAttribute("nomeCampo", "idCliente");
				}
			}

		}

	}

	/**
	 * Inicializa a Rota
	 * 
	 * @param inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
	 * @param fachada
	 * @param objetoSetorComercial
	 */
	public void carregarRota(
			ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm,
			Fachada fachada, String codigoSetorComercial) {

		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercial));
		Collection colecaoRota = (Collection) fachada.pesquisar(filtroRota,
				Rota.class.getName());
		sessao.setAttribute("colecaoRota", colecaoRota);
		manterComandoAcaoCobrancaDetalhesActionForm.setRotaInicial("rota");
		manterComandoAcaoCobrancaDetalhesActionForm.setRotaFinal("rota");

	}

}

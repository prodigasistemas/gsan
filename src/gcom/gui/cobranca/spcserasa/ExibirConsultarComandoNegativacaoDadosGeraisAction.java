package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.FiltroNegativCritCobrGrupo;
import gcom.cobranca.FiltroNegativCritElo;
import gcom.cobranca.FiltroNegativCritGerReg;
import gcom.cobranca.FiltroNegativCritUndNeg;
import gcom.cobranca.FiltroNegativacaoComando;
import gcom.cobranca.FiltroNegativacaoCriterio;
import gcom.cobranca.FiltroNegativacaoCriterioClienteTipo;
import gcom.cobranca.FiltroNegativacaoCriterioCpfTipo;
import gcom.cobranca.FiltroNegativacaoCriterioImovelPerfil;
import gcom.cobranca.FiltroNegativacaoCriterioSubcategoria;
import gcom.cobranca.NegativCritCobrGrupo;
import gcom.cobranca.NegativCritElo;
import gcom.cobranca.NegativCritGerReg;
import gcom.cobranca.NegativCritUndNeg;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoCriterioClienteTipo;
import gcom.cobranca.NegativacaoCriterioCpfTipo;
import gcom.cobranca.NegativacaoCriterioImovelPerfil;
import gcom.cobranca.NegativacaoCriterioSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class ExibirConsultarComandoNegativacaoDadosGeraisAction extends
		GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDadosInclusoes");
		HttpSession sessao = httpServletRequest.getSession(false);
		ConsultarDadosNegativacaoComandoActionForm form = (ConsultarDadosNegativacaoComandoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("idSelecionado") != null
				&& !httpServletRequest.getParameter("idSelecionado").equals("")) {

			String idComandoNegativacaoSelecionado = httpServletRequest
					.getParameter("idSelecionado");

			FiltroNegativacaoComando filtroNegativacaoComando = new FiltroNegativacaoComando();
			filtroNegativacaoComando.adicionarParametro(new ParametroSimples(
					FiltroNegativacaoComando.ID, new Integer(
							idComandoNegativacaoSelecionado)));
			filtroNegativacaoComando
					.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			filtroNegativacaoComando
					.adicionarCaminhoParaCarregamentoEntidade("usuario");

			Collection collNegativacaoComando = fachada.pesquisar(
					filtroNegativacaoComando,
					NegativacaoComando.class.getName());
			NegativacaoComando negativacaoComando = (NegativacaoComando) collNegativacaoComando
					.iterator().next();

			if (negativacaoComando.getNegativador().getCliente().getNome() != null) {
				form.setNegativador(negativacaoComando.getNegativador()
						.getCliente().getNome());
			}
			
			if (negativacaoComando.getIndicadorContaNomeCliente() != null) {
				form.setIndicadorContaNomeCliente(negativacaoComando.getIndicadorContaNomeCliente().toString());
			}

			FiltroNegativacaoCriterio filtroNegativacaoCriterio = new FiltroNegativacaoCriterio();
			filtroNegativacaoCriterio.adicionarParametro(new ParametroSimples(
					FiltroNegativacaoCriterio.ID_NEGATIVACAO_COMANDO,
					negativacaoComando.getId()));
			filtroNegativacaoCriterio
					.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
			filtroNegativacaoCriterio
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativacaoCriterio
					.adicionarCaminhoParaCarregamentoEntidade("localidadeInicial");
			filtroNegativacaoCriterio
					.adicionarCaminhoParaCarregamentoEntidade("localidadeFinal");

			Collection collNegativacaoCriterio = fachada.pesquisar(
					filtroNegativacaoCriterio,
					NegativacaoCriterio.class.getName());
			NegativacaoCriterio negativacaoCriterio = (NegativacaoCriterio) collNegativacaoCriterio
					.iterator().next();

			if (negativacaoCriterio.getDescricaoTitulo() != null) {
				form.setTituloComando(negativacaoCriterio.getDescricaoTitulo());
			}

			if (negativacaoCriterio.getDescricaoSolicitacao() != null) {
				form.setDescricaoSolicitacao(negativacaoCriterio
						.getDescricaoSolicitacao());
			}

			if (new Integer(negativacaoComando.getIndicadorSimulacao()) != null) {
				form.setSimularNegativacao(new Integer(negativacaoComando
						.getIndicadorSimulacao()).toString());
			}

			if (negativacaoComando.getDataPrevista() != null) {
				form.setDataPrevistaExecucao(Util
						.formatarData(negativacaoComando.getDataPrevista()));
			}

			if (negativacaoComando.getUsuario().getNomeUsuario() != null) {
				form.setUsuarioResponsavel(negativacaoComando.getUsuario()
						.getNomeUsuario());
			}

			if (negativacaoCriterio.getQuantidadeMaximaInclusoes() != null) {
				form.setQuantidadeMaximaInclusoes(negativacaoCriterio
						.getQuantidadeMaximaInclusoes().toString());
			}

			FiltroNegativacaoCriterioCpfTipo filtroNegativacaoCriterioCpfTipo = new FiltroNegativacaoCriterioCpfTipo();
			filtroNegativacaoCriterioCpfTipo
					.adicionarParametro(new ParametroSimples(
							FiltroNegativacaoCriterioCpfTipo.ID_NEGATIVACAO_CRITERIO,
							negativacaoCriterio.getId()));
			filtroNegativacaoCriterioCpfTipo
					.adicionarCaminhoParaCarregamentoEntidade("cpfTipo");
			filtroNegativacaoCriterioCpfTipo
					.adicionarCaminhoParaCarregamentoEntidade("negativacaoCriterio.cliente");
			filtroNegativacaoCriterioCpfTipo
					.adicionarCaminhoParaCarregamentoEntidade("negativacaoCriterio.clienteRelacaoTipo");

			Collection collNegativacaoCriterioCpfTipo = fachada.pesquisar(
					filtroNegativacaoCriterioCpfTipo,
					NegativacaoCriterioCpfTipo.class.getName());
			sessao.setAttribute("collNegativacaoCriterioCpfTipo",
					collNegativacaoCriterioCpfTipo);

			if (negativacaoCriterio.getAnoMesReferenciaContaInicial() != null) {
				form.setPeriodoReferenciaDebitoInicial(Util
						.formatarAnoMesParaMesAno(negativacaoCriterio
								.getAnoMesReferenciaContaInicial()));
			}

			if (negativacaoCriterio.getAnoMesReferenciaContaFinal() != null) {
				form.setPeriodoReferenciaDebitoFinal(Util
						.formatarAnoMesParaMesAno(negativacaoCriterio
								.getAnoMesReferenciaContaFinal()));
			}

			if (negativacaoCriterio.getDataVencimentoDebitoInicial() != null) {
				form.setPeriodoVencimentoDebitoInicial(Util
						.formatarData(negativacaoCriterio
								.getDataVencimentoDebitoInicial()));
			}

			if (negativacaoCriterio.getDataVencimentoDebitoFinal() != null) {
				form.setPeriodoVencimentoDebitoFinal(Util
						.formatarData(negativacaoCriterio
								.getDataVencimentoDebitoFinal()));
			}

			if (negativacaoCriterio.getValorMinimoDebito() != null) {
				form.setValorDebitoInicial(negativacaoCriterio
						.getValorMinimoDebito().toString());
			}

			if (negativacaoCriterio.getValorMaximoDebito() != null) {
				form.setValorDebitoFinal(negativacaoCriterio
						.getValorMaximoDebito().toString());
			}

			if (new Integer(negativacaoCriterio.getQuantidadeMinimaContas()) != null) {
				form.setNumeroContasInicial(new Integer(negativacaoCriterio
						.getQuantidadeMinimaContas()).toString());
			}

			if (new Integer(negativacaoCriterio.getQuantidadeMaximaContas()) != null) {
				form.setNumeroContasFinal(new Integer(negativacaoCriterio
						.getQuantidadeMaximaContas()).toString());
			}

			if (new Short(
					negativacaoCriterio.getIndicadorNegativacaoContaRevisao()) != null) {
				form.setConsiderarContasRevisao(new Short(negativacaoCriterio
						.getIndicadorNegativacaoContaRevisao()).toString());
			}

			if (new Short(
					negativacaoCriterio.getIndicadorNegativacaoGuiaPagamento()) != null) {
				form.setConsiderarGuiasPagamento(new Short(negativacaoCriterio
						.getIndicadorNegativacaoGuiaPagamento()).toString());
			}

			if (new Short(negativacaoCriterio.getIndicadorParcelamentoAtraso()) != null) {
				form.setParcelamentoAtraso(new Short(negativacaoCriterio
						.getIndicadorParcelamentoAtraso()).toString());
			}

			if (negativacaoCriterio.getNumeroDiasParcelamentoAtraso() != null) {
				form.setDiasAtrasoParcelamento(negativacaoCriterio
						.getNumeroDiasParcelamentoAtraso().toString());
			}

			if (new Short(
					negativacaoCriterio
							.getIndicadorNegativacaoRecebimentoCartaParcelamento()) != null) {
				form.setRecebeuCartaParcelamentoAtraso(new Short(
						negativacaoCriterio
								.getIndicadorNegativacaoRecebimentoCartaParcelamento())
						.toString());
			}

			if (negativacaoCriterio
					.getNumeroDiasAtrasoRecebimentoCartaParcelamento() != null) {
				form.setDiasAtrasoAposRecebimentoCarta(negativacaoCriterio
						.getNumeroDiasAtrasoRecebimentoCartaParcelamento()
						.toString());
			}

			if (negativacaoCriterio.getCliente() != null) {
				if (negativacaoCriterio.getCliente().getNome() != null) {
					form.setCliente(negativacaoCriterio.getCliente().getNome());
				}

				if (negativacaoCriterio.getCliente().getId() != null) {
					form.setIdCliente(negativacaoCriterio.getCliente().getId()
							.toString());
				}

			}

			if (negativacaoCriterio.getClienteRelacaoTipo() != null) {
				if (negativacaoCriterio.getClienteRelacaoTipo().getDescricao() != null) {
					form.setTipoRelacaoCliente(negativacaoCriterio
							.getClienteRelacaoTipo().getDescricao());
				}

			}

			if (new Short(
					negativacaoCriterio
							.getIndicadorNegativacaoImovelParalisacao()) != null) {
				form.setImovelSitEspecialCobranca(new Short(negativacaoCriterio
						.getIndicadorNegativacaoImovelParalisacao()).toString());
			}

			if (new Short(
					negativacaoCriterio
							.getIndicadorNegativacaoImovelSituacaoCobranca()) != null) {
				form.setImovelSitCobranca(new Short(negativacaoCriterio
						.getIndicadorNegativacaoImovelSituacaoCobranca())
						.toString());
			}

			if (negativacaoCriterio.getLocalidadeInicial() != null) {
				form.setLocalidadeInicial(negativacaoCriterio
						.getLocalidadeInicial().getDescricao());
			}

			if (negativacaoCriterio.getLocalidadeFinal() != null) {
				form.setLocalidadeFinal(negativacaoCriterio
						.getLocalidadeFinal().getDescricao());
			}

			if (negativacaoCriterio.getLocalidadeInicial() != null) {
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				if (negativacaoCriterio.getCodigoSetorComercialInicial() != null) {
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
									negativacaoCriterio
											.getCodigoSetorComercialInicial()));
				}
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, negativacaoCriterio
								.getLocalidadeInicial().getId()));

				Collection collSetorComercialInicial = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (collSetorComercialInicial != null
						&& !collSetorComercialInicial.isEmpty()) {
					SetorComercial setorComercialInicial = (SetorComercial) collSetorComercialInicial
							.iterator().next();
					if (setorComercialInicial != null) {
						form.setSetorComercialInicial(setorComercialInicial
								.getDescricao());
					}

				}

				filtroSetorComercial.limparListaParametros();
				if (negativacaoCriterio.getCodigoSetorComercialFinal() != null) {
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
									negativacaoCriterio
											.getCodigoSetorComercialFinal()));
				}
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, negativacaoCriterio
								.getLocalidadeInicial().getId()));
				Collection collSetorComercialFinal = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (collSetorComercialFinal != null
						&& !collSetorComercialFinal.isEmpty()) {
					SetorComercial setorComercialFinal = (SetorComercial) collSetorComercialFinal
							.iterator().next();
					if (setorComercialFinal != null) {
						form.setSetorComercialFinal(setorComercialFinal
								.getDescricao());
					}
				}
			}

			// Lista de subcategoria
			FiltroNegativacaoCriterioSubcategoria filtroNegativacaoCriterioSubcategoria = new FiltroNegativacaoCriterioSubcategoria();
			filtroNegativacaoCriterioSubcategoria
					.adicionarParametro(new ParametroSimples(
							FiltroNegativacaoCriterioSubcategoria.COMP_ID_NEGATIVACAO_CRITERIO_ID,
							negativacaoCriterio.getId()));
			filtroNegativacaoCriterioSubcategoria
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.subcategoria");
			filtroNegativacaoCriterioSubcategoria
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.negativacaoCriterio");
			filtroNegativacaoCriterioSubcategoria
					.adicionarParametro(new ParametroSimples(
							"comp_id.subcategoria.indicadorUso",
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativacaoCriterioSubcategoria
					.setCampoOrderBy("comp_id.subcategoria.descricao");
			Collection collNegativacaoCriterioSubcategoria = fachada.pesquisar(
					filtroNegativacaoCriterioSubcategoria,
					NegativacaoCriterioSubcategoria.class.getName());
			sessao.setAttribute("collNegativacaoCriterioSubcategoria",
					collNegativacaoCriterioSubcategoria);

			// //Lista de Perfil do Imovel
			FiltroNegativacaoCriterioImovelPerfil filtroNegativacaoCriterioImovelPerfil = new FiltroNegativacaoCriterioImovelPerfil();
			filtroNegativacaoCriterioImovelPerfil
					.adicionarParametro(new ParametroSimples(
							FiltroNegativacaoCriterioImovelPerfil.COMP_ID_NEGATIVACAO_CRITERIO_ID,
							negativacaoCriterio.getId()));
			filtroNegativacaoCriterioImovelPerfil
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.imovelPerfil");
			filtroNegativacaoCriterioImovelPerfil
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.negativacaoCriterio");
			filtroNegativacaoCriterioImovelPerfil
					.adicionarParametro(new ParametroSimples(
							"comp_id.imovelPerfil.indicadorUso",
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativacaoCriterioImovelPerfil
					.setCampoOrderBy("comp_id.imovelPerfil.descricao");
			Collection collNegativacaoCriterioImovelPerfil = fachada.pesquisar(
					filtroNegativacaoCriterioImovelPerfil,
					NegativacaoCriterioImovelPerfil.class.getName());
			sessao.setAttribute("collNegativacaoCriterioImovelPerfil",
					collNegativacaoCriterioImovelPerfil);

			// Lista de Cliente Tipo
			FiltroNegativacaoCriterioClienteTipo filtroNegativacaoCriterioClienteTipo = new FiltroNegativacaoCriterioClienteTipo();
			filtroNegativacaoCriterioClienteTipo
					.adicionarParametro(new ParametroSimples(
							FiltroNegativacaoCriterioClienteTipo.COMP_ID_NEGATIVACAO_CRITERIO_ID,
							negativacaoCriterio.getId()));
			filtroNegativacaoCriterioClienteTipo
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.clienteTipo");
			filtroNegativacaoCriterioClienteTipo
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.negativacaoCriterio");
			filtroNegativacaoCriterioClienteTipo
					.adicionarParametro(new ParametroSimples(
							"comp_id.clienteTipo.indicadorUso",
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativacaoCriterioClienteTipo
					.setCampoOrderBy("comp_id.clienteTipo.descricao");
			Collection collNegativacaoCriterioClienteTipo = fachada.pesquisar(
					filtroNegativacaoCriterioClienteTipo,
					NegativacaoCriterioClienteTipo.class.getName());
			sessao.setAttribute("collNegativacaoCriterioClienteTipo",
					collNegativacaoCriterioClienteTipo);

			// Lista de Grupo de Cobrança
			FiltroNegativCritCobrGrupo filtroNegativCritCobrGrupo = new FiltroNegativCritCobrGrupo();
			filtroNegativCritCobrGrupo.adicionarParametro(new ParametroSimples(
					FiltroNegativCritCobrGrupo.COMP_ID_NEGATIVACAO_CRITERIO_ID,
					negativacaoCriterio.getId()));
			filtroNegativCritCobrGrupo
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.cobrancaGrupo");
			filtroNegativCritCobrGrupo
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.negativacaoCriterio");
			filtroNegativCritCobrGrupo.adicionarParametro(new ParametroSimples(
					"comp_id.cobrancaGrupo.indicadorUso",
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativCritCobrGrupo
					.setCampoOrderBy("comp_id.cobrancaGrupo.descricao");
			Collection collNegativCritCobrGrupo = fachada.pesquisar(
					filtroNegativCritCobrGrupo,
					NegativCritCobrGrupo.class.getName());
			sessao.setAttribute("collNegativCritCobrGrupo",
					collNegativCritCobrGrupo);

			// Lista de Gerencia Regional
			FiltroNegativCritGerReg filtroNegativCritGerReg = new FiltroNegativCritGerReg();
			filtroNegativCritGerReg.adicionarParametro(new ParametroSimples(
					FiltroNegativCritGerReg.COMP_ID_NEGATIVACAO_CRITERIO_ID,
					negativacaoCriterio.getId()));
			filtroNegativCritGerReg
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.gerenciaRegional");
			filtroNegativCritGerReg
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.negativacaoCriterio");
			filtroNegativCritGerReg.adicionarParametro(new ParametroSimples(
					"comp_id.gerenciaRegional.indicadorUso",
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativCritGerReg
					.setCampoOrderBy("comp_id.gerenciaRegional.nome");
			Collection collNegativCritGerReg = fachada.pesquisar(
					filtroNegativCritGerReg, NegativCritGerReg.class.getName());
			sessao.setAttribute("collNegativCritGerReg", collNegativCritGerReg);

			// Lista de Unidade de Negócio
			FiltroNegativCritUndNeg filtroNegativCritUndNeg = new FiltroNegativCritUndNeg();
			filtroNegativCritUndNeg.adicionarParametro(new ParametroSimples(
					FiltroNegativCritUndNeg.COMP_ID_NEGATIVACAO_CRITERIO_ID,
					negativacaoCriterio.getId()));
			filtroNegativCritUndNeg
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.unidadeNegocio");
			filtroNegativCritUndNeg.adicionarParametro(new ParametroSimples(
					"comp_id.unidadeNegocio.indicadorUso",
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativCritUndNeg
					.setCampoOrderBy("comp_id.unidadeNegocio.nome");
			Collection collNegativCritUndNeg = fachada.pesquisar(
					filtroNegativCritUndNeg, NegativCritUndNeg.class.getName());
			sessao.setAttribute("collNegativCritUndNeg", collNegativCritUndNeg);

			// Lista de Elo Polo
			FiltroNegativCritElo filtroNegativCritElo = new FiltroNegativCritElo();
			filtroNegativCritElo.adicionarParametro(new ParametroSimples(
					FiltroNegativCritElo.COMP_ID_NEGATIVACAO_CRITERIO_ID,
					negativacaoCriterio.getId()));
			filtroNegativCritElo
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.localidade");
			filtroNegativCritElo
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.negativacaoCriterio");
			filtroNegativCritElo.adicionarParametro(new ParametroSimples(
					"comp_id.localidade.indicadorUso",
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativCritElo
					.setCampoOrderBy("comp_id.localidade.descricao");
			Collection collNegativCritElo = fachada.pesquisar(
					filtroNegativCritElo, NegativCritElo.class.getName());
			sessao.setAttribute("collNegativCritElo", collNegativCritElo);

		}
		return retorno;

	}
}

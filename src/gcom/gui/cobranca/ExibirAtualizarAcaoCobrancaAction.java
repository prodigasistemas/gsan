package gcom.gui.cobranca;

import java.util.Collection;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para atualiza o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 06/11/2006
 */
public class ExibirAtualizarAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAcaoCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// caso seja a primeira vez
		if (httpServletRequest.getParameter("menu") != null) {
			sessao.removeAttribute("voltar");
		}


		AcaoCobrancaAtualizarActionForm acaoCobrancaAtualizarActionForm = (AcaoCobrancaAtualizarActionForm) actionForm;
		if ((httpServletRequest.getParameter("idRegistroAtualizar") != null && !httpServletRequest
				.getParameter("idRegistroAtualizar").equals(""))
				|| (sessao.getAttribute("cobrancaAcao") != null && !sessao
						.getAttribute("cobrancaAcao").equals(""))) {

			if (httpServletRequest.getParameter("objetoConsulta") == null) {

				CobrancaAcao cobrancaAcao = null;
				if (httpServletRequest.getParameter("idRegistroAtualizar") != null
						&& !httpServletRequest.getParameter(
								"idRegistroAtualizar").equals("")) {
					String idAcaoCobranca = httpServletRequest
							.getParameter("idRegistroAtualizar");
					FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
					filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcao.ID, idAcaoCobranca));
					
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
					
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.documentoTipo");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.ligacaoAguaSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.ligacaoEsgotoSituacao");
					filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.cobrancaCriterio");
					

					Collection colecaoCobrancaAcao = fachada.pesquisar(
							filtroCobrancaAcao, CobrancaAcao.class.getName());
					if (colecaoCobrancaAcao != null
							&& !colecaoCobrancaAcao.isEmpty()) {

						cobrancaAcao = (CobrancaAcao) Util
								.retonarObjetoDeColecao(colecaoCobrancaAcao);
					}
					sessao.setAttribute("voltar", "manter");
				} else {
					cobrancaAcao = (CobrancaAcao) sessao
							.getAttribute("cobrancaAcao");
					sessao.setAttribute("voltar", "filtrar");
				}

				if (cobrancaAcao != null && !cobrancaAcao.equals("")) {

					if (cobrancaAcao.getDescricaoCobrancaAcao() != null) {
						acaoCobrancaAtualizarActionForm
								.setDescricaoAcao(cobrancaAcao
										.getDescricaoCobrancaAcao());
					} else {
						acaoCobrancaAtualizarActionForm.setDescricaoAcao("");
					}
				
					if (cobrancaAcao.getCobrancaCriterio() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdCobrancaCriterio(""
										+ cobrancaAcao.getCobrancaCriterio()
												.getId());
						acaoCobrancaAtualizarActionForm
								.setDescricaoCobrancaCriterio(cobrancaAcao
										.getCobrancaCriterio()
										.getDescricaoCobrancaCriterio());
					} else {
						acaoCobrancaAtualizarActionForm
							.setIdCobrancaCriterio("");
					
						acaoCobrancaAtualizarActionForm
							.setDescricaoCobrancaCriterio("");
					}
				
					if (cobrancaAcao.getServicoTipo() != null) {
						acaoCobrancaAtualizarActionForm.setIdServicoTipo(""
								+ cobrancaAcao.getServicoTipo().getId());
						acaoCobrancaAtualizarActionForm
								.setDescricaoServicoTipo(cobrancaAcao
										.getServicoTipo().getDescricao());
					} else {
						acaoCobrancaAtualizarActionForm.setIdServicoTipo("");
						acaoCobrancaAtualizarActionForm.setDescricaoServicoTipo("");
					}
				
					if (cobrancaAcao.getIndicadorObrigatoriedade() != null) {
						acaoCobrancaAtualizarActionForm.setIcAcaoObrigatoria(""
								+ cobrancaAcao.getIndicadorObrigatoriedade());
					} else {
						acaoCobrancaAtualizarActionForm.setIcAcaoObrigatoria("");
					}
				
					if (cobrancaAcao.getIndicadorAcrescimoImpontualidade() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcAcrescimosImpontualidade(""
										+ cobrancaAcao
												.getIndicadorAcrescimoImpontualidade());
					} else {
						acaoCobrancaAtualizarActionForm.setIcAcrescimosImpontualidade("");
					}
				
					if (cobrancaAcao.getIndicadorCronograma() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcCompoeCronograma(""
										+ cobrancaAcao.getIndicadorCronograma());
					} else {
						acaoCobrancaAtualizarActionForm
						.setIcCompoeCronograma("");
					}
					
					if (cobrancaAcao.getIndicadorCobrancaDebACobrar() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcDebitosACobrar(""
										+ cobrancaAcao
												.getIndicadorCobrancaDebACobrar());
					} else {
						acaoCobrancaAtualizarActionForm.setIcDebitosACobrar("");
					}
					
					if (cobrancaAcao.getIndicadorBoletim() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcEmitirBoletimCadastro(""
										+ cobrancaAcao.getIndicadorBoletim());
					} else {
						acaoCobrancaAtualizarActionForm.setIcEmitirBoletimCadastro("");
					}
					
					if (cobrancaAcao.getIndicadorGeracaoTaxa() != null) {
						acaoCobrancaAtualizarActionForm.setIcGeraTaxa(""
								+ cobrancaAcao.getIndicadorGeracaoTaxa());
					} else {
						acaoCobrancaAtualizarActionForm.setIcGeraTaxa("");
					}
					
					if (cobrancaAcao.getIndicadorDebito() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcImoveisSemDebitos(""
										+ cobrancaAcao.getIndicadorDebito());
					} else {
						acaoCobrancaAtualizarActionForm.setIcImoveisSemDebitos("");
					}
					
					if (cobrancaAcao.getIndicadorRepeticao() != null) {
						acaoCobrancaAtualizarActionForm.setIcRepetidaCiclo(""
								+ cobrancaAcao.getIndicadorRepeticao());
					} else {
						acaoCobrancaAtualizarActionForm.setIcRepetidaCiclo("");
					}
					
					if (cobrancaAcao.getIndicadorSuspensaoAbastecimento() != null) {
						acaoCobrancaAtualizarActionForm
								.setIcSuspensaoAbastecimento(""
										+ cobrancaAcao
												.getIndicadorSuspensaoAbastecimento());
					} else {
						acaoCobrancaAtualizarActionForm.setIcSuspensaoAbastecimento("");
					}
					
					if (cobrancaAcao.getCobrancaAcaoPredecessora() != null && 
							!cobrancaAcao.getCobrancaAcaoPredecessora().equals("")) {
						acaoCobrancaAtualizarActionForm
								.setIdAcaoPredecessora(""
										+ cobrancaAcao
												.getCobrancaAcaoPredecessora()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdAcaoPredecessora("");
					}

					if (cobrancaAcao.getLigacaoAguaSituacao() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdSituacaoLigacaoAgua(""
										+ cobrancaAcao.getLigacaoAguaSituacao()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdSituacaoLigacaoAgua("");
					}
					
					if (cobrancaAcao.getLigacaoEsgotoSituacao() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdSituacaoLigacaoEsgoto(""
										+ cobrancaAcao
												.getLigacaoEsgotoSituacao()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdSituacaoLigacaoEsgoto("");
					}
					
					if (cobrancaAcao.getDocumentoTipo() != null) {
						acaoCobrancaAtualizarActionForm
								.setIdTipoDocumentoGerado(""
										+ cobrancaAcao.getDocumentoTipo()
												.getId());
					} else {
						acaoCobrancaAtualizarActionForm.setIdTipoDocumentoGerado("");
					}
					
					if (cobrancaAcao.getNumeroDiasMinimoAcaoPrecedente() != null) {
						acaoCobrancaAtualizarActionForm
								.setNumeroDiasEntreAcoes(""
										+ cobrancaAcao
												.getNumeroDiasMinimoAcaoPrecedente());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasEntreAcoes("");
					}
					
					if (cobrancaAcao.getNumeroDiasValidade() != null) {
						acaoCobrancaAtualizarActionForm
								.setNumeroDiasValidade(""
										+ cobrancaAcao.getNumeroDiasValidade());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasValidade("");
					}
					
					if (cobrancaAcao.getOrdemRealizacao() != null) {
						acaoCobrancaAtualizarActionForm.setOrdemCronograma(""
								+ cobrancaAcao.getOrdemRealizacao());
					} else {
						acaoCobrancaAtualizarActionForm.setOrdemCronograma("");
					}
					
					if (cobrancaAcao.getNumeroDiasVencimento() != null) {
						acaoCobrancaAtualizarActionForm.setNumeroDiasVencimento(""
								+ cobrancaAcao.getNumeroDiasVencimento());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasVencimento("");
					}
					
					if (cobrancaAcao.getIndicadorMetasCronograma() != null) {
						acaoCobrancaAtualizarActionForm.setIcMetasCronograma(""
								+ cobrancaAcao.getIndicadorMetasCronograma());
					} else {
						acaoCobrancaAtualizarActionForm.setIcMetasCronograma("");
					}
					
					if (cobrancaAcao.getIndicadorOrdenamentoCronograma() != null) {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoCronograma(""
								+ cobrancaAcao.getIndicadorOrdenamentoCronograma());
					} else {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoCronograma("");
					}
					
					if (cobrancaAcao.getIndicadorOrdenamentoEventual() != null) {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoEventual(""
								+ cobrancaAcao.getIndicadorOrdenamentoEventual());
					} else {
						acaoCobrancaAtualizarActionForm.setIcOrdenamentoEventual("");
					}
					
					if (cobrancaAcao.getIndicadorDebitoInterfereAcao() != null) {
						acaoCobrancaAtualizarActionForm.setIcDebitoInterfereAcao(""
								+ cobrancaAcao.getIndicadorDebitoInterfereAcao());
					} else { 
						acaoCobrancaAtualizarActionForm.setIcDebitoInterfereAcao("");
					}
					
					if (cobrancaAcao.getNumeroDiasRemuneracaoTerceiro() != null) {
						acaoCobrancaAtualizarActionForm.setNumeroDiasRemuneracaoTerceiro(""
								+ cobrancaAcao.getNumeroDiasRemuneracaoTerceiro());
					} else {
						acaoCobrancaAtualizarActionForm.setNumeroDiasRemuneracaoTerceiro("");
					}
					if(cobrancaAcao.getIndicadorCreditosARealizar()!=null){
						acaoCobrancaAtualizarActionForm.setIcCreditosARealizar(
								cobrancaAcao.getIndicadorCreditosARealizar().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcCreditosARealizar("");
					}
					if(cobrancaAcao.getIndicadorNotasPromissoria()!=null){
						acaoCobrancaAtualizarActionForm.setIcNotasPromissoria(
								cobrancaAcao.getIndicadorNotasPromissoria().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcNotasPromissoria("");
					}
					if(cobrancaAcao.getIndicadorOrdenarMaiorValor()!=null){
						acaoCobrancaAtualizarActionForm.setIcOrdenarMaiorValor(
								cobrancaAcao.getIndicadorOrdenarMaiorValor().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcOrdenarMaiorValor("");
					}
					if(cobrancaAcao.getIndicadorValidarItem()!=null){
						acaoCobrancaAtualizarActionForm.setIcValidarItem(
								cobrancaAcao.getIndicadorValidarItem().toString());
					}else{
						acaoCobrancaAtualizarActionForm.setIcValidarItem("");
					}
				}
				// faz as pesquisas obrigatórias
				pesquisasObrigatorias(fachada, sessao);

				// seta o objeto na sessão para ser atualizado
				sessao.setAttribute("cobrancaAcao", cobrancaAcao);

			}
		}

		// pesquisa os dados do enter
		pesquisarEnter(acaoCobrancaAtualizarActionForm, httpServletRequest,
				fachada);

		return retorno;
	}

	private void pesquisarEnter(
			AcaoCobrancaAtualizarActionForm acaoCobrancaAtualizarActionForm,
			HttpServletRequest httpServletRequest, Fachada fachada) {

		// pesquisa enter de critério de cobrança
		if (acaoCobrancaAtualizarActionForm.getIdCobrancaCriterio() != null
				&& !acaoCobrancaAtualizarActionForm.getIdCobrancaCriterio()
						.equals("")
				&& (acaoCobrancaAtualizarActionForm
						.getDescricaoCobrancaCriterio() == null || acaoCobrancaAtualizarActionForm
						.getDescricaoCobrancaCriterio().equals(""))) {

			FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
			try {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.ID, new Integer(
								acaoCobrancaAtualizarActionForm
										.getIdCobrancaCriterio())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Critério de Cobrança");
			}
			filtroCobrancaCriterio
					.setCampoOrderBy(FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO);
			Collection colecaoCobrancaCriterio = fachada.pesquisar(
					filtroCobrancaCriterio, CobrancaCriterio.class.getName());

			if (colecaoCobrancaCriterio != null
					&& !colecaoCobrancaCriterio.isEmpty()) {
				CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) Util
						.retonarObjetoDeColecao(colecaoCobrancaCriterio);
				acaoCobrancaAtualizarActionForm
						.setDescricaoCobrancaCriterio(cobrancaCriterio
								.getDescricaoCobrancaCriterio());
			} else {
				acaoCobrancaAtualizarActionForm.setIdCobrancaCriterio("");
				acaoCobrancaAtualizarActionForm
						.setDescricaoCobrancaCriterio("COBRANÇA CRITÉRIO INEXISTENTE");
			}

		}

		// pesquisa enter de tipo de serviço
		if (acaoCobrancaAtualizarActionForm.getIdServicoTipo() != null
				&& !acaoCobrancaAtualizarActionForm.getIdServicoTipo().equals(
						"")
				&& (acaoCobrancaAtualizarActionForm.getDescricaoServicoTipo() == null || acaoCobrancaAtualizarActionForm
						.getDescricaoServicoTipo().equals(""))) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			try {
				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, new Integer(
								acaoCobrancaAtualizarActionForm
										.getIdServicoTipo())));
			} catch (NumberFormatException ex) {
				throw new ActionServletException(
						"atencao.campo_texto.numero_obrigatorio", null,
						"Serviço Tipo");
			}
			filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
			Collection colecaoServicoTipo = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());

			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) Util
						.retonarObjetoDeColecao(colecaoServicoTipo);
				acaoCobrancaAtualizarActionForm
						.setDescricaoServicoTipo(servicoTipo.getDescricao());
			} else {
				acaoCobrancaAtualizarActionForm.setIdServicoTipo("");
				acaoCobrancaAtualizarActionForm
						.setDescricaoServicoTipo("TIPO DE SERVIÇO INEXISTENTE");
			}

		}
	}

	private void pesquisasObrigatorias(Fachada fachada, HttpSession sessao) {
		// pesquisa as ações predecessoras
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoAcaoPredecessora = fachada.pesquisar(
				filtroCobrancaAcao, CobrancaAcao.class.getName());
		if (colecaoAcaoPredecessora == null
				|| colecaoAcaoPredecessora.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Cobrança Ação");
		} else {
			sessao.setAttribute("colecaoAcaoPredecessora",
					colecaoAcaoPredecessora);
		}

		// pesquisa os tipos de documentos
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());
		if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Documento Tipo");
		} else {
			sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
		}

		// pesquisa as situações de ligações de agua
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroDocumentoTipo
				.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(
				filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		if (colecaoLigacaoAguaSituacao == null
				|| colecaoLigacaoAguaSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Ligação Agua Situação");
		} else {
			sessao.setAttribute("colecaoLigacaoAguaSituacao",
					colecaoLigacaoAguaSituacao);
		}

		// pesquisa as situações de ligações de agua
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		filtroDocumentoTipo
				.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(
				filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class
						.getName());
		if (colecaoLigacaoEsgotoSituacao == null
				|| colecaoLigacaoEsgotoSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Ligação Esgoto Situação");
		} else {
			sessao.setAttribute("colecaoLigacaoEsgotoSituacao",
					colecaoLigacaoEsgotoSituacao);
		}
	}
}

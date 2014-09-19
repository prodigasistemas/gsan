package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
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
 * /**
 * <p>
 * <b>[UC0191]</b> Manter Anormalidade de Leitura
 * </p>
 * <p>
 * Esta funcionalidade permite atualizar uma Anormalidade de Leitura
 * </p>
 * 
 * @author Thiago Tenório, Magno Gouveia
 * @since 31/10/2006, 23/08/2011
 */
public class ExibirAtualizarAnormalidadeLeituraAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAnormalidadeLeitura");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarAnormalidadeLeituraActionForm form = (AtualizarAnormalidadeLeituraActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			form.setTipoServico("");
		}

		Fachada fachada = Fachada.getInstancia();

		String id = null;

		String idLeituraAnormalidade = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
				&& !httpServletRequest.getParameter("idRegistroAtualizacao")
						.equals("")) {

			sessao.removeAttribute("objetoLeituraAnormalidade");
			sessao.removeAttribute("colecaoLeituraAnormalidadeTela");

		}

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		/*
		 * Verifica se o servicoCobrancaValor já está na sessão, em caso
		 * afirmativo significa que o usuário já entrou na tela e apenas
		 * selecionou algum item que deu um reload na tela e em caso negativo
		 * significa que ele está entrando pela primeira vez
		 */

		if (sessao.getAttribute("colecaoLeituraAnormalidadeTela") == null) {

			if (sessao.getAttribute("objetoLeituraAnormalidade") != null) {

				LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) sessao
						.getAttribute("objetoLeituraAnormalidade");

				sessao.setAttribute("idLeituraAnormalidade",
						leituraAnormalidade.getId().toString());

				sessao.setAttribute("leituraAnormalidade", leituraAnormalidade);

				form.setDescricao(leituraAnormalidade.getDescricao());
				form.setAbreviatura(leituraAnormalidade.getDescricaoAbreviada());
				form.setIndicadorRelativoHidrometro(""
						+ leituraAnormalidade.getIndicadorRelativoHidrometro());
				form.setIndicadorImovelSemHidrometro(""
						+ leituraAnormalidade.getIndicadorImovelSemHidrometro());
				form.setUsoRestritoSistema(""
						+ leituraAnormalidade.getIndicadorSistema());
				form.setPerdaTarifaSocial(""
						+ leituraAnormalidade.getIndicadorPerdaTarifaSocial());
				form.setOsAutomatico(""
						+ leituraAnormalidade.getIndicadorEmissaoOrdemServico());
				form.setTipoServico(leituraAnormalidade.getServicoTipo()
						.getId().toString());
				form.setConsumoLeituraNaoInformado(leituraAnormalidade
						.getLeituraAnormalidadeConsumoSemleitura().getId()
						.toString());
				form.setConsumoLeituraInformado(leituraAnormalidade
						.getLeituraAnormalidadeConsumoComleitura().getId()
						.toString());
				form.setLeituraLeituraNaoturaInformado(leituraAnormalidade
						.getLeituraAnormalidadeLeituraSemleitura().getId()
						.toString());
				form.setLeituraLeituraInformado(leituraAnormalidade
						.getLeituraAnormalidadeLeituraComleitura().getId()
						.toString());
				form.setDataUltimaAlteracao(Util
						.formatarData(leituraAnormalidade.getUltimaAlteracao()));
				form.setIndicadorUso("" + leituraAnormalidade.getIndicadorUso());
				form.setNumeroFatorSemLeitura(""
						+ leituraAnormalidade.getNumeroFatorSemLeitura());
				form.setNumeroFatorComLeitura(""
						+ leituraAnormalidade.getNumeroFatorComLeitura());
				form.setIndicadorLeitura(""
						+ leituraAnormalidade.getIndicadorLeitura());
				form.setNumeroVezesSuspendeLeitura(leituraAnormalidade
						.getNumeroVezesSuspendeLeitura().toString());
				form.setNumeroMesesLeituraSuspensa(leituraAnormalidade
						.getNumeroMesesLeituraSuspensa().toString());

				id = leituraAnormalidade.getId().toString();

				sessao.setAttribute("leituraAnormalidadeAtualizar",
						leituraAnormalidade);
				sessao.removeAttribute("objetoLeituraAnormalidade");

			} else {

				LeituraAnormalidade leituraAnormalidade = null;

				idLeituraAnormalidade = null;

				if (httpServletRequest.getParameter("idRegistroAtualizacao") == null
						|| httpServletRequest.getParameter(
								"idRegistroAtualizacao").equals("")) {
					leituraAnormalidade = (LeituraAnormalidade) sessao
							.getAttribute("objetoLeituraAnormalidade");
				} else {
					idLeituraAnormalidade = (String) httpServletRequest
							.getParameter("idRegistroAtualizacao");
					sessao.setAttribute("idRegistroAtualizacao",
							idLeituraAnormalidade);
				}

				if (idLeituraAnormalidade != null) {

					id = idLeituraAnormalidade;

					FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
					filtroLeituraAnormalidade
							.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoSemleitura");
					filtroLeituraAnormalidade
							.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoComleitura");
					filtroLeituraAnormalidade
							.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraSemleitura");
					filtroLeituraAnormalidade
							.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraComleitura");

					filtroLeituraAnormalidade
							.adicionarParametro(new ParametroSimples(
									FiltroLeituraAnormalidade.ID,
									idLeituraAnormalidade));

					Collection<LeituraAnormalidade> colecaoLeituraAnormalidade = fachada
							.pesquisar(filtroLeituraAnormalidade,
									LeituraAnormalidade.class.getName());

					if (colecaoLeituraAnormalidade == null
							|| colecaoLeituraAnormalidade.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					httpServletRequest.setAttribute(
							"colecaoLeituraAnormalidade",
							colecaoLeituraAnormalidade);

					leituraAnormalidade = (LeituraAnormalidade) colecaoLeituraAnormalidade
							.iterator().next();

				}

				if (idLeituraAnormalidade == null) {
					if (sessao.getAttribute("idRegistroAtualizacao") != null) {
						idLeituraAnormalidade = (String) sessao
								.getAttribute("idRegistroAtualizacao");
					} else {
						leituraAnormalidade = (LeituraAnormalidade) sessao
								.getAttribute("leituraAnormalidade");
						idLeituraAnormalidade = leituraAnormalidade.getId()
								.toString();
					}
				}

				form.setDescricao(leituraAnormalidade.getDescricao());

				form.setAbreviatura(leituraAnormalidade.getDescricaoAbreviada());

				form.setIndicadorRelativoHidrometro(""
						+ leituraAnormalidade.getIndicadorRelativoHidrometro());
				form.setIndicadorImovelSemHidrometro(""
						+ leituraAnormalidade.getIndicadorImovelSemHidrometro());
				form.setUsoRestritoSistema(""
						+ leituraAnormalidade.getIndicadorSistema());
				form.setPerdaTarifaSocial(""
						+ leituraAnormalidade.getIndicadorPerdaTarifaSocial());
				form.setOsAutomatico(""
						+ leituraAnormalidade.getIndicadorEmissaoOrdemServico());

				if (leituraAnormalidade.getServicoTipo() != null) {
					form.setTipoServico(leituraAnormalidade.getServicoTipo()
							.getId().toString());
				} else {
					form.setTipoServico("");
				}

				if (leituraAnormalidade
						.getLeituraAnormalidadeConsumoSemleitura() != null) {
					form.setConsumoLeituraNaoInformado(leituraAnormalidade
							.getLeituraAnormalidadeConsumoSemleitura().getId()
							.toString());
				} else {
					form.setConsumoLeituraNaoInformado("");
				}

				if (leituraAnormalidade
						.getLeituraAnormalidadeConsumoComleitura() != null) {
					form.setConsumoLeituraInformado(leituraAnormalidade
							.getLeituraAnormalidadeConsumoComleitura().getId()
							.toString());
				} else {
					form.setConsumoLeituraInformado("");
				}

				if (leituraAnormalidade
						.getLeituraAnormalidadeLeituraSemleitura() != null) {
					form.setLeituraLeituraNaoturaInformado(leituraAnormalidade
							.getLeituraAnormalidadeLeituraSemleitura().getId()
							.toString());
				} else {
					form.setLeituraLeituraNaoturaInformado("");
				}

				if (leituraAnormalidade
						.getLeituraAnormalidadeLeituraComleitura() != null) {
					form.setLeituraLeituraInformado(leituraAnormalidade
							.getLeituraAnormalidadeLeituraComleitura().getId()
							.toString());
				} else {
					form.setLeituraLeituraInformado("");
				}

				form.setIndicadorUso("" + leituraAnormalidade.getIndicadorUso());
				form.setDataUltimaAlteracao(Util
						.formatarDataComHora(leituraAnormalidade
								.getUltimaAlteracao()));

				if (leituraAnormalidade.getNumeroFatorSemLeitura() != null) {
					form.setNumeroFatorSemLeitura(""
							+ leituraAnormalidade.getNumeroFatorSemLeitura());
				}
				if (leituraAnormalidade.getNumeroFatorComLeitura() != null) {
					form.setNumeroFatorComLeitura(""
							+ leituraAnormalidade.getNumeroFatorComLeitura());
				}
				if (leituraAnormalidade.getIndicadorLeitura() != null) {
					form.setIndicadorLeitura(""
							+ leituraAnormalidade.getIndicadorLeitura());
				}

				if (leituraAnormalidade.getNumeroVezesSuspendeLeitura() != null
						&& !leituraAnormalidade.getNumeroVezesSuspendeLeitura()
								.equals("")) {
					form.setNumeroVezesSuspendeLeitura(leituraAnormalidade
							.getNumeroVezesSuspendeLeitura().toString());
				} else {
					form.setNumeroVezesSuspendeLeitura("0");
				}

				if (leituraAnormalidade.getNumeroMesesLeituraSuspensa() != null
						&& !leituraAnormalidade.getNumeroMesesLeituraSuspensa()
								.equals("")) {
					form.setNumeroMesesLeituraSuspensa(leituraAnormalidade
							.getNumeroMesesLeituraSuspensa().toString());
				} else {
					form.setNumeroMesesLeituraSuspensa("0");
				}

				/**
				 * 
				 * Pamela Gatinho - 13/03/2012
				 * Campo que identifica se a anormalidade será usada ou
				 * não no sistema de leitura e impressão simultanea.
				 */
				form.setIndicadorImpressaoSimultanea(""
						+ leituraAnormalidade.getIndicadorImpressaoSimultanea());
				
				sessao.setAttribute("leituraAnormalidadeAtualizar",
						leituraAnormalidade);
			}
		}

		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			sessao.removeAttribute("colecaoLeituraAnormalidadeTela");

			String leituraAnormalidadeID = null;

			if (sessao.getAttribute("idRegistroAtualizacao") != null
					&& !sessao.getAttribute("idRegistroAtualizacao").equals("")) {
				leituraAnormalidadeID = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			}

			if (leituraAnormalidadeID.equalsIgnoreCase("")) {
				leituraAnormalidadeID = null;
			}

			if ((leituraAnormalidadeID == null) && (id == null)) {

				LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) sessao
						.getAttribute("objetoLeituraAnormalidade");

				form.setDescricao(leituraAnormalidade.getDescricao());
				form.setAbreviatura(leituraAnormalidade.getDescricaoAbreviada());
				form.setIndicadorRelativoHidrometro(""
						+ leituraAnormalidade.getIndicadorRelativoHidrometro());
				form.setIndicadorImovelSemHidrometro(""
						+ leituraAnormalidade.getIndicadorImovelSemHidrometro());
				form.setUsoRestritoSistema(""
						+ leituraAnormalidade.getIndicadorSistema());
				form.setPerdaTarifaSocial(""
						+ leituraAnormalidade.getIndicadorPerdaTarifaSocial());
				form.setOsAutomatico(""
						+ leituraAnormalidade.getIndicadorEmissaoOrdemServico());
				form.setTipoServico(leituraAnormalidade.getServicoTipo()
						.getId().toString());
				form.setConsumoLeituraNaoInformado(leituraAnormalidade
						.getLeituraAnormalidadeConsumoSemleitura().getId()
						.toString());
				form.setConsumoLeituraInformado(leituraAnormalidade
						.getLeituraAnormalidadeConsumoComleitura().getId()
						.toString());
				form.setLeituraLeituraNaoturaInformado(leituraAnormalidade
						.getLeituraAnormalidadeLeituraSemleitura().getId()
						.toString());
				form.setLeituraLeituraInformado(leituraAnormalidade
						.getLeituraAnormalidadeLeituraComleitura().getId()
						.toString());

				if (leituraAnormalidade.getNumeroFatorSemLeitura() != null) {
					form.setNumeroFatorSemLeitura(""
							+ leituraAnormalidade.getNumeroFatorSemLeitura());
				}
				if (leituraAnormalidade.getNumeroFatorComLeitura() != null) {
					form.setNumeroFatorComLeitura(""
							+ leituraAnormalidade.getNumeroFatorComLeitura());
				}
				if (leituraAnormalidade.getIndicadorLeitura() != null) {
					form.setIndicadorLeitura(""
							+ leituraAnormalidade.getIndicadorLeitura());
				}

				form.setNumeroVezesSuspendeLeitura(leituraAnormalidade
						.getNumeroVezesSuspendeLeitura().toString());
				form.setNumeroMesesLeituraSuspensa(leituraAnormalidade
						.getNumeroMesesLeituraSuspensa().toString());

				sessao.setAttribute("leituraAnormalidadeAtualizar",
						leituraAnormalidade);
				sessao.removeAttribute("leituraAnormalidade");
			}

			if ((idLeituraAnormalidade == null) && (id != null)) {
				idLeituraAnormalidade = id;
			}

			if (idLeituraAnormalidade != null) {

				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				filtroLeituraAnormalidade
						.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoSemleitura");
				filtroLeituraAnormalidade
						.adicionarParametro(new ParametroSimples(
								FiltroLeituraAnormalidade.ID,
								idLeituraAnormalidade));

				Collection<LeituraAnormalidade> colecaoLeituraAnormalidade = fachada
						.pesquisar(filtroLeituraAnormalidade,
								LeituraAnormalidade.class.getName());

				if (Util.isVazioOrNulo(colecaoLeituraAnormalidade)) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoLeituraAnormalidade",
						colecaoLeituraAnormalidade);

				LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) colecaoLeituraAnormalidade
						.iterator().next();

				form.setAbreviatura(leituraAnormalidade.getDescricaoAbreviada());
				form.setIndicadorRelativoHidrometro(""
						+ leituraAnormalidade.getIndicadorRelativoHidrometro());
				form.setIndicadorImovelSemHidrometro(""
						+ leituraAnormalidade.getIndicadorImovelSemHidrometro());
				form.setUsoRestritoSistema(""
						+ leituraAnormalidade.getIndicadorSistema());
				form.setPerdaTarifaSocial(""
						+ leituraAnormalidade.getIndicadorPerdaTarifaSocial());
				form.setOsAutomatico(""
						+ leituraAnormalidade.getIndicadorEmissaoOrdemServico());
				form.setTipoServico(leituraAnormalidade.getServicoTipo()
						.getId().toString());
				form.setConsumoLeituraNaoInformado(leituraAnormalidade
						.getLeituraAnormalidadeConsumoSemleitura().getId()
						.toString());
				form.setConsumoLeituraInformado(leituraAnormalidade
						.getLeituraAnormalidadeConsumoComleitura().getId()
						.toString());
				form.setLeituraLeituraNaoturaInformado(leituraAnormalidade
						.getLeituraAnormalidadeLeituraSemleitura().getId()
						.toString());
				form.setLeituraLeituraInformado(leituraAnormalidade
						.getLeituraAnormalidadeLeituraComleitura().getId()
						.toString());

				if (leituraAnormalidade.getNumeroFatorSemLeitura() != null) {
					form.setNumeroFatorSemLeitura(""
							+ leituraAnormalidade.getNumeroFatorSemLeitura());
				}
				if (leituraAnormalidade.getNumeroFatorComLeitura() != null) {
					form.setNumeroFatorComLeitura(""
							+ leituraAnormalidade.getNumeroFatorComLeitura());
				}
				if (leituraAnormalidade.getIndicadorLeitura() != null) {
					form.setIndicadorLeitura(""
							+ leituraAnormalidade.getIndicadorLeitura());
				}

				form.setNumeroVezesSuspendeLeitura(leituraAnormalidade
						.getNumeroVezesSuspendeLeitura().toString());
				form.setNumeroMesesLeituraSuspensa(leituraAnormalidade
						.getNumeroMesesLeituraSuspensa().toString());

				httpServletRequest.setAttribute("idLeituraAnormalidade",
						idLeituraAnormalidade);
				
				/**
				 * 
				 * Pamela Gatinho - 13/03/2012
				 * Campo que identifica se a anormalidade será usada ou
				 * não no sistema de leitura e impressão simultanea.
				 */
				form.setIndicadorImpressaoSimultanea(""
					+ leituraAnormalidade.getIndicadorImpressaoSimultanea());
				
				sessao.setAttribute("leituraAnormalidadeAtualizar",
						leituraAnormalidade);
			}
		}
		// -------------- bt DESFAZER ---------------

		httpServletRequest.setAttribute("colecaoLeituraAnormalidadeTela",
				sessao.getAttribute("colecaoLeituraAnormalidadeTipoValorTela"));

		return retorno;

	}

}

package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * <<Descrição da Classe>>
 * 
 * @author lms
 * @date 14/08/2006
 */
public class ExibirGerarOrdemServicoInserirRAAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String forward = "exibirGerarOrdemServicoInserirRAPopup";

		ActionForward retorno = actionMapping.findForward(forward);
		GerarOrdemServicoActionForm form = (GerarOrdemServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		// Imovel
		Imovel imovel = null;

		if (httpServletRequest.getParameter("idImovel") != null) {
			Integer idImovel = Util
					.converterStringParaInteger(httpServletRequest
							.getParameter("idImovel"));
			imovel = new Imovel();
			imovel.setId(idImovel);
		}

		ServicoTipo servicoTipo = null;

		// caso venha do encerrar OS popup
		if (httpServletRequest.getParameter("caminhoRetornoOS") != null
				&& !httpServletRequest.getParameter("caminhoRetornoOS").equals(
						"")) {
			sessao.setAttribute("caminhoRetornoOS", httpServletRequest
					.getParameter("caminhoRetornoOS"));
		}
		// caso venha do encerrar os popup
		String veioEncerrarOS = (String) httpServletRequest
				.getAttribute("veioEncerrarOS");
		if (veioEncerrarOS == null) {
			// caso venha do encerrar os
			veioEncerrarOS = httpServletRequest.getParameter("veioEncerrarOS");
		}
		// caso venha do caso de uso de encerrar OS
		if (veioEncerrarOS != null && !veioEncerrarOS.equals("")) {
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.INDICADOR_FISCALIZACAO_SERVICO_TIPO_REF,
					ServicoTipoReferencia.INDICADOR_FISCALIZACAO_SIM));
			Collection colecaoServicoTipo = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());
			if(colecaoServicoTipo == null || colecaoServicoTipo.isEmpty()){
				throw new ActionServletException(
				"atencao.servico_tipo.sem_fiscalizacao");	
			}
			sessao.setAttribute("colecaoServicosTipo", colecaoServicoTipo);
			// se vier o id da RA então seta na ordem de serviço
			// caso venha do encerrar os popup
			String numeroRA = (String) httpServletRequest
					.getAttribute("numeroRA");
			if (numeroRA == null) {
				// caso venha do encerrar os
				numeroRA = httpServletRequest.getParameter("numeroRA");
			}
			if (numeroRA != null && !numeroRA.equals("")) {
				RegistroAtendimento registroAtendimento = new RegistroAtendimento();
				registroAtendimento.setId(new Integer(numeroRA));
				// [SF0004] - Verificar unidade do usuário
				fachada.verificarUnidadeUsuario(registroAtendimento,
						usuarioLogado);
				form.getOrdemServico().setRegistroAtendimento(
						registroAtendimento);
			}
			// se vier o id da OS então seta o id da os como OSReferencia então
			// seta na ordem de serviço
			// caso venha do encerrar os popup
			String numeroOS = (String) httpServletRequest
					.getAttribute("numeroOS");
			if (numeroOS == null) {
				// caso venha do encerrar os
				numeroOS = httpServletRequest.getParameter("numeroOS");
			}
			if (numeroOS != null && !numeroOS.equals("")) {
				// Ordem de Serviço Referência

				Integer idOrdemServicoReferencia = Util
						.converterStringParaInteger(numeroOS);

				if (Util.validarNumeroMaiorQueZERO(idOrdemServicoReferencia)) {
					OrdemServico os = fachada
							.pesquisarOrdemServico(idOrdemServicoReferencia);
					form.setIdOrdemServicoReferencia(os.getId().toString());
					form.setDescricaoOrdemServicoReferencia(os.getServicoTipo()
							.getDescricao());
					form.getOrdemServico().setOsReferencia(os);
					sessao.setAttribute("veioEncerrarOS", "SIM");
				}
			}
		} else {

			String idTipoSolicitacao = (String) httpServletRequest
					.getParameter("idTipoSolicitacao");
			if (idTipoSolicitacao != null && !idTipoSolicitacao.equals("")) {
				boolean existe = false;
				FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.ID,
						new Integer(idTipoSolicitacao)));

				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO_ID,
						SolicitacaoTipoGrupo.ID_OPERACIONAL_AGUA_COM_DIAGNOSTICO));
				Collection colecaoSolicitacaoTipo = fachada.pesquisar(
						filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
				if (colecaoSolicitacaoTipo != null
						&& !colecaoSolicitacaoTipo.isEmpty()) {
					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
					filtroServicoTipo
							.adicionarParametro(new ParametroSimples(
									FiltroServicoTipo.INDICADOR_DIAGNOSTICO_SERVICO_TIPO_REF,
									ServicoTipoReferencia.INDICADOR_DIAGNOSTICO_ATIVO));
					Collection colecaoServicoTipo = fachada.pesquisar(
							filtroServicoTipo, ServicoTipo.class.getName());
					if (colecaoServicoTipo != null
							&& !colecaoServicoTipo.isEmpty()) {
						existe = true;
						Integer idServicoTipo = ((ServicoTipo) Util
								.retonarObjetoDeColecao(colecaoServicoTipo))
								.getId();

						String valorServicoOriginal = null;
						Integer idServicoTipoPrioridadeOriginal = null;
						String descricaoServicoTipoPrioridadeOriginal = null;

						servicoTipo = fachada
								.pesquisarSevicoTipo(idServicoTipo);
						form.setIdServicoTipo("" + idServicoTipo);
						String descricaoServicoTipo = servicoTipo
								.getDescricao();
						if (servicoTipo.getValor() != null) {
							valorServicoOriginal = servicoTipo.getValor()
									.toString();
						}
						if (servicoTipo.getServicoTipoPrioridade() != null) {
							idServicoTipoPrioridadeOriginal = servicoTipo
									.getServicoTipoPrioridade().getId();
							descricaoServicoTipoPrioridadeOriginal = servicoTipo
									.getServicoTipoPrioridade().getDescricao();
							ServicoTipoPrioridade servicoTipoPrioridadeOriginal = new ServicoTipoPrioridade();
							servicoTipoPrioridadeOriginal.setId(idServicoTipoPrioridadeOriginal);
							form.getOrdemServico()
									.setServicoTipoPrioridadeOriginal(
											servicoTipoPrioridadeOriginal);
						}
						form.getOrdemServico().setServicoTipo(servicoTipo);
						form.getOrdemServico().setImovel(imovel);

						form.setDescricaoServicoTipo(descricaoServicoTipo);
						form.setValorServicoOriginal(valorServicoOriginal);
						form
								.setIdPrioridadeServicoOriginal(idServicoTipoPrioridadeOriginal
										+ "");
						form
								.setDescricaoPrioridadeServicoOriginal(descricaoServicoTipoPrioridadeOriginal);

						// Serviço Tipo Referência

						// Serviço Tipo
						Integer idServicoTipoReferencia = Util
								.converterStringParaInteger(httpServletRequest
										.getParameter("idServicoTipo"));

						if (Util
								.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
							ServicoTipo st = fachada
									.pesquisarSevicoTipo(idServicoTipoReferencia);
							form.setIdServicoTipoReferencia(st.getId()
									.toString());
							form.setDescricaoServicoTipoReferencia(st
									.getDescricao());
							form.getOrdemServico().setServicoTipoReferencia(st);
						}

						httpServletRequest.setAttribute(
								"solicitacaoEspecificacaoOperacionalAgua",
								"SIM");

					}

				}
				if (!existe) {
					// Serviço Tipo
					Integer idServicoTipo = Util
							.converterStringParaInteger(httpServletRequest
									.getParameter("idServicoTipo"));
					String valorServicoOriginal = null;
					Integer idServicoTipoPrioridadeOriginal = null;
					String descricaoServicoTipoPrioridadeOriginal = null;

					servicoTipo = fachada.pesquisarSevicoTipo(idServicoTipo);
					String descricaoServicoTipo = servicoTipo.getDescricao();
					if (servicoTipo.getValor() != null) {
						valorServicoOriginal = servicoTipo.getValor()
								.toString();
					}
					if (servicoTipo.getServicoTipoPrioridade() != null) {
						idServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getId();
						descricaoServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getDescricao();
						ServicoTipoPrioridade servicoTipoPrioridadeOriginal = new ServicoTipoPrioridade();
						form.getOrdemServico()
								.setServicoTipoPrioridadeOriginal(
										servicoTipoPrioridadeOriginal);
					}
					form.getOrdemServico().setServicoTipo(servicoTipo);
					form.getOrdemServico().setImovel(imovel);

					form.setDescricaoServicoTipo(descricaoServicoTipo);
					form.setValorServicoOriginal(valorServicoOriginal);
					form
							.setIdPrioridadeServicoOriginal(idServicoTipoPrioridadeOriginal
									+ "");
					form
							.setDescricaoPrioridadeServicoOriginal(descricaoServicoTipoPrioridadeOriginal);

					// Ordem de Serviço Referência

					Integer idOrdemServicoReferencia = Util
							.converterStringParaInteger(form
									.getIdOrdemServicoReferencia());

					if (Util
							.validarNumeroMaiorQueZERO(idOrdemServicoReferencia)) {
						OrdemServico os = fachada
								.pesquisarOrdemServico(idOrdemServicoReferencia);
						form.setIdOrdemServicoReferencia(os.getId().toString());
						form.setDescricaoOrdemServicoReferencia(os
								.getServicoTipo().getDescricao());
						form.getOrdemServico().setOsReferencia(os);
					}

					// Serviço Tipo Referência

					Integer idServicoTipoReferencia = Util
							.converterStringParaInteger(form
									.getIdServicoTipoReferencia());

					if (Util.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
						ServicoTipo st = fachada
								.pesquisarSevicoTipo(idServicoTipoReferencia);
						form.setIdServicoTipoReferencia(st.getId().toString());
						form.setDescricaoServicoTipoReferencia(st
								.getDescricao());
						form.getOrdemServico().setServicoTipoReferencia(st);
					}

				}
			} else {
				// Serviço Tipo
				Integer idServicoTipo = null;
				if (httpServletRequest.getParameter("idServicoTipo") != null
						&& !httpServletRequest.getParameter("idServicoTipo")
								.equals("")) {
					// Serviço Tipo
					idServicoTipo = Util
							.converterStringParaInteger(httpServletRequest
									.getParameter("idServicoTipo"));
				}
				if (form.getIdServicoTipo() != null
						&& !form.getIdServicoTipo().equals("")) {
					// Serviço Tipo
					idServicoTipo = Util.converterStringParaInteger(form
							.getIdServicoTipo());
				}
				if (idServicoTipo != null) {
					String valorServicoOriginal = null;
					Integer idServicoTipoPrioridadeOriginal = null;
					String descricaoServicoTipoPrioridadeOriginal = null;

					servicoTipo = fachada.pesquisarSevicoTipo(idServicoTipo);
					String descricaoServicoTipo = servicoTipo.getDescricao();
					if (servicoTipo.getValor() != null) {
						valorServicoOriginal = servicoTipo.getValor()
								.toString();
					}
					if (servicoTipo.getServicoTipoPrioridade() != null) {
						idServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getId();
						descricaoServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getDescricao();
						ServicoTipoPrioridade servicoTipoPrioridadeOriginal = new ServicoTipoPrioridade();
						servicoTipoPrioridadeOriginal.setId(idServicoTipoPrioridadeOriginal);
						form.getOrdemServico()
								.setServicoTipoPrioridadeOriginal(
										servicoTipoPrioridadeOriginal);
					}
					form.getOrdemServico().setServicoTipo(servicoTipo);
					if (imovel != null) {
						form.getOrdemServico().setImovel(imovel);
					}

					form.setDescricaoServicoTipo(descricaoServicoTipo);
					form.setValorServicoOriginal(valorServicoOriginal);
					form
							.setIdPrioridadeServicoOriginal(idServicoTipoPrioridadeOriginal
									+ "");
					form
							.setDescricaoPrioridadeServicoOriginal(descricaoServicoTipoPrioridadeOriginal);
				}

				// Ordem de Serviço Referência
				if (sessao.getAttribute("veioEncerrarOS") == null
						|| sessao.getAttribute("veioEncerrarOS").equals("")) {
					Integer idOrdemServicoReferencia = Util
							.converterStringParaInteger(form
									.getIdOrdemServicoReferencia());

					if (Util
							.validarNumeroMaiorQueZERO(idOrdemServicoReferencia)) {
						OrdemServico os = fachada
								.pesquisarOrdemServico(idOrdemServicoReferencia);
						form.setIdOrdemServicoReferencia(os.getId().toString());
						form.setDescricaoOrdemServicoReferencia(os
								.getServicoTipo().getDescricao());
						form.getOrdemServico().setOsReferencia(os);
					}
					// Serviço Tipo Referência
					if (form.getIdServicoTipoReferencia() != null
							&& !form.getIdServicoTipoReferencia().equals("")) {
						Integer idServicoTipoReferencia = Util
								.converterStringParaInteger(form
										.getIdServicoTipoReferencia());

						if (Util
								.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
							ServicoTipo st = fachada
									.pesquisarSevicoTipo(idServicoTipoReferencia);
							form.setIdServicoTipoReferencia(st.getId()
									.toString());
							form.setDescricaoServicoTipoReferencia(st
									.getDescricao());
							form.getOrdemServico().setServicoTipoReferencia(st);
						}
					}

				}
			}
		}

		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
		filtroServicoTipoPrioridade
				.setCampoOrderBy(FiltroCreditoTipo.DESCRICAO);
		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(
				FiltroCreditoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoServicoTipoPrioridade = fachada.pesquisar(
				filtroServicoTipoPrioridade, ServicoTipoPrioridade.class
						.getName());

		sessao.setAttribute("colecaoServicoTipoPrioridade",
				colecaoServicoTipoPrioridade);

		httpServletRequest.setAttribute("servicoTipo", servicoTipo);

		form.getOrdemServico().setObservacao(form.getObservacao());
		if (form.getIdPrioridadeServicoAtual() != null
				&& !form.getIdPrioridadeServicoAtual().equals("")) {
			ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();
			servicoTipoPrioridade.setId(new Integer(form
					.getIdPrioridadeServicoAtual()));
			form.getOrdemServico().setServicoTipoPrioridadeAtual(
					servicoTipoPrioridade);
		} else {
			form.getOrdemServico().setServicoTipoPrioridadeAtual(null);
		}

		if (httpServletRequest.getParameter("close") != null) {
			if (sessao.getAttribute("caminhoRetornoOS") != null
					&& !sessao.getAttribute("caminhoRetornoOS").equals("")) {
				httpServletRequest.setAttribute("close", sessao
						.getAttribute("caminhoRetornoOS"));
			} else {
				httpServletRequest.setAttribute("close", Boolean.TRUE);
			}

			
			sessao.setAttribute("ordemServicoFiscalizacao", form.getOrdemServico());
			sessao.removeAttribute("veioEncerrarOS");
			sessao.removeAttribute("caminhoRetornoOS");
		}

		return retorno;

	}
}

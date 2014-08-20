package gcom.gui.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaCriterio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança [UC0326] Filtrar Comandos de
 * Ação de Conbrança - Eventual
 */
public class ExibirFiltrarComandosAcaoCobrancaEventualAction extends GcomAction {

	private String localidadeID = null;

	private String setorComercialCD = null;

	private HttpSession sessao;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = actionMapping.findForward("exibirFiltrarComandosAcaoCobrancaEventual");

		Fachada fachada = Fachada.getInstancia();

		FiltrarComandosAcaoCobrancaEventualActionForm form = (FiltrarComandosAcaoCobrancaEventualActionForm) actionForm;

		String situacaoComando = form.getSituacaoComando();

		if (situacaoComando == null) {
			form.setSituacaoComando("Todos");
		}

		String indicadorCriterio = form.getIndicadorCriterio();

		if (indicadorCriterio == null) {
			form.setIndicadorCriterio("Todos");
		}

		sessao = request.getSession(false);

		sessao.removeAttribute("filtroCobrancaAcaoAtividadeComando");

		String carregando = request.getParameter("carregando");

		if (carregando != null && !carregando.equals("")) {

			if (sessao.getAttribute("filtrarComandosAcaoCobrancaEventualActionForm") != null) {

				FiltrarComandosAcaoCobrancaEventualActionForm formRecarregar = (FiltrarComandosAcaoCobrancaEventualActionForm) 
						sessao.getAttribute("filtrarComandosAcaoCobrancaEventualActionForm");

				form.setAcaoCobranca(formRecarregar.getAcaoCobranca());
				form.setIndicadorCriterio(formRecarregar.getIndicadorCriterio());
				form.setCriterioCobranca(formRecarregar.getCriterioCobranca());
				form.setAtividadeCobranca(formRecarregar.getAtividadeCobranca());
				form.setGrupoCobranca(formRecarregar.getGrupoCobranca());
				form.setGerenciaRegional(formRecarregar.getGerenciaRegional());
				form.setLocalidadeOrigemID(formRecarregar.getLocalidadeOrigemID());
				form.setLocalidadeDestinoID(formRecarregar.getLocalidadeDestinoID());
				form.setNomeLocalidadeOrigem(formRecarregar.getNomeLocalidadeOrigem());
				form.setNomeLocalidadeDestino(formRecarregar.getNomeLocalidadeDestino());
				form.setSetorComercialOrigemCD(formRecarregar.getSetorComercialOrigemCD());
				form.setSetorComercialOrigemID(formRecarregar.getSetorComercialOrigemID());
				form.setSetorComercialDestinoCD(formRecarregar.getSetorComercialDestinoCD());
				form.setSetorComercialDestinoID(formRecarregar.getSetorComercialDestinoID());
				form.setNomeSetorComercialOrigem(formRecarregar.getNomeSetorComercialOrigem());
				form.setNomeSetorComercialDestino(formRecarregar.getNomeSetorComercialDestino());
				form.setUnidadeNegocio(formRecarregar.getUnidadeNegocio());
				form.setDataEmissaoInicio(formRecarregar.getDataEmissaoInicio());
				form.setDataEmissaoFim(formRecarregar.getDataEmissaoFim());
				form.setIdCobrancaAcaoAtividadeComando(formRecarregar.getIdCobrancaAcaoAtividadeComando());

				if (formRecarregar.getRotaInicial() != null && !formRecarregar.getRotaInicial().equals("")) {
					carregarRota(form, fachada, formRecarregar.getSetorComercialOrigemCD(), formRecarregar.getLocalidadeOrigemID());
					form.setRotaInicial(formRecarregar.getRotaInicial());
				} else {
					form.setRotaInicial(null);
					sessao.setAttribute("colecaoRota", null);
				}
				
				if (formRecarregar.getRotaFinal() != null && !formRecarregar.getRotaFinal().equals("")) {
					form.setRotaFinal(formRecarregar.getRotaFinal());
				} else {
					form.setRotaFinal(null);
					sessao.setAttribute("colecaoRota", null);
				}

				form.setClienteRelacaoTipo(formRecarregar.getClienteRelacaoTipo());
				form.setIdCliente(formRecarregar.getIdCliente());
				form.setNomeCliente(formRecarregar.getNomeCliente());
				form.setPeriodoComandoInicial(formRecarregar.getPeriodoComandoInicial());
				form.setPeriodoComandoFinal(formRecarregar.getPeriodoComandoFinal());
				form.setPeriodoRealizacaoComandoInicial(formRecarregar.getPeriodoRealizacaoComandoInicial());
				form.setPeriodoRealizacaoComandoFinal(formRecarregar.getPeriodoRealizacaoComandoFinal());
				form.setPeriodoReferenciaContasInicial(formRecarregar.getPeriodoReferenciaContasInicial());
				form.setPeriodoReferenciaContasFinal(formRecarregar.getPeriodoReferenciaContasFinal());
				form.setPeriodoVencimentoContasInicial(formRecarregar.getPeriodoVencimentoContasInicial());
				form.setPeriodoVencimentoContasFinal(formRecarregar.getPeriodoVencimentoContasFinal());
				form.setIntervaloValorDocumentosInicial(formRecarregar.getIntervaloValorDocumentosInicial());
				form.setIntervaloValorDocumentosFinal(formRecarregar.getIntervaloValorDocumentosFinal());
				form.setIntervaloQuantidadeDocumentosInicial(formRecarregar.getIntervaloQuantidadeDocumentosInicial());
				form.setIntervaloQuantidadeDocumentosFinal(formRecarregar.getIntervaloQuantidadeDocumentosFinal());
				form.setIntervaloQuantidadeItensDocumentosInicial(formRecarregar.getIntervaloQuantidadeItensDocumentosInicial());
				form.setIntervaloQuantidadeItensDocumentosFinal(formRecarregar.getIntervaloQuantidadeItensDocumentosFinal());
				form.setSituacaoComando(formRecarregar.getSituacaoComando());
			}

		}

		// CARREGAR AS COBRANÇAS GRUPO
		if (sessao.getAttribute("colecaoGrupoCobranca") == null) {
			sessao.setAttribute("colecaoGrupoCobranca", fachada.obterColecaoCobrancaGrupo());
		}

		// CARREGAR AS COBRANÇAS ATIVIDADE
		if (sessao.getAttribute("colecaoAtividadeCobranca") == null) {
			sessao.setAttribute("colecaoAtividadeCobranca", fachada.obterColecaoCobrancaAtividade());
		}

		// CARREGAR AS COBRANÇAS ACAO
		if (sessao.getAttribute("colecaoAcaoCobranca") == null) {
			sessao.setAttribute("colecaoAcaoCobranca", fachada.obterColecaoCobrancaAcao());
		}

		// CARREGAR AS GERENCIAIS REGIONAIS
		if (sessao.getAttribute("colecaoGerenciaRegional") == null) {
			sessao.setAttribute("colecaoGerenciaRegional", fachada.obterColecaoGerenciaRegional());
		}

		// CARREGAR AS UNIDADE NEGOCIO
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			sessao.setAttribute("colecaoUnidadeNegocio", fachada.obterColecaoGerenciaRegional());
		}

		// CARREGAR OS CLIENTE RELACAO TIPO
		if (sessao.getAttribute("colecaoClienteRelacaoTipo") == null) {
			sessao.setAttribute("colecaoClienteRelacaoTipo", fachada.obterColecaoClienteRelacaoTipo());
		}

		// CARREGAR OS TITULOS DE COBRANCA ACAO ATIVIDAD COMAND
		if (sessao.getAttribute("colecaoCobrancaAcaoAtividadeComando") == null) {
			Collection colecaoAtividadesEventuaisAcaoCobrancaComandadas = fachada.obterListaAtividadesEventuaisAcaoCobrancaComandadas();
			sessao.setAttribute("colecaoCobrancaAcaoAtividadeComando", colecaoAtividadesEventuaisAcaoCobrancaComandadas);
		}

		String objetoConsulta = (String) request.getParameter("objetoConsulta");
		String inscricaoTipo = (String) request.getParameter("inscricaoTipo");
		String rota = (String) request.getParameter("rota");

		// carregar as rotas
		if (rota != null && !rota.trim().equalsIgnoreCase("")) {
			carregarRota(form, fachada, form.getSetorComercialOrigemCD(), form.getLocalidadeOrigemID());
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null && !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:
				pesquisarLocalidade(inscricaoTipo, form, fachada, request);

				break;
			// Setor Comercial
			case 2:
				pesquisarLocalidade(inscricaoTipo, form, fachada, request);
				pesquisarSetorComercial(inscricaoTipo, form, fachada, request);

				break;
			case 3:
				pesquisarCliente(inscricaoTipo, form, fachada, request);
				break;

			default:
				break;
			}
		}

		String criterioCobranca = form.getCriterioCobranca();
		// pesquisar o critério de cobrança
		if (criterioCobranca != null && !criterioCobranca.equals("")) {

			CobrancaCriterio cobrancaCriterio = fachada.obterCobrancaCriterio(criterioCobranca);

			if (cobrancaCriterio == null) {
				form.setCriterioCobranca("");
				form.setNomeCriterioCobranca("Critério de Cobrança Inexistente");
				request.setAttribute("corCriterioCobranca", "exception");
				request.setAttribute("nomeCampo", "criterioCobranca");
			} else {
				form.setCriterioCobranca(String.valueOf(cobrancaCriterio.getId()));
				form.setNomeCriterioCobranca(cobrancaCriterio.getDescricaoCobrancaCriterio());
				request.setAttribute("corCriterioCobranca", "valor");
				request.setAttribute("nomeCampo", "grupoCobranca");
			}
		}

		return retorno;
	}

	/**
	 * Pesquisa a Localidade
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param request
	 */
	private void pesquisarLocalidade(String inscricaoTipo,
			FiltrarComandosAcaoCobrancaEventualActionForm form,
			Fachada fachada, HttpServletRequest request) {

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			form.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) form.getLocalidadeOrigemID();

			Localidade objetoLocalidade = fachada.obterLocalidadeGerenciaRegional(localidadeID);

			if (objetoLocalidade == null) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				form.setLocalidadeOrigemID("");
				form.setNomeLocalidadeOrigem("Localidade Inexistente");
				request.setAttribute("corLocalidadeOrigem", "exception");
				request.setAttribute("nomeCampo", "localidadeOrigemID");

			} else {
				form.setLocalidadeOrigemID(String.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidadeOrigem(objetoLocalidade.getDescricao());
				request.setAttribute("corLocalidadeOrigem", "valor");

				String localidadeDestinoID = (String) form.getLocalidadeDestinoID();
				// verifica o valor das localidades, origem e final
				if (localidadeDestinoID != null) {

					if (localidadeDestinoID.equals("")) {
						form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
						form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
					} else {
						int localidadeDestino = new Integer(localidadeDestinoID).intValue();
						int localidadeOrigem = objetoLocalidade.getId().intValue();
						if (localidadeOrigem > localidadeDestino) {
							form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
							form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
						}
					}
				}
				
				request.setAttribute("nomeCampo", "localidadeDestinoID");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) form.getLocalidadeDestinoID();

			Localidade objetoLocalidade = fachada.obterLocalidadeGerenciaRegional(localidadeID);

			form.setInscricaoTipo("destino");

			if (objetoLocalidade == null) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				form.setLocalidadeDestinoID("");
				form.setNomeLocalidadeDestino("Localidade inexistente");
				request.setAttribute("corLocalidadeDestino", "exception");
				request.setAttribute("nomeCampo", "localidadeDestinoID");
			} else {
				int localidadeDestino = objetoLocalidade.getId().intValue();

				String localidade = (String) form.getLocalidadeOrigemID();

				if (localidade != null && !localidade.equals("")) {

					int localidadeOrigem = new Integer(localidade).intValue();
					if (localidadeDestino < localidadeOrigem) {
						form.setLocalidadeDestinoID("");
						request.setAttribute("mensagem", "Localidae Final menor que o Inicial");
						form.setNomeLocalidadeDestino("");
						request.setAttribute("corLocalidadeDestino", "valor");
						request.setAttribute("nomeCampo", "localidadeDestinoID");
					} else {
						form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
						form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
						request.setAttribute("corLocalidadeDestino", "valor");
						request.setAttribute("nomeCampo", "setorComercialOrigemCD");

						pesquisarLocalidade("origem", form, fachada, request);
					}
				} else {
					form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
					form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
					request.setAttribute("corLocalidadeDestino", "valor");
					request.setAttribute("nomeCampo", "setorComercialOrigemCD");
					pesquisarLocalidade("origem", form, fachada, request);
				}
			}
		}
	}

	/**
	 * Pesquisa o Setor Comercial para ser usado no Filtrar Comandos de Ação de
	 * Cobrança
	 * 
	 * @param inscricaoTipo
	 * @param form
	 * @param fachada
	 * @param request
	 */
	private void pesquisarSetorComercial(String inscricaoTipo,
			FiltrarComandosAcaoCobrancaEventualActionForm form,
			Fachada fachada, HttpServletRequest request) {

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			form.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) form.getLocalidadeOrigemID();

			// Recebe o valor do campo localidadeOrigemID do formulário.
			setorComercialCD = (String) form.getSetorComercialOrigemCD();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				SetorComercial objetoSetorComercial = fachada.obterSetorComercialLocalidade(localidadeID, setorComercialCD);

				if (objetoSetorComercial == null) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					form.setSetorComercialOrigemCD("");
					form.setSetorComercialOrigemID("");
					form.setNomeSetorComercialOrigem("Setor Comercial Inexistente");
					request.setAttribute("corSetorComercialOrigem", "exception");
					form.setRotaInicial(null);
					form.setRotaFinal(null);
					request.setAttribute("nomeCampo", "setorComercialOrigemCD");

				} else {
					// setorComercialOrigem
					form.setSetorComercialOrigemCD(String.valueOf(objetoSetorComercial.getCodigo()));
					form.setSetorComercialOrigemID(String.valueOf(objetoSetorComercial.getId()));
					form.setNomeSetorComercialOrigem(objetoSetorComercial.getDescricao());
					request.setAttribute("corSetorComercialOrigem", "valor");

					String setorComercialDestinoCD = (String) form.getSetorComercialDestinoCD();

					// verifica o valor dos setores comerciais, origem e final
					if (setorComercialDestinoCD != null) {

						if (setorComercialDestinoCD.equals("")) {

							// setorComercialDestino
							form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
							form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
							form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());

							carregarRota(form, fachada, objetoSetorComercial.getCodigo() + "", localidadeID);

						} else {
							int setorDestino = new Integer(setorComercialDestinoCD).intValue();
							int setorOrigem = objetoSetorComercial.getCodigo();
							if (setorOrigem > setorDestino) {

								// setorComercialDestino
								form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
								form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
								form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());

								carregarRota(form, fachada,objetoSetorComercial.getCodigo() + "", localidadeID);
							}
						}
						
						request.setAttribute("nomeCampo", "setorComercialDestinoCD");
					}
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				form.setSetorComercialOrigemCD("");
				form.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				request.setAttribute("corSetorComercialOrigem", "exception");
				request.setAttribute("nomeCampo", "setorComercialOrigemCD");
			}
		} else {
			form.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) form.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form.getSetorComercialDestinoCD();

				SetorComercial objetoSetorComercial = fachada.obterSetorComercialLocalidade(localidadeID, setorComercialCD);

				if (objetoSetorComercial == null) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					form.setSetorComercialDestinoCD("");
					form.setSetorComercialDestinoID("");
					form.setNomeSetorComercialDestino("Setor Comercial Inexistente");
					request.setAttribute("corSetorComercialDestino", "exception");
					form.setRotaInicial(null);
					form.setRotaFinal(null);
					request.setAttribute("nomeCampo", "setorComercialDestinoCD");
				} else {
					int setorDestino = objetoSetorComercial.getCodigo();

					String setor = (String) form.getSetorComercialOrigemCD();

					if (setor != null && !setor.equals("")) {

						int setorOrigem = new Integer(setor).intValue();
						
						if (setorDestino < setorOrigem) {

							form.setSetorComercialDestinoCD("");
							form.setSetorComercialDestinoID("");
							request.setAttribute("mensagem", "Setor Comercial Final menor que o Inicial");
							form.setNomeSetorComercialDestino("");
							request.setAttribute("corSetorComercialDestino", "valor");
							form.setRotaInicial(null);
							form.setRotaFinal(null);
							request.setAttribute("nomeCampo", "setorComercialDestinoCD");
						} else {
							// rota
							carregarRota(form, fachada, objetoSetorComercial.getCodigo() + "", localidadeID);

							// setor comercial destino
							form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
							form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
							form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
							request.setAttribute("corSetorComercialDestino", "valor");
							request.setAttribute("nomeCampo", "rotaFinal");
						}
					} else {

						carregarRota(form, fachada, objetoSetorComercial.getCodigo() + "", localidadeID);

						// setor comercial destino
						form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
						form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
						form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
						request.setAttribute("corSetorComercialDestino", "valor");
						request.setAttribute("nomeCampo", "rotaFinal");

					}
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				form.setSetorComercialDestinoCD("");
				form.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				request.setAttribute("corSetorComercialDestino", "exception");
				request.setAttribute("nomeCampo", "setorComercialDestinoCD");
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
	public void carregarRota(FiltrarComandosAcaoCobrancaEventualActionForm form, Fachada fachada,
			String codigoSetorComercial, String idLocalidade) {
		Collection colecaoRota = fachada.obterColecaoRotaSetorComercial(codigoSetorComercial, idLocalidade);

		sessao.setAttribute("colecaoRota", colecaoRota);
		form.setRotaInicial("");
		form.setRotaFinal("");
	}

	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param request
	 */
	private void pesquisarCliente(String inscricaoTipo,
			FiltrarComandosAcaoCobrancaEventualActionForm form,
			Fachada fachada, HttpServletRequest request) {

		String idCliente = form.getIdCliente();

		// -------Parte que trata do código quando o usuário tecla enter
		// se o id do cliente for diferente de nulo
		if (idCliente != null && !idCliente.toString().trim().equalsIgnoreCase("")) {

			Cliente cliente = fachada.obterCliente(idCliente);

			if (cliente != null) {
				// O cliente foi encontrado
				form.setIdCliente(cliente.getId().toString());
				form.setNomeCliente(cliente.getNome());

				sessao.setAttribute("clienteObj", cliente);
			} else {
				request.setAttribute("codigoClienteNaoEncontrado", "true");
				form.setNomeCliente("Cliente Inexistente");
				request.setAttribute("nomeCampo", "idCliente");
			}
		}
	}
}

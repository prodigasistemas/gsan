package gcom.gui.cobranca;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Conbrança - Tipo de Comando Cronograma
 */
public class ExibirInserirComandoAcaoCobrancaEventualCriterioRotaAction extends GcomAction {

	private Collection colecaoPesquisa = null;

	private String localidadeID = null;

	private String setorComercialCD = null;

	private HttpSession sessao;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirComandoAcaoCobrancaEventualCriterioRota");

		// Mudar isso quando implementar a parte de segurança
		sessao = request.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		sessao.removeAttribute("colecaoCobrancaGrupo");
		sessao.removeAttribute("colecaoCobrancaAtividade");
		sessao.removeAttribute("colecaoCobrancaAcao");

		String limparForm = (String) request.getParameter("limparForm");

		String validarCriterio = (String) request.getParameter("validarCriterio");

		String validar = (String) request.getParameter("validar");

		InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form = (InserirComandoAcaoCobrancaEventualCriterioRotaActionForm) actionForm;

		if (sessao.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm") != null) {
			InserirComandoAcaoCobrancaEventualCriterioComandoActionForm formSessao = (InserirComandoAcaoCobrancaEventualCriterioComandoActionForm) sessao
					.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm");

			form.setRotaInicial(formSessao.getRotaInicial());
			form.setRotaFinal(formSessao.getRotaFinal());

			form.setLocalidadeOrigemID(formSessao.getLocalidadeOrigemID());
			form.setLocalidadeDestinoID(formSessao.getLocalidadeDestinoID());

			form.setSetorComercialDestinoCD(formSessao.getSetorComercialDestinoCD());
			form.setSetorComercialDestinoID(formSessao.getSetorComercialDestinoID());
			form.setNomeSetorComercialDestino(formSessao.getNomeSetorComercialDestino());

			form.setSetorComercialOrigemCD(formSessao.getSetorComercialOrigemCD());
			form.setSetorComercialOrigemID(formSessao.getSetorComercialOrigemID());
			form.setNomeSetorComercialOrigem(formSessao.getNomeSetorComercialOrigem());

			form.setCodigoClienteSuperior(formSessao.getCodigoClienteSuperior());
			form.setNomeClienteSuperior(formSessao.getNomeClienteSuperior());
			form.setNomeCliente(formSessao.getNomeCliente());
			form.setClienteRelacaoTipo(formSessao.getClienteRelacaoTipo());

			sessao.removeAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm");
		}

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {
			form.reset(actionMapping, request);
			form.setIndicadorGerarBoletimCadastro("2");
			form.setIndicadorImoveisDebito("1");
			
			if (sessao.getAttribute("colecaoRota") != null) {
				sessao.removeAttribute("colecaoRota");
			}
		}

		String limparRota = (String) request.getParameter("limparRota");

		// limpar as rotas
		if (limparRota != null && !limparRota.trim().equalsIgnoreCase("")) {
			form.setRotaInicial("");
			sessao.setAttribute("colecaoRota", null);
			form.setRotaInicial(null);
			form.setRotaFinal("");
			form.setRotaFinal(null);
		}

		String idCobrancaAcaoAtividadeComando = (String) request.getParameter("idCobrancaAcaoAtividadeComando");

		if (idCobrancaAcaoAtividadeComando != null && !idCobrancaAcaoAtividadeComando.equals("")) {

			FiltroCobrancaAcaoAtividadeComando filtro = new FiltroCobrancaAcaoAtividadeComando();
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ACAO);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_GRUPO);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ATIVIDADE);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.USUARIO);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.GERENCIAL_REGIONAL);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.LOCALIDADE_INICIAL);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.ROTA_INICIAL);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.ROTA_FINAL);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE_RELACAO_TIPO);
			filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeComando.ID, idCobrancaAcaoAtividadeComando));

			Collection colecaoCobrancaAcaoAtividadeComando = fachada.pesquisar(filtro, CobrancaAcaoAtividadeComando.class.getName());
			if (colecaoCobrancaAcaoAtividadeComando != null && !colecaoCobrancaAcaoAtividadeComando.isEmpty()) {
				CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) 
						colecaoCobrancaAcaoAtividadeComando.iterator().next();

				String[] acaoCobranca = { cobrancaAcaoAtividadeComando.getCobrancaAcao().getId().toString() };
				form.setCobrancaAcao(acaoCobranca);
				form.setCobrancaAtividade(cobrancaAcaoAtividadeComando.getCobrancaAtividade().getId().toString());

				// cobranca grupo
				if (cobrancaAcaoAtividadeComando.getCobrancaGrupo() != null) {
					form.setCobrancaGrupo(cobrancaAcaoAtividadeComando.getCobrancaGrupo().getId().toString());
				} else {
					form.setCobrancaGrupo("");
				}
				// gerencia regional
				if (cobrancaAcaoAtividadeComando.getGerenciaRegional() != null) {
					form.setGerenciaRegional(cobrancaAcaoAtividadeComando.getGerenciaRegional().getId().toString());
				} else {
					form.setGerenciaRegional("");
				}
				// unidade negocio
				if (cobrancaAcaoAtividadeComando.getUnidadeNegocio() != null) {
					form.setUnidadeNegocio(cobrancaAcaoAtividadeComando.getUnidadeNegocio().getId().toString());
				} else {
					form.setUnidadeNegocio("");
				}
				// localidade inicial
				if (cobrancaAcaoAtividadeComando.getLocalidadeInicial() != null) {
					form.setLocalidadeOrigemID(cobrancaAcaoAtividadeComando.getLocalidadeInicial().getId().toString());
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, 
							cobrancaAcaoAtividadeComando.getLocalidadeInicial().getId()));
					Collection colecaoLocalidadesIniciais = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

					Localidade localidadeInicial = (Localidade) colecaoLocalidadesIniciais.iterator().next();
					form.setNomeLocalidadeOrigem(localidadeInicial.getDescricao());
				} else {
					form.setLocalidadeOrigemID("");
					form.setNomeLocalidadeOrigem("");
				}
				// localidade final
				if (cobrancaAcaoAtividadeComando.getLocalidadeFinal() != null) {
					form.setLocalidadeDestinoID(cobrancaAcaoAtividadeComando.getLocalidadeFinal().getId().toString());
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
							cobrancaAcaoAtividadeComando.getLocalidadeFinal().getId()));
					Collection colecaoLocalidadesFinais = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

					Localidade localidadeFinal = (Localidade) colecaoLocalidadesFinais.iterator().next();
					form.setNomeLocalidadeDestino(localidadeFinal.getDescricao());
				} else {
					form.setLocalidadeDestinoID("");
					form.setNomeLocalidadeDestino("");
				}
				if (cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial() != null) {
					form.setSetorComercialOrigemCD(cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial()
							.toString());
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, cobrancaAcaoAtividadeComando
									.getCodigoSetorComercialInicial().toString()));
					Collection colecaoSetorComercialIniciais = fachada.pesquisar(filtroSetorComercial,
							SetorComercial.class.getName());

					SetorComercial setorComercialInicial = (SetorComercial) colecaoSetorComercialIniciais.iterator()
							.next();
					form.setNomeSetorComercialOrigem(setorComercialInicial.getDescricao());
				} else {
					form.setSetorComercialOrigemCD("");
					form.setNomeSetorComercialOrigem("");
				}
				if (cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal() != null) {
					form.setSetorComercialDestinoCD(cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal()
							.toString());
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, cobrancaAcaoAtividadeComando
									.getCodigoSetorComercialFinal().toString()));
					Collection colecaoSetorComercialFinais = fachada.pesquisar(filtroSetorComercial,
							SetorComercial.class.getName());

					SetorComercial setorComercialFinal = (SetorComercial) colecaoSetorComercialFinais.iterator().next();
					form.setNomeSetorComercialOrigem(setorComercialFinal.getDescricao());
				} else {
					form.setSetorComercialDestinoCD("");
					form.setNomeSetorComercialOrigem("");
				}
				boolean carregou = false;
				// rota inicial
				if (cobrancaAcaoAtividadeComando.getRotaInicial() != null) {
					// carregarRota(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,fachada,cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial().toString(),inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeOrigemID());
					form.setRotaInicial(cobrancaAcaoAtividadeComando.getRotaInicial().getCodigo().toString());
					carregou = true;
				} else {
					form.setRotaInicial("");
					sessao.setAttribute("colecaoRota", null);
					form.setRotaInicial(null);
				}
				// rota final
				if (cobrancaAcaoAtividadeComando.getRotaFinal() != null) {
					if (!carregou) {
						// carregarRota(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,fachada,cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal().toString(),inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeOrigemID());
					}

					form.setRotaFinal(cobrancaAcaoAtividadeComando.getRotaFinal().getCodigo().toString());
				} else {
					form.setRotaFinal("");
					sessao.setAttribute("colecaoRota", null);
					form.setRotaFinal(null);
				}

				if (cobrancaAcaoAtividadeComando.getCliente() != null) {
					form.setIdCliente(cobrancaAcaoAtividadeComando.getCliente().getId().toString());
					FiltroCliente filtroCliente = new FiltroCliente();
					filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
							cobrancaAcaoAtividadeComando.getCliente().getId().toString()));
					Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
					Cliente cliente = (Cliente) colecaoCliente.iterator().next();
					form.setNomeCliente(cliente.getNome());

				} else {
					form.setNomeCliente("");
					form.setIdCliente("");
				}

				if (cobrancaAcaoAtividadeComando.getClienteRelacaoTipo() != null) {
					form.setClienteRelacaoTipo(cobrancaAcaoAtividadeComando.getClienteRelacaoTipo().getId().toString());
				} else {
					form.setClienteRelacaoTipo("");
				}

				if (cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaInicial() != null) {
					form.setPeriodoInicialConta(Util.formatarAnoMesParaMesAno(Util.adicionarZerosEsquedaNumero(6,
							cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaInicial().toString())
							+ ""));
				} else {
					form.setPeriodoInicialConta("");
				}

				if (cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaFinal() != null) {
					form.setPeriodoFinalConta(Util.formatarAnoMesParaMesAno(Util.adicionarZerosEsquedaNumero(6,
							cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaFinal().toString())
							+ ""));
				} else {
					form.setPeriodoFinalConta("");
				}

				if (cobrancaAcaoAtividadeComando.getDataVencimentoContaInicial() != null) {
					form.setPeriodoVencimentoContaInicial(Util.formatarData(cobrancaAcaoAtividadeComando
							.getDataVencimentoContaInicial()));
				} else {
					form.setPeriodoVencimentoContaInicial("");
				}

				if (cobrancaAcaoAtividadeComando.getDataVencimentoContaFinal() != null) {
					form.setPeriodoVencimentoContaFinal(Util.formatarData(cobrancaAcaoAtividadeComando
							.getDataVencimentoContaFinal()));
				} else {
					form.setPeriodoVencimentoContaFinal("");
				}

				// id cobrança criterio
				if (cobrancaAcaoAtividadeComando != null && cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null) {
					sessao.setAttribute("idCobrancaCriterio", cobrancaAcaoAtividadeComando.getCobrancaCriterio()
							.getId().toString());
				}

				// indicador de criterio
				if (cobrancaAcaoAtividadeComando != null && cobrancaAcaoAtividadeComando.getIndicadorCriterio() != null) {
					if (cobrancaAcaoAtividadeComando.getIndicadorCriterio().shortValue() == 1) {
						form.setCobrancaAtividadeIndicadorExecucao("1");
						// manterComandoAcaoCobrancaDetalhesActionForm.setIndicador("Rota");
					} else if (cobrancaAcaoAtividadeComando.getIndicadorCriterio().shortValue() == 2) {
						form.setCobrancaAtividadeIndicadorExecucao("2");
						// manterComandoAcaoCobrancaDetalhesActionForm.setIndicador("Comando");
					}
				}

				if (cobrancaAcaoAtividadeComando.getDescricaoTitulo() != null) {
					form.setTitulo(cobrancaAcaoAtividadeComando.getDescricaoTitulo());
				}

				if (cobrancaAcaoAtividadeComando.getDescricaoSolicitacao() != null) {
					form.setDescricaoSolicitacao(cobrancaAcaoAtividadeComando.getDescricaoSolicitacao());
				}

				if (cobrancaAcaoAtividadeComando.getDataEncerramentoPrevista() != null) {
					if (cobrancaAcaoAtividadeComando.getComando() != null) {
						form.setPrazoExecucao(""
								+ Util.obterQuantidadeDiasEntreDuasDatas(cobrancaAcaoAtividadeComando.getComando(),
										cobrancaAcaoAtividadeComando.getDataEncerramentoPrevista()));
					}
				}

				if (cobrancaAcaoAtividadeComando.getQuantidadeMaximaDocumentos() != null) {
					form.setQuantidadeMaximaDocumentos(""
							+ cobrancaAcaoAtividadeComando.getQuantidadeMaximaDocumentos());
				}
				if (cobrancaAcaoAtividadeComando.getValorLimiteObrigatoria() != null) {
					form.setValorLimiteObrigatoria("" + cobrancaAcaoAtividadeComando.getValorLimiteObrigatoria());
				}
				if (cobrancaAcaoAtividadeComando.getIndicadorBoletim() != null) {
					form.setIndicadorGerarBoletimCadastro("" + cobrancaAcaoAtividadeComando.getIndicadorBoletim());
				}
				if (cobrancaAcaoAtividadeComando.getIndicadorDebito() != null) {
					form.setIndicadorImoveisDebito("" + cobrancaAcaoAtividadeComando.getIndicadorDebito());
				}

			}

		}// fim do comando ação cobrança pelo request

		// valdiar os criteorios de rota e comando para o usuário selecionar
		if (validarCriterio != null && !validarCriterio.equals("")) {

			if (validar != null && validar.equals("Atividade")) {// validar a
				// atividade
				// selecionada

				if (form.getCobrancaAtividade() != null && !form.getCobrancaAtividade().equals("")) {
					FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
					filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.ID, form
							.getCobrancaAtividade()));
					Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade,
							CobrancaAtividade.class.getName());
					if (colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()) {
						CobrancaAtividade cobrancaAtividade = (CobrancaAtividade) colecaoCobrancaAtividade.iterator()
								.next();
						String indicador = null;
						if (cobrancaAtividade.getIndicadorExecucao() != null) {
							indicador = cobrancaAtividade.getIndicadorExecucao().toString();
						} else {
							indicador = "";
						}
						form.setCobrancaAtividadeIndicadorExecucao(indicador);
						// httpServletRequest.setAttribute("cobrancaAtividadeIndicadorExecucao",cobrancaAtividade.getIndicadorExecucao().toString());
					} else {
						form.setCobrancaAtividadeIndicadorExecucao("");
						// httpServletRequest.setAttribute("cobrancaAtividadeIndicadorExecucao","");
					}
					form.setCobrancaAtividade(form.getCobrancaAtividade());
				}
			}
		}

		// CARREGAR AS COBRANÇAS GRUPO - INICIO
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		if (sessao.getAttribute("colecaoCobrancaGrupo") == null) {
			Collection colecaoCobrancaGrupo = (Collection) fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class
					.getName());
			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
			if (colecaoCobrancaGrupo != null && !colecaoCobrancaGrupo.isEmpty()) {
				// carregar grupo de cobrança
				sessao.setAttribute("colecaoCobrancaGrupo", colecaoCobrancaGrupo);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
						"Tabela Cobrança Grupo");
			}
		}
		// FIM COBRANÇA GRUPO

		// CLIENTE
		if (form.getIdCliente() != null && !form.equals("")) {
			pesquisarCliente(form.getInscricaoTipo(), form, fachada, request);
		}

		// CARREGAR AS COBRANÇAS ATIVIDADE - INICIO

		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		if (sessao.getAttribute("colecaoCobrancaAtividade") == null) {
			filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.DESCRICAO);
			filtroCobrancaAtividade.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCobrancaAtividade.ID,
					CobrancaAtividade.ENCERRAR));
			Collection colecaoCobrancaAtividade = (Collection) fachada.pesquisar(filtroCobrancaAtividade,
					CobrancaAtividade.class.getName());
			if (colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()) {
				// carregar atividade de cobrança
				sessao.setAttribute("colecaoCobrancaAtividade", colecaoCobrancaAtividade);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
						"Tabela Cobrança Atividade");
			}
		}

		// FIM COBRANÇA ATIVIDADE

		// CARREGAR AS COBRANÇAS ACAO - INICIO

		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();

		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_USO,
				ConstantesSistema.SIM));

		if (sessao.getAttribute("colecaoCobrancaAcao") == null) {
			filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
			Collection colecaoCobrancaAcao = (Collection) fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class
					.getName());
			if (colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()) {
				// carregar ação de cobrança
				sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
						"Tabela Cobrança Ação");
			}
		}

		// FIM COBRANÇA Ação

		if (sessao.getAttribute("colecaoFiscalizacaoSituacao") == null) {

			FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();

			filtroFiscalizacaoSituacao.setCampoOrderBy(FiltroFiscalizacaoSituacao.DESCRICAO);

			Collection colecaoFiscalizacaoSituacao = (Collection) fachada.pesquisar(filtroFiscalizacaoSituacao,
					FiscalizacaoSituacao.class.getName());
			if (colecaoFiscalizacaoSituacao != null && !colecaoFiscalizacaoSituacao.isEmpty()) {
				// carregar ação de cobrança
				sessao.setAttribute("colecaoFiscalizacaoSituacao", colecaoFiscalizacaoSituacao);
			}
		}

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO,
				ConstantesSistema.SIM));

		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
		Collection colecaoGerenciaRegional = (Collection) fachada.pesquisar(filtroGerenciaRegional,
				GerenciaRegional.class.getName());
		if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
			// carregar gerencia regional
			sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Tabela Gerência Regional");
		}

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO,
				ConstantesSistema.SIM));

		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO);
		Collection colecaoUnidadeNegocio = (Collection) fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class
				.getName());
		if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
			// carregar gerencia regional
			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Tabela Unidade Negócio");
		}

		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		filtroClienteRelacaoTipo.setCampoOrderBy(FiltroClienteRelacaoTipo.DESCRICAO);
		// carrega os cliente relação tipo
		Collection colecaoClienteRelacaoTipo = (Collection) fachada.pesquisar(filtroClienteRelacaoTipo,
				ClienteRelacaoTipo.class.getName());
		if (colecaoClienteRelacaoTipo != null && !colecaoClienteRelacaoTipo.isEmpty()) {
			// carregar cliente relação tipo
			sessao.setAttribute("colecaoClienteRelacaoTipo", colecaoClienteRelacaoTipo);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Tabela Cliente Relação Tipo");
		}

		String periodoFinalConta = fachada.pesquisarParametrosDoSistema().getAnoMesArrecadacao() + "";

		if ((form.getPeriodoFinalConta() != null && form.getPeriodoFinalConta().equals(""))
				| form.getPeriodoFinalConta() == null) {

			String ano = periodoFinalConta.substring(0, 4);
			String mes = periodoFinalConta.substring(4, 6);
			form.setPeriodoFinalConta(mes + "/" + ano);
		}

		if ((form.getPeriodoVencimentoContaFinal() != null && form.getPeriodoVencimentoContaFinal().equals(""))
				| form.getPeriodoVencimentoContaFinal() == null) {
			
			int numeroDiasVencimentoCobranca = fachada.pesquisarParametrosDoSistema().getNumeroDiasVencimentoCobranca().intValue();
			Date periodoVencimentoContaFinal = Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca);

			form.setPeriodoVencimentoContaFinal(new SimpleDateFormat("dd/MM/yyyy").format(periodoVencimentoContaFinal));
		}

		String objetoConsulta = (String) request.getParameter("objetoConsulta");

		String inscricaoTipo = (String) request.getParameter("inscricaoTipo");

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") && inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

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
			case 4:
				pesquisarLogradouro(form, fachada, request);
				break;
			default:
				break;
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
	 * @param httpServletRequest
	 */
	private void pesquisarLocalidade(
			String inscricaoTipo,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeOrigemID();

			// / gerenciaRegionalID = (String)
			// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
			// .getGerenciaRegional();

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeID));

			// / filtroLocalidade.adicionarParametro(new ParametroSimples(
			// // FiltroLocalidade.ID_GERENCIA, gerenciaRegionalID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeOrigemID("");
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setNomeLocalidadeOrigem("Localidade Inexistente");
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeOrigemID");

			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeOrigemID(String
						.valueOf(objetoLocalidade.getId()));
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeLocalidadeOrigem(objetoLocalidade
						.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");

				String localidadeDestinoID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getLocalidadeDestinoID();
				// verifica o valor das localidades, origem e final
				if (localidadeDestinoID != null) {

					if (localidadeDestinoID.equals("")) {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeDestinoID(String
								.valueOf(objetoLocalidade.getId()));
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
					} else {
						int localidadeDestino = new Integer(localidadeDestinoID).intValue();
						int localidadeOrigem = objetoLocalidade.getId().intValue();
						if (localidadeOrigem > localidadeDestino) {
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeDestinoID(String
									.valueOf(objetoLocalidade.getId()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
						}
					}
				}
				httpServletRequest.setAttribute("nomeCampo", "localidadeDestinoID");

			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeDestinoID();

			// / gerenciaRegionalID = (String)
			// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
			// .getGerenciaRegional();

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeID));

			// / filtroLocalidade.adicionarParametro(new ParametroSimples(
			// FiltroLocalidade.ID_GERENCIA, gerenciaRegionalID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setInscricaoTipo("destino");

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeDestinoID("");
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setNomeLocalidadeDestino("Localidade inexistente");
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeDestinoID");

			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);

				int localidadeDestino = objetoLocalidade.getId().intValue();

				String localidade = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getLocalidadeOrigemID();

				if (localidade != null && !localidade.equals("")) {

					int localidadeOrigem = new Integer(localidade).intValue();
					if (localidadeDestino < localidadeOrigem) {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeDestinoID("");
						// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						// .setNomeLocalidadeDestino("Loc. Final maior que a
						// Inicial");
						httpServletRequest.setAttribute("mensagem", "Localidae Final menor que o Inicial");
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeLocalidadeDestino("");
						httpServletRequest.setAttribute("corLocalidadeDestino", "valor");

						httpServletRequest.setAttribute("nomeCampo", "localidadeDestinoID");

					} else {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeDestinoID(String
								.valueOf(objetoLocalidade.getId()));
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
						httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
						httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");

					}
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeDestinoID(String
							.valueOf(objetoLocalidade.getId()));
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeLocalidadeDestino(objetoLocalidade
							.getDescricao());
					httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");

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
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,
						localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialOrigemCD("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialOrigemID("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialOrigem("Setor Comercial Inexistente");
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaInicial(null);
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaFinal(null);

					httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					// setorComercialID =
					// objetoSetorComercial.getId().toString();
					// setorComercialOrigem
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialOrigemCD(String
							.valueOf(objetoSetorComercial.getCodigo()));
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialOrigemID(String
							.valueOf(objetoSetorComercial.getId()));
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialOrigem", "valor");

					String setorComercialDestinoCD = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.getSetorComercialDestinoCD();

					// verifica o valor dos setores comerciais, origem e final
					if (setorComercialDestinoCD != null) {

						if (setorComercialDestinoCD.equals("")) {

							// setorComercialDestino
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());

							// carregarRota(
							// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
							// fachada,
							// objetoSetorComercial.getCodigo()+"",localidadeID);

						} else {

							int setorDestino = new Integer(setorComercialDestinoCD).intValue();
							int setorOrigem = objetoSetorComercial.getCodigo();
							if (setorOrigem > setorDestino) {

								// setorComercialDestino
								inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
										.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
								inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
										.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
								inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
										.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());

								// carregarRota(
								// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
								// fachada,
								// objetoSetorComercial.getCodigo()+"",localidadeID);
							}
						}
						httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");
					}
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialOrigemCD("");
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
			}
		} else {

			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,
						localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoCD("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoID("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialDestino("Setor Comercial Inexistente");
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaInicial(null);
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaFinal(null);
					httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);

					int setorDestino = objetoSetorComercial.getCodigo();

					String setor = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.getSetorComercialOrigemCD();

					if (setor != null && !setor.equals("")) {

						int setorOrigem = new Integer(setor).intValue();
						if (setorDestino < setorOrigem) {

							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoCD("");
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoID("");
							// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							// .setNomeSetorComercialDestino("Setor Final maior
							// que Inicial");
							httpServletRequest.setAttribute("mensagem", "Setor Comercial Final menor que o Inicial");
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeSetorComercialDestino("");
							httpServletRequest.setAttribute("corSetorComercialDestino", "valor");

							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaInicial(null);
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaFinal(null);
							httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");

						} else {
							// rota
							// carregarRota(
							// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
							// fachada,
							// objetoSetorComercial.getCodigo()+"",localidadeID
							// );

							// setor comercial destino
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
							httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
							httpServletRequest.setAttribute("nomeCampo", "rotaFinal");
						}
					} else {

						// carregarRota(
						// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
						// fachada,
						// objetoSetorComercial.getCodigo()+"",localidadeID);

						// setor comercial destino
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoCD(String
								.valueOf(objetoSetorComercial.getCodigo()));
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoID(String
								.valueOf(objetoSetorComercial.getId()));
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
						httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
						httpServletRequest.setAttribute("nomeCampo", "rotaFinal");

					}
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoCD("");
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");

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
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
			Fachada fachada, String codigoSetorComercial, String idLocalidade) {

		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, idLocalidade));
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercial));
		Collection colecaoRota = (Collection) fachada.pesquisar(filtroRota, Rota.class.getName());
		sessao.setAttribute("colecaoRota", colecaoRota);
		inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaInicial("rota");
		inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaFinal("rota");

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
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		String idCliente = null;
		if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
			idCliente = inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCodigoClienteSuperior();
		} else {
			idCliente = inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getIdCliente();
		}

		// -------Parte que trata do código quando o usuário tecla enter
		// se o id do cliente for diferente de nulo
		if (idCliente != null && !idCliente.toString().trim().equalsIgnoreCase("")) {

			FiltroCliente filtroCliente = new FiltroCliente();
			Collection clientes = null;
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, new Integer(idCliente)));
			clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			if (clientes != null && !clientes.isEmpty()) {
				// O cliente foi encontrado

				if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setCodigoClienteSuperior(((Cliente) ((List) clientes).get(0)).getId().toString());
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeClienteSuperior(((Cliente) ((List) clientes).get(0)).getNome());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setIdCliente(((Cliente) ((List) clientes)
							.get(0)).getId().toString());
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeCliente(((Cliente) ((List) clientes).get(0)).getNome());
				}

				Cliente cliente = new Cliente();

				cliente = (Cliente) clientes.iterator().next();
				sessao.setAttribute("clienteObj", cliente);
			} else {
				if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
					httpServletRequest.setAttribute("codigoClienteSuperiorNaoEncontrado", "true");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeClienteSuperior("");
					httpServletRequest.setAttribute("nomeCampo", "codigoClienteSuperior");
				} else {
					httpServletRequest.setAttribute("codigoClienteNaoEncontrado", "true");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeCliente("");
					httpServletRequest.setAttribute("nomeCampo", "idCliente");
				}

			}

		}

	}

	private void pesquisarLogradouro(InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form, Fachada fachada,
			HttpServletRequest httpServletRequest) {

		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0011] Valida Logradouro

		// Filtra Logradouro
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, form.getLogradouroId()));

		// Recupera Logradouro
		Collection<Logradouro> colecaoLogradouro = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());

		if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {
			sessao.setAttribute("logradouroEncontrada", "true");
			Logradouro logradouro = colecaoLogradouro.iterator().next();
			form.setLogradouroDescricao(logradouro.getNome());
			form.setLogradouroId(logradouro.getId().toString());
		} else {
			sessao.removeAttribute("logradouroEncontrada");
			form.setLogradouroId("");
			form.setLogradouroDescricao("Logradouro inexistente");
		}

	}

}

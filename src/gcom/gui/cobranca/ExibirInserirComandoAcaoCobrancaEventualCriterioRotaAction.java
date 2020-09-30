package gcom.gui.cobranca;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
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
import gcom.cobranca.CobrancaAcaoAtividadeComandoSubcategoria;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComandoSubcategoria;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

/**
 * [UC0243] Inserir Comando de Ação de Conbrança - Tipo de Comando Cronograma
 */
public class ExibirInserirComandoAcaoCobrancaEventualCriterioRotaAction extends GcomAction {

	private static final int LOCALIDADE = 1;
	private static final int SETOR_COMERCIAL = 2;
	private static final int CLIENTE = 3;
	private static final int LOGRADOURO = 4;

	private String localidadeID = null;
	private String setorComercialCD = null;

	private HttpSession sessao;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = actionMapping.findForward("exibirInserirComandoAcaoCobrancaEventualCriterioRota");

		sessao = request.getSession(false);
		sessao.removeAttribute("colecaoCobrancaGrupo");
		sessao.removeAttribute("colecaoCobrancaAtividade");
		sessao.removeAttribute("colecaoCobrancaAcao");
		sessao.removeAttribute("colecaoSubcategoria");
		
		String limparForm = (String) request.getParameter("limparForm");
		String validarCriterio = (String) request.getParameter("validarCriterio");
		String validar = (String) request.getParameter("validar");

		InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form = (InserirComandoAcaoCobrancaEventualCriterioRotaActionForm) actionForm;

		verificarForm(form);
		limpar(actionMapping, request, limparForm, form);

		String idComando = (String) request.getParameter("idCobrancaAcaoAtividadeComando");
		if (idComando != null && !idComando.equals("")) {
			recuperarDadosComando(form, idComando);
		}

		setarCobrancaAtividadeIndicadorExecucao(validarCriterio, validar, form);

		pesquisarCliente(form.getInscricaoTipo(), form, request);
		
		carregarCobrancaGrupo();
		carregarCobrancaAtividade();
		carregarCobrancaAcao();
		carregarSubcategorias();
		carregarFiscalizacaoSituacao();
		carregarGerenciaRegional();
		carregarUnidadeNegocio();
		carregarClienteRelacaoTipo();

		setPeriodoFinalConta(form);
		setPeriodoVencimentoContaFinal(form);

		String objetoConsulta = (String) request.getParameter("objetoConsulta");
		String inscricaoTipo = (String) request.getParameter("inscricaoTipo");

		realizarConsultas(request, form, objetoConsulta, inscricaoTipo);

		return retorno;
	}

	private void realizarConsultas(HttpServletRequest request, InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form, String objetoConsulta,
			String inscricaoTipo) {
		if (objetoConsulta != null 
				&& !objetoConsulta.trim().equalsIgnoreCase("") 
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			
			case LOCALIDADE:
				pesquisarLocalidade(inscricaoTipo, form, request);
				break;
				
			case SETOR_COMERCIAL:
				pesquisarLocalidade(inscricaoTipo, form, request);
				pesquisarSetorComercial(inscricaoTipo, form, request);

				break;
			case CLIENTE:
				pesquisarCliente(inscricaoTipo, form, request);
				break;
			case LOGRADOURO:
				pesquisarLogradouro(form, request);
				break;
			default:
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarCobrancaAcao() {
		if (sessao.getAttribute("colecaoCobrancaAcao") == null) {
			FiltroCobrancaAcao filtro = new FiltroCobrancaAcao();
			filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_USO, ConstantesSistema.SIM));
			filtro.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
			
			Collection<CobrancaAcao> colecao = getFachada().pesquisar(filtro, CobrancaAcao.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				sessao.setAttribute("colecaoCobrancaAcao", colecao);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null, "Tabela Cobrança Ação");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarCobrancaAtividade() {
		if (sessao.getAttribute("colecaoCobrancaAtividade") == null) {
			Filtro filtro = new FiltroCobrancaAtividade();
			filtro.setCampoOrderBy(FiltroCobrancaAtividade.DESCRICAO);
			filtro.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCobrancaAtividade.ID, CobrancaAtividade.ENCERRAR));

			Collection<CobrancaAtividade> colecao = getFachada().pesquisar(filtro, CobrancaAtividade.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				sessao.setAttribute("colecaoCobrancaAtividade", colecao);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null, "Tabela Cobrança Atividade");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarCobrancaGrupo() {
		if (sessao.getAttribute("colecaoCobrancaGrupo") == null) {
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);

			Collection<CobrancaGrupo> colecao = getFachada().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				sessao.setAttribute("colecaoCobrancaGrupo", colecao);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null, "Tabela Cobrança Grupo");
			}
		}
	}

	private void setarCobrancaAtividadeIndicadorExecucao(
			String validarCriterio,
			String validar,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form) {

		if (validarCriterio != null && !validarCriterio.equals("")) {

			if (validar != null && validar.equals("Atividade")) {

				if (form.getCobrancaAtividade() != null && !form.getCobrancaAtividade().equals("")) {
					CobrancaAtividade cobrancaAtividade = pesquisarCobrancaAtividade(form.getCobrancaAtividade());

					if (cobrancaAtividade != null && cobrancaAtividade.getIndicadorExecucao() != null) {
						form.setCobrancaAtividadeIndicadorExecucao(cobrancaAtividade.getIndicadorExecucao().toString());
					} else {
						form.setCobrancaAtividadeIndicadorExecucao("");
					}

					form.setCobrancaAtividade(form.getCobrancaAtividade());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private CobrancaAtividade pesquisarCobrancaAtividade(String cobrancaAtividade) {
		Filtro filtro = new FiltroCobrancaAtividade();
		filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.ID, cobrancaAtividade));

		Collection<CobrancaAtividade> colecao = getFachada().pesquisar(filtro, CobrancaAtividade.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			return (CobrancaAtividade) colecao.iterator().next();
		} else {
			return null;
		}
	}

	private void limpar(ActionMapping actionMapping, HttpServletRequest request, String limparForm,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form) {
		limparForm(actionMapping, request, limparForm, form);
		limparRotas(request, form);
	}

	private void limparRotas(HttpServletRequest request, InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form) {
		String limparRota = (String) request.getParameter("limparRota");

		if (limparRota != null && !limparRota.trim().equalsIgnoreCase("")) {
			form.setRotaInicial("");
			sessao.setAttribute("colecaoRota", null);
			form.setRotaInicial(null);
			form.setRotaFinal("");
			form.setRotaFinal(null);
		}
	}

	private void limparForm(ActionMapping actionMapping, HttpServletRequest request, String limparForm,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form) {
		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {
			form.reset(actionMapping, request);
			form.setIndicadorGerarBoletimCadastro("2");
			form.setIndicadorImoveisDebito("1");
			
			if (sessao.getAttribute("colecaoRota") != null) {
				sessao.removeAttribute("colecaoRota");
			}
		}
	}

	private void verificarForm(InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form) {
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
	}

	private void setPeriodoVencimentoContaFinal(InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form) {
		
		if ((form.getPeriodoVencimentoContaFinal() != null && form.getPeriodoVencimentoContaFinal().equals(""))
				| form.getPeriodoVencimentoContaFinal() == null) {
			
			int numeroDiasVencimentoCobranca = getFachada().pesquisarParametrosDoSistema().getNumeroDiasVencimentoCobranca().intValue();
			Date periodoVencimentoContaFinal = Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca);

			form.setPeriodoVencimentoContaFinal(new SimpleDateFormat("dd/MM/yyyy").format(periodoVencimentoContaFinal));
		}
	}

	private void setPeriodoFinalConta(InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form) {
		String periodoFinalConta = getFachada().pesquisarParametrosDoSistema().getAnoMesArrecadacao().toString();

		if ((form.getPeriodoFinalConta() != null && form.getPeriodoFinalConta().equals(""))
				| form.getPeriodoFinalConta() == null) {

			String ano = periodoFinalConta.substring(0, 4);
			String mes = periodoFinalConta.substring(4, 6);
			form.setPeriodoFinalConta(mes + "/" + ano);
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarClienteRelacaoTipo() {
		Filtro filtro = new FiltroClienteRelacaoTipo();
		filtro.setCampoOrderBy(FiltroClienteRelacaoTipo.DESCRICAO);

		Collection<ClienteRelacaoTipo> colecao = getFachada().pesquisar(filtro, ClienteRelacaoTipo.class.getName());
		if (colecao != null && !colecao.isEmpty()) {
			sessao.setAttribute("colecaoClienteRelacaoTipo", colecao);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Tabela Cliente Relação Tipo");
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarUnidadeNegocio() {
		Filtro filtro = new FiltroUnidadeNegocio();
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.SIM));
		filtro.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO);
		
		Collection<UnidadeNegocio> colecao = getFachada().pesquisar(filtro, UnidadeNegocio.class.getName());
		if (colecao != null && !colecao.isEmpty()) {
			sessao.setAttribute("colecaoUnidadeNegocio", colecao);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Tabela Unidade Negócio");
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarGerenciaRegional() {
		Filtro filtro = new FiltroGerenciaRegional();
		filtro.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.SIM));
		filtro.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
		
		Collection<GerenciaRegional> colecao = getFachada().pesquisar(filtro, GerenciaRegional.class.getName());
		if (colecao != null && !colecao.isEmpty()) {
			sessao.setAttribute("colecaoGerenciaRegional", colecao);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Tabela Gerência Regional");
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarFiscalizacaoSituacao() {
		if (sessao.getAttribute("colecaoFiscalizacaoSituacao") == null) {
			Filtro filtro = new FiltroFiscalizacaoSituacao();
			filtro.setCampoOrderBy(FiltroFiscalizacaoSituacao.DESCRICAO);

			Collection<FiscalizacaoSituacao> colecao = getFachada().pesquisar(filtro, FiscalizacaoSituacao.class.getName());
			if (colecao != null && !colecao.isEmpty()) {
				sessao.setAttribute("colecaoFiscalizacaoSituacao", colecao);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarSubcategorias() {
		if (sessao.getAttribute("colecaoSubcategoria") == null) {
			Filtro filtro = new FiltroSubCategoria();
			filtro.setCampoOrderBy(FiltroSubCategoria.ID);

			Collection<Subcategoria> colecao = getFachada().pesquisar(filtro, Subcategoria.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				sessao.setAttribute("colecaoSubcategoria", colecao);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null, "Tabela SubCategoria");
			}
		}
	}

	private void recuperarDadosComando(
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form,
			String idComando) {

		CobrancaAcaoAtividadeComando comando = pesquisarComando(idComando);

		if (comando != null) {
			String[] acaoCobranca = { comando.getCobrancaAcao().getId().toString() };
			form.setCobrancaAcao(acaoCobranca);
			form.setCobrancaAtividade(comando.getCobrancaAtividade().getId().toString());

			// cobranca grupo
			if (comando.getCobrancaGrupo() != null) {
				form.setCobrancaGrupo(comando.getCobrancaGrupo().getId().toString());
			} else {
				form.setCobrancaGrupo("");
			}
			// gerencia regional
			if (comando.getGerenciaRegional() != null) {
				form.setGerenciaRegional(comando.getGerenciaRegional().getId().toString());
			} else {
				form.setGerenciaRegional("");
			}
			// unidade negocio
			if (comando.getUnidadeNegocio() != null) {
				form.setUnidadeNegocio(comando.getUnidadeNegocio().getId().toString());
			} else {
				form.setUnidadeNegocio("");
			}

			// localidade inicial
			if (comando.getLocalidadeInicial() != null) {
				String idLocalidadeInicial = comando.getLocalidadeInicial().getId().toString();
				String descricaoLocalidadeInicial = pesquisarDescricaoLocalidadeComando(idLocalidadeInicial);
				form.setLocalidadeOrigemID(idLocalidadeInicial);
				form.setNomeLocalidadeOrigem(descricaoLocalidadeInicial);
			} else {
				form.setLocalidadeOrigemID("");
				form.setNomeLocalidadeOrigem("");
			}

			// localidade final
			if (comando.getLocalidadeFinal() != null) {
				String idLocalidadeFinal = comando.getLocalidadeFinal().getId().toString();
				String descricaoLocalidadeFinal = pesquisarDescricaoLocalidadeComando(idLocalidadeFinal);
				form.setLocalidadeDestinoID(idLocalidadeFinal);
				form.setNomeLocalidadeDestino(descricaoLocalidadeFinal);
			} else {
				form.setLocalidadeDestinoID("");
				form.setNomeLocalidadeDestino("");
			}

			if (comando.getCodigoSetorComercialInicial() != null) {
				String codigoSetorComercial = comando.getCodigoSetorComercialInicial().toString();
				String descricaoSetorComercial = pesquisarDescricaoSetorComercialComando(codigoSetorComercial);
				form.setSetorComercialOrigemCD(codigoSetorComercial);
				form.setNomeSetorComercialOrigem(descricaoSetorComercial);
			} else {
				form.setSetorComercialOrigemCD("");
				form.setNomeSetorComercialOrigem("");
			}

			if (comando.getCodigoSetorComercialFinal() != null) {
				String codigoSetorComercial = comando.getCodigoSetorComercialFinal().toString();
				String descricaoSetorComercial = pesquisarDescricaoSetorComercialComando(codigoSetorComercial);
				form.setSetorComercialDestinoCD(codigoSetorComercial);
				form.setNomeSetorComercialDestino(descricaoSetorComercial);
			} else {
				form.setSetorComercialDestinoCD("");
				form.setNomeSetorComercialDestino("");
			}

			if (comando.getRotaInicial() != null) {
				form.setRotaInicial(comando.getRotaInicial().getCodigo().toString());
			} else {
				form.setRotaInicial("");
				sessao.setAttribute("colecaoRota", null);
				form.setRotaInicial(null);
			}

			if (comando.getRotaFinal() != null) {
				form.setRotaFinal(comando.getRotaFinal().getCodigo().toString());
			} else {
				form.setRotaFinal("");
				sessao.setAttribute("colecaoRota", null);
				form.setRotaFinal(null);
			}

			if (comando.getCliente() != null) {
				Cliente cliente = pesquisarClienteComando(comando);
				form.setIdCliente(comando.getCliente().getId().toString());
				form.setNomeCliente(cliente.getNome());
			} else {
				form.setNomeCliente("");
				form.setIdCliente("");
			}

			form.setSubcategoria(carregaSubcategoriaDoComando(form, comando));

			if (comando.getClienteRelacaoTipo() != null) {
				form.setClienteRelacaoTipo(comando.getClienteRelacaoTipo().getId().toString());
			} else {
				form.setClienteRelacaoTipo("");
			}

			if (comando.getAnoMesReferenciaContaInicial() != null) {
				form.setPeriodoInicialConta(String.valueOf(
						Util.formatarAnoMesParaMesAno(Util.adicionarZerosEsquedaNumero(6, comando.getAnoMesReferenciaContaInicial().toString()))));
			} else {
				form.setPeriodoInicialConta("");
			}

			if (comando.getAnoMesReferenciaContaFinal() != null) {
				form.setPeriodoFinalConta(String.valueOf(
						Util.formatarAnoMesParaMesAno(Util.adicionarZerosEsquedaNumero(6, comando.getAnoMesReferenciaContaFinal().toString()))));
			} else {
				form.setPeriodoFinalConta("");
			}

			if (comando.getDataVencimentoContaInicial() != null) {
				form.setPeriodoVencimentoContaInicial(Util.formatarData(comando.getDataVencimentoContaInicial()));
			} else {
				form.setPeriodoVencimentoContaInicial("");
			}

			if (comando.getDataVencimentoContaFinal() != null) {
				form.setPeriodoVencimentoContaFinal(Util.formatarData(comando.getDataVencimentoContaFinal()));
			} else {
				form.setPeriodoVencimentoContaFinal("");
			}

			if (comando != null && comando.getCobrancaCriterio() != null) {
				sessao.setAttribute("idCobrancaCriterio", comando.getCobrancaCriterio().getId().toString());
			}

			if (comando != null && comando.getIndicadorCriterio() != null) {
				if (comando.getIndicadorCriterio().shortValue() == 1) {
					form.setCobrancaAtividadeIndicadorExecucao("1");
				} else if (comando.getIndicadorCriterio().shortValue() == 2) {
					form.setCobrancaAtividadeIndicadorExecucao("2");
				}
			}

			if (comando.getDescricaoTitulo() != null) {
				form.setTitulo(comando.getDescricaoTitulo());
			}

			if (comando.getDescricaoSolicitacao() != null) {
				form.setDescricaoSolicitacao(comando.getDescricaoSolicitacao());
			}

			if (comando.getDataEncerramentoPrevista() != null) {
				if (comando.getComando() != null) {
					form.setPrazoExecucao(String.valueOf(Util.obterQuantidadeDiasEntreDuasDatas(comando.getComando(), comando.getDataEncerramentoPrevista())));
				}
			}

			if (comando.getQuantidadeMaximaDocumentos() != null) {
				form.setQuantidadeMaximaDocumentos(comando.getQuantidadeMaximaDocumentos().toString());
			}

			if (comando.getValorLimiteObrigatoria() != null) {
				form.setValorLimiteObrigatoria(comando.getValorLimiteObrigatoria().toString());
			}

			if (comando.getIndicadorBoletim() != null) {
				form.setIndicadorGerarBoletimCadastro(comando.getIndicadorBoletim().toString());
			}

			if (comando.getIndicadorDebito() != null) {
				form.setIndicadorImoveisDebito(comando.getIndicadorDebito().toString());
			}
		}
	}

	@SuppressWarnings("unchecked")
	private String pesquisarDescricaoLocalidadeComando(String idLocalidade) {
		Filtro filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));

		Collection<Localidade> colecao = getFachada().pesquisar(filtro, SetorComercial.class.getName());

		Localidade localidade = (Localidade) colecao.iterator().next();

		return localidade.getDescricao();
	}
	
	@SuppressWarnings("unchecked")
	private String pesquisarDescricaoSetorComercialComando(String codigoSetorComercial) {
		Filtro filtro = new FiltroSetorComercial();
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
		
		Collection<SetorComercial> colecao = getFachada().pesquisar(filtro, SetorComercial.class.getName());

		SetorComercial setorComercial = (SetorComercial) colecao.iterator().next();
		
		return setorComercial.getDescricao();
	}

	@SuppressWarnings("unchecked")
	private Cliente pesquisarClienteComando(CobrancaAcaoAtividadeComando comando) {
		Filtro filtro = new FiltroCliente();
		filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, comando.getCliente().getId().toString()));
		
		Collection<Cliente> colecao = getFachada().pesquisar(filtro, Cliente.class.getName());
		
		return (Cliente) colecao.iterator().next();
	}

	@SuppressWarnings("unchecked")
	private CobrancaAcaoAtividadeComando pesquisarComando(String idComando) {
		Filtro filtro = new FiltroCobrancaAcaoAtividadeComando();
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
		filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeComando.ID, idComando));

		Collection<CobrancaAcaoAtividadeComando> colecao = getFachada().pesquisar(filtro, CobrancaAcaoAtividadeComando.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			return colecao.iterator().next();
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private String[] carregaSubcategoriaDoComando(
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form,
			CobrancaAcaoAtividadeComando comando) {
		
			Filtro filtro = new FiltroCobrancaAcaoAtividadeComandoSubcategoria();
			filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeComandoSubcategoria.COBRANCA_ACAO_ATIVIDADE_COMANDO_ID, comando.getId().toString()));
			
			Collection<CobrancaAcaoAtividadeComandoSubcategoria> colecao = getFachada().pesquisar(filtro, CobrancaAcaoAtividadeComandoSubcategoria.class.getName());
			
			String[] ids = new String[colecao.size()];
			int i = 0;
			for (CobrancaAcaoAtividadeComandoSubcategoria subcategoriaComando : colecao) {
				ids[i++] = subcategoriaComando.getSubcategoria().getId().toString();
			}
			
		return ids;

	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarLocalidade(
			String inscricaoTipo,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form,
			HttpServletRequest request) {

		Filtro filtro = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			form.setInscricaoTipo("origem");
			localidadeID = (String) form.getLocalidadeOrigemID();

			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeID));
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<Localidade> colecaoLocalidade = getFachada().pesquisar(filtro, Localidade.class.getName());

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
				form.setLocalidadeOrigemID("");
				form.setNomeLocalidadeOrigem("Localidade Inexistente");
				request.setAttribute("corLocalidadeOrigem", "exception");
				request.setAttribute("nomeCampo", "localidadeOrigemID");
			
			} else {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				form.setLocalidadeOrigemID(String.valueOf(localidade.getId()));
				form.setNomeLocalidadeOrigem(localidade.getDescricao());
				request.setAttribute("corLocalidadeOrigem", "valor");

				String localidadeDestinoID = (String) form.getLocalidadeDestinoID();

				if (localidadeDestinoID != null) {

					if (localidadeDestinoID.equals("")) {
						form.setLocalidadeDestinoID(String.valueOf(localidade.getId()));
						form.setNomeLocalidadeDestino(localidade.getDescricao());
					} else {
						int localidadeDestino = new Integer(localidadeDestinoID).intValue();
						int localidadeOrigem = localidade.getId().intValue();
						if (localidadeOrigem > localidadeDestino) {
							form.setLocalidadeDestinoID(String.valueOf(localidade.getId()));
							form.setNomeLocalidadeDestino(localidade.getDescricao());
						}
					}
				}
				
				request.setAttribute("nomeCampo", "localidadeDestinoID");
			}
		} else {
			localidadeID = (String) form.getLocalidadeDestinoID();

			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeID));
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<Localidade> colecaoLocalidade = getFachada().pesquisar(filtro, Localidade.class.getName());

			form.setInscricaoTipo("destino");

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
				form.setLocalidadeDestinoID("");
				form.setNomeLocalidadeDestino("Localidade inexistente");
				request.setAttribute("corLocalidadeDestino", "exception");
				request.setAttribute("nomeCampo", "localidadeDestinoID");

			} else {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

				int localidadeDestino = localidade.getId().intValue();

				String localidadeOrigemId = (String) form.getLocalidadeOrigemID();

				if (localidadeOrigemId != null && !localidadeOrigemId.equals("")) {

					int localidadeOrigem = new Integer(localidadeOrigemId).intValue();
					if (localidadeDestino < localidadeOrigem) {
						form.setLocalidadeDestinoID("");
						form.setNomeLocalidadeDestino("");
						request.setAttribute("mensagem", "Localidae Final menor que o Inicial");
						request.setAttribute("corLocalidadeDestino", "valor");
						request.setAttribute("nomeCampo", "localidadeDestinoID");

					} else {
						form.setLocalidadeDestinoID(String.valueOf(localidade.getId()));
						form.setNomeLocalidadeDestino(localidade.getDescricao());
						request.setAttribute("corLocalidadeDestino", "valor");
						request.setAttribute("nomeCampo", "setorComercialOrigemCD");
					}
				} else {
					form.setLocalidadeDestinoID(String.valueOf(localidade.getId()));
					form.setNomeLocalidadeDestino(localidade.getDescricao());
					request.setAttribute("corLocalidadeDestino", "valor");
					request.setAttribute("nomeCampo", "setorComercialOrigemCD");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form,
			HttpServletRequest request) {

		Filtro filtro = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			form.setInscricaoTipo("origem");
			localidadeID = (String) form.getLocalidadeOrigemID();

			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form.getSetorComercialOrigemCD();

				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection<SetorComercial> colecaoSetorComercial = getFachada().pesquisar(filtro, SetorComercial.class.getName());

				if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
					form.setSetorComercialOrigemCD("");
					form.setSetorComercialOrigemID("");
					form.setNomeSetorComercialOrigem("Setor Comercial Inexistente");
					form.setRotaInicial(null);
					form.setRotaFinal(null);
					request.setAttribute("corSetorComercialOrigem", "exception");
					request.setAttribute("nomeCampo", "setorComercialOrigemCD");

				} else {
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
					form.setSetorComercialOrigemCD(String.valueOf(setorComercial.getCodigo()));
					form.setSetorComercialOrigemID(String.valueOf(setorComercial.getId()));
					form.setNomeSetorComercialOrigem(setorComercial.getDescricao());
					request.setAttribute("corSetorComercialOrigem", "valor");

					String setorComercialDestinoCD = (String) form.getSetorComercialDestinoCD();

					if (setorComercialDestinoCD != null) {
						if (setorComercialDestinoCD.equals("")) {

							form.setSetorComercialDestinoCD(String.valueOf(setorComercial.getCodigo()));
							form.setSetorComercialDestinoID(String.valueOf(setorComercial.getId()));
							form.setNomeSetorComercialDestino(setorComercial.getDescricao());

						} else {

							int setorDestino = new Integer(setorComercialDestinoCD).intValue();
							int setorOrigem = setorComercial.getCodigo();
							if (setorOrigem > setorDestino) {

								form.setSetorComercialDestinoCD(String.valueOf(setorComercial.getCodigo()));
								form.setSetorComercialDestinoID(String.valueOf(setorComercial.getId()));
								form.setNomeSetorComercialDestino(setorComercial.getDescricao());
							}
						}
						request.setAttribute("nomeCampo", "setorComercialDestinoCD");
					}
				}
			} else {
				form.setSetorComercialOrigemCD("");
				form.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				request.setAttribute("corSetorComercialOrigem", "exception");
				request.setAttribute("nomeCampo", "setorComercialOrigemCD");
			}
		} else {

			form.setInscricaoTipo("destino");

			localidadeID = (String) form.getLocalidadeDestinoID();

			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form.getSetorComercialDestinoCD();

				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection<SetorComercial> colecaoSetorComercial = getFachada().pesquisar(filtro, SetorComercial.class.getName());

				if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
					form.setSetorComercialDestinoCD("");
					form.setSetorComercialDestinoID("");
					form.setNomeSetorComercialDestino("Setor Comercial Inexistente");
					request.setAttribute("corSetorComercialDestino", "exception");
					form.setRotaInicial(null);
					form.setRotaFinal(null);
					request.setAttribute("nomeCampo", "setorComercialDestinoCD");
				} else {
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
					int setorDestino = setorComercial.getCodigo();
					String setor = (String) form.getSetorComercialOrigemCD();

					if (setor != null && !setor.equals("")) {

						int setorOrigem = new Integer(setor).intValue();
						if (setorDestino < setorOrigem) {
							form.setSetorComercialDestinoCD("");
							form.setSetorComercialDestinoID("");
							form.setNomeSetorComercialDestino("");
							form.setRotaInicial(null);
							form.setRotaFinal(null);
							request.setAttribute("mensagem", "Setor Comercial Final menor que o Inicial");
							request.setAttribute("corSetorComercialDestino", "valor");
							request.setAttribute("nomeCampo", "setorComercialDestinoCD");

						} else {
							form.setSetorComercialDestinoCD(String.valueOf(setorComercial.getCodigo()));
							form.setSetorComercialDestinoID(String.valueOf(setorComercial.getId()));
							form.setNomeSetorComercialDestino(setorComercial.getDescricao());
							request.setAttribute("corSetorComercialDestino", "valor");
							request.setAttribute("nomeCampo", "rotaFinal");
						}
					} else {

						form.setSetorComercialDestinoCD(String.valueOf(setorComercial.getCodigo()));
						form.setSetorComercialDestinoID(String.valueOf(setorComercial.getId()));
						form.setNomeSetorComercialDestino(setorComercial.getDescricao());
						request.setAttribute("corSetorComercialDestino", "valor");
						request.setAttribute("nomeCampo", "rotaFinal");
					}
				}
			} else {
				form.setSetorComercialDestinoCD("");
				form.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				request.setAttribute("corSetorComercialDestino", "exception");
				request.setAttribute("nomeCampo", "setorComercialDestinoCD");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarCliente(
			String inscricaoTipo,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form,
			HttpServletRequest request) {

		if (form.getIdCliente() != null && !form.equals("")) {
			String idCliente = null;

			if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
				idCliente = form.getCodigoClienteSuperior();
			} else {
				idCliente = form.getIdCliente();
			}

			if (idCliente != null && !idCliente.toString().trim().equalsIgnoreCase("")) {

				Filtro filtro = new FiltroCliente();
				filtro.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, new Integer(idCliente)));

				Collection<Cliente> clientes = getFachada().pesquisar(filtro, Cliente.class.getName());

				if (clientes != null && !clientes.isEmpty()) {

					Cliente cliente = clientes.iterator().next();

					if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
						form.setCodigoClienteSuperior(cliente.getId().toString());
						form.setNomeClienteSuperior(cliente.getNome());
					} else {
						form.setIdCliente(cliente.getId().toString());
						form.setNomeCliente(cliente.getNome());
					}

					sessao.setAttribute("clienteObj", cliente);
				} else {
					if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
						request.setAttribute("codigoClienteSuperiorNaoEncontrado", "true");
						form.setNomeClienteSuperior("");
						request.setAttribute("nomeCampo", "codigoClienteSuperior");
					} else {
						request.setAttribute("codigoClienteNaoEncontrado", "true");
						form.setNomeCliente("");
						request.setAttribute("nomeCampo", "idCliente");
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarLogradouro(
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form,
			HttpServletRequest request) {

		Filtro filtro = new FiltroLogradouro();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, form.getLogradouroId()));

		Collection<Logradouro> colecao = getFachada().pesquisar(filtro, Logradouro.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			sessao.setAttribute("logradouroEncontrada", "true");
			Logradouro logradouro = colecao.iterator().next();
			form.setLogradouroDescricao(logradouro.getNome());
			form.setLogradouroId(logradouro.getId().toString());
		} else {
			sessao.removeAttribute("logradouroEncontrada");
			form.setLogradouroId("");
			form.setLogradouroDescricao("Logradouro inexistente");
		}
	}
}

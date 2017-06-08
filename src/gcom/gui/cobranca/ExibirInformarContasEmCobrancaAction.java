package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresaContratoCobranca;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarContasEmCobrancaAction extends GcomAction {

	private boolean algumParametroInformado = false;
	private InformarContasEmCobrancaActionForm form;
	private HttpSession sessao;
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirInformarContasEmCobranca");

		this.form = (InformarContasEmCobrancaActionForm) actionForm;
		this.sessao = getSessao(request);

		inicializarCampos(request);
		pesquisarCamposEnter(request);
		limparTotalizacao(request);

		if (request.getParameter("pesquisarQtdContas") != null && form.getIdEmpresa() != null && !form.getIdEmpresa().equals("")) {
			ComandoEmpresaCobrancaContaHelper helper = montarHelper();

			if (helper != null) {

				boolean agruparPorImovel = true;
				EmpresaContratoCobranca contrato = pesquisarContrato();

				if (contrato.getPercentualContratoCobranca() != null && contrato.getPercentualContratoCobranca().compareTo(BigDecimal.ZERO) != 0) {
					agruparPorImovel = false;
				}

				Collection<Object[]> colecaoDados = (Collection<Object[]>) getFachada().pesquisarQuantidadeContas(helper, agruparPorImovel);

				if (agruparPorImovel) {
					selecionarContasAgrupandoPorImovel(request, helper, colecaoDados, contrato);
				} else {
					selecionarContas(request, helper, colecaoDados);
				}

				form.setTotalSelecionado("sim");
				sessao.setAttribute("habilitaCamposCiclo", true);
			} else {
				sessao.removeAttribute("habilitaCamposCiclo");
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
		}

		return retorno;
	}

	private void selecionarContas(HttpServletRequest request, ComandoEmpresaCobrancaContaHelper helper, Collection<Object[]> colecaoDados) {
		limparColecoes(request);

		Integer qtdContas = 0;
		Integer qtdClientes = 0;
		BigDecimal valorTotalDivida = new BigDecimal(0.0);

		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			for (Iterator<Object[]> iterator = colecaoDados.iterator(); iterator.hasNext();) {
				Object[] dados = (Object[]) iterator.next();

				qtdContas += (Integer) dados[0];
				qtdClientes += (Integer) dados[1];
				valorTotalDivida = valorTotalDivida.add((BigDecimal) dados[2]);
			}

			form.setQtdContas(qtdContas.toString());
			form.setQtdClientes(qtdClientes.toString());
			form.setValorTotalDivida(Util.formatarMoedaReal(valorTotalDivida));
		} else {
			limparTotais();
		}
	}

	private void limparTotalizacao(HttpServletRequest request) {
		if (request.getParameter("limparTotalizacao") != null && request.getParameter("limparTotalizacao").equalsIgnoreCase("SIM")) {
			limparTotais();

			form.setTotalSelecionado(null);
			sessao.setAttribute("habilitaCamposCiclo", false);

			limparColecoes(request);
		}
	}

	private void limparTotais() {
		form.setQtdContas("");
		form.setQtdClientes("");
		form.setValorTotalDivida("");
	}

	@SuppressWarnings("unchecked")
	private EmpresaContratoCobranca pesquisarContrato() {
		FiltroEmpresaContratoCobranca filtro = new FiltroEmpresaContratoCobranca();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresaContratoCobranca.EMPRESA_ID, form.getIdEmpresa()));
		Collection<EmpresaContratoCobranca> colecao = Fachada.getInstancia().pesquisar(filtro, EmpresaContratoCobranca.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			return (EmpresaContratoCobranca) Util.retonarObjetoDeColecao(colecao);
		} else {
			return null;
		}
	}

	private void selecionarContasAgrupandoPorImovel(HttpServletRequest request, ComandoEmpresaCobrancaContaHelper helper, Collection<Object[]> colecaoDados, EmpresaContratoCobranca contrato) {
		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Collection<String> colecaoFaixa = new ArrayList<String>();
			Collection<Integer> colecaoQtdeContas = new ArrayList<Integer>();
			Collection<Integer> colecaoQtdeClientes = new ArrayList<Integer>();
			Collection<BigDecimal> colecaoValorTotalDivida = new ArrayList<BigDecimal>();

			List<EmpresaCobrancaFaixa> faixas = pesquisarFaixas(contrato.getId());

			if (faixas != null && !faixas.isEmpty()) {

				EmpresaCobrancaFaixa faixa = (EmpresaCobrancaFaixa) faixas.get(0);
				Integer numeroMinimoContas = null;
				Integer numeroMaximoContas = faixa.getNumeroMinimoContasFaixa() - 1;

				Integer qtdeContas = 0;
				Integer qtdeClientes = 0;

				Iterator<Object[]> iterator = colecaoDados.iterator();

				for (int i = 0; i < faixas.size(); i++) {

					faixa = (EmpresaCobrancaFaixa) faixas.get(i);

					numeroMinimoContas = faixa.getNumeroMinimoContasFaixa();

					numeroMaximoContas = null;

					if (i < (faixas.size() - 1)) {
						numeroMaximoContas = ((EmpresaCobrancaFaixa) faixas.get(i + 1)).getNumeroMinimoContasFaixa() - 1;
					}

					qtdeContas = 0;

					qtdeClientes = 0;

					BigDecimal valorTotalDivida = new BigDecimal(0.0);

					iterator = colecaoDados.iterator();

					while (iterator.hasNext()) {
						Object[] dados = (Object[]) iterator.next();

						if (dados[0] != null) {
							Integer quantidade = (Integer) dados[0];

							if (quantidade >= numeroMinimoContas && (numeroMaximoContas == null || quantidade <= numeroMaximoContas)) {

								qtdeContas += quantidade;

								if (dados[1] != null) {
									qtdeClientes += (Integer) dados[1];
								}

								if (dados[2] != null) {
									valorTotalDivida = valorTotalDivida.add((BigDecimal) dados[2]);
								}
							}
						}
					}

					if (i < (faixas.size() - 1)) {
						colecaoFaixa.add(numeroMinimoContas + " a " + numeroMaximoContas);
					} else {
						colecaoFaixa.add("Mais de " + numeroMinimoContas);
					}
					colecaoQtdeContas.add(qtdeContas);
					colecaoQtdeClientes.add(qtdeClientes);
					colecaoValorTotalDivida.add(valorTotalDivida);
				}

				if (!colecaoQtdeContas.isEmpty() && !colecaoQtdeClientes.isEmpty() && !colecaoValorTotalDivida.isEmpty()) {
					form.setColecaoInformada("sim");
					sessao.setAttribute("colecaoQuantidadeContas", true);
					sessao.setAttribute("tamanho", colecaoFaixa.size());
					sessao.setAttribute("colecaoFaixa", colecaoFaixa);
					sessao.setAttribute("colecaoQtdeContas", colecaoQtdeContas);
					sessao.setAttribute("colecaoQtdeClientes", colecaoQtdeClientes);
					sessao.setAttribute("colecaoValorTotalDivida", colecaoValorTotalDivida);
				} else {
					limparCampos(request);
				}

			} else {
				limparCampos(request);
			}

		} else {
			limparCampos(request);
		}
	}

	@SuppressWarnings("unchecked")
	private List<EmpresaCobrancaFaixa> pesquisarFaixas(Integer idContrato) {
		FiltroEmpresaCobrancaFaixa filtro = new FiltroEmpresaCobrancaFaixa();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, idContrato));
		filtro.setCampoOrderBy(FiltroEmpresaCobrancaFaixa.NUMERO_MAXIMO_CONTAS_FAIXA);

		return (List<EmpresaCobrancaFaixa>) Fachada.getInstancia().pesquisar(filtro, EmpresaCobrancaFaixa.class.getName());
	}

	private void limparColecoes(HttpServletRequest request) {
		form.setColecaoInformada(null);
		sessao.removeAttribute("colecaoQuantidadeContas");
		sessao.removeAttribute("colecaoFaixa");
		sessao.removeAttribute("colecaoQtdeContas");
		sessao.removeAttribute("colecaoQtdeClientes");
		sessao.removeAttribute("colecaoValorTotalDivida");
	}

	private void limparCampos(HttpServletRequest request) {
		form.setQtdContas("0");
		form.setQtdClientes("0");
		form.setValorTotalDivida(Util.formatarMoedaReal(BigDecimal.ZERO));

		limparColecoes(request);
	}

	@SuppressWarnings("unchecked")
	private void inicializarCampos(HttpServletRequest request) {
		if (request.getParameter("menu") != null && !request.getParameter("menu").trim().equals("")) {
			form.setQuantidadeDiasVencimento("25");
			form.setIndicadorCobrancaTelemarketing("1");
			form.setIndicadorGerarComDebitoPreterito("1");

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
			Collection<UnidadeNegocio> colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			Collection<Categoria> colecaoCategoria = this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());
			sessao.setAttribute("colecaoCategoria", colecaoCategoria);

			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
			Collection<ImovelPerfil> colecaoImovelPerfil = this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
			Collection<GerenciaRegional> colecaoGerenciaRegional = this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);

			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
			Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = this.getFachada().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarCamposEnter(HttpServletRequest request) {
		String idEmpresa = form.getIdEmpresa();
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
			FiltroEmpresa filtro = new FiltroEmpresa();
			filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));

			Collection<Empresa> colecao = getFachada().pesquisar(filtro, Empresa.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecao);
				form.setIdEmpresa(empresa.getId().toString());
				form.setNomeEmpresa(empresa.getDescricao());
				request.setAttribute("nomeCampo", "idEmpresa");
			} else {
				form.setIdEmpresa("");
				form.setNomeEmpresa("EMPRESA INEXISTENTE");
				request.setAttribute("empresaInexistente", true);
				request.setAttribute("nomeCampo", "idEmpresa");
			}
		} else {
			form.setNomeEmpresa("");
		}

		String idImovel = form.getIdImovel();
		if (idImovel != null && !idImovel.trim().equals("")) {
			Imovel imovel = getFachada().pesquisarImovelDigitado(new Integer(idImovel));

			if (imovel != null) {
				form.setIdImovel(imovel.getId().toString());
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
				request.setAttribute("nomeCampo", "referenciaInicial");
			} else {
				form.setIdImovel("");
				form.setInscricaoImovel("IMÓVEL INEXISTENTE");
				request.setAttribute("imovelInexistente", true);
				request.setAttribute("nomeCampo", "idImovel");
			}
		} else {
			form.setInscricaoImovel("");
		}

		String idCliente = form.getIdCliente();
		if (idCliente != null && !idCliente.trim().equals("")) {
			Cliente cliente = getFachada().pesquisarClienteDigitado(new Integer(idCliente));

			if (cliente != null) {
				form.setIdCliente(cliente.getId().toString());
				form.setNomeCliente(cliente.getNome());
				request.setAttribute("nomeCampo", "referenciaInicial");
			} else {
				form.setIdCliente("");
				form.setNomeCliente("CLIENTE INEXISTENTE");
				request.setAttribute("clienteInexistente", true);
				request.setAttribute("nomeCampo", "idCliente");
			}
		} else {
			form.setNomeCliente("");
		}

		String idServicoTipo = form.getIdServicoTipo();
		if (idServicoTipo != null && !idServicoTipo.trim().equals("")) {
			FiltroServicoTipo filtro = new FiltroServicoTipo();
			filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idServicoTipo));

			Collection<ServicoTipo> colecao = getFachada().pesquisar(filtro, ServicoTipo.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecao);
				form.setIdServicoTipo(servicoTipo.getId().toString());
				form.setDescricaoServicoTipo(servicoTipo.getDescricao());
				request.setAttribute("nomeCampo", "idLocalidadeOrigem");
				request.setAttribute("idServicoTipoEncontrada", true);
			} else {
				form.setIdServicoTipo("");
				form.setDescricaoServicoTipo("TIPO DE SERVICO INEXISTENTE");
				request.removeAttribute("idServicoTipoEncontrada");
				request.setAttribute("nomeCampo", "idServicoTipo");
			}
		} else {
			form.setNomeCliente("");
		}

		String idLocalidadeOrigem = form.getIdLocalidadeOrigem();
		if (idLocalidadeOrigem != null
				&& !idLocalidadeOrigem.trim().equals("")
				&& request.getParameter("tipoPesquisa") != null
				&& (request.getParameter("tipoPesquisa").equals("localidadeOrigem") 
						|| request.getParameter("tipoPesquisa").equals("setorComercialOrigem") 
						|| request.getParameter("tipoPesquisa").equals("quadraInicial"))) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeOrigem));

			Collection<Localidade> colecaoLocalidade = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

				form.setIdLocalidadeOrigem(localidade.getId().toString());
				form.setNomeLocalidadeOrigem(localidade.getDescricao());
				request.setAttribute("nomeCampo", "codigoSetorComercialOrigem");

				if (request.getParameter("tipoPesquisa").equals("localidadeOrigem")) {
					form.setIdLocalidadeDestino(localidade.getId().toString());
					form.setNomeLocalidadeDestino(localidade.getDescricao());
					request.setAttribute("nomeCampo", "codigoSetorComercialDestino");
				}

				String codigoSetorComercialOrigem = form.getCodigoSetorComercialOrigem();
				if (codigoSetorComercialOrigem != null && !codigoSetorComercialOrigem.trim().equals("")) {
					FiltroSetorComercial filtroSetor = new FiltroSetorComercial();
					filtroSetor.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidade.getId()));
					filtroSetor.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialOrigem));

					Collection<SetorComercial> colecaoSetor = getFachada().pesquisar(filtroSetor, SetorComercial.class.getName());

					if (colecaoSetor != null && !colecaoSetor.isEmpty()) {
						SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);

						form.setIdSetorComercialOrigem("" + setorComercial.getId());
						form.setCodigoSetorComercialOrigem("" + setorComercial.getCodigo());
						form.setDescricaoSetorComercialOrigem(setorComercial.getDescricao());
						request.setAttribute("nomeCampo", "idLocalidadeDestino");

						if (request.getParameter("tipoPesquisa").equals("setorComercialOrigem")) {
							form.setIdSetorComercialDestino("" + setorComercial.getId());
							form.setCodigoSetorComercialDestino("" + setorComercial.getCodigo());
							form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
							request.setAttribute("nomeCampo", "idLocalidadeDestino");
						}

						String codigoQuadraInicial = form.getCodigoQuadraInicial();
						if (codigoQuadraInicial != null && !codigoQuadraInicial.trim().equals("")) {
							FiltroQuadra filtroQuadra = new FiltroQuadra();
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, codigoQuadraInicial));

							Collection<Quadra> colecaoQuadra = getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
							if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
								Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

								form.setCodigoQuadraInicial("" + quadra.getNumeroQuadra());
								form.setDescricaoQuadraInicial(quadra.getDescricao());
								request.setAttribute("nomeCampo", "codigoQuadraInicial");

								if (request.getParameter("tipoPesquisa").equals("quadraInicial")) {
									form.setCodigoQuadraFinal("" + quadra.getNumeroQuadra());
									form.setDescricaoQuadraFinal(quadra.getDescricao());
									request.setAttribute("nomeCampo", "codigoQuadraInicial");
								}
							} else {
								form.setCodigoQuadraInicial("");
								form.setDescricaoQuadraInicial("QUADRA INEXISTENTE");
								request.setAttribute("quadraInicialInexistente", true);
								request.setAttribute("nomeCampo", "codigoQuadraInicial");
							}
						}
					} else {
						form.setIdSetorComercialOrigem("");
						form.setCodigoSetorComercialOrigem("");
						form.setDescricaoSetorComercialOrigem("SETOR COMERCIAL INEXISTENTE");
						request.setAttribute("setorComercialOrigemInexistente", true);
						request.setAttribute("nomeCampo", "codigoSetorComercialOrigem");
					}
				}
			} else {
				form.setIdLocalidadeOrigem("");
				form.setNomeLocalidadeOrigem("LOCALIDADE INEXISTENTE");
				request.setAttribute("localidadeOrigemInexistente", true);
				request.setAttribute("nomeCampo", "idLocalidadeOrigem");
			}
		}

		String idLocalidadeDestino = form.getIdLocalidadeDestino();
		if (idLocalidadeDestino != null
				&& !idLocalidadeDestino.trim().equals("")
				&& request.getParameter("tipoPesquisa") != null
				&& (request.getParameter("tipoPesquisa").equals("localidadeDestino") 
						|| request.getParameter("tipoPesquisa").equals("setorComercialDestino") 
						|| request.getParameter("tipoPesquisa").equals("quadraFinal"))) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeDestino));

			Collection<Localidade> colecaoLocalidade = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

				form.setIdLocalidadeDestino(localidade.getId().toString());
				form.setNomeLocalidadeDestino(localidade.getDescricao());
				request.setAttribute("nomeCampo", "codigoSetorComercialDestino");

				String codigoSetorComercialDestino = form.getCodigoSetorComercialDestino();
				if (codigoSetorComercialDestino != null && !codigoSetorComercialDestino.trim().equals("")) {
					FiltroSetorComercial filtroSetor = new FiltroSetorComercial();
					filtroSetor.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidade.getId()));
					filtroSetor.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialDestino));

					Collection<SetorComercial> colecaoSetor = getFachada().pesquisar(filtroSetor, SetorComercial.class.getName());

					if (colecaoSetor != null && !colecaoSetor.isEmpty()) {
						SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);

						form.setIdSetorComercialDestino("" + setorComercial.getId());
						form.setCodigoSetorComercialDestino("" + setorComercial.getCodigo());
						form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
						request.setAttribute("nomeCampo", "referenciaInicial");

						String codigoQuadraFinal = form.getCodigoQuadraFinal();
						if (codigoQuadraFinal != null && !codigoQuadraFinal.trim().equals("")) {
							if (form.getCodigoQuadraInicial() != null && !form.getCodigoQuadraInicial().trim().equals("")) {
								Integer codQuadraFinal = new Integer(codigoQuadraFinal);
								Integer codQuadraInicial = new Integer(form.getCodigoQuadraInicial());

								if (codQuadraFinal.compareTo(codQuadraInicial) < 0) {
									throw new ActionServletException("atencao.quadraInicial.maior.que.quadraFinal");
								}
							}

							FiltroQuadra filtroQuadra = new FiltroQuadra();
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, codigoQuadraFinal));

							Collection<Quadra> colecaoQuadra = getFachada().pesquisar(filtroQuadra, Quadra.class.getName());

							if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
								Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
								form.setCodigoQuadraFinal("" + quadra.getNumeroQuadra());
								form.setDescricaoQuadraFinal(quadra.getDescricao());
								request.setAttribute("nomeCampo", "codigoQuadraFinal");

							} else {
								form.setCodigoQuadraFinal("");
								form.setDescricaoQuadraFinal("QUADRA INEXISTENTE");
								request.setAttribute("quadraFinalInexistente", true);
								request.setAttribute("nomeCampo", "codigoQuadraFinal");
							}
						}
					} else {
						form.setIdSetorComercialDestino("");
						form.setCodigoSetorComercialDestino("");
						form.setDescricaoSetorComercialDestino("SETOR COMERCIAL INEXISTENTE");
						request.setAttribute("setorComercialDestinoInexistente", true);
						request.setAttribute("nomeCampo", "codigoSetorComercialDestino");
					}
				}
			} else {
				form.setIdLocalidadeDestino("");
				form.setNomeLocalidadeDestino("LOCALIDADE INEXISTENTE");

				request.setAttribute("localidadeDestinoInexistente", true);
				request.setAttribute("nomeCampo", "idLocalidadeDestino");
			}
		}
	}

	private ComandoEmpresaCobrancaContaHelper montarHelper() {
		ComandoEmpresaCobrancaConta comando = new ComandoEmpresaCobrancaConta();

		comando.setIndicadorResidencial(ConstantesSistema.NAO.intValue());
		comando.setIndicadorComercial(ConstantesSistema.NAO.intValue());
		comando.setIndicadorIndustrial(ConstantesSistema.NAO.intValue());
		comando.setIndicadorPublico(ConstantesSistema.NAO.intValue());
		comando.setImovel(pesquisarImovel());
		comando.setCliente(pesquisarCliente());
		comando.setLocalidadeInicial(pesquisarLocalidadeInicial());
		comando.setLocalidadeFinal(pesquisarLocalidadeFinal());
		comando.setCodigoSetorComercialInicial(pesquisarSetorComercialInicial());
		comando.setCodigoSetorComercialFinal(pesquisarSetorComercialFinal());
		comando.setNumeroQuadraInicial(pesquisarQuadraInicial());
		comando.setNumeroQuadraFinal(pesquisarQuadraFinal());
		comando = montarIndicadoresCategoria(comando);

		List<Integer> idsUnidadeNegocio = montarListaIds(form.getIdsUnidadeNegocio());
		List<Integer> idsGerenciaRegional = montarListaIds(form.getIdsGerenciaRegional());
		List<Integer> idsImovelPerfil = montarListaIds(form.getIdsImovelPerfil());
		List<Integer> idsLigacaoAguaSituacao = montarListaIds(form.getIdsLigacaoAguaSituacao());
		
		if (form.getDataVencimentoInicial() != null && !form.getDataVencimentoInicial().equals("")) {
			algumParametroInformado = true;
			comando.setDataVencimentoContaInicial(Util.converteStringParaDate(form.getDataVencimentoInicial()));
		}

		if (form.getDataVencimentoFinal() != null && !form.getDataVencimentoFinal().equals("")) {
			algumParametroInformado = true;
			comando.setDataVencimentoContaFinal(Util.converteStringParaDate(form.getDataVencimentoFinal()));
		}

		if (form.getReferenciaInicial() != null && !form.getReferenciaInicial().equals("")) {
			algumParametroInformado = true;
			comando.setReferenciaContaInicial(Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaInicial()));
		} else {
			Integer referenciaInicialFormatada = 198001;
			comando.setReferenciaContaInicial(referenciaInicialFormatada);
		}

		if (form.getReferenciaFinal() != null && !form.getReferenciaFinal().equals("")) {
			algumParametroInformado = true;
			comando.setReferenciaContaFinal(Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaFinal()));
		} else {
			SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
			comando.setReferenciaContaFinal(sistemaParametro.getAnoMesArrecadacao());
		}

		if (form.getValorMinimo() != null && !form.getValorMinimo().equals("")) {
			algumParametroInformado = true;
			comando.setValorMinimoConta(Util.formatarMoedaRealparaBigDecimal(form.getValorMinimo()));
		}

		if (form.getValorMaximo() != null && !form.getValorMaximo().equals("")) {
			algumParametroInformado = true;
			comando.setValorMaximoConta(Util.formatarMoedaRealparaBigDecimal(form.getValorMaximo()));
		}

		if (form.getQuantidadeContasInicial() != null && !form.getQuantidadeContasInicial().equals("")) {
			algumParametroInformado = true;
			comando.setQtdContasInicial(new Integer(form.getQuantidadeContasInicial()));
		}

		if (form.getQuantidadeContasFinal() != null && !form.getQuantidadeContasFinal().equals("")) {
			algumParametroInformado = true;
			comando.setQtdContasFinal(new Integer(form.getQuantidadeContasFinal()));
		}

		if (comando.getQtdContasInicial() != null && comando.getQtdContasFinal() == null) {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", "Quantidade de Contas Final");
		}

		if (comando.getQtdContasInicial() == null && comando.getQtdContasFinal() != null) {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", "Quantidade de Contas Inicial");
		}

		if (comando.getQtdContasInicial() != null && comando.getQtdContasFinal() != null && comando.getQtdContasFinal().compareTo(comando.getQtdContasInicial()) < 0) {
			throw new ActionServletException("atencao.quantidade.contas_final.menor.quantidade_inicial");
		}

		if (form.getQuantidadeDiasVencimento() != null && !form.getQuantidadeDiasVencimento().equals("")) {
			algumParametroInformado = true;
			comando.setQtdDiasVencimento(new Integer(form.getQuantidadeDiasVencimento()));
		}
		
		if (form.getIndicadorCobrancaTelemarketing() != null) {
			comando.setIndicadorCobrancaTelemarketing(new Short(form.getIndicadorCobrancaTelemarketing()));
		}
		
		if (form.getQuantidadeMaximaClientes() != null && !form.getQuantidadeMaximaClientes().trim().equals("") && Integer.parseInt(form.getQuantidadeMaximaClientes()) > 0) {
			comando.setQtdMaximaClientes(new Integer(form.getQuantidadeMaximaClientes()));
		}
		
		if (form.getIndicadorGerarComDebitoPreterito() != null && !form.getIndicadorGerarComDebitoPreterito().trim().equals("")) {
			comando.setIndicadorGerarComDebitoPreterito(new Short(form.getIndicadorGerarComDebitoPreterito()));
		}

		if (algumParametroInformado) {
			ComandoEmpresaCobrancaContaHelper helper = new ComandoEmpresaCobrancaContaHelper();
			helper.setComando(comando);
			helper.setIdsUnidadeNegocio(idsUnidadeNegocio);
			helper.setIdsGerenciaRegional(idsGerenciaRegional);
			helper.setIdsImovelPerfil(idsImovelPerfil);
			helper.setIdsLigacaoAguaSituacao(idsLigacaoAguaSituacao);

			return helper;
		} else {
			return null;
		}
	}
	
	private List<Integer> montarListaIds(String[] ids) {
		List<Integer> lista = new ArrayList<Integer>();

		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				Integer id = new Integer(ids[i]);

				if (id == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					lista = null;
					break;
				}
				lista.add(id);
			}
		}

		return lista;
	}
	
	private ComandoEmpresaCobrancaConta montarIndicadoresCategoria(ComandoEmpresaCobrancaConta comando) {
		String[] ids = form.getIdsCategoria();
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (ids[i].equals(Categoria.COMERCIAL.toString())) {
					comando.setIndicadorComercial(ConstantesSistema.SIM.intValue());
				} else if (ids[i].equals(Categoria.INDUSTRIAL.toString())) {
					comando.setIndicadorIndustrial(ConstantesSistema.SIM.intValue());
				} else if (ids[i].equals(Categoria.RESIDENCIAL.toString())) {
					comando.setIndicadorResidencial(ConstantesSistema.SIM.intValue());
				} else if (ids[i].equals(Categoria.PUBLICO.toString())) {
					comando.setIndicadorPublico(ConstantesSistema.SIM.intValue());
				}
			}
		}
		return comando;
	}

	@SuppressWarnings("unchecked")
	private Integer pesquisarQuadraFinal() {
		Integer numero = null;
		if (form.getCodigoQuadraFinal() != null && !form.getCodigoQuadraFinal().equals("")) {
			algumParametroInformado = true;

			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercialDestino()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, form.getCodigoQuadraFinal()));
			Collection<Quadra> colecao = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				numero = new Integer(form.getCodigoQuadraFinal());
			} else {
				throw new ActionServletException("atencao.pesquisa.quadra_final_inexistente");
			}
		}
		return numero;
	}
	
	@SuppressWarnings("unchecked")
	private Integer pesquisarQuadraInicial() {
		Integer numero = null;
		if (form.getCodigoQuadraInicial() != null && !form.getCodigoQuadraInicial().equals("")) {
			algumParametroInformado = true;

			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercialOrigem()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, form.getCodigoQuadraInicial()));
			Collection<Quadra> colecao = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				numero = new Integer(form.getCodigoQuadraInicial());
			} else {
				throw new ActionServletException("atencao.pesquisa.quadra_inicial_inexistente");
			}
		}
		return numero;
	}

	@SuppressWarnings("unchecked")
	private Integer pesquisarSetorComercialFinal() {
		Integer codigo = null;
		if (form.getCodigoSetorComercialDestino() != null && !form.getCodigoSetorComercialDestino().equals("")) {
			algumParametroInformado = true;

			FiltroSetorComercial filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidadeDestino()));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialDestino()));
			Collection<SetorComercial> colecao = this.getFachada().pesquisar(filtro, SetorComercial.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				codigo = new Integer(form.getCodigoSetorComercialDestino());
			} else {
				throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");
			}
		}
		return codigo;
	}

	@SuppressWarnings("unchecked")
	private Integer pesquisarSetorComercialInicial() {
		Integer codigo = null;
		if (form.getCodigoSetorComercialOrigem() != null && !form.getCodigoSetorComercialOrigem().equals("")) {
			algumParametroInformado = true;

			FiltroSetorComercial filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidadeOrigem()));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialOrigem()));
			Collection<SetorComercial> colecao = this.getFachada().pesquisar(filtro, SetorComercial.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				codigo = new Integer(form.getCodigoSetorComercialOrigem());
			} else {
				throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");
			}
		}
		return codigo;
	}

	@SuppressWarnings("unchecked")
	private Localidade pesquisarLocalidadeFinal() {
		Localidade localidade = null;
		if (form.getIdLocalidadeDestino() != null && !form.getIdLocalidadeDestino().equals("")) {
			algumParametroInformado = true;

			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeDestino()));
			Collection<Localidade> colecao = this.getFachada().pesquisar(filtro, Localidade.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				localidade = (Localidade) colecao.iterator().next();
			} else {
				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");
			}
		}
		return localidade;
	}
	
	@SuppressWarnings("unchecked")
	private Localidade pesquisarLocalidadeInicial() {
		Localidade localidade = null;
		if (form.getIdLocalidadeOrigem() != null && !form.getIdLocalidadeOrigem().equals("")) {
			algumParametroInformado = true;

			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeOrigem()));
			Collection<Localidade> colecao = this.getFachada().pesquisar(filtro, Localidade.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				localidade = (Localidade) colecao.iterator().next();
			} else {
				throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");
			}
		}
		return localidade;
	}

	@SuppressWarnings("unchecked")
	private Cliente pesquisarCliente() {
		Cliente cliente = null;
		if (form.getIdCliente() != null && !form.getIdCliente().equals("")) {
			algumParametroInformado = true;

			FiltroCliente filtro = new FiltroCliente();
			filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getIdCliente()));
			Collection<Cliente> colecao = this.getFachada().pesquisar(filtro, Cliente.class.getName());
			if (colecao != null && !colecao.isEmpty()) {
				cliente = (Cliente) colecao.iterator().next();
			} else {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
		}
		return cliente;
	}

	@SuppressWarnings("unchecked")
	private Imovel pesquisarImovel() {
		Imovel imovel = null;
		if (form.getIdImovel() != null && !form.getIdImovel().equals("")) {
			algumParametroInformado = true;

			FiltroImovel filtro = new FiltroImovel();
			filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getIdImovel()));
			Collection<Imovel> colecao = this.getFachada().pesquisar(filtro, Imovel.class.getName());
			if (colecao != null && !colecao.isEmpty()) {
				imovel = (Imovel) colecao.iterator().next();
			} else {
				throw new ActionServletException("atencao.imovel.inexistente");
			}
		}
		return imovel;
	}
}

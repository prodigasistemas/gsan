package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
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
import gcom.gui.ActionServletException;
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

public class ExibirInformarContasEmCobrancaAction extends GcomAction {

	private InformarContasEmCobrancaActionForm form;
	private HttpSession sessao;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		form = (InformarContasEmCobrancaActionForm) actionForm;
		sessao = getSessao(request);

		inicializarCampos(request);
		pesquisarCamposEnter(request);

		return mapping.findForward("exibirInformarContasEmCobranca");
	}

	@SuppressWarnings("unchecked")
	private void inicializarCampos(HttpServletRequest request) {
		if (request.getParameter("menu") != null && !request.getParameter("menu").trim().equals("")) {
			form.setQuantidadeDiasVencimento("25");
			form.setIndicadorCobrancaTelemarketing("1");
			form.setIndicadorGerarComDebitoPreterito("1");
			form.setIndicadorPossuiCpfCnpj("1");

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
		if (idLocalidadeOrigem != null && !idLocalidadeOrigem.trim().equals("") && request.getParameter("tipoPesquisa") != null
				&& (request.getParameter("tipoPesquisa").equals("localidadeOrigem") || request.getParameter("tipoPesquisa").equals("setorComercialOrigem") || request.getParameter("tipoPesquisa").equals("quadraInicial"))) {

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
		if (idLocalidadeDestino != null && !idLocalidadeDestino.trim().equals("") && request.getParameter("tipoPesquisa") != null
				&& (request.getParameter("tipoPesquisa").equals("localidadeDestino") || request.getParameter("tipoPesquisa").equals("setorComercialDestino") || request.getParameter("tipoPesquisa").equals("quadraFinal"))) {

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
}

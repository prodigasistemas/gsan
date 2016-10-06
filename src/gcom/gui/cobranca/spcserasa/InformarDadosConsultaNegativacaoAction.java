package gcom.gui.cobranca.spcserasa;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
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
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

@SuppressWarnings({"rawtypes", "unchecked"})
public class InformarDadosConsultaNegativacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirResumoNegativacaoParametros");
		HttpSession sessao = request.getSession(false);
		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;
		DadosConsultaNegativacaoHelper helper = new DadosConsultaNegativacaoHelper();

		// Negativador
		String[] arrayNegativador = form.getArrayNegativador();
		Negativador negativadorColecao = new Negativador();
		negativadorColecao.setId(-1);

		Collection colecaoNegativador = new ArrayList();
		Collection colecaoIdNegativador = new ArrayList();
		if (arrayNegativador != null) {
			colecaoNegativador.add(negativadorColecao);
			FiltroNegativador filtroNegativador = new FiltroNegativador();

			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");

			for (int i = 0; i < arrayNegativador.length; i++) {
				if (!arrayNegativador[i].equals("") && !arrayNegativador[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdNegativador.add(arrayNegativador[i]);
				}
			}

			filtroNegativador.adicionarParametro(new ParametroSimplesIn(FiltroNegativador.ID, colecaoIdNegativador));

			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);

			Collection colecaoNegativadorPesquisa = getFachada().pesquisar(filtroNegativador, Negativador.class.getName());

			if (colecaoNegativadorPesquisa != null && !colecaoNegativadorPesquisa.isEmpty()) {
				colecaoNegativador.addAll(colecaoNegativadorPesquisa);
			}
		} else {
			colecaoNegativador.add(negativadorColecao);
		}

		helper.setColecaoNegativador(colecaoNegativador);

		if (form.getPeriodoEnvioNegativacaoInicio() != null && !form.getPeriodoEnvioNegativacaoInicio().equals("")) {
			String periodoEnvioNegativacaoInicio = form.getPeriodoEnvioNegativacaoInicio();
			sessao.setAttribute("periodoEnvioNegativacaoInicio", periodoEnvioNegativacaoInicio);
			helper.setPeriodoEnvioNegativacaoInicio(Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoInicio()));
		}

		if (form.getPeriodoEnvioNegativacaoFim() != null && !form.getPeriodoEnvioNegativacaoFim().equals("")) {
			String periodoEnvioNegativacaoFim = form.getPeriodoEnvioNegativacaoFim();
			sessao.setAttribute("periodoEnvioNegativacaoFim", periodoEnvioNegativacaoFim);
			helper.setPeriodoEnvioNegativacaoFim(Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoFim()));
		}

		if (form.getPeriodoExclusaoNegativacaoInicio() != null && !form.getPeriodoExclusaoNegativacaoInicio().equals("")) {
			String periodoExclusaoNegativacaoInicio = form.getPeriodoExclusaoNegativacaoInicio();
			sessao.setAttribute("periodoExclusaoNegativacaoInicio", periodoExclusaoNegativacaoInicio);
			helper.setPeriodoExclusaoNegativacaoInicio(Util.converteStringParaDate(form.getPeriodoExclusaoNegativacaoInicio()));
		}

		if (form.getPeriodoExclusaoNegativacaoFim() != null && !form.getPeriodoExclusaoNegativacaoFim().equals("")) {
			String periodoExclusaoNegativacaoFim = form.getPeriodoExclusaoNegativacaoFim();
			sessao.setAttribute("periodoExclusaoNegativacaoFim", periodoExclusaoNegativacaoFim);
			helper.setPeriodoExclusaoNegativacaoFim(Util.converteStringParaDate(form.getPeriodoExclusaoNegativacaoFim()));
		}

		if (helper.getPeriodoEnvioNegativacaoInicio() != null && helper.getPeriodoEnvioNegativacaoFim() != null) {
			if (helper.getPeriodoEnvioNegativacaoFim().before(helper.getPeriodoEnvioNegativacaoInicio())) {
				throw new ActionServletException("atencao.data_final_periodo_negativacao_anterior_data_inicial");
			}
		}

		if (helper.getPeriodoExclusaoNegativacaoInicio() != null
				&& helper.getPeriodoExclusaoNegativacaoFim() != null) {
			if (helper.getPeriodoExclusaoNegativacaoFim().before(helper.getPeriodoExclusaoNegativacaoInicio())) {
				throw new ActionServletException("atencao.data_final_periodo_negativacao_anterior_data_inicial");
			}
		}

		if (form.getIdNegativadorExclusaoMotivo() != null
				&& !form.getIdNegativadorExclusaoMotivo().equals("")
				&& new Integer(form.getIdNegativadorExclusaoMotivo()) > 0) {
			helper.setIdNegativadorExclusaoMotivo(new Integer(form.getIdNegativadorExclusaoMotivo()));
		}

		if (form.getIdNegativacaoComando() != null && !form.getIdNegativacaoComando().equals("")) {
			helper.setIdNegativacaoComando(new Integer(form.getIdNegativacaoComando()));
		}

		// Grupo Cobrança
		String[] arrayCobrancaGrupo = form.getArrayCobrancaGrupo();
		CobrancaGrupo cobrancaGrupoColecao = new CobrancaGrupo();
		cobrancaGrupoColecao.setId(-1);

		Collection colecaoCobrancaGrupo = new ArrayList();

		if (arrayCobrancaGrupo != null) {
			cobrancaGrupoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

			for (int i = 0; i < arrayCobrancaGrupo.length; i++) {
				if (!arrayCobrancaGrupo[i].equals("") && !arrayCobrancaGrupo[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayCobrancaGrupo.length) {
						filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, arrayCobrancaGrupo[i], ConectorOr.CONECTOR_OR,
								arrayCobrancaGrupo.length));

					} else {
						filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, arrayCobrancaGrupo[i]));
					}
				}
			}

			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);

			Collection colecaoCobrancaGrupoPesquisa = getFachada().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			if (colecaoCobrancaGrupoPesquisa != null && !colecaoCobrancaGrupoPesquisa.isEmpty()) {
				colecaoCobrancaGrupo.addAll(colecaoCobrancaGrupoPesquisa);
			}
		} else {
			cobrancaGrupoColecao.setDescricao("TODOS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
		}

		helper.setColecaoCobrancaGrupo(colecaoCobrancaGrupo);

		if (form.getIdEloPolo() != null && !form.getIdEloPolo().equals("") && new Integer(form.getIdEloPolo()) > 0) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdEloPolo()));

			Collection coll = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (coll.size() != 1) {
				throw new ActionServletException("pesquisa.elo.inexistente");
			} else {
				helper.setIdEloPolo(new Integer(form.getIdEloPolo()));
			}
		}

		if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("") && new Integer(form.getIdLocalidade()) > 0) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));

			Collection coll = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (coll.size() != 1) {
				throw new ActionServletException("pesquisa.localidade.inexistente");
			} else {
				helper.setIdLocalidade(new Integer(form.getIdLocalidade()));
			}
		}

		if (form.getIdSetorComercial() != null && !form.getIdSetorComercial().equals("") && new Integer(form.getIdSetorComercial()) > 0) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));

			Collection coll = getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			if (coll.size() != 1) {
				throw new ActionServletException("atencao.setor_comercial.inexistente");
			} else {
				helper.setIdSetorComercial(new Integer(form.getIdSetorComercial()));
			}
		}

		if (form.getIdQuadra() != null && !form.getIdQuadra().equals("") && new Integer(form.getIdQuadra()) > 0) {
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercial()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, form.getIdQuadra()));

			Collection coll = getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
			if (coll.size() != 1) {
				throw new ActionServletException("atencao.quadra.inexistente");
			} else {
				helper.setIdQuadra(new Integer(form.getIdQuadra()));
			}
		}

		// Gerência Regional
		String[] arrayGerenciaRegional = form.getArrayGerenciaRegional();
		GerenciaRegional gerenciaRegionalColecao = new GerenciaRegional();
		gerenciaRegionalColecao.setId(-1);

		Collection colecaoGerenciaRegional = new ArrayList();

		if (arrayGerenciaRegional != null) {
			gerenciaRegionalColecao.setNome("OPÇÕES SELECIONADAS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			for (int i = 0; i < arrayGerenciaRegional.length; i++) {
				if (!arrayGerenciaRegional[i].equals("") && !arrayGerenciaRegional[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayGerenciaRegional.length) {
						filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, arrayGerenciaRegional[i],
								ConectorOr.CONECTOR_OR, arrayGerenciaRegional.length));

					} else {
						filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, arrayGerenciaRegional[i]));
					}
				}
			}

			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);

			Collection colecaoGerenciaRegionalPesquisa = getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegionalPesquisa != null && !colecaoGerenciaRegionalPesquisa.isEmpty()) {
				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);
			}
		} else {
			gerenciaRegionalColecao.setNome("TODOS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
		}

		helper.setColecaoGerenciaRegional(colecaoGerenciaRegional);

		// Unidade de Negócio
		String[] arrayUnidadeNegocio = form.getArrayUnidadeNegocio();
		UnidadeNegocio unidadeNegocioColecao = new UnidadeNegocio();
		unidadeNegocioColecao.setId(-1);

		Collection colecaoUnidadeNegocio = new ArrayList();

		if (arrayUnidadeNegocio != null) {
			unidadeNegocioColecao.setNome("OPÇÕES SELECIONADAS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			for (int i = 0; i < arrayUnidadeNegocio.length; i++) {
				if (!arrayUnidadeNegocio[i].equals("") && !arrayUnidadeNegocio[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayUnidadeNegocio.length) {
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, arrayUnidadeNegocio[i], ConectorOr.CONECTOR_OR,
								arrayUnidadeNegocio.length));

					} else {
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, arrayUnidadeNegocio[i]));
					}
				}
			}

			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

			Collection colecaoUnidadeNegocioPesquisa = getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocioPesquisa != null && !colecaoUnidadeNegocioPesquisa.isEmpty()) {
				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
			}
		} else {
			unidadeNegocioColecao.setNome("TODOS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
		}

		helper.setColecaoUnidadeNegocio(colecaoUnidadeNegocio);

		// Perfil Imóvel
		String[] arrayImovelPerfil = form.getArrayImovelPerfil();
		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection colecaoImovelPerfil = new ArrayList();

		if (arrayImovelPerfil != null) {
			imovelPerfilColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			for (int i = 0; i < arrayImovelPerfil.length; i++) {
				if (!arrayImovelPerfil[i].equals("") && !arrayImovelPerfil[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayImovelPerfil.length) {
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, arrayImovelPerfil[i], ConectorOr.CONECTOR_OR,
								arrayImovelPerfil.length));

					} else {
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, arrayImovelPerfil[i]));
					}
				}
			}

			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

			Collection colecaoImovelPerfilPesquisa = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

			if (colecaoImovelPerfilPesquisa != null && !colecaoImovelPerfilPesquisa.isEmpty()) {
				colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
			}
		} else {
			imovelPerfilColecao.setDescricao("TODOS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
		}

		helper.setColecaoImovelPerfil(colecaoImovelPerfil);

		// Categoria
		String[] arrayCategoria = form.getArrayCategoria();
		Categoria categoriaColecao = new Categoria();
		categoriaColecao.setId(-1);

		Collection colecaoCategoria = new ArrayList();

		if (arrayCategoria != null) {
			categoriaColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoCategoria.add(categoriaColecao);
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			for (int i = 0; i < arrayCategoria.length; i++) {
				if (!arrayCategoria[i].equals("") && !arrayCategoria[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayCategoria.length) {
						filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, arrayCategoria[i], ConectorOr.CONECTOR_OR,
								arrayCategoria.length));

					} else {
						filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, arrayCategoria[i]));
					}
				}
			}

			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

			Collection colecaoCategoriaPesquisa = getFachada().pesquisar(filtroCategoria, Categoria.class.getName());

			if (colecaoCategoriaPesquisa != null && !colecaoCategoriaPesquisa.isEmpty()) {
				colecaoCategoria.addAll(colecaoCategoriaPesquisa);
			}
		} else {
			categoriaColecao.setDescricao("TODOS");
			colecaoCategoria.add(categoriaColecao);
		}

		helper.setColecaoCategoria(colecaoCategoria);

		// TipoCliente
		String[] arrayTipoCliente = form.getArrayTipoCliente();
		ClienteTipo tipoClienteColecao = new ClienteTipo();
		tipoClienteColecao.setId(-1);

		Collection colecaoTipoCliente = new ArrayList();

		if (arrayTipoCliente != null) {
			tipoClienteColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoTipoCliente.add(tipoClienteColecao);
			FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

			for (int i = 0; i < arrayTipoCliente.length; i++) {
				if (!arrayTipoCliente[i].equals("") && !arrayTipoCliente[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayTipoCliente.length) {
						filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID, arrayTipoCliente[i], ConectorOr.CONECTOR_OR,
								arrayTipoCliente.length));

					} else {
						filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID, arrayTipoCliente[i]));
					}
				}
			}

			filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

			Collection colecaoTipoClientePesquisa = getFachada().pesquisar(filtroClienteTipo, ClienteTipo.class.getName());

			if (colecaoTipoClientePesquisa != null && !colecaoTipoClientePesquisa.isEmpty()) {
				colecaoTipoCliente.addAll(colecaoTipoClientePesquisa);
			}
		} else {
			tipoClienteColecao.setDescricao("TODOS");
			colecaoTipoCliente.add(tipoClienteColecao);
		}

		helper.setColecaoClienteTipo(colecaoTipoCliente);
		
		// Esfera Poder
		String[] arrayEsferaPoder = form.getArrayEsferaPoder();
		EsferaPoder esferaPoderColecao = new EsferaPoder();
		esferaPoderColecao.setId(-1);

		Collection colecaoEsferaPoder = new ArrayList();

		if (arrayEsferaPoder != null) {
			esferaPoderColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoEsferaPoder.add(esferaPoderColecao);
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			for (int i = 0; i < arrayEsferaPoder.length; i++) {
				if (!arrayEsferaPoder[i].equals("") && !arrayEsferaPoder[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayEsferaPoder.length) {
						filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.ID, arrayEsferaPoder[i], ConectorOr.CONECTOR_OR, arrayEsferaPoder.length));

					} else {
						filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.ID, arrayEsferaPoder[i]));
					}
				}
			}

			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);

			Collection colecaoEsferaPoderPesquisa = getFachada().pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());

			if (colecaoEsferaPoderPesquisa != null && !colecaoEsferaPoderPesquisa.isEmpty()) {
				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
			}
		} else {
			esferaPoderColecao.setDescricao("TODOS");
			colecaoEsferaPoder.add(esferaPoderColecao);
		}

		helper.setColecaoEsferaPoder(colecaoEsferaPoder);

		// Ligacao Agua Situacao
		String[] arrayLigacaoAguaSituacao = form.getArrayLigacaoAguaSituacao();
		LigacaoAguaSituacao ligacaoAguaSituacaoColecao = new LigacaoAguaSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection colecaoLigacaoAguaSituacao = new ArrayList();
		int x = 0;

		if (arrayLigacaoAguaSituacao != null) {
			ligacaoAguaSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

			for (x = 0; x < arrayLigacaoAguaSituacao.length; x++) {
				if (!arrayLigacaoAguaSituacao[x].equals("") && !arrayLigacaoAguaSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (x + 1 < arrayLigacaoAguaSituacao.length) {
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, arrayLigacaoAguaSituacao[x], ConectorOr.CONECTOR_OR, arrayEsferaPoder.length));

					} else {
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, arrayLigacaoAguaSituacao[x]));
					}
				}
			}

			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);

			Collection colecaoLigacaoAguaSituacaoPesquisa = getFachada().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

			if (colecaoLigacaoAguaSituacaoPesquisa != null && !colecaoLigacaoAguaSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoAguaSituacao.addAll(colecaoLigacaoAguaSituacaoPesquisa);
			}
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}

		helper.setColecaoLigacaoAguaSituacao(colecaoLigacaoAguaSituacao);

		// Ligacao Esgoto Situacao
		String[] arrayLigacaoEsgotoSituacao = form.getArrayLigacaoEsgotoSituacao();
		LigacaoEsgotoSituacao ligacaoEsgotoSituacaoColecao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacaoColecao.setId(-1);

		Collection colecaoLigacaoEsgotoSituacao = new ArrayList();

		if (arrayLigacaoEsgotoSituacao != null) {
			ligacaoEsgotoSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

			for (x = 0; x < arrayLigacaoEsgotoSituacao.length; x++) {
				if (!arrayLigacaoEsgotoSituacao[x].equals("") && !arrayLigacaoEsgotoSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (x + 1 < arrayLigacaoEsgotoSituacao.length) {
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, arrayLigacaoEsgotoSituacao[x], ConectorOr.CONECTOR_OR, arrayEsferaPoder.length));

					} else {
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, arrayLigacaoEsgotoSituacao[x]));
					}
				}
			}

			filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);

			Collection colecaoLigacaoEsgotoSituacaoPesquisa = getFachada().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());

			if (colecaoLigacaoEsgotoSituacaoPesquisa != null && !colecaoLigacaoEsgotoSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoEsgotoSituacao.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
			}
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}

		helper.setColecaoLigacaoEsgotoSituacao(colecaoLigacaoEsgotoSituacao);

		if (form.getIndicadorRelAcompanhamentoClientesNegativados() != null && form.getIndicadorRelAcompanhamentoClientesNegativados().equals("sim")) {
			// Motivo de Rejeição
			String[] arrayMotivoRejeicao = form.getArrayMotivoRejeicao();
			NegativadorRetornoMotivo negativadorRetornoMotivoColecao = new NegativadorRetornoMotivo();
			negativadorRetornoMotivoColecao.setId(-1);

			Collection colecaoMotivoRejeicao = new ArrayList();
			int t = 0;

			if (arrayMotivoRejeicao != null) {
				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("OPÇÕES SELECIONADAS");
				colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
				FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
				filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, new Short("2")));
				filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR,colecaoIdNegativador));

				for (t = 0; t < arrayMotivoRejeicao.length; t++) {
					if (!arrayMotivoRejeicao[t].equals("") && !arrayMotivoRejeicao[t].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

						if (t + 1 < arrayMotivoRejeicao.length) {
							filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.ID, arrayMotivoRejeicao[t], ConectorOr.CONECTOR_OR, arrayMotivoRejeicao.length));

						} else {
							filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.ID, arrayMotivoRejeicao[t]));
						}
					}
				}

				filtroNegativadorRetornoMotivo.setCampoOrderBy(FiltroNegativadorRetornoMotivo.DESCRICAO_RETORNO_CODIGO);

				Collection colecaoNegativadorRetornoMotivoPesquisa = getFachada().pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());

				if (colecaoNegativadorRetornoMotivoPesquisa != null && !colecaoNegativadorRetornoMotivoPesquisa.isEmpty()) {
					colecaoMotivoRejeicao.addAll(colecaoNegativadorRetornoMotivoPesquisa);
				}
			} else {
				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("TODOS");
				colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
			}

			helper.setColecaoMotivoRejeicao(colecaoMotivoRejeicao);
			if (form.getIndicadorApenasNegativacoesRejeitadas() != null && !form.getIndicadorApenasNegativacoesRejeitadas().equals("")) {
				helper.setIndicadorApenasNegativacoesRejeitadas(new Short(form.getIndicadorApenasNegativacoesRejeitadas()));
			}
		}

		sessao.setAttribute("dadosConsultaNegativacaoHelper", helper);

		if (form.getTipoConsulta().equals(InformarDadosConsultaNegativacaoActionForm.ACOMPANHAMENTO)) {
			form.setIndicadorRelExclusao(null);
			form.setIndicadorRelAcompanhamentoClientesNegativados("sim");
			form.setIndicadorApenasNegativacoesRejeitadas(ConstantesSistema.NAO.toString());
			retorno = actionMapping.findForward("gerarRelatorioAcompanhamentoClientesNegativados");
		} else if (form.getTipoConsulta().equals(InformarDadosConsultaNegativacaoActionForm.EXCLUSOES)) {
			form.setIndicadorRelExclusao("sim");
			form.setIndicadorRelAcompanhamentoClientesNegativados(null);
			retorno = actionMapping.findForward("gerarRelatorioNegativacoesExcluidas");
		} else if (form.getTipoConsulta().equals(InformarDadosConsultaNegativacaoActionForm.RESUMO)) {
			form.setIndicadorRelExclusao(null);
			form.setIndicadorRelAcompanhamentoClientesNegativados(null);
			retorno = actionMapping.findForward("exibirResumoNegativacaoParametros");
		}

		return retorno;
	}
}

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
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ExibirInformarDadosConsultaNegativacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirInformarDadosConsultaNegativacao");
		HttpSession sessao = request.getSession(false);
		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;

		setarTipoRelatorio(form);
		setarTipoConsulta(form);
		pesquisarNegativador(sessao);
		selecionarNegativador(sessao, form);
		pesquisarCobrancaGrupo(sessao);
		pesquisarGerenciaRegional(sessao);
		pesquisarUnidadeNegocio(sessao);
		pesquisarEloPolo(request, form);
		pesquisarLocalidade(request, form);
		pesquisarSetorComercial(request, form);
		pesquisarQuadra(form, request);
		pesquisarImovelPerfil(sessao);
		pesquisarCategoria(sessao);
		pesquisarClienteTipo(sessao);
		pesquisarEsferaPoder(sessao);
		pesquisarLigacaoAguaSituacao(sessao);
		pesquisarLigacaoEsgotoSituacao(sessao);
		pesquisarMotivoRejeicao(sessao, form);

		return retorno;
	}

	private void pesquisarMotivoRejeicao(HttpSession sessao, InformarDadosConsultaNegativacaoActionForm form) {
		if (form.getArrayNegativador() != null && form.getArrayNegativador().length > 0
				&& form.getIndicadorRelAcompanhamentoClientesNegativados() != null
				&& form.getIndicadorRelAcompanhamentoClientesNegativados().equals("sim")) {

			Collection colecaoNegativador = new ArrayList();
			for (int i = 0; i < form.getArrayNegativador().length; i++) {
				colecaoNegativador.add(form.getArrayNegativador()[i]);
			}

			FiltroNegativadorRetornoMotivo filtro = new FiltroNegativadorRetornoMotivo();
			filtro.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, ConstantesSistema.NAO));
			filtro.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, colecaoNegativador));
			filtro.setCampoOrderBy("descricaoRetornocodigo");

			Collection colecaoMotivoRejeicao = Fachada.getInstancia().pesquisar(filtro, NegativadorRetornoMotivo.class.getName());
			sessao.setAttribute("collMotivoRejeicao", colecaoMotivoRejeicao);
		}
	}

	private void pesquisarLigacaoEsgotoSituacao(HttpSession sessao) {
		if (sessao.getAttribute("collLigacaoEsgotoSituacao") == null) {
			FiltroLigacaoEsgotoSituacao filtro = new FiltroLigacaoEsgotoSituacao();
			filtro.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy("descricao");
			Collection colecaoLigacaoEsgotoSituacao = Fachada.getInstancia().pesquisar(filtro, LigacaoEsgotoSituacao.class.getName());
			sessao.setAttribute("collLigacaoEsgotoSituacao", colecaoLigacaoEsgotoSituacao);
		}
	}

	private void pesquisarLigacaoAguaSituacao(HttpSession sessao) {
		if (sessao.getAttribute("collLigacaoAguaSituacao") == null) {
			FiltroLigacaoAguaSituacao filtro = new FiltroLigacaoAguaSituacao();
			filtro.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy("descricao");
			Collection colecaoLigacaoAguaSituacao = Fachada.getInstancia().pesquisar(filtro, LigacaoAguaSituacao.class.getName());
			sessao.setAttribute("collLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
		}
	}

	private void pesquisarEsferaPoder(HttpSession sessao) {
		if (sessao.getAttribute("collEsferaPoder") == null) {
			FiltroEsferaPoder filtro = new FiltroEsferaPoder();
			filtro.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy("descricao");
			Collection colecaoEsferaPoder = Fachada.getInstancia().pesquisar(filtro, EsferaPoder.class.getName());
			sessao.setAttribute("collEsferaPoder", colecaoEsferaPoder);
		}
	}

	private void pesquisarClienteTipo(HttpSession sessao) {
		if (sessao.getAttribute("collClienteTipo") == null) {
			FiltroClienteTipo filtro = new FiltroClienteTipo();
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy("descricao");
			Collection colecaoClienteTipo = Fachada.getInstancia().pesquisar(filtro, ClienteTipo.class.getName());
			sessao.setAttribute("collClienteTipo", colecaoClienteTipo);
		}
	}

	private void pesquisarCategoria(HttpSession sessao) {
		if (sessao.getAttribute("collCategoria") == null) {
			FiltroCategoria filtro = new FiltroCategoria();
			filtro.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy("descricao");
			Collection colecaoCategoria = Fachada.getInstancia().pesquisar(filtro, Categoria.class.getName());
			sessao.setAttribute("collCategoria", colecaoCategoria);
		}
	}

	private void pesquisarImovelPerfil(HttpSession sessao) {
		if (sessao.getAttribute("collImovelPerfil") == null) {
			FiltroImovelPerfil filtro = new FiltroImovelPerfil();
			filtro.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy("descricao");
			Collection colecaoImovelPerfil = Fachada.getInstancia().pesquisar(filtro, ImovelPerfil.class.getName());
			sessao.setAttribute("collImovelPerfil", colecaoImovelPerfil);
		}
	}

	private void pesquisarSetorComercial(HttpServletRequest request, InformarDadosConsultaNegativacaoActionForm form) {
		if (form.getIdSetorComercial() != null && !form.getIdSetorComercial().trim().equals("")) {
			FiltroSetorComercial filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));

			Collection colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtro, SetorComercial.class.getName());

			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
				if (((SetorComercial) ((List) colecaoSetorComercial).get(0)).getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.setor_comercial_inativo", null, ((SetorComercial) ((List) colecaoSetorComercial).get(0)).getId().toString());
				}

				Integer codigoSetorComercial = ((SetorComercial) ((List) colecaoSetorComercial).get(0)).getCodigo();
				form.setIdSetorComercial(codigoSetorComercial.toString());
				form.setDescricaoSetorComercial(((SetorComercial) ((List) colecaoSetorComercial).get(0)).getDescricao());
			} else {
				request.setAttribute("corSetorComercial", "exception");
				form.setDescricaoSetorComercial(ConstantesSistema.CODIGO_SETOR_COMERCIAL_INEXISTENTE);
				form.setIdSetorComercial("");
			}
		}
	}

	private void pesquisarLocalidade(HttpServletRequest request, InformarDadosConsultaNegativacaoActionForm form) {
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().trim().equals("")) {
			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));

			Collection colecaoLocalidade = Fachada.getInstancia().pesquisar(filtro, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				if (((Localidade) ((List) colecaoLocalidade).get(0)).getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.localidade_inativa", null, "" + ((Localidade) ((List) colecaoLocalidade).get(0)).getId());
				}

				form.setIdLocalidade(((Localidade) ((List) colecaoLocalidade).get(0)).getId().toString());
				form.setDescricaoLocalidade(((Localidade) ((List) colecaoLocalidade).get(0)).getDescricao());
			} else {
				request.setAttribute("corLocalidade", "exception");
				form.setDescricaoLocalidade(ConstantesSistema.CODIGO_LOCALIDADE_INEXISTENTE);
				form.setIdLocalidade("");
			}
		}
	}

	private void pesquisarEloPolo(HttpServletRequest request, InformarDadosConsultaNegativacaoActionForm form) {
		if (form.getIdEloPolo() != null && !form.getIdEloPolo().trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdEloPolo()));

			Collection colecaoEloPolo = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoEloPolo != null && !colecaoEloPolo.isEmpty()) {
				if (((Localidade) ((List) colecaoEloPolo).get(0)).getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.localidade_inativa", null, "" + ((Localidade) ((List) colecaoEloPolo).get(0)).getId());
				}

				form.setIdEloPolo(((Localidade) ((List) colecaoEloPolo).get(0)).getId().toString());
				form.setDescricaoEloPolo(((Localidade) ((List) colecaoEloPolo).get(0)).getDescricao());
			} else {
				request.setAttribute("corEloPolo", "exception");
				form.setDescricaoEloPolo(ConstantesSistema.CODIGO_ELO_POLO_INEXISTENTE);
				form.setIdEloPolo("");
			}
		}
	}

	private void pesquisarUnidadeNegocio(HttpSession sessao) {
		if (sessao.getAttribute("collUnidadeNegocio") == null) {
			FiltroUnidadeNegocio filtro = new FiltroUnidadeNegocio();
			filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy("nome");
			Collection colecaoUnidadeNegocio = Fachada.getInstancia().pesquisar(filtro, UnidadeNegocio.class.getName());
			sessao.setAttribute("collUnidadeNegocio", colecaoUnidadeNegocio);
		}
	}

	private void pesquisarGerenciaRegional(HttpSession sessao) {
		if (sessao.getAttribute("collGerenciaRegional") == null) {
			FiltroGerenciaRegional filtro = new FiltroGerenciaRegional();
			filtro.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy("nome");
			Collection colecao = Fachada.getInstancia().pesquisar(filtro, GerenciaRegional.class.getName());
			sessao.setAttribute("collGerenciaRegional", colecao);
		}
	}

	private void setarTipoRelatorio(InformarDadosConsultaNegativacaoActionForm form) {
		if (form.getTipoRelatorioNegativacao() == null || form.getTipoRelatorioNegativacao().trim().equals("")) {
			form.setTipoRelatorioNegativacao("1");
		}
	}

	private void pesquisarCobrancaGrupo(HttpSession sessao) {
		if (sessao.getAttribute("collCobrancaGrupo") == null) {
			FiltroCobrancaGrupo filtro = new FiltroCobrancaGrupo();
			filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy("descricao");
			Collection colecao = Fachada.getInstancia().pesquisar(filtro, CobrancaGrupo.class.getName());
			sessao.setAttribute("collCobrancaGrupo", colecao);
		}
	}

	private void selecionarNegativador(HttpSession sessao, InformarDadosConsultaNegativacaoActionForm form) {
		if (form.getArrayNegativador() != null && form.getArrayNegativador().length > 0) {
			Collection colecao = new ArrayList();
			for (int i = 0; i < form.getArrayNegativador().length; i++) {
				colecao.add(form.getArrayNegativador()[i]);
			}

			pesquisarNegativadorExclusaoMotivo(sessao, colecao);
		}
	}

	private void pesquisarNegativadorExclusaoMotivo(HttpSession sessao, Collection colecaoNegativador) {
		FiltroNegativadorExclusaoMotivo filtro = new FiltroNegativadorExclusaoMotivo();
		filtro.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorExclusaoMotivo.NEGATIVADOR_ID, colecaoNegativador));
		Collection colecao = Fachada.getInstancia().pesquisar(filtro, NegativadorExclusaoMotivo.class.getName());
		sessao.setAttribute("collNegativadorExclusaoMotivo", colecao);
	}

	private void pesquisarNegativador(HttpSession sessao) {
		if (sessao.getAttribute("collNegativador") == null) {
			FiltroNegativador filtro = new FiltroNegativador();
			filtro.adicionarParametro(new ParametroSimples(FiltroNegativador.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtro.setCampoOrderBy("cliente.nome");
			
			Collection colecao = Fachada.getInstancia().pesquisar(filtro, Negativador.class.getName());
			sessao.setAttribute("collNegativador", colecao);
		}
	}

	private void setarTipoConsulta(InformarDadosConsultaNegativacaoActionForm form) {
		if (form.getTipoConsulta() == null || form.getTipoConsulta().trim().equals("")) {
			form.setTipoConsulta(InformarDadosConsultaNegativacaoActionForm.ACOMPANHAMENTO);
			form.setIndicadorRelExclusao(null);
			form.setIndicadorRelAcompanhamentoClientesNegativados("sim");
			form.setIndicadorApenasNegativacoesRejeitadas(ConstantesSistema.NAO.toString());
		} else {
			if (form.getTipoConsulta().equals(InformarDadosConsultaNegativacaoActionForm.ACOMPANHAMENTO)) {
				form.setIndicadorRelExclusao(null);
				form.setIndicadorRelAcompanhamentoClientesNegativados("sim");
				form.setIndicadorApenasNegativacoesRejeitadas(ConstantesSistema.NAO.toString());
			} else if (form.getTipoConsulta().equals(InformarDadosConsultaNegativacaoActionForm.EXCLUSOES)) {
				form.setIndicadorRelExclusao("sim");
				form.setIndicadorRelAcompanhamentoClientesNegativados(null);
			} else if (form.getTipoConsulta().equals(InformarDadosConsultaNegativacaoActionForm.RESUMO)) {
				form.setIndicadorRelExclusao(null);
				form.setIndicadorRelAcompanhamentoClientesNegativados(null);
			}
		}
	}

	public void pesquisarQuadra(InformarDadosConsultaNegativacaoActionForm form, HttpServletRequest request) {
		if (form.getIdQuadra() != null && !form.getIdQuadra().trim().equals("")
				&& (form.getIdSetorComercial() != null && !form.getIdSetorComercial().toString().trim().equalsIgnoreCase(""))
				&& (form.getIdLocalidade() != null && !form.getIdLocalidade().toString().trim().equalsIgnoreCase(""))) {
			
			FiltroQuadra filtro = new FiltroQuadra();
			
			if (form.getIdLocalidade() != null && !form.getIdLocalidade().equalsIgnoreCase("")) {
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidade())));
			}
			
			if (form.getIdSetorComercial() != null && !form.getIdSetorComercial().equalsIgnoreCase("")) {
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercial())));
			}
			
			filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, new Integer(form.getIdQuadra())));
			
			Collection quadras = getFachada().pesquisar(filtro, Quadra.class.getName());
			
			if (quadras != null && !quadras.isEmpty()) {
				form.setIdQuadra("" + ((Quadra) ((List) quadras).get(0)).getNumeroQuadra());
				request.setAttribute("idQuadraNaoEncontrada", "true");
				request.setAttribute("nomeCampo", "loteFiltro");
			} else {
				form.setIdQuadra("");
				request.setAttribute("codigoQuadraNaoEncontrada", "true");
				request.setAttribute("idQuadraNaoEncontrada", "exception");
				request.setAttribute("msgQuadra", "QUADRA INEXISTENTE");
				request.setAttribute("nomeCampo", "idQuadraFiltro");
			}
		}
	}
}

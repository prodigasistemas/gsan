package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.DescriptorEntity;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

public class ExibirFiltrarAlteracaoAtualizacaoCadastralAction extends GcomAction {

	private static Logger logger = Logger.getLogger(ExibirFiltrarAlteracaoAtualizacaoCadastralAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;

		try {
			if (StringUtils.isNotEmpty(request.getParameter("filtroMatricula")))
				preencherInscricaoImovel(form, request);
			
			if (StringUtils.isNotEmpty(request.getParameter("filterClass")))
				preencherCampoDescricao(form, request);

			HttpSession sessao = request.getSession(false);

			carregarComboEmpresas(sessao);
			carregarComboLeiturista(form, sessao);
			carregarColunaImoveis(sessao, request, form);
			
		} catch (Exception e) {
			logger.error("Erro ao filtrar Cadastro", e);
		}

		return mapping.findForward("exibirFiltrarAlteracaoAtualizacaoCadastral");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Collection carregarImoveisSelecionados(FiltrarAlteracaoAtualizacaoCadastralActionForm form, HttpSession sessao) {
		Collection colecao = null;
		
		if (form.getColunaImoveisSelecionados() != null) {
			String[] aux = form.getColunaImoveisSelecionados();

			colecao = Arrays.asList(aux);

			Filtro filtro = new FiltroTabelaColuna();
			filtro.adicionarParametro(new ParametroSimplesIn(FiltroTabelaColuna.ID, colecao));
			filtro.setCampoOrderBy(FiltroTabelaColuna.DESCRICAO_COLUNA);

			colecao = Fachada.getInstancia().pesquisar(filtro, TabelaColuna.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				sessao.setAttribute("colecaoColunaImoveisSelecionados", colecao);
				sessao.setAttribute("existeColecaoColunaImoveisSelecionados", colecao);
			}
		}
		
		return colecao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarComboLeiturista(FiltrarAlteracaoAtualizacaoCadastralActionForm form, HttpSession sessao) {
		Collection colecao = new ArrayList();
		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1") && !form.getIdEmpresa().equals("")) {
			Filtro filtro = new FiltroLeiturista(FiltroLeiturista.FUNCIONARIO_NOME);
			filtro.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, form.getIdEmpresa()));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);

			Collection pesquisa = getFachada().pesquisar(filtro, Leiturista.class.getName());

			if (pesquisa != null && !pesquisa.isEmpty()) {
				Iterator it = pesquisa.iterator();
				while (it.hasNext()) {
					Leiturista leiturista = (Leiturista) it.next();
					DadosLeiturista dadosLeiturista = new DadosLeiturista(leiturista);
					colecao.add(dadosLeiturista);
				}
			}
		}

		sessao.setAttribute("colecaoLeiturista", colecao);
	}

	@SuppressWarnings("unchecked")
	private void carregarComboEmpresas(HttpSession sessao) {
		if (sessao.getAttribute("colecaoEmpresa") == null) {
			Filtro filtro = new FiltroEmpresa();
			filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

			Collection<Empresa> colecao = getFachada().pesquisar(filtro, Empresa.class.getName());

			if ((colecao == null) || (colecao.size() == 0)) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
			} else {
				sessao.setAttribute("colecaoEmpresa", colecao);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void carregarColunaImoveis(HttpSession sessao, HttpServletRequest request, FiltrarAlteracaoAtualizacaoCadastralActionForm form) {
		Collection colecaoColunaImoveisSelecionados = carregarImoveisSelecionados(form, sessao);

		Filtro filtro = new FiltroTabelaColuna();

		filtro.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.INDICADOR_ATUALIZACAO_CADASRAL, ConstantesSistema.SIM));
		filtro.setConsultaSemLimites(true);
		filtro.setCampoOrderBy(FiltroTabelaColuna.DESCRICAO_COLUNA);

		Collection colecaoColunaImoveis = Fachada.getInstancia().pesquisar(filtro, TabelaColuna.class.getName());

		if (colecaoColunaImoveisSelecionados == null) {
			sessao.setAttribute("colecaoColunaImoveis", colecaoColunaImoveis);
		} else {
			removerImoveisSelecionados(colecaoColunaImoveisSelecionados, colecaoColunaImoveis);
			sessao.setAttribute("colecaoColunaImoveis", colecaoColunaImoveis);
		}
	}

	@SuppressWarnings("rawtypes")
	private void removerImoveisSelecionados(Collection colecaoColunaImoveisSelecionados, Collection colecaoColunaImoveis) {
		for (Iterator iteratorColunaImoveis = colecaoColunaImoveis.iterator(); iteratorColunaImoveis.hasNext();) {

			TabelaColuna colunaImoveis = (TabelaColuna) iteratorColunaImoveis.next();
			for (Iterator iteratorColunaImoveisSelecionados = colecaoColunaImoveisSelecionados.iterator(); iteratorColunaImoveisSelecionados.hasNext();) {

				TabelaColuna colunaImoveisSelecionado = (TabelaColuna) iteratorColunaImoveisSelecionados.next();

				if (colunaImoveis.getId().compareTo(colunaImoveisSelecionado.getId()) == 0) {
					iteratorColunaImoveis.remove();
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void preencherInscricaoImovel(FiltrarAlteracaoAtualizacaoCadastralActionForm form, HttpServletRequest request) {
		FiltroImovel filtro = new FiltroImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatricula()));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA_ROTA);
		Collection pesquisa = Fachada.getInstancia().pesquisar(filtro, Imovel.class.getName());

		Imovel imovel = null;
		if (pesquisa != null && !pesquisa.isEmpty()) {
			imovel = (Imovel) Util.retonarObjetoDeColecao(pesquisa);
		}

		if (imovel == null) {
			form.setInscricao("Matrícula não encontrada");
			request.setAttribute("corInscricao", "#FF0000");
		} else {
			form.setInscricao(imovel.getInscricaoFormatada());
			request.setAttribute("corInscricao", "#000000");
		}
	}
	
	private void preencherCampoDescricao(FiltrarAlteracaoAtualizacaoCadastralActionForm form, HttpServletRequest request) throws Exception {
		String filterClass = "gcom.cadastro.localidade." + request.getParameter("filterClass");

		FilterClassParameters filter = null;
		String fieldName = null;
		
		if (filterClass.contains("FiltroLocalidade")) {
			fieldName = request.getParameter("fieldLocalidade");
			filter = buildFiltroLocalidade(form, filterClass, fieldName);
		} else if (filterClass.contains("FiltroSetorComercial")) {
			String fieldLocalidade = request.getParameter("fieldLocalidade");
			fieldName = request.getParameter("fieldSetorComercial");
			filter = buildFiltroSetorComercial(form, request, filterClass, fieldName, fieldLocalidade);
		}

		DescriptorEntity entidade = pesquisarEntidade(filter);

		Method setNome = FiltrarAlteracaoAtualizacaoCadastralActionForm.class.getMethod("setNome" + fieldName, String.class);
		if (entidade == null) {
			setNome.invoke(form, filter.getInvalidMessage());
			request.setAttribute("cor" + filter.getFieldName(), "#FF0000");
		} else {
			setNome.invoke(form, entidade.getDescricao());
			request.setAttribute("cor" + filter.getFieldName(), "#000000");
		}
	}

	private FilterClassParameters buildFiltroSetorComercial(FiltrarAlteracaoAtualizacaoCadastralActionForm form, HttpServletRequest request, String filterClass, String fieldName, String fieldLocalidade) throws Exception {
		Filtro filtro = (Filtro) Class.forName(filterClass).newInstance();
		FilterClassParameters filter;
		filter = new FilterClassParameters(filtro, new SetorComercial(), "Setor comercial inexistente", fieldName);
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, (String) recuperaValorCampo(form, "Cd"+ fieldName)));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, new Integer(recuperaValorCampo(form, "Id"+ fieldLocalidade))));
		request.setAttribute("cor" + fieldLocalidade, "#000000");
		return filter;
	}

	private FilterClassParameters buildFiltroLocalidade(FiltrarAlteracaoAtualizacaoCadastralActionForm form, String filterClass, String fieldLocalidade) throws Exception {
		Filtro filtro = (Filtro) Class.forName(filterClass).newInstance();
		FilterClassParameters filter;
		filter = new FilterClassParameters(filtro, new Localidade(), "Localidade inexistente", fieldLocalidade);
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, recuperaValorCampo(form, "Id" + fieldLocalidade)));
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		return filter;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DescriptorEntity pesquisarEntidade(FilterClassParameters parameters) {
		Collection pesquisa = Fachada.getInstancia().pesquisar(parameters.getFilter(), parameters.getEntity().getClass().getName());
		if (pesquisa == null || pesquisa.isEmpty()) {
			return null;
		} else {
			return (DescriptorEntity) Util.retonarObjetoDeColecao(pesquisa);
		}
	}
	
	private String recuperaValorCampo(FiltrarAlteracaoAtualizacaoCadastralActionForm form, String fieldName) throws Exception{
		Method getMethod = FiltrarAlteracaoAtualizacaoCadastralActionForm.class.getMethod("get" + fieldName);
		return (String) getMethod.invoke(form);
		
	}
}

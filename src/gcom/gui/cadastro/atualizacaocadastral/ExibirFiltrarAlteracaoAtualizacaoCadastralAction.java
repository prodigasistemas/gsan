package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
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
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		try {
			if (StringUtils.isNotEmpty(httpServletRequest.getParameter("filterClass"))){
				preencherCampoDescricao(form, httpServletRequest);
			}

			HttpSession sessao = httpServletRequest.getSession(false);

			carregarComboEmpresas(fachada, sessao);
			carregarComboLeiturista(form, fachada, sessao);
			carregarColunaImoveis(sessao, httpServletRequest, form);

		} catch (Exception e) {
			logger.error("Erro ao filtrar Cadastro", e);
		}

		return actionMapping.findForward("exibirFiltrarAlteracaoAtualizacaoCadastral");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Collection carregarImoveisSelecionados(FiltrarAlteracaoAtualizacaoCadastralActionForm form, HttpSession sessao) {
		Collection colecaoColunaImoveisSelecionados = null;
		if (form.getColunaImoveisSelecionados() != null) {

			String[] aux = form.getColunaImoveisSelecionados();

			colecaoColunaImoveisSelecionados = Arrays.asList(aux);

			FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
			filtroTabelaColuna.adicionarParametro(new ParametroSimplesIn(FiltroTabelaColuna.ID, colecaoColunaImoveisSelecionados));
			filtroTabelaColuna.setCampoOrderBy(FiltroTabelaColuna.DESCRICAO_COLUNA);

			colecaoColunaImoveisSelecionados = Fachada.getInstancia().pesquisar(filtroTabelaColuna, TabelaColuna.class.getName());

			if (colecaoColunaImoveisSelecionados != null && !colecaoColunaImoveisSelecionados.isEmpty()) {
				sessao.setAttribute("colecaoColunaImoveisSelecionados", colecaoColunaImoveisSelecionados);
				sessao.setAttribute("existeColecaoColunaImoveisSelecionados", colecaoColunaImoveisSelecionados);
			}
		}
		return colecaoColunaImoveisSelecionados;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarComboLeiturista(FiltrarAlteracaoAtualizacaoCadastralActionForm form, Fachada fachada, HttpSession sessao) {
		Collection colecaoLeiturista = new ArrayList();
		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1") && !form.getIdEmpresa().equals("")) {
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.FUNCIONARIO_NOME);
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, form.getIdEmpresa()));
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);

			Collection colecao = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				Iterator it = colecao.iterator();
				while (it.hasNext()) {
					Leiturista leiturista = (Leiturista) it.next();
					DadosLeiturista dadosLeiturista = new DadosLeiturista(leiturista);
					
					colecaoLeiturista.add(dadosLeiturista);
				}
			}

		}

		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
	}

	@SuppressWarnings("unchecked")
	private void carregarComboEmpresas(Fachada fachada, HttpSession sessao) {
		if (sessao.getAttribute("colecaoEmpresa") == null) {
			Collection<Empresa> colecaoEmpresa = null;

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

			colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());

			if ((colecaoEmpresa == null) || (colecaoEmpresa.size() == 0)) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
			} else {
				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void carregarColunaImoveis(HttpSession sessao, HttpServletRequest httpServletRequest, FiltrarAlteracaoAtualizacaoCadastralActionForm form) {
		
		Collection colecaoColunaImoveisSelecionados = carregarImoveisSelecionados(form, sessao);

		FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();

		filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.INDICADOR_ATUALIZACAO_CADASRAL, ConstantesSistema.SIM));
		filtroTabelaColuna.setConsultaSemLimites(true);
		filtroTabelaColuna.setCampoOrderBy(FiltroTabelaColuna.DESCRICAO_COLUNA);

		Collection colecaoColunaImoveis = Fachada.getInstancia().pesquisar(filtroTabelaColuna, TabelaColuna.class.getName());

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

	private void preencherCampoDescricao(FiltrarAlteracaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest)
			throws Exception {
		
		String filterClass = "gcom.cadastro.localidade."+ httpServletRequest.getParameter("filterClass");
		
		FilterClassParameters filter = null;
		String fieldName = null;
		String fieldLocalidade = httpServletRequest.getParameter("fieldLocalidade");
		if (filterClass.contains("FiltroLocalidade")){
			fieldName = fieldLocalidade;
			filter = buildFiltroLocalidade(form, filterClass, fieldLocalidade);			
		}else if (filterClass.contains("FiltroSetorComercial")){
			fieldName = httpServletRequest.getParameter("fieldSetorComercial");
			filter = buildFiltroSetorComercial(form, httpServletRequest, filterClass, fieldName, fieldLocalidade);
		}
		
		DescriptorEntity entidade = pesquisarEntidade(filter);

		Method setNome = FiltrarAlteracaoAtualizacaoCadastralActionForm.class.getMethod("setNome" + fieldName, String.class);
		if (entidade == null) {
			setNome.invoke(form, filter.getInvalidMessage());
			httpServletRequest.setAttribute("cor" + filter.getFieldName(), "#FF0000");
		} else {
			setNome.invoke(form, entidade.getDescricao());
			httpServletRequest.setAttribute("cor" + filter.getFieldName(), "#000000");
		}
	}

	private FilterClassParameters buildFiltroSetorComercial(FiltrarAlteracaoAtualizacaoCadastralActionForm form,
			HttpServletRequest httpServletRequest, String filterClass, String fieldName, String fieldLocalidade) throws Exception {
		Filtro filtro = (Filtro) Class.forName(filterClass).newInstance();
		FilterClassParameters filter;
		filter = new FilterClassParameters(filtro, new SetorComercial(), "Setor comercial inexistente", fieldName);
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, (String) recuperaValorCampo(form, "Cd"+ fieldName)));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, new Integer(recuperaValorCampo(form, "Id"+ fieldLocalidade))));
		httpServletRequest.setAttribute("cor" + fieldLocalidade, "#000000");
		return filter;
	}

	private FilterClassParameters buildFiltroLocalidade(FiltrarAlteracaoAtualizacaoCadastralActionForm form, String filterClass, String fieldLocalidade)
			throws Exception {
		Filtro filtro = (Filtro) Class.forName(filterClass).newInstance();
		FilterClassParameters filter;
		filter = new FilterClassParameters(filtro, new Localidade(), "Localidade inexistente", fieldLocalidade);
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, recuperaValorCampo(form, "Id" + fieldLocalidade)));
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		return filter;
	}

	private DescriptorEntity pesquisarEntidade(FilterClassParameters parameters) {
		Collection colecaoPesquisa = Fachada.getInstancia().pesquisar(parameters.getFilter(), parameters.getEntity().getClass().getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			return null;
		} else {
			return (DescriptorEntity) Util.retonarObjetoDeColecao(colecaoPesquisa);
		}
	}
	
	private String recuperaValorCampo(FiltrarAlteracaoAtualizacaoCadastralActionForm form, String fieldName) throws Exception{
		Method getMethod = FiltrarAlteracaoAtualizacaoCadastralActionForm.class.getMethod("get" + fieldName);
		return (String) getMethod.invoke(form);
		
	}
}

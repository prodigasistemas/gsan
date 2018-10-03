package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
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
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.DescriptorEntity;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
	
	private FiltrarAlteracaoAtualizacaoCadastralActionForm form;
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;

		try {
			if (StringUtils.isNotEmpty(request.getParameter("filtroMatricula")))
				preencherInscricaoImovel(request);
			
			if (StringUtils.isNotEmpty(request.getParameter("filterClass")))
				preencherCampoDescricao(request);

			HttpSession sessao = request.getSession(false);

			carregarComboEmpresas(sessao);
			carregarComboLeiturista(sessao);
			carregarComboOcorrencias(sessao);
			
		} catch (Exception e) {
			logger.error("Erro ao filtrar Cadastro", e);
		}

		return mapping.findForward("exibirFiltrarAlteracaoAtualizacaoCadastral");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarComboLeiturista(HttpSession sessao) {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void preencherInscricaoImovel(HttpServletRequest request) {
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
	
	private void preencherCampoDescricao(HttpServletRequest request) throws Exception {
		String filterClass = "gcom.cadastro.localidade." + request.getParameter("filterClass");

		FilterClassParameters filter = null;
		String fieldName = null;
		
		if (filterClass.contains("FiltroLocalidade")) {
			fieldName = request.getParameter("fieldLocalidade");
			filter = buildFiltroLocalidade(filterClass, fieldName);
		} else if (filterClass.contains("FiltroSetorComercial")) {
			String fieldLocalidade = request.getParameter("fieldLocalidade");
			fieldName = request.getParameter("fieldSetorComercial");
			filter = buildFiltroSetorComercial(request, filterClass, fieldName, fieldLocalidade);
		}

		DescriptorEntity entidade = pesquisarEntidade(filter);

		Method setNome = FiltrarAlteracaoAtualizacaoCadastralActionForm.class.getMethod("setNome" + fieldName, String.class);
		if (entidade == null) {
			setNome.invoke(filter.getInvalidMessage());
			request.setAttribute("cor" + filter.getFieldName(), "#FF0000");
		} else {
			setNome.invoke(entidade.getDescricao());
			request.setAttribute("cor" + filter.getFieldName(), "#000000");
		}
	}

	private FilterClassParameters buildFiltroSetorComercial(HttpServletRequest request, String filterClass, String fieldName, String fieldLocalidade) throws Exception {
		Filtro filtro = (Filtro) Class.forName(filterClass).newInstance();
		FilterClassParameters filter;
		filter = new FilterClassParameters(filtro, new SetorComercial(), "Setor comercial inexistente", fieldName);
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, (String) recuperaValorCampo("Cd"+ fieldName)));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, new Integer(recuperaValorCampo("Id"+ fieldLocalidade))));
		request.setAttribute("cor" + fieldLocalidade, "#000000");
		return filter;
	}

	private FilterClassParameters buildFiltroLocalidade(String filterClass, String fieldLocalidade) throws Exception {
		Filtro filtro = (Filtro) Class.forName(filterClass).newInstance();
		FilterClassParameters filter;
		filter = new FilterClassParameters(filtro, new Localidade(), "Localidade inexistente", fieldLocalidade);
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, recuperaValorCampo("Id" + fieldLocalidade)));
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
	
	private String recuperaValorCampo(String fieldName) throws Exception{
		Method getMethod = FiltrarAlteracaoAtualizacaoCadastralActionForm.class.getMethod("get" + fieldName);
		return (String) getMethod.invoke(form);
		
	}
	
	@SuppressWarnings("unchecked")
	private void carregarComboOcorrencias(HttpSession sessao) {
		if (StringUtils.isNotEmpty(form.getOcorrenciaCadastro()) && !form.getOcorrenciaCadastro().equals("-1")) {

			Filtro filtro = new FiltroCadastroOcorrencia(FiltroCadastroOcorrencia.ID);
			filtro.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.INDICADOR_VALIDACAO, form.getOcorrenciaCadastro()));

			Collection<CadastroOcorrencia> colecao = getFachada().pesquisar(filtro, CadastroOcorrencia.class.getName());
			sessao.setAttribute("colecaoCadastroOcorrencia", colecao);
			form.setOcorrenciaCadastroSelecionada("-1");
		} else {
			sessao.setAttribute("colecaoCadastroOcorrencia", null);
		}
	} 
}

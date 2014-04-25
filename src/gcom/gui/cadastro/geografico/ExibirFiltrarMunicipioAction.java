package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0006] FILTRAR MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 03/01/2007
 */

public class ExibirFiltrarMunicipioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarMunicipio");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		if (sessao.getAttribute("consulta") != null) {
			sessao.removeAttribute("consulta");
		}

		FiltrarMunicipioActionForm form = (FiltrarMunicipioActionForm) actionForm;

		// Código para checar ou não o ATUALIZAR

		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setIndicadorUso("3");
			form.setOrdernarPor("1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
					.toString());

		}

		// Verificar a existencia de dados

		// REGIAO DESENVOLVIMENTO

		FiltroRegiaoDesenvolvimento filtroRegiaoDesenv = new FiltroRegiaoDesenvolvimento();

		filtroRegiaoDesenv
				.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);

		Collection<RegiaoDesenvolvimento> colecaoRegiaoDesenv = fachada
				.pesquisar(filtroRegiaoDesenv, RegiaoDesenvolvimento.class
						.getName());

		if (colecaoRegiaoDesenv == null || colecaoRegiaoDesenv.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Região Desenvolvimento");
		}

		httpServletRequest.setAttribute("colecaoRegiaoDesenv",
				colecaoRegiaoDesenv);

		// REGIAO

		if (httpServletRequest.getParameter("carregarRegioes") != null) {
			
			Collection<Regiao> regioes = new ArrayList();

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.UNIDADE_FEDERACAO_ID, form.getUnidadeFederacao()));
			
			filtroMunicipio
			.adicionarCaminhoParaCarregamentoEntidade(FiltroMunicipio.MICRORREGICAO);
			
			filtroMunicipio
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMunicipio.REGIAO);

			Collection colecaoMunicipios = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipios != null && !colecaoMunicipios.isEmpty()) {
				for (Iterator iter = colecaoMunicipios.iterator(); iter
						.hasNext();) {
					Municipio element = (Municipio) iter.next();
					if (!regioes.contains(element
							.getMicrorregiao().getRegiao())) {
						regioes.add(element.getMicrorregiao().getRegiao());
					}

				}
			}

			httpServletRequest.setAttribute("colecaoRegiao",
					regioes);
			
		} else {

			FiltroRegiao filtroRegiao = new FiltroRegiao();

			filtroRegiao.setCampoOrderBy(FiltroRegiao.DESCRICAO);

			Collection<Regiao> colecaoRegiao = fachada.pesquisar(filtroRegiao,
					Regiao.class.getName());

			if (colecaoRegiao == null || colecaoRegiao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Região");
			}
			
			httpServletRequest.setAttribute("colecaoRegiao", colecaoRegiao);
		}

		

		// MICRORREGIAO

		Collection<Microrregiao> colecaoMicrorregiao = new ArrayList();

		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

		if (httpServletRequest.getParameter("combo") != null
				&& !form.getRegiao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			filtroMicrorregiao.adicionarParametro(new ParametroSimples(
					FiltroMicrorregiao.REGIAO_ID, form.getRegiao()));

			filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);

			colecaoMicrorregiao = fachada.pesquisar(filtroMicrorregiao,
					Microrregiao.class.getName());

			if (colecaoMicrorregiao == null || colecaoMicrorregiao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Microrregião");
			}

			httpServletRequest.setAttribute("colecaoMicrorregiao",
					colecaoMicrorregiao);
		}

		// UNIDADE FEDERAÇÃO

		if (httpServletRequest.getParameter("combo") != null
				&& !form.getRegiao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			Collection<UnidadeFederacao> unidadesFederacao = new ArrayList();

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.REGIAO_ID, form.getRegiao()));

			filtroMunicipio
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMunicipio.UNIDADE_FEDERACAO);

			Collection colecaoMunicipios = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipios != null && !colecaoMunicipios.isEmpty()) {
				for (Iterator iter = colecaoMunicipios.iterator(); iter
						.hasNext();) {
					Municipio element = (Municipio) iter.next();
					if (!unidadesFederacao.contains(element
							.getUnidadeFederacao())) {
						unidadesFederacao.add(element.getUnidadeFederacao());
					}

				}
			}

			httpServletRequest.setAttribute("colecaoUnidadeFederacao",
					unidadesFederacao);

		} else {

			FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();

			filtroUnidadeFederacao
					.setCampoOrderBy(FiltroUnidadeFederacao.DESCRICAO);

			Collection<UnidadeFederacao> colecaoUnidadeFederacao = fachada
					.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class
							.getName());

			if (colecaoUnidadeFederacao == null
					|| colecaoUnidadeFederacao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Unidade Federação");
			}

			httpServletRequest.setAttribute("colecaoUnidadeFederacao",
					colecaoUnidadeFederacao);

		}

		// Se voltou da tela de Atualizar Localidade
		if (sessao.getAttribute("voltar") != null
				&& sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if (sessao.getAttribute("tipoPesquisa") != null) {
				form.setTipoPesquisa(sessao.getAttribute("tipoPesquisa")
						.toString());
			}
		}
		sessao.removeAttribute("voltar");
		sessao.removeAttribute("tipoPesquisa");

		return retorno;
	}
}

package gcom.gui.cadastro;

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
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
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

public class ExibirConsultarArquivoTextoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessao = request.getSession(false);

		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		String objetoConsulta = (String) request.getParameter("objetoConsulta");
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") && (objetoConsulta.trim().equals("1"))) {
			pesquisarLocalidade(form, request);
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") && (objetoConsulta.trim().equals("2"))) {
			pesquisarSetorComercial(form, request);
		}

		pesquisarEmpresa(sessao);

		sessao.removeAttribute("permissao");
		if (getUsuarioLogado(request).getEmpresa().getIndicadorEmpresaPrincipal().equals(new Short("1"))) {
			sessao.setAttribute("permissao", "1");
		} else {
			sessao.setAttribute("permissao", "2");
		}

		if (request.getParameter("menu") != null) {
			form.setIdEmpresa("" + getUsuarioLogado(request).getEmpresa().getId());
		}

		pesquisarLeituristas(sessao, form);

		return mapping.findForward("consultarArquivoTextoAtualizacaoCadastral");

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pesquisarLeituristas(HttpSession sessao, ConsultarArquivoTextoAtualizacaoCadastralActionForm form) {
		Collection colecaoLeiturista = new ArrayList();

		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1") && !form.getIdEmpresa().equals("")) {

			Filtro filtro = new FiltroLeiturista(FiltroLeiturista.FUNCIONARIO_NOME);
			filtro.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, form.getIdEmpresa()));
			filtro.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);

			Collection colecao = getFachada().pesquisar(filtro, Leiturista.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				Iterator iterator = colecao.iterator();
				while (iterator.hasNext()) {

					Leiturista leiturista = (Leiturista) iterator.next();
					DadosLeiturista dadosLeiturista = null;

					if (leiturista.getFuncionario() != null) {
						dadosLeiturista = new DadosLeiturista(leiturista.getId(), leiturista.getFuncionario().getNome());
					} else {
						dadosLeiturista = new DadosLeiturista(leiturista.getId(), leiturista.getCliente().getNome());
					}

					colecaoLeiturista.add(dadosLeiturista);
				}
			}
		}

		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
	}

	@SuppressWarnings("rawtypes")
	private void pesquisarEmpresa(HttpSession sessao) {
		Filtro filtro = new FiltroEmpresa();
		filtro.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		Collection colecao = getFachada().pesquisar(filtro, Empresa.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			sessao.setAttribute("colecaoEmpresa", colecao);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pesquisarLocalidade(ConsultarArquivoTextoAtualizacaoCadastralActionForm form, HttpServletRequest request) {
		Filtro filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, (String) form.getIdLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecao = Fachada.getInstancia().pesquisar(filtro, Localidade.class.getName());

		if (colecao == null || colecao.isEmpty()) {
			form.setIdLocalidade("");
			form.setNomeLocalidade("Localidade inexistente");

			request.setAttribute("corLocalidadeOrigem", "exception");
			request.setAttribute("nomeCampo", "localidadeInicial");

		} else {
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecao);
			form.setIdLocalidade(String.valueOf(objetoLocalidade.getId()));
			form.setNomeLocalidade(objetoLocalidade.getDescricao());

			request.setAttribute("corLocalidadeOrigem", "valor");
			request.setAttribute("nomeCampo", "nomeLocalidadeInicial");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pesquisarSetorComercial(ConsultarArquivoTextoAtualizacaoCadastralActionForm form, HttpServletRequest request) {
		Filtro filtro = new FiltroSetorComercial();
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE, form.getIdLocalidade()));

		Collection colecao = Fachada.getInstancia().pesquisar(filtro, SetorComercial.class.getName());

		if (colecao == null || colecao.isEmpty()) {
			form.setCodigoSetorComercial("");
			form.setNomeSetorComercial("Setor inexistente");

			request.setAttribute("corSetorComercialOrigem", "exception");
			request.setAttribute("nomeCampo", "nomeSetorComercialInicial");
		} else {
			SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecao);
			form.setCodigoSetorComercial(String.valueOf(objetoSetorComercial.getCodigo()));
			form.setNomeSetorComercial(objetoSetorComercial.getDescricao());

			request.setAttribute("corSetorComercialOrigem", "valor");
			request.setAttribute("nomeCampo", "nomeSetorComercialInicial");
		}
	}
}

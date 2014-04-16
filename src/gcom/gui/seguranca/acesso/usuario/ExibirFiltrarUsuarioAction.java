package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarUsuarioAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// FachadaBatch.getInstancia().gerarResumoSituacaoEspecialFaturamento();

		ActionForward retorno = actionMapping.findForward("filtrarUsuario");

		FiltrarUsuarioActionForm form = (FiltrarUsuarioActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// caso venha da tela atualizar sem ter passado pelo filtro
		// (no caso vim da tela de sucesso do inserir)
		if (sessao.getAttribute("indicadorAtualizar") == null) {
			sessao.setAttribute("indicadorAtualizar", "1");
		}
	
		
		String limpar = (String) httpServletRequest.getParameter("limpar");
		if (limpar != null && !limpar.equals("")) {

			form.setParmsUsuarioTipo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			form.setUsuarioTipo("");
			form.setEmpresa("");
			form.setIdFuncionario("");
			form.setNomeFuncionario("");
			form.setNome("");
			form.setFuncionarioNaoEncontrada("false");
			form.setIdLotacao("");
			form.setNomeLotacao("");
			form.setLotacaoNaoEncontrada("false");
			form.setDataCadastramentoInicial("");
			form.setDataCadastramentoFinal("");
			form.setDataExpiracaoInicial("");
			form.setDataExpiracaoFinal("");
			form.setGrupo(null);
			form.setAbrangencia("");
			form.setEloNaoEncontrada("false");
			form.setGerenciaRegional("");
			form.setIdElo("");
			form.setNomeElo("");
			form.setLocalidadeNaoEncontrada("false");
			form.setIdLocalidade("");
			form.setNomeLocalidade("");
			form.setIdEmpresaFuncionario("");
			form.setDataCadastramentoInicial("");
			form.setDataNascimento("");
			form.setCpf("");
			sessao.setAttribute("indicadorAtualizar", "1");
			httpServletRequest.setAttribute("nomeCampo", "usuarioTipo");
			form.setUsuarioSituacao("" + ConstantesSistema.NUMERO_NAO_INFORMADO);

		}
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			httpServletRequest.setAttribute("nomeCampo", "usuarioTipo");

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoEmpresa = Fachada.getInstancia().pesquisar(
					filtroEmpresa, Empresa.class.getSimpleName());
			if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						" Empresa está ");
			}
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);

			FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();

			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUsuarioTipo = Fachada.getInstancia().pesquisar(
					filtroUsuarioTipo, UsuarioTipo.class.getSimpleName());
			if (colecaoUsuarioTipo == null || colecaoUsuarioTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						" Tipo do Usúario está ");
			}
			sessao.setAttribute("colecaoUsuarioTipo", colecaoUsuarioTipo);

			FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();

			filtroUsuarioAbrangencia.adicionarParametro(new ParametroSimples(
					FiltroUsuarioAbrangencia.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUsuarioAbrangencia = Fachada.getInstancia()
					.pesquisar(filtroUsuarioAbrangencia,
							UsuarioAbrangencia.class.getSimpleName());
			sessao.setAttribute("colecaoUsuarioAbrangencia",
					colecaoUsuarioAbrangencia);

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoGerenciaRegional = Fachada.getInstancia()
					.pesquisar(filtroGerenciaRegional,
							GerenciaRegional.class.getSimpleName());
			sessao
					.setAttribute("colecaoGerenciaRegional",
							colecaoGerenciaRegional);

			FiltroGrupo filtroGrupo = new FiltroGrupo();
			filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
			filtroGrupo.adicionarParametro(new ParametroSimples(
					FiltroGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoGrupo = Fachada.getInstancia().pesquisar(
					filtroGrupo, Grupo.class.getSimpleName());
			sessao.setAttribute("colecaoGrupo", colecaoGrupo);

			Collection colecaoUsuarioSituacao = Fachada.getInstancia()
					.pesquisar(new FiltroUsuarioSituacao(),
							UsuarioSituacao.class.getSimpleName());
			sessao.setAttribute("colecaoUsuarioSituacao", colecaoUsuarioSituacao);

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = Fachada.getInstancia()
					.pesquisar(filtroUnidadeNegocio,
							UnidadeNegocio.class.getSimpleName());
			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

		}

		if (form.getIdFuncionario() != null
				&& !form.getIdFuncionario().equals("")) {
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, form.getIdFuncionario()));
			filtroFuncionario
					.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
			Collection coll = Fachada.getInstancia().pesquisar(
					filtroFuncionario, Funcionario.class.getSimpleName());
			if (coll != null && !coll.isEmpty()) {
				Funcionario f = (Funcionario) coll.iterator().next();
				form.setIdFuncionario(f.getId().toString());
				form.setNomeFuncionario(f.getNome());
				form.setNome(f.getNome());
				form.setCpf(f.getNumeroCpf());
				form.setDataNascimento(Util.formatarData(f.getDataNascimento()));
				form.setFuncionarioNaoEncontrada("false");
			} else {
				form.setIdFuncionario("");
				form.setNomeFuncionario("Funcionario inexistente.");
				form.setFuncionarioNaoEncontrada("true");
			}
		}

		if (form.getIdLotacao() != null && !form.getIdLotacao().equals("")) {

			FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));
			Collection colecaoLotacao = Fachada.getInstancia().pesquisar(
					filtroUnidadeEmpresa,
					UnidadeOrganizacional.class.getSimpleName());
			if (colecaoLotacao != null && !colecaoLotacao.isEmpty()) {
				UnidadeOrganizacional unidadeEmpresa = (UnidadeOrganizacional) colecaoLotacao
						.iterator().next();
				form.setIdLotacao(unidadeEmpresa.getId().toString());
				form.setNomeLotacao(unidadeEmpresa.getDescricao());
				form.setLotacaoNaoEncontrada("false");
			} else {
				form.setIdLotacao("");
				form.setNomeLotacao("Lotação inexistente.");
				form.setLotacaoNaoEncontrada("true");
			}
		}

		if (form.getIdElo() != null && !form.getIdElo().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, form.getIdElo()));
			Collection colecaoElo = Fachada.getInstancia().pesquisar(
					filtroLocalidade, Localidade.class.getSimpleName());
			if (colecaoElo != null && !colecaoElo.isEmpty()) {
				Localidade l = (Localidade) colecaoElo.iterator().next();
				if (l.getLocalidade() != null
						&& !l.getId().equals(l.getLocalidade().getId())) {
					throw new ActionServletException(
							"atencao.localidade.nao.elo");
				}
				form.setIdElo(l.getId().toString());
				form.setNomeElo(l.getDescricao());
				form.setEloNaoEncontrada("false");
			} else {
				form.setIdElo("");
				form.setNomeElo("Elo inexistente.");
				form.setEloNaoEncontrada("true");
			}
		}

		if (form.getIdLocalidade() != null
				&& !form.getIdLocalidade().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, form.getIdLocalidade()));

			Collection colecaoLocalidade = Fachada.getInstancia().pesquisar(
					filtroLocalidade, Localidade.class.getSimpleName());
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade l = (Localidade) colecaoLocalidade.iterator().next();
				form.setIdLocalidade(l.getId().toString());
				form.setIdElo("" + l.getLocalidade().getId());
				form.setIdElo(l.getLocalidade().getDescricao());
				form.setNomeLocalidade(l.getDescricao());
				form.setLocalidadeNaoEncontrada("false");
			} else {
				form.setIdLocalidade("");
				form.setNomeLocalidade(" Localidade inexistente.");
				form.setLocalidadeNaoEncontrada("true");
			}
		}

		sessao.getAttribute("indicadorAtualizar");
		return retorno;
	}
}

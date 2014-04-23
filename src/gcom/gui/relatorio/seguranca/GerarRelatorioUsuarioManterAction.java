package gcom.gui.relatorio.seguranca;

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
import gcom.gui.seguranca.acesso.usuario.FiltrarUsuarioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.seguranca.RelatorioManterUsuario;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Arthur Carvalho
 * @created 09/04/2008
 */
public class GerarRelatorioUsuarioManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarUsuarioActionForm filtrarUsuarioActionForm = (FiltrarUsuarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroUsuarioGrupo filtroUsuarioGrupo = (FiltroUsuarioGrupo) sessao
				.getAttribute("filtroUsuarioGrupo");

		// Inicio da parte que vai mandar os parametros para o relatório
		
		// Tipo do Usuário
		String idTipoUsuario = filtrarUsuarioActionForm.getUsuarioTipo();

		UsuarioTipo usuarioTipo = null;

		if (idTipoUsuario != null
				&& !idTipoUsuario.equals("")) {

			FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();

			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.ID, idTipoUsuario));

			Collection colecaoTiposUsuario = fachada.pesquisar(
					filtroUsuarioTipo, UsuarioTipo.class.getName());

			if (colecaoTiposUsuario != null && !colecaoTiposUsuario.isEmpty()) {
				// O tipo do usuário foi encontrado
				usuarioTipo = (UsuarioTipo) Util
						.retonarObjetoDeColecao(colecaoTiposUsuario);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Tipo Usuário");
			}

		}

		// Empresa
		String idEmpresa = filtrarUsuarioActionForm.getEmpresa();
		
		Empresa empresa = null;

		if (idEmpresa != null
				&& !idEmpresa.equals("")) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {

				empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Empresa");

			}

		}
		
		// Funcionário
		String idFuncionario = filtrarUsuarioActionForm.getIdFuncionario();

		Funcionario funcionario = null;

		if (idFuncionario != null && !idFuncionario.trim().equals("")) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idFuncionario));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				funcionario = (Funcionario) Util
						.retonarObjetoDeColecao(colecaoFuncionario);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Funcionário");
			}

		}
		
		// Unidade de Lotação
		String idUnidadeLotacao = filtrarUsuarioActionForm.getIdLotacao();

		UnidadeOrganizacional unidadeLotacao = null;

		if (idUnidadeLotacao != null && !idUnidadeLotacao.trim().equals("")) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidadeLotacao));

			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidadeOrganizacional != null
					&& !colecaoUnidadeOrganizacional.isEmpty()) {

				unidadeLotacao = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Unidade Lotação");
			}
		}

		// Situação do Usuário
		String idUsuarioSituacao = filtrarUsuarioActionForm
				.getUsuarioSituacao();

		UsuarioSituacao usuarioSituacao = null;

		if (idUsuarioSituacao != null && !idUsuarioSituacao.equals("")) {

			FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();

			filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(
					FiltroUsuarioSituacao.ID, idUsuarioSituacao));

			Collection colecaoUsuarioSituacao = fachada.pesquisar(
					filtroUsuarioSituacao, UsuarioSituacao.class.getName());

			if (colecaoUsuarioSituacao != null
					&& !colecaoUsuarioSituacao.isEmpty()) {
				usuarioSituacao = (UsuarioSituacao) Util
						.retonarObjetoDeColecao(colecaoUsuarioSituacao);

			} else {
				throw new ActionServletException("atencao._inexistente", null,
						"Situação do Usuário");
			}

		}

		// Abrangência do Acesso
		String idAbrangencia = filtrarUsuarioActionForm.getAbrangencia();

		UsuarioAbrangencia usuarioAbrangencia = null;

		if (idAbrangencia != null && !idAbrangencia.equals("")) {

			FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();

			filtroUsuarioAbrangencia.adicionarParametro(new ParametroSimples(
					FiltroUsuarioAbrangencia.ID, idAbrangencia));

			Collection colecaoUsuarioAbrangencia = fachada.pesquisar(
					filtroUsuarioAbrangencia, UsuarioAbrangencia.class
							.getName());

			if (colecaoUsuarioAbrangencia != null
					&& !colecaoUsuarioAbrangencia.isEmpty()) {

				usuarioAbrangencia = (UsuarioAbrangencia) Util
						.retonarObjetoDeColecao(colecaoUsuarioAbrangencia);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Abrangência Acesso");
			}
		}
		

		// Gerência Regional
		String idGerenciaRegional = filtrarUsuarioActionForm
				.getGerenciaRegional();

		GerenciaRegional gerenciaRegional = null;

		if (idGerenciaRegional != null && !idGerenciaRegional.equals("")) {

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			Collection colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null
					&& !colecaoGerenciaRegional.isEmpty()) {
				gerenciaRegional = (GerenciaRegional) Util
						.retonarObjetoDeColecao(colecaoGerenciaRegional);

			} else {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Gerência Regional");
			}

		}

		// Unidade de Negócio
		String idUnidadeNegocio = filtrarUsuarioActionForm.getUnidadeNegocio();

		UnidadeNegocio unidadeNegocio = null;

		if (idUnidadeNegocio != null && !idUnidadeNegocio.equals("")) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, idUnidadeNegocio));

			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				unidadeNegocio = (UnidadeNegocio) Util
						.retonarObjetoDeColecao(colecaoUnidadeNegocio);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Unidade Negócio");
			}
		}

		// Elo Pólo
		String idElo = filtrarUsuarioActionForm.getIdElo();

		Localidade elo = null;

		if (idElo != null && !idElo.trim().equals("")) {

			FiltroLocalidade filtroElo = new FiltroLocalidade();

			filtroElo.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idElo));
			filtroElo.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID_ELO, idElo));

			Collection colecaoElo = fachada.pesquisar(filtroElo,
					Localidade.class.getName());

			if (colecaoElo != null && !colecaoElo.isEmpty()) {
				elo = (Localidade) Util.retonarObjetoDeColecao(colecaoElo);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Elo Pólo");
			}
		}
		
		// Localidade
		String idLocalidade = filtrarUsuarioActionForm.getIdLocalidade();

		Localidade localidade = null;

		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");
			}
		}

		// Grupo
		String[] idsGrupos = filtrarUsuarioActionForm.getGrupo();
		
		Collection<Grupo> colecaoGruposParametro = new ArrayList<Grupo>();

		if (idsGrupos != null) {
			
			for (int i = 0; i < idsGrupos.length; i++) {
				
				if (!idsGrupos[i].equals("")) {
					FiltroGrupo filtroGrupo = new FiltroGrupo();
					filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID, idsGrupos[i]));
					
					Collection colecaoGrupo = fachada.pesquisar(filtroGrupo, Grupo.class.getName());
					
					if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {
						Grupo grupo = (Grupo) Util.retonarObjetoDeColecao(colecaoGrupo);
						colecaoGruposParametro.add(grupo);
					}
				}
			}
				
		}
		
		// Período Cadastramento Acesso
		String periodoCadastramentoInicial = filtrarUsuarioActionForm.getDataCadastramentoInicial();
		String periodoCadastramentoFinal = filtrarUsuarioActionForm.getDataCadastramentoFinal();
		
		Date dataCadastramentoInicial = null;
		Date dataCadastramentoFinal = null;
		
		if (periodoCadastramentoInicial != null && !periodoCadastramentoInicial.trim().equals("")) {
			dataCadastramentoInicial = Util.converteStringParaDate(periodoCadastramentoInicial);
		}
		
		if (periodoCadastramentoFinal != null && !periodoCadastramentoFinal.trim().equals("")) {
			dataCadastramentoFinal = Util.converteStringParaDate(periodoCadastramentoFinal);
		}

		// Período Expiração
		String periodoExpiracaoInicial = filtrarUsuarioActionForm.getDataExpiracaoInicial();
		String periodoExpiracaoFinal = filtrarUsuarioActionForm.getDataExpiracaoFinal();
		
		Date dataExpiracaoInicial = null;
		Date dataExpiracaoFinal = null;
		
		if (periodoExpiracaoInicial != null && !periodoExpiracaoInicial.trim().equals("")) {
			dataExpiracaoInicial = Util.converteStringParaDate(periodoExpiracaoInicial);
		}
		
		if (periodoExpiracaoFinal != null && !periodoExpiracaoFinal.trim().equals("")) {
			dataExpiracaoFinal = Util.converteStringParaDate(periodoExpiracaoFinal);
		}

		// seta os parametros que serão mostrados no relatório

		// Fim da parte que vai mandar os parametros para o relatório

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioManterUsuario relatorio = new RelatorioManterUsuario(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorio.addParametro("filtroUsuarioGrupo", filtroUsuarioGrupo);
		relatorio.addParametro("usuarioTipo", usuarioTipo);
		relatorio.addParametro("empresa", empresa);
		relatorio.addParametro("funcionario", funcionario);
		relatorio.addParametro("nomeUsuario", filtrarUsuarioActionForm.getNome());
		relatorio.addParametro("unidadeLotacao", unidadeLotacao);
		relatorio.addParametro("usuarioSituacao", usuarioSituacao);
		relatorio.addParametro("login", filtrarUsuarioActionForm.getLoginUsuario());
		relatorio.addParametro("usuarioAbrangencia", usuarioAbrangencia);
		relatorio.addParametro("gerenciaRegional", gerenciaRegional);
		relatorio.addParametro("unidadeNegocio", unidadeNegocio);
		relatorio.addParametro("elo", elo);
		relatorio.addParametro("localidade", localidade);
		relatorio.addParametro("colecaoGruposParametro", colecaoGruposParametro);
		relatorio.addParametro("dataCadastramentoInicial", dataCadastramentoInicial);
		relatorio.addParametro("dataCadastramentoFinal", dataCadastramentoFinal);
		relatorio.addParametro("dataExpiracaoInicial", dataExpiracaoInicial);
		relatorio.addParametro("dataExpiracaoFinal", dataExpiracaoFinal);
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}

package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.endereco.bean.AtualizarLogradouroCepHelper;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela atualização de um logradouro na base
 * 
 * @author Sávio Luiz
 */
public class AtualizarLogradouroAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		LogradouroActionForm logradouroActionForm = (LogradouroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Recupera a variável para indicar se o usuário apertou o botão de
		// confirmar da tela de
		// confirmação do wizard
		String confirmado = httpServletRequest.getParameter("confirmado");

		// ===========================================================================

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LOGRADOURO_ATUALIZAR,
				// new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
				// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO),
				// new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
				// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO),
				// new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
				// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO),
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		// // [UC0107] Registrar Transação
		// Operacao operacao = new Operacao();
		// operacao.setId(Operacao.OPERACAO_LOGRADOURO_ATUALIZAR);
		//
		// OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		// operacaoEfetuada.setOperacao(operacao);

		// ==========================================================================

		Logradouro logradouro = (Logradouro) sessao.getAttribute("logradouro");

		Municipio municipio = null;

		String idMunicipio = (String) logradouroActionForm.getIdMunicipio();
		// String codigoBairro = (String)
		// logradouroActionForm.getCodigoBairro();

		if (idMunicipio != null && !idMunicipio.trim().equals("")
				&& Integer.parseInt(idMunicipio) > 0) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				municipio = ((Municipio) ((List) municipioEncontrado).get(0));

			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null, "município");
			}
		}

		logradouro.setMunicipio(municipio);

		LogradouroTipo logradouroTipo = new LogradouroTipo();

		if (logradouroActionForm.getIdTipo() != null
				&& !logradouroActionForm.getIdTipo().equals(0)) {

			logradouroTipo.setId(new Integer(""
					+ logradouroActionForm.getIdTipo()));

			logradouro.setLogradouroTipo(logradouroTipo);
		} else {
			throw new ActionServletException("atencao.required", null, "Tipo");
		}

		LogradouroTitulo logradouroTitulo = null;

		if (logradouroActionForm.getIdTitulo() != null) {

			logradouroTitulo = new LogradouroTitulo();
			if (!logradouroActionForm.getIdTitulo().equals(0)) {
				logradouroTitulo.setId(new Integer(""
						+ logradouroActionForm.getIdTitulo()));
			} else {
				logradouroTitulo = null;
			}

			logradouro.setLogradouroTitulo(logradouroTitulo);
		}

		Collection colecaoBairros = (Collection) sessao
				.getAttribute("colecaoBairrosSelecionadosUsuario");
		Collection colecaoCeps = (Collection) sessao
				.getAttribute("colecaoCepSelecionadosUsuario");

		if (colecaoBairros == null || colecaoBairros.isEmpty()) {
			throw new ActionServletException("atencao.required", null,
					"Bairro(s)");
		}

		if (colecaoCeps == null || colecaoCeps.isEmpty()) {
			throw new ActionServletException("atencao.required", null, "CEP(s)");
		}

		Short indicadorDeUso = new Short(logradouroActionForm.getIndicadorUso());

		logradouro.setIndicadorUso(indicadorDeUso);

		logradouro.setNome(logradouroActionForm.getNome());

		logradouro.setNomePopular(logradouroActionForm.getNomePopular());

		// ======================================================================

		// [UC0107] Registrar Transação
		// logradouro.setOperacaoEfetuada(operacaoEfetuada);
		// logradouro.adicionarUsuario(Usuario.USUARIO_TESTE,
		// UsuarioAcao.USUARIO_ACAO_TESTE);

		// ======================================================================

		registradorOperacao.registrarOperacao(logradouro);

		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

		// -------------Parte que atualiza um logradouro na
		// base----------------------
		filtroLogradouro.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroLogradouro.ID, logradouro.getLogradouroTipo().getId()));
		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID_LOGRADOUROTIPO, logradouro
						.getLogradouroTipo().getId()));
		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID_MUNICIPIO, logradouro.getMunicipio()
						.getId()));
		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.NOME, logradouro.getNome()));

		if (logradouro.getLogradouroTitulo() == null
				|| logradouro.getLogradouroTitulo().equals("")) {
			filtroLogradouro.adicionarParametro(new ParametroNulo(
					FiltroLogradouro.ID_LOGRADOUROTITULO));
		} else {
			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID_LOGRADOUROTITULO, logradouro
							.getLogradouroTitulo().getId()));
		}

		Collection logradouros = fachada.pesquisar(filtroLogradouro,
				Logradouro.class.getName());
		if (logradouros != null && !logradouros.isEmpty()) {
			Logradouro logradouroPesquisado = (Logradouro) ((List) logradouros)
					.get(0);
			if (!logradouro.getId().equals(logradouroPesquisado.getId())) {
				if (confirmado == null
						|| !confirmado.trim().equalsIgnoreCase("ok")) {
					httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/atualizarLogradouroAction.do");
					// Monta a página de confirmação do wizard para perguntar se
					// o usuário quer inserir
					// o pagamento mesmo sem a conta existir para a referência e
					// o imóvel informados
					return montarPaginaConfirmacao(
							"atencao.logradouro_ja_existente.confirmacao",
							httpServletRequest, actionMapping);
				}
			}
		}
		
		Collection colecaoAtualizarLogradouroBairroHelper = null;
		if (sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper") != null){
			colecaoAtualizarLogradouroBairroHelper = (Collection)
			sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper");
		}
		
		Collection colecaoAtualizarLogradouroCepHelper = null;
		if (sessao.getAttribute("colecaoAtualizarLogradouroCepHelper") != null){
			colecaoAtualizarLogradouroCepHelper = (Collection)
			sessao.getAttribute("colecaoAtualizarLogradouroCepHelper");
		}
		
		for (Object object : colecaoCeps) {
			Cep cep = (Cep) object;
			
			FiltroCep filtroCep = new FiltroCep();
			filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO, cep.getCodigo()));
			Iterator cepIterator =  fachada.pesquisar(filtroCep, Cep.class.getName()).iterator();
			Cep cepExistente = null;
			if (cepIterator.hasNext()) {
				cepExistente = (Cep) cepIterator.next(); 
			}
			
			if (cepExistente == null) {
				continue;
			}
			
			FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
			filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO,logradouro.getId()));
			filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_CEP, cepExistente.getCepId()));
			filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_DESATIVO));
			filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade("cep");
			Iterator logradouroCepIterator = fachada.pesquisar(filtroLogradouroCep, LogradouroCep.class.getName()).iterator();
			LogradouroCep logradouroCep = null;
			if (logradouroCepIterator.hasNext()) {
				logradouroCep = (LogradouroCep) logradouroCepIterator.next();
			}
			
			if (logradouroCep != null && sessao.getAttribute("ativarCepLogradouro") == null) {
				httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/atualizarLogradouroAction.do");
				httpServletRequest.setAttribute("cancelamento", "TRUE");
				httpServletRequest.setAttribute("nomeBotao1", "Sim");
				httpServletRequest.setAttribute("nomeBotao2", "Não");
				sessao.setAttribute("ativarCepLogradouro", "true");

				return montarPaginaConfirmacao("atencao.ativar_cep_logradouro", httpServletRequest, actionMapping, cepExistente.getCepFormatado());
			} else if (logradouroCep != null && sessao.getAttribute("ativarCepLogradouro").equals("true") && (confirmado != null && confirmado.equals("ok"))) {
				if (colecaoAtualizarLogradouroCepHelper == null) {
					colecaoAtualizarLogradouroCepHelper = new ArrayList();
				}
				AtualizarLogradouroCepHelper atualizarLogradouroCepHelper = new AtualizarLogradouroCepHelper();
				atualizarLogradouroCepHelper.setCep(cepExistente);
				atualizarLogradouroCepHelper.setLogradouroCep(logradouroCep);
				colecaoAtualizarLogradouroCepHelper.add(atualizarLogradouroCepHelper);
				sessao.setAttribute("ativarCepLogradouro", null);
			} else if (logradouroCep != null && sessao.getAttribute("ativarCepLogradouro").equals("true") && (confirmado != null && confirmado.equals("cancelar"))) {
				sessao.setAttribute("ativarCepLogradouro", null);
			}
		}
		
		fachada.atualizarLogradouro(logradouro, colecaoBairros, colecaoCeps, 
		colecaoAtualizarLogradouroBairroHelper, colecaoAtualizarLogradouroCepHelper);

		montarPaginaSucesso(httpServletRequest, "Logradouro de código "
				+ logradouro.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Logradouro",
				"exibirManterLogradouroAction.do");

		return retorno;
	}
}

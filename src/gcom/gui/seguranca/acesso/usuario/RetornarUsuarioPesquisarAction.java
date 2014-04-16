package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarUsuarioPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Inicializacoes de variaveis
		ActionForward retorno = actionMapping
				.findForward("retornoPesquisa");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Fachada fachada = Fachada.getInstancia();
		
		PesquisarUsuarioActionForm form = (PesquisarUsuarioActionForm) actionForm;
		
		String tipoUsuario = form.getTipoUsuario();
		String nomeUsuario = form.getNome();
		String matriculaFuncionario = form.getMatriculaFuncionario();
		String tipoPesquisa = (String) form.getTipoPesquisa();
		String login = form.getLogin();
		String idUnidadeOrganizacional = form.getIdUnidadeOrganizacional();
		
		//Matrícula mair q zero 
		if (matriculaFuncionario.equals("0")){
			throw new ActionServletException("atencao.matricula_usuario_maior_zero");
		}
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();		
		
		boolean peloMenosUm = false;
		
		//Pesquisa usuarioTipo
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioTipo");
		if(tipoUsuario != null && (new Integer(tipoUsuario).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.USUARIO_TIPO_ID, tipoUsuario));
			peloMenosUm = true;
		}
		
		//Pesquisa nomeUsuario		
		if(nomeUsuario != null && !nomeUsuario.equals("")){
			peloMenosUm = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroUsuario.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroUsuario.NOME_USUARIO, nomeUsuario));
			} else {
				filtroUsuario.adicionarParametro(new ComparacaoTexto(
						FiltroUsuario.NOME_USUARIO, nomeUsuario));
			}
			
		}
		
		if(login != null && !login.equals("")){
			peloMenosUm = true;
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, login));
		}
		
		//Pesquisa matriculaFuncionario
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		if(matriculaFuncionario != null && !matriculaFuncionario.equals("")){
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, matriculaFuncionario));
			peloMenosUm = true;
		}
		
		if(idUnidadeOrganizacional != null && !idUnidadeOrganizacional.equals("")){
			peloMenosUm = true;
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.UNIDADE_ORGANIZACIONAL_ID, idUnidadeOrganizacional));
		}
		
		if (!peloMenosUm){
			throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
		}
		
		//
		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroUsuario, Usuario.class.getName());
		Collection collectionUsuario = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Validações 
		if (collectionUsuario == null || collectionUsuario.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "usuario");
		} else {
			sessao.setAttribute("collectionUsuario", collectionUsuario);

		}		
		
		String popup = (String) sessao.getAttribute("popup");
		if (popup != null && popup.equals("2")) {
			sessao.setAttribute("popup", popup);
		} else {
			sessao.removeAttribute("popup");
		}
		
		return retorno;
	}

}

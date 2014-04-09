package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.FiltrarLeituristaActionForm;
import gcom.micromedicao.FiltroLeiturista;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 22/07/2007
 */
public class FiltrarLeituristaAction extends GcomAction {

	/**
	 * Este caso de uso permite Filtrar um Leiturista
	 * 
	 * [UC0590] Filtrar Leiturista
	 * 
	 * 
	 * @author Thiago Tenório e Thiago Nascimento
	 * @date 11/06/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterLeiturista");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarLeituristaActionForm filtrarLeituristaActionForm = (FiltrarLeituristaActionForm) actionForm;

		FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.ID);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade("usuario");

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String idFuncionario = filtrarLeituristaActionForm.getIdFuncionario();
		String idCliente = filtrarLeituristaActionForm.getIdCliente();
		String empresaID = filtrarLeituristaActionForm.getEmpresaID();
		String telefone = filtrarLeituristaActionForm.getTelefone();
		String ddd = filtrarLeituristaActionForm.getDdd();
		String indicadorUso = filtrarLeituristaActionForm.getIndicadorUso();
		String imei = filtrarLeituristaActionForm.getImei();
		String loginUsuario = filtrarLeituristaActionForm.getLoginUsuario();

		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		// Verifica se o campo Códigodo funcionario foi informado
		if (idFuncionario != null && !idFuncionario.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.FUNCIONARIO, idFuncionario));
		}
		
		// Verifica se o campo Consumo a Ser Cobrado (leitura não informada) foi informado

		if (empresaID != null
				&& !empresaID.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.EMPRESA, empresaID));

		}

		// Verifica se o campo Códigodo cliente foi informado
		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.CLIENTE, idCliente));
		}

		// Verifica se o campo numero telefone foi informado
		if (telefone != null && !telefone.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.TELEFONE, telefone));

		}

		// Verifica se o campo DDD foi informado
		if (ddd != null && !ddd.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.DDD, ddd));

		}
		

		// Verifica se o campo Indicador de Uso foi informado
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")
				&& !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroLeiturista.adicionarParametro(new ParametroSimples(
						FiltroLeiturista.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroLeiturista.adicionarParametro(new ParametroSimples(
						FiltroLeiturista.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		//Numero do Imei
		if(imei !=null && !imei.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.IMEI, imei));

		}
		
		// Usuario
		if ( loginUsuario != null && !loginUsuario.equals( "" ) ){
			// Filtra Usuario
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, loginUsuario ) );		
			
			// Recupera Usuário
			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = colecaoUsuario.iterator().next();
				
				filtroLeiturista.adicionarParametro( new ParametroSimples( FiltroLeiturista.USUARIO_ID, usuario.getId() ) );
			}
		}		

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		sessao.setAttribute("filtroLeiturista", filtroLeiturista);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar); 

		return retorno;
	}
}

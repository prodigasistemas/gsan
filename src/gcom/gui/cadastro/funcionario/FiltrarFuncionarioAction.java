package gcom.gui.cadastro.funcionario;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 * @date 13/04/2007
 */
public class FiltrarFuncionarioAction extends GcomAction{

	/**
	 * 
	 * [UC????] Filtrar Funcionario
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/04/2007
	 * 
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterFuncionarioAction");
		
		FiltrarFuncionarioActionForm form = (FiltrarFuncionarioActionForm) actionForm;

		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		boolean peloMenosUmParametroInformado = false;

		String nome = form.getNome();

		String idEmpresa = form.getEmpresa();

		String idUnidade = form.getIdUnidade();

		String matricula = form.getMatricula();
		
		String idFuncionarioCargo = form.getFuncionarioCargo();
		
		String numeroCpf = form.getNumeroCpf();
		
		String dataNascimento = form.getDataNascimento();
		
		// Verifica se o campo matricula foi informado
		if (matricula != null && !matricula.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, matricula));

		}
		
		// Verifica se o campo nome foi informado
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.NOME, nome));

		}

		
		// Verifica se o campo cargo foi informado
		if (idFuncionarioCargo != null
				&& !idFuncionarioCargo.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.FUNCIONARIO_CARGO_ID, idFuncionarioCargo));

		}
		
		// Verifica se o campo empresa foi informado
		if (idEmpresa != null
				&& !idEmpresa.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.UNIDADE_EMPRESA_ID, idEmpresa));

		}
		
		// Verifica se o campo descricaoCargo foi informado
		if (idUnidade != null && !idUnidade.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID, idUnidade));

		}
		
		// Verifica se o campo CPF foi informado
		if (numeroCpf != null && !numeroCpf.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.NUMERO_CPF, numeroCpf));
		}
		
		// Verifica se o campo Data de nascimento foi informado
		if (dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.DATA_NASCIMENTO, dataNascimento));
		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterFuncionarioAction e nele verificar se irá para o
		// atualizar ou para o manter
		if (form.getAtualizar() != null	&& form.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",form.getAtualizar());
			
		}
		
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("funcionarioCargo");
		
		// Manda o filtro pelo sessao para o
		// ExibirFuncionalidadeAction
		sessao.setAttribute("filtroFuncionario", filtroFuncionario);

		httpServletRequest.setAttribute("filtroFuncionario", filtroFuncionario);


		return retorno;

	}



	
}

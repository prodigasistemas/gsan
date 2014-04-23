package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza a pesquisa de cliente de acordo com os parâmetros informados
 * 
 * @author Sávio Luiz
 * @created 25 de Janeiro de 2006
 */
public class PesquisarArrecadadorAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("listaArrecadador");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarArrecadadorActionForm pesquisarArrecadadorActionForm = (PesquisarArrecadadorActionForm) actionForm;

		// Recupera os parâmetros do form
		String inscricaoEstadual = (String) pesquisarArrecadadorActionForm
				.getInscricaoEstadual();
		String idLocalidade = (String) pesquisarArrecadadorActionForm
				.getIdLocalidade();
		String idCliente = (String) pesquisarArrecadadorActionForm
				.getIdCliente();
		String idImovel = (String) pesquisarArrecadadorActionForm.getIdImovel();

		// filtro para a pesquisa de endereco do cliente
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

		filtroArrecadador.setCampoOrderBy(FiltroArrecadador.CODIGO_AGENTE);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro

		if (inscricaoEstadual != null
				&& !inscricaoEstadual.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ComparacaoTexto(
					FiltroArrecadador.INSCRICAO_ESTATAL, inscricaoEstadual));
		}

		if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.LOCALIDADE_ID, idLocalidade));
		}

		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CLIENTE_ID, idCliente));
		}

		if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.IMOVEL_ID, idImovel));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroArrecadador
				.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");

		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("imovel");

		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoArrecadadores = null;

		// Obtém a instância da Fachada
		//Fachada fachada = Fachada.getInstancia();

		// pesquisa os endereços do cliente
		//colecaoArrecadadores = fachada.pesquisar(filtroArrecadador,
			//	Arrecadador.class.getName());
		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroArrecadador, Arrecadador.class.getName());
		colecaoArrecadadores = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		if (colecaoArrecadadores == null || colecaoArrecadadores.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Arrecadador");
		} else if (colecaoArrecadadores.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a coleção na sessão
			sessao.setAttribute("colecaoArrecadadores", colecaoArrecadadores);

		}

		return retorno;
	}

}

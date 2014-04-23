package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

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
 * @date 05/08/2006
 */
public class FiltrarGerenciaRegionalAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servicço
	 * 
	 * [UC0437] Pesquisar Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Thiago Tenório, Ivan Sérgio
	 * @date 31/07/2006, 11/06/2007
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
				.findForward("exibirManterGerenciaRegional");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarGerenciaRegionalActionForm filtrarGerenciaRegionalActionForm = (FiltrarGerenciaRegionalActionForm) actionForm;

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String gerenciaRegionalID = filtrarGerenciaRegionalActionForm.getGerenciaRegionalID();
		String gerenciaRegionalNome = filtrarGerenciaRegionalActionForm.getGerenciaRegionalNome();
		String gerenciaRegionalNomeAbre = filtrarGerenciaRegionalActionForm.getGerenciaRegionalNomeAbre();
		
		String indicadorUso = filtrarGerenciaRegionalActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarGerenciaRegionalActionForm.getTipoPesquisa();
		String cnpjGerenciaRegional = filtrarGerenciaRegionalActionForm.getCnpjGerenciaRegional();
		
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");

		
		// Verifica se o campo Código foi informado
		if (gerenciaRegionalID != null && !gerenciaRegionalID.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, gerenciaRegionalID));
		}

		// Verifica se o campo Descrição foi informado
		if (gerenciaRegionalNome != null && !gerenciaRegionalNome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null &&
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
					
				filtroGerenciaRegional.adicionarParametro(new ComparacaoTextoCompleto(
					FiltroGerenciaRegional.NOME, gerenciaRegionalNome));
			}else {
				filtroGerenciaRegional.adicionarParametro(new ComparacaoTexto(
						FiltroGerenciaRegional.NOME, gerenciaRegionalNome));
			}
		}
		
		// Verifica se o CNPJ da Gerencia de configuracao foiinformada.
		if (cnpjGerenciaRegional != null && !cnpjGerenciaRegional.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
					
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.CNPJ_GERENCIA_REGIONAL, cnpjGerenciaRegional));
		}
		

		// Verifica se o campo descrição abreviatura foi informado
		if (gerenciaRegionalNomeAbre != null
				&& !gerenciaRegionalNomeAbre.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroGerenciaRegional.adicionarParametro(new ComparacaoTexto(
					FiltroGerenciaRegional.NOME_ABREVIADO,
					gerenciaRegionalNomeAbre));

		}
		
		// Verifica se o campo Indicador de Uso foi informado
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("") && !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		// Carrega os dados do Endereco
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		sessao.setAttribute("filtroGerenciaRegional", filtroGerenciaRegional);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);

		return retorno;
	}
}

package gcom.gui.relatorio.cadastro.imovel;

import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioDadosCadastraisImovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *Classe com a lógica de geração do relatorio de Dados Cadastrais
 *do wizard de Consultar Imovel
 *
 * @author Marlon Patrick
 * @since 22/09/2009
 */
public class GerarRelatorioDadosCadastraisImovelAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarImovelActionForm consultarImovelForm = (ConsultarImovelActionForm) actionForm;

		RelatorioDadosCadastraisImovel relatorioDadosCadasraisImovel = criarRelatorioComParametros(
				consultarImovelForm, sessao, tipoRelatorio);

		ActionForward retorno = null;

		try {
			retorno = processarExibicaoRelatorio(relatorioDadosCadasraisImovel,
					tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}
	
	/**
	 *Esse método cria o objeto RelatorioDadosCadastraisImovel,
	 *adiciona os parametros necessários ao seu funcionamento e o retorna.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioDadosCadastraisImovel criarRelatorioComParametros(ConsultarImovelActionForm consultarImovelForm,
			HttpSession sessao, String tipoRelatorio) {
		
		RelatorioDadosCadastraisImovel relatorioDadosCadastraisImovel = new RelatorioDadosCadastraisImovel((Usuario)sessao.getAttribute("usuarioLogado"));
		
		relatorioDadosCadastraisImovel.addParametro("colecaoClienteImovel",sessao.getAttribute("imovelClientes"));
		relatorioDadosCadastraisImovel.addParametro("colecaoImovelSubcategoria",sessao.getAttribute("imovelSubcategorias"));
		relatorioDadosCadastraisImovel.addParametro("consultarImovelForm",consultarImovelForm);
		relatorioDadosCadastraisImovel.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		return relatorioDadosCadastraisImovel;
	}


}

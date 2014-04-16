package gcom.gui.relatorio.arrecadacao;

import gcom.gui.arrecadacao.ManterGuiaDevolucaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioGuiaDevolucao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório guia de devolução
 * 
 * @author Ana Maria
 * @date 06/10/2006
 */
public class GerarRelatorioGuiaDevolucaoAction extends
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
		//HttpSession sessao = httpServletRequest.getSession(false);
		
		//Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// cria uma instância da classe do relatório
		RelatorioGuiaDevolucao relatorioGuiaDevolucao = new RelatorioGuiaDevolucao((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		 String[] ids = null;
		 
		 if (actionForm instanceof ManterGuiaDevolucaoActionForm) {
			 //tela de Manter Guia de Devolução
			 ManterGuiaDevolucaoActionForm manterGuiaDevolucaoActionForm = (ManterGuiaDevolucaoActionForm) actionForm;
			 ids = manterGuiaDevolucaoActionForm.getIdRegistrosRemocao();
		 }else{
			 //tela de Inserir Guia de Devolução
			 String idGuiaDevolucao = (String) httpServletRequest.getParameter("idGuiaDevolucao");
        	 ids = new String[1];
        	 ids[0] = idGuiaDevolucao; 
		 }
		
		relatorioGuiaDevolucao.addParametro("idsGuiaDevolucao",ids);
		//relatorioGuiaDevolucao.addParametro("usuarioLogado",usuarioLogado);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioGuiaDevolucao.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioGuiaDevolucao,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}

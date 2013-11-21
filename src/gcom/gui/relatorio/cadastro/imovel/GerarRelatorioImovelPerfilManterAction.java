package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.gui.cadastro.imovel.FiltrarImovelPerfilActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioManterImovelPerfil;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioImovelPerfilManterAction extends ExibidorProcessamentoTarefaRelatorio{
	
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

		FiltrarImovelPerfilActionForm filtrarImovelPerfilActionForm = (FiltrarImovelPerfilActionForm) actionForm;

		FiltroImovelPerfil filtroImovelPerfil = (FiltroImovelPerfil) sessao
				.getAttribute("filtroImovelPerfil");

		// Inicio da parte que vai mandar os parametros para o relatório

		ImovelPerfil imovelPerfilParametros = new ImovelPerfil();
		String id = null;

		String idImovelPerfilPesquisar = (String) filtrarImovelPerfilActionForm.getId();

		if (idImovelPerfilPesquisar != null && !idImovelPerfilPesquisar.equals("")) {
			id = idImovelPerfilPesquisar;
		}				
		// seta os parametros que serão mostrados no relatório

		imovelPerfilParametros.setId(id == null ? null : new Integer(id));
		imovelPerfilParametros.setDescricao(""
				+filtrarImovelPerfilActionForm.getDescricao());	

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterImovelPerfil relatorioManterImovelPerfil = new RelatorioManterImovelPerfil(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterImovelPerfil.addParametro("filtroImovelPerfil",
				filtroImovelPerfil);
		relatorioManterImovelPerfil.addParametro("imovelPerfilParametros",
				imovelPerfilParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterImovelPerfil.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterImovelPerfil,
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

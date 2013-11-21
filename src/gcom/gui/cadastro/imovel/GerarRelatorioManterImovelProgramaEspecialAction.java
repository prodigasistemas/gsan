package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioImovelProgramaEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioManterImovelProgramaEspecialAction extends
		ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarImovelProgramaEspecialActionForm form = (FiltrarImovelProgramaEspecialActionForm) actionForm;

		FiltroImovelProgramaEspecial filtroImovelProgramaEspecial = (FiltroImovelProgramaEspecial) sessao
				.getAttribute("filtroImovelProgramaEspecial");
		
		//cria uma instância da classe do relatório
		RelatorioImovelProgramaEspecial relatorioImovelProgramaEspecial = new RelatorioImovelProgramaEspecial(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioImovelProgramaEspecial.addParametro("filtroImovelProgramaEspecial",filtroImovelProgramaEspecial);
		
		if(form.getMatriculaImovel()!=null && !form.getMatriculaImovel().equals("")){
			relatorioImovelProgramaEspecial.addParametro("id",form.getMatriculaImovel());
		}
		if(form.getInscricaoImovel()!=null && !form.getInscricaoImovel().equals("")){
			relatorioImovelProgramaEspecial.addParametro("inscricao",form.getInscricaoImovel());
		}


		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioImovelProgramaEspecial.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		try {
			retorno = processarExibicaoRelatorio(relatorioImovelProgramaEspecial,
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

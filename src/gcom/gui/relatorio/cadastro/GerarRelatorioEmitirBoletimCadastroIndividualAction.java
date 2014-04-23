package gcom.gui.relatorio.cadastro;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioEmitirBoletimCadastroIndividual;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1011] Emitir Boletim de Cadastro Individual.
 * 
 * @author Hugo Leonardo
 * @date 24/03/2010
 * 
 */

public class GerarRelatorioEmitirBoletimCadastroIndividualAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioEmitirBoletimCadastroIndividualActionForm form = 
			(GerarRelatorioEmitirBoletimCadastroIndividualActionForm) actionForm;
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		Integer idImovel = null;
		
		// Matricula Imovel
		if (form.getMatriculaImovel() != null && 
			!form.getMatriculaImovel().equals("")) {
			
			idImovel = new Integer(form.getMatriculaImovel());
			
		}else {
			throw new ActionServletException("atencao.imovel.inexistente");
		}
			
		TarefaRelatorio relatorio = null;
		
		relatorio = new RelatorioEmitirBoletimCadastroIndividual((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("idImovel", idImovel);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			return retorno;
		}
	
}

package gcom.gui.relatorio.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioAnalisePerdasCreditos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioAnalisePerdasCreditosAction extends ExibidorProcessamentoTarefaRelatorio{

//	Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		
		String mesAno = httpServletRequest.getParameter("mesAno");
		
		if(mesAno == null || mesAno.equals("") || mesAno.length() != 7){
			throw new ActionServletException(
					"atencao.data.invalida", null, "Mês/ano Inválido");
		}else{
			mesAno = mesAno.substring(3,7) +mesAno.substring(0,2) ;
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		TarefaRelatorio relatorio = null;
		
		relatorio = new RelatorioAnalisePerdasCreditos(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		relatorio.addParametro("mesAno", mesAno);
		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		return retorno;
		
	}
	
}

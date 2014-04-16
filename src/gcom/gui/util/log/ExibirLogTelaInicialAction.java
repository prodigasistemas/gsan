package gcom.gui.util.log;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class ExibirLogTelaInicialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirLogTelaInicial");

		Collection<LabelValueBean> colecaoArquivos = new ArrayList();
		
		//File fileLog = new File("C:\\jboss-4.0.1sp1\\server\\default\\log");
		File fileLog = new File("/usr/local/jboss/server/default/log");
		
		String[] arquivos = fileLog.list();
		
		for (int aux = 0; aux < arquivos.length; aux++) {
			String nomeArquivo = arquivos[aux];
			
			if(nomeArquivo.startsWith("server") && nomeArquivo.endsWith(".log")){
				
				colecaoArquivos.add(new LabelValueBean(nomeArquivo , nomeArquivo));
			}
			
		}
		
		httpServletRequest.setAttribute("colecaoArquivos",colecaoArquivos);
		
        return retorno;
	}

}

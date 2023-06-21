package gcom.gui.cadastro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;
import gcom.util.ImagemUtil;

public class ExibirImagemRecadastramentoAguaParaAction extends GcomAction {

private String caminhoJboss = System.getProperty("jboss.server.home.dir");

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		DadosRecadastramentoAguaParaActionForm form = (DadosRecadastramentoAguaParaActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		String path = form.getPath();

		try {
			InputStream input = ImagemUtil.carregarImagemDoServidorDeArquivosPortal(String.format(path));

			httpServletResponse.setContentType("image/jpeg");

			OutputStream output = httpServletResponse.getOutputStream();
			ImagemUtil.copiar(input, output, false);

			input.close();
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
		
	}	

}

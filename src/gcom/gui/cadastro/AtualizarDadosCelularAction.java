package gcom.gui.cadastro;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class AtualizarDadosCelularAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) 
	throws Exception {
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarDadosCelularActionForm form = (AtualizarDadosCelularActionForm) actionForm;

		FormFile myFile = form.getUploadPicture();
		String contentType = myFile.getContentType();
		String fileName = myFile.getFileName();
		int fileSize = myFile.getFileSize();
		//byte[] dados = myFile.getFileData();
		System.out.println("contentType: " + contentType);
		System.out.println("File Name: " + fileName);
		System.out.println("File Size: " + fileSize);

		montarPaginaSucesso(httpServletRequest, "Atualização/Inclusão com sucesso",
				"Realizar outra Atualização/Inclusã", "exibirAtualizarDadosCelularAction.do?menu=sim");
		
		return retorno;
	}
}

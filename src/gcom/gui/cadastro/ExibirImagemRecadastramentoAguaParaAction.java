package gcom.gui.cadastro;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;
import gcom.util.ImagemUtil;

public class ExibirImagemRecadastramentoAguaParaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		
		String pathImagem = (String) request.getParameter("pathImagem");

		try {
			InputStream input = ImagemUtil.carregarImagemDoServidorDeArquivos(pathImagem);
			
			response.setContentType(getContentTypeArquivo(pathImagem,input));

			OutputStream output = response.getOutputStream();

			ImagemUtil.copiar(input, output, false);

			input.close();
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private String getContentTypeArquivo(String path, InputStream input) {
	    String fileExtension = path.substring(path.lastIndexOf('.') + 1).toLowerCase();

	    if (fileExtension.equals("png")) {
	        return "image/png";
	    } else if (fileExtension.equals("jpg") || fileExtension.equals("jpeg")) {
	        return "image/jpeg";
	    } else if (fileExtension.equals("pdf")) {
	        return "application/pdf";
	    } else if (fileExtension.equals("doc")) {
	        return "application/msword";
	    } else if (fileExtension.equals("docx")) {
	        return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	    } else {
	    	
			File file = new File(input.toString());
			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();

			return fileTypeMap.getContentType(file.getName()); 
	    }
	}

}

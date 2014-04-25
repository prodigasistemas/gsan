package gcom.gui.cadastro;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável para dar um onLoad no arquivo e importar os ceps.
 * 
 * @author Sávio Luiz
 * @created 13/07/2007
 */
public class TesteCelularAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		try {

			// FileUpload fup = new FileUpload();
			/* boolean isMultipart = */FileUpload
					.isMultipartContent(httpServletRequest);

			DiskFileUpload upload = new DiskFileUpload();

			// Parse the request
			List items = upload.parseRequest(httpServletRequest);

			FileItem item = null;
			String file = null;

			// pega uma lista de itens do form
			Iterator iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();
				if (item.getFieldName().equals("uploadPicture")) {
					file = item.getString();
				}
				if (file != null) {
					// verifica se não é diretorio
					if (!item.isFormField()) {
						// coloca o nome do item para maiusculo
						String nomeItem = item.getName().toUpperCase();
						// compara o final do nome do arquivo é .txt
						if (nomeItem.endsWith(".TXT")) {
//							TesteCelular testeCelular = new TesteCelular();
//							testeCelular.setDadosTxt(item.get());
//							fachada.inserir(testeCelular);

						} else {
							throw new ActionServletException(
									"atencao.tipo_importacao.nao_txt");
						}

					} else {
						throw new ActionServletException("atencao.nao_arquivo");
					}
				}
			}

		} catch (NumberFormatException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}
		montarPaginaSucesso(httpServletRequest,
				"Cep(s) importado(s) com sucesso!", "Importar outro(s) cep(s)",
				"exibirImportarCepAction.do");

		return retorno;
	}

}

package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de filtrar de Recepcionar Arquivo TXT Encerramento OS Cobrança
 * 
 * @author Mariana Victor
 * @date 17/06/2011
 */
public class ExibirRegistrarArquivoTxtEncerramentoOSCobrancaAction extends GcomAction {
	
	/**
	 * Este caso de uso permite o registro do TXT da empresa de cobrança para encerramento de OS.
	 * 
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * @author Mariana Victor
	 * @date 17/06/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("registrarArquivoTxtEncerramentoOSCobranca");
		
//		HttpSession sessao = httpServletRequest.getSession(false);
//		
//		Fachada fachada = Fachada.getInstancia();

//		RegistrarArquivoTxtEncerramentoOSCobrancaActionForm form = 
//			(RegistrarArquivoTxtEncerramentoOSCobrancaActionForm) actionForm;
		

		String idEmpresa = null;
		
		// Parte que trata do código quando o usuário tecla enter
		if (httpServletRequest.getParameter("objetoConsulta") != null) {

			try {
				
				DiskFileUpload upload = new DiskFileUpload();

				// Parse the request
				List items = upload.parseRequest(httpServletRequest);

				if (items != null) {
					@SuppressWarnings("unused")
					FileItem item = null;

					// pega uma lista de itens do form
					Iterator iter = items.iterator();
					while (iter.hasNext()) {

						item = (FileItem) iter.next();		
						
						if (item.getFieldName().equals("idEmpresa")) {
							idEmpresa = item.getString();
						}
						
					}
				}
			} catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}

			httpServletRequest.getParameter("caminho");

			

		}

		pesquisarCamposEnter(httpServletRequest, this.getFachada(), idEmpresa);
		
		return retorno;
	}


	@SuppressWarnings("unchecked")
	private void pesquisarCamposEnter(
			HttpServletRequest httpServletRequest,
			Fachada fachada, String idEmpresa) {

		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				Empresa empresa = (Empresa) Util
						.retonarObjetoDeColecao(colecaoEmpresa);
				httpServletRequest.setAttribute("idEmpresa",empresa.getId()
						.toString());
				httpServletRequest.setAttribute("nomeEmpresa",empresa
						.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			} else {
				httpServletRequest.setAttribute("idEmpresa","");
				httpServletRequest.setAttribute("nomeEmpresa","EMPRESA INEXISTENTE");

				httpServletRequest.setAttribute("empresaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			}

		} else {
			httpServletRequest.setAttribute("nomeEmpresa","");
		}
	}
}

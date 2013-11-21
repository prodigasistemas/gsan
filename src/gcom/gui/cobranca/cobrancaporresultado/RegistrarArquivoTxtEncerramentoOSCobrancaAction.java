package gcom.gui.cobranca.cobrancaporresultado;

import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável para dar processar e importar o arquivo.
 * 
 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
 * 
 * @author Mariana Victor
 * @date 17/06/2011
 */
public class RegistrarArquivoTxtEncerramentoOSCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		String idEmpresa = null;

		Object[] parametrosFormulario = null;
        FileItem arquivoInformado = null;
		
		//ARQUIVO
		try{
			parametrosFormulario = recebendoObjetos(httpServletRequest);
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}
		
		arquivoInformado = (FileItem) parametrosFormulario[0];
		idEmpresa = (String) parametrosFormulario[1];
		
		// VALIDAÇÃO DO ARQUIVO
		// [FS0003 – Verificar existência de dados no arquivo]
		// [FS0004 – Verificar existência do arquivo de empresa de cobrança]
		StringBuilder stringBuilder = fachada.validarArquivoTxtEncerramentoOSCobranca(arquivoInformado);
		
		String[] nomeArquivoPartido = arquivoInformado.getName().split("\\.");
		
		Map parametros = new HashMap();
		parametros.put("idEmpresa", idEmpresa);
		parametros.put("stringBuilder", stringBuilder);
		
		parametros.put("nomeArquivo", nomeArquivoPartido[0]);

		fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
          		Processo.PROCESSAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA, usuarioLogado);
		
		//montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Arquivo TXT para Encerramento de Ordens de Serviço de Cobrança Enviado para Processamento", 
			"Voltar",
			"exibirRegistrarArquivoTxtEncerramentoOSCobrancaAction.do?menu=sim");

		return retorno;
	}
	
	/**
	 * Realizando o upload do arquivo informado
	 * 
	 * @author Mariana Victor
	 * @date 20/06/2011
	 * 
	 * @param HttpServletRequest
	 */
	private Object[] recebendoObjetos(HttpServletRequest httpServletRequest) throws FileUploadException {
		
		Object[] parametrosFormulario = new Object[2]; 
		
		DiskFileUpload upload = new DiskFileUpload();
		
		List itens = upload.parseRequest(httpServletRequest);
		FileItem fileItem = null;
		
		if (itens != null) {
			
			Iterator iter = itens.iterator();
			
			while (iter.hasNext()) {
				
				fileItem = (FileItem) iter.next();
				
				if (fileItem.getFieldName().equals("uploadPicture")) {
					
					parametrosFormulario[0] = fileItem;
				}
				
				if (fileItem.getFieldName().equals("idEmpresa")) {
					
					parametrosFormulario[1] = fileItem.getString();
				}
			}
		}
		
		return parametrosFormulario;
	}

}

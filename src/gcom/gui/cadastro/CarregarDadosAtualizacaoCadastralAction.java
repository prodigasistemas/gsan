package gcom.gui.cadastro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * 
 * Carregar Dados para Atualizacao Cadastral
 *
 * @author ana maria
 * @date 18/05/2009
 */
public class CarregarDadosAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)  {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		try {
			DiskFileUpload upload = new DiskFileUpload();

			// Parse the request
			List items = upload.parseRequest(httpServletRequest);
			
			FileItem item = null;
			Fachada fachada = Fachada.getInstancia();

			// pega uma lista de itens do form
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				item = (FileItem) iter.next();

				// verifica se não é diretorio
				if (!item.isFormField()) {
					// coloca o nome do item para maiusculo
					String nomeItem = item.getName().toUpperCase();
					if (nomeItem.endsWith(".TXT")) {
						// abre o arquivo
						InputStreamReader reader = new InputStreamReader(item
								.getInputStream());
						BufferedReader buffer = new BufferedReader(reader);						
						
	/*					Map parametros = new HashMap();
						parametros.put("arqTexto",arqTexto);

						Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
						             		Processo.GERAR_DIFERENCA_ARQUIVO_TEXTO_ATU_CADASTRAL ,
						             		this.getUsuarioLogado(httpServletRequest));
						
						montarPaginaSucesso(httpServletRequest, "Geração da diferença do arquivo texto encaminhada para Batch.", "", "");*/

						fachada.carregarImovelAtualizacaoCadastral(buffer);

					}
					
				}
			}

		} catch (FileUploadException e) {
			throw new ActionServletException(
			"atencao.arquivo_nao_encontrado");
		}catch (IOException e) {
			throw new ActionServletException(
			"atencao.arquivo_nao_encontrado");
		}
		
		montarPaginaSucesso(httpServletRequest,
				"Arquivo carregado com sucesso.", 
				"Carregar outro arquivo",
				"exibirCarregarDadosAtualizacaoCadastralAction.do?menu=sim");


		return retorno;

	}
	
}
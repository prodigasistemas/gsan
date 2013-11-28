package gcom.gui.cadastro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

			List items = upload.parseRequest(httpServletRequest);
			
			FileItem item = null;
			Fachada fachada = Fachada.getInstancia();

			Iterator iterator = items.iterator();
			
			while (iterator.hasNext()) {
				item = (FileItem) iterator.next();

				if (!item.isFormField()) {
					String nomeItem = item.getName().toUpperCase();
					
					if (nomeItem.endsWith(".ZIP")) {
						ZipInputStream zipInputStream = new ZipInputStream(
								item.getInputStream());
						
						ZipEntry zipEntry = null;
						BufferedReader buffer = null;
						ArrayList<String> nomesImagens = new ArrayList<String>();
						
						while ((zipEntry = zipInputStream.getNextEntry()) != null) {
							if (zipEntry.getName().startsWith("__")) {
								continue;
							}
							
							System.out.println("Descompactando " + zipEntry.getName());
							
							if (zipEntry.getName().endsWith(".txt")) {
								File arquivoRetorno = new File(zipEntry.getName());
								
								FileOutputStream fileOutputStream = new FileOutputStream(arquivoRetorno);
								
								for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
									fileOutputStream.write(c);
								}
								
								zipInputStream.closeEntry();
								fileOutputStream.close();

								buffer = new BufferedReader(new FileReader(arquivoRetorno));
								arquivoRetorno.delete();
								
							} else if (zipEntry.getName().endsWith(".jpeg")
									|| zipEntry.getName().endsWith(".jpg")
									|| zipEntry.getName().endsWith(".png")) {
								
								File imagem = new File(zipEntry.getName());
								nomesImagens.add(imagem.getName());
								
								FileOutputStream fileOutputStream = new FileOutputStream(imagem);
								for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
									fileOutputStream.write(c);
								}
								
								zipInputStream.closeEntry();
								fileOutputStream.close();
							}
						}

						fachada.carregarImovelAtualizacaoCadastral(buffer, nomesImagens);
						zipInputStream.close();
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
package gcom.gui.cadastro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.exception.BaseRuntimeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ejb.EJBException;
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
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		DiskFileUpload upload = new DiskFileUpload();

		List items = null;
		try {
			items = upload.parseRequest(httpServletRequest);
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new ActionServletException("erro_arquivo_carregado");
		}

		Iterator iterator = items.iterator();

		while (iterator.hasNext()) {
			FileItem item = (FileItem) iterator.next();

			if (!item.isFormField()) {
				String nomeItem = item.getName().toUpperCase();

				if (nomeItem.endsWith(".ZIP")) {
					try {
						ZipInputStream zipInputStream = new ZipInputStream(item.getInputStream());

						ZipEntry zipEntry = null;
						BufferedReader buffer = null;
						ArrayList<String> imagens = new ArrayList<String>();

						while ((zipEntry = zipInputStream.getNextEntry()) != null) {
							if (zipEntry.getName().startsWith("__")) {
								continue;
							}

							System.out.println("Descompactando " + zipEntry.getName());

							if (zipEntry.getName().endsWith(".txt")) {
								
								buffer = this.lerArquivoTxt(buffer, zipInputStream, zipEntry);
								
							} else if (zipEntry.getName().endsWith(".jpeg")
									|| zipEntry.getName().endsWith(".jpg")
									|| zipEntry.getName().endsWith(".png")) {

								this.lerImagem(zipInputStream, zipEntry, imagens);
							}
						}

						Fachada.getInstancia().carregarImovelAtualizacaoCadastral(buffer, imagens);
						zipInputStream.close();
					}catch (Exception e) {
						if (e instanceof EJBException){
							Throwable t = ((EJBException) e).getCausedByException();
							if (t instanceof BaseRuntimeException){
								throw new ActionServletException(t.getMessage(), ((BaseRuntimeException) t).getParametros());
							}
						}
						throw new ActionServletException("atencao.erro_arquivo_carregado");
					}
				} else {
					throw new ActionServletException("atencao.arquivo_zip_nao_encontrado");
				}
			}
		}

		montarPaginaSucesso(httpServletRequest,
				"Arquivo carregado com sucesso.", "Carregar outro arquivo",
				"exibirCarregarDadosAtualizacaoCadastralAction.do?menu=sim");

		return retorno;

	}

	private ArrayList<String> lerImagem(ZipInputStream zipInputStream,
			ZipEntry zipEntry, ArrayList<String> imagens)
			throws FileNotFoundException, IOException {

		File imagem = new File(zipEntry.getName());
		imagens.add(imagem.getName());

		FileOutputStream fileOutputStream = new FileOutputStream(imagem);
		for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
			fileOutputStream.write(c);
		}

		zipInputStream.closeEntry();
		fileOutputStream.close();

		return imagens;
	}

	private BufferedReader lerArquivoTxt(BufferedReader buffer,
			ZipInputStream zipInputStream, ZipEntry zipEntry)
			throws FileNotFoundException, IOException {

		File arquivoRetorno = new File(zipEntry.getName());

		FileOutputStream fileOutputStream = new FileOutputStream(arquivoRetorno);

		for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
			fileOutputStream.write(c);
		}

		zipInputStream.closeEntry();
		fileOutputStream.close();

		buffer = new BufferedReader(new FileReader(arquivoRetorno));
		arquivoRetorno.delete();

		return buffer;
	}
}
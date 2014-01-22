package gcom.gui.cadastro;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ejb.EJBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

/**
 * 
 * Carregar Dados para Atualizacao Cadastral
 * 
 * @author ana maria
 * @date 18/05/2009
 */
public class CarregarDadosAtualizacaoCadastralAction extends GcomAction {
	
	private static Logger logger = Logger.getLogger(CarregarDadosAtualizacaoCadastralAction.class);

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

						AtualizacaoCadastral atualizacao =  Fachada.getInstancia().carregarImovelAtualizacaoCadastral(buffer, imagens);
						
						if (atualizacao.existeErroNoCadastro()){
							HttpSession sessao = httpServletRequest.getSession(false);
							Map<String, List<String>> mapErros = new HashMap<String, List<String>>();
							
							for (AtualizacaoCadastralImovel imovel: atualizacao.getAtualizacoesImovel()){
								List<String> erros = mapErros.get(String.valueOf(imovel.getMatricula()));
								if (erros == null){
									erros = new ArrayList<String>();
									mapErros.put(String.valueOf(imovel.getMatricula()), erros);
								}
								erros.addAll(imovel.getMensagensErro());
							}
							
							httpServletRequest.setAttribute("colecaoErrosCadastro", mapErros);
							
							retorno = actionMapping.findForward("CarregarDadosAtualizacaoCadastralAction");
						}
						
						zipInputStream.close();
					}catch (Exception e) {
						if (e instanceof EJBException){
							Throwable t = ((EJBException) e).getCausedByException();
							if (t instanceof BaseRuntimeException){
								throw new ActionServletException(t.getMessage(), ((BaseRuntimeException) t).getParametros());
							}
						}
						logger.error("Erro ao carregar arquivo de atualizacao.");
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
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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ejb.EJBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.jboss.logging.Logger;

public class CarregarDadosAtualizacaoCadastralAction extends GcomAction {

	private static Logger logger = Logger
			.getLogger(CarregarDadosAtualizacaoCadastralAction.class);

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CarregarDadosAtualizacaoCadastralActionForm form = (CarregarDadosAtualizacaoCadastralActionForm) actionForm;

		FormFile arquivo = form.getArquivo();
		
		String nomeArquivoTxt = "";

		String nomeArquivoZip = arquivo.getFileName();
		
		String caminhoJboss = System.getProperty("jboss.server.home.dir");
		
		this.salvarArquivoZip(arquivo, nomeArquivoZip, caminhoJboss);

		if (nomeArquivoZip.toLowerCase().endsWith(".zip")) {
			try {
				ZipInputStream zipInputStream = new ZipInputStream(arquivo.getInputStream());

				ZipEntry zipEntry = null;
				BufferedReader buffer = null;
				List<String> imagens = new ArrayList<String>();

				while ((zipEntry = zipInputStream.getNextEntry()) != null) {
					
					String nomeItem = zipEntry.getName().toLowerCase();
					
					if (nomeItem.startsWith("__")) {
						continue;
					}

					if (nomeItem.endsWith(".txt")) {

						nomeArquivoTxt = nomeItem.substring(0, 10);

						System.out.println("Descompactando " + nomeItem);

						buffer = this.lerArquivoTxt(buffer, zipInputStream, zipEntry);

					} else if (nomeItem.endsWith(".jpeg")
							|| nomeItem.endsWith(".jpg")
							|| nomeItem.endsWith(".png")) {

						imagens = this.lerImagem(zipInputStream, zipEntry, imagens, nomeArquivoTxt, caminhoJboss);
					}
				}

				AtualizacaoCadastral atualizacao = Fachada.getInstancia().carregarImovelAtualizacaoCadastral(buffer, imagens);

				if (atualizacao.existeErroNoArquivo()) {
					
					Map<String, List<String>> mapErros = new HashMap<String, List<String>>();

					for (AtualizacaoCadastralImovel imovel : atualizacao.getImoveisComErro()) {
						
						List<String> erros = mapErros.get(String.valueOf(imovel.getMatricula()));
						
						if (erros == null) {
							erros = new ArrayList<String>();
							mapErros.put(String.valueOf(imovel.getMatricula()), erros);
						}
						erros.addAll(imovel.getMensagensErro());
					}

					httpServletRequest.setAttribute("mapErros", mapErros);

					form.setColecaoErrosCadastro(mapErros);
					form.setNomeArquivo(arquivo.getFileName());
					form.setTotalImoveis(atualizacao.getTotalImoveis() + "");
					form.setTotalImoveisComErro(atualizacao.getTotalImoveisComErro() + "");

					retorno = actionMapping.findForward("CarregarDadosAtualizacaoCadastralAction");
				}

				zipInputStream.close();
			} catch (Exception e) {
				if (e instanceof EJBException) {
					Throwable t = ((EJBException) e).getCausedByException();
					if (t instanceof BaseRuntimeException) {
						throw new ActionServletException(t.getMessage(), ((BaseRuntimeException) t).getParametros());
					}
				}
				logger.error("Erro ao carregar arquivo de atualizacao.");
				throw new ActionServletException("atencao.erro_arquivo_carregado");
			}
		} else {
			throw new ActionServletException("atencao.arquivo_zip_nao_encontrado");
		}

		montarPaginaSucesso(httpServletRequest,
				"Arquivo carregado com sucesso.", "Carregar outro arquivo",
				"exibirCarregarDadosAtualizacaoCadastralAction.do?menu=sim");

		return retorno;
	}

	private void salvarArquivoZip(FormFile arquivoCarregado, String nomeArquivoZip, String caminhoJboss) {
		try {
			File pasta = new File(caminhoJboss + "/retorno", nomeArquivoZip.substring(0, 10));
			
			if (!pasta.exists()) {
				pasta.mkdir();
			} else {
				File arquivos[] = pasta.listFiles();

				for (File arquivo : arquivos) {
					if (nomeArquivoZip.equals(arquivo.getName())) {
						arquivo.delete();
					}
				}
			}
			
	        byte tamanho[] = arquivoCarregado.getFileData();  
	        File arquivoZip = new File(pasta, nomeArquivoZip);  
	        OutputStream out = new FileOutputStream(arquivoZip);          
	        out.write(tamanho);  
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new ActionServletException("atencao.erro_salvar_arquivo_zip");
		}
	}

	private List<String> lerImagem(ZipInputStream zipInputStream,
			ZipEntry zipEntry, List<String> imagens, String nomeArquivo,
			String caminhoJboss) throws FileNotFoundException, IOException {

		try {
			File pasta = new File(caminhoJboss + "/images", nomeArquivo);

			if (!pasta.exists()) {
				pasta.mkdir();
			} else {
				File arquivos[] = pasta.listFiles();

				for (File arquivo : arquivos) {
					if (zipEntry.getName().equals(arquivo.getName())) {
						arquivo.delete();
					}
				}
			}

			imagens = this.extrairImagem(zipInputStream, zipEntry, imagens, pasta);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return imagens;
	}

	private List<String> extrairImagem(ZipInputStream zipInputStream,
			ZipEntry zipEntry, List<String> imagens, File caminho)
			throws FileNotFoundException, IOException {

		File imagem = new File(caminho, zipEntry.getName());
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

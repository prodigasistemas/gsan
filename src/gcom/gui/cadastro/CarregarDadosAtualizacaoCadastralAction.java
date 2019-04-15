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
import java.io.InputStream;
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

	private static Logger logger = Logger.getLogger(CarregarDadosAtualizacaoCadastralAction.class);
	
	private String caminhoJboss = System.getProperty("jboss.server.home.dir");

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = mapping.findForward("telaSucesso");

		CarregarDadosAtualizacaoCadastralActionForm form = (CarregarDadosAtualizacaoCadastralActionForm) actionForm;

		FormFile arquivo = form.getArquivo();

		String nomeArquivoZip = arquivo.getFileName();

		this.salvarArquivoZip(arquivo, nomeArquivoZip);

		if (nomeArquivoZip.toLowerCase().endsWith(".zip")) {
			try {
				ZipInputStream zis = new ZipInputStream(arquivo.getInputStream());

				ZipEntry entry = null;
				BufferedReader buffer = null;
				List<String> imagens = new ArrayList<String>();

				if (contemTxt(arquivo.getInputStream())) {
					
					while ((entry = zis.getNextEntry()) != null) {

						String nomeItem = entry.getName().toLowerCase();

						if (!nomeItem.contains("macosx"))  {
							
							if (nomeItem.endsWith(".txt")) {
								
								System.out.println("Descompactando " + nomeItem);
								buffer = this.lerArquivoTxt(buffer, zis, nomeItem);
								
							} else if (isFoto(nomeItem)) {
								
								imagens = this.lerImagem(zis, nomeItem, imagens, nomeArquivoZip);
							}
						}
					}
					
					retorno = carregarArquivoTxtComFotos(mapping, request, retorno, form, arquivo, buffer, imagens);
					
				} else {
					while ((entry = zis.getNextEntry()) != null) {

						String nomeItem = entry.getName().toLowerCase();

						if (isFoto(nomeItem)) {
							imagens = this.lerImagem(zis, nomeItem, imagens, nomeArquivoZip);
						}
					}
					
					apagarImagensRetorno(imagens);
					salvarImagens(imagens, nomeArquivoZip.substring(0, 10));
				}


				zis.close();
				
			} catch (Exception e) {
				if (e instanceof EJBException) {
					Throwable t = ((EJBException) e).getCausedByException();
					if (t instanceof BaseRuntimeException) {
						throw new ActionServletException(t.getMessage(), ((BaseRuntimeException) t).getParametros());
					}
				}
				
				e.printStackTrace();
				logger.error("Erro ao carregar arquivo de atualizacao.");
				throw new ActionServletException("atencao.erro_arquivo_carregado");
			}
		} else {
			throw new ActionServletException("atencao.arquivo_zip_nao_encontrado");
		}

		montarPaginaSucesso(request, "Arquivo carregado com sucesso.", 
				"Carregar outro arquivo",
				"exibirCarregarDadosAtualizacaoCadastralAction.do?menu=sim");

		return retorno;
	}

	private void apagarImagensRetorno(List<String> imagens) {
		List<Integer> imoveis = getListaImoveis(imagens);
		
		for (Integer imovel : imoveis) {
			getFachada().apagarImagemRetorno(imovel);
		}
	}

	private boolean isFoto(String nomeItem) {
		return nomeItem.endsWith(".jpeg") || nomeItem.endsWith(".jpg") || nomeItem.endsWith(".png");
	}

	private ActionForward carregarArquivoTxtComFotos(ActionMapping mapping, HttpServletRequest request, ActionForward retorno,
			CarregarDadosAtualizacaoCadastralActionForm form, FormFile arquivo, BufferedReader buffer, List<String> imagens) throws Exception {
		
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

			request.setAttribute("mapErros", mapErros);

			form.setColecaoErrosCadastro(mapErros);
			form.setNomeArquivo(arquivo.getFileName());
			form.setTotalImoveis(atualizacao.getTotalImoveis() + "");
			form.setTotalImoveisComErro(atualizacao.getTotalImoveisComErro() + "");
			form.setTipoArquivo(atualizacao.getArquivoTexto().getDescricaoTipoRetorno());

			retorno = mapping.findForward("CarregarDadosAtualizacaoCadastralAction");
		}
		return retorno;
	}

	private void salvarArquivoZip(FormFile arquivoCarregado, String nomeArquivoZip) {
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

	private List<String> lerImagem(ZipInputStream zis, String nomeArquivo, List<String> imagens,
			String nomeArquivoZip) throws FileNotFoundException, IOException {

		try {
			File pasta = new File(caminhoJboss + "/images/" + nomeArquivoZip.substring(0, 10));
			if (!pasta.exists()) {
				pasta.mkdir();
			} else {
				File arquivos[] = pasta.listFiles();

				for (File arquivo : arquivos) {
					if (nomeArquivo.equals(arquivo.getName())) {
						arquivo.delete();
					}
				}
			}

			imagens = this.extrairImagem(zis, nomeArquivo, imagens, pasta);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return imagens;
	}

	private List<String> extrairImagem(ZipInputStream zis, String nomeArquivo, List<String> imagens,
			File caminho) throws FileNotFoundException, IOException {

		File imagem = new File(caminho, nomeArquivo);
		imagens.add(imagem.getName());

		FileOutputStream fileOutputStream = new FileOutputStream(imagem);
		for (int c = zis.read(); c != -1; c = zis.read()) {
			fileOutputStream.write(c);
		}

		zis.closeEntry();
		fileOutputStream.close();

		return imagens;
	}

	private BufferedReader lerArquivoTxt(BufferedReader buffer, ZipInputStream zis, String nomeArquivo)
			throws FileNotFoundException, IOException {

		File arquivoRetorno = new File(System.getProperty("jboss.server.home.dir") + "/retorno/" + nomeArquivo);

		FileOutputStream fileOutputStream = new FileOutputStream(arquivoRetorno);

		for (int c = zis.read(); c != -1; c = zis.read()) {
			fileOutputStream.write(c);
		}

		zis.closeEntry();
		fileOutputStream.close();

		buffer = new BufferedReader(new FileReader(arquivoRetorno));
		arquivoRetorno.delete();

		return buffer;
	}

	private boolean contemTxt(InputStream inputStream) throws IOException {
		ZipInputStream zis = new ZipInputStream(inputStream);
		ZipEntry entry = null;
		
		boolean contemTxt = false;
		
		while ((entry = zis.getNextEntry()) != null) {
			String item = entry.getName().toLowerCase();
			if (item.endsWith(".txt")) {
				contemTxt = true;
				break;
			}
		}

		return contemTxt;
	}
	
	private void salvarImagens(List<String> imagens, String nomeArquivo) throws Exception {
		for (String imagem : imagens) {
			String pasta = "/images/" + nomeArquivo;

			Integer idImovel = getIdImovel(imagem);
			Integer idImovelRetorno = getFachada().obterIdImovelRetorno(idImovel);
			
			if (idImovelRetorno != null) {
				getFachada().inserirImagemRetorno(idImovel, imagem, pasta, idImovelRetorno);
			}
		}
	}

	private Integer getIdImovel(String nomeImagem) {
		String idImovel = nomeImagem.substring(0, nomeImagem.indexOf("_"));
		return Integer.valueOf(idImovel);
	}
	
	private List<Integer> getListaImoveis(List<String> imagens) {
		List<Integer> imoveis = new ArrayList<Integer>();
		
		for (String imagem : imagens) {
			Integer imovel = getIdImovel(imagem);
			
			if (!imoveis.contains(imovel))
				imoveis.add(imovel);
		}
		
		return imoveis;
	}
}

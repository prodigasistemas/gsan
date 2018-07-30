package gcom.gui.cadastro;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.gui.GcomAction;
import gcom.tarefa.TarefaException;
import gcom.util.IoUtil;
import gcom.util.ZipUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarArquivosImoveisARevisarAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		int percentualAleatorios = 0;
		if (!form.getPercentualAleatorios().equals("") && !form.getPercentualAleatorios().equals("0")) {
			percentualAleatorios = Integer.parseInt(form.getPercentualAleatorios()); 
		}
		
		List<ArquivoTextoAtualizacaoCadastral> listaArquivoTexto = getFachada().regerarArquivosAtualizacaoCadastral(getIdsArquivos(form), percentualAleatorios);

		if (listaArquivoTexto != null && !listaArquivoTexto.isEmpty()) {
			try {
				String nomeArquivoZIP = getNomeArquivo(form.getIdLocalidade());
				File arquivoZIP = new File(nomeArquivoZIP);
				ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(arquivoZIP));

				for (ArquivoTextoAtualizacaoCadastral arquivoTexto : listaArquivoTexto) {
					adicionar(zip, arquivoTexto);
				}

				download(response, zip, nomeArquivoZIP, arquivoZIP);
				zip.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo zip", e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo txt", e);
			}
		}

		return null;
	}

	private List<Integer> getIdsArquivos(ConsultarArquivoTextoAtualizacaoCadastralActionForm form) {
		List<Integer> ids = new ArrayList<Integer>();

		for (String id : form.getIdsRegistros()) {
			ids.add(Integer.parseInt(id));
		}
		return ids;
	}

	private void download(HttpServletResponse httpServletResponse, ZipOutputStream zip, String nomeArquivoZIP, File arquivoZIP) throws IOException {
		zip.flush();
		zip.close();

		httpServletResponse.setContentType("application/zip");
		httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + nomeArquivoZIP + ".zip");

		ServletOutputStream sos = httpServletResponse.getOutputStream();
		sos.write(IoUtil.getBytesFromFile(arquivoZIP));
		sos.flush();
		sos.close();

		arquivoZIP.delete();
	}

	private void adicionar(ZipOutputStream zip, ArquivoTextoAtualizacaoCadastral arquivoTexto) throws FileNotFoundException, IOException, ClassNotFoundException {
		File temp = new File(arquivoTexto.getDescricaoArquivo() + ".txt");

		FileOutputStream outputStream = new FileOutputStream(temp);
		outputStream.write(((StringBuilder) IoUtil.transformarBytesParaObjeto(arquivoTexto.getArquivoTexto())).toString().getBytes());
		outputStream.close();

		ZipUtil.adicionarArquivo(zip, temp);

		temp.delete();

		zip.closeEntry();
	}

	private String getNomeArquivo(String idLocalidade) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String data = formatter.format(new Date());

		return "ROTAS_RECADASTRAMENTO_REVISAO_" + data;
	}
}

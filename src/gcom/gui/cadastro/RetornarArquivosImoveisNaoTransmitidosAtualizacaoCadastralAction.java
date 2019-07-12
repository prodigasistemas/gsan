package gcom.gui.cadastro;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.gui.GcomAction;
import gcom.tarefa.TarefaException;
import gcom.util.ZipUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarArquivosImoveisNaoTransmitidosAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		Integer idEmpresa = null;
		if (!form.getIdEmpresa().equals("") && !form.getIdEmpresa().equals("0")) {
			idEmpresa = Integer.valueOf(form.getIdEmpresa());
		}
		
		List<ArquivoTextoAtualizacaoCadastral> listaArquivoTexto = getFachada().regerarArquivosAtualizacaoCadastral(
				getIdsArquivos(form),
				ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_TRANSMISSAO,
				null,
				idEmpresa);

		if (listaArquivoTexto != null && !listaArquivoTexto.isEmpty()) {
			try {
				String nomeArquivoZIP = getNomeArquivo(form.getIdLocalidade());
				File arquivoZIP = new File(nomeArquivoZIP);
				ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(arquivoZIP));

				for (ArquivoTextoAtualizacaoCadastral arquivoTexto : listaArquivoTexto) {
					ZipUtil.adicionarEmZip(zip, arquivoTexto.getDescricaoArquivo(), arquivoTexto.getArquivoTexto());
				}

				ZipUtil.download(response, zip, nomeArquivoZIP, arquivoZIP);
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

	private String getNomeArquivo(String idLocalidade) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String data = formatter.format(new Date());

		return "ROTAS_RECADASTRAMENTO_NAO_TRANSMITIDOS_" + data;
	}
}

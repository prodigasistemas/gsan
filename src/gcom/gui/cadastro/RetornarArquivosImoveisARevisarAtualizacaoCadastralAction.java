package gcom.gui.cadastro;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.gui.ActionServletException;
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

public class RetornarArquivosImoveisARevisarAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		int percentualAleatorios = 0;
		if (!form.getPercentualAleatorios().equals("") && !form.getPercentualAleatorios().equals("0")) {
			percentualAleatorios = Integer.parseInt(form.getPercentualAleatorios()); 
		}
		
		Integer idEmpresa = null;
		if (!form.getIdEmpresa().equals("") && !form.getIdEmpresa().equals("0")) {
			idEmpresa = Integer.valueOf(form.getIdEmpresa());
		}
		
		List<ArquivoTextoAtualizacaoCadastral> listaArquivoTexto = getFachada().gerarArquivosFiscalizacaoOuRevisaoDeAtualizacaoCadastral(
				SituacaoAtualizacaoCadastral.EM_REVISAO, getIdsArquivos(form), percentualAleatorios, null, idEmpresa);

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
		} else {
			throw new ActionServletException("atencao.arquivo_revisao_atualizacao_cadastral_vazio");
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

		return "ROTAS_RECADASTRAMENTO_REVISAO_" + data;
	}
}

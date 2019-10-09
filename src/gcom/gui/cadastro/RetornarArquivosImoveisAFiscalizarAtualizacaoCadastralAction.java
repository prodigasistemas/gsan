package gcom.gui.cadastro;

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

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.tarefa.TarefaException;
import gcom.util.CollectionUtil;
import gcom.util.ZipUtil;

public class RetornarArquivosImoveisAFiscalizarAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		int percentualAleatorios = 0;
 		if (!form.getPercentualAleatorios().equals("") && !form.getPercentualAleatorios().equals("0")) {
			percentualAleatorios = Integer.parseInt(form.getPercentualAleatorios()); 
		}
		
		int lote = 0;
		if (!form.getLote().equals("") && !form.getLote().equals("0")) {
			lote = Integer.parseInt(form.getLote()); 
		}
		
		Integer idEmpresa = null;
		if (!form.getIdEmpresa().equals("") && !form.getIdEmpresa().equals("0")) {
			idEmpresa = Integer.valueOf(form.getIdEmpresa());
		}
		
		List<ArquivoTextoAtualizacaoCadastral> listaArquivoTexto = getFachada().gerarArquivosFiscalizacaoOuRevisaoDeAtualizacaoCadastral(
				SituacaoAtualizacaoCadastral.EM_FISCALIZACAO, getIdsArquivos(form), percentualAleatorios, lote, idEmpresa);

		if (CollectionUtil.naoEhVazia(listaArquivoTexto)) {
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
			} finally {
				
			}
		} else {
			throw new ActionServletException("atencao.arquivo_fiscalizacao_atualizacao_cadastral_vazio");
		}

		return null;
	}

	private List<Integer> getIdsArquivos(ConsultarArquivoTextoAtualizacaoCadastralActionForm form) {
		List<Integer> ids = new ArrayList<Integer>();

		if (form.getIdsRegistros() != null && form.getIdsRegistros().length > 0) {
			for (String id : form.getIdsRegistros()) {
				ids.add(Integer.parseInt(id));
			}
		}
		return ids;
	}

	private String getNomeArquivo(String idLocalidade) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String data = formatter.format(new Date());

		return "ROTAS_RECADASTRAMENTO_FISCALIZACAO_" + data;
	}
}

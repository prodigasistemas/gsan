package gcom.gui.cadastro;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.tarefa.TarefaException;
import gcom.util.ZipUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarZipArquivoTxtAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		String[] idsArquivoTxt = form.getIdsRegistros();

		Collection<ArquivoTextoAtualizacaoCadastral> colecaoArquivoTexto = getFachada().pesquisarArquivoTextoAtualizacaoCadastro(idsArquivoTxt);

		if (colecaoArquivoTexto != null && !colecaoArquivoTexto.isEmpty()) {

			try {
				String nomeArquivoZIP = getNomeArquivo(form.getIdLocalidade());
				File arquivoZIP = new File(nomeArquivoZIP);
				ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(arquivoZIP));

				for (ArquivoTextoAtualizacaoCadastral arquivoTexto : colecaoArquivoTexto) {
					int situacao = arquivoTexto.getSituacaoTransmissaoLeitura().getId().intValue();
					
					if (situacao != SituacaoTransmissaoLeitura.DISPONIVEL.intValue() && situacao != SituacaoTransmissaoLeitura.TRANSMITIDO.intValue()) {

						ZipUtil.adicionarEmZip(zip, arquivoTexto.getDescricaoArquivo(), arquivoTexto.getArquivoTexto());
						
						if (situacao == SituacaoTransmissaoLeitura.LIBERADO.intValue())
							getFachada().atualizarArquivoTextoAtualizacaoCadstral(arquivoTexto.getId());
						
					} else {
						throw new ActionServletException("atencao.nao_baixar_arquivo_txt_atualizacao_cadastral");
					}
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

	private String getNomeArquivo(String idLocalidade) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String data = formatter.format(new Date());

		String nomeArquivoZIP = "Rotas_Cadastro";

		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			nomeArquivoZIP += "_L" + idLocalidade;
		}

		return nomeArquivoZIP + "_" + data;
	}
}

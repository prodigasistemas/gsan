package gcom.gui.cadastro;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.IoUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarArquivoTxtAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		String idArquivoTxt = request.getParameter("idRegistroAtualizacao");

		ArquivoTextoAtualizacaoCadastral arquivoTexto = getFachada().pesquisarArquivoTextoAtualizacaoCadastro(Integer.parseInt(idArquivoTxt));
		
		int situacao = arquivoTexto.getSituacaoTransmissaoLeitura().getId().intValue();

		if (situacao != SituacaoTransmissaoLeitura.DISPONIVEL.intValue() && situacao != SituacaoTransmissaoLeitura.TRANSMITIDO.intValue()) {

			try {
				File temporario = File.createTempFile("temporario", ".txt");

				FileOutputStream outputStream = new FileOutputStream(temporario);
				outputStream.write(((StringBuilder) IoUtil.transformarBytesParaObjeto(arquivoTexto.getArquivoTexto())).toString().getBytes());
				outputStream.close();

				FileInputStream inputStream = new FileInputStream(temporario);

				int INPUT_BUFFER_SIZE = 1024;
				byte[] temp = new byte[INPUT_BUFFER_SIZE];
				int numBytesRead = 0;

				ByteArrayOutputStream arquivo = new ByteArrayOutputStream();

				while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
					arquivo.write(temp, 0, numBytesRead);
				}

				inputStream.close();
				inputStream = null;

				arquivo.close();

				response.setContentType("text/plain");
				response.addHeader("Content-Disposition", "attachment; filename=" + arquivoTexto.getDescricaoArquivo() + ".txt");
				ServletOutputStream out;

				out = response.getOutputStream();
				out.write(arquivo.toByteArray());
				out.flush();
				out.close();

				temporario.delete();

				if (situacao == SituacaoTransmissaoLeitura.LIBERADO.intValue())
					getFachada().atualizarArquivoTextoAtualizacaoCadstral(arquivoTexto.getId());
				
			} catch (IOException e) {
				reportarErros(request, "erro.sistema");

			} catch (ClassNotFoundException e) {
				reportarErros(request, "erro.sistema");
			}
		} else {
			throw new ActionServletException("atencao.nao_baixar_arquivo_txt_atualizacao_cadastral");
		}
		return null;
	}
}

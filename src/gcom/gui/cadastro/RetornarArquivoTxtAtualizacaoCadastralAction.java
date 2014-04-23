package gcom.gui.cadastro;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.fachada.Fachada;
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

/**
 * Consultar Arquivo Texto da Atualização Cadastral
 * 
 * @author Ana Maria 
 * @date 02/03/2009
 */
public class RetornarArquivoTxtAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		String idArquivoTxt = httpServletRequest
				.getParameter("idRegistroAtualizacao");

		Fachada fachada = Fachada.getInstancia();

		ArquivoTextoAtualizacaoCadastral arquivoTexto = fachada.pesquisarArquivoTextoAtualizacaoCadastro(Integer.parseInt(idArquivoTxt));
		
		if(!arquivoTexto.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.DISPONIVEL) && 
				!arquivoTexto.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
			
			try {
				File temporario = File.createTempFile("temporario", ".txt");
	
				FileOutputStream outputStream = new FileOutputStream(temporario);
				outputStream.write(((StringBuilder) IoUtil
						.transformarBytesParaObjeto(arquivoTexto
								.getArquivoTexto())).toString().getBytes());
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
	
				httpServletResponse.setContentType("text/plain");
				httpServletResponse.addHeader("Content-Disposition",
						"attachment; filename="+arquivoTexto.getDescricaoArquivo()+".txt");
				ServletOutputStream out;
	
				out = httpServletResponse.getOutputStream();
				out.write(arquivo.toByteArray());
				out.flush();
				out.close();
	
				// Apaga todo o conteúdo gerado
				temporario.delete();
				
				//Atualizar Situacao do Arquivo
				fachada.atualizarArquivoTextoAtualizacaoCadstral(arquivoTexto.getId());
			
			} catch (IOException e) {
				reportarErros(httpServletRequest, "erro.sistema");
	
			} catch (ClassNotFoundException e) {
				reportarErros(httpServletRequest, "erro.sistema");
			}
		}else{
			throw new ActionServletException("atencao.nao_baixar_arquivo_txt_atualizacao_cadastral");
		}
		return retorno;
	}
}

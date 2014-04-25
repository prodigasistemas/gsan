package gcom.gui.cadastro;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.tarefa.TarefaException;
import gcom.util.IoUtil;
import gcom.util.ZipUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Arquivo Texto da Atualização Cadastral
 * 
 * @author COSANPA - Felipe Santos
 * @date 04/12/2013
 */
public class RetornarZipArquivoTxtAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = 
			(ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;
	
		String[] idsArquivoTxt = form.getIdsRegistros();

		Fachada fachada = Fachada.getInstancia();
		
		ZipOutputStream zip = null;
		
		Collection<ArquivoTextoAtualizacaoCadastral> colecaoArquivoTexto = fachada.pesquisarArquivoTextoAtualizacaoCadastro(
				idsArquivoTxt);
		
		if (colecaoArquivoTexto != null && !colecaoArquivoTexto.isEmpty()) {
			
			try {
				String nomeArquivoZIP = this.gerarNomeArquivoZIP(form.getIdLocalidade());
				File arquivoZIP = new File(nomeArquivoZIP);
				
				zip = new ZipOutputStream(new FileOutputStream(arquivoZIP));
				
				for (ArquivoTextoAtualizacaoCadastral arquivoTexto : colecaoArquivoTexto) {
					
					if(!arquivoTexto.getSituacaoTransmissaoLeitura().getId().equals(
							SituacaoTransmissaoLeitura.DISPONIVEL) 
							&& !arquivoTexto.getSituacaoTransmissaoLeitura().getId().equals(
									SituacaoTransmissaoLeitura.TRANSMITIDO)) {
						
						this.adicionarArquivoTexto(zip, arquivoTexto);
						
						fachada.atualizarArquivoTextoAtualizacaoCadstral(arquivoTexto.getId());
					} else {
						throw new ActionServletException("atencao.nao_baixar_arquivo_txt_atualizacao_cadastral");
					}
				}
				
				this.enviarArquivoZIP(httpServletResponse, zip, nomeArquivoZIP, arquivoZIP);
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo zip", e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo txt", e);
			}
			
			try {
				zip.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao fechar o arquivo zip", e);
			}

		}
		
		return retorno;
	}

	private void enviarArquivoZIP(HttpServletResponse httpServletResponse,
		ZipOutputStream zip, String nomeArquivoZIP, File arquivoZIP)
		throws IOException {
		
		zip.flush();
		zip.close();
		
		httpServletResponse.setContentType("application/zip");
		httpServletResponse.addHeader("Content-Disposition",
				"attachment; filename="+nomeArquivoZIP + ".zip");
		
		ServletOutputStream sos = httpServletResponse.getOutputStream();
		sos.write(IoUtil.getBytesFromFile(arquivoZIP));
		sos.flush();
		sos.close();

		arquivoZIP.delete();
	}

	private void adicionarArquivoTexto(ZipOutputStream zip,
		ArquivoTextoAtualizacaoCadastral arquivoTexto) 
		throws FileNotFoundException, IOException, ClassNotFoundException {
		
		File arquivoTxtTemp = new File(arquivoTexto.getDescricaoArquivo() + ".txt");
		
		FileOutputStream outputStream = new FileOutputStream(arquivoTxtTemp);
		outputStream.write(((StringBuilder) IoUtil.transformarBytesParaObjeto(
				arquivoTexto.getArquivoTexto())).toString().getBytes());
		outputStream.close();

		ZipUtil.adicionarArquivo(zip, arquivoTxtTemp);

		arquivoTxtTemp.delete();

		zip.closeEntry();
	}

	private String gerarNomeArquivoZIP(String idLocalidade) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String data = formatter.format(new Date());
		
		String nomeArquivoZIP = "Rotas_Cadastro";
		
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			nomeArquivoZIP += "_L" + idLocalidade;
		}
		
		nomeArquivoZIP += "_" + data;
		
		return nomeArquivoZIP;
	}
}

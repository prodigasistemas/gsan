package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para Retornar o Arquivo Texto do Acompanhamento de Arquivos de Roteiro Compactado
 * 
 * @author Thúlio Araújo
 * @since 02/08/2011
 */
public class RetornarArquivoTxtAcompanhamentoArqRoteiroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		String idArquivo = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
		filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID, idArquivo));
		
		Collection<?> colecaoArquivoTxtAcompanhamentoServico = fachada.pesquisar(
				filtroArquivoTextoAcompanhamentoServico,
			    ArquivoTextoAcompanhamentoServico.class.getName());
		
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) colecaoArquivoTxtAcompanhamentoServico
			    .iterator().next();
		
		String nomeArquivo = fachada.nomeArquivoAcompanhamentoServico(arquivoTextoAcompanhamentoServico.getEquipe().getId());
		
		try {
			boolean arquivoPossuiExtensaoGZ = nomeArquivo.toUpperCase()
					.endsWith(".GZ");

			// se o arquivo não possuir extensão GZ
			if (!arquivoPossuiExtensaoGZ) {
				File temporario = File.createTempFile("temporario", ".txt");
				FileOutputStream outputStream = new FileOutputStream(temporario);

				outputStream.write( 
					fachada.gerarArquivoTextoOrdensServicoAcompanhamentoEquipe(
						arquivoTextoAcompanhamentoServico.getEquipe().getId(),
						arquivoTextoAcompanhamentoServico.getDataProgramacao(),true, false));
				
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
						"attachment; filename="+nomeArquivo);
				ServletOutputStream out;

				out = httpServletResponse.getOutputStream();
				out.write(arquivo.toByteArray());
				out.flush();
				out.close();
			}else{   
			    httpServletResponse.setContentType("application/zip");
			    httpServletResponse.addHeader("Content-Disposition",
			      "attachment; filename="+ nomeArquivo );
			    ServletOutputStream out;
			 
			    out = httpServletResponse.getOutputStream();
			    
//			    if ( arquivoTextoRoteiroEmpresa.getArquivoTextoNaoRecebido() != null ){
//			    	out.write( arquivoTextoRoteiroEmpresa.getArquivoTextoNaoRecebido() );	
//			    } else {
//			    	out.write( arquivoTextoRoteiroEmpresa.getArquivoTexto() );
//			    }
			    
			    out.flush();
			    out.close();
			}
		} catch (IOException e) {
			reportarErros(httpServletRequest, "erro.sistema");
		}
		
		return retorno;
	}
}

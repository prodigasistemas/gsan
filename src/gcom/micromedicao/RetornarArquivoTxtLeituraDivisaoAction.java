package gcom.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.IoUtil;
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
  Descrição da classe
 * 
 * @author Hugo Leonardo
 * @date 05/06/2010
 */
public class RetornarArquivoTxtLeituraDivisaoAction extends GcomAction {

 /**
  * Este caso de uso permite Retornar um Arquivo Txt Leitura Divisão
  * 
  * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
  *  
  * @author Hugo Leonardo
  * @created 04/06/2010
  * 
  * @param actionMapping
  * @param actionForm
  * @param httpServletRequest
  * @param httpServletResponse
  * @return
  */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		
		String idArquivoTxtDivisao = httpServletRequest
		    .getParameter("idRegistroAtualizacao");
		
		FiltroArquivoTextoRoteiroEmpresaDivisao filtroArquivoTextoRoteiroEmpresaDivisao = 
			new FiltroArquivoTextoRoteiroEmpresaDivisao();
		filtroArquivoTextoRoteiroEmpresaDivisao.adicionarParametro(new ParametroSimples( 
				FiltroArquivoTextoRoteiroEmpresaDivisao.ID, idArquivoTxtDivisao));
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoArquivoTxtLeituraDivisao = fachada.pesquisar(filtroArquivoTextoRoteiroEmpresaDivisao,
			ArquivoTextoRoteiroEmpresaDivisao.class.getName());
		
		ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = (ArquivoTextoRoteiroEmpresaDivisao) 
			colecaoArquivoTxtLeituraDivisao.iterator().next();
		  
		if(arquivoTextoRoteiroEmpresaDivisao.getSituacaoTransmissaoLeitura().getId()
				.equals(SituacaoTransmissaoLeitura.LIBERADO)){
	   
			try {
				   
				boolean ehgz = arquivoTextoRoteiroEmpresaDivisao.getNomeArquivo().toUpperCase().endsWith(".GZ");
			
				if(!ehgz){
						
					File temporario = File.createTempFile("temporario", ".txt"); 
				    FileOutputStream outputStream = new FileOutputStream(temporario);
					    
				    try {
						outputStream.write((
						  (StringBuilder) IoUtil.transformarBytesParaObjeto(
						    arquivoTextoRoteiroEmpresaDivisao.getArquivoTexto())).toString().getBytes());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
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
							"attachment; filename="+arquivoTextoRoteiroEmpresaDivisao.getNomeArquivo());
					ServletOutputStream out;
		
					out = httpServletResponse.getOutputStream();
					out.write(arquivo.toByteArray());
					out.flush();
					out.close();
					
				}else{   
					    
					httpServletResponse.setContentType("application/zip");
				    httpServletResponse.addHeader("Content-Disposition",
				      "attachment; filename="+ arquivoTextoRoteiroEmpresaDivisao.getNomeArquivo() );
				    ServletOutputStream out;
				 
				    out = httpServletResponse.getOutputStream();
				    out.write( arquivoTextoRoteiroEmpresaDivisao.getArquivoTexto() );
				    out.flush();
				    out.close();
				}
			    
			    //Atualizar Situacao do Arquivo Divisão
				fachada.atualizarArquivoTextoDivisaoEnviado(arquivoTextoRoteiroEmpresaDivisao.getId(),SituacaoTransmissaoLeitura.EM_CAMPO);
			   
			} catch (IOException e) {
				reportarErros(httpServletRequest, "erro.sistema");
			} 
		}else{
			throw new ActionServletException("atencao.arquivo_divisao_nao_pode_baixar");
		}
		
		return retorno;
	}

}

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

/*
  Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 27/08/2007
 */
public class RetornarArquivoTxtLeituraAction extends GcomAction {

 /*
  * Este caso de uso permite Retornar um Arquivo Txt Leitura
  * 
  * [UC0629] Retornar Arquivo Txt Leitura
  * 
  * 
  * @author Thiago Tenório e Thiago Nascimento
  * @date 29/01/2008
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

  String idArquivoTxt = httpServletRequest
    .getParameter("idRegistroAtualizacao");

  FiltroArquivoTextoRoteiroEmpresa filtroArquivoTextoRoteiroEmpresa = new FiltroArquivoTextoRoteiroEmpresa();
  filtroArquivoTextoRoteiroEmpresa
    .adicionarParametro(new ParametroSimples(
      FiltroArquivoTextoRoteiroEmpresa.ID, idArquivoTxt));

  Fachada fachada = Fachada.getInstancia();

  Collection colecaoArquivoTxtLeitura = fachada.pesquisar(
    filtroArquivoTextoRoteiroEmpresa,
    ArquivoTextoRoteiroEmpresa.class.getName());

  ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) colecaoArquivoTxtLeitura
    .iterator().next();
  if (arquivoTextoRoteiroEmpresa.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.LIBERADO)
			|| arquivoTextoRoteiroEmpresa.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.EM_CAMPO)) {
   
   try {
	   
	   boolean arquivoPossuiExtensaoGZ = arquivoTextoRoteiroEmpresa.getNomeArquivo().toUpperCase().endsWith(".GZ");

	   //se o arquivo não possuir extensão GZ
	   if(!arquivoPossuiExtensaoGZ){
			File temporario = File.createTempFile("temporario", ".txt"); 
		    FileOutputStream outputStream = new FileOutputStream(temporario);
		    try {
		    	
		    	if ( arquivoTextoRoteiroEmpresa.getArquivoTextoNaoRecebido() != null ){
					outputStream.write((
							  (StringBuilder) IoUtil.transformarBytesParaObjeto(
							    arquivoTextoRoteiroEmpresa.getArquivoTextoNaoRecebido())).toString().getBytes());		    		
		    	} else {
					outputStream.write((
							  (StringBuilder) IoUtil.transformarBytesParaObjeto(
							    arquivoTextoRoteiroEmpresa.getArquivoTexto())).toString().getBytes());		    		
		    	}
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
					"attachment; filename="+arquivoTextoRoteiroEmpresa.getNomeArquivo());
			ServletOutputStream out;

			out = httpServletResponse.getOutputStream();
			out.write(arquivo.toByteArray());
			out.flush();
			out.close();
		}else{   
		    httpServletResponse.setContentType("application/zip");
		    httpServletResponse.addHeader("Content-Disposition",
		      "attachment; filename="+ arquivoTextoRoteiroEmpresa.getNomeArquivo() );
		    ServletOutputStream out;
		 
		    out = httpServletResponse.getOutputStream();
		    
		    if ( arquivoTextoRoteiroEmpresa.getArquivoTextoNaoRecebido() != null ){
		    	out.write( arquivoTextoRoteiroEmpresa.getArquivoTextoNaoRecebido() );	
		    } else {
		    	out.write( arquivoTextoRoteiroEmpresa.getArquivoTexto() );
		    }
		    
		    
		    out.flush();
		    out.close();
		}
    
    //Atualizar Situacao do Arquivo
    fachada.atualizarArquivoTextoEnviado(arquivoTextoRoteiroEmpresa.getId(),SituacaoTransmissaoLeitura.EM_CAMPO);
   
   } catch (IOException e) {
    reportarErros(httpServletRequest, "erro.sistema");
   } 
  }else{
   throw new ActionServletException("atencao.arquivo_nao_pode_baixar");
  }
  return retorno;
 }
}

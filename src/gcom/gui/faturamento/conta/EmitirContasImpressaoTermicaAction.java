package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.EmitirSegundaViaContaInternetActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0820] Atualizar Faturamento do Movimento do Celular
 * 
 * @author Bruno Barros
 * @date 10/06/2009
 */

public class EmitirContasImpressaoTermicaAction extends ExibidorProcessamentoTarefaRelatorio {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("appletImpressao");
        
        EmitirContasImpressaoTermicaActionForm form = 
			(EmitirContasImpressaoTermicaActionForm) actionForm;
        
        
        
        try {
            DiskFileUpload upload = new DiskFileUpload();
            StringBuilder arquivoCompletoImpressaoTermica = new StringBuilder("");
            
            List itensForm = upload.parseRequest(httpServletRequest);
            Iterator iteItensForm = itensForm.iterator();
            
            Fachada fachada = Fachada.getInstancia();
            String matricula = "";
            String matriculaImovelUnico = "";
            
            while ( iteItensForm.hasNext() ){
                
                FileItem item = ( FileItem )iteItensForm.next();
                
                if(item.getFieldName().equals("matricula")){
                	matricula = item.getString();
                }else if(item.getFieldName().equals("matriculaImovelUnico")){
                	matriculaImovelUnico = item.getString();                
                // Caso não seja um field do formulario
                // é o arquivo
                }else if ( !item.isFormField() ){
                    // Lemos 
                    InputStreamReader reader = new InputStreamReader(item.getInputStream());
                    BufferedReader buffer = new BufferedReader(reader);
                   
                    
                    
                    if ( buffer != null  ) {  
                    	while (buffer.ready()) {
                    		arquivoCompletoImpressaoTermica.append(buffer.readLine()+"\n");
                    	}
                         buffer.close();
                        
                        }
                    httpServletRequest.setAttribute("stringImpressaoTermica", arquivoCompletoImpressaoTermica.toString());
                    }        			

                }
            
            String stringImpressao = arquivoCompletoImpressaoTermica.toString();
            
            if((matricula == null
            		|| matricula.equals("")
					|| matricula.equals("0"))
			&& (matriculaImovelUnico == null
					|| matriculaImovelUnico.equals("")
					|| matriculaImovelUnico.equals("0") ) ){
						httpServletRequest.setAttribute("stringImpressaoTermica", stringImpressao);
														
			}else if (matricula != null
            		&& !matricula.equals("")
					&& !matricula.equals("0")){
				String token = "T 7 1 464 90 " + matricula;
				boolean imprimir = false;
							if(stringImpressao.contains(token)){
								stringImpressao = stringImpressao.substring(stringImpressao.lastIndexOf(token));
								if(stringImpressao.indexOf("! ") == -1){
									throw new ActionServletException("erro.matricula_pertencente_ao_ultimo_imovel_do_arquivo");
								}else{
									stringImpressao = stringImpressao.substring(stringImpressao.indexOf("! "));
									imprimir = true;
								}
							}else{
								throw new ActionServletException("erro.matricula_inexistente_no_arquivo_selecionado");
							}
							if(imprimir){
								httpServletRequest.setAttribute("stringImpressaoTermica", stringImpressao);
							}
			}else{
				String token = "T 7 1 464 90 " + matriculaImovelUnico;
				boolean imprimir = false;
				String novaStringImpressao = "";
							if(stringImpressao.contains(token)){
								
								
								int indiceInicial = 0;
								
								String[] impressaoIndividual = stringImpressao.split("! ");
								
								for (int i = 0; i < impressaoIndividual.length; i++) {
									if(impressaoIndividual[i].contains(token)){
										novaStringImpressao = "! "+impressaoIndividual[i];
										imprimir = true;
										break;
									}
								}
							}else{
								throw new ActionServletException("erro.matricula_inexistente_no_arquivo_selecionado");
							}
							if(imprimir){
								httpServletRequest.setAttribute("stringImpressaoTermica", novaStringImpressao);
							}
			}
           
            
            
            return retorno;            
            
          
        } catch (FileUploadException e) {
            throw new ActionServletException("erro.atualizacao.nao_concluida");        
        } catch (IOException e) {
            throw new ActionServletException("erro.atualizacao.nao_concluida");        
        }
       
    }
    
}

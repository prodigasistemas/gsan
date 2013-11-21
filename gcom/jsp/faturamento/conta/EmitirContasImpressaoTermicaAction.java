/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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
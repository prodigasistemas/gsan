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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

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
 * [UC1225] Incluir Dados Acompanhamento de Serviço
 * 
 * Este caso de uso permite a inserção de dados na tabela de ordem de serviço para acompanhamento do serviço.
 * 
 * @author Thúlio Araújo
 * @date 22/09/2011
 */
public class AtualizarOrdemServicoAcompanhamentoCelularAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        try {
            DiskFileUpload upload = new DiskFileUpload();
            
            // Parse the request
            List itensForm = upload.parseRequest(httpServletRequest);
            Iterator iteItensForm = itensForm.iterator();
            
            Fachada fachada = Fachada.getInstancia();            
            
            while ( iteItensForm.hasNext() ){
                
                FileItem item = ( FileItem )iteItensForm.next();
                
                // Caso não seja um field do formulario
                // é o arquivo
                if ( !item.isFormField() ){
                    // Lemos 
                    InputStreamReader reader = new InputStreamReader(item.getInputStream());
                    BufferedReader buffer = new BufferedReader(reader);
                    
                    //[FS0002] - Verificar existência de dados no arquivo
                    //Caso o arquivo esteja sem dados, exibir a mensagem ?Arquivo <<nome do arquivo >> sem dados?
                    //e cancelar a operação.
                    if ( buffer != null  ) {  
                    	fachada.retornoAtualizarOrdemServicoAcompanhamentoServico(buffer);
                    }else{
                    	throw new ActionServletException("atencao.ordem_servico_atualiza_celular_sem_dados", item.getFieldName());
                    }
                }                
            }
            
        	// montando página de sucesso
    		montarPaginaSucesso(httpServletRequest,
    			"Ordem Serviço Movimento Celular Atualizado com sucesso", 
    			"Voltar",
    			"/exibirAtualizarOrdemServicoAcompanhamentoCelularAction.do");
            
            return retorno;            
          
        } catch (FileUploadException e) {
            throw new ActionServletException("erro.atualizacao.nao_concluida");        
        } catch (IOException e) {
            throw new ActionServletException("erro.atualizacao.nao_concluida");        
        }       
    }    
}
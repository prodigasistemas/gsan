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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da atualização de um R.A (Aba nº 04 - Anexos) 
 *
 * @author Raphael Rossiter
 * @date 27/07/2009
 */
public class ExibirAtualizarRegistroAtendimentoAnexosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarRegistroAtendimentoAnexos");
        
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);
        
        String adicionar = httpServletRequest.getParameter("adicionar");
		String remover = httpServletRequest.getParameter("remover");
		String visualizar = httpServletRequest.getParameter("visualizar");
		
		//ADICIONANDO UM ARQUIVO
		if (adicionar != null && !adicionar.equals("")){
			
			Object[] parametrosFormulario = null;
	        FileItem arquivoInformado = null;
	        String observacaoAnexo = null;
			
			//ARQUIVO
			try{
				
				parametrosFormulario = recebendoObjetos(httpServletRequest);
			
			}
			catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}
			
			arquivoInformado = (FileItem) parametrosFormulario[0];
			observacaoAnexo = (String) parametrosFormulario[1];
			
			//VALIDAÇÃO DO ARQUIVO
			fachada.validarRegistroAtendimentoAnexos(arquivoInformado);
			
			RegistroAtendimentoAnexo registroAtendimentoanexo = this.gerarRegistroAtendimentoAnexo(
			arquivoInformado, observacaoAnexo);
			
			//INSERINDO O ARQUIVO NA COLEÇÃO DE VISUALIZAÇÃO
			Collection colecaoRegistroAtendimentoAnexo = null;
			
			if (sessao.getAttribute("colecaoRegistroAtendimentoAnexo") != null){
				
				colecaoRegistroAtendimentoAnexo = (Collection) 
				sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
				
				colecaoRegistroAtendimentoAnexo.add(registroAtendimentoanexo);
				
			}
			else{
				
				colecaoRegistroAtendimentoAnexo = new ArrayList();
				colecaoRegistroAtendimentoAnexo.add(registroAtendimentoanexo);
				
				sessao.setAttribute("colecaoRegistroAtendimentoAnexo", colecaoRegistroAtendimentoAnexo);
			}
		}
		
		//REMOVENDO UM ARQUIVO
		this.removerArquivo(remover, sessao);
		
		//OBTENDO ARQUIVO PARA VISUALIZAÇÃO
		RegistroAtendimentoAnexo registroAtendimentoAnexo = this.obterArquivoParaVisualizacao(visualizar, sessao);
		
		//PREPARANDO VISUALIZAÇÃO DO ARQUIVO
		if (registroAtendimentoAnexo != null){
			
			OutputStream out = null;
			
			String mimeType = ConstantesSistema.CONTENT_TYPE_GENERICO;
			
			if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_DOC)){
				mimeType = ConstantesSistema.CONTENT_TYPE_MSWORD;
			}
			else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_PDF)){
				mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
			}
			else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_JPG)){
				mimeType = ConstantesSistema.CONTENT_TYPE_JPEG;
			}
			
			try {
				httpServletResponse.setContentType(mimeType);
				out = httpServletResponse.getOutputStream();
				
				out.write(registroAtendimentoAnexo.getImagemDocumento());
				out.flush();
				out.close();
			} 
			catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
			}
		}
		
		//CARREGANDO OS ANEXOS QUE ESTÃO CADASTRADOS NA BASE
		if (sessao.getAttribute("colecaoRegistroAtendimentoAnexo") == null) {
			
			StatusWizard statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");
			
			FiltroRegistroAtendimentoAnexo filtroRegistroAtendimentoAnexo = new FiltroRegistroAtendimentoAnexo();
			
			filtroRegistroAtendimentoAnexo.adicionarParametro(new ParametroSimples(
			FiltroRegistroAtendimentoAnexo.REGISTRO_ATENDIMENTO_ID,
			statusWizard.getId()));

			Collection colecaoRegistroAtendimentoAnexo = fachada.pesquisar(filtroRegistroAtendimentoAnexo,
			RegistroAtendimentoAnexo.class.getName());
			
			sessao.setAttribute("colecaoRegistroAtendimentoAnexo", colecaoRegistroAtendimentoAnexo);
		}
		
		httpServletRequest.setAttribute("nomeCampo", "arquivoAnexo");
        
        return retorno;
	}
	
	/**
	 * Removendo um arquivo da coleção
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private void removerArquivo(String identificacao, HttpSession sessao){
		
		if (identificacao != null && !identificacao.equals("")){
			
			Collection colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					colecaoRegistroAtendimentoAnexo.remove(anexoColecao);
					break;
				}
			}
		}
	}
	
	/**
	 * Realizando o upload do arquivo informado
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param HttpServletRequest
	 */
	private Object[] recebendoObjetos(HttpServletRequest httpServletRequest) throws FileUploadException {
		
		Object[] parametrosFormulario = new Object[2]; 
		
		DiskFileUpload upload = new DiskFileUpload();
		
		List itens = upload.parseRequest(httpServletRequest);
		FileItem fileItem = null;
		
		if (itens != null) {
			
			Iterator iter = itens.iterator();
			
			while (iter.hasNext()) {
				
				fileItem = (FileItem) iter.next();
				
				if (fileItem.getFieldName().equals("arquivoAnexo")) {
					
					parametrosFormulario[0] = fileItem;
				}
				
				if (fileItem.getFieldName().equals("observacaoAnexo")) {
					
					parametrosFormulario[1] = fileItem.getString();
				}
			}
		}
		
		return parametrosFormulario;
	}
	
	/**
	 * Gerando o objeto que referencia o arquivo que será anexado ao RA
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param FileItem
	 * @param String
	 */
	private RegistroAtendimentoAnexo gerarRegistroAtendimentoAnexo(FileItem arquivoAnexo, 
			String observacaoAnexo){
		
		RegistroAtendimentoAnexo anexo = new RegistroAtendimentoAnexo();
		
		//ARQUIVO EM BYTES
		anexo.setImagemDocumento(arquivoAnexo.get());
		
		//EXTENSÃO
		anexo.setNomeExtensaoDocumento(Util.obterExtensaoDoArquivo(arquivoAnexo));
		
		//OBSERVAÇÃO
		if (observacaoAnexo != null && !observacaoAnexo.equals("")){
			
			anexo.setDescricaoDocumento(observacaoAnexo.trim());
		}
		
		//ÚLTIMA ALTERAÇÃO
		anexo.setUltimaAlteracao(new Date());
		
		return anexo;
		
	}
	
	/**
	 * Removendo um arquivo da coleção
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private RegistroAtendimentoAnexo obterArquivoParaVisualizacao(String identificacao, HttpSession sessao){
		
		RegistroAtendimentoAnexo registroAtendimentoAnexo = null;
		
		if (identificacao != null && !identificacao.equals("")){
			
			Collection colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					registroAtendimentoAnexo = anexoColecao;
					break;
				}
			}
		}
		
		return registroAtendimentoAnexo;
	}
}

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
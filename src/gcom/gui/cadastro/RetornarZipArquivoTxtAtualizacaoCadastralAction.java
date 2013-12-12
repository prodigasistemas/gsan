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
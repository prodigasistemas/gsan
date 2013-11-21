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
package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável para dar um onLoad no arquivo e importar os ceps.
 * 
 * @author Sávio Luiz
 * @created 24 de Agosto de 2005
 */
public class ImportarCepAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		try {

			// FileUpload fup = new FileUpload();
			/* boolean isMultipart = */FileUpload
					.isMultipartContent(httpServletRequest);

			DiskFileUpload upload = new DiskFileUpload();

			// Parse the request
			List items = upload.parseRequest(httpServletRequest);

			Fachada fachada = Fachada.getInstancia();
			// Cria a coleção de cep que serão importados
			Collection<Cep> colecaoCepImportadas = new ArrayList();

			FileItem item = null;

			// pega uma lista de itens do form
			Iterator iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();
				// verifica se não é diretorio
				if (!item.isFormField()) {
					// coloca o nome do item para maiusculo
					String nomeItem = item.getName().toUpperCase();
					// compara o final do nome do arquivo é .txt
					if (nomeItem.endsWith(".TXT")) {

						// abre o arquivo
						InputStreamReader reader = new InputStreamReader(item
								.getInputStream());

						BufferedReader buffer = new BufferedReader(reader);
						// StringBuffer linha = new StringBuffer();
						// cria uma variavel do tipo boolean
						boolean eof = false;
						// enquanto a variavel for false
						int i= 0;
						while (!eof) {
							// pega a linha do arquivo
							String linhaLida = buffer.readLine();
							// se for a ultima linha do arquivo
							if (linhaLida == null) {
								// seta a variavel boolean para true
								eof = true;
							} else {
								i++;
								System.out.println(i);
								// cria um objeto do tipo cep
								Cep cep = new Cep();
								// caso a linha tenha menos de 160 digitos na
								// linha
								// então não entra no if
								// senão cria o objeto para a inclusão
								if (linhaLida.length() >= 160) {
									cep.setSigla(linhaLida.substring(0, 2)
											.trim());
									cep.setLogradouro((linhaLida.substring(2,
											62).trim()));
									if (linhaLida.substring(62, 70).trim().length() > 0) {
										int codigoCep = Integer
												.parseInt(linhaLida.substring(
														62, 70).trim());
										cep.setCodigo(new Integer(codigoCep));
									}
									cep.setMunicipio(linhaLida.substring(70,
											100).trim());
									cep.setBairro(linhaLida.substring(100, 130)
											.trim());
									cep.setDescricaoTipoLogradouro(linhaLida
											.substring(130, 160).trim());
									cep
											.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
									cep.setUltimaAlteracao(new Date());
									cep.setCepTipo(null);
									colecaoCepImportadas.add(cep);
								}
							}

						}
						// fecha o arquivo
						buffer.close();
						reader.close();
						item.getInputStream().close();

					} else {
						throw new ActionServletException(
								"atencao.tipo_importacao.nao_txt");
					}

				}
			}
			if (colecaoCepImportadas != null && !colecaoCepImportadas.isEmpty()) {
				fachada.inserirCepImportados(colecaoCepImportadas);
			} else {
				throw new ActionServletException("atencao.arquivo_sem_dados",
						null, item.getName());
			}

		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");

		} catch (NumberFormatException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}
		montarPaginaSucesso(httpServletRequest,
				"Cep(s) importado(s) com sucesso!", "Importar outro(s) cep(s)",
				"exibirImportarCepAction.do");

		return retorno;
	}

}

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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
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
 * Action responsável para dar um onLoad no arquivo e importar os ceps.
 * 
 * @author Sávio Luiz, Raphael Rossiter
 * @created 24/08/2005, 02/09/2009
 */
public class RegistrarLeiturasAnormalidadesAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		try {

			String idFaturamentoGrupo = null;

			String movimentoCelular = null;

			DiskFileUpload upload = new DiskFileUpload();

			FaturamentoGrupo faturamentoGrupo = null;

			// UPLOAD DO ARQUIVO
			List items = upload.parseRequest(httpServletRequest);

			Fachada fachada = Fachada.getInstancia();
			
			Collection<MedicaoHistorico> colecaoMedicaoHistorico = new ArrayList();

			FileItem item = null;

			// NOME DO ARQUIVO
			String nomeItem = null;

			Iterator iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();

				if (item.getFieldName().equals("idFaturamentoGrupo")) {
					idFaturamentoGrupo = item.getString();
				}

				if (item.getFieldName().equals("movimentoCelular")) {
					movimentoCelular = item.getString();
				}
				
				if (idFaturamentoGrupo != null && !idFaturamentoGrupo.equals("")){
					
					//CARREGANDO OS DADOS DO GRUPO DE FATURAMENTO ONDE SERÃO REGISTRADAS AS LEITURAS
					FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
					
					filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, idFaturamentoGrupo));
					
					Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo,
					FaturamentoGrupo.class.getName());
					
					if (colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()) {
						
						faturamentoGrupo = (FaturamentoGrupo) 
						Util.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
					} 
					else {
						
						throw new ActionServletException("atencao.entidade.inexistente",null,"Faturamento Grupo");
					}
				}

				if (movimentoCelular != null) {

					/*
					 * Caso o movimento seja proveniente de celular; será necessário recuperar os dados
					 * do movimento daquele grupo.
					 */
					if (movimentoCelular.equals("1")) {
						
						//RECUPERANDO OS DADOS DAS MEDIÇÕES
						colecaoMedicaoHistorico = 
						fachada.recuperarMedicoesHistorico(new Integer(idFaturamentoGrupo));
						
					} 
					else {

						// VERIFICA SE NÃO FOI INFORMADO UM DIRETÓRIO
						if (!item.isFormField()) {
							
							// COLOCA O NOME DO ARQUIVO PARA MAIÚSCULO
							nomeItem = item.getName().toUpperCase();
							
							// VERIFICA SE O ARQUIVO ESTÁ COM A EXTENSÃO .TXT
							if (nomeItem.endsWith(".TXT")) {

								colecaoMedicaoHistorico = fachada.processarArquivoTextoParaRegistrarLeiturasAnormalidades(
								new Integer(idFaturamentoGrupo), item);

							} 
							else {
								
								throw new ActionServletException("atencao.tipo_importacao.nao_txt");
							}

						}
					}
				}
			}
			
			
			//VARIFICANDO SE EXISTE ALGUMA MEDIÇÃO DIPONÍVEL PARA REGISTRAR 
			if (colecaoMedicaoHistorico != null && !colecaoMedicaoHistorico.isEmpty()) {

				fachada.registrarLeiturasAnormalidades(colecaoMedicaoHistorico,
						faturamentoGrupo.getId(), faturamentoGrupo.getAnoMesReferencia(),
						getUsuarioLogado(httpServletRequest), nomeItem);

			} 
			else if (movimentoCelular != null && movimentoCelular.endsWith("1")) {
				
				throw new ActionServletException(
				"atencao.movimento_roteiro_empresa_dados", null, idFaturamentoGrupo);
			} 
			else {
				
				throw new ActionServletException(
				"atencao.arquivo_sem_dados", null, item.getName());
			}
			

			
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new ActionServletException("erro.sistema", e);
		}
		

		// MONTANDO A PÁGINA DE SUCESSO
		montarPaginaSucesso(httpServletRequest,
		"Leituras e Anormalidades Enviado para Processamento",
		"Voltar", "/exibirRegistrarLeiturasAnormalidadesAction.do");
		
		
		return retorno;
	}
}

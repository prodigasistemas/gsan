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
package gcom.gui.cobranca;

import gcom.cobranca.bean.OrdemCorteHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioOrdemCorte;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class GerarOrdemCorteAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarOrdem");
		
		DiskFileUpload upload = new DiskFileUpload();
		List items = null;
		try {
			items = upload.parseRequest(httpServletRequest);
			this.getSessao(httpServletRequest).setAttribute("arquivo",items);
		} catch (FileUploadException e) {
			items = (List) this.getSessao(httpServletRequest).getAttribute("arquivo");
			if(items == null){
				throw new ActionServletException("erro.sistema", e);
			}
			
		}
		
		Collection colecaoOrdemCorteHelper = 
			this.gerarColecaoOrdemCorteHelper(items);
		
		FileItem item = (FileItem) Util.retonarObjetoDeColecao(items);
		String nomeArquivo = item.getName().replace(".txt","");
		nomeArquivo = nomeArquivo.replace("EMITIR","");
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioOrdemCorte relatorio = 
			new RelatorioOrdemCorte(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("colecaoOrdemCorteHelper", colecaoOrdemCorteHelper);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("nomeArquivo",nomeArquivo);
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
	private Collection gerarColecaoOrdemCorteHelper(List items){
		
		Collection retorno = new ArrayList();
		
		FileItem item = null;
		String nomeItem = null;
		
		// Parse the request
		try {
			
			Iterator iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();

				// verifica se não é diretorio
				if (!item.isFormField()) {
					// coloca o nome do item para maiusculo
					nomeItem = item.getName().toUpperCase();
					// compara o final do nome do arquivo é .txt
					if (nomeItem.endsWith(".TXT")) {

						// abre o arquivo
						InputStreamReader reader = new InputStreamReader(item.getInputStream());

						BufferedReader buffer = new BufferedReader(reader);
						
						// cria uma variavel do tipo boolean
						boolean eof = false;
						
						OrdemCorteHelper ordemCorteHelper = null;
						
						// enquanto a variavel for false
						while (!eof) {
							// pega a linha do arquivo
							String linhaLida = buffer.readLine();

							// se for a ultima linha do arquivo
							if (linhaLida == null || linhaLida.trim().length() == 0) {
								// seta a variavel boolean para true
								eof = true;
							} else {
								
								ordemCorteHelper = new OrdemCorteHelper();
								
								//1-Numero da OS (Campo 27 do TXT)
								ordemCorteHelper.setNumero(linhaLida.substring(1466, 1474).trim());
								
								//2-Nome do Cliente Usuario (Campo 13 do TXT)
								ordemCorteHelper.setNomeCliente(linhaLida.substring(409, 459).trim());

								//3-Endereco de Imovel - Parte 1 (Campo 9 do TXT)
								ordemCorteHelper.setEndereco(linhaLida.substring(67, 167).trim());
								
								//4-Bairro (Campo 10.1 do TXT)
								ordemCorteHelper.setEnderecoLinha2(linhaLida.substring(167, 197).trim());
								
								//5,6,7-Municipio,UF,CEP (Campo 10.2,10.3,10.4 do TXT)
								String enderecoLinha3 = linhaLida.substring(197, 227).trim() +" "+
									linhaLida.substring(227, 229).trim() +" "+
									linhaLida.substring(229, 238).trim();
								
								ordemCorteHelper.setEnderecoLinha3(enderecoLinha3);
								
								//8-Rota/Grupo de Cobrança (Campo 4,4.1,3 do TXT)
								String rotaGrupo = linhaLida.substring(16, 22).trim()+"."+
									linhaLida.substring(22, 26).trim()+"/"+
									linhaLida.substring(14, 16).trim();
								
								ordemCorteHelper.setRotaGrupo(rotaGrupo);
								
								//9-Matricula do Imovel (Campo 7 do TXT)
								ordemCorteHelper.setMatricula(linhaLida.substring(38, 47).trim());
								
								//10-Inscricao do Imovel (Campo 8 do TXT)
								ordemCorteHelper.setInscricao(linhaLida.substring(47, 67).trim());
								
								//11-Data de Emissao(Campo 21 do TXT)
								ordemCorteHelper.setDataEmissao(linhaLida.substring(1264, 1274).trim());
								
								//12
								//13
								
								//14-Numero do Hidrometro (Campo 23 do TXT)
								ordemCorteHelper.setNumeroHidrometro(linhaLida.substring(1284, 1294).trim());
								
								//15-Local de Instalação do Hidrometro (Campo 24 do TXT)
								ordemCorteHelper.setLocalizacaoHidrometro(linhaLida.substring(1294, 1299).trim());

								//16-Data de Vencimento (Campo 22 do TXT)
								ordemCorteHelper.setDataVencimento(linhaLida.substring(1274, 1284).trim());
								
								//17-Somatorio do Valor Total das Contas (Campo 16 do TXT)
								ordemCorteHelper.setValor(linhaLida.substring(1194, 1208).trim());
								
								//18-Codigo de Barras Formatado (Campo 25 do TXT)
								String codigoBarraFormatado = linhaLida.substring(1299,1354).trim();
								ordemCorteHelper.setRepresentacaoNumericaCodBarraFormatada(codigoBarraFormatado);
								
								//19-Codigo de Barras Sem os digitos (Campo 26 do TXT)
								
								//Representação numerica cod barra sem digito
								String codigoBarra = codigoBarraFormatado.substring(0,11)
									+ codigoBarraFormatado.substring(14, 25)
									+ codigoBarraFormatado.substring(28, 39)
									+ codigoBarraFormatado.substring(42, 53);
								
								ordemCorteHelper.setRepresentacaoNumericaCodBarraSemDigito(codigoBarra);
								
								//20-Data de Emissao (Campo 21 do TXT)
								ordemCorteHelper.setDataEmissao(linhaLida.substring(1264, 1274).trim());
								
								//21-Situacao da Ligação de Agua (Campo 28 do TXT)
								ordemCorteHelper.setSituacaoLigacaoAgua(linhaLida.substring(1474, 1494).trim());
								
								//22-Situacao da Ligacao de Esgoto (Campo 29 do TXT)
								ordemCorteHelper.setSituacaoLigacaoEsgoto(linhaLida.substring(1494, 1514).trim());
								
								//23-Categoria Principal (Campo 30 do TXT)
								ordemCorteHelper.setCategoriaPrincipal(linhaLida.substring(1514, 1529).trim());
								
								retorno.add(ordemCorteHelper);
							}
						}//fim do while eof
					} else {
						throw new ActionServletException("atencao.tipo_importacao.nao_txt");
					}
				}	
			}

		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		}
	
		return retorno;
	}
	

}
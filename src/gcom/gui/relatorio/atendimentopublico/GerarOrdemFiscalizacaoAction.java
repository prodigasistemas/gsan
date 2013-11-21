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

package gcom.gui.relatorio.atendimentopublico;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioOrdemFiscalizacao;
import gcom.relatorio.atendimentopublico.RelatorioOrdemFiscalizacaoBean;
import gcom.relatorio.atendimentopublico.RelatorioOrdemFiscalizacaoFaturasBean;
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

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Raphael Rossiter
 * @data 02/01/2008
 */
public class GerarOrdemFiscalizacaoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("gerarOrdemFiscalizacao");
		
		//REALIZANDO O UPLOAD DO ARQUIVO INFORMADO =========================================
		DiskFileUpload upload = new DiskFileUpload();
		List itens = null;
		try {
			itens = upload.parseRequest(httpServletRequest);
			this.getSessao(httpServletRequest).setAttribute("arquivo",itens);
		} catch (FileUploadException e) {
			itens = (List) this.getSessao(httpServletRequest).getAttribute("arquivo");
			if(itens == null){
				throw new ActionServletException("erro.sistema", e);
			}
		}
		
		//==================================================================================
		
		//Carregar objeto BEAN para montagem do relatório ==================================
		
		Collection colecaoOrdemFiscalizacao = this.gerarColecaoOrdemFiscalizacaoHelper(itens);
		
		//==================================================================================
		
		
		//Definindo em que formato o relatório será gerado =================================
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		//==================================================================================
		
		
		//Renomeando o arquivo =============================================================
		FileItem item = (FileItem) Util.retonarObjetoDeColecao(itens);
		
		String nomeArquivo = this.tratarNomeArquivo(item.getName());
		
		nomeArquivo = nomeArquivo.replace(".txt","");
		nomeArquivo = nomeArquivo.replace("EMITIR","RELATORIO");
		//==================================================================================
		
		
		RelatorioOrdemFiscalizacao relatorio = 
		new RelatorioOrdemFiscalizacao(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("colecaoOrdemFiscalizacao", colecaoOrdemFiscalizacao);
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("nomeArquivo", nomeArquivo);
		
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
		httpServletResponse, actionMapping); 

		
		return retorno;
	}
	
	
	private Collection gerarColecaoOrdemFiscalizacaoHelper(List itens){
		
		Collection retorno = 
			new ArrayList();
		
		FileItem item = null;
		String nomeItem = null;
		
		// Parse the request
		try {
			
			Iterator iter = itens.iterator();
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
						
						RelatorioOrdemFiscalizacaoBean relatorioOrdemFiscalizacaoBean = null;
						
						// enquanto a variavel for false
						while (!eof) {
							// pega a linha do arquivo
							String linhaLida = buffer.readLine();
							
							// se for a ultima linha do arquivo
							if (linhaLida == null || linhaLida.trim().length() == 0) {
								// seta a variavel boolean para true
								eof = true;
							} else {
								
								relatorioOrdemFiscalizacaoBean = new RelatorioOrdemFiscalizacaoBean();
								
								//-Sequencial de Impressão
								relatorioOrdemFiscalizacaoBean.setSequencial(linhaLida.substring(7,14).trim());
								
								//-Grupo
								relatorioOrdemFiscalizacaoBean.setGrupo(linhaLida.substring(14,16).trim());
								
								//-Rota.SeqRota
								relatorioOrdemFiscalizacaoBean.setRotaSeqRota(linhaLida.substring(16, 22).trim()+"."+
								linhaLida.substring(22, 26).trim());
								
								//-Matricula
								relatorioOrdemFiscalizacaoBean.setMatricula(linhaLida.substring(38, 47).trim());
								
								//-Inscricao
								relatorioOrdemFiscalizacaoBean.setInscricao(linhaLida.substring(47, 67).trim());
								
								//-Endereco - Parte 1 + Parte 2 + Parte 3
								relatorioOrdemFiscalizacaoBean.setEndereco(
								linhaLida.substring(67,167).trim() +
								" " + linhaLida.substring(167, 197).trim() + 
								" " + linhaLida.substring(197, 227).trim()
								+ " - " +linhaLida.substring(227, 229).trim()
								+ "   " +linhaLida.substring(229, 238).trim());
								
								//-Nome do Cliente
								relatorioOrdemFiscalizacaoBean.setNomeCliente(linhaLida.substring(409, 459).trim());
								
								//-Valor Total do Debito
								relatorioOrdemFiscalizacaoBean.setValorDebito(linhaLida.substring(1194, 1208).trim());
								
								//-Data Emissao
								relatorioOrdemFiscalizacaoBean.setDataEmissao(linhaLida.substring(1264, 1274).trim());
								
								//-Descricao Situacao Ligacao Agua
								relatorioOrdemFiscalizacaoBean.setLigacaoAguaSituacao(linhaLida.substring(1475, 1495).trim());
								
								//-Descricao Situacao Ligacao Esgoto
								relatorioOrdemFiscalizacaoBean.setLigacaoEsgotoSituacao(linhaLida.substring(1495, 1515).trim());
								
//								//ITEM 23 - Número do Hidrômetro
								relatorioOrdemFiscalizacaoBean.setNumeroHidrometro(linhaLida.substring(1284, 1294).trim());
								
								//ITEM 31 - Consumo Médio
								relatorioOrdemFiscalizacaoBean.setConsumoMedio(linhaLida.substring(1530, 1540).trim());
								
								//ITEM 32 - Consumo Fixo
								relatorioOrdemFiscalizacaoBean.setConsumoFixo(linhaLida.substring(1540, 1550).trim());
								
								//ITEM 33 - Residêncial
								relatorioOrdemFiscalizacaoBean.setQtdeEconResidencial(linhaLida.substring(1550, 1553).trim());
								
								//ITEM 34 - Comercial
								relatorioOrdemFiscalizacaoBean.setQtdeEconComercial(linhaLida.substring(1553, 1556).trim());
								
								//ITEM 35 - Industrial
								relatorioOrdemFiscalizacaoBean.setQtdeEconIndustrial(linhaLida.substring(1556, 1559).trim());
								
								//ITEM 36 - Público
								relatorioOrdemFiscalizacaoBean.setQtdeEconPublica(linhaLida.substring(1559, 1562).trim());
								
								//ITEM 37 - Soma Total das economias
								relatorioOrdemFiscalizacaoBean.setQtdeEconTotal(linhaLida.substring(1562, 1566).trim());
								
								//ITEM 38 - Data da Posição do Débito
								relatorioOrdemFiscalizacaoBean.setDataPosicaoDebito(linhaLida.substring(1566, 1576).trim());
								
								//ITEM 39 - Data do Corte
								relatorioOrdemFiscalizacaoBean.setDataCorte(linhaLida.substring(1576, 1586).trim());
								
								//ITEM 40 - Data da Supressão
								relatorioOrdemFiscalizacaoBean.setDataSupressaoTotal(linhaLida.substring(1586, 1596).trim());
								
								//ITEM 41 - Origem
								relatorioOrdemFiscalizacaoBean.setOrigem(linhaLida.substring(1596, 1611).trim());
								
								//ITEM 42 - Ocorrência
								relatorioOrdemFiscalizacaoBean.setOcorrencia(linhaLida.substring(1611, 1645).trim());
								
								//ITEM 43 - Data Última Alteração
								relatorioOrdemFiscalizacaoBean.setUltimaAlteracao(linhaLida.substring(1645, 1655).trim());
								
								//ITEM 44 - Ordem de Serviço
								relatorioOrdemFiscalizacaoBean.setOrdemFiscalizacao(linhaLida.substring(1655, 1670).trim());
								
								//ITEM 45 - Tipo de Consumidor (ImovelPerfil da tabela CobrancaDocumento)
								relatorioOrdemFiscalizacaoBean.setTipoConsumidor(linhaLida.substring(1670, 1690).trim());
								
								
								ArrayList colecaoDebito = new ArrayList();
								RelatorioOrdemFiscalizacaoFaturasBean relatorioOrdemFiscalizacaoFaturasBean = null;

								//anoMes LINHA 1
								String anoMesFatura = linhaLida.substring(462,469).trim();
								String valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(481, 495).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(471,481).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 2
								anoMesFatura = linhaLida.substring(523,530).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(542, 556).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(532,542).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 3
								anoMesFatura = linhaLida.substring(584,591).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(603, 617).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(593,603).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 4
								anoMesFatura = linhaLida.substring(645,652).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(664, 678).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(654,664).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 5
								anoMesFatura = linhaLida.substring(706,713).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(725, 739).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(715,725).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 6
								anoMesFatura = linhaLida.substring(767,774).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(786, 800).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(776,786).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 7
								anoMesFatura = linhaLida.substring(828,835).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(847,861).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(837,847).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 8
								anoMesFatura = linhaLida.substring(889,896).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(908,922).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(898,908).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 9
								anoMesFatura = linhaLida.substring(950,957).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(969,983).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(959,969).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 10
								anoMesFatura = linhaLida.substring(1011,1018).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(1030,1044).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(1020,1030).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 11
								anoMesFatura = linhaLida.substring(1072,1079).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(1091,1105).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(1081,1091).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								//anoMes LINHA 12
								anoMesFatura = linhaLida.substring(1133,1140).trim();
								valor = "";
								
								if(!anoMesFatura.equals("")){
									valor = linhaLida.substring(1152,1166).trim();
									
									relatorioOrdemFiscalizacaoFaturasBean = new RelatorioOrdemFiscalizacaoFaturasBean();
									relatorioOrdemFiscalizacaoFaturasBean.setFaturaAtraso(anoMesFatura);
									relatorioOrdemFiscalizacaoFaturasBean.setValor(valor);
									relatorioOrdemFiscalizacaoFaturasBean.setDataVencimento(linhaLida.substring(1142,1152).trim());
									
									colecaoDebito.add(relatorioOrdemFiscalizacaoFaturasBean);
								}
								
								
								relatorioOrdemFiscalizacaoBean.setArrayRelatorioOrdemFiscalizacaoFaturasBean(colecaoDebito);
								relatorioOrdemFiscalizacaoBean.setArrayJRFaturas(new JRBeanCollectionDataSource(colecaoDebito));

								retorno.add(relatorioOrdemFiscalizacaoBean);
							}
						}//fim do while eof
					}
				}	
			}

		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		}
	
		return retorno;
	}
	
	
	public String tratarNomeArquivo(String caminho) {
		
		if (caminho.indexOf("\\") != -1){
			
			caminho = caminho.substring(caminho.lastIndexOf("\\") + 1);
		}
	
		return caminho;
	}
}

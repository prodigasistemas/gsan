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
package gcom.gui.relatorio.faturamento;

import gcom.faturamento.bean.EmitirContaTipo2Helper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioContaTipo2;
import gcom.relatorio.faturamento.conta.ContaLinhasDescricaoServicosTarifasTotalHelper;
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

public class GerarContasAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarContas");
		
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
		
		Collection colecaoEmitirContaTipo2Helper = 
			this.gerarColecaoEmitirContaTipo2Helper(items);
		
		FileItem item = (FileItem) Util.retonarObjetoDeColecao(items);
		String nomeArquivo = item.getName().replace(".txt","");
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioContaTipo2 relatorio = 
			new RelatorioContaTipo2(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("colecaoEmitirContaTipo2Helper", colecaoEmitirContaTipo2Helper);
		
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
	
	private Collection gerarColecaoEmitirContaTipo2Helper(List items){
		
		Collection retorno = 
			new ArrayList();
		
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
						
						EmitirContaTipo2Helper emitirContaTipo2Helper = null;
						
						// enquanto a variavel for false
						while (!eof) {
							// pega a linha do arquivo
							String linhaLida = buffer.readLine();

							// se for a ultima linha do arquivo
							if (linhaLida == null || linhaLida.trim().length() == 0) {
								// seta a variavel boolean para true
								eof = true;
							} else {
								
								emitirContaTipo2Helper = new EmitirContaTipo2Helper();
								
								//1-Número da Conta
								emitirContaTipo2Helper.setNumeroConta(linhaLida.substring(0, 10).trim());
								
								//2-Nome do Cliente
								emitirContaTipo2Helper.setNomeCliente(linhaLida.substring(10, 60).trim());

								//3-Endereco - Parte 1
								emitirContaTipo2Helper.setEndereco(linhaLida.substring(60, 160).trim());
								
								//4-Endereco - Parte 2
								emitirContaTipo2Helper.setBairro(linhaLida.substring(160, 190).trim());
								
								//5-Endereco - Parte 3
								emitirContaTipo2Helper.setEnderecoLinha3(linhaLida.substring(190, 231).trim());
								
								//8-Referencia
								emitirContaTipo2Helper.setMesAnoConta(linhaLida.substring(231, 238).trim());
								
								//9-Vencimento
								//Nesse caso pega o primeiro vencimento da conta dos vencimento alternativos
								//Anterior - linhaLida.substring(238, 248)
								emitirContaTipo2Helper.setDataVencimento(linhaLida.substring(1075,1085).trim());
								
								//10-Matricula do imovel
								emitirContaTipo2Helper.setMatricula(linhaLida.substring(248, 256).trim());
								
								//11-Inscricao do Imovel
								emitirContaTipo2Helper.setInscricao(linhaLida.substring(256, 276).trim());
								
								//12-Rota
								emitirContaTipo2Helper.setRota(linhaLida.substring(276, 284).trim());
								
								//13-Categoria
								emitirContaTipo2Helper.setCategoria(linhaLida.substring(284, 297).trim());
								
								//14-Numero de Economias
								emitirContaTipo2Helper.setQtdeEconomia(linhaLida.substring(297, 301).trim());

								//15-Numero do Hidrometro
								emitirContaTipo2Helper.setHidrometro(linhaLida.substring(301, 311).trim());
								
								//16-Tipo Consumo Faturado
								emitirContaTipo2Helper.setDescricaoTipoConsumo(linhaLida.substring(311, 331).trim());
								
								//17-Consumo Faturado
								emitirContaTipo2Helper.setVolFaturado(linhaLida.substring(331, 335).trim());

								//18-Consumo Estimado
								
								//19-Data Leitura Anterior
								emitirContaTipo2Helper.setDtLeituraAnterior(linhaLida.substring(339, 349).trim());
								
								//20-Data Leitura Atual
								emitirContaTipo2Helper.setDtLeituraAtual(linhaLida.substring(349, 359).trim());
								
								//21-Dias de Consumo
								emitirContaTipo2Helper.setDiasConsumo(linhaLida.substring(359, 361).trim());
								
								//22-Leitura Anterior
								emitirContaTipo2Helper.setLeituraAnterior(linhaLida.substring(361, 368).trim());
								
								//23-Leitura Atual
								emitirContaTipo2Helper.setLeituraAtual(linhaLida.substring(368, 375).trim());
								
								//24-Media
								emitirContaTipo2Helper.setConsumoMedio(linhaLida.substring(375, 381).trim());
								
								//25-Referencia 1
								emitirContaTipo2Helper.setMesAno1Conta(linhaLida.substring(381, 388).trim()); 
								//26-Consumo 1
								emitirContaTipo2Helper.setConsumo1Conta(linhaLida.substring(388, 392).trim());

								//27-Referencia 2
								emitirContaTipo2Helper.setMesAno2Conta(linhaLida.substring(392, 399).trim());
								//28-Consumo 2
								emitirContaTipo2Helper.setConsumo2Conta(linhaLida.substring(399, 403).trim());
								
								//29-Referencia 3
								emitirContaTipo2Helper.setMesAno3Conta(linhaLida.substring(403, 410).trim());
								//30-Consumo 3
								emitirContaTipo2Helper.setConsumo3Conta(linhaLida.substring(410, 414).trim());
								
								//31-Referencia 4
								emitirContaTipo2Helper.setMesAno4Conta(linhaLida.substring(414, 421).trim());
								//32-Consumo 4
								emitirContaTipo2Helper.setConsumo4Conta(linhaLida.substring(421, 425).trim());
								
								//33-Refencia 5
								emitirContaTipo2Helper.setMesAno5Conta(linhaLida.substring(425, 432).trim());
								//34-Consumo 5
								emitirContaTipo2Helper.setConsumo5Conta(linhaLida.substring(432, 436).trim());
								
								//35-Referencia 6
								emitirContaTipo2Helper.setMesAno6Conta(linhaLida.substring(436, 443).trim());
								//36-Consumo 6
								emitirContaTipo2Helper.setConsumo6Conta(linhaLida.substring(443, 447).trim());
								
								Collection<ContaLinhasDescricaoServicosTarifasTotalHelper> colecaoServicos = new ArrayList();
								ContaLinhasDescricaoServicosTarifasTotalHelper descricaoServico = null;

								//37-Descricao Item 1
								//38-Valor Item 1
								String servico = linhaLida.substring(447, 502).trim();
								String valor = null;
								
								if(!servico.equals("")){
									valor = linhaLida.substring(502, 516).trim();
									
									descricaoServico = new ContaLinhasDescricaoServicosTarifasTotalHelper();
									descricaoServico.setDescricaoServicosTarifas(servico);
									descricaoServico.setValor(valor);
									
									colecaoServicos.add(descricaoServico);
								}
								
								//39-Descricao Item 2
								//40-Valor Item 2
								servico = linhaLida.substring(516, 571).trim();
								if(!servico.equals("")){
									valor = linhaLida.substring(571, 585).trim();
									
									descricaoServico = new ContaLinhasDescricaoServicosTarifasTotalHelper();
									descricaoServico.setDescricaoServicosTarifas(servico);
									descricaoServico.setValor(valor);
									
									colecaoServicos.add(descricaoServico);
								}

								//41-Descricao Item 3
								//42-Valor Item 3
								servico = linhaLida.substring(585, 640).trim();
								if(!servico.equals("")){
									valor = linhaLida.substring(640, 654).trim();
									
									descricaoServico = new ContaLinhasDescricaoServicosTarifasTotalHelper();
									descricaoServico.setDescricaoServicosTarifas(servico);
									descricaoServico.setValor(valor);
									
									colecaoServicos.add(descricaoServico);
								}
								
								//43-Descricao Item 4
								//44-Valor Item 4
								servico = linhaLida.substring(654, 709).trim();
								if(!servico.equals("")){
									valor = linhaLida.substring(709, 723).trim();
									
									descricaoServico = new ContaLinhasDescricaoServicosTarifasTotalHelper();
									descricaoServico.setDescricaoServicosTarifas(servico);
									descricaoServico.setValor(valor);
									
									colecaoServicos.add(descricaoServico);
								}
								
								//45-Descricao Item 5
								//46-Valor Item 5
								servico = linhaLida.substring(723, 778).trim();
								if(!servico.equals("")){
									valor = linhaLida.substring(778, 792).trim();
									
									descricaoServico = new ContaLinhasDescricaoServicosTarifasTotalHelper();
									descricaoServico.setDescricaoServicosTarifas(servico);
									descricaoServico.setValor(valor);
									
									colecaoServicos.add(descricaoServico);
								}
								
								//47-Descricao Item 6
								//48-Valor Item 6
								servico = linhaLida.substring(792, 847).trim();
								if(!servico.equals("")){
									valor = linhaLida.substring(847, 861).trim();
									
									descricaoServico = new ContaLinhasDescricaoServicosTarifasTotalHelper();
									descricaoServico.setDescricaoServicosTarifas(servico);
									descricaoServico.setValor(valor);
									
									colecaoServicos.add(descricaoServico);
								}
								
								emitirContaTipo2Helper.setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(colecaoServicos);
								
								//49-Valor Total Conta
								emitirContaTipo2Helper.setValorTotalConta(linhaLida.substring(861,875).trim());
								
								//50-Mensagem 1
								emitirContaTipo2Helper.setMsgConta(linhaLida.substring(875,945).trim());
								
								//51-Mensagem 2
								emitirContaTipo2Helper.setMsgLinha1Conta(linhaLida.substring(945,1010).trim());
								
								//52-Mensagem 3
								emitirContaTipo2Helper.setMsgLinha2Conta(linhaLida.substring(1010,1075).trim());
								
								//53-Mensagem 4
								emitirContaTipo2Helper.setDatasVencimentos(linhaLida.substring(1075,1140).trim());
								
								//54-Mensagem 5
								emitirContaTipo2Helper.setMsgLinha3Conta(linhaLida.substring(1140,1205).trim());
								
								//55-Mensagem 6
								emitirContaTipo2Helper.setMsgLinha4Conta(linhaLida.substring(1205,1270).trim());

								//56-Mensagem 7
								emitirContaTipo2Helper.setMsgLinha5Conta(linhaLida.substring(1270,1335).trim());
								
								//57-Turbidez Valor Medio
								emitirContaTipo2Helper.setValorMedioTurbidez(linhaLida.substring(1335,1340).trim());

								//58-Turbidez Valor Padrao
								emitirContaTipo2Helper.setPadraoTurbidez(linhaLida.substring(1340,1360).trim());
								
								//59-Ph Valor Medio
								emitirContaTipo2Helper.setValorMedioPh(linhaLida.substring(1360,1365).trim());

								//60-Ph Valor Padrao
								emitirContaTipo2Helper.setPadraoPh(linhaLida.substring(1365,1385).trim());
								
								//61-Cor Valor Medio
								emitirContaTipo2Helper.setValorMedioCor(linhaLida.substring(1385,1390).trim());

								//62-Cor Valor Padrao
								emitirContaTipo2Helper.setPadraoCor(linhaLida.substring(1390,1410).trim());
								
								//63-Cloro Valor Medio
								emitirContaTipo2Helper.setValorMedioCloro(linhaLida.substring(1410,1415).trim());

								//64-Cloro Valor Padrao
								emitirContaTipo2Helper.setPadraoCloro(linhaLida.substring(1415,1435).trim());

								//65-Fluor Valor Medio
								emitirContaTipo2Helper.setValorMedioFluor(linhaLida.substring(1435,1440).trim());

								//66-Fluor Valor Padrao
								emitirContaTipo2Helper.setPadraoFluor(linhaLida.substring(1440,1460).trim());
								
								//67-Ferro Valor Medio
								emitirContaTipo2Helper.setValorMedioFerro(linhaLida.substring(1460,1465).trim());

								//68-Ferro Valor Padrao
								emitirContaTipo2Helper.setPadraoFerro(linhaLida.substring(1465,1485).trim());
								
								//69-Coliformes Totais Valor Medio
								emitirContaTipo2Helper.setValorMedioColiformesTotais(linhaLida.substring(1485,1495));

								//70-Coliformes Totais Valor Padrao
								emitirContaTipo2Helper.setPadraoColiformesTotais(linhaLida.substring(1495,1515).trim());
								
								//71-Coliformes Fecais Valor Medio
								emitirContaTipo2Helper.setValorMedioColiformesfecais(linhaLida.substring(1515,1525).trim());

								//72-Coliformes Fecais Valor Padrao
								emitirContaTipo2Helper.setPadraoColiformesfecais(linhaLida.substring(1525,1545).trim());
								
								
								//73-Representação Numerica Codigo de Barras
								if(!linhaLida.substring(1545,1600).trim().equals("")){

									String codigoBarraFormatado = Util.formatarCodigoBarra(linhaLida.substring(1545,1600).trim());
									emitirContaTipo2Helper.setRepresentacaoNumericaCodBarraFormatada(codigoBarraFormatado);

									//Representação numerica cod barra sem digito
									String codigoBarra = codigoBarraFormatado.substring(0,11)
										+ codigoBarraFormatado.substring(14, 25)
										+ codigoBarraFormatado.substring(28, 39)
										+ codigoBarraFormatado.substring(42, 53);
								
									emitirContaTipo2Helper.setRepresentacaoNumericaCodBarraSemDigito(codigoBarra);
									emitirContaTipo2Helper.setIndicadorCodigoBarras("true");
								}else{
									emitirContaTipo2Helper.setIndicadorCodigoBarras(null);
								}
								
								//74 - Codigo de Barras								
								
								//75-Origem
								emitirContaTipo2Helper.setOrigem(linhaLida.substring(1640,1643).trim());
								
								//76-Data Limite Para Pagamento

								//77-Grupo Faturamento
								emitirContaTipo2Helper.setGrupoFaturamento(linhaLida.substring(1653,1656).trim());
								
								//78-Sequencial de Impressao
								emitirContaTipo2Helper.setSequencialImpressao(linhaLida.substring(1656,1663).trim());
								
								//79-Codigo rota e sequencial do cliente responsavel
								if(!linhaLida.substring(1663,1679).trim().equals("")){
									emitirContaTipo2Helper.setCodigoRotaSequencialRota(linhaLida.substring(1663,1679).trim());
								}else{
									emitirContaTipo2Helper.setCodigoRotaSequencialRota(null);
								}
								
								retorno.add(emitirContaTipo2Helper);
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
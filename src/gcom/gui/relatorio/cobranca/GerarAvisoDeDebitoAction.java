package gcom.gui.relatorio.cobranca;

import gcom.cobranca.bean.ReavisoDeDebitoHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.ReavisoDeDebitoLinhasDescricaoDebitosHelper;
import gcom.relatorio.cobranca.RelatorioAvisoDeDebito;
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

public class GerarAvisoDeDebitoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarAvisoDeDebito");
		
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
		
		FileItem item = (FileItem) Util.retonarObjetoDeColecao(items);
		String nomeArquivo = item.getName().replace(".txt","");
		nomeArquivo = nomeArquivo.replace("EMITIR","");
		
		Collection colecaoEmitirReavisoDeDebitoHelper = 
			this.gerarColecaoEmitirReavisoDeDebitoHelper(items);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		

		RelatorioAvisoDeDebito relatorio = 
			new RelatorioAvisoDeDebito(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("colecaoEmitirReavisoDeDebitoHelper", colecaoEmitirReavisoDeDebitoHelper);
		relatorio.addParametro("nomeArquivo",nomeArquivo);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
	private Collection gerarColecaoEmitirReavisoDeDebitoHelper(List items){
		
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
						
						ReavisoDeDebitoHelper reavisoDeDebitoHelper = null;
						
						// enquanto a variavel for false
						while (!eof) {
							// pega a linha do arquivo
							String linhaLida = buffer.readLine();

							// se for a ultima linha do arquivo
							if (linhaLida == null || linhaLida.trim().length() == 0) {
								// seta a variavel boolean para true
								eof = true;
							} else {
								
								reavisoDeDebitoHelper = new ReavisoDeDebitoHelper();
								
								//1-Número da Conta
								reavisoDeDebitoHelper.setNumero(linhaLida.substring(0,7).trim());
								
								//2-Nome do Cliente
								reavisoDeDebitoHelper.setNomeCliente(linhaLida.substring(409, 459).trim());

								//3-Endereco - Parte 1
								reavisoDeDebitoHelper.setEndereco(linhaLida.substring(238,338).trim());
								
								//4-Endereco - Parte 2
								reavisoDeDebitoHelper.setEnderecoLinha2(linhaLida.substring(167, 197).trim());
								
								//5-Endereco - Parte 3
								reavisoDeDebitoHelper.setEnderecoLinha3(linhaLida.substring(197, 227).trim()
										+ " - " +linhaLida.substring(227, 229).trim()
										+ "   " +linhaLida.substring(229, 238).trim());
								
								//-Data Emissao
								reavisoDeDebitoHelper.setDataEmissao(linhaLida.substring(1264, 1274).trim());
								
								// J - Rota/Grupo
								reavisoDeDebitoHelper.setRotaGrupo(linhaLida.substring(16,22).trim() +"." 
										+ linhaLida.substring(22,26).trim()+"/"
										+ linhaLida.substring(14,16).trim());
								
								//-Codigo Auxiliar
								reavisoDeDebitoHelper.setCodigoAuxiliar(linhaLida.substring(38, 47).trim());
								
								//-Hidrometro
								reavisoDeDebitoHelper.setNumeroHidrometro(linhaLida.substring(1284, 1294).trim());
								
								//vencimento
								reavisoDeDebitoHelper.setVencimento(linhaLida.substring(1274, 1284).trim());
								
								//Total a pagar
								reavisoDeDebitoHelper.setTotalAPagar(linhaLida.substring(1194, 1208).trim());
								
								//descricao situacao ligacao agua
								reavisoDeDebitoHelper.setDescricaoSituacaoLigacaoAgua(linhaLida.substring(1476, 1496).trim());
								
//								descricao situacao ligacao esgoto
								reavisoDeDebitoHelper.setDescricaoSituacaoLigacaoEsgoto(linhaLida.substring(1496, 1516).trim());
								
//								descricao categoria principal
								reavisoDeDebitoHelper.setDescricaoCategoriaPrincipal(linhaLida.substring(1516, 1530).trim());
								
								//inscricao
								reavisoDeDebitoHelper.setInscricao(linhaLida.substring(47, 67).trim());
								
								Collection colecaoDebito = new ArrayList();
								ReavisoDeDebitoLinhasDescricaoDebitosHelper reavisoDeDebitoLinhasDescricaoDebitosHelper = null;

								//mes ano Linha 1
								String debito = linhaLida.substring(462,469).trim();
								String valor = "";
								String origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(481, 495).trim();
									origem = linhaLida.substring(469,471).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(471,481).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 2
								debito = linhaLida.substring(523,530).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(542, 556).trim();
									origem = linhaLida.substring(530,532).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(532,542).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 3
								debito = linhaLida.substring(584,591).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(603, 617).trim();
									origem = linhaLida.substring(591,593).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(593,603).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 4
								debito = linhaLida.substring(645,652).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(664, 678).trim();
									origem = linhaLida.substring(652,654).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(654,664).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 5
								debito = linhaLida.substring(706,713).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(725, 739).trim();
									origem = linhaLida.substring(713,715).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(715,725).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 6
								debito = linhaLida.substring(767,774).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(786, 800).trim();
									origem = linhaLida.substring(774,776).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(776,786).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 7
								debito = linhaLida.substring(828,835).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(847,861).trim();
									origem = linhaLida.substring(835,837).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(837,847).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 8
								debito = linhaLida.substring(889,896).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(908,922).trim();
									origem = linhaLida.substring(896,898).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(898,908).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 9
								debito = linhaLida.substring(950,957).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(969,983).trim();
									origem = linhaLida.substring(957,959).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(959,969).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 10
								debito = linhaLida.substring(1011,1018).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(1030,1044).trim();
									origem = linhaLida.substring(1018,1020).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(1020,1030).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 11
								debito = linhaLida.substring(1072,1079).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(1091,1105).trim();
									origem = linhaLida.substring(1079,1081).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(1081,1091).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
//								mes ano Linha 12
								debito = linhaLida.substring(1133,1140).trim();
								valor = "";
								origem = "";
								
								if(!debito.equals("")){
									valor = linhaLida.substring(1152,1166).trim();
									origem = linhaLida.substring(1140,1142).trim();
									
									reavisoDeDebitoLinhasDescricaoDebitosHelper = new ReavisoDeDebitoLinhasDescricaoDebitosHelper();
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setMesAno(debito);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setValor(valor);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setOrigem(origem);
									reavisoDeDebitoLinhasDescricaoDebitosHelper.setVencimento(linhaLida.substring(1142,1152).trim());
									
									colecaoDebito.add(reavisoDeDebitoLinhasDescricaoDebitosHelper);
								}
								
								reavisoDeDebitoHelper.setColecaoReavisoDeDebitoLinhasDescricaoDebitosHelper(colecaoDebito);
								
								//71-Representação numerica cod barra formatado
								String codigoBarraFormatado = linhaLida.substring(1299, 1354).trim();
								reavisoDeDebitoHelper.setRepresentacaoNumericaCodBarraFormatada(codigoBarraFormatado);

								//Representação numerica cod barra sem digito
								String codigoBarra = codigoBarraFormatado.substring(0,11)
									+ codigoBarraFormatado.substring(14, 25)
									+ codigoBarraFormatado.substring(28, 39)
									+ codigoBarraFormatado.substring(42, 53);
							
								reavisoDeDebitoHelper.setRepresentacaoNumericaCodBarraSemDigito(codigoBarra);

								retorno.add(reavisoDeDebitoHelper);
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
	

}

package gcom.gui.faturamento;

import gcom.faturamento.bean.FaturaClienteResponsavelHelper;
import gcom.faturamento.bean.FaturaItemClienteResponsavelHelper;
import gcom.faturamento.conta.FaturaItem;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioFaturaClienteResponsavel;
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

public class GerarFaturaClienteResponsavelAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarFatura");
		
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
		
		Collection colecaoFaturaClienteResponsavelHelper = 
			this.gerarColecaoFaturaClienteResponsavelHelper(items);
		
		FileItem item = (FileItem) Util.retonarObjetoDeColecao(items);
		String nomeArquivo = item.getName().replace(".txt","");
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioFaturaClienteResponsavel relatorio = 
			new RelatorioFaturaClienteResponsavel(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("colecaoFaturaClienteResponsavelHelper", colecaoFaturaClienteResponsavelHelper);
		
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
	
	private Collection gerarColecaoFaturaClienteResponsavelHelper(List items){
		
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
						
						FaturaClienteResponsavelHelper faturaClienteResponsavelHelper = null;
						
						// enquanto a variavel for false
						while (!eof) {
							// pega a linha do arquivo
							String linhaLida = buffer.readLine();

							// se for a ultima linha do arquivo
							if (linhaLida == null || linhaLida.trim().length() == 0) {
								// seta a variavel boolean para true
								eof = true;
							} else {
								
								faturaClienteResponsavelHelper = new FaturaClienteResponsavelHelper();
								
								//1-Numero da Fatura (Campo 1 do TXT)
								faturaClienteResponsavelHelper.setNumeroFatura(linhaLida.substring(0, 9).trim());
								
								//2-Responsavel pelo Pagamento (Campo 2 do TXT)
								faturaClienteResponsavelHelper.setNomeCliente(linhaLida.substring(9, 59).trim());

								//3-Endereco de Correspondencia - Parte 1 (Campo 3.1 do TXT)
								faturaClienteResponsavelHelper.setEndereco(linhaLida.substring(59, 159).trim());
								
								//4-Bairro (Campo 3.2.1 do TXT)
								faturaClienteResponsavelHelper.setEnderecoLinha2(linhaLida.substring(159, 189).trim());
								
								//5,6,7-Municipio,UF,CEP (Campo 3.2.2,3.2.3,3.2.4 do TXT)
								String enderecoLinha3 = linhaLida.substring(189, 219).trim() +" "+
									linhaLida.substring(219, 221).trim() +" "+
									linhaLida.substring(221, 230).trim();
								
								faturaClienteResponsavelHelper.setEnderecoLinha3(enderecoLinha3);
								
								//8-Tipo do Cliente (Campo 5 do TXT)
								faturaClienteResponsavelHelper.setTipoResponsavel(linhaLida.substring(237, 287).trim());
								
								//9-Quantidade Itens da Fatura (Campo 6 do TXT)
								faturaClienteResponsavelHelper.setQtdeItens(linhaLida.substring(287, 291).trim());
								
								//10-Data de Emissao (Campo 7 do TXT)
								faturaClienteResponsavelHelper.setDataEmissao(linhaLida.substring(291, 301).trim());

								//11-Mês/Ano (Campo 4 do TXT)
								faturaClienteResponsavelHelper.setMesAno(linhaLida.substring(230, 237).trim());
								
								//12-Data de Vencimento (Campo 8 do TXT)
								//Nesse caso pega o primeiro vencimento da fatura dos vencimento alternativos
								//Anterior - linhaLida.substring(301, 311)
								faturaClienteResponsavelHelper.setPrimeiroVencimento(linhaLida.substring(709,719).trim());
								
								//13-Turbidez Valor Medio (Campo 13 do TXT)
								faturaClienteResponsavelHelper.setValorMedioTurbidez(linhaLida.substring(499,504).trim());

								//14-Turbidez Valor Padrao (Campo 14 do TXT)
								faturaClienteResponsavelHelper.setPadraoTurbidez(linhaLida.substring(504,524).trim());
								
								//15-Ph Valor Medio (Campo 15 do TXT)
								faturaClienteResponsavelHelper.setValorMedioPh(linhaLida.substring(524,529).trim());

								//16-Ph Valor Padrao (Campo 16 do TXT)
								faturaClienteResponsavelHelper.setPadraoPh(linhaLida.substring(529,549).trim());
								
								//17-Cor Valor Medio (Campo 17 do TXT)
								faturaClienteResponsavelHelper.setValorMedioCor(linhaLida.substring(549,554).trim());

								//18-Cor Valor Padrao (Campo 18 do TXT)
								faturaClienteResponsavelHelper.setPadraoCor(linhaLida.substring(554,574).trim());
								
								//19-Cloro Valor Medio (Campo 19 do TXT)
								faturaClienteResponsavelHelper.setValorMedioCloro(linhaLida.substring(574,579).trim());

								//20-Cloro Valor Padrao (Campo 20 do TXT)
								faturaClienteResponsavelHelper.setPadraoCloro(linhaLida.substring(579,599).trim());

								//21-Fluor Valor Medio (Campo 21 do TXT)
								faturaClienteResponsavelHelper.setValorMedioFluor(linhaLida.substring(599,604).trim());

								//22-Fluor Valor Padrao (Campo 22 do TXT)
								faturaClienteResponsavelHelper.setPadraoFluor(linhaLida.substring(604,624).trim());
								
								//23-Ferro Valor Medio (Campo 23 do TXT)
								faturaClienteResponsavelHelper.setValorMedioFerro(linhaLida.substring(624,629).trim());

								//24-Ferro Valor Padrao(Campo 24 do TXT)
								faturaClienteResponsavelHelper.setPadraoFerro(linhaLida.substring(629,649).trim());
								
								//25-Coliformes Totais Valor Medio (Campo 25 do TXT)
								faturaClienteResponsavelHelper.setValorMedioColiformesTotais(linhaLida.substring(649,659));

								//26-Coliformes Totais Valor Padrao (Campo 26 do TXT)
								faturaClienteResponsavelHelper.setPadraoColiformesTotais(linhaLida.substring(659,679).trim());
								
								//27-Coliformes Fecais Valor Medio (Campo 27 do TXT)
								faturaClienteResponsavelHelper.setValorMedioColiformesfecais(linhaLida.substring(679,689).trim());

								//28-Coliformes Fecais Valor Padrao (Campo 28 do TXT)
								faturaClienteResponsavelHelper.setPadraoColiformesfecais(linhaLida.substring(689,709).trim());
								
								//29-Vencimento Alternativo 1
								//30-Vencimento Alternativo 2
								//31-Vencimento Alternativo 3
								//32-Vencimento Alternativo 4
								//33-Vencimento Alternativo 5
								//34-Vencimento Alternativo 6
								
								//São as data de vencimento
								String datasVencimentos = linhaLida.substring(709,719).trim() +" "+
									linhaLida.substring(719,729).trim() +" "+
									linhaLida.substring(729,739).trim() +" "+
									linhaLida.substring(739,749).trim() +" "+
									linhaLida.substring(749,759).trim() +" "+
									linhaLida.substring(759,769).trim();
								
								faturaClienteResponsavelHelper.setDatasVencimentos(datasVencimentos);
								
								Collection<FaturaItemClienteResponsavelHelper> colecaoFaturaItem = new ArrayList();

								//35-Nome do Usuario (Campo 13.1 do TXT)
								//36-Matrícula do Imóvel (Campo 13.2 do TXT)
								//37-Consumo (Campo 13.3 do TXT)
								//38-Valor da Conta (Campo 13.4 do TXT)
								int quantidade = 0;
								int quantidadeItensPorFatura = FaturaItem.QTD_ITENS_RELATORIO_FATURA.intValue();
								
								for (int i = 0; i < quantidadeItensPorFatura; i++) {
									
									int index1 = 769 + quantidade;
									
									int index2 = index1 + 50;
									int index3 = index2 + 9;
									int index4 = index3 + 9;
									int index5 = index4 + 14;
									
									String nome = linhaLida.substring(index1, index2).trim();
									
									if(!nome.equals("")){
										FaturaItemClienteResponsavelHelper faturaItemClienteResponsavelHelper = 
											new FaturaItemClienteResponsavelHelper();
										
										faturaItemClienteResponsavelHelper.setNome(nome);
										faturaItemClienteResponsavelHelper.setMatricula(linhaLida.substring(index2, index3).trim());
										faturaItemClienteResponsavelHelper.setConsumo(linhaLida.substring(index3, index4).trim());
										faturaItemClienteResponsavelHelper.setValor(linhaLida.substring(index4, index5).trim());
										
										colecaoFaturaItem.add(faturaItemClienteResponsavelHelper);
									}else{
										break;
									}
									
									quantidade = quantidade + 82;									
								}								

								faturaClienteResponsavelHelper.setColecaoFaturaItemClienteResponsavelHelper(colecaoFaturaItem);

								//Só gera codigo de barras se existir valor nesse campo
								
								//17-Codigo de Barras Formatado (Campo 11 do TXT)
								String codigoBarrasGerado = linhaLida.substring(387,499).trim();
								boolean gerarCodigoBarras = false;
								if(!codigoBarrasGerado.equals("")){
									gerarCodigoBarras = true;
								}
								
								if(gerarCodigoBarras){

									//16-Somatorio do Valor Total(Campo 9 do TXT)
									faturaClienteResponsavelHelper.setValorTotalAPagar(linhaLida.substring(311, 325).trim());

									String codigoBarraFormatado = linhaLida.substring(332,387).trim();
									faturaClienteResponsavelHelper.setRepresentacaoNumericaCodBarraFormatada(codigoBarraFormatado);
									
									//Representação numerica cod barra sem digito
									String codigoBarra = codigoBarraFormatado.substring(0,11)
										+ codigoBarraFormatado.substring(14, 25)
										+ codigoBarraFormatado.substring(28, 39)
										+ codigoBarraFormatado.substring(42, 53);
									
									faturaClienteResponsavelHelper.setRepresentacaoNumericaCodBarraSemDigito(codigoBarra);
									faturaClienteResponsavelHelper.setIndicadorCodigoBarras("S");
									
									//17-Valor Bruto(Campo 36 do TXT)
									faturaClienteResponsavelHelper.setValorBruto(linhaLida.substring(2409, 2423).trim());
									
									//18-Valor Impostos(Campo 37 do TXT)
									faturaClienteResponsavelHelper.setValorImposto(linhaLida.substring(2423, 2437).trim());
								}else{
									//16-Somatorio do Valor Total(Campo 9 do TXT)
									faturaClienteResponsavelHelper.setValorTotalAPagar("CONTINUA...");
									faturaClienteResponsavelHelper.setIndicadorCodigoBarras(null);
									
								}
								
								retorno.add(faturaClienteResponsavelHelper);
							}
						}//fim do while eof
					}else {
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

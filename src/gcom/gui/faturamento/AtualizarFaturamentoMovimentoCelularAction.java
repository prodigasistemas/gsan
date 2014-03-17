package gcom.gui.faturamento;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.gui.ActionServletException;
import gcom.gui.micromedicao.ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction;
import gcom.micromedicao.ArquivoTextoRetornoIS;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorioAtualizacaoMovimentoCelular;
import gcom.relatorio.faturamento.RelatorioErrosMovimentosContaPreFaturadas;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Date;
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
import org.jboss.logging.Logger;

public class AtualizarFaturamentoMovimentoCelularAction extends ExibidorProcessamentoTarefaRelatorioAtualizacaoMovimentoCelular {

	private static Logger logger = Logger.getLogger(AtualizarFaturamentoMovimentoCelularAction.class);
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        try {
            DiskFileUpload upload = new DiskFileUpload();
            
            int indcFinalizacao = 0;
            Integer codRota = null;
    		Integer setorComercial = null;
    		Integer localidade = null;
    		boolean temRegistroTipo0 = false;
    		Integer numeroSequenciaArquivo = null;
            
            // Parse the request
            List itensForm = upload.parseRequest(httpServletRequest);
            Iterator iteItensForm = itensForm.iterator();
            
            byte[] byteRelatorio = null;
            Integer idRota = 0;
            Integer anoMesReferencia = null;
			Integer diferenca = null;
            boolean indicadorSucessoAtualizacao = false;
            
            Fachada fachada = Fachada.getInstancia();            
            
            while ( iteItensForm.hasNext() ){
                
                FileItem item = ( FileItem )iteItensForm.next();
                
                // Caso não seja um field do formulario
                // é o arquivo
                if ( !item.isFormField() ){
                    // Lemos 
                    InputStreamReader reader = new InputStreamReader(item.getInputStream());
                    InputStreamReader inputSemRegistroZero = new InputStreamReader(item.getInputStream());
                    InputStreamReader readerOriginal = new InputStreamReader(item.getInputStream());
                    BufferedReader buffer = new BufferedReader(reader);
                    BufferedReader bufferOriginal = new BufferedReader(readerOriginal);
                    BufferedReader bufferSemRegistroZero = new BufferedReader(inputSemRegistroZero);
                    
                    String nomeArquivo = item.getName();
                    
                    String registro0 = buffer.readLine();
                    
                    logger.info("Linha arquivo String: " + registro0);
                    
                    /**
					 * TODO : COSANPA
					 * Adicionando alterações para salvar o arquivo
					 * de retorno da rota.
					 */
					ArquivoTextoRetornoIS arquivoRetorno = new ArquivoTextoRetornoIS();
					/*
					 * TODO - COSANPA
					 * 
					 * Caso o conteúdo do arquivo não seja diferente de nulo ou
					 * com string vazia, mostra mensagem informando que o arquivo 
					 * está não contém dados.
					 * 
					 * @author Felipe Santos
					 * @date 16/11/2011
					 */
                    
					if (registro0 != null && !registro0.trim().equals("")) {
						
						int registroTipo = Integer.parseInt(registro0.substring(0, 1));
						
						if(registroTipo == 0){
							bufferSemRegistroZero = null;
							inputSemRegistroZero = null;
							temRegistroTipo0 = true;
							indcFinalizacao = Integer.parseInt(registro0.substring(1,2));
							codRota = Integer.parseInt(registro0.substring(8,15));
							setorComercial = Integer.parseInt(registro0.substring(5,8));
							localidade = Integer.parseInt(registro0.substring(2,5));
							
							/*
							 * TODO - COSANPA 
							 * 
							 * Verifica o número o número de sequência do arquivo
							 * e o id da Rota com a informação contida no registro 0
							 * 
							 */
							if (registro0.length() == 17) {
								numeroSequenciaArquivo = Integer.parseInt(registro0.substring(15, 17));
								idRota = fachada.obterIdRotaPorSetorComercialELocalidade(codRota, setorComercial, localidade);
							} else {
								numeroSequenciaArquivo = Integer.parseInt(registro0.substring(19, 21));
								idRota = Integer.parseInt(registro0.substring(15, 19));
							}
							
							anoMesReferencia = fachada.retornaAnoMesFaturamentoGrupoDaRota(idRota);

							arquivoRetorno.setAnoMesReferencia(anoMesReferencia);
							arquivoRetorno.setCodigoRota(codRota);
							arquivoRetorno.setCodigoSetorComercial(setorComercial);
							arquivoRetorno.setNomeArquivo(nomeArquivo);
							arquivoRetorno.setTempoRetornoArquivo(new Date());
							
							int tipoFinalizacao = ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO;
							arquivoRetorno.setTipoFinalizacao(new Short(tipoFinalizacao + ""));
							
							logger.info("Finalizando arquivo offline [Localidade: " + localidade + ", Setor: " + setorComercial
											+ ", Rota: " + codRota + "]");
							
							
							// Caso não encotremos essa rota, pesquisamos 
							// assumindo que o imovel possue rota alternativa
							if ( idRota == null ){
								String primeiroRegistro = buffer.readLine();
								Integer matricula = Integer.parseInt( primeiroRegistro.substring( 1, 10 ) );
								
								FiltroImovel filtroImovel = new FiltroImovel();
								filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "rotaAlternativa.setorComercial" );
								filtroImovel.adicionarParametro( new ParametroSimples( FiltroImovel.ID, matricula ) );				
								Collection<Imovel> colImovel = Fachada.getInstancia().pesquisar( filtroImovel, Imovel.class.getName() );
								Imovel imo = (Imovel) Util.retonarObjetoDeColecao( colImovel );
								
								localidade = imo.getLocalidade().getId();
								setorComercial = imo.getRotaAlternativa().getSetorComercial().getCodigo();
								codRota = imo.getRotaAlternativa().getCodigo().intValue();
								
								idRota = fachada.obterIdRotaPorSetorComercialELocalidade(codRota,setorComercial,localidade);
								
								// Remontamos o buffer
								String linha;
								StringBuffer arquivo = new StringBuffer();
								arquivo.append( primeiroRegistro + "\n" );
								
								while( ( linha = buffer.readLine() ) != null ){					
									arquivo.append(linha + "\n");					
								}				
								
								InputStream is = new ByteArrayInputStream( arquivo.toString().getBytes() );        	
								InputStreamReader readerRetorno = new InputStreamReader( is );    		
								buffer = new BufferedReader(readerRetorno);
							}                		
							
							// Caso o tipo de finalização seja de arquivo com imóveis faltando, pesquisamos quais ja chegaram
							if ( indcFinalizacao == ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO ){        				
								buffer = fachada.removerImoveisJaProcessadosBufferImpressaoSimultanea( idRota, buffer );
							}
						} else {          
							buffer = bufferSemRegistroZero;
							throw new ActionServletException("atencao.arquivo_sem_registro_tipo0", nomeArquivo);
						}
						
						
						RetornoAtualizarFaturamentoMovimentoCelularHelper helper = null;
						
						helper = fachada.atualizarFaturamentoMovimentoCelular(buffer, true, true, null, arquivoRetorno, bufferOriginal);
						byteRelatorio = helper.getRelatorioConsistenciaProcessamento();
						indicadorSucessoAtualizacao = helper.getIndicadorSucessoAtualizacao();						     			
						
//						indicadorSucessoAtualizacao = true;
					} else {
						throw new ActionServletException("atencao.arquivo_sem_dados", nomeArquivo);
					}
					
                    break;
                }                
            }
            
            Integer[] idsSituacaoTransmissao = new Integer[1];
            idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
            
            boolean indicadorRotaDividida = fachada.isRotaDividida(idRota, anoMesReferencia);
			
			String mensagemAtualizacao = null;
			
			// Pesquisa Arquivo Texto Roteiro Empresa
			ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = fachada.pesquisarArquivosTextoRoteiroEmpresaTransmissaoOffline(
					localidade, idRota, anoMesReferencia);
			
			// Quantidade de Imóveis Transmitidos para rota dividida
			Integer contadorImoveisMovimentoContaPF = null;						
			Integer quantidadeImoveisArquivoDividido = null;
			
			/*
			 * TODO - COSANPA
			 * 
			 * Monta mensagem de sucesso ou erro para Atualização de Faturamento Movimento Celular
			 * 
			 * @author Felipe Santos
			 * @date 29/07/2011
			 */				
			String textoInformacoesRota = "<br />";
			textoInformacoesRota += "<br />";
			textoInformacoesRota += "Localidade: " + localidade + ", Setor: "
					+ setorComercial + ", Rota: " + codRota;
			
			String mensagemPrincipalSucesso = "Faturamento Movimento Celular foi Atualizado com sucesso. "
				+"Verifique o relatório gerado."+textoInformacoesRota;
			
			String mensagemPrincipalErro = "Não foi possível Atualizar Faturamento Movimento Celular. "
				+"Verifique o relatório gerado para identificar o problema."+textoInformacoesRota;
			
			String mensagemPrincipalErroNaoCorresponde = "Não foi possível Atualizar Faturamento Movimento Celular. "
				+"A quantidade de imóveis não corresponde ao esperado. Verifique o relatório gerado."+textoInformacoesRota;
			
			String mensagemFinalizadoIncompleto = "Faturamento Movimento Celular foi Finalizado Incompleto.";
			
			if(indicadorSucessoAtualizacao){
				
				if ( temRegistroTipo0 ){				
					if( indcFinalizacao == ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA ||
						indcFinalizacao == ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO	){
                    
						diferenca = fachada.pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(idRota, anoMesReferencia);

						if (diferenca == null) {
							diferenca = 0;
						} else if (diferenca < 0) {
							diferenca *= -1;
						}
						
						mensagemAtualizacao = mensagemPrincipalSucesso;
						
						/*
						 * TODO - COSANPA
						 * Verifica se a rota é Dividida. Caso NÃO seja dividida, atualiza o arquivo
						 * principal para TRANSMITIDO. Caso a rota seja dividida, atualiza o arquivo
						 * dividido para TRANSMITIDO através do número de sequência do arquivo.
						 */
						if (!fachada.isRotaDividida(idRota, anoMesReferencia)) {
							if (diferenca != 0) {
								
								String msg = "Quantidade de imóveis enviados não corresponde ao esperado. ";
								msg += "[Imóveis faltando transmitir: " + diferenca;
								msg += ", Localidade: " + localidade
										+ ", Setor: " + setorComercial
										+ ", Rota: " + codRota + "]";

								logger.info(msg);
								
								mensagemAtualizacao = mensagemPrincipalErroNaoCorresponde;
								mensagemAtualizacao += "<br />";
								mensagemAtualizacao += "<br />";
								mensagemAtualizacao += "Imóveis faltando transmitir: " + diferenca;
								indicadorSucessoAtualizacao = false;
							} else {
								
								// atualiza o arquivo principal para a situação de TRANSMITIDO
								fachada.atualizarArquivoTextoEnviadoPorRota(idRota,SituacaoTransmissaoLeitura.EM_CAMPO,
										SituacaoTransmissaoLeitura.TRANSMITIDO);
							}
						} else {
							mensagemAtualizacao += ". Parte: " + numeroSequenciaArquivo;
							
							// Pesquisa Arquivo Texto Roteiro Empresa Divisão
							ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = fachada.pesquisarArquivoTextoRoteiroEmpresaDivisao(
									arquivoTextoRoteiroEmpresa.getId(), numeroSequenciaArquivo);							
							
							// Quantidade de imóveis do arquivo dividido
							quantidadeImoveisArquivoDividido = arquivoTextoRoteiroEmpresaDivisao.getQuantidadeImovel();
							
							// Lista contendo todos o imóveis transmitidos da rota
							List<Integer> listaImoveisMovimentoContaPF = fachada.obterImoveisMovimentoContaPF(idRota, anoMesReferencia);
							
							// Arquivo dividido original
							File arquivoOriginal = new File(arquivoTextoRoteiroEmpresaDivisao.getNomeArquivo());
							
							FileOutputStream out = new FileOutputStream(arquivoOriginal.getAbsolutePath());
	
							out.write(arquivoTextoRoteiroEmpresaDivisao.getArquivoTexto());
	
							out.close();
							
							// Obtém a quantidade de imóveis transmitidos do arquivo dividido
							contadorImoveisMovimentoContaPF = new Integer(IoUtil.obterQuantidadeImoveisTransmitidos(
									arquivoOriginal, listaImoveisMovimentoContaPF));
							
							// Atualiza o arquivo dividido para TRANSMITIDO caso a quantidade de
							// imóveis transmitidos seja igual a quantidade total de imóveis do arquivo
							if (contadorImoveisMovimentoContaPF >= quantidadeImoveisArquivoDividido) {
								fachada.atualizarArquivoTextoDividido(idRota, anoMesReferencia, numeroSequenciaArquivo,
										SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.TRANSMITIDO);
								
								if (diferenca != 0) {
									String msg = "Quantidade de imóveis enviados não corresponde ao esperado. ";
									msg += "[Imóveis faltando transmitir: " + diferenca;
									msg += ", Localidade: " + localidade
											+ ", Setor: " + setorComercial
											+ ", Rota: " + codRota + "]";
									
									mensagemAtualizacao += "<br />";
									mensagemAtualizacao += "<br />";
									mensagemAtualizacao += "Imóveis faltando transmitir: " + diferenca;

									logger.info(msg);
								} else {								
									// Verifica se todas as rotas divididas estão com a situação de TRANSMITIDO								 
									if (!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(
											idRota, anoMesReferencia, idsSituacaoTransmissao)) {
										// atualiza o arquivo principal para a situação de TRANSMITIDO
										fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
												SituacaoTransmissaoLeitura.TRANSMITIDO);
									}
								}
							} else {								
								mensagemAtualizacao = mensagemPrincipalErroNaoCorresponde + numeroSequenciaArquivo;
								
								mensagemAtualizacao += "<br />";
								mensagemAtualizacao += "<br />";
								mensagemAtualizacao += "Imóveis faltando transmitir: " + diferenca;
								indicadorSucessoAtualizacao = false;
							}						}

					} else if (indcFinalizacao == ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA_INCOMPLETA) {

						mensagemAtualizacao = mensagemFinalizadoIncompleto;
						indicadorSucessoAtualizacao = false;
						
						if (!indicadorRotaDividida) {

							fachada.atualizarArquivoTextoEnviadoPorRota(idRota,SituacaoTransmissaoLeitura.EM_CAMPO,
									SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
							
						} else {
							// verifica se todas as rotas divididas estão com a situação de TRANSMITIDO
							if (!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesReferencia, idsSituacaoTransmissao)) {
								// atualiza o arquivo principal para a situação de TRANSMITIDO
								fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
										SituacaoTransmissaoLeitura.TRANSMITIDO);
							} else {
								// verifica se todas as rotas divididas estão com a situação de TRANSMITIDO ou FINALIZADO INCOMPLETO
								idsSituacaoTransmissao = new Integer[2];
								idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
								idsSituacaoTransmissao[1] = SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO;

								if (!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesReferencia,
										idsSituacaoTransmissao)) {
									// atualiza o arquivo principal para a situação de FINALIZADO INCOMPLETO
									fachada.atualizarArquivoTextoEnviadoPorRota(idRota,SituacaoTransmissaoLeitura.EM_CAMPO,
											SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
								}
							}
						}
					}
				}
			} else {
				mensagemAtualizacao = mensagemPrincipalErro;
			}
            
			/*
			 * Monta o relatório gerado e tela de sucesso ou erro da transmissão
			 */
			if (byteRelatorio != null) {
				RelatorioErrosMovimentosContaPreFaturadas relatorio = new RelatorioErrosMovimentosContaPreFaturadas(
						(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

				relatorio.setRelatorio(byteRelatorio);

				httpServletRequest.setAttribute("telaSucessoRelatorio", true);
				
				retorno = processarExibicaoRelatorio(relatorio,TarefaRelatorio.TIPO_PDF + "",
						indicadorSucessoAtualizacao, mensagemAtualizacao,
						httpServletRequest, httpServletResponse, actionMapping);
			} else {
				montarPaginaSucesso(httpServletRequest, mensagemAtualizacao, "Voltar", "/exibirAtualizarFaturamentoMovimentoCelularAction.do");
				
				if (indicadorSucessoAtualizacao) {	
					 retorno = actionMapping.findForward("telaSucesso");
				 } else {					 
					 retorno = actionMapping.findForward("telaAtencaoRelatorioAtualizarFaturamentoMovimentoCelular");
				 }
			}
			
            return retorno;
        } catch (ActionServletException ex) {
        	throw ex;
		} catch (Exception ex) {			
			throw new ActionServletException("erro.atualizacao.nao_concluida");
		}     
    }
}
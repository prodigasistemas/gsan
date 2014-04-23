package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * [UCXXX] -  
 * 
 *
 * @author Bruno Barros
 * @date 15/06/2011
 *
 */
public class ProcessarRequisicaoDispositivoMovelAcompanhamentoServicoAction extends GcomAction {
	
	// Constantes da classe	
	// Tipos de Respota
	private static final byte RESPOSTA_ERRO = '#';
	private static final byte RESPOSTA_OK = '*';
	
	// Tipos de Requisição
	private static final short BAIXAR_ARQUIVO = 1;
	private static final short ATUALIZAR_ARQUIVO = 2;
	private static final short BAIXAR_MENSAGEM = 3;
	private static final short ENCERRAR_OS = 4;
	private static final short RECEBER_FOTO = 5;
	private static final short MUDAR_SITUACAO_ORDEM_SERVICO = 6;
	private static final short ENCERRAR_ARQUIVO_RETORNO = 7;
	//private static final short UPLOAD_RETORNO_ARQUIVO = 7;
	
	// Fachada
	Fachada fachada = Fachada.getInstancia();
	
	/**
	 * Método Execute do Action
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException 
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response){

		InputStream in = null;
		OutputStream out = null;
		DataInputStream din = null;
		
		// Verificamos qual foi a ação solicitada pelo dispositivo movel
		try {
			in = request.getInputStream();
			out = response.getOutputStream();
			din = new DataInputStream(in);
			
			// O primeiro byte da requisição possue sempre o tipo da requisição
			// feita pelo dispositivo móvel
			int acaoSolicitada = din.readByte();
			long imei = din.readLong();
			switch ( acaoSolicitada ) {
			// Baixar o arquivo ?			
			case BAIXAR_ARQUIVO:
				// No caso de baixas de arquivo, é sempre passado
				// o imei do celular
				this.baixarArquivo(imei,response,out);
				break;
			case ATUALIZAR_ARQUIVO:
				// No caso de baixas de arquivo, é sempre passado
				// o imei do celular
				this.atualizarArquivo(imei,response,out);
				break;
			case BAIXAR_MENSAGEM:
				this.baixarMensagem(imei,response,out);
				break;				
			case RECEBER_FOTO:
				this.receberFoto(din, response, out);
				break;
			case MUDAR_SITUACAO_ORDEM_SERVICO:
				atualizarSituacaoProgramacaoOrdemServico(din, response, out);
				break;
			case ENCERRAR_OS:
				encerrarOS(din, response, out);
				break;
			case ENCERRAR_ARQUIVO_RETORNO:
				atualizarSituacaoArquivo(imei,response,out);
			default:
				break;
			}
			
		// Caso aconteça qualquer problema, retornamos para o
		// dispositivo mobel o caracter de erro no processamento
		// da requisição
		} catch (Exception e) {						
			response.setContentLength( 1 );			
			try {
				out.write(RESPOSTA_ERRO);
				out.flush();
				
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. RETORNOU " + RESPOSTA_ERRO + " PARA O CELULAR /n/n/n");
				
				e.printStackTrace();
			} catch (IOException e1) {
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. NÃO ENVIOU RESPOSTA PARA O CELULAR /n/n/n");
				e1.printStackTrace();
				
				throw new ActionServletException( e1.getMessage() );
			}
		}finally{
			if (in != null) {
				try {
					in.close();					
				} catch (IOException e) {
					e.printStackTrace();					
					throw new ActionServletException( e.getMessage() );					
				}
			}
			
			if(out != null){
				try {					
					out.close();
				} catch (IOException e) {
					e.printStackTrace();					
					throw new ActionServletException( e.getMessage() );				
				}
			}
			
		}
		
		return null;		
	}
	
	/**
	 * 
	 * [UCXXX] - 
	 * 
	 * SF/FS - 
	 *
	 * @author Bruno Barros
	 * @date 15/06/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void baixarArquivo( long imei, HttpServletResponse response, OutputStream out ) throws IOException{		
		
		try{
			// Arquivo retornado
			byte[] arq = fachada.baixarArquivoTextoAcompanhamentoServico( imei );
			
			// Nome do arquivo
			//String nomeArquivo = (String) retorno[1];
			
			if(arq != null && arq.length > 0){
				System.out.println("Inicio : Baixando arquivo Mobile");

				// Parametro que identifica que o tipo de arquivo da rota está sendo enviado
				String parametroArquivoBaixarRota = "";				
				
				// 1 do tipo de resposta ok + parametro Arquivo baixar rota + tamanho do arquivo da rota
				response.setContentLength( 1+parametroArquivoBaixarRota.getBytes().length+arq.length );

				out.write( RESPOSTA_OK );
				out.write( parametroArquivoBaixarRota.getBytes() );
				out.write( arq );
				
				out.flush();

				System.out.println("Fim: Baixando arquivo Mobile");	        
			}else{
				System.out.println("Não existem arquivos liberados para o imei " + imei);

				response.setContentLength( 1 );

				out.write(RESPOSTA_ERRO);
				out.flush();
			}
			
		} catch( Exception e ){
			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}

	/**
	 * 
	 * [UCXXX] - 
	 * 
	 * SF/FS - 
	 *
	 * @author Bruno Barros
	 * @date 15/06/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void atualizarArquivo( long imei, HttpServletResponse response, OutputStream out ) throws IOException{		
		
		try{
			// Arquivo retornado
			byte[] arq = fachada.atualizarArquivoTextoAcompanhamentoServico( imei );
			
			// Nome do arquivo
			//String nomeArquivo = (String) retorno[1];
			
			if(arq != null && arq.length > 0){
				System.out.println("Inicio : Atualizando Arquivo Mobile");

				// Parametro que identifica que o tipo de arquivo da rota está sendo enviado
				String parametroArquivoBaixarRota = "";				
				
				// 1 do tipo de resposta ok + parametro Arquivo baixar rota + tamanho do arquivo da rota
				response.setContentLength( 1+parametroArquivoBaixarRota.getBytes().length+arq.length );

				out.write( RESPOSTA_OK );
				out.write( parametroArquivoBaixarRota.getBytes() );
				out.write( arq );
				
				out.flush();

				System.out.println("Fim: Atualizando Arquivo Mobile");	        
			}else{
				System.out.println("Não existe atualização");
				response.setContentLength( 1 );

				out.write(RESPOSTA_ERRO);
				out.flush();
			}
			
		} catch( Exception e ){
			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}	
	
	/**
	 * 
	 * [UCXXX] - 
	 * 
	 * SF/FS - 
	 *
	 * @author Thúlio Araújo
	 * @date 08/0/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void baixarMensagem( long imei, HttpServletResponse response, OutputStream out ) throws IOException{		
		
		try{
			// Mensagem Retornada
			String mensagemRetorno = fachada.retornaMensagemAcompanhamentoArquivosRoteiroImei( imei );
			
			if(mensagemRetorno != null && !mensagemRetorno.equals(null)){
				System.out.println("Inicio : Baixando Mensagem");

				String mensagem = "13|"+mensagemRetorno;
				
				byte[] bytesMensagem = mensagem.getBytes();
				
				response.setContentLength( 1+bytesMensagem.length );

				out.write( RESPOSTA_OK );
				out.write( bytesMensagem );
				
				out.flush();

				System.out.println("Fim: Baixando Mensagem");	        
			}else{
				System.out.println("Não há mensagens para baixar...");

				response.setContentLength( 1 );

				out.write(RESPOSTA_ERRO);
				out.flush();
			}
		} catch( Exception e ){
			System.out.println("Erro ao Baixar Mensagem");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	/**
	 * 
	/**
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * Método que insere o array de bytes vindo do celular
	 * e o insere no banco
	 *  
	 * @param data
	 * @param response
	 * @param out
	 * 
	 * @param fileLength 
	 * @throws IOException
	 */
	public void receberFoto( DataInputStream data, HttpServletResponse response, OutputStream out ) throws IOException{		
		// Lemos o número da OS para qual essa foto pertence
		int numeroOS = data.readInt();
		
		// Verificamos qual o tipo da foto, se inicio, meio ou fim
		int tipoFoto = data.readInt();
		
		long fileSize = data.readLong();

		byte[] bytesFoto = new byte[(int)fileSize]; 
		int read = 0;
		int numRead = 0;
		while (read < bytesFoto.length && (numRead=data.read(bytesFoto, read, bytesFoto.length-read)) >= 0) {
			read = read + numRead;
		}
		
		// inserimos na base
		Fachada.getInstancia().inserirFotoOrdemServico( numeroOS, tipoFoto, bytesFoto );		
	}
	
	/**
	 * 
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * Altera a situação de uma ordem de serviço no GSAN
	 * 
	 * 
	 * @param data
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void atualizarSituacaoProgramacaoOrdemServico( 
			DataInputStream data, 
			HttpServletResponse response, 
			OutputStream out ) throws IOException{		
		try{		
			// Lemos o número da OS para qual essa foto pertence
			int numeroOS = data.readInt();
		
			// Verificamos qual o tipo da foto, se inicio, meio ou fim
			short situacao = data.readShort();
		
			Fachada.getInstancia().atualizarSituacaoProgramacaoOrdemServico( numeroOS , situacao);
		} catch( Exception e ){
			System.out.println("Erro ao Baixar Mensagem");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
		
	}
	
	/**
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * Método que insere o array de bytes vindo do celular
	 * e o insere no banco
	 *  
	 * @param data
	 * @param response
	 * @param out
	 * 
	 * @param fileLength 
	 * @throws IOException
	 */
	public void encerrarOS( DataInputStream data, HttpServletResponse response, OutputStream out ) throws IOException{		
		try{
		
			long fileSize = data.readLong();
	
			byte[] bytesArquivo = new byte[(int)fileSize]; 
			int read = 0;
			int numRead = 0;
			while (read < bytesArquivo.length && (numRead=data.read(bytesArquivo, read, bytesArquivo.length-read)) >= 0) {
				read = read + numRead;
			}
			
			String arquivo = new String(bytesArquivo);
			
			BufferedReader buffer = new BufferedReader(new StringReader(arquivo));
			
			// inserimos na base
			Fachada.getInstancia().retornoAtualizarOrdemServicoAcompanhamentoServico( buffer );
			
			response.setContentLength( 1 );

			out.write( RESPOSTA_OK );

			out.flush();

		}catch( Exception e ){
			System.out.println("Erro ao efetuar operação.");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	/**
	 * 
	 * [UCXXX] - 
	 * 
	 * SF/FS - 
	 *
	 * @author Bruno Barros
	 * @date 15/06/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void atualizarSituacaoArquivo( long imei , HttpServletResponse response, OutputStream out ) throws IOException{		
		
		try{
			// Arquivo retornado
			fachada.atualizarSituacaoArquivoTextoAcompanhamentoServico( imei,SituacaoTransmissaoLeitura.TRANSMITIDO.shortValue() );
			
			response.setContentLength( 1 );

			out.write( RESPOSTA_OK );

			out.flush();

				
		} catch( Exception e ){
			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}
}

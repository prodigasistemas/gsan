package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.GZIP;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
 * 
 * @author Tiago Nascimento, Rômulo Aurélio
 * @Data ??/??/??,  27/10/2010
 *
 */
public class AlterarLeituristaArquivoLeituraAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada f = Fachada.getInstancia();
		
		AlterarLeituristaArquivoLeituraActionForm form = (AlterarLeituristaArquivoLeituraActionForm) actionForm;
			
		ActionForward retorno = actionMapping.findForward("telaSucesso");
						
		if(form.getArquivoID()!=null && !form.getArquivoID().equals("") && !form.getArquivoID().equals("-1")){
				if(form.getLeitursitaID()!=null && !form.getLeitursitaID().equals("") 
						&& !form.getLeitursitaID().equals("-1")){
					
					FiltroArquivoTextoRoteiroEmpresa filtroArquivo = 
						new FiltroArquivoTextoRoteiroEmpresa();										
					
					filtroArquivo.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRoteiroEmpresa.ROTA);
					filtroArquivo.adicionarCaminhoParaCarregamentoEntidade( "leiturista.usuario" );					
					
					filtroArquivo.adicionarParametro(new ParametroSimples(
							FiltroArquivoTextoRoteiroEmpresa.ID, form.getArquivoID()));
					
					Collection colecao = f.pesquisar(filtroArquivo,ArquivoTextoRoteiroEmpresa.class.getName());
					if(colecao!=null && !colecao.isEmpty()){
						ArquivoTextoRoteiroEmpresa arq = 
							(ArquivoTextoRoteiroEmpresa) colecao.iterator().next();

//						Integer idGrupoFaturamento = new Integer(form.getGrupoFaturamentoID());
//						Integer anoMesReferencia = new Integer(Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno()));
//
//						f.alterarLeituristaArquivoLeitura(arq, new Integer(form.getLeitursitaID()),idGrupoFaturamento,anoMesReferencia);
//						
						FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
						filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade( "usuario" );
						filtroLeiturista.adicionarParametro(new ParametroSimples(
								FiltroLeiturista.ID, form.getLeitursitaID()));
						
						colecao = f.pesquisar(filtroLeiturista,Leiturista.class.getName());
						
						boolean rotaDividida = Fachada.getInstancia().isRotaDividida( arq.getRota().getId(), arq.getAnoMesReferencia() );
						
						byte[] arquivo = this.getByteArrayArquivo( arq );

						ByteArrayInputStream is = new ByteArrayInputStream( arquivo );
						
						if(colecao!=null && !colecao.isEmpty()){
							Leiturista leiturista = (Leiturista)colecao.iterator().next();
							
							String[] novosDados = null;
							String novoLogin = null;
							String novaSenha = null;							
							
							String[] dadosAntigos = null; 
							String loginAntigo = null;
							String senhaAntiga = null;
							
							StringBuffer bufferArquivo = null;
								
							// Apenas alteramos o arquivo se for impressão simultanea
							if ( arq.getServicoTipoCelular().getId().intValue() == ServicoTipoCelular.IMPRESSAO_SIMULTANEA ){
								// Carregamos o login e a senha do leiturista antigo
								novosDados = selecionarLoginSenhaNova( leiturista );

								novoLogin = novosDados[0];
								novaSenha = novosDados[1];
								
								dadosAntigos = selecionarLoginSenhaAntigos( arq );

								loginAntigo = dadosAntigos[0];
								senhaAntiga = dadosAntigos[1];
								
								bufferArquivo = trocarLoginSenhaArquivo( is, loginAntigo, senhaAntiga, novoLogin, novaSenha );							
								compactarSalvarArquivo( arq, bufferArquivo );								
							}
							
							Integer numeroSequencial = f.numeroSequenciaUltimaRota(leiturista.getId());
							numeroSequencial = numeroSequencial + 1;
							
							
							if ( !rotaDividida ){
								arq.setLeiturista(leiturista);
								arq.setNumeroImei(leiturista.getNumeroImei());
								
								// Atualizamos o movimento roteiro empresa para que esse
								// fique com o mesmo leiturista do arquivo
								f.alterarLeituristaMovimentoRoteiroEmpresa( arq.getRota().getId(), arq.getAnoMesReferencia(), leiturista.getId() );								
							}
							
							arq.setNumeroSequenciaLeitura(numeroSequencial);
							arq.setUltimaAlteracao(new Date());
							arq.setCodigoLeiturista(leiturista.getCodigoDDD());
							arq.setNumeroFoneLeiturista(leiturista.getNumeroFone());
							
							f.atualizar(arq);
							
							if ( !rotaDividida ){
								arq.getRota().setLeiturista(leiturista);	
							}							
							
							arq.getRota().setNumeroSequenciaLeitura(numeroSequencial);
							arq.getRota().setUltimaAlteracao(new Date());
							
							f.atualizar(arq.getRota());
							
							// Caso seja dividido selecionamos o arquivo informado
							if ( rotaDividida ){
								FiltroArquivoTextoRoteiroEmpresaDivisao filtroDividido = new FiltroArquivoTextoRoteiroEmpresaDivisao();
								filtroDividido.adicionarParametro( new ParametroSimples( FiltroArquivoTextoRoteiroEmpresaDivisao.ID, form.getArquivoDivididoID() ) );
								filtroDividido.adicionarCaminhoParaCarregamentoEntidade( "leiturista.usuario" );
								colecao = f.pesquisar(filtroDividido, ArquivoTextoRoteiroEmpresaDivisao.class.getName());								
								
								ArquivoTextoRoteiroEmpresaDivisao arqDivisao = 
									(ArquivoTextoRoteiroEmpresaDivisao) colecao.iterator().next();
								
								arquivo = this.getByteArrayArquivoDivido( arqDivisao );								
								is = new ByteArrayInputStream( arquivo );
								
								if(colecao!=null && !colecao.isEmpty()){
									// Carregamos o login e a senha do leiturista antigo
									novosDados = selecionarLoginSenhaNova( leiturista );

									novoLogin = novosDados[0];
									novaSenha = novosDados[1];
									
									dadosAntigos = selecionarLoginSenhaAntigos( arqDivisao );

									loginAntigo = dadosAntigos[0];
									senhaAntiga = dadosAntigos[1];
									
									bufferArquivo = trocarLoginSenhaArquivo( is, loginAntigo, senhaAntiga, novoLogin, novaSenha );
									
									arquivo = this.getByteArrayArquivoDivido( arqDivisao );								
									is = new ByteArrayInputStream( arquivo );
									
									// Altermos o leiturista desses imóveis em movimento_roteiro_empresa
									Collection<Integer> colIdsImovel = carregarIdsImovel( is );
									f.alterarLeituristaMovimentoRoteiroEmpresa( colIdsImovel, arq.getAnoMesReferencia(), leiturista.getId() );
									
									compactarSalvarArquivoDividido( arqDivisao, bufferArquivo );
									
									arqDivisao.setLeiturista(leiturista);
									arqDivisao.setNumeroImei(leiturista.getNumeroImei());
									
									f.atualizar( arqDivisao );
								}

								
							}
							
							montarPaginaSucesso(httpServletRequest, "Alteração do Leiturista do Arquivo Texto para Leitura com sucesso.",
									"Realizar outra Manutenção de Arquivo Texto para Leitura",
									"exibirAlterarLeituristaArquivoLeituraAction.do?menu=sim");
						}
					}
				}else{
					throw new ActionServletException("atencao.nenhum_arquivo_selecionado");
				}
		}else{
			throw new ActionServletException("atencao.nenhum_leiturista_selecionado");
		}
		
		return retorno;
	}
	
	/**
	 * 
	 * Verifica se deve descompactar o arquivo, caso afirmativo, 
	 * descompacta e ja retorna o arquivo descompactado
	 * 
	 * @param arq
	 * @return
	 * @throws ActionServletException
	 */
	private byte[] getByteArrayArquivo( ArquivoTextoRoteiroEmpresa arq ) throws ActionServletException{
		byte[] arquivo = arq.getArquivoTexto();
		
		if ( arq.getNomeArquivo().endsWith( ".gz" ) ){
			try {
				arquivo = GZIP.inflate( arquivo );
			} catch (IOException e) {
				throw new ActionServletException("Erro ao descompactar o arquivo");
			}
		}
		
		return arquivo;
		
	}
	
	/**
	 * 
	 * Seleciona o login e senha do novo leiturista,
	 * caso não tenha coloca a padrão
	 * 
	 * @param leiturista
	 * @return
	 */
	private String[] selecionarLoginSenhaNova( Leiturista leiturista ){
		// Carregamos o login e a senha do leiturista antigo
		String novoLogin = "";
		String novaSenha = "";
		
		if ( leiturista.getUsuario() != null ){
			novoLogin = leiturista.getUsuario().getLogin();
			novaSenha = leiturista.getUsuario().getSenha();
		} else {
			//LOGIN LEITURISTA
			novoLogin = "gcom";
			
			//SENHA LEITURISTA
			String senhaGerada = "senha";
			String senhaCriptografada = null;
			try {
				senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
			} catch (ErroCriptografiaException e1) {
				throw new ActionServletException("erro.criptografia.senha");
			}
			novaSenha = senhaCriptografada;								
		}
		
		String[] retorno = new String[2];
		
		retorno[0] = novoLogin;
		retorno[1] = novaSenha;
		
		return retorno;
	}
	
	/**
	 * 
	 * Seleciona o login e senha do leiturista antigo do arquivo texto reteiro empresa,
	 * caso não tenha coloca a padrão
	 *  
	 * @param arq
	 * @return
	 */
	private String[] selecionarLoginSenhaAntigos(  ArquivoTextoRoteiroEmpresa arq ){
		
		String loginAntigo = "";
		String senhaAntiga = "";
		
		if ( arq.getLeiturista().getUsuario() != null ){
			loginAntigo = arq.getLeiturista().getUsuario().getLogin();
			senhaAntiga = arq.getLeiturista().getUsuario().getSenha();
		} else {
			//LOGIN LEITURISTA
			loginAntigo = "gcom";
			
			//SENHA LEITURISTA
			String senhaGerada = "senha";
			String senhaCriptografada = null;
			try {
				senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
			} catch (ErroCriptografiaException e1) {
				throw new ActionServletException("erro.criptografia.senha");
			}
			senhaAntiga = senhaCriptografada;
		}
		
		String[] retorno = new String[2];
		
		retorno[0] = loginAntigo;
		retorno[1] = senhaAntiga;
		
		return retorno;
	}
	
	/**
	 * Recebe um inputstream, e retorna um String buffer ja com as informações trocadas
	 * 
	 * @param is
	 * @param loginAntigo
	 * @param senhaAntiga
	 * @param novoLogin
	 * @param novaSenha
	 * @return
	 */
	private StringBuffer trocarLoginSenhaArquivo ( 
			InputStream is, 
			String loginAntigo, 
			String senhaAntiga, 
			String novoLogin, 
			String novaSenha  ){
		
		Vector linhas = new Vector();	
		
		try {
			linhas = Util.carregaLinhas( is, 0 );
		} catch (IOException e) {
			throw new ActionServletException( "Erro ao carregar Arquivo" );
		}
		
		
		int contador = 0;
		StringBuffer bufferArquivo = new StringBuffer();
		
		for (Object linha : linhas) {
			if ( ("" + linha).contains( loginAntigo ) ){
				linhas.setElementAt( (""+linhas.elementAt( contador ) ).replace( Util.completaString( loginAntigo, 11 ), Util.completaString( novoLogin, 11 ) ), contador ); 
			}
			
			if ( ("" + linha).contains( senhaAntiga ) ){
				linhas.setElementAt( (""+linhas.elementAt( contador ) ).replace( Util.completaString( senhaAntiga, 40 ), Util.completaString( novaSenha, 40 ) ), contador );
			}
			
			bufferArquivo.append( linhas.elementAt( contador )+"\n" );
			
			contador++;
		}
		
		return bufferArquivo;		
	}	
	
	/**
	 * 
	 * Recompacta e salva o arquivo
	 * 
	 * @param arq
	 * @param bufferArquivo
	 */
	private void compactarSalvarArquivo( ArquivoTextoRoteiroEmpresa arq, StringBuffer bufferArquivo ){
		// ARQUIVO TEMPORÁRIO GERADO PARA ROTA
		   ByteArrayOutputStream baosArquivoZip = new ByteArrayOutputStream();
			  
		   GZIPOutputStream zos = null;
		   BufferedWriter out = null;
			
			try{							
			   File compactado = new File(arq.getNomeArquivo() + ".tar.gz");

				zos = new GZIPOutputStream(new FileOutputStream(compactado));
				File leitura = new File(arq.getNomeArquivo() + ".txt");

				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leitura.getAbsolutePath())));
				out.write(bufferArquivo.toString());
				out.flush();
				ZipUtil.adicionarArquivo(zos, leitura);

				zos.close();

				FileInputStream inputStream = new FileInputStream(compactado);

				// Escrevemos aos poucos
				int INPUT_BUFFER_SIZE = 1024;
				byte[] temp = new byte[INPUT_BUFFER_SIZE];
				int numBytesRead = 0;

				while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
					baosArquivoZip.write(temp, 0, numBytesRead);
				}

				arq.setArquivoTexto(baosArquivoZip
						.toByteArray());

				// Fechamos o inputStream
				inputStream.close();
				baosArquivoZip.close();

				inputStream = null;
				compactado.delete();
				leitura.delete();
			} catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
			}
	}
	
	/**
	 * 
	 * Seleciona o login e senha do leiturista antigo do arquivo texto reteiro empresa,
	 * caso não tenha coloca a padrão
	 *  
	 * @param arq
	 * @return
	 */
	private String[] selecionarLoginSenhaAntigos(  ArquivoTextoRoteiroEmpresaDivisao arq ){
		
		String loginAntigo = "";
		String senhaAntiga = "";
		
		if ( arq.getLeiturista().getUsuario() != null ){
			loginAntigo = arq.getLeiturista().getUsuario().getLogin();
			senhaAntiga = arq.getLeiturista().getUsuario().getSenha();
		} else {
			//LOGIN LEITURISTA
			loginAntigo = "gcom";
			
			//SENHA LEITURISTA
			String senhaGerada = "senha";
			String senhaCriptografada = null;
			try {
				senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
			} catch (ErroCriptografiaException e1) {
				throw new ActionServletException("erro.criptografia.senha");
			}
			senhaAntiga = senhaCriptografada;
		}
		
		String[] retorno = new String[2];
		
		retorno[0] = loginAntigo;
		retorno[1] = senhaAntiga;
		
		return retorno;
	}	
	
	/**
	 * 
	 * Verifica se deve descompactar o arquivo, caso afirmativo, 
	 * descompacta e ja retorna o arquivo descompactado
	 * 
	 * @param arq
	 * @return
	 * @throws ActionServletException
	 */
	private byte[] getByteArrayArquivoDivido( ArquivoTextoRoteiroEmpresaDivisao arq ) throws ActionServletException{
		byte[] arquivo = arq.getArquivoTexto();
		
		if ( arq.getNomeArquivo().endsWith( ".gz" ) ){
			try {
				arquivo = GZIP.inflate( arquivo );
			} catch (IOException e) {
				throw new ActionServletException("Erro ao descompactar o arquivo");
			}
		}
		
		return arquivo;
		
	}	
	
	/**
	 * 
	 * Recompacta e salva o arquivo
	 * 
	 * @param arq
	 * @param bufferArquivo
	 */
	private void compactarSalvarArquivoDividido( ArquivoTextoRoteiroEmpresaDivisao arq, StringBuffer bufferArquivo ){
		// ARQUIVO TEMPORÁRIO GERADO PARA ROTA
		   ByteArrayOutputStream baosArquivoZip = new ByteArrayOutputStream();
			  
		   GZIPOutputStream zos = null;
		   BufferedWriter out = null;
			
			try{							
			   File compactado = new File(arq.getNomeArquivo() + ".tar.gz");

				zos = new GZIPOutputStream(new FileOutputStream(compactado));
				File leitura = new File(arq.getNomeArquivo() + ".txt");

				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leitura.getAbsolutePath())));
				out.write(bufferArquivo.toString());
				out.flush();
				ZipUtil.adicionarArquivo(zos, leitura);

				zos.close();

				FileInputStream inputStream = new FileInputStream(compactado);

				// Escrevemos aos poucos
				int INPUT_BUFFER_SIZE = 1024;
				byte[] temp = new byte[INPUT_BUFFER_SIZE];
				int numBytesRead = 0;

				while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
					baosArquivoZip.write(temp, 0, numBytesRead);
				}

				arq.setArquivoTexto(baosArquivoZip
						.toByteArray());

				// Fechamos o inputStream
				inputStream.close();
				baosArquivoZip.close();

				inputStream = null;
				compactado.delete();
				leitura.delete();
			} catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
			}
	}
	
	/**
	 * Seleciona os imóveis que fazem parte desse arquivo
	 *  
	 * @author Bruno Barros
	 * @param is - InputStream do Arquivo
	 *  
	 * @return Coleção com os id's
	 */
	public Collection<Integer> carregarIdsImovel( InputStream is ){
		Vector linhas = new Vector();	
		
		try {
			linhas = Util.carregaLinhas( is, 0 );
		} catch (IOException e) {
			throw new ActionServletException( "Erro ao carregar Arquivo" );
		}
		
		Collection<Integer> retorno = new ArrayList();
		
		for (Object linha : linhas) {
			
			if ( (""+linha).substring( 0, 2 ).equals( "01" ) ){
				retorno.add( new Integer( ( ""+linha ).substring( 2, 11 ) ) );
			}
		}
		
		return retorno;	
		
	}
}

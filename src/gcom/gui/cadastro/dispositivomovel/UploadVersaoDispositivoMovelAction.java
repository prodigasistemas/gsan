package gcom.gui.cadastro.dispositivomovel;

import gcom.cadastro.FiltroVersaoMobile;
import gcom.cadastro.VersaoMobile;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.IOException;
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

/**
 * [UC1015] Upload nova versão GSAN Dispositivo Móvel
 * 
 *   Este caso de uso permite realizar o envio dos arquivos referentes à 
 *   nova versão do GSAN Dispositivo Móvel.
 * 
 * @author Hugo Amorim
 * @since 11/05/2010
 *
 */
public class UploadVersaoDispositivoMovelAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Controla se usuario informou
		// os dois arquivos  necessários.
		boolean informouJad = false;
		boolean informouJar = false;
		
		String appVersion = "";
		
		DiskFileUpload upload = new DiskFileUpload();
	
		FileItem arquivoJad = null;
		FileItem arquivoJar = null;
		
		try {
			
			List items = upload.parseRequest(httpServletRequest);
			
			FileItem item = null;
			
			Iterator iter = items.iterator();
			
			while (iter.hasNext()) {
				
				item = (FileItem) iter.next();
				
				// verifica se não é diretorio
				if (!item.isFormField()) {
					
					String nomeItem = item.getName().toUpperCase();
					
					if(nomeItem.endsWith(".JAD")){
						informouJad = true;
						
						arquivoJad = item;
						
                        // Abre o arquivo
                        InputStreamReader reader = new InputStreamReader(item.getInputStream());
                        BufferedReader buffer = new BufferedReader(reader);
                        
                        boolean eof = false;
                        
                        // Enquanto a variavel for false
                        // 6.	O sistema ler e copia os bytes do Arquivo .JAD.
                        while (!eof) {
                        	
                        	// Pega a linha do arquivo
                            String linhaLida = buffer.readLine();
                            
                            //7.	O sistema obtém o valor da propriedade 'App-Version' do arquivo .JAD. 
                            if (linhaLida != null && linhaLida.length() > 0 && linhaLida.contains("App-Version")) {

                            	appVersion = linhaLida.substring(13).trim();
                            	
                            }else{
                            	break;
                            }
                        }
						
						
					}else if(nomeItem.endsWith(".JAR")){
						informouJar = true;
						
						arquivoJar = item;
						
					}								
				}
			}
			
			if(!informouJad){
				throw new ActionServletException("atencao.arquivo_jad_invalido");
			}
			if(!informouJar){
				throw new ActionServletException("atencao.arquivo_jar_invalido");					
			}
			
			// FS004 - Versão existente
			FiltroVersaoMobile filtro = new FiltroVersaoMobile();
			
			filtro.adicionarParametro(
					new ParametroSimples(FiltroVersaoMobile.VERSAO_NUMERO,
							appVersion));
			
			Collection<VersaoMobile> colecaoVersaoMobile = 
				fachada.pesquisar(filtro, VersaoMobile.class.getName());
			
			if(colecaoVersaoMobile!=null
					&& !colecaoVersaoMobile.isEmpty()){
				throw new ActionServletException("atencao.versao_ja_existe");
			}
			
			
			//10.	O sistema armazena as informações lidas dos arquivos
			
			VersaoMobile versaoMobileInserir = new VersaoMobile();
			
			versaoMobileInserir.setNumeroVersao(appVersion);
			
			versaoMobileInserir.setArquivoJad(arquivoJad.get());
			
			versaoMobileInserir.setArquivoJar(arquivoJar.get());
			
			versaoMobileInserir.setDataEnvio(new Date());
			
			fachada.inserir(versaoMobileInserir);
			
						
		} catch (FileUploadException e) {
			throw new ActionServletException("atencao.erro_ao_armazenar_arquivo");
		} catch (IOException e) {
			throw new ActionServletException("atencao.erro_ao_armazenar_arquivo");
		}
		
		montarPaginaSucesso(httpServletRequest, "Arquivos enviados com sucesso.",
				"Realizar outro upload.",
				"exibirUploadVersaoDispositivoMovelAction.do?menu=sim");
		
		return retorno;
	}
}

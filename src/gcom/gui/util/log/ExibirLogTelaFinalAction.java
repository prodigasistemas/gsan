package gcom.gui.util.log;

import gcom.gui.GcomAction;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirLogTelaFinalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirLogTelaFinal");
		ExibirLogActionForm form = (ExibirLogActionForm) actionForm;
		StringBuffer textoErro = new StringBuffer("");

		try{
			int TAMANHO_PAGINA = 1500;
			
			String nomeArquivo = form.getArquivo();
			
			//File file = new File("C:\\jboss-4.0.1sp1\\server\\default\\log\\"+nomeArquivo);
			File file = new File("/usr/local/jboss/server/default/log/"+nomeArquivo);
			
			FileReader fw = new FileReader(file);
			BufferedReader bis = new BufferedReader (fw);
			
			try {		
				
				String ini = httpServletRequest.getParameter("inicio");
				String numeroPagina = httpServletRequest.getParameter("numeroPagina");
				String verTud = httpServletRequest.getParameter("verTudo");
				String palavra = httpServletRequest.getParameter("palavra");
								
				boolean verTudo = false;
				int inicio = 0;
				int tamanhoDoArquivo = 0;
				
				while ( bis.readLine() != null ){
					tamanhoDoArquivo++;
				}
				if (bis != null){
					bis.close();	
				}
				if (fw != null){
					fw.close();
				}
				
				fw = new FileReader(file);				
				bis = new BufferedReader (fw);
				
				if (verTud != null && !verTud.equals("")) {
					verTudo = true;
				}

				if (ini != null && !ini.equals("")) {
					inicio = Integer.parseInt(ini);
				
				}else if(numeroPagina != null && !numeroPagina.equals("") ){
					inicio = Integer.parseInt(numeroPagina);
					inicio = inicio*TAMANHO_PAGINA;
				
				}

				if( verTudo ){
					
					String line;
					while ( (line = bis.readLine()) != null){
						textoErro.append(line);
						textoErro.append("<br>");
					}
				}else if( palavra != null && !palavra.equals("") ){				

					String line;
					boolean temLinha = false;
					int tam = 0;
					int in = 0;
					
					while ( (line = bis.readLine()) != null && tam <= TAMANHO_PAGINA){
						in++;
						
						int posicao = line.indexOf(palavra);
						if( posicao != -1 ){
							
							textoErro.append(line.substring(0,posicao));
							textoErro.append("<span style=\"background:#44ff77;\" >");
							textoErro.append(line.substring(posicao,posicao+palavra.length()));
							textoErro.append("</span>");
							textoErro.append(line.substring(posicao+palavra.length()));
							textoErro.append("<br>");
							
							temLinha = true;
							
							if(tam == 0){
								inicio = in;
							}

							tam++;
							
							
						}else if(temLinha) {
							textoErro.append(line);
							textoErro.append("<br>");
							tam++;
						}
					
					}
				
				
				}else{
				
					int tamanhoFinal = inicio + TAMANHO_PAGINA; 
					int i = 0;
	
					String line;
					while ( (line = bis.readLine()) != null && (i <= tamanhoFinal) ){
						i++;
						if(i >= inicio) {
							textoErro.append(line);
							textoErro.append("<br>");
						}
					}
				}
				
				int anterior = inicio-TAMANHO_PAGINA;
				int proximo = inicio+TAMANHO_PAGINA ;
				int ultima 	= tamanhoDoArquivo-TAMANHO_PAGINA;
				int paginaAtual = inicio/TAMANHO_PAGINA;
				
				if(inicio != 0 ){
					form.setInicio(""+inicio);	
				}else{
					form.setInicio(null);
				}
				
				form.setAnterior(""+anterior);
				form.setProximo(""+proximo);
				form.setUltima(""+ultima);
				form.setPaginaAtual(""+paginaAtual);
				
			} catch (EOFException eof){
				System.out.println("Fim do arquivo");
			} finally {
				if (bis != null){
					bis.close();	
				}
				if (fw != null){
					fw.close();
				}
			}
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
        
		form.setTextoErro(textoErro.toString());
		
		
        return retorno;
	}

}

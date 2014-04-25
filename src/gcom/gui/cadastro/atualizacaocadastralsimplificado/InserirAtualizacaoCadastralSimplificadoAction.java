package gcom.gui.cadastro.atualizacaocadastralsimplificado;

import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.IoUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
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
 * 
 * Lê o arquivo a partir dos parâmetros da requisição e insere no BD.
 * [UC0969] Importar arquivo de atualização cadastral simplificado
 * 
 * 
 * @author Samuel Valerio
 * 
 * @date 21/10/2009
 * 
 * 
 */
public class InserirAtualizacaoCadastralSimplificadoAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");
		
		DiskFileUpload upload = new DiskFileUpload();
		
		Integer idArquivo = null;
		String nomeArquivo = null;
		
		try {
			List items = upload.parseRequest(httpServletRequest);
			
			if (items != null) {
				FileItem item = null;
				String comentario = null;
				
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					item = (FileItem) iter.next();
					
					if (item.getFieldName().equals("comentario")) {
						comentario = item.getString().toUpperCase();
					}
					
					// Caso não seja um field do formulario
	                // é o arquivo
	                if ( !item.isFormField() ){
	                	InputStreamReader reader;
						try {
							reader = new InputStreamReader(item.getInputStream());
							BufferedReader buffer = new BufferedReader(reader);
							
							Collection<AtualizacaoCadastralSimplificadoLinha> linhas = new HashSet<AtualizacaoCadastralSimplificadoLinha>();
							
							final AtualizacaoCadastralSimplificado arquivo = new AtualizacaoCadastralSimplificado();
							
							String linha;
							int numeroLinha = 0;
							StringBuffer conteudoDoArquivo = new StringBuffer();
							while ((linha = buffer.readLine()) != null) {
								final AtualizacaoCadastralSimplificadoLinha linhaObj = new AtualizacaoCadastralSimplificadoLinha(linha.split("#"), ++numeroLinha);
								linhaObj.setArquivo(arquivo);
								linhas.add(linhaObj);
								conteudoDoArquivo.append(linha);
								conteudoDoArquivo.append(System.getProperty("line.separator"));
							}
							
							arquivo.setComentario(comentario);
							arquivo.setUsuario((Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado"));
							AtualizacaoCadastralSimplificadoBinario arquivoBinario = new AtualizacaoCadastralSimplificadoBinario();
							arquivoBinario.setBinario(IoUtil.transformarObjetoParaBytes(conteudoDoArquivo));
							arquivo.setNome(item.getName());
							idArquivo = Fachada.getInstancia().inserirArquivoAtualizacaoCadastralSimplificado(arquivo, arquivoBinario, linhas);
							
							// colocando o nome do arquivo em uma variável para exibí-lo na página de sucesso
							nomeArquivo = arquivo.getNome();
							
						} catch (IOException e) {
							throw new ActionServletException("erro.inserir_arquivo_atualizacao_cadastral_simplificado.envio");
						}
	                    
	                }
					
				}
			}
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.inserir_arquivo_atualizacao_cadastral_simplificado.envio");
		}
		
		montarPaginaSucesso(httpServletRequest, "O arquivo " + nomeArquivo

				+ " foi importado com sucesso.", "Visualizar detalhes da importação",

				"exibirDetalhesAtualizacaoCadastralSimplificadoAction.do?id="+idArquivo);

		return retorno;
	}
}

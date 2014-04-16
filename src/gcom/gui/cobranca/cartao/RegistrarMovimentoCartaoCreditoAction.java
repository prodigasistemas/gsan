package gcom.gui.cobranca.cartao;

import gcom.arrecadacao.Arrecadador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RegistrarMovimentoCartaoCreditoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		try {

			Integer idArrecadador = null;
			Arrecadador arrecadador = null;

			DiskFileUpload upload = new DiskFileUpload();

			// Parse the request
			List items = upload.parseRequest(httpServletRequest);

			FileItem item = null;

			// cria uma string builder que recupera o txt e joga para o
			// controlador para o processamento
			StringBuilder stringBuilderTxt = new StringBuilder();

			// cria um contador para saber quantas linhas terá o txt
			int quantidadeRegistros = 0;
			
			// pega uma lista de itens do form
			Iterator iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();

				//ARRECADADOR 
				if (item.getFieldName().equals("idArrecadador")) {
					idArrecadador = new Integer(item.getString());
					
					/*
					 * [FS0001 – Verificar existência do arrecadador]
					 * [FS0002 – Verificar arrecadação forma cartão crédito]
					 */
					arrecadador = fachada.verificarArrecadacaoFormaCartaoCredito(Integer.valueOf(idArrecadador));
				}

				// verifica se não é diretorio
				if (!item.isFormField()) {

					// coloca o nome do item para maiusculo
					String nomeItem = item.getName().toUpperCase();
					
					// compara o final do nome do arquivo que deve ser .RET
					if (nomeItem.endsWith(".RET")){

						// Abre o arquivo
						InputStreamReader reader = new InputStreamReader(item.getInputStream());
						BufferedReader buffer = new BufferedReader(reader);

						// StringBuffer linha = new StringBuffer();
						// cria uma variavel do tipo boolean
						boolean eof = false;
						// enquanto a variavel for false
						while (!eof) {

							// pega a linha do arquivo
							String linhaLida = buffer.readLine();
							
							// se for a ultima linha do arquivo
							if (linhaLida != null && linhaLida.length() > 0) {
								
								String codigoRegistro = linhaLida.substring(0, 1).toUpperCase();
								Character codigoRegistroCharacter = codigoRegistro.charAt(0);
								
								/*
								 * CÓDIGO ASC
								 * 0 = 48
								 * 9 = 57
								 */
								if (((int) codigoRegistroCharacter) >= 48 && ((int) codigoRegistroCharacter) <= 57) {

									//Completa a string para o tamanho de 251 caso necessario.
									String linhaCompleta = null;
									if (linhaLida.length() != 251) {
										linhaCompleta = Util.completaString(linhaLida, 251);
									} else {
										linhaCompleta = linhaLida;
									}
									
									stringBuilderTxt.append(linhaCompleta);
									stringBuilderTxt.append("\n");
									quantidadeRegistros = quantidadeRegistros + 1;
								} 
								else {
									break;
								}
							} 
							else {
								break;
							}

						}

						// fecha o arquivo
						buffer.close();
						reader.close();
						item.getInputStream().close();

					}
					else {
						throw new ActionServletException("atencao.tipo_importacao.nao_ret");
					}

				}
			}

			if (quantidadeRegistros != 0) {
				
				this.getFachada().registrarMovimentoCartaoCredito(arrecadador, stringBuilderTxt, 
				quantidadeRegistros, usuarioLogado);
				
			} else {
				throw new ActionServletException("atencao.importacao.nao_concluida");
			}

		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");

		} catch (NumberFormatException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}

		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Movimento Cartão de Crédito Enviado para Processamento", 
			"Voltar",
			"/exibirRegistrarMovimentoCartaoCreditoAction.do");

		return retorno;
	}

}

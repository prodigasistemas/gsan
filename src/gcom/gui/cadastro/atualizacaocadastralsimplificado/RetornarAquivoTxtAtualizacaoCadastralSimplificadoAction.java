package gcom.gui.cadastro.atualizacaocadastralsimplificado;

import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Retornar o arquivo texto a partir do BD.
 * [UC0969] Importar arquivo de atualização cadastral simplificado
 * 
 * 
 * @author Samuel Valerio
 * 
 * @date 21/10/2009
 * 
 * 
 */
public class RetornarAquivoTxtAtualizacaoCadastralSimplificadoAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;

		String idArquivoTxt = httpServletRequest.getParameter("id");

		if (idArquivoTxt == null)
			throw new ActionServletException(
					"atencao.arquivo_texto_id_deve_ser_informado");

		try {
			Integer.parseInt(idArquivoTxt);
		} catch (NumberFormatException nfe) {
			throw new ActionServletException(
					"atencao.arquivo_texto_id_deve_ser_numerico", null, idArquivoTxt);
		}

		Fachada fachada = Fachada.getInstancia();
		
		FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario filtro = new FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario();
		filtro.adicionarParametro(new ParametroSimples(FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario.ARQUIVO_ID, idArquivoTxt));
		filtro.adicionarCaminhoParaCarregamentoEntidade("arquivo");
		
		AtualizacaoCadastralSimplificadoBinario binario = (AtualizacaoCadastralSimplificadoBinario) Util
				.retonarObjetoDeColecao(fachada.pesquisar(filtro,
						AtualizacaoCadastralSimplificadoBinario.class.getName()));
		
		if (binario == null)
			throw new ActionServletException(
					"atencao.arquivo_texto_nao_encontrado_para_o_id_informado", null, idArquivoTxt);

		try {
			File temporario = File.createTempFile("temporario", ".txt");
	
			FileOutputStream outputStream = new FileOutputStream(temporario);
			outputStream.write(((StringBuffer) IoUtil
					.transformarBytesParaObjeto(binario.getBinario())).toString()
					.getBytes());
			outputStream.close();
	
			FileInputStream inputStream = new FileInputStream(temporario);
	
			int INPUT_BUFFER_SIZE = 1024;
			byte[] temp = new byte[INPUT_BUFFER_SIZE];
			int numBytesRead = 0;
	
			ByteArrayOutputStream arquivoStream = new ByteArrayOutputStream();
	
			while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
				arquivoStream.write(temp, 0, numBytesRead);
			}
	
			inputStream.close();
			inputStream = null;

			arquivoStream.close();
			httpServletResponse.setContentType("text/plain");
			httpServletResponse.addHeader("Content-Disposition",
					"attachment; filename=" + binario.getArquivo().getNome());
			ServletOutputStream out;

			out = httpServletResponse.getOutputStream();
			out.write(arquivoStream.toByteArray());
			out.flush();
			out.close();

			temporario.delete();
		} catch (IOException e) {
			reportarErros(httpServletRequest, "erro.sistema");

		} catch (ClassNotFoundException e) {
			reportarErros(httpServletRequest, "erro.sistema");
		}

		return retorno;
	}

}

package gcom.gui.cadastro.atualizacaocadastralsimplificado;

import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.FiltroAtualizacaoCadastralSimplificado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Carrega os dados necessários e redireciona para a página que
 * exibe os detalhes da importação.
 * 
 * [UC0969] Importar arquivo de atualização cadastral simplificado
 * 
 * @author Samuel Valerio
 * 
 * @date 27/10/2009
 * 
 * 
 */
public class ExibirDetalhesAtualizacaoCadastralSimplificadoAction extends GcomAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward retorno = mapping.findForward("exibirDetalhesAtualizacaoCadastralSimplificado");
		
		String idArquivoTxt = request.getParameter("id");

		validarSeIdNaoEhNulo(idArquivoTxt);

		int idArquivoTxtInt = validarSeIdEhNumerico(idArquivoTxt);

		Fachada fachada = Fachada.getInstancia();

		AtualizacaoCadastralSimplificado arquivo = buscarArquivoTxt(idArquivoTxt, fachada);
		
		if (arquivo == null)
			throw new ActionServletException(
					"atencao.arquivo_texto_nao_encontrado_para_o_id_informado", null, idArquivoTxt);
		
		request.setAttribute("arquivo", arquivo);
		request.setAttribute("criticas", fachada.pesquisarAtualizacaoCadastralSimplificadoCritica(idArquivoTxtInt));
		
		return retorno;
	}

	/**
	 * Busca o arquivo txt no banco de dados.
	 *  
	 * @param idArquivoTxt Id do arquivo a ser procurado.
	 * @param fachada Fachada para invocar o método de busca.
	 * @return O arquivo texto correspondente ao id procurado.
	 */
	@SuppressWarnings("unchecked")
	private AtualizacaoCadastralSimplificado buscarArquivoTxt(String idArquivoTxt,
			Fachada fachada) {
		FiltroAtualizacaoCadastralSimplificado filtro = new FiltroAtualizacaoCadastralSimplificado();
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtro.adicionarParametro(new ParametroSimples(
				FiltroAtualizacaoCadastralSimplificado.ID, idArquivoTxt));

		AtualizacaoCadastralSimplificado arquivo = (AtualizacaoCadastralSimplificado) Util
				.retonarObjetoDeColecao(fachada.pesquisar(filtro,
						AtualizacaoCadastralSimplificado.class.getName()));
		return arquivo;
	}

	/**
	 * Valida se o id do arquivo txt passado como parâmetro é numérico.
	 * Se for, retornar o valor convertido para inteiro. Caso contrário,
	 * lança uma exceção.
	 * 
	 * @param idArquivoTxt Id do arquivo txt.
	 * @return O valor convertido para inteiro.
	 */
	private int validarSeIdEhNumerico(String idArquivoTxt) {
		int idArquivoTxtInt = 0;
		try {
			idArquivoTxtInt = Integer.parseInt(idArquivoTxt);
		} catch (NumberFormatException nfe) {
			throw new ActionServletException(
					"atencao.arquivo_texto_id_deve_ser_numerico", null, idArquivoTxt);
		}
		return idArquivoTxtInt;
	}

	/**
	 * Valida se o id do arquivo txt informado como parâmetro não é nulo.
	 * Se for, lança uma exceção.
	 * 
	 * @param idArquivoTxt Id do arquivo txt a ser validado.
	 */
	private void validarSeIdNaoEhNulo(String idArquivoTxt) {
		if (idArquivoTxt == null)
			throw new ActionServletException(
					"atencao.arquivo_texto_id_deve_ser_informado");
	}
}

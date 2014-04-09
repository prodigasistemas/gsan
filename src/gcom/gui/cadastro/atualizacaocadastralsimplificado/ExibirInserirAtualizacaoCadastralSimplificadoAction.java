package gcom.gui.cadastro.atualizacaocadastralsimplificado;

import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.FiltroAtualizacaoCadastralSimplificado;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Carrega os dados necessários e redireciona para a página que invocará o
 * [UC0969] Importar arquivo de atualização cadastral simplificado
 * 
 * 
 * @author Samuel Valerio
 * 
 * @date 21/10/2009
 * 
 * 
 */
public class ExibirInserirAtualizacaoCadastralSimplificadoAction extends GcomAction {
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirAtualizacaoCadastralSimplificado");
		
		Fachada fachada = Fachada.getInstancia();
		
		// buscando os arquivos que já foram importados para exibí-los na página
		final FiltroAtualizacaoCadastralSimplificado filtro = new FiltroAtualizacaoCadastralSimplificado("ultimaAlteracao desc");
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuario");
		Collection<AtualizacaoCadastralSimplificado> arquivos = fachada.pesquisar(filtro, AtualizacaoCadastralSimplificado.class.getName());

		if (arquivos != null && !arquivos.isEmpty()) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtro, AtualizacaoCadastralSimplificado.class.getName());
			arquivos = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			httpServletRequest.setAttribute("arquivos", arquivos);
		}

		return retorno;
	}

}

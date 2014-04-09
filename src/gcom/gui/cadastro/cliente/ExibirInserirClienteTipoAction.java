package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Este cado de uso Permite Inserir um Tipo de Cliente [UC???] Inserir Tipo 
 * Cliente
 * 
 * @author Thiago Tenório
 * @date 07/02/2007
 */
public class ExibirInserirClienteTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirClienteTipo");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirClienteTipoActionForm inserirClienteTipoActionForm = (InserirClienteTipoActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			// -------------- bt DESFAZER ---------------

			// Limpando o formulario
			inserirClienteTipoActionForm.setDescricao("");
			inserirClienteTipoActionForm.setTipoPessoa("1");
			inserirClienteTipoActionForm.setEsferaPoder("");

		}

		if (inserirClienteTipoActionForm.getTipoPessoa().equals("1")) {
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);

			filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.ID, EsferaPoder.PARTICULAR));

			// Retorna Esfera do Poder
			Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder,
					EsferaPoder.class.getName());

			if (colecaoEsferaPoder == null || colecaoEsferaPoder.isEmpty()) {
				// Nenhum registro na tabela esfera_poder foi encontrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Esfera poder");
			} else {
				sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
			}
		} else {
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);

			filtroEsferaPoder.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroEsferaPoder.ID, EsferaPoder.PARTICULAR));
			filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Esfera do Poder
			Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder,
					EsferaPoder.class.getName());

			if (colecaoEsferaPoder == null || colecaoEsferaPoder.isEmpty()) {
				// Nenhum registro na tabela esfera_poder foi encontrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Esfera poder");
			} else {
				sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
			}

		}

		// devolve o mapeamento de retorno
		return retorno;
	}

}

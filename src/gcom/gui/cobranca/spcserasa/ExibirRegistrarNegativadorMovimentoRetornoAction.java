package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
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


/**
 * Action que define o pré-processamento da página de filtrar do Tipo de Registro do Negativador
 * 
 * @author Yara Taciane de Souza
 * @created 09/01/2008
 */
public class ExibirRegistrarNegativadorMovimentoRetornoAction extends GcomAction {
	/**
	 * Este caso de uso permite o registro do movimento de retorno dos negativadores
	 * 
	 * [UC0672] Registrar Movimento de Retorno dos Negativadores
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 09/01/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("registrarNegativadorMovimentoRetorno");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		

		// Parte que trata do código quando o usuário tecla enter
		
		if (httpServletRequest.getParameter("objetoConsulta") != null) {

			try {
				
				DiskFileUpload upload = new DiskFileUpload();

				// Parse the request
				List items = upload.parseRequest(httpServletRequest);

				if (items != null) {
					@SuppressWarnings("unused")
					FileItem item = null;

					// pega uma lista de itens do form
					Iterator iter = items.iterator();
					while (iter.hasNext()) {

						item = (FileItem) iter.next();						

					}
				}
			} catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}

			httpServletRequest.getParameter("caminho");

			

		}

		// Criação das coleções
		Collection colecaoNegativador = (Collection) sessao.getAttribute("colecaoNegativador");

		if (colecaoNegativador == null) {

			FiltroNegativador filtroNegativador = new FiltroNegativador();			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.adicionarParametro(
					new ParametroSimples(FiltroNegativador.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));	
			filtroNegativador.setConsultaSemLimites(true);

			colecaoNegativador = fachada.pesquisar(filtroNegativador,
					Negativador.class.getName());

			if (colecaoNegativador == null || colecaoNegativador.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"NEGATIVADOR");
			} else {
				sessao.setAttribute("colecaoNegativador", colecaoNegativador);
			}
		}

	
		

		return retorno;
	}
    
   

}
 

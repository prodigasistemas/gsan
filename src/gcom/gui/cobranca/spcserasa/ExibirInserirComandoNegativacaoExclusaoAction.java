package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da inserção de um Comando de Negativação (Aba nº 05 - Exclusão) 
 *
 * @author Vivianne Sousa
 * @date 05/07/2010
 */
public class ExibirInserirComandoNegativacaoExclusaoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirComandoNegativacaoExclusao");
     
    	HttpSession sessao = httpServletRequest.getSession(false);

    	Fachada fachada = Fachada.getInstancia();
    	
		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
        
		// Pesquisando motivo de retorno
        if(sessao.getAttribute("colecaoRetornoMotivo") == null){
        	
        	FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
        	filtroNegativadorRetornoMotivo.setCampoOrderBy(FiltroNegativadorRetornoMotivo.DESCRICAO_RETORNO_CODIGO);
        	filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
        			FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR,
        			new Integer(inserirComandoNegativacaoActionForm.getIdNegativador())));
        	filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
        		FiltroNegativadorRetornoMotivo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
        	
			Collection colecaoRetornoMotivo = fachada.pesquisar(filtroNegativadorRetornoMotivo,
					NegativadorRetornoMotivo.class.getName());
			
			if (colecaoRetornoMotivo != null && !colecaoRetornoMotivo.isEmpty()) {
				sessao.setAttribute("colecaoRetornoMotivo",	colecaoRetornoMotivo);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Motivo de Retorno");
			}
        }

    	return retorno;
    }
 
}

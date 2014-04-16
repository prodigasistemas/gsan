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
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class FiltrarNegativadorRetornoMotivoAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirManterNegativadorRetornoMotivo");
		HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarNegativadorRetornoMotivoActionForm form = (FiltrarNegativadorRetornoMotivoActionForm) actionForm; 
        
        Integer idNegativador = 0;
        short codigoRetornoMotivo = 0;
        String descricaoRetornoMotivo = "";
        short indicadorRegistroAceito = 0;
        String checkAtualizar = "";
        short indicadorUso = 0;
        
        if (form.getIdNegativador() != null && !form.getIdNegativador().equals("")){
        	idNegativador = new Integer(form.getIdNegativador());
        }
        if (form.getCodigoMotivo() != null && !form.getCodigoMotivo().equals("")){
        	codigoRetornoMotivo = Short.parseShort(form.getCodigoMotivo());
        } 
        if (form.getDescricaoRetornoMotivo() != null && !form.getDescricaoRetornoMotivo().equals("") ){
        	descricaoRetornoMotivo = form.getDescricaoRetornoMotivo();
        }
        if (form.getIndicadorRegistroAceito() != null && !form.getIndicadorRegistroAceito().equals("") && !form.getIndicadorRegistroAceito().equals(ConstantesSistema.TODOS.toString())){
        	indicadorRegistroAceito = Short.parseShort(form.getIndicadorRegistroAceito());
        }
        if (form.getIndicadorUso() != null && !form.getIndicadorUso().equals("") && !form.getIndicadorUso().equals(ConstantesSistema.TODOS.toString())){
        	indicadorUso = Short.parseShort(form.getIndicadorUso());
        }
        if (form.getCheckAtualizar() != null && !form.getCheckAtualizar().equals("")){
        	checkAtualizar = form.getCheckAtualizar();
        }
        
		FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
		
		if (form.getIdNegativador() != null && !form.getIdNegativador().equals("")){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, idNegativador));
		}
		
		if (form.getCodigoMotivo() != null && !form.getCodigoMotivo().equals("")){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, codigoRetornoMotivo));
		}
		
		if (form.getDescricaoRetornoMotivo() != null && !form.getDescricaoRetornoMotivo().equals("")){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.DESCRICAO_RETORNO_CODIGO, descricaoRetornoMotivo));
		}
		
		if (form.getIndicadorRegistroAceito() != null && !form.getIndicadorRegistroAceito().equals("") && !form.getIndicadorRegistroAceito().equals(ConstantesSistema.TODOS.toString())){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, indicadorRegistroAceito));
		}
		
		if (form.getIndicadorUso() != null && !form.getIndicadorUso().equals("") && !form.getIndicadorUso().equals(ConstantesSistema.TODOS.toString())){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.INDICADOR_USO, indicadorUso));
		}

		//check do código do motivo
        //caso não exista, informa erro no preenchimento do campo de codigo de retorno
        if (codigoRetornoMotivo != 0){
			FiltroNegativadorRetornoMotivo fNRM = new FiltroNegativadorRetornoMotivo();
			fNRM.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, codigoRetornoMotivo));
        	
        	Collection codigoMotivoEncontrado = Fachada.getInstancia().pesquisar(fNRM, NegativadorRetornoMotivo.class.getName());
        	        	
        	if (codigoMotivoEncontrado == null || codigoMotivoEncontrado.isEmpty()) {
        		throw new ActionServletException("atencao.codigo_motivo_negativador_retorno_motivo_nao_existe_cadastro");
        	}
		}
		
		sessao.setAttribute("filtroNegativadorRetornoMotivo",filtroNegativadorRetornoMotivo);
		sessao.setAttribute("checkAtualizar",checkAtualizar);
		
		return retorno;
        
    }
}

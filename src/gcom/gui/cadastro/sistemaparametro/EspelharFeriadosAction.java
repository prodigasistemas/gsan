package gcom.gui.cadastro.sistemaparametro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0535]MANTER FERIADO
 * [SB0003] Criar espelhos dos feriados existentes
 * 
 * @author Bruno Barros
 * @date 12/01/2009
 */



public class EspelharFeriadosAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward(
                "telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
        EspelharFeriadosActionForm form = 
            (EspelharFeriadosActionForm) actionForm;
		
		
		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
		
		String indicadorTipoFeriado = form.getIndicadorTipoFeriado();
        String anoOrigem = form.getAnoOrigemFeriado();
        String anoDestino = form.getAnoDestinoFeriado();
        
        // [FS002] - Verificar preenchimento dos campos
        if ( indicadorTipoFeriado == null || 
             "".equals( indicadorTipoFeriado ) ){
            throw new ActionServletException(
                    "atencao.campo.informado", null, "Tipo de feriado");
        }
        
        if ( anoOrigem == null || "".equals( anoOrigem ) ){
            throw new ActionServletException(
                    "atencao.campo.informado", null, "Ano de Origem");
        }
        
        if ( anoDestino == null || "".equals( anoDestino ) ){
            throw new ActionServletException(
                    "atencao.campo.informado", null, "Ano de Destino");
        }
        
        // Espelhamos os feriados
        fachada.espelharFeriados( indicadorTipoFeriado, anoOrigem, anoDestino );
        
        //Monta a página de sucesso
        montarPaginaSucesso(httpServletRequest,
                "Feriado(s) espelhado(s) com sucesso",
                "Espelhar mais feriados","exibirEspelharFeriadosAction.do?menu=sim",
                "","");

        return retorno;		
		
	}
}
	

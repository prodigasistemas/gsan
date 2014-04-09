package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da pesquisa de Observação do Registro Atendimento (Solicitação da CAER)
 *
 * @author Rafael Pinto
 * @date 14/03/2007
 */
public class PesquisarObservacaoRegistroAtendimentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("pesquisarObservacaoRegistroAtendimento");

        PesquisarObservacaoRegistroAtendimentoActionForm form = 
        	(PesquisarObservacaoRegistroAtendimentoActionForm) actionForm;
        
        Integer matriculaImovel = null;

        if (form.getMatriculaImovel() == null || form.getMatriculaImovel().equals("")){
        	throw new ActionServletException("errors.required", null, "município");	
        }else{
        	matriculaImovel = new Integer(form.getMatriculaImovel());
        }
        
        Date periodoAtendimentoInicial = null;
        Date periodoAtendimentoFinal = null;

        if (form.getPeriodoAtendimentoInicial() != null && !form.getPeriodoAtendimentoInicial().equals("")){
        	periodoAtendimentoInicial = Util.converteStringParaDate(form.getPeriodoAtendimentoInicial());
        }
        
        if (form.getPeriodoAtendimentoFinal() != null && !form.getPeriodoAtendimentoFinal().equals("")){
        	periodoAtendimentoFinal = Util.converteStringParaDate(form.getPeriodoAtendimentoFinal());
        }        
        

        Collection<RegistroAtendimento> colecaoRA = new ArrayList();
        
        colecaoRA = 
        	this.getFachada().pesquisarObservacaoRegistroAtendimento(matriculaImovel,periodoAtendimentoInicial,periodoAtendimentoFinal);
        
        if(colecaoRA == null || colecaoRA.isEmpty()) {
            throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Registro Atendimento");
        }
        
        httpServletRequest.setAttribute("colecaoRA", colecaoRA);

        
        return retorno;
    }

}

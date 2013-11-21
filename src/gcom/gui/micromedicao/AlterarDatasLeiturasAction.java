package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.AlterarDatasLeiturasHelper;
import gcom.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Action para alteração das datas de leituras 
 *
 * @author bruno
 * @date 26/02/2009
 */
public class AlterarDatasLeiturasAction extends GcomAction {
    
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse arg3) throws Exception {

        HttpSession sessao = httpServletRequest.getSession(false);
        
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        AlterarDatasLeiturasActionForm form = 
            (AlterarDatasLeiturasActionForm) actionForm;	
        
        Collection<AlterarDatasLeiturasHelper> colHelper =
            ( Collection<AlterarDatasLeiturasHelper> )
                sessao.getAttribute( "colecaoHelper" );
        
        processar( form, colHelper );
        
        montarPaginaSucesso(httpServletRequest, 
                "Datas informadas alteradas com sucesso.", 
                "Alterar outras datas",
                "exibirAlterarDatasLeiturasAction.do?menu=sim");
        
        return retorno;
	}
    
    /**
     * 
     * [UC0889] Alterar Datas das Leituras
     * 
     * Montamos o helper e alteramos as datas solicitadas
     *
     * @author bruno
     * @date 26/02/2009
     *
     * @param httpServletRequest
     */    
    private void processar( 
            AlterarDatasLeiturasActionForm form, 
            Collection<AlterarDatasLeiturasHelper> colHelper ){
        
        Object[] arrayHelper = colHelper.toArray();
        
        Collection<AlterarDatasLeiturasHelper> colHelperParaAlteracao
            = new ArrayList();
        
        if ( form.getArrDtAtual() != null || form.getArrDtAnterior() != null  ){
            for ( int i=0; i<form.getArrDtAtual().length; i++ ){
                String dtAtual = form.getArrDtAtual()[i];
                String dtAnterior = form.getArrDtAnterior()[i];
                
                // Caso ambas as datas estejam nulas elas não foram informadas...
                if ( ( dtAtual == null || dtAtual.equals( "" ) ) &&
                     ( dtAnterior == null || dtAnterior.equals( "" ) ) ){
                    continue;
                }
                
                // [FS0005] Validar data de leitura
                if ( ( dtAtual == null || dtAtual.equals( "" ) ) &&
                     ( dtAnterior != null && !dtAtual.equals( "" ) ) ){
                    throw new ActionServletException("atencao.informar_data_leitura_atual");
                }

                // [FS0005] Validar data de leitura
                if ( ( dtAtual != null && !dtAtual.equals( "" ) ) &&
                     ( dtAnterior == null || dtAtual.equals( "" ) ) ){
                    throw new ActionServletException("atencao.informar_data_leitura_anterior");
                }
                
                // [FS0005] Validar data de leitura
                if ( Util.compararData( Util.converteStringParaDate( dtAnterior ),
                                        Util.converteStringParaDate( dtAtual ) ) == 1 ){
                    throw new ActionServletException("atencao.data.anterior.atual");
                }
                
                AlterarDatasLeiturasHelper obj = 
                    ( AlterarDatasLeiturasHelper ) arrayHelper[i];
                
                obj.setDtLeituraAnteriorNova( dtAnterior );
                obj.setDtLeituraAtualNova( dtAtual ); 
                
                colHelperParaAlteracao.add( obj );
            }
        }
        
        Fachada fachada = Fachada.getInstancia();
        
        fachada.alterarDatasLeituras( colHelperParaAlteracao, Integer.parseInt( form.getIdGrupo() ) );        
    }
}

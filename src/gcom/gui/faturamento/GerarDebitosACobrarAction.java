package gcom.gui.faturamento;

import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class GerarDebitosACobrarAction extends GcomAction {
    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	// Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("telaPrincipal");

        // Obt�m a inst�ncia da fachada
        //Fachada fachada = Fachada.getInstancia();
        
        Collection rotas = new ArrayList();
        
        Rota rota = new Rota();
        rota.setId(3);
        
        rotas.add(rota);
        
        /*Rota rota1 = new Rota();
        
        rota1.setId(3);
        
        rotas.add(rota1); */
        
       // Integer anoMesCorrente = 200601;
        
       // Integer indicadorGeracaoMulta = 1;
        
       // Integer indicadorGeracaoJuros = 2;
        
      //  Integer indicadorGeracaoAtualizacao = 2;
        
        //Integer qtdParcelamentos = fachada.desfazerParcelamentosPorEntradaNaoPaga();
        
       /* try {
			Collection gerarDados = fachada.gerarDebitosACobrarDeAcrescimosPorImpontualidade(rotas,
					indicadorGeracaoMulta, indicadorGeracaoJuros, indicadorGeracaoAtualizacao);
        } catch (ControladorException e) {
			
			e.printStackTrace();
		}
        */
        return retorno;
    }
}

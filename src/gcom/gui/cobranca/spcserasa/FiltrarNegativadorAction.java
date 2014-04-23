package gcom.gui.cobranca.spcserasa;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.filtro.ParametroSimples;

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
public class FiltrarNegativadorAction extends GcomAction {

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
		ActionForward retorno = actionMapping.findForward("exibirManterNegativador");
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o form do cliente
        FiltrarNegativadorActionForm form = (FiltrarNegativadorActionForm) actionForm; 
        
        short codigoAgente = 0;
        Integer clienteId = null;
        Integer imovelId= null; 
        String inscricaoEstadual = null;
        short ativo = 0; 
        String checkAtualizar = "";
        
        if (!form.getCodigoAgente().equals("") && form.getCodigoAgente() != null){
        	codigoAgente = Short.parseShort(form.getCodigoAgente());
        }
        if (!form.getCodigoCliente().equals("") && form.getCodigoCliente() != null){
        	clienteId = new Integer(form.getCodigoCliente());
        }
        if (!form.getCodigoImovel().equals("") && form.getCodigoImovel() != null){
        	imovelId = new Integer(form.getCodigoImovel());
        }
        if (!form.getInscricaoEstadual().equals("") && form.getInscricaoEstadual() != null){
        	inscricaoEstadual = form.getInscricaoEstadual();
        }
        if (!form.getAtivo().equals("") && form.getAtivo() != null){
        	ativo = Short.parseShort(form.getAtivo());
        }
       	
        if (!form.getCheckAtualizar().equals("") && form.getCheckAtualizar() != null){
        	checkAtualizar = form.getCheckAtualizar();
        }
        
		FiltroNegativador filtroNegativador = new FiltroNegativador();
		boolean peloMenosUmParametroInformado = false; 
		
		if (!form.getCodigoAgente().equals("") && form.getCodigoAgente() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.CODIGO_AGENTE, codigoAgente));
			peloMenosUmParametroInformado = true;
		}
		
		if (!form.getCodigoCliente().equals("") && form.getCodigoCliente() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_CLIENTE, clienteId));
			peloMenosUmParametroInformado = true;
		}
		
		if (!form.getCodigoImovel().equals("") && form.getCodigoImovel() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_IMOVEL, imovelId));
			peloMenosUmParametroInformado = true;
		}
		
		if (!form.getInscricaoEstadual().equals("") && form.getInscricaoEstadual() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.INSCRICAO_ESTADUAL, inscricaoEstadual));
			peloMenosUmParametroInformado = true;
		}
		
		if (!form.getAtivo().equals("") && form.getAtivo() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.INDICADOR_USO, ativo));
			peloMenosUmParametroInformado = true;
		}

		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		
		sessao.setAttribute("filtroNegativador",filtroNegativador);
		sessao.setAttribute("checkAtualizar",checkAtualizar);
		
		return retorno;
        
    }
}

package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa a página para inserir um Negativador
 * 
 * @author Thiago Vieira
 */
public class ExibirInserirNegativadorAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("inserirNegativador");
        InserirNegativadorActionForm form = (InserirNegativadorActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        String idDigitadoEnterCliente = (String) form.getCodigoCliente();
        String idDigitadoEnterImovel = (String) form.getCodigoImovel();
        String idDigitadoEnterAgente = (String) form.getCodigoAgente();
        
        form.setOkAgente("false");
        form.setOkCliente("false");
        form.setOkImovel("false");
        
        // verifica se o codigo do cliente foi digitado
        if (idDigitadoEnterCliente != null
                && !idDigitadoEnterCliente.trim().equals("")
                && Integer.parseInt(idDigitadoEnterCliente) > 0) {
            
        	FiltroCliente filtroCliente = new FiltroCliente();
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List) clienteEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",
							null, ""
									+ ((Cliente) ((List) clienteEncontrado)
											.get(0)).getId());
				}
			  //-------------------------------------------------------------------
				
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clienteEncontrado);
				
				FiltroNegativador filtroNegativador = new FiltroNegativador();
				
				filtroNegativador.adicionarParametro(new ParametroSimples(
						FiltroNegativador.NEGATIVADOR_CLIENTE, cliente.getId()));				

				Collection collNegativador = fachada.pesquisar(filtroNegativador,
						Negativador.class.getName());
								
		
				
				if(collNegativador != null && !collNegativador.isEmpty()){
					
					throw new ActionServletException("atencao.negativador_associado_cliente");
					
					
				}
				
                //---------------------------------------------------------------------
				form.setCodigoCliente(((Cliente) ((List) clienteEncontrado)
								.get(0)).getId().toString());
				form.setNomeCliente(((Cliente) ((List) clienteEncontrado)
								.get(0)).getNome());
				form.setOkCliente("true");
			} else {
				httpServletRequest.setAttribute("corCliente","exception");
               	form.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
               	form.setCodigoCliente("");
               	form.setOkCliente("false");
			}
        }
        
        if (idDigitadoEnterImovel != null && !idDigitadoEnterImovel.trim().equals("")) {
			// Pesquisa o imovel na base
        	
        	FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarParametro(new ParametroSimples(
        			FiltroImovel.ID, idDigitadoEnterImovel));
        	
        	Collection collImovel = fachada.pesquisar(filtroImovel,
        			Imovel.class.getName());
        	
        	Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(collImovel);
        	
			if (imovel != null) {
				//-------------------------------------------------------------------
							
				FiltroNegativador filtroNegativador = new FiltroNegativador();
				
				filtroNegativador.adicionarParametro(new ParametroSimples(
						FiltroNegativador.NEGATIVADOR_IMOVEL, imovel.getId()));				

				Collection collNegativador = fachada.pesquisar(filtroNegativador,
						Negativador.class.getName());
							
				if(collNegativador != null && !collNegativador.isEmpty()){
					
					throw new ActionServletException("atencao.negativador_associado_imovel");
					
					
				}
				
                //---------------------------------------------------------------------
					
				String imovelEncontrado = fachada.pesquisarInscricaoImovel(new Integer(idDigitadoEnterImovel));
				
				// O imovel foi encontrado
				form.setCodigoImovel(idDigitadoEnterImovel);
				form.setInscricaoImovel(imovelEncontrado);
				form.setOkImovel("true");
			} else {
				httpServletRequest.setAttribute("corImovel","exception");
           		form.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
           		form.setCodigoImovel("");
           		form.setOkImovel("false");
			}
        }
        
        if (idDigitadoEnterAgente != null && !idDigitadoEnterAgente.trim().equals("")){
        	FiltroNegativador fNegativador = new FiltroNegativador();
        	fNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.CODIGO_AGENTE, idDigitadoEnterAgente));
        	
        	Collection codigoAgenteEncontrado = Fachada.getInstancia().pesquisar(fNegativador, Negativador.class.getName());
        	        	
        	if (codigoAgenteEncontrado != null && !codigoAgenteEncontrado.isEmpty()) {
        		
        		if (((Negativador) ((List) codigoAgenteEncontrado).get(0)).getIndicadorUso() 
        				== ConstantesSistema.INDICADOR_USO_DESATIVO.shortValue()) {
					throw new ActionServletException("atencao.negativador.inativo",
							null, ""
									+ ((Negativador) ((List) codigoAgenteEncontrado)
											.get(0)).getId());
				}
        		form.setCodigoAgente("");
        		httpServletRequest.setAttribute("corAgente","exception");
           		form.setMensagemAgente(ConstantesSistema.CODIGO_AGENTE_JA_CADASTRADO);
           		form.setOkAgente("false");
        	} else {
        		form.setOkAgente("true");
        	}
        }
        return retorno;
    }
}

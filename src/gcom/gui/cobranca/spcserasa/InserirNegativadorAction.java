package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class InserirNegativadorAction extends GcomAction {

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
  	
    	//localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();

		// Pega o form do cliente
        InserirNegativadorActionForm form = (InserirNegativadorActionForm) actionForm; 

        short codigoAgente = Short.parseShort(form.getCodigoAgente());
        Integer clienteId = new Integer(form.getCodigoCliente());
        
        Integer imovelId = null;
        Imovel imovel = new Imovel();
        
        if(!"".equals(form.getCodigoImovel())){
        	imovelId = new Integer(form.getCodigoImovel());    		
    		imovel.setId(imovelId);
    		
        }
       
       	String inscricaoEstadual = form.getInscricaoEstadual();
       	
		Cliente cliente = new Cliente(); 
		cliente.setId(clienteId);

		
		
		//......................................................................
		FiltroNegativador filtroNegativador = new FiltroNegativador();

		filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_CLIENTE, clienteId));		
		
		Collection collNegativador= fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
		
		if(collNegativador != null && !collNegativador.isEmpty()){		
			throw new ActionServletException("atencao.negativador_associado_cliente");
			
		}		
		
		//......................................................................
		if(imovelId != null){
			
			filtroNegativador = new FiltroNegativador();

			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_IMOVEL, imovelId));
			
			Collection collNegativadorImovel= fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
			
			if(collNegativadorImovel != null && !collNegativadorImovel.isEmpty()){		
				throw new ActionServletException("atencao.negativador_associado_imovel");
				
			}
			
			
		}
				
		//......................................................................
		
		filtroNegativador = new FiltroNegativador();

		filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.CODIGO_AGENTE, codigoAgente));
		
		Collection collNegativadorAgente =  fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
		
		if(collNegativadorAgente != null && !collNegativadorAgente.isEmpty()){		
			throw new ActionServletException("atencao.negativador_associado_agente");
			
		}		
		//......................................................................
				
		//Criando objeto Negativador a ser incluido
		Negativador negativador = new Negativador();
		negativador.setCliente(cliente);
		negativador.setCodigoAgente(codigoAgente);

		if(imovelId != null){
			negativador.setImovel(imovel);
		}
		
		
		negativador.setNumeroInscricaoEstadual(inscricaoEstadual);
		negativador.setUltimaAlteracao(new Date());
		negativador.setIndicadorUso(new Integer(1).shortValue());
		
	// Insere o Negativador
		
		Integer codigoNegativador = -1;
		if (negativador != null) {			
			
			codigoNegativador = (Integer) Fachada.getInstancia().inserir(negativador);
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}
		
		
		Operacao operacao = new Operacao();


		operacao.setId(Operacao.OPERACAO_INSERIR_NEGATIVADOR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

        montarPaginaSucesso(httpServletRequest, "Negativador "
				+ codigoNegativador + " inserido com sucesso.",
				"Inserir outro Negativador",
				"exibirInserirNegativadorAction.do?menu=sim",
				"","");
        
		return retorno;
        
    }
}

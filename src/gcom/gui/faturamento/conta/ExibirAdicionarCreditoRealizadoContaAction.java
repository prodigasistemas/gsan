package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoOrigem;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirAdicionarCreditoRealizadoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAdicionarCreditoRealizadoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);

        Fachada fachada = Fachada.getInstancia();
        
        //Instância do formulário que está sendo utilizado
        AdicionarCreditoRealizadoContaActionForm adicionarCreditoRealizadoContaActionForm = 
        (AdicionarCreditoRealizadoContaActionForm) actionForm;
        
        /*
         * Costante que informa o ano limite para o campo anoMesReferencia da conta
         */
        httpServletRequest.setAttribute("anoLimite", ConstantesSistema.ANO_LIMITE);
        
        //Recebimento da matricula do imóvel selecionado
        String idImovel = (String) httpServletRequest.getParameter("imovel");
        
        if (idImovel == null || idImovel.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.adicionar_debito_imovel_obrigatorio");
        }
        else{
        	//O id do imóvel será utilizado no action de inserção dos créditos realizados.
        	adicionarCreditoRealizadoContaActionForm.setImovelID(idImovel);
        }
        
        //Carregar categorias
        if (sessao.getAttribute("colecaoAdicionarCreditoTipo") == null){
        
        	FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo(FiltroCreditoTipo.DESCRICAO_ABREVIADA);
        	
        	filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	Collection colecaoAdicionarCreditoTipo = fachada.pesquisar(filtroCreditoTipo,
        		CreditoTipo.class.getName());
        
        	if (colecaoAdicionarCreditoTipo == null || colecaoAdicionarCreditoTipo.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "CREDITO_TIPO");
        	}
        
        	// Disponibiliza a coleção pela sessão
        	sessao.setAttribute("colecaoAdicionarCreditoTipo", colecaoAdicionarCreditoTipo);
        
        }
        
        if (sessao.getAttribute("colecaoAdicionarCreditoOrigem") == null){
        	
        	FiltroCreditoOrigem filtroCreditoOrigem = new FiltroCreditoOrigem(FiltroCreditoOrigem.DESCRICAO_ABREVIADA);
        	filtroCreditoOrigem.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCreditoOrigem.ID, CreditoOrigem.BOLSA_AGUA));
        	
        	filtroCreditoOrigem.adicionarParametro(new ParametroSimples(FiltroCreditoOrigem.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	Collection colecaoAdicionarCreditoOrigem = fachada.pesquisar(filtroCreditoOrigem,
        		CreditoOrigem.class.getName());
        
        	if (colecaoAdicionarCreditoOrigem == null || colecaoAdicionarCreditoOrigem.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "CREDITO_ORIGEM");
        	}
        
        	// Disponibiliza a coleção pela sessão
        	sessao.setAttribute("colecaoAdicionarCreditoOrigem", colecaoAdicionarCreditoOrigem);
        	
        }
        
        return retorno;
    }

}


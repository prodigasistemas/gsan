package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FinanciamentoTipo;

import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirAdicionarDebitoCobradoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAdicionarDebitoCobradoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);

        Fachada fachada = Fachada.getInstancia();
        
        //Instância do formulário que está sendo utilizado
        AdicionarDebitoCobradoContaActionForm adicionarDebitoCobradoContaActionForm = 
        (AdicionarDebitoCobradoContaActionForm) actionForm;
        
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
        	//O id do imóvel será utilizado no action de inserção dos débitos cobrados.
        	adicionarDebitoCobradoContaActionForm.setImovelID(idImovel);
        }
        
        //Carregar tipo do débito
        if (sessao.getAttribute("colecaoAdicionarDebitoTipo") == null){
        
        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo(FiltroDebitoTipo.DESCRICAO_ABREVIADA);
        	filtroDebitoTipo.setConsultaSemLimites(true);
        	
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.FINANCIAMENTO_TIPO, FinanciamentoTipo.SERVICO_NORMAL));
        	
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
        	Collection colecaoAdicionarDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
        		DebitoTipo.class.getName());
        
        	if (colecaoAdicionarDebitoTipo == null || colecaoAdicionarDebitoTipo.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "DEBITO_TIPO");
        	}
        
        	// Disponibiliza a coleção pela sessão
        	sessao.setAttribute("colecaoAdicionarDebitoTipo", colecaoAdicionarDebitoTipo);
        
        }
        
        if (adicionarDebitoCobradoContaActionForm.getDebitoTipoID() != null && !adicionarDebitoCobradoContaActionForm.getDebitoTipoID().trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, adicionarDebitoCobradoContaActionForm.getDebitoTipoID()));
        	
        	Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
        	
        	DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
        	
        	adicionarDebitoCobradoContaActionForm.setValorDebito(Util.formatarMoedaReal(debitoTipo.getValorSugerido()));
        } else {
        	adicionarDebitoCobradoContaActionForm.setValorDebito("");
        }
        
        boolean alterarValorSugeridoEmTipoDebito = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO,
						this.getUsuarioLogado(httpServletRequest));

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
				alterarValorSugeridoEmTipoDebito);
        
		if (adicionarDebitoCobradoContaActionForm.getValorDebito() == null
				|| adicionarDebitoCobradoContaActionForm.getValorDebito()
						.equals("")) {

			httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
					true);

		}
		
        return retorno;
    }

}

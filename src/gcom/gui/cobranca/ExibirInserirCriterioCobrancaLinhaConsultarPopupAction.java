package gcom.gui.cobranca;

import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaCriterioLinha;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.FiltroCobrancaCriterioLinha;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Conbrança - Vosualizar Criterio de Cobranca Linha
 * @author Rafael Santos
 * @since 02/03/2006
 */
public class ExibirInserirCriterioCobrancaLinhaConsultarPopupAction  extends GcomAction{
	
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirCriterioCobrancaLinhaConsultarPopupAction");

        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);
        
        InserirCriterioCobrancaLinhaConsultarPopupActionForm inserirCriterioCobrancaLinhaConsultarPopupActionForm = (InserirCriterioCobrancaLinhaConsultarPopupActionForm) actionForm;
        
        String idCriterioCobranca = httpServletRequest.getParameter("idCriterioCobranca");
        Collection colecaoCobrancaCriterioLinha = null;
        //pesquisar criterio cobranca
        if(idCriterioCobranca != null && !idCriterioCobranca.equals("")){
        	FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID,idCriterioCobranca));
        	
        	Collection colecaoCobrancaCriterio = fachada.pesquisar(filtroCobrancaCriterio,CobrancaCriterio.class.getName());

        	if(colecaoCobrancaCriterio != null && !colecaoCobrancaCriterio.isEmpty()){
        		
        		CobrancaCriterio cobrancaCriterio = (CobrancaCriterio)colecaoCobrancaCriterio.iterator().next();
        		inserirCriterioCobrancaLinhaConsultarPopupActionForm.setDescricaoCobrancaCriterio(cobrancaCriterio.getDescricaoCobrancaCriterio());
        	}
        
        	FiltroCobrancaCriterioLinha filtroCobrancaCriterioLinha = new FiltroCobrancaCriterioLinha();
        	filtroCobrancaCriterioLinha.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterioLinha.COBRANCA_CRITERIO_ID,idCriterioCobranca));
        	filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaCriterioLinha.CATEGORIA);
        	filtroCobrancaCriterioLinha.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaCriterioLinha.IMOVEL_PERFIL);
        	filtroCobrancaCriterioLinha.setCampoOrderBy(FiltroCobrancaCriterioLinha.ID_IMOVEL_PERFIL,FiltroCobrancaCriterioLinha.ID_CATEGORIA);
        	
        	colecaoCobrancaCriterioLinha = fachada.pesquisar(filtroCobrancaCriterioLinha,CobrancaCriterioLinha.class.getName());
        	
        	if(colecaoCobrancaCriterioLinha == null || colecaoCobrancaCriterioLinha.isEmpty()){
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
						null, "Tabela Cobrança Critério Linha");
        	}
        	
        }
        
       sessao.setAttribute("colecaoCobrancaCriterioLinha",colecaoCobrancaCriterioLinha);
        
        
        return retorno;
    }

}

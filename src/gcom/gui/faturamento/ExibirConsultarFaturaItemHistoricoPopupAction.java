package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItemHistorico;
import gcom.faturamento.conta.FiltroFaturaItemHistorico;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC0871]
 * 
 * @author Flávio Leonardo
 * @date 21/01/2009
 */
public class ExibirConsultarFaturaItemHistoricoPopupAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        // Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("exibirConsultarFaturaItemHistoricoPopup");

        // Instacia a fachada
        Fachada fachada = Fachada.getInstancia();

        // Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarFaturaClienteResponsavelActionForm form = (FiltrarFaturaClienteResponsavelActionForm) actionForm;

        FiltroFaturaItemHistorico filtroFaturaItemHistorico = new FiltroFaturaItemHistorico();
        
        filtroFaturaItemHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");
        filtroFaturaItemHistorico.adicionarCaminhoParaCarregamentoEntidade("contaGeral.conta");
        filtroFaturaItemHistorico.adicionarCaminhoParaCarregamentoEntidade("contaGeral.contaHistorico");
        
        filtroFaturaItemHistorico.adicionarParametro(new ParametroSimples(FiltroFaturaItemHistorico.ANO_MES_REFERENCIA,
        		Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno())));
        
        Fatura fatura = (Fatura)sessao.getAttribute("fatura");
        
        filtroFaturaItemHistorico.adicionarParametro(new ParametroSimples(FiltroFaturaItemHistorico.FATURA_ID,
        		fatura.getId()));
        
        Collection colecaoFaturaItemHistorico = fachada.pesquisar(filtroFaturaItemHistorico, FaturaItemHistorico.class.getName());
        
        sessao.setAttribute("colecaoFaturaItemHistorico", colecaoFaturaItemHistorico);
        
        return retorno;
    }
}

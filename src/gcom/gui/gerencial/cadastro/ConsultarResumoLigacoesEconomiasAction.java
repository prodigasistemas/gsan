package gcom.gui.gerencial.cadastro;

import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarResumoLigacoesEconomiasAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("consultarResumoLigacoesEconomias");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");
        
        List retornoConsulta = fachada.consultarResumoLigacoesEconomias(informarDadosGeracaoRelatorioConsultaHelper);

        int opcaoTotalizacao = informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao().intValue();
        
        if(opcaoTotalizacao == ConstantesSistema.CODIGO_ESTADO ||
        		opcaoTotalizacao == ConstantesSistema.CODIGO_GERENCIA_REGIONAL ||
        		opcaoTotalizacao == ConstantesSistema.CODIGO_ELO_POLO ||
        		opcaoTotalizacao == ConstantesSistema.CODIGO_LOCALIDADE ||
        		opcaoTotalizacao == ConstantesSistema.CODIGO_SETOR_COMERCIAL ||
        		opcaoTotalizacao == ConstantesSistema.CODIGO_QUADRA){
        	retorno = actionMapping.findForward("consultarResumoLigacoesEconomias");
        }else{
        	retorno = actionMapping.findForward("consultarResumoLigacoesEconomias");
        }
        
        sessao.setAttribute("informarDadosGeracaoRelatorioConsultaHelper", informarDadosGeracaoRelatorioConsultaHelper);
        sessao.setAttribute("colecaoResumoLigacoesEconomias", retornoConsulta);
        //sessao.setAttribute("colecaoResumoAnormalidade", retornoConsulta);
        //devolve o mapeamento de retorno
        return retorno;
    }
}

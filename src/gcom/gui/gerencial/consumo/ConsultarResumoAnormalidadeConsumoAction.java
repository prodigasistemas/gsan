package gcom.gui.gerencial.consumo;

import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gui.GcomAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarResumoAnormalidadeConsumoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("consultarResumoAnormalidadeConsumo");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");
        
        List retornoConsulta = fachada.consultarResumoAnormalidadeConsumo(informarDadosGeracaoRelatorioConsultaHelper);

        sessao.setAttribute("informarDadosGeracaoRelatorioConsultaHelper", informarDadosGeracaoRelatorioConsultaHelper);
        sessao.setAttribute("colecaoResumoAnormalidadeConsumo", retornoConsulta);
        //devolve o mapeamento de retorno
        return retorno;
    }

}

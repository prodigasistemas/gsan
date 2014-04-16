package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0001] INSERIR FERIADO MUNICIPIO
 * 
 * @author Kassia Albuquerque
 * @created 20/12/2006
 */


public class InserirFeriadoMunicipioPopupAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirFeriadoMunicipioPopup");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        InserirFeriadoMunicipioPopupActionForm inserirFeriadoMunicipioPopupActionForm = (InserirFeriadoMunicipioPopupActionForm) actionForm;

        Collection colecaoFeriadoMunicipio = null;
        
        if (sessao.getAttribute("colecaoFeriadoMunicipio") != null) {
        	colecaoFeriadoMunicipio = (Collection) sessao.getAttribute("colecaoFeriadoMunicipio");
        } else {
        	colecaoFeriadoMunicipio = new ArrayList();
        }
        
        String dataFeriado = inserirFeriadoMunicipioPopupActionForm.getData();
        String descricao = inserirFeriadoMunicipioPopupActionForm.getDescricao();
        
        Date dataFormatada = Util.converteStringParaDate(dataFeriado);
        
        MunicipioFeriado municipioFeriado = new MunicipioFeriado();
        
        municipioFeriado.setDataFeriado(dataFormatada);
        municipioFeriado.setDescricaoFeriado(descricao);
        
        colecaoFeriadoMunicipio.add(municipioFeriado);
        
        sessao.setAttribute("retornarTela", "exibirInserirMuncipioAction.do");
        
        httpServletRequest.setAttribute("fecharPopup", "S");
        
        return retorno;
    }

}

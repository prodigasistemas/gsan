package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarResumoContaLocalidadeAction extends GcomAction {

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("gerarResumoContaLocalidade");

        FiltrarMapaControleContaRelatorioActionForm filtrarMapaControleContaRelatorioActionForm = (FiltrarMapaControleContaRelatorioActionForm) actionForm;
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
       // Fachada fachada = Fachada.getInstancia();
        //Variaveis
        String idFaturamentoGrupo = (String) filtrarMapaControleContaRelatorioActionForm
                .getIdGrupoFaturamento();
        
        String firma = (String) filtrarMapaControleContaRelatorioActionForm.getFirma();
        
        String tipoImpressao = filtrarMapaControleContaRelatorioActionForm.getTipoImpressao();
        
        Integer idFirma = null;
        
        sessao.removeAttribute("indicadorAtualizar");
        
        String mesAno = (String)filtrarMapaControleContaRelatorioActionForm.getMesAno();
        
        boolean parametroInformado = false;
        String anoMes = "";
        
        if (idFaturamentoGrupo != null
                && !idFaturamentoGrupo.trim().equalsIgnoreCase("")
                && !idFaturamentoGrupo.trim().equalsIgnoreCase("-1")
                && mesAno != null && !mesAno.trim().equals("")) {
        	String mes = mesAno.substring(0, 2);
        	String ano = mesAno.substring(3, 7);
        	anoMes = ano+mes;
            parametroInformado = true;
        }
        
        if (firma != null && !firma.equals("") && !firma.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
        	idFirma = new Integer(firma);
        }
        
        if(parametroInformado){
        	//chamar metodo de filtragem
        	Collection colecao = fachada.filtrarResumoContasLocalidade(new Integer(idFaturamentoGrupo), anoMes, idFirma, tipoImpressao);
        	sessao.setAttribute("colecaoResumoContaLocalidadeConta",colecao);
        }else{
        	throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
        }
        
        
        return retorno;
    }

}

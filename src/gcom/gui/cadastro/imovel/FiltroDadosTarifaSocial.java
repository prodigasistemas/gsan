package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltroDadosTarifaSocial extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping
        .findForward("retornarFiltroDadosTarifaSocial");
		
		 //obtendo uma instancia da sessao
        //HttpSession sessao = httpServletRequest.getSession(false);
        //DynaValidatorActionForm filtrarImovelActionForm = (DynaValidatorActionForm) actionForm;
        
        /*String tarifaSocialExclusaoMotivoId= (String) filtrarImovelActionForm
        .get("tarifaSocialExclusaoMotivo");
        String tarifaSocialTipoCartaoId= (String) filtrarImovelActionForm
        .get("tarifaSocialExclusaoMotivo");
        String tarifaSocialRendaTipoId= (String) filtrarImovelActionForm
        .get("tarifaSocialExclusaoMotivo");
        String periodoValidadeCartaoInicial= (String) filtrarImovelActionForm
        .get("periodoValidadeCartaoInicial");
        String periodoValidadeCartaoFinal= (String) filtrarImovelActionForm
        .get("periodoValidadeCartaoFinal");
        String periodoRecadastramentoInicial= (String) filtrarImovelActionForm
        .get("periodoRecadastramentoInicial");
        String periodoRecadastramentoFinal= (String) filtrarImovelActionForm
        .get("periodoRecadastramentoFinal");
        String rendaFamiliar= (String) filtrarImovelActionForm
        .get("rendaFamiliar");
        String numeroMesesAdesao= (String) filtrarImovelActionForm
        .get("numeroMesesAdesao");
        String numeroRecadastramentoInicial= (String) filtrarImovelActionForm
        .get("numeroRecadastramentoInicial");
        String numeroRecadastramentoFinal= (String) filtrarImovelActionForm
        .get("numeroRecadastramentoFinal");
        String consumoCelpe= (String) filtrarImovelActionForm
        .get("consumoCelpe");
        String implantacaoInicial= (String) filtrarImovelActionForm
        .get("implantacaoInicial");
        String implantacaoFinal= (String) filtrarImovelActionForm
        .get("implantacaoFinal");
        String periodoExclusaoInicial= (String) filtrarImovelActionForm
        .get("periodoExclusaoInicial");
        String periodoExclusaoFinal= (String) filtrarImovelActionForm
        .get("periodoExclusaoFinal");*/
        
        
        
        return retorno;
	}
}
       
       

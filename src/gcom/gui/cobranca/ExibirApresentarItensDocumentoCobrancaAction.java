package gcom.gui.cobranca;

import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.bean.CobrancaDocumentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.util.Util;

/**
 * 
 * 
 * @author  Raphael Rossiter
 * @created 05/04/2006
 */
public class ExibirApresentarItensDocumentoCobrancaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	ActionForward retorno = actionMapping.findForward("exibirApresentarItensDocumentoCobranca");
    	
    	Fachada fachada = Fachada.getInstancia();
    	
    	ApresentarItensDocumentoCobrancaActionForm apresentarItensDocumentoCobrancaActionForm = 
        (ApresentarItensDocumentoCobrancaActionForm) actionForm;
    	
    	String cobrancaDocumentoID = httpServletRequest.getParameter("cobrancaDocumentosID");
    	
    	CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
    	cobrancaDocumento.setId(new Integer(cobrancaDocumentoID));
    	
    	CobrancaDocumentoHelper cobrancaDocumentoHelper = fachada.apresentaItensDocumentoCobranca(cobrancaDocumento);
    	
    	
    	apresentarItensDocumentoCobrancaActionForm.setMatriculaImovel(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getId().toString());
    	apresentarItensDocumentoCobrancaActionForm.setInscricaoImovel(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getInscricaoFormatada());
    	apresentarItensDocumentoCobrancaActionForm.setSituacaoAguaImovel(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getLigacaoAguaSituacao().getDescricao());
    	apresentarItensDocumentoCobrancaActionForm.setSituacaoEsgotoImovel(cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getLigacaoEsgotoSituacao().getDescricao());
    	apresentarItensDocumentoCobrancaActionForm.setSequencial(String.valueOf(cobrancaDocumentoHelper.getCobrancaDocumento().getNumeroSequenciaDocumento()));
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getValorDocumento() != null){
    		apresentarItensDocumentoCobrancaActionForm.setValorDocumento(Util.formatarMoedaReal(cobrancaDocumentoHelper.getCobrancaDocumento().getValorDocumento()));
    	}
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getValorDesconto() != null){
    		apresentarItensDocumentoCobrancaActionForm.setValorDesconto(Util.formatarMoedaReal(cobrancaDocumentoHelper.getCobrancaDocumento().getValorDesconto()));
    	}
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getDocumentoEmissaoForma() != null){
    		apresentarItensDocumentoCobrancaActionForm.setFormaEmissao(cobrancaDocumentoHelper.getCobrancaDocumento().getDocumentoEmissaoForma().getDescricaoDocumentoEmissaoForma());
    	}
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getEmissao() != null){
    		apresentarItensDocumentoCobrancaActionForm.setDataHoraEmissao(Util.formatarDataComHora(cobrancaDocumentoHelper.getCobrancaDocumento().getEmissao()));
    	} 
    	
    	if (cobrancaDocumentoHelper.getCobrancaDocumento().getMotivoNaoEntregaDocumento() != null){
    		apresentarItensDocumentoCobrancaActionForm.setMotivoNaoEntregaDocumento(cobrancaDocumentoHelper.getCobrancaDocumento().getMotivoNaoEntregaDocumento().getMotivoNaoeEntregaDocumento());
    	}
    	
    	apresentarItensDocumentoCobrancaActionForm.setQtdItens(cobrancaDocumentoHelper.getQuantidadeItensCobrancaDocumento().toString());
    	
    	
    	httpServletRequest.setAttribute("imovel", cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getId().toString());
    	httpServletRequest.setAttribute("cobrancaDocumentoHelper", cobrancaDocumentoHelper);
    	
        return retorno;
        
    }
}

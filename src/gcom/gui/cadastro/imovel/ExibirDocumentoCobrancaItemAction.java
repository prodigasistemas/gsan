package gcom.gui.cadastro.imovel;

import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.FiltroCobrancaDocumento;
import gcom.cobranca.bean.CobrancaDocumentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * Visualiza em PopUp dos dados do Documento de Cobranca 
 * [UC0472] Consultar Imovel 
 * Aba 8° - Documentos de Cobrança
 * 
 * @author  Rafael Santos
 * @created 19/09/2006
 */
public class ExibirDocumentoCobrancaItemAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	ActionForward retorno = actionMapping.findForward("exibirDocumentoCobrancaItem");
    	
    	Fachada fachada = Fachada.getInstancia();
    	
    	DocumentoCobrancaItemActionForm documentoCobrancaItemActionForm = (DocumentoCobrancaItemActionForm) actionForm;
    	
    	String cobrancaDocumentoID = httpServletRequest.getParameter("cobrancaDocumentosID");
    	
    	CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
    	cobrancaDocumento.setId(new Integer(cobrancaDocumentoID));
    	
    	CobrancaDocumentoHelper cobrancaDocumentoHelper = fachada.apresentaItensDocumentoCobranca(cobrancaDocumento);
    	
    	if(httpServletRequest.getParameter("numeroOS") != null && !httpServletRequest.getParameter("numeroOS").equals("")){
    		cobrancaDocumentoHelper.setIdOrdemServico(new Integer((String) httpServletRequest.getParameter("numeroOS")));
    	}
    	
    	cobrancaDocumento = cobrancaDocumentoHelper.getCobrancaDocumento();
    	
    	documentoCobrancaItemActionForm.setMatriculaImovel(cobrancaDocumento.getImovel().getId().toString());
    	documentoCobrancaItemActionForm.setInscricaoImovel(cobrancaDocumento.getImovel().getInscricaoFormatada());
    	documentoCobrancaItemActionForm.setSituacaoAguaImovel(cobrancaDocumento.getImovel().getLigacaoAguaSituacao().getDescricao());
    	documentoCobrancaItemActionForm.setSituacaoEsgotoImovel(cobrancaDocumento.getImovel().getLigacaoEsgotoSituacao().getDescricao());
    	documentoCobrancaItemActionForm.setSequencial(String.valueOf(cobrancaDocumento.getNumeroSequenciaDocumento()));
    	
    	if (cobrancaDocumento.getValorDocumento() != null){
    		documentoCobrancaItemActionForm.setValorDocumento(Util.formatarMoedaReal(cobrancaDocumento.getValorDocumento()));
    	}
    	
    	if (cobrancaDocumento.getValorDesconto() != null){
    		documentoCobrancaItemActionForm.setValorDesconto(Util.formatarMoedaReal(cobrancaDocumentoHelper.getCobrancaDocumento().getValorDesconto()));
    	}
    	
    	if (cobrancaDocumento.getDocumentoEmissaoForma() != null){
    		documentoCobrancaItemActionForm.setFormaEmissao(cobrancaDocumentoHelper.getCobrancaDocumento().getDocumentoEmissaoForma().getDescricaoDocumentoEmissaoForma());
    	}
    	
    	if (cobrancaDocumento.getEmissao() != null){
    		documentoCobrancaItemActionForm.setDataHoraEmissao(Util.formatarDataComHora(cobrancaDocumentoHelper.getCobrancaDocumento().getEmissao()));
    	} 
    	
    	if (cobrancaDocumento.getMotivoNaoEntregaDocumento() != null){
    		documentoCobrancaItemActionForm.setMotivoNaoEntregaDocumento(cobrancaDocumentoHelper.getCobrancaDocumento().getMotivoNaoEntregaDocumento().getMotivoNaoeEntregaDocumento());
    	}
    	
    	if (cobrancaDocumento.getUsuario() != null){
    		documentoCobrancaItemActionForm.setNomeUsuario(cobrancaDocumento.getUsuario().getNomeUsuario());
    	} else {
    		documentoCobrancaItemActionForm.setNomeUsuario("");
    	}
    	
    	if (cobrancaDocumento.getValorAcrescimos() != null){
    		documentoCobrancaItemActionForm.setValorAcrescimo(Util.formatarMoedaReal(cobrancaDocumentoHelper.getCobrancaDocumento().getValorAcrescimos()));
    	}
    	
    	documentoCobrancaItemActionForm.setQtdItens(cobrancaDocumentoHelper.getQuantidadeItensCobrancaDocumento().toString());
    	
    	httpServletRequest.setAttribute("imovel", cobrancaDocumentoHelper.getCobrancaDocumento().getImovel().getId().toString());
    	httpServletRequest.setAttribute("cobrancaDocumentoHelper", cobrancaDocumentoHelper);
    	
        return retorno;
        
    }
}

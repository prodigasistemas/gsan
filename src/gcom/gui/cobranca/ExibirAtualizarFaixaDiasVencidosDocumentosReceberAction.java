package gcom.gui.cobranca;

import java.util.Date;

import gcom.cobranca.DocumentosReceberFaixaDiasVencidos;
import gcom.cobranca.FiltroDocumentosReceberFaixaDiasVencidos;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarFaixaDiasVencidosDocumentosReceberAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarFaixaDiasVencidosDocumentosReceber");
		
		AtualizarFaixaDiasVencidosDocumentosReceberActionForm form = 
				(AtualizarFaixaDiasVencidosDocumentosReceberActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		DocumentosReceberFaixaDiasVencidos documentosReceberFaixaDiasVencidos = 
				(DocumentosReceberFaixaDiasVencidos) sessao.getAttribute("documentosReceberFaixaDiasVencidos");
		
		String idDocumentosReceberFaixaDiasVencidos = (String) httpServletRequest.getParameter("idRegistroAtualizar");
		
		if(documentosReceberFaixaDiasVencidos == null){
				
			FiltroDocumentosReceberFaixaDiasVencidos filtroDocumentosReceberFaixaDiasVencidos = 
					new FiltroDocumentosReceberFaixaDiasVencidos();
			
			filtroDocumentosReceberFaixaDiasVencidos.adicionarParametro(
						new ParametroSimples(FiltroDocumentosReceberFaixaDiasVencidos.ID, 
								new Integer(idDocumentosReceberFaixaDiasVencidos)));
			
			
			documentosReceberFaixaDiasVencidos = (DocumentosReceberFaixaDiasVencidos) 
					fachada.pesquisar(filtroDocumentosReceberFaixaDiasVencidos, 
							DocumentosReceberFaixaDiasVencidos.class.getName()).iterator().next();
		}
		
		form.setDescricaoFaixa(documentosReceberFaixaDiasVencidos.getDescricaoFaixa());
		form.setIndicadorUso(documentosReceberFaixaDiasVencidos.getIndicadorUso());
		form.setValorInicialFaixa(documentosReceberFaixaDiasVencidos.getValorInicialFaixa());
		form.setValorFinalFaixa(documentosReceberFaixaDiasVencidos.getValorFinalFaixa());
		
		Date timeStamp = documentosReceberFaixaDiasVencidos.getUltimaAlteracao();
		
		if(documentosReceberFaixaDiasVencidos.getDescricaoFaixa() != null && !documentosReceberFaixaDiasVencidos.getDescricaoFaixa().equals("")){
			form.setDescricaoFaixa(documentosReceberFaixaDiasVencidos.getDescricaoFaixa());
		}
		
		if(documentosReceberFaixaDiasVencidos.getIndicadorUso() != null && !documentosReceberFaixaDiasVencidos.getIndicadorUso().equals("")){
			form.setIndicadorUso(documentosReceberFaixaDiasVencidos.getIndicadorUso());
		}
		
		if(documentosReceberFaixaDiasVencidos.getValorInicialFaixa() != null && !documentosReceberFaixaDiasVencidos.getValorInicialFaixa().equals("")){
			form.setValorInicialFaixa(documentosReceberFaixaDiasVencidos.getValorInicialFaixa());
		}
		
		if(documentosReceberFaixaDiasVencidos.getValorFinalFaixa() != null && !documentosReceberFaixaDiasVencidos.getValorFinalFaixa().equals("")){
			form.setValorFinalFaixa(documentosReceberFaixaDiasVencidos.getValorFinalFaixa());
		}
		
		sessao.setAttribute("idDocumentosReceberFaixaDiasVencidos", idDocumentosReceberFaixaDiasVencidos);
		sessao.setAttribute("documentosReceberFaixaDiasVencidos", documentosReceberFaixaDiasVencidos);
		sessao.setAttribute("timeStamp", timeStamp);
		sessao.setAttribute("valorFaixaInicial",documentosReceberFaixaDiasVencidos.getValorInicialFaixa());
		sessao.setAttribute("valorFaixaFinal",documentosReceberFaixaDiasVencidos.getValorFinalFaixa());
		sessao.setAttribute("descricaoFaixa",documentosReceberFaixaDiasVencidos.getDescricaoFaixa());
		httpServletRequest.setAttribute("idDocumentosReceberFaixaDiasVencidos", idDocumentosReceberFaixaDiasVencidos);		
		return retorno;
	}
}

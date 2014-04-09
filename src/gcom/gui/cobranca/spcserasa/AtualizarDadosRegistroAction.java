package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorMovimentoReg;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Negativador Registro Tipo
 * 
 * @author Yara Taciane 
 * @created 08/01/2008
 */
public class AtualizarDadosRegistroAction extends GcomAction {
	/**
	 * @author Yara Taciane
	 * @date 08/01/2008
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
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ConsultarDadosRegistroActionForm form = (ConsultarDadosRegistroActionForm) actionForm;

		NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) sessao.getAttribute("negativadorMovimentoReg");
		
		if(negativadorMovimentoReg == null){
			negativadorMovimentoReg = new NegativadorMovimentoReg();
			negativadorMovimentoReg.setId(new Integer(form.getIdNegativadorMovimentoReg()));			
		}
		
		Fachada fachada = Fachada.getInstancia();
		
		// Seta os campos para serem atualizados				
		
		if (!"-1".equals(form.getIndicadorCorrecao())&& form.getIndicadorCorrecao()!= null) {
		
			//Short indicadorCorrecao = Short.parseShort() ;
			Short indicadorCorrecao = new Short(form.getIndicadorCorrecao());
			negativadorMovimentoReg.setIndicadorCorrecao(indicadorCorrecao);
			negativadorMovimentoReg.setUltimaAlteracao(new Date());
			
			//RM3307 - Vivianne Sousa 09/12/2010 - analista:Adriana Ribeiro
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			negativadorMovimentoReg.setUsuarioCorrecao(usuarioLogado);
			
			fachada.atualizar(negativadorMovimentoReg);
			
		}
			
	
		
		// Atualiza o negativadorContrato 
	
		
	
		return retorno;
		
	}
	
    
	
    
   
}
 

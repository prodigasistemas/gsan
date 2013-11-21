package gcom.gui.faturamento;

import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * 
 * [UC0972] Gerar TXT das Contas dos Projetos Especiais
 * 
 * @author Hugo Amorim, Anderson Italo
 * @since 14/12/2009, 29/01/2010
 *
 */
public class ExibirGerarTxtContasProjetosEspeciaisAction extends GcomAction {
	

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		

		ActionForward retorno = actionMapping
				.findForward("exibirGerarTxtContasProjetosEspeciaisAction");

		//Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		GerarTxtContasProjetosEspeciaisForm gerarTxtContasProjetosEspeciaisForm = (GerarTxtContasProjetosEspeciaisForm) actionForm;
		
		if (gerarTxtContasProjetosEspeciaisForm.getIdCliente() != null
				&& !gerarTxtContasProjetosEspeciaisForm.getIdCliente().equals("")){
			
			Cliente cliente = fachada.consultarCliente(new Integer(gerarTxtContasProjetosEspeciaisForm.getIdCliente().trim()));
			if (cliente != null){
				gerarTxtContasProjetosEspeciaisForm.setNomeCliente(cliente.getNome());
			}else{
				httpServletRequest.setAttribute(
	                    "codigoClienteNaoEncontrado", "true");
	            
	            gerarTxtContasProjetosEspeciaisForm.setNomeCliente("CLIENTE INEXISTENTE");
			}
			
		}
		 
		if(httpServletRequest.getParameter("menu")!=null
				&& httpServletRequest.getParameter("menu").equalsIgnoreCase("sim")){
			gerarTxtContasProjetosEspeciaisForm.reset();
		}
		
		return retorno;
	}
}

package gcom.gui.faturamento;
import gcom.fachada.Fachada;
import gcom.faturamento.QualidadeAgua;
import gcom.faturamento.QualidadeAguaPadrao;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Atualizar Qualidade Agua
 *
 * @author Flávio Leonardo
 */


public class AtualizarQualidadeAguaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarQualidadeAguaActionForm form = (AtualizarQualidadeAguaActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		Fachada fachada = Fachada.getInstancia();
		
		QualidadeAgua qualidadeAgua = (QualidadeAgua) sessao.getAttribute("qualidadeAgua");
		
		String[] idQualidadeAgua = new String[1];
		
		idQualidadeAgua[0] = qualidadeAgua.getId().toString();
		
		//Atualiza a entidade com os valores do formulário
		form.setDadosQualidadeAgua(qualidadeAgua);
		
		//quando o valor estiver em branco na tela setar no banco como vazio
		//Flávio Leonardo CRC-1419 data: 17/03/09
		
		QualidadeAguaPadrao qualidadeAguaPadrao = new QualidadeAguaPadrao();
		
		qualidadeAguaPadrao.setId((Integer)sessao.getAttribute("qualidadeAguaPadraoId"));
		
		if (form.getPadraoTurbidez()!= null && !form.getPadraoTurbidez().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoTurbidez(form.getPadraoTurbidez());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoTurbidez("");
		}
		if (form.getPadraoCloroResidual()!= null && !form.getPadraoCloroResidual().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoCloro(form.getPadraoCloroResidual());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoCloro("");
		}
		if (form.getPadraoPH()!= null && !form.getPadraoPH().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoPh(form.getPadraoPH());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoPh("");
		}
		if (form.getPadraoCor()!= null && !form.getPadraoCor().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoCor(form.getPadraoCor());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoCor("");
		}
		if (form.getPadraoFluor()!= null && !form.getPadraoFluor().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoFluor(form.getPadraoFluor());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoFluor("");
		}
		if (form.getPadraoFerro()!= null && !form.getPadraoFerro().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoFerro(form.getPadraoFerro());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoFerro("");
		}
		if (form.getPadraoColiformesTotais()!= null && !form.getPadraoColiformesTotais().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoColiformesTotais(form.getPadraoColiformesTotais());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoColiformesTotais("");
		}
		if (form.getPadraoColiformesFecais()!= null && !form.getPadraoColiformesFecais().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoColiformesFecais(form.getPadraoColiformesFecais());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoColiformesFecais("");
		}
		if (form.getPadraoNitrato()!= null && !form.getPadraoNitrato().equals("")){
			qualidadeAguaPadrao.setDescricaoNitrato(form.getPadraoNitrato());
		}else{
			qualidadeAguaPadrao.setDescricaoNitrato("");
		}
		
		if (form.getPadraoColiformesTermotolerantes()!= null && !form.getPadraoColiformesTermotolerantes().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoColiformesTermotolerantes(form.getPadraoColiformesTermotolerantes());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoColiformesTermotolerantes("");
		}
		
		if (form.getPadraoAlcalinidade() != null && !form.getPadraoAlcalinidade().equals("")){
			
			qualidadeAguaPadrao.setDescricaoPadraoAlcalinidade(form.getPadraoAlcalinidade());
			
		}else{
			
			qualidadeAguaPadrao.setDescricaoPadraoAlcalinidade("");
			
		}
		
		qualidadeAgua.setUltimaAlteracao(new Date());
		qualidadeAguaPadrao.setUltimaAlteracao(new Date());
		//atualiza na base de dados de Qualidade Agua
		fachada.atualizar(qualidadeAgua);
		fachada.atualizar(qualidadeAguaPadrao);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Qualidade de Água de código "+ form.getIdQualidadeAgua()+" atualizado com sucesso.", "Realizar outra Manutenção de Qualidade de Água",
				"exibirFiltrarQualidadeAguaAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    




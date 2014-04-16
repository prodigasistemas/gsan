package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Thiago Silva Toscano de Brito, 
 *  	   Yara Taciane de Souza.
 * @date 22/12/2007
 */
public class ExcluirNegativacaoOnLineAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		ExcluirNegativacaoOnLineActionForm form = (ExcluirNegativacaoOnLineActionForm) actionForm; 
 
		NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) getSessao(httpServletRequest).getAttribute("negativadorMovimentoReg");
		Imovel imovel = (Imovel) getSessao(httpServletRequest).getAttribute("imovel");
		Collection itensConta = (Collection) getSessao(httpServletRequest).getAttribute("itensConta");
		Collection itensGuiaPagamento = (Collection) getSessao(httpServletRequest).getAttribute("itensGuiaPagamento");

		NegativadorExclusaoMotivo negativadorExclusaoMotivo = new NegativadorExclusaoMotivo();
		negativadorExclusaoMotivo.setId(new Integer(form.getMotivoExclusao()));
		
		Date dataExclusao = Util.converteStringParaDate(form.getDataExclusao());

		Usuario usuarioPreenchido = new Usuario();
		usuarioPreenchido.setId(new Integer(form.getIdUsuario()));
		
			
		//..........................................................................................................

		if (!"".equals(form.getDataExclusao()) && form.getDataExclusao() != null) {
			
			String dtExclusao = form.getDataExclusao();
			if (Util.validarDiaMesAno(dtExclusao)) {
				throw new ActionServletException(
						"atencao.data_exclusao_invalida");
			}		
			Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
			
			if(Util.compararData(dataExclusao, dataAtualSemHora) ==  1){
				String dataAtual = Util.formatarData(new Date());
				throw new ActionServletException(
						"atencao.data_exclusao_superior_data_corrente", null,
						dataAtual);
			}			
			
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data de Exclusão");
		}
		
		//..........................................................................................................
		if ((!"".equals(form.getDataExclusao()) && form.getDataExclusao() != null) && (!"".equals(form.getDataEnvio()) && form.getDataEnvio() != null)) {
			
			Date dtExclusao = Util.converteStringParaDate(form.getDataExclusao());
			Date dtEnvio =  negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio();					
			String dataEnvio= Util.formatarData(dtEnvio);
			
			if(Util.compararData(dtExclusao, dtEnvio) == -1){				
				throw new ActionServletException(
						"atencao.data_exclusao_nao_pode_inferior_data_envio", null,dataEnvio);
			}			
			
		} 
		
		//..........................................................................................................
		
		Fachada.getInstancia().excluirNegativacaoOnLine(imovel, negativadorMovimentoReg, itensConta, itensGuiaPagamento, negativadorExclusaoMotivo, dataExclusao, usuarioPreenchido,usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Exclusão do imóvel " + form.getIdImovel() + " efetuada com sucesso.",
				"Excluir outra Negativação","exibirExcluirNegativacaoOnLineAction.do?menu=sim","", "");
		
		

		
		
		
		
		return retorno;
	}
}

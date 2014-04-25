package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.registroatendimento.ConjuntoTramitacaoRaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de encerra inserir
 * dados de pavimento na ordem serviço.
 * 
 * @author Pedro Alexandre
 * @created 19/12/2007
 */
public class ExibirInserirDadosPavimentoOrdemServicoPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("dadosPavimentoOrdemServicoPopup");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		ConjuntoTramitacaoRaActionForm conjuntoTramitacaoRaActionForm = (ConjuntoTramitacaoRaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		//Recupera a ordem de serviço da sessão.
		OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
		String descricaoTipoServico = ordemServico.getServicoTipo().getDescricao();
		
		//Caso o imóvel tenha pavimento de calçada,recupera o valor.
		String pavimentoCalcada = null;
		if(ordemServico.getImovel().getPavimentoCalcada() != null){
			pavimentoCalcada = ordemServico.getImovel().getPavimentoCalcada().getId() + "";
		}

		//Caso o imóvel tenha pavimento de rua,recupera o valor. 
		String pavimentoRua = null;		
		if(ordemServico.getImovel().getPavimentoRua() != null){
			pavimentoRua = ordemServico.getImovel().getPavimentoRua().getId() + "";
		}

		//Seta os dados no form
		conjuntoTramitacaoRaActionForm.setIdOrdemServico(ordemServico.getId() + "");
		conjuntoTramitacaoRaActionForm.setDescricaoTipoServico(descricaoTipoServico);
		conjuntoTramitacaoRaActionForm.setIdPavimentoCalcada(pavimentoCalcada);
		conjuntoTramitacaoRaActionForm.setIdPavimentoRua(pavimentoRua);
		
		//Filtar os registros de pavimentode calçada
		FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
		Collection colecaoPavimentoCalcada = fachada.pesquisar(filtroPavimentoCalcada,PavimentoCalcada.class.getName());
		
		//Filtra os registros de pavimento de rua
		FiltroPavimentoRua  filtroPavimentoRua = new FiltroPavimentoRua();
		Collection colecaoPavimentoRua = fachada.pesquisar(filtroPavimentoRua,PavimentoRua.class.getName());
		
		httpServletRequest.setAttribute("colecaoPavimentoCalcada", colecaoPavimentoCalcada);
		httpServletRequest.setAttribute("colecaoPavimentoRua", colecaoPavimentoRua);
		
		return retorno;
	}
}

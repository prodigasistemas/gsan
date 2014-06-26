package gcom.gui.arrecadacao;

import gcom.arrecadacao.bean.ManterDebitoAutomaticoHelper;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterDebitoAutomaticoAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterDebitoAutomatico");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroDebitoAutomatico filtro = (FiltroDebitoAutomatico) sessao.getAttribute("filtroDebitoAutomatico");
		filtro.adicionarCaminhoParaCarregamentoEntidade( "agencia.banco" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel" );
		
		Collection colecaoDebitoAutomatico = new ArrayList();
		
		if(filtro != null && !filtro.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno, filtro, DebitoAutomatico.class.getName());
			
			colecaoDebitoAutomatico = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		
		Iterator iteratorDebitoAutomatico = colecaoDebitoAutomatico.iterator();
		Collection<ManterDebitoAutomaticoHelper> colecaoHelper = new ArrayList();
		
		while (iteratorDebitoAutomatico.hasNext()) {
			DebitoAutomatico debitoAutomatico = (DebitoAutomatico) iteratorDebitoAutomatico.next();
			ManterDebitoAutomaticoHelper helper = new ManterDebitoAutomaticoHelper();
			
			helper.setIdDebitoAutomatico(debitoAutomatico.getId() + "");
			helper.setSiglaBanco(debitoAutomatico.getAgencia().getBanco().getDescricaoAbreviada());
			helper.setAgencia(debitoAutomatico.getAgencia().getCodigoAgencia());
			
			Cliente cliente = Fachada.getInstancia().pesquisarClienteUsuarioImovel(debitoAutomatico.getImovel().getId());
			helper.setNomeCliente(cliente.getNome());
			helper.setMatriculaImovel(debitoAutomatico.getImovel().getId() + "");
			helper.setMatriculaImovelFormatada(Util.retornaMatriculaImovelFormatada( debitoAutomatico.getImovel().getId()));
			
			colecaoHelper.add(helper);
		}
		
		httpServletRequest.setAttribute("colecaoDebitoAutomaticoHelper", colecaoHelper);

		return retorno;
	}
}

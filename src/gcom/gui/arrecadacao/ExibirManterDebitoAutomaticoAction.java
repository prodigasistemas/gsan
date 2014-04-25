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

/**
 * [UC0802] - Filtrar Debito Automático
 * 
 * @author Bruno Barros
 * @since 11/06/2008
 */
public class ExibirManterDebitoAutomaticoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterDebitoAutomatico");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recuperamos o filtro que foi passado e realizamos a pesquisa
		FiltroDebitoAutomatico filtro = (FiltroDebitoAutomatico) sessao
				.getAttribute("filtroDebitoAutomatico");
		
		// Adicionamos no filtro o caminho de banco e de agencia,
		// pois precisaremos de algumas informações desses objetos
		filtro.adicionarCaminhoParaCarregamentoEntidade( "agencia.banco" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "imovel" );
		
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Collection colecaoDebitoAutomatico = new ArrayList();
		if(filtro != null && !filtro.equals("")){
			Map resultado = 
				controlarPaginacao(
						httpServletRequest, 
						retorno,
						filtro, 
						DebitoAutomatico.class.getName() );
			
			colecaoDebitoAutomatico = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		
		// Criamos o helper que será carregado na tela de manter debito automatico
		Iterator iteDebitoAutomatico = colecaoDebitoAutomatico.iterator();
		Collection<ManterDebitoAutomaticoHelper> colHelper = new ArrayList();
		
		while ( iteDebitoAutomatico.hasNext() ){
			DebitoAutomatico debito = (DebitoAutomatico) iteDebitoAutomatico.next();
			ManterDebitoAutomaticoHelper helper = new ManterDebitoAutomaticoHelper();
			
			helper.setIdDebitoAutomatico( debito.getId()+"" );
			helper.setSiglaBanco( debito.getAgencia().getBanco().getDescricaoAbreviada() );
			helper.setAgencia( debito.getAgencia().getCodigoAgencia() );
			
			Cliente cliente = Fachada.getInstancia().pesquisarClienteUsuarioImovel( debito.getImovel().getId() );
			helper.setNomeCliente( cliente.getNome() );
			helper.setMatriculaImovel( debito.getImovel().getId()+"" );
			helper.setMatriculaImovelFormatada( Util.retornaMatriculaImovelFormatada( debito.getImovel().getId() ) );
			
			colHelper.add( helper );
		}
		
		httpServletRequest.setAttribute("colecaoDebitoAutomaticoHelper", colHelper);

		return retorno;
	}
}

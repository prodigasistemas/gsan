package gcom.gui.relatorio.faturamento;

import java.util.ArrayList;
import java.util.Collection;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que faz a exibição da tela para o usuário setar os campos e permitir
 * que ele insera uma resolução de diretoria [UC0217] Inserir Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class ExibirGerarRelatorioFaturasAgrupadasAction extends GcomAction {

	/**
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
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioFaturasAgrupadas");
		
		GerarRelatorioFaturasAgrupadasActionForm gerarRelatorioFaturasAgrupadasActionForm = (GerarRelatorioFaturasAgrupadasActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").trim().equals("")) {
			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			gerarRelatorioFaturasAgrupadasActionForm.setMesAno(Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
			gerarRelatorioFaturasAgrupadasActionForm.setIndicadorTotalizador(ConstantesSistema.SIM.toString());
		}
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);
		
		Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		
		httpServletRequest.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
		
		String idCliente = gerarRelatorioFaturasAgrupadasActionForm.getIdCliente();
		
		if (idCliente != null && !idCliente.trim().equals("")) {
			Cliente cliente = fachada.pesquisarClienteDigitado(new Integer(idCliente));
			
			if (cliente != null) {
				gerarRelatorioFaturasAgrupadasActionForm.setNomeCliente(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idEsferaPoder");
			} else {
				gerarRelatorioFaturasAgrupadasActionForm.setIdCliente("");
				gerarRelatorioFaturasAgrupadasActionForm.setNomeCliente("Cliente Inexistente");

				httpServletRequest.setAttribute("clienteInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}
		} else {
			gerarRelatorioFaturasAgrupadasActionForm.setNomeCliente("");
		}
		
		String idClienteSuperior = gerarRelatorioFaturasAgrupadasActionForm.getIdClienteSuperior();
		
		if (idClienteSuperior != null && !idClienteSuperior.trim().equals("")) {
			Cliente cliente = fachada.pesquisarClienteDigitado(new Integer(idClienteSuperior));
			
			if (cliente != null) {
				gerarRelatorioFaturasAgrupadasActionForm.setNomeClienteSuperior(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			} else {
				gerarRelatorioFaturasAgrupadasActionForm.setIdClienteSuperior("");
				gerarRelatorioFaturasAgrupadasActionForm.setNomeClienteSuperior("Cliente Inexistente");

				httpServletRequest.setAttribute("clienteSuperiorInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idClienteSuperior");
			}
		} else {
			gerarRelatorioFaturasAgrupadasActionForm.setNomeClienteSuperior("");
		}
		
		Collection clientesAssociados = new ArrayList();
		
		String idEsferaPoder = gerarRelatorioFaturasAgrupadasActionForm.getIdEsferaPoder();
		
		if (idEsferaPoder != null && !idEsferaPoder.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			clientesAssociados = fachada.pesquisarClientesFaturas(new Integer(idEsferaPoder));
		} 
		
		httpServletRequest.setAttribute("clientesAssociados", clientesAssociados);
		
		return retorno;

	}

}

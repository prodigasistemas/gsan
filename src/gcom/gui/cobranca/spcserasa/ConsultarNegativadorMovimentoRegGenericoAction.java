package gcom.gui.cobranca.spcserasa;


import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorMovimentoReg;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Chama o o caso de uso correspondente ao do negativador passado.
 * [UC0683]- Consultar Dados do Registro SPC ou
 * [UC0684]- Consultar Dados do Registro SERASA
 * 
 * @author Yara Taciane de Souza
 * @date 23/01/2008
 */
public class ConsultarNegativadorMovimentoRegGenericoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		NegativadorMovimentoReg negativadorMovimentoReg = null;

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		String confirmado = httpServletRequest.getParameter("confirmado");

		String idNegativadorMovimentoReg = null;

		if (httpServletRequest.getParameter("reload") == null
				|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
						"") && (confirmado == null || confirmado.equals(""))) {

			if (httpServletRequest.getParameter("idRegistro") != null) {
				idNegativadorMovimentoReg = httpServletRequest
						.getParameter("idRegistro");
				httpServletRequest.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistro", idNegativadorMovimentoReg);
			} else if (httpServletRequest.getParameter("idRegistro") == null) {
				idNegativadorMovimentoReg = (String) sessao
						.getAttribute("idRegistro");
				httpServletRequest.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistro") != null) {
				idNegativadorMovimentoReg = httpServletRequest
						.getParameter("idRegistro");
				httpServletRequest.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistro", idNegativadorMovimentoReg);
			}
		} else {
			idNegativadorMovimentoReg = (String) sessao
					.getAttribute("idRegistro");
		}
	

		if ((idNegativadorMovimentoReg != null && !idNegativadorMovimentoReg
				.equals(""))
				&& (httpServletRequest.getParameter("desfazer") == null)
				&& (httpServletRequest.getParameter("reload") == null || httpServletRequest
						.getParameter("reload").equalsIgnoreCase(""))) {


			FiltroNegativadorMovimentoReg filtroNegativadorMovimentoReg = new FiltroNegativadorMovimentoReg();
			filtroNegativadorMovimentoReg
					.adicionarParametro(new ParametroSimples(
							FiltroNegativadorMovimentoReg.ID,
							idNegativadorMovimentoReg));
			
			filtroNegativadorMovimentoReg
			.adicionarCaminhoParaCarregamentoEntidade("negativadorRegistroTipo");		
			filtroNegativadorMovimentoReg.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimento.negativador.cliente");
			filtroNegativadorMovimentoReg.adicionarCaminhoParaCarregamentoEntidade("imovel");
		
			Collection<NegativadorMovimentoReg> collectionNegativadorMovimentoReg = fachada
					.pesquisar(filtroNegativadorMovimentoReg,
							NegativadorMovimentoReg.class.getName());
			
		   //------------------------------------------------------------------------------------------------------------	

			if (collectionNegativadorMovimentoReg != null) {
				
				negativadorMovimentoReg = (NegativadorMovimentoReg) Util
						.retonarObjetoDeColecao(collectionNegativadorMovimentoReg);

				if (negativadorMovimentoReg.getNegativadorMovimento() != null
						&& negativadorMovimentoReg.getNegativadorMovimento()
								.getNegativador() != null) {

					Negativador negativador = negativadorMovimentoReg
							.getNegativadorMovimento().getNegativador();

					if (negativador.getId().equals(Negativador.NEGATIVADOR_SPC)) {
						retorno = actionMapping
								.findForward("consultarDadosRegistroSPC");
					} else if (negativador.getId().equals(
							Negativador.NEGATIVADOR_SERASA)) {
						retorno = actionMapping
								.findForward("consultarDadosRegistroSERASA");
					} else {
						throw new ActionServletException(
						"atencao.negativador.nao.selecionado");
					}
					
				sessao.setAttribute("negativadorMovimentoReg", negativadorMovimentoReg);
					

				} else {
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
				

			}

		}

		return retorno;
	}
}

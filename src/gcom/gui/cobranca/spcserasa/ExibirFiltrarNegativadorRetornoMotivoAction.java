package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibir tela de filtro para motivo de retorno do Negativador.
 * 
 * @author Thiago Vieira
 * @date 04/01/2008
 */
public class ExibirFiltrarNegativadorRetornoMotivoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarNegativadorRetornoMotivo");
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		FiltrarNegativadorRetornoMotivoActionForm form = (FiltrarNegativadorRetornoMotivoActionForm) actionForm;
		
		form.setOkCodigoMotivo("true");
		String codigoMotivoDigitado = (String) form.getCodigoMotivo();
		
		if (httpServletRequest.getParameter("limpaSessao") != null
				&& !httpServletRequest.getParameter("limpaSessao").equals("")) {
			form.setDescricaoRetornoMotivo("");
			form.setCodigoMotivo("");
			form.setIndicadorUso("");
			form.setIndicadorRegistroAceito("");		
		}

		Collection colecaoNegativador = (Collection) sessao.getAttribute("colecaoNegativador");

		if (colecaoNegativador == null) {

			FiltroNegativador filtroNegativador = new FiltroNegativador();			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.adicionarParametro(
					new ParametroSimples(FiltroNegativador.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));	
			filtroNegativador.setConsultaSemLimites(true);

			colecaoNegativador = fachada.pesquisar(filtroNegativador,Negativador.class.getName());

			if (colecaoNegativador == null || colecaoNegativador.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"NEGATIVADOR");
			} else {
				sessao.setAttribute("colecaoNegativador", colecaoNegativador);
			}
		}
		
		//setar o valor inicial de "Indicador de Registro Aceito" sempre para todos, inicialmente
		form.setIndicadorRegistroAceito(Short.toString(ConstantesSistema.TODOS));
//		setar o valor inicial de "Indicador de Uso" sempre para todos, inicialmente
		form.setIndicadorUso(Short.toString(ConstantesSistema.TODOS));
		
		if (codigoMotivoDigitado != null && !codigoMotivoDigitado.trim().equals("")){
			FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, codigoMotivoDigitado));
        	
        	Collection codigoMotivoEncontrado = Fachada.getInstancia().pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
        	        	
        	if (codigoMotivoEncontrado != null && !codigoMotivoEncontrado.isEmpty()) {
           		form.setOkCodigoMotivo("true");
        	} else {
        		form.setCodigoMotivo("");
        		httpServletRequest.setAttribute("corCodigoMotivo","exception");
           		form.setMensagemCodigoMotivo(ConstantesSistema.CODIGO_MOTIVO_INEXISTENTE);
        		form.setOkCodigoMotivo("false");
        	}
		}
		
		return retorno;
	}
}

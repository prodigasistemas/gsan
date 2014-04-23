package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroTramite;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de consulta de tramites do ra.
 * 
 * @author Leonardo Regis
 * @created 11/08/2006
 */
public class ExibirConsultarRegistroAtendimentoTramiteDetalhadoAction extends GcomAction {
	/**
	 * Excute do Exibir Consultar RA Tramites do Popup
	 *
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimentoTramiteDetalhado");
		
		Fachada fachada = Fachada.getInstancia();
		
		ConsultarRegistroAtendimentoTramiteActionForm consultarRegistroAtendimentoTramite = 
				(ConsultarRegistroAtendimentoTramiteActionForm) actionForm;
		
		Collection<Tramite> colecaoTramite = null; 

		FiltroTramite filtroTramite = new FiltroTramite();
		
		filtroTramite.adicionarParametro(new ParametroSimples(FiltroTramite.ID, new Integer(consultarRegistroAtendimentoTramite.getTramiteId())));
		filtroTramite.setCampoOrderBy("dataTramite");
		
		filtroTramite.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalDestino");
		filtroTramite.adicionarCaminhoParaCarregamentoEntidade("usuarioResponsavel");
		filtroTramite.adicionarCaminhoParaCarregamentoEntidade("usuarioRegistro");
		//filtroTramite.adicionarCaminhoParaCarregamentoEntidade("dataTramite");	
		
		colecaoTramite = fachada.pesquisar(filtroTramite, Tramite.class.getName());
		if (colecaoTramite != null && !colecaoTramite.isEmpty()) {
			Tramite tramite = (Tramite) Util.retonarObjetoDeColecao(colecaoTramite);
			consultarRegistroAtendimentoTramite.setUnidadeDestinoId(tramite.getUnidadeOrganizacionalDestino().getId()+"");
			consultarRegistroAtendimentoTramite.setUnidadeDestinoDescricao(tramite.getUnidadeOrganizacionalDestino().getDescricao());
			consultarRegistroAtendimentoTramite.setUsuarioResponsavelId(tramite.getUsuarioResponsavel().getId()+"");
			consultarRegistroAtendimentoTramite.setUsuarioResponsavelNome(tramite.getUsuarioResponsavel().getNomeUsuario());
			consultarRegistroAtendimentoTramite.setUsuarioRegistroId(tramite.getUsuarioRegistro().getId()+"");
			consultarRegistroAtendimentoTramite.setUsuarioRegistroNome(tramite.getUsuarioRegistro().getNomeUsuario());
			consultarRegistroAtendimentoTramite.setDataTramitacao(Util.formatarData(tramite.getDataTramite()));
			consultarRegistroAtendimentoTramite.setHoraTramitacao(Util.formatarHoraSemData(tramite.getDataTramite()));
			consultarRegistroAtendimentoTramite.setParecer(tramite.getParecerTramite());
			
		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Trâmite");
		}
		
		return retorno;
	}
}

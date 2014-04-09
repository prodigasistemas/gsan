package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 04/08/2006
 */
public class InserirTipoServicoReferenciaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um tipo de serviço de referência.
	 * 
	 * [UC0436] Inserir Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 04/08/2006
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

		//ActionForward retorno = actionMapping.findForward("tipoServicoReferenciaInserirFechar");
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirTipoServicoReferenciaActionForm inserirTipoServicoReferenciaActionForm = (InserirTipoServicoReferenciaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		ServicoTipoReferencia servicoTipoReferencia = new ServicoTipoReferencia();

		String descricao = inserirTipoServicoReferenciaActionForm
				.getDescricao();

		String abreviatura = inserirTipoServicoReferenciaActionForm
				.getAbreviatura();

		String indicadorExistenciaOsReferencia = inserirTipoServicoReferenciaActionForm
				.getIndicadorExistenciaOsReferencia();

		String situacaoOSAntes = null;

		if (indicadorExistenciaOsReferencia != null
				&& indicadorExistenciaOsReferencia
						.equals(ServicoTipoReferencia.INDICADOR_EXISTENCIA_OS_REFERENCIA_ATIVO
								.toString())) {
			if (inserirTipoServicoReferenciaActionForm.getSituacaoOSAntes() == null
					|| inserirTipoServicoReferenciaActionForm
							.getSituacaoOSAntes()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException(
						"atencao.indicador_os_incompativel_os_antes");
			}

			if (inserirTipoServicoReferenciaActionForm.getSituacaoOSApos() == null
					|| inserirTipoServicoReferenciaActionForm
							.getSituacaoOSApos()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException(
						"atencao.indicador_os_incompativel_os_apos");

			}
		}
		if ( inserirTipoServicoReferenciaActionForm.getSituacaoOSAntes() != null ) {
			if (!inserirTipoServicoReferenciaActionForm.getSituacaoOSAntes()
					.toString().equals(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
	
				situacaoOSAntes =  inserirTipoServicoReferenciaActionForm
						.getSituacaoOSAntes() ;
	
				servicoTipoReferencia.setSituacaoOsReferenciaAntes(new Integer(
						situacaoOSAntes));
	
			}
		} else {
			situacaoOSAntes = "";
		}
		
		String situacaoOSApos = null;

		if ( inserirTipoServicoReferenciaActionForm.getSituacaoOSApos() != null ) {
			if (!inserirTipoServicoReferenciaActionForm.getSituacaoOSApos()
					.toString().equals(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				situacaoOSApos =  inserirTipoServicoReferenciaActionForm
						.getSituacaoOSApos() ;
	
				servicoTipoReferencia.setSituacaoOsReferenciaApos(new Integer(
						situacaoOSApos));
			}
		} else { 
			situacaoOSApos = "";
		}
		String tipoServico = inserirTipoServicoReferenciaActionForm
				.getTipoServico();

		// Monta o Objeto Servico Tipo Referencia

		servicoTipoReferencia.setDescricao(descricao);

		servicoTipoReferencia.setDescricaoAbreviada(abreviatura);

		servicoTipoReferencia.setIndicadorExistenciaOsReferencia(new Short(
				indicadorExistenciaOsReferencia));
		
		
		if (tipoServico != null
				&& !tipoServico.equals("")) {

			ServicoTipo servicoTipo = new ServicoTipo();

			servicoTipo.setId(new Integer(tipoServico));

			servicoTipoReferencia.setServicoTipo(servicoTipo);

		}
		
		if(inserirTipoServicoReferenciaActionForm.getIndicadorDiagnostico() != null
				&& !inserirTipoServicoReferenciaActionForm.getIndicadorDiagnostico().trim().equals("")){
			servicoTipoReferencia.setIndicadorDiagnostico(new Short(inserirTipoServicoReferenciaActionForm.getIndicadorDiagnostico()));
		}
		
		if(inserirTipoServicoReferenciaActionForm.getIndicadorFiscalizacao() != null
				&& !inserirTipoServicoReferenciaActionForm.getIndicadorFiscalizacao().trim().equals("")){
			servicoTipoReferencia.setIndicadorFiscalizacao(new Short(inserirTipoServicoReferenciaActionForm.getIndicadorFiscalizacao()));
		}

		servicoTipoReferencia.setIndicadorUso(new Short(
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if(sessao.getAttribute("semMenu") != null){
			fachada.validarTipoServicoReferenciaParaInsercao(servicoTipoReferencia);
			sessao.setAttribute("servicoTipoReferencia", servicoTipoReferencia);
			httpServletRequest.setAttribute("fecharPopup","S");
		}else{
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			fachada.inserirTipoServicoReferencia(servicoTipoReferencia, usuarioLogado);
			
			montarPaginaSucesso(httpServletRequest,
					"Tipo de Serviço de Referência " + descricao + " inserido com sucesso.",
					"Inserir outro Tipo de Serviço de Referência",
					"exibirInserirTipoServicoReferenciaAction.do?menu=sim");
			
			sessao.removeAttribute("InserirTipoServicoReferenciaActionForm");
		}

		return retorno;
	}
}

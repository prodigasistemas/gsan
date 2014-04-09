package gcom.gui.faturamento;

import java.util.Collection;

import gcom.cadastro.imovel.Categoria;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ValidarRetirarSituacaoEspecialFaturamentoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm = (SituacaoEspecialFaturamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);				
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
	
		SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper = transferirActionFormParaHelper(situacaoEspecialFaturamentoActionForm,usuarioLogado);
		
		Collection pesquisarImoveisParaSerRetirados = (Collection) sessao.getAttribute("COMSituacaoEspecialFaturamento");
		
		
		fachada.retirarSituacaoEspecialFaturamento(situacaoEspecialFaturamentoHelper, pesquisarImoveisParaSerRetirados);
		
		
		
		montarPaginaSucesso(
				httpServletRequest,
				situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisCOMSituacaoEspecialFaturamento()
						+ " imóvel(is) retirado(s) da situação especial de faturamento com sucesso.",
				"Realizar outra Manutenção de Situação Especial de Faturamento",
				"exibirSituacaoEspecialFaturamentoInformarAction.do?menu=sim");
		
		situacaoEspecialFaturamentoActionForm.reset(actionMapping, httpServletRequest);
		
		return retorno;
	}


	
	private SituacaoEspecialFaturamentoHelper transferirActionFormParaHelper(
			SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm,
			Usuario usuarioLogado) {

		SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper = new SituacaoEspecialFaturamentoHelper();

		situacaoEspecialFaturamentoHelper
				.setIdImovel(situacaoEspecialFaturamentoActionForm
						.getIdImovel() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getIdImovel());

		situacaoEspecialFaturamentoHelper
				.setInscricaoTipo(situacaoEspecialFaturamentoActionForm
						.getInscricaoTipo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getInscricaoTipo());

		situacaoEspecialFaturamentoHelper
				.setLoteDestino(situacaoEspecialFaturamentoActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLoteDestino());

		situacaoEspecialFaturamentoHelper
				.setQuadraDestinoNM(situacaoEspecialFaturamentoActionForm
						.getQuadraDestinoNM() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraDestinoNM());

		situacaoEspecialFaturamentoHelper
				.setLoteOrigem(situacaoEspecialFaturamentoActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getLoteOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeLocalidadeOrigem(situacaoEspecialFaturamentoActionForm
						.getNomeLocalidadeOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeLocalidadeOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeSetorComercialOrigem(situacaoEspecialFaturamentoActionForm
						.getNomeSetorComercialOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeSetorComercialOrigem());

		situacaoEspecialFaturamentoHelper
				.setQuadraOrigemNM(situacaoEspecialFaturamentoActionForm
						.getQuadraOrigemNM() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraOrigemNM());

		situacaoEspecialFaturamentoHelper
				.setQuadraMensagemOrigem(situacaoEspecialFaturamentoActionForm
						.getQuadraMensagemOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraMensagemOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeLocalidadeDestino(situacaoEspecialFaturamentoActionForm
						.getNomeLocalidadeDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeLocalidadeDestino());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialDestinoCD(situacaoEspecialFaturamentoActionForm
						.getSetorComercialDestinoCD() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialDestinoCD());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialOrigemCD(situacaoEspecialFaturamentoActionForm
						.getSetorComercialOrigemCD() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialOrigemCD());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialOrigemID(situacaoEspecialFaturamentoActionForm
						.getSetorComercialOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialOrigemID());

		situacaoEspecialFaturamentoHelper
				.setQuadraOrigemID(situacaoEspecialFaturamentoActionForm
						.getQuadraOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraOrigemID());

		situacaoEspecialFaturamentoHelper
				.setLocalidadeDestinoID(situacaoEspecialFaturamentoActionForm
						.getLocalidadeDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLocalidadeDestinoID());

		situacaoEspecialFaturamentoHelper
				.setLocalidadeOrigemID(situacaoEspecialFaturamentoActionForm
						.getLocalidadeOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLocalidadeOrigemID());

		situacaoEspecialFaturamentoHelper
				.setNomeSetorComercialDestino(situacaoEspecialFaturamentoActionForm
						.getNomeSetorComercialDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeSetorComercialDestino());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialDestinoID(situacaoEspecialFaturamentoActionForm
						.getSetorComercialDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialDestinoID());

		situacaoEspecialFaturamentoHelper
				.setQuadraMensagemDestino(situacaoEspecialFaturamentoActionForm
						.getQuadraMensagemDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraMensagemDestino());

		situacaoEspecialFaturamentoHelper
				.setQuadraDestinoID(situacaoEspecialFaturamentoActionForm
						.getQuadraDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraDestinoID());

		situacaoEspecialFaturamentoHelper
				.setTipoSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getTipoSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getTipoSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setLoteOrigem(situacaoEspecialFaturamentoActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getLoteOrigem());

		situacaoEspecialFaturamentoHelper
				.setLoteDestino(situacaoEspecialFaturamentoActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLoteDestino());

		situacaoEspecialFaturamentoHelper
				.setSubloteOrigem(situacaoEspecialFaturamentoActionForm
						.getSubloteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSubloteOrigem());

		situacaoEspecialFaturamentoHelper
				.setSubloteDestino(situacaoEspecialFaturamentoActionForm
						.getSubloteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSubloteDestino());

		situacaoEspecialFaturamentoHelper
				.setIdFaturamentoSituacaoMotivo(situacaoEspecialFaturamentoActionForm
						.getIdFaturamentoSituacaoMotivo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getIdFaturamentoSituacaoMotivo());

		situacaoEspecialFaturamentoHelper
				.setIdFaturamentoSituacaoTipo(situacaoEspecialFaturamentoActionForm
						.getIdFaturamentoSituacaoTipo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getIdFaturamentoSituacaoTipo());

		situacaoEspecialFaturamentoHelper
				.setMesAnoReferenciaFaturamentoInicial(situacaoEspecialFaturamentoActionForm
						.getMesAnoReferenciaFaturamentoInicial() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getMesAnoReferenciaFaturamentoInicial());

		situacaoEspecialFaturamentoHelper
				.setMesAnoReferenciaFaturamentoFinal(situacaoEspecialFaturamentoActionForm
						.getMesAnoReferenciaFaturamentoFinal() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getMesAnoReferenciaFaturamentoFinal());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisCOMSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisCOMSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisCOMSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisSEMSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisSEMSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisSEMSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisAtualizados(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisAtualizados() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisAtualizados());
		
		situacaoEspecialFaturamentoHelper
		.setCodigoRotaInicial(situacaoEspecialFaturamentoActionForm
				.getCdRotaInicial() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getCdRotaInicial());
		
		situacaoEspecialFaturamentoHelper
		.setCodigoRotaFinal(situacaoEspecialFaturamentoActionForm
				.getCdRotaFinal() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getCdRotaFinal());
		
		situacaoEspecialFaturamentoHelper
		.setSequencialRotaInicial(situacaoEspecialFaturamentoActionForm
				.getSequencialRotaInicial() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getSequencialRotaInicial());
		
		situacaoEspecialFaturamentoHelper
		.setSequencialRotaFinal(situacaoEspecialFaturamentoActionForm
				.getSequencialRotaFinal() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getSequencialRotaFinal());
		
		//Colocado por Raphael Rossiter em 11/08/2008 - Analista:Rosana Carvalho
		if (situacaoEspecialFaturamentoActionForm.getObservacaoRetira() != null &&
			!situacaoEspecialFaturamentoActionForm.getObservacaoRetira().equals("")){
			situacaoEspecialFaturamentoHelper.setObservacaoRetira(
					situacaoEspecialFaturamentoActionForm.getObservacaoRetira());		
			situacaoEspecialFaturamentoHelper.setObservacao(
			situacaoEspecialFaturamentoActionForm.getObservacaoRetira());
		}
		
		/*situacaoEspecialFaturamentoHelper.setIdUsuarioRetira(usuarioLogado.getId().toString());
		situacaoEspecialFaturamentoHelper.setIdUsuario(usuarioLogado.getId().toString());*/
		
		situacaoEspecialFaturamentoHelper.setUsuarioLogado(usuarioLogado);
		Integer quantidadeImoveisCom = 0;
		if (situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisCOMSituacaoEspecialFaturamento()!=null 
				&& !situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisCOMSituacaoEspecialFaturamento().equals("")){
			
			quantidadeImoveisCom =new Integer(situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisCOMSituacaoEspecialFaturamento());
		}
		
		Integer quantidadeTotal = quantidadeImoveisCom;
		
		situacaoEspecialFaturamentoHelper.setQuantidadeDeImoveis(quantidadeTotal.toString());
		
		situacaoEspecialFaturamentoHelper.setIdsCategoria(situacaoEspecialFaturamentoActionForm.getIdsCategoria());
		
		if (situacaoEspecialFaturamentoActionForm.getIdsCategoria() != null) {
			
			String [] idsCategoria = situacaoEspecialFaturamentoActionForm.getIdsCategoria();
			
			for (int i = 0; i < idsCategoria.length; i++) {
				
				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorComercial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorIndustrial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorResidencial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorPublico(ConstantesSistema.SIM.toString());
				}
				
			}
		} 
		
		situacaoEspecialFaturamentoHelper.setIndicadorConsumoImovel(
				situacaoEspecialFaturamentoActionForm.getIndicadorConsumoImovel());
		
		return situacaoEspecialFaturamentoHelper;
	}
}

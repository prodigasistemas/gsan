package gcom.gui.faturamento;

import gcom.arrecadacao.ContratoDemanda;
import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarContratoDemandaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarContratoDemandaActionForm atualizarContratoDemandaActionForm = (AtualizarContratoDemandaActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		ContratoDemanda contratoDemanda = (ContratoDemanda) sessao
				.getAttribute("contratoDemandaAtualizar");
		
		String dataInicioContrato = atualizarContratoDemandaActionForm.getDataInicioContrato();
		String dataFimContrato = atualizarContratoDemandaActionForm.getDataFimContrato();
		String dataEncerramento = atualizarContratoDemandaActionForm.getDataEncerramento();
		String idMotivoCancelamento = atualizarContratoDemandaActionForm.getIdMotivoCancelamento();
		
		Imovel imovel = null;

		String idImovel = atualizarContratoDemandaActionForm.getIdImovel();
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
				imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Imóvel");
			}
			
		}
		
		contratoDemanda.setNumeroContrato(atualizarContratoDemandaActionForm.getNumeroContrato());
		contratoDemanda.setImovel(imovel);
		
		if (dataInicioContrato != null && !dataInicioContrato.trim().equals("")) {
			contratoDemanda.setDataContratoInicio(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataInicioContrato()));
		} else {
			contratoDemanda.setDataContratoInicio(null);
		}
		
		if (dataFimContrato != null && !dataFimContrato.trim().equals("")) {
			contratoDemanda.setDataContratoFim(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataFimContrato()));
		} else {
			contratoDemanda.setDataContratoFim(null);
		}
		
		if (dataEncerramento != null && !dataEncerramento.trim().equals("")) {
			contratoDemanda.setDataContratoEncerrado(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataEncerramento()));
		} else {
			contratoDemanda.setDataContratoEncerrado(null);
		}
		
		if (idMotivoCancelamento != null && !idMotivoCancelamento.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ContratoMotivoCancelamento contratoMotivoCancelamento = new ContratoMotivoCancelamento();
			contratoMotivoCancelamento.setId(new Integer(idMotivoCancelamento));
			contratoDemanda.setContratoMotivoCancelamento(contratoMotivoCancelamento);
		} else {
			contratoDemanda.setContratoMotivoCancelamento(null);
		}
		
		if (contratoDemanda.getDataContratoEncerrado() == null && contratoDemanda.getContratoMotivoCancelamento() == null) {
			FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
			filtroContratoDemanda.adicionarParametro(
					new ParametroSimples(FiltroContratoDemanda.IMOVEL, idImovel));
			filtroContratoDemanda.adicionarParametro(
					new ParametroNulo(FiltroContratoDemanda.DATACONTRATOENCERRAMENTO));
			filtroContratoDemanda.adicionarParametro(
					new ParametroSimplesDiferenteDe(FiltroContratoDemanda.ID, contratoDemanda.getId()));
			Collection colecaoContratoDemanda = fachada.pesquisar(
					filtroContratoDemanda, ContratoDemanda.class.getName());
			
			if (colecaoContratoDemanda != null & !colecaoContratoDemanda.isEmpty()) {
				throw new ActionServletException("atencao.contrato.demanda.encerrado", null, idImovel);
			}
		}
		
		// Inserir na base de dados ContratoDemanda
		fachada.atualizarContratoDemanda(contratoDemanda,usuarioLogado);
		
		// Montar a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato de Demanda de código "
				+ contratoDemanda.getId().toString()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Contrato de Demanda",
				"exibirFiltrarContratoDemandaAction.do?menu=sim");

		return retorno;

	}
}

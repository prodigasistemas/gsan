package gcom.gui.faturamento;

import gcom.arrecadacao.ContratoDemanda;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0512] Inserir Contrato de Demanda
 * 
 * @author Eduardo Bianchi
 * @date 15/02/2007
 */

public class InserirContratoDemandaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirContratoDemandaActionForm inserirContratoDemandaActionForm = (InserirContratoDemandaActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao
		.getAttribute(Usuario.USUARIO_LOGADO);
		
		ContratoDemanda contratoDemanda = new ContratoDemanda();
		
		Imovel imovel = null;
		
		String idImovel = inserirContratoDemandaActionForm.getIdImovel();
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
				imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Imóvel");
			}
			
			FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
			filtroContratoDemanda.adicionarParametro(
					new ParametroSimples(FiltroContratoDemanda.IMOVEL, idImovel));
			filtroContratoDemanda.adicionarParametro(
					new ParametroNulo(FiltroContratoDemanda.DATACONTRATOENCERRAMENTO));
			
			Collection colecaoContratoDemanda = fachada.pesquisar(
					filtroContratoDemanda, ContratoDemanda.class.getName());
			
			if (colecaoContratoDemanda != null & !colecaoContratoDemanda.isEmpty()) {
				throw new ActionServletException("atencao.contrato.demanda.encerrado", null, idImovel);
			}
		}
		
		
		
		imovel.setId(new Integer(inserirContratoDemandaActionForm.getIdImovel()));
		
		contratoDemanda.setNumeroContrato(inserirContratoDemandaActionForm.getNumeroContrato());
		contratoDemanda.setImovel(imovel);
		contratoDemanda.setDataContratoInicio(Util.converteStringParaDate(inserirContratoDemandaActionForm.getDataInicioContrato()));
		contratoDemanda.setDataContratoFim(Util.converteStringParaDate(inserirContratoDemandaActionForm.getDataFimContrato()));
		contratoDemanda.setUltimaAlteracao(new Date());
		
		// Inserir na base de dados ContratoDemanda
		Integer idContratoDemanda = fachada.inserirContratoDemanda(contratoDemanda,usuarioLogado);
		
		// Montar a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato de Demanda de código "
				+ idContratoDemanda.toString()
				+ " inserido com sucesso.",
				"Inserir outro Contrato de Demanda",
				"exibirInserirContratoDemandaAction.do?menu=sim",
				"exibirAtualizarContratoDemandaAction.do?inserir=sim&contratoDemandaID="
				+ idContratoDemanda,
		"Atualizar Contrato de Demanda Inserido");
		
		sessao.removeAttribute("InserirContratoDemandaActionForm");
		
		return retorno;
	}
}

package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 20/03/2007
 */
public class AlterarSituacaoLigacaoAction extends GcomAction {

	/**
	 * [UC0555] Alterar Situacao da Ligacao
	 * 
	 * Este caso de uso permite alterar a situacao da ligacao de agua e/ou
	 * esgoto de acordo com o indicadorde rede e Ordem de Servico gerada.
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		AlterarSituacaoLigacaoActionForm form = (AlterarSituacaoLigacaoActionForm) actionForm;

		//eeIntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		OrdemServico ordemServico = (OrdemServico) sessao
				.getAttribute("ordemServico");
		
		boolean veioEncerrarOS = false;

		// Usuario logado no sistema
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		
		String idOrdemServico = form.getIdOrdemServico();
		String indicadorTipoLigacao = form.getIndicadorTipoLigacao();
		String idSituacaoLigacaoAguaNova = form.getSituacaoLigacaoAguaNova();
		String idSituacaoLigacaoEsgotoNova = form
				.getSituacaoLigacaoEsgotoNova();
		
		if (idOrdemServico == null) {
			throw new ActionServletException("atencao.required", null,
					"Ordem de Serviço");
		} 

		if (indicadorTipoLigacao == null) {
			throw new ActionServletException("atencao.required", null,
					"Tipo de Ligacao a ser Removida");
		}
		
		if (indicadorTipoLigacao == null) {
			throw new ActionServletException("atencao.required", null,
					"Tipo de Ligacao a ser Removida");
		}
		if (indicadorTipoLigacao != null){
			if(indicadorTipoLigacao.equalsIgnoreCase("1") 
				&& idSituacaoLigacaoAguaNova.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
					"Nova Situação da Ligação de Água");
			}
		}
		
		if (indicadorTipoLigacao != null){
			if(indicadorTipoLigacao.equalsIgnoreCase("2") 
				&& idSituacaoLigacaoEsgotoNova.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
					"Nova Situação da Ligação de Esgoto");
			}
		}
		
		if (indicadorTipoLigacao != null){
			if(indicadorTipoLigacao.equalsIgnoreCase("3") 
				&& idSituacaoLigacaoAguaNova.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)
				&& idSituacaoLigacaoEsgotoNova.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
					"Nova Situação da Ligação de Água e de Esgoto");
			}
		}
		
		fachada.validarOrdemServicoAlterarSituacaoLigacao(ordemServico,veioEncerrarOS);
		
		Imovel imovel = ordemServico.getImovel();

		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				imovel.getId()));

		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");

		Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class
				.getName());

		imovel = (Imovel) colecaoImovel.iterator().next();
		
		Integer idImovel = fachada.alterarSituacaoLigacao( imovel,  indicadorTipoLigacao,  idSituacaoLigacaoAguaNova, 
				 idSituacaoLigacaoEsgotoNova,  idOrdemServico, 
				 usuarioLogado);

		
		montarPaginaSucesso(httpServletRequest, "A Alteração da Situação da Ligação do Imóvel "
				+ idImovel+ " efetuada com sucesso.",
				"Alterar outra Situação da Ligação",
				"exibirAlterarSituacaoLigacaoAction.do?menu=sim");
		return retorno;
	}
}

package gcom.gui.faturamento.credito;

import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0194] Crédito a Realizar
 * Permite cancelar um ou mais créditos a realizar de um determinado imóvel
 * @author Roberta Costa
 * @since  11/01/2006 
 */
public class ManterCreditoARealizarAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        // Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        // Pega uma instância da fachada
        Fachada fachada = Fachada.getInstancia();

        // Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        // Instância do formulário que está sendo utilizado
        ManterCreditoARealizarActionForm manterCreditoARealizarActionForm = (ManterCreditoARealizarActionForm) actionForm;

        // Pega o Imóvel da sessão
        Imovel imovel = null;
        if ( sessao.getAttribute("imovelPesquisado") != null ){
        	imovel = (Imovel)sessao.getAttribute("imovelPesquisado");
        }

        // Obtém os ids de remoção
        String[] ids = manterCreditoARealizarActionForm.getIdCreditoARealizar();
        
        //mensagem de erro quando o usuário tenta excluir sem ter selecionado nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }        
        
		// [FS0003 - Verificar usuário com débito em cobrança administrativa
		if (imovel != null) {
			FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();

			filtroImovelCobrancaSituacao
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
			filtroImovelCobrancaSituacao
					.adicionarParametro(new ParametroSimples(
							FiltroImovelCobrancaSituacao.IMOVEL_ID, imovel
									.getId()));

			Collection imovelCobrancaSituacaoEncontrada = fachada
					.pesquisar(filtroImovelCobrancaSituacao,
							ImovelCobrancaSituacao.class.getName());

			// Verifica se o imóvel tem débito em cobrança administrativa
			if (imovelCobrancaSituacaoEncontrada != null
					&& !imovelCobrancaSituacaoEncontrada.isEmpty()) {
				
				if (((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
						.get(0)).getCobrancaSituacao() != null) {
					
					if (((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
							.get(0)).getCobrancaSituacao().getId().equals(
							CobrancaSituacao.COBRANCA_ADMINISTRATIVA) && ((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
									.get(0)).getDataRetiradaCobranca() == null) {
						
						throw new ActionServletException(
								"atencao.pesquisa.imovel.cobranca_administrativa");
					}
				}
			}
		}

        fachada.cancelarCreditoARealizar(ids, imovel, usuarioLogado);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Crédito(s) a Realizar(s) do Imóvel " + imovel.getId() + " cancelado(s) com sucesso.",
                    "Cancelar outro Crédito a Realizar",
                    "exibirManterCreditoARealizarAction.do");
        }

		return retorno;
	}
}

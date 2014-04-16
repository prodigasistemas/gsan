package gcom.gui.faturamento;

import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Manter Débito a Cobrar ao Imovel
 * [UC0184] Manter Débito a Cobrar
 * @author Rafael Santos
 * @since 30/12/2005 
 * 
 */
public class ManterDebitoACobrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManterDebitoACobrarActionForm manterDebitoACobrarActionForm = (ManterDebitoACobrarActionForm) actionForm;
		
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        String confirmado = httpServletRequest.getParameter("confirmado");

        HttpSession sessao = httpServletRequest.getSession(false);
        Imovel imovel = null;
        
        if(sessao.getAttribute("imovelPesquisado") != null){
        	imovel = (Imovel)sessao.getAttribute("imovelPesquisado");
        }

        Fachada fachada = Fachada.getInstancia();

//      Obtém os ids de remoção
        String[] ids = manterDebitoACobrarActionForm.getIdDebitoACobrar();
        
        //mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum
        //registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException(
                    "atencao.registros.nao_selecionados");
        }        
        
		// [FS0003 - Verificar usuário com débito em cobrança
		// administrativa]
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
		
		if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
			httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/manterDebitoACobrarAction.do");
			
			if(fachada.verificarAutosAssociadosAoDebito(manterDebitoACobrarActionForm.getIdDebitoACobrar())){
				return  montarPaginaConfirmacao("atencao.existe_auto_infracao",
					httpServletRequest,actionMapping);
			}
		}
		
        Integer matriculaImovel = null;
        if (imovel != null){
        	matriculaImovel = imovel.getId();
        }
        fachada.cancelarDebitoACobrar(ids, getUsuarioLogado(httpServletRequest), matriculaImovel);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Débito(s) a Cobrar do imóvel " +imovel.getId()+ " cancelado(s) com sucesso.",
                    "Realizar outro Cancelamento de Débito a Cobrar",
                    "exibirManterDebitoACobrarAction.do?menu=sim");
        }

		return retorno;
	}
}

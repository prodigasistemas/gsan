package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarEconomiaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		EconomiaActionForm economiaActionForm = (EconomiaActionForm) actionForm;

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoImovelEconomiasModificadas = (Collection) sessao
				.getAttribute("colecaoImovelEconomiasModificadas");

		Collection colecaoClientesImoveisEconomiaRemovidas = (Collection) sessao.getAttribute("colecaoClientesImoveisEconomiaRemovidas");
				
		Collection colecaoRemovidas = (Collection) sessao.getAttribute("colecaoRemovidas");
		
        /** alterado por pedro alexandre dia 18/11/2006 
         * Recupera o usuário logado para passar no metódo de remover quadra
         * para verificar se o usuário tem abrangência para remover as quadras 
         * selecionadas.
         */
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

        
		int alteracao = 0;
		
		if (colecaoRemovidas != null && !colecaoRemovidas.isEmpty()){
			for (Iterator iter = colecaoRemovidas.iterator(); iter.hasNext();) {
				ImovelEconomia imovelEconomiaIterator = (ImovelEconomia) iter.next();
               
                 /** alterado por pedro alexandre dia 19/11/2006
                 * alterado para acoplar o esquema de segurança de acesso por abragência */
				//fachada.removerImovelEconomia(imovelEconomiaIterator);
               
				fachada.removerImovelEconomia(imovelEconomiaIterator, usuarioLogado);
				
			}
			
			alteracao = 1;
			
		}
		
		
		if (colecaoImovelEconomiasModificadas != null
				&& !colecaoImovelEconomiasModificadas.isEmpty()) {
			// Cria variaveis

            /** alterado por pedro alexandre dia 19/11/2006
             * alterado para acoplar o esquema de segurança de acesso por abragência */
			// inseri os clientes imoveis economias e os imoveis economia na
			// subcategoria
            //fachada.informarImovelEconomia(colecaoImovelEconomiasModificadas);
			
			
			
			fachada.informarImovelEconomia(colecaoImovelEconomiasModificadas, usuarioLogado);
			
			
			
			alteracao = 1;
		}
		
				
		if (colecaoClientesImoveisEconomiaRemovidas != null && !colecaoClientesImoveisEconomiaRemovidas.isEmpty()){
			
			Iterator iterator = colecaoClientesImoveisEconomiaRemovidas.iterator();
			
			while (iterator.hasNext()) {
				ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) iterator.next();
				
				fachada.remover(clienteImovelEconomia);
				
			}
			
		}
		
		
		if (alteracao == 0){
			throw new ActionServletException(
					"atencao.nao_atualizado_subcategoria_economia");
		}

		// limpa o form e a sessao
		sessao.removeAttribute("colecaoImovelSubCategoriasCadastradas");
		sessao.removeAttribute("colecaoImovelEconomiasModificadas");
		sessao.removeAttribute("colecaoClientesImoveisEconomiaRemovidas");
		economiaActionForm.setCodigoImovelSubCategoria(null);
		
		montarPaginaSucesso(httpServletRequest, "Imóvel "
				+ economiaActionForm.getIdImovel()
				+ " com economia(s) informada(s) com sucesso.",
				"Informar outro(s) Imóvel(eis) Economia(s)",
				"exibirInformarEconomiaAction.do?menu=sim");

		economiaActionForm.setIdImovel(null);
		economiaActionForm.setEnderecoImovel(null);

		return retorno;
	}
}

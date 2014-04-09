package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirManterTarifaSocialCartaoTipoAction extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Define ação de retorno
        ActionForward retorno = actionMapping
                .findForward("manterTarifaSocialCartaoTipo");

        //Obtém a fachada
        Fachada fachada = Fachada.getInstancia();

        //Form dinâmico para obter
        //DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //Inicializa a coleção
        Collection colecaoTarifaSocialCartaoTipo = null;

        //Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Criação do filtro de tarifa social cartão tipo
        FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = null;

        //Verifica se o filtro foi informado pela página de filtragem de
        // logradouro
        if (httpServletRequest.getAttribute("filtroTarifaSocialCartaoTipo") != null) {
            filtroTarifaSocialCartaoTipo = (FiltroTarifaSocialCartaoTipo) httpServletRequest
                    .getAttribute("filtroTarifaSocialCartaoTipo");
        } else {
            //Caso o exibirManterTarifaSocialCartaoTipo não tenha passado por
            // algum esquema de filtro,
            //a quantidade de registros é verificada para avaliar a necessidade
            // de filtragem
            filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();

            if (fachada.registroMaximo(TarifaSocialCartaoTipo.class) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
                //Se o limite de registros foi atingido, a página de filtragem
                // é chamada
                retorno = actionMapping
                        .findForward("filtrarTarifaSocialCartaoTipo");
                //limpa os parametros do form pesquisar
                sessao.removeAttribute("PesquisarActionForm");
            }
        }

        //A pesquisa de tarifa social cartão tipo só será feita se o forward
        // estiver direcionado
        //para a página de manterTarifaSocialCartaoTipo
        if (retorno.getName().equalsIgnoreCase("manterTarifaSocialCartaoTipo")) {
                        
        	Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTarifaSocialCartaoTipo, TarifaSocialCartaoTipo.class.getName());
			colecaoTarifaSocialCartaoTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

            if (colecaoTarifaSocialCartaoTipo == null
                    || colecaoTarifaSocialCartaoTipo.isEmpty()) {
                //Nenhum cliente cadastrado
                throw new ActionServletException("atencao.naocadastrado", null,
                        "tipo de cartão da tarifa social");
            }

            /*
             * if (logradouros.size() >
             * ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) { throw new
             * ActionServletException("atencao.pesquisa.muitosregistros"); }
             */
            sessao.setAttribute("colecaoTarifaSocialCartaoTipo",
                    colecaoTarifaSocialCartaoTipo);

        }
        return retorno;
    }

}

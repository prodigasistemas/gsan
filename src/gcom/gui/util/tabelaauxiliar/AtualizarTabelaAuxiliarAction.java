package gcom.gui.util.tabelaauxiliar;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;

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
public class AtualizarTabelaAuxiliarAction extends GcomAction {
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

        //Pega o action form
        TabelaAuxiliarActionForm tabelaAuxiliarActionForm = (TabelaAuxiliarActionForm) actionForm;

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Recupera o ponto de coleta da sessão
        TabelaAuxiliar tabelaAuxiliar = (TabelaAuxiliar) sessao
                .getAttribute("tabelaAuxiliar");

        //Atualiza a tabela auxiliar com os dados inseridos pelo usuário
        //A data de última alteração não é alterada, pois será usada na
        //verificação de atualização
        tabelaAuxiliar.setDescricao(tabelaAuxiliarActionForm.getDescricao().toUpperCase());
        tabelaAuxiliar.setIndicadorUso(new Short(tabelaAuxiliarActionForm.getIndicadorUso()));

        //Atualiza os dados
        fachada.atualizarTabelaAuxiliar(tabelaAuxiliar);
        
        String titulo = (String) sessao.getAttribute("titulo");
        
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

            montarPaginaSucesso(httpServletRequest,
            	titulo+" atualizada com sucesso",
                "Realizar outra manutenção de "+ titulo,
                ((String) sessao.getAttribute("funcionalidadeTabelaAuxiliarFiltrar"))+"&menu=sim");
        }

        //Remove os objetos da sessão
        sessao.removeAttribute("funcionalidadeTabelaAuxiliarFiltrar");
        sessao.removeAttribute("titulo");
        sessao.removeAttribute("descricao");
        sessao.removeAttribute("tabelaAuxiliar");
        sessao.removeAttribute("tamanhoMaximoCampo");
        sessao.removeAttribute("pacoteNomeObjeto");
        sessao.removeAttribute("tabelasAuxiliares");
        sessao.removeAttribute("tela");
        sessao.removeAttribute("totalRegistros");
        
        //devolve o mapeamento de retorno
        return retorno;
    }

}

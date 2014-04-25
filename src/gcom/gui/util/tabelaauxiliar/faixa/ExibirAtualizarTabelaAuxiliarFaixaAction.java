package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixa;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixa;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo/Romulo
 */
public class ExibirAtualizarTabelaAuxiliarFaixaAction extends GcomAction {
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("atualizarTabelaAuxiliarFaixa");

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        
    	if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
        //Código da tabela auxiliar a ser atualizada
        String id = null;

        if (httpServletRequest.getAttribute("id") != null) {
			id = (String) httpServletRequest.getAttribute("id");
			sessao.setAttribute("id", id);
		} else {
			if (manutencaoRegistroActionForm.getIdRegistroAtualizacao() != null) {
				id = manutencaoRegistroActionForm.getIdRegistroAtualizacao();
				sessao.setAttribute("id", id);
			}
		}

		if (sessao.getAttribute("id") != null) {
			id = (String) sessao.getAttribute("id");
		}

        //Cria o filtro para atividade
        FiltroTabelaAuxiliarFaixa filtroTabelaAuxiliarFaixa = new FiltroTabelaAuxiliarFaixa();

        //Adiciona o parâmetro no filtro
        filtroTabelaAuxiliarFaixa.adicionarParametro(new ParametroSimples(
                FiltroTabelaAuxiliarFaixa.ID, id));

        //String com path do pacote mais o nome do objeto
        String pacoteNomeObjeto = (String) sessao
                .getAttribute("pacoteNomeObjeto");

        //Pesquisa a tabela auxiliar
        Collection tabelasAuxiliaresFaixas = fachada.pesquisarTabelaAuxiliar(
                filtroTabelaAuxiliarFaixa, pacoteNomeObjeto);

        //Caso a coleção esteja vazia, indica erro inesperado
        if (tabelasAuxiliaresFaixas == null
                || tabelasAuxiliaresFaixas.isEmpty()) {
            throw new ActionServletException("erro.sistema");
        }

        Iterator iteratorTabelaAuxiliarFaixa = tabelasAuxiliaresFaixas
                .iterator();

        //A tabela auxiliar faixa que será atualizada
        TabelaAuxiliarFaixa tabelaAuxiliarFaixa = (TabelaAuxiliarFaixa) iteratorTabelaAuxiliarFaixa
                .next();

        //Manda a tabela auxiliar na sessão
        sessao.setAttribute("tabelaAuxiliarFaixa", tabelaAuxiliarFaixa);

        //Devolve o mapeamento de retorno
        return retorno;
    }

}

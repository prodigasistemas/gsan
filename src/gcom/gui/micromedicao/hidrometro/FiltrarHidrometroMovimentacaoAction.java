package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarHidrometroMovimentacaoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("filtrarHidrometroMovimentacao");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //HidrometroActionForm HidrometroActionForm = (HidrometroActionForm) actionForm;

        FiltroHidrometro filtroHidrometro = new FiltroHidrometro();

        // Local para recebimento dos parâmetros da pesquisa

        //boolean peloMenosUmParametroInformado = false;
        boolean peloMenosUmParametroInformado = true;

        // Local para adicionar os parametros recebidos no objeto filtro

        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }

        //Retorna Hidrometro
        Collection colecaoPesquisa = fachada.pesquisar(filtroHidrometro,
                Hidrometro.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Hidrometro nao encontrado
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Hidrômetro");
        } else {
            if (colecaoPesquisa.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
                //O limite de registros foi ultrapassado
                throw new ActionServletException(
                        "atencao.pesquisa.muitosregistros");
            } else {
                sessao.setAttribute("colecaoHidrometro", colecaoPesquisa);
            }
        }

        //devolve o mapeamento de retorno
        return retorno;
    }

}

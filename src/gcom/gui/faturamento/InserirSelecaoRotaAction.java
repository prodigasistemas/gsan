package gcom.gui.faturamento;

import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class InserirSelecaoRotaAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirSelecionarRota");

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Formulário de pesquisa
        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        if (sessao.getAttribute("colecaoRotasSelecionadas") != null) {

            // Coleção retornada pela pesquisa
            Collection colecaoRotasSelecionadas = (Collection) sessao
                    .getAttribute("colecaoRotasSelecionadas");

            // Coleção que irá ser gerada a partir da seleção efetuada pelo
            // usuário
            List colecaoRotasSelecionadasUsuario = new Vector();

            if (pesquisarActionForm.get("idRotaSelecao") != null) {

                Iterator colecaoRotasSelecionadasIterator;

                Rota rotaInserir;

                int indexArray = 0;
                Integer rotaID;

                // Rotas selecionadas pelo usuário
                String[] rotasSelecionadas = (String[]) pesquisarActionForm
                        .get("idRotaSelecao");

                while (rotasSelecionadas.length > indexArray) {
                    rotaID = new Integer(rotasSelecionadas[indexArray]);

                    colecaoRotasSelecionadasIterator = colecaoRotasSelecionadas
                            .iterator();

                    while (colecaoRotasSelecionadasIterator.hasNext()) {

                        rotaInserir = (Rota) colecaoRotasSelecionadasIterator
                                .next();

                        if (rotaInserir.getId().equals(rotaID)) {
                            colecaoRotasSelecionadasUsuario.add(rotaInserir);
                            break;
                        }
                    }

                    indexArray++;
                }

                // A coleção pode ser acumulativa ou está sendo gerada pela
                // primeira vez
                if (sessao.getAttribute("colecaoRotasSelecionadasUsuario") != null) {
                    Collection colecaoRotasSelecionadasUsuarioSessao = (Collection) sessao
                            .getAttribute("colecaoRotasSelecionadasUsuario");

                    colecaoRotasSelecionadasUsuarioSessao
                            .addAll(colecaoRotasSelecionadasUsuario);
                } else {
                    sessao.setAttribute("colecaoRotasSelecionadasUsuario",
                            colecaoRotasSelecionadasUsuario);
                }

                // Remove a coleção gerada pela pesquisa efetuada pelo usuário
                sessao.removeAttribute("colecaoRotasSelecionadas");

                // Flag para indicar o retorno para o caso de uso que chamou a
                // funcionalidade
                httpServletRequest.setAttribute("retornarUseCase", "OK");
            }

        }

        //devolve o mapeamento de retorno
        return retorno;
    }

}

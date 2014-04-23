package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

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
public class AtualizarTarifaSocialCartaoTipoAction extends GcomAction {
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

        //Obtém o action form
        TarifaSocialCartaoTipoActionForm tarifaSocialCartaoTipoActionForm = (TarifaSocialCartaoTipoActionForm) actionForm;

        //Define a ação de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém a fachada
        Fachada fachada = Fachada.getInstancia();

        TarifaSocialCartaoTipo tarifaSocialCartaoTipo = (TarifaSocialCartaoTipo) sessao
                .getAttribute("tarifaSocialCartaoTipo");

        //Obtém a descrição
        String descricao = (String) tarifaSocialCartaoTipoActionForm
                .getDescricao();
        //Obtém a descrição abreviada
        String descricaoAbreviada = (String) tarifaSocialCartaoTipoActionForm
                .getDescricaoAbreviada();
        //Obtém o indicador de existência de validade
        String validade = (String) tarifaSocialCartaoTipoActionForm
                .getValidade();

        //Obtém e converte o indicador de uso
        Short indicadorDeUso = new Short(tarifaSocialCartaoTipoActionForm
                .getIndicadorUso());

        Short numeroMaximoMeses = null;

        if (!tarifaSocialCartaoTipoActionForm.getNumeroMaximoMeses().equals("")) {
            numeroMaximoMeses = new Short(tarifaSocialCartaoTipoActionForm
                    .getNumeroMaximoMeses());
            
            if (numeroMaximoMeses.intValue() == 0
					|| numeroMaximoMeses.intValue() > 99) {
				throw new ActionServletException(
						"atencao.numero.meses.fora.faixa.permitido");
			}
            
        }

        //Seta os campos para serem atualizados
        tarifaSocialCartaoTipo.setDescricao(descricao);
        tarifaSocialCartaoTipo.setDescricaoAbreviada(descricaoAbreviada);
        tarifaSocialCartaoTipo.setIndicadorValidade(new Short(validade));
        tarifaSocialCartaoTipo.setNumeroMesesAdesao(numeroMaximoMeses);
        tarifaSocialCartaoTipo.setIndicadorUso(indicadorDeUso);

        fachada.atualizarTarifaSocialCartaoTipo(tarifaSocialCartaoTipo);

        montarPaginaSucesso(httpServletRequest,
                "Tipo de Cartão da Tarifa Social de código  "
                        + tarifaSocialCartaoTipo.getId()
                        + " atualizado com sucesso.",
                "Realizar outra Manutenção de Tipo de Cartão da Tarifa Social",
                "filtrarTarifaSocialCartaoTipoAction.do?menu=sim");

        sessao.removeAttribute("TarifaSocialCartaoTipoActionForm");

        return retorno;
    }
}

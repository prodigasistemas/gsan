package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o tipo SistemaAbastecimento>
 * 
 * @author Administrador
 */
public class AtualizarTabelaAuxiliarAbreviadaTipoAction extends GcomAction {
    /**
     * <Descrição do Método>
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
        TabelaAuxiliarAbreviadaTipoActionForm tabelaAuxiliarAbreviadaTipoActionForm = (TabelaAuxiliarAbreviadaTipoActionForm) actionForm;

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Recupera o ponto de coleta da sessão
        TabelaAuxiliarAbreviadaTipo tabelaAuxiliarAbreviadaTipo = (TabelaAuxiliarAbreviadaTipo) sessao
                .getAttribute("tabelaAuxiliarAbreviadaTipo");

        //Atualiza a tabela auxiliar com os dados inseridos pelo usuário
        //A data de última alteração não é alterada, pois será usada na
        //verificação de atualização
        tabelaAuxiliarAbreviadaTipo.setDescricao(tabelaAuxiliarAbreviadaTipoActionForm
                .getDescricao().toUpperCase());
        tabelaAuxiliarAbreviadaTipo.setDescricaoAbreviada(tabelaAuxiliarAbreviadaTipoActionForm
                .getDescricaoAbreviada().toUpperCase());
        
        
        if(tabelaAuxiliarAbreviadaTipoActionForm.getIndicadorUso()!=null){
        	tabelaAuxiliarAbreviadaTipo.setIndicadorUso(new Short(tabelaAuxiliarAbreviadaTipoActionForm.getIndicadorUso()));
        }
        if (tabelaAuxiliarAbreviadaTipoActionForm.getSistemaAbastecimento() != null
				&& !tabelaAuxiliarAbreviadaTipoActionForm
						.getSistemaAbastecimento().equals("")
				&& !tabelaAuxiliarAbreviadaTipoActionForm
						.getSistemaAbastecimento().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
        	SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();	
        	sistemaAbastecimento.setId(new Integer(tabelaAuxiliarAbreviadaTipoActionForm.getSistemaAbastecimento()));
        	tabelaAuxiliarAbreviadaTipo.setSistemaAbastecimento(sistemaAbastecimento);
        } else {
        	tabelaAuxiliarAbreviadaTipo.setSistemaAbastecimento(null);
        }

        //Atualiza os dados
        fachada.atualizarTabelaAuxiliar(tabelaAuxiliarAbreviadaTipo);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

            montarPaginaSucesso(
                    httpServletRequest,
                    ((String) sessao.getAttribute("titulo")) + " " + tabelaAuxiliarAbreviadaTipo.getId().toString()
                            + " atualizado(a) com sucesso.",
                    "Realizar outra manutenção de "
                            + ((String) sessao.getAttribute("titulo")),
                    ((String) sessao
                            .getAttribute("funcionalidadeTabelaAuxiliarAbreviadaTipoFiltrar"))+"&menu=sim");

        }

        //Remove os objetos da sessão
        sessao.removeAttribute("funcionalidadeTabelaAuxiliarAbreviadaTipoManter");
        sessao.removeAttribute("titulo");
        sessao.removeAttribute("tabelaAuxiliarAbreviadaTipo");
        sessao.removeAttribute("tamMaxCampoDescricao");
        sessao.removeAttribute("tamMaxCampoDescricaoAbreviada");
        sessao.removeAttribute("descricao");
        sessao.removeAttribute("descricaoAbreviada");
        sessao.removeAttribute("tituloTipo");
        sessao.removeAttribute("tabelaAuxiliarTipo");
        sessao.removeAttribute("tabelasAuxiliaresTipos");
        sessao.removeAttribute("totalRegistros");
        sessao.removeAttribute("tipo");
        

        //devolve o mapeamento de retorno
        return retorno;
    }

}

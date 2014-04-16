package gcom.gui.cadastro.localidade;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AlterarSetorComercialAction extends GcomAction {

    //Obtém a instância da fachada
    Fachada fachada = Fachada.getInstancia();

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        PesquisarAtualizarSetorComercialActionForm pesquisarAtualizarSetorComercialActionForm = (PesquisarAtualizarSetorComercialActionForm) actionForm;

        String setorComercialID = pesquisarAtualizarSetorComercialActionForm
                .getSetorComercialID();
        String localidadeID = pesquisarAtualizarSetorComercialActionForm
                .getLocalidadeID();
        String setorComercialCD = pesquisarAtualizarSetorComercialActionForm
                .getSetorComercialCD();
        String setorComercialNome = pesquisarAtualizarSetorComercialActionForm
                .getSetorComercialNome();
        String municipioID = pesquisarAtualizarSetorComercialActionForm
                .getMunicipioID();
        String indicadorUso = pesquisarAtualizarSetorComercialActionForm
                .getIndicadorUso();

        if (setorComercialID == null || setorComercialID.equalsIgnoreCase("")) {
            throw new ActionServletException(
                    "atencao.setor_comercial_nao_informado");
        } else if (localidadeID == null || localidadeID.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.localidade_nao_informada");
        } else if (setorComercialCD == null
                || setorComercialCD.equalsIgnoreCase("")) {
            throw new ActionServletException(
                    "atencao.codigo_setor_comercial_nao_informado");
        } else if (municipioID == null || municipioID.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.municipio_nao_informado");
        } else if (setorComercialNome == null
                || setorComercialNome.equalsIgnoreCase("")) {
            throw new ActionServletException(
                    "atencao.nome_setor_comercial_nao_informado");
        } else if (indicadorUso == null || indicadorUso.equalsIgnoreCase("")) {
            throw new ActionServletException(
                    "atencao.indicador_uso_nao_informado");
        }

        else {

            //=====================================================================
            //Validando os dados informados pelo usuário.

            Municipio municipioNovo = (Municipio) pesquisarObjeto(municipioID,
                    3);
            if (municipioNovo == null) {
                throw new ActionServletException(
                        "atencao.pesquisa.municipio_inexistente");
            }

            Short indicadorUsoNovo = new Short(indicadorUso);

            //======================================================================

            SetorComercial setorComercialAtual = (SetorComercial) sessao.getAttribute("setorComercialManter");

            if (setorComercialAtual == null) {
                //Setor comercial nao encontrado
                throw new ActionServletException(
                        "atencao.processo.setorComercialNaoCadastrada");
            }
			setorComercialAtual.setDescricao(setorComercialNome);
			setorComercialAtual.setMunicipio(municipioNovo);
			setorComercialAtual.setIndicadorUso(indicadorUsoNovo);
			
			fachada.atualizarSetorComercial(setorComercialAtual,null);

			montarPaginaSucesso(httpServletRequest,
			        "Setor comercial de código  "
			                + setorComercialAtual.getCodigo()
			                + "  atualizado com sucesso.",
			        " Realizar outra manutenção de setor comercial",
			        "exibirAtualizarSetorComercialAction.do");
        }

        //devolve o mapeamento de retorno
        return retorno;
    }

    /**
     * 
     * @param objetoPesquisa
     * @param objetoPai
     * @param tipoObjeto
     * @return
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private Object pesquisarObjeto(String objetoPesquisa, int tipoObjeto) {

        Object retorno = null;
        Collection colecaoPesquisa = null;

        switch (tipoObjeto) {
        //Setor Comercial
        case 2:

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID, objetoPesquisa));

            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
                retorno = Util.retonarObjetoDeColecao(colecaoPesquisa);
            }

            break;

        case 3:

            FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.ID, objetoPesquisa));

            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroMunicipio,
                    Municipio.class.getName());

            if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
                retorno = Util.retonarObjetoDeColecao(colecaoPesquisa);
            }

            break;

        default:
            break;
        }

        return retorno;
    }

}

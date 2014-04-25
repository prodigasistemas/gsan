package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirImovelPrincipalAction extends GcomAction {

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

        ActionForward retorno = actionMapping
                .findForward("inserirImovelPrincipal");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm inserirImovelPrincipalActionForm = (DynaValidatorForm) sessao
                .getAttribute("InserirImovelActionForm");

        String pesquisar = httpServletRequest.getParameter("pesquisar");
        
        //Cria variaveis
        String idImovel = (String) inserirImovelPrincipalActionForm
                .get("idImovel");

        //Cria Filtros
        FiltroImovel filtroImovel = new FiltroImovel();
        
        //Objetos que serão retornados pelo Hibernate
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
        
        filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,
				Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR,2));

        filtroImovel.adicionarParametro(new ParametroNulo(
				FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));
        

        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();

        Collection imoveis = null;//new HashSet();

        //       sessao.setAttribute("imoveisPrincipal", imoveis);

        if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
            filtroImovel.adicionarParametro(new ParametroSimples(
                    FiltroImovel.ID, new Integer(idImovel.trim())));

            imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            if (imoveis != null && !imoveis.isEmpty()) {
                /*
                 * inserirImovelPrincipalActionForm.set("idImovel", ((Imovel)
                 * ((List) imoveis).get(0)).getId().toString());
                 * inserirImovelPrincipalActionForm.set("descricaoImovel",
                 * ((Imovel) ((List) imoveis).get(0)).getNumeroImovel());
                 * inserirImovelPrincipalActionForm.set("tipoLogradouro",
                 * ((Imovel) ((List)
                 * imoveis).get(0)).getLogradouro().getLogradouroTipo().getDescricao());
                 * inserirImovelPrincipalActionForm.set("tituloLogradouro",
                 * ((Imovel) ((List)
                 * imoveis).get(0)).getLogradouro().getLogradouroTitulo().getDescricao());
                 * inserirImovelPrincipalActionForm.set("logradouro", ((Imovel)
                 * ((List) imoveis).get(0)).getLogradouro().getNome());
                 * inserirImovelPrincipalActionForm.set("municipio", ((Imovel)
                 * ((List)
                 * imoveis).get(0)).getLogradouro().getMunicipio().getNome());
                 * inserirImovelPrincipalActionForm.set("bairro", ((Imovel)
                 * ((List) imoveis).get(0)).getQuadra().getBairro().getNome());
                 */
                sessao.setAttribute("imoveisPrincipal", imoveis);
            } else {
            	if(pesquisar != null && !pesquisar.equalsIgnoreCase("")){
            		throw new ActionServletException("atencao.pesquisa.imovel.inexistente", null,
            				idImovel);
            	}
            }

        }

        return retorno;
    }

}

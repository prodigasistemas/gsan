package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

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
 * @author rodrigo
 */
public class InserirTarifaSocialImovelAction extends GcomAction {

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
                .findForward("gerenciadorProcesso");

        HttpSession sessao = httpServletRequest.getSession(false);

        //Instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        DynaValidatorForm inserirTarifaSocialActionForm = (DynaValidatorForm) actionForm;

        String idImovel = (String) inserirTarifaSocialActionForm
                .get("idImovel");

        if (idImovel != null && !idImovel.equals("")) {

            //Filtro para pesquisa dos imóveis
            FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

            //Filtro para pesquisa de subcategorias para calculo de economias
            //FiltroImovelSubCategoria filtroImovelSubcategoria = new
            // FiltroImovelSubCategoria();

            //Adiciona os parametros para pesquisa de imovel
            filtroClienteImovel.adicionarParametro(new ParametroSimples(
                    FiltroClienteImovel.IMOVEL_ID, idImovel));
            filtroClienteImovel.setInitializeLazy(true);
            //Objetos que serão retornados pelo Hibernate
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
            filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");

            //Adicionar os parametros para pesquisa de economias
            //filtroImovelSubcategoria.adicionarParametro(new
            // ParametroSimples(FiltroImovelSubCategoria.IMOVEL_ID, idImovel));

            //filtroImovelSubcategoria.adicionarColecaoParaSerCarregada("imovelEconomias");

            //Pesquisa e monta uma coleção de cliente imóveis
            Collection clienteImoveis = fachada.pesquisar(filtroClienteImovel,
                    ClienteImovel.class.getName());

            //Pesquisa e monta uma coleção de imóveis
            //Collection imovelSubcategorias =
            // fachada.pesquisar(filtroImovelSubcategoria,
            // ImovelSubcategoria.class.getName());

            ClienteImovel clienteImovelObj = null;

            //ImovelSubcategoria imovelSubcategoriaObj = new
            // ImovelSubcategoria();
            //Collection imovelEconomias = null;

            //Verifica se o resultado da busca de imóvel foi vazia
            if (clienteImoveis == null || clienteImoveis.isEmpty()) {

                throw new ActionServletException(
                        "atencao.pesquisa.nenhumresultado", null, "imovel");
            } else {
                //Verifica se o imóvel já está com a tarifa social

                clienteImovelObj = (ClienteImovel) clienteImoveis.iterator()
                        .next();

                if (clienteImovelObj.getImovel().getImovelPerfil() != null &&
                	clienteImovelObj.getImovel().getImovelPerfil().getId()
                    .equals(ImovelPerfil.TARIFA_SOCIAL)) {

                    throw new ActionServletException("atencao.imovel.ja_tarifasocial");
                }
            }

            //Obter quantidade de economias do imóvel para determinar qual o
            // próximo caso de uso
            //será executado
            //[UC0065 - Inserir dados tarifa social - Uma economia]
            //[UC0066 - Inserir dados tarifa social - Mais de uma economia]
            Imovel imovel = new Imovel();

            imovel.setId(new Integer(idImovel));

            int quantidadeEconomiasImovel = fachada
                    .obterQuantidadeEconomias(imovel);

            if (!(quantidadeEconomiasImovel > 0)) {
                //O imovel não possui nenhuma economia
                throw new ActionServletException(
                        "atencao.imovel.economias_nao_cadastradas");
            }

            //manda o clienteimovel para sessao para JSP carregar seus dados
            //sessao.setAttribute("clienteImovel", clienteImovelObj);

            //manda a coleção de Imovel Economia para sessão
            //sessao.setAttribute("colecaoImovelEconomia", imovelEconomias);
            // observar isso, ta indo so o ultimo

            //manda a coleção de Imovel Subcategoria para sessão
            //sessao.setAttribute("colecaoImovelSubcategoria",
            // imovelSubcategorias);

            //atualiza a quantidade de economias do ActionForm
            inserirTarifaSocialActionForm.set("qtdEconomia", new Integer(
                    quantidadeEconomiasImovel));
            
            //Coloca o imovel na sessao
            sessao.setAttribute("imovelTarifa", clienteImovelObj.getImovel());

        }

        return retorno;
    }
}

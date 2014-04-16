package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelEconomia;
import gcom.cadastro.imovel.ImovelEconomia;
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


/**
 * Action ExibirImovelEconomiaRelacaoClienteImovelAction
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class ExibirImovelEconomiaRelacaoClienteImovelAction extends GcomAction {

    /**
     * Método responsavel por responder a requisicao
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
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibir");

        ConsultarRelacaoClienteImovelActionForm form = (ConsultarRelacaoClienteImovelActionForm) actionForm;

        HttpSession sessao = request.getSession(false);
        sessao.removeAttribute("imovel");
        sessao.removeAttribute("collClienteImovel");
        sessao.removeAttribute("collImovelSubCategoriaHelper");
        sessao.removeAttribute("cliente");
        sessao.removeAttribute("collClienteImovelEconomia");

        if (form.getIdImovelEconomia() == null || "".equals(form.getIdImovelEconomia())) {
	    	throw new ActionServletException("erro.parametro.nao.informado", null, "idImovelEconomia");
	    }
        
        FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();
        filtroImovelEconomia.adicionarParametro( new ParametroSimples(FiltroImovelEconomia.ID, form.getIdImovelEconomia()));
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL_CATEGORIA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL_SUB_CATEGORIA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.AREA_CONSTRUIDA_FAIXA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOCALIDADE);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.SETOR_COMERCIAL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.QUADRA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TIPO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TITULO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.BAIRRO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.SETOR_COMERCIAL_MUNICIPIO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.SETOR_COMERCIAL_MUNICIPIO_UF);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.MUNICIPIO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.CEP);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.UNIDADE_FEDERACAO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LIGACAO_AGUA_SITUACAO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.ENDERECO_REFERENCIA);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_INICIAL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TITULO_PERIMETRO_INICIAL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_FINAL);
        filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL + "." + FiltroImovel.LOGRADOURO_TITULO_PERIMETRO_FINAL);
        
        Collection coll = Fachada.getInstancia().pesquisar(filtroImovelEconomia,ImovelEconomia.class.getSimpleName());
        if (coll != null && !coll.isEmpty()) {
        	ImovelEconomia imovelEconomia = (ImovelEconomia) coll.iterator().next();
        	sessao.setAttribute("imovelEconomia",imovelEconomia);

			// consulta os ClienteImovelEconomia que pertenca ao imovelSubCategoria corrente ( id do imovel e id da subcategoria)
        	FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
        	filtroClienteImovelEconomia.adicionarParametro( new ParametroSimples(FiltroClienteImovelEconomia.IMOVEL_ECONOMIA_ID, form.getIdImovelEconomia()));
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIA);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_AREA_CONSTRUIDA_FAIXA);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_IMOVEL_SUB_CATEGORIA);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_IMOVEL_CATEGORIA);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL);
        	filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");
        	Collection collClienteImovelEconomia = Fachada.getInstancia().pesquisar(filtroClienteImovelEconomia, ClienteImovelEconomia.class.getSimpleName());
        	sessao.setAttribute("collClienteImovelEconomia",collClienteImovelEconomia);

        } else {
        	throw new ActionServletException("atencao.naocadastrado", null, "ImovelEconomia");
        }	

        return retorno;
    }
}

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
public class ExibirAtualizarImovelPrincipalAction extends GcomAction {

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
                .findForward("atualizarImovelPrincipal");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm atualizarImovelPrincipalActionForm = (DynaValidatorForm) actionForm;

        String pesquisar = httpServletRequest.getParameter("pesquisar");

        //Cria variaveis
        String idImovel = (String) atualizarImovelPrincipalActionForm
                .get("idImovel");

        
        if(pesquisar != null && !pesquisar.equalsIgnoreCase("")){
	        
	        //Cria variaveis
	        //Cria Filtros
	        FiltroImovel filtroImovel = new FiltroImovel();
	        
	        //Objetos que serão retornados pelo Hibernate
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal");
	        
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.quadra");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroBairro.bairro.municipio.unidadeFederacao");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.cep");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTipo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.enderecoReferencia");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.setorComercial");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.imovelPrincipal");
	        
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
	                    FiltroImovel.ID, new Integer(idImovel)));
	
	            Imovel imovelAtualizado = (Imovel)sessao.getAttribute("imovelAtualizacao");
	            
	            //testa se o imovel a ser principal é o mesmo que esta sendo atualizado
	            if(idImovel.equals(imovelAtualizado.getId().toString())){
	            	//atualizarImovelPrincipalActionForm.set("idImovel","");
	            	throw new ActionServletException("atencao.imovel.principal.igual.atualizado");
	            }
	            
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
	            	sessao.setAttribute("idImoveilPrincipal", ((Imovel)imoveis.iterator().next()).getId().toString());
	                sessao.setAttribute("imoveisPrincipal", imoveis);
	                atualizarImovelPrincipalActionForm.set("idImovel",idImovel);
	            } else {
	            	if(pesquisar != null && !pesquisar.equalsIgnoreCase("")){
	            		throw new ActionServletException("atencao.pesquisa.imovel.inexistente",null, idImovel);
	            	}
	            }
	
	        }
        }else{
        	 atualizarImovelPrincipalActionForm.set("idImovel",idImovel);
        }

        return retorno;
    }

}

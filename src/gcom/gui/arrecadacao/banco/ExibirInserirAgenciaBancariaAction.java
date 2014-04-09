package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite Inserir uma Agencia Bancaria
 *
 * @author Thiago Tenório
 * @date 07/02/2007
 */
public class ExibirInserirAgenciaBancariaAction extends GcomAction {


    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirAgenciaBancaria");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        InserirAgenciaBancariaActionForm inserirAgenciaBancariaActionForm = (InserirAgenciaBancariaActionForm) actionForm;

        String limparForm = (String) httpServletRequest
                .getParameter("limparCampos");
        String removerEndereco = (String) httpServletRequest
                .getParameter("removerEndereco");

        Collection colecaoPesquisa = null;
        
        
        FiltroBanco filtroBanco = new FiltroBanco();

        filtroBanco.setCampoOrderBy(FiltroBanco.NOME_BANCO);
        
        filtroBanco.adicionarParametro(new ParametroSimples(
                FiltroBanco.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna banco
        colecaoPesquisa = fachada.pesquisar(filtroBanco,
                Banco.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Nenhum registro na tabela localidade_porte foi encontrado
            throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "Banco");
        } else {
            sessao.setAttribute("colecaoPorte", colecaoPesquisa);
        }
    

        
        //Constrói filtro para pesquisa do banco
		filtroBanco.setCampoOrderBy(FiltroBanco.ID);
		filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		sessao.setAttribute("colecaoBanco", fachada.pesquisar(filtroBanco, Banco.class.getName(), "BANCO"));

		httpServletRequest.setAttribute("colecaoBanco", colecaoPesquisa);
        
        if ((limparForm == null || limparForm.trim().equalsIgnoreCase("")) ||
        		(httpServletRequest.getParameter("desfazer") != null
                        && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))) {
        	//-------------- bt DESFAZER ---------------
        	

        	
            //Limpando o formulario
			inserirAgenciaBancariaActionForm.setEmail("");
			inserirAgenciaBancariaActionForm.setFax("");
			inserirAgenciaBancariaActionForm.setNome("");
			inserirAgenciaBancariaActionForm.setBancoID("");
			inserirAgenciaBancariaActionForm.setRamal("");
			inserirAgenciaBancariaActionForm.setTelefone("");

            //Limpa o endereço da sessão
            if (sessao.getAttribute("colecaoEnderecos") != null) {
                sessao.removeAttribute("colecaoEnderecos");
            }
            sessao.removeAttribute("tipoPesquisaRetorno");
        }

        //Remove o endereco informado.
        if (removerEndereco != null
                && !removerEndereco.trim().equalsIgnoreCase("")) {

            if (sessao.getAttribute("colecaoEnderecos") != null) {

                Collection enderecos = (Collection) sessao
                        .getAttribute("colecaoEnderecos");
                if (!enderecos.isEmpty()) {
                    enderecos.remove(enderecos.iterator().next());
                }

            }

        }

        //devolve o mapeamento de retorno
        return retorno;
    }

}

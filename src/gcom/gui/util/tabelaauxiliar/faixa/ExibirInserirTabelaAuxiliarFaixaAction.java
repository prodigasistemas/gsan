package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.gui.GcomAction;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixa;

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
public class ExibirInserirTabelaAuxiliarFaixaAction extends GcomAction {
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

        //Prepara o retorno da Ação
        ActionForward retorno = actionMapping
                .findForward("inserirTabelaAuxiliarFaixa");

        //Pega o parametro passado no request
        //String tela = (String) httpServletRequest.getParameter("tela");

        //Declaração de objetos e tipos primitivos
        String titulo = null;
        int tamMaxCampoFaixaInicial = 3;
        int tamMaxCampoFaixaFinal = 3;
        TabelaAuxiliarFaixa tabelaAuxiliarFaixa = null;
        String funcionalidadeTabelaAuxiliarFaixaInserir = null;

        //********BLOCO DE CÓDIGO PARA DEFINIÇÃO DOS CADASTROS PERTENCENTES A
        // INSERIR TABELA AUXILIAR FAIXA******//
        //        Para serem incluidos novos cadastros com código, faixa inicial e
        // faixa final basta apenas cria um novo
        //        if (condicional) semelhante ao exemplo abaixo, informando apenas os
        // dados relativos
        //        ao objeto desejado.

        //Identifica a string do objeto passado no get do request
        /*
         * if (tela.equals("areaConstruida")) { //Título a ser exido nas páginas
         * titulo = "Área Construída"; //Cria o objeto AreaConstruida
         * areaConstruida = new AreaConstruida(); //Associa o objeto tabela
         * auxiliar ao tipo criado tabelaAuxiliarFaixa = areaConstruida;
         * //Define o link a ser exibido na página de sucesso do inserir
         * funcionalidadeTabelaAuxiliarFaixaInserir =
         * Funcionalidade.TABELA_AUXILIAR_FAIXA_INSERIR +
         * Funcionalidade.TELA_AREA_CONSTRUIDA; //Obtém o tamanho da propriedade
         * da classe de acordo com length do mapeamento tamMaxCampoFaixaInicial =
         * HibernateUtil.getColumnSize(AreaConstruida.class,"faixaInicial");
         * tamMaxCampoFaixaFinal =
         * HibernateUtil.getColumnSize(AreaConstruida.class,"faixaFinal"); }
         */

        //********FIM DO BLOCO DE CÓDIGO*******//
        //Cria a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //tempo da sessão
        sessao.setMaxInactiveInterval(1000);
        //Adiciona os objetos na sessão
        sessao.setAttribute("tabelaAuxiliarFaixa", tabelaAuxiliarFaixa);
        sessao.setAttribute("funcionalidadeTabelaAuxiliarFaixaInserir",
                funcionalidadeTabelaAuxiliarFaixaInserir);
        sessao.setAttribute("titulo", titulo);
        //Adiciona os objetos no request
        httpServletRequest.setAttribute("tamMaxCampoFaixaInicial", new Integer(
                tamMaxCampoFaixaInicial));
        httpServletRequest.setAttribute("tamMaxCampoFaixaFinal", new Integer(
                tamMaxCampoFaixaFinal));

        return retorno;
    }

}

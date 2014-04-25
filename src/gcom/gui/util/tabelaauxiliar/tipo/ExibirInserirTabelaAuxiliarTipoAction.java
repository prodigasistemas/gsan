package gcom.gui.util.tabelaauxiliar.tipo;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;
import gcom.util.tabelaauxiliar.tipo.TabelaAuxiliarTipo;

import java.util.Collection;

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
public class ExibirInserirTabelaAuxiliarTipoAction extends GcomAction {
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
                .findForward("inserirTabelaAuxiliarTipo");

        Fachada fachada = Fachada.getInstancia();

        //Pega o parametro passado no request
        //String tela = (String) httpServletRequest.getParameter("tela");

        //Declaração de objetos e tipos primitivos
        String titulo = null;
        String tituloTipo = null;
        int tamMaxCampoDescricao = 40;
        String funcionalidadeTabelaAuxiliarTipoInserir = null;
        Collection tipos = null;
        TabelaAuxiliarTipo tabelaAuxiliarTipo = null;
        FiltroTabelaAuxiliar filtroTabelaAuxiliar = null;
        String pacoteNomeObjeto = null;

        //********BLOCO DE CÓDIGO PARA DEFINIÇÃO DOS CADASTROS PERTENCENTES A
        // INSERIR TABELA AUXILIAR TIPO******//
        //        Para serem incluidos novos cadastros com código, descrição e tipo
        // basta apenas cria um novo
        //        if (condicional) semelhante ao exemplo abaixo, informando apenas os
        // dados relativos
        //        ao objeto desejado.

        //Identifica a string do objeto passado no get do request
        /*
         * if (tela.equals("bacia")) { //Título a ser exido nas páginas titulo =
         * "Bacia"; tituloTipo = "Tipo Pavimento Rua"; //Cria o filtro referente
         * ao objeto tipo filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();
         * //Cria o objeto principal Bacia bacia = new Bacia(); //Cria o objeto
         * tipo TipoPavimentoRua tipoPavimentoRua = new TipoPavimentoRua();
         * //Associa o objeto tabela auxiliar tipo ao tipo criado
         * tabelaAuxiliarTipo = bacia; //Pega o path do pacote mais o nome da
         * classe pacoteNomeObjeto = tipoPavimentoRua.getClass().getName();
         * //Define o link a ser exibido na página de sucesso do inserir
         * funcionalidadeTabelaAuxiliarTipoInserir =
         * Funcionalidade.TABELA_AUXILIAR_TIPO_INSERIR +
         * Funcionalidade.TELA_BACIA; //Obtém o tamanho da propriedade da classe
         * de acordo com length do mapeamento tamMaxCampoDescricao =
         * HibernateUtil.getColumnSize(Bacia.class,"descricao"); }
         */
        //********FIM DO BLOCO DE CÓDIGO*******//
        tipos = fachada.pesquisarTabelaAuxiliar(filtroTabelaAuxiliar,
                pacoteNomeObjeto);

        //Caso a coleção esteja vazia, indica erro inesperado
        if (tipos == null || tipos.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado");
        }

        //Cria a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //tempo da sessão
        sessao.setMaxInactiveInterval(1000);
        //Adiciona os objetos na sessão
        sessao.setAttribute("tabelaAuxiliarTipo", tabelaAuxiliarTipo);
        sessao.setAttribute("funcionalidadeTabelaAuxiliarTipoInserir",
                funcionalidadeTabelaAuxiliarTipoInserir);
        sessao.setAttribute("titulo", titulo);
        sessao.setAttribute("tituloTipo", tituloTipo);
        //Adiciona o objeto no request
        httpServletRequest.setAttribute("tamMaxCampoDescricao", new Integer(
                tamMaxCampoDescricao));
        httpServletRequest.setAttribute("tipos", tipos);

        return retorno;
    }

}

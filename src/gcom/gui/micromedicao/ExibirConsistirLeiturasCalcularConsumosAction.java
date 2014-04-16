package gcom.gui.micromedicao;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;

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
public class ExibirConsistirLeiturasCalcularConsumosAction extends GcomAction {
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

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("consistirLeiturasCalcularConsumos");

        //ConsistirLeiturasCalcularConsumosActionForm consistirLeiturasCalcularConsumosActionForm = (ConsistirLeiturasCalcularConsumosActionForm) actionForm;

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        String confirmacao = (String) httpServletRequest.getParameter("sim");

        if (confirmacao != null && confirmacao.equals("sim")) {

            //Filtro Sistema parâmetro
            FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
            //Pesquisa os paramêtros do sistema
            Collection colecaoSistemaParametro = fachada.pesquisar(
                    filtroSistemaParametro, SistemaParametro.class.getName());
            SistemaParametro sistemaParametro = null;

            if (colecaoSistemaParametro != null
                    && !colecaoSistemaParametro.equals("")) {
                sistemaParametro = (SistemaParametro) Util
                        .retonarObjetoDeColecao(colecaoSistemaParametro);
            }

            //Criação do filtro
            FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
            //Parâmetro que define o id do grupo de faturamento selecionado
           /* ParametroSimples parametroSimples = new ParametroSimples(
                    FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID,
                    consistirLeiturasCalcularConsumosActionForm
                            .getIdFaturamentoGrupo(),
                    FiltroParametro.CONECTOR_AND);*/

            //Parâmetro que define o mês de faturamento
            filtroFaturamentoAtividadeCronograma
                    .adicionarParametro(new ParametroSimples(
                            FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
                            new Integer(sistemaParametro.getAnoMesArrecadacao()),
                            FiltroParametro.CONECTOR_AND));
            //Parâmetro que define o faturamento atividade registrar leitura e
            // anormalidade
            filtroFaturamentoAtividadeCronograma
                    .adicionarParametro(new ParametroSimples(
                            FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID,
                            FaturamentoAtividade.REGISTRAR_LEITURA_ANORMALIDADE));

            //Pesquisar faturamento atividade cronograma
            Collection colecaoFaturamentoAtividadeCronograma = fachada
                    .pesquisar(filtroFaturamentoAtividadeCronograma,
                            FaturamentoAtividadeCronograma.class.getName());

            if (colecaoFaturamentoAtividadeCronograma != null
                    && !colecaoFaturamentoAtividadeCronograma.equals("")) {

                FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
                        .retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronograma);

                //Caso não tenha sido realizado a atividade de faturamento do
                // cronograma
                if (faturamentoAtividadeCronograma.getDataRealizacao() == null) {
                    httpServletRequest.setAttribute("confirmacao",
                            "confirmacao");
                } else {
                    //Foi realizado
                    httpServletRequest.removeAttribute("confirmacao");
                }
                sessao.setAttribute("sistemaParametro", sistemaParametro);
            } else {
                //Não existe nada cadastro na tabela de faturamento atividade
                // cronograma conforme os parâmetros passados
                throw new ActionServletException(
                        "atencao.inexistente.faturamento_atividade_cronograma");
            }
        }

        //Criação das coleções
        Collection faturamentoGrupos = null;

        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
                FiltroFaturamentoGrupo.DESCRICAO);
        /*ParametroSimples parametroSimples = new ParametroSimples(
                FiltroFaturamentoGrupo.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO);*/

        //Realiza as pesquisas
        faturamentoGrupos = fachada.pesquisar(filtroFaturamentoGrupo,
                FaturamentoGrupo.class.getName());

        if (faturamentoGrupos == null || faturamentoGrupos.isEmpty()) {
            //Nenhuma grupo de faturamento cadastrado
            throw new ActionServletException("erro.sistema");
            //reportarErrosMensagem(httpServletRequest, "erro.naocadastrado",
            // "grupo de faturamento");
        } else {
            //Envia os objetos no request
            httpServletRequest.setAttribute("faturamentoGrupos",
                    faturamentoGrupos);
        }

        return retorno;
    }
}

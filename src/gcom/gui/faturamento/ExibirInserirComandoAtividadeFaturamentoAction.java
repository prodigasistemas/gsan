package gcom.gui.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirComandoAtividadeFaturamentoAction extends GcomAction {

    /**
     * 
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirComandoAtividadeFaturamento");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        // Remove a coleção de rotas não habilitadas
        sessao.removeAttribute("colecaoRotasNaoHabilitadas");

        //Instância do formulário que está sendo utilizado
        InserirComandoAtividadeFaturamentoActionForm inserirComandoAtividadeFaturamentoActionForm = (InserirComandoAtividadeFaturamentoActionForm) actionForm;

        Collection colecaoFaturamentoGrupo;
        Collection colecaoAtividadeFaturamento;

        // Grupo de faturamento (Carregar coleção)
        if (sessao.getAttribute("colecaoGrupoFaturamento") == null) {

            colecaoFaturamentoGrupo = fachada.pesquisarFaturamentoGrupoComCronogramaMensalParaMesCorrente();

            sessao.setAttribute("colecaoGrupoFaturamento", colecaoFaturamentoGrupo);
            
        }

        
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        /*
         * Número mínimo de dias (PARM_NNMINIMODIASEMISSAOVENCIMENTO da tabela
         * SISTEMA_PARAMETROS)
         */
        
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

        if (sessao.getAttribute("dataCorrente") == null &&
            sistemaParametro != null) {

            if (sistemaParametro.getNumeroMinimoDiasEmissaoVencimento() != null) {
                Calendar dataCorrente = new GregorianCalendar();
                dataCorrente.add(Calendar.DATE, sistemaParametro
                .getNumeroMinimoDiasEmissaoVencimento().intValue());

                sessao.setAttribute("dataCorrente", formatoData
                .format(dataCorrente.getTime()));
            }
        }

        // Grupo selecionado
        String grupoFaturamentoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getGrupoFaturamentoID();
        // Atividade selecionado
        String atividadeFaturamentoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getAtividadeFaturamentoID();

        if (grupoFaturamentoJSP != null
                && !grupoFaturamentoJSP.equalsIgnoreCase("")
                && !grupoFaturamentoJSP.equalsIgnoreCase(String
                        .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

            colecaoFaturamentoGrupo = (Collection) sessao
                    .getAttribute("colecaoGrupoFaturamento");

            FaturamentoGrupo faturamentoGrupo = obterFaturamentoGrupoSelecionado(
            grupoFaturamentoJSP, colecaoFaturamentoGrupo);

            // [FS0003] - Verificar existência do cronograma para o grupo
            //fachada.verificarExistenciaCronogramaGrupo(faturamentoGrupo);

            // Atividade de faturamento (Carregar coleção)
            // [SB0001] - Selecionar atividade de faturamento
            colecaoAtividadeFaturamento = fachada
            .selecionarAtividadeFaturamentoQuePodeSerComandada(faturamentoGrupo);
            httpServletRequest.setAttribute("colecaoAtividadeFaturamento",
            colecaoAtividadeFaturamento);
        

            // O sistema apresenta na tela:

            //Referência Faturamento
            /*
             * A referência do faturamento (FTGR_AMREFERENCIA da tabela
             * FATURAMENTO_GRUPO para FTGR_ID=Grupo selecionado)
             */
            if (faturamentoGrupo.getAnoMesReferencia() != null) {
                
                inserirComandoAtividadeFaturamentoActionForm
                .setReferenciaFaturamento(Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia()
                .intValue()));
                
                String pesquisarAtividade = httpServletRequest.getParameter("pesquisa");

                //Data de vencimento do grupo
                /*
                 * 3.2. A data de vencimento do grupo (formatar a partir do mês
                 * seguinte ao mês de referência do faturamento e do dia de
                 * vencimento do grupo (FTGR_NNDIAVENCIMENTO da tabela
                 * FATURAMENTO_GRUPO para FTGR_ID=Grupo selecionado)) caso a
                 * atividade de faturamento selecionada corresponda à atividade
                 * faturar grupo, permitindo que seja alterada; caso contrário,
                 * este campo deve ser ocultado
                 */

                if (atividadeFaturamentoJSP != null
                    && !atividadeFaturamentoJSP.equalsIgnoreCase("")
                    && pesquisarAtividade != null && !pesquisarAtividade.equalsIgnoreCase("")) {

                    /*
                     * Alterado por Raphael Rossiter - Analista: Aryed Lins
                     * A data do vencimento será exibida caso a atividade também seja GERAR E ENVIAR ARQU. LEITURA
                     * (Pré-Faturamento)
                     */
                	if ((atividadeFaturamentoJSP.equalsIgnoreCase(String.valueOf(FaturamentoAtividade.FATURAR_GRUPO)) ||
                		atividadeFaturamentoJSP.equalsIgnoreCase(String.valueOf(FaturamentoAtividade.GERAR_ARQUIVO_LEITURA)))
                        && faturamentoGrupo.getDiaVencimento() != null) {
                    	/** alterado por pedro alexandre dia 26/06/2007 */
                        /*int diaVencimento = faturamentoGrupo.getDiaVencimento()
                                .intValue();
                        String mesVencimento = String.valueOf(
                                faturamentoGrupo.getAnoMesReferencia()
                                        .intValue()).substring(4, 6);
                        String anoVencimento = String.valueOf(
                                faturamentoGrupo.getAnoMesReferencia()
                                        .intValue()).substring(0, 4);

                        
                         * Colocado por Raphael Rossiter em 05/05/2007
                         * Obtendo a data de vencimento do grupo
                          
                        Date dataVencimento = fachada.obterDataVencimentoFaturamentoGrupo(diaVencimento, 
                        new Integer(mesVencimento), new Integer(anoVencimento));*/

                    	//[UC0618] Obter Data de Vencimento do Grupo 
                    	Date dataVencimento = fachada.obterDataVencimentoGrupo(faturamentoGrupo.getId(), null);
                    	
                    	/** fim alteração */
                    	
                        inserirComandoAtividadeFaturamentoActionForm
                        .setVencimentoGrupo(formatoData.format(dataVencimento));

                        sessao.setAttribute("exibirCampoVencimentoGrupo", formatoData
                        .format(dataVencimento));
                        
                        //Colocar o foco no campo vencimento do grupo
                    	httpServletRequest.setAttribute("nomeCampo", "vencimentoGrupo");
                    }
                    else{
                    	
                    	sessao.removeAttribute("exibirCampoVencimentoGrupo");
                    	
                    	//Colocar o foco no botão exibir rotas não habilitadas
                    	httpServletRequest.setAttribute("nomeCampo", "popupRotasNaoHabilitadas");
                    }

                    //Obter objeto FaturamentoAtividade a partir do ID
                    // -------------------
                    FiltroFaturamentoAtividade filtroFaturamentoAtividade = new FiltroFaturamentoAtividade();

                    filtroFaturamentoAtividade.setConsultaSemLimites(true);
                    
                    filtroFaturamentoAtividade
                            .adicionarParametro(new ParametroSimples(
                                    FiltroFaturamentoAtividade.ID,
                                    atividadeFaturamentoJSP));

                    filtroFaturamentoAtividade
                            .adicionarParametro(new ParametroSimples(
                                    FiltroFaturamentoAtividade.INDICADOR_USO,
                                    ConstantesSistema.INDICADOR_USO_ATIVO));

                    Collection colecaoFaturamentoAtividade = fachada.pesquisar(
                            filtroFaturamentoAtividade,
                            FaturamentoAtividade.class.getName());

                    FaturamentoAtividade faturamentoAtividade = (FaturamentoAtividade) Util
                            .retonarObjetoDeColecao(colecaoFaturamentoAtividade);
                    //--------------------------------------------------------------------

                    // [FS0008] - Verificar existência da atividade no
                    // cronograma do grupo do mês corrente
                    //fachada.verificarExistenciaCronogramaAtividadeGrupo(
                    //        faturamentoAtividade, faturamentoGrupo);

                    // Lista as rotas "habilitadas" do grupo

                    // [FS0006] - Verificar existência de rotas para o grupo
                    Collection colecaoRotasGrupo = fachada
                            .verificarExistenciaRotaGrupo(faturamentoGrupo);

                    if (faturamentoAtividade != null) {
        				
	        			
	                    //[SB0002] - Verificar Situação da Atividade para a Rota
	                    // true = Rotas habilitadas
	                    Collection colecaoRotasSituacao = fachada
	                            .verificarSituacaoAtividadeRota(colecaoRotasGrupo,
	                                    faturamentoAtividade, faturamentoGrupo,
	                                    true);
	
	                    //[FS0007] - Verificar seleção de pelo menos uma rota
	                    // habilitada
	                    if (colecaoRotasSituacao == null
	                            || colecaoRotasSituacao.isEmpty()) {
	                        throw new ActionServletException(
	                                "atencao.pesquisa.nenhuma.rota_habilitada_grupo");
	                    }
	                    
	                    // Passa a colecao de rotas habilitadas pelo request
	                    httpServletRequest.setAttribute("colecaoRotasHabilitadas",
	                            colecaoRotasSituacao);
                    }else {
                    	// Passa a colecao de rotas habilitadas pelo request
	                    httpServletRequest.setAttribute("colecaoRotasHabilitadas",
	                            null);
                    }
                    
                }
                else{
                	
                	inserirComandoAtividadeFaturamentoActionForm.setVencimentoGrupo("");
                	inserirComandoAtividadeFaturamentoActionForm.setAtividadeFaturamentoID(
                	String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
                	
                	//Colocar o foco no campo de atividades
                	httpServletRequest.setAttribute("nomeCampo", "atividadeFaturamentoID");
                }
            }

        } else {
            // Limpa o restante dos campos do formulário.
            inserirComandoAtividadeFaturamentoActionForm
                    .setReferenciaFaturamento("");
            inserirComandoAtividadeFaturamentoActionForm.setVencimentoGrupo("");
        }

        return retorno;
    }

    /**
     * Retorna o objeto FaturamentoGrupo selecionado
     * 
     * @param id
     * @param colecao
     * @return
     */
    private FaturamentoGrupo obterFaturamentoGrupoSelecionado(String id,
            Collection colecao) {
        FaturamentoGrupo retorno = null;
        Iterator colecaoIterator = colecao.iterator();

        while (colecaoIterator.hasNext()) {
            retorno = (FaturamentoGrupo) colecaoIterator.next();

            if (retorno.getId().equals(new Integer(id))) {
                break;
            }
        }

        return retorno;
    }

    /**
     * Retorna o objeto FaturamentoAtividade selecionado
     * 
     * @param id
     * @param colecao
     * @return
     */
    /*private FaturamentoAtividade obterFaturamentoAtividadeSelecionado(
            String id, Collection colecao) {
        FaturamentoAtividade retorno = null;
        Iterator colecaoIterator = colecao.iterator();

        while (colecaoIterator.hasNext()) {
            retorno = (FaturamentoAtividade) colecaoIterator.next();

            if (retorno.getId().equals(new Integer(id))) {
                break;
            }
        }

        return retorno;
    }*/

}

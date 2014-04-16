package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroArrecadadorMovimento;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a análise do movimento dos arrecadadores e os avisos 
 * bancários associados 
 *
 * @author Raphael Rossiter
 *
 * @date 08/03/2006
 */
public class ExibirApresentarAnaliseMovimentoArrecadadoresAction extends GcomAction {
    
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirApresentarAnaliseMovimentoArrecadadores");
        
        String idArrecadadorMovimento = httpServletRequest.getParameter("arrecadadorMovimentoID");
        
        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        ApresentarAnaliseMovimentoArrecadadoresActionForm apresentarAnaliseMovimentoArrecadadoresActionForm = 
        (ApresentarAnaliseMovimentoArrecadadoresActionForm) actionForm;
        
        
        FiltroArrecadadorMovimento filtroArrecadadorMovimento = new FiltroArrecadadorMovimento();
        filtroArrecadadorMovimento.setConsultaSemLimites(true);
        
        filtroArrecadadorMovimento.adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.ID,
        idArrecadadorMovimento));
        
        Collection colecaoArrecadadorMovimento = fachada.pesquisar(filtroArrecadadorMovimento,
        ArrecadadorMovimento.class.getName());
        
        ArrecadadorMovimento arrecadadorMovimento = (ArrecadadorMovimento) 
        Util.retonarObjetoDeColecao(colecaoArrecadadorMovimento);
        
        
        apresentarAnaliseMovimentoArrecadadoresActionForm.setCodigoNomeArrecadador(
        arrecadadorMovimento.getCodigoBanco() + " - " + arrecadadorMovimento.getNomeBanco());
        
        
        //1 - ENVIO  2 - RETORNO
        if (arrecadadorMovimento.getCodigoRemessa().equals(new Short(String.valueOf(ConstantesSistema.CODIGO_ENVIO)))){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setCodigoRemessa(
        	ConstantesSistema.ENVIO);
        	
        	sessao.removeAttribute("formaArrecadacao");
        }
        else if (arrecadadorMovimento.getCodigoRemessa().equals(new Short(String.valueOf(ConstantesSistema.CODIGO_RETORNO)))){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setCodigoRemessa(
            ConstantesSistema.RETORNO);
        	
        	sessao.setAttribute("formaArrecadacao",ConstantesSistema.RETORNO);
        	
        }
        
        if (arrecadadorMovimento.getDescricaoIdentificacaoServico() != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setIdentificacaoServico(
        	arrecadadorMovimento.getDescricaoIdentificacaoServico());
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setIdentificacaoServico("");
        }
        
        if (arrecadadorMovimento.getNumeroSequencialArquivo() != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNsa(
        	arrecadadorMovimento.getNumeroSequencialArquivo().toString());
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNsa("");
        }
        
        if (arrecadadorMovimento.getDataGeracao() != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setDataGeracao(
        	Util.formatarData(arrecadadorMovimento.getDataGeracao()));
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setDataGeracao("");
        }
        
        if (arrecadadorMovimento.getNumeroRegistrosMovimento() != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosMovimento(
        	arrecadadorMovimento.getNumeroRegistrosMovimento().toString());
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosMovimento("");
        }
        
        
        /* Número de registros em ocorrência (número de linhas da tabela ARRECADADOR_MOVIMENTO_ITEM com 
         * ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_DSOCORRENCIA diferente de "OK")
         */
        Integer numeroRegistrosOcorrencia = fachada.obterNumeroRegistrosEmOcorrenciaPorMovimentoArrecadadores(
        arrecadadorMovimento, ConstantesSistema.OK);
        
        if (numeroRegistrosOcorrencia != null){
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosOcorrencia(
        	numeroRegistrosOcorrencia.toString());
        }else{
        	apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosOcorrencia("");
        }
        
        
        /*
         * Número de registros que não foram aceitos (número de linhas da tabela 
         * ARRECADADOR_MOVIMENTO_ITEM com ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_ICACEITACAO 
         * igual a 2 (NÃO))
         */
        Integer numeroRegistrosNaoAceitos = fachada.obterNumeroRegistrosNaoAceitosPorMovimentoArrecadadores(
        arrecadadorMovimento, ConstantesSistema.REGISTROS_NAO_ACEITOS);
                
         if (numeroRegistrosNaoAceitos != null){
             apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosNaoAceitos(
             numeroRegistrosNaoAceitos.toString());
         }else{
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setNumeroRegistrosNaoAceitos("");
         }
        
        BigDecimal valorTotalMovimento = new BigDecimal("0.00");
         if (arrecadadorMovimento.getValorTotalMovimento() != null){
         	apresentarAnaliseMovimentoArrecadadoresActionForm.setValorTotalMovimento(
         	Util.formatarMoedaReal(arrecadadorMovimento.getValorTotalMovimento()));
         	valorTotalMovimento = arrecadadorMovimento.getValorTotalMovimento();
         }
         
         if (arrecadadorMovimento.getUltimaAlteracao() != null){
          	
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setDataProcessamento(
          	 Util.formatarData(arrecadadorMovimento.getUltimaAlteracao()));
        	 
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setHoraProcessamento(
        	 Util.formatarHoraSemData(arrecadadorMovimento.getUltimaAlteracao()));
        	 
         }else{
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setDataProcessamento("");
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setHoraProcessamento("");
        	 
         }
         
         
         
         /*
          * Caso o valor total do movimento (ARMV_VALORTOTALMOVIMENTO) seja diferente do valor da soma das
          * arrecadações dos avisos bancários relacionados (ARMV_ID = ARMV_ID da tabela AVISO_BANCARIO e o campo
          * para totalização será AVBC_VLARRECADACAO), a situação do movimento será "ABERTO".
          * Caso contrário a situação do movimento será "FECHADO"
          */
         String situacaoArrecadadorMovimento = fachada.obterSituacaoArrecadadorMovimento(
         arrecadadorMovimento);
         
         apresentarAnaliseMovimentoArrecadadoresActionForm.setSituacaoMovimento(situacaoArrecadadorMovimento);
         
         
         /*
          * Valor total dos avisos bancários de um determinado movimento (Total da soma do campo
          * AVBC_VALORARRECADACAO da tabela AVISO_BANCARIO com ARMV_ID = ARMV_ID da tabela 
          * ARRECADADOR_MOVIMENTO 
          */
         BigDecimal valorAvisosBancarios = fachada.obterTotalArrecadacaoAvisoBancarioPorMovimentoArrecadadores(
         arrecadadorMovimento);
         
         if (valorAvisosBancarios != null){
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setValorTotalAvisosBancarios(
        	 Util.formatarMoedaReal(valorAvisosBancarios));
         }else{
        	 apresentarAnaliseMovimentoArrecadadoresActionForm.setValorTotalAvisosBancarios("");
         }
         
         BigDecimal diferenca = new BigDecimal("0.00");
         if (valorAvisosBancarios != null){
        	 diferenca = valorTotalMovimento.subtract(valorAvisosBancarios);
         }else{
        	 diferenca = valorTotalMovimento;
         }
         apresentarAnaliseMovimentoArrecadadoresActionForm.setValordiferencaVlMovimentoVlAvisos(Util.formatarMoedaReal(diferenca));
         
         /*
          * Lista os avisos bancários associados ao movimento
          */
         Collection<AvisoBancarioHelper> colecaoAvisosBancariosPorMovimentoArrecadador = 
         fachada.obterColecaoAvisosBancariosPorArrecadadorMovimento(arrecadadorMovimento);
         
         httpServletRequest.setAttribute("colecaoAvisosBancariosPorMovimentoArrecadador",
         colecaoAvisosBancariosPorMovimentoArrecadador);
         
         
         //Parâmetro que será utilizado para montar o link para os itens do movimento
         httpServletRequest.setAttribute("idArrecadadorMovimento", idArrecadadorMovimento);
         
         
        return retorno;
    }
}

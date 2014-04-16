package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * Action que finaliza a primeira página do processo de inserir pagamentos
 * 
 * @author 	Pedro Alexandre 
 * @created 16/02/2006
 */
public class InserirPagamentosAvisoBancarioAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//cria a variável que vai armazenar o retorno
    	ActionForward retorno = null;
    	
    	//cria uma instÂncia da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //recupera o form 
        DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //recupera os dados informados na página de aviso bancário
        String tipoInclusao = (String) pagamentoActionForm.get("tipoInclusao");
        String dataPagamento = (String) pagamentoActionForm.get("dataPagamento");
        String idAvisoBancario = (String) pagamentoActionForm.get("idAvisoBancario");  
        String idFormaArrecadacao =(String) pagamentoActionForm.get("idFormaArrecadacao");

        //se o tipo de inclusão não for informado 
        if(tipoInclusao == null || tipoInclusao.trim().equalsIgnoreCase("")){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Tipo de Inclusão");
        }
        
        //se a data de pagamento não for informada 
        if(dataPagamento == null || dataPagamento.trim().equalsIgnoreCase("")){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Data de Pagamento");
        }
        
        //se o aviso bancário não for informado
        if(idAvisoBancario == null || idAvisoBancario.trim().equalsIgnoreCase("")){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Aviso Bancário");
        } else {

        	FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        	filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        	filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        	
        	Collection colecaoAvisoBancario = fachada.pesquisar(filtroAvisoBancario, AvisoBancario.class.getName());
        	
        	if (colecaoAvisoBancario != null && !colecaoAvisoBancario.isEmpty()) {
        		AvisoBancario avisoBancario = (AvisoBancario) Util.retonarObjetoDeColecao(colecaoAvisoBancario);
        		
        		pagamentoActionForm.set("codigoAgenteArrecadador", avisoBancario.getArrecadador().getCodigoAgente().toString());
        		pagamentoActionForm.set("dataLancamentoAviso", Util.formatarData(avisoBancario.getDataLancamento()));
        		pagamentoActionForm.set("numeroSequencialAviso", avisoBancario.getNumeroSequencial().toString());
        		
        	} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Aviso Bancário");
			}

		}
        
        // se a forma de arrecadação não for informada
        if(idFormaArrecadacao == null || idFormaArrecadacao.trim().equalsIgnoreCase("")){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Forma de Arrecadação");
        }
//        else if (tipoInclusao.equals("ficha") && 
//        		!idFormaArrecadacao.equals(ArrecadacaoForma.FICHA_COMPENSACAO.toString())){
//        	//levanta a exceção para a próxima camada
//        	throw new ActionServletException("atencao.arrecadacao_forma_invalida");
//        }
        
        //cria o formato da data
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

        try {
        	//tenta converter a data de apagamento
            dataFormato.parse(dataPagamento);

            //se não conseguir converter
        } catch (ParseException ex) {
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.data_pagamento_invalida");
        }
        
        //recupera o status atual do wizard
        StatusWizard statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");
                
        //cria o filtro de forma de arrecadação
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        
        //seta o código da forma de arrecadação no filtro
        filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO, idFormaArrecadacao));
        
        //pesquisa a forma de arrecadação no sistema
        Collection colecaoFormaArrecadacao = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
        
        //se não existir a forma de arrecadação no sistema
        if(colecaoFormaArrecadacao == null || colecaoFormaArrecadacao.isEmpty()){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Forma de Arrecadação");
        	
        }else{
        	//recupera a forma de arrecadação da coleção
        	ArrecadacaoForma formaArrecadacao =(ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoFormaArrecadacao);
        	
        	//setaa descrição da forma de arrecadação no form
        	pagamentoActionForm.set("descricaoFormaArrecadacao", formaArrecadacao.getDescricao());
        }
        	
        //se o tipo de inclusão for "manual"
        if (tipoInclusao != null && tipoInclusao.equalsIgnoreCase("manual")) {

            //seta o status do wizard para a página de inclusão manual
        	statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                    2, "TipoInclusaoA.gif", "TipoInclusaoD.gif",
                    "exibirInserirPagamentosTipoInclusaoManualAction",
                    "inserirPagamentosTipoInclusaoManualAction"));
        	
        	retorno = actionMapping.findForward("inserirPagamentosTipoInclusaoManual");

            //Limpa os códigos de barras do form da página de inserir pagamento por caneta óptica
            pagamentoActionForm.set("codigoBarraDigitadoCampo1","");
        	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificadorCampo1","");
        	pagamentoActionForm.set("codigoBarraDigitadoCampo2","");
        	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificadorCampo2","");
        	pagamentoActionForm.set("codigoBarraDigitadoCampo3","");
        	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificadorCampo3","");
        	pagamentoActionForm.set("codigoBarraDigitadoCampo4","");
        	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificadorCampo4","");
        	pagamentoActionForm.set("codigoBarraPorLeituraOtica","");  
        	//pagamentoActionForm.set("tipoLeitura","optica");
        	pagamentoActionForm.set("tipoLeitura","");
        	
          //se o tipo de inclusão for "caneta"  
        } else if(tipoInclusao != null && tipoInclusao.equalsIgnoreCase("caneta")){
            
        	//seta o status do wizard para a página de inclusão por caneta
        	statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                    2, "TipoInclusaoA.gif", "TipoInclusaoD.gif",
                    "exibirInserirPagamentosTipoInclusaoCanetaAction",
                    "inserirPagamentosTipoInclusaoCanetaAction"));
        	
        	retorno = actionMapping.findForward("inserirPagamentosTipoInclusaoCaneta");
        	
        	//Limpa os dados do form da página de inserir pagamento manual
        	pagamentoActionForm.set("valorPagamento","");
        	pagamentoActionForm.set("idTipoDocumento","");
        	pagamentoActionForm.set("idImovel","");
        	pagamentoActionForm.set("descricaoImovel","");
        	pagamentoActionForm.set("idGuiaPagamento","");
        	pagamentoActionForm.set("descricaoGuiaPagamento","");
        	pagamentoActionForm.set("valorGuiaPagamento","");
        	pagamentoActionForm.set("idDebitoACobrar","");
        	pagamentoActionForm.set("descricaoDebitoACobrar","");
        	pagamentoActionForm.set("valorDebitoACobrar","");
        	pagamentoActionForm.set("idLocalidade","");
        	pagamentoActionForm.set("descricaoLocalidade","");
        	pagamentoActionForm.set("referenciaConta","");
        	pagamentoActionForm.set("descricaoReferenciaConta","");
        	pagamentoActionForm.set("idCliente","");
        	pagamentoActionForm.set("nomeCliente","");
        	pagamentoActionForm.set("idTipoDebito","");
        	pagamentoActionForm.set("descricaoTipoDebito","");
        }
        else if(tipoInclusao != null && tipoInclusao.equalsIgnoreCase("ficha")){
            
        	//seta o status do wizard para a página de inclusão por caneta
        	statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                    2, "TipoInclusaoA.gif", "TipoInclusaoD.gif",
                    "exibirInserirPagamentosTipoInclusaoFichaCompensacaoAction",
                    "inserirPagamentosTipoInclusaoFichaCompensacaoAction"));
        	
        	retorno = actionMapping.findForward("inserirPagamentosTipoInclusaoFichaCompensacao");
        }

 
    	
        //seta o form na sessão
        sessao.setAttribute("PagamentoActionForm",pagamentoActionForm);
        
        //seta o status do wizard na sessão
        sessao.setAttribute("statusWizard",statusWizard);
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}

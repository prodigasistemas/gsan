package gcom.gui.arrecadacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Tiago Moreno
 * @create 07/02/2006
 * 
 */
public class PesquisarMovimentoArrecadadorAction extends GcomAction {

public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarMovimentoArrecadadorResultado");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Recupera informações do formulario
		PesquisarMovimentoArrecadadorActionForm pesquisarMovimentoArrecadadorActionForm = (PesquisarMovimentoArrecadadorActionForm) actionForm;

	    String codigoBanco = null;
		String codigoRemessa = null;
		String descricaoIdentificacaoServico = null;
		String numeroSequencialArquivo = null;
		Date dataGeracaoInicio = null;
		Date dataGeracaoFim = null;
		Date ultimaAlteracaoInicio = null;
		Date ultimaAlteracaoFim = null;
		String descricaoOcorrencia = null;
		String indicadorAceitacao = null; 
		String indicadorAbertoFechado = null;
				
		boolean peloMenosUmParametroInformado = false;
		
		 if (pesquisarMovimentoArrecadadorActionForm.getIdBanco() != null &&
       	!pesquisarMovimentoArrecadadorActionForm.getIdBanco().equalsIgnoreCase("")){
       	
			 codigoBanco = pesquisarMovimentoArrecadadorActionForm.getIdBanco().trim();
       	
       	peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getTipoRemessa() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getTipoRemessa().equalsIgnoreCase("")){
           	
       	codigoRemessa = pesquisarMovimentoArrecadadorActionForm.getTipoRemessa().trim();
           	
           peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getIdentificacaoServico() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getIdentificacaoServico().equalsIgnoreCase("") &&
           !pesquisarMovimentoArrecadadorActionForm.getIdentificacaoServico().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
       	
       	descricaoIdentificacaoServico = pesquisarMovimentoArrecadadorActionForm.getIdentificacaoServico().trim();

       	peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getNumeroSequencialArquivo() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getNumeroSequencialArquivo().equalsIgnoreCase("")){
           	
       	numeroSequencialArquivo = pesquisarMovimentoArrecadadorActionForm.getNumeroSequencialArquivo().trim();
           	
           peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getDataMovimentoInicio() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getDataMovimentoInicio().equalsIgnoreCase("")){
   		
       	dataGeracaoInicio = Util.converteStringParaDate(pesquisarMovimentoArrecadadorActionForm.getDataMovimentoInicio());
       	dataGeracaoFim = dataGeracaoInicio;
       	
       	peloMenosUmParametroInformado = true;
		
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getDataMovimentoFim() != null &&
		        !pesquisarMovimentoArrecadadorActionForm.getDataMovimentoFim().equalsIgnoreCase("")){
			
       	dataGeracaoFim = Util.converteStringParaDate(pesquisarMovimentoArrecadadorActionForm.getDataMovimentoFim());
       	
       	peloMenosUmParametroInformado = true;
		}
       
              
       if (pesquisarMovimentoArrecadadorActionForm.getMovimentoItemOcorrencia() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getMovimentoItemOcorrencia().equalsIgnoreCase("") &&
           !pesquisarMovimentoArrecadadorActionForm.getMovimentoItemOcorrencia().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
           
       	descricaoOcorrencia = pesquisarMovimentoArrecadadorActionForm.getMovimentoItemOcorrencia().trim();
       	
           peloMenosUmParametroInformado = true;
       }
       
       if (pesquisarMovimentoArrecadadorActionForm.getMovimentoItemAceito() != null &&
           !pesquisarMovimentoArrecadadorActionForm.getMovimentoItemAceito().equalsIgnoreCase("") &&
           !pesquisarMovimentoArrecadadorActionForm.getMovimentoItemAceito().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
           	
       	indicadorAceitacao = pesquisarMovimentoArrecadadorActionForm.getMovimentoItemAceito().trim();
       	
           peloMenosUmParametroInformado = true;
       }
       
              
       //Erro caso o usuário mandou filtrar sem nenhum parâmetro
       if (!peloMenosUmParametroInformado) {
           throw new ActionServletException(
           "atencao.filtro.nenhum_parametro_informado");
       }
			
		
		//1º Passo - Pegar o total de registros através de um count da consulta
		// que aparecerá na tela
		Integer totalRegistros = fachada.filtrarMovimentoArrecadadoresCount
				(codigoBanco, codigoRemessa, descricaoIdentificacaoServico,
	    		 numeroSequencialArquivo, dataGeracaoInicio,
	    		 dataGeracaoFim, ultimaAlteracaoInicio, ultimaAlteracaoFim,
	    		 descricaoOcorrencia, indicadorAceitacao,indicadorAbertoFechado);
        
		//2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,totalRegistros);
		
		//	3º Passo - Obter a coleção da consulta que aparecerá na tela passando
		// o numero de paginas da pesquisa que está no request
		Collection colecaoMovimentoArrecadador = fachada.filtrarMovimentoArrecadadorParaPaginacao(
				 codigoBanco, codigoRemessa, descricaoIdentificacaoServico,
	    		 numeroSequencialArquivo, dataGeracaoInicio,
	    		 dataGeracaoFim, ultimaAlteracaoInicio, ultimaAlteracaoFim,
	    		 descricaoOcorrencia, indicadorAceitacao, 
	    		 ((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")),indicadorAbertoFechado);
		
		
		
		// valida se a colecao esta vazia
		if (colecaoMovimentoArrecadador == null) {
			throw new ActionServletException("atencao.colecao_vazia");
		} else {
			if (!colecaoMovimentoArrecadador.isEmpty()) {
				// joga a colecao na sessão
				sessao.setAttribute("colecaoMovimentoArrecadador",
					colecaoMovimentoArrecadador);
		} else {
			throw new ActionServletException("atencao.colecao_vazia");
			}
		}
		
		return retorno;
	}}

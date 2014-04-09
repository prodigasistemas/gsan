package gcom.gui.arrecadacao.aviso;

import java.util.Date;

import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0611] Movimentar Pagamentos/ Devoluções entre Avisos Bancários
 * 
 * @author Ana Maria
 *
 * @date 07/06/2007
 */
public class SelecionarPagamentosAvisoBancarioAction extends
		GcomAction {
  
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirMovimentarPagamentosDevolucoesAvisoBancario");	
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		SelecionarPagamentosAvisoBancarioActionForm form = (SelecionarPagamentosAvisoBancarioActionForm) actionForm;
		
		// Recupera os parâmetros do form
		Integer avisoBancarioO = new Integer(form.getAvisoBancarioO());
		Integer avisoBancarioD =  new Integer(form.getAvisoBancarioD());
		String dataDevolucao 		=  form.getDataDevolucao();
		String dataPagamento	=  form.getDataPagamento();
		Integer idArrecadacaoForma =  new Integer(form.getIdArrecadacaoForma());
		
		boolean peloMenosUmParametroInformado = false;
		
		FiltroDevolucao filtroDevolucao = null;
		if(dataDevolucao != null && !dataDevolucao.equals("")){
		  peloMenosUmParametroInformado = true;	
		  Date data = Util.converteStringParaDate(dataDevolucao); 
		  filtroDevolucao = new FiltroDevolucao();
		  filtroDevolucao.adicionarParametro(new ParametroSimples(FiltroDevolucao.AVISO_BANCARIO_ID, avisoBancarioO));
		  filtroDevolucao.adicionarParametro(new ParametroSimples(FiltroDevolucao.DATA_DEVOLUCAO, data));			
		}
		
		FiltroPagamento filtroPagamento = null;
		if((dataPagamento != null && !dataPagamento.equals("")) ||
				(idArrecadacaoForma != null && !idArrecadacaoForma.equals("0"))){
		  peloMenosUmParametroInformado = true;	
		  filtroPagamento = new FiltroPagamento();
		  filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.AVISO_BANCARIO_ID, avisoBancarioO));
		  if(dataPagamento != null && !dataPagamento.equals("")){
			Date data = Util.converteStringParaDate(dataPagamento);  
			filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.DATA_PAGAMENTO, data));   
		  }
		  if(idArrecadacaoForma != null && !idArrecadacaoForma.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.PAGAMENTO_ARRECADACAO_FORMA, idArrecadacaoForma));   
		  }

		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pela sessão 
		sessao.setAttribute("filtroDevolucao", filtroDevolucao);
		sessao.setAttribute("filtroPagamento", filtroPagamento);	
				
		sessao.setAttribute("avisoBancarioO", avisoBancarioO);
		sessao.setAttribute("avisoBancarioD", avisoBancarioD);
		
		String descricaoABOrigem = form.getCodigoAgenteArrecadadorO()+" - "+form.getDataLancamentoAvisoO()+" - "+form.getNumeroSequencialAvisoO();
		String descricaoABDestino = form.getCodigoAgenteArrecadadorD()+" - "+form.getDataLancamentoAvisoD()+" - "+form.getNumeroSequencialAvisoD();
		
		sessao.setAttribute("descricaoABOrigem", descricaoABOrigem);
		sessao.setAttribute("descricaoABDestino", descricaoABDestino);
		
		return retorno;
      
	}
}

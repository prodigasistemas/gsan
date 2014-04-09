package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade exibir para o usuário os itens do movimento do arrecadador
 *
 * @author Raphael Rossiter
 * @date 20/03/2006
 */
public class ExibirConsultarItensMovimentoArrecadadorAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

	        ActionForward retorno = actionMapping.findForward("exibirConsultarItensMovimentoArrecadador");
	        
	        HttpSession sessao = getSessao(httpServletRequest);
	        
	        PesquisarItensMovimentoArrecadadorActionForm pesquisarItensMovimentoArrecadadorActionForm = 
	        	(PesquisarItensMovimentoArrecadadorActionForm) actionForm;
	        
	        String idArrecadadorMovimento = pesquisarItensMovimentoArrecadadorActionForm.getIdArrecadadorMovimento();
	        
	        Fachada fachada = Fachada.getInstancia();
	        
	        ArrecadadorMovimento arrecadadorMovimento = new ArrecadadorMovimento();
	        arrecadadorMovimento.setId(new Integer(idArrecadadorMovimento));
	        
	        Integer idImovel = null ;
			String retornoImovel = "";
	        if (pesquisarItensMovimentoArrecadadorActionForm.getMatriculaImovel() != null 
					&& !pesquisarItensMovimentoArrecadadorActionForm.getMatriculaImovel() .equals("")) {
	        	idImovel = new Integer(pesquisarItensMovimentoArrecadadorActionForm.getMatriculaImovel());
	        	
				retornoImovel = fachada.pesquisarInscricaoImovel(idImovel);
				if(retornoImovel == null || retornoImovel.equalsIgnoreCase(""))
				{
					throw new ActionServletException(
						"atencao.imovel.inexistente");
				}
			}else{
				sessao.removeAttribute("valorDadosMovimento");
				sessao.removeAttribute("valorDadosPagamento");
			}

	        String nomeArrecadador = "";
	        
	        Collection<Object[]> nomeArrecadadorNomeAgencia = null;
			
			if(idArrecadadorMovimento != null){
				nomeArrecadadorNomeAgencia = fachada.consultarNomeArrecadadorNomeAgencia( idArrecadadorMovimento );
				
				Iterator iteDados= nomeArrecadadorNomeAgencia.iterator();
				
				while (iteDados.hasNext()) {
					
					String dados = (String) iteDados.next();
				
					if (dados != null) {
						
						if (dados != null) {
							nomeArrecadador = (String) dados;
						} 
					}
				}
				
			}	
	        
			sessao.setAttribute("nomeArrecadador", nomeArrecadador);
	        
			Short indicadorAceitacao = null;
			if (pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao()!= null &&
					!pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao().equals("") &&
					!pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao().equals("3") &&
					!pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				indicadorAceitacao = new Short(pesquisarItensMovimentoArrecadadorActionForm.getIndicadorAceitacao());
			}
			
			String descricaoOcorrencia = null;
			if (pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia()!= null &&
					!pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia().equals("") &&
					!pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia().equals("3") &&
					!pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				descricaoOcorrencia = pesquisarItensMovimentoArrecadadorActionForm.getDescricaoOcorrencia();
			}
			
			// ------------ Forma de Arrecadacao --------------- Kássia Albuquerque
			
			String codigoArrecadacaoForma = null;
			if (pesquisarItensMovimentoArrecadadorActionForm.getFormaArrecadacao()!= null && 
					!pesquisarItensMovimentoArrecadadorActionForm.getFormaArrecadacao().equals("")&&
					!pesquisarItensMovimentoArrecadadorActionForm.getFormaArrecadacao().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
				
				filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO,
						pesquisarItensMovimentoArrecadadorActionForm.getFormaArrecadacao()));
				
				Collection<ArrecadacaoForma> colecaoArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma,ArrecadacaoForma.class.getName());
				
				if (colecaoArrecadacaoForma!= null && !colecaoArrecadacaoForma.isEmpty()){
					
					ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma)colecaoArrecadacaoForma.iterator().next();
					codigoArrecadacaoForma = arrecadacaoForma.getCodigoArrecadacaoForma();
					String descricaoArrecadacao = arrecadacaoForma.getDescricao();
					
					pesquisarItensMovimentoArrecadadorActionForm.setDescricaoFormaArrecadacao(descricaoArrecadacao);
				}
			}
			
			Short indicadorDiferencaValorMovimentoValorPagamento = Short.parseShort( pesquisarItensMovimentoArrecadadorActionForm.getIndicadorDiferencaValorMovimentoValorPagamento() );
	        
	        Collection colecaoArrecadadorMovimentoItemHelper =fachada.
        	consultarItensMovimentoArrecadador(arrecadadorMovimento,idImovel,indicadorAceitacao,descricaoOcorrencia
	        			,codigoArrecadacaoForma, indicadorDiferencaValorMovimentoValorPagamento );
	        
	        
	        // ------------- Agrupando os valores Totais do Movimento e do Pagamento ------- Kássia Albuquerque
	        
	        ArrecadadorMovimentoItemHelper helper = null;
	        BigDecimal valorDadosMovimento = new BigDecimal("0.00");
	        BigDecimal valorDadosPagamento = new BigDecimal("0.00");
			
	
			if (colecaoArrecadadorMovimentoItemHelper != null && !colecaoArrecadadorMovimentoItemHelper.isEmpty()) {
				
				Iterator colecaoArrecadadorMovimentoItemHelperIterator = colecaoArrecadadorMovimentoItemHelper.iterator();
				// percorre a colecao de debito a cobrar somando o valor para obter um valor total
				while (colecaoArrecadadorMovimentoItemHelperIterator.hasNext()) {
	
					helper = (ArrecadadorMovimentoItemHelper) colecaoArrecadadorMovimentoItemHelperIterator.next();
					
					if (helper.getVlMovimento()!= null && !helper.getVlMovimento().equals("")){
						
						valorDadosMovimento = valorDadosMovimento.add(Util.formatarMoedaRealparaBigDecimal(helper.getVlMovimento()));
					}
					
					if (helper.getVlPagamento()!= null && !helper.getVlPagamento().equals("")){
						
						valorDadosPagamento = valorDadosPagamento.add(Util.formatarMoedaRealparaBigDecimal(helper.getVlPagamento()));
					}
					
					
				}
				
				sessao.setAttribute("valorDadosMovimento", Util .formatarMoedaReal(valorDadosMovimento));
				sessao.setAttribute("valorDadosPagamento", Util .formatarMoedaReal(valorDadosPagamento));
			}
	        
			httpServletRequest.setAttribute("colecaoArrecadadorMovimentoItemHelper", colecaoArrecadadorMovimentoItemHelper);

			pesquisarItensMovimentoArrecadadorActionForm.setColecaoArrecadadorMovimentoItem(colecaoArrecadadorMovimentoItemHelper);
		      
	       
	       return retorno;
	    }

}

package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentado;
import gcom.micromedicao.hidrometro.HidrometroMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentado;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class ExibirConsultarMovimentacaoHidrometroAction extends GcomAction {

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

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("consultarMovimentacaoHidrometro");

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// remove objetos da sessão vindos do filtro
		sessao.removeAttribute("colecaoHidrometroMotivoMovimentacao");
		sessao.removeAttribute("ManutencaoRegistroActionForm");
		
		// Cria coleção
		Collection colecaoHidrometroMovimentacao = null;

		Collection colecaoHidrometroMovimentado = null;

		FiltroHidrometroMovimentacao filtroHidrometroMovimentacao = (FiltroHidrometroMovimentacao) httpServletRequest
				.getAttribute("filtroMovimentacaoHidrometro");

		if (sessao.getAttribute("fixo") != null && 
        	!sessao.getAttribute("fixo").equals("")) {
            
        	String fixo = (String) sessao.getAttribute("fixo");
            String faixaInicial = (String) sessao.getAttribute("faixaInicial");
            String faixaFinal = (String) sessao.getAttribute("faixaFinal");
            
            // 1º Passo - Pegar o total de registros através de um count da consulta
    		// que aparecerá na tela

            Integer totalRegistros = 
            	this.getFachada().pesquisarNumeroHidrometroMovimentacaoPorFaixaCount(fixo,
            		fixo + faixaInicial, 
            		fixo + faixaFinal);
            
            // 2º Passo - Chamar a função de Paginação passando o total de registros
    		retorno = 
    			this.controlarPaginacao(httpServletRequest, retorno,totalRegistros);
            
    		colecaoHidrometroMovimentacao = 
    			this.getFachada().pesquisarNumeroHidrometroMovimentacaoPorFaixaPaginacao(
    					fixo + faixaInicial, 
    					fixo + faixaFinal, 
    				((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")));
    	
        }else{
        	
        	// Aciona o controle de paginação para que sejam pesquisados apenas
        	// os registros que aparecem na página
        	Map resultado = controlarPaginacao(httpServletRequest, retorno,
        			filtroHidrometroMovimentacao, HidrometroMovimentacao.class.getName());
        	colecaoHidrometroMovimentacao = (Collection) resultado.get("colecaoRetorno");
        	retorno = (ActionForward) resultado.get("destinoActionForward");
        }
		
		

		if (!colecaoHidrometroMovimentacao.isEmpty()) {

			Iterator hidrometroMovimentacaoIterator = colecaoHidrometroMovimentacao
					.iterator();

			while (hidrometroMovimentacaoIterator.hasNext()) {

				HidrometroMovimentacao hidrometroMovimentacao = (HidrometroMovimentacao) hidrometroMovimentacaoIterator
						.next();

				FiltroHidrometroMovimentado filtroHidrometroMovimentado = new FiltroHidrometroMovimentado();

				filtroHidrometroMovimentado
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroMovimentado.HIDROMETRO_MOVIMENTACAO_ID,
								hidrometroMovimentacao.getId()));

				colecaoHidrometroMovimentado = fachada.pesquisar(
						filtroHidrometroMovimentado,
						HidrometroMovimentado.class.getName());

				Integer quantidade = colecaoHidrometroMovimentado.size();

				hidrometroMovimentacao.setQuantidade(quantidade.toString());
			}
		}
		// Caso a coleção seja null
		if (colecaoHidrometroMovimentacao == null
				|| colecaoHidrometroMovimentacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		// Envia objeto na sessão
		sessao.setAttribute("colecaoHidrometroMovimentacao",
				colecaoHidrometroMovimentacao);

		// devolve o mapeamento de retorno
		return retorno;
	}
}

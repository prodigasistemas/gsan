package gcom.gui.faturamento.debito;

import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Descrição da classe 
 *
 * @author Administrador
 * @date 09/03/2006
 */
public class PesquisarTipoDebitoAction extends GcomAction {
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Administrador
	 * @date 09/03/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {

		//seta o mapeamento de retorno para a tela de resultado da pesquisa de tipo de débito
		ActionForward retorno = actionMapping.findForward("listaTipoDebito");

		//cria uma instância da fachada
		//Fachada fachada = Fachada.getInstancia();
		
		//cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//recupera o form de pesquisa de tipo de débito
		PesquisarTipoDebitoActionForm pesquisarTipoDebitoActionForm = (PesquisarTipoDebitoActionForm) actionForm;
		
		// Recupera os parâmetros do form
		String idTipoDebito = pesquisarTipoDebitoActionForm.getIdTipoDebito();
		String descricao =  pesquisarTipoDebitoActionForm.getDescricao();
		String[] idTipoFinanciamento =  pesquisarTipoDebitoActionForm.getIdTipoFinanciamento();
		String[] idLancamentoItemContabil =  pesquisarTipoDebitoActionForm.getIdItemLancamentoContabil();
		String intervaloValorLimiteInicial = pesquisarTipoDebitoActionForm.getIntervaloValorLimiteInicial();
		String intervaloValorLimiteFinal = pesquisarTipoDebitoActionForm.getIntervaloValorLimiteFinal();
		String tipoPesquisa = pesquisarTipoDebitoActionForm.getTipoPesquisa();

		//cria o filtro de tipo de débito que vai conter os parâmetros da pesquisa
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

		//seta a ordenação do retorno da pesquisa
		filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

		//cria a flag que vai indicar se o uusário informou um parâmetro para pesquisa
		boolean peloMenosUmParametroInformado = false;
		
		//seta no filtro o indicador de geração
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.INDICADOR_GERACAO_AUTOMATICA, new Integer("2")));
		
		
		//se o usuário informar o código do tipo de débito
		if(idTipoDebito != null && !idTipoDebito.trim().equalsIgnoreCase("")){
			peloMenosUmParametroInformado = true;
		    filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID,idTipoDebito));
		}

		//se o usuário informar a descrição do tipo do débito
		if(descricao != null && !descricao.trim().equalsIgnoreCase("")){
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroDebitoTipo.adicionarParametro(new ComparacaoTextoCompleto(FiltroDebitoTipo.DESCRICAO,descricao));
			} else {
				filtroDebitoTipo.adicionarParametro(new ComparacaoTexto(FiltroDebitoTipo.DESCRICAO,descricao));
			}	
			
		}

		
		
		
		//Caso o usuário indicar os tipos de financiamentos para pesquisar os tipos de débito 
		if(idTipoFinanciamento != null && !idTipoFinanciamento[0].equals("-1")
				&&idTipoFinanciamento.length >0 ){
			//Indica que o usuário informou um parâmetro para pesquisar
			peloMenosUmParametroInformado = true;
			
			//Laço para inserir no filtro todos os tipos de financiamentos informados 
			for(int i=0; i< idTipoFinanciamento.length; i++ ){
			  if(! (new Integer(idTipoFinanciamento[i]).equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)))){
				  
				if(idTipoFinanciamento.length==1){
					filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.FINANCIAMENTO_TIPO,idTipoFinanciamento[i]));	
				}else{
				  if( i == 0 ){
					  filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.FINANCIAMENTO_TIPO,idTipoFinanciamento[i], ParametroSimples.CONECTOR_OR,idTipoFinanciamento.length ));	
				  }else{
				    if( i  == (idTipoFinanciamento.length - 1) ){
				      filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.FINANCIAMENTO_TIPO,idTipoFinanciamento[i]));
				    }else{
				      filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.FINANCIAMENTO_TIPO,idTipoFinanciamento[i], ParametroSimples.CONECTOR_OR));
				    }
				  }
			    }
			  }
			}
		}
		
		//Caso o usuário indicar os tipos de financiamentos para pesquisar os tipos de débito 
		if(idLancamentoItemContabil != null && !idLancamentoItemContabil[0].equals("-1") 
				&& idLancamentoItemContabil.length >0){
			//Indica que o usuário informou um parâmetro para pesquisar
			peloMenosUmParametroInformado = true;
			
			//Laço para inserir no filtro todos os tipos de financiamentos informados 
			for(int i=0; i< idLancamentoItemContabil.length; i++ ){
			  if(! (new Integer(idLancamentoItemContabil[i]).equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)))){
				  
				if(idLancamentoItemContabil.length==1){
					filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.LANCAMENTO_ITEM_CONTABIL_ID,idLancamentoItemContabil[i]));	
				}else{
				  if( i == 0 ){
					  filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.LANCAMENTO_ITEM_CONTABIL_ID,idLancamentoItemContabil[i], ParametroSimples.CONECTOR_OR,idLancamentoItemContabil.length ));	
				  }else{
				    if( i  == (idLancamentoItemContabil.length - 1) ){
				      filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.LANCAMENTO_ITEM_CONTABIL_ID,idLancamentoItemContabil[i]));
				    }else{
				      filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.LANCAMENTO_ITEM_CONTABIL_ID,idLancamentoItemContabil[i], ParametroSimples.CONECTOR_OR));
				    }
				  }
			    }
			  }
			}
		}
		
	
		//se o usuário informar o intervalo inicial do valor de limite 
		if (intervaloValorLimiteInicial != null && !intervaloValorLimiteInicial.trim().equalsIgnoreCase("")) {
			
			//se o usuário não informar o intervalo final do valor de limite 
			if(intervaloValorLimiteFinal == null || intervaloValorLimiteFinal.trim().equalsIgnoreCase("")){
				//o intervalo final do valor de limite vai receber o valor do intervalo inicial 
				intervaloValorLimiteFinal = intervaloValorLimiteInicial;
				
				//se o usuário informar o intervalo final do valor de limite 
			}else{
				//se o intervalo final do valor de limite for menor que o inicial
				if((Util.formatarMoedaRealparaBigDecimal(intervaloValorLimiteInicial)).doubleValue() > ( (Util.formatarMoedaRealparaBigDecimal(intervaloValorLimiteFinal))).doubleValue()){
					//levanta a exceção para a próxima camada
					throw new ActionServletException("atencao.valorlimitefinal.menorque");
				}
			}
			
			//se o usuário não informar o intervalo inicial do valor de limite 
		} else{
			//seta o intervalo final do valor de limite para null 
			intervaloValorLimiteFinal = null; 
		}

		//se o intervalo final do valor de limite não estiver nulo ou em branco
		if (intervaloValorLimiteFinal != null && !intervaloValorLimiteFinal.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			//seta no filtro para retornar os tipos de débito entre os valores informados
			filtroDebitoTipo.adicionarParametro(new MaiorQue(FiltroDebitoTipo.VALOR_LIMITE, Util.formatarMoedaRealparaBigDecimal(intervaloValorLimiteInicial), ParametroSimples.CONECTOR_AND));
			filtroDebitoTipo.adicionarParametro(new MenorQue(FiltroDebitoTipo.VALOR_LIMITE, Util.formatarMoedaRealparaBigDecimal(intervaloValorLimiteFinal)));
		}
		
				
		// erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		//carrega os objetos necessários da pesquisa
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

		//pesquisa a coleção de tipos débitos, de acordo com os parâmetros existentes no filtro 
		//Collection colecaoTipoDebitos = fachada.pesquisar(filtroDebitoTipo,DebitoTipo.class.getName());

		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroDebitoTipo, DebitoTipo.class.getName());
		Collection colecaoTipoDebitos = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//se a pesquisa não retornou nenhum tipo de débito 
		if (colecaoTipoDebitos == null || colecaoTipoDebitos.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Tipo de Débito");
			
			//se a pesquisa retornou uma quantidade de registros maior que a permitida para a pesquisa
		} else if (colecaoTipoDebitos.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			//muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			//manda a coleção de tipos de débitos pesquisados para a págian de sucesso na sessão
			sessao.setAttribute("colecaoTipoDebitos", colecaoTipoDebitos);
		}

		//retorna o mapeamento contido na variável retorno
		return retorno;
	}

}

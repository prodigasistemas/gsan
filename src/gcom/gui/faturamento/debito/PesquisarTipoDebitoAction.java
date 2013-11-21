/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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

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
package gcom.gui.cobranca;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoACobrarCategoriaPK;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade a emissão do extrato de débitos do imóvel
 * 
 * @author Vivianne Sousa
 * @date 24/08/2007
 */
public class EmissaoExtratoDebitoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("emissaoExtratoDebito");
		Fachada fachada = Fachada.getInstancia();
		
		DebitoCreditoDadosSelecaoExtratoActionForm form = 
			(DebitoCreditoDadosSelecaoExtratoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		String idImovel = form.getIdImovel();
		String indicadorIncluirAcrescimosImpontualidade = form.getIndicadorIncluirAcrescimosImpontualidade();
		String indicadorTaxaCobranca = form.getIndicadorTaxaCobranca();
		
		Collection<ContaValoresHelper> colecaoContas =  null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento = null;
		Collection<DebitoACobrar> colecaoDebitosACobrar = null;
		Collection<CreditoARealizar> colecaoCreditoARealizar = null;
		Collection<DebitoACobrar> colecaoDebitosACobrarParcelamento = null;
		Collection<CreditoARealizar> colecaoCreditoARealizarParcelamento = null;
		Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento = null;
		Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento = null;
		BigDecimal valorAcrescimosImpontualidade = new BigDecimal("0.00");
		BigDecimal valorDocumento = new BigDecimal("0.00");
		BigDecimal valorDesconto =  new BigDecimal("0.00");
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");
		
		String idsContas = httpServletRequest.getParameter("conta");
		String idsDebitos = httpServletRequest.getParameter("debito");
		String idsCreditos = httpServletRequest.getParameter("credito");
		String idsGuias = httpServletRequest.getParameter("guiaPagamento");
		String idsParcelamentos = httpServletRequest.getParameter("parcelamento");
		
		Object[] contas = this.obterContasSelecionadas(idsContas, sessao);
		Object[] debitos = this.obterDebitosSelecionados(idsDebitos, sessao);
        Object[] creditos = this.obterCreditosSelecionadas(idsCreditos, sessao);
        Object[] guias = this.obterGuiasSelecionadas(idsGuias, sessao);		
        Object[] parcelamentos = this.obterParcelamentosSelecionados(idsParcelamentos, sessao);
        
        //ANTECIPAÇÃO DE PARCELAS DE PARCELAMENTO
        Map<String, String[]> requestAntecipacaoParcelasMap = httpServletRequest.getParameterMap();
        
        Object[] parcelasAntecipacaoParcelamento = this.obterAntecipacaoParcelasParcelamentosSelecionados(
        sessao, requestAntecipacaoParcelasMap, fachada);
        
		/*
		 * TOTALIZANDO O VALOR DE CADA TIPO DE DOCUMENTO SELECIONADO, EM SEPERADO
		 */
        BigDecimal valorTotalConta = BigDecimal.ZERO;
		BigDecimal valorTotalDebitoACobrar = BigDecimal.ZERO;
		BigDecimal valorTotalCreditoARealizar = BigDecimal.ZERO;
		BigDecimal valorTotalGuiaPagamento = BigDecimal.ZERO;
		BigDecimal valorTotalDebitoParcelamento = BigDecimal.ZERO;
		BigDecimal valorTotalCreditoParcelamento = BigDecimal.ZERO;
		BigDecimal valorTotalAntecipacaoDebitoParcelamento = BigDecimal.ZERO;
		BigDecimal valorTotalAntecipacaoCreditoParcelamento = BigDecimal.ZERO;
		
        //CONTAS
		if(contas != null){
        	colecaoContas = (Collection)contas[0];
        	valorTotalConta = (BigDecimal)contas[1];
        	
        	if(contas[2] != null && 
        	   !indicadorIncluirAcrescimosImpontualidade.equals(CobrancaDocumento.NAO_INCLUIR_ACRESCIMOS)){
        		valorAcrescimosImpontualidade = (BigDecimal)contas[2];
        	}
        	
        	valorAtualizacaoMonetaria = (BigDecimal)contas[3];
			valorJurosMora =  (BigDecimal)contas[4];
			valorMulta = (BigDecimal)contas[5];
        }
		
		//DÉBITOS A COBRAR
        if(debitos != null){
        	colecaoDebitosACobrar = (Collection)debitos[0];
        	valorTotalDebitoACobrar = (BigDecimal)debitos[1];
        }
        
        
        //CRÉDITOS A REALIZAR
        if(creditos != null){
        	colecaoCreditoARealizar = (Collection)creditos[0];
        	valorTotalCreditoARealizar = (BigDecimal)creditos[1];
        }
        
        //GUIAS DE PAGAMENTO
        if(guias != null){
        	colecaoGuiasPagamento = (Collection)guias[0];
        	valorTotalGuiaPagamento = (BigDecimal)guias[1];
        	
        	valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add((BigDecimal)guias[2]);
			valorJurosMora = valorJurosMora.add((BigDecimal)guias[3]);
			valorMulta = valorMulta.add((BigDecimal)guias[4]);
        }
        
        //PARCELAMENTOS
        if(parcelamentos != null){
        	colecaoDebitosACobrarParcelamento = (Collection)parcelamentos[0];
        	valorTotalDebitoParcelamento = (BigDecimal)parcelamentos[1];
        	
        	if(colecaoDebitosACobrarParcelamento != null){
        		if(colecaoDebitosACobrar == null){
        			colecaoDebitosACobrar = new ArrayList();
        		}
        		colecaoDebitosACobrar.addAll(colecaoDebitosACobrarParcelamento);
        	}
        	
        	colecaoCreditoARealizarParcelamento = (Collection)parcelamentos[2];
        	valorTotalCreditoParcelamento = (BigDecimal)parcelamentos[3];
        	
        	if(colecaoCreditoARealizarParcelamento != null){
        		if(colecaoCreditoARealizar == null){
        			colecaoCreditoARealizar = new ArrayList();
        		}
        		colecaoCreditoARealizar.addAll(colecaoCreditoARealizarParcelamento);
        	}
        }
        
        //ANTECIPAÇÃO PARCELAS DE PARCELAMENTO
        if (parcelasAntecipacaoParcelamento != null){
        	
        	colecaoAntecipacaoDebitosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) parcelasAntecipacaoParcelamento[0];
        	valorTotalAntecipacaoDebitoParcelamento = (BigDecimal) parcelasAntecipacaoParcelamento[1];
        	colecaoAntecipacaoCreditosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) parcelasAntecipacaoParcelamento[2];
        	valorTotalAntecipacaoCreditoParcelamento = (BigDecimal) parcelasAntecipacaoParcelamento[3];
        }
        
        //TOTALIZANDO O VALOR DO DOCUMENTO DE COBRANÇA QUE SERÁ GERADO
        valorDocumento = valorTotalConta.add(valorTotalDebitoACobrar);
        valorDocumento = valorDocumento.add(valorTotalGuiaPagamento);
        valorDocumento = valorDocumento.add(valorTotalDebitoParcelamento);
        valorDocumento = valorDocumento.add(valorTotalAntecipacaoDebitoParcelamento);
        valorDocumento = valorDocumento.subtract(valorTotalCreditoARealizar);
        valorDocumento = valorDocumento.subtract(valorTotalCreditoParcelamento);
        valorDocumento = valorDocumento.subtract(valorTotalAntecipacaoCreditoParcelamento);
        
        
        //TOTALIZANDO O VALOR DOS CRÉDITOS A REALIZAR
        BigDecimal valorCreditoDocumento = valorTotalCreditoARealizar.add(valorTotalCreditoParcelamento);
        valorCreditoDocumento = valorCreditoDocumento.add(valorTotalAntecipacaoCreditoParcelamento);
        
		
        if(indicadorIncluirAcrescimosImpontualidade.equals(CobrancaDocumento.INCLUIR_ACRESCIMOS)){
        	valorDocumento = valorDocumento.add(valorAcrescimosImpontualidade);
        }
        
		if(indicadorIncluirAcrescimosImpontualidade.equals(CobrancaDocumento.INCLUIR_ACRESCIMOS_COM_DESCONTO)){
			valorDesconto = valorDesconto.add(valorAcrescimosImpontualidade);
		}
		
//		valorDesconto = valorDesconto.add(valorTotalCreditoARealizar);
//		valorDesconto = valorDesconto.add(valorTotalCreditoParcelamento);
		Short indicadorContasRevisao = 2; 
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		if(sistemaParametro.getResolucaoDiretoria() != null){
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			calcularDescontos(form,	fachada, sistemaParametro, usuarioLogado,
					valorDocumento, valorTotalDebitoParcelamento, 
					valorCreditoDocumento, colecaoGuiasPagamento, 
					colecaoContas, valorMulta, valorJurosMora, 
					valorAtualizacaoMonetaria);
			
	        valorDocumento = Util.formatarMoedaRealparaBigDecimal(form.getValorPagamentoAVista());
	        valorDesconto = Util.formatarMoedaRealparaBigDecimal(form.getValorDescontoPagamentoAVista());
	        indicadorContasRevisao = 1;
			
		}
	//Obtem o indicador de permissão de emissão de documentos
		FiltroImovel pesquisaImovel = new FiltroImovel();
		pesquisaImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel)));
		pesquisaImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.COBRANCA_SITUACAO_TIPO);
		Collection<Imovel> imoveis = fachada.pesquisar(pesquisaImovel, Imovel.class.getName());
		Imovel imov = imoveis.iterator().next();
		
		/*
		 * --Erivan Sousa--
		 * Impede que o extrato seja emitido caso o indicador cbsp_icemitedoccobranca 
		 * da tabela cobranca.cobranca_situacao_tipo seja igual a 2
		 */

		if(imov.getCobrancaSituacaoTipo() != null){
			if(imov.getCobrancaSituacaoTipo().getIndicadorEmitirDocumentoCobranca().equals(ConstantesSistema.NAO)){
				throw new  ActionServletException("atencao.extratonaoemitido_imovel_situacaoespecial");
			}
		}

				

		if(indicadorTaxaCobranca != null &&
				indicadorTaxaCobranca.equals(ConstantesSistema.SIM.toString())){
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel)));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			
			filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
					FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,
					Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR,2));

			filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));
			
			Collection<Imovel> imovelPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		    Imovel imovel = imovelPesquisado.iterator().next();
		   
//		    SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			BigDecimal valorTaxa = fachada.obterValorTaxaDocumentoCobranca(imovel, Short.valueOf(indicadorTaxaCobranca));
			valorDocumento = valorDocumento.add(valorTaxa);
			
			DebitoTipo debitoTipo = null;

			// Pesquisa o tipo de débito no sistema
			debitoTipo = fachada.pesquisarDebitoTipo(DebitoTipo.TAXA_COBRANCA);

			// Cria a variável que vai armazenar a situação de crédito/débito
			DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao();
			debitoCreditoSituacaoAtual.setId(DebitoCreditoSituacao.NORMAL);

			// Cria a variável que vai armazenar a forma de cobrança
			CobrancaForma cobrancaForma = new CobrancaForma();
			cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);

			/** Cria o débito a cobrar geral */
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			debitoACobrarGeral.setIndicadorHistorico(new Short("2"));
			debitoACobrarGeral.setUltimaAlteracao(new Date());
			Integer idDebitoACobrarGeral = (Integer) fachada.inserir(debitoACobrarGeral);

			// Cria o débito a cobrar
			// Seta as informações necessárias para criar o débito a cobrar
			DebitoACobrar debitoACobrar = new DebitoACobrar();
			debitoACobrar.setImovel(imovel);
			debitoACobrar.setDebitoTipo(debitoTipo);
			debitoACobrar.setGeracaoDebito(new Date());
			debitoACobrar.setAnoMesReferenciaDebito(null);
			
			Integer anoMesParametro = sistemaParametro.getAnoMesArrecadacao();
			Integer anoMesCorrente = Util.formataAnoMes(new Date());
			
			if(anoMesParametro > anoMesCorrente){
				debitoACobrar.setAnoMesReferenciaContabil(anoMesParametro);				
			}else{
				debitoACobrar.setAnoMesReferenciaContabil(anoMesCorrente);
			}
			
			debitoACobrar.setAnoMesCobrancaDebito(sistemaParametro.getAnoMesArrecadacao());
			debitoACobrar.setValorDebito(valorTaxa);
			debitoACobrar.setNumeroPrestacaoDebito((new Short("1")).shortValue());
			debitoACobrar.setNumeroPrestacaoCobradas((new Short("0")).shortValue());
			debitoACobrar.setLocalidade(imovel.getLocalidade());
			debitoACobrar.setQuadra(imovel.getQuadra());
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
			debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
			debitoACobrar.setNumeroLote(imovel.getLote());
			debitoACobrar.setNumeroSubLote(imovel.getSubLote());
			debitoACobrar.setPercentualTaxaJurosFinanciamento(new BigDecimal("0.00"));
			debitoACobrar.setRegistroAtendimento(null);
			debitoACobrar.setOrdemServico(null);
			debitoACobrar.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo());
			debitoACobrar.setLancamentoItemContabil(debitoTipo.getLancamentoItemContabil());
			debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
			debitoACobrar.setDebitoCreditoSituacaoAnterior(null);
			debitoACobrar.setParcelamentoGrupo(null);
			debitoACobrar.setCobrancaForma(cobrancaForma);
			debitoACobrar.setUltimaAlteracao(new Date());
			debitoACobrar.setId(idDebitoACobrarGeral);
			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

			fachada.inserir(debitoACobrar);
			
			// [UC0108] Obter Quantidade de Economias por Categoria
			Collection<Categoria> colecaoCategoriasImovel = fachada.obterQuantidadeEconomiasCategoria(imovel);

			// [UC0185] Obter Valor por Categoria, passando o valor da taxa
			Collection<BigDecimal> colecaoValorePorCategoria = fachada.obterValorPorCategoria(colecaoCategoriasImovel, valorTaxa);

			// Cria as iterações de categoria e valor
			Iterator iteratorCategoria = colecaoCategoriasImovel.iterator();
			Iterator iteratorValorPorCategoria = colecaoValorePorCategoria.iterator();

			// Inclui na tabela DEBITO_A_COBRAR_CATEGORIA a(s) categoria(s) e
			// sua(s) respectiva(s) quantidade(s) de economia retornados pels
			//[UC0108] e os valores retornados pelo [UC0185] para cada categoria
			while (iteratorCategoria.hasNext()) {
				// Recupera a categoria
				Categoria categoria = (Categoria) iteratorCategoria.next();

				// Recupera o valor da categoria
				BigDecimal valorPorCategoria = (BigDecimal) iteratorValorPorCategoria.next();

				// Cria o débito a cobrar categoria
				DebitoACobrarCategoria debitoACobrarCategoria = new DebitoACobrarCategoria();

				DebitoACobrarCategoriaPK debitoACobrarCategoriaPK = new DebitoACobrarCategoriaPK(debitoACobrar, categoria);
				debitoACobrarCategoria.setComp_id(debitoACobrarCategoriaPK);
				debitoACobrarCategoria.setQuantidadeEconomia(categoria.getQuantidadeEconomiasCategoria());
				debitoACobrarCategoria.setValorCategoria(valorPorCategoria);
				debitoACobrarCategoria.setUltimaAlteracao(new Date());

				// Inserindo o DEBITO_A_COBRAR_CATEGORIA no banco de dados
				fachada.inserir(debitoACobrarCategoria);
			}

			if(colecaoDebitosACobrar == null){
				colecaoDebitosACobrar = new ArrayList();
    		}
			colecaoDebitosACobrar.add(debitoACobrar);
			
		}
		
		
		if(valorDocumento.compareTo(new BigDecimal("0.00")) < 0){
			throw new ActionServletException("atencao.resultado.negativo");
		}
		
        sessao.setAttribute("colecaoContasExtrato",colecaoContas);
        sessao.setAttribute("colecaoGuiasPagamentoExtrato",colecaoGuiasPagamento);
        sessao.setAttribute("colecaoDebitosACobrarExtrato",colecaoDebitosACobrar);
        sessao.setAttribute("colecaoCreditoARealizarExtrato",colecaoCreditoARealizar);
        sessao.setAttribute("valorAcrescimosImpontualidadeExtrato" ,valorAcrescimosImpontualidade);
        sessao.setAttribute("valorDocumentoExtrato" ,valorDocumento);
        sessao.setAttribute("valorDescontoExtrato" ,valorDesconto);
        sessao.setAttribute("valorCreditoARealizar" ,valorCreditoDocumento);
        sessao.setAttribute("idImovelExtrato",idImovel);
        sessao.setAttribute("nomeClienteExtrato",form.getNomeClienteUsuarioImovel());
        sessao.setAttribute("cpfCnpj", form.getCpfCnpj() );
        sessao.setAttribute("colecaoAntecipacaoDebitosDeParcelamento",colecaoAntecipacaoDebitosDeParcelamento);
        sessao.setAttribute("colecaoAntecipacaoCreditosDeParcelamento",colecaoAntecipacaoCreditosDeParcelamento);
        sessao.setAttribute("indicadorContasRevisao",indicadorContasRevisao);	
        
		return retorno;
	}
	
	
	private Object[] obterContasSelecionadas(String idsContas, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<ContaValoresHelper> colecaoContas = null;
		BigDecimal valorTotalConta = BigDecimal.ZERO;
		BigDecimal valorTotalAcrescimoImpontualidade = BigDecimal.ZERO;
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");
		
		if (idsContas != null && !idsContas.equals("")){
			retorno = new Object[6];
			colecaoContas = new ArrayList();
			
			Collection colecaoContasSessao = (Collection) sessao.getAttribute("colecaoConta");
			Iterator itColecaoContasSessao = colecaoContasSessao.iterator();
			ContaValoresHelper contaValoresHelper = null;
			
			String[] idsContasArray = idsContas.split(",");
			
			while (itColecaoContasSessao.hasNext()){
				
				contaValoresHelper = (ContaValoresHelper) itColecaoContasSessao.next();
				
				for(int x=0; x<idsContasArray.length; x++){
					
					if (contaValoresHelper.getConta().getId().equals(new Integer(idsContasArray[x]))){
						colecaoContas.add(contaValoresHelper);
						valorTotalConta = valorTotalConta.add(contaValoresHelper.getValorTotalConta());
						valorTotalAcrescimoImpontualidade = valorTotalAcrescimoImpontualidade.add(
								contaValoresHelper.getValorTotalContaValoresParcelamento());
						
						if (contaValoresHelper.getConta().getValorImposto() != null) {
							valorTotalConta = valorTotalConta.subtract(contaValoresHelper.getConta().getValorImposto());
						}						
						
						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						
					}
				}
			}
			retorno[0] = colecaoContas;
			retorno[1] = valorTotalConta;
			retorno[2] = valorTotalAcrescimoImpontualidade;
			retorno[3] = valorAtualizacaoMonetaria;
			retorno[4] = valorJurosMora;
			retorno[5] = valorMulta;
		}

		return retorno;
	}
	
	private Object[] obterDebitosSelecionados(String idsDebitos, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<DebitoACobrar> colecaoDebitos = null;
		BigDecimal valorTotalDebitoACobrar = BigDecimal.ZERO;
		
		if (idsDebitos != null && !idsDebitos.equals("")){
			retorno = new Object[2];
			colecaoDebitos = new ArrayList();
			
			Collection colecaoDebitosSessao = (Collection) sessao.getAttribute("colecaoDebitoACobrar");
			Iterator itColecaoDebitosSessao = colecaoDebitosSessao.iterator();
			DebitoACobrar debitoACobrar = null;
			
			String[] idsDebitosArray = idsDebitos.split(",");
			
			while (itColecaoDebitosSessao.hasNext()){
				
				debitoACobrar = (DebitoACobrar) itColecaoDebitosSessao.next();
				
				for(int x=0; x<idsDebitosArray.length; x++){
					
					if (debitoACobrar.getId().equals(new Integer(idsDebitosArray[x]))){
						colecaoDebitos.add(debitoACobrar);
						valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(debitoACobrar.getValorTotalComBonus());
						
					}
				}
			}
			retorno[0] = colecaoDebitos;
			retorno[1] = valorTotalDebitoACobrar;
		}

		return retorno;
	}
	
	private Object[] obterCreditosSelecionadas(String idsCreditos, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<CreditoARealizar> colecaoCreditos = null;
		BigDecimal valorTotalCreditoARealizar = BigDecimal.ZERO;
		
		if (idsCreditos != null && !idsCreditos.equals("")){
			retorno = new Object[2];
			colecaoCreditos = new ArrayList();
			
			Collection colecaoCreditosSessao = (Collection) sessao.getAttribute("colecaoCreditoARealizar");
			Iterator itColecaoCreditosSessao = colecaoCreditosSessao.iterator();
			CreditoARealizar creditoARealizar = null;
			
			String[] idsCreditosArray = idsCreditos.split(",");
			
			while (itColecaoCreditosSessao.hasNext()){
				
				creditoARealizar = (CreditoARealizar) itColecaoCreditosSessao.next();
				
				for(int x=0; x<idsCreditosArray.length; x++){
					
					if (creditoARealizar.getId().equals(new Integer(idsCreditosArray[x]))){
						colecaoCreditos.add(creditoARealizar);
						valorTotalCreditoARealizar = valorTotalCreditoARealizar.add(creditoARealizar.getValorTotalComBonus());
						
					}
				}
			}
			retorno[0] = colecaoCreditos;
			retorno[1] = valorTotalCreditoARealizar;
		}
		
		return retorno;
	}
	
private Object[] obterGuiasSelecionadas(String idsGuias, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuias = null;
		BigDecimal valorTotalGuiaPagamento = BigDecimal.ZERO;
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");
		
		if (idsGuias != null && !idsGuias.equals("")){
			retorno = new Object[5];
			colecaoGuias = new ArrayList();
			
			Collection colecaoGuiasSessao = (Collection) sessao.getAttribute("colecaoGuiaPagamento");
			Iterator itColecaoGuiasSessao = colecaoGuiasSessao.iterator();
			GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = null;
			
			String[] idsGuiasArray = idsGuias.split(",");
			
			while (itColecaoGuiasSessao.hasNext()){
				
				guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) itColecaoGuiasSessao.next();
				
				for(int x=0; x<idsGuiasArray.length; x++){
					
					if (guiaPagamentoValoresHelper.getGuiaPagamento().getId().equals(new Integer(idsGuiasArray[x]))){
						colecaoGuias.add(guiaPagamentoValoresHelper);
						valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(
								guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
						
						if (guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorJurosMora() != null && !guiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(guiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorMulta() != null	&& !guiaPagamentoValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(guiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						
					}
				}
			}
			retorno[0] = colecaoGuias;
			retorno[1] = valorTotalGuiaPagamento;
			retorno[2] = valorAtualizacaoMonetaria;
			retorno[3] = valorJurosMora;
			retorno[4] = valorMulta;
		}
		
		return retorno;
	}

	
	private Object[] obterParcelamentosSelecionados(String idsParcelamentos, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<DebitoACobrar> colecaoDebitoFinal = null;
		Collection<CreditoARealizar> colecaoCreditoFinal = null;
		BigDecimal valorTotalDebito = BigDecimal.ZERO;
		BigDecimal valorTotalCredito = BigDecimal.ZERO;
		
		if (idsParcelamentos != null && !idsParcelamentos.equals("")){
			retorno = new Object[4];
			colecaoDebitoFinal = new ArrayList();
			colecaoCreditoFinal = new ArrayList();
			
			Collection colecaoDebitoCreditoParcelamentoSessao = (Collection) sessao.getAttribute("colecaoDebitoCreditoParcelamento");
			Iterator itColecaoDebitoCreditoParcelamentoSessao = colecaoDebitoCreditoParcelamentoSessao.iterator();
			DebitoCreditoParcelamentoHelper debitoCreditoParcelamentoHelper = null;
			
			String[] idsParcelamentoArray = idsParcelamentos.split(",");
			
			while (itColecaoDebitoCreditoParcelamentoSessao.hasNext()){
				
				debitoCreditoParcelamentoHelper = (DebitoCreditoParcelamentoHelper) itColecaoDebitoCreditoParcelamentoSessao.next();
				
				for(int x=0; x<idsParcelamentoArray.length; x++){
					
					if (debitoCreditoParcelamentoHelper.getParcelamento().getId().equals(new Integer(idsParcelamentoArray[x]))){
						Collection<DebitoACobrar> colecaoDebito = null;
						Collection<CreditoARealizar> colecaoCredito = null;
						
						if(debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento() != null &&
								!debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento().isEmpty()){
							colecaoCredito = debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento();	
						
							Iterator iterCredito = colecaoCredito.iterator();
							while (iterCredito.hasNext()) {
								CreditoARealizar creditoARealizar = (CreditoARealizar) iterCredito.next();
								colecaoCreditoFinal.add(creditoARealizar);
								valorTotalCredito = valorTotalCredito.add(creditoARealizar.getValorTotalComBonus());
							}
							
						}

						if(debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento() != null &&
								!debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento().isEmpty()){
							colecaoDebito = debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento();
							
							Iterator iterDebito = colecaoDebito.iterator();
							while (iterDebito.hasNext()) {
								DebitoACobrar debitoACobrar = (DebitoACobrar) iterDebito.next();
								colecaoDebitoFinal.add(debitoACobrar);
								valorTotalDebito = valorTotalDebito.add(debitoACobrar.getValorTotalComBonus());
							}
							
						}
						
					}
				}
			}
			retorno[0] = colecaoDebitoFinal;
			retorno[1] = valorTotalDebito;
			retorno[2] = colecaoCreditoFinal;
			retorno[3] = valorTotalCredito;
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0630] Solicitar Emissão do Extrato de Débitos 
	 *
	 * @author Raphael Rossiter
	 * @date 30/03/2010
	 *
	 * @param sessao
	 * @param requestMap
	 * @param fachada
	 * @return Object[]
	 */
	private Object[] obterAntecipacaoParcelasParcelamentosSelecionados(HttpSession sessao, 
			Map<String, String[]> requestMap, Fachada fachada){
		
		Object[] retorno = null;
		
		//PARCELAMENTOS DISPONIBILIZADOS PARA O USUÁRIO SELECIONAR
		Collection colecaoDebitoCreditoParcelamentoSessao = (Collection) 
		sessao.getAttribute("colecaoDebitoCreditoParcelamento");
		
		if (colecaoDebitoCreditoParcelamentoSessao != null && 
			!colecaoDebitoCreditoParcelamentoSessao.isEmpty()){
			
			Iterator itColecaoDebitoCreditoParcelamentoSessao = colecaoDebitoCreditoParcelamentoSessao.iterator();
			DebitoCreditoParcelamentoHelper debitoCreditoParcelamentoHelper = null;
			
			BigDecimal valorTotalDebito = BigDecimal.ZERO;
			BigDecimal valorTotalCredito = BigDecimal.ZERO;
			
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitos = new ArrayList();
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditos = new ArrayList();
			
			while (itColecaoDebitoCreditoParcelamentoSessao.hasNext()){
				
				debitoCreditoParcelamentoHelper = (DebitoCreditoParcelamentoHelper) 
				itColecaoDebitoCreditoParcelamentoSessao.next();
			
				if (requestMap.get("parc" + debitoCreditoParcelamentoHelper.getParcelamento().getId()) != null) {
					
					//QUANTIDADE DE PARCELAS QUE SERÃO ANTECIPADAS
					String qtdAntecipacaoParcelas = (requestMap.get("parc" + 
					debitoCreditoParcelamentoHelper.getParcelamento().getId()))[0];
					
					if(qtdAntecipacaoParcelas != null && !qtdAntecipacaoParcelas.equals("")){
						
						//INICIALIZANDO O OBJETO DE RETORNO
						if (retorno == null){
							retorno = new Object[4];
						}
						
						//QUANTIDADE DE PARCELAS QUE SERÃO ANTECIPADAS
						debitoCreditoParcelamentoHelper.setQuantidadeAntecipacaoParcelas(
						Integer.valueOf(qtdAntecipacaoParcelas));
						
						/*
						 * SELECIONANDO OS DÉBITOS RELACIONADOS AO PARCELAMENTO E CALCULANDO O VALOR QUE SERÁ
						 * COLOCADO NO EXTRATO DE ACORDO COM A QUANTIDADE DE PARCELAS QUE FOI INFORMADA PARA
						 * ANTECIPAÇÃO.
						 */
						if (debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento() != null &&
					       !debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento().isEmpty()){
						
							Collection colecaoDebito = debitoCreditoParcelamentoHelper
						   .getColecaoDebitoACobrarParcelamento();
						
						   Iterator iterDebito = colecaoDebito.iterator();
						
						   while (iterDebito.hasNext()) {
							
							   DebitoACobrar debitoACobrar = (DebitoACobrar) iterDebito.next();
							
							   /*
							    * [UC0630] Solicitar Emissão do Extrato de Débitos
							    * [FS0003] – Quantidade de Parcelas Informadas Inválida
							    */
							   fachada.verificarQuantidadeParcelasInformada(debitoACobrar, 
							   Short.valueOf(qtdAntecipacaoParcelas));
							   
							   //CALCULANDO O VALOR QUE SERÁ ANTECIPADO
							   valorTotalDebito = valorTotalDebito.add(debitoACobrar
							   .getValorAntecipacaoParcela(Integer.valueOf(qtdAntecipacaoParcelas)));
						   }
						   
						   //DÉBITOS A COBRAR QUE FARÃO PARTE DO EXTRATO DE DÉBITO
						   colecaoAntecipacaoDebitos.add(debitoCreditoParcelamentoHelper);
						}
						
						
						/*
						 * SELECIONANDO OS CRÉDITOS RELACIONADOS AO PARCELAMENTO E CALCULANDO O VALOR QUE SERÁ
						 * COLOCADO NO EXTRATO DE ACORDO COM A QUANTIDADE DE PARCELAS QUE FOI INFORMADA PARA
						 * ANTECIPAÇÃO.
						 */
						if(debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento() != null &&
						   !debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento().isEmpty()){
							
							Collection colecaoCredito = debitoCreditoParcelamentoHelper
							.getColecaoCreditoARealizarParcelamento();
							
							Iterator iterCredito = colecaoCredito.iterator();
							
							while (iterCredito.hasNext()) {
								
								CreditoARealizar creditoARealizar = (CreditoARealizar) iterCredito.next();
								
								//CALCULANDO O VALOR QUE SERÁ ANTECIPADO
								valorTotalCredito = valorTotalCredito.add(creditoARealizar
								.getValorAntecipacaoParcela(Integer.valueOf(qtdAntecipacaoParcelas)));
							}
							
							//CRÉDITOS A REALIZAR QUE FARÃO PARTE DO EXTRATO DE DÉBITO
							colecaoAntecipacaoCreditos.add(debitoCreditoParcelamentoHelper);
						}
					}
				}
			}
			
			if (retorno != null){
				
				retorno[0] = colecaoAntecipacaoDebitos;
				retorno[1] = valorTotalDebito;
				retorno[2] = colecaoAntecipacaoCreditos;
				retorno[3] = valorTotalCredito;
			}
		}
		
		return retorno;
	}

	
	private void calcularDescontos(DebitoCreditoDadosSelecaoExtratoActionForm form,
			Fachada fachada, SistemaParametro sistemaParametro, Usuario usuarioLogado,
			BigDecimal valorDebitoTotalAtualizado, BigDecimal valorTotalDebitoParcelamento, 
			BigDecimal valorCreditoDocumento, Collection colecaoGuiasPagamento, 
			Collection colecaoContas, BigDecimal valorMulta, BigDecimal valorJurosMora, 
			BigDecimal valorAtualizacaoMonetaria){
		
		ImovelPerfil imovelPerfil = fachada.obterImovelPerfil(new Integer(form.getIdImovel())); 
		Short numeroReparcelamentoConsecutivos = fachada.consultarNumeroReparcelamentoConsecutivosImovel(new Integer(form.getIdImovel()));
		
		if(numeroReparcelamentoConsecutivos == null){
			numeroReparcelamentoConsecutivos = new Short("0");
		}
		
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = 
			new IndicadoresParcelamentoHelper();
		
		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer("3"));
		
		//CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
		ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
				sistemaParametro.getResolucaoDiretoria().getId(), 
				new Integer(form.getIdImovel()), 
				new BigDecimal("0.00"), 
				new Integer(form.getIdLigacaoAguaSituacaoImovel()), 
				new Integer(form.getIdLigacaoEsgotoSituacaoImovel()), 
				imovelPerfil.getId(), 
				"01/0001", 
				new Integer(form.getIndicadorRestabelecimento()), 
				colecaoContas, 
				valorDebitoTotalAtualizado, 
				valorMulta, 
				valorJurosMora, 
				valorAtualizacaoMonetaria, 
				new Integer(numeroReparcelamentoConsecutivos.toString()), 
				colecaoGuiasPagamento, 
				usuarioLogado, 
				valorTotalDebitoParcelamento, 
				Util.formatarMesAnoComBarraParaAnoMes("01/0001"),
				Util.formatarMesAnoComBarraParaAnoMes("12/9999"), 
				indicadoresParcelamentoHelper,
				valorCreditoDocumento);
		
		NegociacaoOpcoesParcelamentoHelper negociacaoOpcoesParcelamentoHelper = 
			fachada.calcularValorDosDescontosPagamentoAVista(helper);
		
		BigDecimal valorTotalDescontoPagamentoAVista = negociacaoOpcoesParcelamentoHelper.getValorTotalDescontoPagamentoAVista();
		BigDecimal valorPagamentoAVista = valorDebitoTotalAtualizado.subtract(valorTotalDescontoPagamentoAVista);
		
		form.setValorDescontoPagamentoAVista(Util.formatarMoedaReal(valorTotalDescontoPagamentoAVista));
		form.setValorPagamentoAVista(Util.formatarMoedaReal(valorPagamentoAVista));
		
	}
	

	public static void main(String[] args) {
		System.out.println(Util.formataAnoMes(new Date()));
	}

}

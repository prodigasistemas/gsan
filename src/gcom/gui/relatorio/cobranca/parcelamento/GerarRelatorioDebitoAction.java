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
package gcom.gui.relatorio.cobranca.parcelamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.parcelamento.ExtratoDebitoRelatorioHelper;
import gcom.relatorio.cobranca.parcelamento.RelatorioDebito;
import gcom.relatorio.cobranca.parcelamento.RelatorioDebitoHelper;
import gcom.relatorio.cobranca.parcelamento.RelatorioExtratoDebito;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * [UC0444] Gerar e Emitir Extrato de Débito
 * @author Vivianne Sousa
 * @date 07/09/2006
 */

public class GerarRelatorioDebitoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			
		//Linha 2
		String inscricao = "";    	
		String nomeUsuario = "";         
		String matricula = ""; 	
		String cpfCnpj = "";
		
		//Linha 3
		String enderecoImovel = "";

		Imovel imovel = null;
		Collection<ContaValoresHelper> colecaoContas =  null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento = null;
		Collection<DebitoACobrar> colecaoDebitosACobrar = null;
		Collection<CreditoARealizar> colecaoCreditoARealizar = null;
		Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento = null;
		Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento = null;
		BigDecimal valorAcrescimosImpontualidade = new BigDecimal("0.00");
		BigDecimal valorDocumento = new BigDecimal("0.00");
		BigDecimal valorDesconto =  new BigDecimal("0.00");
		BigDecimal valorDescontoCredito =  new BigDecimal("0.00");
		Short indicadorGeracaoTaxaCobranca = new Short("2") ;  // no caso do parcelamento sempre 2
		
		BigDecimal parcelamentoValorDebitoACobrarServico = null;
		BigDecimal parcelamentoValorDebitoACobrarParcelamento = null;
		
		Short indicadorContasRevisao = 2; 
		
		ResolucaoDiretoria resolucaoDiretoria = null;
		
		if(httpServletRequest.getParameter("relatorioDebito") != null){
			//relatorio chamado a partir da tela de relatório de debito
			Integer idImovel = new Integer((String)sessao.getAttribute("idImovelRelatorio"));
			imovel = fachada.pesquisarImovel(idImovel);
			
			colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContasRelatorio");
			colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiasPagamentoRelatorio");
			colecaoDebitosACobrar = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitosACobrarRelatorio");
			colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizarRelatorio");
			colecaoAntecipacaoDebitosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) sessao.getAttribute("colecaoAntecipacaoDebitosDeParcelamentoRelatorio");
        	colecaoAntecipacaoCreditosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) sessao.getAttribute("colecaoAntecipacaoCreditosDeParcelamentoRelatorio");
			valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidadeRelatorio");
			valorDocumento = (BigDecimal) sessao.getAttribute("valorDocumentoRelatorio");
			valorDesconto =  (BigDecimal) sessao.getAttribute("valorDescontoRelatorio");
			valorDescontoCredito = (BigDecimal)sessao.getAttribute("valorCreditoARealizarRelatorio");
			//Linha 2
			 inscricao = imovel.getInscricaoFormatada();    	
			 nomeUsuario = (String)sessao.getAttribute("nomeClienteRelatorio");   
			 matricula = imovel.getId().toString(); 	
			 cpfCnpj = (String)sessao.getAttribute("cpfCnpjRelatorio");
			 
			
			 //Linha 3
			 try {
				enderecoImovel = fachada.pesquisarEnderecoFormatado(idImovel);
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
		
		if (valorAcrescimosImpontualidade == null){
			valorAcrescimosImpontualidade = BigDecimal.ZERO;
		}
		
		if (valorDocumento.compareTo(new BigDecimal("0.00")) < 0){
			throw new ActionServletException("atencao.resultado.extrato.negativo");
		}

		if(valorDesconto == null){
			valorDesconto = new BigDecimal("0.00");
		}
		
		Date maiorDataVencimentoContas = null;
		
		//Vivianne Sousa 11/12/2008 analista:Adriano Britto
		//verifica se as contas em revisão vão aparecer no extrato de debito
		//na tela de parcelamento o usuario tem a opção de escolher 
		//se as contas em revisão entram no parcelamento
		if(indicadorContasRevisao.equals(ConstantesSistema.NAO)){
			//Vivianne Sousa - 02/07/2008
			Map mapContas =  retirarContasEmRevisaoDeColecaoContas(colecaoContas);
			colecaoContas =  (Collection)mapContas.get("colecaoContasSemContasEmRevisao");
			BigDecimal valorTotalContasRevisao = (BigDecimal) mapContas.get("valorTotalContasRevisao");
			valorDocumento = valorDocumento.subtract(valorTotalContasRevisao);
			maiorDataVencimentoContas = (Date)mapContas.get("maiorDataVencimentoContas");
		}else{
			maiorDataVencimentoContas = obterMaiorDataVencimentoContas(colecaoContas);
		}
		
		Integer numeroParcelasAntecipadasCredito = 0;
		Integer numeroParcelasAntecipadasDebito = 0;

		if (colecaoAntecipacaoCreditosDeParcelamento != null
				&& !colecaoAntecipacaoCreditosDeParcelamento.isEmpty())
			numeroParcelasAntecipadasCredito = colecaoAntecipacaoCreditosDeParcelamento
					.size();
		if (colecaoAntecipacaoDebitosDeParcelamento != null
				&& !colecaoAntecipacaoDebitosDeParcelamento.isEmpty())
			numeroParcelasAntecipadasDebito = colecaoAntecipacaoDebitosDeParcelamento
					.size();

		RelatorioDebitoHelper relatorioDebitoHelper = new RelatorioDebitoHelper(
				colecaoContas, colecaoGuiasPagamento, colecaoDebitosACobrar,
				colecaoCreditoARealizar, numeroParcelasAntecipadasDebito,
				numeroParcelasAntecipadasCredito);
	
		/*ExtratoDebitoRelatorioHelper extratoDebitoRelatorioHelper = fachada.gerarEmitirExtratoDebito(
				imovel,indicadorGeracaoTaxaCobranca,colecaoContas,colecaoGuiasPagamento,colecaoDebitosACobrar,
				valorAcrescimosImpontualidade,valorDesconto,valorDocumento, 
				colecaoCreditoARealizar, null, resolucaoDiretoria, colecaoAntecipacaoDebitosDeParcelamento,
				colecaoAntecipacaoCreditosDeParcelamento);*/

		//CobrancaDocumento documentoCobranca = extratoDebitoRelatorioHelper.getDocumentoCobranca();
		
		//Linha 1
		Object[] pesquisaLocalidade = fachada.pesquisarLocalidadeSetorImovel(imovel.getId());
		String nomeLocalidade = (String) pesquisaLocalidade[0];
		//String nomeLocalidade = documentoCobranca.getLocalidade().getDescricao();

		//Linha 3
		//String seqDocCobranca = "" + documentoCobranca.getNumeroSequenciaDocumento();						
				
		//Linha 4		
		String situacaoAgua = "" + fachada.pesquisarIdLigacaoAguaSituacao(imovel.getId());//efetuarParcelamentoDebitosActionForm.get("situacaoAgua");	
		String situacaoEsgoto = "" + fachada.pesquisarIdLigacaoEsgotoSituacao(imovel.getId());//(String)efetuarParcelamentoDebitosActionForm.get("situacaoEsgoto");	
		String qtdResidencial = "";
		String qtdComercial = "";
		String qtdIndustrial = "";
		String qtdPublico = "";
		ImovelPerfil imovelPerfil= fachada.pesquisarImovelPerfilIdImovel(imovel.getId());
		String descPerfilImovel = "" + imovelPerfil.getDescricao();	
		String dataEmissao = Util.formatarData(new Date());

//		String dataValidade = Util.formatarData(documentoCobranca.getDataValidade(usuario));
//		Vivianne Sousa 15/09/2008
	/*	String dataValidade = Util.formatarData(fachada.
				obterDataValidadeDocumentoCobranca(documentoCobranca, usuario,maiorDataVencimentoContas));*/
		
	
		//Obter Quantidade de economias por categoria
		Collection colecaoCategorias = fachada.obterQuantidadeEconomiasCategoria(imovel);	
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
			Iterator iteratorColecaoCategorias = colecaoCategorias.iterator();
			Categoria categoria = null;

			while (iteratorColecaoCategorias.hasNext()) {
				categoria = (Categoria) iteratorColecaoCategorias.next();

				if (categoria.getId().equals(Categoria.RESIDENCIAL)) {
					qtdResidencial = "" + categoria.getQuantidadeEconomiasCategoria();
				} else if (categoria.getId().equals(Categoria.COMERCIAL)) {
					qtdComercial = "" + categoria.getQuantidadeEconomiasCategoria();
				} else if (categoria.getId().equals(Categoria.INDUSTRIAL)) {
					qtdIndustrial = "" + categoria.getQuantidadeEconomiasCategoria();
				} else if (categoria.getId().equals(Categoria.PUBLICO)) {
					qtdPublico = "" + categoria.getQuantidadeEconomiasCategoria();
				}
			}
		}
		
		// linhas 7 , 8, 9 e 10
		BigDecimal valorTotalContas = new BigDecimal("0.00") ;
		BigDecimal valorServicosAtualizacoes = new BigDecimal("0.00");
		
		String valorTotalContasString = "" ;
		String valorServicosAtualizacoesString = "";
		String valorDescontoString =  ""; 
		String valorTotalComDescontoString = "";
		
		if(colecaoContas != null && !colecaoContas.equals("")){
			Iterator contaIterator = colecaoContas.iterator();
			
			while(contaIterator.hasNext()) {
				ContaValoresHelper conta = (ContaValoresHelper) contaIterator.next();
				valorTotalContas = valorTotalContas.add(conta.getValorTotalConta());
			}
				
		}
		
		//valorTotalContas = extratoDebitoRelatorioHelper.getValorTotalConta();
		valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		//valor dos acrescimos por impontualidade +
		//valor total das guias de pagamento +
		//valor total restante dos debitos a cobrar 
		
		BigDecimal valorTotalRestanteDebitosACobrar = new BigDecimal("0.00");
		
	/*	if (parcelamentoValorDebitoACobrarServico != null && parcelamentoValorDebitoACobrarParcelamento != null){
			valorTotalRestanteDebitosACobrar = parcelamentoValorDebitoACobrarServico.add(parcelamentoValorDebitoACobrarParcelamento);
		}else{
			valorTotalRestanteDebitosACobrar = extratoDebitoRelatorioHelper.getValorTotalRestanteDebitosACobrar();
		}*/
		
		//debito com valor total restante equivale ao valor das prestaçõea antecipadas e ao total de parcelas bonus 
		BigDecimal somaPrestacao = new BigDecimal("0.00");
		if(colecaoDebitosACobrar != null && !colecaoDebitosACobrar.equals("")) {
			Iterator debitos = colecaoDebitosACobrar.iterator();
			
			while(debitos.hasNext()) {
				
				DebitoACobrar debito = (DebitoACobrar)debitos.next();
				if(debito.getNumeroParcelasAntecipadas() != null) {
					
					int prestacao = 0;
					if(colecaoAntecipacaoDebitosDeParcelamento != null) {
						while (prestacao < colecaoAntecipacaoDebitosDeParcelamento
								.size()) {
							somaPrestacao = somaPrestacao.add(debito
									.getValorPrestacao());
							prestacao++;

						}
					}
				}else {
					somaPrestacao.add(debito.getValorTotalComBonus());
				}
				
				valorTotalRestanteDebitosACobrar = valorTotalRestanteDebitosACobrar.add(somaPrestacao); 
			}
			valorTotalRestanteDebitosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		}
		
		//obter o valor geral das guias de pagamento
		BigDecimal valorTotalGuias = new BigDecimal("0.00");
		if(colecaoGuiasPagamento != null && !colecaoGuiasPagamento.equals("")) {
			Iterator guiasIterator = colecaoGuiasPagamento.iterator();
			
			while(guiasIterator.hasNext()){
				GuiaPagamentoValoresHelper guia = (GuiaPagamentoValoresHelper) guiasIterator.next();
				if(guia.getGuiaPagamento() != null &&
						guia.getGuiaPagamento().getValorDebito() != null) {
					valorTotalGuias = valorTotalGuias.add(guia.getGuiaPagamento().getValorDebito());
				}
			}
			valorTotalGuias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		}
		
		valorServicosAtualizacoes = valorAcrescimosImpontualidade.add(valorTotalGuias.add(valorTotalRestanteDebitosACobrar));
		
		 valorTotalContasString = Util.formatarMoedaReal(valorTotalContas);
		 valorServicosAtualizacoesString = Util.formatarMoedaReal(valorServicosAtualizacoes);
		 
		 valorDescontoCredito  = valorDescontoCredito.add(valorDesconto);
		 valorDescontoString = Util.formatarMoedaReal(valorDescontoCredito);
		 
		 valorTotalComDescontoString = Util.formatarMoedaReal(valorDocumento); 
		
		// Parte que vai mandar o relatório para a tela
		// cria uma instância da classe do relatório
		 RelatorioDebito relatorioDebito = new RelatorioDebito((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		//Linha 1
		 relatorioDebito.addParametro("nomeLocalidade",nomeLocalidade);
		
		//Linha 2
		 relatorioDebito.addParametro("inscricao",inscricao);
		 relatorioDebito.addParametro("nomeUsuario",nomeUsuario);
		 relatorioDebito.addParametro("matricula",matricula);
		 relatorioDebito.addParametro("cpfCnpj", cpfCnpj);
		
		//Linha 3
		 relatorioDebito.addParametro("enderecoImovel",enderecoImovel);
		 //relatorioExtratoDebito.addParametro("seqDocCobranca",seqDocCobranca);
		
		//Linha 4
		 relatorioDebito.addParametro("situacaoAgua",situacaoAgua);
		 relatorioDebito.addParametro("situacaoEsgoto",situacaoEsgoto);
		 relatorioDebito.addParametro("qtdResidencial",qtdResidencial);
		 relatorioDebito.addParametro("qtdComercial",qtdComercial);
		 relatorioDebito.addParametro("qtdIndustrial",qtdIndustrial);
		 relatorioDebito.addParametro("qtdPublico",qtdPublico);
		 relatorioDebito.addParametro("descPerfilImovel",descPerfilImovel);
		 relatorioDebito.addParametro("dataEmissao",dataEmissao);
		 //relatorioExtratoDebito.addParametro("dataValidade",dataValidade);
		
		//linhas 7 , 8, 9 e 10
		 relatorioDebito.addParametro("valorTotalContas",valorTotalContasString);
		 relatorioDebito.addParametro("valorServicosAtualizacoes",valorServicosAtualizacoesString);
		 relatorioDebito.addParametro("valorDesconto",valorDescontoString);
		 relatorioDebito.addParametro("valorTotalComDesconto",valorTotalComDescontoString);
		 relatorioDebito.addParametro("imovel", imovel);

		/*//Linha 11
		//CRC0959 - Vivianne Sousa - 08/09/2010 - analista:Fatima Sampaio
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		if(extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento()!= null 
			&& sistemaParametro.getValorExtratoFichaComp() != null
			&& !sistemaParametro.getValorExtratoFichaComp().equals(new BigDecimal("0.00"))
			&& extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento().
				compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0){
			 
			 	//representação numérica do código de barras
				//[SB0010] – Obter Representação numérica do Nosso Número da Ficha de Compensação
				StringBuilder nossoNumero = fachada.obterNossoNumeroFichaCompensacao(
						DocumentoTipo.EXTRATO_DE_DEBITO.toString(),documentoCobranca.getId().toString()) ;
				String nossoNumeroSemDV = nossoNumero.toString().substring(0,17);
				relatorioExtratoDebito.addParametro("nossoNumero",nossoNumero.toString());
				
				Date dataVencimentoMais75 = Util.adicionarNumeroDiasDeUmaData(new Date(),75);
				String fatorVencimento = fachada.obterFatorVencimento(dataVencimentoMais75);
				
				String especificacaoCodigoBarra = fachada.
					obterEspecificacaoCodigoBarraFichaCompensacao(
				    ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, 
				    ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO, 
				    documentoCobranca.getValorDocumento(), nossoNumeroSemDV.toString(),
					ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO, fatorVencimento);
				                                
				String representacaoNumericaCodigoBarraFichaCompensacao = 
				fachada.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(especificacaoCodigoBarra);
				
				relatorioExtratoDebito.addParametro("representacaoNumericaCodBarraSemDigito",especificacaoCodigoBarra);
				relatorioExtratoDebito.addParametro("representacaoNumericaCodBarra",representacaoNumericaCodigoBarraFichaCompensacao);
				
		 }else{
			 
			 
				String representacaoNumericaCodBarra = "";
				//[UC0229] Obtém a representação numérica do código de barra
				
				representacaoNumericaCodBarra = fachada
							.obterRepresentacaoNumericaCodigoBarra(
									5,
		                            valorDocumento,
									documentoCobranca.getLocalidade().getId(),
									imovel.getId(),
									null,
									null,
									null,
									null,
									seqDocCobranca,
									documentoCobranca.getDocumentoTipo().getId(),
									null, null,null);
				
				
				//Formata a representação númerica do código de barras
				String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
						.substring(0, 11)
						+ "-"
						+ representacaoNumericaCodBarra.substring(11, 12)
						+ " "
						+ representacaoNumericaCodBarra.substring(12, 23)
						+ "-"
						+ representacaoNumericaCodBarra.substring(23, 24)
						+ " "
						+ representacaoNumericaCodBarra.substring(24, 35)
						+ "-"
						+ representacaoNumericaCodBarra.substring(35, 36)
						+ " "
						+ representacaoNumericaCodBarra.substring(36, 47)
						+ "-"
						+ representacaoNumericaCodBarra.substring(47, 48);
				
				relatorioExtratoDebito.addParametro("representacaoNumericaCodBarra",representacaoNumericaCodBarraFormatada);
				
				String representacaoNumericaCodBarraSemDigito = 
					representacaoNumericaCodBarra.substring(0, 11)
					+ representacaoNumericaCodBarra.substring(12, 23)
					+ representacaoNumericaCodBarra.substring(24, 35)
					+ representacaoNumericaCodBarra.substring(36, 47);
				
				relatorioExtratoDebito.addParametro("representacaoNumericaCodBarraSemDigito",representacaoNumericaCodBarraSemDigito);
			 
		 }*/
		
		 relatorioDebito.addParametro("valorAcrescimosImpontualidade",valorAcrescimosImpontualidade);
		 relatorioDebito.addParametro("relatorioDebitoHelper",relatorioDebitoHelper);
		
		String codigoRotaESequencialRota = fachada.obterRotaESequencialRotaDoImovel(imovel.getId());
		relatorioDebito.addParametro("codigoRotaESequencialRota", codigoRotaESequencialRota);
		
		//String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		//if (tipoRelatorio == null) {
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		//}

		relatorioDebito.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioDebito,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}


		return retorno;
	}

	private Collection obterColecaoDebitosACobrarDoParcelamento(Collection colecaoDebitosACobrar){
		
		Collection<DebitoACobrar> colecaoDebitosACobrarParcelamento = new ArrayList();
		
		if (colecaoDebitosACobrar != null && !colecaoDebitosACobrar.isEmpty()) {
			Iterator debitoACobrarValores = colecaoDebitosACobrar.iterator();

			while (debitoACobrarValores.hasNext()) {
				DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
				
				//Verificar existência de juros sobre imóvel
				if(debitoACobrar.getDebitoTipo().getId() != null && !debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
					
					// Debitos A Cobrar - Serviço
					if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.SERVICO_NORMAL)) {
					
						colecaoDebitosACobrarParcelamento.add(debitoACobrar);
					}

					// Debitos A Cobrar - Parcelamento
					if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
						|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
						|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
					
						colecaoDebitosACobrarParcelamento.add(debitoACobrar);
					}
				}
			}
			
		}
		
		
		
		return colecaoDebitosACobrarParcelamento;
	}
	
	
	private Collection obterColecaoDebitosACobrarSemJurosParcelamento(Collection colecaoDebitosACobrar){
		
		Collection<DebitoACobrar> colecaoDebitosACobrarParcelamento = new ArrayList();
		
		if (colecaoDebitosACobrar != null && !colecaoDebitosACobrar.isEmpty()) {
			Iterator debitoACobrarValores = colecaoDebitosACobrar.iterator();

			while (debitoACobrarValores.hasNext()) {
				DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
				
				//Verificar existência de juros sobre imóvel
				if(debitoACobrar.getDebitoTipo().getId() != null && 
						!debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
					colecaoDebitosACobrarParcelamento.add(debitoACobrar);
				}
			}
			
		}
		
		return colecaoDebitosACobrarParcelamento;
	}
	
	
	//Vivianne Sousa - 02/07/2008
	//retornaa colecao de contas som as contas em revisão e 
	//o valor total das contas em revisão para diminuir do valor do documento 
	private Map retirarContasEmRevisaoDeColecaoContas(Collection<ContaValoresHelper> colecaoContas){
		
        Map retorno = new HashMap();
		BigDecimal valorTotalContasRevisao =  BigDecimal.ZERO;
		Collection<ContaValoresHelper> colecaoContasSemContasEmRevisao = new ArrayList<ContaValoresHelper>();
		Date maiorDataVencimentoContas = Util.converteStringParaDate("01/01/0001");
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator iter = colecaoContas.iterator();

		
			while (iter.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iter.next();
				Conta conta = contaValoresHelper.getConta();
				
				if (conta.getContaMotivoRevisao() != null){
					
					valorTotalContasRevisao = valorTotalContasRevisao.
						add(contaValoresHelper.getValorTotalConta());
					
				}else{
					colecaoContasSemContasEmRevisao.add(contaValoresHelper);

					if(Util.compararData(conta.getDataVencimentoConta(),maiorDataVencimentoContas) == 1){
						maiorDataVencimentoContas = conta.getDataVencimentoConta();
					}
				}

			}
			
		}
		
		retorno.put("colecaoContasSemContasEmRevisao",colecaoContasSemContasEmRevisao);
		retorno.put("valorTotalContasRevisao",valorTotalContasRevisao);
		retorno.put("maiorDataVencimentoContas",maiorDataVencimentoContas);
		
		return retorno;
	}
	
	
	//Vivianne Sousa - 16/04/2009 
	//retorna a maior Data de Vencimento da coleção de Contas
	private Date obterMaiorDataVencimentoContas(Collection<ContaValoresHelper> colecaoContas){
		
		Date maiorDataVencimentoContas = Util.converteStringParaDate("01/01/0001");
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator iter = colecaoContas.iterator();

			while (iter.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iter.next();
				Conta conta = contaValoresHelper.getConta();
				
				if(Util.compararData(conta.getDataVencimentoConta(),maiorDataVencimentoContas) == 1){
					maiorDataVencimentoContas = conta.getDataVencimentoConta();
				}
			}	
		}

		return maiorDataVencimentoContas;
		
	}
	
	
	
//	private Collection obterColecaoCreditoARealizarSemDescontoParcelamento(Collection colecaoCreditoARealizar){
//		
//		Collection<CreditoARealizar> colecaoCreditoARealizarParcelamento = new ArrayList();
//		
//		if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
//			Iterator creditoARealizarValores = colecaoCreditoARealizar.iterator();
//
//			while (creditoARealizarValores.hasNext()) {
//				CreditoARealizar creditoARealizar = (CreditoARealizar) creditoARealizarValores.next();
//				
//				
//				if(creditoARealizar.getCreditoOrigem() != null && 
//						!creditoARealizar.getCreditoOrigem().getId().equals(CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO)){
//					colecaoCreditoARealizarParcelamento.add(creditoARealizar);
//				}
//			}
//			
//		}
//		
//		return colecaoCreditoARealizarParcelamento;
//	}

	
	
	/*private Object[] obterContasSelecionadas(String idsContas, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<ContaValoresHelper> colecaoContas = null;
		BigDecimal valorTotalConta = BigDecimal.ZERO;
		BigDecimal valorTotalAcrescimoImpontualidade = BigDecimal.ZERO;
		
		if (idsContas != null && !idsContas.equals("")){
			retorno = new Object[3];
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
					}
				}
			}
			retorno[0] = colecaoContas;
			retorno[1] = valorTotalConta;
			retorno[2] = valorTotalAcrescimoImpontualidade;
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
						valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(debitoACobrar.getValorTotal());
						
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
						valorTotalCreditoARealizar = valorTotalCreditoARealizar.add(creditoARealizar.getValorTotal());
						
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
		
		if (idsGuias != null && !idsGuias.equals("")){
			retorno = new Object[2];
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
					}
				}
			}
			retorno[0] = colecaoGuias;
			retorno[1] = valorTotalGuiaPagamento;
		}
		
		return retorno;
	}*/

	
	

}

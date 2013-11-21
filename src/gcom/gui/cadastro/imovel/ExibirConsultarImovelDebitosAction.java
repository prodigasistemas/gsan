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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0473] Consultar Imovel
 * Metodo da 4° Aba - Débitos do Imóvel
 * 
 * @author Rafael Santos
 * @since 14/09/2006
 * 
 */
public class ExibirConsultarImovelDebitosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("consultarImovelDebitos");

		//cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//cria uma instância da sessão
		HttpSession sessao = getSessao(httpServletRequest);
		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
		//recupera a flag de limpar o form  
		String limparForm = httpServletRequest.getParameter("limparForm");
		String indicadorNovo = httpServletRequest.getParameter("indicadorNovo");
		String idImovelPrincipalAba = null;
		if (httpServletRequest.getParameter("matriculaAssociada") != null
				&& !httpServletRequest.getParameter("matriculaAssociada").equals("")) {
			idImovelPrincipalAba = (String) httpServletRequest.getParameter("matriculaAssociada");
		} else if (sessao.getAttribute("idImovelPrincipalAba") != null) {
			idImovelPrincipalAba = (String) sessao.getAttribute("idImovelPrincipalAba");
		}
		
		/*Pesquisar o imóvel a partir da matrícula do imóvel informada na página
		 ====================================================================== */
		//recupera o código do imóvel
		String idImovelDebitos = consultarImovelActionForm.getIdImovelDebitos();

		if (limparForm != null && !limparForm.equals("")) {

			//limpar os dados 
			httpServletRequest.setAttribute("idImovelDebitosNaoEncontrado",null);
			
			sessao.removeAttribute("imovelDebitos");
			sessao.removeAttribute("colecaoContaValores");
			sessao.removeAttribute("idImovelPrincipalAba");
			sessao.removeAttribute("imovelClientes");

			// Manda a colecao e os valores total de conta pelo request
			sessao.removeAttribute("colecaoDebitoACobrar");
			sessao.removeAttribute("valorConta");
			sessao.removeAttribute("valorAcrescimo");
			sessao.removeAttribute("valorAgua");
			sessao.removeAttribute("valorEsgoto");
			sessao.removeAttribute("valorDebito");
			sessao.removeAttribute("valorCredito");
			sessao.removeAttribute("valorImposto");
			sessao.removeAttribute("valorContaAcrescimo");

			// Manda a colecao e o valor total de DebitoACobrar pelo request
			sessao.removeAttribute("colecaoDebitoACobrar");
			sessao.removeAttribute("valorDebitoACobrar");

			// Manda a colecao e o valor total de CreditoARealizar pelo request
			sessao.removeAttribute("colecaoCreditoARealizar");
			sessao.removeAttribute("valorCreditoARealizar");
			sessao.removeAttribute("valorCreditoARealizarSemDescontosParcelamento");

			// Manda a colecao e o valor total de GuiaPagamento pelo request
			sessao.removeAttribute("colecaoGuiaPagamentoValores");
			sessao.removeAttribute("valorGuiaPagamento");

			sessao.removeAttribute("colecaoCobrancaSituacao");
			
			sessao.removeAttribute("valorTotalSemAcrescimo");
			sessao.removeAttribute("valorTotalComAcrescimo");
			sessao.removeAttribute("valorToralSemAcrescimoESemJurosParcelamento");

			sessao.removeAttribute("abrirPopupPROMAIS");
			
			consultarImovelActionForm.setIdImovelDadosComplementares(null);
			consultarImovelActionForm.setIdImovelDadosCadastrais(null);
			consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
			consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
			consultarImovelActionForm.setIdImovelDebitos(null);
			consultarImovelActionForm.setIdImovelPagamentos(null);
			consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
			consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
			consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
			consultarImovelActionForm.setImovIdAnt(null);
//			consultarImovelActionForm.setSituacaoCobrancaDadosComplementares(null);

			consultarImovelActionForm.setIdImovelDebitos(null);
			consultarImovelActionForm.setMatriculaImovelDebitos(null);
			consultarImovelActionForm.setSituacaoEsgotoDebitos(null);
			consultarImovelActionForm.setSituacaoAguaDebitos(null);
			consultarImovelActionForm.setCodigoImovel(null);
			consultarImovelActionForm.setTipoRelacao(null);
			consultarImovelActionForm.setReferenciaInicial(null);
			consultarImovelActionForm.setReferenciaFinal(null);
			consultarImovelActionForm.setDataVencimentoInicial(null);
			consultarImovelActionForm.setDataVencimentoFinal(null);
			consultarImovelActionForm.setLigacaoAgua(null);
			consultarImovelActionForm.setLigacaoEsgoto(null);
			consultarImovelActionForm.setMaticula(null);
			consultarImovelActionForm.setInscricao(null);
			consultarImovelActionForm.setNomeCliente(null);
			consultarImovelActionForm.setTipoRelacaoCliente(null);
			consultarImovelActionForm.setCpf(null);
			consultarImovelActionForm.setCnpj(null);
			consultarImovelActionForm.setRefInicial(null);
			consultarImovelActionForm.setRefFinal(null);
			consultarImovelActionForm.setDtInicial(null);
			consultarImovelActionForm.setDtFinal(null);
			
			if (indicadorNovo == null || indicadorNovo.equals("")) {
				idImovelDebitos = null;
				idImovelPrincipalAba = null;
			}

			//        }else if (idImovelDebitos != null && !idImovelDebitos.equalsIgnoreCase("")){
		}
		
		Imovel imovel = null;
		
		if ((idImovelDebitos != null && !idImovelDebitos.equalsIgnoreCase("")) || (idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase(""))) {

			if (idImovelDebitos != null && !idImovelDebitos.equalsIgnoreCase("")) {
				
				
				if (idImovelPrincipalAba != null&& !idImovelPrincipalAba.equalsIgnoreCase("")) {

					if (indicadorNovo != null && !indicadorNovo.equals("")) {

						consultarImovelActionForm.setIdImovelDebitos(idImovelDebitos);

					} else if (!(idImovelDebitos.equals(idImovelPrincipalAba))) {
						
						consultarImovelActionForm.setIdImovelDebitos(idImovelPrincipalAba);
						idImovelDebitos = idImovelPrincipalAba;
					}

				}
			} else if (idImovelPrincipalAba != null	&& !idImovelPrincipalAba.equalsIgnoreCase("")) {
				consultarImovelActionForm.setIdImovelRegistroAtendimento(idImovelPrincipalAba);
				idImovelDebitos = idImovelPrincipalAba;
			}
			
			if(idImovelDebitos!=null && !idImovelDebitos.equalsIgnoreCase("")){
				//	pesquisar cliente do imovel
				Collection clientesImovel = fachada.pesquisarClientesImovelExcluidoOuNao(new Integer(idImovelDebitos.trim()));
				sessao.setAttribute("imovelClientes",clientesImovel);
			}
			
			//Obtém a instância da Fachada

			//verifica se o objeto imovel é o mesmo ja pesquisado, para não precisar pesquisar mais.
			boolean imovelNovoPesquisado = false;
			if (sessao.getAttribute("imovelDebitos") != null) {
				imovel = (Imovel) sessao.getAttribute("imovelDebitos");
				if (!(imovel.getId().toString().equals(idImovelDebitos.trim()))) {
					imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(idImovelDebitos.trim()));
					imovelNovoPesquisado = true;
				}

			} else {
				imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(idImovelDebitos.trim()));
				imovelNovoPesquisado = true;
			}

			if (imovel != null) {
				sessao.setAttribute("imovelDebitos", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
				
				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}
				
				consultarImovelActionForm.setIdImovelDebitos(imovel.getId().toString());

				// Laço para adicionar todos os id's dos grupos no filtro
				// para pesquisar os acessos de todos os grupos do usuário
				// logado
				FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = new FiltroGrupoFuncionalidadeOperacao();
				
				filtroGrupoFuncionalidadeOperacao.adicionarParametro(
						new ParametroSimples(FiltroGrupoFuncionalidadeOperacao.OPERACAO_ID, 
								Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_PROMAIS));
				
				// Pesquisa os grupos do usuário
				Collection colecaoGruposUsuario = 
					this.getFachada().pesquisarGruposUsuario(this.getUsuarioLogado(httpServletRequest).getId());
				
				filtroGrupoFuncionalidadeOperacao.adicionarParametro(new ParametroSimplesIn(FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,colecaoGruposUsuario));

				/*
				 * Pesquisa todas as permissões do usuário
				 * pesquisa ocorrência na tabela GrupoFuncionalidadeOperacao para os grupos
				 * aos quais o usuário logado pertence 
				 */
				Collection permissoes = this.getFachada().pesquisar(filtroGrupoFuncionalidadeOperacao,GrupoFuncionalidadeOperacao.class.getName());
				
				//Caso já tenha sido executado o promais não abrir novamente.
				if(this.getSistemaParametro()!=null 
						&& this.getSistemaParametro().getIndicadorPopupAtualizacaoCadastral() != null
						&& this.getSistemaParametro().getIndicadorPopupAtualizacaoCadastral().compareTo(ConstantesSistema.SIM)==0
						&& !Util.isVazioOrNulo(permissoes)){
					if(sessao.getAttribute("promaisExecutado")!=null){
						if(sessao.getAttribute("idImovelPromaisExecutado")!=null){
							
							Integer idImovelPromaisExecutado = (Integer) sessao.getAttribute("idImovelPromaisExecutado");
							
							if(idImovelPromaisExecutado.toString().equals(imovel.getId().toString())){
								sessao.removeAttribute("abrirPopupPROMAIS");
							}else{
								sessao.setAttribute("abrirPopupPROMAIS", "TRUE");
								sessao.setAttribute("promaisExecutado", "sim");
								sessao.setAttribute("idImovelPromaisExecutado", imovel.getId());
							}
						}else{
							sessao.setAttribute("abrirPopupPROMAIS", "TRUE");
							sessao.setAttribute("promaisExecutado", "sim");
							sessao.setAttribute("idImovelPromaisExecutado", imovel.getId());
						}
					}else{
						sessao.setAttribute("abrirPopupPROMAIS", "TRUE");
						sessao.setAttribute("promaisExecutado", "sim");
						sessao.setAttribute("idImovelPromaisExecutado", imovel.getId());
					}			
				}else{
					sessao.removeAttribute("abrirPopupPROMAIS");
				}
				

				//cobranca situacao
//				if (imovel.getCobrancaSituacao() != null) {
//					consultarImovelActionForm.setSituacaoCobrancaDadosComplementares(imovel.getCobrancaSituacao().getDescricao());
//					
//				}else{
//					consultarImovelActionForm.setSituacaoCobrancaDadosComplementares(null);
//				}

				//caso o imovel pesquisado seja diferente do pesquisado anterior ou seja a primeira vez que se esteja pesquisando
				if (imovelNovoPesquisado) {
					//seta na tela a inscrição do imovel
					httpServletRequest.setAttribute("idImovelDebitosNaoEncontrado", null);

					consultarImovelActionForm.setMatriculaImovelDebitos(fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(idImovelDebitos.trim())));

					// seta a situação de agua
					consultarImovelActionForm.setSituacaoAguaDebitos(imovel.getLigacaoAguaSituacao().getDescricao());
					// seta a situação de esgoto
					consultarImovelActionForm.setSituacaoEsgotoDebitos(imovel.getLigacaoEsgotoSituacao().getDescricao());

					//seta a situação de agua
					consultarImovelActionForm.setLigacaoAguaSituacaoId(imovel.getLigacaoAguaSituacao().getId().toString());
					// seta a situação de esgoto
					consultarImovelActionForm.setLigacaoEsgotoSituacaoId(imovel.getLigacaoEsgotoSituacao().getId().toString());

					//RM3307 – Inclusão de lista de motivo de retorno de negativação dos imóveis
					//Vivianne Sousa - 06/12/2010 - analista:Adriana Ribeiro
					Collection colecaoDadosNegativacaoRetorno = fachada.
						pesquisarNegativadorRetornoMotivoDoReg(new Integer(idImovelDebitos.trim()));
					
					
					String referenciaInicial = "01/0001";
					String referenciaFinal = "12/9999";
					String dataVencimentoInicial = "01/01/0001";
					String dataVencimentoFinal = "31/12/9999";

					// Para auxiliar na formatação de uma data
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					String mesInicial = referenciaInicial.substring(0, 2);
					String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
					String anoMesInicial = anoInicial + mesInicial;
					String mesFinal = referenciaFinal.substring(0, 2);
					String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
					String anoMesFinal = anoFinal + mesFinal;

					Date dataVencimentoDebitoI;
					Date dataVencimentoDebitoF;

					try {
						dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
					} catch (ParseException ex) {
						dataVencimentoDebitoI = null;
					}
					try {
						dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
					} catch (ParseException ex) {
						dataVencimentoDebitoF = null;
					}

					// seta valores constantes para chamar o metodo que consulta debitos do imovel
					Integer tipoImovel = new Integer(1);
					Integer indicadorPagamento = new Integer(1);
					Integer indicadorConta = new Integer(1);
					Integer indicadorDebito = new Integer(1);
					Integer indicadorCredito = new Integer(1);
					Integer indicadorNotas = new Integer(1);
					Integer indicadorGuias = new Integer(1);
					Integer indicadorAtualizar = new Integer(1);

					// Obtendo os débitos do imovel
					ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada
							.obterDebitoImovelOuCliente(tipoImovel.intValue(),
									idImovelDebitos.trim(), null, null,
									anoMesInicial, anoMesFinal,
									dataVencimentoDebitoI,
									dataVencimentoDebitoF, indicadorPagamento
											.intValue(), indicadorConta
											.intValue(), indicadorDebito
											.intValue(), indicadorCredito
											.intValue(), indicadorNotas
											.intValue(), indicadorGuias
											.intValue(), indicadorAtualizar
											.intValue(), null);

					Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();

					ContaValoresHelper dadosConta = null;

					BigDecimal valorConta = new BigDecimal("0.00");
					BigDecimal valorAcrescimo = new BigDecimal("0.00");
					BigDecimal valorAgua = new BigDecimal("0.00");
					BigDecimal valorEsgoto = new BigDecimal("0.00");
					BigDecimal valorDebito = new BigDecimal("0.00");
					BigDecimal valorCredito = new BigDecimal("0.00");
					BigDecimal valorImposto = new BigDecimal("0.00");
					BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
					BigDecimal valorJurosMora = new BigDecimal("0.00");
					BigDecimal valorMulta = new BigDecimal("0.00");
					
					if (colecaoContaValores != null	&& !colecaoContaValores.isEmpty()) {
						java.util.Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores.iterator();
						// percorre a colecao de conta somando o valor para obter um valor total
						while (colecaoContaValoresIterator.hasNext()) {

							dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();
							valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
							valorAcrescimo = valorAcrescimo.add(dadosConta.getValorTotalContaValores());
							valorAgua = valorAgua.add(dadosConta.getConta().getValorAgua());
							valorEsgoto = valorEsgoto.add(dadosConta.getConta().getValorEsgoto());
							valorDebito = valorDebito.add(dadosConta.getConta().getDebitos());
							valorCredito = valorCredito.add(dadosConta.getConta().getValorCreditos());
							valorImposto = valorImposto.add(dadosConta.getConta().getValorImposto());
							
							if (dadosConta.getValorAtualizacaoMonetaria() != null && !dadosConta.getValorAtualizacaoMonetaria().equals("")) {
								valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosConta.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (dadosConta.getValorJurosMora() != null	&& !dadosConta.getValorJurosMora().equals("")) {
								valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorJurosMora = valorJurosMora.add(dadosConta.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (dadosConta.getValorMulta() != null && !dadosConta.getValorMulta().equals("")) {
								valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorMulta = valorMulta.add(dadosConta.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
						}
					}

					Collection<DebitoACobrar> colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();

					BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
					BigDecimal valorDebitoACobrarSemJurosParcelamento = new BigDecimal("0.00");
					DebitoACobrar dadosDebito = null;
					BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
					BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
					BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
					BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
					int indiceCurtoPrazo = 0;
					int indiceLongoPrazo = 1;

					if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
						java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
						// percorre a colecao de debito a cobrar somando o valor para obter um valor total
						while (colecaoDebitoACobrarIterator.hasNext()) {

							dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
							valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
							
							if (dadosDebito.getDebitoTipo() != null &&
									!dadosDebito.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
								valorDebitoACobrarSemJurosParcelamento = valorDebitoACobrarSemJurosParcelamento.add(dadosDebito.getValorTotalComBonus());
							}
							
							//Debitos A Cobrar - Parcelamento
							if (dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
								|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
								|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
								
								// [SB0001] Obter Valores de Curto e Longo Prazo
								valorRestanteACobrar = dadosDebito.getValorTotalComBonus();

								BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
										dadosDebito.getNumeroPrestacaoDebito(),	
										dadosDebito.getNumeroPrestacaoCobradasMaisBonus(),
										valorRestanteACobrar);
								valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
								valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
							}
							
						}
						valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
					}

					Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoImovel.getColecaoCreditoARealizar();

					BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
					BigDecimal valorCreditoARealizarSemDescontosParcelamento = new BigDecimal("0.00");
					CreditoARealizar dadosCredito = null;

					if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
						java.util.Iterator<CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar.iterator();
						// percorre a colecao de credito a realizar somando o valor para obter um valor total
						while (colecaoCreditoARealizarIterator.hasNext()) {

							dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator.next();
							valorCreditoARealizar = valorCreditoARealizar.add(dadosCredito.getValorTotalComBonus());
							
							if (dadosCredito.getCreditoOrigem() != null && 
									!dadosCredito.getCreditoOrigem().getId().equals(CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO)){
								valorCreditoARealizarSemDescontosParcelamento = valorCreditoARealizarSemDescontosParcelamento.add(dadosCredito.getValorTotalComBonus());
							}
						}
					}

					Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoImovel.getColecaoGuiasPagamentoValores();

					BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
					GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;

					if (colecaoGuiaPagamentoValores != null	&& !colecaoGuiaPagamentoValores.isEmpty()) {
						java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores.iterator();
						// percorre a colecao de guia de pagamento somando o valor para obter um valor total
						while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {

							dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
							valorGuiaPagamento = valorGuiaPagamento.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
							
							if (dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
								valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (dadosGuiaPagamentoValoresHelper.getValorJurosMora() != null && !dadosGuiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
								valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorJurosMora = valorJurosMora.add(dadosGuiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
							if (dadosGuiaPagamentoValoresHelper.getValorMulta() != null	&& !dadosGuiaPagamentoValoresHelper.getValorMulta().equals("")) {
								valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
								valorMulta = valorMulta.add(dadosGuiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
							}
						}
					}

					//pesquisa todas as situações de cobrança ativa do imovel
					Collection colecaoCobrancaSituacao = fachada.pesquisarImovelCobrancaSituacaoPorImovel(new Integer(idImovelDebitos.trim()));
					
					if ((colecaoContaValores == null)&& (colecaoDebitoACobrar == null || colecaoDebitoACobrar.isEmpty())
							&& (colecaoCreditoARealizar == null || colecaoCreditoARealizar.isEmpty()) && (colecaoGuiaPagamentoValores == null)) {
						if (colecaoContaValores == null){
							sessao.removeAttribute("colecaoContaValores");
							sessao.removeAttribute("valorConta");
							sessao.removeAttribute("valorAcrescimo");
							sessao.removeAttribute("valorAgua");
							sessao.removeAttribute("valorEsgoto");
							sessao.removeAttribute("valorDebito");
							sessao.removeAttribute("valorCredito");
							sessao.removeAttribute("valorContaAcrescimo");
							sessao.removeAttribute("valorImposto");
							sessao.removeAttribute("valorTotalSemAcrescimo");
							sessao.removeAttribute("valorTotalComAcrescimo");
							sessao.removeAttribute("valorToralSemAcrescimoESemJurosParcelamento");							
						} 
						if (colecaoDebitoACobrar == null || colecaoDebitoACobrar.isEmpty()){
							sessao.removeAttribute("colecaoDebitoACobrar");
							sessao.removeAttribute("valorDebitoACobrar");							
						}
						if (colecaoCreditoARealizar == null || colecaoCreditoARealizar.isEmpty()){
							sessao.removeAttribute("colecaoCreditoARealizar");
							sessao.removeAttribute("valorCreditoARealizar");
							sessao.removeAttribute("valorCreditoARealizarSemDescontosParcelamento");							
						}
						if (colecaoGuiaPagamentoValores == null || colecaoGuiaPagamentoValores.isEmpty()){
							sessao.removeAttribute("colecaoGuiaPagamentoValores");
							sessao.removeAttribute("valorGuiaPagamento");							
						}
						
						if(colecaoCobrancaSituacao == null){
							sessao.removeAttribute("colecaoCobrancaSituacao");
						}
						
						if(colecaoDadosNegativacaoRetorno == null){
							sessao.removeAttribute("colecaoDadosNegativacaoRetorno");
						}
						
					} else {
						// Manda a colecao pelo request
						sessao.setAttribute("colecaoContaValores",colecaoContaValores);

						// Manda a colecao e os valores total de conta pelo request
						sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrar);
						sessao.setAttribute("valorConta", Util.formatarMoedaReal(valorConta));
						sessao.setAttribute("valorAcrescimo", Util.formatarMoedaReal(valorAcrescimo));
						sessao.setAttribute("valorAgua", Util.formatarMoedaReal(valorAgua));
						sessao.setAttribute("valorEsgoto", Util.formatarMoedaReal(valorEsgoto));
						sessao.setAttribute("valorDebito", Util.formatarMoedaReal(valorDebito));
						sessao.setAttribute("valorCredito", Util.formatarMoedaReal(valorCredito));
						sessao.setAttribute("valorContaAcrescimo", Util.formatarMoedaReal(valorConta.add(valorAcrescimo)));
						sessao.setAttribute("valorImposto", Util.formatarMoedaReal(valorImposto));

						// Manda a colecao e o valor total de DebitoACobrar pelo request
						sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrar);
						sessao.setAttribute("valorDebitoACobrar", Util.formatarMoedaReal(valorDebitoACobrar));

						// Manda a colecao e o valor total de CreditoARealizar pelo request
						sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
						sessao.setAttribute("valorCreditoARealizar", Util.formatarMoedaReal(valorCreditoARealizar));
						sessao.setAttribute("valorCreditoARealizarSemDescontosParcelamento",Util.formatarMoedaReal(valorCreditoARealizarSemDescontosParcelamento));

						// Manda a colecao e o valor total de GuiaPagamento pelo request
						sessao.setAttribute("colecaoGuiaPagamentoValores",colecaoGuiaPagamentoValores);
						sessao.setAttribute("valorGuiaPagamento", Util.formatarMoedaReal(valorGuiaPagamento));

						// Soma o valor total dos debitos e subtrai dos creditos
						BigDecimal valorTotalSemAcrescimo = valorConta.add(valorDebitoACobrar);
						valorTotalSemAcrescimo = valorTotalSemAcrescimo.add(valorGuiaPagamento);
						valorTotalSemAcrescimo = valorTotalSemAcrescimo.subtract(valorCreditoARealizar);

						BigDecimal valorTotalComAcrescimo = valorTotalSemAcrescimo.add(valorAcrescimo);
						
						BigDecimal valorToralSemAcrescimoESemJurosParcelamento = 
							valorConta.add(valorDebitoACobrarSemJurosParcelamento);
						
						valorToralSemAcrescimoESemJurosParcelamento = 
							valorToralSemAcrescimoESemJurosParcelamento.add(valorGuiaPagamento);
						
						
						sessao.setAttribute("valorTotalSemAcrescimo", Util
								.formatarMoedaReal(valorTotalSemAcrescimo));
						sessao.setAttribute("valorTotalComAcrescimo", Util
								.formatarMoedaReal(valorTotalComAcrescimo));
						sessao.setAttribute("valorToralSemAcrescimoESemJurosParcelamento", 
								Util.formatarMoedaReal(valorToralSemAcrescimoESemJurosParcelamento));
						
						sessao.setAttribute("colecaoCobrancaSituacao", colecaoCobrancaSituacao);
						sessao.setAttribute("colecaoDadosNegativacaoRetorno",colecaoDadosNegativacaoRetorno);
						
						///////////////////////////////////////////////////////////////////////
						Short indicadorContasRevisao = 2; 
						SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
						if(sistemaParametro.getResolucaoDiretoria() != null){
							indicadorContasRevisao = 1;
							Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
							ImovelPerfil imovelPerfil = fachada.obterImovelPerfil(new Integer(idImovelDebitos.trim())); 
							Short numeroReparcelamentoConsecutivos = fachada.consultarNumeroReparcelamentoConsecutivosImovel(new Integer(idImovelDebitos.trim()));
							
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
									new Integer(idImovelDebitos.trim()), 
									new BigDecimal("0.00"), 
									new Integer(consultarImovelActionForm.getLigacaoAguaSituacaoId()), 
									new Integer(consultarImovelActionForm.getLigacaoEsgotoSituacaoId()), 
									imovelPerfil.getId(), 
									"01/0001", 
									new Integer("2"),//indicador de restabelecimento 
									colecaoContaValores, 
									valorTotalComAcrescimo, 
									valorMulta, 
									valorJurosMora, 
									valorAtualizacaoMonetaria, 
									new Integer(numeroReparcelamentoConsecutivos.toString()), 
									colecaoGuiaPagamentoValores, 
									usuarioLogado, 
									valorTotalRestanteParcelamentosACobrar, 
									Util.formatarMesAnoComBarraParaAnoMes("01/0001"),
									Util.formatarMesAnoComBarraParaAnoMes("12/9999"), 
									indicadoresParcelamentoHelper,
									valorCreditoARealizar);
							
							NegociacaoOpcoesParcelamentoHelper negociacaoOpcoesParcelamentoHelper = 
								fachada.calcularValorDosDescontosPagamentoAVista(helper);
							
							BigDecimal valorTotalDescontoPagamentoAVista = negociacaoOpcoesParcelamentoHelper.getValorTotalDescontoPagamentoAVista();
							BigDecimal valorPagamentoAVista = valorTotalComAcrescimo.subtract(valorTotalDescontoPagamentoAVista);

							sessao.setAttribute("valorTotalDescontoPagamentoAVista", valorTotalDescontoPagamentoAVista);
							sessao.setAttribute("valorPagamentoAVista", valorPagamentoAVista);
							
						
						}
						sessao.setAttribute("indicadorContasRevisao",indicadorContasRevisao);
						//////////////////////////////////////////////////////////////////////
					}
					
				}
			} else {

				//imovel não encontrado
				httpServletRequest.setAttribute("idImovelDebitosNaoEncontrado",
						"true");

				consultarImovelActionForm
						.setMatriculaImovelDebitos("IMÓVEL INEXISTENTE");
				sessao.removeAttribute("imovelDebitos");
				sessao.removeAttribute("colecaoContaValores");
				sessao.removeAttribute("idImovelPrincipalAba");

				// Manda a colecao e os valores total de conta pelo request
				sessao.removeAttribute("colecaoDebitoACobrar");
				sessao.removeAttribute("valorConta");
				sessao.removeAttribute("valorAcrescimo");
				sessao.removeAttribute("valorAgua");
				sessao.removeAttribute("valorEsgoto");
				sessao.removeAttribute("valorDebito");
				sessao.removeAttribute("valorCredito");
				sessao.removeAttribute("valorContaAcrescimo");
				sessao.removeAttribute("valorImposto");

				// Manda a colecao e o valor total de DebitoACobrar pelo request
				sessao.removeAttribute("colecaoDebitoACobrar");
				sessao.removeAttribute("valorDebitoACobrar");

				// Manda a colecao e o valor total de CreditoARealizar pelo request
				sessao.removeAttribute("colecaoCreditoARealizar");
				sessao.removeAttribute("valorCreditoARealizar");
				sessao.removeAttribute("valorCreditoARealizarSemDescontosParcelamento");

				// Manda a colecao e o valor total de GuiaPagamento pelo request
				sessao.removeAttribute("colecaoGuiaPagamentoValores");
				sessao.removeAttribute("valorGuiaPagamento");

				sessao.removeAttribute("valorTotalSemAcrescimo");
				sessao.removeAttribute("valorTotalComAcrescimo");
				sessao.removeAttribute("valorToralSemAcrescimoESemJurosParcelamento");
				
				sessao.removeAttribute("colecaoCobrancaSituacao");

				consultarImovelActionForm.setIdImovelDadosComplementares(null);
				consultarImovelActionForm.setIdImovelDadosCadastrais(null);
				consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
				consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
				consultarImovelActionForm.setIdImovelDebitos(null);
				consultarImovelActionForm.setIdImovelPagamentos(null);
				consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
				consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
				consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
				consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
				consultarImovelActionForm.setImovIdAnt(null);
				
				consultarImovelActionForm.setSituacaoEsgotoDebitos(null);
				consultarImovelActionForm.setSituacaoAguaDebitos(null);
				consultarImovelActionForm.setCodigoImovel(null);
				consultarImovelActionForm.setTipoRelacao(null);
				consultarImovelActionForm.setReferenciaInicial(null);
				consultarImovelActionForm.setReferenciaFinal(null);
				consultarImovelActionForm.setDataVencimentoInicial(null);
				consultarImovelActionForm.setDataVencimentoFinal(null);
				consultarImovelActionForm.setLigacaoAgua(null);
				consultarImovelActionForm.setLigacaoEsgoto(null);
				consultarImovelActionForm.setMaticula(null);
				consultarImovelActionForm.setInscricao(null);
				consultarImovelActionForm.setNomeCliente(null);
				consultarImovelActionForm.setTipoRelacaoCliente(null);
				consultarImovelActionForm.setCpf(null);
				consultarImovelActionForm.setCnpj(null);
				consultarImovelActionForm.setRefInicial(null);
				consultarImovelActionForm.setRefFinal(null);
				consultarImovelActionForm.setDtInicial(null);
				consultarImovelActionForm.setDtFinal(null);

			}

		} else {
			//matricula do imovel incorreta
			consultarImovelActionForm
					.setIdImovelHistoricoFaturamento(idImovelDebitos);

			httpServletRequest.setAttribute("idImovelDebitosNaoEncontrado",
					null);
			sessao.removeAttribute("imovelDebitos");
			sessao.removeAttribute("colecaoContaValores");
			sessao.removeAttribute("idImovelPrincipalAba");

			// Manda a colecao e os valores total de conta pelo request
			sessao.removeAttribute("colecaoDebitoACobrar");
			sessao.removeAttribute("valorConta");
			sessao.removeAttribute("valorAcrescimo");
			sessao.removeAttribute("valorAgua");
			sessao.removeAttribute("valorEsgoto");
			sessao.removeAttribute("valorDebito");
			sessao.removeAttribute("valorCredito");
			sessao.removeAttribute("valorContaAcrescimo");
			sessao.removeAttribute("valorImposto");

			// Manda a colecao e o valor total de DebitoACobrar pelo request
			sessao.removeAttribute("colecaoDebitoACobrar");
			sessao.removeAttribute("valorDebitoACobrar");

			// Manda a colecao e o valor total de CreditoARealizar pelo request
			sessao.removeAttribute("colecaoCreditoARealizar");
			sessao.removeAttribute("valorCreditoARealizar");
			sessao.removeAttribute("valorCreditoARealizarSemDescontosParcelamento");

			// Manda a colecao e o valor total de GuiaPagamento pelo request
			sessao.removeAttribute("colecaoGuiaPagamentoValores");
			sessao.removeAttribute("valorGuiaPagamento");

			sessao.removeAttribute("valorTotalSemAcrescimo");
			sessao.removeAttribute("valorTotalComAcrescimo");

			consultarImovelActionForm.setMatriculaImovelDebitos(null);
			consultarImovelActionForm.setSituacaoEsgotoDebitos(null);
			consultarImovelActionForm.setSituacaoAguaDebitos(null);
			consultarImovelActionForm.setCodigoImovel(null);
			consultarImovelActionForm.setTipoRelacao(null);
			consultarImovelActionForm.setReferenciaInicial(null);
			consultarImovelActionForm.setReferenciaFinal(null);
			consultarImovelActionForm.setDataVencimentoInicial(null);
			consultarImovelActionForm.setDataVencimentoFinal(null);
			consultarImovelActionForm.setLigacaoAgua(null);
			consultarImovelActionForm.setLigacaoEsgoto(null);
			consultarImovelActionForm.setMaticula(null);
			consultarImovelActionForm.setInscricao(null);
			consultarImovelActionForm.setNomeCliente(null);
			consultarImovelActionForm.setTipoRelacaoCliente(null);
			consultarImovelActionForm.setCpf(null);
			consultarImovelActionForm.setCnpj(null);
			consultarImovelActionForm.setRefInicial(null);
			consultarImovelActionForm.setRefFinal(null);
			consultarImovelActionForm.setDtInicial(null);
			consultarImovelActionForm.setDtFinal(null);

		}
        
		//Vivianne Sousa - 16/04/2009 - analista:Adriano Britto
		Short indicadorEmissaoExtratoNaConsulta = indicadorEmissaoExtratoNaConsulta = fachada.pesquisarParametrosDoSistema().getIndicadorEmissaoExtratoNaConsulta();
		consultarImovelActionForm.setIndicadorEmissaoExtratoNaConsulta(indicadorEmissaoExtratoNaConsulta.toString());
		
		if (imovel != null && imovel.getIndicadorExclusao() != null && imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
			consultarImovelActionForm.setIndicadorEmissaoExtratoNaConsulta(ConstantesSistema.NAO.toString());
		}
		
		//retorna o mapeamento contido na variável retorno
		return retorno;
	}

}
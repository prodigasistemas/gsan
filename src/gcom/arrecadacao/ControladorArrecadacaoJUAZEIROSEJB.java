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
package gcom.arrecadacao;

import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.SessionBean;

/**
 * Controlador Arrecadacao Juazeiro
 *
 * @author Rafael Corrêa
 * @date 30/06/2009
 */
public class ControladorArrecadacaoJUAZEIROSEJB extends ControladorArrecadacao implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DE JUAZEIRO
	//==============================================================================================================
	/**
	 * [UC0264] - Distribuir Dados do Código de Barras - LEGADO
	 *
	 * @author Rafael Corrêa
	 * @date 03/07/2009
	 *
	 * @param idPagamento
	 * @param tipoPagamento
	 * @param idEmpresa
	 * @return RegistroHelperCodigoBarrasTipoPagamento
	 * @throws ControladorException 
	 */
	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento(
			String idPagamento, String tipoPagamento, String idEmpresa) throws ControladorException {

		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = new RegistroHelperCodigoBarrasTipoPagamento();

		registroHelperCodigoBarrasTipoPagamento = 
			super.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, tipoPagamento, idEmpresa);
		
		//===============================================================================================================================
		//LEGADO - JUAZEIRO
		//===============================================================================================================================
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_LEGADO_JUAZEIRO.toString())){
			
			String anoMes = idPagamento.substring(0, 6);
			
			// Tipo de Documento
			if (!Util.validarMesAnoSemBarra(anoMes) || new Integer(anoMes.substring(2,6)).intValue() > 2009 || new Integer(anoMes.substring(2,6)).intValue() < 1990) {
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_JUAZEIRO.toString());
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 12).trim());
			} else {
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_JUAZEIRO.toString());
				String idImovel = idPagamento.substring(6, 11).trim();
				
				// Mês/Ano da conta
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(anoMes);
				
				//Imóvel
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idImovel);
				
				//Localidade
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento.substring(11, 24).trim());
			}

		}
		//===============================================================================================================================
		
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras - LEGADO
	 *
	 * @author Rafael Corrêa
	 * @date 03/07/2009
	 *
	 * @param registroHelperCodigoBarras
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @param sistemaParametro
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasPorTipoPagamento(
			RegistroHelperCodigoBarras registroHelperCodigoBarras, Date dataPagamento, Integer anoMesPagamento,
			BigDecimal valorPagamento, Integer idFormaArrecadacao, SistemaParametro sistemaParametro, Usuario usuarioLogado) 
			throws ControladorException {
		
		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = null;
		
		String tipoPagamento = registroHelperCodigoBarras.getTipoPagamento();

		
		//LEGADO - COSANPA
		//===============================================================================================================================
		
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_LEGADO_JUAZEIRO.toString())){
			
			/*
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 0 (Conta), caso contrário;
			 * será (Guia de Pagamento).
			 */
			if (registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento4().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_JUAZEIRO.toString())){
				
				//CONTA
				pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasConta_JUAZEIRO_LEGADO(
				registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
				
				//===============================================================================================================================
			} else{
				
				//EXTRATO
				pagamentoHelperCodigoBarras = processarPagamentosCodigoBarrasGuiaPagamento_JUAZEIRO_LEGADO(
				registroHelperCodigoBarras, sistemaParametro, dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
				
				//===============================================================================================================================
			}
		}else{
		
		pagamentoHelperCodigoBarras = super.processarPagamentosCodigoBarrasPorTipoPagamento(
				registroHelperCodigoBarras, dataPagamento,
				anoMesPagamento, valorPagamento,
				idFormaArrecadacao, sistemaParametro, usuarioLogado);
		}

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * LEGADO CONTA
	 * 
	 * @autor: Rafael Corrêa 
	 * @data: 03/07/2009
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasConta_JUAZEIRO_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean idLocalidadeInvalida = false;
		boolean matriculaImovelInvalida = false;

		int anoMes = 0;
		Integer idImovelNaBase = null;
		Integer matriculaImovel = null;

		boolean anoMesReferencia = false;

		idLocalidadeInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		if (idLocalidadeInvalida) {
			descricaoOcorrencia = "CÓDIGO DA LOCALIDADE NÃO NUMÉRICA";
		}

		String idImovelSemDigito = registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2();
		
		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(idImovelSemDigito);

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {

			/*
			 * Verifica se existe a matricula do imóvel na base
			 */
			matriculaImovel = new Integer(idImovelSemDigito + Util.obterDigitoVerificadorModulo10(idImovelSemDigito));

			idImovelNaBase = null;

			try {
				idImovelNaBase = repositorioImovel
						.recuperarMatriculaImovel(matriculaImovel);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			/*
			 * Se o id do imovel pesquisado na base for diferente de nulo
			 */
			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}

		anoMesReferencia = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento1());

		if (!anoMesReferencia) {

			// valida o ano mes de referencia da conta
			anoMes = Util.formatarMesAnoParaAnoMes(Integer
					.parseInt(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1()));

			anoMesReferencia = Util.validarAnoMesSemBarra("" + anoMes);

			if (anoMesReferencia) {
				descricaoOcorrencia = "ANO/MÊS DE REFERÊNCIA DA CONTA INVÁLIDA";
			}

		} else {
			descricaoOcorrencia = "ANO/MÊS DE REFERÊNCIA DA CONTA INVÁLIDA";
		}

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer idConta = null;

			// Valida o amo mes de referencia da conta
			int anoMesReferenciaInt = Util.formatarMesAnoParaAnoMes(Integer
					.parseInt(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1()));

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);

			try {
				idConta = repositorioFaturamento
						.pesquisarExistenciaContaComSituacaoAtual(imovel,
								anoMesReferenciaInt);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
			
			/*
             * Alterado por Raphael Rossiter em 09/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da conta e NÃO com
             * a localidade do imóvel.
             */
			if (idConta == null || idConta.equals("")) {
				descricaoOcorrencia = "CONTA INEXISTENTE";
			
				try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidade(idImovelNaBase);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
			}
			else {
				
				try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorConta(idConta);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
			}

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(anoMes);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecadação da tabela sistema parametro então seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecadação da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			pagamento.setDebitoTipo(null);

			// Verifica se o id da conta é diferente de nulo
			if (idConta != null) {

				ContaGeral conta = new ContaGeral();
				conta.setId(idConta);
				pagamento.setContaGeral(conta);

			} else {

				pagamento.setContaGeral(null);
			}
			pagamento.setGuiaPagamento(null);

			// verifica se o id da conta é diferente de nulo
			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);
			} else {

				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.CONTA);
			documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
			pagamento.setDocumentoTipo(documentoTipo);
			pagamento.setAvisoBancario(null);

			if (idImovelNaBase != null) {
				pagamento.setImovel(imovel);
			} else {
				pagamento.setImovel(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaArrecadacao);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());
			
			/*
			 * Alteracao referente ao relatorio de Float - Francisco : 14/07/08
			 */
			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.CONTA);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);
			
			pagamento.setDataProcessamento(new Date());

			colecaoPagamnetos.add(pagamento);

		} else {
			// atribui o valor 2(NÃO) ao indicador aceitacao
			// registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * GUIA DE PAGAMENTO
	 * 
	 * @autor: Rafael Corrêa 
	 * @data: 02/05/2007
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamento_JUAZEIRO_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();
		
		GuiaPagamento guiaPagamento = null;

		guiaPagamento = this.pesquisarExistenciaGuiaPagamentoPorNumeroGuiaFatura(registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());

        if (guiaPagamento == null) {
        	descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
        }

		if (descricaoOcorrencia.equals("OK")) {

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(null);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecadação da tabela sistema parametro então seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecadação da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			pagamento.setContaGeral(null);
			
			if (guiaPagamento != null) {
				pagamento.setDebitoTipo(guiaPagamento.getDebitoTipo());
				pagamento.setGuiaPagamento(guiaPagamento);
				pagamento.setLocalidade(guiaPagamento.getLocalidade());
				pagamento.setImovel(guiaPagamento.getImovel());
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			if (guiaPagamento != null && guiaPagamento.getDebitoTipo().getId().equals(DebitoTipo.ENTRADA_PARCELAMENTO)){
				documentoTipo.setId(DocumentoTipo.ENTRADA_DE_PARCELAMENTO);	
			} else {
				documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			}
			documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaArrecadacao);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());

			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			/*
			 * Alteracao referente ao Relatorio do Float - Francisco: 14/07/08
			 */
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);
			
			pagamento.setDataProcessamento(new Date());
			
			colecaoPagamentos.add(pagamento);

		} else {

			// Atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 *
	 * @author Rafael Corrêa
	 * @date 03/07/2009
	 *
	 * @param registroHelperCodigoG
	 * @param arrecadadorMovimentoItemHelper
	 * @throws ControladorException
	 */
	public void distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(RegistroHelperCodigoG registroHelperCodigoG,
			ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper) throws ControladorException {
		
		
		super.distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(registroHelperCodigoG,
		arrecadadorMovimentoItemHelper);
		
		//LEGADO - JUAZEIRO
		//===============================================================================================================================
		if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_LEGADO_JUAZEIRO.toString())){
			
			/*
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 1 (Conta_Legado), caso contrário;
			 * será (Extrato Legado).
			 */
			if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento4().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_JUAZEIRO.toString())){
				
				
				String idImovelSemDigito = registroHelperCodigoG
						.getRegistroHelperCodigoBarras()
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2();
				
				String idImovel = idImovelSemDigito + Util.obterDigitoVerificadorModulo10(idImovelSemDigito);
				
				arrecadadorMovimentoItemHelper.setIdentificacao(idImovel);
				arrecadadorMovimentoItemHelper.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
			}
			else{
				
				String identificacao = registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento1();
				
				//GUIA DE PAGAMENTO
				GuiaPagamento guiaPagamento = this.pesquisarExistenciaGuiaPagamentoPorNumeroGuiaFatura(
				identificacao);
				
				if (guiaPagamento != null && guiaPagamento.getImovel() != null){
					arrecadadorMovimentoItemHelper.setIdentificacao(guiaPagamento.getImovel().getId().toString());
				}
				
				arrecadadorMovimentoItemHelper.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
			}
		}
	}

}

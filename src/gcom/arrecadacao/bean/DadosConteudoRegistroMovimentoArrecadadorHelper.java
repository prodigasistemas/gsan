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
package gcom.arrecadacao.bean;

import gcom.arrecadacao.pagamento.Pagamento;

import java.util.Collection;


/**
 * Classe que irá auxiliar no formato do retorno da pesquisa dos itens de um
 * determinado movimento do arrecadador
 *
 * @author Raphael Rossiter
 * @date 20/03/2006
 */
public class DadosConteudoRegistroMovimentoArrecadadorHelper {
	
	private String codigoRegistro;
	private String identificacaoClienteEmpresa;
	private String agenciaDebito;
	private String identificacaoClienteBanco;
	private String dataOpcaoExclusao;
	private String descricaoMovimento;
	private String ocorrencia;
	private String indicadorAceitacao;
	private String dataVencimentoDebito;
	private String valorDebito;
	private String codigoMoeda;
	private String codigoRetorno;
	private String mesAnoReferenciaConta;
	private String digitoVerificadorConta;
	private String identificacaoAgenciaContaDigitoCreditada;
	private String dataPagamento;
	private String dataPrevistaCredito;
	private String valorRecebido;
	private String valorTarifa;
	private String nsr;
	private String codigoAgenciaArrecadadora;
	private String formaArrecadacaoCaptura;
	private String numeroAutenticacaoCaixaOUCodigoTransacao;
	private String formaPagamento;
	private String codigoAgencia;
	private String nomeAgencia;
	private String nomeLogradouro;
	private String numero;
	private String codigoCep;
	private String sufixoCep;
	private String nomeCidade;
	private String siglaUnidadeFederacao;
	private String situacaoAgencia;
	
	//CARTÃO DE CRÉDITO/DÉBITO
	private String numeroCartao;
	private String dataTransacao;
	private String valorTransacao;
	private String numeroParcela;
	private String numeroParcelaDebito;
	
	
	private DadosConteudoCodigoBarrasHelper dadosConteudoCodigoBarrasHelper;
	
	private Collection<Pagamento> colecaoPagamentos;
	
	
	public Collection<Pagamento> getColecaoPagamentos() {
		return colecaoPagamentos;
	}


	public void setColecaoPagamentos(Collection<Pagamento> colecaoPagamentos) {
		this.colecaoPagamentos = colecaoPagamentos;
	}


	public String getCodigoMoeda() {
		return codigoMoeda;
	}


	public void setCodigoMoeda(String codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}


	public String getCodigoRetorno() {
		return codigoRetorno;
	}


	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}


	public String getDataVencimentoDebito() {
		return dataVencimentoDebito;
	}


	public void setDataVencimentoDebito(String dataVencimentoDebito) {
		this.dataVencimentoDebito = dataVencimentoDebito;
	}


	public String getDigitoVerificadorConta() {
		return digitoVerificadorConta;
	}


	public void setDigitoVerificadorConta(String digitoVerificadorConta) {
		this.digitoVerificadorConta = digitoVerificadorConta;
	}


	public String getMesAnoReferenciaConta() {
		return mesAnoReferenciaConta;
	}


	public void setMesAnoReferenciaConta(String mesAnoReferenciaConta) {
		this.mesAnoReferenciaConta = mesAnoReferenciaConta;
	}


	public String getValorDebito() {
		return valorDebito;
	}


	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}


	public DadosConteudoRegistroMovimentoArrecadadorHelper() {}


	public String getAgenciaDebito() {
		return agenciaDebito;
	}


	public void setAgenciaDebito(String agenciaDebito) {
		this.agenciaDebito = agenciaDebito;
	}


	public String getCodigoRegistro() {
		return codigoRegistro;
	}


	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}


	public String getDataOpcaoExclusao() {
		return dataOpcaoExclusao;
	}


	public void setDataOpcaoExclusao(String dataOpcaoExclusao) {
		this.dataOpcaoExclusao = dataOpcaoExclusao;
	}


	public String getDescricaoMovimento() {
		return descricaoMovimento;
	}


	public void setDescricaoMovimento(String descricaoMovimento) {
		this.descricaoMovimento = descricaoMovimento;
	}


	public String getIdentificacaoClienteBanco() {
		return identificacaoClienteBanco;
	}


	public void setIdentificacaoClienteBanco(String identificacaoClienteBanco) {
		this.identificacaoClienteBanco = identificacaoClienteBanco;
	}


	public String getIdentificacaoClienteEmpresa() {
		return identificacaoClienteEmpresa;
	}


	public void setIdentificacaoClienteEmpresa(String identificacaoClienteEmpresa) {
		this.identificacaoClienteEmpresa = identificacaoClienteEmpresa;
	}


	public String getIndicadorAceitacao() {
		return indicadorAceitacao;
	}


	public void setIndicadorAceitacao(String indicadorAceitacao) {
		this.indicadorAceitacao = indicadorAceitacao;
	}


	public String getOcorrencia() {
		return ocorrencia;
	}


	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}


	public String getCodigoAgenciaArrecadadora() {
		return codigoAgenciaArrecadadora;
	}


	public void setCodigoAgenciaArrecadadora(String codigoAgenciaArrecadadora) {
		this.codigoAgenciaArrecadadora = codigoAgenciaArrecadadora;
	}


	public String getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public String getDataPrevistaCredito() {
		return dataPrevistaCredito;
	}


	public void setDataPrevistaCredito(String dataPrevistaCredito) {
		this.dataPrevistaCredito = dataPrevistaCredito;
	}


	public String getFormaArrecadacaoCaptura() {
		return formaArrecadacaoCaptura;
	}


	public void setFormaArrecadacaoCaptura(String formaArrecadacaoCaptura) {
		this.formaArrecadacaoCaptura = formaArrecadacaoCaptura;
	}


	public String getFormaPagamento() {
		return formaPagamento;
	}


	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	public String getIdentificacaoAgenciaContaDigitoCreditada() {
		return identificacaoAgenciaContaDigitoCreditada;
	}


	public void setIdentificacaoAgenciaContaDigitoCreditada(
			String identificacaoAgenciaContaDigitoCreditada) {
		this.identificacaoAgenciaContaDigitoCreditada = identificacaoAgenciaContaDigitoCreditada;
	}


	public String getNsr() {
		return nsr;
	}


	public void setNsr(String nsr) {
		this.nsr = nsr;
	}


	public String getNumeroAutenticacaoCaixaOUCodigoTransacao() {
		return numeroAutenticacaoCaixaOUCodigoTransacao;
	}


	public void setNumeroAutenticacaoCaixaOUCodigoTransacao(
			String numeroAutenticacaoCaixaOUCodigoTransacao) {
		this.numeroAutenticacaoCaixaOUCodigoTransacao = numeroAutenticacaoCaixaOUCodigoTransacao;
	}


	public String getValorRecebido() {
		return valorRecebido;
	}


	public void setValorRecebido(String valorRecebido) {
		this.valorRecebido = valorRecebido;
	}


	public String getValorTarifa() {
		return valorTarifa;
	}


	public void setValorTarifa(String valorTarifa) {
		this.valorTarifa = valorTarifa;
	}


	public String getCodigoAgencia() {
		return codigoAgencia;
	}


	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}


	public String getCodigoCep() {
		return codigoCep;
	}


	public void setCodigoCep(String codigoCep) {
		this.codigoCep = codigoCep;
	}


	public String getNomeAgencia() {
		return nomeAgencia;
	}


	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}


	public String getNomeCidade() {
		return nomeCidade;
	}


	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}


	public String getNomeLogradouro() {
		return nomeLogradouro;
	}


	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getSiglaUnidadeFederacao() {
		return siglaUnidadeFederacao;
	}


	public void setSiglaUnidadeFederacao(String siglaUnidadeFederacao) {
		this.siglaUnidadeFederacao = siglaUnidadeFederacao;
	}


	public String getSituacaoAgencia() {
		return situacaoAgencia;
	}


	public void setSituacaoAgencia(String situacaoAgencia) {
		this.situacaoAgencia = situacaoAgencia;
	}


	public String getSufixoCep() {
		return sufixoCep;
	}


	public void setSufixoCep(String sufixoCep) {
		this.sufixoCep = sufixoCep;
	}


	public DadosConteudoCodigoBarrasHelper getDadosConteudoCodigoBarrasHelper() {
		return dadosConteudoCodigoBarrasHelper;
	}


	public void setDadosConteudoCodigoBarrasHelper(
			DadosConteudoCodigoBarrasHelper dadosConteudoCodigoBarrasHelper) {
		this.dadosConteudoCodigoBarrasHelper = dadosConteudoCodigoBarrasHelper;
	}


	public String getDataTransacao() {
		return dataTransacao;
	}


	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}


	public String getNumeroCartao() {
		return numeroCartao;
	}


	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}


	public String getNumeroParcela() {
		return numeroParcela;
	}


	public void setNumeroParcela(String numeroParcela) {
		this.numeroParcela = numeroParcela;
	}


	public String getNumeroParcelaDebito() {
		return numeroParcelaDebito;
	}


	public void setNumeroParcelaDebito(String numeroParcelaDebito) {
		this.numeroParcelaDebito = numeroParcelaDebito;
	}


	public String getValorTransacao() {
		return valorTransacao;
	}


	public void setValorTransacao(String valorTransacao) {
		this.valorTransacao = valorTransacao;
	}
}

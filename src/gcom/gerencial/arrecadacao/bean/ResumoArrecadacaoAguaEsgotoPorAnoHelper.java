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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gerencial.arrecadacao.bean;

import java.math.BigDecimal;


/**
 * Classe bean para agrupamento dos historicos 
 * de consumo com as quebras solicitadas
 *  
 * @author Fernando Fontelles
 * @date 03/06/2010
 * 		 
 */

public class ResumoArrecadacaoAguaEsgotoPorAnoHelper {

	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idCodigoElo;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer idPerfilImovel;
	private Integer idEsferaPoder;
	private Integer idTipoCliente;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idSubCategoria;
	private Integer idPerfilLigacaoAgua;
	private Integer idPerfilLigacaoEsgoto;
	private Integer idTipoDocumento;
	private Integer idSituacaoPagamento;
	private Integer idIndicadorContasRecebidas;
	private Integer idEpocaPagamento;
	private Integer idCodigoSetorComercial;
	private Integer idFormaArrecadacao;
	private Integer idAgenteArrecadador;
	private BigDecimal valorAgua = new BigDecimal(0);
	private BigDecimal valorEsgoto = new BigDecimal(0);
	private BigDecimal valorNaoIdentificado = new BigDecimal(0);
	private BigDecimal valorImpostos = new BigDecimal(0);
	private Integer quantidadeContas = new Integer(0);
	private short indicadorHidrometro;
	private Integer anoMesReferenciaDocumento;
	private Integer quantidadePagamentos = new Integer(0);
	
	// Dados da Arrecadacao OUTROS
	private Integer idTipoFinanciamento;
	private Integer idLancamentoItemContabilOutros;
	private BigDecimal valorDebitos = new BigDecimal(0);
	
	// Dados da Arrecadacao CREDITOS
	private Integer idCreditoOrigem;
	private Integer idLancamentoItemContabilCredito;
	private BigDecimal valorCredito = new BigDecimal(0);
	
	// Dados da Devolucao
	private BigDecimal valorDevolucoesClassificadas = new BigDecimal(0);
	private BigDecimal valorDevolucoesNaoClassificadas = new BigDecimal(0);
	private Integer idDevolucaoSituacaoAtual = null;
	
	/**
	 * Construtor com a sequencia correta de quebras para o 
	 * caso de uso Gerar resumo da Arrecadacao Por Ano
	 * 
	 * OBS - 4 quebras adicionais nao foram passadas neste contrutor,
	 * a saber, idCategoria, idSubCatergoria, idEsferaPoder e idTipoCliente,
	 * pois no momento da criacao deste objeto essas informacoes nao estao disponiveis.
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idCodigoElo
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param idPerfilImovel
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto
	 * @param idTipoDocumento
	 * @param idSituacaoPagamento
	 * @param idIndicadorContasRecebidas
	 * @param idEpocaPagamento
	 * @param idCodigoSetorComercial
	 */
	
	public ResumoArrecadacaoAguaEsgotoPorAnoHelper(
			Integer idGerenciaRegional,
			Integer idUnidadeNegocio,
			Integer idCodigoElo,
			Integer idLocalidade,
			Integer idSetorComercial,
			Integer idPerfilImovel,
			Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto,
			Integer idPerfilLigacaoAgua,
			Integer idPerfilLigacaoEsgoto,
			Integer idTipoDocumento,
			Integer idSituacaoPagamento,
			Integer idIndicadorContasRecebidas,
			Integer idEpocaPagamento,
			Integer idCodigoSetorComercial,
			Integer idFormaArrecadacao,
			Integer idAgenteArrecadador) {
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idCodigoElo = idCodigoElo;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
		this.idTipoDocumento = idTipoDocumento;
		this.idSituacaoPagamento = idSituacaoPagamento;
		this.idIndicadorContasRecebidas = idIndicadorContasRecebidas;
		this.idEpocaPagamento = idEpocaPagamento;
		this.idCodigoSetorComercial = idCodigoSetorComercial;
		this.idFormaArrecadacao = idFormaArrecadacao;
		this.idAgenteArrecadador = idAgenteArrecadador;
	}
	
	public ResumoArrecadacaoAguaEsgotoPorAnoHelper() {}
	

	public Integer getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}


	public Integer getIdCodigoElo() {
		return idCodigoElo;
	}


	public void setIdCodigoElo(Integer idCodigoElo) {
		this.idCodigoElo = idCodigoElo;
	}


	public Integer getIdCodigoSetorComercial() {
		return idCodigoSetorComercial;
	}


	public void setIdCodigoSetorComercial(Integer idCodigoSetorComercial) {
		this.idCodigoSetorComercial = idCodigoSetorComercial;
	}


	public Integer getIdEpocaPagamento() {
		return idEpocaPagamento;
	}


	public void setIdEpocaPagamento(Integer idEpocaPagamento) {
		this.idEpocaPagamento = idEpocaPagamento;
	}


	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}


	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}


	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}


	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}


	public Integer getIdIndicadorContasRecebidas() {
		return idIndicadorContasRecebidas;
	}


	public void setIdIndicadorContasRecebidas(Integer idIndicadorContasRecebidas) {
		this.idIndicadorContasRecebidas = idIndicadorContasRecebidas;
	}


	public Integer getIdLocalidade() {
		return idLocalidade;
	}


	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}


	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}


	public Integer getIdPerfilLigacaoAgua() {
		return idPerfilLigacaoAgua;
	}


	public void setIdPerfilLigacaoAgua(Integer idPerfilLigacaoAgua) {
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
	}


	public Integer getIdPerfilLigacaoEsgoto() {
		return idPerfilLigacaoEsgoto;
	}


	public void setIdPerfilLigacaoEsgoto(Integer idPerfilLigacaoEsgoto) {
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
	}
	
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}


	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}


	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}


	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}


	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}


	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}


	public Integer getIdSituacaoPagamento() {
		return idSituacaoPagamento;
	}


	public void setIdSituacaoPagamento(Integer idSituacaoPagamento) {
		this.idSituacaoPagamento = idSituacaoPagamento;
	}


	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}


	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}


	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}


	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}


	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}


	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}


	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}


	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public BigDecimal getValorAgua() {
		return valorAgua;
	}


	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}


	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}


	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}


	public BigDecimal getValorNaoIdentificado() {
		return valorNaoIdentificado;
	}


	public void setValorNaoIdentificado(BigDecimal valorNaoIdentificado) {
		this.valorNaoIdentificado = valorNaoIdentificado;
	}
	
	
	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}


	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}
	
	
	/**
	 * Compara duas properiedades inteiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais( Integer pro1, Integer pro2 ){
	  if ( pro2 != null ){
		  if ( !pro2.equals( pro1 ) ){
			  return false;
		  }
	  } else if ( pro1 != null ){
		  return false;
	  }
	  
	  // Se chegou ate aqui quer dizer que as propriedades sao iguais
	  return true;
	}
	
	private boolean propriedadesIguais( short pro1, short pro2 ){
	  if ( pro2 != pro1 ){
		  return false;
	  }	  

	  return true;
	}
	
	
    /**
     * Compara dois objetos levando em consideracao apenas as propriedades
     * que identificam o agrupamento
     * 
     * @param obj Objeto a ser comparado com a instancia deste objeto 
     */
	public boolean equals( Object obj ){
		
		if ( !( obj instanceof ResumoArrecadacaoAguaEsgotoPorAnoHelper ) ){
			return false;			
		} else {
			ResumoArrecadacaoAguaEsgotoPorAnoHelper resumoTemp = (ResumoArrecadacaoAguaEsgotoPorAnoHelper) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
			  propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
			  propriedadesIguais( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio ) &&
			  propriedadesIguais( this.idCodigoElo, resumoTemp.idCodigoElo ) &&
			  propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
			  propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial ) &&
			  propriedadesIguais( this.idPerfilImovel, resumoTemp.idPerfilImovel ) &&
			  propriedadesIguais( this.idEsferaPoder, resumoTemp.idEsferaPoder ) &&
			  propriedadesIguais( this.idTipoCliente, resumoTemp.idTipoCliente ) &&
			  propriedadesIguais( this.idSituacaoLigacaoAgua, resumoTemp.idSituacaoLigacaoAgua ) &&
			  propriedadesIguais( this.idSituacaoLigacaoEsgoto, resumoTemp.idSituacaoLigacaoEsgoto ) &&
			  propriedadesIguais( this.idCategoria, resumoTemp.idCategoria ) &&
			  propriedadesIguais( this.idSubCategoria, resumoTemp.idSubCategoria ) &&			  
			  propriedadesIguais( this.idPerfilLigacaoAgua, resumoTemp.idPerfilLigacaoAgua ) &&
			  propriedadesIguais( this.idPerfilLigacaoEsgoto, resumoTemp.idPerfilLigacaoEsgoto ) &&
			  propriedadesIguais( this.idTipoDocumento, resumoTemp.idTipoDocumento ) &&
			  propriedadesIguais( this.idSituacaoPagamento, resumoTemp.idSituacaoPagamento ) &&
			  propriedadesIguais( this.idIndicadorContasRecebidas, resumoTemp.idIndicadorContasRecebidas ) &&
			  propriedadesIguais( this.idEpocaPagamento, resumoTemp.idEpocaPagamento ) &&
			  propriedadesIguais( this.idCodigoSetorComercial, resumoTemp.idCodigoSetorComercial ) &&
			  propriedadesIguais( this.idFormaArrecadacao, resumoTemp.idFormaArrecadacao ) &&
			  propriedadesIguais( this.idAgenteArrecadador, resumoTemp.idAgenteArrecadador ) && 
			  propriedadesIguais( this.indicadorHidrometro, resumoTemp.indicadorHidrometro ) &&
			  propriedadesIguais( this.anoMesReferenciaDocumento, resumoTemp.anoMesReferenciaDocumento );
			  
		}
	}


	public Integer getIdAgenteArrecadador() {
		return idAgenteArrecadador;
	}


	public void setIdAgenteArrecadador(Integer idAgenteArrecadador) {
		this.idAgenteArrecadador = idAgenteArrecadador;
	}


	public Integer getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}


	public void setIdFormaArrecadacao(Integer idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}


	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}


	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}


	public Integer getAnoMesReferenciaDocumento() {
		return anoMesReferenciaDocumento;
	}


	public void setAnoMesReferenciaDocumento(Integer anoMesReferenciaDocumento) {
		this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
	}


	public short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}


	public void setIndicadorHidrometro(short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}


	public Integer getIdCreditoOrigem() {
		return idCreditoOrigem;
	}


	public void setIdCreditoOrigem(Integer idCreditoOrigem) {
		this.idCreditoOrigem = idCreditoOrigem;
	}


	public Integer getIdLancamentoItemContabilCredito() {
		return idLancamentoItemContabilCredito;
	}


	public void setIdLancamentoItemContabilCredito(
			Integer idLancamentoItemContabilCredito) {
		this.idLancamentoItemContabilCredito = idLancamentoItemContabilCredito;
	}


	public Integer getIdLancamentoItemContabilOutros() {
		return idLancamentoItemContabilOutros;
	}


	public void setIdLancamentoItemContabilOutros(
			Integer idLancamentoItemContabilOutros) {
		this.idLancamentoItemContabilOutros = idLancamentoItemContabilOutros;
	}


	public Integer getIdTipoFinanciamento() {
		return idTipoFinanciamento;
	}


	public void setIdTipoFinanciamento(Integer idTipoFinanciamento) {
		this.idTipoFinanciamento = idTipoFinanciamento;
	}


	public BigDecimal getValorCredito() {
		return valorCredito;
	}


	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}


	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}


	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}
	
	public Integer getIdDevolucaoSituacaoAtual() {
		return idDevolucaoSituacaoAtual;
	}


	public void setIdDevolucaoSituacaoAtual(Integer idDevolucaoSituacaoAtual) {
		this.idDevolucaoSituacaoAtual = idDevolucaoSituacaoAtual;
	}


	public BigDecimal getValorDevolucoesClassificadas() {
		return valorDevolucoesClassificadas;
	}


	public void setValorDevolucoesClassificadas(
			BigDecimal valorDevolucoesClassificadas) {
		this.valorDevolucoesClassificadas = valorDevolucoesClassificadas;
	}


	public BigDecimal getValorDevolucoesNaoClassificadas() {
		return valorDevolucoesNaoClassificadas;
	}


	public void setValorDevolucoesNaoClassificadas(
			BigDecimal valorDevolucoesNaoClassificadas) {
		this.valorDevolucoesNaoClassificadas = valorDevolucoesNaoClassificadas;
	}

	public Integer getQuantidadePagamentos() {
		return quantidadePagamentos;
	}

	public void setQuantidadePagamentos(Integer quantidadePagamentos) {
		this.quantidadePagamentos = quantidadePagamentos;
	}
}

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
package gcom.gerencial.cadastro.bean;

import java.math.BigDecimal;

/**
 * Classe responsável por ajudar o caso de uso Gerar Resumo do Parcelamento Por Ano 
 *
 * @author Fernando Fontelles
 * @date 21/06/2010
 */
public class ResumoParcelamentoPorAnoHelper {

	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer idElo;
	private Integer idSetorComercial;
//	private Integer idRota;
//	private Integer idQuadra;
	private Integer codigoSetorComercial;
//	private Integer numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idEsfera;
	private Integer idTipoClienteResponsavel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idPerfilLigacaoAgua;
	private Integer idPerfilLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idSubCategoria;
	private Integer anoMesReferencia;
	private Integer consumoTarifa;
	private Short indicadorHidrometro;
	private Short numeroParcelamentoConsecutivos;
//	private Short codigoRota;

	
	private Integer qtdContas = new Integer(0);
	private Integer qtdGuias = new Integer(0);
	private Short qtdServicosIndiretos = 0;
	private Short qtdParcelamento = 0;
	private BigDecimal vlContas = new BigDecimal(0);
	private BigDecimal vlGuias = new BigDecimal(0);
	private BigDecimal vlServicosIndiretos = new BigDecimal(0);
	private BigDecimal vlCreditoRealizar = new BigDecimal(0);
	private BigDecimal vlAcrescimoImpontualidade = new BigDecimal(0);
	private BigDecimal vlSancoes = new BigDecimal(0);
	private BigDecimal vlDescontoAcrescimo = new BigDecimal(0);
	private BigDecimal vlDescontoInatividade = new BigDecimal(0);
	private BigDecimal vlDescontoAntiguidade = new BigDecimal(0);
	private BigDecimal vlTotalParcelamento = new BigDecimal(0);
	
	
	private BigDecimal vlDebitosACobrarTotal = new BigDecimal(0);
	private BigDecimal vlDebitosACobrarAcrescimos = new BigDecimal(0);
	private BigDecimal vlDebitosACobrarReligSancoes = new BigDecimal(0);
	private BigDecimal vlDebitosACobrarParcelamentos = new BigDecimal(0);
	private BigDecimal vlEntrada = new BigDecimal(0);
	private BigDecimal vlJurosParcelamento = new BigDecimal(0);
	private Short qtdMediaParcelas = 0;
	private Short qtdTotalParcelas = 0;
	
	
	
	public Short getQtdMediaParcelas() {
		return qtdMediaParcelas;
	}

	public void setQtdMediaParcelas(Short qtdMediaParcelas) {
		this.qtdMediaParcelas = qtdMediaParcelas;
	}

	public Short getQtdTotalParcelas() {
		return qtdTotalParcelas;
	}

	public void setQtdTotalParcelas(Short qtdTotalParcelas) {
		this.qtdTotalParcelas = qtdTotalParcelas;
	}

	public BigDecimal getVlDebitosACobrarAcrescimos() {
		return vlDebitosACobrarAcrescimos;
	}

	public void setVlDebitosACobrarAcrescimos(BigDecimal vlDebitosACobrarAcrescimos) {
		this.vlDebitosACobrarAcrescimos = vlDebitosACobrarAcrescimos;
	}

	public BigDecimal getVlDebitosACobrarParcelamentos() {
		return vlDebitosACobrarParcelamentos;
	}

	public void setVlDebitosACobrarParcelamentos(
			BigDecimal vlDebitosACobrarParcelamentos) {
		this.vlDebitosACobrarParcelamentos = vlDebitosACobrarParcelamentos;
	}

	public BigDecimal getVlDebitosACobrarReligSancoes() {
		return vlDebitosACobrarReligSancoes;
	}

	public void setVlDebitosACobrarReligSancoes(
			BigDecimal vlDebitosACobrarReligSancoes) {
		this.vlDebitosACobrarReligSancoes = vlDebitosACobrarReligSancoes;
	}

	public BigDecimal getVlDebitosACobrarTotal() {
		return vlDebitosACobrarTotal;
	}

	public void setVlDebitosACobrarTotal(BigDecimal vlDebitosACobrarTotal) {
		this.vlDebitosACobrarTotal = vlDebitosACobrarTotal;
	}

	public BigDecimal getVlEntrada() {
		return vlEntrada;
	}

	public void setVlEntrada(BigDecimal vlEntrada) {
		this.vlEntrada = vlEntrada;
	}

	public BigDecimal getVlJurosParcelamento() {
		return vlJurosParcelamento;
	}

	public void setVlJurosParcelamento(BigDecimal vlJurosParcelamento) {
		this.vlJurosParcelamento = vlJurosParcelamento;
	}
	
//	public Short getCodigoRota() {
//		return codigoRota;
//	}
//
//	public void setCodigoRota(Short codigoRota) {
//		this.codigoRota = codigoRota;
//	}

	/**
	 * Construtor com a sequencia correta de quebras para o 
	 * caso de uso Gerar resumo do Parcelamento Por Ano
	 * 
	 * OBS - Duas quebras adicionais nao foram passadas neste contrutor,
	 * a saber, idCategoria e idSubCatergoria, pois no momento da criacao deste objeto
	 * essas informacoes nao estao disponiveis. 
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idLocalidade
	 * @param idElo
	 * @param idSetorComercial
	 * @param codigoSetorComercial
	 * @param idPerfilImovel
	 * @param idEsfera
	 * @param idTipoClienteResponsavel
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 */
	public ResumoParcelamentoPorAnoHelper( 
			Integer idGerenciaRegional,
			Integer idUnidadeNegocio,
			Integer idElo,
			Integer idLocalidade,
			Integer idSetorComercial,
//			Integer idRota,
//			Integer idQuadra,
			Integer codigoSetorComercial,
//			Integer numeroQuadra,
			Integer idPerfilImovel,
			Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto,
			Integer idPerfilLigacaoAgua,
			Integer idPerfilLigacaoEsgoto,
			Integer consumoTarifa,
			Short indicadorHidrometro,
			Short numeroParcelamentoConsecutivos
//			Short codigoRota
			){
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idElo = idElo;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
//		this.idRota = idRota;
//		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
//		this.numeroQuadra = numeroQuadra; 
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
		this.consumoTarifa = consumoTarifa;
		this.indicadorHidrometro = indicadorHidrometro;
		this.numeroParcelamentoConsecutivos = numeroParcelamentoConsecutivos;
//		this.codigoRota = codigoRota;
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
	
    /**
     * Compara dois objetos levando em consideracao apenas as propriedades
     * que identificam o agrupamento
     * 
     * @param obj Objeto a ser comparado com a instancia deste objeto 
     */
	public boolean equals( Object obj ){
		
		if ( !( obj instanceof ResumoParcelamentoPorAnoHelper ) ){
			return false;			
		} else {
			ResumoParcelamentoPorAnoHelper resumoTemp = ( ResumoParcelamentoPorAnoHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
			  propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
			  propriedadesIguais( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio ) &&
			  propriedadesIguais( this.idElo, resumoTemp.idElo ) &&
			  propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
			  propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial ) &&
//			  propriedadesIguais( this.idRota, resumoTemp.idRota ) &&
//			  propriedadesIguais( this.idQuadra, resumoTemp.idQuadra ) &&
			  propriedadesIguais( this.codigoSetorComercial, resumoTemp.codigoSetorComercial ) &&
//			  propriedadesIguais( this.numeroQuadra, resumoTemp.numeroQuadra ) &&
			  propriedadesIguais( this.idPerfilImovel, resumoTemp.idPerfilImovel ) &&
			  propriedadesIguais( this.idEsfera, resumoTemp.idEsfera ) &&
			  propriedadesIguais( this.idTipoClienteResponsavel, resumoTemp.idTipoClienteResponsavel ) &&
			  propriedadesIguais( this.idSituacaoLigacaoAgua, resumoTemp.idSituacaoLigacaoAgua ) &&
			  propriedadesIguais( this.idSituacaoLigacaoEsgoto, resumoTemp.idSituacaoLigacaoEsgoto ) &&
			  propriedadesIguais( this.idPerfilLigacaoAgua, resumoTemp.idPerfilLigacaoAgua ) &&
			  propriedadesIguais( this.idPerfilLigacaoEsgoto, resumoTemp.idPerfilLigacaoEsgoto ) &&
			  propriedadesIguais( this.idCategoria, resumoTemp.idCategoria ) &&
			  propriedadesIguais( this.idSubCategoria, resumoTemp.idSubCategoria ) &&
			  propriedadesIguais( this.consumoTarifa, resumoTemp.consumoTarifa ) && 
			  propriedadesIguais( this.indicadorHidrometro.intValue(), resumoTemp.indicadorHidrometro.intValue() ) &&
			  propriedadesIguais( this.numeroParcelamentoConsecutivos.intValue(), resumoTemp.numeroParcelamentoConsecutivos.intValue());
		}		
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}

	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public Integer getIdEsfera() {
		return idEsfera;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

//	public Integer getIdQuadra() {
//		return idQuadra;
//	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	public Integer getIdTipoClienteResponsavel() {
		return idTipoClienteResponsavel;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

//	public Integer getNumeroQuadra() {
//		return numeroQuadra;
//	}

	public Integer getIdElo() {
		return idElo;
	}

//	public Integer getIdRota() {
//		return idRota;
//	}

	public void setIdEsfera(Integer idEsfera) {
		this.idEsfera = idEsfera;
	}

	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	public void setIdTipoClienteResponsavel(Integer idTipoClienteResponsavel) {
		this.idTipoClienteResponsavel = idTipoClienteResponsavel;
	}
	public Integer getQtdContas() {
		return qtdContas;
	}

	public void setQtdContas(Integer qtdContas) {
		this.qtdContas = qtdContas;
	}

	public Integer getQtdGuias() {
		return qtdGuias;
	}

	public void setQtdGuias(Integer qtdGuias) {
		this.qtdGuias = qtdGuias;
	}
	public Short getQtdServicosIndiretos() {
		return qtdServicosIndiretos;
	}

	public void setQtdServicosIndiretos(Short qtdServicosIndiretos) {
		this.qtdServicosIndiretos = qtdServicosIndiretos;
	}

	public Short getQtdParcelamento() {
		return qtdParcelamento;
	}

	public void setQtdParcelamento(Short qtdParcelamento) {
		this.qtdParcelamento = qtdParcelamento;
	}
	public BigDecimal getVlContas() {
		return vlContas;
	}

	public void setVlContas(BigDecimal vlContas) {
		this.vlContas = vlContas;
	}

	public BigDecimal getVlGuias() {
		return vlGuias;
	}

	public void setVlGuias(BigDecimal vlGuias) {
		this.vlGuias = vlGuias;
	}

	public BigDecimal getVlServicosIndiretos() {
		return vlServicosIndiretos;
	}

	public void setVlServicosIndiretos(BigDecimal vlServicosIndiretos) {
		this.vlServicosIndiretos = vlServicosIndiretos;
	}

	public BigDecimal getVlAcrescimoImpontualidade() {
		return vlAcrescimoImpontualidade;
	}

	public void setVlAcrescimoImpontualidade(BigDecimal vlAcrescimoImpontualidade) {
		this.vlAcrescimoImpontualidade = vlAcrescimoImpontualidade;
	}

	public BigDecimal getVlCreditoRealizar() {
		return vlCreditoRealizar;
	}

	public void setVlCreditoRealizar(BigDecimal vlCreditoRealizar) {
		this.vlCreditoRealizar = vlCreditoRealizar;
	}
	public BigDecimal getVlSancoes() {
		return vlSancoes;
	}

	public void setVlSancoes(BigDecimal vlSancoes) {
		this.vlSancoes = vlSancoes;
	}
	public BigDecimal getVlDescontoAcrescimo() {
		return vlDescontoAcrescimo;
	}

	public void setVlDescontoAcrescimo(BigDecimal vlDescontoAcrescimo) {
		this.vlDescontoAcrescimo = vlDescontoAcrescimo;
	}

	public BigDecimal getVlDescontoAntiguidade() {
		return vlDescontoAntiguidade;
	}

	public void setVlDescontoAntiguidade(BigDecimal vlDescontoAntiguidade) {
		this.vlDescontoAntiguidade = vlDescontoAntiguidade;
	}

	public BigDecimal getVlDescontoInatividade() {
		return vlDescontoInatividade;
	}

	public void setVlDescontoInatividade(BigDecimal vlDescontoInatividade) {
		this.vlDescontoInatividade = vlDescontoInatividade;
	}

	public BigDecimal getVlTotalParcelamento() {
		return vlTotalParcelamento;
	}

	public void setVlTotalParcelamento(BigDecimal vlTotalParcelamento) {
		this.vlTotalParcelamento = vlTotalParcelamento;
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

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getConsumoTarifa() {
		return consumoTarifa;
	}

	public void setConsumoTarifa(Integer consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	public Short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(Short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public Short getNumeroParcelamentoConsecutivos() {
		return numeroParcelamentoConsecutivos;
	}

	public void setNumeroParcelamentoConsecutivos(
			Short numeroParcelamentoConsecutivos) {
		this.numeroParcelamentoConsecutivos = numeroParcelamentoConsecutivos;
	}
}
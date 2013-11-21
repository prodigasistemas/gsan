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
package gcom.gerencial.cobranca.bean;

import java.math.BigDecimal;

/**
 * Classe responsável por ajudar Gerar Resumo da Pendencia Por Ano
 *
 * @author Fernando Fontelles Filho
 * @date 25/03/2010
 */
public class ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper {	
	
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idElo;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Integer idPerfilImovel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idPrincipalCategoriaImovel;
	private Integer idPrincipalSubCategoriaImovel;
	private Integer idEsferaPoder;
	private Integer idHidrometro;
	private Integer idTipoDocumento;
	private Integer anoMesReferenciaDocumento;
	private Integer idTipoFinanciamento;
	private Integer idReferenciaVencimentoConta;	
	private Integer quantidadeLigacoes = 0;
	private Integer quantidadeDocumentos = 1;
	private BigDecimal valorPendenteDebito = new BigDecimal(0);
	
	public Integer getAnoMesReferenciaDocumento() {
		return anoMesReferenciaDocumento;
	}
	public void setAnoMesReferenciaDocumento(Integer anoMesReferenciaDocumento) {
		this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
	}
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComericial(Integer codigoSetorComericial) {
		this.codigoSetorComercial = codigoSetorComericial;
	}
	public Integer getIdElo() {
		return idElo;
	}
	public void setIdElo(Integer idElo) {
		this.idElo = idElo;
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
	
	public Integer getIdPrincipalCategoriaImovel() {
		return idPrincipalCategoriaImovel;
	}
	public void setIdPrincipalCategoriaImovel(Integer idPrincipalCategoriaImovel) {
		this.idPrincipalCategoriaImovel = idPrincipalCategoriaImovel;
	}
	public Integer getIdPrincipalSubCategoriaImovel() {
		return idPrincipalSubCategoriaImovel;
	}
	public void setIdPrincipalSubCategoriaImovel(Integer idPrincipalSubCategoriaImovel) {
		this.idPrincipalSubCategoriaImovel = idPrincipalSubCategoriaImovel;
	}
	
	public Integer getIdReferenciaVencimentoConta() {
		return idReferenciaVencimentoConta;
	}
	public void setIdReferenciaVencimentoConta(Integer idReferenciaVencimentoConta) {
		this.idReferenciaVencimentoConta = idReferenciaVencimentoConta;
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
	
	public Integer getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}
	public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	public BigDecimal getValorPendenteDebito() {
		return valorPendenteDebito;
	}
	public void setValorPendenteDebito(BigDecimal valorPendenteDebito) {
		if ( valorPendenteDebito != null ){
			this.valorPendenteDebito = valorPendenteDebito;
		}
	}

	/**
	 * Construtor com os campos iniciais do objeto
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idElo
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param idRota
	 * @param idQuadra
	 * @param codigoSetorComericial
	 * @param numeroQuadra
	 * @param idPerfilImovel
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto
	 * @param idVolFixadoAgua
	 * @param idVolFixadoEsgoto
	 * @param idTipoDocumento
	 * @param anoMesReferenciaDocumento
	 * @param idTipoFinanciamento
	 * @param idReferenciaVencimentoConta
	 */
	public ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper(
			Integer idGerenciaRegional, //0
			Integer idUnidadeNegocio, //1
			Integer idElo, //2
			Integer idLocalidade, //3
			Integer idSetorComercial, //4
			Integer codigoSetorComercial, //5
			Integer idPerfilImovel, //6
			Integer idSituacaoLigacaoAgua, //7
			Integer idSituacaoLigacaoEsgoto, //8
			Integer idHidrometro, //9
			Integer idTipoDocumento, //10
			Integer anoMesReferenciaDocumento, //11
			Integer idTipoFinanciamento, //12
			Integer idReferenciaVencimentoConta, //13
			BigDecimal valorPendenteDebito //14
			) {			
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idElo = idElo;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.codigoSetorComercial = codigoSetorComercial;
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idHidrometro = idHidrometro;
		this.idTipoDocumento = idTipoDocumento;
		this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
		this.idTipoFinanciamento = idTipoFinanciamento;
		this.idReferenciaVencimentoConta = idReferenciaVencimentoConta;				
		this.valorPendenteDebito = ( valorPendenteDebito == null ? new BigDecimal(0) : valorPendenteDebito );
	}
	
	/**
	 * Compara duas properiedades Integereiras, comparando
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
		
		if ( !( obj instanceof ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper ) ){
			return false;			
		} else {
			ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper resumoTemp = ( 
					ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
			  propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
			  propriedadesIguais( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio ) &&
			  propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
			  propriedadesIguais( this.idElo, resumoTemp.idElo ) &&
			  propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial ) &&
			  propriedadesIguais( this.codigoSetorComercial, resumoTemp.codigoSetorComercial ) &&
			  propriedadesIguais( this.idPerfilImovel, resumoTemp.idPerfilImovel ) &&
			  propriedadesIguais( this.idEsferaPoder, resumoTemp.idEsferaPoder ) &&
			  propriedadesIguais( this.idSituacaoLigacaoAgua, resumoTemp.idSituacaoLigacaoAgua ) &&
			  propriedadesIguais( this.idSituacaoLigacaoEsgoto, resumoTemp.idSituacaoLigacaoEsgoto ) &&
			  propriedadesIguais( this.idPrincipalCategoriaImovel, resumoTemp.idPrincipalCategoriaImovel ) &&
			  propriedadesIguais( this.idPrincipalSubCategoriaImovel, resumoTemp.idPrincipalSubCategoriaImovel ) &&
			  propriedadesIguais( this.idHidrometro, resumoTemp.idHidrometro ) &&
			  propriedadesIguais( this.idTipoDocumento, resumoTemp.idTipoDocumento ) &&		
			  propriedadesIguais( this.anoMesReferenciaDocumento, resumoTemp.anoMesReferenciaDocumento ) &&
			  propriedadesIguais( this.idTipoFinanciamento, resumoTemp.idTipoFinanciamento ) &&
			  propriedadesIguais( this.idReferenciaVencimentoConta, resumoTemp.idReferenciaVencimentoConta );
		}		
	}
	public Integer getIdHidrometro() {
		return idHidrometro;
	}
	public void setIdHidrometro(Integer idHidrometro) {
		this.idHidrometro = idHidrometro;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public Integer getIdTipoFinanciamento() {
		return idTipoFinanciamento;
	}
	public void setIdTipoFinanciamento(Integer idTipoFinanciamento) {
		this.idTipoFinanciamento = idTipoFinanciamento;
	}
	
}

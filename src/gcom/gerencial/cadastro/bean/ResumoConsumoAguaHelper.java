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
 * Classe bean para agrupamento dos historicos 
 * de consumo com as quebras solicitadas
 *  
 *
 * @author Bruno Barros, Ivan Sérgio
 * @date 20/04/2007, 19/01/2009
 * @alteracao 19/01/2009 - CRC1012 - Adicionado o Indicador de Ligacao Faturada.
 */

public class ResumoConsumoAguaHelper {
	
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer idElo;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;		
	private Integer codigoSetorComercial;		
	private Integer numeroQuadra;
	private Integer idImovelPerfil;
    private Integer idLigacaoAguaSituacao;
    private Integer idLigacaoEsgotoSituacao;	
	private Integer idCategoria;
	private Integer idSubCategoria;    
	private Integer idEsferaPoder;
	private Integer idClienteTipo;
    private Integer idLigacaoAguaPerfil;
    private Integer idLigacaoEsgotoPerfil;
    private Integer idConsumoTipo;
    private Integer quantidadeConsumoAgua  = new Integer(0);
	private Integer quantidadeLigacoes = new Integer(0);
	private Integer quantidadeEconomias = new Integer(0);
	private Integer quantidadeConsumoAguaExcedente = new Integer(0);
	private Integer idVolumeExcedente;
	private Integer idHidrometro;
	private Integer volumeFaturado;
	private BigDecimal valorAgua;
	
	private Integer indicadorLigacaoFaturada;
	
	public Integer getIdVolumeExcedente() {
		return idVolumeExcedente;
	}
	public void setIdVolumeExcedente(Integer indicadorVolumeExcedente) {
		this.idVolumeExcedente = indicadorVolumeExcedente;
	}
	public void setQuantidadeConsumoAguaExcedente(
			Integer quantidadeConsumoAguaExcedente) {
		this.quantidadeConsumoAguaExcedente = quantidadeConsumoAguaExcedente;
	}
	public Integer getIdClienteTipo() {
		return idClienteTipo;
	}
	public void setIdClienteTipo(Integer idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setIdCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public Integer getIdConsumoTipo() {
		return idConsumoTipo;
	}
	public void setIdConsumoTipo(Integer idConsumoTipo) {
		this.idConsumoTipo = idConsumoTipo;
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
	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}
	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLcalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdLigacaoAguaPerfil() {
		return idLigacaoAguaPerfil;
	}
	public void setIdLigacaoAguaPerfil(Integer idLigacaoAguaPerfil) {
		this.idLigacaoAguaPerfil = idLigacaoAguaPerfil;
	}
	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}
	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}
	public Integer getIdLigacaoEsgotoPerfil() {
		return idLigacaoEsgotoPerfil;
	}
	public void setIdLigacaoEsgotoPerfil(Integer idLigacaoEsgotoPerfil) {
		this.idLigacaoEsgotoPerfil = idLigacaoEsgotoPerfil;
	}
	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}
	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setIdNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public Integer getIdQuadra() {
		return idQuadra;
	}
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}
	public Integer getIdRota() {
		return idRota;
	}
	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public Integer getQuantidadeConsumoAgua() {
		return quantidadeConsumoAgua;
	}
	public void setQuantidadeConsumoAgua(Integer quantidadeConsumoAgua) {
		this.quantidadeConsumoAgua = quantidadeConsumoAgua;
	}
	public Integer getQuantidadeConsumoAguaExcedente() {
		return this.quantidadeConsumoAguaExcedente;
	}
	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}
	
	/**
	 * Construtor com a sequencia correta de quebras para o 
	 * caso de uso UC[0570] - Gerar resumo do consumo de agua
	 * 
	 * OBS - Duas quebras adicionais nao foram passadas neste contrutor,
	 * a saber, idCategoria e idSubCatergoria, pois no momento da criacao deste objeto
	 * essas informacoes nao estao disponiveis. 
	 *
	 * @param idGerenciaRegional
	 * param  idUnidadeNegocio
	 * @param idLocalidade
	 * @param idElo
	 * @param idSetorComercial
	 * @param idRota
	 * @param idQuadra
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param idImovelPerfil
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto
	 * @param idTipoConsumo
	 * @param qtdConsumoAgua
	 */
	public ResumoConsumoAguaHelper( 
			Integer idGerenciaRegional,
			Integer idUnidadeNegocio,
			Integer idLocalidade,
			Integer idElo,
			Integer idSetorComercial,
			Integer idRota,
			Integer idQuadra,
			Integer codigoSetorComercial,
			Integer numeroQuadra,
			Integer idImovelPerfil,
			Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto,			
			Integer idPerfilLigacaoAgua,
			Integer idPerfilLigacaoEsgoto,
			Integer idTipoConsumo
			//BigDecimal  valorAgua 
            ){
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.idElo = idElo;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra; 
		this.idImovelPerfil = idImovelPerfil;
		this.idLigacaoAguaSituacao = idSituacaoLigacaoAgua;
		this.idLigacaoEsgotoSituacao = idSituacaoLigacaoEsgoto;
		this.idLigacaoAguaPerfil = idPerfilLigacaoAgua;
		this.idLigacaoEsgotoPerfil = idPerfilLigacaoEsgoto;
		this.idConsumoTipo = idTipoConsumo;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}
	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
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
		
		if ( !( obj instanceof ResumoConsumoAguaHelper ) ){
			return false;			
		} else {
			ResumoConsumoAguaHelper resumoTemp = ( ResumoConsumoAguaHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
			  propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
			  propriedadesIguais( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio ) &&
			  propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
			  propriedadesIguais( this.idElo, resumoTemp.idElo ) &&
			  propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial ) &&
			  propriedadesIguais( this.idRota, resumoTemp.idRota ) &&
			  propriedadesIguais( this.idQuadra, resumoTemp.idQuadra ) &&
			  propriedadesIguais( this.codigoSetorComercial, resumoTemp.codigoSetorComercial ) &&
			  propriedadesIguais( this.numeroQuadra, resumoTemp.numeroQuadra ) &&
			  propriedadesIguais( this.idImovelPerfil, resumoTemp.idImovelPerfil ) &&
			  propriedadesIguais( this.idLigacaoAguaSituacao, resumoTemp.idLigacaoAguaSituacao ) &&
			  propriedadesIguais( this.idLigacaoEsgotoSituacao, resumoTemp.idLigacaoEsgotoSituacao ) &&
			  propriedadesIguais( this.idCategoria, resumoTemp.idCategoria ) &&
			  propriedadesIguais( this.idSubCategoria, resumoTemp.idSubCategoria ) &&			  
			  propriedadesIguais( this.idEsferaPoder, resumoTemp.idEsferaPoder ) &&
			  propriedadesIguais( this.idClienteTipo, resumoTemp.idClienteTipo ) &&
			  propriedadesIguais( this.idLigacaoAguaPerfil, resumoTemp.idLigacaoAguaPerfil ) &&
			  propriedadesIguais( this.idLigacaoEsgotoPerfil, resumoTemp.idLigacaoEsgotoPerfil ) &&
			  propriedadesIguais( this.idConsumoTipo, resumoTemp.idConsumoTipo ) &&
			  propriedadesIguais( this.idVolumeExcedente, resumoTemp.idVolumeExcedente) &&
			  propriedadesIguais( this.idHidrometro, resumoTemp.idHidrometro);
		}	
	}
	
	public Integer getIdHidrometro() {
	
		return idHidrometro;
	}
	
	public void setIdHidrometro(Integer idHidrometro) {
	
		this.idHidrometro = idHidrometro;
	}
	
	public Integer getVolumeFaturado() {
	
		return volumeFaturado;
	}
	
	public void setVolumeFaturado(Integer volumeFaturado) {
	
		this.volumeFaturado = volumeFaturado;
	}
	/**
	 * @return Retorna o campo indicadorLigacaoFaturada.
	 */
	public Integer getIndicadorLigacaoFaturada() {
		return indicadorLigacaoFaturada;
	}
	/**
	 * @param indicadorLigacaoFaturada O indicadorLigacaoFaturada a ser setado.
	 */
	public void setIndicadorLigacaoFaturada(Integer indicadorLigacaoFaturada) {
		this.indicadorLigacaoFaturada = indicadorLigacaoFaturada;
	}
	public BigDecimal getValorAgua() {
		return valorAgua;
	}
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}
}


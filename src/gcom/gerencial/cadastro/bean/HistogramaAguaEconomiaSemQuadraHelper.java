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

public class HistogramaAguaEconomiaSemQuadraHelper {
	
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idElo;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Integer idTipoCategoria;
	private Integer idCategoria;
	private Integer idConsumoTarifa;	
	private Integer idPerfilImovel;
	private Integer idEsferaPoder;
	private Integer idSituacaoLigacaoAgua;
	private Integer idConsumoReal;
	private Integer idExistenciaHidrometro;
	private Integer idExistenciaPoco;
	private Integer idExistenciaVolumeFixoAgua;
	private Integer quantidadeConsumo = 0;
	private Integer quantidadeEconomia = 0;
	private Integer quantidadeLigacoes = 0;
	private Float valorFaturadoEconomia = 0f;
	private Integer volumeFaturadoEconomia = 0;
	private Integer idSubCategoria;
	
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Integer getIdConsumoReal() {
		return idConsumoReal;
	}
	public void setIdConsumoReal(Integer idConsumoReal) {
		this.idConsumoReal = idConsumoReal;
	}
	public Integer getIdConsumoTarifa() {
		return idConsumoTarifa;
	}
	public void setIdConsumoTarifa(Integer idConsumoTarifa) {
		this.idConsumoTarifa = idConsumoTarifa;
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
	public Integer getIdExistenciaHidrometro() {
		return idExistenciaHidrometro;
	}
	public void setIdExistenciaHidrometro(Integer idExistenciaHidrometro) {
		this.idExistenciaHidrometro = idExistenciaHidrometro;
	}
	public Integer getIdExistenciaPoco() {
		return idExistenciaPoco;
	}
	public void setIdExistenciaPoco(Integer idExistenciaPoco) {
		this.idExistenciaPoco = idExistenciaPoco;
	}
	public Integer getIdExistenciaVolumeFixoAgua() {
		return idExistenciaVolumeFixoAgua;
	}
	public void setIdExistenciaVolumeFixoAgua(Integer idExistenciaVolumeFixoAgua) {
		this.idExistenciaVolumeFixoAgua = idExistenciaVolumeFixoAgua;
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
	public Integer getIdTipoCategoria() {
		return idTipoCategoria;
	}
	public void setIdTipoCategoria(Integer idTipoCategoria) {
		this.idTipoCategoria = idTipoCategoria;
	}
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public Integer getQuantidadeConsumo() {
		return quantidadeConsumo;
	}
	public void setQuantidadeConsumo(Integer quantidadeConsumo) {
		this.quantidadeConsumo = quantidadeConsumo;
	}
	public Integer getQuantidadeEconomia() {
		return quantidadeEconomia;
	}
	public void setQuantidadeEconomia(Integer quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}
	public Float getValorFaturadoEconomia() {
		return valorFaturadoEconomia;
	}
	public void setValorFaturadoEconomia(Float valorFaturadoEconomia) {
		this.valorFaturadoEconomia = valorFaturadoEconomia;
	}
	public Integer getVolumeFaturadoEconomia() {
		return volumeFaturadoEconomia;
	}
	public void setVolumeFaturadoEconomia(Integer volumeFaturadoEconomia) {
		this.volumeFaturadoEconomia = volumeFaturadoEconomia;
	}
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}
	
	public HistogramaAguaEconomiaSemQuadraHelper() {}
	
	public HistogramaAguaEconomiaSemQuadraHelper(
			Integer idGerenciaRegional, 
			Integer idUnidadeNegocio, 
			Integer idElo, 
			Integer idLocalidade, 
			Integer idSetorComercial, 
			Integer codigoSetorComercial, 
			Integer idTipoCategoria, 
			Integer idCategoria,
			Integer idSubCategoria,
			Integer idConsumoTarifa, 
			Integer idPerfilImovel, 
			Integer idEsferaPoder, 
			Integer idSituacaoLigacaoAgua, 
			Integer idConsumoReal, 
			Integer idExistenciaHidrometro, 
			Integer idExistenciaPoco, 
			Integer idExistenciaVolumeFixoAgua, 
			Integer quantidadeConsumo,
			Integer quantidadeLigacoes) {
		super();
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idElo = idElo;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.codigoSetorComercial = codigoSetorComercial;
		this.idTipoCategoria = idTipoCategoria;
		this.idCategoria = idCategoria;
		this.idSubCategoria = idSubCategoria;
		this.idConsumoTarifa = idConsumoTarifa;
		this.idPerfilImovel = idPerfilImovel;
		this.idEsferaPoder = idEsferaPoder;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idConsumoReal = idConsumoReal;
		this.idExistenciaHidrometro = idExistenciaHidrometro;
		this.idExistenciaPoco = idExistenciaPoco;
		this.idExistenciaVolumeFixoAgua = idExistenciaVolumeFixoAgua;
		this.quantidadeConsumo = quantidadeConsumo;
		this.quantidadeLigacoes = quantidadeLigacoes;
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
		
		if ( !( obj instanceof HistogramaAguaEconomiaSemQuadraHelper ) ){
			return false;			
		} else {
			HistogramaAguaEconomiaSemQuadraHelper resumoTemp = ( HistogramaAguaEconomiaSemQuadraHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
				propriedadesIguais( this.idGerenciaRegional			, resumoTemp.idGerenciaRegional ) &&
				propriedadesIguais( this.idUnidadeNegocio			, resumoTemp.idUnidadeNegocio ) &&
				propriedadesIguais( this.idElo						, resumoTemp.idElo ) &&
				propriedadesIguais( this.idLocalidade				, resumoTemp.idLocalidade ) &&
				propriedadesIguais( this.idSetorComercial			, resumoTemp.idSetorComercial ) &&
				propriedadesIguais( this.codigoSetorComercial		, resumoTemp.codigoSetorComercial ) &&
				propriedadesIguais( this.idTipoCategoria			, resumoTemp.idTipoCategoria ) &&
				propriedadesIguais( this.idCategoria				, resumoTemp.idCategoria ) &&
				propriedadesIguais( this.idSubCategoria				, resumoTemp.idSubCategoria ) &&
				propriedadesIguais( this.idConsumoTarifa			, resumoTemp.idConsumoTarifa ) &&
				propriedadesIguais( this.idPerfilImovel				, resumoTemp.idPerfilImovel ) &&
				propriedadesIguais( this.idEsferaPoder				, resumoTemp.idEsferaPoder ) &&
				propriedadesIguais( this.idSituacaoLigacaoAgua		, resumoTemp.idSituacaoLigacaoAgua ) &&
				propriedadesIguais( this.idConsumoReal				, resumoTemp.idConsumoReal ) &&
				propriedadesIguais( this.idExistenciaHidrometro		, resumoTemp.idExistenciaHidrometro ) &&
				propriedadesIguais( this.idExistenciaPoco			, resumoTemp.idExistenciaPoco ) &&
				propriedadesIguais( this.idExistenciaVolumeFixoAgua	, resumoTemp.idExistenciaVolumeFixoAgua ) &&
				propriedadesIguais( this.quantidadeConsumo			, resumoTemp.quantidadeConsumo );
		}	
	}
	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}
	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

}

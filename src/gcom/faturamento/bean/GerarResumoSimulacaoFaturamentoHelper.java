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
package gcom.faturamento.bean;

import java.math.BigDecimal;

/**
 * Parâmetros para Gerar o Resumo Simulção do faturamento
 *
 * @author Fernando Fontelles
 * 
 * @date 22/01/2010
 */
public class GerarResumoSimulacaoFaturamentoHelper {

	Integer anoMes;
	
	Integer grupoFaturamentoId;
	
	Integer gerenciaRegionalId;
	
	Integer localidadeId;
	
	Integer setorComercialId;
	
	Integer rotaId;
	
	Integer quadraId;
	
	Integer codigoSetorComercial;
	
	Integer numeroQuadra;
	
	Integer ligacaoAguaSituacaoId;
	
	Integer ligacaoEsgotoSituacaoId;
	
	Integer perfilImovelId;
	
	Integer esferaPoderId;
	
	Short indicadorDebitoConta;
	
	Short indicadorSimulacao;
	
	BigDecimal valorAgua;
	
	Integer consumoAgua;
	
	BigDecimal valorEsgoto;
	
	Integer consumoEsgoto;
	
	BigDecimal valorDebitos;
	
	BigDecimal valorCreditos;
	
	BigDecimal valorImpostos;
	
	Integer idConta;
	
	/*
	 * Utilizado para o batch 
	 * [UC0980] - Gerar Resumo Simulação do Faturamento que faz parte do processo de faturar grupo	  
	 */
	
	public GerarResumoSimulacaoFaturamentoHelper(){}
	
	public GerarResumoSimulacaoFaturamentoHelper(Integer idConta, 
		Integer anoMes, 
		Integer grupoFaturamentoId, 
		Integer gerenciaRegionalId, 
		Integer localidadeId, 
		int setorComercialId, 
		Integer rotaId, 
		Integer quadraId, 
		Integer codigoSetorComercial, 
		int numeroQuadra, 
		Integer ligacaoAguaSituacaoId, 
		Integer ligacaoEsgotoSituacaoId, 
		Integer perfilImovelId, 
		Integer esferaPoderId, 
		Short indicadorDebitoConta, 
		Integer indicadorSimulacao, 
		BigDecimal valorAgua, 
		Integer consumoAgua, 
		BigDecimal valorEsgoto, 
		Integer consumoEsgoto, 
		BigDecimal valorDebitos, 
		BigDecimal valorCreditos,
		BigDecimal valorImpostos) {
		
		this.idConta = idConta;
		this.anoMes = anoMes;
		this.grupoFaturamentoId = grupoFaturamentoId;
		this.gerenciaRegionalId = gerenciaRegionalId;
		this.localidadeId = localidadeId;
		this.setorComercialId = setorComercialId;
		this.rotaId = rotaId;
		this.quadraId = quadraId;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.ligacaoAguaSituacaoId = ligacaoAguaSituacaoId;
		this.ligacaoEsgotoSituacaoId = ligacaoEsgotoSituacaoId;
		this.perfilImovelId = perfilImovelId;
		this.esferaPoderId = esferaPoderId;
		this.indicadorDebitoConta = indicadorDebitoConta;
		this.indicadorSimulacao = indicadorSimulacao.shortValue();
		this.valorAgua = valorAgua;
		this.consumoAgua = consumoAgua;
		this.valorEsgoto = valorEsgoto;
		this.consumoEsgoto = consumoEsgoto;
		this.valorDebitos = valorDebitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		
	}
	
	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public Integer getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}

	public void setGerenciaRegionalId(Integer gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}

	public Integer getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(Integer anoMes) {
		this.anoMes = anoMes;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public Integer getEsferaPoderId() {
		return esferaPoderId;
	}

	public void setEsferaPoderId(Integer esferaPoderId) {
		this.esferaPoderId = esferaPoderId;
	}

	public Integer getGrupoFaturamentoId() {
		return grupoFaturamentoId;
	}

	public void setGrupoFaturamentoId(Integer grupoFaturamentoId) {
		this.grupoFaturamentoId = grupoFaturamentoId;
	}

	public Short getIndicadorDebitoConta() {
		return indicadorDebitoConta;
	}

	public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}

	public Short getIndicadorSimulacao() {
		return indicadorSimulacao;
	}

	public void setIndicadorSimulacao(Short indicadorSimulacao) {
		this.indicadorSimulacao = indicadorSimulacao;
	}


	public Integer getLigacaoAguaSituacaoId() {
		return ligacaoAguaSituacaoId;
	}

	public void setLigacaoAguaSituacaoId(Integer ligacaoAguaSituacaoId) {
		this.ligacaoAguaSituacaoId = ligacaoAguaSituacaoId;
	}

	public Integer getLigacaoEsgotoSituacaoId() {
		return ligacaoEsgotoSituacaoId;
	}

	public void setLigacaoEsgotoSituacaoId(Integer ligacaoEsgotoSituacaoId) {
		this.ligacaoEsgotoSituacaoId = ligacaoEsgotoSituacaoId;
	}

	public Integer getLocalidadeId() {
		return localidadeId;
	}

	public void setLocalidadeId(Integer localidadeId) {
		this.localidadeId = localidadeId;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getPerfilImovelId() {
		return perfilImovelId;
	}

	public void setPerfilImovelId(Integer perfilImovelId) {
		this.perfilImovelId = perfilImovelId;
	}

	public Integer getQuadraId() {
		return quadraId;
	}

	public void setQuadraId(Integer quadraId) {
		this.quadraId = quadraId;
	}

	public Integer getRotaId() {
		return rotaId;
	}

	public void setRotaId(Integer rotaId) {
		this.rotaId = rotaId;
	}

	public Integer getSetorComercialId() {
		return setorComercialId;
	}

	public void setSetorComercialId(Integer setorComercialId) {
		this.setorComercialId = setorComercialId;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}
		
}

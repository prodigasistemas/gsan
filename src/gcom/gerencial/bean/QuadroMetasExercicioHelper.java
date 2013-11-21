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
package gcom.gerencial.bean;

import gcom.util.Util;
import java.math.BigDecimal;

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de relatorio
 *
 * @author Bruno Barros
 * @date 10/03/2008
 */
public class QuadroMetasExercicioHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String opcaoTotalizacao = "";
	private String nomeGerenciaRegional;
	private String nomeUnidadeNegocio;
	private String nomeLocalidade;
	private String nomeCentroCusto;
	
	// Ligacoes Cadastradas
	private Integer quantidadeLigacoesCadastradasDezembro;
	private Integer quantidadeLigacoesCadastradasJaneiro;
	private Integer quantidadeLigacoesCadastradasFevereiro;
	private Integer quantidadeLigacoesCadastradasMarco;
	private Integer quantidadeLigacoesCadastradasAbril;
	private Integer quantidadeLigacoesCadastradasMaio;
	private Integer quantidadeLigacoesCadastradasJunho;
	private Integer quantidadeLigacoesCadastradasJulho;
	private Integer quantidadeLigacoesCadastradasAgosto;
	private Integer quantidadeLigacoesCadastradasSetembro;
	private Integer quantidadeLigacoesCadastradasOutubro;
	private Integer quantidadeLigacoesCadastradasNovembro;
	
	// Ligacoes Cortadas
	private Integer quantidadeLigacoesCortadasDezembro;
	private Integer quantidadeLigacoesCortadasJaneiro;
	private Integer quantidadeLigacoesCortadasFevereiro;
	private Integer quantidadeLigacoesCortadasMarco;
	private Integer quantidadeLigacoesCortadasAbril;
	private Integer quantidadeLigacoesCortadasMaio;
	private Integer quantidadeLigacoesCortadasJunho;
	private Integer quantidadeLigacoesCortadasJulho;
	private Integer quantidadeLigacoesCortadasAgosto;
	private Integer quantidadeLigacoesCortadasSetembro;
	private Integer quantidadeLigacoesCortadasOutubro;
	private Integer quantidadeLigacoesCortadasNovembro;
	
	// Ligacoes Suprimidas
	private Integer quantidadeLigacoesSuprimidasDezembro;
	private Integer quantidadeLigacoesSuprimidasJaneiro;
	private Integer quantidadeLigacoesSuprimidasFevereiro;
	private Integer quantidadeLigacoesSuprimidasMarco;
	private Integer quantidadeLigacoesSuprimidasAbril;
	private Integer quantidadeLigacoesSuprimidasMaio;
	private Integer quantidadeLigacoesSuprimidasJunho;
	private Integer quantidadeLigacoesSuprimidasJulho;
	private Integer quantidadeLigacoesSuprimidasAgosto;
	private Integer quantidadeLigacoesSuprimidasSetembro;
	private Integer quantidadeLigacoesSuprimidasOutubro;
	private Integer quantidadeLigacoesSuprimidasNovembro;
	
	// Ligacoes Ativas
	private Integer quantidadeLigacoesAtivasDezembro;
	private Integer quantidadeLigacoesAtivasJaneiro;
	private Integer quantidadeLigacoesAtivasFevereiro;
	private Integer quantidadeLigacoesAtivasMarco;
	private Integer quantidadeLigacoesAtivasAbril;
	private Integer quantidadeLigacoesAtivasMaio;
	private Integer quantidadeLigacoesAtivasJunho;
	private Integer quantidadeLigacoesAtivasJulho;
	private Integer quantidadeLigacoesAtivasAgosto;
	private Integer quantidadeLigacoesAtivasSetembro;
	private Integer quantidadeLigacoesAtivasOutubro;
	private Integer quantidadeLigacoesAtivasNovembro;

	
	// Ligacoes Ativas com débitos a mais de 3 meses
	private Integer quantidadeLigacoesAtivasDebitos3mDezembro;
	private Integer quantidadeLigacoesAtivasDebitos3mJaneiro;
	private Integer quantidadeLigacoesAtivasDebitos3mFevereiro;
	private Integer quantidadeLigacoesAtivasDebitos3mMarco;
	private Integer quantidadeLigacoesAtivasDebitos3mAbril;
	private Integer quantidadeLigacoesAtivasDebitos3mMaio;
	private Integer quantidadeLigacoesAtivasDebitos3mJunho;
	private Integer quantidadeLigacoesAtivasDebitos3mJulho;
	private Integer quantidadeLigacoesAtivasDebitos3mAgosto;
	private Integer quantidadeLigacoesAtivasDebitos3mSetembro;
	private Integer quantidadeLigacoesAtivasDebitos3mOutubro;
	private Integer quantidadeLigacoesAtivasDebitos3mNovembro;

	
	
	// Ligacoes Consumo Medido
	private Integer quantidadeLigacoesConsumoMedidoDezembro;
	private Integer quantidadeLigacoesConsumoMedidoJaneiro;
	private Integer quantidadeLigacoesConsumoMedidoFevereiro;
	private Integer quantidadeLigacoesConsumoMedidoMarco;
	private Integer quantidadeLigacoesConsumoMedidoAbril;
	private Integer quantidadeLigacoesConsumoMedidoMaio;
	private Integer quantidadeLigacoesConsumoMedidoJunho;
	private Integer quantidadeLigacoesConsumoMedidoJulho;
	private Integer quantidadeLigacoesConsumoMedidoAgosto;
	private Integer quantidadeLigacoesConsumoMedidoSetembro;
	private Integer quantidadeLigacoesConsumoMedidoOutubro;
	private Integer quantidadeLigacoesConsumoMedidoNovembro;
	
	// Ligacoes Consumo 5m3
	private Integer quantidadeLigacoesConsumo5m3Dezembro;
	private Integer quantidadeLigacoesConsumo5m3Janeiro;
	private Integer quantidadeLigacoesConsumo5m3Fevereiro;
	private Integer quantidadeLigacoesConsumo5m3Marco;
	private Integer quantidadeLigacoesConsumo5m3Abril;
	private Integer quantidadeLigacoesConsumo5m3Maio;
	private Integer quantidadeLigacoesConsumo5m3Junho;
	private Integer quantidadeLigacoesConsumo5m3Julho;
	private Integer quantidadeLigacoesConsumo5m3Agosto;
	private Integer quantidadeLigacoesConsumo5m3Setembro;
	private Integer quantidadeLigacoesConsumo5m3Outubro;
	private Integer quantidadeLigacoesConsumo5m3Novembro;
	
	// Ligacoes Consumo Não Medido
	private Integer quantidadeLigacoesConsumoNaoMedidoDezembro;
	private Integer quantidadeLigacoesConsumoNaoMedidoJaneiro;
	private Integer quantidadeLigacoesConsumoNaoMedidoFevereiro;
	private Integer quantidadeLigacoesConsumoNaoMedidoMarco;
	private Integer quantidadeLigacoesConsumoNaoMedidoAbril;
	private Integer quantidadeLigacoesConsumoNaoMedidoMaio;
	private Integer quantidadeLigacoesConsumoNaoMedidoJunho;
	private Integer quantidadeLigacoesConsumoNaoMedidoJulho;
	private Integer quantidadeLigacoesConsumoNaoMedidoAgosto;
	private Integer quantidadeLigacoesConsumoNaoMedidoSetembro;
	private Integer quantidadeLigacoesConsumoNaoMedidoOutubro;
	private Integer quantidadeLigacoesConsumoNaoMedidoNovembro;
	
	// Ligacoes Consumo Medio
	private Integer quantidadeLigacoesConsumoMediaDezembro;
	private Integer quantidadeLigacoesConsumoMediaJaneiro;
	private Integer quantidadeLigacoesConsumoMediaFevereiro;
	private Integer quantidadeLigacoesConsumoMediaMarco;
	private Integer quantidadeLigacoesConsumoMediaAbril;
	private Integer quantidadeLigacoesConsumoMediaMaio;
	private Integer quantidadeLigacoesConsumoMediaJunho;
	private Integer quantidadeLigacoesConsumoMediaJulho;
	private Integer quantidadeLigacoesConsumoMediaAgosto;
	private Integer quantidadeLigacoesConsumoMediaSetembro;
	private Integer quantidadeLigacoesConsumoMediaOutubro;
	private Integer quantidadeLigacoesConsumoMediaNovembro;
	
	// Ligacoes quantidade Economias
	private Integer quantidadeEconomiasDezembro;
	private Integer quantidadeEconomiasJaneiro;
	private Integer quantidadeEconomiasFevereiro;
	private Integer quantidadeEconomiasMarco;
	private Integer quantidadeEconomiasAbril;
	private Integer quantidadeEconomiasMaio;
	private Integer quantidadeEconomiasJunho;
	private Integer quantidadeEconomiasJulho;
	private Integer quantidadeEconomiasAgosto;
	private Integer quantidadeEconomiasSetembro;
	private Integer quantidadeEconomiasOutubro;
	private Integer quantidadeEconomiasNovembro;
	
	public String getNomeCentroCusto() {
	
		return nomeCentroCusto;
	}
	
	public void setNomeCentroCusto(String nomeCentroCusto) {
	
		this.nomeCentroCusto = nomeCentroCusto;
	}
	
	public String getNomeGerenciaRegional() {
	
		return nomeGerenciaRegional;
	}
	
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
	
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	
	public String getNomeLocalidade() {
	
		return nomeLocalidade;
	}
	
	public void setNomeLocalidade(String nomeLocalidade) {
	
		this.nomeLocalidade = nomeLocalidade;
	}
	
	public String getNomeUnidadeNegocio() {
	
		return nomeUnidadeNegocio;
	}
	
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
	
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}
	
	public String getOpcaoTotalizacao() {
	
		return opcaoTotalizacao;
	}
	
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
	
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	
	
	public Integer getQuantidadeEconomiasJaneiro() {
	
		return quantidadeEconomiasJaneiro;
	}
	
	public void setQuantidadeEconomiasJaneiro(
			Integer quantidadeEconomiasJaneiro) {
	
		this.quantidadeEconomiasJaneiro = quantidadeEconomiasJaneiro;
	}
	
	public Integer getQuantidadeEconomiasFevereiro() {
	
		return quantidadeEconomiasFevereiro;
	}
	
	public void setQuantidadeEconomiasFevereiro(
			Integer quantidadeEconomiasFevereiro) {
	
		this.quantidadeEconomiasFevereiro = quantidadeEconomiasFevereiro;
	}
	
	public Integer getQuantidadeEconomiasJulho() {
	
		return quantidadeEconomiasJulho;
	}
	
	public void setQuantidadeEconomiasJulho(
			Integer quantidadeEconomiasJulho) {
	
		this.quantidadeEconomiasJulho = quantidadeEconomiasJulho;
	}
	
	public Integer getQuantidadeEconomiasJunho() {
	
		return quantidadeEconomiasJunho;
	}
	
	public void setQuantidadeEconomiasJunho(
			Integer quantidadeEconomiasJunho) {
	
		this.quantidadeEconomiasJunho = quantidadeEconomiasJunho;
	}
	
	public Integer getQuantidadeEconomiasMarco() {
	
		return quantidadeEconomiasMarco;
	}
	
	public void setQuantidadeEconomiasMarco(
			Integer quantidadeEconomiasMarco) {
	
		this.quantidadeEconomiasMarco = quantidadeEconomiasMarco;
	}
	
	public Integer getQuantidadeEconomiasAbril() {
	
		return quantidadeEconomiasAbril;
	}
	
	public void setQuantidadeEconomiasAbril(
			Integer quantidadeEconomiasAbril) {
	
		this.quantidadeEconomiasAbril = quantidadeEconomiasAbril;
	}
	
	public Integer getQuantidadeEconomiasMaio() {
	
		return quantidadeEconomiasMaio;
	}
	
	public void setQuantidadeEconomiasMaio(
			Integer quantidadeEconomiasMaio) {
	
		this.quantidadeEconomiasMaio = quantidadeEconomiasMaio;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mDezembro() {
	
		return quantidadeLigacoesAtivasDebitos3mDezembro;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mDezembro(
			Integer quantidadeLigacoesAtivasDebitos3mDezembro) {
	
		this.quantidadeLigacoesAtivasDebitos3mDezembro = quantidadeLigacoesAtivasDebitos3mDezembro;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mJaneiro() {
	
		return quantidadeLigacoesAtivasDebitos3mJaneiro;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mJaneiro(
			Integer quantidadeLigacoesAtivasDebitos3mJaneiro) {
	
		this.quantidadeLigacoesAtivasDebitos3mJaneiro = quantidadeLigacoesAtivasDebitos3mJaneiro;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mFevereiro() {
	
		return quantidadeLigacoesAtivasDebitos3mFevereiro;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mFevereiro(
			Integer quantidadeLigacoesAtivasDebitos3mFevereiro) {
	
		this.quantidadeLigacoesAtivasDebitos3mFevereiro = quantidadeLigacoesAtivasDebitos3mFevereiro;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mJulho() {
	
		return quantidadeLigacoesAtivasDebitos3mJulho;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mJulho(
			Integer quantidadeLigacoesAtivasDebitos3mJulho) {
	
		this.quantidadeLigacoesAtivasDebitos3mJulho = quantidadeLigacoesAtivasDebitos3mJulho;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mJunho() {
	
		return quantidadeLigacoesAtivasDebitos3mJunho;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mJunho(
			Integer quantidadeLigacoesAtivasDebitos3mJunho) {
	
		this.quantidadeLigacoesAtivasDebitos3mJunho = quantidadeLigacoesAtivasDebitos3mJunho;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mMarco() {
	
		return quantidadeLigacoesAtivasDebitos3mMarco;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mMarco(
			Integer quantidadeLigacoesAtivasDebitos3mMarco) {
	
		this.quantidadeLigacoesAtivasDebitos3mMarco = quantidadeLigacoesAtivasDebitos3mMarco;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mAbril() {
	
		return quantidadeLigacoesAtivasDebitos3mAbril;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mAbril(
			Integer quantidadeLigacoesAtivasDebitos3mAbril) {
	
		this.quantidadeLigacoesAtivasDebitos3mAbril = quantidadeLigacoesAtivasDebitos3mAbril;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mMaio() {
	
		return quantidadeLigacoesAtivasDebitos3mMaio;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mMaio(
			Integer quantidadeLigacoesAtivasDebitos3mMaio) {
	
		this.quantidadeLigacoesAtivasDebitos3mMaio = quantidadeLigacoesAtivasDebitos3mMaio;
	}
	
	public Integer getQuantidadeLigacoesAtivasDezembro() {
	
		return quantidadeLigacoesAtivasDezembro;
	}
	
	public void setQuantidadeLigacoesAtivasDezembro(
			Integer quantidadeLigacoesAtivasDezembro) {
	
		this.quantidadeLigacoesAtivasDezembro = quantidadeLigacoesAtivasDezembro;
	}
	
	public Integer getQuantidadeLigacoesAtivasJaneiro() {
	
		return quantidadeLigacoesAtivasJaneiro;
	}
	
	public void setQuantidadeLigacoesAtivasJaneiro(
			Integer quantidadeLigacoesAtivasJaneiro) {
	
		this.quantidadeLigacoesAtivasJaneiro = quantidadeLigacoesAtivasJaneiro;
	}
	
	public Integer getQuantidadeLigacoesAtivasFevereiro() {
	
		return quantidadeLigacoesAtivasFevereiro;
	}
	
	public void setQuantidadeLigacoesAtivasFevereiro(
			Integer quantidadeLigacoesAtivasFevereiro) {
	
		this.quantidadeLigacoesAtivasFevereiro = quantidadeLigacoesAtivasFevereiro;
	}
	
	public Integer getQuantidadeLigacoesAtivasJulho() {
	
		return quantidadeLigacoesAtivasJulho;
	}
	
	public void setQuantidadeLigacoesAtivasJulho(
			Integer quantidadeLigacoesAtivasJulho) {
	
		this.quantidadeLigacoesAtivasJulho = quantidadeLigacoesAtivasJulho;
	}
	
	public Integer getQuantidadeLigacoesAtivasJunho() {
	
		return quantidadeLigacoesAtivasJunho;
	}
	
	public void setQuantidadeLigacoesAtivasJunho(
			Integer quantidadeLigacoesAtivasJunho) {
	
		this.quantidadeLigacoesAtivasJunho = quantidadeLigacoesAtivasJunho;
	}
	
	public Integer getQuantidadeLigacoesAtivasMarco() {
	
		return quantidadeLigacoesAtivasMarco;
	}
	
	public void setQuantidadeLigacoesAtivasMarco(
			Integer quantidadeLigacoesAtivasMarco) {
	
		this.quantidadeLigacoesAtivasMarco = quantidadeLigacoesAtivasMarco;
	}
	
	public Integer getQuantidadeLigacoesAtivasAbril() {
	
		return quantidadeLigacoesAtivasAbril;
	}
	
	public void setQuantidadeLigacoesAtivasAbril(
			Integer quantidadeLigacoesAtivasAbril) {
	
		this.quantidadeLigacoesAtivasAbril = quantidadeLigacoesAtivasAbril;
	}
	
	public Integer getQuantidadeLigacoesAtivasMaio() {
	
		return quantidadeLigacoesAtivasMaio;
	}
	
	public void setQuantidadeLigacoesAtivasMaio(
			Integer quantidadeLigacoesAtivasMaio) {
	
		this.quantidadeLigacoesAtivasMaio = quantidadeLigacoesAtivasMaio;
	}
	
	public Integer getQuantidadeLigacoesCadastradasDezembro() {
	
		return quantidadeLigacoesCadastradasDezembro;
	}
	
	public void setQuantidadeLigacoesCadastradasDezembro(
			Integer quantidadeLigacoesCadastradasDezembro) {
	
		this.quantidadeLigacoesCadastradasDezembro = quantidadeLigacoesCadastradasDezembro;
	}
	
	public Integer getQuantidadeLigacoesCadastradasJaneiro() {
	
		return quantidadeLigacoesCadastradasJaneiro;
	}
	
	public void setQuantidadeLigacoesCadastradasJaneiro(
			Integer quantidadeLigacoesCadastradasJaneiro) {
	
		this.quantidadeLigacoesCadastradasJaneiro = quantidadeLigacoesCadastradasJaneiro;
	}
	
	public Integer getQuantidadeLigacoesCadastradasFevereiro() {
	
		return quantidadeLigacoesCadastradasFevereiro;
	}
	
	public void setQuantidadeLigacoesCadastradasFevereiro(
			Integer quantidadeLigacoesCadastradasFevereiro) {
	
		this.quantidadeLigacoesCadastradasFevereiro = quantidadeLigacoesCadastradasFevereiro;
	}
	
	public Integer getQuantidadeLigacoesCadastradasJulho() {
	
		return quantidadeLigacoesCadastradasJulho;
	}
	
	public void setQuantidadeLigacoesCadastradasJulho(
			Integer quantidadeLigacoesCadastradasJulho) {
	
		this.quantidadeLigacoesCadastradasJulho = quantidadeLigacoesCadastradasJulho;
	}
	
	public Integer getQuantidadeLigacoesCadastradasJunho() {
	
		return quantidadeLigacoesCadastradasJunho;
	}
	
	public void setQuantidadeLigacoesCadastradasJunho(
			Integer quantidadeLigacoesCadastradasJunho) {
	
		this.quantidadeLigacoesCadastradasJunho = quantidadeLigacoesCadastradasJunho;
	}
	
	public Integer getQuantidadeLigacoesCadastradasMarco() {
	
		return quantidadeLigacoesCadastradasMarco;
	}
	
	public void setQuantidadeLigacoesCadastradasMarco(
			Integer quantidadeLigacoesCadastradasMarco) {
	
		this.quantidadeLigacoesCadastradasMarco = quantidadeLigacoesCadastradasMarco;
	}
	
	public Integer getQuantidadeLigacoesCadastradasAbril() {
	
		return quantidadeLigacoesCadastradasAbril;
	}
	
	public void setQuantidadeLigacoesCadastradasAbril(
			Integer quantidadeLigacoesCadastradasAbril) {
	
		this.quantidadeLigacoesCadastradasAbril = quantidadeLigacoesCadastradasAbril;
	}
	
	public Integer getQuantidadeLigacoesCadastradasMaio() {
	
		return quantidadeLigacoesCadastradasMaio;
	}
	
	public void setQuantidadeLigacoesCadastradasMaio(
			Integer quantidadeLigacoesCadastradasMaio) {
	
		this.quantidadeLigacoesCadastradasMaio = quantidadeLigacoesCadastradasMaio;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Dezembro() {
	
		return quantidadeLigacoesConsumo5m3Dezembro;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Dezembro(
			Integer quantidadeLigacoesConsumo5m3Dezembro) {
	
		this.quantidadeLigacoesConsumo5m3Dezembro = quantidadeLigacoesConsumo5m3Dezembro;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Janeiro() {
	
		return quantidadeLigacoesConsumo5m3Janeiro;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Janeiro(
			Integer quantidadeLigacoesConsumo5m3Janeiro) {
	
		this.quantidadeLigacoesConsumo5m3Janeiro = quantidadeLigacoesConsumo5m3Janeiro;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Fevereiro() {
	
		return quantidadeLigacoesConsumo5m3Fevereiro;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Fevereiro(
			Integer quantidadeLigacoesConsumo5m3Fevereiro) {
	
		this.quantidadeLigacoesConsumo5m3Fevereiro = quantidadeLigacoesConsumo5m3Fevereiro;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Julho() {
	
		return quantidadeLigacoesConsumo5m3Julho;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Julho(
			Integer quantidadeLigacoesConsumo5m3Julho) {
	
		this.quantidadeLigacoesConsumo5m3Julho = quantidadeLigacoesConsumo5m3Julho;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Junho() {
	
		return quantidadeLigacoesConsumo5m3Junho;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Junho(
			Integer quantidadeLigacoesConsumo5m3Junho) {
	
		this.quantidadeLigacoesConsumo5m3Junho = quantidadeLigacoesConsumo5m3Junho;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Marco() {
	
		return quantidadeLigacoesConsumo5m3Marco;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Marco(
			Integer quantidadeLigacoesConsumo5m3Marco) {
	
		this.quantidadeLigacoesConsumo5m3Marco = quantidadeLigacoesConsumo5m3Marco;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Abril() {
	
		return quantidadeLigacoesConsumo5m3Abril;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Abril(
			Integer quantidadeLigacoesConsumo5m3Abril) {
	
		this.quantidadeLigacoesConsumo5m3Abril = quantidadeLigacoesConsumo5m3Abril;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Maio() {
	
		return quantidadeLigacoesConsumo5m3Maio;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Maio(
			Integer quantidadeLigacoesConsumo5m3Maio) {
	
		this.quantidadeLigacoesConsumo5m3Maio = quantidadeLigacoesConsumo5m3Maio;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaDezembro() {
	
		return quantidadeLigacoesConsumoMediaDezembro;
	}
	
	public void setQuantidadeLigacoesConsumoMediaDezembro(
			Integer quantidadeLigacoesConsumoMediaDezembro) {
	
		this.quantidadeLigacoesConsumoMediaDezembro = quantidadeLigacoesConsumoMediaDezembro;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaJaneiro() {
	
		return quantidadeLigacoesConsumoMediaJaneiro;
	}
	
	public void setQuantidadeLigacoesConsumoMediaJaneiro(
			Integer quantidadeLigacoesConsumoMediaJaneiro) {
	
		this.quantidadeLigacoesConsumoMediaJaneiro = quantidadeLigacoesConsumoMediaJaneiro;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaFevereiro() {
	
		return quantidadeLigacoesConsumoMediaFevereiro;
	}
	
	public void setQuantidadeLigacoesConsumoMediaFevereiro(
			Integer quantidadeLigacoesConsumoMediaFevereiro) {
	
		this.quantidadeLigacoesConsumoMediaFevereiro = quantidadeLigacoesConsumoMediaFevereiro;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaJulho() {
	
		return quantidadeLigacoesConsumoMediaJulho;
	}
	
	public void setQuantidadeLigacoesConsumoMediaJulho(
			Integer quantidadeLigacoesConsumoMediaJulho) {
	
		this.quantidadeLigacoesConsumoMediaJulho = quantidadeLigacoesConsumoMediaJulho;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaJunho() {
	
		return quantidadeLigacoesConsumoMediaJunho;
	}
	
	public void setQuantidadeLigacoesConsumoMediaJunho(
			Integer quantidadeLigacoesConsumoMediaJunho) {
	
		this.quantidadeLigacoesConsumoMediaJunho = quantidadeLigacoesConsumoMediaJunho;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaMarco() {
	
		return quantidadeLigacoesConsumoMediaMarco;
	}
	
	public void setQuantidadeLigacoesConsumoMediaMarco(
			Integer quantidadeLigacoesConsumoMediaMarco) {
	
		this.quantidadeLigacoesConsumoMediaMarco = quantidadeLigacoesConsumoMediaMarco;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaAbril() {
	
		return quantidadeLigacoesConsumoMediaAbril;
	}
	
	public void setQuantidadeLigacoesConsumoMediaAbril(
			Integer quantidadeLigacoesConsumoMediaAbril) {
	
		this.quantidadeLigacoesConsumoMediaAbril = quantidadeLigacoesConsumoMediaAbril;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaMaio() {
	
		return quantidadeLigacoesConsumoMediaMaio;
	}
	
	public void setQuantidadeLigacoesConsumoMediaMaio(
			Integer quantidadeLigacoesConsumoMediaMaio) {
	
		this.quantidadeLigacoesConsumoMediaMaio = quantidadeLigacoesConsumoMediaMaio;
	}

	public Integer getQuantidadeLigacoesConsumoMedidoDezembro() {
	
		return quantidadeLigacoesConsumoMedidoDezembro;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoDezembro(
			Integer quantidadeLigacoesConsumoMedidoDezembro) {
	
		this.quantidadeLigacoesConsumoMedidoDezembro = quantidadeLigacoesConsumoMedidoDezembro;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoJaneiro() {
	
		return quantidadeLigacoesConsumoMedidoJaneiro;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoJaneiro(
			Integer quantidadeLigacoesConsumoMedidoJaneiro) {
	
		this.quantidadeLigacoesConsumoMedidoJaneiro = quantidadeLigacoesConsumoMedidoJaneiro;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoFevereiro() {
	
		return quantidadeLigacoesConsumoMedidoFevereiro;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoFevereiro(
			Integer quantidadeLigacoesConsumoMedidoFevereiro) {
	
		this.quantidadeLigacoesConsumoMedidoFevereiro = quantidadeLigacoesConsumoMedidoFevereiro;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoJulho() {
	
		return quantidadeLigacoesConsumoMedidoJulho;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoJulho(
			Integer quantidadeLigacoesConsumoMedidoJulho) {
	
		this.quantidadeLigacoesConsumoMedidoJulho = quantidadeLigacoesConsumoMedidoJulho;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoJunho() {
	
		return quantidadeLigacoesConsumoMedidoJunho;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoJunho(
			Integer quantidadeLigacoesConsumoMedidoJunho) {
	
		this.quantidadeLigacoesConsumoMedidoJunho = quantidadeLigacoesConsumoMedidoJunho;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoMarco() {
	
		return quantidadeLigacoesConsumoMedidoMarco;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoMarco(
			Integer quantidadeLigacoesConsumoMedidoMarco) {
	
		this.quantidadeLigacoesConsumoMedidoMarco = quantidadeLigacoesConsumoMedidoMarco;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoAbril() {
	
		return quantidadeLigacoesConsumoMedidoAbril;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoAbril(
			Integer quantidadeLigacoesConsumoMedidoAbril) {
	
		this.quantidadeLigacoesConsumoMedidoAbril = quantidadeLigacoesConsumoMedidoAbril;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoMaio() {
	
		return quantidadeLigacoesConsumoMedidoMaio;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoMaio(
			Integer quantidadeLigacoesConsumoMedidoMaio) {
	
		this.quantidadeLigacoesConsumoMedidoMaio = quantidadeLigacoesConsumoMedidoMaio;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoDezembro() {
	
		return quantidadeLigacoesConsumoNaoMedidoDezembro;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoDezembro(
			Integer quantidadeLigacoesConsumoNaoMedidoDezembro) {
	
		this.quantidadeLigacoesConsumoNaoMedidoDezembro = quantidadeLigacoesConsumoNaoMedidoDezembro;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoJaneiro() {
	
		return quantidadeLigacoesConsumoNaoMedidoJaneiro;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoJaneiro(
			Integer quantidadeLigacoesConsumoNaoMedidoJaneiro) {
	
		this.quantidadeLigacoesConsumoNaoMedidoJaneiro = quantidadeLigacoesConsumoNaoMedidoJaneiro;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoFevereiro() {
	
		return quantidadeLigacoesConsumoNaoMedidoFevereiro;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoFevereiro(
			Integer quantidadeLigacoesConsumoNaoMedidoFevereiro) {
	
		this.quantidadeLigacoesConsumoNaoMedidoFevereiro = quantidadeLigacoesConsumoNaoMedidoFevereiro;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoJulho() {
	
		return quantidadeLigacoesConsumoNaoMedidoJulho;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoJulho(
			Integer quantidadeLigacoesConsumoNaoMedidoJulho) {
	
		this.quantidadeLigacoesConsumoNaoMedidoJulho = quantidadeLigacoesConsumoNaoMedidoJulho;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoJunho() {
	
		return quantidadeLigacoesConsumoNaoMedidoJunho;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoJunho(
			Integer quantidadeLigacoesConsumoNaoMedidoJunho) {
	
		this.quantidadeLigacoesConsumoNaoMedidoJunho = quantidadeLigacoesConsumoNaoMedidoJunho;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoMarco() {
	
		return quantidadeLigacoesConsumoNaoMedidoMarco;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoMarco(
			Integer quantidadeLigacoesConsumoNaoMedidoMarco) {
	
		this.quantidadeLigacoesConsumoNaoMedidoMarco = quantidadeLigacoesConsumoNaoMedidoMarco;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoAbril() {
	
		return quantidadeLigacoesConsumoNaoMedidoAbril;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoAbril(
			Integer quantidadeLigacoesConsumoNaoMedidoAbril) {
	
		this.quantidadeLigacoesConsumoNaoMedidoAbril = quantidadeLigacoesConsumoNaoMedidoAbril;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoMaio() {
	
		return quantidadeLigacoesConsumoNaoMedidoMaio;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoMaio(
			Integer quantidadeLigacoesConsumoNaoMedidoMaio) {
	
		this.quantidadeLigacoesConsumoNaoMedidoMaio = quantidadeLigacoesConsumoNaoMedidoMaio;
	}
	
	public Integer getQuantidadeLigacoesCortadasDezembro() {
	
		return quantidadeLigacoesCortadasDezembro;
	}
	
	public void setQuantidadeLigacoesCortadasDezembro(
			Integer quantidadeLigacoesCortadasDezembro) {
	
		this.quantidadeLigacoesCortadasDezembro = quantidadeLigacoesCortadasDezembro;
	}
	
	public Integer getQuantidadeLigacoesCortadasJaneiro() {
	
		return quantidadeLigacoesCortadasJaneiro;
	}
	
	public void setQuantidadeLigacoesCortadasJaneiro(
			Integer quantidadeLigacoesCortadasJaneiro) {
	
		this.quantidadeLigacoesCortadasJaneiro = quantidadeLigacoesCortadasJaneiro;
	}
	
	public Integer getQuantidadeLigacoesCortadasFevereiro() {
	
		return quantidadeLigacoesCortadasFevereiro;
	}
	
	public void setQuantidadeLigacoesCortadasFevereiro(
			Integer quantidadeLigacoesCortadasFevereiro) {
	
		this.quantidadeLigacoesCortadasFevereiro = quantidadeLigacoesCortadasFevereiro;
	}
	
	public Integer getQuantidadeLigacoesCortadasJulho() {
	
		return quantidadeLigacoesCortadasJulho;
	}
	
	public void setQuantidadeLigacoesCortadasJulho(
			Integer quantidadeLigacoesCortadasJulho) {
	
		this.quantidadeLigacoesCortadasJulho = quantidadeLigacoesCortadasJulho;
	}
	
	public Integer getQuantidadeLigacoesCortadasJunho() {
	
		return quantidadeLigacoesCortadasJunho;
	}
	
	public void setQuantidadeLigacoesCortadasJunho(
			Integer quantidadeLigacoesCortadasJunho) {
	
		this.quantidadeLigacoesCortadasJunho = quantidadeLigacoesCortadasJunho;
	}
	
	public Integer getQuantidadeLigacoesCortadasMarco() {
	
		return quantidadeLigacoesCortadasMarco;
	}
	
	public void setQuantidadeLigacoesCortadasMarco(
			Integer quantidadeLigacoesCortadasMarco) {
	
		this.quantidadeLigacoesCortadasMarco = quantidadeLigacoesCortadasMarco;
	}
	
	public Integer getQuantidadeLigacoesCortadasAbril() {
	
		return quantidadeLigacoesCortadasAbril;
	}
	
	public void setQuantidadeLigacoesCortadasAbril(
			Integer quantidadeLigacoesCortadasAbril) {
	
		this.quantidadeLigacoesCortadasAbril = quantidadeLigacoesCortadasAbril;
	}
	
	public Integer getQuantidadeLigacoesCortadasMaio() {
	
		return quantidadeLigacoesCortadasMaio;
	}
	
	public void setQuantidadeLigacoesCortadasMaio(
			Integer quantidadeLigacoesCortadasMaio) {
	
		this.quantidadeLigacoesCortadasMaio = quantidadeLigacoesCortadasMaio;
	}

	public Integer getQuantidadeLigacoesSuprimidasDezembro() {
	
		return quantidadeLigacoesSuprimidasDezembro;
	}
	
	public void setQuantidadeLigacoesSuprimidasDezembro(
			Integer quantidadeLigacoesSuprimidasDezembro) {
	
		this.quantidadeLigacoesSuprimidasDezembro = quantidadeLigacoesSuprimidasDezembro;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasJaneiro() {
	
		return quantidadeLigacoesSuprimidasJaneiro;
	}
	
	public void setQuantidadeLigacoesSuprimidasJaneiro(
			Integer quantidadeLigacoesSuprimidasJaneiro) {
	
		this.quantidadeLigacoesSuprimidasJaneiro = quantidadeLigacoesSuprimidasJaneiro;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasFevereiro() {
	
		return quantidadeLigacoesSuprimidasFevereiro;
	}
	
	public void setQuantidadeLigacoesSuprimidasFevereiro(
			Integer quantidadeLigacoesSuprimidasFevereiro) {
	
		this.quantidadeLigacoesSuprimidasFevereiro = quantidadeLigacoesSuprimidasFevereiro;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasJulho() {
	
		return quantidadeLigacoesSuprimidasJulho;
	}
	
	public void setQuantidadeLigacoesSuprimidasJulho(
			Integer quantidadeLigacoesSuprimidasJulho) {
	
		this.quantidadeLigacoesSuprimidasJulho = quantidadeLigacoesSuprimidasJulho;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasJunho() {
	
		return quantidadeLigacoesSuprimidasJunho;
	}
	
	public void setQuantidadeLigacoesSuprimidasJunho(
			Integer quantidadeLigacoesSuprimidasJunho) {
	
		this.quantidadeLigacoesSuprimidasJunho = quantidadeLigacoesSuprimidasJunho;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasMarco() {
	
		return quantidadeLigacoesSuprimidasMarco;
	}
	
	public void setQuantidadeLigacoesSuprimidasMarco(
			Integer quantidadeLigacoesSuprimidasMarco) {
	
		this.quantidadeLigacoesSuprimidasMarco = quantidadeLigacoesSuprimidasMarco;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasAbril() {
	
		return quantidadeLigacoesSuprimidasAbril;
	}
	
	public void setQuantidadeLigacoesSuprimidasAbril(
			Integer quantidadeLigacoesSuprimidasAbril) {
	
		this.quantidadeLigacoesSuprimidasAbril = quantidadeLigacoesSuprimidasAbril;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasMaio() {
	
		return quantidadeLigacoesSuprimidasMaio;
	}
	
	public void setQuantidadeLigacoesSuprimidasMaio(
			Integer quantidadeLigacoesSuprimidasMaio) {
	
		this.quantidadeLigacoesSuprimidasMaio = quantidadeLigacoesSuprimidasMaio;
	}

	
	public Integer getQuantidadeEconomiasDezembro() {
	
		return quantidadeEconomiasDezembro;
	}

	
	public void setQuantidadeEconomiasDezembro(
			Integer quantidadeEconomiasDezembro) {
	
		this.quantidadeEconomiasDezembro = quantidadeEconomiasDezembro;
	}
	
	// Calculos do indice de ( Cortadas + Suprimidas ) / Cadastradas	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasDezembro(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasDezembro(), 
								this.getQuantidadeLigacoesSuprimidasDezembro() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasDezembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasJaneiro(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasJaneiro(), 
								this.getQuantidadeLigacoesSuprimidasJaneiro() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasJaneiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasFevereiro(){
		try{
		return 
			new BigDecimal ( 
					Util.somaInteiros( 
							this.getQuantidadeLigacoesCortadasFevereiro(), 
							this.getQuantidadeLigacoesSuprimidasFevereiro() ) ).
								divide( 
										new BigDecimal( this.getQuantidadeLigacoesCadastradasFevereiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasMarco(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasMarco(), 
								this.getQuantidadeLigacoesSuprimidasMarco() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasMarco() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasAbril(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasAbril(), 
								this.getQuantidadeLigacoesSuprimidasAbril() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasAbril() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasMaio(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasMaio(), 
								this.getQuantidadeLigacoesSuprimidasMaio() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasMaio() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasJunho(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasJunho(), 
								this.getQuantidadeLigacoesSuprimidasJunho() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasJunho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasJulho(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasJulho(), 
								this.getQuantidadeLigacoesSuprimidasJulho() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasJulho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasAgosto(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasAgosto(), 
								this.getQuantidadeLigacoesSuprimidasAgosto() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasAgosto() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}	
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasSetembro(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasSetembro(), 
								this.getQuantidadeLigacoesSuprimidasSetembro() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasSetembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}	
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasOutubro(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasOutubro(), 
								this.getQuantidadeLigacoesSuprimidasOutubro() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasOutubro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}	
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasNovembro(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasNovembro(), 
								this.getQuantidadeLigacoesSuprimidasNovembro() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasNovembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}	
	
	// Calculamos os indices de Ativas com débitos a mais de 3 meses / ativas
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasDezembro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mDezembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasDezembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasJaneiro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mJaneiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasJaneiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasFevereiro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mFevereiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasFevereiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasMarco(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mMarco() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasMarco() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasAbril(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mAbril() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasAbril() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasMaio(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mMaio() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasMaio() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasJunho(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mJunho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasJunho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasJulho(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mJulho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasJulho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}	
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasAgosto(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mAgosto() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasAgosto() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasSetembro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mSetembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasSetembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasOutubro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mOutubro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasOutubro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDivididoAtivasNovembro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mNovembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasNovembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	// Calculamos o indice de consumo 5m3 / consumo média	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoDezembro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Dezembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoDezembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoJaneiro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Janeiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoJaneiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoFevereiro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Fevereiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoFevereiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoMarco(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Marco() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoMarco() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}

	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoAbril(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Abril() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoAbril() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoMaio(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Maio() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoMaio() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoJunho(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Junho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoJunho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoJulho(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Julho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoJulho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoAgosto(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Agosto() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoAgosto() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoSetembro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Setembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSetembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoOutubro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Outubro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoOutubro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DivididoConsumoMedidoNovembro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Novembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoNovembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	// Calculamos o consumo nao medido / consumo medido ativo
	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoDezembro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoDezembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoDezembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoJaneiro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoJaneiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoJaneiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoFevereiro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoFevereiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoFevereiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoMarco(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoMarco() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoMarco() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoAbril(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoAbril() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoAbril() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoMaio(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoMaio() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoMaio() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoJunho(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoJunho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoJunho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoJulho(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoJulho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoJulho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}	

	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoAgosto(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoAgosto() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoAgosto() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}	

	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoSetembro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoSetembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSetembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}	

	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoOutubro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoOutubro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoOutubro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}	

	
	public BigDecimal getIndiceConsumoNaoMedidoDivididoConsumoMedidoNovembro(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoNovembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoNovembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	// Calculamos o indice de consumo medido / consumo medido ativo
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoDezembro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaDezembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoDezembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoJaneiro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaJaneiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoJaneiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoFevereiro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaFevereiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoFevereiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoMarco(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaMarco() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoMarco() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoAbril(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaAbril() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoAbril() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoMaio(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaMaio() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoMaio() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoJunho(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaJunho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoJunho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoJulho(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaJulho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoJulho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoAgosto(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaAgosto() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoAgosto() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoSetembro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaSetembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSetembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoOutubro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaOutubro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoOutubro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoNovembro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaNovembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoNovembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceSuprimidaCadastradasDezembro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasDezembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasDezembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceSuprimidaCadastradasJaneiro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasJaneiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasJaneiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	
	public BigDecimal getIndiceSuprimidaCadastradasFevereiro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasFevereiro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasFevereiro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	public BigDecimal getIndiceSuprimidaCadastradasMarco(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasMarco() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasMarco() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}	
	
	public BigDecimal getIndiceSuprimidaCadastradasAbril(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasAbril() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasAbril() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	
	public BigDecimal getIndiceSuprimidaCadastradasMaio(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasMaio() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasMaio() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceSuprimidaCadastradasJunho(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasJunho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasJunho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	

	public BigDecimal getIndiceSuprimidaCadastradasJulho(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasJulho() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasJulho() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	
	public BigDecimal getIndiceSuprimidaCadastradasAgosto(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasAgosto() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasAgosto() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	
	public BigDecimal getIndiceSuprimidaCadastradasSetembro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasSetembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasSetembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceSuprimidaCadastradasOutubro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasOutubro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasOutubro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}		
	
	public BigDecimal getIndiceSuprimidaCadastradasNovembro(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasNovembro() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasNovembro() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	public Integer getQuantidadeEconomiasAgosto() {
	
		return quantidadeEconomiasAgosto;
	}

	
	public void setQuantidadeEconomiasAgosto(Integer quantidadeEconomiasAgosto) {
	
		this.quantidadeEconomiasAgosto = quantidadeEconomiasAgosto;
	}

	
	public Integer getQuantidadeEconomiasNovembro() {
	
		return quantidadeEconomiasNovembro;
	}

	
	public void setQuantidadeEconomiasNovembro(Integer quantidadeEconomiasNovembro) {
	
		this.quantidadeEconomiasNovembro = quantidadeEconomiasNovembro;
	}

	
	public Integer getQuantidadeEconomiasOutubro() {
	
		return quantidadeEconomiasOutubro;
	}

	
	public void setQuantidadeEconomiasOutubro(Integer quantidadeEconomiasOutubro) {
	
		this.quantidadeEconomiasOutubro = quantidadeEconomiasOutubro;
	}

	
	public Integer getQuantidadeEconomiasSetembro() {
	
		return quantidadeEconomiasSetembro;
	}

	
	public void setQuantidadeEconomiasSetembro(Integer quantidadeEconomiasSetembro) {
	
		this.quantidadeEconomiasSetembro = quantidadeEconomiasSetembro;
	}

	
	public Integer getQuantidadeLigacoesAtivasAgosto() {
	
		return quantidadeLigacoesAtivasAgosto;
	}

	
	public void setQuantidadeLigacoesAtivasAgosto(
			Integer quantidadeLigacoesAtivasAgosto) {
	
		this.quantidadeLigacoesAtivasAgosto = quantidadeLigacoesAtivasAgosto;
	}

	
	public Integer getQuantidadeLigacoesAtivasDebitos3mAgosto() {
	
		return quantidadeLigacoesAtivasDebitos3mAgosto;
	}

	
	public void setQuantidadeLigacoesAtivasDebitos3mAgosto(
			Integer quantidadeLigacoesAtivasDebitos3mAgosto) {
	
		this.quantidadeLigacoesAtivasDebitos3mAgosto = quantidadeLigacoesAtivasDebitos3mAgosto;
	}

	
	public Integer getQuantidadeLigacoesAtivasDebitos3mNovembro() {
	
		return quantidadeLigacoesAtivasDebitos3mNovembro;
	}

	
	public void setQuantidadeLigacoesAtivasDebitos3mNovembro(
			Integer quantidadeLigacoesAtivasDebitos3mNovembro) {
	
		this.quantidadeLigacoesAtivasDebitos3mNovembro = quantidadeLigacoesAtivasDebitos3mNovembro;
	}

	
	public Integer getQuantidadeLigacoesAtivasDebitos3mOutubro() {
	
		return quantidadeLigacoesAtivasDebitos3mOutubro;
	}

	
	public void setQuantidadeLigacoesAtivasDebitos3mOutubro(
			Integer quantidadeLigacoesAtivasDebitos3mOutubro) {
	
		this.quantidadeLigacoesAtivasDebitos3mOutubro = quantidadeLigacoesAtivasDebitos3mOutubro;
	}

	
	public Integer getQuantidadeLigacoesAtivasDebitos3mSetembro() {
	
		return quantidadeLigacoesAtivasDebitos3mSetembro;
	}

	
	public void setQuantidadeLigacoesAtivasDebitos3mSetembro(
			Integer quantidadeLigacoesAtivasDebitos3mSetembro) {
	
		this.quantidadeLigacoesAtivasDebitos3mSetembro = quantidadeLigacoesAtivasDebitos3mSetembro;
	}

	
	public Integer getQuantidadeLigacoesAtivasNovembro() {
	
		return quantidadeLigacoesAtivasNovembro;
	}

	
	public void setQuantidadeLigacoesAtivasNovembro(
			Integer quantidadeLigacoesAtivasNovembro) {
	
		this.quantidadeLigacoesAtivasNovembro = quantidadeLigacoesAtivasNovembro;
	}

	
	public Integer getQuantidadeLigacoesAtivasOutubro() {
	
		return quantidadeLigacoesAtivasOutubro;
	}

	
	public void setQuantidadeLigacoesAtivasOutubro(
			Integer quantidadeLigacoesAtivasOutubro) {
	
		this.quantidadeLigacoesAtivasOutubro = quantidadeLigacoesAtivasOutubro;
	}

	
	public Integer getQuantidadeLigacoesAtivasSetembro() {
	
		return quantidadeLigacoesAtivasSetembro;
	}

	
	public void setQuantidadeLigacoesAtivasSetembro(
			Integer quantidadeLigacoesAtivasSetembro) {
	
		this.quantidadeLigacoesAtivasSetembro = quantidadeLigacoesAtivasSetembro;
	}

	
	public Integer getQuantidadeLigacoesCadastradasAgosto() {
	
		return quantidadeLigacoesCadastradasAgosto;
	}

	
	public void setQuantidadeLigacoesCadastradasAgosto(
			Integer quantidadeLigacoesCadastradasAgosto) {
	
		this.quantidadeLigacoesCadastradasAgosto = quantidadeLigacoesCadastradasAgosto;
	}

	
	public Integer getQuantidadeLigacoesCadastradasNovembro() {
	
		return quantidadeLigacoesCadastradasNovembro;
	}

	
	public void setQuantidadeLigacoesCadastradasNovembro(
			Integer quantidadeLigacoesCadastradasNovembro) {
	
		this.quantidadeLigacoesCadastradasNovembro = quantidadeLigacoesCadastradasNovembro;
	}

	
	public Integer getQuantidadeLigacoesCadastradasOutubro() {
	
		return quantidadeLigacoesCadastradasOutubro;
	}

	
	public void setQuantidadeLigacoesCadastradasOutubro(
			Integer quantidadeLigacoesCadastradasOutubro) {
	
		this.quantidadeLigacoesCadastradasOutubro = quantidadeLigacoesCadastradasOutubro;
	}

	
	public Integer getQuantidadeLigacoesCadastradasSetembro() {
	
		return quantidadeLigacoesCadastradasSetembro;
	}

	
	public void setQuantidadeLigacoesCadastradasSetembro(
			Integer quantidadeLigacoesCadastradasSetembro) {
	
		this.quantidadeLigacoesCadastradasSetembro = quantidadeLigacoesCadastradasSetembro;
	}

	
	public Integer getQuantidadeLigacoesConsumo5m3Agosto() {
	
		return quantidadeLigacoesConsumo5m3Agosto;
	}

	
	public void setQuantidadeLigacoesConsumo5m3Agosto(
			Integer quantidadeLigacoesConsumo5m3Agosto) {
	
		this.quantidadeLigacoesConsumo5m3Agosto = quantidadeLigacoesConsumo5m3Agosto;
	}

	
	public Integer getQuantidadeLigacoesConsumo5m3Novembro() {
	
		return quantidadeLigacoesConsumo5m3Novembro;
	}

	
	public void setQuantidadeLigacoesConsumo5m3Novembro(
			Integer quantidadeLigacoesConsumo5m3Novembro) {
	
		this.quantidadeLigacoesConsumo5m3Novembro = quantidadeLigacoesConsumo5m3Novembro;
	}

	
	public Integer getQuantidadeLigacoesConsumo5m3Outubro() {
	
		return quantidadeLigacoesConsumo5m3Outubro;
	}

	
	public void setQuantidadeLigacoesConsumo5m3Outubro(
			Integer quantidadeLigacoesConsumo5m3Outubro) {
	
		this.quantidadeLigacoesConsumo5m3Outubro = quantidadeLigacoesConsumo5m3Outubro;
	}

	
	public Integer getQuantidadeLigacoesConsumo5m3Setembro() {
	
		return quantidadeLigacoesConsumo5m3Setembro;
	}

	
	public void setQuantidadeLigacoesConsumo5m3Setembro(
			Integer quantidadeLigacoesConsumo5m3Setembro) {
	
		this.quantidadeLigacoesConsumo5m3Setembro = quantidadeLigacoesConsumo5m3Setembro;
	}

	
	public Integer getQuantidadeLigacoesConsumoMediaAgosto() {
	
		return quantidadeLigacoesConsumoMediaAgosto;
	}

	
	public void setQuantidadeLigacoesConsumoMediaAgosto(
			Integer quantidadeLigacoesConsumoMediaAgosto) {
	
		this.quantidadeLigacoesConsumoMediaAgosto = quantidadeLigacoesConsumoMediaAgosto;
	}

	
	public Integer getQuantidadeLigacoesConsumoMediaNovembro() {
	
		return quantidadeLigacoesConsumoMediaNovembro;
	}

	
	public void setQuantidadeLigacoesConsumoMediaNovembro(
			Integer quantidadeLigacoesConsumoMediaNovembro) {
	
		this.quantidadeLigacoesConsumoMediaNovembro = quantidadeLigacoesConsumoMediaNovembro;
	}

	
	public Integer getQuantidadeLigacoesConsumoMediaOutubro() {
	
		return quantidadeLigacoesConsumoMediaOutubro;
	}

	
	public void setQuantidadeLigacoesConsumoMediaOutubro(
			Integer quantidadeLigacoesConsumoMediaOutubro) {
	
		this.quantidadeLigacoesConsumoMediaOutubro = quantidadeLigacoesConsumoMediaOutubro;
	}

	
	public Integer getQuantidadeLigacoesConsumoMediaSetembro() {
	
		return quantidadeLigacoesConsumoMediaSetembro;
	}

	
	public void setQuantidadeLigacoesConsumoMediaSetembro(
			Integer quantidadeLigacoesConsumoMediaSetembro) {
	
		this.quantidadeLigacoesConsumoMediaSetembro = quantidadeLigacoesConsumoMediaSetembro;
	}

	
	public Integer getQuantidadeLigacoesConsumoMedidoAgosto() {
	
		return quantidadeLigacoesConsumoMedidoAgosto;
	}

	
	public void setQuantidadeLigacoesConsumoMedidoAgosto(
			Integer quantidadeLigacoesConsumoMedidoAgosto) {
	
		this.quantidadeLigacoesConsumoMedidoAgosto = quantidadeLigacoesConsumoMedidoAgosto;
	}

	
	public Integer getQuantidadeLigacoesConsumoMedidoNovembro() {
	
		return quantidadeLigacoesConsumoMedidoNovembro;
	}

	
	public void setQuantidadeLigacoesConsumoMedidoNovembro(
			Integer quantidadeLigacoesConsumoMedidoNovembro) {
	
		this.quantidadeLigacoesConsumoMedidoNovembro = quantidadeLigacoesConsumoMedidoNovembro;
	}

	
	public Integer getQuantidadeLigacoesConsumoMedidoOutubro() {
	
		return quantidadeLigacoesConsumoMedidoOutubro;
	}

	
	public void setQuantidadeLigacoesConsumoMedidoOutubro(
			Integer quantidadeLigacoesConsumoMedidoOutubro) {
	
		this.quantidadeLigacoesConsumoMedidoOutubro = quantidadeLigacoesConsumoMedidoOutubro;
	}

	
	public Integer getQuantidadeLigacoesConsumoMedidoSetembro() {
	
		return quantidadeLigacoesConsumoMedidoSetembro;
	}

	
	public void setQuantidadeLigacoesConsumoMedidoSetembro(
			Integer quantidadeLigacoesConsumoMedidoSetembro) {
	
		this.quantidadeLigacoesConsumoMedidoSetembro = quantidadeLigacoesConsumoMedidoSetembro;
	}

	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoAgosto() {
	
		return quantidadeLigacoesConsumoNaoMedidoAgosto;
	}

	
	public void setQuantidadeLigacoesConsumoNaoMedidoAgosto(
			Integer quantidadeLigacoesConsumoNaoMedidoAgosto) {
	
		this.quantidadeLigacoesConsumoNaoMedidoAgosto = quantidadeLigacoesConsumoNaoMedidoAgosto;
	}

	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoNovembro() {
	
		return quantidadeLigacoesConsumoNaoMedidoNovembro;
	}

	
	public void setQuantidadeLigacoesConsumoNaoMedidoNovembro(
			Integer quantidadeLigacoesConsumoNaoMedidoNovembro) {
	
		this.quantidadeLigacoesConsumoNaoMedidoNovembro = quantidadeLigacoesConsumoNaoMedidoNovembro;
	}

	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoOutubro() {
	
		return quantidadeLigacoesConsumoNaoMedidoOutubro;
	}

	
	public void setQuantidadeLigacoesConsumoNaoMedidoOutubro(
			Integer quantidadeLigacoesConsumoNaoMedidoOutubro) {
	
		this.quantidadeLigacoesConsumoNaoMedidoOutubro = quantidadeLigacoesConsumoNaoMedidoOutubro;
	}

	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoSetembro() {
	
		return quantidadeLigacoesConsumoNaoMedidoSetembro;
	}

	
	public void setQuantidadeLigacoesConsumoNaoMedidoSetembro(
			Integer quantidadeLigacoesConsumoNaoMedidoSetembro) {
	
		this.quantidadeLigacoesConsumoNaoMedidoSetembro = quantidadeLigacoesConsumoNaoMedidoSetembro;
	}

	
	public Integer getQuantidadeLigacoesCortadasAgosto() {
	
		return quantidadeLigacoesCortadasAgosto;
	}

	
	public void setQuantidadeLigacoesCortadasAgosto(
			Integer quantidadeLigacoesCortadasAgosto) {
	
		this.quantidadeLigacoesCortadasAgosto = quantidadeLigacoesCortadasAgosto;
	}

	
	public Integer getQuantidadeLigacoesCortadasNovembro() {
	
		return quantidadeLigacoesCortadasNovembro;
	}

	
	public void setQuantidadeLigacoesCortadasNovembro(
			Integer quantidadeLigacoesCortadasNovembro) {
	
		this.quantidadeLigacoesCortadasNovembro = quantidadeLigacoesCortadasNovembro;
	}

	
	public Integer getQuantidadeLigacoesCortadasOutubro() {
	
		return quantidadeLigacoesCortadasOutubro;
	}

	
	public void setQuantidadeLigacoesCortadasOutubro(
			Integer quantidadeLigacoesCortadasOutubro) {
	
		this.quantidadeLigacoesCortadasOutubro = quantidadeLigacoesCortadasOutubro;
	}

	
	public Integer getQuantidadeLigacoesCortadasSetembro() {
	
		return quantidadeLigacoesCortadasSetembro;
	}

	
	public void setQuantidadeLigacoesCortadasSetembro(
			Integer quantidadeLigacoesCortadasSetembro) {
	
		this.quantidadeLigacoesCortadasSetembro = quantidadeLigacoesCortadasSetembro;
	}

	
	public Integer getQuantidadeLigacoesSuprimidasAgosto() {
	
		return quantidadeLigacoesSuprimidasAgosto;
	}

	
	public void setQuantidadeLigacoesSuprimidasAgosto(
			Integer quantidadeLigacoesSuprimidasAgosto) {
	
		this.quantidadeLigacoesSuprimidasAgosto = quantidadeLigacoesSuprimidasAgosto;
	}

	
	public Integer getQuantidadeLigacoesSuprimidasNovembro() {
	
		return quantidadeLigacoesSuprimidasNovembro;
	}

	
	public void setQuantidadeLigacoesSuprimidasNovembro(
			Integer quantidadeLigacoesSuprimidasNovembro) {
	
		this.quantidadeLigacoesSuprimidasNovembro = quantidadeLigacoesSuprimidasNovembro;
	}

	
	public Integer getQuantidadeLigacoesSuprimidasOutubro() {
	
		return quantidadeLigacoesSuprimidasOutubro;
	}

	
	public void setQuantidadeLigacoesSuprimidasOutubro(
			Integer quantidadeLigacoesSuprimidasOutubro) {
	
		this.quantidadeLigacoesSuprimidasOutubro = quantidadeLigacoesSuprimidasOutubro;
	}

	
	public Integer getQuantidadeLigacoesSuprimidasSetembro() {
	
		return quantidadeLigacoesSuprimidasSetembro;
	}

	
	public void setQuantidadeLigacoesSuprimidasSetembro(
			Integer quantidadeLigacoesSuprimidasSetembro) {
	
		this.quantidadeLigacoesSuprimidasSetembro = quantidadeLigacoesSuprimidasSetembro;
	}	
}

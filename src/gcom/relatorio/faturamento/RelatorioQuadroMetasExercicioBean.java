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
package gcom.relatorio.faturamento;

import gcom.gerencial.bean.QuadroMetasExercicioHelper;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * classe responsável por criar o relatório de Quadro de Metas Acumulado
 * 
 * @author Bruno Barros
 * @created 27/12/2007
 */
public class RelatorioQuadroMetasExercicioBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	
	private String opcaoTotalizacao = "";
	private String nomeGerenciaRegional;
	private String nomeUnidadeNegocio;
	private String nomeLocalidade;
	private String nomeCentroCusto;
	
	// Ligacoes Cadastradas
	private String quantidadeLigacoesCadastradasDezembro;
	private String quantidadeLigacoesCadastradasJaneiro;
	private String quantidadeLigacoesCadastradasFevereiro;
	private String quantidadeLigacoesCadastradasMarco;
	private String quantidadeLigacoesCadastradasAbril;
	private String quantidadeLigacoesCadastradasMaio;
	private String quantidadeLigacoesCadastradasJunho;
	private String quantidadeLigacoesCadastradasJulho;
	private String quantidadeLigacoesCadastradasAgosto;
	private String quantidadeLigacoesCadastradasSetembro;
	private String quantidadeLigacoesCadastradasOutubro;
	private String quantidadeLigacoesCadastradasNovembro;
	
	// Ligacoes Cortadas
	private String quantidadeLigacoesCortadasDezembro;
	private String quantidadeLigacoesCortadasJaneiro;
	private String quantidadeLigacoesCortadasFevereiro;
	private String quantidadeLigacoesCortadasMarco;
	private String quantidadeLigacoesCortadasAbril;
	private String quantidadeLigacoesCortadasMaio;
	private String quantidadeLigacoesCortadasJunho;
	private String quantidadeLigacoesCortadasJulho;
	private String quantidadeLigacoesCortadasAgosto;
	private String quantidadeLigacoesCortadasSetembro;
	private String quantidadeLigacoesCortadasOutubro;
	private String quantidadeLigacoesCortadasNovembro;
	
	// Ligacoes Suprimidas
	private String quantidadeLigacoesSuprimidasDezembro;
	private String quantidadeLigacoesSuprimidasJaneiro;
	private String quantidadeLigacoesSuprimidasFevereiro;
	private String quantidadeLigacoesSuprimidasMarco;
	private String quantidadeLigacoesSuprimidasAbril;
	private String quantidadeLigacoesSuprimidasMaio;
	private String quantidadeLigacoesSuprimidasJunho;
	private String quantidadeLigacoesSuprimidasJulho;
	private String quantidadeLigacoesSuprimidasAgosto;
	private String quantidadeLigacoesSuprimidasSetembro;
	private String quantidadeLigacoesSuprimidasOutubro;
	private String quantidadeLigacoesSuprimidasNovembro;
	
	// Ligacoes Ativas
	private String quantidadeLigacoesAtivasDezembro;
	private String quantidadeLigacoesAtivasJaneiro;
	private String quantidadeLigacoesAtivasFevereiro;
	private String quantidadeLigacoesAtivasMarco;
	private String quantidadeLigacoesAtivasAbril;
	private String quantidadeLigacoesAtivasMaio;
	private String quantidadeLigacoesAtivasJunho;
	private String quantidadeLigacoesAtivasJulho;
	private String quantidadeLigacoesAtivasAgosto;
	private String quantidadeLigacoesAtivasSetembro;
	private String quantidadeLigacoesAtivasOutubro;
	private String quantidadeLigacoesAtivasNovembro;

	
	// Ligacoes Ativas com débitos a mais de 3 meses
	private String quantidadeLigacoesAtivasDebitos3mDezembro;
	private String quantidadeLigacoesAtivasDebitos3mJaneiro;
	private String quantidadeLigacoesAtivasDebitos3mFevereiro;
	private String quantidadeLigacoesAtivasDebitos3mMarco;
	private String quantidadeLigacoesAtivasDebitos3mAbril;
	private String quantidadeLigacoesAtivasDebitos3mMaio;
	private String quantidadeLigacoesAtivasDebitos3mJunho;
	private String quantidadeLigacoesAtivasDebitos3mJulho;
	private String quantidadeLigacoesAtivasDebitos3mAgosto;
	private String quantidadeLigacoesAtivasDebitos3mSetembro;
	private String quantidadeLigacoesAtivasDebitos3mOutubro;
	private String quantidadeLigacoesAtivasDebitos3mNovembro;

	
	
	// Ligacoes Consumo Medido
	private String quantidadeLigacoesConsumoMedidoDezembro;
	private String quantidadeLigacoesConsumoMedidoJaneiro;
	private String quantidadeLigacoesConsumoMedidoFevereiro;
	private String quantidadeLigacoesConsumoMedidoMarco;
	private String quantidadeLigacoesConsumoMedidoAbril;
	private String quantidadeLigacoesConsumoMedidoMaio;
	private String quantidadeLigacoesConsumoMedidoJunho;
	private String quantidadeLigacoesConsumoMedidoJulho;
	private String quantidadeLigacoesConsumoMedidoAgosto;
	private String quantidadeLigacoesConsumoMedidoSetembro;
	private String quantidadeLigacoesConsumoMedidoOutubro;
	private String quantidadeLigacoesConsumoMedidoNovembro;
	
	// Ligacoes Consumo 5m3
	private String quantidadeLigacoesConsumo5m3Dezembro;
	private String quantidadeLigacoesConsumo5m3Janeiro;
	private String quantidadeLigacoesConsumo5m3Fevereiro;
	private String quantidadeLigacoesConsumo5m3Marco;
	private String quantidadeLigacoesConsumo5m3Abril;
	private String quantidadeLigacoesConsumo5m3Maio;
	private String quantidadeLigacoesConsumo5m3Junho;
	private String quantidadeLigacoesConsumo5m3Julho;
	private String quantidadeLigacoesConsumo5m3Agosto;
	private String quantidadeLigacoesConsumo5m3Setembro;
	private String quantidadeLigacoesConsumo5m3Outubro;
	private String quantidadeLigacoesConsumo5m3Novembro;
	
	// Ligacoes Consumo Não Medido
	private String quantidadeLigacoesConsumoNaoMedidoDezembro;
	private String quantidadeLigacoesConsumoNaoMedidoJaneiro;
	private String quantidadeLigacoesConsumoNaoMedidoFevereiro;
	private String quantidadeLigacoesConsumoNaoMedidoMarco;
	private String quantidadeLigacoesConsumoNaoMedidoAbril;
	private String quantidadeLigacoesConsumoNaoMedidoMaio;
	private String quantidadeLigacoesConsumoNaoMedidoJunho;
	private String quantidadeLigacoesConsumoNaoMedidoJulho;
	private String quantidadeLigacoesConsumoNaoMedidoAgosto;
	private String quantidadeLigacoesConsumoNaoMedidoSetembro;
	private String quantidadeLigacoesConsumoNaoMedidoOutubro;
	private String quantidadeLigacoesConsumoNaoMedidoNovembro;
	
	// Ligacoes Consumo Medio
	private String quantidadeLigacoesConsumoMediaDezembro;
	private String quantidadeLigacoesConsumoMediaJaneiro;
	private String quantidadeLigacoesConsumoMediaFevereiro;
	private String quantidadeLigacoesConsumoMediaMarco;
	private String quantidadeLigacoesConsumoMediaAbril;
	private String quantidadeLigacoesConsumoMediaMaio;
	private String quantidadeLigacoesConsumoMediaJunho;
	private String quantidadeLigacoesConsumoMediaJulho;
	private String quantidadeLigacoesConsumoMediaAgosto;
	private String quantidadeLigacoesConsumoMediaSetembro;
	private String quantidadeLigacoesConsumoMediaOutubro;
	private String quantidadeLigacoesConsumoMediaNovembro;
	
	// Ligacoes quantidade Economias
	private String quantidadeEconomiasDezembro;
	private String quantidadeEconomiasJaneiro;
	private String quantidadeEconomiasFevereiro;
	private String quantidadeEconomiasMarco;
	private String quantidadeEconomiasAbril;
	private String quantidadeEconomiasMaio;
	private String quantidadeEconomiasJunho;
	private String quantidadeEconomiasJulho;
	private String quantidadeEconomiasAgosto;
	private String quantidadeEconomiasSetembro;
	private String quantidadeEconomiasOutubro;
	private String quantidadeEconomiasNovembro;
	
	// Calculos do indice de ( Cortadas + Suprimidas ) / Cadastradas	
	public String indiceCortadaMaisSuprimidaDivididoCadastradasDezembro;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasJaneiro;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasFevereiro;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasMarco;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasAbril;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasMaio;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasJunho;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasJulho;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasAgosto;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasSetembro;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasOutubro;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasNovembro;
	
	
	// Calculamos os indices de Ativas com débitos a mais de 3 meses / ativas
	public String indiceAtivasDebitos3mDivididoAtivasDezembro;
	public String indiceAtivasDebitos3mDivididoAtivasJaneiro;
	public String indiceAtivasDebitos3mDivididoAtivasFevereiro;
	public String indiceAtivasDebitos3mDivididoAtivasMarco;
	public String indiceAtivasDebitos3mDivididoAtivasAbril;
	public String indiceAtivasDebitos3mDivididoAtivasMaio;
	public String indiceAtivasDebitos3mDivididoAtivasJunho;
	public String indiceAtivasDebitos3mDivididoAtivasJulho;
	public String indiceAtivasDebitos3mDivididoAtivasAgosto;
	public String indiceAtivasDebitos3mDivididoAtivasSetembro;
	public String indiceAtivasDebitos3mDivididoAtivasOutubro;
	public String indiceAtivasDebitos3mDivididoAtivasNovembro;
	
	
	// Calculamos o indice de consumo 5m3 / consumo média
	public String indiceConsumo5m3DivididoConsumoMedidoDezembro;
	public String indiceConsumo5m3DivididoConsumoMedidoJaneiro;
	public String indiceConsumo5m3DivididoConsumoMedidoFevereiro;
	public String indiceConsumo5m3DivididoConsumoMedidoMarco;
	public String indiceConsumo5m3DivididoConsumoMedidoAbril;
	public String indiceConsumo5m3DivididoConsumoMedidoMaio;
	public String indiceConsumo5m3DivididoConsumoMedidoJunho;
	public String indiceConsumo5m3DivididoConsumoMedidoJulho;
	public String indiceConsumo5m3DivididoConsumoMedidoAgosto;
	public String indiceConsumo5m3DivididoConsumoMedidoSetembro;
	public String indiceConsumo5m3DivididoConsumoMedidoOutubro;
	public String indiceConsumo5m3DivididoConsumoMedidoNovembro;
	
	
	// Calculamos o consumo nao medido / consumo medido ativo
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoDezembro;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoJaneiro;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoFevereiro;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoMarco;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoAbril;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoMaio;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoJunho;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoJulho;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoAgosto;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoSetembro;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoOutubro;
	public String indiceConsumoNaoMedidoDivididoConsumoMedidoNovembro;	
	
	// Calculamos o indice de consumo medido / consumo medido ativo
	public String indiceConsumoMediaDivididoConsumoMedidoDezembro;
	public String indiceConsumoMediaDivididoConsumoMedidoJaneiro;
	public String indiceConsumoMediaDivididoConsumoMedidoFevereiro;
	public String indiceConsumoMediaDivididoConsumoMedidoMarco;
	public String indiceConsumoMediaDivididoConsumoMedidoAbril;
	public String indiceConsumoMediaDivididoConsumoMedidoMaio;
	public String indiceConsumoMediaDivididoConsumoMedidoJunho;
	public String indiceConsumoMediaDivididoConsumoMedidoJulho;
	public String indiceConsumoMediaDivididoConsumoMedidoAgosto;
	public String indiceConsumoMediaDivididoConsumoMedidoSetembro;
	public String indiceConsumoMediaDivididoConsumoMedidoOutubro;
	public String indiceConsumoMediaDivididoConsumoMedidoNovembro;	
	
	public String indiceSuprimidaCadastradasDezembro;
	public String indiceSuprimidaCadastradasJaneiro;
	public String indiceSuprimidaCadastradasFevereiro;
	public String indiceSuprimidaCadastradasMarco;
	public String indiceSuprimidaCadastradasAbril;
	public String indiceSuprimidaCadastradasMaio;
	public String indiceSuprimidaCadastradasJunho;
	public String indiceSuprimidaCadastradasJulho;
	public String indiceSuprimidaCadastradasAgosto;
	public String indiceSuprimidaCadastradasSetembro;
	public String indiceSuprimidaCadastradasOutubro;
	public String indiceSuprimidaCadastradasNovembro;
	
	
	public RelatorioQuadroMetasExercicioBean(QuadroMetasExercicioHelper quadro) {
		this.opcaoTotalizacao = quadro.getOpcaoTotalizacao();
		if ( quadro.getNomeGerenciaRegional() != null ){
			this.nomeGerenciaRegional = quadro.getNomeGerenciaRegional().trim();
		}

		if ( quadro.getNomeUnidadeNegocio() != null ){		
			this.nomeUnidadeNegocio = quadro.getNomeUnidadeNegocio().trim();
		}
		
		if ( quadro.getNomeLocalidade() != null ){
			this.nomeLocalidade = quadro.getNomeLocalidade().trim();
		}
		
		if ( quadro.getNomeCentroCusto() != null ){
			this.nomeCentroCusto = quadro.getNomeCentroCusto().trim();
		}
		
		// Ligacoes Cadastradas
		this.quantidadeLigacoesCadastradasDezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasDezembro() );
		this.quantidadeLigacoesCadastradasJaneiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasJaneiro() );
		this.quantidadeLigacoesCadastradasFevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasFevereiro() );
		this.quantidadeLigacoesCadastradasMarco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasMarco() );
		this.quantidadeLigacoesCadastradasAbril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasAbril() );
		this.quantidadeLigacoesCadastradasMaio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasMaio() );
		this.quantidadeLigacoesCadastradasJunho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasJunho() );
		this.quantidadeLigacoesCadastradasJulho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasJulho() );
		this.quantidadeLigacoesCadastradasAgosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasAgosto() );
		this.quantidadeLigacoesCadastradasSetembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasSetembro() );
		this.quantidadeLigacoesCadastradasOutubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasOutubro() );
		this.quantidadeLigacoesCadastradasNovembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasNovembro() );
		
		
		// Ligacoes Cortadas
		this.quantidadeLigacoesCortadasDezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasDezembro() );
		this.quantidadeLigacoesCortadasJaneiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasJaneiro() );
		this.quantidadeLigacoesCortadasFevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasFevereiro() );
		this.quantidadeLigacoesCortadasMarco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasMarco() );
		this.quantidadeLigacoesCortadasAbril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasAbril() );
		this.quantidadeLigacoesCortadasMaio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasMaio() );
		this.quantidadeLigacoesCortadasJunho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasJunho() );
		this.quantidadeLigacoesCortadasJulho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasJulho() );
		this.quantidadeLigacoesCortadasAgosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasAgosto() );
		this.quantidadeLigacoesCortadasSetembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasSetembro() );
		this.quantidadeLigacoesCortadasOutubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasOutubro() );
		this.quantidadeLigacoesCortadasNovembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasNovembro() );
		
		
		// Ligacoes Suprimidas
		this.quantidadeLigacoesSuprimidasDezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasDezembro() );
		this.quantidadeLigacoesSuprimidasJaneiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasJaneiro() );
		this.quantidadeLigacoesSuprimidasFevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasFevereiro() );
		this.quantidadeLigacoesSuprimidasMarco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasMarco() );
		this.quantidadeLigacoesSuprimidasAbril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasAbril() );
		this.quantidadeLigacoesSuprimidasMaio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasMaio() );
		this.quantidadeLigacoesSuprimidasJunho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasJunho() );
		this.quantidadeLigacoesSuprimidasJulho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasJulho() );
		this.quantidadeLigacoesSuprimidasAgosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasAgosto() );
		this.quantidadeLigacoesSuprimidasSetembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasSetembro() );
		this.quantidadeLigacoesSuprimidasOutubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasOutubro() );
		this.quantidadeLigacoesSuprimidasNovembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasNovembro() );
		
		
		
		// Ligacoes Ativas
		this.quantidadeLigacoesAtivasDezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDezembro() );
		this.quantidadeLigacoesAtivasJaneiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasJaneiro() );
		this.quantidadeLigacoesAtivasFevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasFevereiro() );
		this.quantidadeLigacoesAtivasMarco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasMarco() );
		this.quantidadeLigacoesAtivasAbril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasAbril() );
		this.quantidadeLigacoesAtivasMaio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasMaio() );
		this.quantidadeLigacoesAtivasJunho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasJunho() );
		this.quantidadeLigacoesAtivasJulho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasJulho() );
		this.quantidadeLigacoesAtivasAgosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasAgosto() );
		this.quantidadeLigacoesAtivasSetembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasSetembro() );
		this.quantidadeLigacoesAtivasOutubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasOutubro() );
		this.quantidadeLigacoesAtivasNovembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasNovembro() );
		
		
		// Ligacoes Ativas com débitos a mais de 3 meses
		this.quantidadeLigacoesAtivasDebitos3mDezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mDezembro() );
		this.quantidadeLigacoesAtivasDebitos3mJaneiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mJaneiro() );
		this.quantidadeLigacoesAtivasDebitos3mFevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mFevereiro() );
		this.quantidadeLigacoesAtivasDebitos3mMarco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mMarco() );
		this.quantidadeLigacoesAtivasDebitos3mAbril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mAbril() );
		this.quantidadeLigacoesAtivasDebitos3mMaio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mMaio() );
		this.quantidadeLigacoesAtivasDebitos3mJunho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mJunho() );
		this.quantidadeLigacoesAtivasDebitos3mJulho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mJulho() );
		this.quantidadeLigacoesAtivasDebitos3mAgosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mAgosto() );
		this.quantidadeLigacoesAtivasDebitos3mSetembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mSetembro() );
		this.quantidadeLigacoesAtivasDebitos3mOutubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mOutubro() );
		this.quantidadeLigacoesAtivasDebitos3mNovembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mNovembro() );
		
		
		// Ligacoes Consumo Medido
		this.quantidadeLigacoesConsumoMedidoDezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoDezembro() );
		this.quantidadeLigacoesConsumoMedidoJaneiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoJaneiro() );
		this.quantidadeLigacoesConsumoMedidoFevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoFevereiro() );
		this.quantidadeLigacoesConsumoMedidoMarco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoMarco() );
		this.quantidadeLigacoesConsumoMedidoAbril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoAbril() );
		this.quantidadeLigacoesConsumoMedidoMaio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoMaio() );
		this.quantidadeLigacoesConsumoMedidoJunho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoJunho() );
		this.quantidadeLigacoesConsumoMedidoJulho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoJulho() );
		this.quantidadeLigacoesConsumoMedidoAgosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoAgosto() );
		this.quantidadeLigacoesConsumoMedidoSetembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoSetembro() );
		this.quantidadeLigacoesConsumoMedidoOutubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoOutubro() );
		this.quantidadeLigacoesConsumoMedidoNovembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoNovembro() );
		
		
		// Ligacoes Consumo 5m3
		this.quantidadeLigacoesConsumo5m3Dezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Dezembro() );
		this.quantidadeLigacoesConsumo5m3Janeiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Janeiro() );
		this.quantidadeLigacoesConsumo5m3Fevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Fevereiro() );
		this.quantidadeLigacoesConsumo5m3Marco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Marco() );
		this.quantidadeLigacoesConsumo5m3Abril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Abril() );
		this.quantidadeLigacoesConsumo5m3Maio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Maio() );
		this.quantidadeLigacoesConsumo5m3Junho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Junho() );
		this.quantidadeLigacoesConsumo5m3Julho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Julho() );
		this.quantidadeLigacoesConsumo5m3Agosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Agosto() );
		this.quantidadeLigacoesConsumo5m3Setembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Setembro() );
		this.quantidadeLigacoesConsumo5m3Outubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Outubro() );
		this.quantidadeLigacoesConsumo5m3Novembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Novembro() );
		
		
		// Ligacoes Consumo Não Medido
		this.quantidadeLigacoesConsumoNaoMedidoDezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoDezembro() );
		this.quantidadeLigacoesConsumoNaoMedidoJaneiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoJaneiro() );
		this.quantidadeLigacoesConsumoNaoMedidoFevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoFevereiro() );
		this.quantidadeLigacoesConsumoNaoMedidoMarco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoMarco() );
		this.quantidadeLigacoesConsumoNaoMedidoAbril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoAbril() );
		this.quantidadeLigacoesConsumoNaoMedidoMaio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoMaio() );
		this.quantidadeLigacoesConsumoNaoMedidoJunho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoJunho() );
		this.quantidadeLigacoesConsumoNaoMedidoJulho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoJulho() );
		this.quantidadeLigacoesConsumoNaoMedidoAgosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoAgosto() );
		this.quantidadeLigacoesConsumoNaoMedidoSetembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoSetembro() );
		this.quantidadeLigacoesConsumoNaoMedidoOutubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoOutubro() );
		this.quantidadeLigacoesConsumoNaoMedidoNovembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoNovembro() );
		
		
		// Ligacoes Consumo Medio
		this.quantidadeLigacoesConsumoMediaDezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaDezembro() );
		this.quantidadeLigacoesConsumoMediaJaneiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaJaneiro() );
		this.quantidadeLigacoesConsumoMediaFevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaFevereiro() );
		this.quantidadeLigacoesConsumoMediaMarco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaMarco() );
		this.quantidadeLigacoesConsumoMediaAbril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaAbril() );
		this.quantidadeLigacoesConsumoMediaMaio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaMaio() );
		this.quantidadeLigacoesConsumoMediaJunho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaJunho() );
		this.quantidadeLigacoesConsumoMediaJulho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaJulho() );
		this.quantidadeLigacoesConsumoMediaAgosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaAgosto() );
		this.quantidadeLigacoesConsumoMediaSetembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaSetembro() );
		this.quantidadeLigacoesConsumoMediaOutubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaOutubro() );
		this.quantidadeLigacoesConsumoMediaNovembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaNovembro() );
		
		
		// Ligacoes quantidade Economias
		this.quantidadeEconomiasDezembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasDezembro() );
		this.quantidadeEconomiasJaneiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasJaneiro() );
		this.quantidadeEconomiasFevereiro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasFevereiro() );
		this.quantidadeEconomiasMarco = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasMarco() );
		this.quantidadeEconomiasAbril = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasAbril() );
		this.quantidadeEconomiasMaio = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasMaio() );
		this.quantidadeEconomiasJunho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasJunho() );
		this.quantidadeEconomiasJulho = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasJulho() );
		this.quantidadeEconomiasAgosto = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasAgosto() );
		this.quantidadeEconomiasSetembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasSetembro() );
		this.quantidadeEconomiasOutubro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasOutubro() );
		this.quantidadeEconomiasNovembro = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasNovembro() );
		
		
		
		// Calculos do indice de  = Cortadas + Suprimidas ) / Cadastradas	
		this.indiceCortadaMaisSuprimidaDivididoCadastradasDezembro = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasDezembro(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasJaneiro = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasJaneiro(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasFevereiro = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasFevereiro(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasMarco = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasMarco(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasAbril = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasAbril(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasMaio = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasMaio(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasJunho = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasJunho(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasJulho = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasJulho(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasAgosto = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasAgosto(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasSetembro = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasSetembro(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasOutubro = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasOutubro(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasNovembro = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasNovembro(), 2, true );		
		
		
		// Calculamos os indices de Ativas com débitos a mais de 3 meses / ativas
		this.indiceAtivasDebitos3mDivididoAtivasDezembro = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasDezembro(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasJaneiro = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasJaneiro(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasFevereiro = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasFevereiro(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasMarco = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasMarco(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasAbril = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasAbril(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasMaio = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasMaio(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasJunho = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasJunho(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasJulho = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasJulho(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasAgosto = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasAgosto(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasSetembro = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasSetembro(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasOutubro = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasOutubro(), 2, true );
		this.indiceAtivasDebitos3mDivididoAtivasNovembro = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDivididoAtivasNovembro(), 2, true );
		
		
		// Calculamos o indice de consumo 5m3 / consumo média
		this.indiceConsumo5m3DivididoConsumoMedidoDezembro = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoDezembro(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoJaneiro = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoJaneiro(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoFevereiro = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoFevereiro(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoMarco = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoMarco(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoAbril = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoAbril(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoMaio = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoMaio(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoJunho = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoJunho(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoJulho = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoJulho(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoAgosto = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoAgosto(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoSetembro = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoSetembro(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoOutubro = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoOutubro(), 2, true );
		this.indiceConsumo5m3DivididoConsumoMedidoNovembro = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DivididoConsumoMedidoNovembro(), 2, true );
		
		
//		 Calculamos o consumo nao medido / consumo medido ativo
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoDezembro = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoDezembro(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoJaneiro = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoJaneiro(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoFevereiro = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoFevereiro(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoMarco = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoMarco(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoAbril = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoAbril(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoMaio = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoMaio(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoJunho = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoJunho(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoJulho = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoJulho(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoAgosto = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoAgosto(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoSetembro = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoSetembro(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoOutubro = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoOutubro(), 2, true );
		this.indiceConsumoNaoMedidoDivididoConsumoMedidoNovembro = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDivididoConsumoMedidoNovembro(), 2, true );
		
		// Calculamos o indice de consumo medido / consumo medido ativo
		this.indiceConsumoMediaDivididoConsumoMedidoDezembro = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoDezembro(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoJaneiro = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoJaneiro(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoFevereiro = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoFevereiro(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoMarco = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoMarco(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoAbril = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoAbril(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoMaio = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoMaio(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoJunho = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoJunho(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoJulho = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoJulho(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoAgosto = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoAgosto(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoSetembro = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoSetembro(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoOutubro = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoOutubro(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoNovembro = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoNovembro(), 2, true );
		
		this.indiceSuprimidaCadastradasDezembro = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasDezembro(), 2, true );
		this.indiceSuprimidaCadastradasJaneiro = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasJaneiro(), 2, true );
		this.indiceSuprimidaCadastradasFevereiro = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasFevereiro(), 2, true );
		this.indiceSuprimidaCadastradasMarco = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasMarco(), 2, true );
		this.indiceSuprimidaCadastradasAbril = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasAbril(), 2, true );
		this.indiceSuprimidaCadastradasMaio = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasMaio(), 2, true );
		this.indiceSuprimidaCadastradasJunho = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasJunho(), 2, true );
		this.indiceSuprimidaCadastradasJulho = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasJulho(), 2, true );
		this.indiceSuprimidaCadastradasAgosto = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasAgosto(), 2, true );		
		this.indiceSuprimidaCadastradasSetembro = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasSetembro(), 2, true );
		this.indiceSuprimidaCadastradasOutubro = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasOutubro(), 2, true );
		this.indiceSuprimidaCadastradasNovembro = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasNovembro(), 2, true );
	}

	
	public static long getSerialVersionUID() {
	
		return serialVersionUID;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasAbril() {
	
		return indiceAtivasDebitos3mDivididoAtivasAbril;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasAgosto() {
	
		return indiceAtivasDebitos3mDivididoAtivasAgosto;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasDezembro() {
	
		return indiceAtivasDebitos3mDivididoAtivasDezembro;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasFevereiro() {
	
		return indiceAtivasDebitos3mDivididoAtivasFevereiro;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasJaneiro() {
	
		return indiceAtivasDebitos3mDivididoAtivasJaneiro;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasJulho() {
	
		return indiceAtivasDebitos3mDivididoAtivasJulho;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasJunho() {
	
		return indiceAtivasDebitos3mDivididoAtivasJunho;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasMaio() {
	
		return indiceAtivasDebitos3mDivididoAtivasMaio;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasMarco() {
	
		return indiceAtivasDebitos3mDivididoAtivasMarco;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasNovembro() {
	
		return indiceAtivasDebitos3mDivididoAtivasNovembro;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasOutubro() {
	
		return indiceAtivasDebitos3mDivididoAtivasOutubro;
	}


	
	public String getIndiceAtivasDebitos3mDivididoAtivasSetembro() {
	
		return indiceAtivasDebitos3mDivididoAtivasSetembro;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoAbril() {
	
		return indiceConsumo5m3DivididoConsumoMedidoAbril;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoAgosto() {
	
		return indiceConsumo5m3DivididoConsumoMedidoAgosto;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoDezembro() {
	
		return indiceConsumo5m3DivididoConsumoMedidoDezembro;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoFevereiro() {
	
		return indiceConsumo5m3DivididoConsumoMedidoFevereiro;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoJaneiro() {
	
		return indiceConsumo5m3DivididoConsumoMedidoJaneiro;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoJulho() {
	
		return indiceConsumo5m3DivididoConsumoMedidoJulho;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoJunho() {
	
		return indiceConsumo5m3DivididoConsumoMedidoJunho;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoMaio() {
	
		return indiceConsumo5m3DivididoConsumoMedidoMaio;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoMarco() {
	
		return indiceConsumo5m3DivididoConsumoMedidoMarco;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoNovembro() {
	
		return indiceConsumo5m3DivididoConsumoMedidoNovembro;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoOutubro() {
	
		return indiceConsumo5m3DivididoConsumoMedidoOutubro;
	}


	
	public String getIndiceConsumo5m3DivididoConsumoMedidoSetembro() {
	
		return indiceConsumo5m3DivididoConsumoMedidoSetembro;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoAbril() {
	
		return indiceConsumoMediaDivididoConsumoMedidoAbril;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoAgosto() {
	
		return indiceConsumoMediaDivididoConsumoMedidoAgosto;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoDezembro() {
	
		return indiceConsumoMediaDivididoConsumoMedidoDezembro;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoFevereiro() {
	
		return indiceConsumoMediaDivididoConsumoMedidoFevereiro;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoJaneiro() {
	
		return indiceConsumoMediaDivididoConsumoMedidoJaneiro;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoJulho() {
	
		return indiceConsumoMediaDivididoConsumoMedidoJulho;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoJunho() {
	
		return indiceConsumoMediaDivididoConsumoMedidoJunho;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoMaio() {
	
		return indiceConsumoMediaDivididoConsumoMedidoMaio;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoMarco() {
	
		return indiceConsumoMediaDivididoConsumoMedidoMarco;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoNovembro() {
	
		return indiceConsumoMediaDivididoConsumoMedidoNovembro;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoOutubro() {
	
		return indiceConsumoMediaDivididoConsumoMedidoOutubro;
	}


	
	public String getIndiceConsumoMediaDivididoConsumoMedidoSetembro() {
	
		return indiceConsumoMediaDivididoConsumoMedidoSetembro;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoAbril() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoAbril;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoAgosto() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoAgosto;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoDezembro() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoDezembro;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoFevereiro() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoFevereiro;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoJaneiro() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoJaneiro;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoJulho() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoJulho;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoJunho() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoJunho;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoMaio() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoMaio;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoMarco() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoMarco;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoNovembro() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoNovembro;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoOutubro() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoOutubro;
	}


	
	public String getIndiceConsumoNaoMedidoDivididoConsumoMedidoSetembro() {
	
		return indiceConsumoNaoMedidoDivididoConsumoMedidoSetembro;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasAbril() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasAbril;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasAgosto() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasAgosto;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasDezembro() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasDezembro;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasFevereiro() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasFevereiro;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasJaneiro() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasJaneiro;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasJulho() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasJulho;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasJunho() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasJunho;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasMaio() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasMaio;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasMarco() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasMarco;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasNovembro() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasNovembro;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasOutubro() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasOutubro;
	}


	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasSetembro() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasSetembro;
	}


	
	public String getIndiceSuprimidaCadastradasAbril() {
	
		return indiceSuprimidaCadastradasAbril;
	}


	
	public String getIndiceSuprimidaCadastradasAgosto() {
	
		return indiceSuprimidaCadastradasAgosto;
	}


	
	public String getIndiceSuprimidaCadastradasDezembro() {
	
		return indiceSuprimidaCadastradasDezembro;
	}


	
	public String getIndiceSuprimidaCadastradasFevereiro() {
	
		return indiceSuprimidaCadastradasFevereiro;
	}


	
	public String getIndiceSuprimidaCadastradasJaneiro() {
	
		return indiceSuprimidaCadastradasJaneiro;
	}


	
	public String getIndiceSuprimidaCadastradasJulho() {
	
		return indiceSuprimidaCadastradasJulho;
	}


	
	public String getIndiceSuprimidaCadastradasJunho() {
	
		return indiceSuprimidaCadastradasJunho;
	}


	
	public String getIndiceSuprimidaCadastradasMaio() {
	
		return indiceSuprimidaCadastradasMaio;
	}


	
	public String getIndiceSuprimidaCadastradasMarco() {
	
		return indiceSuprimidaCadastradasMarco;
	}


	
	public String getIndiceSuprimidaCadastradasNovembro() {
	
		return indiceSuprimidaCadastradasNovembro;
	}


	
	public String getIndiceSuprimidaCadastradasOutubro() {
	
		return indiceSuprimidaCadastradasOutubro;
	}


	
	public String getIndiceSuprimidaCadastradasSetembro() {
	
		return indiceSuprimidaCadastradasSetembro;
	}


	
	public String getNomeCentroCusto() {
	
		return nomeCentroCusto;
	}


	
	public String getNomeGerenciaRegional() {
	
		return nomeGerenciaRegional;
	}


	
	public String getNomeLocalidade() {
	
		return nomeLocalidade;
	}


	
	public String getNomeUnidadeNegocio() {
	
		return nomeUnidadeNegocio;
	}


	
	public String getOpcaoTotalizacao() {
	
		return opcaoTotalizacao;
	}


	
	public String getQuantidadeEconomiasAbril() {
	
		return quantidadeEconomiasAbril;
	}


	
	public String getQuantidadeEconomiasAgosto() {
	
		return quantidadeEconomiasAgosto;
	}


	
	public String getQuantidadeEconomiasDezembro() {
	
		return quantidadeEconomiasDezembro;
	}


	
	public String getQuantidadeEconomiasFevereiro() {
	
		return quantidadeEconomiasFevereiro;
	}


	
	public String getQuantidadeEconomiasJaneiro() {
	
		return quantidadeEconomiasJaneiro;
	}


	
	public String getQuantidadeEconomiasJulho() {
	
		return quantidadeEconomiasJulho;
	}


	
	public String getQuantidadeEconomiasJunho() {
	
		return quantidadeEconomiasJunho;
	}


	
	public String getQuantidadeEconomiasMaio() {
	
		return quantidadeEconomiasMaio;
	}


	
	public String getQuantidadeEconomiasMarco() {
	
		return quantidadeEconomiasMarco;
	}


	
	public String getQuantidadeEconomiasNovembro() {
	
		return quantidadeEconomiasNovembro;
	}


	
	public String getQuantidadeEconomiasOutubro() {
	
		return quantidadeEconomiasOutubro;
	}


	
	public String getQuantidadeEconomiasSetembro() {
	
		return quantidadeEconomiasSetembro;
	}


	
	public String getQuantidadeLigacoesAtivasAbril() {
	
		return quantidadeLigacoesAtivasAbril;
	}


	
	public String getQuantidadeLigacoesAtivasAgosto() {
	
		return quantidadeLigacoesAtivasAgosto;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mAbril() {
	
		return quantidadeLigacoesAtivasDebitos3mAbril;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mAgosto() {
	
		return quantidadeLigacoesAtivasDebitos3mAgosto;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mDezembro() {
	
		return quantidadeLigacoesAtivasDebitos3mDezembro;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mFevereiro() {
	
		return quantidadeLigacoesAtivasDebitos3mFevereiro;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mJaneiro() {
	
		return quantidadeLigacoesAtivasDebitos3mJaneiro;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mJulho() {
	
		return quantidadeLigacoesAtivasDebitos3mJulho;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mJunho() {
	
		return quantidadeLigacoesAtivasDebitos3mJunho;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mMaio() {
	
		return quantidadeLigacoesAtivasDebitos3mMaio;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mMarco() {
	
		return quantidadeLigacoesAtivasDebitos3mMarco;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mNovembro() {
	
		return quantidadeLigacoesAtivasDebitos3mNovembro;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mOutubro() {
	
		return quantidadeLigacoesAtivasDebitos3mOutubro;
	}


	
	public String getQuantidadeLigacoesAtivasDebitos3mSetembro() {
	
		return quantidadeLigacoesAtivasDebitos3mSetembro;
	}


	
	public String getQuantidadeLigacoesAtivasDezembro() {
	
		return quantidadeLigacoesAtivasDezembro;
	}


	
	public String getQuantidadeLigacoesAtivasFevereiro() {
	
		return quantidadeLigacoesAtivasFevereiro;
	}


	
	public String getQuantidadeLigacoesAtivasJaneiro() {
	
		return quantidadeLigacoesAtivasJaneiro;
	}


	
	public String getQuantidadeLigacoesAtivasJulho() {
	
		return quantidadeLigacoesAtivasJulho;
	}


	
	public String getQuantidadeLigacoesAtivasJunho() {
	
		return quantidadeLigacoesAtivasJunho;
	}


	
	public String getQuantidadeLigacoesAtivasMaio() {
	
		return quantidadeLigacoesAtivasMaio;
	}


	
	public String getQuantidadeLigacoesAtivasMarco() {
	
		return quantidadeLigacoesAtivasMarco;
	}


	
	public String getQuantidadeLigacoesAtivasNovembro() {
	
		return quantidadeLigacoesAtivasNovembro;
	}


	
	public String getQuantidadeLigacoesAtivasOutubro() {
	
		return quantidadeLigacoesAtivasOutubro;
	}


	
	public String getQuantidadeLigacoesAtivasSetembro() {
	
		return quantidadeLigacoesAtivasSetembro;
	}


	
	public String getQuantidadeLigacoesCadastradasAbril() {
	
		return quantidadeLigacoesCadastradasAbril;
	}


	
	public String getQuantidadeLigacoesCadastradasAgosto() {
	
		return quantidadeLigacoesCadastradasAgosto;
	}


	
	public String getQuantidadeLigacoesCadastradasDezembro() {
	
		return quantidadeLigacoesCadastradasDezembro;
	}


	
	public String getQuantidadeLigacoesCadastradasFevereiro() {
	
		return quantidadeLigacoesCadastradasFevereiro;
	}


	
	public String getQuantidadeLigacoesCadastradasJaneiro() {
	
		return quantidadeLigacoesCadastradasJaneiro;
	}


	
	public String getQuantidadeLigacoesCadastradasJulho() {
	
		return quantidadeLigacoesCadastradasJulho;
	}


	
	public String getQuantidadeLigacoesCadastradasJunho() {
	
		return quantidadeLigacoesCadastradasJunho;
	}


	
	public String getQuantidadeLigacoesCadastradasMaio() {
	
		return quantidadeLigacoesCadastradasMaio;
	}


	
	public String getQuantidadeLigacoesCadastradasMarco() {
	
		return quantidadeLigacoesCadastradasMarco;
	}


	
	public String getQuantidadeLigacoesCadastradasNovembro() {
	
		return quantidadeLigacoesCadastradasNovembro;
	}


	
	public String getQuantidadeLigacoesCadastradasOutubro() {
	
		return quantidadeLigacoesCadastradasOutubro;
	}


	
	public String getQuantidadeLigacoesCadastradasSetembro() {
	
		return quantidadeLigacoesCadastradasSetembro;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Abril() {
	
		return quantidadeLigacoesConsumo5m3Abril;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Agosto() {
	
		return quantidadeLigacoesConsumo5m3Agosto;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Dezembro() {
	
		return quantidadeLigacoesConsumo5m3Dezembro;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Fevereiro() {
	
		return quantidadeLigacoesConsumo5m3Fevereiro;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Janeiro() {
	
		return quantidadeLigacoesConsumo5m3Janeiro;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Julho() {
	
		return quantidadeLigacoesConsumo5m3Julho;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Junho() {
	
		return quantidadeLigacoesConsumo5m3Junho;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Maio() {
	
		return quantidadeLigacoesConsumo5m3Maio;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Marco() {
	
		return quantidadeLigacoesConsumo5m3Marco;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Novembro() {
	
		return quantidadeLigacoesConsumo5m3Novembro;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Outubro() {
	
		return quantidadeLigacoesConsumo5m3Outubro;
	}


	
	public String getQuantidadeLigacoesConsumo5m3Setembro() {
	
		return quantidadeLigacoesConsumo5m3Setembro;
	}


	
	public String getQuantidadeLigacoesConsumoMediaAbril() {
	
		return quantidadeLigacoesConsumoMediaAbril;
	}


	
	public String getQuantidadeLigacoesConsumoMediaAgosto() {
	
		return quantidadeLigacoesConsumoMediaAgosto;
	}


	
	public String getQuantidadeLigacoesConsumoMediaDezembro() {
	
		return quantidadeLigacoesConsumoMediaDezembro;
	}


	
	public String getQuantidadeLigacoesConsumoMediaFevereiro() {
	
		return quantidadeLigacoesConsumoMediaFevereiro;
	}


	
	public String getQuantidadeLigacoesConsumoMediaJaneiro() {
	
		return quantidadeLigacoesConsumoMediaJaneiro;
	}


	
	public String getQuantidadeLigacoesConsumoMediaJulho() {
	
		return quantidadeLigacoesConsumoMediaJulho;
	}


	
	public String getQuantidadeLigacoesConsumoMediaJunho() {
	
		return quantidadeLigacoesConsumoMediaJunho;
	}


	
	public String getQuantidadeLigacoesConsumoMediaMaio() {
	
		return quantidadeLigacoesConsumoMediaMaio;
	}


	
	public String getQuantidadeLigacoesConsumoMediaMarco() {
	
		return quantidadeLigacoesConsumoMediaMarco;
	}


	
	public String getQuantidadeLigacoesConsumoMediaNovembro() {
	
		return quantidadeLigacoesConsumoMediaNovembro;
	}


	
	public String getQuantidadeLigacoesConsumoMediaOutubro() {
	
		return quantidadeLigacoesConsumoMediaOutubro;
	}


	
	public String getQuantidadeLigacoesConsumoMediaSetembro() {
	
		return quantidadeLigacoesConsumoMediaSetembro;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoAbril() {
	
		return quantidadeLigacoesConsumoMedidoAbril;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoAgosto() {
	
		return quantidadeLigacoesConsumoMedidoAgosto;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoDezembro() {
	
		return quantidadeLigacoesConsumoMedidoDezembro;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoFevereiro() {
	
		return quantidadeLigacoesConsumoMedidoFevereiro;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoJaneiro() {
	
		return quantidadeLigacoesConsumoMedidoJaneiro;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoJulho() {
	
		return quantidadeLigacoesConsumoMedidoJulho;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoJunho() {
	
		return quantidadeLigacoesConsumoMedidoJunho;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoMaio() {
	
		return quantidadeLigacoesConsumoMedidoMaio;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoMarco() {
	
		return quantidadeLigacoesConsumoMedidoMarco;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoNovembro() {
	
		return quantidadeLigacoesConsumoMedidoNovembro;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoOutubro() {
	
		return quantidadeLigacoesConsumoMedidoOutubro;
	}


	
	public String getQuantidadeLigacoesConsumoMedidoSetembro() {
	
		return quantidadeLigacoesConsumoMedidoSetembro;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoAbril() {
	
		return quantidadeLigacoesConsumoNaoMedidoAbril;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoAgosto() {
	
		return quantidadeLigacoesConsumoNaoMedidoAgosto;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoDezembro() {
	
		return quantidadeLigacoesConsumoNaoMedidoDezembro;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoFevereiro() {
	
		return quantidadeLigacoesConsumoNaoMedidoFevereiro;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoJaneiro() {
	
		return quantidadeLigacoesConsumoNaoMedidoJaneiro;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoJulho() {
	
		return quantidadeLigacoesConsumoNaoMedidoJulho;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoJunho() {
	
		return quantidadeLigacoesConsumoNaoMedidoJunho;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoMaio() {
	
		return quantidadeLigacoesConsumoNaoMedidoMaio;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoMarco() {
	
		return quantidadeLigacoesConsumoNaoMedidoMarco;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoNovembro() {
	
		return quantidadeLigacoesConsumoNaoMedidoNovembro;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoOutubro() {
	
		return quantidadeLigacoesConsumoNaoMedidoOutubro;
	}


	
	public String getQuantidadeLigacoesConsumoNaoMedidoSetembro() {
	
		return quantidadeLigacoesConsumoNaoMedidoSetembro;
	}


	
	public String getQuantidadeLigacoesCortadasAbril() {
	
		return quantidadeLigacoesCortadasAbril;
	}


	
	public String getQuantidadeLigacoesCortadasAgosto() {
	
		return quantidadeLigacoesCortadasAgosto;
	}


	
	public String getQuantidadeLigacoesCortadasDezembro() {
	
		return quantidadeLigacoesCortadasDezembro;
	}


	
	public String getQuantidadeLigacoesCortadasFevereiro() {
	
		return quantidadeLigacoesCortadasFevereiro;
	}


	
	public String getQuantidadeLigacoesCortadasJaneiro() {
	
		return quantidadeLigacoesCortadasJaneiro;
	}


	
	public String getQuantidadeLigacoesCortadasJulho() {
	
		return quantidadeLigacoesCortadasJulho;
	}


	
	public String getQuantidadeLigacoesCortadasJunho() {
	
		return quantidadeLigacoesCortadasJunho;
	}


	
	public String getQuantidadeLigacoesCortadasMaio() {
	
		return quantidadeLigacoesCortadasMaio;
	}


	
	public String getQuantidadeLigacoesCortadasMarco() {
	
		return quantidadeLigacoesCortadasMarco;
	}


	
	public String getQuantidadeLigacoesCortadasNovembro() {
	
		return quantidadeLigacoesCortadasNovembro;
	}


	
	public String getQuantidadeLigacoesCortadasOutubro() {
	
		return quantidadeLigacoesCortadasOutubro;
	}


	
	public String getQuantidadeLigacoesCortadasSetembro() {
	
		return quantidadeLigacoesCortadasSetembro;
	}


	
	public String getQuantidadeLigacoesSuprimidasAbril() {
	
		return quantidadeLigacoesSuprimidasAbril;
	}


	
	public String getQuantidadeLigacoesSuprimidasAgosto() {
	
		return quantidadeLigacoesSuprimidasAgosto;
	}


	
	public String getQuantidadeLigacoesSuprimidasDezembro() {
	
		return quantidadeLigacoesSuprimidasDezembro;
	}


	
	public String getQuantidadeLigacoesSuprimidasFevereiro() {
	
		return quantidadeLigacoesSuprimidasFevereiro;
	}


	
	public String getQuantidadeLigacoesSuprimidasJaneiro() {
	
		return quantidadeLigacoesSuprimidasJaneiro;
	}


	
	public String getQuantidadeLigacoesSuprimidasJulho() {
	
		return quantidadeLigacoesSuprimidasJulho;
	}


	
	public String getQuantidadeLigacoesSuprimidasJunho() {
	
		return quantidadeLigacoesSuprimidasJunho;
	}


	
	public String getQuantidadeLigacoesSuprimidasMaio() {
	
		return quantidadeLigacoesSuprimidasMaio;
	}


	
	public String getQuantidadeLigacoesSuprimidasMarco() {
	
		return quantidadeLigacoesSuprimidasMarco;
	}


	
	public String getQuantidadeLigacoesSuprimidasNovembro() {
	
		return quantidadeLigacoesSuprimidasNovembro;
	}


	
	public String getQuantidadeLigacoesSuprimidasOutubro() {
	
		return quantidadeLigacoesSuprimidasOutubro;
	}


	
	public String getQuantidadeLigacoesSuprimidasSetembro() {
	
		return quantidadeLigacoesSuprimidasSetembro;
	}
}


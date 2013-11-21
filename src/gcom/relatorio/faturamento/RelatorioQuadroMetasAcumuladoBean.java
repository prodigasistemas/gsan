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

import gcom.gerencial.bean.QuadroMetasAcumuladoHelper;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * classe responsável por criar o relatório de Quadro de Metas Acumulado
 * 
 * @author Bruno Barros
 * @created 27/12/2007
 */
public class RelatorioQuadroMetasAcumuladoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String opcaoTotalizacao;
	private String nomeGerenciaRegional;
	private String nomeUnidadeNegocio;
	private String nomeLocalidade;
	private String nomeCentroCusto;
	
	// Ligacoes Cadastradas
	private String quantidadeLigacoesCadastradasSubcategoria101;
	private String quantidadeLigacoesCadastradasSubcategoria102;
	private String quantidadeLigacoesCadastradasSubcategoria103;
	private String quantidadeLigacoesCadastradasSubcategoria200;
	private String quantidadeLigacoesCadastradasSubcategoria300;
	private String quantidadeLigacoesCadastradasSubcategoria400;
	private String quantidadeLigacoesCadastradasSubcategoria116;
	private String quantidadeLigacoesCadastradasSubcategoria115;
	private String quantidadeLigacoesCadastradas;
	
	// Ligacoes Cortadas
	private String quantidadeLigacoesCortadasSubcategoria101;
	private String quantidadeLigacoesCortadasSubcategoria102;
	private String quantidadeLigacoesCortadasSubcategoria103;
	private String quantidadeLigacoesCortadasSubcategoria200;
	private String quantidadeLigacoesCortadasSubcategoria300;
	private String quantidadeLigacoesCortadasSubcategoria400;
	private String quantidadeLigacoesCortadasSubcategoria116;
	private String quantidadeLigacoesCortadasSubcategoria115;
	private String quantidadeLigacoesCortadas;
	
	// Ligacoes Suprimidas
	private String quantidadeLigacoesSuprimidasSubcategoria101;
	private String quantidadeLigacoesSuprimidasSubcategoria102;
	private String quantidadeLigacoesSuprimidasSubcategoria103;
	private String quantidadeLigacoesSuprimidasSubcategoria200;
	private String quantidadeLigacoesSuprimidasSubcategoria300;
	private String quantidadeLigacoesSuprimidasSubcategoria400;
	private String quantidadeLigacoesSuprimidasSubcategoria116;
	private String quantidadeLigacoesSuprimidasSubcategoria115;
	private String quantidadeLigacoesSuprimidas;
	
	// Ligacoes Ativas
	private String quantidadeLigacoesAtivasSubcategoria101;
	private String quantidadeLigacoesAtivasSubcategoria102;
	private String quantidadeLigacoesAtivasSubcategoria103;
	private String quantidadeLigacoesAtivasSubcategoria200;
	private String quantidadeLigacoesAtivasSubcategoria300;
	private String quantidadeLigacoesAtivasSubcategoria400;
	private String quantidadeLigacoesAtivasSubcategoria116;
	private String quantidadeLigacoesAtivasSubcategoria115;
	private String quantidadeLigacoesAtivas;
	
	// Ligacoes Ativas com débitos a mais de 3 meses
	private String quantidadeLigacoesAtivasDebitos3mSubcategoria101;
	private String quantidadeLigacoesAtivasDebitos3mSubcategoria102;
	private String quantidadeLigacoesAtivasDebitos3mSubcategoria103;
	private String quantidadeLigacoesAtivasDebitos3mSubcategoria200;
	private String quantidadeLigacoesAtivasDebitos3mSubcategoria300;
	private String quantidadeLigacoesAtivasDebitos3mSubcategoria400;
	private String quantidadeLigacoesAtivasDebitos3mSubcategoria116;
	private String quantidadeLigacoesAtivasDebitos3mSubcategoria115;
	private String quantidadeLigacoesAtivasDebitos3m;
	
	// Ligacoes Consumo Medido
	private String quantidadeLigacoesConsumoMedidoSubcategoria101;
	private String quantidadeLigacoesConsumoMedidoSubcategoria102;
	private String quantidadeLigacoesConsumoMedidoSubcategoria103;
	private String quantidadeLigacoesConsumoMedidoSubcategoria200;
	private String quantidadeLigacoesConsumoMedidoSubcategoria300;
	private String quantidadeLigacoesConsumoMedidoSubcategoria400;
	private String quantidadeLigacoesConsumoMedidoSubcategoria116;
	private String quantidadeLigacoesConsumoMedidoSubcategoria115;
	private String quantidadeLigacoesConsumoMedido;
	
	// Ligacoes Consumo 5m3
	private String quantidadeLigacoesConsumo5m3Subcategoria101;
	private String quantidadeLigacoesConsumo5m3Subcategoria102;
	private String quantidadeLigacoesConsumo5m3Subcategoria103;
	private String quantidadeLigacoesConsumo5m3Subcategoria200;
	private String quantidadeLigacoesConsumo5m3Subcategoria300;
	private String quantidadeLigacoesConsumo5m3Subcategoria400;
	private String quantidadeLigacoesConsumo5m3Subcategoria116;
	private String quantidadeLigacoesConsumo5m3Subcategoria115;
	private String quantidadeLigacoesConsumo5m3;
	
	// Ligacoes Consumo Não Medido
	private String quantidadeLigacoesConsumoNaoMedidoSubcategoria101;
	private String quantidadeLigacoesConsumoNaoMedidoSubcategoria102;
	private String quantidadeLigacoesConsumoNaoMedidoSubcategoria103;
	private String quantidadeLigacoesConsumoNaoMedidoSubcategoria200;
	private String quantidadeLigacoesConsumoNaoMedidoSubcategoria300;
	private String quantidadeLigacoesConsumoNaoMedidoSubcategoria400;
	private String quantidadeLigacoesConsumoNaoMedidoSubcategoria116;
	private String quantidadeLigacoesConsumoNaoMedidoSubcategoria115;
	private String quantidadeLigacoesConsumoNaoMedido;
	
	// Ligacoes Consumo Medio
	private String quantidadeLigacoesConsumoMediaSubcategoria101;
	private String quantidadeLigacoesConsumoMediaSubcategoria102;
	private String quantidadeLigacoesConsumoMediaSubcategoria103;
	private String quantidadeLigacoesConsumoMediaSubcategoria200;
	private String quantidadeLigacoesConsumoMediaSubcategoria300;
	private String quantidadeLigacoesConsumoMediaSubcategoria400;
	private String quantidadeLigacoesConsumoMediaSubcategoria116;
	private String quantidadeLigacoesConsumoMediaSubcategoria115;
	private String quantidadeLigacoesConsumoMedia;
	
	// Ligacoes quantidade Economias
	private String quantidadeEconomiasSubcategoria101;
	private String quantidadeEconomiasSubcategoria102;
	private String quantidadeEconomiasSubcategoria103;
	private String quantidadeEconomiasSubcategoria200;
	private String quantidadeEconomiasSubcategoria300;
	private String quantidadeEconomiasSubcategoria400;
	private String quantidadeEconomiasSubcategoria116;
	private String quantidadeEconomiasSubcategoria115;
	private String quantidadeEconomias;
	
	// Calculos do indice de ( Cortadas + Suprimidas ) / Cadastradas	
	public String indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria101;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria102;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria103;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria200;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria300;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria400;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria116;
	public String indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria115;
	public String indiceCortadaMaisSuprimidaDivididoCadastradas;
	
	// Calculamos os indices de Ativas com débitos a mais de 3 meses / ativas
	public String indiceAtivasDebitos3mDividoAtivasSubcategoria101;
	public String indiceAtivasDebitos3mDividoAtivasSubcategoria102;
	public String indiceAtivasDebitos3mDividoAtivasSubcategoria103;
	public String indiceAtivasDebitos3mDividoAtivasSubcategoria200;
	public String indiceAtivasDebitos3mDividoAtivasSubcategoria300;
	public String indiceAtivasDebitos3mDividoAtivasSubcategoria400;
	public String indiceAtivasDebitos3mDividoAtivasSubcategoria116;
	public String indiceAtivasDebitos3mDividoAtivasSubcategoria115;
	public String indiceAtivasDebitos3mDividoAtivas;
	
	// Calculamos o indice de consumo 5m3 / consumo média
	public String indiceConsumo5m3DividoConsumoMedidoSubcategoria101;
	public String indiceConsumo5m3DividoConsumoMedidoSubcategoria102;
	public String indiceConsumo5m3DividoConsumoMedidoSubcategoria103;
	public String indiceConsumo5m3DividoConsumoMedidoSubcategoria200;
	public String indiceConsumo5m3DividoConsumoMedidoSubcategoria300;
	public String indiceConsumo5m3DividoConsumoMedidoSubcategoria400;
	public String indiceConsumo5m3DividoConsumoMedidoSubcategoria116;
	public String indiceConsumo5m3DividoConsumoMedidoSubcategoria115;
	public String indiceConsumo5m3DividoConsumoMedido;
	
	// Calculamos o consumo nao medido / consumo medido ativo
	public String indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria101;
	public String indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria102;
	public String indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria103;
	public String indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria200;
	public String indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria300;
	public String indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria400;
	public String indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria116;
	public String indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria115;
	public String indiceConsumoNaoMedidoDividoConsumoMedido;
	
	// Calculamos o indice de consumo medido / consumo medido ativo
	public String indiceConsumoMediaDivididoConsumoMedidoSubcategoria101;
	public String indiceConsumoMediaDivididoConsumoMedidoSubcategoria102;
	public String indiceConsumoMediaDivididoConsumoMedidoSubcategoria103;
	public String indiceConsumoMediaDivididoConsumoMedidoSubcategoria200;
	public String indiceConsumoMediaDivididoConsumoMedidoSubcategoria300;
	public String indiceConsumoMediaDivididoConsumoMedidoSubcategoria400;
	public String indiceConsumoMediaDivididoConsumoMedidoSubcategoria116;
	public String indiceConsumoMediaDivididoConsumoMedidoSubcategoria115;
	public String indiceConsumoMediaDivididoConsumoMedido;
	
	public String indiceSuprimidaCadastradasSubcategoria101;
	public String indiceSuprimidaCadastradasSubcategoria102;
	public String indiceSuprimidaCadastradasSubcategoria103;
	public String indiceSuprimidaCadastradasSubcategoria200;
	public String indiceSuprimidaCadastradasSubcategoria300;
	public String indiceSuprimidaCadastradasSubcategoria400;
	public String indiceSuprimidaCadastradasSubcategoria116;
	public String indiceSuprimidaCadastradasSubcategoria115;
	public String indiceSuprimidaCadastradas;
	
	public RelatorioQuadroMetasAcumuladoBean(QuadroMetasAcumuladoHelper quadro) {
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
		this.quantidadeLigacoesCadastradasSubcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasSubcategoria101() );
		this.quantidadeLigacoesCadastradasSubcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasSubcategoria102() );
		this.quantidadeLigacoesCadastradasSubcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasSubcategoria103() );
		this.quantidadeLigacoesCadastradasSubcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasSubcategoria200() );
		this.quantidadeLigacoesCadastradasSubcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasSubcategoria300() );
		this.quantidadeLigacoesCadastradasSubcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasSubcategoria400() );
		this.quantidadeLigacoesCadastradasSubcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasSubcategoria116() );
		this.quantidadeLigacoesCadastradasSubcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradasSubcategoria115() );
		this.quantidadeLigacoesCadastradas = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCadastradas() );
		
		// Ligacoes Cortadas
		this.quantidadeLigacoesCortadasSubcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasSubcategoria101() );
		this.quantidadeLigacoesCortadasSubcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasSubcategoria102() );
		this.quantidadeLigacoesCortadasSubcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasSubcategoria103() );
		this.quantidadeLigacoesCortadasSubcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasSubcategoria200() );
		this.quantidadeLigacoesCortadasSubcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasSubcategoria300() );
		this.quantidadeLigacoesCortadasSubcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasSubcategoria400() );
		this.quantidadeLigacoesCortadasSubcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasSubcategoria116() );
		this.quantidadeLigacoesCortadasSubcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadasSubcategoria115() );
		this.quantidadeLigacoesCortadas = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesCortadas() );
		
		// Ligacoes Suprimidas
		this.quantidadeLigacoesSuprimidasSubcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasSubcategoria101() );
		this.quantidadeLigacoesSuprimidasSubcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasSubcategoria102() );
		this.quantidadeLigacoesSuprimidasSubcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasSubcategoria103() );
		this.quantidadeLigacoesSuprimidasSubcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasSubcategoria200() );
		this.quantidadeLigacoesSuprimidasSubcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasSubcategoria300() );
		this.quantidadeLigacoesSuprimidasSubcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasSubcategoria400() );
		this.quantidadeLigacoesSuprimidasSubcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasSubcategoria116() );
		this.quantidadeLigacoesSuprimidasSubcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidasSubcategoria115() );
		this.quantidadeLigacoesSuprimidas = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesSuprimidas() );
		
		// Ligacoes Ativas
		this.quantidadeLigacoesAtivasSubcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasSubcategoria101() );
		this.quantidadeLigacoesAtivasSubcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasSubcategoria102() );
		this.quantidadeLigacoesAtivasSubcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasSubcategoria103() );
		this.quantidadeLigacoesAtivasSubcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasSubcategoria200() );
		this.quantidadeLigacoesAtivasSubcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasSubcategoria300() );
		this.quantidadeLigacoesAtivasSubcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasSubcategoria400() );
		this.quantidadeLigacoesAtivasSubcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasSubcategoria116() );
		this.quantidadeLigacoesAtivasSubcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasSubcategoria115() );
		this.quantidadeLigacoesAtivas = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivas() );
		
		// Ligacoes Ativas com débitos a mais de 3 meses
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mSubcategoria101() );
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mSubcategoria102() );
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mSubcategoria103() );
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mSubcategoria200() );
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mSubcategoria300() );
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mSubcategoria400() );
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mSubcategoria116() );
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3mSubcategoria115() );
		this.quantidadeLigacoesAtivasDebitos3m = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesAtivasDebitos3m() );
		
		// Ligacoes Consumo Medido
		this.quantidadeLigacoesConsumoMedidoSubcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoSubcategoria101() );
		this.quantidadeLigacoesConsumoMedidoSubcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoSubcategoria102() );
		this.quantidadeLigacoesConsumoMedidoSubcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoSubcategoria103() );
		this.quantidadeLigacoesConsumoMedidoSubcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoSubcategoria200() );
		this.quantidadeLigacoesConsumoMedidoSubcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoSubcategoria300() );
		this.quantidadeLigacoesConsumoMedidoSubcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoSubcategoria400() );
		this.quantidadeLigacoesConsumoMedidoSubcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoSubcategoria116() );
		this.quantidadeLigacoesConsumoMedidoSubcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedidoSubcategoria115() );
		this.quantidadeLigacoesConsumoMedido = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedido() );
		
		// Ligacoes Consumo 5m3
		this.quantidadeLigacoesConsumo5m3Subcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Subcategoria101() );
		this.quantidadeLigacoesConsumo5m3Subcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Subcategoria102() );
		this.quantidadeLigacoesConsumo5m3Subcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Subcategoria103() );
		this.quantidadeLigacoesConsumo5m3Subcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Subcategoria200() );
		this.quantidadeLigacoesConsumo5m3Subcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Subcategoria300() );
		this.quantidadeLigacoesConsumo5m3Subcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Subcategoria400() );
		this.quantidadeLigacoesConsumo5m3Subcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Subcategoria116() );
		this.quantidadeLigacoesConsumo5m3Subcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3Subcategoria115() );
		this.quantidadeLigacoesConsumo5m3 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumo5m3() );
		
		// Ligacoes Consumo Não Medido
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria101() );
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria102() );
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria103() );
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria200() );
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria300() );
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria400() );
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria116() );
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria115() );
		this.quantidadeLigacoesConsumoNaoMedido = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoNaoMedido() );
		
		// Ligacoes Consumo Medio
		this.quantidadeLigacoesConsumoMediaSubcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaSubcategoria101() );
		this.quantidadeLigacoesConsumoMediaSubcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaSubcategoria102() );
		this.quantidadeLigacoesConsumoMediaSubcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaSubcategoria103() );
		this.quantidadeLigacoesConsumoMediaSubcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaSubcategoria200() );
		this.quantidadeLigacoesConsumoMediaSubcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaSubcategoria300() );
		this.quantidadeLigacoesConsumoMediaSubcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaSubcategoria400() );
		this.quantidadeLigacoesConsumoMediaSubcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaSubcategoria116() );
		this.quantidadeLigacoesConsumoMediaSubcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMediaSubcategoria115() );
		this.quantidadeLigacoesConsumoMedia = Util.agruparNumeroEmMilhares( quadro.getQuantidadeLigacoesConsumoMedia() );
		
		// Ligacoes quantidade Economias
		this.quantidadeEconomiasSubcategoria101 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasSubcategoria101() );
		this.quantidadeEconomiasSubcategoria102 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasSubcategoria102() );
		this.quantidadeEconomiasSubcategoria103 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasSubcategoria103() );
		this.quantidadeEconomiasSubcategoria200 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasSubcategoria200() );
		this.quantidadeEconomiasSubcategoria300 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasSubcategoria300() );
		this.quantidadeEconomiasSubcategoria400 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasSubcategoria400() );
		this.quantidadeEconomiasSubcategoria116 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasSubcategoria116() );
		this.quantidadeEconomiasSubcategoria115 = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomiasSubcategoria115() );
		this.quantidadeEconomias = Util.agruparNumeroEmMilhares( quadro.getQuantidadeEconomias() );
		
		// Calculos do indice de  = Cortadas + Suprimidas ) / Cadastradas	
		this.indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria101 = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria101(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria102 = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria102(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria103 = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria103(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria200 = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria200(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria300 = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria300(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria400 = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria400(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria116 = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria116(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria115 = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria115(), 2, true );
		this.indiceCortadaMaisSuprimidaDivididoCadastradas = Util.formataBigDecimal( quadro.getIndiceCortadaMaisSuprimidaDivididoCadastradas(), 2, true );
		
		// Calculamos os indices de Ativas com débitos a mais de 3 meses / ativas
		this.indiceAtivasDebitos3mDividoAtivasSubcategoria101 = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDividoAtivasSubcategoria101(), 2, true );
		this.indiceAtivasDebitos3mDividoAtivasSubcategoria102 = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDividoAtivasSubcategoria102(), 2, true );
		this.indiceAtivasDebitos3mDividoAtivasSubcategoria103 = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDividoAtivasSubcategoria103(), 2, true );
		this.indiceAtivasDebitos3mDividoAtivasSubcategoria200 = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDividoAtivasSubcategoria200(), 2, true );
		this.indiceAtivasDebitos3mDividoAtivasSubcategoria300 = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDividoAtivasSubcategoria300(), 2, true );
		this.indiceAtivasDebitos3mDividoAtivasSubcategoria400 = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDividoAtivasSubcategoria400(), 2, true );
		this.indiceAtivasDebitos3mDividoAtivasSubcategoria116 = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDividoAtivasSubcategoria116(), 2, true );
		this.indiceAtivasDebitos3mDividoAtivasSubcategoria115 = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDividoAtivasSubcategoria115(), 2, true );
		this.indiceAtivasDebitos3mDividoAtivas = Util.formataBigDecimal( quadro.getIndiceAtivasDebitos3mDividoAtivas(), 2, true );
		
		// Calculamos o indice de consumo 5m3 / consumo média
		this.indiceConsumo5m3DividoConsumoMedidoSubcategoria101 = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DividoConsumoMedidoSubcategoria101(), 2, true );
		this.indiceConsumo5m3DividoConsumoMedidoSubcategoria102 = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DividoConsumoMedidoSubcategoria102(), 2, true );
		this.indiceConsumo5m3DividoConsumoMedidoSubcategoria103 = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DividoConsumoMedidoSubcategoria103(), 2, true );
		this.indiceConsumo5m3DividoConsumoMedidoSubcategoria200 = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DividoConsumoMedidoSubcategoria200(), 2, true );
		this.indiceConsumo5m3DividoConsumoMedidoSubcategoria300 = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DividoConsumoMedidoSubcategoria300(), 2, true );
		this.indiceConsumo5m3DividoConsumoMedidoSubcategoria400 = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DividoConsumoMedidoSubcategoria400(), 2, true );
		this.indiceConsumo5m3DividoConsumoMedidoSubcategoria116 = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DividoConsumoMedidoSubcategoria116(), 2, true );
		this.indiceConsumo5m3DividoConsumoMedidoSubcategoria115 = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DividoConsumoMedidoSubcategoria115(), 2, true );
		this.indiceConsumo5m3DividoConsumoMedido = Util.formataBigDecimal( quadro.getIndiceConsumo5m3DividoConsumoMedido(), 2, true );
		
//		 Calculamos o consumo nao medido / consumo medido ativo
		this.indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria101 = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria101(), 2, true );
		this.indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria102 = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria102(), 2, true );
		this.indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria103 = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria103(), 2, true );
		this.indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria200 = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria200(), 2, true );
		this.indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria300 = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria300(), 2, true );
		this.indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria400 = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria400(), 2, true );
		this.indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria116 = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria116(), 2, true );
		this.indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria115 = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria115(), 2, true );
		this.indiceConsumoNaoMedidoDividoConsumoMedido = Util.formataBigDecimal( quadro.getIndiceConsumoNaoMedidoDividoConsumoMedido(), 2, true );
		
		// Calculamos o indice de consumo medido / consumo medido ativo
		this.indiceConsumoMediaDivididoConsumoMedidoSubcategoria101 = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria101(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoSubcategoria102 = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria102(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoSubcategoria103 = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria103(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoSubcategoria200 = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria200(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoSubcategoria300 = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria300(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoSubcategoria400 = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria400(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoSubcategoria116 = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria116(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedidoSubcategoria115 = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria115(), 2, true );
		this.indiceConsumoMediaDivididoConsumoMedido = Util.formataBigDecimal( quadro.getIndiceConsumoMediaDivididoConsumoMedido(), 2, true );
		
		this.indiceSuprimidaCadastradasSubcategoria101 = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasSubcategoria101(), 2, true );
		this.indiceSuprimidaCadastradasSubcategoria102 = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasSubcategoria102(), 2, true );
		this.indiceSuprimidaCadastradasSubcategoria103 = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasSubcategoria103(), 2, true );
		this.indiceSuprimidaCadastradasSubcategoria200 = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasSubcategoria200(), 2, true );
		this.indiceSuprimidaCadastradasSubcategoria300 = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasSubcategoria300(), 2, true );
		this.indiceSuprimidaCadastradasSubcategoria400 = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasSubcategoria400(), 2, true );
		this.indiceSuprimidaCadastradasSubcategoria116 = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasSubcategoria116(), 2, true );
		this.indiceSuprimidaCadastradasSubcategoria115 = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradasSubcategoria115(), 2, true );
		this.indiceSuprimidaCadastradas = Util.formataBigDecimal( quadro.getIndiceSuprimidaCadastradas(), 2, true );		
	}

	
	public static long getSerialVersionUID() {
	
		return serialVersionUID;
	}

	
	public String getIndiceAtivasDebitos3mDividoAtivas() {
	
		return indiceAtivasDebitos3mDividoAtivas;
	}

	
	public String getIndiceAtivasDebitos3mDividoAtivasSubcategoria101() {
	
		return indiceAtivasDebitos3mDividoAtivasSubcategoria101;
	}

	
	public String getIndiceAtivasDebitos3mDividoAtivasSubcategoria102() {
	
		return indiceAtivasDebitos3mDividoAtivasSubcategoria102;
	}

	
	public String getIndiceAtivasDebitos3mDividoAtivasSubcategoria103() {
	
		return indiceAtivasDebitos3mDividoAtivasSubcategoria103;
	}

	
	public String getIndiceAtivasDebitos3mDividoAtivasSubcategoria115() {
	
		return indiceAtivasDebitos3mDividoAtivasSubcategoria115;
	}

	
	public String getIndiceAtivasDebitos3mDividoAtivasSubcategoria116() {
	
		return indiceAtivasDebitos3mDividoAtivasSubcategoria116;
	}

	
	public String getIndiceAtivasDebitos3mDividoAtivasSubcategoria200() {
	
		return indiceAtivasDebitos3mDividoAtivasSubcategoria200;
	}

	
	public String getIndiceAtivasDebitos3mDividoAtivasSubcategoria300() {
	
		return indiceAtivasDebitos3mDividoAtivasSubcategoria300;
	}

	
	public String getIndiceAtivasDebitos3mDividoAtivasSubcategoria400() {
	
		return indiceAtivasDebitos3mDividoAtivasSubcategoria400;
	}

	
	public String getIndiceConsumo5m3DividoConsumoMedido() {
	
		return indiceConsumo5m3DividoConsumoMedido;
	}

	
	public String getIndiceConsumo5m3DividoConsumoMedidoSubcategoria101() {
	
		return indiceConsumo5m3DividoConsumoMedidoSubcategoria101;
	}

	
	public String getIndiceConsumo5m3DividoConsumoMedidoSubcategoria102() {
	
		return indiceConsumo5m3DividoConsumoMedidoSubcategoria102;
	}

	
	public String getIndiceConsumo5m3DividoConsumoMedidoSubcategoria103() {
	
		return indiceConsumo5m3DividoConsumoMedidoSubcategoria103;
	}

	
	public String getIndiceConsumo5m3DividoConsumoMedidoSubcategoria115() {
	
		return indiceConsumo5m3DividoConsumoMedidoSubcategoria115;
	}

	
	public String getIndiceConsumo5m3DividoConsumoMedidoSubcategoria116() {
	
		return indiceConsumo5m3DividoConsumoMedidoSubcategoria116;
	}

	
	public String getIndiceConsumo5m3DividoConsumoMedidoSubcategoria200() {
	
		return indiceConsumo5m3DividoConsumoMedidoSubcategoria200;
	}

	
	public String getIndiceConsumo5m3DividoConsumoMedidoSubcategoria300() {
	
		return indiceConsumo5m3DividoConsumoMedidoSubcategoria300;
	}

	
	public String getIndiceConsumo5m3DividoConsumoMedidoSubcategoria400() {
	
		return indiceConsumo5m3DividoConsumoMedidoSubcategoria400;
	}

	
	public String getIndiceConsumoMediaDivididoConsumoMedido() {
	
		return indiceConsumoMediaDivididoConsumoMedido;
	}

	
	public String getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria101() {
	
		return indiceConsumoMediaDivididoConsumoMedidoSubcategoria101;
	}

	
	public String getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria102() {
	
		return indiceConsumoMediaDivididoConsumoMedidoSubcategoria102;
	}

	
	public String getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria103() {
	
		return indiceConsumoMediaDivididoConsumoMedidoSubcategoria103;
	}

	
	public String getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria115() {
	
		return indiceConsumoMediaDivididoConsumoMedidoSubcategoria115;
	}

	
	public String getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria116() {
	
		return indiceConsumoMediaDivididoConsumoMedidoSubcategoria116;
	}

	
	public String getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria200() {
	
		return indiceConsumoMediaDivididoConsumoMedidoSubcategoria200;
	}

	
	public String getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria300() {
	
		return indiceConsumoMediaDivididoConsumoMedidoSubcategoria300;
	}

	
	public String getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria400() {
	
		return indiceConsumoMediaDivididoConsumoMedidoSubcategoria400;
	}

	
	public String getIndiceConsumoNaoMedidoDividoConsumoMedido() {
	
		return indiceConsumoNaoMedidoDividoConsumoMedido;
	}

	
	public String getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria101() {
	
		return indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria101;
	}

	
	public String getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria102() {
	
		return indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria102;
	}

	
	public String getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria103() {
	
		return indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria103;
	}

	
	public String getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria115() {
	
		return indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria115;
	}

	
	public String getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria116() {
	
		return indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria116;
	}

	
	public String getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria200() {
	
		return indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria200;
	}

	
	public String getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria300() {
	
		return indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria300;
	}

	
	public String getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria400() {
	
		return indiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria400;
	}

	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradas() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradas;
	}

	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria101() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria101;
	}

	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria102() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria102;
	}

	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria103() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria103;
	}

	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria115() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria115;
	}

	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria116() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria116;
	}

	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria200() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria200;
	}

	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria300() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria300;
	}

	
	public String getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria400() {
	
		return indiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria400;
	}

	
	public String getIndiceSuprimidaCadastradas() {
	
		return indiceSuprimidaCadastradas;
	}

	
	public String getIndiceSuprimidaCadastradasSubcategoria101() {
	
		return indiceSuprimidaCadastradasSubcategoria101;
	}

	
	public String getIndiceSuprimidaCadastradasSubcategoria102() {
	
		return indiceSuprimidaCadastradasSubcategoria102;
	}

	
	public String getIndiceSuprimidaCadastradasSubcategoria103() {
	
		return indiceSuprimidaCadastradasSubcategoria103;
	}

	
	public String getIndiceSuprimidaCadastradasSubcategoria115() {
	
		return indiceSuprimidaCadastradasSubcategoria115;
	}

	
	public String getIndiceSuprimidaCadastradasSubcategoria116() {
	
		return indiceSuprimidaCadastradasSubcategoria116;
	}

	
	public String getIndiceSuprimidaCadastradasSubcategoria200() {
	
		return indiceSuprimidaCadastradasSubcategoria200;
	}

	
	public String getIndiceSuprimidaCadastradasSubcategoria300() {
	
		return indiceSuprimidaCadastradasSubcategoria300;
	}

	
	public String getIndiceSuprimidaCadastradasSubcategoria400() {
	
		return indiceSuprimidaCadastradasSubcategoria400;
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

	
	public String getQuantidadeEconomias() {
	
		return quantidadeEconomias;
	}

	
	public String getQuantidadeEconomiasSubcategoria101() {
	
		return quantidadeEconomiasSubcategoria101;
	}

	
	public String getQuantidadeEconomiasSubcategoria102() {
	
		return quantidadeEconomiasSubcategoria102;
	}

	
	public String getQuantidadeEconomiasSubcategoria103() {
	
		return quantidadeEconomiasSubcategoria103;
	}

	
	public String getQuantidadeEconomiasSubcategoria115() {
	
		return quantidadeEconomiasSubcategoria115;
	}

	
	public String getQuantidadeEconomiasSubcategoria116() {
	
		return quantidadeEconomiasSubcategoria116;
	}

	
	public String getQuantidadeEconomiasSubcategoria200() {
	
		return quantidadeEconomiasSubcategoria200;
	}

	
	public String getQuantidadeEconomiasSubcategoria300() {
	
		return quantidadeEconomiasSubcategoria300;
	}

	
	public String getQuantidadeEconomiasSubcategoria400() {
	
		return quantidadeEconomiasSubcategoria400;
	}

	
	public String getQuantidadeLigacoesAtivas() {
	
		return quantidadeLigacoesAtivas;
	}

	
	public String getQuantidadeLigacoesAtivasDebitos3m() {
	
		return quantidadeLigacoesAtivasDebitos3m;
	}

	
	public String getQuantidadeLigacoesAtivasDebitos3mSubcategoria101() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria101;
	}

	
	public String getQuantidadeLigacoesAtivasDebitos3mSubcategoria102() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria102;
	}

	
	public String getQuantidadeLigacoesAtivasDebitos3mSubcategoria103() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria103;
	}

	
	public String getQuantidadeLigacoesAtivasDebitos3mSubcategoria115() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria115;
	}

	
	public String getQuantidadeLigacoesAtivasDebitos3mSubcategoria116() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria116;
	}

	
	public String getQuantidadeLigacoesAtivasDebitos3mSubcategoria200() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria200;
	}

	
	public String getQuantidadeLigacoesAtivasDebitos3mSubcategoria300() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria300;
	}

	
	public String getQuantidadeLigacoesAtivasDebitos3mSubcategoria400() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria400;
	}

	
	public String getQuantidadeLigacoesAtivasSubcategoria101() {
	
		return quantidadeLigacoesAtivasSubcategoria101;
	}

	
	public String getQuantidadeLigacoesAtivasSubcategoria102() {
	
		return quantidadeLigacoesAtivasSubcategoria102;
	}

	
	public String getQuantidadeLigacoesAtivasSubcategoria103() {
	
		return quantidadeLigacoesAtivasSubcategoria103;
	}

	
	public String getQuantidadeLigacoesAtivasSubcategoria115() {
	
		return quantidadeLigacoesAtivasSubcategoria115;
	}

	
	public String getQuantidadeLigacoesAtivasSubcategoria116() {
	
		return quantidadeLigacoesAtivasSubcategoria116;
	}

	
	public String getQuantidadeLigacoesAtivasSubcategoria200() {
	
		return quantidadeLigacoesAtivasSubcategoria200;
	}

	
	public String getQuantidadeLigacoesAtivasSubcategoria300() {
	
		return quantidadeLigacoesAtivasSubcategoria300;
	}

	
	public String getQuantidadeLigacoesAtivasSubcategoria400() {
	
		return quantidadeLigacoesAtivasSubcategoria400;
	}

	
	public String getQuantidadeLigacoesCadastradas() {
	
		return quantidadeLigacoesCadastradas;
	}

	
	public String getQuantidadeLigacoesCadastradasSubcategoria101() {
	
		return quantidadeLigacoesCadastradasSubcategoria101;
	}

	
	public String getQuantidadeLigacoesCadastradasSubcategoria102() {
	
		return quantidadeLigacoesCadastradasSubcategoria102;
	}

	
	public String getQuantidadeLigacoesCadastradasSubcategoria103() {
	
		return quantidadeLigacoesCadastradasSubcategoria103;
	}

	
	public String getQuantidadeLigacoesCadastradasSubcategoria115() {
	
		return quantidadeLigacoesCadastradasSubcategoria115;
	}

	
	public String getQuantidadeLigacoesCadastradasSubcategoria116() {
	
		return quantidadeLigacoesCadastradasSubcategoria116;
	}

	
	public String getQuantidadeLigacoesCadastradasSubcategoria200() {
	
		return quantidadeLigacoesCadastradasSubcategoria200;
	}

	
	public String getQuantidadeLigacoesCadastradasSubcategoria300() {
	
		return quantidadeLigacoesCadastradasSubcategoria300;
	}

	
	public String getQuantidadeLigacoesCadastradasSubcategoria400() {
	
		return quantidadeLigacoesCadastradasSubcategoria400;
	}

	
	public String getQuantidadeLigacoesConsumo5m3() {
	
		return quantidadeLigacoesConsumo5m3;
	}

	
	public String getQuantidadeLigacoesConsumo5m3Subcategoria101() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria101;
	}

	
	public String getQuantidadeLigacoesConsumo5m3Subcategoria102() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria102;
	}

	
	public String getQuantidadeLigacoesConsumo5m3Subcategoria103() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria103;
	}

	
	public String getQuantidadeLigacoesConsumo5m3Subcategoria115() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria115;
	}

	
	public String getQuantidadeLigacoesConsumo5m3Subcategoria116() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria116;
	}

	
	public String getQuantidadeLigacoesConsumo5m3Subcategoria200() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria200;
	}

	
	public String getQuantidadeLigacoesConsumo5m3Subcategoria300() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria300;
	}

	
	public String getQuantidadeLigacoesConsumo5m3Subcategoria400() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria400;
	}

	
	public String getQuantidadeLigacoesConsumoMedia() {
	
		return quantidadeLigacoesConsumoMedia;
	}

	
	public String getQuantidadeLigacoesConsumoMediaSubcategoria101() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria101;
	}

	
	public String getQuantidadeLigacoesConsumoMediaSubcategoria102() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria102;
	}

	
	public String getQuantidadeLigacoesConsumoMediaSubcategoria103() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria103;
	}

	
	public String getQuantidadeLigacoesConsumoMediaSubcategoria115() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria115;
	}

	
	public String getQuantidadeLigacoesConsumoMediaSubcategoria116() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria116;
	}

	
	public String getQuantidadeLigacoesConsumoMediaSubcategoria200() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria200;
	}

	
	public String getQuantidadeLigacoesConsumoMediaSubcategoria300() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria300;
	}

	
	public String getQuantidadeLigacoesConsumoMediaSubcategoria400() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria400;
	}

	
	public String getQuantidadeLigacoesConsumoMedido() {
	
		return quantidadeLigacoesConsumoMedido;
	}

	
	public String getQuantidadeLigacoesConsumoMedidoSubcategoria101() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria101;
	}

	
	public String getQuantidadeLigacoesConsumoMedidoSubcategoria102() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria102;
	}

	
	public String getQuantidadeLigacoesConsumoMedidoSubcategoria103() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria103;
	}

	
	public String getQuantidadeLigacoesConsumoMedidoSubcategoria115() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria115;
	}

	
	public String getQuantidadeLigacoesConsumoMedidoSubcategoria116() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria116;
	}

	
	public String getQuantidadeLigacoesConsumoMedidoSubcategoria200() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria200;
	}

	
	public String getQuantidadeLigacoesConsumoMedidoSubcategoria300() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria300;
	}

	
	public String getQuantidadeLigacoesConsumoMedidoSubcategoria400() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria400;
	}

	
	public String getQuantidadeLigacoesConsumoNaoMedido() {
	
		return quantidadeLigacoesConsumoNaoMedido;
	}

	
	public String getQuantidadeLigacoesConsumoNaoMedidoSubcategoria101() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria101;
	}

	
	public String getQuantidadeLigacoesConsumoNaoMedidoSubcategoria102() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria102;
	}

	
	public String getQuantidadeLigacoesConsumoNaoMedidoSubcategoria103() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria103;
	}

	
	public String getQuantidadeLigacoesConsumoNaoMedidoSubcategoria115() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria115;
	}

	
	public String getQuantidadeLigacoesConsumoNaoMedidoSubcategoria116() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria116;
	}

	
	public String getQuantidadeLigacoesConsumoNaoMedidoSubcategoria200() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria200;
	}

	
	public String getQuantidadeLigacoesConsumoNaoMedidoSubcategoria300() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria300;
	}

	
	public String getQuantidadeLigacoesConsumoNaoMedidoSubcategoria400() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria400;
	}

	
	public String getQuantidadeLigacoesCortadas() {
	
		return quantidadeLigacoesCortadas;
	}

	
	public String getQuantidadeLigacoesCortadasSubcategoria101() {
	
		return quantidadeLigacoesCortadasSubcategoria101;
	}

	
	public String getQuantidadeLigacoesCortadasSubcategoria102() {
	
		return quantidadeLigacoesCortadasSubcategoria102;
	}

	
	public String getQuantidadeLigacoesCortadasSubcategoria103() {
	
		return quantidadeLigacoesCortadasSubcategoria103;
	}

	
	public String getQuantidadeLigacoesCortadasSubcategoria115() {
	
		return quantidadeLigacoesCortadasSubcategoria115;
	}

	
	public String getQuantidadeLigacoesCortadasSubcategoria116() {
	
		return quantidadeLigacoesCortadasSubcategoria116;
	}

	
	public String getQuantidadeLigacoesCortadasSubcategoria200() {
	
		return quantidadeLigacoesCortadasSubcategoria200;
	}

	
	public String getQuantidadeLigacoesCortadasSubcategoria300() {
	
		return quantidadeLigacoesCortadasSubcategoria300;
	}

	
	public String getQuantidadeLigacoesCortadasSubcategoria400() {
	
		return quantidadeLigacoesCortadasSubcategoria400;
	}

	
	public String getQuantidadeLigacoesSuprimidas() {
	
		return quantidadeLigacoesSuprimidas;
	}

	
	public String getQuantidadeLigacoesSuprimidasSubcategoria101() {
	
		return quantidadeLigacoesSuprimidasSubcategoria101;
	}

	
	public String getQuantidadeLigacoesSuprimidasSubcategoria102() {
	
		return quantidadeLigacoesSuprimidasSubcategoria102;
	}

	
	public String getQuantidadeLigacoesSuprimidasSubcategoria103() {
	
		return quantidadeLigacoesSuprimidasSubcategoria103;
	}

	
	public String getQuantidadeLigacoesSuprimidasSubcategoria115() {
	
		return quantidadeLigacoesSuprimidasSubcategoria115;
	}

	
	public String getQuantidadeLigacoesSuprimidasSubcategoria116() {
	
		return quantidadeLigacoesSuprimidasSubcategoria116;
	}

	
	public String getQuantidadeLigacoesSuprimidasSubcategoria200() {
	
		return quantidadeLigacoesSuprimidasSubcategoria200;
	}

	
	public String getQuantidadeLigacoesSuprimidasSubcategoria300() {
	
		return quantidadeLigacoesSuprimidasSubcategoria300;
	}

	
	public String getQuantidadeLigacoesSuprimidasSubcategoria400() {
	
		return quantidadeLigacoesSuprimidasSubcategoria400;
	}
}


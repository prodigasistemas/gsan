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

public class CalcularValoresAguaEsgotoFaixaHelper {
	
	private Integer idConsumoTarifaFaixa;

	private Integer consumoFaturadoAguaFaixa;

	private BigDecimal valorFaturadoAguaFaixa;

	private Integer consumoFaturadoEsgotoFaixa;

	private BigDecimal valorFaturadoEsgotoFaixa;

	private Integer limiteInicialConsumoFaixa;

	private Integer limiteFinalConsumoFaixa;

	private BigDecimal valorTarifaFaixa;

	public CalcularValoresAguaEsgotoFaixaHelper() {
	}

	public CalcularValoresAguaEsgotoFaixaHelper(
			Integer idConsumoTarifaFaixa,
			Integer consumoFaturadoAguaFaixa,
			BigDecimal valorFaturadoAguaFaixa,
			Integer consumoFaturadoEsgotoFaixa,
			BigDecimal valorFaturadoEsgotoFaixa,
			Integer limiteInicialConsumoFaixa, Integer limiteFinalConsumoFaixa,
			BigDecimal valorTarifaFaixa) {
		this.idConsumoTarifaFaixa = idConsumoTarifaFaixa; 
		this.consumoFaturadoAguaFaixa = consumoFaturadoAguaFaixa;
		this.valorFaturadoAguaFaixa = valorFaturadoAguaFaixa;
		this.consumoFaturadoEsgotoFaixa = consumoFaturadoEsgotoFaixa;
		this.valorFaturadoEsgotoFaixa = valorFaturadoEsgotoFaixa;
		this.limiteInicialConsumoFaixa = limiteInicialConsumoFaixa;
		this.limiteFinalConsumoFaixa = limiteFinalConsumoFaixa;
		this.valorTarifaFaixa = valorTarifaFaixa;
	}

	public Integer getConsumoFaturadoAguaFaixa() {
		return consumoFaturadoAguaFaixa;
	}

	public void setConsumoFaturadoAguaFaixa(Integer consumoFaturadoAguaFaixa) {
		this.consumoFaturadoAguaFaixa = consumoFaturadoAguaFaixa;
	}

	public Integer getConsumoFaturadoEsgotoFaixa() {
		return consumoFaturadoEsgotoFaixa;
	}

	public void setConsumoFaturadoEsgotoFaixa(Integer consumoFaturadoEsgotoFaixa) {
		this.consumoFaturadoEsgotoFaixa = consumoFaturadoEsgotoFaixa;
	}

	public Integer getLimiteFinalConsumoFaixa() {
		return limiteFinalConsumoFaixa;
	}

	public void setLimiteFinalConsumoFaixa(Integer limiteFinalConsumoFaixa) {
		this.limiteFinalConsumoFaixa = limiteFinalConsumoFaixa;
	}

	public Integer getLimiteInicialConsumoFaixa() {
		return limiteInicialConsumoFaixa;
	}

	public void setLimiteInicialConsumoFaixa(Integer limiteInicialConsumoFaixa) {
		this.limiteInicialConsumoFaixa = limiteInicialConsumoFaixa;
	}

	public BigDecimal getValorFaturadoAguaFaixa() {
		return valorFaturadoAguaFaixa;
	}

	public void setValorFaturadoAguaFaixa(BigDecimal valorFaturadoAguaFaixa) {
		this.valorFaturadoAguaFaixa = valorFaturadoAguaFaixa;
	}

	public BigDecimal getValorFaturadoEsgotoFaixa() {
		return valorFaturadoEsgotoFaixa;
	}

	public void setValorFaturadoEsgotoFaixa(BigDecimal valorFaturadoEsgotoFaixa) {
		this.valorFaturadoEsgotoFaixa = valorFaturadoEsgotoFaixa;
	}

	public BigDecimal getValorTarifaFaixa() {
		return valorTarifaFaixa;
	}

	public void setValorTarifaFaixa(BigDecimal valorTarifaFaixa) {
		this.valorTarifaFaixa = valorTarifaFaixa;
	}

	public Integer getIdConsumoTarifaFaixa() {
		return idConsumoTarifaFaixa;
	}

	public void setIdConsumoTarifaFaixa(Integer idConsumoTarifaFaixa) {
		this.idConsumoTarifaFaixa = idConsumoTarifaFaixa;
	}

}

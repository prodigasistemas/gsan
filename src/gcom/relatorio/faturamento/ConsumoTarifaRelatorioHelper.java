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

import java.math.BigDecimal;
import java.util.Date;

public class ConsumoTarifaRelatorioHelper {
	
	private Integer idConsumoTarifa;
	private String descricaoConsumoTarifa;
	private Date dataValidadeInicial;
	private String categoria;
	private Integer faixaInicial;
	private Integer faixaFinal;
	private BigDecimal custo;
	private BigDecimal tarifaMinima;
	private Integer consumoMinimo;
	
	/**
	 * @return Retorna o campo consumoMinimo.
	 */
	public Integer getConsumoMinimo() {
		return consumoMinimo;
	}
	/**
	 * @param consumoMinimo O consumoMinimo a ser setado.
	 */
	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}
	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return Retorna o campo custo.
	 */
	public BigDecimal getCusto() {
		return custo;
	}
	/**
	 * @param custo O custo a ser setado.
	 */
	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}
	/**
	 * @return Retorna o campo dataValidadeInicial.
	 */
	public Date getDataValidadeInicial() {
		return dataValidadeInicial;
	}
	/**
	 * @param dataValidadeInicial O dataValidadeInicial a ser setado.
	 */
	public void setDataValidadeInicial(Date dataValidadeInicial) {
		this.dataValidadeInicial = dataValidadeInicial;
	}
	/**
	 * @return Retorna o campo descricaoConsumoTarifa.
	 */
	public String getDescricaoConsumoTarifa() {
		return descricaoConsumoTarifa;
	}
	/**
	 * @param descricaoConsumoTarifa O descricaoConsumoTarifa a ser setado.
	 */
	public void setDescricaoConsumoTarifa(String descricaoConsumoTarifa) {
		this.descricaoConsumoTarifa = descricaoConsumoTarifa;
	}
	/**
	 * @return Retorna o campo faixaFinal.
	 */
	public Integer getFaixaFinal() {
		return faixaFinal;
	}
	/**
	 * @param faixaFinal O faixaFinal a ser setado.
	 */
	public void setFaixaFinal(Integer faixaFinal) {
		this.faixaFinal = faixaFinal;
	}
	/**
	 * @return Retorna o campo faixaInicial.
	 */
	public Integer getFaixaInicial() {
		return faixaInicial;
	}
	/**
	 * @param faixaInicial O faixaInicial a ser setado.
	 */
	public void setFaixaInicial(Integer faixaInicial) {
		this.faixaInicial = faixaInicial;
	}
	/**
	 * @return Retorna o campo idConsumoTarifa.
	 */
	public Integer getIdConsumoTarifa() {
		return idConsumoTarifa;
	}
	/**
	 * @param idConsumoTarifa O idConsumoTarifa a ser setado.
	 */
	public void setIdConsumoTarifa(Integer idConsumoTarifa) {
		this.idConsumoTarifa = idConsumoTarifa;
	}
	/**
	 * @return Retorna o campo tarifaMinima.
	 */
	public BigDecimal getTarifaMinima() {
		return tarifaMinima;
	}
	/**
	 * @param tarifaMinima O tarifaMinima a ser setado.
	 */
	public void setTarifaMinima(BigDecimal tarifaMinima) {
		this.tarifaMinima = tarifaMinima;
	}
	
	

}

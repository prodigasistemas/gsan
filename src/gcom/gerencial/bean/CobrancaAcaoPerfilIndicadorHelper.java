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

import gcom.cobranca.ResumoCobrancaAcao;
import gcom.util.Util;

import java.math.BigDecimal;

public class CobrancaAcaoPerfilIndicadorHelper {

	private Integer id;
	private String descricao;
	private Integer quantidadeDocumento;
	private BigDecimal valorDocumento;
	private Short indicadorLimite;
	
	public CobrancaAcaoPerfilIndicadorHelper(Integer id, String descricao, Short indicadorLimite,
			Integer quantidadeDocumento, BigDecimal valorDocumento) {
		this.id = id;
		this.descricao = descricao;
		this.indicadorLimite = indicadorLimite;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
		
	}

	public String getPercentualValor(String valorTotal) {
		String valorPercentual = Util.calcularPercentual(getValorDocumento().toString() , valorTotal);
		valorPercentual = Util.formatarMoedaReal(new BigDecimal(valorPercentual));
		return valorPercentual;
	}
	
	public String getPercentualQuantidade(String quantidadeTotal) {
		String quantidadePercentual = Util.calcularPercentual(getQuantidadeDocumento().toString(), quantidadeTotal);
		quantidadePercentual = Util.formatarMoedaReal(new BigDecimal(quantidadePercentual));
		return quantidadePercentual;
	}
	
	/**
	 * @return Retorna o campo indicadorLimite.
	 */
	public Short getIndicadorLimite() {
		return indicadorLimite;
	}
	/**
	 * @param indicadorLimite O indicadorLimite a ser setado.
	 */
	public void setIndicadorLimite(Short indicadorLimite) {
		this.indicadorLimite = indicadorLimite;
	}
	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public Integer getQuantidadeDocumento() {
		return quantidadeDocumento;
	}
	/**
	 * @param quantidadeDocumento O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(Integer quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}
	/**
	 * @return Retorna o campo valorDocumento.
	 */
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}
	/**
	 * @param valorDocumento O valorDocumento a ser setado.
	 */
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	
	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricaoIndicador(){
		String	descricao = "";
		if(getIndicadorLimite() != null && !getIndicadorLimite().equals("")){
			if(getIndicadorLimite() == ResumoCobrancaAcao.INDICADOR_ACIMA){
			  descricao = "ACIMA";  	
			}else if(getIndicadorLimite() == ResumoCobrancaAcao.INDICADOR_ABAIXO){
			  descricao = "ABAIXO";	
			}
		}
		return descricao;
	}
}

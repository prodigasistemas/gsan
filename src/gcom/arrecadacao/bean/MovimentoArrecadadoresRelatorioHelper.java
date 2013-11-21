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
package gcom.arrecadacao.bean;

import java.math.BigDecimal;
import java.util.Date;


public class MovimentoArrecadadoresRelatorioHelper {
	
	private Integer anoMesReferencia;
	private String arrecadador;
	private String descricaoArrecadacaoForma;
	private Date dataPagamento;
	private Integer qtdeDocumentos;
	private Integer qtdePagamentos;
	private BigDecimal valorPagamento;
	
	/**
	 * @return Retorna o campo valorPagamento.
	 */
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	/**
	 * @param valorPagamento O valorPagamento a ser setado.
	 */
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}
	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	/**
	 * @return Retorna o campo arrecadador.
	 */
	public String getArrecadador() {
		return arrecadador;
	}
	/**
	 * @param arrecadador O arrecadador a ser setado.
	 */
	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}
	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}
	/**
	 * @param dataPagamento O dataPagamento a ser setado.
	 */
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	/**
	 * @return Retorna o campo descricaoArrecadacaoForma.
	 */
	public String getDescricaoArrecadacaoForma() {
		return descricaoArrecadacaoForma;
	}
	/**
	 * @param descricaoArrecadacaoForma O descricaoArrecadacaoForma a ser setado.
	 */
	public void setDescricaoArrecadacaoForma(String descricaoArrecadacaoForma) {
		this.descricaoArrecadacaoForma = descricaoArrecadacaoForma;
	}
	/**
	 * @return Retorna o campo qtdeDocumentos.
	 */
	public Integer getQtdeDocumentos() {
		return qtdeDocumentos;
	}
	/**
	 * @param qtdeDocumentos O qtdeDocumentos a ser setado.
	 */
	public void setQtdeDocumentos(Integer qtdeDocumentos) {
		this.qtdeDocumentos = qtdeDocumentos;
	}
	/**
	 * @return Retorna o campo qtdePagamentos.
	 */
	public Integer getQtdePagamentos() {
		return qtdePagamentos;
	}
	/**
	 * @param qtdePagamentos O qtdePagamentos a ser setado.
	 */
	public void setQtdePagamentos(Integer qtdePagamentos) {
		this.qtdePagamentos = qtdePagamentos;
	} 
	
	
	
}

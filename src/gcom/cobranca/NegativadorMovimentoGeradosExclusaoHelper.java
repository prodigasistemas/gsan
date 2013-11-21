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
* Thiago Silva Toscano de Brito
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

package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * Classe utilizada na resposta do caso de uso 
 * [UC0673] - Gerar Movimento de Exclusão de Negativação  
 *
 * @author Thiago Toscano
 * @date 04/01/2008
 */
public class NegativadorMovimentoGeradosExclusaoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 private String descricaoNegativador = "";

	 private String nsa= "";

	 private String quantidadeRegistros= "";	 
	 	
	 private String valorDebito= ""; 
	 
	 private Date dataProcessamento;
	 
	 private Date horaProcessamento;

	/**
	 * @return Retorna o campo serialVersionUID.
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return Retorna o campo dataProcessamento.
	 */
	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	/**
	 * @param dataProcessamento O dataProcessamento a ser setado.
	 */
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	/**
	 * @return Retorna o campo descricaoNegativador.
	 */
	public String getDescricaoNegativador() {
		return descricaoNegativador;
	}

	/**
	 * @param descricaoNegativador O descricaoNegativador a ser setado.
	 */
	public void setDescricaoNegativador(String descricaoNegativador) {
		this.descricaoNegativador = descricaoNegativador;
	}

	/**
	 * @return Retorna o campo horaProcessamento.
	 */
	public Date getHoraProcessamento() {
		return horaProcessamento;
	}

	/**
	 * @param horaProcessamento O horaProcessamento a ser setado.
	 */
	public void setHoraProcessamento(Date horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}

	/**
	 * @return Retorna o campo nsa.
	 */
	public String getNsa() {
		return nsa;
	}

	/**
	 * @param nsa O nsa a ser setado.
	 */
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}

	/**
	 * @return Retorna o campo quantidadeRegistros.
	 */
	public String getQuantidadeRegistros() {
		return quantidadeRegistros;
	}

	/**
	 * @param quantidadeRegistros O quantidadeRegistros a ser setado.
	 */
	public void setQuantidadeRegistros(String quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public String getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
}
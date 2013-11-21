/**
 * 
 */
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
package gcom.cobranca.bean;

import java.util.Date;

/**
 * Objeto do caso de uso [UC0216] Calcular Acrescimo por Impontualidade
 *  Valor Multa
 *  Valor Juros de Mora
 *  Valor Atualizacao Monetaria
 * @author Rafael Santos
 * @since 05/01/2006
 *
 */
public class ConsultarTransferenciasDebitoHelper {
	
	private Integer idImovelOrigem;
	
	private Integer idImovelDestino;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private Integer idUsuario;
	
	public ConsultarTransferenciasDebitoHelper() {}
	
	/**
	 * @param valorMulta
	 * @param valorJurosMora
	 * @param valorAtualizacaoMonetaria
	 */
	public ConsultarTransferenciasDebitoHelper(Integer idImovelOrigem, Integer idImovelDestino, Date dataInicial, Date dataFinal, Integer idUsuario) {
		super();
		// TODO Auto-generated constructor stub
		this.idImovelOrigem = idImovelOrigem;
		this.idImovelDestino = idImovelDestino;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.idUsuario = idUsuario;
	}

	/**
	 * @return Retorna o campo dataFinal.
	 */
	public Date getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal O dataFinal a ser setado.
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * @return Retorna o campo dataInicial.
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * @param dataInicial O dataInicial a ser setado.
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * @return Retorna o campo idImovelDestino.
	 */
	public Integer getIdImovelDestino() {
		return idImovelDestino;
	}

	/**
	 * @param idImovelDestino O idImovelDestino a ser setado.
	 */
	public void setIdImovelDestino(Integer idImovelDestino) {
		this.idImovelDestino = idImovelDestino;
	}

	/**
	 * @return Retorna o campo idImovelOrigem.
	 */
	public Integer getIdImovelOrigem() {
		return idImovelOrigem;
	}

	/**
	 * @param idImovelOrigem O idImovelOrigem a ser setado.
	 */
	public void setIdImovelOrigem(Integer idImovelOrigem) {
		this.idImovelOrigem = idImovelOrigem;
	}

	/**
	 * @return Retorna o campo idUsuario.
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario O idUsuario a ser setado.
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	
}

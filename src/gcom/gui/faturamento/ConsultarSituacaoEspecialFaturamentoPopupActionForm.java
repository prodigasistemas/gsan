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
package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

/**
 * Descrição da classe 
 *
 * @author Rafael Corrêa
 * @date 18/09/2008
 */
public class ConsultarSituacaoEspecialFaturamentoPopupActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String tipo;
	private String consumoFixoNaoMedido;	
	private String consumoFixoMedido;
	private String volumeFixoNaoMedido;	
	private String volumeFixoMedido;
	private String motivo;
	private String mesAnoReferenciaFaturamentoInicial;
	private String mesAnoReferenciaFaturamentoFinal;
	private String mesAnoReferenciaRetirada;
	private String observacaoInforma;
	private String observacaoRetira;
	
	/**
	 * @return Retorna o campo consumoFixoMedido.
	 */
	public String getConsumoFixoMedido() {
		return consumoFixoMedido;
	}
	/**
	 * @param consumoFixoMedido O consumoFixoMedido a ser setado.
	 */
	public void setConsumoFixoMedido(String consumoFixoMedido) {
		this.consumoFixoMedido = consumoFixoMedido;
	}
	/**
	 * @return Retorna o campo consumoFixoNaoMedido.
	 */
	public String getConsumoFixoNaoMedido() {
		return consumoFixoNaoMedido;
	}
	/**
	 * @param consumoFixoNaoMedido O consumoFixoNaoMedido a ser setado.
	 */
	public void setConsumoFixoNaoMedido(String consumoFixoNaoMedido) {
		this.consumoFixoNaoMedido = consumoFixoNaoMedido;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoFinal.
	 */
	public String getMesAnoReferenciaFaturamentoFinal() {
		return mesAnoReferenciaFaturamentoFinal;
	}
	/**
	 * @param mesAnoReferenciaFaturamentoFinal O mesAnoReferenciaFaturamentoFinal a ser setado.
	 */
	public void setMesAnoReferenciaFaturamentoFinal(
			String mesAnoReferenciaFaturamentoFinal) {
		this.mesAnoReferenciaFaturamentoFinal = mesAnoReferenciaFaturamentoFinal;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoInicial.
	 */
	public String getMesAnoReferenciaFaturamentoInicial() {
		return mesAnoReferenciaFaturamentoInicial;
	}
	/**
	 * @param mesAnoReferenciaFaturamentoInicial O mesAnoReferenciaFaturamentoInicial a ser setado.
	 */
	public void setMesAnoReferenciaFaturamentoInicial(
			String mesAnoReferenciaFaturamentoInicial) {
		this.mesAnoReferenciaFaturamentoInicial = mesAnoReferenciaFaturamentoInicial;
	}
	/**
	 * @return Retorna o campo motivo.
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo O motivo a ser setado.
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacaoInforma() {
		return observacaoInforma;
	}
	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacaoInforma(String observacaoInforma) {
		this.observacaoInforma = observacaoInforma;
	}
	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacaoRetira() {
		return observacaoRetira;
	}
	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacaoRetira(String observacaoRetira) {
		this.observacaoRetira = observacaoRetira;
	}
	/**
	 * @return Retorna o campo tipo.
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo O tipo a ser setado.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return Retorna o campo volumeFixoMedido.
	 */
	public String getVolumeFixoMedido() {
		return volumeFixoMedido;
	}
	/**
	 * @param volumeFixoMedido O volumeFixoMedido a ser setado.
	 */
	public void setVolumeFixoMedido(String volumeFixoMedido) {
		this.volumeFixoMedido = volumeFixoMedido;
	}
	/**
	 * @return Retorna o campo volumeFixoNaoMedido.
	 */
	public String getVolumeFixoNaoMedido() {
		return volumeFixoNaoMedido;
	}
	/**
	 * @param volumeFixoNaoMedido O volumeFixoNaoMedido a ser setado.
	 */
	public void setVolumeFixoNaoMedido(String volumeFixoNaoMedido) {
		this.volumeFixoNaoMedido = volumeFixoNaoMedido;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaRetirada.
	 */
	public String getMesAnoReferenciaRetirada() {
		return mesAnoReferenciaRetirada;
	}
	/**
	 * @param mesAnoReferenciaRetirada O mesAnoReferenciaRetirada a ser setado.
	 */
	public void setMesAnoReferenciaRetirada(String mesAnoReferenciaRetirada) {
		this.mesAnoReferenciaRetirada = mesAnoReferenciaRetirada;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

}

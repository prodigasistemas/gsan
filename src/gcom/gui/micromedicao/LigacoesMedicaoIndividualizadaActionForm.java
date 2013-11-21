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
package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 28 de Junho de 2004
 */
public class LigacoesMedicaoIndividualizadaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idImovel;
	private String inscricaoImovel;
	private String qtdEconomias;
	private String leituraAnterior;
	private String dataLeituraAnterior;
	private String leituraAtual;
	private String dataLeituraAtual;
	private String consumoMedia;
	private String consumoMedido;
	private String consumoFaturado;
	private String rateio;
	private String consumoEsgoto;
	private String idLeituraAnormalidade;
	private String consumoAnormalidade;
	private String consumoTipo;
	private String idMedicaoHistorico;
	private String idConsumoHistorico;
	private String dsAbreviadaAnormalidadeConsumo;
	private String dsAbreviadaTipoConsumo; 
	
	/**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	idImovel = null;
    	inscricaoImovel = null;
    	qtdEconomias = null;
    	leituraAnterior = null;
    	dataLeituraAnterior = null;
    	leituraAtual = null;
    	dataLeituraAtual = null;
    	consumoMedia = null;
    	consumoMedido = null;
    	consumoFaturado = null;
    	rateio = null;
    	consumoEsgoto = null;
    	idLeituraAnormalidade = null;
    	consumoAnormalidade = null;
    	consumoTipo = null;
    	idMedicaoHistorico = null;
    	idConsumoHistorico = null;
    	dsAbreviadaAnormalidadeConsumo = null;
    	dsAbreviadaTipoConsumo = null; 
    }

	public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public String getConsumoFaturado() {
		return consumoFaturado;
	}

	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	public String getConsumoMedia() {
		return consumoMedia;
	}

	public void setConsumoMedia(String consumoMedia) {
		this.consumoMedia = consumoMedia;
	}

	public String getConsumoMedido() {
		return consumoMedido;
	}

	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}

	public String getConsumoTipo() {
		return consumoTipo;
	}

	public void setConsumoTipo(String consumoTipo) {
		this.consumoTipo = consumoTipo;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public String getDsAbreviadaAnormalidadeConsumo() {
		return dsAbreviadaAnormalidadeConsumo;
	}

	public void setDsAbreviadaAnormalidadeConsumo(
			String dsAbreviadaAnormalidadeConsumo) {
		this.dsAbreviadaAnormalidadeConsumo = dsAbreviadaAnormalidadeConsumo;
	}

	public String getDsAbreviadaTipoConsumo() {
		return dsAbreviadaTipoConsumo;
	}

	public void setDsAbreviadaTipoConsumo(String dsAbreviadaTipoConsumo) {
		this.dsAbreviadaTipoConsumo = dsAbreviadaTipoConsumo;
	}

	public String getIdConsumoHistorico() {
		return idConsumoHistorico;
	}

	public void setIdConsumoHistorico(String idConsumoHistorico) {
		this.idConsumoHistorico = idConsumoHistorico;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdLeituraAnormalidade() {
		return idLeituraAnormalidade;
	}

	public void setIdLeituraAnormalidade(String idLeituraAnormalidade) {
		this.idLeituraAnormalidade = idLeituraAnormalidade;
	}

	public String getIdMedicaoHistorico() {
		return idMedicaoHistorico;
	}

	public void setIdMedicaoHistorico(String idMedicaoHistorico) {
		this.idMedicaoHistorico = idMedicaoHistorico;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(String qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public String getRateio() {
		return rateio;
	}

	public void setRateio(String rateio) {
		this.rateio = rateio;
	}

}

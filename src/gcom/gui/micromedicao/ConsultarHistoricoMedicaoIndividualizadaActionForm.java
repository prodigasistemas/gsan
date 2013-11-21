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

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Form do Atualizar Tipo de RateioPopup
 * 
 * @author Rafael Santos
 * @since 11/01/2006 [UC00098] Manter Vinculos Imoveis Rateio Consumo
 */
public class ConsultarHistoricoMedicaoIndividualizadaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String codigoImovel;
	
	private String descricaoImovel;
	
	private String inscricaoImovel;
	
	private String situacaoAgua;
	
	private String situacaoEsgoto;
	
	private String tipoRateio;
	
	private String quantidadeImoveisVinculados;
	
	private String mesAno;
	
	private String endereco; 
	
	private String idTipoLigacao;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		codigoImovel = null;
		
			
	}

	/**
	 * @return Returns the codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel The codigoImovel to set.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return Returns the inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel The inscricaoImovel to set.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Returns the quantidadeImoveisVinculados.
	 */
	public String getQuantidadeImoveisVinculados() {
		return quantidadeImoveisVinculados;
	}

	/**
	 * @param quantidadeImoveisVinculados The quantidadeImoveisVinculados to set.
	 */
	public void setQuantidadeImoveisVinculados(String quantidadeImoveisVinculados) {
		this.quantidadeImoveisVinculados = quantidadeImoveisVinculados;
	}

	/**
	 * @return Returns the situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	/**
	 * @param situacaoAgua The situacaoAgua to set.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	/**
	 * @return Returns the situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	/**
	 * @param situacaoEsgoto The situacaoEsgoto to set.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	/**
	 * @return Returns the tipoRateio.
	 */
	public String getTipoRateio() {
		return tipoRateio;
	}

	/**
	 * @param tipoRateio The tipoRateio to set.
	 */
	public void setTipoRateio(String tipoRateio) {
		this.tipoRateio = tipoRateio;
	}

	/**
	 * @return Returns the mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno The mesAno to set.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	/**
	 * @return Returns the endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco The endereco to set.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getIdTipoLigacao() {
		return idTipoLigacao;
	}

	public void setIdTipoLigacao(String idTipoLigacao) {
		this.idTipoLigacao = idTipoLigacao;
	}


}
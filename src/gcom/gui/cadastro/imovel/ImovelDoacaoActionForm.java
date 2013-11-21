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
package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 22 de Dezembro de 2005
 */
public class ImovelDoacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovelDoacao;

	private String idImovel;

	private String idEntidadeBeneficente;

	private String valorDoacao;

	private String dataAdesao;

	private String dataCancelamento;

	private String idUsuarioAdesao;

	private String idUsuarioCancelamento;

	private String dataHoraUltimaAlteracao;
	
	private String inscricaoImovel;
	
	private String quantidadeParcela;
	
	private String quantidadeParcelaMaxima;

	/**
	 * @return Returns the idImovelDoacao.
	 */
	public String getIdImovelDoacao() {
		return idImovelDoacao;
	}

	/**
	 * @param consumoAlto
	 *            the idImovelDoacao to set.
	 */
	public void setIdImovelDoacao(String idImovelDoacao) {
		this.idImovelDoacao = idImovelDoacao;
	}

	/**
	 * @return Returns the idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param consumoEstouro
	 *            the idImovel to set.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Returns the idEntidadeBeneficente.
	 */
	public String getIdEntidadeBeneficente() {
		return idEntidadeBeneficente;
	}

	/**
	 * @param consumoMinimo
	 *            the idEntidadeBeneficente to set.
	 */
	public void setIdEntidadeBeneficente(String idEntidadeBeneficente) {
		this.idEntidadeBeneficente = idEntidadeBeneficente;
	}

	/**
	 * @return Returns the valorDoacao.
	 */
	public String getValorDoacao() {
		return valorDoacao;
	}

	/**
	 * @param descricao
	 *            the valorDoacao to set.
	 */
	public void setValorDoacao(String valorDoacao) {
		this.valorDoacao = valorDoacao;
	}

	/**
	 * @return Returns the dataAdesao.
	 */
	public String getDataAdesao() {
		return dataAdesao;
	}

	/**
	 * @param descricaoAbreviada
	 *            the dataAdesao to set.
	 */
	public void setDataAdesao(String dataAdesao) {
		this.dataAdesao = dataAdesao;
	}

	/**
	 * @return Returns the dataCancelamento.
	 */
	public String getDataCancelamento() {
		return dataCancelamento;
	}

	/**
	 * @param idCategoria
	 *            the dataCancelamento to set.
	 */
	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	/**
	 * @return Returns the idUsuarioAdesao.
	 */
	public String getIdUsuarioAdesao() {
		return idUsuarioAdesao;
	}

	/**
	 * @param indicadorUso
	 *            the idUsuarioAdesao to set.
	 */
	public void setIdUsuarioAdesao(String idUsuarioAdesao) {
		this.idUsuarioAdesao = idUsuarioAdesao;
	}

	/**
	 * @return Returns the idUsuarioCancelamento.
	 */
	public String getIdUsuarioCancelamento() {
		return idUsuarioCancelamento;
	}

	/**
	 * @param mediaBaixoConsumo
	 *            the idUsuarioCancelamento to set.
	 */
	public void setIdUsuarioCancelamento(String idUsuarioCancelamento) {
		this.idUsuarioCancelamento = idUsuarioCancelamento;
	}

	/**
	 * @return Returns the dataHoraUltimaAlteracao.
	 */
	public String getDataHoraUltimaAlteracao() {
		return dataHoraUltimaAlteracao;
	}

	/**
	 * @param porcentagemMediaBaixoConsumo
	 *            the dataHoraUltimaAlteracao to set.
	 */
	public void setDataHoraUltimaAlteracao(String dataHoraUltimaAlteracao) {
		this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
	}
	
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Returns the quantidadeParcela.
	 */
	public String getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(String quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	
	
	/**
	 * @return Returns the quantidadeParcelaMaxima.
	 */
	public String getQuantidadeParcelaMaxima() {
		return quantidadeParcelaMaxima;
	}

	/**
	 * @param quantidadeParcelaMaxima The quantidadeParcelaMaxima to set.
	 */
	public void setQuantidadeParcelaMaxima(String quantidadeParcelaMaxima) {
		this.quantidadeParcelaMaxima = quantidadeParcelaMaxima;
	}

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
		idImovelDoacao = null;
		idImovel = null;
		idEntidadeBeneficente = null;
		valorDoacao = null;
		dataAdesao = null;
		dataCancelamento = null;
		idUsuarioAdesao = null;
		idUsuarioCancelamento = null;
		dataHoraUltimaAlteracao = null;
		inscricaoImovel = null;
		quantidadeParcela = null;
		quantidadeParcelaMaxima = null;
	}

}
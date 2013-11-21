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
package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 29/08/2006
 */
public class ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
		extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String municipio;

	private String sistemaAbastecimento;

	private String zonaAbastecimento;

	private String bairro;

	private String areaBairro;

	private String mes;

	private String ano;

	private String codigoMunicipio;

	private String codigoBairro;

	private String codigoSistemaAbastecimento;

	private String codigoZonaAbastecimento;

	private String codigoAreaBairro;

	/**
	 * @return Retorna o campo ano.
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * @param ano
	 *            O ano a ser setado.
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/**
	 * @return Retorna o campo mes.
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes
	 *            O mes a ser setado.
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return Retorna o campo areaBairro.
	 */
	public String getAreaBairro() {
		return areaBairro;
	}

	/**
	 * @param areaBairro
	 *            O areaBairro a ser setado.
	 */
	public void setAreaBairro(String areaBairro) {
		this.areaBairro = areaBairro;
	}

	/**
	 * @return Retorna o campo bairro.
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro
	 *            O bairro a ser setado.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return Retorna o campo municipio.
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            O municipio a ser setado.
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return Retorna o campo sistemaAbastecimento.
	 */
	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}

	/**
	 * @param sistemaAbastecimento
	 *            O sistemaAbastecimento a ser setado.
	 */
	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}

	/**
	 * @return Retorna o campo zonaAbastecimento.
	 */
	public String getZonaAbastecimento() {
		return zonaAbastecimento;
	}

	/**
	 * @param zonaAbastecimento
	 *            O zonaAbastecimento a ser setado.
	 */
	public void setZonaAbastecimento(String zonaAbastecimento) {
		this.zonaAbastecimento = zonaAbastecimento;
	}

	/**
	 * @return Retorna o campo codigoAreaBairro.
	 */
	public String getCodigoAreaBairro() {
		return codigoAreaBairro;
	}

	/**
	 * @param codigoAreaBairro
	 *            O codigoAreaBairro a ser setado.
	 */
	public void setCodigoAreaBairro(String codigoAreaBairro) {
		this.codigoAreaBairro = codigoAreaBairro;
	}

	/**
	 * @return Retorna o campo codigoBairro.
	 */
	public String getCodigoBairro() {
		return codigoBairro;
	}

	/**
	 * @param codigoBairro
	 *            O codigoBairro a ser setado.
	 */
	public void setCodigoBairro(String codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	/**
	 * @return Retorna o campo codigoMunicipio.
	 */
	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	/**
	 * @param codigoMunicipio
	 *            O codigoMunicipio a ser setado.
	 */
	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	/**
	 * @return Retorna o campo codigoSistemaAbastecimento.
	 */
	public String getCodigoSistemaAbastecimento() {
		return codigoSistemaAbastecimento;
	}

	/**
	 * @param codigoSistemaAbastecimento
	 *            O codigoSistemaAbastecimento a ser setado.
	 */
	public void setCodigoSistemaAbastecimento(String codigoSistemaAbastecimento) {
		this.codigoSistemaAbastecimento = codigoSistemaAbastecimento;
	}

	/**
	 * @return Retorna o campo codigoZonaAbastecimento.
	 */
	public String getCodigoZonaAbastecimento() {
		return codigoZonaAbastecimento;
	}

	/**
	 * @param codigoZonaAbastecimento
	 *            O codigoZonaAbastecimento a ser setado.
	 */
	public void setCodigoZonaAbastecimento(String codigoZonaAbastecimento) {
		this.codigoZonaAbastecimento = codigoZonaAbastecimento;
	}

}

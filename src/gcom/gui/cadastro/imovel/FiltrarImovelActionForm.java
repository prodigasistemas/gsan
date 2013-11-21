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

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC]Filtrar Imovel
 * 
 * @author Rafael Santos
 * @created 04/07/2006
 */
public class FiltrarImovelActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String idLocalidadeFiltro;
	private String localidadeDescricaoFiltro;
	private String idSetorComercialFiltro;
	private String idImovelFiltro;
	private String setorComercialDescricaoFiltro;
	private String idQuadraFiltro;
	private String quadraDescricaoFiltro;
	private String loteFiltro;
	private String subLoteFiltro;
	private String idClienteFiltro;
	private String nomeClienteFiltro;
	private String atualizarFiltro;
	private String matriculaFiltro;
	
	private String cepFiltro;
	private String cepDescricaoFiltro;
	private String idBairroFiltro;
	private String bairroFiltro;
	private String idMunicipioFiltro;
	private String municipioFiltro;
	private String idLogradouroFiltro;
	private String logradouroFiltro;
	
	private String numeroImovelInicialFiltro;
	private String numeroImovelFinalFiltro;
	/**
	 * @return Retorna o campo atualizarFiltro.
	 */
	public String getAtualizarFiltro() {
		return atualizarFiltro;
	}
	/**
	 * @param atualizarFiltro O atualizarFiltro a ser setado.
	 */
	public void setAtualizarFiltro(String atualizarFiltro) {
		this.atualizarFiltro = atualizarFiltro;
	}
	/**
	 * @return Retorna o campo bairroFiltro.
	 */
	public String getBairroFiltro() {
		return bairroFiltro;
	}
	/**
	 * @param bairroFiltro O bairroFiltro a ser setado.
	 */
	public void setBairroFiltro(String bairroFiltro) {
		this.bairroFiltro = bairroFiltro;
	}
	/**
	 * @return Retorna o campo cepFiltro.
	 */
	public String getCepFiltro() {
		return cepFiltro;
	}
	/**
	 * @param cepFiltro O cepFiltro a ser setado.
	 */
	public void setCepFiltro(String cepFiltro) {
		this.cepFiltro = cepFiltro;
	}
	/**
	 * @return Retorna o campo idBairroFiltro.
	 */
	public String getIdBairroFiltro() {
		return idBairroFiltro;
	}
	/**
	 * @param idBairroFiltro O idBairroFiltro a ser setado.
	 */
	public void setIdBairroFiltro(String idBairroFiltro) {
		this.idBairroFiltro = idBairroFiltro;
	}
	/**
	 * @return Retorna o campo idClienteFiltro.
	 */
	public String getIdClienteFiltro() {
		return idClienteFiltro;
	}
	/**
	 * @param idClienteFiltro O idClienteFiltro a ser setado.
	 */
	public void setIdClienteFiltro(String idClienteFiltro) {
		this.idClienteFiltro = idClienteFiltro;
	}
	/**
	 * @return Retorna o campo idImovelFiltro.
	 */
	public String getIdImovelFiltro() {
		return idImovelFiltro;
	}
	/**
	 * @param idImovelFiltro O idImovelFiltro a ser setado.
	 */
	public void setIdImovelFiltro(String idImovelFiltro) {
		this.idImovelFiltro = idImovelFiltro;
	}
	/**
	 * @return Retorna o campo idLocalidadeFiltro.
	 */
	public String getIdLocalidadeFiltro() {
		return idLocalidadeFiltro;
	}
	/**
	 * @param idLocalidadeFiltro O idLocalidadeFiltro a ser setado.
	 */
	public void setIdLocalidadeFiltro(String idLocalidadeFiltro) {
		this.idLocalidadeFiltro = idLocalidadeFiltro;
	}
	/**
	 * @return Retorna o campo idLogradouroFiltro.
	 */
	public String getIdLogradouroFiltro() {
		return idLogradouroFiltro;
	}
	/**
	 * @param idLogradouroFiltro O idLogradouroFiltro a ser setado.
	 */
	public void setIdLogradouroFiltro(String idLogradouroFiltro) {
		this.idLogradouroFiltro = idLogradouroFiltro;
	}
	/**
	 * @return Retorna o campo idMunicipioFiltro.
	 */
	public String getIdMunicipioFiltro() {
		return idMunicipioFiltro;
	}
	/**
	 * @param idMunicipioFiltro O idMunicipioFiltro a ser setado.
	 */
	public void setIdMunicipioFiltro(String idMunicipioFiltro) {
		this.idMunicipioFiltro = idMunicipioFiltro;
	}
	/**
	 * @return Retorna o campo idQuadraFiltro.
	 */
	public String getIdQuadraFiltro() {
		return idQuadraFiltro;
	}
	/**
	 * @param idQuadraFiltro O idQuadraFiltro a ser setado.
	 */
	public void setIdQuadraFiltro(String idQuadraFiltro) {
		this.idQuadraFiltro = idQuadraFiltro;
	}
	/**
	 * @return Retorna o campo idSetorComercialFiltro.
	 */
	public String getIdSetorComercialFiltro() {
		return idSetorComercialFiltro;
	}
	/**
	 * @param idSetorComercialFiltro O idSetorComercialFiltro a ser setado.
	 */
	public void setIdSetorComercialFiltro(String idSetorComercialFiltro) {
		this.idSetorComercialFiltro = idSetorComercialFiltro;
	}
	/**
	 * @return Retorna o campo localidadeDescricaoFiltro.
	 */
	public String getLocalidadeDescricaoFiltro() {
		return localidadeDescricaoFiltro;
	}
	/**
	 * @param localidadeDescricaoFiltro O localidadeDescricaoFiltro a ser setado.
	 */
	public void setLocalidadeDescricaoFiltro(String localidadeDescricaoFiltro) {
		this.localidadeDescricaoFiltro = localidadeDescricaoFiltro;
	}
	/**
	 * @return Retorna o campo logradouroFiltro.
	 */
	public String getLogradouroFiltro() {
		return logradouroFiltro;
	}
	/**
	 * @param logradouroFiltro O logradouroFiltro a ser setado.
	 */
	public void setLogradouroFiltro(String logradouroFiltro) {
		this.logradouroFiltro = logradouroFiltro;
	}
	/**
	 * @return Retorna o campo loteFiltro.
	 */
	public String getLoteFiltro() {
		return loteFiltro;
	}
	/**
	 * @param loteFiltro O loteFiltro a ser setado.
	 */
	public void setLoteFiltro(String loteFiltro) {
		this.loteFiltro = loteFiltro;
	}
	/**
	 * @return Retorna o campo matriculaFiltro.
	 */
	public String getMatriculaFiltro() {
		return matriculaFiltro;
	}
	/**
	 * @param matriculaFiltro O matriculaFiltro a ser setado.
	 */
	public void setMatriculaFiltro(String matriculaFiltro) {
		this.matriculaFiltro = matriculaFiltro;
	}
	/**
	 * @return Retorna o campo municipioFiltro.
	 */
	public String getMunicipioFiltro() {
		return municipioFiltro;
	}
	/**
	 * @param municipioFiltro O municipioFiltro a ser setado.
	 */
	public void setMunicipioFiltro(String municipioFiltro) {
		this.municipioFiltro = municipioFiltro;
	}
	/**
	 * @return Retorna o campo nomeClienteFiltro.
	 */
	public String getNomeClienteFiltro() {
		return nomeClienteFiltro;
	}
	/**
	 * @param nomeClienteFiltro O nomeClienteFiltro a ser setado.
	 */
	public void setNomeClienteFiltro(String nomeClienteFiltro) {
		this.nomeClienteFiltro = nomeClienteFiltro;
	}
	/**
	 * @return Retorna o campo quadraDescricaoFiltro.
	 */
	public String getQuadraDescricaoFiltro() {
		return quadraDescricaoFiltro;
	}
	/**
	 * @param quadraDescricaoFiltro O quadraDescricaoFiltro a ser setado.
	 */
	public void setQuadraDescricaoFiltro(String quadraDescricaoFiltro) {
		this.quadraDescricaoFiltro = quadraDescricaoFiltro;
	}
	/**
	 * @return Retorna o campo setorComercialDescricaoFiltro.
	 */
	public String getSetorComercialDescricaoFiltro() {
		return setorComercialDescricaoFiltro;
	}
	/**
	 * @param setorComercialDescricaoFiltro O setorComercialDescricaoFiltro a ser setado.
	 */
	public void setSetorComercialDescricaoFiltro(
			String setorComercialDescricaoFiltro) {
		this.setorComercialDescricaoFiltro = setorComercialDescricaoFiltro;
	}
	/**
	 * @return Retorna o campo subLoteFiltro.
	 */
	public String getSubLoteFiltro() {
		return subLoteFiltro;
	}
	/**
	 * @param subLoteFiltro O subLoteFiltro a ser setado.
	 */
	public void setSubLoteFiltro(String subLoteFiltro) {
		this.subLoteFiltro = subLoteFiltro;
	}
	/**
	 * @return Retorna o campo cepDescricaoFiltro.
	 */
	public String getCepDescricaoFiltro() {
		return cepDescricaoFiltro;
	}
	/**
	 * @param cepDescricaoFiltro O cepDescricaoFiltro a ser setado.
	 */
	public void setCepDescricaoFiltro(String cepDescricaoFiltro) {
		this.cepDescricaoFiltro = cepDescricaoFiltro;
	}
	
	/**
	 * @return Retorna o campo numeroImovelFinal.
	 */
	public String getNumeroImovelFinalFiltro() {
		return numeroImovelFinalFiltro;
	}
	/**
	 * @param numeroImovelFinal O numeroImovelFinal a ser setado.
	 */
	public void setNumeroImovelFinalFiltro(String numeroImovelFinalFiltro) {
		this.numeroImovelFinalFiltro = numeroImovelFinalFiltro;
	}
	/**
	 * @return Retorna o campo numeroImovelInicial.
	 */
	public String getNumeroImovelInicialFiltro() {
		return numeroImovelInicialFiltro;
	}
	/**
	 * @param numeroImovelInicial O numeroImovelInicial a ser setado.
	 */
	public void setNumeroImovelInicialFiltro(String numeroImovelInicialFiltro) {
		this.numeroImovelInicialFiltro = numeroImovelInicialFiltro;
	}
	
}

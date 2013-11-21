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
package gcom.gui.cadastro.cliente;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0000]Inserir Cliente
 *
 * @author Roberta Costa,Rafael Santos
 * @date 13/07/2006,11/10/2006
 */
public class InserirClienteActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String nomeClienteInserir;
    private String nomeAbreviadoClienteInserir;
    private String tipoPessoaClienteInserir;
    private String emailClienteInserir;
    private String indicadorUsoClienteInserir;
    private String cpfClienteInserir;
    private String rgClienteInserir;
    private String dataEmissaoClienteInserir;
    private String idOrgaoExpedidorClienteInserir;
    private String idUnidadeFederacaoClienteInserir;
    private String dataNascimentoClienteInserir;
    private String idProfissaoClienteInserir;
    private String idPessoaSexoClienteInserir;
    private String cnpjClienteInserir;
    private String idRamoAtividadeClienteInserir;
    private String codigoClienteResponsavelClienteInserir;
    private String idTipoTelefoneClienteInserir;
    private String idMunicipioClienteInserir;
    private String descricaoMunicipioClienteInserir;
	private String dddClienteInserir;
	private String telefoneClienteInserir;
	private String ramalClienteInserir;
	private String nomeClienteReceitaFederal;
	private String cpfClienteReceitaFederal;
	
	/**
	 * @return Retorna o campo cnpjClienteInserir.
	 */
	public String getCnpjClienteInserir() {
		return cnpjClienteInserir;
	}
	/**
	 * @param cnpjClienteInserir O cnpjClienteInserir a ser setado.
	 */
	public void setCnpjClienteInserir(String cnpjClienteInserir) {
		this.cnpjClienteInserir = cnpjClienteInserir;
	}
	/**
	 * @return Retorna o campo codigoClienteResponsavelClienteInserir.
	 */
	public String getCodigoClienteResponsavelClienteInserir() {
		return codigoClienteResponsavelClienteInserir;
	}
	/**
	 * @param codigoClienteResponsavelClienteInserir O codigoClienteResponsavelClienteInserir a ser setado.
	 */
	public void setCodigoClienteResponsavelClienteInserir(
			String codigoClienteResponsavelClienteInserir) {
		this.codigoClienteResponsavelClienteInserir = codigoClienteResponsavelClienteInserir;
	}
	/**
	 * @return Retorna o campo cpfClienteInserir.
	 */
	public String getCpfClienteInserir() {
		return cpfClienteInserir;
	}
	/**
	 * @param cpfClienteInserir O cpfClienteInserir a ser setado.
	 */
	public void setCpfClienteInserir(String cpfClienteInserir) {
		this.cpfClienteInserir = cpfClienteInserir;
	}
	/**
	 * @return Retorna o campo dataEmissaoClienteInserir.
	 */
	public String getDataEmissaoClienteInserir() {
		return dataEmissaoClienteInserir;
	}
	/**
	 * @param dataEmissaoClienteInserir O dataEmissaoClienteInserir a ser setado.
	 */
	public void setDataEmissaoClienteInserir(String dataEmissaoClienteInserir) {
		this.dataEmissaoClienteInserir = dataEmissaoClienteInserir;
	}
	/**
	 * @return Retorna o campo dataNascimentoClienteInserir.
	 */
	public String getDataNascimentoClienteInserir() {
		return dataNascimentoClienteInserir;
	}
	/**
	 * @param dataNascimentoClienteInserir O dataNascimentoClienteInserir a ser setado.
	 */
	public void setDataNascimentoClienteInserir(String dataNascimentoClienteInserir) {
		this.dataNascimentoClienteInserir = dataNascimentoClienteInserir;
	}
	/**
	 * @return Retorna o campo dddClienteInserir.
	 */
	public String getDddClienteInserir() {
		return dddClienteInserir;
	}
	/**
	 * @param dddClienteInserir O dddClienteInserir a ser setado.
	 */
	public void setDddClienteInserir(String dddClienteInserir) {
		this.dddClienteInserir = dddClienteInserir;
	}
	/**
	 * @return Retorna o campo descricaoMunicipioClienteInserir.
	 */
	public String getDescricaoMunicipioClienteInserir() {
		return descricaoMunicipioClienteInserir;
	}
	/**
	 * @param descricaoMunicipioClienteInserir O descricaoMunicipioClienteInserir a ser setado.
	 */
	public void setDescricaoMunicipioClienteInserir(
			String descricaoMunicipioClienteInserir) {
		this.descricaoMunicipioClienteInserir = descricaoMunicipioClienteInserir;
	}
	/**
	 * @return Retorna o campo emailClienteInserir.
	 */
	public String getEmailClienteInserir() {
		return emailClienteInserir;
	}
	/**
	 * @param emailClienteInserir O emailClienteInserir a ser setado.
	 */
	public void setEmailClienteInserir(String emailClienteInserir) {
		this.emailClienteInserir = emailClienteInserir;
	}
	/**
	 * @return Retorna o campo idMunicipioClienteInserir.
	 */
	public String getIdMunicipioClienteInserir() {
		return idMunicipioClienteInserir;
	}
	/**
	 * @param idMunicipioClienteInserir O idMunicipioClienteInserir a ser setado.
	 */
	public void setIdMunicipioClienteInserir(String idMunicipioClienteInserir) {
		this.idMunicipioClienteInserir = idMunicipioClienteInserir;
	}
	/**
	 * @return Retorna o campo idOrgaoExpedidorClienteInserir.
	 */
	public String getIdOrgaoExpedidorClienteInserir() {
		return idOrgaoExpedidorClienteInserir;
	}
	/**
	 * @param idOrgaoExpedidorClienteInserir O idOrgaoExpedidorClienteInserir a ser setado.
	 */
	public void setIdOrgaoExpedidorClienteInserir(
			String idOrgaoExpedidorClienteInserir) {
		this.idOrgaoExpedidorClienteInserir = idOrgaoExpedidorClienteInserir;
	}
	/**
	 * @return Retorna o campo idPessoaSexoClienteInserir.
	 */
	public String getIdPessoaSexoClienteInserir() {
		return idPessoaSexoClienteInserir;
	}
	/**
	 * @param idPessoaSexoClienteInserir O idPessoaSexoClienteInserir a ser setado.
	 */
	public void setIdPessoaSexoClienteInserir(String idPessoaSexoClienteInserir) {
		this.idPessoaSexoClienteInserir = idPessoaSexoClienteInserir;
	}
	/**
	 * @return Retorna o campo idProfissaoClienteInserir.
	 */
	public String getIdProfissaoClienteInserir() {
		return idProfissaoClienteInserir;
	}
	/**
	 * @param idProfissaoClienteInserir O idProfissaoClienteInserir a ser setado.
	 */
	public void setIdProfissaoClienteInserir(String idProfissaoClienteInserir) {
		this.idProfissaoClienteInserir = idProfissaoClienteInserir;
	}
	/**
	 * @return Retorna o campo idRamoAtividadeClienteInserir.
	 */
	public String getIdRamoAtividadeClienteInserir() {
		return idRamoAtividadeClienteInserir;
	}
	/**
	 * @param idRamoAtividadeClienteInserir O idRamoAtividadeClienteInserir a ser setado.
	 */
	public void setIdRamoAtividadeClienteInserir(
			String idRamoAtividadeClienteInserir) {
		this.idRamoAtividadeClienteInserir = idRamoAtividadeClienteInserir;
	}
	/**
	 * @return Retorna o campo idTipoTelefoneClienteInserir.
	 */
	public String getIdTipoTelefoneClienteInserir() {
		return idTipoTelefoneClienteInserir;
	}
	/**
	 * @param idTipoTelefoneClienteInserir O idTipoTelefoneClienteInserir a ser setado.
	 */
	public void setIdTipoTelefoneClienteInserir(String idTipoTelefoneClienteInserir) {
		this.idTipoTelefoneClienteInserir = idTipoTelefoneClienteInserir;
	}
	/**
	 * @return Retorna o campo idUnidadeFederacaoClienteInserir.
	 */
	public String getIdUnidadeFederacaoClienteInserir() {
		return idUnidadeFederacaoClienteInserir;
	}
	/**
	 * @param idUnidadeFederacaoClienteInserir O idUnidadeFederacaoClienteInserir a ser setado.
	 */
	public void setIdUnidadeFederacaoClienteInserir(
			String idUnidadeFederacaoClienteInserir) {
		this.idUnidadeFederacaoClienteInserir = idUnidadeFederacaoClienteInserir;
	}
	/**
	 * @return Retorna o campo indicadorUsoClienteInserir.
	 */
	public String getIndicadorUsoClienteInserir() {
		return indicadorUsoClienteInserir;
	}
	/**
	 * @param indicadorUsoClienteInserir O indicadorUsoClienteInserir a ser setado.
	 */
	public void setIndicadorUsoClienteInserir(String indicadorUsoClienteInserir) {
		this.indicadorUsoClienteInserir = indicadorUsoClienteInserir;
	}
	/**
	 * @return Retorna o campo nomeAbreviadoClienteInserir.
	 */
	public String getNomeAbreviadoClienteInserir() {
		return nomeAbreviadoClienteInserir;
	}
	/**
	 * @param nomeAbreviadoClienteInserir O nomeAbreviadoClienteInserir a ser setado.
	 */
	public void setNomeAbreviadoClienteInserir(String nomeAbreviadoClienteInserir) {
		this.nomeAbreviadoClienteInserir = nomeAbreviadoClienteInserir;
	}
	/**
	 * @return Retorna o campo nomeClienteInserir.
	 */
	public String getNomeClienteInserir() {
		return nomeClienteInserir;
	}
	/**
	 * @param nomeClienteInserir O nomeClienteInserir a ser setado.
	 */
	public void setNomeClienteInserir(String nomeClienteInserir) {
		this.nomeClienteInserir = nomeClienteInserir;
	}
	/**
	 * @return Retorna o campo ramalClienteInserir.
	 */
	public String getRamalClienteInserir() {
		return ramalClienteInserir;
	}
	/**
	 * @param ramalClienteInserir O ramalClienteInserir a ser setado.
	 */
	public void setRamalClienteInserir(String ramalClienteInserir) {
		this.ramalClienteInserir = ramalClienteInserir;
	}
	/**
	 * @return Retorna o campo rgClienteInserir.
	 */
	public String getRgClienteInserir() {
		return rgClienteInserir;
	}
	/**
	 * @param rgClienteInserir O rgClienteInserir a ser setado.
	 */
	public void setRgClienteInserir(String rgClienteInserir) {
		this.rgClienteInserir = rgClienteInserir;
	}
	/**
	 * @return Retorna o campo telefoneClienteInserir.
	 */
	public String getTelefoneClienteInserir() {
		return telefoneClienteInserir;
	}
	/**
	 * @param telefoneClienteInserir O telefoneClienteInserir a ser setado.
	 */
	public void setTelefoneClienteInserir(String telefoneClienteInserir) {
		this.telefoneClienteInserir = telefoneClienteInserir;
	}
	/**
	 * @return Retorna o campo tipoPessoaClienteInserir.
	 */
	public String getTipoPessoaClienteInserir() {
		return tipoPessoaClienteInserir;
	}
	/**
	 * @param tipoPessoaClienteInserir O tipoPessoaClienteInserir a ser setado.
	 */
	public void setTipoPessoaClienteInserir(String tipoPessoaClienteInserir) {
		this.tipoPessoaClienteInserir = tipoPessoaClienteInserir;
	}
	
	public String getNomeClienteReceitaFederal() {
		return nomeClienteReceitaFederal;
	}
	public void setNomeClienteReceitaFederal(String nomeClienteReceitaFederal) {
		this.nomeClienteReceitaFederal = nomeClienteReceitaFederal;
	}
	public String getCpfClienteReceitaFederal() {
		return cpfClienteReceitaFederal;
	}
	public void setCpfClienteReceitaFederal(String cpfClienteReceitaFederal) {
		this.cpfClienteReceitaFederal = cpfClienteReceitaFederal;
	}
	
}
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

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class FiltrarClienteOutrosCriteriosActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String bairro;
  private String cep;
  private String cnpj;
  private String cpf;
  private String dataEmissao;
  private String dataNascimento;
  private String email;
  private String idCliente;
  private String idClienteResponsavel;
  private String idImovel;
  private String indicadorUso;
  private String logradouro;
  private String municipio;
  private String nomeAbreviadoCliente;
  private String nomeCliente;
  private String orgaoEmissor;
  private String profissao;
  private String ramoAtividade;
  private String rg;
  private String sexo;
  private String tipoCliente;
  private String descricaoMunicipioCliente;
  private String descricaoBairroCliente;
  private String descricaoLogradouroCliente;
  private String inscricao;
  
  public String getInscricao() {
	return inscricao;
}
public void setInscricao(String inscricao) {
	this.inscricao = inscricao;
}
public String getBairro() {
    return bairro;
  }
  public void setBairro(String bairro) {
    this.bairro = bairro;
  }
  public String getCep() {
    return cep;
  }
  public void setCep(String cep) {
    this.cep = cep;
  }
  public String getCnpj() {
    return cnpj;
  }
  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  public String getDataEmissao() {
    return dataEmissao;
  }
  public void setDataEmissao(String dataEmissao) {
    this.dataEmissao = dataEmissao;
  }
  public String getDataNascimento() {
    return dataNascimento;
  }
  public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getIdCliente() {
    return idCliente;
  }
  public void setIdCliente(String idCliente) {
    this.idCliente = idCliente;
  }
  public String getIdClienteResponsavel() {
    return idClienteResponsavel;
  }
  public void setIdClienteResponsavel(String idClienteResponsavel) {
    this.idClienteResponsavel = idClienteResponsavel;
  }
  public String getIdImovel() {
    return idImovel;
  }
  public void setIdImovel(String idImovel) {
    this.idImovel = idImovel;
  }
  public String getIndicadorUso() {
    return indicadorUso;
  }
  public void setIndicadorUso(String indicadorUso) {
    this.indicadorUso = indicadorUso;
  }
  public String getLogradouro() {
    return logradouro;
  }
  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }
  public String getMunicipio() {
    return municipio;
  }
  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }
  public String getNomeAbreviadoCliente() {
    return nomeAbreviadoCliente;
  }
  public void setNomeAbreviadoCliente(String nomeAbreviadoCliente) {
    this.nomeAbreviadoCliente = nomeAbreviadoCliente;
  }
  public String getNomeCliente() {
    return nomeCliente;
  }
  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }
  public String getOrgaoEmissor() {
    return orgaoEmissor;
  }
  public void setOrgaoEmissor(String orgaoEmissor) {
    this.orgaoEmissor = orgaoEmissor;
  }
  public String getProfissao() {
    return profissao;
  }
  public void setProfissao(String profissao) {
    this.profissao = profissao;
  }
  public String getRamoAtividade() {
    return ramoAtividade;
  }
  public void setRamoAtividade(String ramoAtividade) {
    this.ramoAtividade = ramoAtividade;
  }
  public String getRg() {
    return rg;
  }
  public void setRg(String rg) {
    this.rg = rg;
  }
  public String getSexo() {
    return sexo;
  }
  public void setSexo(String sexo) {
    this.sexo = sexo;
  }
  public String getTipoCliente() {
    return tipoCliente;
  }
  public void setTipoCliente(String tipoCliente) {
    this.tipoCliente = tipoCliente;
  } 
  public String getDescricaoBairroCliente() {
	return descricaoBairroCliente;
  }
  public void setDescricaoBairroCliente(String descricaoBairroCliente) {
	this.descricaoBairroCliente = descricaoBairroCliente;
  }
  public String getDescricaoLogradouroCliente() {
	return descricaoLogradouroCliente;
  }
  public void setDescricaoLogradouroCliente(String descricaoLogradouroCliente) {
	this.descricaoLogradouroCliente = descricaoLogradouroCliente;
  }
  public String getDescricaoMunicipioCliente() {
	return descricaoMunicipioCliente;
  }
  public void setDescricaoMunicipioCliente(String descricaoMunicipioCliente) {
	this.descricaoMunicipioCliente = descricaoMunicipioCliente;
  }
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
}
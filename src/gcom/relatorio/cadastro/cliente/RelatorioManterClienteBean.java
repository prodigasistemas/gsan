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
package gcom.relatorio.cadastro.cliente;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @created 13 de Outubro de 2005
 * @version 1.0
 */

public class RelatorioManterClienteBean implements RelatorioBean {
	private String codigoCliente;
	
    private String nome;

    private String tipoCliente;

    private String email;

    private String cpf;

    private String rg;

    private String dataEmissao;

    private String orgaoExpeditor;

    private String estado;

    private String dataNascimento;

    private String profissao;

    private String sexo;

    private String endereco;

    private String numeroTelefone;

    private String ramal;

    private String tipoTelefone;

    private String municipio;

    private String indicadorUso;

    private String cnpj;

    private String ramo;

    private String codigoClienteResponsavel;

    private String nomeClienteResponsavel;

    private String pessoa;

    /**
     * Constructor for the RelatorioManterClienteBean object
     * 
     * @param nome
     *            Description of the Parameter
     * @param tipoCliente
     *            Description of the Parameter
     * @param email
     *            Description of the Parameter
     * @param cpf
     *            Description of the Parameter
     * @param rg
     *            Description of the Parameter
     * @param dataEmissao
     *            Description of the Parameter
     * @param orgaoExpeditor
     *            Description of the Parameter
     * @param estado
     *            Description of the Parameter
     * @param dataNascimento
     *            Description of the Parameter
     * @param profissao
     *            Description of the Parameter
     * @param sexo
     *            Description of the Parameter
     * @param endereco
     *            Description of the Parameter
     * @param numeroTelefone
     *            Description of the Parameter
     * @param ramal
     *            Description of the Parameter
     * @param tipoTelefone
     *            Description of the Parameter
     * @param municipio
     *            Description of the Parameter
     * @param indicadorUso
     *            Description of the Parameter
     * @param pessoa
     *            Description of the Parameter
     */
    public RelatorioManterClienteBean(String codigoCliente, String nome, String tipoCliente,
            String email, String cpf, String rg, String dataEmissao,
            String orgaoExpeditor, String estado, String dataNascimento,
            String profissao, String sexo, String endereco,
            String numeroTelefone, String ramal, String tipoTelefone,
            String municipio, String indicadorUso, String pessoa) {
    	this.codigoCliente = codigoCliente;
        this.nome = nome;
        this.tipoCliente = tipoCliente;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.dataEmissao = dataEmissao;
        this.orgaoExpeditor = orgaoExpeditor;
        this.estado = estado;
        this.dataNascimento = dataNascimento;
        this.profissao = profissao;
        this.sexo = sexo;
        this.endereco = endereco;
        this.numeroTelefone = numeroTelefone;
        this.ramal = ramal;
        this.tipoTelefone = tipoTelefone;
        this.municipio = municipio;
        this.indicadorUso = indicadorUso;
        this.pessoa = pessoa;
    }

    /**
     * Constructor for the RelatorioManterClienteBean object
     * 
     * @param nome
     *            Description of the Parameter
     * @param tipoCliente
     *            Description of the Parameter
     * @param email
     *            Description of the Parameter
     * @param cnpj
     *            Description of the Parameter
     * @param ramo
     *            Description of the Parameter
     * @param codigoClienteResponsavel
     *            Description of the Parameter
     * @param nomeClienteResponsavel
     *            Description of the Parameter
     * @param endereco
     *            Description of the Parameter
     * @param numeroTelefone
     *            Description of the Parameter
     * @param ramal
     *            Description of the Parameter
     * @param tipoTelefone
     *            Description of the Parameter
     * @param municipio
     *            Description of the Parameter
     * @param indicadorUso
     *            Description of the Parameter
     * @param pessoa
     *            Description of the Parameter
     */
    public RelatorioManterClienteBean(String codigoCliente, String nome, String tipoCliente,
            String email, String cnpj, String ramo,
            String codigoClienteResponsavel, String nomeClienteResponsavel,
            String endereco, String numeroTelefone, String ramal,
            String tipoTelefone, String municipio, String indicadorUso,
            String pessoa) {
    	this.codigoCliente = codigoCliente;
        this.nome = nome;
        this.tipoCliente = tipoCliente;
        this.email = email;
        this.cnpj = cnpj;
        this.ramo = ramo;
        this.codigoClienteResponsavel = codigoClienteResponsavel;
        this.nomeClienteResponsavel = nomeClienteResponsavel;
        this.endereco = endereco;
        this.numeroTelefone = numeroTelefone;
        this.ramal = ramal;
        this.tipoTelefone = tipoTelefone;
        this.municipio = municipio;
        this.indicadorUso = indicadorUso;
        this.pessoa = pessoa;
    }

    /**
     * Gets the cnpj attribute of the RelatorioManterClienteBean object
     * 
     * @return The cnpj value
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Gets the codigoClienteResponsavel attribute of the
     * RelatorioManterClienteBean object
     * 
     * @return The codigoClienteResponsavel value
     */
    public String getCodigoClienteResponsavel() {
        return codigoClienteResponsavel;
    }

    /**
     * Gets the cpf attribute of the RelatorioManterClienteBean object
     * 
     * @return The cpf value
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Gets the dataEmissao attribute of the RelatorioManterClienteBean object
     * 
     * @return The dataEmissao value
     */
    public String getDataEmissao() {
        return dataEmissao;
    }

    /**
     * Gets the dataNascimento attribute of the RelatorioManterClienteBean
     * object
     * 
     * @return The dataNascimento value
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Gets the email attribute of the RelatorioManterClienteBean object
     * 
     * @return The email value
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the estado attribute of the RelatorioManterClienteBean object
     * 
     * @return The estado value
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Gets the municipio attribute of the RelatorioManterClienteBean object
     * 
     * @return The municipio value
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Gets the nome attribute of the RelatorioManterClienteBean object
     * 
     * @return The nome value
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets the nomeClienteResponsavel attribute of the
     * RelatorioManterClienteBean object
     * 
     * @return The nomeClienteResponsavel value
     */
    public String getNomeClienteResponsavel() {
        return nomeClienteResponsavel;
    }

    /**
     * Gets the numeroTelefone attribute of the RelatorioManterClienteBean
     * object
     * 
     * @return The numeroTelefone value
     */
    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    /**
     * Gets the orgaoExpeditor attribute of the RelatorioManterClienteBean
     * object
     * 
     * @return The orgaoExpeditor value
     */
    public String getOrgaoExpeditor() {
        return orgaoExpeditor;
    }

    /**
     * Gets the profissao attribute of the RelatorioManterClienteBean object
     * 
     * @return The profissao value
     */
    public String getProfissao() {
        return profissao;
    }

    /**
     * Gets the ramal attribute of the RelatorioManterClienteBean object
     * 
     * @return The ramal value
     */
    public String getRamal() {
        return ramal;
    }

    /**
     * Gets the ramo attribute of the RelatorioManterClienteBean object
     * 
     * @return The ramo value
     */
    public String getRamo() {
        return ramo;
    }

    /**
     * Sets the ramo attribute of the RelatorioManterClienteBean object
     * 
     * @param ramo
     *            The new ramo value
     */
    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    /**
     * Sets the ramal attribute of the RelatorioManterClienteBean object
     * 
     * @param ramal
     *            The new ramal value
     */
    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    /**
     * Sets the profissao attribute of the RelatorioManterClienteBean object
     * 
     * @param profissao
     *            The new profissao value
     */
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    /**
     * Sets the orgaoExpeditor attribute of the RelatorioManterClienteBean
     * object
     * 
     * @param orgaoExpeditor
     *            The new orgaoExpeditor value
     */
    public void setOrgaoExpeditor(String orgaoExpeditor) {
        this.orgaoExpeditor = orgaoExpeditor;
    }

    /**
     * Sets the numeroTelefone attribute of the RelatorioManterClienteBean
     * object
     * 
     * @param numeroTelefone
     *            The new numeroTelefone value
     */
    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    /**
     * Sets the nomeClienteResponsavel attribute of the
     * RelatorioManterClienteBean object
     * 
     * @param nomeClienteResponsavel
     *            The new nomeClienteResponsavel value
     */
    public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
        this.nomeClienteResponsavel = nomeClienteResponsavel;
    }

    /**
     * Sets the nome attribute of the RelatorioManterClienteBean object
     * 
     * @param nome
     *            The new nome value
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Sets the municipio attribute of the RelatorioManterClienteBean object
     * 
     * @param municipio
     *            The new municipio value
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * Sets the indicadorUso attribute of the RelatorioManterClienteBean object
     * 
     * @param indicadorUso
     *            The new indicadorUso value
     */
    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Sets the estado attribute of the RelatorioManterClienteBean object
     * 
     * @param estado
     *            The new estado value
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Sets the endereco attribute of the RelatorioManterClienteBean object
     * 
     * @param endereco
     *            The new endereco value
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Sets the email attribute of the RelatorioManterClienteBean object
     * 
     * @param email
     *            The new email value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the dataNascimento attribute of the RelatorioManterClienteBean
     * object
     * 
     * @param dataNascimento
     *            The new dataNascimento value
     */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Sets the dataEmissao attribute of the RelatorioManterClienteBean object
     * 
     * @param dataEmissao
     *            The new dataEmissao value
     */
    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * Sets the cpf attribute of the RelatorioManterClienteBean object
     * 
     * @param cpf
     *            The new cpf value
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Sets the codigoClienteResponsavel attribute of the
     * RelatorioManterClienteBean object
     * 
     * @param codigoClienteResponsavel
     *            The new codigoClienteResponsavel value
     */
    public void setCodigoClienteResponsavel(String codigoClienteResponsavel) {
        this.codigoClienteResponsavel = codigoClienteResponsavel;
    }

    /**
     * Sets the cnpj attribute of the RelatorioManterClienteBean object
     * 
     * @param cnpj
     *            The new cnpj value
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Gets the rg attribute of the RelatorioManterClienteBean object
     * 
     * @return The rg value
     */
    public String getRg() {
        return rg;
    }

    /**
     * Gets the sexo attribute of the RelatorioManterClienteBean object
     * 
     * @return The sexo value
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Gets the tipoCliente attribute of the RelatorioManterClienteBean object
     * 
     * @return The tipoCliente value
     */
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     * Gets the tipoTelefone attribute of the RelatorioManterClienteBean object
     * 
     * @return The tipoTelefone value
     */
    public String getTipoTelefone() {
        return tipoTelefone;
    }

    /**
     * Sets the rg attribute of the RelatorioManterClienteBean object
     * 
     * @param rg
     *            The new rg value
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * Sets the sexo attribute of the RelatorioManterClienteBean object
     * 
     * @param sexo
     *            The new sexo value
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Sets the tipoCliente attribute of the RelatorioManterClienteBean object
     * 
     * @param tipoCliente
     *            The new tipoCliente value
     */
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * Sets the tipoTelefone attribute of the RelatorioManterClienteBean object
     * 
     * @param tipoTelefone
     *            The new tipoTelefone value
     */
    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    /**
     * Gets the endereco attribute of the RelatorioManterClienteBean object
     * 
     * @return The endereco value
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Gets the indicadorUso attribute of the RelatorioManterClienteBean object
     * 
     * @return The indicadorUso value
     */
    public String getIndicadorUso() {
        return indicadorUso;
    }

    /**
     * Gets the pessoa attribute of the RelatorioManterClienteBean object
     * 
     * @return The pessoa value
     */
    public String getPessoa() {
        return pessoa;
    }

    /**
     * Sets the pessoa attribute of the RelatorioManterClienteBean object
     * 
     * @param pessoa
     *            The new pessoa value
     */
    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

	/**
	 * @return Retorna o campo codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente O codigoCliente a ser setado.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

}

/*
* Copyright (C) 2007-2007 the GSAN -Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place -Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN -Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.seguranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.funcionario.Funcionario;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConsultaCdl implements Serializable {
	private static final long serialVersionUID = 1L;
   
   //Ambos

   private Integer id;
	
   private String codigoDddComercial;
   
   private String telefoneComercialCliente;
   
   private String codigoDddFax;
   
   private String numeroFaxCliente;
   
   private String logradouroCliente;
   
   private String numeroImovelCliente;
   
   private String complementoEnderecoCliente;
   
   private String bairroCliente;
   
   private String cidadeCliente;
   
   private Integer cepCliente;
   
   private String uf;
   
   private Short codigoAcaoOperador;
   
   private Date ultimaAlteracao;
   
   private String cpfUsuario;
   
   private Cliente codigoCliente;
   
   private String loginUsuario;
   
   private Funcionario idFuncionario;
   
   private Usuario usuario;
   
   //Pessoa Física
   
   private String situacaoCpf;
   
   private String situacaoRg;
   
   private Date dataNascimento;
   
   private String estadoCivil;
   
   private Integer idadeCliente;
   
   private String nomeMae;
   
   private String nomePai;
   
   private String numeroRg;
   
   private String tituloEleitor;
   
   private String sexoCliente;
   
   private String signoCliente;
   
   private String cpfCliente;
   
   private String nomeCliente;
   
   private String codigoDddResidencial;
   
   private String telefoneResidencialCliente;
   
   private String codigoDddCelular;
   
   private String telefoneCelularCliente;
   
   //Pessoa Jurídica
   
   private String cnpjCliente;
   
   private String situacaoCnpj;
   
   private String situacaoInscricaoEstadual;
   
   private String naturezaJuridica;
   
   private String atividadeEconomicaPrincipal;
   
   private String atividadeEconomicaSecundaria;
   
   private Date dataFundacao;
   
   private String inscricaoEstadual;
   
   private String nomeComercial;
   
   private String numeroNireniec;
   
   private String razaoSocial;
   
   private String razaoSocialAnterior;
   
   private BigDecimal valorCapitalSocial;
   
   private String mensagemRetorno;
   
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCodigoDddComercial() {
		return codigoDddComercial;
	}
	
	public void setCodigoDddComercial(String codigoDddComercial) {
		this.codigoDddComercial = codigoDddComercial;
	}
	
	public String getTelefoneComercialCliente() {
		return telefoneComercialCliente;
	}
	
	public void setTelefoneComercialCliente(String telefoneComercialCliente) {
		this.telefoneComercialCliente = telefoneComercialCliente;
	}
	
	public String getCodigoDddFax() {
		return codigoDddFax;
	}
	
	public void setCodigoDddFax(String codigoDddFax) {
		this.codigoDddFax = codigoDddFax;
	}
	
	public String getNumeroFaxCliente() {
		return numeroFaxCliente;
	}
	
	public void setNumeroFaxCliente(String numeroFaxCliente) {
		this.numeroFaxCliente = numeroFaxCliente;
	}
	
	public String getLogradouroCliente() {
		return logradouroCliente;
	}
	
	public void setLogradouroCliente(String logradouroCliente) {
		this.logradouroCliente = logradouroCliente;
	}
	
	public String getNumeroImovelCliente() {
		return numeroImovelCliente;
	}
	
	public void setNumeroImovelCliente(String numeroImovelCliente) {
		this.numeroImovelCliente = numeroImovelCliente;
	}
	
	public String getComplementoEnderecoCliente() {
		return complementoEnderecoCliente;
	}
	
	public void setComplementoEnderecoCliente(String complementoEnderecoCliente) {
		this.complementoEnderecoCliente = complementoEnderecoCliente;
	}
	
	public String getCidadeCliente() {
		return cidadeCliente;
	}
	
	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}
	
	public Integer getCepCliente() {
		return cepCliente;
	}
	
	public void setCepCliente(Integer cepCliente) {
		this.cepCliente = cepCliente;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public Short getCodigoAcaoOperador() {
		return codigoAcaoOperador;
	}
	
	public void setCodigoAcaoOperador(Short codigoAcaoOperador) {
		this.codigoAcaoOperador = codigoAcaoOperador;
	}
	
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getCpfUsuario() {
		return cpfUsuario;
	}
	
	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	
	public Cliente getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setCodigoCliente(Cliente codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	public String getLoginUsuario() {
		return loginUsuario;
	}
	
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	
	public String getSituacaoCpf() {
		return situacaoCpf;
	}
	
	public void setSituacaoCpf(String situacaoCpf) {
		this.situacaoCpf = situacaoCpf;
	}
	
	public String getSituacaoRg() {
		return situacaoRg;
	}
	
	public void setSituacaoRg(String situacaoRg) {
		this.situacaoRg = situacaoRg;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public Integer getIdadeCliente() {
		return idadeCliente;
	}
	
	public void setIdadeCliente(Integer idadeCliente) {
		this.idadeCliente = idadeCliente;
	}
	
	public String getNomeMae() {
		return nomeMae;
	}
	
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	
	public String getNomePai() {
		return nomePai;
	}
	
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	
	public String getNumeroRg() {
		return numeroRg;
	}
	
	public void setNumeroRg(String numeroRg) {
		this.numeroRg = numeroRg;
	}
	
	public String getTituloEleitor() {
		return tituloEleitor;
	}
	
	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}
	
	public String getSexoCliente() {
		return sexoCliente;
	}
	
	public void setSexoCliente(String sexoCliente) {
		this.sexoCliente = sexoCliente;
	}
	
	public String getSignoCliente() {
		return signoCliente;
	}
	
	public void setSignoCliente(String signoCliente) {
		this.signoCliente = signoCliente;
	}
	
	public String getCpfCliente() {
		return cpfCliente;
	}
	
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getCodigoDddResidencial() {
		return codigoDddResidencial;
	}
	
	public void setCodigoDddResidencial(String codigoDddResidencial) {
		this.codigoDddResidencial = codigoDddResidencial;
	}
	
	public String getTelefoneResidencialCliente() {
		return telefoneResidencialCliente;
	}
	
	public void setTelefoneResidencialCliente(String telefoneResidencialCliente) {
		this.telefoneResidencialCliente = telefoneResidencialCliente;
	}
	
	public String getCodigoDddCelular() {
		return codigoDddCelular;
	}
	
	public void setCodigoDddCelular(String codigoDddCelular) {
		this.codigoDddCelular = codigoDddCelular;
	}
	
	public String getTelefoneCelularCliente() {
		return telefoneCelularCliente;
	}
	
	public void setTelefoneCelularCliente(String telefoneCelularCliente) {
		this.telefoneCelularCliente = telefoneCelularCliente;
	}
	
	public String getCnpjCliente() {
		return cnpjCliente;
	}
	
	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	
	public String getSituacaoCnpj() {
		return situacaoCnpj;
	}
	
	public void setSituacaoCnpj(String situacaoCnpj) {
		this.situacaoCnpj = situacaoCnpj;
	}
	
	public String getSituacaoInscricaoEstadual() {
		return situacaoInscricaoEstadual;
	}
	
	public void setSituacaoInscricaoEstadual(String situacaoInscricaoEstadual) {
		this.situacaoInscricaoEstadual = situacaoInscricaoEstadual;
	}
	
	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}
	
	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}
	
	public String getAtividadeEconomicaPrincipal() {
		return atividadeEconomicaPrincipal;
	}
	
	public void setAtividadeEconomicaPrincipal(String atividadeEconomicaPrincipal) {
		this.atividadeEconomicaPrincipal = atividadeEconomicaPrincipal;
	}
	
	public String getAtividadeEconomicaSecundaria() {
		return atividadeEconomicaSecundaria;
	}
	
	public void setAtividadeEconomicaSecundaria(String atividadeEconomicaSecundaria) {
		this.atividadeEconomicaSecundaria = atividadeEconomicaSecundaria;
	}
	
	public Date getDataFundacao() {
		return dataFundacao;
	}
	
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	
	public String getNomeComercial() {
		return nomeComercial;
	}
	
	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}
	
	public String getNumeroNireniec() {
		return numeroNireniec;
	}
	
	public void setNumeroNireniec(String numeroNireniec) {
		this.numeroNireniec = numeroNireniec;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getRazaoSocialAnterior() {
		return razaoSocialAnterior;
	}
	
	public void setRazaoSocialAnterior(String razaoSocialAnterior) {
		this.razaoSocialAnterior = razaoSocialAnterior;
	}
	
	
	public BigDecimal getValorCapitalSocial() {
		return valorCapitalSocial;
	}
	
	public void setValorCapitalSocial(BigDecimal valorCapitalSocial) {
		this.valorCapitalSocial = valorCapitalSocial;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getBairroCliente() {
		return bairroCliente;
	}
	
	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}
	
	public Funcionario getIdFuncionario() {
		return idFuncionario;
	}
	
	public void setIdFuncionario(Funcionario idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getMensagemRetorno() {
		return mensagemRetorno;
	}

	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

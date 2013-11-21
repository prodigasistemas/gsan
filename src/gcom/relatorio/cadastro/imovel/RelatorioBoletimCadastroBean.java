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
package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório de boletim de cadastro
 * 
 * @author Rafael Pinto
 * @created 18/01/2008
 */
public class RelatorioBoletimCadastroBean implements RelatorioBean , Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String numeroDocumentoReferencia;
	
	// Cliente Proprietario
	private String codigoClienteProprietario;
	private String nomeClienteProprietario;
	private String tipoClienteProprietario;
	private String cpfCnpjProprietario;
	private String rgProprietario;
	private String dataEmissaoProprietario;
	private String orgaoExpedidorProprietario;
	private String unidadeFederacaoProprietario;
	private String dataNascimentoProprietario;
	private String profissaoProprietario;
	private String sexoProprietario;
	private String nomeMaeProprietario;
	private String indicadorUsoProprietario;
	private String tipoEnderecoProprietario;
	private String codigoLogradouroProprietario;
	private String enderecoProprietario;
	private String cepProprietario;
	private String bairroProprietario;
	private String referenciaProprietario;
	private String numeroImovelProprietario;
	private String complementoProprietario;
	private String tipoTelefone1Proprietario;
	private String ddd1Proprietario;
	private String numero1TelefoneProprietario;
	private String ramal1Proprietario;
	private String tipoTelefone2Proprietario;
	private String ddd2Proprietario;
	private String numero2TelefoneProprietario;
	private String ramal2Proprietario;
	
	// Cliente Usuario
	private String codigoClienteUsuario;
	private String nomeClienteUsuario;
	private String tipoClienteUsuario;
	private String cpfCnpjUsuario;
	private String rgUsuario;
	private String dataEmissaoUsuario;
	private String orgaoExpedidorUsuario;
	private String unidadeFederacaoUsuario;
	private String dataNascimentoUsuario;
	private String profissaoUsuario;
	private String sexoUsuario;
	private String nomeMaeUsuario;
	private String indicadorUsoUsuario;
	private String tipoEnderecoUsuario;
	private String codigoLogradouroUsuario;
	private String enderecoUsuario;
	private String cepUsuario;
	private String bairroUsuario;
	private String referenciaUsuario;
	private String numeroImovelUsuario;
	private String complementoUsuario;
	private String tipoTelefone1Usuario;
	private String ddd1Usuario;
	private String numero1TelefoneUsuario;
	private String ramal1Usuario;
	private String tipoTelefone2Usuario;
	private String ddd2Usuario;
	private String numero2TelefoneUsuario;
	private String ramal2Usuario;
	
	// Informações do Imovel
	private String movimentoImovel;
	private String inscricaoAnteriorImovel;
	private String matriculaImovel;
	private String inscricaoAtualImovel; 
	private String numeroMoradores;
	private String nomeConta;
	private String codigoLogradouroImovel;
	private String enderecoImovel;
	private String cepImovel;
	private String bairroImovel;
	private String referenciaImovel;
	private String numeroImovel;
	private String complementoImovel;
	
	private String subCategoria1; 
	private String quantidadeEconomias1;
	private String subCategoria2; 
	private String quantidadeEconomias2;
	private String subCategoria3; 
	private String quantidadeEconomias3;
	private String subCategoria4; 
	private String quantidadeEconomias4;
	private String subCategoria5; 
	private String quantidadeEconomias5;
	private String subCategoria6; 
	private String quantidadeEconomias6;
	private String quantidadeApHotel; 
	private String areaConstruida;
	private String situacaoAgua;
	private String diamentroAgua;
	private String materiaAgua;
	private String volumeInferior;
	private String volumeSuperior;
	private String volumePiscina;
	private String jardim;
	private String pavimentacaoCalcada; 
	private String pavimentacaoRua;
	private String fonteAbastecimento;
	private String poco;
	private String numeroPontos;
	private String situacaoEsgoto;
	private String diamentroEsgoto;
	private String materiaEsgoto;
	private String perfilImovel;
	private String despejo;
	private String leituraInicial;
	private String capacidade;
	private String marca;
	private String local;
	private String protecao;
	private String cavalete;
	private String codigoRota;
	private String sequencialRota;
	private String valorDebitos;
	private String descricaoAbreviadaPrincipalCategoria;

	public String getNumeroDocumentoReferencia() {
		return numeroDocumentoReferencia;
	}

	public void setNumeroDocumentoReferencia(String numeroDocumentoReferencia) {
		this.numeroDocumentoReferencia = numeroDocumentoReferencia;
	}

	public String getAreaConstruida() {
		return areaConstruida;
	}


	public String getBairroImovel() {
		return bairroImovel;
	}


	public String getBairroProprietario() {
		return bairroProprietario;
	}


	public String getBairroUsuario() {
		return bairroUsuario;
	}


	public String getCapacidade() {
		return capacidade;
	}


	public String getCavalete() {
		return cavalete;
	}


	public String getCepImovel() {
		return cepImovel;
	}


	public String getCepProprietario() {
		return cepProprietario;
	}


	public String getCepUsuario() {
		return cepUsuario;
	}


	public String getCodigoClienteProprietario() {
		return codigoClienteProprietario;
	}


	public String getCodigoClienteUsuario() {
		return codigoClienteUsuario;
	}


	public String getCodigoLogradouroImovel() {
		return codigoLogradouroImovel;
	}


	public String getCodigoLogradouroProprietario() {
		return codigoLogradouroProprietario;
	}


	public String getCodigoLogradouroUsuario() {
		return codigoLogradouroUsuario;
	}


	public String getCodigoRota() {
		return codigoRota;
	}


	public String getComplementoImovel() {
		return complementoImovel;
	}


	public String getComplementoProprietario() {
		return complementoProprietario;
	}


	public String getComplementoUsuario() {
		return complementoUsuario;
	}


	public String getCpfCnpjProprietario() {
		return cpfCnpjProprietario;
	}


	public String getCpfCnpjUsuario() {
		return cpfCnpjUsuario;
	}


	public String getDataEmissaoProprietario() {
		return dataEmissaoProprietario;
	}


	public String getDataEmissaoUsuario() {
		return dataEmissaoUsuario;
	}


	public String getDataNascimentoProprietario() {
		return dataNascimentoProprietario;
	}


	public String getDataNascimentoUsuario() {
		return dataNascimentoUsuario;
	}


	public String getDdd1Proprietario() {
		return ddd1Proprietario;
	}


	public String getDdd1Usuario() {
		return ddd1Usuario;
	}


	public String getDdd2Proprietario() {
		return ddd2Proprietario;
	}


	public String getDdd2Usuario() {
		return ddd2Usuario;
	}


	public String getDespejo() {
		return despejo;
	}


	public String getDiamentroAgua() {
		return diamentroAgua;
	}


	public String getDiamentroEsgoto() {
		return diamentroEsgoto;
	}


	public String getEnderecoImovel() {
		return enderecoImovel;
	}


	public String getEnderecoProprietario() {
		return enderecoProprietario;
	}


	public String getEnderecoUsuario() {
		return enderecoUsuario;
	}


	public String getFonteAbastecimento() {
		return fonteAbastecimento;
	}


	public String getIndicadorUsoProprietario() {
		return indicadorUsoProprietario;
	}


	public String getIndicadorUsoUsuario() {
		return indicadorUsoUsuario;
	}


	public String getInscricaoAnteriorImovel() {
		return inscricaoAnteriorImovel;
	}


	public String getInscricaoAtualImovel() {
		return inscricaoAtualImovel;
	}


	public String getJardim() {
		return jardim;
	}


	public String getLeituraInicial() {
		return leituraInicial;
	}


	public String getLocal() {
		return local;
	}


	public String getMarca() {
		return marca;
	}


	public String getMateriaAgua() {
		return materiaAgua;
	}


	public String getMateriaEsgoto() {
		return materiaEsgoto;
	}


	public String getMatriculaImovel() {
		return matriculaImovel;
	}


	public String getMovimentoImovel() {
		return movimentoImovel;
	}


	public String getNomeClienteProprietario() {
		return nomeClienteProprietario;
	}


	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}


	public String getNomeConta() {
		return nomeConta;
	}


	public String getNomeMaeProprietario() {
		return nomeMaeProprietario;
	}


	public String getNomeMaeUsuario() {
		return nomeMaeUsuario;
	}


	public String getNumero1TelefoneProprietario() {
		return numero1TelefoneProprietario;
	}


	public String getNumero1TelefoneUsuario() {
		return numero1TelefoneUsuario;
	}


	public String getNumero2TelefoneProprietario() {
		return numero2TelefoneProprietario;
	}


	public String getNumero2TelefoneUsuario() {
		return numero2TelefoneUsuario;
	}


	public String getNumeroImovel() {
		return numeroImovel;
	}


	public String getNumeroImovelProprietario() {
		return numeroImovelProprietario;
	}


	public String getNumeroImovelUsuario() {
		return numeroImovelUsuario;
	}


	public String getNumeroMoradores() {
		return numeroMoradores;
	}


	public String getNumeroPontos() {
		return numeroPontos;
	}


	public String getOrgaoExpedidorProprietario() {
		return orgaoExpedidorProprietario;
	}


	public String getOrgaoExpedidorUsuario() {
		return orgaoExpedidorUsuario;
	}


	public String getPavimentacaoCalcada() {
		return pavimentacaoCalcada;
	}


	public String getPavimentacaoRua() {
		return pavimentacaoRua;
	}


	public String getPerfilImovel() {
		return perfilImovel;
	}


	public String getPoco() {
		return poco;
	}


	public String getProfissaoProprietario() {
		return profissaoProprietario;
	}


	public String getProfissaoUsuario() {
		return profissaoUsuario;
	}


	public String getProtecao() {
		return protecao;
	}


	public String getQuantidadeApHotel() {
		return quantidadeApHotel;
	}


	public String getQuantidadeEconomias1() {
		return quantidadeEconomias1;
	}


	public String getQuantidadeEconomias2() {
		return quantidadeEconomias2;
	}


	public String getQuantidadeEconomias3() {
		return quantidadeEconomias3;
	}


	public String getQuantidadeEconomias4() {
		return quantidadeEconomias4;
	}


	public String getQuantidadeEconomias5() {
		return quantidadeEconomias5;
	}


	public String getQuantidadeEconomias6() {
		return quantidadeEconomias6;
	}


	public String getRamal1Proprietario() {
		return ramal1Proprietario;
	}


	public String getRamal1Usuario() {
		return ramal1Usuario;
	}


	public String getRamal2Proprietario() {
		return ramal2Proprietario;
	}


	public String getRamal2Usuario() {
		return ramal2Usuario;
	}


	public String getReferenciaImovel() {
		return referenciaImovel;
	}


	public String getReferenciaProprietario() {
		return referenciaProprietario;
	}


	public String getReferenciaUsuario() {
		return referenciaUsuario;
	}


	public String getRgProprietario() {
		return rgProprietario;
	}


	public String getRgUsuario() {
		return rgUsuario;
	}


	public String getSequencialRota() {
		return sequencialRota;
	}


	public String getSexoProprietario() {
		return sexoProprietario;
	}


	public String getSexoUsuario() {
		return sexoUsuario;
	}


	public String getSituacaoAgua() {
		return situacaoAgua;
	}


	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}


	public String getSubCategoria1() {
		return subCategoria1;
	}


	public String getSubCategoria2() {
		return subCategoria2;
	}


	public String getSubCategoria3() {
		return subCategoria3;
	}


	public String getSubCategoria4() {
		return subCategoria4;
	}


	public String getSubCategoria5() {
		return subCategoria5;
	}


	public String getSubCategoria6() {
		return subCategoria6;
	}


	public String getTipoClienteProprietario() {
		return tipoClienteProprietario;
	}


	public String getTipoClienteUsuario() {
		return tipoClienteUsuario;
	}


	public String getTipoEnderecoProprietario() {
		return tipoEnderecoProprietario;
	}


	public String getTipoEnderecoUsuario() {
		return tipoEnderecoUsuario;
	}


	public String getTipoTelefone1Proprietario() {
		return tipoTelefone1Proprietario;
	}


	public String getTipoTelefone1Usuario() {
		return tipoTelefone1Usuario;
	}


	public String getTipoTelefone2Proprietario() {
		return tipoTelefone2Proprietario;
	}


	public String getTipoTelefone2Usuario() {
		return tipoTelefone2Usuario;
	}


	public String getUnidadeFederacaoProprietario() {
		return unidadeFederacaoProprietario;
	}


	public String getUnidadeFederacaoUsuario() {
		return unidadeFederacaoUsuario;
	}


	public String getValorDebitos() {
		return valorDebitos;
	}

	public String getVolumeInferior() {
		return volumeInferior;
	}

	public String getVolumePiscina() {
		return volumePiscina;
	}

	public String getVolumeSuperior() {
		return volumeSuperior;
	}
	
	public String getDescricaoAbreviadaPrincipalCategoria() {
		return descricaoAbreviadaPrincipalCategoria;
	}

	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public void setBairroImovel(String bairroImovel) {
		this.bairroImovel = bairroImovel;
	}

	public void setBairroProprietario(String bairroProprietario) {
		this.bairroProprietario = bairroProprietario;
	}

	public void setBairroUsuario(String bairroUsuario) {
		this.bairroUsuario = bairroUsuario;
	}

	public void setCapacidade(String capacidade) {
		this.capacidade = capacidade;
	}

	public void setCavalete(String cavalete) {
		this.cavalete = cavalete;
	}

	public void setCepImovel(String cepImovel) {
		this.cepImovel = cepImovel;
	}

	public void setCepProprietario(String cepProprietario) {
		this.cepProprietario = cepProprietario;
	}

	public void setCepUsuario(String cepUsuario) {
		this.cepUsuario = cepUsuario;
	}

	public void setCodigoClienteProprietario(String codigoClienteProprietario) {
		this.codigoClienteProprietario = codigoClienteProprietario;
	}

	public void setCodigoClienteUsuario(String codigoClienteUsuario) {
		this.codigoClienteUsuario = codigoClienteUsuario;
	}

	public void setCodigoLogradouroImovel(String codigoLogradouroImovel) {
		this.codigoLogradouroImovel = codigoLogradouroImovel;
	}

	public void setCodigoLogradouroProprietario(String codigoLogradouroProprietario) {
		this.codigoLogradouroProprietario = codigoLogradouroProprietario;
	}

	public void setCodigoLogradouroUsuario(String codigoLogradouroUsuario) {
		this.codigoLogradouroUsuario = codigoLogradouroUsuario;
	}


	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}


	public void setComplementoImovel(String complementoImovel) {
		this.complementoImovel = complementoImovel;
	}


	public void setComplementoProprietario(String complementoProprietario) {
		this.complementoProprietario = complementoProprietario;
	}


	public void setComplementoUsuario(String complementoUsuario) {
		this.complementoUsuario = complementoUsuario;
	}


	public void setCpfCnpjProprietario(String cpfCnpjProprietario) {
		this.cpfCnpjProprietario = cpfCnpjProprietario;
	}


	public void setCpfCnpjUsuario(String cpfCnpjUsuario) {
		this.cpfCnpjUsuario = cpfCnpjUsuario;
	}


	public void setDataEmissaoProprietario(String dataEmissaoProprietario) {
		this.dataEmissaoProprietario = dataEmissaoProprietario;
	}


	public void setDataEmissaoUsuario(String dataEmissaoUsuario) {
		this.dataEmissaoUsuario = dataEmissaoUsuario;
	}


	public void setDataNascimentoProprietario(String dataNascimentoProprietario) {
		this.dataNascimentoProprietario = dataNascimentoProprietario;
	}


	public void setDataNascimentoUsuario(String dataNascimentoUsuario) {
		this.dataNascimentoUsuario = dataNascimentoUsuario;
	}


	public void setDdd1Proprietario(String ddd1Proprietario) {
		this.ddd1Proprietario = ddd1Proprietario;
	}


	public void setDdd1Usuario(String ddd1Usuario) {
		this.ddd1Usuario = ddd1Usuario;
	}


	public void setDdd2Proprietario(String ddd2Proprietario) {
		this.ddd2Proprietario = ddd2Proprietario;
	}


	public void setDdd2Usuario(String ddd2Usuario) {
		this.ddd2Usuario = ddd2Usuario;
	}


	public void setDespejo(String despejo) {
		this.despejo = despejo;
	}


	public void setDiamentroAgua(String diamentroAgua) {
		this.diamentroAgua = diamentroAgua;
	}


	public void setDiamentroEsgoto(String diamentroEsgoto) {
		this.diamentroEsgoto = diamentroEsgoto;
	}


	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}


	public void setEnderecoProprietario(String enderecoProprietario) {
		this.enderecoProprietario = enderecoProprietario;
	}


	public void setEnderecoUsuario(String enderecoUsuario) {
		this.enderecoUsuario = enderecoUsuario;
	}


	public void setFonteAbastecimento(String fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}


	public void setIndicadorUsoProprietario(String indicadorUsoProprietario) {
		this.indicadorUsoProprietario = indicadorUsoProprietario;
	}


	public void setIndicadorUsoUsuario(String indicadorUsoUsuario) {
		this.indicadorUsoUsuario = indicadorUsoUsuario;
	}


	public void setInscricaoAnteriorImovel(String inscricaoAnteriorImovel) {
		this.inscricaoAnteriorImovel = inscricaoAnteriorImovel;
	}


	public void setInscricaoAtualImovel(String inscricaoAtualImovel) {
		this.inscricaoAtualImovel = inscricaoAtualImovel;
	}


	public void setJardim(String jardim) {
		this.jardim = jardim;
	}


	public void setLeituraInicial(String leituraInicial) {
		this.leituraInicial = leituraInicial;
	}


	public void setLocal(String local) {
		this.local = local;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public void setMateriaAgua(String materiaAgua) {
		this.materiaAgua = materiaAgua;
	}


	public void setMateriaEsgoto(String materiaEsgoto) {
		this.materiaEsgoto = materiaEsgoto;
	}


	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}


	public void setMovimentoImovel(String movimentoImovel) {
		this.movimentoImovel = movimentoImovel;
	}


	public void setNomeClienteProprietario(String nomeClienteProprietario) {
		this.nomeClienteProprietario = nomeClienteProprietario;
	}


	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}


	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}


	public void setNomeMaeProprietario(String nomeMaeProprietario) {
		this.nomeMaeProprietario = nomeMaeProprietario;
	}


	public void setNomeMaeUsuario(String nomeMaeUsuario) {
		this.nomeMaeUsuario = nomeMaeUsuario;
	}


	public void setNumero1TelefoneProprietario(String numero1TelefoneProprietario) {
		this.numero1TelefoneProprietario = numero1TelefoneProprietario;
	}


	public void setNumero1TelefoneUsuario(String numero1TelefoneUsuario) {
		this.numero1TelefoneUsuario = numero1TelefoneUsuario;
	}


	public void setNumero2TelefoneProprietario(String numero2TelefoneProprietario) {
		this.numero2TelefoneProprietario = numero2TelefoneProprietario;
	}


	public void setNumero2TelefoneUsuario(String numero2TelefoneUsuario) {
		this.numero2TelefoneUsuario = numero2TelefoneUsuario;
	}


	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}


	public void setNumeroImovelProprietario(String numeroImovelProprietario) {
		this.numeroImovelProprietario = numeroImovelProprietario;
	}


	public void setNumeroImovelUsuario(String numeroImovelUsuario) {
		this.numeroImovelUsuario = numeroImovelUsuario;
	}


	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}


	public void setNumeroPontos(String numeroPontos) {
		this.numeroPontos = numeroPontos;
	}


	public void setOrgaoExpedidorProprietario(String orgaoExpedidorProprietario) {
		this.orgaoExpedidorProprietario = orgaoExpedidorProprietario;
	}


	public void setOrgaoExpedidorUsuario(String orgaoExpedidorUsuario) {
		this.orgaoExpedidorUsuario = orgaoExpedidorUsuario;
	}


	public void setPavimentacaoCalcada(String pavimentacaoCalcada) {
		this.pavimentacaoCalcada = pavimentacaoCalcada;
	}


	public void setPavimentacaoRua(String pavimentacaoRua) {
		this.pavimentacaoRua = pavimentacaoRua;
	}


	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}


	public void setPoco(String poco) {
		this.poco = poco;
	}


	public void setProfissaoProprietario(String profissaoProprietario) {
		this.profissaoProprietario = profissaoProprietario;
	}


	public void setProfissaoUsuario(String profissaoUsuario) {
		this.profissaoUsuario = profissaoUsuario;
	}


	public void setProtecao(String protecao) {
		this.protecao = protecao;
	}


	public void setQuantidadeApHotel(String quantidadeApHotel) {
		this.quantidadeApHotel = quantidadeApHotel;
	}


	public void setQuantidadeEconomias1(String quantidadeEconomias1) {
		this.quantidadeEconomias1 = quantidadeEconomias1;
	}


	public void setQuantidadeEconomias2(String quantidadeEconomias2) {
		this.quantidadeEconomias2 = quantidadeEconomias2;
	}


	public void setQuantidadeEconomias3(String quantidadeEconomias3) {
		this.quantidadeEconomias3 = quantidadeEconomias3;
	}


	public void setQuantidadeEconomias4(String quantidadeEconomias4) {
		this.quantidadeEconomias4 = quantidadeEconomias4;
	}


	public void setQuantidadeEconomias5(String quantidadeEconomias5) {
		this.quantidadeEconomias5 = quantidadeEconomias5;
	}


	public void setQuantidadeEconomias6(String quantidadeEconomias6) {
		this.quantidadeEconomias6 = quantidadeEconomias6;
	}


	public void setRamal1Proprietario(String ramal1Proprietario) {
		this.ramal1Proprietario = ramal1Proprietario;
	}


	public void setRamal1Usuario(String ramal1Usuario) {
		this.ramal1Usuario = ramal1Usuario;
	}


	public void setRamal2Proprietario(String ramal2Proprietario) {
		this.ramal2Proprietario = ramal2Proprietario;
	}


	public void setRamal2Usuario(String ramal2Usuario) {
		this.ramal2Usuario = ramal2Usuario;
	}


	public void setReferenciaImovel(String referenciaImovel) {
		this.referenciaImovel = referenciaImovel;
	}


	public void setReferenciaProprietario(String referenciaProprietario) {
		this.referenciaProprietario = referenciaProprietario;
	}


	public void setReferenciaUsuario(String referenciaUsuario) {
		this.referenciaUsuario = referenciaUsuario;
	}


	public void setRgProprietario(String rgProprietario) {
		this.rgProprietario = rgProprietario;
	}


	public void setRgUsuario(String rgUsuario) {
		this.rgUsuario = rgUsuario;
	}


	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}


	public void setSexoProprietario(String sexoProprietario) {
		this.sexoProprietario = sexoProprietario;
	}


	public void setSexoUsuario(String sexoUsuario) {
		this.sexoUsuario = sexoUsuario;
	}


	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}


	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}


	public void setSubCategoria1(String subCategoria1) {
		this.subCategoria1 = subCategoria1;
	}


	public void setSubCategoria2(String subCategoria2) {
		this.subCategoria2 = subCategoria2;
	}


	public void setSubCategoria3(String subCategoria3) {
		this.subCategoria3 = subCategoria3;
	}


	public void setSubCategoria4(String subCategoria4) {
		this.subCategoria4 = subCategoria4;
	}


	public void setSubCategoria5(String subCategoria5) {
		this.subCategoria5 = subCategoria5;
	}


	public void setSubCategoria6(String subCategoria6) {
		this.subCategoria6 = subCategoria6;
	}


	public void setTipoClienteProprietario(String tipoClienteProprietario) {
		this.tipoClienteProprietario = tipoClienteProprietario;
	}


	public void setTipoClienteUsuario(String tipoClienteUsuario) {
		this.tipoClienteUsuario = tipoClienteUsuario;
	}


	public void setTipoEnderecoProprietario(String tipoEnderecoProprietario) {
		this.tipoEnderecoProprietario = tipoEnderecoProprietario;
	}


	public void setTipoEnderecoUsuario(String tipoEnderecoUsuario) {
		this.tipoEnderecoUsuario = tipoEnderecoUsuario;
	}


	public void setTipoTelefone1Proprietario(String tipoTelefone1Proprietario) {
		this.tipoTelefone1Proprietario = tipoTelefone1Proprietario;
	}


	public void setTipoTelefone1Usuario(String tipoTelefone1Usuario) {
		this.tipoTelefone1Usuario = tipoTelefone1Usuario;
	}


	public void setTipoTelefone2Proprietario(String tipoTelefone2Proprietario) {
		this.tipoTelefone2Proprietario = tipoTelefone2Proprietario;
	}


	public void setTipoTelefone2Usuario(String tipoTelefone2Usuario) {
		this.tipoTelefone2Usuario = tipoTelefone2Usuario;
	}


	public void setUnidadeFederacaoProprietario(String unidadeFederacaoProprietario) {
		this.unidadeFederacaoProprietario = unidadeFederacaoProprietario;
	}


	public void setUnidadeFederacaoUsuario(String unidadeFederacaoUsuario) {
		this.unidadeFederacaoUsuario = unidadeFederacaoUsuario;
	}


	public void setValorDebitos(String valorDebitos) {
		this.valorDebitos = valorDebitos;
	}


	public void setVolumeInferior(String volumeInferior) {
		this.volumeInferior = volumeInferior;
	}


	public void setVolumePiscina(String volumePiscina) {
		this.volumePiscina = volumePiscina;
	}


	public void setVolumeSuperior(String volumeSuperior) {
		this.volumeSuperior = volumeSuperior;
	}

	public void setDescricaoAbreviadaPrincipalCategoria(
			String descricaoAbreviadaPrincipalCategoria) {
		this.descricaoAbreviadaPrincipalCategoria = descricaoAbreviadaPrincipalCategoria;
	}
	
}

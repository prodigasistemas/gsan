/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1011] Emitir Boletim de Cadastro Individual.
 * 
 * classe responsável por criar o relatório de Boletim de Cadastro Individual
 * 
 * @author Hugo Leonardo
 * @date 24/03/2010
 * 
 */

public class RelatorioBoletimCadastroIndividualBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	// Proprietario
	private String nomeProprietario;
	private String sexoProprietario;
	private String cpfProprietario;
	private String cnpjProprietario;
	private String rgProprietario;
	private String ufProprietario;
	private String foneProprietario;
	private String foneTipoProprietario;
	private String enderecoProprietario;
	private String enderecoRefProprietario;
	private String idEnderecoRefProprietario;
	private String enderecoComplementoProprietario;
	private String bairroProprietario;
	private String municipioProprietario;
	private String cepProprietario;
	private String enderecoTipoProprietario;
	
	//Usuario
	private String nomeUsuario;
	private String sexoUsuario;
	private String cpfUsuario;
	private String cnpjUsuario;
	private String rgUsuario;
	private String ufUsuario;
	private String foneUsuario;
	private String foneTipoUsuario;
	
	//Imovel
	private String inscricao;
	private String matricula;
	private String perfilImovel;
	private String enderecoImovel;
	private String enderecoRefImovel;
	private String idEnderecoRefImovel;
	private String enderecoComplementoImovel;
	private String bairroImovel;
	private String municipioImovel;
	private String cepImovel;
	private String categoriaPrincipal;
	private String subcategorias;
	private String numeroEconomias;
	private String numeroMoradores;
	private String numeroMedidorCelpe;
	private String pavimentoTipoRua;
	private String pavimentoTipoCalcada;
	private String abastecimentoFonte;
	private String esgotoSituacao;
	private String aguaSituacao;
	private String hidrometro;
	private String hidrometroNumero;
	private String hidrometroMarca;
	private String hidrometroCapacidade;
	private String localInstalacao;
	private String protecaoTipo;
	private String cavalete;
	private String ocorrenciaCadastro;
	
	
	public RelatorioBoletimCadastroIndividualBean() {
		
	}

	public String getBairroProprietario() {
		return bairroProprietario;
	}

	public void setBairroProprietario(String bairroProprietario) {
		this.bairroProprietario = bairroProprietario;
	}

	public String getCepProprietario() {
		return cepProprietario;
	}

	public void setCepProprietario(String cepProprietario) {
		this.cepProprietario = cepProprietario;
	}

	public String getCnpjProprietario() {
		return cnpjProprietario;
	}

	public void setCnpjProprietario(String cnpjProprietario) {
		this.cnpjProprietario = cnpjProprietario;
	}

	public String getCnpjUsuario() {
		return cnpjUsuario;
	}

	public void setCnpjUsuario(String cnpjUsuario) {
		this.cnpjUsuario = cnpjUsuario;
	}

	public String getCpfProprietario() {
		return cpfProprietario;
	}

	public void setCpfProprietario(String cpfProprietario) {
		this.cpfProprietario = cpfProprietario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getEnderecoProprietario() {
		return enderecoProprietario;
	}

	public void setEnderecoProprietario(String enderecoProprietario) {
		this.enderecoProprietario = enderecoProprietario;
	}

	public String getEnderecoTipoProprietario() {
		return enderecoTipoProprietario;
	}

	public void setEnderecoTipoProprietario(String enderecoTipoProprietario) {
		this.enderecoTipoProprietario = enderecoTipoProprietario;
	}

	public String getFoneProprietario() {
		return foneProprietario;
	}

	public void setFoneProprietario(String foneProprietario) {
		this.foneProprietario = foneProprietario;
	}

	public String getFoneTipoProprietario() {
		return foneTipoProprietario;
	}

	public void setFoneTipoProprietario(String foneTipoProprietario) {
		this.foneTipoProprietario = foneTipoProprietario;
	}

	public String getFoneTipoUsuario() {
		return foneTipoUsuario;
	}

	public void setFoneTipoUsuario(String foneTipoUsuario) {
		this.foneTipoUsuario = foneTipoUsuario;
	}

	public String getFoneUsuario() {
		return foneUsuario;
	}

	public void setFoneUsuario(String foneUsuario) {
		this.foneUsuario = foneUsuario;
	}

	public String getMunicipioProprietario() {
		return municipioProprietario;
	}

	public void setMunicipioProprietario(String municipioProprietario) {
		this.municipioProprietario = municipioProprietario;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getRgProprietario() {
		return rgProprietario;
	}

	public void setRgProprietario(String rgProprietario) {
		this.rgProprietario = rgProprietario;
	}

	public String getRgUsuario() {
		return rgUsuario;
	}

	public void setRgUsuario(String rgUsuario) {
		this.rgUsuario = rgUsuario;
	}

	public String getSexoProprietario() {
		return sexoProprietario;
	}

	public void setSexoProprietario(String sexoProprietario) {
		this.sexoProprietario = sexoProprietario;
	}

	public String getSexoUsuario() {
		return sexoUsuario;
	}

	public void setSexoUsuario(String sexoUsuario) {
		this.sexoUsuario = sexoUsuario;
	}

	public String getUfProprietario() {
		return ufProprietario;
	}

	public void setUfProprietario(String ufProprietario) {
		this.ufProprietario = ufProprietario;
	}

	public String getUfUsuario() {
		return ufUsuario;
	}

	public void setUfUsuario(String ufUsuario) {
		this.ufUsuario = ufUsuario;
	}

	public String getAbastecimentoFonte() {
		return abastecimentoFonte;
	}

	public void setAbastecimentoFonte(String abastecimentoFonte) {
		this.abastecimentoFonte = abastecimentoFonte;
	}

	public String getAguaSituacao() {
		return aguaSituacao;
	}

	public void setAguaSituacao(String aguaSituacao) {
		this.aguaSituacao = aguaSituacao;
	}

	public String getBairroImovel() {
		return bairroImovel;
	}

	public void setBairroImovel(String bairroImovel) {
		this.bairroImovel = bairroImovel;
	}

	public String getCavalete() {
		return cavalete;
	}

	public void setCavalete(String cavalete) {
		this.cavalete = cavalete;
	}

	public String getCepImovel() {
		return cepImovel;
	}

	public void setCepImovel(String cepImovel) {
		this.cepImovel = cepImovel;
	}

	public String getEnderecoComplementoImovel() {
		return enderecoComplementoImovel;
	}

	public void setEnderecoComplementoImovel(String enderecoComplementoImovel) {
		this.enderecoComplementoImovel = enderecoComplementoImovel;
	}

	public String getEnderecoComplementoProprietario() {
		return enderecoComplementoProprietario;
	}

	public void setEnderecoComplementoProprietario(
			String enderecoComplementoProprietario) {
		this.enderecoComplementoProprietario = enderecoComplementoProprietario;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getEnderecoRefImovel() {
		return enderecoRefImovel;
	}

	public void setEnderecoRefImovel(String enderecoRefImovel) {
		this.enderecoRefImovel = enderecoRefImovel;
	}

	public String getEnderecoRefProprietario() {
		return enderecoRefProprietario;
	}

	public void setEnderecoRefProprietario(String enderecoRefProprietario) {
		this.enderecoRefProprietario = enderecoRefProprietario;
	}

	public String getEsgotoSituacao() {
		return esgotoSituacao;
	}

	public void setEsgotoSituacao(String esgotoSituacao) {
		this.esgotoSituacao = esgotoSituacao;
	}

	public String getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}

	public String getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}

	public void setHidrometroCapacidade(String hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public String getHidrometroMarca() {
		return hidrometroMarca;
	}

	public void setHidrometroMarca(String hidrometroMarca) {
		this.hidrometroMarca = hidrometroMarca;
	}

	public String getHidrometroNumero() {
		return hidrometroNumero;
	}

	public void setHidrometroNumero(String hidrometroNumero) {
		this.hidrometroNumero = hidrometroNumero;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLocalInstalacao() {
		return localInstalacao;
	}

	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMunicipioImovel() {
		return municipioImovel;
	}

	public void setMunicipioImovel(String municipioImovel) {
		this.municipioImovel = municipioImovel;
	}

	public String getNumeroMedidorCelpe() {
		return numeroMedidorCelpe;
	}

	public void setNumeroMedidorCelpe(String numeroMedidorCelpe) {
		this.numeroMedidorCelpe = numeroMedidorCelpe;
	}

	public String getNumeroMoradores() {
		return numeroMoradores;
	}

	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public String getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public void setOcorrenciaCadastro(String ocorrenciaCadastro) {
		this.ocorrenciaCadastro = ocorrenciaCadastro;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getProtecaoTipo() {
		return protecaoTipo;
	}

	public void setProtecaoTipo(String protecaoTipo) {
		this.protecaoTipo = protecaoTipo;
	}

	public String getPavimentoTipoCalcada() {
		return pavimentoTipoCalcada;
	}

	public void setPavimentoTipoCalcada(String pavimentoTipoCalcada) {
		this.pavimentoTipoCalcada = pavimentoTipoCalcada;
	}

	public String getPavimentoTipoRua() {
		return pavimentoTipoRua;
	}

	public void setPavimentoTipoRua(String pavimentoTipoRua) {
		this.pavimentoTipoRua = pavimentoTipoRua;
	}

	public String getCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	public void setCategoriaPrincipal(String categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
	}

	public String getNumeroEconomias() {
		return numeroEconomias;
	}

	public void setNumeroEconomias(String numeroEconomias) {
		this.numeroEconomias = numeroEconomias;
	}

	public String getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(String subcategorias) {
		this.subcategorias = subcategorias;
	}

	public String getIdEnderecoRefProprietario() {
		return idEnderecoRefProprietario;
	}

	public void setIdEnderecoRefProprietario(String idEnderecoRefProprietario) {
		this.idEnderecoRefProprietario = idEnderecoRefProprietario;
	}

	public String getIdEnderecoRefImovel() {
		return idEnderecoRefImovel;
	}

	public void setIdEnderecoRefImovel(String idEnderecoRefImovel) {
		this.idEnderecoRefImovel = idEnderecoRefImovel;
	}
	
}

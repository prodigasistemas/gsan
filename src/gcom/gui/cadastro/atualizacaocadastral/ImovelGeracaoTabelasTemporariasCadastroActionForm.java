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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0830] Filtro Para Geracao de Tabelas Temporarias para Atualizacao Cadastral
 * 
 * @author Vinicius Medeiros
 * @created 05/08/2008
 */
public class ImovelGeracaoTabelasTemporariasCadastroActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	// Parametros
	private String matricula;
	private String nomeImovel;
	private String cliente;
	private String clienteNome;
	private String sugestao = ConstantesSistema.SIM.toString();
	private String firma;
	private String nomeFirma;
	private String quantidadeMaxima;
	private String elo;
	private String nomeElo;
	private String inscricaoTipo;
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	private String setorComercialInicial;
	private String codigoSetorComercialInicial;
	private String nomeSetorComercialInicial;
	private String setorComercialFinal;
	private String codigoSetorComercialFinal;
	private String nomeSetorComercialFinal;
	private String quadraInicial;
	private String idQuadraInicial;
	private String quadraFinal;
	private String idQuadraFinal;
	private String rotaInicial;
	private String rotaSequenciaInicial;
	private String rotaFinal;
	private String rotaSequenciaFinal;
	private String idSituacaoLigacaoAgua;
	private String imovelSituacao = ConstantesSistema.SIM.toString();
	
	// Caracteristicas
	private String perfilImovel;
	private String perfilImovelDescricao;
	private String categoria;
	private String categoriaDescricao;
	private String subCategoria;
	private String subCategoriaDescricao;

	

	public String getElo() {
		return elo;
	}
	public void setElo(String elo) {
		this.elo = elo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCategoriaDescricao() {
		return categoriaDescricao;
	}
	public void setCategoriaDescricao(String categoriaDescricao) {
		this.categoriaDescricao = categoriaDescricao;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getIdQuadraFinal() {
		return idQuadraFinal;
	}
	public void setIdQuadraFinal(String idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}
	public String getIdQuadraInicial() {
		return idQuadraInicial;
	}
	public void setIdQuadraInicial(String idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}
	public String getInscricaoTipo() {
		return inscricaoTipo;
	}
	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}
	public String getLocalidadeFinal() {
		return localidadeFinal;
	}
	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}
	public String getLocalidadeInicial() {
		return localidadeInicial;
	}
	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeElo() {
		return nomeElo;
	}
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}
	public String getNomeFirma() {
		return nomeFirma;
	}
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	public String getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String getPerfilImovelDescricao() {
		return perfilImovelDescricao;
	}
	public void setPerfilImovelDescricao(String perfilImovelDescricao) {
		this.perfilImovelDescricao = perfilImovelDescricao;
	}
	public String getQuadraFinal() {
		return quadraFinal;
	}
	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}
	public String getQuadraInicial() {
		return quadraInicial;
	}
	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}
	public String getQuantidadeMaxima() {
		return quantidadeMaxima;
	}
	public void setQuantidadeMaxima(String quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}
	public String getRotaFinal() {
		return rotaFinal;
	}
	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}
	public String getRotaInicial() {
		return rotaInicial;
	}
	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}
	public String getRotaSequenciaFinal() {
		return rotaSequenciaFinal;
	}
	public void setRotaSequenciaFinal(String rotaSequenciaFinal) {
		this.rotaSequenciaFinal = rotaSequenciaFinal;
	}
	public String getRotaSequenciaInicial() {
		return rotaSequenciaInicial;
	}
	public void setRotaSequenciaInicial(String rotaSequenciaInicial) {
		this.rotaSequenciaInicial = rotaSequenciaInicial;
	}
	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}
	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}
	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}
	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	public String getSubCategoriaDescricao() {
		return subCategoriaDescricao;
	}
	public void setSubCategoriaDescricao(String subCategoriaDescricao) {
		this.subCategoriaDescricao = subCategoriaDescricao;
	}
	public String getSugestao() {
		return sugestao;
	}
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}
	
	public String getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}
	public void setIdSituacaoLigacaoAgua(String idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}
	public String getImovelSituacao() {
		return imovelSituacao;
	}
	public void setImovelSituacao(String imovelSituacao) {
		this.imovelSituacao = imovelSituacao;
	}
	public String getClienteNome() {
		return clienteNome;
	}
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	public String getNomeImovel() {
		return nomeImovel;
	}
	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}
	
}


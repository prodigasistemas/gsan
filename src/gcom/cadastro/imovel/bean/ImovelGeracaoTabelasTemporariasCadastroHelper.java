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
package gcom.cadastro.imovel.bean;

import java.io.Serializable;
import java.util.Collection;

public class ImovelGeracaoTabelasTemporariasCadastroHelper  implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * [UC0831] Gerar Tabelas Temporarias para Atualizacao Cadastral
	 *
	 *
	 * @author Vinicius
	 * @date 10/09/2008
	 *
	 */

	private Collection colecaoMatriculas;
	private String matricula;
	private String nomeMatricula;
	private String cliente;
	private String nomeCliente;
	private String sugestao;
	private String firma;
	private String nomeFirma;
	private String leiturista;
	private String nomeLeiturista;
	private Integer quantidadeMaxima;
	private String elo;
	private String nomeElo;
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	private String setorComercialInicial;
	private String nomeSetorComercialInicial;
	private String setorComercialFinal;
	private String nomeSetorComercialFinal;
	private String codigoSetorComercialInicial;
	private String codigoSetorComercialFinal;
	private String idQuadraInicial;
	private String idQuadraFinal;
	private String quadraInicial;
	private String quadraFinal;
	private Integer rotaInicial;
	private Integer rotaSequenciaInicial;
	private Integer rotaFinal;
	private Integer rotaSequenciaFinal;
	private String perfilImovel;
	private String nomePerfilImovel;
	private String categoria;
	private String nomeCategoria;
	private String subCategoria;
	private String nomeSubCategoria;
	private String idSituacaoLigacaoAgua;
	private String nomeSituacaoLigacaoAgua;
	private String imovelSituacao;
	private String msgQuadraInicial;
	private String msgQuadraFinal;
	private String quadraMensagemOrigem;
	
	
	private Collection colecaoImovel;


	/**
	 * @return Returns the categoria.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria The categoria to set.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Returns the cliente.
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente The cliente to set.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Returns the codigoSetorComercialFinal.
	 */
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	/**
	 * @param codigoSetorComercialFinal The codigoSetorComercialFinal to set.
	 */
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	/**
	 * @return Returns the codigoSetorComercialInicial.
	 */
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	/**
	 * @param codigoSetorComercialInicial The codigoSetorComercialInicial to set.
	 */
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	/**
	 * @return Returns the colecaoImovel.
	 */
	public Collection getColecaoImovel() {
		return colecaoImovel;
	}

	/**
	 * @param colecaoImovel The colecaoImovel to set.
	 */
	public void setColecaoImovel(Collection colecaoImovel) {
		this.colecaoImovel = colecaoImovel;
	}

	/**
	 * @return Returns the colecaoMatriculas.
	 */
	public Collection getColecaoMatriculas() {
		return colecaoMatriculas;
	}

	/**
	 * @param colecaoMatriculas The colecaoMatriculas to set.
	 */
	public void setColecaoMatriculas(Collection colecaoMatriculas) {
		this.colecaoMatriculas = colecaoMatriculas;
	}

	/**
	 * @return Returns the elo.
	 */
	public String getElo() {
		return elo;
	}

	/**
	 * @param elo The elo to set.
	 */
	public void setElo(String elo) {
		this.elo = elo;
	}

	/**
	 * @return Returns the firma.
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * @param firma The firma to set.
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}

	/**
	 * @return Returns the idQuadraFinal.
	 */
	public String getIdQuadraFinal() {
		return idQuadraFinal;
	}

	/**
	 * @param idQuadraFinal The idQuadraFinal to set.
	 */
	public void setIdQuadraFinal(String idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}

	/**
	 * @return Returns the idQuadraInicial.
	 */
	public String getIdQuadraInicial() {
		return idQuadraInicial;
	}

	/**
	 * @param idQuadraInicial The idQuadraInicial to set.
	 */
	public void setIdQuadraInicial(String idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}

	/**
	 * @return Returns the idSituacaoLigacaoAgua.
	 */
	public String getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	/**
	 * @param idSituacaoLigacaoAgua The idSituacaoLigacaoAgua to set.
	 */
	public void setIdSituacaoLigacaoAgua(String idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	/**
	 * @return Returns the imovelSituacao.
	 */
	public String getImovelSituacao() {
		return imovelSituacao;
	}

	/**
	 * @param imovelSituacao The imovelSituacao to set.
	 */
	public void setImovelSituacao(String imovelSituacao) {
		this.imovelSituacao = imovelSituacao;
	}

	/**
	 * @return Returns the localidadeFinal.
	 */
	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	/**
	 * @param localidadeFinal The localidadeFinal to set.
	 */
	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	/**
	 * @return Returns the localidadeInicial.
	 */
	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	/**
	 * @param localidadeInicial The localidadeInicial to set.
	 */
	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	/**
	 * @return Returns the matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula The matricula to set.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Returns the nomeElo.
	 */
	public String getNomeElo() {
		return nomeElo;
	}

	/**
	 * @param nomeElo The nomeElo to set.
	 */
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}

	/**
	 * @return Returns the nomeLocalidadeFinal.
	 */
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	/**
	 * @param nomeLocalidadeFinal The nomeLocalidadeFinal to set.
	 */
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	/**
	 * @return Returns the nomeLocalidadeInicial.
	 */
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	/**
	 * @param nomeLocalidadeInicial The nomeLocalidadeInicial to set.
	 */
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	/**
	 * @return Returns the nomeMatricula.
	 */
	public String getNomeMatricula() {
		return nomeMatricula;
	}

	/**
	 * @param nomeMatricula The nomeMatricula to set.
	 */
	public void setNomeMatricula(String nomeMatricula) {
		this.nomeMatricula = nomeMatricula;
	}

	/**
	 * @return Returns the nomeSetorComercialFinal.
	 */
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	/**
	 * @param nomeSetorComercialFinal The nomeSetorComercialFinal to set.
	 */
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	/**
	 * @return Returns the nomeSetorComercialInicial.
	 */
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	/**
	 * @param nomeSetorComercialInicial The nomeSetorComercialInicial to set.
	 */
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	/**
	 * @return Returns the perfilImovel.
	 */
	public String getPerfilImovel() {
		return perfilImovel;
	}

	/**
	 * @param perfilImovel The perfilImovel to set.
	 */
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	/**
	 * @return Returns the quadraFinal.
	 */
	public String getQuadraFinal() {
		return quadraFinal;
	}

	/**
	 * @param quadraFinal The quadraFinal to set.
	 */
	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	/**
	 * @return Returns the quadraInicial.
	 */
	public String getQuadraInicial() {
		return quadraInicial;
	}

	/**
	 * @param quadraInicial The quadraInicial to set.
	 */
	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	/**
	 * @return Returns the quantidadeMaxima.
	 */
	public Integer getQuantidadeMaxima() {
		return quantidadeMaxima;
	}

	/**
	 * @param quantidadeMaxima The quantidadeMaxima to set.
	 */
	public void setQuantidadeMaxima(Integer quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}

	/**
	 * @return Returns the rotaFinal.
	 */
	public Integer getRotaFinal() {
		return rotaFinal;
	}

	/**
	 * @param rotaFinal The rotaFinal to set.
	 */
	public void setRotaFinal(Integer rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	/**
	 * @return Returns the rotaInicial.
	 */
	public Integer getRotaInicial() {
		return rotaInicial;
	}

	/**
	 * @param rotaInicial The rotaInicial to set.
	 */
	public void setRotaInicial(Integer rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	/**
	 * @return Returns the rotaSequenciaFinal.
	 */
	public Integer getRotaSequenciaFinal() {
		return rotaSequenciaFinal;
	}

	/**
	 * @param rotaSequenciaFinal The rotaSequenciaFinal to set.
	 */
	public void setRotaSequenciaFinal(Integer rotaSequenciaFinal) {
		this.rotaSequenciaFinal = rotaSequenciaFinal;
	}

	/**
	 * @return Returns the rotaSequenciaInicial.
	 */
	public Integer getRotaSequenciaInicial() {
		return rotaSequenciaInicial;
	}

	/**
	 * @param rotaSequenciaInicial The rotaSequenciaInicial to set.
	 */
	public void setRotaSequenciaInicial(Integer rotaSequenciaInicial) {
		this.rotaSequenciaInicial = rotaSequenciaInicial;
	}

	/**
	 * @return Returns the setorComercialFinal.
	 */
	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}

	/**
	 * @param setorComercialFinal The setorComercialFinal to set.
	 */
	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	/**
	 * @return Returns the setorComercialInicial.
	 */
	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}

	/**
	 * @param setorComercialInicial The setorComercialInicial to set.
	 */
	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	/**
	 * @return Returns the subCategoria.
	 */
	public String getSubCategoria() {
		return subCategoria;
	}

	/**
	 * @param subCategoria The subCategoria to set.
	 */
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	/**
	 * @return Returns the sugestao.
	 */
	public String getSugestao() {
		return sugestao;
	}

	/**
	 * @param sugestao The sugestao to set.
	 */
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}

	/**
	 * @return Returns the msgQuadraInicial.
	 */
	public String getMsgQuadraInicial() {
		return msgQuadraInicial;
	}

	/**
	 * @param msgQuadraInicial The msgQuadraInicial to set.
	 */
	public void setMsgQuadraInicial(String msgQuadraInicial) {
		this.msgQuadraInicial = msgQuadraInicial;
	}

	/**
	 * @return Returns the quadraMensagemOrigem.
	 */
	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}

	/**
	 * @param quadraMensagemOrigem The quadraMensagemOrigem to set.
	 */
	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}

	/**
	 * @return Returns the msgQuadraFinal.
	 */
	public String getMsgQuadraFinal() {
		return msgQuadraFinal;
	}

	/**
	 * @param msgQuadraFinal The msgQuadraFinal to set.
	 */
	public void setMsgQuadraFinal(String msgQuadraFinal) {
		this.msgQuadraFinal = msgQuadraFinal;
	}

	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Returns the nomeCategoria.
	 */
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	/**
	 * @param nomeCategoria The nomeCategoria to set.
	 */
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	/**
	 * @return Returns the nomePerfilImovel.
	 */
	public String getNomePerfilImovel() {
		return nomePerfilImovel;
	}

	/**
	 * @param nomePerfilImovel The nomePerfilImovel to set.
	 */
	public void setNomePerfilImovel(String nomePerfilImovel) {
		this.nomePerfilImovel = nomePerfilImovel;
	}

	/**
	 * @return Returns the nomeFirma.
	 */
	public String getNomeFirma() {
		return nomeFirma;
	}

	/**
	 * @param nomeFirma The nomeFirma to set.
	 */
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}

	/**
	 * @return Returns the nomeSubCategoria.
	 */
	public String getNomeSubCategoria() {
		return nomeSubCategoria;
	}

	/**
	 * @param nomeSubCategoria The nomeSubCategoria to set.
	 */
	public void setNomeSubCategoria(String nomeSubCategoria) {
		this.nomeSubCategoria = nomeSubCategoria;
	}

	/**
	 * @return Returns the nomeSituacaoLigacaoAgua.
	 */
	public String getNomeSituacaoLigacaoAgua() {
		return nomeSituacaoLigacaoAgua;
	}

	/**
	 * @param nomeSituacaoLigacaoAgua The nomeSituacaoLigacaoAgua to set.
	 */
	public void setNomeSituacaoLigacaoAgua(String nomeSituacaoLigacaoAgua) {
		this.nomeSituacaoLigacaoAgua = nomeSituacaoLigacaoAgua;
	}

	public String getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(String leiturista) {
		this.leiturista = leiturista;
	}

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}
}

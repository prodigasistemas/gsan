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
package gcom.spcserasa.bean;

import java.util.Collection;


/**
 * [UC 0681] Pesquisar Negativador Movimento
 * 
 * @author Yara Taciane
 * @date 21/01/2008
 */

public class NegativadorMovimentoHelper implements Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idNegativador;
	private String negativadorDescricao;
	private short codigoMovimento;
	private Integer idImovel;
	private Integer numeroSequencialArquivo;
	private String dataProcessamentoInicial;
	private String dataProcessamentoFinal;
	private short indicadorRegistro;
	private short indicadorMovimento;
	private short indicadorCorrigido;
	private Integer idNegativadorMovimento;

	private Collection colecaoGerenciaRegional;
	private Collection colecaoUnidadeNegocio;
	private Integer idLocalidade;
	private Integer idLocalidadePolo;
	private Collection colecaoMotivoRejeicao;
	
	public NegativadorMovimentoHelper(
			Integer idNegativador,		
			short codigoMovimento,
			Integer idImovel,
			Integer numeroSequencialArquivo,
			String dataProcessamentoInicial,
			String dataProcessamentoFinal,
			short indicadorMovimento,
			short indicadorRegistro,
			short indicadorCorrigido) {
		
		this.idNegativador = idNegativador;		
		this.codigoMovimento = codigoMovimento;
		this.idImovel = idImovel;
		this.numeroSequencialArquivo = numeroSequencialArquivo;
		this.dataProcessamentoInicial = dataProcessamentoInicial;
		this.dataProcessamentoFinal = dataProcessamentoFinal;	
		this.indicadorRegistro = indicadorRegistro;
		this.indicadorMovimento = indicadorMovimento;
		this.indicadorCorrigido = indicadorCorrigido;
		
	}
	
	
	
	public NegativadorMovimentoHelper(
			Integer idNegativador,
			String negativadorDescricao,
			short codigoMovimento,
			Integer numeroSequencialArquivo,
			String dataProcessamentoInicial,
			String dataProcessamentoFinal,
			short indicadorRegistro,
			short indicadorCorrigido) {
		
		this.idNegativador = idNegativador;
		this.negativadorDescricao = negativadorDescricao;
		this.codigoMovimento = codigoMovimento;
		this.numeroSequencialArquivo = numeroSequencialArquivo;
		this.dataProcessamentoInicial = dataProcessamentoInicial;
		this.dataProcessamentoFinal = dataProcessamentoFinal;	
		this.indicadorRegistro = indicadorRegistro;
		this.indicadorCorrigido = indicadorCorrigido;
		
	}

	
	
	public NegativadorMovimentoHelper(
			Integer idNegativador,		
			short codigoMovimento,
			Integer numeroSequencialArquivo,
			String dataProcessamentoInicial,
			String dataProcessamentoFinal,
			short indicadorMovimento,
			short indicadorRegistro,
			short indicadorCorrigido,
			Integer idNegativadorMovimento) {
		
		this.idNegativador = idNegativador;		
		this.codigoMovimento = codigoMovimento;
		this.numeroSequencialArquivo = numeroSequencialArquivo;
		this.dataProcessamentoInicial = dataProcessamentoInicial;
		this.dataProcessamentoFinal = dataProcessamentoFinal;	
		this.indicadorRegistro = indicadorRegistro;
		this.indicadorMovimento = indicadorMovimento;
		this.indicadorCorrigido = indicadorCorrigido;
		this.idNegativadorMovimento = idNegativadorMovimento;
		
	}
	
	@Override
	public NegativadorMovimentoHelper clone() {
		try { return (NegativadorMovimentoHelper) super.clone(); } catch (CloneNotSupportedException e) { return null; }
	}
	
	


	/**
	 * @return Retorna o campo codigoMovimento.
	 */
	public short getCodigoMovimento() {
		return codigoMovimento;
	}



	/**
	 * @param codigoMovimento O codigoMovimento a ser setado.
	 */
	public void setCodigoMovimento(short codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}





	/**
	 * @return Retorna o campo idNegativador.
	 */
	public Integer getIdNegativador() {
		return idNegativador;
	}



	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(Integer idNegativador) {
		this.idNegativador = idNegativador;
	}



	/**
	 * @return Retorna o campo negativadorDescricao.
	 */
	public String getNegativadorDescricao() {
		return negativadorDescricao;
	}



	/**
	 * @param negativadorDescricao O negativadorDescricao a ser setado.
	 */
	public void setNegativadorDescricao(String negativadorDescricao) {
		this.negativadorDescricao = negativadorDescricao;
	}



	/**
	 * @return Retorna o campo numeroSequencialArquivo.
	 */
	public Integer getNumeroSequencialArquivo() {
		return numeroSequencialArquivo;
	}



	/**
	 * @param numeroSequencialArquivo O numeroSequencialArquivo a ser setado.
	 */
	public void setNumeroSequencialArquivo(Integer numeroSequencialArquivo) {
		this.numeroSequencialArquivo = numeroSequencialArquivo;
	}



	/**
	 * @return Retorna o campo indicadorRegistro.
	 */
	public short getIndicadorRegistro() {
		return indicadorRegistro;
	}



	/**
	 * @param indicadorRegistro O indicadorRegistro a ser setado.
	 */
	public void setIndicadorRegistro(short indicadorRegistro) {
		this.indicadorRegistro = indicadorRegistro;
	}



	/**
	 * @return Retorna o campo dataProcessamentoFinal.
	 */
	public String getDataProcessamentoFinal() {
		return dataProcessamentoFinal;
	}



	/**
	 * @param dataProcessamentoFinal O dataProcessamentoFinal a ser setado.
	 */
	public void setDataProcessamentoFinal(String dataProcessamentoFinal) {
		this.dataProcessamentoFinal = dataProcessamentoFinal;
	}



	/**
	 * @return Retorna o campo dataProcessamentoInicial.
	 */
	public String getDataProcessamentoInicial() {
		return dataProcessamentoInicial;
	}



	/**
	 * @param dataProcessamentoInicial O dataProcessamentoInicial a ser setado.
	 */
	public void setDataProcessamentoInicial(String dataProcessamentoInicial) {
		this.dataProcessamentoInicial = dataProcessamentoInicial;
	}



	/**
	 * @return Retorna o campo indicadorMovimento.
	 */
	public short getIndicadorMovimento() {
		return indicadorMovimento;
	}



	/**
	 * @param indicadorMovimento O indicadorMovimento a ser setado.
	 */
	public void setIndicadorMovimento(short indicadorMovimento) {
		this.indicadorMovimento = indicadorMovimento;
	}



	/**
	 * @return Retorna o campo indicadorCorrigido.
	 */
	public short getIndicadorCorrigido() {
		return indicadorCorrigido;
	}



	/**
	 * @param indicadorCorrigido O indicadorCorrigido a ser setado.
	 */
	public void setIndicadorCorrigido(short indicadorCorrigido) {
		this.indicadorCorrigido = indicadorCorrigido;
	}



	/**
	 * @return Retorna o campo idNegativadorMovimento.
	 */
	public Integer getIdNegativadorMovimento() {
		return idNegativadorMovimento;
	}



	/**
	 * @param idNegativadorMovimento O idNegativadorMovimento a ser setado.
	 */
	public void setIdNegativadorMovimento(Integer idNegativadorMovimento) {
		this.idNegativadorMovimento = idNegativadorMovimento;
	}



	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}



	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}



	public Collection getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}



	public void setColecaoGerenciaRegional(Collection colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}



	public Collection getColecaoMotivoRejeicao() {
		return colecaoMotivoRejeicao;
	}



	public void setColecaoMotivoRejeicao(Collection colecaoMotivoRejeicao) {
		this.colecaoMotivoRejeicao = colecaoMotivoRejeicao;
	}



	public Collection getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}



	public void setColecaoUnidadeNegocio(Collection colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}



	public Integer getIdLocalidade() {
		return idLocalidade;
	}



	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}



	public Integer getIdLocalidadePolo() {
		return idLocalidadePolo;
	}



	public void setIdLocalidadePolo(Integer idLocalidadePolo) {
		this.idLocalidadePolo = idLocalidadePolo;
	}

}
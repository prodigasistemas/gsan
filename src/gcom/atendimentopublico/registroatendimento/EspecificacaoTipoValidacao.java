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
package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 
 * Classe da tabela atendimentopublico.solicitacao_tipo_especificacao
 *
 * @author Rafael Santos
 * @date 26/10/2006
 */
public class EspecificacaoTipoValidacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
    
    /** nullable persistent field */
    private String descricaoEspecificacaoTipoValidacao;

    private char codigoConstante;

    private short indicadorUso;

    private Date ultimaAlteracao;
    
    public final static char ALTERACAO_CADASTRAL = 'I';
    
    public final static char ALTERACAO_CONTA = 'C';
    
    public final static char VENCIMENTO_ALTERNATIVO = 'V';
    
    public final static char TRANSFERENCIA_DEBITO = 'T';
    
	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo codigoConstante.
	 */
	public char getCodigoConstante() {
		return codigoConstante;
	}


	/**
	 * @param codigoConstante O codigoConstante a ser setado.
	 */
	public void setCodigoConstante(char codigoConstante) {
		this.codigoConstante = codigoConstante;
	}


	/**
	 * @return Retorna o campo descricaoEspecificacaoTipoValidacao.
	 */
	public String getDescricaoEspecificacaoTipoValidacao() {
		return descricaoEspecificacaoTipoValidacao;
	}


	/**
	 * @param descricaoEspecificacaoTipoValidacao O descricaoEspecificacaoTipoValidacao a ser setado.
	 */
	public void setDescricaoEspecificacaoTipoValidacao(
			String descricaoEspecificacaoTipoValidacao) {
		this.descricaoEspecificacaoTipoValidacao = descricaoEspecificacaoTipoValidacao;
	}


	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public short getIndicadorUso() {
		return indicadorUso;
	}


	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}


	/**
	 * @return Retorna o campo solicitacaoTipoEspecificacao.
	 */
	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}


	/**
	 * @param solicitacaoTipoEspecificacao O solicitacaoTipoEspecificacao a ser setado.
	 */
	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}


	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


}

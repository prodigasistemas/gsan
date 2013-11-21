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
package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0452] Obter Dados do Registro de Atendimento
 * 
 * @author Ana Maria
 * @date 14/08/2006
 * 
 */
public class ObterDadosRegistroAtendimentoHelper {
	
	private RegistroAtendimento registroAtendimento;
	
	private String descricaoSituacaoRA;
	
	private String enderecoOcorrencia;
	
	private UnidadeOrganizacional unidadeAtendimento;
	
	private UnidadeOrganizacional unidadeAtual;
	
	private RegistroAtendimentoSolicitante solicitante;
	
	private Short codigoExistenciaRAAssociado;
	
	private RegistroAtendimento RAAssociado;
	
	private String tituloNumeroRAAssociado;
	
	private String tituloSituacaoRAAssociado;
	
	private String descricaoSituacaoRAAssociado;
	
	private Short codigoRota;
	
	private Integer sequencialRota;
	
	private Collection colecaoRegistroAtendimentoAnexo;
	
	/**
	 * @return Retorna o campo codigoRota.
	 */
	public Short getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public Integer getSequencialRota() {
		return sequencialRota;
	}

	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getDescricaoSituacaoRAAssociado() {
		return descricaoSituacaoRAAssociado;
	}

	public void setDescricaoSituacaoRAAssociado(String descricaoSituacaoRAAssociado) {
		this.descricaoSituacaoRAAssociado = descricaoSituacaoRAAssociado;
	}

	public ObterDadosRegistroAtendimentoHelper(){}	

	public UnidadeOrganizacional getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(UnidadeOrganizacional unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

	public UnidadeOrganizacional getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(UnidadeOrganizacional unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public Short getCodigoExistenciaRAAssociado() {
		return codigoExistenciaRAAssociado;
	}

	public void setCodigoExistenciaRAAssociado(Short codigoExistenciaRAAssociado) {
		this.codigoExistenciaRAAssociado = codigoExistenciaRAAssociado;
	}

	public String getDescricaoSituacaoRA() {
		return descricaoSituacaoRA;
	}

	public void setDescricaoSituacaoRA(String descricaoSituacaoRA) {
		this.descricaoSituacaoRA = descricaoSituacaoRA;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public RegistroAtendimento getRAAssociado() {
		return RAAssociado;
	}

	public void setRAAssociado(RegistroAtendimento associado) {
		RAAssociado = associado;
	}

	public String getTituloNumeroRAAssociado() {
		return tituloNumeroRAAssociado;
	}

	public void setTituloNumeroRAAssociado(String tituloNumeroRAAssociado) {
		this.tituloNumeroRAAssociado = tituloNumeroRAAssociado;
	}

	public String getTituloSituacaoRAAssociado() {
		return tituloSituacaoRAAssociado;
	}

	public void setTituloSituacaoRAAssociado(String tituloSituacaoRAAssociado) {
		this.tituloSituacaoRAAssociado = tituloSituacaoRAAssociado;
	}

	public RegistroAtendimentoSolicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(RegistroAtendimentoSolicitante solicitante) {
		this.solicitante = solicitante;
	}

	public Collection getColecaoRegistroAtendimentoAnexo() {
		return colecaoRegistroAtendimentoAnexo;
	}

	public void setColecaoRegistroAtendimentoAnexo(
			Collection colecaoRegistroAtendimentoAnexo) {
		this.colecaoRegistroAtendimentoAnexo = colecaoRegistroAtendimentoAnexo;
	}	
}

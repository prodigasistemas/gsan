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
package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class OsReferidaRetornoTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** persistent field */
	private short indicadorDeferimento;

	/** persistent field */
	private Short situacaoOsReferencia;

	/** persistent field */
	private short indicadorTrocaServico;

	/** persistent field */
	private short indicadorUso;

	/** persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	// private Integer codigoPenalidade;
	/** nullable persistent field */
	// private BigDecimal penaSemInfracao;
	/** persistent field */
	private AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;

	/** persistent field */
	private gcom.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia;

	public final static short INDICADOR_DEFERIMENTO_SIM = 1;

	public final static short INDICADOR_DEFERIMENTO_NAO = 2;

	public final static short INDICADOR_TROCA_SERVICO_SIM = 1;

	public final static short INDICADOR_TROCA_SERVICO_NAO = 2;

	/** full constructor */
	public OsReferidaRetornoTipo(
			String descricao,
			String descricaoAbreviada,
			short indicadorDeferimento,
			Short situacaoOsReferencia,
			short indicadorTrocaServico,
			short indicadorUso,
			Date ultimaAlteracao,
			Integer codigoPenalidade,
			BigDecimal penaSemInfracao,
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento,
			gcom.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorDeferimento = indicadorDeferimento;
		this.situacaoOsReferencia = situacaoOsReferencia;
		this.indicadorTrocaServico = indicadorTrocaServico;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		// this.codigoPenalidade = codigoPenalidade;
		// this.penaSemInfracao = penaSemInfracao;
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
		this.servicoTipoReferencia = servicoTipoReferencia;
	}

	/** default constructor */
	public OsReferidaRetornoTipo() {
	}

	/** minimal constructor */
	public OsReferidaRetornoTipo(
			short indicadorDeferimento,
			Short situacaoOsReferencia,
			short indicadorTrocaServico,
			short indicadorUso,
			Date ultimaAlteracao,
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento,
			gcom.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia) {
		this.indicadorDeferimento = indicadorDeferimento;
		this.situacaoOsReferencia = situacaoOsReferencia;
		this.indicadorTrocaServico = indicadorTrocaServico;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
		this.servicoTipoReferencia = servicoTipoReferencia;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public short getIndicadorDeferimento() {
		return this.indicadorDeferimento;
	}

	public void setIndicadorDeferimento(short indicadorDeferimento) {
		this.indicadorDeferimento = indicadorDeferimento;
	}

	public Short getSituacaoOsReferencia() {
		return this.situacaoOsReferencia;
	}

	public void setSituacaoOsReferencia(Short situacaoOsReferencia) {
		this.situacaoOsReferencia = situacaoOsReferencia;
	}

	public Short getIndicadorTrocaServico() {
		return this.indicadorTrocaServico;
	}

	public void setIndicadorTrocaServico(short indicadorTrocaServico) {
		this.indicadorTrocaServico = indicadorTrocaServico;
	}

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	// public Integer getCodigoPenalidade() {
	// return this.codigoPenalidade;
	// }
	//
	// public void setCodigoPenalidade(Integer codigoPenalidade) {
	// this.codigoPenalidade = codigoPenalidade;
	// }
	//
	// public BigDecimal getPenaSemInfracao() {
	// return this.penaSemInfracao;
	// }
	//
	// public void setPenaSemInfracao(BigDecimal penaSemInfracao) {
	// this.penaSemInfracao = penaSemInfracao;
	// }

	public AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
		return this.atendimentoMotivoEncerramento;
	}

	public void setAtendimentoMotivoEncerramento(
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipoReferencia getServicoTipoReferencia() {
		return this.servicoTipoReferencia;
	}

	public void setServicoTipoReferencia(
			gcom.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia) {
		this.servicoTipoReferencia = servicoTipoReferencia;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = new FiltroOSReferidaRetornoTipo();
		filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
				FiltroOSReferidaRetornoTipo.ID, this.getId()));
		// filtroImovel.adicionarCaminhoParaCarregamentoEntidade
		// ("ligacaoEsgoto");
		filtroOSReferidaRetornoTipo
				.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");

		filtroOSReferidaRetornoTipo
				.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");

		return filtroOSReferidaRetornoTipo;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}

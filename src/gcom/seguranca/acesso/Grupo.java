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
package gcom.seguranca.acesso;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class Grupo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_INSERIR_GRUPO=6;
	public static final int ATRIBUTOS_REMOVER_GRUPO=7;
	public static final int ATRIBUTOS_ATUALIZAR_GRUPO=75;
	
	
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private String descricao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private String descricaoAbreviada;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private Short indicadorUso;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private Date ultimaAlteracao;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private Integer numDiasExpiracaoSenha;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private String mensagem;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_GRUPO, ATRIBUTOS_ATUALIZAR_GRUPO})
	private Short indicadorSuperintendencia;
	
	private BigDecimal competenciaRetificacao;

	/** Operacao de teste */
	public static final Integer ADMINISTRADOR = 1;
	
	public static final Integer ATENDENTE_LOJA = 46;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];

		retorno[0] = "id";
		
		return retorno;
	}
	
	/** full constructor */
	public Grupo(String descricao, String descricaoAbreviada,
			Short indicadorUso, Date ultimaAlteracao) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public Grupo() {
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

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	public Integer getNumDiasExpiracaoSenha() {
		return numDiasExpiracaoSenha;
	}

	public void setNumDiasExpiracaoSenha(Integer numDiasExpiracaoSenha) {
		this.numDiasExpiracaoSenha = numDiasExpiracaoSenha;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID, 
				this.getId()));
		
		return filtroGrupo;
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return getId().toString(); 
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"descricao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao"};
		return labels;		
	}

	public Short getIndicadorSuperintendencia() {
		return indicadorSuperintendencia;
	}

	public void setIndicadorSuperintendencia(Short indicadorSuperintendencia) {
		this.indicadorSuperintendencia = indicadorSuperintendencia;
	}

	public BigDecimal getCompetenciaRetificacao() {
		return competenciaRetificacao;
	}

	public void setCompetenciaRetificacao(BigDecimal competenciaRetificacao) {
		this.competenciaRetificacao = competenciaRetificacao;
	}
	
}

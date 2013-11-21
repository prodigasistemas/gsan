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
package gcom.batch;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UnidadeProcessamento implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int ROTA = 1;

	public static final int CONTA = 2;

	public static final int FATURAMENTO_ATIVIDADE_CRONOGRAMA_ROTA = 3;

	public static final int RELATORIO = 4;

	public static final int FUNCIONALIDADE = 5;

	public static final int LOCALIDADE = 6;

	public static final int COBRANCA_GRUPO_CRONOGRAMA_MES = 7;

	public static final int SETOR_COMERCIAL = 8;

	public static final int HIDROMETRO_MARCA = 9;

	public static final int COB_ACAO_ATIV_CRONOG = 10;

	public static final int COB_ACAO_ATIV_COMAND = 11;
	
	public static final int RA_ENCERRAMENTO_COM = 12;
	
	public static final int NEGATIVADOR_MOVIMENTO_REG = 14;
	
	public static final int COMANDO_EMPRESA_COBRANCA_CONTA = 15;
	
	public static final int COMANDO_EMPRESA_COBRANCA_CONTA_EXTENSAO = 16;
	
	public static final int UNIDADE_NEGOCIO = 17;
	
	public static final int HIDROMETRO = 18;
	
	public static final int EMPRESA = 19;
	
	public static final int QUADRA = 20;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoUnidadeProcessamento;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private short indicadorUso;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** persistent field */
	private Set unidadesIniciadas;

	/** persistent field */
	private Set processosFuncionalidades;

	/** full constructor */
	public UnidadeProcessamento(String descricaoUnidadeProcessamento,
			Date ultimaAlteracao, short indicadorUso,
			String descricaoAbreviada, Set unidadesIniciadas,
			Set processosFuncionalidades) {
		this.descricaoUnidadeProcessamento = descricaoUnidadeProcessamento;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorUso = indicadorUso;
		this.descricaoAbreviada = descricaoAbreviada;
		this.unidadesIniciadas = unidadesIniciadas;
		this.processosFuncionalidades = processosFuncionalidades;
	}

	/** default constructor */
	public UnidadeProcessamento() {
	}

	/** minimal constructor */
	public UnidadeProcessamento(short indicadorUso, Set unidadesIniciadas,
			Set processosFuncionalidades) {
		this.indicadorUso = indicadorUso;
		this.unidadesIniciadas = unidadesIniciadas;
		this.processosFuncionalidades = processosFuncionalidades;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoUnidadeProcessamento() {
		return this.descricaoUnidadeProcessamento;
	}

	public void setDescricaoUnidadeProcessamento(
			String descricaoUnidadeProcessamento) {
		this.descricaoUnidadeProcessamento = descricaoUnidadeProcessamento;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Set getUnidadesIniciadas() {
		return this.unidadesIniciadas;
	}

	public void setUnidadesIniciadas(Set unidadesIniciadas) {
		this.unidadesIniciadas = unidadesIniciadas;
	}

	public Set getProcessosFuncionalidades() {
		return this.processosFuncionalidades;
	}

	public void setProcessosFuncionalidades(Set processosFuncionalidades) {
		this.processosFuncionalidades = processosFuncionalidades;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}

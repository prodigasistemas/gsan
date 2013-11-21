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
package gcom.seguranca.transacao;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class TabelaColuna extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

	public static final int DATA_VENCIMENTO_CONTA = 419;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String coluna;

	/** nullable persistent field */
	private String descricaoColuna;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Short indicadorPrimaryKey = new Short((short) 2);

	/** nullable persistent field */
	private String nomeAbreviado;

	/** persistent field */
	private gcom.seguranca.transacao.Tabela tabela;

	private Integer indicadorPodeRetificarConta;

	private Short indicadorAtualizacaoCadastral;

	// Constantes
	// Nome do campo + tabela correspondente
	public final static Integer CPF_CNPJ_CLIENTE_ATU_CADASTRAL = 23829;

	// public final static Integer CNPJ_CLIENTE_ATU_CADASTRAL = 275;
	public final static Integer NOME_CLIENTE_ATU_CADASTRAL = 23820;

	public final static Integer PROFISSAO_CLIENTE_ATU_CADASTRAL = 23827;

	public final static Integer RAMO_ATIVIDADE_CLIENTE_ATU_CADASTRAL = 35022;

	public final static Integer CLIENTE_TIPO_CLIENTE_ATU_CADASTRAL = 23821;

	public final static Integer CLIENTE_RELACAO_TIPO_CLIENTE_IMOVEL_ATU_CADASTRAL = 23843;

	public final static Integer NUMERO_IMOVEL_CLIENTE_ENDERECO_ATU_CADASTRAL = 23840;

	public final static Integer COMPLEMENTO_CLIENTE_ENDERECO_ATU_CADASTRAL = 23841;
	
	public final static Integer SEXO_CLIENTE_ATUALIZACAO_CADASTRAL = 23828;

	public final static Integer NUMERO_MORADORES_IMOVEL_ATU_CADASTRAL = 23792;

	public final static Integer CODIGO_PERFIL_IMOVEL_ATU_CADASTRAL = 115087;

	public final static Integer NUMERO_IMOVEL_ATU_CADASTRAL = 35021;

	public final static Integer COMPLEMENTO_ENDERECO_IMOVEL_ATU_CADASTRAL = 23779;
	
	public final static Integer CODIGO_CADASTRO_OCORRENCIA_IMOVEL_ATU_CADASTRAL = 35024;
	
	public final static Integer ID_SITUACAO_LIGACAO_AGUA_IMOVEL_ATU_CADASTRAL = 23786;
	
	public final static Integer ID_SITUACAO_LIGACAO_ESGOTO_IMOVEL_ATU_CADASTRAL = 23787;

	public final static Integer CODIGO_SUBCATEGORIA_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL = 35027;

	public final static Integer QUANTIDADES_ECONOMIAS_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL = 23852;

	public final static Integer CODIGO_CATEGORIA_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL = 23854;
	
	public final static Integer TIPO_FONE_CLIENTE_TELEFONE = 23848;
	
	public final static Integer DDD_CLIENTE_TELEFONE = 23849;
	
	public final static Integer NUMERO_FONE_CLIENTE_TELEFONE = 23850;
	
	public final static Integer INDICADOR_FONE_PADRAO_CLIENTE_TELEFONE = 115095;

	public final static Integer ID_IMOVEL = 278;

	public final static Integer ID_CLIENTE_ATU_CADASTRAL = 115096;
	
	public final static Integer CODIGO_CLIENTE_ATU_CADASTRAL = 115097;

	public final static Integer ID_CLIENTE_TELEFONE_ATU_CADASTRAL = 115092;

	/** full constructor */
	public TabelaColuna(String coluna, String descricaoColuna,
			Date ultimaAlteracao, gcom.seguranca.transacao.Tabela tabela,
			Short indicadorPrimaryKey, String nomeAbreviado) {
		this.coluna = coluna;
		this.descricaoColuna = descricaoColuna;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabela = tabela;
		this.indicadorPrimaryKey = indicadorPrimaryKey;
		this.nomeAbreviado = nomeAbreviado;
	}

	/** default constructor */
	public TabelaColuna() {
	}

	/** minimal constructor */
	public TabelaColuna(gcom.seguranca.transacao.Tabela tabela) {
		this.tabela = tabela;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColuna() {
		return this.coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public String getDescricaoColuna() {
		return this.descricaoColuna;
	}

	public void setDescricaoColuna(String descricaoColuna) {
		this.descricaoColuna = descricaoColuna;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.seguranca.transacao.Tabela getTabela() {
		return this.tabela;
	}

	public void setTabela(gcom.seguranca.transacao.Tabela tabela) {
		this.tabela = tabela;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo indicadorPrimaryKey.
	 */
	public Short getIndicadorPrimaryKey() {
		return indicadorPrimaryKey;
	}

	/**
	 * @param indicadorPrimaryKey
	 *            O indicadorPrimaryKey a ser setado.
	 */
	public void setIndicadorPrimaryKey(Short indicadorPrimaryKey) {
		this.indicadorPrimaryKey = indicadorPrimaryKey;
	}

	/**
	 * @return Retorna o campo nomeAbreviado.
	 */
	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	/**
	 * @param nomeAbreviado
	 *            O nomeAbreviado a ser setado.
	 */
	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public Integer getIndicadorPodeRetificarConta() {
		return indicadorPodeRetificarConta;
	}

	public void setIndicadorPodeRetificarConta(
			Integer indicadorPodeRetificarConta) {
		this.indicadorPodeRetificarConta = indicadorPodeRetificarConta;
	}

	public Short getIndicadorAtualizacaoCadastral() {
		return indicadorAtualizacaoCadastral;
	}

	public void setIndicadorAtualizacaoCadastral(
			Short indicadorAtualizacaoCadastral) {
		this.indicadorAtualizacaoCadastral = indicadorAtualizacaoCadastral;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}

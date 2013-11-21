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
package gcom.financeiro.lancamento;

import gcom.financeiro.ContaContabil;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LancamentoItemContabil extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	// constantes de item contábil >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public final static Integer LIGACOES_AGUA = new Integer(1);

	public final static Integer ACRESCIMOS_POR_IMPONTUALIDADE = new Integer(2);

	public final static Integer RELIGACOES_E_SANCOES = new Integer(3);

	public final static Integer AFERICAO_DE_HIDROMETROS = new Integer(4);

	public final static Integer EXTENSOES_REDE_AGUA = new Integer(5);

	public final static Integer OUTROS_SERVICOS_AGUA = new Integer(6);

	public final static Integer LIGACOES_ESGOTO = new Integer(7);

	public final static Integer EXTENSOES_REDE_ESGOTO = new Integer(8);

	public final static Integer OUTROS_SERVICOS_ESGOTO = new Integer(9);
	
	public final static Integer TARIFA_DE_AGUA = new Integer(10);
	
	public final static Integer TARIFA_DE_ESGOTO = new Integer(11);
	
	public final static Integer JUROS_SOBRE_CONTRATO_PARCELAMENTO = new Integer(2);
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/** identifier field */
	private Integer id;

	/** persistent field */
	private String descricao;

	/** persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Short sequenciaImpressao;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Short indicadorUso;

	/** persistent field */
	private gcom.financeiro.lancamento.LancamentoItem lancamentoItem;

	/** full constructor */
	public LancamentoItemContabil(String descricao, String descricaoAbreviada,
			Short sequenciaImpressao, Date ultimaAlteracao, Short indicadorUso,
			gcom.financeiro.lancamento.LancamentoItem lancamentoItem) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.sequenciaImpressao = sequenciaImpressao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorUso = indicadorUso;
		this.lancamentoItem = lancamentoItem;

	}

	/** default constructor */
	public LancamentoItemContabil() {
	}

	// Construido por Sávio Luiz para setar o id no objeto
	public LancamentoItemContabil(Integer id) {
		this.id = id;
	}

	/** minimal constructor */
	public LancamentoItemContabil(String descricao, String descricaoAbreviada,
			gcom.financeiro.lancamento.LancamentoItem lancamentoItem,
			ContaContabil contaContabil) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.lancamentoItem = lancamentoItem;

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

	public Short getSequenciaImpressao() {
		return this.sequenciaImpressao;
	}

	public void setSequenciaImpressao(Short sequenciaImpressao) {
		this.sequenciaImpressao = sequenciaImpressao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.financeiro.lancamento.LancamentoItem getLancamentoItem() {
		return this.lancamentoItem;
	}

	public void setLancamentoItem(
			gcom.financeiro.lancamento.LancamentoItem lancamentoItem) {
		this.lancamentoItem = lancamentoItem;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public Short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso
	 *            O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	

	public Filtro retornaFiltro(){
		FiltroLancamentoItemContabil filtro = new FiltroLancamentoItemContabil();
		filtro. adicionarParametro(
				new ParametroSimples(FiltroLancamentoItemContabil.ID, this.getId()));		
		return filtro;
	}

	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
		if (lancamentoItem !=null){
			getLancamentoItem();
		}
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}

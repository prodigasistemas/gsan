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
package gcom.faturamento.credito;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CreditoOrigem extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	// constantes de origem de crédito >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public final static Integer CONTAS_PAGAS_EM_DUPLICIDADE_EXCESSO = new Integer(
			1);

	public final static Integer DEVOLUCAO_TARIFA_AGUA = new Integer(2);

	public final static Integer DEVOLUCAO_TARIFA_ESGOTO = new Integer(3);

	public final static Integer SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE = new Integer(
			4);

	public final static Integer DEVOLUCAO_JUROS_PARCELAMENTO = new Integer(5);

	public final static Integer DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO = new Integer(
			6);

	public final static Integer VALORES_COBRADOS_INDEVIDAMENTE = new Integer(7);

	public final static Integer DESCONTOS_CONDICIONAIS = new Integer(7);

	public final static Integer DESCONTOS_INCONDICIONAIS = new Integer(8);

	public final static Integer AJUSTES_PARA_ZERAR_CONTA = new Integer(9);
	
	/**TODO: COSANPA
	 * Mantis 615 - Contabilizar créditos com crédito origem correspondente 
	 * a Contas Pagas em Excesso
	 * 
	 * @author Wellington Rocha
	 * @date */
	public final static Integer CONTAS_PAGAS_EM_EXCESSO = new Integer (10);

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoCreditoOrigem;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** full constructor */
	public CreditoOrigem(String descricaoCreditoOrigem,
			String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
		this.descricaoCreditoOrigem = descricaoCreditoOrigem;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public CreditoOrigem() {
	}

	// Construido por Sávio Luiz para setar o id no objeto
	public CreditoOrigem(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoCreditoOrigem() {
		return this.descricaoCreditoOrigem;
	}

	public void setDescricaoCreditoOrigem(String descricaoCreditoOrigem) {
		this.descricaoCreditoOrigem = descricaoCreditoOrigem;
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

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroCreditoOrigem filtro = new FiltroCreditoOrigem();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroCreditoOrigem.ID, this.getId()));		
		return filtro;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoCreditoOrigem();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
		getId();
	}
}

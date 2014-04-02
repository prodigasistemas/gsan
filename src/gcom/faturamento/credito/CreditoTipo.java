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

import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CreditoTipo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	public final static Integer DESCONTO_ACRESCIMOS_IMPONTUALIDADE = new Integer(1);

	public final static Integer DESCONTO_ANTIGUIDADE_DEBITO = new Integer(2);

	public final static Integer DESCONTO_INATIVIDADE_LIGACAO_AGUA = new Integer(3);

	public final static Integer CREDITOS_ANTERIORES = new Integer(4);

	public final static Integer DEVOLUCAO_PAGAMENTOS_DUPLICIDADE = new Integer(5);

	public final static Integer DEVOLUCAO_OUTROS_VALORES = new Integer(7);

	public final static Integer DESCONTOS_CONCEDIDOS = new Integer(8);

	public final static Integer DEVOLUCAO_ACRESCIMOS_IMPONTUALIDADE = new Integer(9);
	
	public final static Integer DESCONTO_SANCOES = new Integer(10);
	
	public final static Integer DESCONTO_TARIFA_SOCIAL = new Integer(11);
	
	public final static Integer CREDITO_NITRATO = new Integer(0);
	
	public final static Integer PAGAMENTO_PARCIAL = new Integer(856);

	public final static Integer PAGAMENTO_NAO_CONFERE = new Integer(860);
	

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Integer indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private BigDecimal valorLimite;

	/** nullable persistent field */
	private Short indicadorGeracaoAutomatica;

	/** nullable persistent field */
	private LancamentoItemContabil lancamentoItemContabil;
    
    /** nullable persistent field */
    private Integer codigoConstante;

	/** full constructor */
	public CreditoTipo(String descricao, String descricaoAbreviada,
			Integer indicadorUso, Date ultimaAlteracao, BigDecimal valorLimite,
			Short indicadorGeracaoAutomatica,
			LancamentoItemContabil lancamentoItemContabil) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.valorLimite = valorLimite;
		this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	/** default constructor */
	public CreditoTipo() {
	}
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * Construtor de CreditoTipo 
	 * 
	 * @param id
	 * @param descricao
	 */
	public CreditoTipo(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	// Construido por Sávio Luiz para setar o id no objeto
	public CreditoTipo(Integer id) {
		this.id = id;
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

	public Integer getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Integer indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorLimite() {
		return this.valorLimite;
	}

	public void setValorLimite(BigDecimal valorLimite) {
		this.valorLimite = valorLimite;
	}

	public Short getIndicadorGeracaoAutomatica() {
		return this.indicadorGeracaoAutomatica;
	}

	public void setIndicadorGeracaoAutomatica(Short indicadorGeracaoAutomatica) {
		this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();

		filtroCreditoTipo.adicionarParametro(new ParametroSimples(
				FiltroCreditoTipo.ID, this.getId()));
		filtroCreditoTipo
				.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

		return filtroCreditoTipo;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

    public Integer getCodigoConstante() {
        return codigoConstante;
    }

    public void setCodigoConstante(Integer codigoConstante) {
        this.codigoConstante = codigoConstante;
    }

	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		
		if (obj instanceof CreditoTipo) {
			
			CreditoTipo castOther = (CreditoTipo) obj;
			
			retorno = this.getId().compareTo(castOther.getId())==0;
		}
		
		return retorno;
	}

	@Override
	public int hashCode() {
		return this.getId();
	}
    
    
}
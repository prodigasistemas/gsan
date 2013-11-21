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
package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
//@ControleAlteracao
public class CobrancaCriterio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private static final int ATRIBUTOS_INSERIR_CRITERIO = 139; 

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private String descricaoCobrancaCriterio;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Date dataInicioVigencia;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short numeroContaAntiga;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoImovelParalisacao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoImovelSituacaoCobranca;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoDebitoContaMes;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoContaRevisao;    

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoInquilinoDebitoContaMes;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoDebitoContaAntiga;

    /** persistent field */
    //private gcom.cobranca.CobrancaAcao cobrancaAcao;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private BigDecimal percentualValorMinimoPagoParceladoCancelado;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private BigDecimal percentualQuantidadeMinimoPagoParceladoCancelado;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private BigDecimal valorLimitePrioridade;
    
    private Set cobrancaCriterioLinhas;
    
    private Set criteriosSituacaoCobranca;
    
    private Set criteriosSituacaoLigacaoAgua;
    
    private Set criteriosSituacaoLigacaoEsgoto;

    public Set getCobrancaCriterioLinhas() {
		return cobrancaCriterioLinhas;
	}

	public void setCobrancaCriterioLinhas(Set cobrancaCriterioLinhas) {
		this.cobrancaCriterioLinhas = cobrancaCriterioLinhas;
	}

	/** full constructor */
    public CobrancaCriterio(String descricaoCobrancaCriterio, Date dataInicioVigencia, Short indicadorUso, Short numeroContaAntiga, Short indicadorEmissaoImovelParalisacao, Date ultimaAlteracao, Short indicadorEmissaoImovelSituacaoCobranca, Short indicadorEmissaoDebitoContaMes, Short indicadorEmissaoContaRevisao, Short indicadorEmissaoInquilinoDebitoContaMes, Short indicadorEmissaoDebitoContaAntiga) {
        this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
        this.dataInicioVigencia = dataInicioVigencia;
        this.indicadorUso = indicadorUso;
        this.numeroContaAntiga = numeroContaAntiga;
        this.indicadorEmissaoImovelParalisacao = indicadorEmissaoImovelParalisacao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorEmissaoImovelSituacaoCobranca = indicadorEmissaoImovelSituacaoCobranca;
        this.indicadorEmissaoDebitoContaMes = indicadorEmissaoDebitoContaMes;
        this.indicadorEmissaoContaRevisao = indicadorEmissaoContaRevisao;
        this.indicadorEmissaoInquilinoDebitoContaMes = indicadorEmissaoInquilinoDebitoContaMes;
        this.indicadorEmissaoDebitoContaAntiga = indicadorEmissaoDebitoContaAntiga;
//        this.cobrancaAcao = cobrancaAcao;
    }

    /** default constructor */
    public CobrancaCriterio() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoCobrancaCriterio() {
        return this.descricaoCobrancaCriterio;
    }

    public void setDescricaoCobrancaCriterio(String descricaoCobrancaCriterio) {
        this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
    }

    public Date getDataInicioVigencia() {
        return this.dataInicioVigencia;
    }

    public void setDataInicioVigencia(Date dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Short getNumeroContaAntiga() {
        return this.numeroContaAntiga;
    }

    public void setNumeroContaAntiga(Short numeroContaAntiga) {
        this.numeroContaAntiga = numeroContaAntiga;
    }

    public Short getIndicadorEmissaoImovelParalisacao() {
        return this.indicadorEmissaoImovelParalisacao;
    }

    public void setIndicadorEmissaoImovelParalisacao(Short indicadorEmissaoImovelParalisacao) {
        this.indicadorEmissaoImovelParalisacao = indicadorEmissaoImovelParalisacao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getIndicadorEmissaoImovelSituacaoCobranca() {
        return this.indicadorEmissaoImovelSituacaoCobranca;
    }

    public void setIndicadorEmissaoImovelSituacaoCobranca(Short indicadorEmissaoImovelSituacaoCobranca) {
        this.indicadorEmissaoImovelSituacaoCobranca = indicadorEmissaoImovelSituacaoCobranca;
    }

    public Short getIndicadorEmissaoDebitoContaMes() {
        return this.indicadorEmissaoDebitoContaMes;
    }

    public void setIndicadorEmissaoDebitoContaMes(Short indicadorEmissaoDebitoContaMes) {
        this.indicadorEmissaoDebitoContaMes = indicadorEmissaoDebitoContaMes;
    }

    public Short getIndicadorEmissaoContaRevisao() {
        return this.indicadorEmissaoContaRevisao;
    }

    public void setIndicadorEmissaoContaRevisao(Short indicadorEmissaoContaRevisao) {
        this.indicadorEmissaoContaRevisao = indicadorEmissaoContaRevisao;
    }

    public Short getIndicadorEmissaoInquilinoDebitoContaMes() {
        return this.indicadorEmissaoInquilinoDebitoContaMes;
    }

    public void setIndicadorEmissaoInquilinoDebitoContaMes(Short indicadorEmissaoInquilinoDebitoContaMes) {
        this.indicadorEmissaoInquilinoDebitoContaMes = indicadorEmissaoInquilinoDebitoContaMes;
    }

    public Short getIndicadorEmissaoDebitoContaAntiga() {
        return this.indicadorEmissaoDebitoContaAntiga;
    }

    public void setIndicadorEmissaoDebitoContaAntiga(Short indicadorEmissaoDebitoContaAntiga) {
        this.indicadorEmissaoDebitoContaAntiga = indicadorEmissaoDebitoContaAntiga;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
	public Filtro retornaFiltro(){
		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();

		filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID,
				this.getId()));
//		filtroCobrancaCriterio.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterioLinhas");
		
		return filtroCobrancaCriterio;
	}

	/**
	 * @return Retorna o campo percentualQuantidadeMinimoPagoParceladoCancelado.
	 */
	public BigDecimal getPercentualQuantidadeMinimoPagoParceladoCancelado() {
		return percentualQuantidadeMinimoPagoParceladoCancelado;
	}

	/**
	 * @param percentualQuantidadeMinimoPagoParceladoCancelado O percentualQuantidadeMinimoPagoParceladoCancelado a ser setado.
	 */
	public void setPercentualQuantidadeMinimoPagoParceladoCancelado(
			BigDecimal percentualQuantidadeMinimoPagoParceladoCancelado) {
		this.percentualQuantidadeMinimoPagoParceladoCancelado = percentualQuantidadeMinimoPagoParceladoCancelado;
	}

	/**
	 * @return Retorna o campo percentualValorMinimoPagoParceladoCancelado.
	 */
	public BigDecimal getPercentualValorMinimoPagoParceladoCancelado() {
		return percentualValorMinimoPagoParceladoCancelado;
	}

	/**
	 * @param percentualValorMinimoPagoParceladoCancelado O percentualValorMinimoPagoParceladoCancelado a ser setado.
	 */
	public void setPercentualValorMinimoPagoParceladoCancelado(
			BigDecimal percentualValorMinimoPagoParceladoCancelado) {
		this.percentualValorMinimoPagoParceladoCancelado = percentualValorMinimoPagoParceladoCancelado;
	}

	/**
	 * @return Retorna o campo valorLimitePrioridade.
	 */
	public BigDecimal getValorLimitePrioridade() {
		return valorLimitePrioridade;
	}

	/**
	 * @param valorLimitePrioridade O valorLimitePrioridade a ser setado.
	 */
	public void setValorLimitePrioridade(BigDecimal valorLimitePrioridade) {
		this.valorLimitePrioridade = valorLimitePrioridade;
	}

	public Set getCriteriosSituacaoCobranca() {
		return criteriosSituacaoCobranca;
	}

	public void setCriteriosSituacaoCobranca(Set criteriosSituacaoCobranca) {
		this.criteriosSituacaoCobranca = criteriosSituacaoCobranca;
	}

	public Set getCriteriosSituacaoLigacaoAgua() {
		return criteriosSituacaoLigacaoAgua;
	}

	public void setCriteriosSituacaoLigacaoAgua(Set criteriosSituacaoLigacaoAgua) {
		this.criteriosSituacaoLigacaoAgua = criteriosSituacaoLigacaoAgua;
	}

	public Set getCriteriosSituacaoLigacaoEsgoto() {
		return criteriosSituacaoLigacaoEsgoto;
	}

	public void setCriteriosSituacaoLigacaoEsgoto(Set criteriosSituacaoLigacaoEsgoto) {
		this.criteriosSituacaoLigacaoEsgoto = criteriosSituacaoLigacaoEsgoto;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoCobrancaCriterio();
	}
}

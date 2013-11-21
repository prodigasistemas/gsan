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
package gcom.micromedicao.consumo;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.micromedicao.RateioTipo;
import gcom.micromedicao.Rota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsumoHistoricoAnterior implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesFaturamento;

    /** nullable persistent field */
    private Short indicadorAlteracaoUltimosConsumos;

    /** nullable persistent field */
    private Short indicadorAjuste;

    /** nullable persistent field */
    private Integer numeroConsumoFaturadoMes;

    /** nullable persistent field */
    private Integer numeroConsumoRateio;

    /** nullable persistent field */
    private Short indicadorImovelCondominio;

    /** nullable persistent field */
    private Integer numeroConsumoMedio;

    /** nullable persistent field */
    private Integer numeroConsumoMinimo;

    /** nullable persistent field */
    private Short indicadorFaturamento;

    /** nullable persistent field */
    private BigDecimal percentualColeta;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Rota rota;

    /** persistent field */
    private ConsumoAnormalidade consumoAnormalidade;

    /** persistent field */
    private PocoTipo pocoTipo;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private LigacaoTipo ligacaoTipo;

    /** persistent field */
    private ConsumoHistoricoAnterior consumoHistoricoAnterior;

    /** persistent field */
    private FaturamentoSituacaoTipo faturamentoSituacaoTipo;

    /** persistent field */
    private ConsumoTipo consumoTipo;

    /** persistent field */
    private RateioTipo rateioTipo;

    private Integer consumoImovelVinculadosCondominio;
    

	/** full constructor */
    public ConsumoHistoricoAnterior(Integer id, int anoMesFaturamento, Short indicadorAlteracaoUltimosConsumos, Short indicadorAjuste, Integer numeroConsumoFaturadoMes, Integer numeroConsumoRateio, Short indicadorImovelCondominio, Integer numeroConsumoMedio, Integer numeroConsumoMinimo, Short indicadorFaturamento, BigDecimal percentualColeta, Date ultimaAlteracao, Rota rota, ConsumoAnormalidade consumoAnormalidade, PocoTipo pocoTipo, Imovel imovel, LigacaoTipo ligacaoTipo, ConsumoHistoricoAnterior consumoHistoricoAnterior, FaturamentoSituacaoTipo faturamentoSituacaoTipo, ConsumoTipo consumoTipo, RateioTipo rateioTipo) {
        this.id = id;
        this.anoMesFaturamento = anoMesFaturamento;
        this.indicadorAlteracaoUltimosConsumos = indicadorAlteracaoUltimosConsumos;
        this.indicadorAjuste = indicadorAjuste;
        this.numeroConsumoFaturadoMes = numeroConsumoFaturadoMes;
        this.numeroConsumoRateio = numeroConsumoRateio;
        this.indicadorImovelCondominio = indicadorImovelCondominio;
        this.numeroConsumoMedio = numeroConsumoMedio;
        this.numeroConsumoMinimo = numeroConsumoMinimo;
        this.indicadorFaturamento = indicadorFaturamento;
        this.percentualColeta = percentualColeta;
        this.ultimaAlteracao = ultimaAlteracao;
        this.rota = rota;
        this.consumoAnormalidade = consumoAnormalidade;
        this.pocoTipo = pocoTipo;
        this.imovel = imovel;
        this.ligacaoTipo = ligacaoTipo;
        this.consumoHistoricoAnterior = consumoHistoricoAnterior;
        this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
        this.consumoTipo = consumoTipo;
        this.rateioTipo = rateioTipo;
    }

    /** default constructor */
    public ConsumoHistoricoAnterior() {
    }

    public int getAnoMesFaturamento() {
		return anoMesFaturamento;
	}

	public void setAnoMesFaturamento(int anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}

	public ConsumoAnormalidade getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(ConsumoAnormalidade consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	public ConsumoHistoricoAnterior getConsumoHistoricoAnterior() {
		return consumoHistoricoAnterior;
	}

	public void setConsumoHistoricoAnterior(
			ConsumoHistoricoAnterior consumoHistoricoAnterior) {
		this.consumoHistoricoAnterior = consumoHistoricoAnterior;
	}

	public ConsumoTipo getConsumoTipo() {
		return consumoTipo;
	}

	public void setConsumoTipo(ConsumoTipo consumoTipo) {
		this.consumoTipo = consumoTipo;
	}

	public FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
		return faturamentoSituacaoTipo;
	}

	public void setFaturamentoSituacaoTipo(
			FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Short getIndicadorAjuste() {
		return indicadorAjuste;
	}

	public void setIndicadorAjuste(Short indicadorAjuste) {
		this.indicadorAjuste = indicadorAjuste;
	}

	public Short getIndicadorAlteracaoUltimosConsumos() {
		return indicadorAlteracaoUltimosConsumos;
	}

	public void setIndicadorAlteracaoUltimosConsumos(
			Short indicadorAlteracaoUltimosConsumos) {
		this.indicadorAlteracaoUltimosConsumos = indicadorAlteracaoUltimosConsumos;
	}

	public Short getIndicadorFaturamento() {
		return indicadorFaturamento;
	}

	public void setIndicadorFaturamento(Short indicadorFaturamento) {
		this.indicadorFaturamento = indicadorFaturamento;
	}

	public Short getIndicadorImovelCondominio() {
		return indicadorImovelCondominio;
	}

	public void setIndicadorImovelCondominio(Short indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}

	public LigacaoTipo getLigacaoTipo() {
		return ligacaoTipo;
	}

	public void setLigacaoTipo(LigacaoTipo ligacaoTipo) {
		this.ligacaoTipo = ligacaoTipo;
	}

	public Integer getNumeroConsumoFaturadoMes() {
		return numeroConsumoFaturadoMes;
	}

	public void setNumeroConsumoFaturadoMes(Integer numeroConsumoFaturadoMes) {
		this.numeroConsumoFaturadoMes = numeroConsumoFaturadoMes;
	}

	public Integer getNumeroConsumoMedio() {
		return numeroConsumoMedio;
	}

	public void setNumeroConsumoMedio(Integer numeroConsumoMedio) {
		this.numeroConsumoMedio = numeroConsumoMedio;
	}

	public Integer getNumeroConsumoMinimo() {
		return numeroConsumoMinimo;
	}

	public void setNumeroConsumoMinimo(Integer numeroConsumoMinimo) {
		this.numeroConsumoMinimo = numeroConsumoMinimo;
	}

	public Integer getNumeroConsumoRateio() {
		return numeroConsumoRateio;
	}

	public void setNumeroConsumoRateio(Integer numeroConsumoRateio) {
		this.numeroConsumoRateio = numeroConsumoRateio;
	}

	public BigDecimal getPercentualColeta() {
		return percentualColeta;
	}

	public void setPercentualColeta(BigDecimal percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	public PocoTipo getPocoTipo() {
		return pocoTipo;
	}

	public void setPocoTipo(PocoTipo pocoTipo) {
		this.pocoTipo = pocoTipo;
	}

	public RateioTipo getRateioTipo() {
		return rateioTipo;
	}

	public void setRateioTipo(RateioTipo rateioTipo) {
		this.rateioTipo = rateioTipo;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

    public Integer getConsumoImovelVinculadosCondominio() {
		return consumoImovelVinculadosCondominio;
	}

	public void setConsumoImovelVinculadosCondominio(
			Integer consumoImovelVinculadosCondominio) {
		this.consumoImovelVinculadosCondominio = consumoImovelVinculadosCondominio;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}

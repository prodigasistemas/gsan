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
package gcom.cobranca.parcelamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoQuantidadePrestacao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short quantidadeMaximaPrestacao;

    /** nullable persistent field */
    private BigDecimal taxaJuros;

    /** nullable persistent field */
	private BigDecimal percentualMinimoEntrada;

	/** nullable persistent field */
	private BigDecimal percentualTarifaMinimaImovel;
	
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento parcelamentoQuantidadeReparcelamento;
    
    private BigDecimal percentualValorReparcelado;
    
    private Short quantidadeMaxPrestacaoEspecial;
    
    private BigDecimal percentualEntradaSugerida;
    
    private Integer fatorQuantidadePrestacoes;
    
    private short indicadorMediaValorContas;
    
    private Short indicadorValorUltimaContaEmAtraso;
    

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroParcelamentoQuantidadePrestacao filtroParcelamentoQuantidadePrestacao = new FiltroParcelamentoQuantidadePrestacao();
		
		filtroParcelamentoQuantidadePrestacao. adicionarCaminhoParaCarregamentoEntidade("parcelamentoQuantidadeReparcelamento");
		filtroParcelamentoQuantidadePrestacao. adicionarParametro(
				new ParametroSimples(FiltroParcelamentoQuantidadePrestacao.ID, this.getId()));
		return filtroParcelamentoQuantidadePrestacao; 
	}
    
    
    
    /** full constructor */
    public ParcelamentoQuantidadePrestacao(Short quantidadeMaximaPrestacao, BigDecimal taxaJuros, 
    		BigDecimal percentualMinimoEntrada, BigDecimal percentualTarifaMinimaImovel,
    		Date ultimaAlteracao, 
    		gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento parcelamentoQuantidadeReparcelamento,
    		BigDecimal percentualValorReparcelado, BigDecimal percentualEntradaSugerida) {
        this.quantidadeMaximaPrestacao = quantidadeMaximaPrestacao;
        this.taxaJuros = taxaJuros;
        this.percentualMinimoEntrada = percentualMinimoEntrada;
        this.percentualTarifaMinimaImovel = percentualTarifaMinimaImovel;
        this.ultimaAlteracao = ultimaAlteracao;
        this.parcelamentoQuantidadeReparcelamento = parcelamentoQuantidadeReparcelamento;
        this.percentualValorReparcelado = percentualValorReparcelado;
        this.percentualEntradaSugerida = percentualEntradaSugerida;
    }

    /** default constructor */
    public ParcelamentoQuantidadePrestacao() {
    }

    /** minimal constructor */
    public ParcelamentoQuantidadePrestacao(gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento parcelamentoQuantidadeReparcelamento) {
        this.parcelamentoQuantidadeReparcelamento = parcelamentoQuantidadeReparcelamento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getQuantidadeMaximaPrestacao() {
        return this.quantidadeMaximaPrestacao;
    }

    public void setQuantidadeMaximaPrestacao(Short quantidadeMaximaPrestacao) {
        this.quantidadeMaximaPrestacao = quantidadeMaximaPrestacao;
    }

    public BigDecimal getTaxaJuros() {
        return this.taxaJuros;
    }

    public void setTaxaJuros(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento getParcelamentoQuantidadeReparcelamento() {
        return this.parcelamentoQuantidadeReparcelamento;
    }

    public void setParcelamentoQuantidadeReparcelamento(gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento parcelamentoQuantidadeReparcelamento) {
        this.parcelamentoQuantidadeReparcelamento = parcelamentoQuantidadeReparcelamento;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }


	public BigDecimal getPercentualMinimoEntrada() {
		return percentualMinimoEntrada;
	}

	public void setPercentualMinimoEntrada(BigDecimal percentualMinimoEntrada) {
		this.percentualMinimoEntrada = percentualMinimoEntrada;
	}

	public BigDecimal getPercentualTarifaMinimaImovel() {
		return percentualTarifaMinimaImovel;
	}

	public void setPercentualTarifaMinimaImovel(
			BigDecimal percentualTarifaMinimaImovel) {
		this.percentualTarifaMinimaImovel = percentualTarifaMinimaImovel;
	}

	public BigDecimal getPercentualValorReparcelado() {
		return percentualValorReparcelado;
	}

	public void setPercentualValorReparcelado(BigDecimal percentualValorReparcelado) {
		this.percentualValorReparcelado = percentualValorReparcelado;
	}

	public Short getQuantidadeMaxPrestacaoEspecial() {
		return quantidadeMaxPrestacaoEspecial;
	}

	public void setQuantidadeMaxPrestacaoEspecial(
			Short quantidadeMaxPrestacaoEspecial) {
		this.quantidadeMaxPrestacaoEspecial = quantidadeMaxPrestacaoEspecial;
	}

	public BigDecimal getPercentualEntradaSugerida() {
		return percentualEntradaSugerida;
	}

	public void setPercentualEntradaSugerida(BigDecimal percentualEntradaSugerida) {
		this.percentualEntradaSugerida = percentualEntradaSugerida;
	}

	public short getIndicadorMediaValorContas() {
		return indicadorMediaValorContas;
	}

	public void setIndicadorMediaValorContas(short indicadorMediaValorContas) {
		this.indicadorMediaValorContas = indicadorMediaValorContas;
	}

	public Integer getFatorQuantidadePrestacoes() {
		return fatorQuantidadePrestacoes;
	}

	public void setFatorQuantidadePrestacoes(Integer fatorQuantidadePrestacoes) {
		this.fatorQuantidadePrestacoes = fatorQuantidadePrestacoes;
	}

	public Short getIndicadorValorUltimaContaEmAtraso() {
		return indicadorValorUltimaContaEmAtraso;
	}

	public void setIndicadorValorUltimaContaEmAtraso(
			Short indicadorValorUltimaContaEmAtraso) {
		this.indicadorValorUltimaContaEmAtraso = indicadorValorUltimaContaEmAtraso;
	}
	
	
}
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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.localidade.Localidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterio implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoTitulo;

    /** persistent field */
    private String descricaoSolicitacao;

    /** nullable persistent field */
    private Integer codigoSetorComercialInicial;

    /** nullable persistent field */
    private Integer codigoSetorComercialFinal;

    /** nullable persistent field */
    private Integer anoMesReferenciaContaInicial;

    /** nullable persistent field */
    private Integer anoMesReferenciaContaFinal;

    /** nullable persistent field */
    private Date dataVencimentoDebitoInicial;

    /** nullable persistent field */
    private Date dataVencimentoDebitoFinal;


    /** nullable persistent field */
    private Integer quantidadeMaximaInclusoes;

    
    /** persistent field */
    private short indicadorNegativacaoImovelParalisacao;

    /** persistent field */
    private short indicadorNegativacaoImovelSituacaoCobranca;

    /** persistent field */
    private short indicadorNegativacaoContaRevisao;

    /** persistent field */
    private short indicadorNegativacaoGuiaPagamento;

  

    /** persistent field */
    private short indicadorNegativacaoInquilinoDebitoContaMes;



    /** persistent field */
    private short indicadorNegativacaoRecebimentoCartaParcelamento;

    /** nullable persistent field */
    private Short numeroDiasAtrasoRecebimentoCartaParcelamento;


    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private BigDecimal valorMinimoDebito;

    /** persistent field */
    private int quantidadeMinimaContas;

    /** persistent field */
    private BigDecimal valorMaximoDebito;


    /** persistent field */
    private int quantidadeMaximaContas;


    /** persistent field */
    private short indicadorParcelamentoAtraso;

    /** nullable persistent field */
    private Integer numeroDiasParcelamentoAtraso;


    /** persistent field */
    private Localidade localidadeFinal;

    /** persistent field */
    private Localidade localidadeInicial;



    /** persistent field */
    private gcom.cobranca.NegativacaoComando negativacaoComando;

    /** persistent field */
    private Cliente cliente;

    private Integer numeroDiasRetorno;


    /** persistent field */
    private ClienteRelacaoTipo clienteRelacaoTipo;

    /** full constructor */
    public NegativacaoCriterio(Integer id, String descricaoTitulo, String descricaoSolicitacao, Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, Integer anoMesReferenciaContaInicial, Integer anoMesReferenciaContaFinal, Date dataVencimentoDebitoInicial, Date dataVencimentoDebitoFinal,  Integer quantidadeMaximaInclusoes, short indicadorNegativacaoImovelParalisacao, short indicadorNegativacaoImovelSituacaoCobranca, short indicadorNegativacaoContaRevisao, short indicadorNegativacaoGuiaPagamento,  short indicadorNegativacaoInquilinoDebitoContaMes,  short indicadorNegativacaoRecebimentoCartaParcelamento, Short numeroDiasAtrasoRecebimentoCartaParcelamento,  short indicadorUso, Date ultimaAlteracao, BigDecimal valorMinimoDebito, int quantidadeMinimaContas, BigDecimal valorMaximoDebito, int quantidadeMaximaContas, short indicadorParcelamentoAtraso, Integer numeroDiasParcelamentoAtraso, Localidade localidadeFinal, Localidade localidadeInicial, gcom.cobranca.NegativacaoComando negativacaoComando, Cliente cliente, ClienteRelacaoTipo clienteRelacaoTipo) {
        this.id = id;
        this.descricaoTitulo = descricaoTitulo;
        this.descricaoSolicitacao = descricaoSolicitacao;
        this.codigoSetorComercialInicial = codigoSetorComercialInicial;
        this.codigoSetorComercialFinal = codigoSetorComercialFinal;
        this.anoMesReferenciaContaInicial = anoMesReferenciaContaInicial;
        this.anoMesReferenciaContaFinal = anoMesReferenciaContaFinal;
        this.dataVencimentoDebitoInicial = dataVencimentoDebitoInicial;
        this.dataVencimentoDebitoFinal = dataVencimentoDebitoFinal;
        this.quantidadeMaximaInclusoes = quantidadeMaximaInclusoes;
        this.indicadorNegativacaoImovelParalisacao = indicadorNegativacaoImovelParalisacao;
        this.indicadorNegativacaoImovelSituacaoCobranca = indicadorNegativacaoImovelSituacaoCobranca;
        this.indicadorNegativacaoContaRevisao = indicadorNegativacaoContaRevisao;
        this.indicadorNegativacaoGuiaPagamento = indicadorNegativacaoGuiaPagamento;
        this.indicadorNegativacaoInquilinoDebitoContaMes = indicadorNegativacaoInquilinoDebitoContaMes;
        this.indicadorNegativacaoRecebimentoCartaParcelamento = indicadorNegativacaoRecebimentoCartaParcelamento;
        this.numeroDiasAtrasoRecebimentoCartaParcelamento = numeroDiasAtrasoRecebimentoCartaParcelamento;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorMinimoDebito = valorMinimoDebito;
        this.quantidadeMinimaContas = quantidadeMinimaContas;
        this.valorMaximoDebito = valorMaximoDebito;
        this.quantidadeMaximaContas = quantidadeMaximaContas;
        this.indicadorParcelamentoAtraso = indicadorParcelamentoAtraso;
        this.numeroDiasParcelamentoAtraso = numeroDiasParcelamentoAtraso;
        this.localidadeFinal = localidadeFinal;
        this.localidadeInicial = localidadeInicial;
        this.negativacaoComando = negativacaoComando;
        this.cliente = cliente;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
    }

    /** default constructor */
    public NegativacaoCriterio() {
    }

    /** minimal constructor */
    public NegativacaoCriterio(Integer id, String descricaoTitulo, String descricaoSolicitacao, short indicadorNegativacaoImovelParalisacao, short indicadorNegativacaoImovelSituacaoCobranca, short indicadorNegativacaoContaRevisao, short indicadorNegativacaoGuiaPagamento, short indicadorNegativacaoInquilinoDebitoContaMes, short indicadorNegativacaoRecebimentoCartaParcelamento, short indicadorUso, Date ultimaAlteracao, BigDecimal valorMinimoDebito, int quantidadeMinimaContas, BigDecimal valorMaximoDebito, int quantidadeMaximaContas,  short indicadorParcelamentoAtraso, Localidade localidadeFinal, Localidade localidadeInicial, gcom.cobranca.NegativacaoComando negativacaoComando, Cliente cliente, ClienteRelacaoTipo clienteRelacaoTipo) {
        this.id = id;
        this.descricaoTitulo = descricaoTitulo;
        this.descricaoSolicitacao = descricaoSolicitacao;
        this.indicadorNegativacaoImovelParalisacao = indicadorNegativacaoImovelParalisacao;
        this.indicadorNegativacaoImovelSituacaoCobranca = indicadorNegativacaoImovelSituacaoCobranca;
        this.indicadorNegativacaoContaRevisao = indicadorNegativacaoContaRevisao;
        this.indicadorNegativacaoGuiaPagamento = indicadorNegativacaoGuiaPagamento;
        this.indicadorNegativacaoInquilinoDebitoContaMes = indicadorNegativacaoInquilinoDebitoContaMes;
        this.indicadorNegativacaoRecebimentoCartaParcelamento = indicadorNegativacaoRecebimentoCartaParcelamento;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorMinimoDebito = valorMinimoDebito;
        this.quantidadeMinimaContas = quantidadeMinimaContas;
        this.valorMaximoDebito = valorMaximoDebito;
        this.quantidadeMaximaContas = quantidadeMaximaContas;
        this.indicadorParcelamentoAtraso = indicadorParcelamentoAtraso;
        this.localidadeFinal = localidadeFinal;
        this.localidadeInicial = localidadeInicial;
        this.negativacaoComando = negativacaoComando;
        this.cliente = cliente;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoTitulo() {
        return this.descricaoTitulo;
    }

    public void setDescricaoTitulo(String descricaoTitulo) {
        this.descricaoTitulo = descricaoTitulo;
    }

    public String getDescricaoSolicitacao() {
        return this.descricaoSolicitacao;
    }

    public void setDescricaoSolicitacao(String descricaoSolicitacao) {
        this.descricaoSolicitacao = descricaoSolicitacao;
    }

    public Integer getCodigoSetorComercialInicial() {
        return this.codigoSetorComercialInicial;
    }

    public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
        this.codigoSetorComercialInicial = codigoSetorComercialInicial;
    }

    public Integer getCodigoSetorComercialFinal() {
        return this.codigoSetorComercialFinal;
    }

    public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
        this.codigoSetorComercialFinal = codigoSetorComercialFinal;
    }

    public Integer getAnoMesReferenciaContaInicial() {
        return this.anoMesReferenciaContaInicial;
    }

    public void setAnoMesReferenciaContaInicial(Integer anoMesReferenciaContaInicial) {
        this.anoMesReferenciaContaInicial = anoMesReferenciaContaInicial;
    }

    public Integer getAnoMesReferenciaContaFinal() {
        return this.anoMesReferenciaContaFinal;
    }

    public void setAnoMesReferenciaContaFinal(Integer anoMesReferenciaContaFinal) {
        this.anoMesReferenciaContaFinal = anoMesReferenciaContaFinal;
    }

    public Date getDataVencimentoDebitoInicial() {
        return this.dataVencimentoDebitoInicial;
    }

    public void setDataVencimentoDebitoInicial(Date dataVencimentoDebitoInicial) {
        this.dataVencimentoDebitoInicial = dataVencimentoDebitoInicial;
    }

    public Date getDataVencimentoDebitoFinal() {
        return this.dataVencimentoDebitoFinal;
    }

    public void setDataVencimentoDebitoFinal(Date dataVencimentoDebitoFinal) {
        this.dataVencimentoDebitoFinal = dataVencimentoDebitoFinal;
    }

    public Integer getQuantidadeMaximaInclusoes() {
        return this.quantidadeMaximaInclusoes;
    }

    public void setQuantidadeMaximaInclusoes(Integer quantidadeMaximaInclusoes) {
        this.quantidadeMaximaInclusoes = quantidadeMaximaInclusoes;
    }

    public short getIndicadorNegativacaoImovelParalisacao() {
        return this.indicadorNegativacaoImovelParalisacao;
    }

    public void setIndicadorNegativacaoImovelParalisacao(short indicadorNegativacaoImovelParalisacao) {
        this.indicadorNegativacaoImovelParalisacao = indicadorNegativacaoImovelParalisacao;
    }

    public short getIndicadorNegativacaoImovelSituacaoCobranca() {
        return this.indicadorNegativacaoImovelSituacaoCobranca;
    }

    public void setIndicadorNegativacaoImovelSituacaoCobranca(short indicadorNegativacaoImovelSituacaoCobranca) {
        this.indicadorNegativacaoImovelSituacaoCobranca = indicadorNegativacaoImovelSituacaoCobranca;
    }

    public short getIndicadorNegativacaoContaRevisao() {
        return this.indicadorNegativacaoContaRevisao;
    }

    public void setIndicadorNegativacaoContaRevisao(short indicadorNegativacaoContaRevisao) {
        this.indicadorNegativacaoContaRevisao = indicadorNegativacaoContaRevisao;
    }

    public short getIndicadorNegativacaoGuiaPagamento() {
        return this.indicadorNegativacaoGuiaPagamento;
    }

    public void setIndicadorNegativacaoGuiaPagamento(short indicadorNegativacaoGuiaPagamento) {
        this.indicadorNegativacaoGuiaPagamento = indicadorNegativacaoGuiaPagamento;
    }

    public short getIndicadorNegativacaoInquilinoDebitoContaMes() {
        return this.indicadorNegativacaoInquilinoDebitoContaMes;
    }

    public void setIndicadorNegativacaoInquilinoDebitoContaMes(short indicadorNegativacaoInquilinoDebitoContaMes) {
        this.indicadorNegativacaoInquilinoDebitoContaMes = indicadorNegativacaoInquilinoDebitoContaMes;
    }

    public short getIndicadorNegativacaoRecebimentoCartaParcelamento() {
        return this.indicadorNegativacaoRecebimentoCartaParcelamento;
    }

    public void setIndicadorNegativacaoRecebimentoCartaParcelamento(short indicadorNegativacaoRecebimentoCartaParcelamento) {
        this.indicadorNegativacaoRecebimentoCartaParcelamento = indicadorNegativacaoRecebimentoCartaParcelamento;
    }

    public Short getNumeroDiasAtrasoRecebimentoCartaParcelamento() {
        return this.numeroDiasAtrasoRecebimentoCartaParcelamento;
    }

    public void setNumeroDiasAtrasoRecebimentoCartaParcelamento(Short numeroDiasAtrasoRecebimentoCartaParcelamento) {
        this.numeroDiasAtrasoRecebimentoCartaParcelamento = numeroDiasAtrasoRecebimentoCartaParcelamento;
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

    public BigDecimal getValorMinimoDebito() {
        return this.valorMinimoDebito;
    }

    public void setValorMinimoDebito(BigDecimal valorMinimoDebito) {
        this.valorMinimoDebito = valorMinimoDebito;
    }

    public int getQuantidadeMinimaContas() {
        return this.quantidadeMinimaContas;
    }

    public void setQuantidadeMinimaContas(int quantidadeMinimaContas) {
        this.quantidadeMinimaContas = quantidadeMinimaContas;
    }

    public BigDecimal getValorMaximoDebito() {
        return this.valorMaximoDebito;
    }

    public void setValorMaximoDebito(BigDecimal valorMaximoDebito) {
        this.valorMaximoDebito = valorMaximoDebito;
    }

    public int getQuantidadeMaximaContas() {
        return this.quantidadeMaximaContas;
    }

    public void setQuantidadeMaximaContas(int quantidadeMaximaContas) {
        this.quantidadeMaximaContas = quantidadeMaximaContas;
    }

    public short getIndicadorParcelamentoAtraso() {
        return this.indicadorParcelamentoAtraso;
    }

    public void setIndicadorParcelamentoAtraso(short indicadorParcelamentoAtraso) {
        this.indicadorParcelamentoAtraso = indicadorParcelamentoAtraso;
    }

    public Integer getNumeroDiasParcelamentoAtraso() {
        return this.numeroDiasParcelamentoAtraso;
    }

    public void setNumeroDiasParcelamentoAtraso(Integer numeroDiasParcelamentoAtraso) {
        this.numeroDiasParcelamentoAtraso = numeroDiasParcelamentoAtraso;
    }

    public Localidade getLocalidadeFinal() {
        return this.localidadeFinal;
    }

    public void setLocalidadeFinal(Localidade localidadeFinal) {
        this.localidadeFinal = localidadeFinal;
    }

    public Localidade getLocalidadeInicial() {
        return this.localidadeInicial;
    }

    public void setLocalidadeInicial(Localidade localidadeInicial) {
        this.localidadeInicial = localidadeInicial;
    }

    public gcom.cobranca.NegativacaoComando getNegativacaoComando() {
        return this.negativacaoComando;
    }

    public void setNegativacaoComando(gcom.cobranca.NegativacaoComando negativacaoComando) {
        this.negativacaoComando = negativacaoComando;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteRelacaoTipo getClienteRelacaoTipo() {
        return this.clienteRelacaoTipo;
    }

    public void setClienteRelacaoTipo(ClienteRelacaoTipo clienteRelacaoTipo) {
        this.clienteRelacaoTipo = clienteRelacaoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getNumeroDiasRetorno() {
		return numeroDiasRetorno;
	}

	public void setNumeroDiasRetorno(Integer numeroDiasRetorno) {
		this.numeroDiasRetorno = numeroDiasRetorno;
	}

}

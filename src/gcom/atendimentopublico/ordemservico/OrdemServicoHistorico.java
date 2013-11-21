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
package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaDocumento;

import java.math.BigDecimal;
import java.util.Date;

public class OrdemServicoHistorico {

	/** identifier field */
    private Integer id;

    /** persistent field */
    private short situacao;

    /** persistent field */
    private Date dataGeracao;

    /** nullable persistent field */
    private Date dataEmissao;

    /** nullable persistent field */
    private Date dataEncerramento;

    /** nullable persistent field */
    private String descricaoParecerEncerramento;

    /** nullable persistent field */
    private String observacao;

    /** nullable persistent field */
    private BigDecimal areaPavimento;

    /** nullable persistent field */
    private BigDecimal valorOriginal;

    /** nullable persistent field */
    private BigDecimal valorAtual;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private short indicadorComercialAtualizado;

    /** nullable persistent field */
    private BigDecimal percentualCobranca;

    /** persistent field */
    private AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo;

    /** persistent field */
    private RegistroAtendimento registroAtendimento;

    /** persistent field */
    private CobrancaDocumento cobrancaDocumento;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva fiscalizacaoColetiva;

    /** persistent field */
    private OrdemServicoHistorico osReferencia;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeOriginal;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeAtual;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo osReferidaRetornoTipo;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipoReferencia;
    
    private short indicadorServicoDiagnosticado;

    /** persistent field */
    private Imovel imovel; 
    
    /** persistent field */
    private Date dataFiscalizacaoSituacao;

    /** persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;
    
    /** persistent field */
    private Short codigoRetornoVistoria;
    
    /** persistent field */
    private Short codigoTipoRecebimento;
    
    /** persistent field */
    private Short codigoRetornoFiscalizacaoColetiva;

    /** persistent field */
    private String parecerFiscalizacaoColetiva;
    
    private short indicadorProgramada;
    
    private Short indicadorEncerramentoAutomatico;
    
    public OrdemServicoHistorico(){}

	public BigDecimal getAreaPavimento() {
		return areaPavimento;
	}

	public void setAreaPavimento(BigDecimal areaPavimento) {
		this.areaPavimento = areaPavimento;
	}

	public AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
		return atendimentoMotivoEncerramento;
	}

	public void setAtendimentoMotivoEncerramento(
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}

	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public Short getCodigoRetornoFiscalizacaoColetiva() {
		return codigoRetornoFiscalizacaoColetiva;
	}

	public void setCodigoRetornoFiscalizacaoColetiva(
			Short codigoRetornoFiscalizacaoColetiva) {
		this.codigoRetornoFiscalizacaoColetiva = codigoRetornoFiscalizacaoColetiva;
	}

	public Short getCodigoRetornoVistoria() {
		return codigoRetornoVistoria;
	}

	public void setCodigoRetornoVistoria(Short codigoRetornoVistoria) {
		this.codigoRetornoVistoria = codigoRetornoVistoria;
	}

	public Short getCodigoTipoRecebimento() {
		return codigoTipoRecebimento;
	}

	public void setCodigoTipoRecebimento(Short codigoTipoRecebimento) {
		this.codigoTipoRecebimento = codigoTipoRecebimento;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getDataFiscalizacaoSituacao() {
		return dataFiscalizacaoSituacao;
	}

	public void setDataFiscalizacaoSituacao(Date dataFiscalizacaoSituacao) {
		this.dataFiscalizacaoSituacao = dataFiscalizacaoSituacao;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getDescricaoParecerEncerramento() {
		return descricaoParecerEncerramento;
	}

	public void setDescricaoParecerEncerramento(String descricaoParecerEncerramento) {
		this.descricaoParecerEncerramento = descricaoParecerEncerramento;
	}

	public gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva getFiscalizacaoColetiva() {
		return fiscalizacaoColetiva;
	}

	public void setFiscalizacaoColetiva(
			gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva fiscalizacaoColetiva) {
		this.fiscalizacaoColetiva = fiscalizacaoColetiva;
	}

	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
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

	public short getIndicadorComercialAtualizado() {
		return indicadorComercialAtualizado;
	}

	public void setIndicadorComercialAtualizado(short indicadorComercialAtualizado) {
		this.indicadorComercialAtualizado = indicadorComercialAtualizado;
	}

	public Short getIndicadorEncerramentoAutomatico() {
		return indicadorEncerramentoAutomatico;
	}

	public void setIndicadorEncerramentoAutomatico(
			Short indicadorEncerramentoAutomatico) {
		this.indicadorEncerramentoAutomatico = indicadorEncerramentoAutomatico;
	}

	public short getIndicadorProgramada() {
		return indicadorProgramada;
	}

	public void setIndicadorProgramada(short indicadorProgramada) {
		this.indicadorProgramada = indicadorProgramada;
	}

	public short getIndicadorServicoDiagnosticado() {
		return indicadorServicoDiagnosticado;
	}

	public void setIndicadorServicoDiagnosticado(short indicadorServicoDiagnosticado) {
		this.indicadorServicoDiagnosticado = indicadorServicoDiagnosticado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public OrdemServicoHistorico getOsReferencia() {
		return osReferencia;
	}

	public void setOsReferencia(
			OrdemServicoHistorico osReferencia) {
		this.osReferencia = osReferencia;
	}

	public gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo getOsReferidaRetornoTipo() {
		return osReferidaRetornoTipo;
	}

	public void setOsReferidaRetornoTipo(
			gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo osReferidaRetornoTipo) {
		this.osReferidaRetornoTipo = osReferidaRetornoTipo;
	}

	public String getParecerFiscalizacaoColetiva() {
		return parecerFiscalizacaoColetiva;
	}

	public void setParecerFiscalizacaoColetiva(String parecerFiscalizacaoColetiva) {
		this.parecerFiscalizacaoColetiva = parecerFiscalizacaoColetiva;
	}

	public BigDecimal getPercentualCobranca() {
		return percentualCobranca;
	}

	public void setPercentualCobranca(BigDecimal percentualCobranca) {
		this.percentualCobranca = percentualCobranca;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo getServicoNaoCobrancaMotivo() {
		return servicoNaoCobrancaMotivo;
	}

	public void setServicoNaoCobrancaMotivo(
			gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo) {
		this.servicoNaoCobrancaMotivo = servicoNaoCobrancaMotivo;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade getServicoTipoPrioridadeAtual() {
		return servicoTipoPrioridadeAtual;
	}

	public void setServicoTipoPrioridadeAtual(
			gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeAtual) {
		this.servicoTipoPrioridadeAtual = servicoTipoPrioridadeAtual;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade getServicoTipoPrioridadeOriginal() {
		return servicoTipoPrioridadeOriginal;
	}

	public void setServicoTipoPrioridadeOriginal(
			gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeOriginal) {
		this.servicoTipoPrioridadeOriginal = servicoTipoPrioridadeOriginal;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipoReferencia() {
		return servicoTipoReferencia;
	}

	public void setServicoTipoReferencia(
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipoReferencia) {
		this.servicoTipoReferencia = servicoTipoReferencia;
	}

	public short getSituacao() {
		return situacao;
	}

	public void setSituacao(short situacao) {
		this.situacao = situacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(BigDecimal valorAtual) {
		this.valorAtual = valorAtual;
	}

	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	} 
}

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
package gcom.arrecadacao;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.debito.DebitoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class DevolucaoHistorico implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	/** identifier field */
	private Integer id;

	/** persistent field */
	private BigDecimal valorDevolucao;

	/** persistent field */
	private int anoMesReferenciaArrecadacao;

	/** persistent field */
	private Date dataDevolucao;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** persistent field */
	private Integer anoMesReferenciaDevolucao;
	
	/** persistent field */
	private Integer codigoAgente;

	/** persistent field */
	private DevolucaoSituacao devolucaoSituacaoAnterior;

	/** persistent field */
	private DevolucaoSituacao devolucaoSituacaoAtual;

	/** persistent field */
	private GuiaDevolucao guiaDevolucao;

	/** persistent field */
	private AvisoBancario avisoBancario;

	/** persistent field */
	private Cliente cliente;

	/** persistent field */
	private Imovel imovel;

	/** persistent field */
	private Localidade localidade;

	/** persistent field */
	private DebitoTipo debitoTipo;
	
	/** persistent field */
	private gcom.faturamento.credito.CreditoARealizarGeral creditoARealizarGeral;
	
	/** persistent field */
	private gcom.arrecadacao.ArrecadadorMovimentoItem arrecadadorMovimentoItem;
	
	 /** persistent field */
    private CobrancaDocumento cobrancaDocumentoAgregador;
    
    /** persistent field */
    private DocumentoTipo documentoTipoAgregador;
	
	/** full constructor */
	public DevolucaoHistorico(BigDecimal valorDevolucao,
			int anoMesReferenciaArrecadacao, Date dataDevolucao,
			Date ultimaAlteracao,
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAnterior,
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAtual,
			gcom.arrecadacao.GuiaDevolucao guiaDevolucao,
			AvisoBancario avisoBancario, Integer anoMesReferenciaDevolucao, 
			Integer codigoAgente,
			Cliente cliente, Imovel imovel, Localidade localidade,
			DebitoTipo debitoTipo) {
		this.valorDevolucao = valorDevolucao;
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
		this.dataDevolucao = dataDevolucao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.devolucaoSituacaoAnterior = devolucaoSituacaoAnterior;
		this.devolucaoSituacaoAtual = devolucaoSituacaoAtual;
		this.guiaDevolucao = guiaDevolucao;
		this.avisoBancario = avisoBancario;
		this.anoMesReferenciaDevolucao = anoMesReferenciaDevolucao;
		this.codigoAgente = codigoAgente;
		this.cliente = cliente;
		this.imovel = imovel;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
	}

	/** default constructor */
	public DevolucaoHistorico() {
	}

	/** minimal constructor */
	public DevolucaoHistorico(BigDecimal valorDevolucao,
			int anoMesReferenciaArrecadacao, Date dataDevolucao,
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAnterior,
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAtual,
			gcom.arrecadacao.GuiaDevolucao guiaDevolucao,
			AvisoBancario avisoBancario, int anoMesReferenciaDevolucao,
			Integer codigoAgente,
			Cliente cliente, Imovel imovel, Localidade localidade,
			DebitoTipo debitoTipo) {
		this.valorDevolucao = valorDevolucao;
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
		this.dataDevolucao = dataDevolucao;
		this.devolucaoSituacaoAnterior = devolucaoSituacaoAnterior;
		this.devolucaoSituacaoAtual = devolucaoSituacaoAtual;
		this.guiaDevolucao = guiaDevolucao;
		this.avisoBancario = avisoBancario;
		this.anoMesReferenciaDevolucao = anoMesReferenciaDevolucao;
		this.codigoAgente = codigoAgente;
		this.cliente = cliente;
		this.imovel = imovel;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
	}

	public int getAnoMesReferenciaArrecadacao() {
		return anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(int anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public Integer getAnoMesReferenciaDevolucao() {
		return anoMesReferenciaDevolucao;
	}

	public void setAnoMesReferenciaDevolucao(Integer anoMesReferenciaDevolucao) {
		this.anoMesReferenciaDevolucao = anoMesReferenciaDevolucao;
	}

	public AvisoBancario getAvisoBancario() {
		return avisoBancario;
	}

	public void setAvisoBancario(AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public DevolucaoSituacao getDevolucaoSituacaoAnterior() {
		return devolucaoSituacaoAnterior;
	}

	public void setDevolucaoSituacaoAnterior(
			DevolucaoSituacao devolucaoSituacaoAnterior) {
		this.devolucaoSituacaoAnterior = devolucaoSituacaoAnterior;
	}

	public DevolucaoSituacao getDevolucaoSituacaoAtual() {
		return devolucaoSituacaoAtual;
	}

	public void setDevolucaoSituacaoAtual(DevolucaoSituacao devolucaoSituacaoAtual) {
		this.devolucaoSituacaoAtual = devolucaoSituacaoAtual;
	}

	public GuiaDevolucao getGuiaDevolucao() {
		return guiaDevolucao;
	}

	public void setGuiaDevolucao(GuiaDevolucao guiaDevolucao) {
		this.guiaDevolucao = guiaDevolucao;
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

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorDevolucao() {
		return valorDevolucao;
	}

	public void setValorDevolucao(BigDecimal valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public String getFormatarAnoMesParaMesAnoArrecadacao() {

		String anoMesArrecadacaoRecebido = "" + this.getAnoMesReferenciaArrecadacao();
		String mesArrecadacao = anoMesArrecadacaoRecebido.substring(4, 6);
		String anoArrecadacao = anoMesArrecadacaoRecebido.substring(0, 4);
		String anoMesArrecadacaoFormatado = mesArrecadacao + "/" + anoArrecadacao;

		return anoMesArrecadacaoFormatado.toString();
	}
    
    public String getFormatarAnoMesParaMesAnoDevolucao() {

        String anoMesDevolucaoRecebido = "" + this.getAnoMesReferenciaDevolucao();
        String mesDevolucao = anoMesDevolucaoRecebido.substring(4, 6);
        String anoDevolucao = anoMesDevolucaoRecebido.substring(0, 4);
        String anoMesDevolucaoFormatado = mesDevolucao + "/" + anoDevolucao;

        return anoMesDevolucaoFormatado.toString();
    }    

	/**
	 * @return Retorna o campo codigoAgente.
	 */
	public Integer getCodigoAgente() {
		return codigoAgente;
	}

	/**
	 * @param codigoAgente O codigoAgente a ser setado.
	 */
	public void setCodigoAgente(Integer codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	public gcom.arrecadacao.ArrecadadorMovimentoItem getArrecadadorMovimentoItem() {
		return arrecadadorMovimentoItem;
	}

	public void setArrecadadorMovimentoItem(
			gcom.arrecadacao.ArrecadadorMovimentoItem arrecadadorMovimentoItem) {
		this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
	}

	public gcom.faturamento.credito.CreditoARealizarGeral getCreditoARealizarGeral() {
		return creditoARealizarGeral;
	}

	public void setCreditoARealizarGeral(
			gcom.faturamento.credito.CreditoARealizarGeral creditoARealizarGeral) {
		this.creditoARealizarGeral = creditoARealizarGeral;
	}

	public CobrancaDocumento getCobrancaDocumentoAgregador() {
		return cobrancaDocumentoAgregador;
	}

	public void setCobrancaDocumentoAgregador(
			CobrancaDocumento cobrancaDocumentoAgregador) {
		this.cobrancaDocumentoAgregador = cobrancaDocumentoAgregador;
	}

	public DocumentoTipo getDocumentoTipoAgregador() {
		return documentoTipoAgregador;
	}

	public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
		this.documentoTipoAgregador = documentoTipoAgregador;
	}

	

}

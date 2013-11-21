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
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Devolucao extends ObjetoTransacao {

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
	private gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAnterior;

	/** persistent field */
	private gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAtual;

	/** persistent field */
	private gcom.arrecadacao.GuiaDevolucao guiaDevolucao;

	/** persistent field */
	private AvisoBancario avisoBancario;

	/** persistent field */
	private Integer anoMesReferenciaDevolucao;

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
	
	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores [SF0004] - Processar
	 * Registro Código F Autor: Sávio Luiz Data: 15/02/2006
	 */

	private Date dataPrevistaCreditoHelper;
	
	private CobrancaDocumento cobrancaDocumento;
	
	private DocumentoTipo documentoTipoAgregador;

	/** full constructor */
	public Devolucao(BigDecimal valorDevolucao,
			int anoMesReferenciaArrecadacao, Date dataDevolucao,
			Date ultimaAlteracao,
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAnterior,
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAtual,
			gcom.arrecadacao.GuiaDevolucao guiaDevolucao,
			AvisoBancario avisoBancario, Integer anoMesReferenciaDevolucao,
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
		this.cliente = cliente;
		this.imovel = imovel;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
	}

	/** default constructor */
	public Devolucao() {
	}

	/** minimal constructor */
	public Devolucao(BigDecimal valorDevolucao,
			int anoMesReferenciaArrecadacao, Date dataDevolucao,
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAnterior,
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAtual,
			gcom.arrecadacao.GuiaDevolucao guiaDevolucao,
			AvisoBancario avisoBancario, int anoMesReferenciaDevolucao,
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
		this.cliente = cliente;
		this.imovel = imovel;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorDevolucao() {
		return this.valorDevolucao;
	}

	public void setValorDevolucao(BigDecimal valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public int getAnoMesReferenciaArrecadacao() {
		return this.anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(int anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public Date getDataDevolucao() {
		return this.dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.arrecadacao.DevolucaoSituacao getDevolucaoSituacaoAnterior() {
		return this.devolucaoSituacaoAnterior;
	}

	public void setDevolucaoSituacaoAnterior(
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAnterior) {
		this.devolucaoSituacaoAnterior = devolucaoSituacaoAnterior;
	}

	public gcom.arrecadacao.DevolucaoSituacao getDevolucaoSituacaoAtual() {
		return this.devolucaoSituacaoAtual;
	}

	public void setDevolucaoSituacaoAtual(
			gcom.arrecadacao.DevolucaoSituacao devolucaoSituacaoAtual) {
		this.devolucaoSituacaoAtual = devolucaoSituacaoAtual;
	}

	public gcom.arrecadacao.GuiaDevolucao getGuiaDevolucao() {
		return this.guiaDevolucao;
	}

	public void setGuiaDevolucao(gcom.arrecadacao.GuiaDevolucao guiaDevolucao) {
		this.guiaDevolucao = guiaDevolucao;
	}

	public AvisoBancario getAvisoBancario() {
		return this.avisoBancario;
	}

	public void setAvisoBancario(AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Returns the anoMesReferenciaDevolucao.
	 */
	public Integer getAnoMesReferenciaDevolucao() {
		return anoMesReferenciaDevolucao;
	}

	/**
	 * @param anoMesReferenciaDevolucao
	 *            The anoMesReferenciaDevolucao to set.
	 */
	public void setAnoMesReferenciaDevolucao(Integer anoMesReferenciaDevolucao) {
		this.anoMesReferenciaDevolucao = anoMesReferenciaDevolucao;
	}

	/**
	 * @return Returns the cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            The cliente to set.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Returns the imovel.
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * @param imovel
	 *            The imovel to set.
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Returns the localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade
	 *            The localidade to set.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Returns the debitoTipo.
	 */
	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	/**
	 * @param debitoTipo
	 *            The debitoTipo to set.
	 */
	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroDevolucao filtroDevolucao = new FiltroDevolucao();
		
		filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade(FiltroDevolucao.DEVOLUCAO_SITUACAO_ATUAL);
		filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade(FiltroDevolucao.DEVOLUCAO_SITUACAO_ANTERIOR);
		filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade(FiltroDevolucao.GUIA_DEVOLUCAO);
		filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade(FiltroDevolucao.AVISO_BANCARIO);
		filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade(FiltroDevolucao.CLIENTE);
		filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade(FiltroDevolucao.IMOVEL);
		filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade(FiltroDevolucao.LOCALIDADE);
		filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade(FiltroDevolucao.DEBITO_TIPO_);
		
		filtroDevolucao.adicionarParametro(
				new ParametroSimples(FiltroDevolucao.ID, this.getId()));
		return filtroDevolucao; 
	}

	public Date getDataPrevistaCreditoHelper() {
		return dataPrevistaCreditoHelper;
	}

	public void setDataPrevistaCreditoHelper(Date dataPrevistaCreditoHelper) {
		this.dataPrevistaCreditoHelper = dataPrevistaCreditoHelper;
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

	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public DocumentoTipo getDocumentoTipoAgregador() {
		return documentoTipoAgregador;
	}

	public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
		this.documentoTipoAgregador = documentoTipoAgregador;
	}
	
	public String getFormatarAnoMesDevolucaoParaMesAno() {

		String anoMesRecebido = "" + this.getAnoMesReferenciaDevolucao();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
	

}
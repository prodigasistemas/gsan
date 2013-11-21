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
* Anderson Italo Felinto de Lima
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
package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de inserir as
 * ações de cobrança.
 * 
 * @author Sávio Luiz
 * @date 17/09/2007
 */
public class AcaoCobrancaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String descricaoAcao;

	private String numeroDiasValidade;

	private String idAcaoPredecessora;

	private String numeroDiasEntreAcoes;

	private String idTipoDocumentoGerado;

	private String idSituacaoLigacaoAgua;

	private String idSituacaoLigacaoEsgoto;

	private String idCobrancaCriterio;

	private String descricaoCobrancaCriterio;

	private String idServicoTipo;

	private String descricaoServicoTipo;

	private String ordemCronograma;

	private String icCompoeCronograma;

	private String icAcaoObrigatoria;

	private String icRepetidaCiclo;

	private String icSuspensaoAbastecimento;
	
	private String icDebitosACobrar;
	
	private String icCreditosARealizar;
	
	private String icNotasPromissoria; 
	
	private String icAcrescimosImpontualidade;
	
	private String icGeraTaxa;
	
	private String icEmitirBoletimCadastro;
	
	private String icImoveisSemDebitos;
	
	private String numeroDiasVencimento;
	
	private String icMetasCronograma;
	
	private String icOrdenamentoCronograma;
	
	private String icOrdenamentoEventual;
	
	private String icDebitoInterfereAcao;
	
	private String numeroDiasRemuneracaoTerceiro;
	
	private String icOrdenarMaiorValor;
	
	private String icValidarItem;

	public String getDescricaoAcao() {
		return descricaoAcao;
	}

	public void setDescricaoAcao(String descricaoAcao) {
		this.descricaoAcao = descricaoAcao;
	}

	public String getDescricaoCobrancaCriterio() {
		return descricaoCobrancaCriterio;
	}

	public void setDescricaoCobrancaCriterio(String descricaoCobrancaCriterio) {
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
	}

	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	public String getIcAcaoObrigatoria() {
		return icAcaoObrigatoria;
	}

	public void setIcAcaoObrigatoria(String icAcaoObrigatoria) {
		this.icAcaoObrigatoria = icAcaoObrigatoria;
	}

	public String getIcAcrescimosImpontualidade() {
		return icAcrescimosImpontualidade;
	}

	public void setIcAcrescimosImpontualidade(String icAcrescimosImpontualidade) {
		this.icAcrescimosImpontualidade = icAcrescimosImpontualidade;
	}

	public String getIcCompoeCronograma() {
		return icCompoeCronograma;
	}

	public void setIcCompoeCronograma(String icCompoeCronograma) {
		this.icCompoeCronograma = icCompoeCronograma;
	}

	public String getIcDebitosACobrar() {
		return icDebitosACobrar;
	}

	public void setIcDebitosACobrar(String icDebitosACobrar) {
		this.icDebitosACobrar = icDebitosACobrar;
	}

	public String getIcEmitirBoletimCadastro() {
		return icEmitirBoletimCadastro;
	}

	public void setIcEmitirBoletimCadastro(String icEmitirBoletimCadastro) {
		this.icEmitirBoletimCadastro = icEmitirBoletimCadastro;
	}

	public String getIcGeraTaxa() {
		return icGeraTaxa;
	}

	public void setIcGeraTaxa(String icGeraTaxa) {
		this.icGeraTaxa = icGeraTaxa;
	}

	public String getIcImoveisSemDebitos() {
		return icImoveisSemDebitos;
	}

	public void setIcImoveisSemDebitos(String icImoveisSemDebitos) {
		this.icImoveisSemDebitos = icImoveisSemDebitos;
	}

	public String getIcRepetidaCiclo() {
		return icRepetidaCiclo;
	}

	public void setIcRepetidaCiclo(String icRepetidaCiclo) {
		this.icRepetidaCiclo = icRepetidaCiclo;
	}

	public String getIcSuspensaoAbastecimento() {
		return icSuspensaoAbastecimento;
	}

	public void setIcSuspensaoAbastecimento(String icSuspensaoAbastecimento) {
		this.icSuspensaoAbastecimento = icSuspensaoAbastecimento;
	}

	public String getIdAcaoPredecessora() {
		return idAcaoPredecessora;
	}

	public void setIdAcaoPredecessora(String idAcaoPredecessora) {
		this.idAcaoPredecessora = idAcaoPredecessora;
	}

	public String getIdCobrancaCriterio() {
		return idCobrancaCriterio;
	}

	public void setIdCobrancaCriterio(String idCobrancaCriterio) {
		this.idCobrancaCriterio = idCobrancaCriterio;
	}

	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	public void setIdSituacaoLigacaoAgua(String idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	public String getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	public void setIdSituacaoLigacaoEsgoto(String idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	public String getIdTipoDocumentoGerado() {
		return idTipoDocumentoGerado;
	}

	public void setIdTipoDocumentoGerado(String idTipoDocumentoGerado) {
		this.idTipoDocumentoGerado = idTipoDocumentoGerado;
	}

	public String getNumeroDiasEntreAcoes() {
		return numeroDiasEntreAcoes;
	}

	public void setNumeroDiasEntreAcoes(String numeroDiasEntreAcoes) {
		this.numeroDiasEntreAcoes = numeroDiasEntreAcoes;
	}

	public String getNumeroDiasValidade() {
		return numeroDiasValidade;
	}

	public void setNumeroDiasValidade(String numeroDiasValidade) {
		this.numeroDiasValidade = numeroDiasValidade;
	}

	public String getOrdemCronograma() {
		return ordemCronograma;
	}

	public void setOrdemCronograma(String ordemCronograma) {
		this.ordemCronograma = ordemCronograma;
	}

	/**
	 * @return Retorna o campo numeroDiasVencimento.
	 */
	public String getNumeroDiasVencimento() {
		return numeroDiasVencimento;
	}

	/**
	 * @param numeroDiasVencimento O numeroDiasVencimento a ser setado.
	 */
	public void setNumeroDiasVencimento(String numeroDiasVencimento) {
		this.numeroDiasVencimento = numeroDiasVencimento;
	}

	/**
	 * @return Retorna o campo icDebitoInterfereAcao.
	 */
	public String getIcDebitoInterfereAcao() {
		return icDebitoInterfereAcao;
	}

	/**
	 * @param icDebitoInterfereAcao O icDebitoInterfereAcao a ser setado.
	 */
	public void setIcDebitoInterfereAcao(String icDebitoInterfereAcao) {
		this.icDebitoInterfereAcao = icDebitoInterfereAcao;
	}

	/**
	 * @return Retorna o campo icMetasCronograma.
	 */
	public String getIcMetasCronograma() {
		return icMetasCronograma;
	}

	/**
	 * @param icMetasCronograma O icMetasCronograma a ser setado.
	 */
	public void setIcMetasCronograma(String icMetasCronograma) {
		this.icMetasCronograma = icMetasCronograma;
	}

	/**
	 * @return Retorna o campo icOrdenamentoCronograma.
	 */
	public String getIcOrdenamentoCronograma() {
		return icOrdenamentoCronograma;
	}

	/**
	 * @param icOrdenamentoCronograma O icOrdenamentoCronograma a ser setado.
	 */
	public void setIcOrdenamentoCronograma(String icOrdenamentoCronograma) {
		this.icOrdenamentoCronograma = icOrdenamentoCronograma;
	}

	/**
	 * @return Retorna o campo icOrdenamentoEventual.
	 */
	public String getIcOrdenamentoEventual() {
		return icOrdenamentoEventual;
	}

	/**
	 * @param icOrdenamentoEventual O icOrdenamentoEventual a ser setado.
	 */
	public void setIcOrdenamentoEventual(String icOrdenamentoEventual) {
		this.icOrdenamentoEventual = icOrdenamentoEventual;
	}

	/**
	 * @return Retorna o campo numeroDiasRemuneracaoTerceiro.
	 */
	public String getNumeroDiasRemuneracaoTerceiro() {
		return numeroDiasRemuneracaoTerceiro;
	}

	/**
	 * @param numeroDiasRemuneracaoTerceiro O numeroDiasRemuneracaoTerceiro a ser setado.
	 */
	public void setNumeroDiasRemuneracaoTerceiro(
			String numeroDiasRemuneracaoTerceiro) {
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
	}

	public String getIcCreditosARealizar() {
		return icCreditosARealizar;
	}

	public void setIcCreditosARealizar(String icCreditosARealizar) {
		this.icCreditosARealizar = icCreditosARealizar;
	}

	public String getIcNotasPromissoria() {
		return icNotasPromissoria;
	}

	public void setIcNotasPromissoria(String icNotasPromissoria) {
		this.icNotasPromissoria = icNotasPromissoria;
	}

	public String getIcOrdenarMaiorValor() {
		return icOrdenarMaiorValor;
	}

	public void setIcOrdenarMaiorValor(String icOrdenarMaiorValor) {
		this.icOrdenarMaiorValor = icOrdenarMaiorValor;
	}

	public String getIcValidarItem() {
		return icValidarItem;
	}

	public void setIcValidarItem(String icValidarItem) {
		this.icValidarItem = icValidarItem;
	}
	
	
}
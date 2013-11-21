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
package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;


public class AdicionarSolicitacaoEspecificacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String descricaoSolicitacao;

	private String prazoPrevisaoAtendimento;

	private String indicadorPavimentoCalcada;

	private String indicadorPavimentoRua;

	private String indicadorLigacaoAgua;

	private String indicadorCobrancaMaterial;

	private String indicadorParecerEncerramento;

	private String indicadorGerarDebito;

	private String indicadorGerarCredito;

	private String indicadorCliente;

	private String indicadorMatriculaImovel;

	private String idSituacaoImovel;

	private String idUnidadeTramitacao;

	private String descricaoUnidadeTramitacao;

	private String indicadorGeraOrdemServico;

	private String idServicoOS;

	private String descricaoServicoOS;

	private String idTipoServico;

	private String descricaoTipoServico;

	private String ordemExecucao;

	private String indicadorVerificarDebito;

	private String idDebitoTipo;

	private String descricaoDebitoTipo;

	private String valorDebito;

	private String indicadorPermiteAlterarValor;

	private String indicadorCobrarJuros;

	private String indicadorEncerramentoAutomatico;

	private String idTipoSolicitacao;

	private String idEspecificacao;

	private String indicadorUrgencia;

	private String indicadorInformarContaRA;

	private String indicadorInformarPagamentoDP;
	
	private String indicadorAlterarVencimentoAlternativo;
	
	private String indicadorLojaVirtual;
	
	public String getIdEspecificacao() {
		return idEspecificacao;
	}

	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}

	public String getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}

	public void setIdTipoSolicitacao(String idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}

	/**
	 * @return Retorna o campo indicadorEncerramentoAutomatico.
	 */
	public String getIndicadorEncerramentoAutomatico() {
		return indicadorEncerramentoAutomatico;
	}

	/**
	 * @param indicadorEncerramentoAutomatico
	 *            O indicadorEncerramentoAutomatico a ser setado.
	 */
	public void setIndicadorEncerramentoAutomatico(
			String indicadorEncerramentoAutomatico) {
		this.indicadorEncerramentoAutomatico = indicadorEncerramentoAutomatico;
	}

	/**
	 * @return Retorna o campo indicadorVerificarDebito.
	 */
	public String getIndicadorVerificarDebito() {
		return indicadorVerificarDebito;
	}

	/**
	 * @param indicadorVerificarDebito
	 *            O indicadorVerificarDebito a ser setado.
	 */
	public void setIndicadorVerificarDebito(String indicadorVerificarDebito) {
		this.indicadorVerificarDebito = indicadorVerificarDebito;
	}

	public String getDescricaoServicoOS() {
		return descricaoServicoOS;
	}

	public void setDescricaoServicoOS(String descricaoServicoOS) {
		this.descricaoServicoOS = descricaoServicoOS;
	}

	public String getDescricaoSolicitacao() {
		return descricaoSolicitacao;
	}

	public void setDescricaoSolicitacao(String descricaoSolicitacao) {
		this.descricaoSolicitacao = descricaoSolicitacao;
	}

	public String getDescricaoUnidadeTramitacao() {
		return descricaoUnidadeTramitacao;
	}

	public void setDescricaoUnidadeTramitacao(String descricaoUnidadeTramitacao) {
		this.descricaoUnidadeTramitacao = descricaoUnidadeTramitacao;
	}

	public String getIdServicoOS() {
		return idServicoOS;
	}

	public void setIdServicoOS(String idServicoOS) {
		this.idServicoOS = idServicoOS;
	}

	public String getIdSituacaoImovel() {
		return idSituacaoImovel;
	}

	public void setIdSituacaoImovel(String idSituacaoImovel) {
		this.idSituacaoImovel = idSituacaoImovel;
	}

	public String getIdUnidadeTramitacao() {
		return idUnidadeTramitacao;
	}

	public void setIdUnidadeTramitacao(String idUnidadeTramitacao) {
		this.idUnidadeTramitacao = idUnidadeTramitacao;
	}

	public String getIndicadorCliente() {
		return indicadorCliente;
	}

	public void setIndicadorCliente(String indicadorCliente) {
		this.indicadorCliente = indicadorCliente;
	}

	public String getIndicadorCobrancaMaterial() {
		return indicadorCobrancaMaterial;
	}

	public void setIndicadorCobrancaMaterial(String indicadorCobrancaMaterial) {
		this.indicadorCobrancaMaterial = indicadorCobrancaMaterial;
	}

	public String getIndicadorGeraOrdemServico() {
		return indicadorGeraOrdemServico;
	}

	public void setIndicadorGeraOrdemServico(String indicadorGeraOrdemServico) {
		this.indicadorGeraOrdemServico = indicadorGeraOrdemServico;
	}

	public String getIndicadorGerarCredito() {
		return indicadorGerarCredito;
	}

	public void setIndicadorGerarCredito(String indicadorGerarCredito) {
		this.indicadorGerarCredito = indicadorGerarCredito;
	}

	public String getIndicadorGerarDebito() {
		return indicadorGerarDebito;
	}

	public void setIndicadorGerarDebito(String indicadorGerarDebito) {
		this.indicadorGerarDebito = indicadorGerarDebito;
	}

	public String getIndicadorParecerEncerramento() {
		return indicadorParecerEncerramento;
	}

	public void setIndicadorParecerEncerramento(
			String indicadorParecerEncerramento) {
		this.indicadorParecerEncerramento = indicadorParecerEncerramento;
	}

	public String getIndicadorPavimentoCalcada() {
		return indicadorPavimentoCalcada;
	}

	public void setIndicadorPavimentoCalcada(String indicadorPavimentoCalcada) {
		this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
	}

	public String getIndicadorPavimentoRua() {
		return indicadorPavimentoRua;
	}

	public void setIndicadorPavimentoRua(String indicadorPavimentoRua) {
		this.indicadorPavimentoRua = indicadorPavimentoRua;
	}

	public String getPrazoPrevisaoAtendimento() {
		return prazoPrevisaoAtendimento;
	}

	public void setPrazoPrevisaoAtendimento(String prazoPrevisaoAtendimento) {
		this.prazoPrevisaoAtendimento = prazoPrevisaoAtendimento;
	}

	public String getOrdemExecucao() {
		return ordemExecucao;
	}

	public void setOrdemExecucao(String ordemExecucao) {
		this.ordemExecucao = ordemExecucao;
	}

	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}

	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}

	public String getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String getIndicadorMatriculaImovel() {
		return indicadorMatriculaImovel;
	}

	public void setIndicadorMatriculaImovel(String indicadorMatriculaImovel) {
		this.indicadorMatriculaImovel = indicadorMatriculaImovel;
	}

	public String getIndicadorLigacaoAgua() {
		return indicadorLigacaoAgua;
	}

	public void setIndicadorLigacaoAgua(String indicadorLigacaoAgua) {
		this.indicadorLigacaoAgua = indicadorLigacaoAgua;
	}

	public String getDescricaoDebitoTipo() {
		return descricaoDebitoTipo;
	}

	public void setDescricaoDebitoTipo(String descricaoDebitoTipo) {
		this.descricaoDebitoTipo = descricaoDebitoTipo;
	}

	public String getIdDebitoTipo() {
		return idDebitoTipo;
	}

	public void setIdDebitoTipo(String idDebitoTipo) {
		this.idDebitoTipo = idDebitoTipo;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getIndicadorCobrarJuros() {
		return indicadorCobrarJuros;
	}

	public void setIndicadorCobrarJuros(String indicadorCobrarJuros) {
		this.indicadorCobrarJuros = indicadorCobrarJuros;
	}

	public String getIndicadorPermiteAlterarValor() {
		return indicadorPermiteAlterarValor;
	}

	public void setIndicadorPermiteAlterarValor(
			String indicadorPermiteAlterarValor) {
		this.indicadorPermiteAlterarValor = indicadorPermiteAlterarValor;
	}

	public String getIndicadorUrgencia() {
		return indicadorUrgencia;
	}

	public void setIndicadorUrgencia(String indicadorUrgencia) {
		this.indicadorUrgencia = indicadorUrgencia;
	}

	public String getIndicadorInformarContaRA() {
		return indicadorInformarContaRA;
	}

	public void setIndicadorInformarContaRA(String indicadorInformarContaRA) {
		this.indicadorInformarContaRA = indicadorInformarContaRA;
	}

	public String getIndicadorInformarPagamentoDP() {
		return indicadorInformarPagamentoDP;
	}

	public void setIndicadorInformarPagamentoDP(
			String indicadorInformarPagamentoDP) {
		this.indicadorInformarPagamentoDP = indicadorInformarPagamentoDP;
	}

	public String getIndicadorAlterarVencimentoAlternativo() {
		return indicadorAlterarVencimentoAlternativo;
	}

	public void setIndicadorAlterarVencimentoAlternativo(
			String indicadorAlterarVencimentoAlternativo) {
		this.indicadorAlterarVencimentoAlternativo = indicadorAlterarVencimentoAlternativo;
	}

	public String getIndicadorLojaVirtual() {
		return indicadorLojaVirtual;
	}

	public void setIndicadorLojaVirtual(String indicadorLojaVirtual) {
		this.indicadorLojaVirtual = indicadorLojaVirtual;
	}
	
}

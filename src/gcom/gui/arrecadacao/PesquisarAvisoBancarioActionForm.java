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
package gcom.gui.arrecadacao;

import org.apache.struts.action.ActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Tiago Moreno
 */
public class PesquisarAvisoBancarioActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idArrecadador;
	private String dataLancamentoInicio;
	private String dataLancamentoFim;
	private String tipoAviso;
	private String periodoArrecadacaoInicio;
	private String periodoArrecadacaoFim;
	private String dataPrevisaoCreditoDebitoInicio;
	private String dataPrevisaoCreditoDebitoFim;
	private String intervaloValorPrevistoInicio;
	private String intervaloValorPrevistoFim;
	private String dataRealizacaoCreditoDebitoInicio;
	private String dataRealizacaoCreditoDebitoFim;
	private String intervaloValorRealizadoInicio;
	private String intervaloValorRealizadoFim;
	private String arrecadadorNome;
	private String idConta;
	private String idBancoConta;
	private String idAgenciaConta;
	private String numeroConta;
	private String codigoBanco;
	private String codigoRemessa;
	private String identificacaoServico;
	private String numeroSequencial;
	private String idMovimento;

	public String getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(String idMovimento) {
		this.idMovimento = idMovimento;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getCodigoRemessa() {
		return codigoRemessa;
	}

	public void setCodigoRemessa(String codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}

	public String getIdentificacaoServico() {
		return identificacaoServico;
	}

	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}

	public String getNumeroSequencial() {
		return numeroSequencial;
	}

	public void setNumeroSequencial(String numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}

	public String getIdAgenciaConta() {
		return idAgenciaConta;
	}

	public void setIdAgenciaConta(String idAgenciaConta) {
		this.idAgenciaConta = idAgenciaConta;
	}

	public String getIdBancoConta() {
		return idBancoConta;
	}

	public void setIdBancoConta(String idBancoConta) {
		this.idBancoConta = idBancoConta;
	}

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getArrecadadorNome() {
		return arrecadadorNome;
	}

	public void setArrecadadorNome(String arrecadadorNome) {
		this.arrecadadorNome = arrecadadorNome;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getDataLancamentoFim() {
		return dataLancamentoFim;
	}

	public void setDataLancamentoFim(String dataLancamentoFim) {
		this.dataLancamentoFim = dataLancamentoFim;
	}

	public String getDataLancamentoInicio() {
		return dataLancamentoInicio;
	}

	public void setDataLancamentoInicio(String dataLancamentoInicio) {
		this.dataLancamentoInicio = dataLancamentoInicio;
	}

	public String getDataPrevisaoCreditoDebitoFim() {
		return dataPrevisaoCreditoDebitoFim;
	}

	public void setDataPrevisaoCreditoDebitoFim(String dataPrevisaoCreditoDebitoFim) {
		this.dataPrevisaoCreditoDebitoFim = dataPrevisaoCreditoDebitoFim;
	}

	public String getDataPrevisaoCreditoDebitoInicio() {
		return dataPrevisaoCreditoDebitoInicio;
	}

	public void setDataPrevisaoCreditoDebitoInicio(
			String dataPrevisaoCreditoDebitoInicio) {
		this.dataPrevisaoCreditoDebitoInicio = dataPrevisaoCreditoDebitoInicio;
	}

	public String getDataRealizacaoCreditoDebitoFim() {
		return dataRealizacaoCreditoDebitoFim;
	}

	public void setDataRealizacaoCreditoDebitoFim(
			String dataRealizacaoCreditoDebitoFim) {
		this.dataRealizacaoCreditoDebitoFim = dataRealizacaoCreditoDebitoFim;
	}

	public String getDataRealizacaoCreditoDebitoInicio() {
		return dataRealizacaoCreditoDebitoInicio;
	}

	public void setDataRealizacaoCreditoDebitoInicio(
			String dataRealizacaoCreditoDebitoInicio) {
		this.dataRealizacaoCreditoDebitoInicio = dataRealizacaoCreditoDebitoInicio;
	}

	public String getIntervaloValorPrevistoFim() {
		return intervaloValorPrevistoFim;
	}

	public void setIntervaloValorPrevistoFim(String intervaloValorPrevistoFim) {
		this.intervaloValorPrevistoFim = intervaloValorPrevistoFim;
	}

	public String getIntervaloValorPrevistoInicio() {
		return intervaloValorPrevistoInicio;
	}

	public void setIntervaloValorPrevistoInicio(String intervaloValorPrevistoInicio) {
		this.intervaloValorPrevistoInicio = intervaloValorPrevistoInicio;
	}

	public String getIntervaloValorRealizadoFim() {
		return intervaloValorRealizadoFim;
	}

	public void setIntervaloValorRealizadoFim(String intervaloValorRealizadoFim) {
		this.intervaloValorRealizadoFim = intervaloValorRealizadoFim;
	}

	public String getIntervaloValorRealizadoInicio() {
		return intervaloValorRealizadoInicio;
	}

	public void setIntervaloValorRealizadoInicio(
			String intervaloValorRealizadoInicio) {
		this.intervaloValorRealizadoInicio = intervaloValorRealizadoInicio;
	}

	public String getPeriodoArrecadacaoFim() {
		return periodoArrecadacaoFim;
	}

	public void setPeriodoArrecadacaoFim(String periodoArrecadacaoFim) {
		this.periodoArrecadacaoFim = periodoArrecadacaoFim;
	}

	public String getPeriodoArrecadacaoInicio() {
		return periodoArrecadacaoInicio;
	}

	public void setPeriodoArrecadacaoInicio(String periodoArrecadacaoInicio) {
		this.periodoArrecadacaoInicio = periodoArrecadacaoInicio;
	}

	public String getTipoAviso() {
		return tipoAviso;
	}

	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}
	
//    public void reset(ActionMapping actionMapping,
//            HttpServletRequest httpServletRequest) {
//			idArrecadador = null;
//			dataLancamentoInicio = null;
//			dataLancamentoFim = null;
//			tipoAviso = null;
//			periodoArrecadacaoInicio = null;
//			periodoArrecadacaoFim = null;
//			dataPrevisaoCreditoDebitoInicio = null;
//			dataPrevisaoCreditoDebitoFim = null;
//			intervaloValorPrevistoInicio = null;
//			intervaloValorPrevistoFim = null;
//			dataRealizacaoCreditoDebitoInicio = null;
//			dataRealizacaoCreditoDebitoFim = null;
//			intervaloValorRealizadoInicio = null;
//			intervaloValorRealizadoFim = null;
//			arrecadadorNome = null;
//			idConta = null;
//			idBancoConta = null;
//			idAgenciaConta = null;
//			numeroConta = null;
//			codigoBanco = null;
//			codigoRemessa = null;
//			identificacaoServico = null;
//			numeroSequencial = null;
//			idMovimento = null;
//    }

	
}

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


import org.apache.struts.validator.ValidatorForm;


/**
 * [UC0538] Filtrar RAs na Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 02/05/2007
 */

public class FiltrarRaDadosAgenciaReguladoraActionForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroRa;
	private String motivoReclamacao;
	private String motivoEncerramentoAtendimento;
	private String indicadorSituacaoAgencia;
	private String indicadorSituacaoRA;
	private String periodoReclamacaoInicio;
	private String periodoReclamacaoFim;
	private String periodoRetornoInicio;
	private String periodoRetornoFim;
	private String motivoRetornoAgencia;
	
	public String getIndicadorSituacaoAgencia() {
		return indicadorSituacaoAgencia;
	}
	public void setIndicadorSituacaoAgencia(String indicadorSituacaoAgencia) {
		this.indicadorSituacaoAgencia = indicadorSituacaoAgencia;
	}
	public String getIndicadorSituacaoRA() {
		return indicadorSituacaoRA;
	}
	public void setIndicadorSituacaoRA(String indicadorSituacaoRA) {
		this.indicadorSituacaoRA = indicadorSituacaoRA;
	}
	public String getMotivoEncerramentoAtendimento() {
		return motivoEncerramentoAtendimento;
	}
	public void setMotivoEncerramentoAtendimento(
			String motivoEncerramentoAtendimento) {
		this.motivoEncerramentoAtendimento = motivoEncerramentoAtendimento;
	}
	public String getMotivoReclamacao() {
		return motivoReclamacao;
	}
	public void setMotivoReclamacao(String motivoReclamacao) {
		this.motivoReclamacao = motivoReclamacao;
	}
	public String getMotivoRetornoAgencia() {
		return motivoRetornoAgencia;
	}
	public void setMotivoRetornoAgencia(String motivoRetornoAgencia) {
		this.motivoRetornoAgencia = motivoRetornoAgencia;
	}
	public String getNumeroRa() {
		return numeroRa;
	}
	public void setNumeroRa(String numeroRa) {
		this.numeroRa = numeroRa;
	}
	public String getPeriodoReclamacaoFim() {
		return periodoReclamacaoFim;
	}
	public void setPeriodoReclamacaoFim(String periodoReclamacaoFim) {
		this.periodoReclamacaoFim = periodoReclamacaoFim;
	}
	public String getPeriodoReclamacaoInicio() {
		return periodoReclamacaoInicio;
	}
	public void setPeriodoReclamacaoInicio(String periodoReclamacaoInicio) {
		this.periodoReclamacaoInicio = periodoReclamacaoInicio;
	}
	public String getPeriodoRetornoFim() {
		return periodoRetornoFim;
	}
	public void setPeriodoRetornoFim(String periodoRetornoFim) {
		this.periodoRetornoFim = periodoRetornoFim;
	}
	public String getPeriodoRetornoInicio() {
		return periodoRetornoInicio;
	}
	public void setPeriodoRetornoInicio(String periodoRetornoInicio) {
		this.periodoRetornoInicio = periodoRetornoInicio;
	}
   
	
	/*public RaDadosAgenciaReguladora setDadosAgenciaReguladora(RaDadosAgenciaReguladora raDadosAgenciaReguladora) {
		
		
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setId(new Integer(getNumeroRADados()));
		raDadosAgenciaReguladora.setRegistroAtendimento(registroAtendimento);
		
		AgenciaReguladoraMotReclamacao agenciaReguladoraMotReclamacao = new AgenciaReguladoraMotReclamacao();
		agenciaReguladoraMotReclamacao.setId(new Integer(getMotivoReclamacao()));
		raDadosAgenciaReguladora.setAgenciaReguladoraMotReclamacao(agenciaReguladoraMotReclamacao);
		
		if (getIdMotivoEncerramento() != null && !getIdMotivoEncerramento().equalsIgnoreCase("")  ){
			
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
			atendimentoMotivoEncerramento.setId(new Integer(getIdMotivoEncerramento()));
			raDadosAgenciaReguladora.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		}
		
		raDadosAgenciaReguladora.setAgenciaReguladora(getNumeroRegistroAgenciaReguladora());
		raDadosAgenciaReguladora.setDataPrevisaoOriginal(Util.converteStringParaDate(getDataPrevisaoOriginal()));
		
		raDadosAgenciaReguladora.setDataPrevisaoAtual(Util.converteStringParaDate(getDataPrevisaoAtual()));
		raDadosAgenciaReguladora.setDescricaoReclamacao(getReclamacao());
		raDadosAgenciaReguladora.setCodigoSituacaoArpe(new Short(getCodigoSituacao()));
		raDadosAgenciaReguladora.setCodigoSituacao(RegistroAtendimento.SITUACAO_PENDENTE);
		
	  return raDadosAgenciaReguladora;
	}*/
	
	
	
	}
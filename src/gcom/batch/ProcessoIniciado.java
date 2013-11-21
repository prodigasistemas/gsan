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
package gcom.batch;

import gcom.seguranca.acesso.usuario.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ProcessoIniciado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date dataHoraAgendamento;

	/** nullable persistent field */
	private Date dataHoraInicio;

	/** nullable persistent field */
	private Date dataHoraTermino;

	/** nullable persistent field */
	private Date dataHoraComando;

	/** persistent field */
	private gcom.batch.Processo processo;

	/** persistent field */
	private Usuario usuario;

	/** persistent field */
	private gcom.batch.ProcessoIniciado processoIniciadoPrecedente;

	/** persistent field */
	private gcom.batch.ProcessoSituacao processoSituacao;

	private Integer codigoGrupoProcesso;

	/** persistent field */
	private Set funcionalidadesIniciadas;

	/** full constructor */
	public ProcessoIniciado(Date dataHoraAgendamento, Date dataHoraInicio,
			Date dataHoraTermino, Date dataHoraComando,
			gcom.batch.Processo processo, Usuario usuario,
			gcom.batch.ProcessoIniciado processoIniciado,
			gcom.batch.ProcessoSituacao processoSituacao,
			Set funcionalidadesIniciadas) {
		this.dataHoraAgendamento = dataHoraAgendamento;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraTermino = dataHoraTermino;
		this.dataHoraComando = dataHoraComando;
		this.processo = processo;
		this.usuario = usuario;
		this.processoIniciadoPrecedente = processoIniciado;
		this.processoSituacao = processoSituacao;
		this.funcionalidadesIniciadas = funcionalidadesIniciadas;
	}

	/** default constructor */
	public ProcessoIniciado() {
	}

	/** minimal constructor */
	public ProcessoIniciado(gcom.batch.Processo processo, Usuario usuario,
			gcom.batch.ProcessoIniciado processoIniciado,
			gcom.batch.ProcessoSituacao processoSituacao,
			Set funcionalidadesIniciadas) {
		this.processo = processo;
		this.usuario = usuario;
		this.processoIniciadoPrecedente = processoIniciado;
		this.processoSituacao = processoSituacao;
		this.funcionalidadesIniciadas = funcionalidadesIniciadas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHoraAgendamento() {
		return this.dataHoraAgendamento;
	}

	public void setDataHoraAgendamento(Date dataHoraAgendamento) {
		this.dataHoraAgendamento = dataHoraAgendamento;
	}

	public Date getDataHoraInicio() {
		return this.dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraTermino() {
		return this.dataHoraTermino;
	}

	public void setDataHoraTermino(Date dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}

	public Date getDataHoraComando() {
		return this.dataHoraComando;
	}

	public void setDataHoraComando(Date dataHoraComando) {
		this.dataHoraComando = dataHoraComando;
	}

	public gcom.batch.Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(gcom.batch.Processo processo) {
		this.processo = processo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public gcom.batch.ProcessoIniciado getProcessoIniciadoPrecedente() {
		return this.processoIniciadoPrecedente;
	}

	public void setProcessoIniciadoPrecedente(
			gcom.batch.ProcessoIniciado processoIniciado) {
		this.processoIniciadoPrecedente = processoIniciado;
	}

	public gcom.batch.ProcessoSituacao getProcessoSituacao() {
		return this.processoSituacao;
	}

	public void setProcessoSituacao(gcom.batch.ProcessoSituacao processoSituacao) {
		this.processoSituacao = processoSituacao;
	}

	public Set getFuncionalidadesIniciadas() {
		return this.funcionalidadesIniciadas;
	}

	public void setFuncionalidadesIniciadas(Set funcionalidadesIniciadas) {
		this.funcionalidadesIniciadas = funcionalidadesIniciadas;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo codigoProcessoIniciado.
	 */
	public Integer getCodigoGrupoProcesso() {
		return codigoGrupoProcesso;
	}

	/**
	 * @param codigoProcessoIniciado
	 *            O codigoProcessoIniciado a ser setado.
	 */
	public void setCodigoGrupoProcesso(Integer codigoProcessoIniciado) {
		this.codigoGrupoProcesso = codigoProcessoIniciado;
	}

}

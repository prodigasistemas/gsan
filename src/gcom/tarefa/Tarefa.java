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
package gcom.tarefa;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Classe que representa uma tarefa
 * 
 * @author thiago
 * @date 24/01/2006
 */
public abstract class Tarefa implements Serializable, Job {

	protected static final long serialVersionUID = 1L;
		
	private Set parametroTarefa = new HashSet();

	private Usuario usuario;

	private int idFuncionalidadeIniciada;

	public Tarefa(Usuario usuario, int idFuncionalidadeIniciada) {
		this.usuario = usuario;
		this.idFuncionalidadeIniciada = idFuncionalidadeIniciada;

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set getParametroTarefa() {
		return parametroTarefa;

	}

	public void setParametroTarefa(Set parametroTarefa) {
		this.parametroTarefa = parametroTarefa;
	}

	/**
	 * Método que adiciona um Short
	 * 
	 * @param nome
	 * @param valor
	 */
	public void addParametro(String nome, Object valor) {
		parametroTarefa.add(new ParametroTarefa(nome, valor));
	}

	/**
	 * 
	 * @author Thiago Toscano
	 * @date 23/05/2006
	 * @param nome
	 * @return
	 */
	public Object getParametro(String nome) {
		Object retorno = null;
		Iterator it = parametroTarefa.iterator();
		while (it.hasNext()) {
			ParametroTarefa parametroTarefa = (ParametroTarefa) it.next();
			if (nome != null && nome.equals(parametroTarefa.getNome())) {
				retorno = parametroTarefa.getValor();
			}
		}
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public abstract Object executar() throws TarefaException;

	public abstract void execute(JobExecutionContext arg0)
			throws JobExecutionException;

	/**
	 * Faz o agendamento da tarefa batch no sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 03/08/2006
	 * 
	 */
	public abstract void agendarTarefaBatch();

	public int getIdFuncionalidadeIniciada() {
		return idFuncionalidadeIniciada;
	}

	public void setIdFuncionalidadeIniciada(int idFuncionalidadeIniciada) {
		this.idFuncionalidadeIniciada = idFuncionalidadeIniciada;
	}

}
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

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RelatorioGerado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private int numeroPaginas;

	/** nullable persistent field */
	private byte[] arquivoRelatorio;

	/** persistent field */
	private gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada;

	/** persistent field */
	private gcom.batch.Relatorio relatorio;

	/** full constructor */
	public RelatorioGerado(Date ultimaAlteracao, int numeroPaginas,
			byte[] arquivoRelatorio,
			gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada,
			gcom.batch.Relatorio relatorio) {
		this.ultimaAlteracao = ultimaAlteracao;
		this.numeroPaginas = numeroPaginas;
		this.arquivoRelatorio = arquivoRelatorio;
		this.funcionalidadeIniciada = funcionalidadeIniciada;
		this.relatorio = relatorio;
	}

	/** default constructor */
	public RelatorioGerado() {
	}

	/** minimal constructor */
	public RelatorioGerado(int numeroPaginas,
			gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada,
			gcom.batch.Relatorio relatorio) {
		this.numeroPaginas = numeroPaginas;
		this.funcionalidadeIniciada = funcionalidadeIniciada;
		this.relatorio = relatorio;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public int getNumeroPaginas() {
		return this.numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public byte[] getArquivoRelatorio() {
		return this.arquivoRelatorio;
	}

	public void setArquivoRelatorio(byte[] arquivoRelatorio) {
		this.arquivoRelatorio = arquivoRelatorio;
	}

	public gcom.batch.FuncionalidadeIniciada getFuncionalidadeIniciada() {
		return this.funcionalidadeIniciada;
	}

	public void setFuncionalidadeIniciada(
			gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada) {
		this.funcionalidadeIniciada = funcionalidadeIniciada;
	}

	public gcom.batch.Relatorio getRelatorio() {
		return this.relatorio;
	}

	public void setRelatorio(gcom.batch.Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}

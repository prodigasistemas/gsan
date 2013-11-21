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
package gcom.gerencial.cobranca;

/** 
 *
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoCobrancaSituacaoEspecialConsultaHelper {

	private Integer idGerenciaRegional;
	private String gerenciaRegional;
	private Integer idLocalidade;
	private String localidadeDescricao;
	private Integer idSituacao;
	private String situacaoDescricao;
	private Integer idMotivo;
	private String motivoDescricao;
	private Integer periodoInicio;
	private Integer periodoFinal;
	private Integer qtParalisada;

	
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}
	public String getMotivoDescricao() {
		return motivoDescricao;
	}
	public void setMotivoDescricao(String motivoDescricao) {
		this.motivoDescricao = motivoDescricao;
	}
	public Integer getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(Integer periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public Integer getPeriodoInicio() {
		return periodoInicio;
	}
	public void setPeriodoInicio(Integer periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	public Integer getQtParalisada() {
		return qtParalisada;
	}
	public void setQtParalisada(Integer qtParalisada) {
		this.qtParalisada = qtParalisada;
	}
	public String getSituacaoDescricao() {
		return situacaoDescricao;
	}
	public void setSituacaoDescricao(String situacaoDescricao) {
		this.situacaoDescricao = situacaoDescricao;
	}
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public Integer getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}
	public Integer getIdSituacao() {
		return idSituacao;
	}
	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}

	public ResumoCobrancaSituacaoEspecialConsultaHelper(Integer idGerenciaRegional, String gerenciaRegional, Integer idLocalidade, String localidadeDescricao, Integer idSituacao, String situacaoDescricao, Integer idMotivo, String motivoDescricao, Integer periodoInicio, Integer periodoFinal, Integer qtParalisada) {
		super();
		// TODO Auto-generated constructor stub
		this.idGerenciaRegional = idGerenciaRegional;
		this.gerenciaRegional = gerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.localidadeDescricao = localidadeDescricao;
		this.idSituacao = idSituacao;
		this.situacaoDescricao = situacaoDescricao;
		this.idMotivo = idMotivo;
		this.motivoDescricao = motivoDescricao;
		this.periodoInicio = periodoInicio;
		this.periodoFinal = periodoFinal;
		this.qtParalisada = qtParalisada;
	}
	
}
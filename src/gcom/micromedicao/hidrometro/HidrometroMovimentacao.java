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
package gcom.micromedicao.hidrometro;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class HidrometroMovimentacao implements Serializable {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date data; 

	/** nullable persistent field */
	private Date hora;

	/** nullable persistent field */
	private String parecer;

	/** nullable persistent field */
	private String quantidade;

	/** nullable persistent field */
	private String fixo;

	/** nullable persistent field */
	private String faixaInicial;

	/** nullable persistent field */
	private String faixaFinal;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemDestino;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemOrigem;

	/** persistent field */
	private Set hidrometroMovimentados;

	/** persistent field */
	private Usuario usuario;
	
	/** persistent field */
	private String dataMovimentacaoInicial;
	
	/** persistent field */
	private String dataMovimentacaoFinal;
	
	/** persistent field */
	private String horaMovimentacaoInicial;

	/** persistent field */
	private String horaMovimentacaoFinal;
	
	
	/** full constructor */
	public HidrometroMovimentacao(
			Date data,
			Date hora,
			String parecer,
			Date ultimaAlteracao,
			gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemDestino,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemOrigem,
			Usuario usuario) {
		this.data = data;
		this.hora = hora;
		this.parecer = parecer;
		this.ultimaAlteracao = ultimaAlteracao;
		this.hidrometroMotivoMovimentacao = hidrometroMotivoMovimentacao;
		this.hidrometroLocalArmazenagemDestino = hidrometroLocalArmazenagemDestino;
		this.hidrometroLocalArmazenagemOrigem = hidrometroLocalArmazenagemOrigem;
		this.usuario = usuario;
	}

	/** default constructor */
	public HidrometroMovimentacao() {
	}

	/** minimal constructor */
	public HidrometroMovimentacao(
			gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemDestino,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemOrigem,
			Usuario usuario) {

		this.hidrometroMotivoMovimentacao = hidrometroMotivoMovimentacao;
		this.hidrometroLocalArmazenagemDestino = hidrometroLocalArmazenagemDestino;
		this.hidrometroLocalArmazenagemOrigem = hidrometroLocalArmazenagemOrigem;
		this.usuario = usuario;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getParecer() {
		return this.parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao getHidrometroMotivoMovimentacao() {
		return this.hidrometroMotivoMovimentacao;
	}

	public Set getHidrometroMovimentados() {
		return hidrometroMovimentados;
	}

	public void setHidrometroMovimentados(Set hidrometroMovimentados) {
		this.hidrometroMovimentados = hidrometroMovimentados;
	}

	public void setHidrometroMotivoMovimentacao(
			gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao) {
		this.hidrometroMotivoMovimentacao = hidrometroMotivoMovimentacao;
	}

	public gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem getHidrometroLocalArmazenagemDestino() {
		return this.hidrometroLocalArmazenagemDestino;
	}

	public void setHidrometroLocalArmazenagemDestino(
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemDestino) {
		this.hidrometroLocalArmazenagemDestino = hidrometroLocalArmazenagemDestino;
	}

	public gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem getHidrometroLocalArmazenagemOrigem() {
		return this.hidrometroLocalArmazenagemOrigem;
	}

	public void setHidrometroLocalArmazenagemOrigem(
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemOrigem) {
		this.hidrometroLocalArmazenagemOrigem = hidrometroLocalArmazenagemOrigem;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getFaixaFinal() {
		return faixaFinal;
	}

	public void setFaixaFinal(String faixaFinal) {
		this.faixaFinal = faixaFinal;
	}

	public String getFaixaInicial() {
		return faixaInicial;
	}

	public void setFaixaInicial(String faixaInicial) {
		this.faixaInicial = faixaInicial;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof HidrometroMovimentacao))
			return false;
		HidrometroMovimentacao castOther = (HidrometroMovimentacao) other;
		return new EqualsBuilder()
			.append(this.getId(),castOther.getId())
			.append(this.getData(),castOther.getData())
			.append(this.getHora(),castOther.getHora())
			.append(this.getParecer(),castOther.getParecer())
			.append(this.getHidrometroMotivoMovimentacao().getId(),castOther.getHidrometroMotivoMovimentacao().getId())
			.append(this.getHidrometroLocalArmazenagemDestino().getId(),castOther.getHidrometroLocalArmazenagemDestino().getId())
			.append(this.getHidrometroLocalArmazenagemOrigem().getId(),castOther.getHidrometroLocalArmazenagemOrigem().getId())
			.isEquals();
	}

	public String getDataMovimentacaoFinal() {
		return dataMovimentacaoFinal;
	}

	public void setDataMovimentacaoFinal(String dataMovimentacaoFinal) {
		this.dataMovimentacaoFinal = dataMovimentacaoFinal;
	}

	public String getDataMovimentacaoInicial() {
		return dataMovimentacaoInicial;
	}

	public void setDataMovimentacaoInicial(String dataMovimentacaoInicial) {
		this.dataMovimentacaoInicial = dataMovimentacaoInicial;
	}

	public String getHoraMovimentacaoFinal() {
		return horaMovimentacaoFinal;
	}

	public void setHoraMovimentacaoFinal(String horaMovimentacaoFinal) {
		this.horaMovimentacaoFinal = horaMovimentacaoFinal;
	}

	public String getHoraMovimentacaoInicial() {
		return horaMovimentacaoInicial;
	}

	public void setHoraMovimentacaoInicial(String horaMovimentacaoInicial) {
		this.horaMovimentacaoInicial = horaMovimentacaoInicial;
	}


}

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
package gcom.financeiro;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LancamentoResumo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** persistent field */
	private int anoMesReferencia;

	/** nullable persistent field */
	private BigDecimal valorResumo;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private SetorComercial setorComercial;

	/** persistent field */
	private gcom.financeiro.LancamentoResumoValorTipo lancamentoResumoValorTipo;

	/** persistent field */
	private Localidade localidade;

	/** persistent field */
	private GerenciaRegional gerenciaRegional;

	/** persistent field */
	private Categoria categoria;

	/** full constructor */
	public LancamentoResumo(
			Integer id,
			int anoMesReferencia,
			BigDecimal valorResumo,
			Date ultimaAlteracao,
			SetorComercial setorComercial,
			gcom.financeiro.LancamentoResumoValorTipo lancamentoResumoValorTipo,
			Localidade localidade, GerenciaRegional gerenciaRegional,
			Categoria categoria) {
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.valorResumo = valorResumo;
		this.ultimaAlteracao = ultimaAlteracao;
		this.setorComercial = setorComercial;
		this.lancamentoResumoValorTipo = lancamentoResumoValorTipo;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.categoria = categoria;
	}

	/** default constructor */
	public LancamentoResumo() {
	}

	/** minimal constructor */
	public LancamentoResumo(
			Integer id,
			int anoMesReferencia,
			SetorComercial setorComercial,
			gcom.financeiro.LancamentoResumoValorTipo lancamentoResumoValorTipo,
			Localidade localidade, GerenciaRegional gerenciaRegional,
			Categoria categoria) {
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.setorComercial = setorComercial;
		this.lancamentoResumoValorTipo = lancamentoResumoValorTipo;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.categoria = categoria;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAnoMesReferencia() {
		return this.anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public BigDecimal getValorResumo() {
		return this.valorResumo;
	}

	public void setValorResumo(BigDecimal valorResumo) {
		this.valorResumo = valorResumo;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public SetorComercial getSetorComercial() {
		return this.setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public gcom.financeiro.LancamentoResumoValorTipo getLancamentoResumoValorTipo() {
		return this.lancamentoResumoValorTipo;
	}

	public void setLancamentoResumoValorTipo(
			gcom.financeiro.LancamentoResumoValorTipo lancamentoResumoValorTipo) {
		this.lancamentoResumoValorTipo = lancamentoResumoValorTipo;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public GerenciaRegional getGerenciaRegional() {
		return this.gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}

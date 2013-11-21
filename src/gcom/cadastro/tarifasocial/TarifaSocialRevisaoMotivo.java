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
package gcom.cadastro.tarifasocial;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TarifaSocialRevisaoMotivo extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Integer indicadorPermiteRecadastramento;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set tarifaSocialDadoEconomias;
    
    /**
	 * Indicador de permissão de recadastramento 
	 */
    public final static Integer INDICADOR_PERMITE_RECADASTRAMENTO_SIM = new Integer("1");
	public final static Integer INDICADOR_PERMITE_RECADASTRAMENTO_NAO = new Integer("2");

    public String toString() {
        return new ToStringBuilder(this)
            .append("rtsmId", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorPermiteRecadastramento.
	 */
	public Integer getIndicadorPermiteRecadastramento() {
		return indicadorPermiteRecadastramento;
	}

	/**
	 * @param indicadorPermiteRecadastramento O indicadorPermiteRecadastramento a ser setado.
	 */
	public void setIndicadorPermiteRecadastramento(
			Integer indicadorPermiteRecadastramento) {
		this.indicadorPermiteRecadastramento = indicadorPermiteRecadastramento;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public Short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo tarifaSocialDadoEconomias.
	 */
	public Set getTarifaSocialDadoEconomias() {
		return tarifaSocialDadoEconomias;
	}

	/**
	 * @param tarifaSocialDadoEconomias O tarifaSocialDadoEconomias a ser setado.
	 */
	public void setTarifaSocialDadoEconomias(Set tarifaSocialDadoEconomias) {
		this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return this.descricao;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] key = {FiltroTarifaSocialRevisaoMotivo.ID};
		return key;
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
	}
    
	@Override
	public Filtro retornaFiltro() {
		FiltroTarifaSocialRevisaoMotivo filtro = new FiltroTarifaSocialRevisaoMotivo();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialRevisaoMotivo.ID, this.getId()));
		return filtro;
	}	
}

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
package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class PocoTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /**
     * identifier field
     */
    private Integer id;

    /**
     * nullable persistent field
     */
    private String descricao;

    /**
     * nullable persistent field
     */
    private Short indicadorUso;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;
    
    /**
	 * @since 19/09/2007
	 */
	private String descricaoComId;
	
	private Short indicadorHidrometroTipoPoco;

    //--CONSTANTES SISTEMA
    /**
     * Description of the Field
     */
    public final static Integer SEM_POCO = new Integer(0);

    /**
     * full constructor
     * 
     * @param descricao
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     * @param ultimaAlteracao
     *            Descrição do parâmetro
     */
    public PocoTipo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * default constructor
     */
    public PocoTipo() {
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de descricao
     * 
     * @return O valor de descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Seta o valor de descricao
     * 
     * @param descricao
     *            O novo valor de descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     */
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    /**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroPocoTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroPocoTipo.ID,
				this.getId()));		
		return filtro;
	}
    
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 19/09/2007
	 *
	 * @return
	 */
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

	public Short getIndicadorHidrometroTipoPoco() {
		return indicadorHidrometroTipoPoco;
	}

	public void setIndicadorHidrometroTipoPoco(Short indicadorHidrometroTipoPoco) {
		this.indicadorHidrometroTipoPoco = indicadorHidrometroTipoPoco;
	}
	
	
}

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
* Thiago Silva Toscano de Brito
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

package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorRegistroTipo extends ObjetoTransacao implements Serializable  {
	
	public static final String TIPO_HEADER = "H";
	public static final String TIPO_TRAILLER = "T";
	public static final String TIPO_DETALHE_CONSUMIDOR = "D";
	public static final String TIPO_DETALHE_SPC = "D";
	public static final String TIPO_DETALHE = "D";

	public static final Integer ID_SPC_HEADER = new Integer(1); 
	public static final Integer ID_SPC_TRAILLER = new Integer(2);
	public static final Integer ID_SPC_DETALHE_CONSUMIDOR = new Integer(3);
	public static final Integer ID_SPC_DETALHE_SPC = new Integer(4);
	public static final Integer ID_SERASA_HEADER = new Integer(5);
	public static final Integer ID_SERASA_TRAILLER = new Integer(6);
	public static final Integer ID_SERASA_DETALHE = new Integer(7);

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoRegistroTipo;

    /** nullable persistent field */
    private String codigoRegistro;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.Negativador negativador;

    /** persistent field */
    private Set negativadorMovimentoReg;

    /** full constructor */
    public NegativadorRegistroTipo(Integer id, String descricaoRegistroTipo, String codigoRegistro, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, Set negativadorMovimentoReg) {
        this.id = id;
        this.descricaoRegistroTipo = descricaoRegistroTipo;
        this.codigoRegistro = codigoRegistro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    /** default constructor */
    public NegativadorRegistroTipo() {
    }

    /** minimal constructor */
    public NegativadorRegistroTipo(Integer id, gcom.cobranca.Negativador negativador, Set negativadorMovimentoReg) {
        this.id = id;
        this.negativador = negativador;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoRegistroTipo() {
        return this.descricaoRegistroTipo;
    }

    public void setDescricaoRegistroTipo(String descricaoRegistroTipo) {
        this.descricaoRegistroTipo = descricaoRegistroTipo;
    }

    public String getCodigoRegistro() {
        return this.codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.Negativador getNegativador() {
        return this.negativador;
    }

    public void setNegativador(gcom.cobranca.Negativador negativador) {
        this.negativador = negativador;
    }

    public Set getNegativadorMovimentoReg() {
        return this.negativadorMovimentoReg;
    }

    public void setNegativadorMovimentoReg(Set negativadorMovimentoReg) {
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public Filtro retornaFiltro() {
		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo();
		filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.ID,this.getId()));		
		return filtroNegativadorRegistroTipo;
	}


	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
}

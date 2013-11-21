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
package gcom.cadastro.geografico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class MunicipioFeriado extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;
    
    /** persistent field */
    private gcom.cadastro.geografico.Municipio municipio;

    /** persistent field */
    private Date dataFeriado;

    /** nullable persistent field */
    private String descricaoFeriado;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public MunicipioFeriado(Date dataFeriado, String descricaoFeriado,
            Date ultimaAlteracao, gcom.cadastro.geografico.Municipio municipio) {
        this.dataFeriado = dataFeriado;
        this.descricaoFeriado = descricaoFeriado;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
    }

    /** default constructor */
    public MunicipioFeriado() {
    }

    /** minimal constructor */
    public MunicipioFeriado(Date dataFeriado,
            gcom.cadastro.geografico.Municipio municipio) {
        this.dataFeriado = dataFeriado;
        this.municipio = municipio;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataFeriado() {
        return this.dataFeriado;
    }

    public void setDataFeriado(Date dataFeriado) {
        this.dataFeriado = dataFeriado;
    }

    public String getDescricaoFeriado() {
        return this.descricaoFeriado;
    }

    public void setDescricaoFeriado(String descricaoFeriado) {
        this.descricaoFeriado = descricaoFeriado;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cadastro.geografico.Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(gcom.cadastro.geografico.Municipio municipio) {
        this.municipio = municipio;
    }
    
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    
    
    public Filtro retornaFiltro(){
		FiltroMunicipioFeriado filtroMunicipioFeriado = new FiltroMunicipioFeriado();

		filtroMunicipioFeriado.adicionarParametro(new ParametroSimples(FiltroMunicipioFeriado.ID,this.getId()));
		filtroMunicipioFeriado.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		return filtroMunicipioFeriado;
	}

    public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

}

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
import gcom.operacional.DistritoOperacional;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class BairroArea extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String nome;
    
    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Short codigoBairroArea;
    
    /** persistent field */
    private Short codigoMunicipio;

    /** persistent field */
    private gcom.cadastro.geografico.Bairro bairro;

    /** persistent field */
    private DistritoOperacional distritoOperacional;

    /** full constructor */
    public BairroArea(String nome, Date ultimaAlteracao, Short codigoBairroArea, Short codigoMunicipio, gcom.cadastro.geografico.Bairro bairro,  DistritoOperacional distritoOperacional) {
        this.nome = nome;
        this.ultimaAlteracao = ultimaAlteracao;
        this.codigoBairroArea = codigoBairroArea;
        this.codigoMunicipio = codigoMunicipio;
        this.bairro = bairro;
        this.distritoOperacional = distritoOperacional;
    }

    /** default constructor */
    public BairroArea() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public gcom.cadastro.geografico.Bairro getBairro() {
        return this.bairro;
    }

    public void setBairro(gcom.cadastro.geografico.Bairro bairro) {
        this.bairro = bairro;
    }

    public DistritoOperacional getDistritoOperacional() {
        return this.distritoOperacional;
    }

    public void setDistritoOperacional(DistritoOperacional distritoOperacional) {
        this.distritoOperacional = distritoOperacional;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/**
	 * @return Retorna o campo codigoBairroArea.
	 */
	public Short getCodigoBairroArea() {
		return codigoBairroArea;
	}

	/**
	 * @param codigoBairroArea O codigoBairroArea a ser setado.
	 */
	public void setCodigoBairroArea(Short codigoBairroArea) {
		this.codigoBairroArea = codigoBairroArea;
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

	/**
	 * @return Retorna o campo codigoMunicipio.
	 */
	public Short getCodigoMunicipio() {
		return codigoMunicipio;
	}

	/**
	 * @param codigoMunicipio O codigoMunicipio a ser setado.
	 */
	public void setCodigoMunicipio(Short codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	
	public Filtro retornaFiltro(){
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		
		filtroBairroArea. adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroBairroArea. adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroBairroArea. adicionarParametro(
				new ParametroSimples(FiltroBairroArea.ID, this.getId()));
		return filtroBairroArea; 
	}

}

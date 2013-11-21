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
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ImovelContaEnvio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set imovels;
    
    /** persistent fiel */
    private Short indicadorClienteResponsavel;
    
    /**
     * Description of the Field
     */
    public final static Integer ENVIAR_CLIENTE_RESPONSAVEL = new Integer("1");

    /**
     * Description of the Field
     */
    public final static Integer ENVIAR_IMOVEL = new Integer("2");

    /**
     * Description of the Field
     */
    public final static Integer NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL = new Integer("3");
    
    /**
     * Description of the Field
     */
    public final static Integer ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO = new Integer("9");
    
    /**
     * Description of the Field
     */
    public final static Integer ENVIAR_PARA_EMAIL = new Integer("4");
    
    /**
     * Description of the Field
     */
    public final static Integer ENVIAR_PARA_IMOVEL_E_PARA_EMAIL = new Integer("5");
    
    public final static Integer ENVIAR_CONTA_BRAILLE = new Integer("6");
    
    public final static Integer ENVIAR_CONTA_BRAILLE_RESPONSAVEL = new Integer("7");
    
    /** full constructor */
    public ImovelContaEnvio() {
    }
    
    
    /** full constructor */
    public ImovelContaEnvio(Integer icteId, String icteDscontaenvio, short icteIcuso, Date icteTmultimaalteracao, Set imovels) {
        this.id = icteId;
        this.descricao = icteDscontaenvio;
        this.indicadorUso = icteIcuso;
        this.ultimaAlteracao = icteTmultimaalteracao;
        this.imovels = imovels;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icteId", getId().toString())
            .toString();
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set getImovels() {
		return imovels;
	}

	public void setImovels(Set imovels) {
		this.imovels = imovels;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	@Override
	public Filtro retornaFiltro() {
		FiltroImovelContaEnvio filtro = new FiltroImovelContaEnvio();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelContaEnvio.ID,
				this.getId()));
		return filtro;
	}


	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}


	public Short getIndicadorClienteResponsavel() {
		return indicadorClienteResponsavel;
	}


	public void setIndicadorClienteResponsavel(Short indicadorClienteResponsavel) {
		this.indicadorClienteResponsavel = indicadorClienteResponsavel;
	}
}

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
package gcom.cobranca;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class UnidadeOrganizacionalTestemunha extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public Filtro retornaFiltro(){
		FiltroUnidadeOrganizacionalTestemunha filtroUnidadeNegocioTestemunha = new FiltroUnidadeOrganizacionalTestemunha();

		filtroUnidadeNegocioTestemunha.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacionalTestemunha.ID,
				this.getId()));

		
		return filtroUnidadeNegocioTestemunha;
	}
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataInicioRelacao;

    /** nullable persistent field */
    private Date dataFimRelacao;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private UnidadeOrganizacional unidadeOrganizacional;
    
    /** nullable persistent field */
    private Usuario usuario;
    
    /** default constructor */
    public UnidadeOrganizacionalTestemunha() {
    }

    public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	/**
	 * @return Retorna o campo dataFimRelacao.
	 */
	public Date getDataFimRelacao() {
		return dataFimRelacao;
	}

	/**
	 * @param dataFimRelacao O dataFimRelacao a ser setado.
	 */
	public void setDataFimRelacao(Date dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}

	/**
	 * @return Retorna o campo dataInicioRelacao.
	 */
	public Date getDataInicioRelacao() {
		return dataInicioRelacao;
	}

	/**
	 * @param dataInicioRelacao O dataInicioRelacao a ser setado.
	 */
	public void setDataInicioRelacao(Date dataInicioRelacao) {
		this.dataInicioRelacao = dataInicioRelacao;
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
	 * @return Retorna o campo unidadeNegocio.
	 */
	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/** full constructor */
	public UnidadeOrganizacionalTestemunha(Integer id, Date dataInicioRelacao,
			Date dataFimRelacao, Date ultimaAlteracao,
			UnidadeOrganizacional unidadeOrganizacional, Usuario usuario) {
		this.id = id;
		this.dataInicioRelacao = dataInicioRelacao;
		this.dataFimRelacao = dataFimRelacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.usuario = usuario;
	}

 
}

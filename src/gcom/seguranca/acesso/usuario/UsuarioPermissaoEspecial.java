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
package gcom.seguranca.acesso.usuario;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class UsuarioPermissaoEspecial extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    
	private static final int ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS=100;
	/** identifier field */
	@ControleAlteracao(value=FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_COMP_ID,funcionalidade={ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS})
	private gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK comp_id;

    /** nullable persistent field */
    @ControleAlteracao(value=FiltroUsuarioPemissaoEspecial.USUARIO_ID ,funcionalidade={ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS})
    private gcom.seguranca.acesso.usuario.Usuario usuario;

    /** nullable persistent field */
    @ControleAlteracao(value=FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_COMP_ID,funcionalidade={ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS})
    private gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS)
    private Date ultimaAlteracao;
    
    public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** full constructor */
    public UsuarioPermissaoEspecial(gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK comp_id, gcom.seguranca.acesso.usuario.Usuario usuario, gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial) {
        this.comp_id = comp_id;
        this.usuario = usuario;
        this.permissaoEspecial = permissaoEspecial;
    }

    /** default constructor */
    public UsuarioPermissaoEspecial() {
    }

    /** minimal constructor */
    public UsuarioPermissaoEspecial(gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.usuario.Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(gcom.seguranca.acesso.usuario.Usuario usuario) {
        this.usuario = usuario;
    }

    public gcom.seguranca.acesso.PermissaoEspecial getPermissaoEspecial() {
        return this.permissaoEspecial;
    }

    public void setPermissaoEspecial(gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial) {
        this.permissaoEspecial = permissaoEspecial;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioPermissaoEspecial) ) return false;
        UsuarioPermissaoEspecial castOther = (UsuarioPermissaoEspecial) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();

		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID,
				this.getPermissaoEspecial().getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID,
				this.getUsuario().getId()));
		
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial");
		
		return filtroUsuarioPemissaoEspecial;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.USUARIO);
		return filtro;
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"permissaoEspecial.descricao",
				"usuario.nomeUsuario",
				"usuario.id"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Per. Esp. Descricao",
				"Nome do Usuario",
				"Id Usuario"};
		return labels;		
	}
	
   
}

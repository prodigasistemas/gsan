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
public class UsuarioGrupoRestricao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    
	private static final int ATRIBUTOS_CONTROLE_PERMISSAO_ESPECIAL = 100;
	
	/** identifier field */
    private gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK comp_id;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CONTROLE_PERMISSAO_ESPECIAL)
    private gcom.seguranca.acesso.GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao;

    /** nullable persistent field */
    private gcom.seguranca.acesso.usuario.UsuarioGrupo usuarioGrupo;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
		
		filtroUsuarioGrupoRestricao. adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroUsuarioGrupoRestricao. adicionarCaminhoParaCarregamentoEntidade("grupoFuncionalidadeOperacao");
		filtroUsuarioGrupoRestricao. adicionarCaminhoParaCarregamentoEntidade("usuarioGrupo");
		filtroUsuarioGrupoRestricao. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupoRestricao.USUARIO_ID, comp_id.getUsuarioId()));
		filtroUsuarioGrupoRestricao. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupoRestricao.FUNCIONALIDADE_ID, comp_id.getFuncionalidadeId()));
		filtroUsuarioGrupoRestricao. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupoRestricao.GRUPO_ID, comp_id.getGrupoId()));
		filtroUsuarioGrupoRestricao. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupoRestricao.OPERACAO_ID, comp_id.getOperacaoId()));
		return filtroUsuarioGrupoRestricao; 
	}
    
    public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** full constructor */
    public UsuarioGrupoRestricao(gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK comp_id, gcom.seguranca.acesso.GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao, gcom.seguranca.acesso.usuario.UsuarioGrupo usuarioGrupo) {
        this.comp_id = comp_id;
        this.grupoFuncionalidadeOperacao = grupoFuncionalidadeOperacao;
        this.usuarioGrupo = usuarioGrupo;
    }

    /** default constructor */
    public UsuarioGrupoRestricao() {
    }

    /** minimal constructor */
    public UsuarioGrupoRestricao(gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.GrupoFuncionalidadeOperacao getGrupoFuncionalidadeOperacao() {
        return this.grupoFuncionalidadeOperacao;
    }

    public void setGrupoFuncionalidadeOperacao(gcom.seguranca.acesso.GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao) {
        this.grupoFuncionalidadeOperacao = grupoFuncionalidadeOperacao;
    }

    public gcom.seguranca.acesso.usuario.UsuarioGrupo getUsuarioGrupo() {
        return this.usuarioGrupo;
    }

    public void setUsuarioGrupo(gcom.seguranca.acesso.usuario.UsuarioGrupo usuarioGrupo) {
        this.usuarioGrupo = usuarioGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioGrupoRestricao) ) return false;
        UsuarioGrupoRestricao castOther = (UsuarioGrupoRestricao) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
    public Filtro retornaFiltroRegistroOperacao() {
    	FiltroUsuarioPemissaoEspecial filtro = new FiltroUsuarioPemissaoEspecial();

//		filtro.adicionarParametro(
//			new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID,this.getComp_id()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.USUARIO_ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.GRUPO_FUNCIONALIDADE_OPERACAO);
		return filtro;
	}
	
//	@Override
//	public String getDescricaoParaRegistroTransacao() {
//		return getGrupoFuncionalidadeOperacao().getComp_id().toString();
//	}
	
//	@Override
//	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
//		String []labels = {"usuario.id"};
//		return labels;		
//	}
//	
//	@Override
//	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
//		String []labels = {"Id usuario"};
//		return labels;		
//	}
	@Override
	public void initializeLazy() {
	}
}

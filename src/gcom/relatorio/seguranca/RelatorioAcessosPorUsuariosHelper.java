/*
 * Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
 * Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.relatorio.seguranca;

import java.io.Serializable;

/**
 * [UC1040] Gerar Relatório de Acessos por Usuário
 * 
 * @author Hugo Leonardo
 *
 * @date 13/07/2010
 */
public class RelatorioAcessosPorUsuariosHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String permissaoEspecial;
	private String usuario;
	private String funcionalidade;
	private String modulo;
	private String operacao;
	private String situacaoUsuario;
	private String usuarioTipo;
	private String grupoAcesso;
	private String unidadeOrganizacional;
	
	
	public String getFuncionalidade() {
		return funcionalidade;
	}
	
	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	
	public String getModulo() {
		return modulo;
	}
	
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	
	public String getOperacao() {
		return operacao;
	}
	
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public String getPermissaoEspecial() {
		return permissaoEspecial;
	}
	
	public void setPermissaoEspecial(String permissaoEspecial) {
		this.permissaoEspecial = permissaoEspecial;
	}
	
	public String getSituacaoUsuario() {
		return situacaoUsuario;
	}
	
	public void setSituacaoUsuario(String situacaoUsuario) {
		this.situacaoUsuario = situacaoUsuario;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getGrupoAcesso() {
		return grupoAcesso;
	}

	public void setGrupoAcesso(String grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}

	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(String unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(String usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	
}
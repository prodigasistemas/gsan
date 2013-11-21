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

//import gcom.seguranca.transacao.TransacaoEfetuada;
import gcom.cadastro.empresa.Empresa;
import gcom.seguranca.acesso.OperacaoEfetuada;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class UsuarioAlteracao implements Serializable {

	private static final long serialVersionUID = 1L;

	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private gcom.seguranca.acesso.OperacaoEfetuada operacaoEfetuada;

    /** persistent field */
    private gcom.seguranca.acesso.usuario.UsuarioAcao usuarioAcao;
    
    private Empresa empresa;

    private String ipAlteracao;
    
    public UsuarioAlteracao(Integer id, Date ultimaAlteracao, Usuario usuario, OperacaoEfetuada operacaoEfetuada, UsuarioAcao usuarioAcao, Empresa empresa) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.usuario = usuario;
        this.operacaoEfetuada = operacaoEfetuada;
        this.usuarioAcao = usuarioAcao;
        this.empresa = empresa;
    }

    /** default constructor */
    public UsuarioAlteracao() {
    }

    /** minimal constructor */
    public UsuarioAlteracao(Integer id, Usuario usuario, OperacaoEfetuada operacaoEfetuada, UsuarioAcao usuarioAcao) {
        this.id = id;
        this.usuario = usuario;
        this.operacaoEfetuada = operacaoEfetuada;
        this.usuarioAcao = usuarioAcao;
    }

    /**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the operacaoEfetuada.
	 */
	public OperacaoEfetuada getOperacaoEfetuada() {
		return operacaoEfetuada;
	}

	/**
	 * @param operacaoEfetuada The operacaoEfetuada to set.
	 */
	public void setOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada) {
		this.operacaoEfetuada = operacaoEfetuada;
	}

	/**
	 * @return Returns the ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao The ultimaAlteracao to set.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Returns the usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario The usuario to set.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return Returns the usuarioAcao.
	 */
	public UsuarioAcao getUsuarioAcao() {
		return usuarioAcao;
	}

	/**
	 * @param usuarioAcao The usuarioAcao to set.
	 */
	public void setUsuarioAcao(UsuarioAcao usuarioAcao) {
		this.usuarioAcao = usuarioAcao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getIpAlteracao() {
		return ipAlteracao;
	}

	public void setIpAlteracao(String ipAlteracao) {
		this.ipAlteracao = ipAlteracao;
	}

	
	
	
}
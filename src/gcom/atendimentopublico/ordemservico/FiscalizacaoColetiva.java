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
package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoColetiva implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataGeracao;

    /** nullable persistent field */
    private String comentario;

    /** nullable persistent field */
    private Date dataEncerramento;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private String nomeFiscalizacaoColetiva;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalAbertura;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalEncerramento;
    
    /** persistent field */
    private Usuario UsuarioDaAbertura;
    
    /** persistent field */
    private Usuario UsuarioDeEncerramento;

    /** full constructor */
    public FiscalizacaoColetiva(Date dataGeracao, String comentario, Date dataEncerramento, Date ultimaAlteracao, String nomeFiscalizacaoColetiva, 
    		UnidadeOrganizacional unidadeOrganizacionalAbertura, UnidadeOrganizacional unidadeOrganizacionalEncerramento, Usuario UsuarioDaAbertura, 
    		Usuario UsuarioDeEncerramento) {
        this.dataGeracao = dataGeracao;
        this.comentario = comentario;
        this.dataEncerramento = dataEncerramento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.nomeFiscalizacaoColetiva = nomeFiscalizacaoColetiva;
        this.unidadeOrganizacionalAbertura = unidadeOrganizacionalAbertura;
        this.unidadeOrganizacionalEncerramento = unidadeOrganizacionalEncerramento;
        this.UsuarioDaAbertura = UsuarioDaAbertura;
        this.UsuarioDeEncerramento = UsuarioDeEncerramento;
    }

    /** default constructor */
    public FiscalizacaoColetiva() {
    }

    /** minimal constructor */
    public FiscalizacaoColetiva(Date dataGeracao, Date ultimaAlteracao) {
        this.dataGeracao = dataGeracao;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataGeracao() {
        return this.dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getDataEncerramento() {
        return this.dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo nomeFiscalizacaoColetiva.
	 */
	public String getNomeFiscalizacaoColetiva() {
		return nomeFiscalizacaoColetiva;
	}

	/**
	 * @param nomeFiscalizacaoColetiva O nomeFiscalizacaoColetiva a ser setado.
	 */
	public void setNomeFiscalizacaoColetiva(String nomeFiscalizacaoColetiva) {
		this.nomeFiscalizacaoColetiva = nomeFiscalizacaoColetiva;
	}

	/**
	 * @return Retorna o campo unidadeOrganizacionalAbertura.
	 */
	public UnidadeOrganizacional getUnidadeOrganizacionalAbertura() {
		return unidadeOrganizacionalAbertura;
	}

	/**
	 * @param unidadeOrganizacionalAbertura O unidadeOrganizacionalAbertura a ser setado.
	 */
	public void setUnidadeOrganizacionalAbertura(
			UnidadeOrganizacional unidadeOrganizacionalAbertura) {
		this.unidadeOrganizacionalAbertura = unidadeOrganizacionalAbertura;
	}

	/**
	 * @return Retorna o campo unidadeOrganizacionalEncerramento.
	 */
	public UnidadeOrganizacional getUnidadeOrganizacionalEncerramento() {
		return unidadeOrganizacionalEncerramento;
	}

	/**
	 * @param unidadeOrganizacionalEncerramento O unidadeOrganizacionalEncerramento a ser setado.
	 */
	public void setUnidadeOrganizacionalEncerramento(
			UnidadeOrganizacional unidadeOrganizacionalEncerramento) {
		this.unidadeOrganizacionalEncerramento = unidadeOrganizacionalEncerramento;
	}

	/**
	 * @return Retorna o campo usuarioDaAbertura.
	 */
	public Usuario getUsuarioDaAbertura() {
		return UsuarioDaAbertura;
	}

	/**
	 * @param usuarioDaAbertura O usuarioDaAbertura a ser setado.
	 */
	public void setUsuarioDaAbertura(Usuario usuarioDaAbertura) {
		UsuarioDaAbertura = usuarioDaAbertura;
	}

	/**
	 * @return Retorna o campo usuarioDeEncerramento.
	 */
	public Usuario getUsuarioDeEncerramento() {
		return UsuarioDeEncerramento;
	}

	/**
	 * @param usuarioDeEncerramento O usuarioDeEncerramento a ser setado.
	 */
	public void setUsuarioDeEncerramento(Usuario usuarioDeEncerramento) {
		UsuarioDeEncerramento = usuarioDeEncerramento;
	}

}

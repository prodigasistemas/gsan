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
package gcom.seguranca.acesso;

import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;


/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 08/11/2006
 */
public class Abrangencia implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Imovel imovel;
    private Localidade localidade;
    private Localidade eloPolo;
    private UnidadeNegocio unidadeNegocio;
    private GerenciaRegional gerenciaRegional;
    private Municipio municipio;
    private SetorComercial setorComercial;
    private Quadra quadra;
    
    public final static String ABRANGENCIA = "abrangencia";
    
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param imovel
     */
    public Abrangencia(Usuario usuario, Imovel imovel) {
        this.usuario = usuario;
        this.imovel = imovel;
    }

    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param localidade
     */
    public Abrangencia(Usuario usuario, Localidade localidade) {
        this.usuario = usuario;
        this.localidade = localidade;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param eloPolo
     * @param usuario
     */
    public Abrangencia(Localidade eloPolo, Usuario usuario) {
        this.usuario = usuario;
        this.eloPolo = eloPolo;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param unidadeNegocio
     */
    public Abrangencia(Usuario usuario, UnidadeNegocio unidadeNegocio) {
        this.usuario = usuario;
        this.unidadeNegocio = unidadeNegocio;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param gerenciaRegional
     */
    public Abrangencia(Usuario usuario, GerenciaRegional gerenciaRegional) {
        this.usuario = usuario;
        this.gerenciaRegional = gerenciaRegional;
    }

    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param municipio
     */
    public Abrangencia(Usuario usuario, Municipio municipio) {
        this.usuario = usuario;
        this.municipio = municipio;
    }

    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param setorComercial
     */
    public Abrangencia(Usuario usuario, SetorComercial setorComercial) {
        this.usuario = usuario;
        this.setorComercial = setorComercial;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param quadra
     */
    public Abrangencia(Usuario usuario, Quadra quadra) {
        this.usuario = usuario;
        this.quadra = quadra;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 13/11/2006
     *
     * @return
     */
    public Localidade getEloPolo() {
        return eloPolo;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 13/11/2006
     *
     * @param eloPolo
     */
    protected void setEloPolo(Localidade eloPolo) {
        this.eloPolo = eloPolo;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 13/11/2006
     *
     * @return
     */
    public GerenciaRegional getGerenciaRegional() {
        return gerenciaRegional;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param gerenciaRegional
     */
    protected void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public Imovel getImovel() {
        return imovel;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param imovel
     */
    protected void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public Localidade getLocalidade() {
        return localidade;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param localidade
     */
    protected void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public UnidadeNegocio getUnidadeNegocio() {
        return unidadeNegocio;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param unidadeNegocio
     */
    protected void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
        this.unidadeNegocio = unidadeNegocio;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }


    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param usuario
     */
    protected void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @return
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @param municipio
     */
    protected void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @return
     */
    public Quadra getQuadra() {
        return quadra;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @param quadra
     */
    protected void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @return
     */
    public SetorComercial getSetorComercial() {
        return setorComercial;
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @param setorComercial
     */
    protected void setSetorComercial(SetorComercial setorComercial) {
        this.setorComercial = setorComercial;
    }
}    
  

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
package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Tramite extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String parecerTramite;

    /** persistent field */
    private Date dataTramite;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Usuario usuarioResponsavel;

    /** persistent field */
    private Usuario usuarioRegistro;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento;

    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalOrigem;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalDestino;
    

    /** full constructor */
    public Tramite(String parecerTramite, Date dataTramite, Date ultimaAlteracao, Usuario usuarioResponsavel, Usuario usuarioRegistro, gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento, UnidadeOrganizacional unidadeOrganizacionalOrigem, UnidadeOrganizacional unidadeOrganizacionalDestino) {
        this.parecerTramite = parecerTramite;
        this.dataTramite = dataTramite;
        this.ultimaAlteracao = ultimaAlteracao;
        this.usuarioResponsavel = usuarioResponsavel;
        this.usuarioRegistro = usuarioRegistro;
        this.registroAtendimento = registroAtendimento;
        this.unidadeOrganizacionalOrigem = unidadeOrganizacionalOrigem;
        this.unidadeOrganizacionalDestino = unidadeOrganizacionalDestino;
    }

    /** default constructor */
    public Tramite() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParecerTramite() {
        return this.parecerTramite;
    }

    public void setParecerTramite(String parecerTramite) {
        this.parecerTramite = parecerTramite;
    }

    public Date getDataTramite() {
        return this.dataTramite;
    }

    public void setDataTramite(Date dataTramite) {
        this.dataTramite = dataTramite;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Usuario getUsuarioResponsavel() {
        return this.usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public Usuario getUsuarioRegistro() {
        return this.usuarioRegistro;
    }

    public void setUsuarioRegistro(Usuario usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public gcom.atendimentopublico.registroatendimento.RegistroAtendimento getRegistroAtendimento() {
        return this.registroAtendimento;
    }

    public void setRegistroAtendimento(gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento) {
        this.registroAtendimento = registroAtendimento;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalOrigem() {
        return this.unidadeOrganizacionalOrigem;
    }

    public void setUnidadeOrganizacionalOrigem(UnidadeOrganizacional unidadeOrganizacionalOrigem) {
        this.unidadeOrganizacionalOrigem = unidadeOrganizacionalOrigem;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalDestino() {
        return this.unidadeOrganizacionalDestino;
    }

    public void setUnidadeOrganizacionalDestino(UnidadeOrganizacional unidadeOrganizacionalDestino) {
        this.unidadeOrganizacionalDestino = unidadeOrganizacionalDestino;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}    

	public Filtro retornaFiltro() {
		FiltroTramite filtroTramite = new FiltroTramite();

		filtroTramite.adicionarParametro(new ParametroSimples(FiltroTramite.ID, this.getId()));
		return filtroTramite;
	}     

}
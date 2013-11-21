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
package gcom.cadastro.unidade;

import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnidadeOrganizacional extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorEsgoto;

    /** persistent field */
    private short indicadorTramite;

    /** persistent field */
    private String descricao;

    /** nullable persistent field */
    private String sigla;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short indicadorAberturaRa;

    /** persistent field */
    private short indicadorUso;
    
    /** nullable persistent field */
    private Short indicadorCentralAtendimento;

    /** persistent field */
    private short indicadorTarifaSocial;  
    
    /** persistent field */
    private gcom.cadastro.unidade.UnidadeTipo unidadeTipo;

    /** persistent field */
    private MeioSolicitacao meioSolicitacao;

    /** persistent field */
    private Empresa empresa;

    /** persistent field */
    private Localidade localidade;
    
    /** persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private gcom.cadastro.unidade.UnidadeOrganizacional unidadeCentralizadora;
    
    private gcom.cadastro.unidade.UnidadeOrganizacional unidadeRepavimentadora;    
 
    /** persistent field */
    private gcom.cadastro.unidade.UnidadeOrganizacional unidadeSuperior;
    
    private Integer codigoConstante;

    /** full constructor */
    public UnidadeOrganizacional(short indicadorEsgoto, short indicadorTramite, String descricao, 
    		String sigla, Date ultimaAlteracao, Short indicadorAberturaRa, 
    		short indicadorUso, gcom.cadastro.unidade.UnidadeTipo unidadeTipo, 
    		MeioSolicitacao meioSolicitacao, Empresa empresa, Localidade localidade, 
    		GerenciaRegional gerenciaRegional, gcom.cadastro.unidade.UnidadeOrganizacional unidadeCentralizadora, 
    		gcom.cadastro.unidade.UnidadeOrganizacional unidadeRepavimentadora,
    		gcom.cadastro.unidade.UnidadeOrganizacional unidadeSuperior,
    		Short indicadorCentralAtendimento,short indicadorTarifaSocial) {
        
    	this.indicadorEsgoto = indicadorEsgoto;
        this.indicadorTramite = indicadorTramite;
        this.descricao = descricao;
        this.sigla = sigla;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorAberturaRa = indicadorAberturaRa;
        this.indicadorUso = indicadorUso;
        this.unidadeTipo = unidadeTipo;
        this.meioSolicitacao = meioSolicitacao;
        this.empresa = empresa;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.unidadeCentralizadora = unidadeCentralizadora;
        this.unidadeRepavimentadora = unidadeRepavimentadora;
        this.unidadeSuperior = unidadeSuperior;
        this.indicadorCentralAtendimento = indicadorCentralAtendimento;
        this.indicadorTarifaSocial = indicadorTarifaSocial;

    }

    /** default constructor */
    public UnidadeOrganizacional() {
    }

    /** minimal constructor */
    public UnidadeOrganizacional(short indicadorEsgoto, short indicadorTramite, String descricao, 
    		Date ultimaAlteracao, short indicadorUso, gcom.cadastro.unidade.UnidadeTipo unidadeTipo, 
    		MeioSolicitacao meioSolicitacao, Empresa empresa, Localidade localidade, 
    		GerenciaRegional gerenciaRegional, gcom.cadastro.unidade.UnidadeOrganizacional unidadeCentralizadora, 
    		gcom.cadastro.unidade.UnidadeOrganizacional unidadeRepavimentadora,
    		gcom.cadastro.unidade.UnidadeOrganizacional unidadeSuperior,Short indicadorCentralAtendimento, 
    		short indicadorTarifaSocial) {
        
    	this.indicadorEsgoto = indicadorEsgoto;
        this.indicadorTramite = indicadorTramite;
        this.descricao = descricao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorUso = indicadorUso;
        this.unidadeTipo = unidadeTipo;
        this.meioSolicitacao = meioSolicitacao;
        this.empresa = empresa;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.unidadeCentralizadora = unidadeCentralizadora;
        this.unidadeRepavimentadora = unidadeRepavimentadora;
        this.unidadeSuperior = unidadeSuperior;
        this.indicadorCentralAtendimento = indicadorCentralAtendimento;
        this.indicadorTarifaSocial = indicadorTarifaSocial;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getIndicadorEsgoto() {
        return this.indicadorEsgoto;
    }

    public void setIndicadorEsgoto(short indicadorEsgoto) {
        this.indicadorEsgoto = indicadorEsgoto;
    }

    public short getIndicadorTramite() {
        return this.indicadorTramite;
    }

    public void setIndicadorTramite(short indicadorTramite) {
        this.indicadorTramite = indicadorTramite;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getIndicadorAberturaRa() {
        return this.indicadorAberturaRa;
    }

    public void setIndicadorAberturaRa(Short indicadorAberturaRa) {
        this.indicadorAberturaRa = indicadorAberturaRa;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public gcom.cadastro.unidade.UnidadeTipo getUnidadeTipo() {
        return this.unidadeTipo;
    }

    public void setUnidadeTipo(gcom.cadastro.unidade.UnidadeTipo unidadeTipo) {
        this.unidadeTipo = unidadeTipo;
    }

    public MeioSolicitacao getMeioSolicitacao() {
        return this.meioSolicitacao;
    }

    public void setMeioSolicitacao(MeioSolicitacao meioSolicitacao) {
        this.meioSolicitacao = meioSolicitacao;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public GerenciaRegional getGerenciaRegional() {
        return this.gerenciaRegional;
    }

    public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }

    public gcom.cadastro.unidade.UnidadeOrganizacional getUnidadeCentralizadora() {
        return this.unidadeCentralizadora;
    }

    public void setUnidadeCentralizadora(gcom.cadastro.unidade.UnidadeOrganizacional unidadeCentralizadora) {
        this.unidadeCentralizadora = unidadeCentralizadora;
    }

    public gcom.cadastro.unidade.UnidadeOrganizacional getUnidadeSuperior() {
        return this.unidadeSuperior;
    }

    public void setUnidadeSuperior(gcom.cadastro.unidade.UnidadeOrganizacional unidadeSuperior) {
        this.unidadeSuperior = unidadeSuperior;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getIndicadorCentralAtendimento() {
		return indicadorCentralAtendimento;
	}

	public void setIndicadorCentralAtendimento(Short indicadorCentralAtendimento) {
		this.indicadorCentralAtendimento = indicadorCentralAtendimento;
	}

	public short getIndicadorTarifaSocial() {
		return indicadorTarifaSocial;
	}

	public void setIndicadorTarifaSocial(short indicadorTarifaSocial) {
		this.indicadorTarifaSocial = indicadorTarifaSocial;
	}



	/**
	 * @return Retorna o campo unidadeRepavimentadora.
	 */
	public gcom.cadastro.unidade.UnidadeOrganizacional getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	/**
	 * @param unidadeRepavimentadora O unidadeRepavimentadora a ser setado.
	 */
	public void setUnidadeRepavimentadora(
			gcom.cadastro.unidade.UnidadeOrganizacional unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}	
	
	public Filtro retornaFiltro(){
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("meioSolicitacao");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("unidadeCentralizadora");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");		
		filtroUnidadeOrganizacional. adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.ID, this.getId()));
		return filtroUnidadeOrganizacional; 
	}
	
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();

		filtro.adicionarParametro(
			new ParametroSimples(FiltroUnidadeOrganizacional.ID,this.getId()));
	
		return filtro;
	}
	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getCodigoConstante() {
		return codigoConstante;
	}

	public void setCodigoConstante(Integer codigoConstante) {
		this.codigoConstante = codigoConstante;
	}
	
}
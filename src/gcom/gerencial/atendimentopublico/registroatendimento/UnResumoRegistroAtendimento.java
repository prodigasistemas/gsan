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
package gcom.gerencial.atendimentopublico.registroatendimento;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.micromedicao.GRota;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
//******************************************************************************
//Autor: Ivan Sergio
//Data: 14/01/2009
//Alteracao: 14/01/2009 - CRC811 - Adicionado o campo amen_id(Motivo Encerramento) a consulta;
//******************************************************************************

public class UnResumoRegistroAtendimento implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static short RERA_QTD_PENDENTES = 1;
	
	public final static short RERA_QTD_ENCERRADAS = 2;
	
	public final static short RERA_QTD_BLOQUEADAS = 3;
	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int referencia;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private int registrosGerados;

    /** persistent field */
    private Integer setorComercial;

    /** persistent field */
    private int registrosPendentes;
    
    private int registrosGeradosNoMesPrazo;    

    /** persistent field */
    private Integer Quadra;

    /** persistent field */
    private int registrosBloqueados;

    /** persistent field */
    private int registrosEncerrados;

    /** persistent field */
    private int indicadorAtendimentoOnline;

    /** persistent field */
    private GMeioSolicitacao gerMeioSolicitacao;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GSolicitacaoTipo gerSolicitacaoTipo;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private GSolicitacaoTipoEspecificacao gerSolicitacaoTipoEspecificacao;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GRota gerRota;
    
    //***********************************************************
    //CRC811 - Motivo Encerramento
    //***********************************************************
    private GAtendimentoMotivoEncerramento gerMotivoEncerramento;
    //***********************************************************
    
    private Integer registrosPendentesForaPrazo;
    private Integer registrosGeradosNoMesForaPrazo;
    private Integer registrosEncerradosForaPrazo;
    private Integer unidadeSolicitacao;
    private Integer unidadeEncerramento;    
    
    private Short codigoRota;

    public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getRegistrosEncerradosForaPrazo() {
		return registrosEncerradosForaPrazo;
	}

	public void setRegistrosEncerradosForaPrazo(Integer registrosEncerradosForaPrazo) {
		this.registrosEncerradosForaPrazo = registrosEncerradosForaPrazo;
	}

	public Integer getRegistrosPendentesForaPrazo() {
		return registrosPendentesForaPrazo;
	}

	public void setRegistrosPendentesForaPrazo(Integer registrosPendentesForaPrazo) {
		this.registrosPendentesForaPrazo = registrosPendentesForaPrazo;
	}

	public Integer getUnidadeEncerramento() {
		return unidadeEncerramento;
	}

	public void setUnidadeEncerramento(Integer unidadeEncerramento) {
		this.unidadeEncerramento = unidadeEncerramento;
	}

	public Integer getUnidadeSolicitacao() {
		return unidadeSolicitacao;
	}

	public void setUnidadeSolicitacao(Integer unidadeSolicitacao) {
		this.unidadeSolicitacao = unidadeSolicitacao;
	}

	/** full constructor */
    public UnResumoRegistroAtendimento(
    		Integer id, 
    		int referencia, 
    		Date ultimaAlteracao, 
    		int registrosGerados, 
    		Integer setorComercial, 
    		int registrosPendentesNoPrazo, 
    		int registrosPendentesForaPrazo,
    		int registrosGeradosNoMesPrazo,
    	    int registrosGeradosNoMesForaPrazo,   		
    		Integer Quadra, 
    		int registrosBloqueados, 
    		int registrosEncerradosNoPrazo, 
    		int registrosEncerradosForaPrazo,
    		int indicadorAtendimentoOnline, 
    		GMeioSolicitacao gerMeioSolicitacao, 
    		GSubcategoria gerSubcategoria, 
    		GClienteTipo gerClienteTipo, 
    		GLigacaoAguaSituacao gerLigacaoAguaSituacao, 
    		GUnidadeNegocio gerUnidadeNegocio, 
    		GLocalidade gerLocalidade, 
    		GLocalidade gerLocalidadeElo, 
    		GSolicitacaoTipo gerSolicitacaoTipo, 
    		GQuadra gerQuadra, 
    		GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao,
    		GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil,
    		GSolicitacaoTipoEspecificacao gerSolicitacaoTipoEspecificacao,
    		GGerenciaRegional gerGerenciaRegional,
    		GSetorComercial gerSetorComercial,
    		GLigacaoAguaPerfil gerLigacaoAguaPerfil,
    		GEsferaPoder gerEsferaPoder,
    		GCategoria gerCategoria,
    		GImovelPerfil gerImovelPerfil,
    		GRota gerRota,
    		Short codigoRota) {
        this.id = id;
        this.referencia = referencia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.registrosGerados = registrosGerados;
        this.setorComercial = setorComercial;
        this.registrosPendentes = registrosPendentesNoPrazo;
        this.registrosPendentesForaPrazo = registrosPendentesForaPrazo;        
        this.registrosGeradosNoMesPrazo = registrosGeradosNoMesPrazo; 
        this.registrosGeradosNoMesForaPrazo = registrosGeradosNoMesForaPrazo;
        this.Quadra = Quadra;
        this.registrosBloqueados = registrosBloqueados;
        this.registrosEncerrados = registrosEncerradosNoPrazo;
        this.registrosEncerradosForaPrazo = registrosEncerradosForaPrazo;
        this.indicadorAtendimentoOnline = indicadorAtendimentoOnline;
        this.gerMeioSolicitacao = gerMeioSolicitacao;
        this.gerSubcategoria = gerSubcategoria;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerSolicitacaoTipo = gerSolicitacaoTipo;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
        this.gerSolicitacaoTipoEspecificacao = gerSolicitacaoTipoEspecificacao;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerCategoria = gerCategoria;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerRota = gerRota;
        this.codigoRota = codigoRota;
    }

    /** default constructor */
    public UnResumoRegistroAtendimento() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getReferencia() {
        return this.referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public int getRegistrosGerados() {
        return this.registrosGerados;
    }

    public void setRegistrosGerados(int registrosGerados) {
        this.registrosGerados = registrosGerados;
    }

    public Integer getSetorComercial() {
        return this.setorComercial;
    }

    public void setSetorComercial(Integer setorComercial) {
        this.setorComercial = setorComercial;
    }

    public int getRegistrosPendentes() {
        return this.registrosPendentes;
    }

    public void setRegistrosPendentes(int registrosPendentes) {
        this.registrosPendentes = registrosPendentes;
    }

    public Integer getQuadra() {
        return this.Quadra;
    }

    public void setQuadra(Integer Quadra) {
        this.Quadra = Quadra;
    }

    public int getRegistrosBloqueados() {
        return this.registrosBloqueados;
    }

    public void setRegistrosBloqueados(int registrosBloqueados) {
        this.registrosBloqueados = registrosBloqueados;
    }

    public int getRegistrosEncerrados() {
        return this.registrosEncerrados;
    }

    public void setRegistrosEncerrados(int registrosEncerrados) {
        this.registrosEncerrados = registrosEncerrados;
    }
    
    public int getRegistrosGeradosNoMesPrazo() {
		return registrosGeradosNoMesPrazo;
	}

	public void setRegistrosGeradosNoMesPrazo(int registrosGeradosNoMesPrazo) {
		this.registrosGeradosNoMesPrazo = registrosGeradosNoMesPrazo;
	}

	public Integer getRegistrosGeradosNoMesForaPrazo() {
		return registrosGeradosNoMesForaPrazo;
	}

	public void setRegistrosGeradosNoMesForaPrazo(
			Integer registrosGeradosNoMesForaPrazo) {
		this.registrosGeradosNoMesForaPrazo = registrosGeradosNoMesForaPrazo;
	}

	public int getIndicadorAtendimentoOnline() {
        return this.indicadorAtendimentoOnline;
    }

    public void setIndicadorAtendimentoOnline(int indicadorAtendimentoOnline) {
        this.indicadorAtendimentoOnline = indicadorAtendimentoOnline;
    }

    public GMeioSolicitacao getGerMeioSolicitacao() {
        return this.gerMeioSolicitacao;
    }

    public void setGerMeioSolicitacao(GMeioSolicitacao gerMeioSolicitacao) {
        this.gerMeioSolicitacao = gerMeioSolicitacao;
    }

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
    }

    public GClienteTipo getGerClienteTipo() {
        return this.gerClienteTipo;
    }

    public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
        this.gerClienteTipo = gerClienteTipo;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GSolicitacaoTipo getGerSolicitacaoTipo() {
        return this.gerSolicitacaoTipo;
    }

    public void setGerSolicitacaoTipo(GSolicitacaoTipo gerSolicitacaoTipo) {
        this.gerSolicitacaoTipo = gerSolicitacaoTipo;
    }

    public GQuadra getGerQuadra() {
        return this.gerQuadra;
    }

    public void setGerQuadra(GQuadra gerQuadra) {
        this.gerQuadra = gerQuadra;
    }

    public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
        return this.gerLigacaoEsgotoSituacao;
    }

    public void setGerLigacaoEsgotoSituacao(GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    }

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public GSolicitacaoTipoEspecificacao getGerSolicitacaoTipoEspecificacao() {
        return this.gerSolicitacaoTipoEspecificacao;
    }

    public void setGerSolicitacaoTipoEspecificacao(GSolicitacaoTipoEspecificacao gerSolicitacaoTipoEspecificacao) {
        this.gerSolicitacaoTipoEspecificacao = gerSolicitacaoTipoEspecificacao;
    }

    public GGerenciaRegional getGerGerenciaRegional() {
        return this.gerGerenciaRegional;
    }

    public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
        this.gerGerenciaRegional = gerGerenciaRegional;
    }

    public GSetorComercial getGerSetorComercial() {
        return this.gerSetorComercial;
    }

    public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
        this.gerSetorComercial = gerSetorComercial;
    }

    public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
        return this.gerLigacaoAguaPerfil;
    }

    public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(GRota gerRota) {
        this.gerRota = gerRota;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo gerMotivoEncerramento.
	 */
	public GAtendimentoMotivoEncerramento getGerMotivoEncerramento() {
		return gerMotivoEncerramento;
	}

	/**
	 * @param gerMotivoEncerramento O gerMotivoEncerramento a ser setado.
	 */
	public void setGerMotivoEncerramento(
			GAtendimentoMotivoEncerramento gerMotivoEncerramento) {
		this.gerMotivoEncerramento = gerMotivoEncerramento;
	}

}

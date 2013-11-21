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
package gcom.gerencial.micromedicao;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.GEmpresa;
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
import gcom.gerencial.faturamento.GConsumoTarifa;
import gcom.gerencial.faturamento.GFaturamentoGrupo;
import gcom.gerencial.micromedicao.leitura.GLeituraSituacao;
import gcom.gerencial.micromedicao.medicao.GMedicaoTipo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoLeituraAnormalidade implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int referencia;

    /** persistent field */
    private int setorcomercial;

    /** persistent field */
    private int quadra;

    /** persistent field */
    private int quantidadeLeituras;

    /** persistent field */
    private int idLeituraAnormalidade;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GMedicaoTipo gerMedicaoTipo;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private gcom.gerencial.micromedicao.GRota gerRota;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;
    
    /** persistent field */
    private GEmpresa gerEmpresa;
    
    /** persistent field */
    private GLeituraSituacao gerLeituraSituacao;
    
    private GConsumoTarifa gerConsumoTarifa;
    
    private GFaturamentoGrupo gerFaturamentoGrupo;
    
    // CRC719
    private int idLeituraAnormalidadeInformada;
    private int quantidadeLeiturasInformada;

    /** full constructor */
    public UnResumoLeituraAnormalidade(
    		int referencia, 
    		GGerenciaRegional gerGerenciaRegional,
    		GUnidadeNegocio gerUnidadeNegocio, 
    		GLocalidade gerLocalidadeElo, 
    		GLocalidade gerLocalidade,
    		int setorcomercial, 
    		gcom.gerencial.micromedicao.GRota gerRota,
    		int quadra, 
    		GImovelPerfil gerImovelPerfil,
    		GEsferaPoder gerEsferaPoder,
    		GClienteTipo gerClienteTipo,
    		GLigacaoAguaSituacao gerLigacaoAguaSituacao,
    		GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao,
    		GCategoria gerCategoria,
    		GSubcategoria gerSubcategoria,
    		GLigacaoAguaPerfil gerLigacaoAguaPerfil,
    		GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil,
    		GMedicaoTipo gerMedicaoTipo,
    		int idLeituraAnormalidade,
    		GSetorComercial gerSetorComercial,
    		GQuadra gerQuadra,
    		int quantidadeLeituras,
    		GEmpresa gerEmpresa,
    		GLeituraSituacao gerLeituraSituacao) {
    	
    	this.referencia = referencia;
    	this.gerGerenciaRegional = gerGerenciaRegional;
    	this.gerUnidadeNegocio = gerUnidadeNegocio;
    	this.gerLocalidadeElo = gerLocalidadeElo;
    	this.gerLocalidade = gerLocalidade;
    	this.setorcomercial = setorcomercial;
    	this.gerRota = gerRota;
    	this.quadra = quadra;
    	this.gerImovelPerfil = gerImovelPerfil;
    	this.gerEsferaPoder = gerEsferaPoder;
    	this.gerClienteTipo = gerClienteTipo;
    	this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    	this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    	this.gerCategoria = gerCategoria;
    	this.gerSubcategoria = gerSubcategoria;
    	this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    	this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    	this.gerMedicaoTipo = gerMedicaoTipo;
    	this.idLeituraAnormalidade = idLeituraAnormalidade;
    	this.gerSetorComercial = gerSetorComercial;
    	this.gerQuadra = gerQuadra;
    	this.quantidadeLeituras = quantidadeLeituras;
    	this.gerEmpresa = gerEmpresa;
    	this.gerLeituraSituacao = gerLeituraSituacao;
    	this.ultimaAlteracao = new Date();
    }

    /** default constructor */
    public UnResumoLeituraAnormalidade() {
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

    public int getSetorcomercial() {
        return this.setorcomercial;
    }

    public void setSetorcomercial(int setorcomercial) {
        this.setorcomercial = setorcomercial;
    }

    public int getQuadra() {
        return this.quadra;
    }

    public void setQuadra(int quadra) {
        this.quadra = quadra;
    }

    public int getQuantidadeLeituras() {
        return this.quantidadeLeituras;
    }

    public void setQuantidadeLeituras(int quantidadeLeituras) {
        this.quantidadeLeituras = quantidadeLeituras;
    }

    public int getIdLeituraAnormalidade() {
        return this.idLeituraAnormalidade;
    }

    public void setIdLeituraAnormalidade(int idLeituraAnormalidade) {
        this.idLeituraAnormalidade = idLeituraAnormalidade;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
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

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
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

    public GClienteTipo getGerClienteTipo() {
        return this.gerClienteTipo;
    }

    public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
        this.gerClienteTipo = gerClienteTipo;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GMedicaoTipo getGerMedicaoTipo() {
        return this.gerMedicaoTipo;
    }

    public void setGerMedicaoTipo(GMedicaoTipo gerMedicaoTipo) {
        this.gerMedicaoTipo = gerMedicaoTipo;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public gcom.gerencial.micromedicao.GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(gcom.gerencial.micromedicao.GRota gerRota) {
        this.gerRota = gerRota;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public GEmpresa getGerEmpresa() {
		return gerEmpresa;
	}

	public void setGerEmpresa(GEmpresa gerEmpresa) {
		this.gerEmpresa = gerEmpresa;
	}

	public GLeituraSituacao getGerLeituraSituacao() {
		return gerLeituraSituacao;
	}

	public void setGerLeituraSituacao(GLeituraSituacao gerLeituraSituacao) {
		this.gerLeituraSituacao = gerLeituraSituacao;
	}

	public GConsumoTarifa getGerConsumoTarifa() {
		return gerConsumoTarifa;
	}

	public void setGerConsumoTarifa(GConsumoTarifa gerConsumoTarifa) {
		this.gerConsumoTarifa = gerConsumoTarifa;
	}

	public GFaturamentoGrupo getGerFaturamentoGrupo() {
		return gerFaturamentoGrupo;
	}

	public void setGerFaturamentoGrupo(GFaturamentoGrupo gerFaturamentoGrupo) {
		this.gerFaturamentoGrupo = gerFaturamentoGrupo;
	}

	/**
	 * @return Retorna o campo idLeituraAnormalidadeInformada.
	 */
	public int getIdLeituraAnormalidadeInformada() {
		return idLeituraAnormalidadeInformada;
	}

	/**
	 * @param idLeituraAnormalidadeInformada O idLeituraAnormalidadeInformada a ser setado.
	 */
	public void setIdLeituraAnormalidadeInformada(int idLeituraAnormalidadeInformada) {
		this.idLeituraAnormalidadeInformada = idLeituraAnormalidadeInformada;
	}

	/**
	 * @return Retorna o campo quantidadeLeiturasInformada.
	 */
	public int getQuantidadeLeiturasInformada() {
		return quantidadeLeiturasInformada;
	}

	/**
	 * @param quantidadeLeiturasInformada O quantidadeLeiturasInformada a ser setado.
	 */
	public void setQuantidadeLeiturasInformada(int quantidadeLeiturasInformada) {
		this.quantidadeLeiturasInformada = quantidadeLeiturasInformada;
	}

}

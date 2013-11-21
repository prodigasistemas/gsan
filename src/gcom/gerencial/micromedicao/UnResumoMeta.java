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
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoMeta implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeLigacoesCadastradas;

    /** persistent field */
    private int quantidadeLigacoesCortadas;

    /** persistent field */
    private int quantidadeLigacoesSuprimidas;

    /** persistent field */
    private int quantidadeLigacoesAtivas;

    /** persistent field */
    private int quantidadeLigacoesAtivasDebito3m;

    /** persistent field */
    private int quantidadeLigacoesConsumoMedido;

    /** persistent field */
    private int quantidadeLigacoesConsumoNaooMedido;

    /** persistent field */
    private int quantidadeLigacoesConsumoAte5m3;

    /** persistent field */
    private int quantidadeLigacoesConsumoMedia;

    /** persistent field */
    private int quantidadeEconomias;

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
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade GLocalidadeElo;

    /** persistent field */
    private GCategoria gerCategoria;

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
    private Integer codigoGrupoSubcategoria;

    /** full constructor */
    public UnResumoMeta(Integer id, int anoMesReferencia, int codigoSetorComercial, int numeroQuadra, int quantidadeLigacoesCadastradas, int quantidadeLigacoesCortadas, int quantidadeLigacoesSuprimidas, int quantidadeLigacoesAtivas, int quantidadeLigacoesAtivasDebito3m, int quantidadeLigacoesConsumoMedido, int quantidadeLigacoesConsumoNaooMedido, int quantidadeLigacoesConsumoAte5m3, int quantidadeLigacoesConsumoMedia, int quantidadeEconomias, Date ultimaAlteracao, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GSubcategoria gerSubcategoria, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GEsferaPoder gerEsferaPoder, GClienteTipo gerClienteTipo, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GUnidadeNegocio gerUnidadeNegocio, GLocalidade gerLocalidade, GLocalidade GLocalidadeElo, GCategoria gerCategoria, GImovelPerfil gerImovelPerfil, gcom.gerencial.micromedicao.GRota gerRota, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeLigacoesCadastradas = quantidadeLigacoesCadastradas;
        this.quantidadeLigacoesCortadas = quantidadeLigacoesCortadas;
        this.quantidadeLigacoesSuprimidas = quantidadeLigacoesSuprimidas;
        this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
        this.quantidadeLigacoesAtivasDebito3m = quantidadeLigacoesAtivasDebito3m;
        this.quantidadeLigacoesConsumoMedido = quantidadeLigacoesConsumoMedido;
        this.quantidadeLigacoesConsumoNaooMedido = quantidadeLigacoesConsumoNaooMedido;
        this.quantidadeLigacoesConsumoAte5m3 = quantidadeLigacoesConsumoAte5m3;
        this.quantidadeLigacoesConsumoMedia = quantidadeLigacoesConsumoMedia;
        this.quantidadeEconomias = quantidadeEconomias;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerSubcategoria = gerSubcategoria;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLocalidade = gerLocalidade;
        this.GLocalidadeElo = GLocalidadeElo;
        this.gerCategoria = gerCategoria;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerRota = gerRota;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    /** default constructor */
    public UnResumoMeta() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public int getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(int codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public int getQuantidadeLigacoesCadastradas() {
        return this.quantidadeLigacoesCadastradas;
    }

    public void setQuantidadeLigacoesCadastradas(int quantidadeLigacoesCadastradas) {
        this.quantidadeLigacoesCadastradas = quantidadeLigacoesCadastradas;
    }

    public int getQuantidadeLigacoesCortadas() {
        return this.quantidadeLigacoesCortadas;
    }

    public void setQuantidadeLigacoesCortadas(int quantidadeLigacoesCortadas) {
        this.quantidadeLigacoesCortadas = quantidadeLigacoesCortadas;
    }

    public int getQuantidadeLigacoesSuprimidas() {
        return this.quantidadeLigacoesSuprimidas;
    }

    public void setQuantidadeLigacoesSuprimidas(int quantidadeLigacoesSuprimidas) {
        this.quantidadeLigacoesSuprimidas = quantidadeLigacoesSuprimidas;
    }

    public int getQuantidadeLigacoesAtivas() {
        return this.quantidadeLigacoesAtivas;
    }

    public void setQuantidadeLigacoesAtivas(int quantidadeLigacoesAtivas) {
        this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
    }

    public int getQuantidadeLigacoesAtivasDebito3m() {
        return this.quantidadeLigacoesAtivasDebito3m;
    }

    public void setQuantidadeLigacoesAtivasDebito3m(int quantidadeLigacoesAtivasDebito3m) {
        this.quantidadeLigacoesAtivasDebito3m = quantidadeLigacoesAtivasDebito3m;
    }

    public int getQuantidadeLigacoesConsumoMedido() {
        return this.quantidadeLigacoesConsumoMedido;
    }

    public void setQuantidadeLigacoesConsumoMedido(int quantidadeLigacoesConsumoMedido) {
        this.quantidadeLigacoesConsumoMedido = quantidadeLigacoesConsumoMedido;
    }

    public int getQuantidadeLigacoesConsumoNaooMedido() {
        return this.quantidadeLigacoesConsumoNaooMedido;
    }

    public void setQuantidadeLigacoesConsumoNaooMedido(int quantidadeLigacoesConsumoNaooMedido) {
        this.quantidadeLigacoesConsumoNaooMedido = quantidadeLigacoesConsumoNaooMedido;
    }

    public int getQuantidadeLigacoesConsumoAte5m3() {
        return this.quantidadeLigacoesConsumoAte5m3;
    }

    public void setQuantidadeLigacoesConsumoAte5m3(int quantidadeLigacoesConsumoAte5m3) {
        this.quantidadeLigacoesConsumoAte5m3 = quantidadeLigacoesConsumoAte5m3;
    }

    public int getQuantidadeLigacoesConsumoMedia() {
        return this.quantidadeLigacoesConsumoMedia;
    }

    public void setQuantidadeLigacoesConsumoMedia(int quantidadeLigacoesConsumoMedia) {
        this.quantidadeLigacoesConsumoMedia = quantidadeLigacoesConsumoMedia;
    }

    public int getQuantidadeEconomias() {
        return this.quantidadeEconomias;
    }

    public void setQuantidadeEconomias(int quantidadeEconomias) {
        this.quantidadeEconomias = quantidadeEconomias;
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

    public GLocalidade getGLocalidadeElo() {
        return this.GLocalidadeElo;
    }

    public void setGLocalidadeElo(GLocalidade GLocalidadeElo) {
        this.GLocalidadeElo = GLocalidadeElo;
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

	public Integer getCodigoGrupoSubcategoria() {
		return codigoGrupoSubcategoria;
	}

	public void setCodigoGrupoSubcategoria(Integer codigoGrupoSubcategoria) {
		this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
	}

}

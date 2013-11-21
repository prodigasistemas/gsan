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
package gcom.gerencial.cobranca;

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
import gcom.gerencial.faturamento.GConsumoTarifa;
import gcom.gerencial.faturamento.GFaturamentoGrupo;
import gcom.gerencial.micromedicao.GRota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoPendencia implements Serializable {

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
    private Date ultimaAlteracao;

    /** persistent field */
    private int indicadorVolumeFixadoAgua;

    /** persistent field */
    private int indicadorVolumeFixadoEsgoto;

    /** persistent field */
    private int anoMesReferenciaDocumento;

    /** persistent field */
    private short indicadorHidrometro;

    /** persistent field */
    private short indicadorVencido;

    /** persistent field */
    private int quantidadeLigacoes;

    /** persistent field */
    private int quantidadeDocumentos;

    /** nullable persistent field */
    private BigDecimal valorPendenteAgua;

    /** nullable persistent field */
    private BigDecimal valorPendenteEsgoto;

    /** nullable persistent field */
    private BigDecimal valorPendenteDebitos;

    /** nullable persistent field */
    private BigDecimal valorPendenteCreditos;

    /** nullable persistent field */
    private BigDecimal valorPendenteImpostos;
   
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
    private GRota gerRota;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;
    
    private GConsumoTarifa gerConsumoTarifa;
    
    private GFaturamentoGrupo gerFaturamentoGrupo;
    
    private Short codigoRota;

  
	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public GFaturamentoGrupo getGerFaturamentoGrupo() {
		return gerFaturamentoGrupo;
	}

	public void setGerFaturamentoGrupo(GFaturamentoGrupo gerFaturamentoGrupo) {
		this.gerFaturamentoGrupo = gerFaturamentoGrupo;
	}

	public GConsumoTarifa getGerConsumoTarifa() {
		return gerConsumoTarifa;
	}

	public void setGerConsumoTarifa(GConsumoTarifa gerConsumoTarifa) {
		this.gerConsumoTarifa = gerConsumoTarifa;
	}

	

	/** full constructor */
    public UnResumoPendencia(Integer id, int anoMesReferencia, int codigoSetorComercial, int numeroQuadra, Date ultimaAlteracao, int indicadorVolumeFixadoAgua, int indicadorVolumeFixadoEsgoto, int anoMesReferenciaDocumento, short indicadorHidrometro, short indicadorVencido, int quantidadeLigacoes, int quantidadeDocumentos, BigDecimal valorPendenteAgua, BigDecimal valorPendenteEsgoto, BigDecimal valorPendenteDebitos, BigDecimal valorPendenteCreditos, BigDecimal valorPendenteImpostos, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GSubcategoria gerSubcategoria, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GEsferaPoder gerEsferaPoder, GClienteTipo gerClienteTipo, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GUnidadeNegocio gerUnidadeNegocio, GLocalidade gerLocalidade, GLocalidade GLocalidadeElo, GCategoria gerCategoria, GImovelPerfil gerImovelPerfil, GRota gerRota, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorVolumeFixadoAgua = indicadorVolumeFixadoAgua;
        this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
        this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
        this.indicadorHidrometro = indicadorHidrometro;
        this.indicadorVencido = indicadorVencido;
        this.quantidadeLigacoes = quantidadeLigacoes;
        this.quantidadeDocumentos = quantidadeDocumentos;
        this.valorPendenteAgua = valorPendenteAgua;
        this.valorPendenteEsgoto = valorPendenteEsgoto;
        this.valorPendenteDebitos = valorPendenteDebitos;
        this.valorPendenteCreditos = valorPendenteCreditos;
        this.valorPendenteImpostos = valorPendenteImpostos;
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
    public UnResumoPendencia() {
    }

    /** minimal constructor */
    public UnResumoPendencia(Integer id, int anoMesReferencia, int codigoSetorComercial, int numeroQuadra, Date ultimaAlteracao, int indicadorVolumeFixadoAgua, int indicadorVolumeFixadoEsgoto, int anoMesReferenciaDocumento, short indicadorHidrometro, short indicadorVencido, int quantidadeLigacoes, int quantidadeDocumentos, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GSubcategoria gerSubcategoria, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GEsferaPoder gerEsferaPoder, GClienteTipo gerClienteTipo, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GUnidadeNegocio gerUnidadeNegocio, GLocalidade gerLocalidade, GLocalidade GLocalidadeElo, GCategoria gerCategoria, GImovelPerfil gerImovelPerfil, GRota gerRota, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorVolumeFixadoAgua = indicadorVolumeFixadoAgua;
        this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
        this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
        this.indicadorHidrometro = indicadorHidrometro;
        this.indicadorVencido = indicadorVencido;
        this.quantidadeLigacoes = quantidadeLigacoes;
        this.quantidadeDocumentos = quantidadeDocumentos;
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

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public int getIndicadorVolumeFixadoAgua() {
        return this.indicadorVolumeFixadoAgua;
    }

    public void setIndicadorVolumeFixadoAgua(int indicadorVolumeFixadoAgua) {
        this.indicadorVolumeFixadoAgua = indicadorVolumeFixadoAgua;
    }

    public int getIndicadorVolumeFixadoEsgoto() {
        return this.indicadorVolumeFixadoEsgoto;
    }

    public void setIndicadorVolumeFixadoEsgoto(int indicadorVolumeFixadoEsgoto) {
        this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
    }

    public int getAnoMesReferenciaDocumento() {
        return this.anoMesReferenciaDocumento;
    }

    public void setAnoMesReferenciaDocumento(int anoMesReferenciaDocumento) {
        this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
    }

    public short getIndicadorHidrometro() {
        return this.indicadorHidrometro;
    }

    public void setIndicadorHidrometro(short indicadorHidrometro) {
        this.indicadorHidrometro = indicadorHidrometro;
    }

    public short getIndicadorVencido() {
        return this.indicadorVencido;
    }

    public void setIndicadorVencido(short indicadorVencido) {
        this.indicadorVencido = indicadorVencido;
    }

    public int getQuantidadeLigacoes() {
        return this.quantidadeLigacoes;
    }

    public void setQuantidadeLigacoes(int quantidadeLigacoes) {
        this.quantidadeLigacoes = quantidadeLigacoes;
    }

    public int getQuantidadeDocumentos() {
        return this.quantidadeDocumentos;
    }

    public void setQuantidadeDocumentos(int quantidadeDocumentos) {
        this.quantidadeDocumentos = quantidadeDocumentos;
    }

    public BigDecimal getValorPendenteAgua() {
        return this.valorPendenteAgua;
    }

    public void setValorPendenteAgua(BigDecimal valorPendenteAgua) {
        this.valorPendenteAgua = valorPendenteAgua;
    }

    public BigDecimal getValorPendenteEsgoto() {
        return this.valorPendenteEsgoto;
    }

    public void setValorPendenteEsgoto(BigDecimal valorPendenteEsgoto) {
        this.valorPendenteEsgoto = valorPendenteEsgoto;
    }

    public BigDecimal getValorPendenteDebitos() {
        return this.valorPendenteDebitos;
    }

    public void setValorPendenteDebitos(BigDecimal valorPendenteDebitos) {
        this.valorPendenteDebitos = valorPendenteDebitos;
    }

    public BigDecimal getValorPendenteCreditos() {
        return this.valorPendenteCreditos;
    }

    public void setValorPendenteCreditos(BigDecimal valorPendenteCreditos) {
        this.valorPendenteCreditos = valorPendenteCreditos;
    }

    public BigDecimal getValorPendenteImpostos() {
        return this.valorPendenteImpostos;
    }

    public void setValorPendenteImpostos(BigDecimal valorPendenteImpostos) {
        this.valorPendenteImpostos = valorPendenteImpostos;
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

    public GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(GRota gerRota) {
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

}

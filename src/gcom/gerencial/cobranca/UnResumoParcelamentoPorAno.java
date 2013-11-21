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
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.faturamento.GConsumoTarifa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoParcelamentoPorAno implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private int setorComercial;

    /** persistent field */
//    private int quadra;

    /** persistent field */
    private int quantidadeContas;

    /** persistent field */
    private BigDecimal valorContas;

    /** persistent field */
    private BigDecimal valorGuias;

    /** persistent field */
    private BigDecimal valorServicosIndiretos;

    /** persistent field */
    private BigDecimal valorCreditos;

    /** persistent field */
    private BigDecimal valorDescricaoAcrescimo;

    /** nullable persistent field */
    private Short valorQuantidadeServicosIndiretos;

    /** persistent field */
    private int quantidadeGuias;

    /** persistent field */
    private BigDecimal valorTotalParcelado;

    /** persistent field */
    private BigDecimal valorDescricaoInatividade;

    /** persistent field */
    private BigDecimal valorFinanciado;

    /** persistent field */
    private BigDecimal valorAcrescimoImpontualidade;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private short quantidadeParcelamentos;

    /** persistent field */
    //private BigDecimal repa_vlsancoes;

    /** persistent field */
    private BigDecimal valorDescricaoAntiguidade;

    /** persistent field */
    private gcom.gerencial.cadastro.imovel.GSubcategoria gerSubcategoria;

    /** persistent field */
    private gcom.gerencial.cadastro.cliente.GClienteTipo gerClienteTipo;

    /** persistent field */
    private gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
//    private gcom.gerencial.cadastro.localidade.GQuadra gerQuadra;

    /** persistent field */
    private gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GSetorComercial gerSetorComercial;

    /** persistent field */
    private gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private gcom.gerencial.cadastro.imovel.GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidadeElo;

    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidade;

    /** persistent field */
    private gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private gcom.gerencial.cadastro.cliente.GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private gcom.gerencial.cadastro.imovel.GCategoria gerCategoria;

    /** persistent field */
//    private gcom.gerencial.micromedicao.GRota gerRota;
    
    /** persistent field */
    private Integer quantidadeTotalParcelas;
    
    private Integer quantidadeMediaParcelas;
    
    /** persistent field */
    private BigDecimal valorJurosParcelamento;
    /** persistent field */
    private BigDecimal valorEntrada;
    /** persistent field */
    private BigDecimal valorDebitosCobrarParcelamentos;
    /** persistent field */
    private BigDecimal valorDebitosCobrarTotal;
    /** persistent field */
    private BigDecimal valorDebitosCobrarAcrescimos;
    /** persistent field */
    private BigDecimal valorDebitosCobrarReligadosSancoes;
    /** persistent field */
    private Short indicadorHidrometro;
    /** persistent field */
    private Integer quantidadeReparcelamentos;
    /** persistent field */
    private BigDecimal valorOutrosDescontos;
    /** persistent field */
    private GConsumoTarifa gerConsumoTarifa;
    
//    private Short codigoRota;
    
    
    /** full constructor */
    public UnResumoParcelamentoPorAno(int anoMesReferencia, 
    		int setorComercial, 
//    		int quadra, 
    		int quantidadeContas, 
    		BigDecimal valorContas, 
    		BigDecimal valorGuias,  
    		BigDecimal valorCreditos, 
    		BigDecimal valorDescricaoAcrescimo,  
    		int quantidadeGuias, 
    		BigDecimal valorDescricaoInatividade, 
    		BigDecimal valorAcrescimoImpontualidade, 
    		Date ultimaAlteracao, 
    		short quantidadeParcelamentos, 
    		BigDecimal valorDescricaoAntiguidade, 
    		GSubcategoria gerSubcategoria, 
    		GClienteTipo gerClienteTipo, 
    		GLigacaoAguaSituacao gerLigacaoAguaSituacao, 
//    		GQuadra gerQuadra, 
    		GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, 
    		GGerenciaRegional gerGerenciaRegional, 
    		GSetorComercial gerSetorComercial, 
    		GLigacaoAguaPerfil gerLigacaoAguaPerfil, 
    		GImovelPerfil gerImovelPerfil, 
    		GUnidadeNegocio gerUnidadeNegocio, 
    		GLocalidade gerLocalidadeElo, 
    		GLocalidade gerLocalidade, 
    		GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil, 
    		GEsferaPoder gerEsferaPoder, 
    		GCategoria gerCategoria, 
//    		GRota gerRota, 
    		BigDecimal valorJurosParcelamento, 
    		BigDecimal valorEntrada,
            BigDecimal valorDebitosCobrarParcelamentos, 
            BigDecimal valorDebitosCobrarTotal, 
            BigDecimal valorDebitosCobrarAcrescimos,
            BigDecimal valorDebitosCobrarReligadosSancoes, 
            Integer quantidadeTotalParcelas,
            Integer quantidadeMediaParcelas, 
            GConsumoTarifa gerConsumoTarifa, 
            Short indicadorHidrometro,
            Integer quantidadeReparcelamentos
//            Short codigoRota
            ) {
        this.anoMesReferencia = anoMesReferencia;
        this.setorComercial = setorComercial;
//        this.quadra = quadra;
        this.quantidadeContas = quantidadeContas;
        this.valorContas = valorContas;
        this.valorGuias = valorGuias;
        this.valorCreditos = valorCreditos;
        this.valorDescricaoAcrescimo = valorDescricaoAcrescimo;
        this.quantidadeGuias = quantidadeGuias;
        this.valorDescricaoInatividade = valorDescricaoInatividade;
        this.valorAcrescimoImpontualidade = valorAcrescimoImpontualidade;
        this.ultimaAlteracao = ultimaAlteracao;
        this.quantidadeParcelamentos = quantidadeParcelamentos;
        this.valorDescricaoAntiguidade = valorDescricaoAntiguidade;
        this.gerSubcategoria = gerSubcategoria;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
//        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerLocalidade = gerLocalidade;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerCategoria = gerCategoria;
//        this.gerRota = gerRota;

        this.valorJurosParcelamento = valorJurosParcelamento;
        this.valorEntrada = valorEntrada;
        this.valorDebitosCobrarParcelamentos = valorEntrada;
        this.valorDebitosCobrarTotal = valorDebitosCobrarTotal;
        this.valorDebitosCobrarAcrescimos = valorDebitosCobrarAcrescimos;
        this.valorDebitosCobrarReligadosSancoes = valorDebitosCobrarReligadosSancoes;    	
        this.quantidadeTotalParcelas = quantidadeTotalParcelas;
        this.quantidadeMediaParcelas = quantidadeMediaParcelas;  

        this.gerConsumoTarifa = gerConsumoTarifa;
        this.indicadorHidrometro = indicadorHidrometro;
        this.quantidadeReparcelamentos = quantidadeReparcelamentos;
//        this.codigoRota = codigoRota;
    }

    /** default constructor */
    public UnResumoParcelamentoPorAno() {
    }

    /** minimal constructor */
    public UnResumoParcelamentoPorAno(int anoMesReferencia, 
    		int setorComercial, 
//    		int quadra, 
    		int quantidadeContas, 
    		BigDecimal valorContas, 
    		BigDecimal valorGuias, 
    		BigDecimal valorServicosIndiretos, 
    		BigDecimal valorCreditos, 
    		BigDecimal valorDescricaoAcrescimo, 
    		int quantidadeGuias, 
    		BigDecimal valorTotalParcelado, 
    		BigDecimal valorDescricaoInatividade, 
    		BigDecimal valorFinanciado, 
    		BigDecimal valorAcrescimoImpontualidade, 
    		Date ultimaAlteracao, 
    		short quantidadeParcelamentos, 
    		BigDecimal valorSancoes, 
    		BigDecimal valorDescricaoAntiguidade, 
    		GSubcategoria gerSubcategoria, 
    		GClienteTipo gerClienteTipo, 
    		GLigacaoAguaSituacao gerLigacaoAguaSituacao, 
//    		GQuadra gerQuadra, 
    		GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, 
    		GGerenciaRegional gerGerenciaRegional, 
    		GSetorComercial gerSetorComercial, 
    		GLigacaoAguaPerfil gerLigacaoAguaPerfil, 
    		GImovelPerfil gerImovelPerfil, 
    		GUnidadeNegocio gerUnidadeNegocio, 
    		GLocalidade gerLocalidadeElo, 
    		GLocalidade gerLocalidadeByLocaId, 
    		GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil, 
    		GEsferaPoder gerEsferaPoder, 
    		GCategoria gerCategoria 
//    		GRota gerRota
    		) {
        this.anoMesReferencia = anoMesReferencia;
        this.setorComercial = setorComercial;
//        this.quadra = quadra;
        this.quantidadeContas = quantidadeContas;
        this.valorContas = valorContas;
        this.valorGuias = valorGuias;
        this.valorServicosIndiretos = valorServicosIndiretos;
        this.valorCreditos = valorCreditos;
        this.valorDescricaoAcrescimo = valorDescricaoAcrescimo;
        this.quantidadeGuias = quantidadeGuias;
        this.valorTotalParcelado = valorTotalParcelado;
        this.valorDescricaoInatividade = valorDescricaoInatividade;
        this.valorFinanciado = valorFinanciado;
        this.valorAcrescimoImpontualidade = valorAcrescimoImpontualidade;
        this.ultimaAlteracao = ultimaAlteracao;
        this.quantidadeParcelamentos = quantidadeParcelamentos;
        this.valorDescricaoAntiguidade = valorDescricaoAntiguidade;
        this.gerSubcategoria = gerSubcategoria;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
//        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerLocalidade = gerLocalidadeByLocaId;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerCategoria = gerCategoria;
//        this.gerRota = gerRota;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

//    public Short getCodigoRota() {
//		return codigoRota;
//	}
//
//	public void setCodigoRota(Short codigoRota) {
//		this.codigoRota = codigoRota;
//	}

	public int getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public gcom.gerencial.cadastro.imovel.GCategoria getGerCategoria() {
		return gerCategoria;
	}

	public void setGerCategoria(
			gcom.gerencial.cadastro.imovel.GCategoria gerCategoria) {
		this.gerCategoria = gerCategoria;
	}

	public gcom.gerencial.cadastro.cliente.GClienteTipo getGerClienteTipo() {
		return gerClienteTipo;
	}

	public void setGerClienteTipo(
			gcom.gerencial.cadastro.cliente.GClienteTipo gerClienteTipo) {
		this.gerClienteTipo = gerClienteTipo;
	}

	public gcom.gerencial.cadastro.cliente.GEsferaPoder getGerEsferaPoder() {
		return gerEsferaPoder;
	}

	public void setGerEsferaPoder(
			gcom.gerencial.cadastro.cliente.GEsferaPoder gerEsferaPoder) {
		this.gerEsferaPoder = gerEsferaPoder;
	}

	public gcom.gerencial.cadastro.localidade.GGerenciaRegional getGerGerenciaRegional() {
		return gerGerenciaRegional;
	}

	public void setGerGerenciaRegional(
			gcom.gerencial.cadastro.localidade.GGerenciaRegional gerGerenciaRegional) {
		this.gerGerenciaRegional = gerGerenciaRegional;
	}

	public gcom.gerencial.cadastro.imovel.GImovelPerfil getGerImovelPerfil() {
		return gerImovelPerfil;
	}

	public void setGerImovelPerfil(
			gcom.gerencial.cadastro.imovel.GImovelPerfil gerImovelPerfil) {
		this.gerImovelPerfil = gerImovelPerfil;
	}

	public gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
		return gerLigacaoAguaPerfil;
	}

	public void setGerLigacaoAguaPerfil(
			gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
		this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
	}

	public gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
		return gerLigacaoAguaSituacao;
	}

	public void setGerLigacaoAguaSituacao(
			gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
		this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
	}

	public gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
		return gerLigacaoEsgotoPerfil;
	}

	public void setGerLigacaoEsgotoPerfil(
			gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
		this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
	}

	public gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
		return gerLigacaoEsgotoSituacao;
	}

	public void setGerLigacaoEsgotoSituacao(
			gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
		this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
	}

	public gcom.gerencial.cadastro.localidade.GLocalidade getGerLocalidade() {
		return gerLocalidade;
	}

	public void setGerLocalidade(
			gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidade) {
		this.gerLocalidade = gerLocalidade;
	}

	public gcom.gerencial.cadastro.localidade.GLocalidade getGerLocalidadeElo() {
		return gerLocalidadeElo;
	}

	public void setGerLocalidadeElo(
			gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidadeElo) {
		this.gerLocalidadeElo = gerLocalidadeElo;
	}

//	public gcom.gerencial.cadastro.localidade.GQuadra getGerQuadra() {
//		return gerQuadra;
//	}
//
//	public void setGerQuadra(gcom.gerencial.cadastro.localidade.GQuadra gerQuadra) {
//		this.gerQuadra = gerQuadra;
//	}
//
//	public gcom.gerencial.micromedicao.GRota getGerRota() {
//		return gerRota;
//	}
//
//	public void setGerRota(gcom.gerencial.micromedicao.GRota gerRota) {
//		this.gerRota = gerRota;
//	}

	public gcom.gerencial.cadastro.localidade.GSetorComercial getGerSetorComercial() {
		return gerSetorComercial;
	}

	public void setGerSetorComercial(
			gcom.gerencial.cadastro.localidade.GSetorComercial gerSetorComercial) {
		this.gerSetorComercial = gerSetorComercial;
	}

	public gcom.gerencial.cadastro.imovel.GSubcategoria getGerSubcategoria() {
		return gerSubcategoria;
	}

	public void setGerSubcategoria(
			gcom.gerencial.cadastro.imovel.GSubcategoria gerSubcategoria) {
		this.gerSubcategoria = gerSubcategoria;
	}

	public gcom.gerencial.cadastro.localidade.GUnidadeNegocio getGerUnidadeNegocio() {
		return gerUnidadeNegocio;
	}

	public void setGerUnidadeNegocio(
			gcom.gerencial.cadastro.localidade.GUnidadeNegocio gerUnidadeNegocio) {
		this.gerUnidadeNegocio = gerUnidadeNegocio;
	}

//	public int getQuadra() {
//		return quadra;
//	}
//
//	public void setQuadra(int quadra) {
//		this.quadra = quadra;
//	}

	public int getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(int quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public int getQuantidadeGuias() {
		return quantidadeGuias;
	}

	public void setQuantidadeGuias(int quantidadeGuias) {
		this.quantidadeGuias = quantidadeGuias;
	}

	public short getQuantidadeParcelamentos() {
		return quantidadeParcelamentos;
	}

	public void setQuantidadeParcelamentos(short quantidadeParcelamentos) {
		this.quantidadeParcelamentos = quantidadeParcelamentos;
	}

	public int getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(int setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorAcrescimoImpontualidade() {
		return valorAcrescimoImpontualidade;
	}

	public void setValorAcrescimoImpontualidade(
			BigDecimal valorAcrescimoImpontualidade) {
		this.valorAcrescimoImpontualidade = valorAcrescimoImpontualidade;
	}

	public BigDecimal getValorContas() {
		return valorContas;
	}

	public void setValorContas(BigDecimal valorContas) {
		this.valorContas = valorContas;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorDescricaoAcrescimo() {
		return valorDescricaoAcrescimo;
	}

	public void setValorDescricaoAcrescimo(BigDecimal valorDescricaoAcrescimo) {
		this.valorDescricaoAcrescimo = valorDescricaoAcrescimo;
	}

	public BigDecimal getValorDescricaoAntiguidade() {
		return valorDescricaoAntiguidade;
	}

	public void setValorDescricaoAntiguidade(BigDecimal valorDescricaoAntiguidade) {
		this.valorDescricaoAntiguidade = valorDescricaoAntiguidade;
	}

	public BigDecimal getValorDescricaoInatividade() {
		return valorDescricaoInatividade;
	}

	public void setValorDescricaoInatividade(BigDecimal valorDescricaoInatividade) {
		this.valorDescricaoInatividade = valorDescricaoInatividade;
	}

	public BigDecimal getValorFinanciado() {
		return valorFinanciado;
	}

	public void setValorFinanciado(BigDecimal valorFinanciado) {
		this.valorFinanciado = valorFinanciado;
	}

	public BigDecimal getValorGuias() {
		return valorGuias;
	}

	public void setValorGuias(BigDecimal valorGuias) {
		this.valorGuias = valorGuias;
	}

	public Short getValorQuantidadeServicosIndiretos() {
		return valorQuantidadeServicosIndiretos;
	}

	public void setValorQuantidadeServicosIndiretos(
			Short valorQuantidadeServicosIndiretos) {
		this.valorQuantidadeServicosIndiretos = valorQuantidadeServicosIndiretos;
	}

	public BigDecimal getValorServicosIndiretos() {
		return valorServicosIndiretos;
	}

	public void setValorServicosIndiretos(BigDecimal valorServicosIndiretos) {
		this.valorServicosIndiretos = valorServicosIndiretos;
	}

	public BigDecimal getValorTotalParcelado() {
		return valorTotalParcelado;
	}

	public void setValorTotalParcelado(BigDecimal valorTotalParcelado) {
		this.valorTotalParcelado = valorTotalParcelado;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getQuantidadeMediaParcelas() {
		return quantidadeMediaParcelas;
	}

	public void setQuantidadeMediaParcelas(Integer quantidadeMediaParcelas) {
		this.quantidadeMediaParcelas = quantidadeMediaParcelas;
	}

	public Integer getQuantidadeTotalParcelas() {
		return quantidadeTotalParcelas;
	}

	public void setQuantidadeTotalParcelas(Integer quantidadeTotalParcelas) {
		this.quantidadeTotalParcelas = quantidadeTotalParcelas;
	}

	public BigDecimal getValorDebitosCobrarAcrescimos() {
		return valorDebitosCobrarAcrescimos;
	}

	public void setValorDebitosCobrarAcrescimos(
			BigDecimal valorDebitosCobrarAcrescimos) {
		this.valorDebitosCobrarAcrescimos = valorDebitosCobrarAcrescimos;
	}

	public BigDecimal getValorDebitosCobrarParcelamentos() {
		return valorDebitosCobrarParcelamentos;
	}

	public void setValorDebitosCobrarParcelamentos(
			BigDecimal valorDebitosCobrarParcelamentos) {
		this.valorDebitosCobrarParcelamentos = valorDebitosCobrarParcelamentos;
	}

	public BigDecimal getValorDebitosCobrarReligadosSancoes() {
		return valorDebitosCobrarReligadosSancoes;
	}

	public void setValorDebitosCobrarReligadosSancoes(
			BigDecimal valorDebitosCobrarReligadosSancoes) {
		this.valorDebitosCobrarReligadosSancoes = valorDebitosCobrarReligadosSancoes;
	}

	public BigDecimal getValorDebitosCobrarTotal() {
		return valorDebitosCobrarTotal;
	}

	public void setValorDebitosCobrarTotal(BigDecimal valorDebitosCobrarTotal) {
		this.valorDebitosCobrarTotal = valorDebitosCobrarTotal;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorJurosParcelamento() {
		return valorJurosParcelamento;
	}

	public void setValorJurosParcelamento(BigDecimal valorJurosParcelamento) {
		this.valorJurosParcelamento = valorJurosParcelamento;
	}

	public GConsumoTarifa getGerConsumoTarifa() {
		return gerConsumoTarifa;
	}

	public void setGerConsumoTarifa(GConsumoTarifa gerConsumoTarifa) {
		this.gerConsumoTarifa = gerConsumoTarifa;
	}

	public Short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(Short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public Integer getQuantidadeReparcelamentos() {
		return quantidadeReparcelamentos;
	}

	public void setQuantidadeReparcelamentos(Integer quantidadeReparcelamentos) {
		this.quantidadeReparcelamentos = quantidadeReparcelamentos;
	}

	public BigDecimal getValorOutrosDescontos() {
		return valorOutrosDescontos;
	}

	public void setValorOutrosDescontos(BigDecimal valorOutrosDescontos) {
		this.valorOutrosDescontos = valorOutrosDescontos;
	}

}

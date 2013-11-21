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
package gcom.gerencial.arrecadacao;

import gcom.gerencial.arrecadacao.pagamento.GEpocaPagamento;
import gcom.gerencial.arrecadacao.pagamento.GPagamentoSituacao;
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
import gcom.gerencial.cobranca.GDocumentoTipo;
import gcom.gerencial.faturamento.credito.GCreditoOrigem;
import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import gcom.gerencial.micromedicao.GRota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoArrecadacaoAguaEsgoto implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int referencia;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeContas;

    /** persistent field */
    private short indicadorRecebidasNomes;

    /** persistent field */
    private BigDecimal volumeAgua;

    /** persistent field */
    private BigDecimal volumeEgoto;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private BigDecimal volumeNaoIdentificado;

    /** persistent field */
    private GSubcategoria gersubCategoria;

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
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GDocumentoTipo gerDocumentoTipo;

    /** persistent field */
    private GPagamentoSituacao gerPagamentoSituacao;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEpocaPagamento gerEpocaPagamento;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GRota gerRota;

    /** persistent field */
    private Set unResumoArrecadacaoCreditos;
    
    /** persistent field */
    private Set unResumoArrecadacaoOutros;

    /** persistent field */
    private BigDecimal valorDocumentosRecebidosCredito;
    
    /** persistent field */
    private BigDecimal valorDocumentosRecebidosOutros;
    
    /** persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabilCredito;
    
    /** persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabilOutros;
    
    /** persistent field */
    private GFinanciamentoTipo gerFinanciamentoTipoOutros;
    
    /** persistent field */
    private GCreditoOrigem gerCreditoOrigem;
    
    /** persistent field */
    private GArrecadacaoForma gerArrecadacaoForma;

    /** persistent field */
    private GArrecadador gerArrecadador;
    

    /** full constructor */
    public UnResumoArrecadacaoAguaEsgoto(int referencia, int codigoSetorComercial, int numeroQuadra, int quantidadeContas, short indicadorRecebidasNomes, BigDecimal volumeAgua, BigDecimal volumeEgoto, Date ultimaAlteracao, BigDecimal volumeNaoIdentificado, GSubcategoria gsubCategoria, GClienteTipo gClienteTipo, GLigacaoAguaSituacao gLigacaoAguaSituacao, GUnidadeNegocio gUnidadeNegocio, GLocalidade gLocalidade, GLocalidade gLocalidadeElo, GQuadra gQuadra, GLigacaoEsgotoSituacao gLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gLigacaoEsgotoPerfil, GGerenciaRegional gGerenciaRegional, GSetorComercial gSetorComercial, GDocumentoTipo gDocumentoTipo, GPagamentoSituacao gPagamentoSituacao, GLigacaoAguaPerfil gLigacaoAguaPerfil, GEpocaPagamento gEpocaPagamento, GEsferaPoder gEsferaPoder, GCategoria gCategoria, GImovelPerfil gImovelPerfil, GRota gRota, Set unResumoArrecadacaoCreditos, Set unResumoArrecadacaoOutros) {
        this.referencia = referencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeContas = quantidadeContas;
        this.indicadorRecebidasNomes = indicadorRecebidasNomes;
        this.volumeAgua = volumeAgua;
        this.volumeEgoto = volumeEgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.volumeNaoIdentificado = volumeNaoIdentificado;
        this.gersubCategoria = gsubCategoria;
        this.gerClienteTipo = gClienteTipo;
        this.gerLigacaoAguaSituacao = gLigacaoAguaSituacao;
        this.gerUnidadeNegocio = gUnidadeNegocio;
        this.gerLocalidade = gLocalidade;
        this.gerLocalidadeElo = gLocalidadeElo;
        this.gerQuadra = gQuadra;
        this.gerLigacaoEsgotoSituacao = gLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gLigacaoEsgotoPerfil;
        this.gerGerenciaRegional = gGerenciaRegional;
        this.gerSetorComercial = gSetorComercial;
        this.gerDocumentoTipo = gDocumentoTipo;
        this.gerPagamentoSituacao = gPagamentoSituacao;
        this.gerLigacaoAguaPerfil = gLigacaoAguaPerfil;
        this.gerEpocaPagamento = gEpocaPagamento;
        this.gerEsferaPoder = gEsferaPoder;
        this.gerCategoria = gCategoria;
        this.gerImovelPerfil = gImovelPerfil;
        this.gerRota = gRota;
        this.unResumoArrecadacaoCreditos = unResumoArrecadacaoCreditos;
        this.unResumoArrecadacaoOutros = unResumoArrecadacaoOutros;
    }

    /** default constructor */
    public UnResumoArrecadacaoAguaEsgoto() {
    }

    
    public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public GCategoria getGerCategoria() {
		return gerCategoria;
	}

	public void setGerCategoria(GCategoria gerCategoria) {
		this.gerCategoria = gerCategoria;
	}

	public GClienteTipo getGerClienteTipo() {
		return gerClienteTipo;
	}

	public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
		this.gerClienteTipo = gerClienteTipo;
	}

	public GDocumentoTipo getGerDocumentoTipo() {
		return gerDocumentoTipo;
	}

	public void setGerDocumentoTipo(GDocumentoTipo gerDocumentoTipo) {
		this.gerDocumentoTipo = gerDocumentoTipo;
	}

	public GEpocaPagamento getGerEpocaPagamento() {
		return gerEpocaPagamento;
	}

	public void setGerEpocaPagamento(
			GEpocaPagamento gerEpocaPagamento) {
		this.gerEpocaPagamento = gerEpocaPagamento;
	}

	public GEsferaPoder getGerEsferaPoder() {
		return gerEsferaPoder;
	}

	public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
		this.gerEsferaPoder = gerEsferaPoder;
	}

	public GGerenciaRegional getGerGerenciaRegional() {
		return gerGerenciaRegional;
	}

	public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
		this.gerGerenciaRegional = gerGerenciaRegional;
	}

	public GImovelPerfil getGerImovelPerfil() {
		return gerImovelPerfil;
	}

	public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
		this.gerImovelPerfil = gerImovelPerfil;
	}

	public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
		return gerLigacaoAguaPerfil;
	}

	public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
		this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
	}

	public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
		return gerLigacaoAguaSituacao;
	}

	public void setGerLigacaoAguaSituacao(
			GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
		this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
	}

	public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
		return gerLigacaoEsgotoPerfil;
	}

	public void setGerLigacaoEsgotoPerfil(
			GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
		this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
	}

	public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
		return gerLigacaoEsgotoSituacao;
	}

	public void setGerLigacaoEsgotoSituacao(
			GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
		this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
	}

	public GLocalidade getGerLocalidade() {
		return gerLocalidade;
	}

	public void setGerLocalidade(GLocalidade gerLocalidade) {
		this.gerLocalidade = gerLocalidade;
	}

	public GLocalidade getGerLocalidadeElo() {
		return gerLocalidadeElo;
	}

	public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
		this.gerLocalidadeElo = gerLocalidadeElo;
	}

	public GPagamentoSituacao getGerPagamentoSituacao() {
		return gerPagamentoSituacao;
	}

	public void setGerPagamentoSituacao(GPagamentoSituacao gerPagamentoSituacao) {
		this.gerPagamentoSituacao = gerPagamentoSituacao;
	}

	public GQuadra getGerQuadra() {
		return gerQuadra;
	}

	public void setGerQuadra(GQuadra gerQuadra) {
		this.gerQuadra = gerQuadra;
	}

	public GRota getGerRota() {
		return gerRota;
	}

	public void setGerRota(GRota gerRota) {
		this.gerRota = gerRota;
	}

	public GSetorComercial getGerSetorComercial() {
		return gerSetorComercial;
	}

	public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
		this.gerSetorComercial = gerSetorComercial;
	}

	public GSubcategoria getGersubCategoria() {
		return gersubCategoria;
	}

	public void setGersubCategoria(GSubcategoria gersubCategoria) {
		this.gersubCategoria = gersubCategoria;
	}

	public GUnidadeNegocio getGerUnidadeNegocio() {
		return gerUnidadeNegocio;
	}

	public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
		this.gerUnidadeNegocio = gerUnidadeNegocio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorRecebidasNomes() {
		return indicadorRecebidasNomes;
	}

	public void setIndicadorRecebidasNomes(short indicadorRecebidasNomes) {
		this.indicadorRecebidasNomes = indicadorRecebidasNomes;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public int getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(int quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Set getUnResumoArrecadacaoCreditos() {
		return unResumoArrecadacaoCreditos;
	}

	public void setUnResumoArrecadacaoCreditos(Set unResumoArrecadacaoCreditos) {
		this.unResumoArrecadacaoCreditos = unResumoArrecadacaoCreditos;
	}

	public Set getUnResumoArrecadacaoOutros() {
		return unResumoArrecadacaoOutros;
	}

	public void setUnResumoArrecadacaoOutros(Set unResumoArrecadacaoOutros) {
		this.unResumoArrecadacaoOutros = unResumoArrecadacaoOutros;
	}

	public BigDecimal getVolumeAgua() {
		return volumeAgua;
	}

	public void setVolumeAgua(BigDecimal volumeAgua) {
		this.volumeAgua = volumeAgua;
	}

	public BigDecimal getVolumeEgoto() {
		return volumeEgoto;
	}

	public void setVolumeEgoto(BigDecimal volumeEgoto) {
		this.volumeEgoto = volumeEgoto;
	}

	public BigDecimal getVolumeNaoIdentificado() {
		return volumeNaoIdentificado;
	}

	public void setVolumeNaoIdentificado(BigDecimal volumeNaoIdentificado) {
		this.volumeNaoIdentificado = volumeNaoIdentificado;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public GCreditoOrigem getGerCreditoOrigem() {
		return gerCreditoOrigem;
	}

	public void setGerCreditoOrigem(GCreditoOrigem gerCreditoOrigem) {
		this.gerCreditoOrigem = gerCreditoOrigem;
	}

	public GFinanciamentoTipo getGerFinanciamentoTipoOutros() {
		return gerFinanciamentoTipoOutros;
	}

	public void setGerFinanciamentoTipoOutros(
			GFinanciamentoTipo gerFinanciamentoTipoOutros) {
		this.gerFinanciamentoTipoOutros = gerFinanciamentoTipoOutros;
	}

	public GLancamentoItemContabil getGerLancamentoItemContabilCredito() {
		return gerLancamentoItemContabilCredito;
	}

	public void setGerLancamentoItemContabilCredito(
			GLancamentoItemContabil gerLancamentoItemContabilCredito) {
		this.gerLancamentoItemContabilCredito = gerLancamentoItemContabilCredito;
	}

	public GLancamentoItemContabil getGerLancamentoItemContabilOutros() {
		return gerLancamentoItemContabilOutros;
	}

	public void setGerLancamentoItemContabilOutros(
			GLancamentoItemContabil gerLancamentoItemContabilOutros) {
		this.gerLancamentoItemContabilOutros = gerLancamentoItemContabilOutros;
	}

	public BigDecimal getValorDocumentosRecebidosCredito() {
		return valorDocumentosRecebidosCredito;
	}

	public void setValorDocumentosRecebidosCredito(
			BigDecimal valorDocumentosRecebidosCredito) {
		this.valorDocumentosRecebidosCredito = valorDocumentosRecebidosCredito;
	}

	public BigDecimal getValorDocumentosRecebidosOutros() {
		return valorDocumentosRecebidosOutros;
	}

	public void setValorDocumentosRecebidosOutros(
			BigDecimal valorDocumentosRecebidosOutros) {
		this.valorDocumentosRecebidosOutros = valorDocumentosRecebidosOutros;
	}

	public GArrecadacaoForma getGerArrecadacaoForma() {
		return gerArrecadacaoForma;
	}

	public void setGerArrecadacaoForma(GArrecadacaoForma gerArrecadacaoForma) {
		this.gerArrecadacaoForma = gerArrecadacaoForma;
	}

	public GArrecadador getGerArrecadador() {
		return gerArrecadador;
	}

	public void setGerArrecadador(GArrecadador gerArrecadador) {
		this.gerArrecadador = gerArrecadador;
	}


	}



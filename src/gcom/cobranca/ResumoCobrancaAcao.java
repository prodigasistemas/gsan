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
package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.micromedicao.Rota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ResumoCobrancaAcao implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	public final static short INDICADOR_ANTES = 1;

	public final static short INDICADOR_APOS = 2;
	
	public final static short INDICADOR_ACIMA = 1;

	public final static short INDICADOR_ABAIXO = 2;	
	
	public final static Integer INIDCADOR_DEFINITIVO = 1;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private Date realizacaoEmitir;

    /** persistent field */
    private Date realizacaoEncerrar;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private short indicadorCronogramaComando;

    /** nullable persistent field */
    private Short indicadorAntesApos;

    /** persistent field */
    private Short indicadorLimite;

    /** persistent field */
    private int quantidadeDocumentos;

    /** persistent field */
    private BigDecimal valorDocumentos;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Integer indicadorDefinitivo;

    /** persistent field */
    private Rota rota;

    /** persistent field */
    private CobrancaDebitoSituacao cobrancaDebitoSituacao;

    /** persistent field */
    private SetorComercial setorComercial;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private EsferaPoder esferaPoder;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private CobrancaGrupo cobrancaGrupo;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private CobrancaAcaoCronograma cobrancaAcaoCronograma;

    /** persistent field */
    private CobrancaAcao cobrancaAcao;

    /** persistent field */
    private CobrancaAcaoSituacao cobrancaAcaoSituacao;

    /** persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;
    
    /** persistent field */
    private CobrancaCriterio cobrancaCriterio;
    
    /** persistent field */
    private Empresa empresa;
    
    private AtendimentoMotivoEncerramento motivoEncerramento;

    
    private UnidadeNegocio unidadeNegocio;
    
    private DocumentoEmissaoForma documentoEmissaoForma;
    
    //**************************************************
    // RM3323
    // Autor: Ivan Sergio
    // Data: 10/12/2010
    // Adicionado o Tipo de Corte
    //**************************************************
    private CorteTipo corteTipo;
    //**************************************************

    public CorteTipo getCorteTipo() {
		return corteTipo;
	}


	public void setCorteTipo(CorteTipo corteTipo) {
		this.corteTipo = corteTipo;
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


	public AtendimentoMotivoEncerramento getMotivoEncerramento() {
		return motivoEncerramento;
	}


	public void setMotivoEncerramento(
			AtendimentoMotivoEncerramento motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}


	public String toString() {
        return new ToStringBuilder(this)
            .append("rcbaId", getId())
            .toString();
    }


	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public int getAnoMesReferencia() {
		return anoMesReferencia;
	}


	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}


	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}


	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	/**
	 * @return Retorna o campo cobrancaAcao.
	 */
	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}


	/**
	 * @param cobrancaAcao O cobrancaAcao a ser setado.
	 */
	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}


	/**
	 * @return Retorna o campo cobrancaAcaoCronograma.
	 */
	public CobrancaAcaoCronograma getCobrancaAcaoCronograma() {
		return cobrancaAcaoCronograma;
	}


	/**
	 * @param cobrancaAcaoCronograma O cobrancaAcaoCronograma a ser setado.
	 */
	public void setCobrancaAcaoCronograma(
			CobrancaAcaoCronograma cobrancaAcaoCronograma) {
		this.cobrancaAcaoCronograma = cobrancaAcaoCronograma;
	}


	/**
	 * @return Retorna o campo cobrancaAcaoSituacao.
	 */
	public CobrancaAcaoSituacao getCobrancaAcaoSituacao() {
		return cobrancaAcaoSituacao;
	}


	/**
	 * @param cobrancaAcaoSituacao O cobrancaAcaoSituacao a ser setado.
	 */
	public void setCobrancaAcaoSituacao(CobrancaAcaoSituacao cobrancaAcaoSituacao) {
		this.cobrancaAcaoSituacao = cobrancaAcaoSituacao;
	}


	/**
	 * @return Retorna o campo cobrancaDebitoSituacao.
	 */
	public CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}


	/**
	 * @param cobrancaDebitoSituacao O cobrancaDebitoSituacao a ser setado.
	 */
	public void setCobrancaDebitoSituacao(
			CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}


	/**
	 * @return Retorna o campo cobrancaGrupo.
	 */
	public CobrancaGrupo getCobrancaGrupo() {
		return cobrancaGrupo;
	}


	/**
	 * @param cobrancaGrupo O cobrancaGrupo a ser setado.
	 */
	public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}


	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}


	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}


	/**
	 * @return Retorna o campo esferaPoder.
	 */
	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}


	/**
	 * @param esferaPoder O esferaPoder a ser setado.
	 */
	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}


	/**
	 * @return Retorna o campo fiscalizacaoSituacao.
	 */
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}


	/**
	 * @param fiscalizacaoSituacao O fiscalizacaoSituacao a ser setado.
	 */
	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}


	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}


	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}


	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return Retorna o campo imovelPerfil.
	 */
	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}


	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}


	/**
	 * @return Retorna o campo indicadorAntesApos.
	 */
	public Short getIndicadorAntesApos() {
		return indicadorAntesApos;
	}


	/**
	 * @param indicadorAntesApos O indicadorAntesApos a ser setado.
	 */
	public void setIndicadorAntesApos(Short indicadorAntesApos) {
		this.indicadorAntesApos = indicadorAntesApos;
	}


	/**
	 * @return Retorna o campo indicadorCronogramaComando.
	 */
	public short getIndicadorCronogramaComando() {
		return indicadorCronogramaComando;
	}


	/**
	 * @param indicadorCronogramaComando O indicadorCronogramaComando a ser setado.
	 */
	public void setIndicadorCronogramaComando(short indicadorCronogramaComando) {
		this.indicadorCronogramaComando = indicadorCronogramaComando;
	}


	/**
	 * @return Retorna o campo indicadorLimite.
	 */
	public Short getIndicadorLimite() {
		return indicadorLimite;
	}


	/**
	 * @param indicadorLimite O indicadorLimite a ser setado.
	 */
	public void setIndicadorLimite(Short indicadorLimite) {
		this.indicadorLimite = indicadorLimite;
	}


	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}


	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}


	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}


	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}


	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}


	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}


	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public int getNumeroQuadra() {
		return numeroQuadra;
	}


	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}


	/**
	 * @return Retorna o campo quadra.
	 */
	public Quadra getQuadra() {
		return quadra;
	}


	/**
	 * @param quadra O quadra a ser setado.
	 */
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}


	/**
	 * @return Retorna o campo quantidadeDocumentos.
	 */
	public int getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}


	/**
	 * @param quantidadeDocumentos O quantidadeDocumentos a ser setado.
	 */
	public void setQuantidadeDocumentos(int quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}


	/**
	 * @return Retorna o campo realizacaoEmitir.
	 */
	public Date getRealizacaoEmitir() {
		return realizacaoEmitir;
	}


	/**
	 * @param realizacaoEmitir O realizacaoEmitir a ser setado.
	 */
	public void setRealizacaoEmitir(Date realizacaoEmitir) {
		this.realizacaoEmitir = realizacaoEmitir;
	}


	/**
	 * @return Retorna o campo realizacaoEncerrar.
	 */
	public Date getRealizacaoEncerrar() {
		return realizacaoEncerrar;
	}


	/**
	 * @param realizacaoEncerrar O realizacaoEncerrar a ser setado.
	 */
	public void setRealizacaoEncerrar(Date realizacaoEncerrar) {
		this.realizacaoEncerrar = realizacaoEncerrar;
	}


	/**
	 * @return Retorna o campo rota.
	 */
	public Rota getRota() {
		return rota;
	}


	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(Rota rota) {
		this.rota = rota;
	}


	/**
	 * @return Retorna o campo setorComercial.
	 */
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}


	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}


	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	/**
	 * @return Retorna o campo valorDocumentos.
	 */
	public BigDecimal getValorDocumentos() {
		return valorDocumentos;
	}


	/**
	 * @param valorDocumentos O valorDocumentos a ser setado.
	 */
	public void setValorDocumentos(BigDecimal valorDocumentos) {
		this.valorDocumentos = valorDocumentos;
	}


    public CobrancaCriterio getCobrancaCriterio() {
        return cobrancaCriterio;
    }


    public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
        this.cobrancaCriterio = cobrancaCriterio;
    }
    
    /**
     * Description of the Method
     * 
     * @param other
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ResumoCobrancaAcao)) {
            return false;
        }
        ResumoCobrancaAcao castOther = (ResumoCobrancaAcao) other;

        return new EqualsBuilder().append(this.getAnoMesReferencia(),
                castOther.getAnoMesReferencia()).append(
                this.getCobrancaAcaoCronograma(), castOther.getCobrancaAcaoCronograma())
               .isEquals();
    }


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public Integer getIndicadorDefinitivo() {
		return indicadorDefinitivo;
	}


	public void setIndicadorDefinitivo(Integer indicadorDefinitivo) {
		this.indicadorDefinitivo = indicadorDefinitivo;
	}


	public DocumentoEmissaoForma getDocumentoEmissaoForma() {
		return documentoEmissaoForma;
	}


	public void setDocumentoEmissaoForma(DocumentoEmissaoForma documentoEmissaoForma) {
		this.documentoEmissaoForma = documentoEmissaoForma;
	}
}

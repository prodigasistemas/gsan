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

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ResumoCobrancaAcaoEventual implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static short INDICADOR_ANTES = 1;

	public final static short INDICADOR_APOS = 2;

	public final static short INDICADOR_ACIMA = 1;

	public final static short INDICADOR_ABAIXO = 2;

	public final static Integer INIDCADOR_DEFINITIVO = 1;

	/** identifier field */
	private Integer Id;

	/** persistent field */
	private Date tempoRealizacaoEmitir;

	/** persistent field */
	private Date tempoRealizacaoEncerrar;

	/** persistent field */
	private int codigoSetorcomercial;

	/** persistent field */
	private int numeroQuadra;

	/** nullable persistent field */
	private Short indicadorAntesApos;

	/** nullable persistent field */
	private Short indicadorAcimaLimite;

	/** persistent field */
	private int quantidadeDocumentos;

	/** persistent field */
	private BigDecimal valorDocumentos;

	/** persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Short indicadorDefinitivo;

	/** persistent field */
	private Quadra quadra;

	/** persistent field */
	private GerenciaRegional gerenciaRegional;

	/** persistent field */
	private Localidade localidade;

	/** persistent field */
	private ImovelPerfil imovelPerfil;

	/** persistent field */
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

	/** persistent field */
	private Rota rota;

	/** persistent field */
	private SetorComercial setorComercial;

	/** persistent field */
	private gcom.cobranca.CobrancaGrupo cobrancaGrupo;

	/** persistent field */
	private LigacaoAguaSituacao ligacaoAguaSituacao;

	/** persistent field */
	private EsferaPoder esferaPoder;

	/** persistent field */
	private Empresa empresa;

	/** persistent field */
	private Categoria categoria;

	/** persistent field */
	private gcom.cobranca.CobrancaAcaoSituacao cobrancaAcaoSituacao;

	/** persistent field */
	private gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao;

	/** persistent field */
	private gcom.cobranca.CobrancaCriterio cobrancaCriterio;

	/** persistent field */
	private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;

	/** persistent field */
	private gcom.cobranca.CobrancaAcao cobrancaAcao;

	/** persistent field */
	private FiscalizacaoSituacao fiscalizacaoSituacao;

    private AtendimentoMotivoEncerramento motivoEncerramento;
    
    private UnidadeNegocio unidadeNegocio;

	
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

	/** full constructor */
	public ResumoCobrancaAcaoEventual(Integer Id, Date tempoRealizacaoEmitir,
			Date tempoRealizacaoEncerrar, int codigoSetorcomercial,
			int numeroQuadra, Short indicadorAntesApos,
			Short indicadorAcimaLimite, int quantidadeDocumentos,
			BigDecimal valorDocumentos, Date ultimaAlteracao,
			Short indicadorDefinitivo, Quadra quadra,
			GerenciaRegional gerenciaRegional, Localidade localidade,
			ImovelPerfil imovelPerfil,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Rota rota,
			SetorComercial setorComercial,
			gcom.cobranca.CobrancaGrupo cobrancaGrupo,
			LigacaoAguaSituacao ligacaoAguaSituacao, EsferaPoder esferaPoder,
			Empresa empresa, Categoria categoria,
			gcom.cobranca.CobrancaAcaoSituacao cobrancaAcaoSituacao,
			gcom.cobranca.CobrancaCriterio cobrancaCriterio,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			gcom.cobranca.CobrancaAcao cobrancaAcao,
			FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.Id = Id;
		this.tempoRealizacaoEmitir = tempoRealizacaoEmitir;
		this.tempoRealizacaoEncerrar = tempoRealizacaoEncerrar;
		this.codigoSetorcomercial = codigoSetorcomercial;
		this.numeroQuadra = numeroQuadra;
		this.indicadorAntesApos = indicadorAntesApos;
		this.indicadorAcimaLimite = indicadorAcimaLimite;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.valorDocumentos = valorDocumentos;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorDefinitivo = indicadorDefinitivo;
		this.quadra = quadra;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.rota = rota;
		this.setorComercial = setorComercial;
		this.cobrancaGrupo = cobrancaGrupo;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.esferaPoder = esferaPoder;
		this.empresa = empresa;
		this.categoria = categoria;
		this.cobrancaAcaoSituacao = cobrancaAcaoSituacao;
		this.cobrancaCriterio = cobrancaCriterio;
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
		this.cobrancaAcao = cobrancaAcao;
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	/** default constructor */
	public ResumoCobrancaAcaoEventual() {
	}

	/** minimal constructor */
	public ResumoCobrancaAcaoEventual(Integer Id, Date tempoRealizacaoEmitir,
			Date tempoRealizacaoEncerrar, int codigoSetorcomercial,
			int numeroQuadra, int quantidadeDocumentos,
			BigDecimal valorDocumentos, Date ultimaAlteracao, Quadra quadra,
			GerenciaRegional gerenciaRegional, Localidade localidade,
			ImovelPerfil imovelPerfil,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Rota rota,
			SetorComercial setorComercial,
			gcom.cobranca.CobrancaGrupo cobrancaGrupo,
			LigacaoAguaSituacao ligacaoAguaSituacao, EsferaPoder esferaPoder,
			Empresa empresa, Categoria categoria,
			gcom.cobranca.CobrancaAcaoSituacao cobrancaAcaoSituacao,
			gcom.cobranca.CobrancaCriterio cobrancaCriterio,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			gcom.cobranca.CobrancaAcao cobrancaAcao,
			FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.Id = Id;
		this.tempoRealizacaoEmitir = tempoRealizacaoEmitir;
		this.tempoRealizacaoEncerrar = tempoRealizacaoEncerrar;
		this.codigoSetorcomercial = codigoSetorcomercial;
		this.numeroQuadra = numeroQuadra;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.valorDocumentos = valorDocumentos;
		this.ultimaAlteracao = ultimaAlteracao;
		this.quadra = quadra;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.rota = rota;
		this.setorComercial = setorComercial;
		this.cobrancaGrupo = cobrancaGrupo;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.esferaPoder = esferaPoder;
		this.empresa = empresa;
		this.categoria = categoria;
		this.cobrancaAcaoSituacao = cobrancaAcaoSituacao;
		this.cobrancaCriterio = cobrancaCriterio;
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
		this.cobrancaAcao = cobrancaAcao;
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	public Date getTempoRealizacaoEmitir() {
		return this.tempoRealizacaoEmitir;
	}

	public void setTempoRealizacaoEmitir(Date tempoRealizacaoEmitir) {
		this.tempoRealizacaoEmitir = tempoRealizacaoEmitir;
	}

	public Date getTempoRealizacaoEncerrar() {
		return this.tempoRealizacaoEncerrar;
	}

	public void setTempoRealizacaoEncerrar(Date tempoRealizacaoEncerrar) {
		this.tempoRealizacaoEncerrar = tempoRealizacaoEncerrar;
	}

	public int getCodigoSetorcomercial() {
		return this.codigoSetorcomercial;
	}

	public void setCodigoSetorcomercial(int codigoSetorcomercial) {
		this.codigoSetorcomercial = codigoSetorcomercial;
	}

	public int getNumeroQuadra() {
		return this.numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getIndicadorAntesApos() {
		return this.indicadorAntesApos;
	}

	public void setIndicadorAntesApos(Short indicadorAntesApos) {
		this.indicadorAntesApos = indicadorAntesApos;
	}

	public Short getIndicadorAcimaLimite() {
		return this.indicadorAcimaLimite;
	}

	public void setIndicadorAcimaLimite(Short indicadorAcimaLimite) {
		this.indicadorAcimaLimite = indicadorAcimaLimite;
	}

	public int getQuantidadeDocumentos() {
		return this.quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(int quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	public BigDecimal getValorDocumentos() {
		return this.valorDocumentos;
	}

	public void setValorDocumentos(BigDecimal valorDocumentos) {
		this.valorDocumentos = valorDocumentos;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorDefinitivo() {
		return this.indicadorDefinitivo;
	}

	public void setIndicadorDefinitivo(Short indicadorDefinitivo) {
		this.indicadorDefinitivo = indicadorDefinitivo;
	}

	public Quadra getQuadra() {
		return this.quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public GerenciaRegional getGerenciaRegional() {
		return this.gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public ImovelPerfil getImovelPerfil() {
		return this.imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return this.ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Rota getRota() {
		return this.rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public SetorComercial getSetorComercial() {
		return this.setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public gcom.cobranca.CobrancaGrupo getCobrancaGrupo() {
		return this.cobrancaGrupo;
	}

	public void setCobrancaGrupo(gcom.cobranca.CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return this.ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public EsferaPoder getEsferaPoder() {
		return this.esferaPoder;
	}

	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public gcom.cobranca.CobrancaAcaoSituacao getCobrancaAcaoSituacao() {
		return this.cobrancaAcaoSituacao;
	}

	public void setCobrancaAcaoSituacao(
			gcom.cobranca.CobrancaAcaoSituacao cobrancaAcaoSituacao) {
		this.cobrancaAcaoSituacao = cobrancaAcaoSituacao;
	}

	public gcom.cobranca.CobrancaCriterio getCobrancaCriterio() {
		return this.cobrancaCriterio;
	}

	public void setCobrancaCriterio(
			gcom.cobranca.CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}

	public gcom.cobranca.CobrancaAcao getCobrancaAcao() {
		return this.cobrancaAcao;
	}

	public void setCobrancaAcao(gcom.cobranca.CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return this.fiscalizacaoSituacao;
	}

	public void setFiscalizacaoSituacao(
			FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("Id", getId()).toString();
	}

	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}

	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}

	public gcom.cobranca.CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}

	public void setCobrancaDebitoSituacao(
			gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}

    public AtendimentoMotivoEncerramento getMotivoEncerramento() {
		return motivoEncerramento;
	}


	public void setMotivoEncerramento(
			AtendimentoMotivoEncerramento motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
}

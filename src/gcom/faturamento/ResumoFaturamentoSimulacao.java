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
package gcom.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
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
public class ResumoFaturamentoSimulacao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private Short indicadorDebitoConta;

    /** nullable persistent field */
    private Integer indicadorSimulacao;

    /** nullable persistent field */
    private Short quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorAgua;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer consumoAgua;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private BigDecimal valorEsgoto;

    /** nullable persistent field */
    private Integer consumoEsgoto;

    /** nullable persistent field */
    private BigDecimal valorDebitos;

    /** nullable persistent field */
    private BigDecimal valorCreditos;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private Integer quantidadeContas;

    /** persistent field */
    private gcom.faturamento.FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
	private EsferaPoder esferaPoder;
	
    /** persistent field */
	private GerenciaRegional gerenciaRegional;
	
    /** persistent field */
	private SetorComercial setorComercial;
	
    /** persistent field */
	private Rota rota;

    /** persistent field */
    private Subcategoria subCategoria;

    /** persistent field */
    private UnidadeNegocio unidadeNegocio;
    
    /** nullable persistent field */
    private BigDecimal valorImposto;

    /** full constructor */
    public ResumoFaturamentoSimulacao(Integer anoMesReferencia, Short indicadorDebitoConta, Integer indicadorSimulacao, Short quantidadeEconomia, BigDecimal valorAgua, Integer codigoSetorComercial, Integer consumoAgua, Integer numeroQuadra, BigDecimal valorEsgoto, Integer consumoEsgoto, BigDecimal valorDebitos, BigDecimal valorCreditos, Date ultimaAlteracao, gcom.faturamento.FaturamentoGrupo faturamentoGrupo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Quadra quadra, Localidade localidade, LigacaoAguaSituacao ligacaoAguaSituacao, Categoria categoria, Integer quantidadeContas, EsferaPoder esferaPoder, GerenciaRegional gerenciaRegional,SetorComercial setorComercial, Rota rota) {
        this.anoMesReferencia = anoMesReferencia;
        this.indicadorDebitoConta = indicadorDebitoConta;
        this.indicadorSimulacao = indicadorSimulacao;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorAgua = valorAgua;
        this.codigoSetorComercial = codigoSetorComercial;
        this.consumoAgua = consumoAgua;
        this.numeroQuadra = numeroQuadra;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.valorDebitos = valorDebitos;
        this.valorCreditos = valorCreditos;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoGrupo = faturamentoGrupo;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.categoria = categoria;
        this.quantidadeContas = quantidadeContas;
        this.esferaPoder = esferaPoder;
        this.gerenciaRegional = gerenciaRegional;
        this.setorComercial = setorComercial;
        this.rota = rota;
    }

    /** default constructor */
    public ResumoFaturamentoSimulacao() {
    }

    /** minimal constructor */
    public ResumoFaturamentoSimulacao(gcom.faturamento.FaturamentoGrupo faturamentoGrupo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Quadra quadra, Localidade localidade, LigacaoAguaSituacao ligacaoAguaSituacao, Categoria categoria) {
        this.faturamentoGrupo = faturamentoGrupo;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.categoria = categoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Short getIndicadorDebitoConta() {
        return this.indicadorDebitoConta;
    }

    public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
        this.indicadorDebitoConta = indicadorDebitoConta;
    }

    public Integer getIndicadorSimulacao() {
        return this.indicadorSimulacao;
    }

    public void setIndicadorSimulacao(Integer indicadorSimulacao) {
        this.indicadorSimulacao = indicadorSimulacao;
    }

    public Short getQuantidadeEconomia() {
        return this.quantidadeEconomia;
    }

    public void setQuantidadeEconomia(Short quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public BigDecimal getValorAgua() {
        return this.valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public Integer getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Integer codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public Integer getConsumoAgua() {
        return this.consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public Integer getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(Integer numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public BigDecimal getValorEsgoto() {
        return this.valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public Integer getConsumoEsgoto() {
        return this.consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public BigDecimal getValorDebitos() {
        return this.valorDebitos;
    }

    public void setValorDebitos(BigDecimal valorDebitos) {
        this.valorDebitos = valorDebitos;
    }

    public BigDecimal getValorCreditos() {
        return this.valorCreditos;
    }

    public void setValorCreditos(BigDecimal valorCreditos) {
        this.valorCreditos = valorCreditos;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(gcom.faturamento.FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public Quadra getQuadra() {
        return this.quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo quantidadeContas.
	 */
	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	/**
	 * @param quantidadeContas O quantidadeContas a ser setado.
	 */
	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
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

	public Subcategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(Subcategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public BigDecimal getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}
}
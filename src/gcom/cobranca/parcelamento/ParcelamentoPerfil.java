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
package gcom.cobranca.parcelamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoPerfil extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /*
     * TODO - COSANPA - Felipe Santos
     * 
     */
    private BigDecimal percentualDescontoAcrescimoMulta;
    
    private BigDecimal percentualDescontoAcrescimoJurosMora;
    
    private BigDecimal percentualDescontoAcrescimoAtualizacaoMonetaria;
    // fim alteração

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Subcategoria subcategoria;

    /** persistent field */
    private ImovelSituacaoTipo imovelSituacaoTipo;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private ResolucaoDiretoria resolucaoDiretoria;
    
    /** persistent field */
    private BigDecimal  percentualTarifaMinimaPrestacao;
    
    /** persistent field */
    private Integer  numeroConsumoMinimo;
    
    /** persistent field */
    private BigDecimal  percentualVariacaoConsumoMedio;
    
    /** persistent field */
    private Short  indicadorChequeDevolvido;
    
    /** persistent field */
    private Short  indicadorSancoesUnicaConta;
    
    private Short  indicadorRetroativoTarifaSocial;
    
    private Integer anoMesReferenciaLimiteInferior;
    
    private Integer anoMesReferenciaLimiteSuperior;
    
    private BigDecimal percentualDescontoTarifaSocial;
    
    private Integer parcelaQuantidadeMinimaFatura;
    
    private Short  indicadorAlertaParcelaMinima;
    
    private Integer numeroConsumoEconomia;
    
    private BigDecimal numeroAreaConstruida;
    
    private Categoria categoria;
    
    private BigDecimal percentualDescontoSancao;
    
    private Integer quantidadeEconomias;
   
    private Short capacidadeHidrometro;
   
    private Short indicadorEntradaMinima;
    
    private Integer  quantidadeMaximaReparcelamento;
    
    private BigDecimal percentualDescontoPagamentoAVista;
    
    private Date dataLimiteDescontoPagamentoAVista;
    

    public Short getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(Short capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	/**
	 * @return Retorna o campo quantidadeEconomias.
	 */
	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	
	/**
	 * @param quantidadeEconomias O quantidadeEconomias a ser setado.
	 */
	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	/**
	 * @return Retorna o campo percentualTarifaMinimaPrestacao.
	 */
	public BigDecimal getPercentualTarifaMinimaPrestacao() {
		return percentualTarifaMinimaPrestacao;
	}

	/**
	 * @param percentualTarifaMinimaPrestacao O percentualTarifaMinimaPrestacao a ser setado.
	 */
	public void setPercentualTarifaMinimaPrestacao(
			BigDecimal percentualTarifaMinimaPrestacao) {
		this.percentualTarifaMinimaPrestacao = percentualTarifaMinimaPrestacao;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
		
		filtroParcelamentoPerfil. adicionarCaminhoParaCarregamentoEntidade("subcategoria");
		filtroParcelamentoPerfil. adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
		filtroParcelamentoPerfil. adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroParcelamentoPerfil. adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
		filtroParcelamentoPerfil. adicionarParametro(
				new ParametroSimples(FiltroParcelamentoPerfil.ID, this.getId()));
		return filtroParcelamentoPerfil; 
	}
    
    /** full constructor */
    public ParcelamentoPerfil(BigDecimal percentualDescontoAcrescimoMulta, BigDecimal percentualDescontoAcrescimoJurosMora, BigDecimal percentualDescontoAcrescimoAtualizacaoMonetaria, 
    		Date ultimaAlteracao, Subcategoria subcategoria, ImovelSituacaoTipo imovelSituacaoTipo, ImovelPerfil imovelPerfil, ResolucaoDiretoria resolucaoDiretoria) {
        this.percentualDescontoAcrescimoMulta = percentualDescontoAcrescimoMulta;
        this.percentualDescontoAcrescimoJurosMora = percentualDescontoAcrescimoJurosMora;
        this.percentualDescontoAcrescimoAtualizacaoMonetaria = percentualDescontoAcrescimoAtualizacaoMonetaria;
        this.ultimaAlteracao = ultimaAlteracao;
        this.subcategoria = subcategoria;
        this.imovelSituacaoTipo = imovelSituacaoTipo;
        this.imovelPerfil = imovelPerfil;
        this.resolucaoDiretoria = resolucaoDiretoria;
    }

    /** default constructor */
    public ParcelamentoPerfil() {
    }

    /** minimal constructor */
    public ParcelamentoPerfil(Subcategoria subcategoria, ImovelSituacaoTipo imovelSituacaoTipo, ImovelPerfil imovelPerfil, ResolucaoDiretoria resolucaoDiretoria) {
        this.subcategoria = subcategoria;
        this.imovelSituacaoTipo = imovelSituacaoTipo;
        this.imovelPerfil = imovelPerfil;
        this.resolucaoDiretoria = resolucaoDiretoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPercentualDescontoAcrescimoMulta() {
        return this.percentualDescontoAcrescimoMulta;
    }

    public void setPercentualDescontoAcrescimoMulta(BigDecimal percentualDescontoAcrescimoMulta) {
        this.percentualDescontoAcrescimoMulta = percentualDescontoAcrescimoMulta;
    }
    
    public BigDecimal getPercentualDescontoAcrescimoJurosMora() {
        return this.percentualDescontoAcrescimoJurosMora;
    }

    public void setPercentualDescontoAcrescimoJurosMora(BigDecimal percentualDescontoAcrescimoJurosMora) {
        this.percentualDescontoAcrescimoJurosMora = percentualDescontoAcrescimoJurosMora;
    }
    
    public BigDecimal getPercentualDescontoAcrescimoAtualizacaoMonetaria() {
        return this.percentualDescontoAcrescimoAtualizacaoMonetaria;
    }

    public void setPercentualDescontoAcrescimoAtualizacaoMonetaria(BigDecimal percentualDescontoAcrescimoAtualizacaoMonetaria) {
        this.percentualDescontoAcrescimoAtualizacaoMonetaria = percentualDescontoAcrescimoAtualizacaoMonetaria;
    }
    
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Subcategoria getSubcategoria() {
        return this.subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public ImovelSituacaoTipo getImovelSituacaoTipo() {
        return this.imovelSituacaoTipo;
    }

    public void setImovelSituacaoTipo(ImovelSituacaoTipo imovelSituacaoTipo) {
        this.imovelSituacaoTipo = imovelSituacaoTipo;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public ResolucaoDiretoria getResolucaoDiretoria() {
        return this.resolucaoDiretoria;
    }

    public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
        this.resolucaoDiretoria = resolucaoDiretoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getIndicadorChequeDevolvido() {
		return indicadorChequeDevolvido;
	}

	public void setIndicadorChequeDevolvido(Short indicadorChequeDevolvido) {
		this.indicadorChequeDevolvido = indicadorChequeDevolvido;
	}

	public Short getIndicadorSancoesUnicaConta() {
		return indicadorSancoesUnicaConta;
	}

	public void setIndicadorSancoesUnicaConta(Short indicadorSancoesUnicaConta) {
		this.indicadorSancoesUnicaConta = indicadorSancoesUnicaConta;
	}

	public Integer getNumeroConsumoMinimo() {
		return numeroConsumoMinimo;
	}

	public void setNumeroConsumoMinimo(Integer numeroConsumoMinimo) {
		this.numeroConsumoMinimo = numeroConsumoMinimo;
	}

	public BigDecimal getPercentualVariacaoConsumoMedio() {
		return percentualVariacaoConsumoMedio;
	}

	public void setPercentualVariacaoConsumoMedio(
			BigDecimal percentualVariacaoConsumoMedio) {
		this.percentualVariacaoConsumoMedio = percentualVariacaoConsumoMedio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getNumeroAreaConstruida() {
		return numeroAreaConstruida;
	}

	public void setNumeroAreaConstruida(BigDecimal numeroAreaConstruida) {
		this.numeroAreaConstruida = numeroAreaConstruida;
	}

	public Integer getNumeroConsumoEconomia() {
		return numeroConsumoEconomia;
	}

	public void setNumeroConsumoEconomia(Integer numeroConsumoEconomia) {
		this.numeroConsumoEconomia = numeroConsumoEconomia;
	}

	public Integer getAnoMesReferenciaLimiteInferior() {
		return anoMesReferenciaLimiteInferior;
	}

	public void setAnoMesReferenciaLimiteInferior(
			Integer anoMesReferenciaLimiteInferior) {
		this.anoMesReferenciaLimiteInferior = anoMesReferenciaLimiteInferior;
	}

	public Integer getAnoMesReferenciaLimiteSuperior() {
		return anoMesReferenciaLimiteSuperior;
	}

	public void setAnoMesReferenciaLimiteSuperior(
			Integer anoMesReferenciaLimiteSuperior) {
		this.anoMesReferenciaLimiteSuperior = anoMesReferenciaLimiteSuperior;
	}

	public Short getIndicadorAlertaParcelaMinima() {
		return indicadorAlertaParcelaMinima;
	}

	public void setIndicadorAlertaParcelaMinima(Short indicadorAlertaParcelaMinima) {
		this.indicadorAlertaParcelaMinima = indicadorAlertaParcelaMinima;
	}

	public Short getIndicadorRetroativoTarifaSocial() {
		return indicadorRetroativoTarifaSocial;
	}

	public void setIndicadorRetroativoTarifaSocial(
			Short indicadorRetroativoTarifaSocial) {
		this.indicadorRetroativoTarifaSocial = indicadorRetroativoTarifaSocial;
	}

	public Integer getParcelaQuantidadeMinimaFatura() {
		return parcelaQuantidadeMinimaFatura;
	}

	public void setParcelaQuantidadeMinimaFatura(
			Integer parcelaQuantidadeMinimaFatura) {
		this.parcelaQuantidadeMinimaFatura = parcelaQuantidadeMinimaFatura;
	}

	/**
	 * @return Retorna o campo percentualDescontoAVista.
	 */
	public BigDecimal getPercentualDescontoTarifaSocial() {
		return percentualDescontoTarifaSocial;
	}

	/**
	 * @param percentualDescontoAVista O percentualDescontoAVista a ser setado.
	 */
	public void setPercentualDescontoTarifaSocial(BigDecimal percentualDescontoTarifaSocial) {
		this.percentualDescontoTarifaSocial = percentualDescontoTarifaSocial;
	}

	/**
	 * @return Retorna o campo percentualDescontoSancao.
	 */
	public BigDecimal getPercentualDescontoSancao() {
		return percentualDescontoSancao;
	}

	/**
	 * @param percentualDescontoSancao O percentualDescontoSancao a ser setado.
	 */
	public void setPercentualDescontoSancao(BigDecimal percentualDescontoSancao) {
		this.percentualDescontoSancao = percentualDescontoSancao;
	}

	public Short getIndicadorEntradaMinima() {
		return indicadorEntradaMinima;
	}

	public void setIndicadorEntradaMinima(Short indicadorEntradaMinima) {
		this.indicadorEntradaMinima = indicadorEntradaMinima;
	}

	public Integer getQuantidadeMaximaReparcelamento() {
		return quantidadeMaximaReparcelamento;
	}

	public void setQuantidadeMaximaReparcelamento(
			Integer quantidadeMaximaReparcelamento) {
		this.quantidadeMaximaReparcelamento = quantidadeMaximaReparcelamento;
	}

	public BigDecimal getPercentualDescontoPagamentoAVista() {
		return percentualDescontoPagamentoAVista;
	}

	public void setPercentualDescontoPagamentoAVista(
			BigDecimal percentualDescontoPagamentoAVista) {
		this.percentualDescontoPagamentoAVista = percentualDescontoPagamentoAVista;
	}

	public Date getDataLimiteDescontoPagamentoAVista() {
		return dataLimiteDescontoPagamentoAVista;
	}

	public void setDataLimiteDescontoPagamentoAVista(
			Date dataLimiteDescontoPagamentoAVista) {
		this.dataLimiteDescontoPagamentoAVista = dataLimiteDescontoPagamentoAVista;
	}
	
	
}

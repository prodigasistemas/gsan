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
package gcom.faturamento.debito;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.ParcelamentoGrupo;
import gcom.faturamento.conta.Conta;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class DebitoCobrado extends ObjetoTransacao{
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** persistent field */
	private Date debitoCobrado;

	/** nullable persistent field */
	private Integer codigoSetorComercial;

	/** nullable persistent field */
	private Integer numeroQuadra;

	/** nullable persistent field */
	private Short numeroLote;

	/** nullable persistent field */
	private Short numeroSubLote;

	/** nullable persistent field */
	private Integer anoMesReferenciaDebito;

	/** nullable persistent field */
	private Integer anoMesCobrancaDebito;

	/** persistent field */
	@ControleAlteracao(funcionalidade=Conta.ATRIBUTOS_RETIFICAR_CONTA)
	private BigDecimal valorPrestacao;

	/** persistent field */
	private short numeroPrestacao;

	/** persistent field */
	private short numeroPrestacaoDebito;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private int lictId;
    
    private Short numeroParcelaBonus;

	/** persistent field */
	private LancamentoItemContabil lancamentoItemContabil;

	/** persistent field */
	private Conta conta;

	/** persistent field */
	private FinanciamentoTipo financiamentoTipo;

	/** persistent field */
	private Quadra quadra;

	/** persistent field */
	private Localidade localidade;

	/** persistent field */	
	private gcom.faturamento.debito.DebitoTipo debitoTipo;

	/** persistent field */
	private ParcelamentoGrupo parcelamentoGrupo;
	
	private DebitoACobrarGeral debitoACobrarGeral;

	/** persistent field */
	private Set debitoCobradoCategorias;

	/** full constructor */
	public DebitoCobrado(Date debitoCobrado, Integer codigoSetorComercial,
			Integer numeroQuadra, Short numeroLote, Short numeroSubLote,
			Integer anoMesReferenciaDebito, Integer anoMesCobrancaDebito,
			BigDecimal valorPrestacao, short numeroPrestacao,
			short numeroPrestacaoDebito, Date ultimaAlteracao, int lictId,
			LancamentoItemContabil lancamentoItemContabil, Conta conta,
			FinanciamentoTipo financiamentoTipo, Quadra quadra,
			Localidade localidade,
			gcom.faturamento.debito.DebitoTipo debitoTipo,
			ParcelamentoGrupo parcelamentoGrupo, Set debitoCobradoCategorias) {
		this.debitoCobrado = debitoCobrado;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.anoMesReferenciaDebito = anoMesReferenciaDebito;
		this.anoMesCobrancaDebito = anoMesCobrancaDebito;
		this.valorPrestacao = valorPrestacao;
		this.numeroPrestacao = numeroPrestacao;
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
		this.ultimaAlteracao = ultimaAlteracao;
		this.lictId = lictId;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.conta = conta;
		this.financiamentoTipo = financiamentoTipo;
		this.quadra = quadra;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
		this.parcelamentoGrupo = parcelamentoGrupo;
		this.debitoCobradoCategorias = debitoCobradoCategorias;
	}

	/** default constructor */
	public DebitoCobrado() {
	}

	/** minimal constructor */
	public DebitoCobrado(Date debitoCobrado, BigDecimal valorPrestacao,
			short numeroPrestacao, short numeroPrestacaoDebito,
			LancamentoItemContabil lancamentoItemContabil, Conta conta,
			FinanciamentoTipo financiamentoTipo, Quadra quadra,
			Localidade localidade,
			gcom.faturamento.debito.DebitoTipo debitoTipo,
			ParcelamentoGrupo parcelamentoGrupo, Set debitoCobradoCategorias) {
		this.debitoCobrado = debitoCobrado;
		this.valorPrestacao = valorPrestacao;
		this.numeroPrestacao = numeroPrestacao;
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.conta = conta;
		this.financiamentoTipo = financiamentoTipo;
		this.quadra = quadra;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
		this.parcelamentoGrupo = parcelamentoGrupo;
		this.debitoCobradoCategorias = debitoCobradoCategorias;
	}
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * Construtor de DebitoCobrado 
	 * 
	 * @param anoMesReferenciaDebito
	 * @param numeroPrestacaoDebito
	 * @param numeroPrestacao
	 * @param valorPrestacao
	 */
	public DebitoCobrado(Integer anoMesReferenciaDebito, short numeroPrestacaoDebito, short numeroPrestacao,
			BigDecimal valorPrestacao, DebitoTipo debitoTipo) {
		this.anoMesReferenciaDebito = anoMesReferenciaDebito;
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
		this.numeroPrestacao = numeroPrestacao;
		this.valorPrestacao = valorPrestacao;
		this.debitoTipo = debitoTipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDebitoCobrado() {
		return this.debitoCobrado;
	}

	public void setDebitoCobrado(Date debitoCobrado) {
		this.debitoCobrado = debitoCobrado;
	}

	public Integer getCodigoSetorComercial() {
		return this.codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getNumeroQuadra() {
		return this.numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getNumeroLote() {
		return this.numeroLote;
	}

	public void setNumeroLote(Short numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Short getNumeroSubLote() {
		return this.numeroSubLote;
	}

	public void setNumeroSubLote(Short numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public Integer getAnoMesReferenciaDebito() {
		return this.anoMesReferenciaDebito;
	}

	public void setAnoMesReferenciaDebito(Integer anoMesReferenciaDebito) {
		this.anoMesReferenciaDebito = anoMesReferenciaDebito;
	}

	public Integer getAnoMesCobrancaDebito() {
		return this.anoMesCobrancaDebito;
	}

	public void setAnoMesCobrancaDebito(Integer anoMesCobrancaDebito) {
		this.anoMesCobrancaDebito = anoMesCobrancaDebito;
	}

	public BigDecimal getValorPrestacao() {
		return this.valorPrestacao;
	}

	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public short getNumeroPrestacao() {
		return this.numeroPrestacao;
	}

	public void setNumeroPrestacao(short numeroPrestacao) {
		this.numeroPrestacao = numeroPrestacao;
	}

	public short getNumeroPrestacaoDebito() {
		return this.numeroPrestacaoDebito;
	}

	public void setNumeroPrestacaoDebito(short numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public int getLictId() {
		return this.lictId;
	}

	public void setLictId(int lictId) {
		this.lictId = lictId;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public FinanciamentoTipo getFinanciamentoTipo() {
		return this.financiamentoTipo;
	}

	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
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

	public gcom.faturamento.debito.DebitoTipo getDebitoTipo() {
		return this.debitoTipo;
	}

	public void setDebitoTipo(gcom.faturamento.debito.DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public ParcelamentoGrupo getParcelamentoGrupo() {
		return this.parcelamentoGrupo;
	}

	public void setParcelamentoGrupo(ParcelamentoGrupo parcelamentoGrupo) {
		this.parcelamentoGrupo = parcelamentoGrupo;
	}

	public Set getDebitoCobradoCategorias() {
		return this.debitoCobradoCategorias;
	}

	public void setDebitoCobradoCategorias(Set debitoCobradoCategorias) {
		this.debitoCobradoCategorias = debitoCobradoCategorias;
	}

	public String toString() {
		return "debitoCobrado";
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroDebitoCobrado filtro = new FiltroDebitoCobrado();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroDebitoCobrado.CODIGO, this.getId()));
		return filtro; 
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof DebitoCobrado) ) return false;
        DebitoCobrado castOther = (DebitoCobrado) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    @Override
    public void initializeLazy() {
    	if (this.getDebitoTipo() != null){
    		this.getDebitoTipo().initializeLazy();
    	}
    	
    }
    
    public String getDescricao(){
    	String desc = "";
    	if (getDebitoTipo() != null){
    		desc = getDebitoTipo().getDescricao();
    	}
    	return desc;
    }
    
    @Override
    public String getDescricaoParaRegistroTransacao() {
    	return this.getDescricao();
    }

    public Short getNumeroParcelaBonus() {
        return numeroParcelaBonus;
    }

    public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
        this.numeroParcelaBonus = numeroParcelaBonus;
    }

    /**
     * @author Vivianne Sousa
     * @created 05/03/2008
    */
    public short getNumeroTotalParcelasMenosBonus() {
        short retorno = getNumeroPrestacao();
        
        if (getNumeroParcelaBonus() != null){
            retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
        }
             
        return retorno;
    }

	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}
}

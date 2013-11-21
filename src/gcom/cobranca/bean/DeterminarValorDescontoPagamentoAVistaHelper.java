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
package gcom.cobranca.bean;

import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;

import java.io.Serializable;
import java.math.BigDecimal;

public class DeterminarValorDescontoPagamentoAVistaHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private ObterOpcoesDeParcelamentoHelper obterOpcoesDeParcelamentoHelper;
	
	private ParcelamentoPerfil parcelamentoPerfil;
	
	private BigDecimal valorDescontoAcrecismosImpotualidade;
	
	private BigDecimal valorDescontoInatividade;
	
	private BigDecimal valorDescontoAntiguidade;
	
	private BigDecimal valorDescontoSancoes;
	
	private BigDecimal valorDescontoTarifaSocial;
	
	private Integer anoMesLimiteMaximo;
	
	private ResolucaoDiretoria resolucaoDiretoria;
	
	private BigDecimal valorCreditoARealizar;
	
	private BigDecimal valorDescontoInatividadeAVista;
	   
    public DeterminarValorDescontoPagamentoAVistaHelper(ObterOpcoesDeParcelamentoHelper helper, ParcelamentoPerfil parcelamentoPerfil,
        BigDecimal valorDescontoAcrecismosImpotualidade, BigDecimal valorDescontoInatividade,
        BigDecimal valorDescontoAntiguidade, BigDecimal valorDescontoSancoes,
        BigDecimal valorDescontoTarifaSocial, Integer anoMesLimiteMaximo,
        ResolucaoDiretoria resolucaoDiretoria, BigDecimal valorCreditoARealizar,
        BigDecimal valorDescontoInatividadeAVista){
       
        this.setObterOpcoesDeParcelamentoHelper(helper);
        this.setParcelamentoPerfil(parcelamentoPerfil);
        this.setValorDescontoAcrecismosImpotualidade(valorDescontoAcrecismosImpotualidade);
        this.setValorDescontoInatividade(valorDescontoInatividade);
        this.setValorDescontoAntiguidade(valorDescontoAntiguidade);
        this.setValorDescontoSancoes(valorDescontoSancoes);
        this.setValorDescontoTarifaSocial(valorDescontoTarifaSocial);
        this.setAnoMesLimiteMaximo(anoMesLimiteMaximo);
        this.setResolucaoDiretoria(resolucaoDiretoria);
        this.setValorCreditoARealizar(valorCreditoARealizar);
        this.valorDescontoInatividadeAVista = valorDescontoInatividadeAVista;
    }
	
	public Integer getAnoMesLimiteMaximo() {
		return anoMesLimiteMaximo;
	}

	public void setAnoMesLimiteMaximo(Integer anoMesLimiteMaximo) {
		this.anoMesLimiteMaximo = anoMesLimiteMaximo;
	}

	public ObterOpcoesDeParcelamentoHelper getObterOpcoesDeParcelamentoHelper() {
		return obterOpcoesDeParcelamentoHelper;
	}

	public void setObterOpcoesDeParcelamentoHelper(
			ObterOpcoesDeParcelamentoHelper obterOpcoesDeParcelamentoHelper) {
		this.obterOpcoesDeParcelamentoHelper = obterOpcoesDeParcelamentoHelper;
	}

	public ParcelamentoPerfil getParcelamentoPerfil() {
		return parcelamentoPerfil;
	}

	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	public BigDecimal getValorDescontoAcrecismosImpotualidade() {
		return valorDescontoAcrecismosImpotualidade;
	}

	public void setValorDescontoAcrecismosImpotualidade(
			BigDecimal valorDescontoAcrecismosImpotualidade) {
		this.valorDescontoAcrecismosImpotualidade = valorDescontoAcrecismosImpotualidade;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public BigDecimal getValorDescontoInatividade() {
		return valorDescontoInatividade;
	}

	public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
		this.valorDescontoInatividade = valorDescontoInatividade;
	}

	public BigDecimal getValorDescontoSancoes() {
		return valorDescontoSancoes;
	}

	public void setValorDescontoSancoes(BigDecimal valorDescontoSancoes) {
		this.valorDescontoSancoes = valorDescontoSancoes;
	}

	public BigDecimal getValorDescontoTarifaSocial() {
		return valorDescontoTarifaSocial;
	}

	public void setValorDescontoTarifaSocial(BigDecimal valorDescontoTarifaSocial) {
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public BigDecimal getValorCreditoARealizar() {
		return valorCreditoARealizar;
	}

	public void setValorCreditoARealizar(BigDecimal valorCreditoARealizar) {
		this.valorCreditoARealizar = valorCreditoARealizar;
	}

	public BigDecimal getValorDescontoInatividadeAVista() {
		return valorDescontoInatividadeAVista;
	}

	public void setValorDescontoInatividadeAVista(
			BigDecimal valorDescontoInatividadeAVista) {
		this.valorDescontoInatividadeAVista = valorDescontoInatividadeAVista;
	}
	
	
}

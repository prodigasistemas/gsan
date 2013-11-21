/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.relatorio.atendimentopublico.bean;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * classe responsável por criar o relatório de Boletim de Medição
 * 
 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
 * 
 * @author Hugo Leonardo
 *
 * @date 03/01/2011
 */
public class RelatorioBoletimCustoPavimentoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String numeroOS;
    private String matricula;
    private String endereco;
    private String tipoPvtoRua;
    private BigDecimal metragem;
    private String tipoPvtoRuaRetorno;
    private BigDecimal metragemRetorno;
    private String dataRetorno;
    private String dataRejeicao;
    private String indicadorAceite;
    private String dataAceite;
    private String motivoRejeicao;
    private String motivoAceite;
    
    private String descricaoTotal;
    private String descricaoTotal1;
    private String descricaoTotal2;
    private String descricaoTotal3;
    private BigDecimal totalMetragem;
    private BigDecimal totalValor;
    
    private String indicadorQuebra;
    private Boolean indicadorPrimeiraOcorrenciaDemandadas;
    private Boolean indicadorPrimeiraOcorrenciaDemandadas3Meses;
    private Boolean indicadorPrimeiraOcorrenciaAceitas;
    
    private Boolean indicadorPrimeiraOcorrenciaTotal;

    public RelatorioBoletimCustoPavimentoBean(){
    	
    }
    
    public RelatorioBoletimCustoPavimentoBean(String numeroOS, String matricula, 
			String endereco, String tipoPvtoRua, BigDecimal metragem,
			String tipoPvtoRuaRetorno, BigDecimal metragemRetorno, 
			String dataRetorno, String dataRejeicao, String indicadorAceite, 
			String dataAceite, String motivoRejeicao, String motivoAceite) {
		
		this.numeroOS = numeroOS;
		this.matricula = matricula;
		this.endereco = endereco;
		this.tipoPvtoRua = tipoPvtoRua;
		this.metragem = metragem;
		this.tipoPvtoRuaRetorno = tipoPvtoRuaRetorno;
		this.metragemRetorno = metragemRetorno;
		this.dataRetorno = dataRetorno;
		this.dataRejeicao = dataRejeicao;
		this.indicadorAceite = indicadorAceite;
		this.dataAceite = dataAceite;
		this.motivoRejeicao = motivoRejeicao;
		this.motivoAceite = motivoAceite;
	}

	public String getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(String dataAceite) {
		this.dataAceite = dataAceite;
	}

	public String getDataRejeicao() {
		return dataRejeicao;
	}

	public void setDataRejeicao(String dataRejeicao) {
		this.dataRejeicao = dataRejeicao;
	}

	public String getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(String dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getIndicadorAceite() {
		return indicadorAceite;
	}

	public void setIndicadorAceite(String indicadorAceite) {
		this.indicadorAceite = indicadorAceite;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public BigDecimal getMetragem() {
		return metragem;
	}

	public void setMetragem(BigDecimal metragem) {
		this.metragem = metragem;
	}

	public BigDecimal getMetragemRetorno() {
		return metragemRetorno;
	}

	public void setMetragemRetorno(BigDecimal metragemRetorno) {
		this.metragemRetorno = metragemRetorno;
	}

	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getTipoPvtoRua() {
		return tipoPvtoRua;
	}

	public void setTipoPvtoRua(String tipoPvtoRua) {
		this.tipoPvtoRua = tipoPvtoRua;
	}

	public String getTipoPvtoRuaRetorno() {
		return tipoPvtoRuaRetorno;
	}

	public void setTipoPvtoRuaRetorno(String tipoPvtoRuaRetorno) {
		this.tipoPvtoRuaRetorno = tipoPvtoRuaRetorno;
	}

	public String getMotivoAceite() {
		return motivoAceite;
	}

	public void setMotivoAceite(String motivoAceite) {
		this.motivoAceite = motivoAceite;
	}

	public String getIndicadorQuebra() {
		return indicadorQuebra;
	}

	public void setIndicadorQuebra(String indicadorQuebra) {
		this.indicadorQuebra = indicadorQuebra;
	}

	public Boolean getIndicadorPrimeiraOcorrenciaDemandadas() {
		return indicadorPrimeiraOcorrenciaDemandadas;
	}

	public void setIndicadorPrimeiraOcorrenciaDemandadas(
			Boolean indicadorPrimeiraOcorrenciaDemandadas) {
		this.indicadorPrimeiraOcorrenciaDemandadas = indicadorPrimeiraOcorrenciaDemandadas;
	}

	public Boolean getIndicadorPrimeiraOcorrenciaAceitas() {
		return indicadorPrimeiraOcorrenciaAceitas;
	}

	public void setIndicadorPrimeiraOcorrenciaAceitas(
			Boolean indicadorPrimeiraOcorrenciaAceitas) {
		this.indicadorPrimeiraOcorrenciaAceitas = indicadorPrimeiraOcorrenciaAceitas;
	}

	public Boolean getIndicadorPrimeiraOcorrenciaDemandadas3Meses() {
		return indicadorPrimeiraOcorrenciaDemandadas3Meses;
	}

	public void setIndicadorPrimeiraOcorrenciaDemandadas3Meses(
			Boolean indicadorPrimeiraOcorrenciaDemandadas3Meses) {
		this.indicadorPrimeiraOcorrenciaDemandadas3Meses = indicadorPrimeiraOcorrenciaDemandadas3Meses;
	}

	public String getDescricaoTotal() {
		return descricaoTotal;
	}

	public void setDescricaoTotal(String descricaoTotal) {
		this.descricaoTotal = descricaoTotal;
	}

	public String getDescricaoTotal1() {
		return descricaoTotal1;
	}

	public void setDescricaoTotal1(String descricaoTotal1) {
		this.descricaoTotal1 = descricaoTotal1;
	}

	public String getDescricaoTotal2() {
		return descricaoTotal2;
	}

	public void setDescricaoTotal2(String descricaoTotal2) {
		this.descricaoTotal2 = descricaoTotal2;
	}

	public String getDescricaoTotal3() {
		return descricaoTotal3;
	}

	public void setDescricaoTotal3(String descricaoTotal3) {
		this.descricaoTotal3 = descricaoTotal3;
	}

	public BigDecimal getTotalMetragem() {
		return totalMetragem;
	}

	public void setTotalMetragem(BigDecimal totalMetragem) {
		this.totalMetragem = totalMetragem;
	}

	public BigDecimal getTotalValor() {
		return totalValor;
	}

	public void setTotalValor(BigDecimal totalValor) {
		this.totalValor = totalValor;
	}

	public Boolean getIndicadorPrimeiraOcorrenciaTotal() {
		return indicadorPrimeiraOcorrenciaTotal;
	}

	public void setIndicadorPrimeiraOcorrenciaTotal(
			Boolean indicadorPrimeiraOcorrenciaTotal) {
		this.indicadorPrimeiraOcorrenciaTotal = indicadorPrimeiraOcorrenciaTotal;
	}

}

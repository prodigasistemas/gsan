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
package gcom.micromedicao.medicao;

import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MedicaoHistoricoAnterior implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesLeitura;

    /** nullable persistent field */
    private Short numeroVezesConsecutivaAnormalidade;

    /** persistent field */
    private Date dataLeituraAnteriorFaturmanento;

    /** persistent field */
    private int numeroLeituraAnteriorFaturamento;

    /** nullable persistent field */
    private Integer numeroLeituraAnteriorInformada;

    /** nullable persistent field */
    private Date dataLeituraAtualInformada;

    /** nullable persistent field */
    private Integer numeroLeituraAtualInformada;

    /** persistent field */
    private Date dataLeituraAtualFaturamento;

    /** persistent field */
    private int numeroLeituraAtualFaturamento;

    /** nullable persistent field */
    private Integer numeroConsumoMedidoMes;

    /** nullable persistent field */
    private Integer numeroConsumoInformado;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Date dataLeituraProcessamentoMovimento;

    /** nullable persistent field */
    private Integer numeroConsumoMedioHidrometro;

    /** persistent field */
    private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;

    /** persistent field */
    private gcom.micromedicao.medicao.MedicaoTipo medicaoTipo;

    /** persistent field */
    private gcom.cadastro.imovel.Imovel imovel;

    /** persistent field */
    private gcom.cadastro.funcionario.Funcionario funcionario;

    /** persistent field */
    private gcom.micromedicao.leitura.LeituraSituacao leituraSituacaoAnterior;

    /** persistent field */
    private gcom.micromedicao.leitura.LeituraSituacao leituraSituacaoAtual;

    /** persistent field */
    private gcom.atendimentopublico.ligacaoagua.LigacaoAgua ligacaoAgua;

    /** persistent field */
    private gcom.micromedicao.leitura.LeituraAnormalidade leituraAnormalidadeInformada;

    /** persistent field */
    private gcom.micromedicao.leitura.LeituraAnormalidade leituraAnormalidadeFaturamento;

      
    /** default constructor */
    public MedicaoHistoricoAnterior() {
    }

    
    
    
    public int getAnoMesLeitura() {
		return anoMesLeitura;
	}




	public void setAnoMesLeitura(int anoMesLeitura) {
		this.anoMesLeitura = anoMesLeitura;
	}




	public Date getDataLeituraAnteriorFaturmanento() {
		return dataLeituraAnteriorFaturmanento;
	}




	public void setDataLeituraAnteriorFaturmanento(
			Date dataLeituraAnteriorFaturmanento) {
		this.dataLeituraAnteriorFaturmanento = dataLeituraAnteriorFaturmanento;
	}




	public Date getDataLeituraAtualFaturamento() {
		return dataLeituraAtualFaturamento;
	}




	public void setDataLeituraAtualFaturamento(Date dataLeituraAtualFaturamento) {
		this.dataLeituraAtualFaturamento = dataLeituraAtualFaturamento;
	}




	public Date getDataLeituraAtualInformada() {
		return dataLeituraAtualInformada;
	}




	public void setDataLeituraAtualInformada(Date dataLeituraAtualInformada) {
		this.dataLeituraAtualInformada = dataLeituraAtualInformada;
	}




	public Date getDataLeituraProcessamentoMovimento() {
		return dataLeituraProcessamentoMovimento;
	}




	public void setDataLeituraProcessamentoMovimento(
			Date dataLeituraProcessamentoMovimento) {
		this.dataLeituraProcessamentoMovimento = dataLeituraProcessamentoMovimento;
	}




	public gcom.cadastro.funcionario.Funcionario getFuncionario() {
		return funcionario;
	}




	public void setFuncionario(gcom.cadastro.funcionario.Funcionario funcionario) {
		this.funcionario = funcionario;
	}




	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return hidrometroInstalacaoHistorico;
	}




	public void setHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public gcom.cadastro.imovel.Imovel getImovel() {
		return imovel;
	}




	public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
		this.imovel = imovel;
	}


	public gcom.micromedicao.leitura.LeituraAnormalidade getLeituraAnormalidadeFaturamento() {
		return leituraAnormalidadeFaturamento;
	}




	public void setLeituraAnormalidadeFaturamento(
			gcom.micromedicao.leitura.LeituraAnormalidade leituraAnormalidadeFaturamento) {
		this.leituraAnormalidadeFaturamento = leituraAnormalidadeFaturamento;
	}




	public gcom.micromedicao.leitura.LeituraAnormalidade getLeituraAnormalidadeInformada() {
		return leituraAnormalidadeInformada;
	}




	public void setLeituraAnormalidadeInformada(
			gcom.micromedicao.leitura.LeituraAnormalidade leituraAnormalidadeInformada) {
		this.leituraAnormalidadeInformada = leituraAnormalidadeInformada;
	}




	public gcom.micromedicao.leitura.LeituraSituacao getLeituraSituacaoAnterior() {
		return leituraSituacaoAnterior;
	}




	public void setLeituraSituacaoAnterior(
			gcom.micromedicao.leitura.LeituraSituacao leituraSituacaoAnterior) {
		this.leituraSituacaoAnterior = leituraSituacaoAnterior;
	}




	public gcom.micromedicao.leitura.LeituraSituacao getLeituraSituacaoAtual() {
		return leituraSituacaoAtual;
	}




	public void setLeituraSituacaoAtual(
			gcom.micromedicao.leitura.LeituraSituacao leituraSituacaoAtual) {
		this.leituraSituacaoAtual = leituraSituacaoAtual;
	}




	public gcom.atendimentopublico.ligacaoagua.LigacaoAgua getLigacaoAgua() {
		return ligacaoAgua;
	}




	public void setLigacaoAgua(
			gcom.atendimentopublico.ligacaoagua.LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}




	public gcom.micromedicao.medicao.MedicaoTipo getMedicaoTipo() {
		return medicaoTipo;
	}




	public void setMedicaoTipo(gcom.micromedicao.medicao.MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}




	public Integer getNumeroConsumoInformado() {
		return numeroConsumoInformado;
	}




	public void setNumeroConsumoInformado(Integer numeroConsumoInformado) {
		this.numeroConsumoInformado = numeroConsumoInformado;
	}




	




	public Integer getNumeroConsumoMedidoMes() {
		return numeroConsumoMedidoMes;
	}




	public void setNumeroConsumoMedidoMes(Integer numeroConsumoMedidoMes) {
		this.numeroConsumoMedidoMes = numeroConsumoMedidoMes;
	}




	public Integer getNumeroConsumoMedioHidrometro() {
		return numeroConsumoMedioHidrometro;
	}




	public void setNumeroConsumoMedioHidrometro(Integer numeroConsumoMedioHidrometro) {
		this.numeroConsumoMedioHidrometro = numeroConsumoMedioHidrometro;
	}




	public int getNumeroLeituraAnteriorFaturamento() {
		return numeroLeituraAnteriorFaturamento;
	}




	public void setNumeroLeituraAnteriorFaturamento(
			int numeroLeituraAnteriorFaturamento) {
		this.numeroLeituraAnteriorFaturamento = numeroLeituraAnteriorFaturamento;
	}




	public Integer getNumeroLeituraAnteriorInformada() {
		return numeroLeituraAnteriorInformada;
	}




	public void setNumeroLeituraAnteriorInformada(
			Integer numeroLeituraAnteriorInformada) {
		this.numeroLeituraAnteriorInformada = numeroLeituraAnteriorInformada;
	}




	public int getNumeroLeituraAtualFaturamento() {
		return numeroLeituraAtualFaturamento;
	}




	public void setNumeroLeituraAtualFaturamento(int numeroLeituraAtualFaturamento) {
		this.numeroLeituraAtualFaturamento = numeroLeituraAtualFaturamento;
	}




	public Integer getNumeroLeituraAtualInformada() {
		return numeroLeituraAtualInformada;
	}




	public void setNumeroLeituraAtualInformada(Integer numeroLeituraAtualInformada) {
		this.numeroLeituraAtualInformada = numeroLeituraAtualInformada;
	}




	public Short getNumeroVezesConsecutivaAnormalidade() {
		return numeroVezesConsecutivaAnormalidade;
	}




	public void setNumeroVezesConsecutivaAnormalidade(
			Short numeroVezesConsecutivaAnormalidade) {
		this.numeroVezesConsecutivaAnormalidade = numeroVezesConsecutivaAnormalidade;
	}




	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}




	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}




	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}

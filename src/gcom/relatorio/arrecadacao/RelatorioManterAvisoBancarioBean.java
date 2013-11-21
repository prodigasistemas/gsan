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
package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos
 * 
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioManterAvisoBancarioBean implements RelatorioBean {
	
	private String idAvisoBancario;

	private String arrecadador;

	private String dataLancamento;

	private String sequencial;

	private String tipo;

	private String numeroDocumento;

	private String banco;

	private String agencia;

	private String numeroConta;

	private String dataRealizacao;

	private BigDecimal totalArrecadacao;

	private BigDecimal totalDeducao;

	private BigDecimal totalDevolucoes;

	private BigDecimal valorAviso;

	private JRBeanCollectionDataSource arrayJrAcertos;

	private ArrayList arrayRelatorioManterAvisoBancarioAcertosBean;

	private JRBeanCollectionDataSource arrayJrDeducoes;

	private ArrayList arrayRelatorioManterAvisoBancarioDeducoesBean;

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosBean
	 */
	public RelatorioManterAvisoBancarioBean(String idAvisoBancario, String arrecadador,
			String dataLancamento, String sequencial, String tipo,
			String numeroDocumento, String banco, String agencia,
			String numeroConta, String dataRealizacao, BigDecimal totalArrecadacao,
            BigDecimal totalDeducao, BigDecimal totalDevolucoes, BigDecimal valorAviso,
			Collection colecaoRelatorioManterAvisoBancarioDeducoesBean,
			Collection colecaoRelatorioManterAvisoBancarioAcertosBean) {
		this.idAvisoBancario = idAvisoBancario;
		this.arrecadador = arrecadador;
		this.dataLancamento = dataLancamento;
		this.sequencial = sequencial;
		this.tipo = tipo;
		this.numeroDocumento = numeroDocumento;
		this.banco = banco;
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.dataRealizacao = dataRealizacao;
		this.totalArrecadacao = totalArrecadacao;
		this.totalDeducao = totalDeducao;
		this.totalDevolucoes = totalDevolucoes;
		this.valorAviso = valorAviso;

		if (colecaoRelatorioManterAvisoBancarioDeducoesBean != null
				&& !colecaoRelatorioManterAvisoBancarioDeducoesBean.isEmpty()) {

			this.arrayRelatorioManterAvisoBancarioDeducoesBean = new ArrayList();
			this.arrayRelatorioManterAvisoBancarioDeducoesBean
					.addAll(colecaoRelatorioManterAvisoBancarioDeducoesBean);
			this.arrayJrDeducoes = new JRBeanCollectionDataSource(
					this.arrayRelatorioManterAvisoBancarioDeducoesBean);

		} else {
			this.arrayJrDeducoes = null;
		}
		
		if (colecaoRelatorioManterAvisoBancarioAcertosBean != null
				&& !colecaoRelatorioManterAvisoBancarioAcertosBean.isEmpty()) {

			this.arrayRelatorioManterAvisoBancarioAcertosBean = new ArrayList();
			this.arrayRelatorioManterAvisoBancarioAcertosBean
					.addAll(colecaoRelatorioManterAvisoBancarioAcertosBean);
			this.arrayJrAcertos = new JRBeanCollectionDataSource(
					this.arrayRelatorioManterAvisoBancarioAcertosBean);

		} else {
			this.arrayJrAcertos = null;
		}
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public JRBeanCollectionDataSource getArrayJrAcertos() {
		return arrayJrAcertos;
	}

	public void setArrayJrAcertos(JRBeanCollectionDataSource arrayJrAcertos) {
		this.arrayJrAcertos = arrayJrAcertos;
	}

	public JRBeanCollectionDataSource getArrayJrDeducoes() {
		return arrayJrDeducoes;
	}

	public void setArrayJrDeducoes(JRBeanCollectionDataSource arrayJrDeducoes) {
		this.arrayJrDeducoes = arrayJrDeducoes;
	}

	public ArrayList getArrayRelatorioManterAvisoBancarioAcertosBean() {
		return arrayRelatorioManterAvisoBancarioAcertosBean;
	}

	public void setArrayRelatorioManterAvisoBancarioAcertosBean(
			ArrayList arrayRelatorioManterAvisoBancarioAcertosBean) {
		this.arrayRelatorioManterAvisoBancarioAcertosBean = arrayRelatorioManterAvisoBancarioAcertosBean;
	}

	public ArrayList getArrayRelatorioManterAvisoBancarioDeducoesBean() {
		return arrayRelatorioManterAvisoBancarioDeducoesBean;
	}

	public void setArrayRelatorioManterAvisoBancarioDeducoesBean(
			ArrayList arrayRelatorioManterAvisoBancarioDeducoesBean) {
		this.arrayRelatorioManterAvisoBancarioDeducoesBean = arrayRelatorioManterAvisoBancarioDeducoesBean;
	}

	public String getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getTotalArrecadacao() {
        return totalArrecadacao;
    }

    public void setTotalArrecadacao(BigDecimal totalArrecadacao) {
        this.totalArrecadacao = totalArrecadacao;
    }

    public BigDecimal getTotalDeducao() {
        return totalDeducao;
    }

    public void setTotalDeducao(BigDecimal totalDeducao) {
        this.totalDeducao = totalDeducao;
    }

    public BigDecimal getTotalDevolucoes() {
        return totalDevolucoes;
    }

    public void setTotalDevolucoes(BigDecimal totalDevolucoes) {
        this.totalDevolucoes = totalDevolucoes;
    }

    public BigDecimal getValorAviso() {
        return valorAviso;
    }

    public void setValorAviso(BigDecimal valorAviso) {
        this.valorAviso = valorAviso;
    }

    public String getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}

}
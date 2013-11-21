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
package gcom.relatorio.financeiro;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC0726] Gerar Relatório das Contas Baixadas Contabilmente
 * 
 * @author Vivianne Sousa
 * @data 08/04/2008
 */
public class RelatorioContasBaixadasContabilmenteBean implements RelatorioBean {

    private String idGerencia;
    private String descGerencia;
    private String idLocalidade;
    private String descLocalidade;
    private String faixaValor;
    private String anoMesReferenciaBaixaContabil;
    private String mensagemFaixaValor;

    private String matricula;
    private String inscricao;
    private String usuario;
    private String endereco;
    private String situacaoAgua;
    private String situacaoEsgoto;
    private String referenciaFatura;
    private BigDecimal valorConta;
    
    
    //Sintetico
    private BigDecimal valorTotalFaixaUm;
    private BigDecimal valorTotalFaixaDois;
    private BigDecimal valorTotalFaixaTres;
    private BigDecimal valorTotal;
    
    
    
    
    public RelatorioContasBaixadasContabilmenteBean(BigDecimal valorTotalFaixaUm, BigDecimal valorTotalFaixaDois, BigDecimal valorTotalFaixaTres, BigDecimal valorTotal) {
		super();
		// TODO Auto-generated constructor stub
		this.valorTotalFaixaUm = valorTotalFaixaUm;
		this.valorTotalFaixaDois = valorTotalFaixaDois;
		this.valorTotalFaixaTres = valorTotalFaixaTres;
		this.valorTotal = valorTotal;
	}

	public RelatorioContasBaixadasContabilmenteBean() {
        
    }
    
    public String getAnoMesReferenciaBaixaContabil() {
        return anoMesReferenciaBaixaContabil;
    }

    public void setAnoMesReferenciaBaixaContabil(String anoMesReferenciaBaixaContabil) {
        this.anoMesReferenciaBaixaContabil = anoMesReferenciaBaixaContabil;
    }

    public String getDescGerencia() {
        return descGerencia;
    }

    public void setDescGerencia(String descGerencia) {
        this.descGerencia = descGerencia;
    }

    public String getDescLocalidade() {
        return descLocalidade;
    }

    public void setDescLocalidade(String descLocalidade) {
        this.descLocalidade = descLocalidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFaixaValor() {
        return faixaValor;
    }

    public void setFaixaValor(String faixaValor) {
        this.faixaValor = faixaValor;
    }

    public String getIdGerencia() {
        return idGerencia;
    }

    public void setIdGerencia(String idGerencia) {
        this.idGerencia = idGerencia;
    }

    public String getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(String idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getReferenciaFatura() {
        return referenciaFatura;
    }

    public void setReferenciaFatura(String referenciaFatura) {
        this.referenciaFatura = referenciaFatura;
    }

    public String getSituacaoAgua() {
        return situacaoAgua;
    }

    public void setSituacaoAgua(String situacaoAgua) {
        this.situacaoAgua = situacaoAgua;
    }

    public String getSituacaoEsgoto() {
        return situacaoEsgoto;
    }

    public void setSituacaoEsgoto(String situacaoEsgoto) {
        this.situacaoEsgoto = situacaoEsgoto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValorConta() {
        return valorConta;
    }

    public void setValorConta(BigDecimal valorConta) {
        this.valorConta = valorConta;
    }

    public String getMensagemFaixaValor() {
        return mensagemFaixaValor;
    }

    public void setMensagemFaixaValor(String mensagemFaixaValor) {
        this.mensagemFaixaValor = mensagemFaixaValor;
    }


	public BigDecimal getValorTotalFaixaDois() {
		return valorTotalFaixaDois;
	}

	public void setValorTotalFaixaDois(BigDecimal valorTotalFaixaDois) {
		this.valorTotalFaixaDois = valorTotalFaixaDois;
	}

	public BigDecimal getValorTotalFaixaTres() {
		return valorTotalFaixaTres;
	}

	public void setValorTotalFaixaTres(BigDecimal valorTotalFaixaTres) {
		this.valorTotalFaixaTres = valorTotalFaixaTres;
	}

	public BigDecimal getValorTotalFaixaUm() {
		return valorTotalFaixaUm;
	}

	public void setValorTotalFaixaUm(BigDecimal valorTotalFaixaUm) {
		this.valorTotalFaixaUm = valorTotalFaixaUm;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

    
    
}

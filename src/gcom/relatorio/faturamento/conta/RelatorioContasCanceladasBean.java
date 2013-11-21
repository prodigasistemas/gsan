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
package gcom.relatorio.faturamento.conta;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */
public class RelatorioContasCanceladasBean implements RelatorioBean {

    private static final long serialVersionUID = 1L;
	
	private String cancelamento;
	private String idResponsavel;
	private String endereco;
	private String referencia;
	private String idMotivo;
	private String valorCancelado;
	private String idRA;
	private String inscricao;
	private String idLocalidade;
	private String nomeLocalidade;
	private String matricula;
	private String codigoSetorComercial;
	private String unidadeNegocio;
	private String idUnidadeNegocio;
	private String idGerenciaRegional;
	private String gerenciaRegional;
	private String idGrupo;
	private String grupo;
	/**TODO: COSANPA
	 * Mantis 581: Incluir opção de contas prescritas no relatório de contas
	 * canceladas/retificadas.
	 * @author Wellington Rocha
	 * @date 18/07/2012*/
	private String icRelatorioDeContasPrescritas;
	
	private String ano;	
	private BigDecimal valorCanceladasAno;
	private int quantidadeContasAno;
	
	public RelatorioContasCanceladasBean(){		
	}
	
	public RelatorioContasCanceladasBean(
			String cancelamento,
			String idResponsavel,
			String endereco,
			String referencia,
			String idMotivo,
			String valorCancelado,
			String idRA,
			String inscricao,
			String idLocalidade,
			String nomeLocalidade,
			String matricula) {
		
		this.cancelamento = cancelamento;
		this.idResponsavel = idResponsavel;
		this.endereco = endereco;
		this.referencia = referencia;
		this.idMotivo = idMotivo;
		this.valorCancelado = valorCancelado;
		this.idRA = idRA;
		this.nomeLocalidade = nomeLocalidade;
		this.inscricao = inscricao;
		this.idLocalidade = idLocalidade;
		this.matricula = matricula;
	}

	public String getIcRelatorioDeContasPrescritas() {
		return icRelatorioDeContasPrescritas;
	}

	public void setIcRelatorioDeContasPrescritas(
			String icRelatorioDeContasPrescritas) {
		this.icRelatorioDeContasPrescritas = icRelatorioDeContasPrescritas;
	}
	
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}


	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}


	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}


	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}


	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}


	public String getIdRA() {
		return idRA;
	}


	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}


	public String getValorCancelado() {
		return valorCancelado;
	}


	public void setValorCancelado(String valorCancelado) {
		this.valorCancelado = valorCancelado;
	}


	public String getCancelamento() {
		return cancelamento;
	}

	public void setCancelamento(String cancelamento) {
		this.cancelamento = cancelamento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(String idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}


	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public BigDecimal getValorCanceladasAno() {
		return valorCanceladasAno;
	}

	public void setValorCanceladasAno(BigDecimal valorCanceladasAno) {
		this.valorCanceladasAno = valorCanceladasAno;
	}

	public int getQuantidadeContasAno() {
		return quantidadeContasAno;
	}

	public void setQuantidadeContasAno(int quantidadeContasAno) {
		this.quantidadeContasAno = quantidadeContasAno;
	}
	
}

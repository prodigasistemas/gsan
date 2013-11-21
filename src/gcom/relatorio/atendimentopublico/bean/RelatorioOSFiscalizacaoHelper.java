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
package gcom.relatorio.atendimentopublico.bean;


public class RelatorioOSFiscalizacaoHelper {
	
	private String sequencial;
	private String inscricao;
	private String nomeClienteUsuario;
	private String grupo;
	private String matricula;
	private String dataEmissao;
	private String categoriaRES;
	private String categoriaPUB;
	private String categoriaIND;
	private String categoriaCOM;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String ordemServico;
	private String dataValidade;
	private String rota;
	private String sequencialRota;
	private String mesAno;
	private String enderecoImovel;
	private String numeroOrdemExecutada;
	private String tipoServico;
	private String dataExecucao;
	private String motivoEncerramento;
	private String tipoPavimento;
	private String qtdeRecomposta;
	private String numeroHidrometro;
	private String leituraHidrometro;
	
	public String getCategoriaCOM() {
		return categoriaCOM;
	}
	public void setCategoriaCOM(String categoriaCOM) {
		this.categoriaCOM = categoriaCOM;
	}
	public String getCategoriaIND() {
		return categoriaIND;
	}
	public void setCategoriaIND(String categoriaIND) {
		this.categoriaIND = categoriaIND;
	}
	public String getCategoriaPUB() {
		return categoriaPUB;
	}
	public void setCategoriaPUB(String categoriaPUB) {
		this.categoriaPUB = categoriaPUB;
	}
	public String getCategoriaRES() {
		return categoriaRES;
	}
	public void setCategoriaRES(String categoriaRES) {
		this.categoriaRES = categoriaRES;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public String getEnderecoImovel() {
		return enderecoImovel;
	}
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getLeituraHidrometro() {
		return leituraHidrometro;
	}
	public void setLeituraHidrometro(String leituraHidrometro) {
		this.leituraHidrometro = leituraHidrometro;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getNumeroOrdemExecutada() {
		return numeroOrdemExecutada;
	}
	public void setNumeroOrdemExecutada(String numeroOrdemExecutada) {
		this.numeroOrdemExecutada = numeroOrdemExecutada;
	}
	public String getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}
	public String getQtdeRecomposta() {
		String retorno = "";
		if(!qtdeRecomposta.equals("0,00")){
			retorno = qtdeRecomposta;
		}
		return retorno;
	}
	public void setQtdeRecomposta(String qtdeRecomposta) {
		this.qtdeRecomposta = qtdeRecomposta;
	}
	public String getRota() {
		return rota;
	}
	public void setRota(String rota) {
		this.rota = rota;
	}
	public String getSequencial() {
		return sequencial;
	}
	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}
	public String getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getTipoPavimento() {
		return tipoPavimento;
	}
	public void setTipoPavimento(String tipoPavimento) {
		this.tipoPavimento = tipoPavimento;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	

}

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
package gcom.cadastro.atualizacaocadastral.bean;

import java.util.Date;


/**
 * Consultar Movimento Atualização Cadastral 
 * 
 * @author Ana Maria
 * @date 03/06/2009
 */
public class ConsultarMovimentoAtualizacaoCadastralHelper {

	private Integer icAutorizado;
	
	private Integer argumento;
	
	private Integer idImovel;
	
	private Integer idCliente;
	
	private Integer qtdAlteracaoImovel;
	
	private Integer qtdAlteracaoCliente;
	
	private String nomeFuncionario;
	
	private String nomeCliente;
	
	private Date dataRealizacao;
	
	private String nomeEmpresa;
	
	private Integer idArquivo;
	
	private String inscricao;
	
	private String tipoClienteNovo;
	
	private Integer idRegistroAlterado;
	
	private Integer idTipoAlteracao;
	
	public ConsultarMovimentoAtualizacaoCadastralHelper(Integer icAutorizado, Integer argumento, Integer idImovel, Integer idCliente, Integer qtdAlteracaoImovel, Integer qtdAlteracaoCliente, String nomeFuncionario, String nomeCliente, Date dataRealizacao, String nomeEmpresa, Integer idArquivo, String inscricao, String tipoClienteNovo) {
		this.icAutorizado = icAutorizado;
		this.argumento = argumento;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.qtdAlteracaoImovel = qtdAlteracaoImovel;
		this.qtdAlteracaoCliente = qtdAlteracaoCliente;
		this.nomeFuncionario = nomeFuncionario;
		this.nomeCliente = nomeCliente;
		this.dataRealizacao = dataRealizacao;
		this.nomeEmpresa = nomeEmpresa;
		this.idArquivo = idArquivo;
		this.inscricao = inscricao;
		this.tipoClienteNovo = tipoClienteNovo;
	}

	public ConsultarMovimentoAtualizacaoCadastralHelper(){}

	public Integer getArgumento() {
		return argumento;
	}

	public void setArgumento(Integer argumento) {
		this.argumento = argumento;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Integer getQtdAlteracaoCliente() {
		return qtdAlteracaoCliente;
	}

	public void setQtdAlteracaoCliente(Integer qtdAlteracaoCliente) {
		this.qtdAlteracaoCliente = qtdAlteracaoCliente;
	}

	public Integer getQtdAlteracaoImovel() {
		return qtdAlteracaoImovel;
	}

	public void setQtdAlteracaoImovel(Integer qtdAlteracaoImovel) {
		this.qtdAlteracaoImovel = qtdAlteracaoImovel;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}	
		
	public Integer getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
	}

	public Integer getIcAutorizado() {
		return icAutorizado;
	}

	public void setIcAutorizado(Integer icAutorizado) {
		this.icAutorizado = icAutorizado;
	}
	
	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getTipoClienteNovo() {
		return tipoClienteNovo;
	}

	public void setTipoClienteNovo(String tipoClienteNovo) {
		this.tipoClienteNovo = tipoClienteNovo;
	}

	public Integer getIdRegistroAlterado() {
		return idRegistroAlterado;
	}

	public void setIdRegistroAlterado(Integer idRegistroAlterado) {
		this.idRegistroAlterado = idRegistroAlterado;
	}

	public Integer getIdTipoAlteracao() {
		return idTipoAlteracao;
	}

	public void setIdTipoAlteracao(Integer idTipoAlteracao) {
		this.idTipoAlteracao = idTipoAlteracao;
	}
	
	
	
}

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
package gcom.faturamento.bean;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Encapsula as informações necessárias para retificar contas de um conjunto de imóveis
 *
 * @author Raphael Rossiter
 * 
 * @date 02/07/2009
 */
public class RetificarConjuntoContaConsumosHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection colecaoImovel;
	
	private Integer idGrupoFaturamento;
	
	private Integer codigoCliente;
	
	private Integer codigoClienteSuperior;
	
	private Integer anoMes; 
	
	private Integer anoMesFim;
	
	private ContaMotivoRetificacao contaMotivoRetificacao;
	
	private Usuario usuarioLogado;
	
	private Date dataVencimentoContaInicio; 
	
	private Date dataVencimentoContaFim;
	
	private String indicadorContaPaga;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	
	private Integer consumoAgua;
	
	private Integer volumeEsgoto;
	
	private Date dataVencimentoContaRetificacao;
	
	private Short indicadorCategoriaEconomiaConta;
	
	public RetificarConjuntoContaConsumosHelper(){}

	public Integer getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(Integer anoMes) {
		this.anoMes = anoMes;
	}

	public Integer getAnoMesFim() {
		return anoMesFim;
	}

	public void setAnoMesFim(Integer anoMesFim) {
		this.anoMesFim = anoMesFim;
	}

	public Collection getColecaoImovel() {
		return colecaoImovel;
	}

	public void setColecaoImovel(Collection colecaoImovel) {
		this.colecaoImovel = colecaoImovel;
	}

	public ContaMotivoRetificacao getContaMotivoRetificacao() {
		return contaMotivoRetificacao;
	}

	public void setContaMotivoRetificacao(
			ContaMotivoRetificacao contaMotivoRetificacao) {
		this.contaMotivoRetificacao = contaMotivoRetificacao;
	}

	public Date getDataVencimentoContaFim() {
		return dataVencimentoContaFim;
	}

	public void setDataVencimentoContaFim(Date dataVencimentoContaFim) {
		this.dataVencimentoContaFim = dataVencimentoContaFim;
	}

	public Date getDataVencimentoContaInicio() {
		return dataVencimentoContaInicio;
	}

	public void setDataVencimentoContaInicio(Date dataVencimentoContaInicio) {
		this.dataVencimentoContaInicio = dataVencimentoContaInicio;
	}

	public String getIndicadorContaPaga() {
		return indicadorContaPaga;
	}

	public void setIndicadorContaPaga(String indicadorContaPaga) {
		this.indicadorContaPaga = indicadorContaPaga;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getVolumeEsgoto() {
		return volumeEsgoto;
	}

	public void setVolumeEsgoto(Integer volumeEsgoto) {
		this.volumeEsgoto = volumeEsgoto;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Date getDataVencimentoContaRetificacao() {
		return dataVencimentoContaRetificacao;
	}

	public void setDataVencimentoContaRetificacao(
			Date dataVencimentoContaRetificacao) {
		this.dataVencimentoContaRetificacao = dataVencimentoContaRetificacao;
	}

	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	public void setCodigoClienteSuperior(Integer codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}

	public Short getIndicadorCategoriaEconomiaConta() {
		return indicadorCategoriaEconomiaConta;
	}

	public void setIndicadorCategoriaEconomiaConta(
			Short indicadorCategoriaEconomiaConta) {
		this.indicadorCategoriaEconomiaConta = indicadorCategoriaEconomiaConta;
	}
}

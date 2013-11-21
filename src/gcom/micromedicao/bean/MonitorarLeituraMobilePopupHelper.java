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
 * Rômulo Aurélio de Melo Souza Filho
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 * Yara Taciane de Souza
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

package gcom.micromedicao.bean;

import gcom.micromedicao.consumo.ConsumoHistorico;

import java.io.Serializable;
import java.util.Collection;

/**
 *[UC0932] Monitorar Leituras Transmitidas
 *
 * Classe de apoio para o caso de uso acima
 * 
 * @author bruno
 *
 */

public class MonitorarLeituraMobilePopupHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String inscricao;
	private String idImovel;
	private String sequencialRota;
	private String leituraAnterior;
	private String leituraAtual;
	private String idAnormalidade;
	private String dtLeitura;
	private String dtRecebimento;
	private String icEmissaoConta;
	private String motivoNaoEmissao;	
	private String dadosCliente;
	private Collection<ConsumoHistorico> colConsumos;
	
	public String getDtLeitura() {
		return dtLeitura;
	}
	public void setDtLeitura(String dtLeitura) {
		this.dtLeitura = dtLeitura;
	}
	public String getDtRecebimento() {
		return dtRecebimento;
	}
	public void setDtRecebimento(String dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}
	public String getIcEmissaoConta() {
		return icEmissaoConta;
	}
	public void setIcEmissaoConta(String icEmissaoConta) {
		this.icEmissaoConta = icEmissaoConta;
	}
	public String getIdAnormalidade() {
		return idAnormalidade;
	}
	public void setIdAnormalidade(String idAnormalidade) {
		this.idAnormalidade = idAnormalidade;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getLeituraAnterior() {
		return leituraAnterior;
	}
	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}
	public String getLeituraAtual() {
		return leituraAtual;
	}
	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}
	public String getMotivoNaoEmissao() {
		return motivoNaoEmissao;
	}
	public void setMotivoNaoEmissao(String motivoNaoEmissao) {
		this.motivoNaoEmissao = motivoNaoEmissao;
	}
	public String getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	public String getDadosCliente() {
		return dadosCliente;
	}
	public void setDadosCliente(String dadosCliente) {
		this.dadosCliente = dadosCliente;
	}
	public Collection<ConsumoHistorico> getColConsumos() {
		return colConsumos;
	}
	public void setColConsumos(Collection<ConsumoHistorico> colConsumos) {
		this.colConsumos = colConsumos;
	}					

	
	
}

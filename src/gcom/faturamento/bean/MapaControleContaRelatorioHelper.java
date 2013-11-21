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

import java.io.Serializable;
import java.math.BigDecimal;


public class MapaControleContaRelatorioHelper implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer idEmpresa;
	private String nomeEmpresa;
	private Integer idTipoConta;
	private String descricaoTipoConta;
	private Integer idLocalidade;
	private Integer codigoSetor;
	private Integer idFaturamentoGrupo;
	private Integer sequencialInicial;
	private Integer sequencialFinal;
	private BigDecimal somaValorAgua;
	private BigDecimal somaValorEsgoto;
	private BigDecimal somaValordebito;
	private BigDecimal somaValorCredito;
	private Integer idEsferaPoder;
	private Integer qtdeContas;
	private Integer qtdTotalMacro;
		
	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public MapaControleContaRelatorioHelper(){}

	public Integer getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(Integer codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public String getDescricaoTipoConta() {
		return descricaoTipoConta;
	}

	public void setDescricaoTipoConta(String descricaoTipoConta) {
		this.descricaoTipoConta = descricaoTipoConta;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdTipoConta() {
		return idTipoConta;
	}

	public void setIdTipoConta(Integer idTipoConta) {
		this.idTipoConta = idTipoConta;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Integer getSequencialFinal() {
		return sequencialFinal;
	}

	public void setSequencialFinal(Integer sequencialFinal) {
		this.sequencialFinal = sequencialFinal;
	}

	public Integer getSequencialInicial() {
		return sequencialInicial;
	}

	public void setSequencialInicial(Integer sequencialInicial) {
		this.sequencialInicial = sequencialInicial;
	}

	public BigDecimal getSomaValorAgua() {
		return somaValorAgua;
	}

	public void setSomaValorAgua(BigDecimal somaValorAgua) {
		this.somaValorAgua = somaValorAgua;
	}

	public BigDecimal getSomaValorCredito() {
		return somaValorCredito;
	}

	public void setSomaValorCredito(BigDecimal somaValorCredito) {
		this.somaValorCredito = somaValorCredito;
	}

	public BigDecimal getSomaValordebito() {
		return somaValordebito;
	}

	public void setSomaValordebito(BigDecimal somaValordebito) {
		this.somaValordebito = somaValordebito;
	}

	public BigDecimal getSomaValorEsgoto() {
		return somaValorEsgoto;
	}

	public void setSomaValorEsgoto(BigDecimal somaValorEsgoto) {
		this.somaValorEsgoto = somaValorEsgoto;
	}
	
	public BigDecimal getValor(){
		BigDecimal valor = BigDecimal.ZERO;
		
		valor = valor.add(this.somaValorAgua);
		valor = valor.add(this.somaValordebito);
		valor = valor.add(this.somaValorEsgoto);
		valor = valor.subtract(this.somaValorCredito);
		
		return valor;
	}
	
	public Integer getQtdContas(){
		Integer qtd = new Integer(0);
		
		if(this.sequencialFinal != null && this.sequencialInicial != null){
			qtd = (this.sequencialFinal.intValue() - this.sequencialInicial) + 1;
		}else{
			qtd = 0;
		}
		
		return qtd;
	}

	/**
	 * @return Retorna o campo qtdeContas.
	 */
	public Integer getQtdeContas() {
		return qtdeContas;
	}

	/**
	 * @param qtdeContas O qtdeContas a ser setado.
	 */
	public void setQtdeContas(Integer qtdeContas) {
		this.qtdeContas = qtdeContas;
	}

	
	public Integer getQtdTotalMacro() {
		return qtdTotalMacro;
	}

	public void setQtdTotalMacro(Integer qtdTotalMacro) {
		this.qtdTotalMacro = qtdTotalMacro;
	}
	
	
	
	
}

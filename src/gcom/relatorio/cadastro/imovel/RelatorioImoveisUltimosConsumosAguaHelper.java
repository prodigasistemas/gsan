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
package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;

/**
 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de imoveis com os ultimos consumos 
 * 
 * @author Rafael Pinto
 * @date 18/12/2007
 */
public class RelatorioImoveisUltimosConsumosAguaHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	
	private Integer unidadeNegocio;
	private String nomeUnidadeNegocio;
	
	private Integer gerenciaRegional;
	private String nomeGerenciaRegional;
	
	private Integer localidade;
	private String descricaoLocalidade;
	
	private Integer setorComercial;
	private String descricaoSetorComercial;
	
	private Short rota;
	private Integer sequencialRota;
	
	private String nomeCliente;
	private String endereco;
	private String matriculaImovel;

	private Integer subCategoria;
	private Short economias;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	private String consumoAgua1;
	private String consumoAgua2;
	private String consumoAgua3;
	private String consumoAgua4;
	private String consumoAgua5;
	private String consumoAgua6;
	
	private String consumoAgua7;
	private String consumoAgua8;
	private String consumoAgua9;
	private String consumoAgua10;
	private String consumoAgua11;
	private String consumoAgua12;
	
	private String descricaoConsumo1;
	private String descricaoConsumo2;
	private String descricaoConsumo3;
	private String descricaoConsumo4;
	private String descricaoConsumo5;
	private String descricaoConsumo6;
	
	private String descricaoConsumo7;
	private String descricaoConsumo8;
	private String descricaoConsumo9;
	private String descricaoConsumo10;
	private String descricaoConsumo11;
	private String descricaoConsumo12;
	
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}
	
	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	
	public Integer getLocalidade() {
		return localidade;
	}
	
	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	
	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}
	
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}
	
	public Short getRota() {
		return rota;
	}
	
	public void setRota(Short rota) {
		this.rota = rota;
	}
	
	public Integer getSequencialRota() {
		return sequencialRota;
	}
	
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	
	public Integer getSetorComercial() {
		return setorComercial;
	}
	
	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
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
	
	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}
	
	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Short getEconomias() {
		return economias;
	}

	public void setEconomias(Short economias) {
		this.economias = economias;
	}

	public Integer getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(Integer subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getConsumoAgua1() {
		return consumoAgua1;
	}

	public void setConsumoAgua1(String consumoAgua1) {
		this.consumoAgua1 = consumoAgua1;
	}

	public String getConsumoAgua2() {
		return consumoAgua2;
	}

	public void setConsumoAgua2(String consumoAgua2) {
		this.consumoAgua2 = consumoAgua2;
	}

	public String getConsumoAgua3() {
		return consumoAgua3;
	}

	public void setConsumoAgua3(String consumoAgua3) {
		this.consumoAgua3 = consumoAgua3;
	}

	public String getConsumoAgua4() {
		return consumoAgua4;
	}

	public void setConsumoAgua4(String consumoAgua4) {
		this.consumoAgua4 = consumoAgua4;
	}

	public String getConsumoAgua5() {
		return consumoAgua5;
	}

	public void setConsumoAgua5(String consumoAgua5) {
		this.consumoAgua5 = consumoAgua5;
	}

	public String getConsumoAgua6() {
		return consumoAgua6;
	}

	public void setConsumoAgua6(String consumoAgua6) {
		this.consumoAgua6 = consumoAgua6;
	}

	public String getDescricaoConsumo1() {
		return descricaoConsumo1;
	}

	public void setDescricaoConsumo1(String descricaoConsumo1) {
		this.descricaoConsumo1 = descricaoConsumo1;
	}

	public String getDescricaoConsumo2() {
		return descricaoConsumo2;
	}

	public void setDescricaoConsumo2(String descricaoConsumo2) {
		this.descricaoConsumo2 = descricaoConsumo2;
	}

	public String getDescricaoConsumo3() {
		return descricaoConsumo3;
	}

	public void setDescricaoConsumo3(String descricaoConsumo3) {
		this.descricaoConsumo3 = descricaoConsumo3;
	}

	public String getDescricaoConsumo4() {
		return descricaoConsumo4;
	}

	public void setDescricaoConsumo4(String descricaoConsumo4) {
		this.descricaoConsumo4 = descricaoConsumo4;
	}

	public String getDescricaoConsumo5() {
		return descricaoConsumo5;
	}

	public void setDescricaoConsumo5(String descricaoConsumo5) {
		this.descricaoConsumo5 = descricaoConsumo5;
	}

	public String getDescricaoConsumo6() {
		return descricaoConsumo6;
	}

	public void setDescricaoConsumo6(String descricaoConsumo6) {
		this.descricaoConsumo6 = descricaoConsumo6;
	}

	public String getConsumoAgua10() {
		return consumoAgua10;
	}

	public void setConsumoAgua10(String consumoAgua10) {
		this.consumoAgua10 = consumoAgua10;
	}

	public String getConsumoAgua11() {
		return consumoAgua11;
	}

	public void setConsumoAgua11(String consumoAgua11) {
		this.consumoAgua11 = consumoAgua11;
	}

	public String getConsumoAgua12() {
		return consumoAgua12;
	}

	public void setConsumoAgua12(String consumoAgua12) {
		this.consumoAgua12 = consumoAgua12;
	}

	public String getConsumoAgua7() {
		return consumoAgua7;
	}

	public void setConsumoAgua7(String consumoAgua7) {
		this.consumoAgua7 = consumoAgua7;
	}

	public String getConsumoAgua8() {
		return consumoAgua8;
	}

	public void setConsumoAgua8(String consumoAgua8) {
		this.consumoAgua8 = consumoAgua8;
	}

	public String getConsumoAgua9() {
		return consumoAgua9;
	}

	public void setConsumoAgua9(String consumoAgua9) {
		this.consumoAgua9 = consumoAgua9;
	}

	public String getDescricaoConsumo10() {
		return descricaoConsumo10;
	}

	public void setDescricaoConsumo10(String descricaoConsumo10) {
		this.descricaoConsumo10 = descricaoConsumo10;
	}

	public String getDescricaoConsumo11() {
		return descricaoConsumo11;
	}

	public void setDescricaoConsumo11(String descricaoConsumo11) {
		this.descricaoConsumo11 = descricaoConsumo11;
	}

	public String getDescricaoConsumo12() {
		return descricaoConsumo12;
	}

	public void setDescricaoConsumo12(String descricaoConsumo12) {
		this.descricaoConsumo12 = descricaoConsumo12;
	}

	public String getDescricaoConsumo7() {
		return descricaoConsumo7;
	}

	public void setDescricaoConsumo7(String descricaoConsumo7) {
		this.descricaoConsumo7 = descricaoConsumo7;
	}

	public String getDescricaoConsumo8() {
		return descricaoConsumo8;
	}

	public void setDescricaoConsumo8(String descricaoConsumo8) {
		this.descricaoConsumo8 = descricaoConsumo8;
	}

	public String getDescricaoConsumo9() {
		return descricaoConsumo9;
	}

	public void setDescricaoConsumo9(String descricaoConsumo9) {
		this.descricaoConsumo9 = descricaoConsumo9;
	}

	
	
	
}
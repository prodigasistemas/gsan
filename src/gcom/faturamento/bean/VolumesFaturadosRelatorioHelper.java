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

/**
 * Helper utilizado pelos relatórios de volumes faturados
 * 
 * @author Rafael Corrêa
 * @since 11/09/2007
 */
public class VolumesFaturadosRelatorioHelper {

	private Integer idImovel;

	private Integer idLocalidade;

	private String nomeLocalidade;

	private Integer idSetorComercial;

	private Integer codigoSetorComercial;

	private String nomeSetorComercial;

	private Integer idQuadra;

	private Integer numeroQuadra;

	private String nomeUsuario;

	private Integer idSituacaoAgua;

	private Integer idSituacaoEsgoto;

	private Integer mediaConsumo;

	private Integer consumoMesAno1;

	private Integer consumoMesAno2;

	private Integer consumoMesAno3;

	private Integer consumoMesAno4;

	private Integer consumoMesAno5;

	private Integer consumoMesAno6;

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getConsumoMesAno1() {
		return consumoMesAno1;
	}

	public void setConsumoMesAno1(Integer consumoMesAno1) {
		this.consumoMesAno1 = consumoMesAno1;
	}

	public Integer getConsumoMesAno2() {
		return consumoMesAno2;
	}

	public void setConsumoMesAno2(Integer consumoMesAno2) {
		this.consumoMesAno2 = consumoMesAno2;
	}

	public Integer getConsumoMesAno3() {
		return consumoMesAno3;
	}

	public void setConsumoMesAno3(Integer consumoMesAno3) {
		this.consumoMesAno3 = consumoMesAno3;
	}

	public Integer getConsumoMesAno4() {
		return consumoMesAno4;
	}

	public void setConsumoMesAno4(Integer consumoMesAno4) {
		this.consumoMesAno4 = consumoMesAno4;
	}

	public Integer getConsumoMesAno5() {
		return consumoMesAno5;
	}

	public void setConsumoMesAno5(Integer consumoMesAno5) {
		this.consumoMesAno5 = consumoMesAno5;
	}

	public Integer getConsumoMesAno6() {
		return consumoMesAno6;
	}

	public void setConsumoMesAno6(Integer consumoMesAno6) {
		this.consumoMesAno6 = consumoMesAno6;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public Integer getIdSituacaoAgua() {
		return idSituacaoAgua;
	}

	public void setIdSituacaoAgua(Integer idSituacaoAgua) {
		this.idSituacaoAgua = idSituacaoAgua;
	}

	public Integer getIdSituacaoEsgoto() {
		return idSituacaoEsgoto;
	}

	public void setIdSituacaoEsgoto(Integer idSituacaoEsgoto) {
		this.idSituacaoEsgoto = idSituacaoEsgoto;
	}

	public Integer getMediaConsumo() {
		return mediaConsumo;
	}

	public void setMediaConsumo(Integer mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

}

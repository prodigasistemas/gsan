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
package gcom.cadastro.imovel.bean;

public class GerarRelatorioAnaliseImovelCorporativoGrandeHelper {

	private static final long serialVersionUID = 1L;


	private Integer idUnidadeNegocio;

	private String nomeUnidadeNegocio;

	private Integer idGerenciaRegional;

	private String nomeGerenciaRegional;

	private Integer idLocalidade;

	private String nomeLocalidade;

	private Integer idImovel;

	private String capacidadeHidrometro;
	
	private Integer idSetorComercial;
	
	private Integer codigoSetorComercial;
	
	private Integer consumoMedio;
	
	private Integer consumoFaturado;
	
	private Integer idTipoLigacao;
	

	/**
	 * @return Retorna o campo idTipoLigacao.
	 */
	public Integer getIdTipoLigacao() {
		return idTipoLigacao;
	}

	/**
	 * @param idTipoLigacao O idTipoLigacao a ser setado.
	 */
	public void setIdTipoLigacao(Integer idTipoLigacao) {
		this.idTipoLigacao = idTipoLigacao;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional
	 *            O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade
	 *            O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio
	 *            O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	/**
	 * @param nomeGerenciaRegional
	 *            O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	/**
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade
	 *            O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Retorna o campo nomeUnidadeNegocio.
	 */
	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	/**
	 * @param nomeUnidadeNegocio
	 *            O nomeUnidadeNegocio a ser setado.
	 */
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public Integer getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(Integer consumoMedio) {
		this.consumoMedio = consumoMedio;
	}


	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}


	/**
	 * @return Retorna o campo capacidadeHidrometro.
	 */
	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	/**
	 * @param capacidadeHidrometro O capacidadeHidrometro a ser setado.
	 */
	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	/**
	 * @return Retorna o campo consumoFaturado.
	 */
	public Integer getConsumoFaturado() {
		return consumoFaturado;
	}

	/**
	 * @param consumoFaturado O consumoFaturado a ser setado.
	 */
	public void setConsumoFaturado(Integer consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}



}

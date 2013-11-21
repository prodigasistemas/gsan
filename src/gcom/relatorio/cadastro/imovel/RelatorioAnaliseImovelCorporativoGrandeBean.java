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

import gcom.relatorio.RelatorioBean;

/**
 * Este caso de uso gera relatório de análise do imóvel corporativo ou grande
 * 
 * @author Ana Maria
 * @date 06/01/09
 * 
 */
public class RelatorioAnaliseImovelCorporativoGrandeBean implements RelatorioBean {


	private String unidadeNegocio;
	
	private String gerenciaRegional;
	
	private String localidade;
	
	private String idSetorComercial;
	
	private String setorComercial;
	
	private String inscricao;

	private String imovel;
	
	private String endereco;
	
	private String capacidadeHidrometro;

	private String consumoMedio;
	
	private String consumoFaturado;
	
	private String tipoLigacao;

	

	public RelatorioAnaliseImovelCorporativoGrandeBean(String unidadeNegocio, 
			String gerenciaRegional, String localidade, String idSetorComercial, 
			String setorComercial, String inscricao, String imovel, String endereco, 
			String capacidadeHidrometro, String consumoMedio, String consumoFaturado,
			String tipoLigacao){

		this.unidadeNegocio = unidadeNegocio;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.idSetorComercial = idSetorComercial;
		this.setorComercial = setorComercial;
		this.inscricao = inscricao;
		this.imovel = imovel;
		this.endereco = endereco;
		this.capacidadeHidrometro = capacidadeHidrometro;
		this.consumoMedio = consumoMedio;
		this.consumoFaturado = consumoFaturado;
		this.tipoLigacao = tipoLigacao;
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
	public String getConsumoFaturado() {
		return consumoFaturado;
	}

	/**
	 * @param consumoFaturado O consumoFaturado a ser setado.
	 */
	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	/**
	 * @return Retorna o campo consumoMedio.
	 */
	public String getConsumoMedio() {
		return consumoMedio;
	}

	/**
	 * @param consumoMedio O consumoMedio a ser setado.
	 */
	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo imovel.
	 */
	public String getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo setorComercial.
	 */
	public String getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo tipoLigacao.
	 */
	public String getTipoLigacao() {
		return tipoLigacao;
	}

	/**
	 * @param tipoLigacao O tipoLigacao a ser setado.
	 */
	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}


}

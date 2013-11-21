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
package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioComparativoLeiturasEAnormalidadesBean implements RelatorioBean {

	private String grupo;

	private String empresa;

	private String idSetor;

	private String localidade;

	private String setor;
	
	private String rota;
	
	private String registrosRecebidos;

	private String registrosComLeitura;

	private String registrosComAnormalidade;

	private String registrosComLeituraEAnormalidade;
	
	private String anormalidadeLeitura;
	
	private String qtdeAnormalidadeLeitura;
	
	private String registrosEnviados;
	
	private String registrosRecebidosConvencional;
	
	private String registrosRecebidosSimultaneo;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioComparativoLeiturasEAnormalidadesBean(
			String grupo,
			String empresa, 
			String idSetor, 
			String localidade, 
			String setor,
			String registrosRecebidos, 
			String registrosComLeitura,
			String registrosComAnormalidade,
			String registrosComLeituraEAnormalidade,
			String anormalidadeLeitura, 
			String qtdeAnormalidadeLeitura, 
			String registrosEnviados,
			String rota,
			String registrosRecebidosConvencional,
			String registrosRecebidosSimultaneo) {
		
		this.grupo = grupo;
		this.empresa = empresa;
		this.idSetor = idSetor;
		this.localidade = localidade;
		this.setor = setor;
		this.registrosRecebidos = registrosRecebidos;
		this.registrosComLeitura = registrosComLeitura;
		this.registrosComAnormalidade = registrosComAnormalidade;
		this.registrosComLeituraEAnormalidade = registrosComLeituraEAnormalidade;
		this.anormalidadeLeitura = anormalidadeLeitura;
		this.qtdeAnormalidadeLeitura = qtdeAnormalidadeLeitura;
		this.registrosEnviados = registrosEnviados;
		this.rota = rota;
		this.registrosRecebidosConvencional = registrosRecebidosConvencional;
		this.registrosRecebidosSimultaneo = registrosRecebidosSimultaneo;
		
	}

	/**
	 * @return Retorna o campo anormalidadeLeitura.
	 */
	public String getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}

	/**
	 * @param anormalidadeLeitura O anormalidadeLeitura a ser setado.
	 */
	public void setAnormalidadeLeitura(String anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo grupo.
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo O grupo a ser setado.
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
	 * @return Retorna o campo localidadeSetor.
	 */
	public String getIdSetor() {
		return idSetor;
	}

	/**
	 * @param localidadeSetor O localidadeSetor a ser setado.
	 */
	public void setIdSetor(String idSetor) {
		this.idSetor = idSetor;
	}

	/**
	 * @return Retorna o campo qtdeAnormalidadeLeitura.
	 */
	public String getQtdeAnormalidadeLeitura() {
		return qtdeAnormalidadeLeitura;
	}

	/**
	 * @param qtdeAnormalidadeLeitura O qtdeAnormalidadeLeitura a ser setado.
	 */
	public void setQtdeAnormalidadeLeitura(String qtdeAnormalidadeLeitura) {
		this.qtdeAnormalidadeLeitura = qtdeAnormalidadeLeitura;
	}

	/**
	 * @return Retorna o campo registrosComAnormalidade.
	 */
	public String getRegistrosComAnormalidade() {
		return registrosComAnormalidade;
	}

	/**
	 * @param registrosComAnormalidade O registrosComAnormalidade a ser setado.
	 */
	public void setRegistrosComAnormalidade(String registrosComAnormalidade) {
		this.registrosComAnormalidade = registrosComAnormalidade;
	}

	/**
	 * @return Retorna o campo registrosComLeitura.
	 */
	public String getRegistrosComLeitura() {
		return registrosComLeitura;
	}

	/**
	 * @param registrosComLeitura O registrosComLeitura a ser setado.
	 */
	public void setRegistrosComLeitura(String registrosComLeitura) {
		this.registrosComLeitura = registrosComLeitura;
	}

	/**
	 * @return Retorna o campo registrosComLeituraEAnormalidade.
	 */
	public String getRegistrosComLeituraEAnormalidade() {
		return registrosComLeituraEAnormalidade;
	}

	/**
	 * @param registrosComLeituraEAnormalidade O registrosComLeituraEAnormalidade a ser setado.
	 */
	public void setRegistrosComLeituraEAnormalidade(
			String registrosComLeituraEAnormalidade) {
		this.registrosComLeituraEAnormalidade = registrosComLeituraEAnormalidade;
	}

	/**
	 * @return Retorna o campo registrosRecebidos.
	 */
	public String getRegistrosRecebidos() {
		return registrosRecebidos;
	}

	/**
	 * @param registrosRecebidos O registrosRecebidos a ser setado.
	 */
	public void setRegistrosRecebidos(String registrosRecebidos) {
		this.registrosRecebidos = registrosRecebidos;
	}

	/**
	 * @return Retorna o campo setor.
	 */
	public String getSetor() {
		return setor;
	}

	/**
	 * @param setor O setor a ser setado.
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}

	/**
	 * @return Retorna o campo totalRegistrosComLeituraEnviados.
	 */
	public String getRegistrosEnviados() {
		return registrosEnviados;
	}

	/**
	 * @param totalRegistrosComLeituraEnviados O totalRegistrosComLeituraEnviados a ser setado.
	 */
	public void setRegistrosEnviados(
			String registrosEnviados) {
		this.registrosEnviados = registrosEnviados;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getRegistrosRecebidosConvencional() {
		return registrosRecebidosConvencional;
	}

	public void setRegistrosRecebidosConvencional(
			String registrosRecebidosConvencional) {
		this.registrosRecebidosConvencional = registrosRecebidosConvencional;
	}

	public String getRegistrosRecebidosSimultaneo() {
		return registrosRecebidosSimultaneo;
	}

	public void setRegistrosRecebidosSimultaneo(String registrosRecebidosSimultaneo) {
		this.registrosRecebidosSimultaneo = registrosRecebidosSimultaneo;
	}

	
}

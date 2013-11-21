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
package gcom.micromedicao.bean;



/**
 * @author Raphael Rossiter
 * @date 03/03/2007
 */
public class ComparativoLeiturasEAnormalidadesRelatorioHelper {
	
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Short codigoRota;
	//private Integer idGrupoFaturamento;
	//private Integer idEmpresa;
	//private String nomeEmpresa;
	private Integer registrosEnviados;
	private Integer registrosRecebidos;
	private Integer registrosComLeitura;
	private Integer registrosComAnormalidade;
	private Integer registrosComLeituraEAnormalidade;
	
	private Integer registrosRecebidosConvencional;
	private Integer registrosRecebidosSimultaneo;
	
	public ComparativoLeiturasEAnormalidadesRelatorioHelper(){}

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
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
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
	 * @return Retorna o campo registroComAnormalidade.
	 */
	public Integer getRegistrosComAnormalidade() {
		return registrosComAnormalidade;
	}

	/**
	 * @param registroComAnormalidade O registroComAnormalidade a ser setado.
	 */
	public void setRegistrosComAnormalidade(Integer registrosComAnormalidade) {
		this.registrosComAnormalidade = registrosComAnormalidade;
	}

	/**
	 * @return Retorna o campo registrosComLeitura.
	 */
	public Integer getRegistrosComLeitura() {
		return registrosComLeitura;
	}

	/**
	 * @param registrosComLeitura O registrosComLeitura a ser setado.
	 */
	public void setRegistrosComLeitura(Integer registrosComLeitura) {
		this.registrosComLeitura = registrosComLeitura;
	}

	/**
	 * @return Retorna o campo registrosComLeituraEAnormalidade.
	 */
	public Integer getRegistrosComLeituraEAnormalidade() {
		return registrosComLeituraEAnormalidade;
	}

	/**
	 * @param registrosComLeituraEAnormalidade O registrosComLeituraEAnormalidade a ser setado.
	 */
	public void setRegistrosComLeituraEAnormalidade(
			Integer registrosComLeituraEAnormalidade) {
		this.registrosComLeituraEAnormalidade = registrosComLeituraEAnormalidade;
	}

	/**
	 * @return Retorna o campo registrosRecebidos.
	 */
	public Integer getRegistrosRecebidos() {
		return registrosRecebidos;
	}

	/**
	 * @param registrosRecebidos O registrosRecebidos a ser setado.
	 */
	public void setRegistrosRecebidos(Integer registrosRecebidos) {
		this.registrosRecebidos = registrosRecebidos;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getRegistrosEnviados() {
		return registrosEnviados;
	}

	public void setRegistrosEnviados(Integer registrosEnviados) {
		this.registrosEnviados = registrosEnviados;
	}

	public Integer getRegistrosRecebidosConvencional() {
		return registrosRecebidosConvencional;
	}

	public void setRegistrosRecebidosConvencional(
			Integer registrosRecebidosConvencional) {
		this.registrosRecebidosConvencional = registrosRecebidosConvencional;
	}

	public Integer getRegistrosRecebidosSimultaneo() {
		return registrosRecebidosSimultaneo;
	}

	public void setRegistrosRecebidosSimultaneo(Integer registrosRecebidosSimultaneo) {
		this.registrosRecebidosSimultaneo = registrosRecebidosSimultaneo;
	}

	
}

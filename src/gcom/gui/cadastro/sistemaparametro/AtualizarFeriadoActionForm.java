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
package gcom.gui.cadastro.sistemaparametro;


import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.util.Util;

import org.apache.struts.validator.ValidatorForm;


/**
 * [UC0535] Manter Feriado [SB0001]Atualizar Feriado
 *
 * @author Kássia Albuquerque
 * @date 24/01/2007
 */

public class AtualizarFeriadoActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;

	private String indicadorTipoFeriado;
	
	private String codigoFeriado;
	
	private String idMunicipio;

	private String descricaoMunicipio;
	
	private String dataFeriado;
	
	private String descricaoFeriado;

	
	
	public String getCodigoFeriado() {
		return codigoFeriado;
	}

	public void setCodigoFeriado(String codigoFeriado) {
		this.codigoFeriado = codigoFeriado;
	}

	public String getDataFeriado() {
		return dataFeriado;
	}

	public void setDataFeriado(String dataFeriado) {
		this.dataFeriado = dataFeriado;
	}

	public String getDescricaoFeriado() {
		return descricaoFeriado;
	}

	public void setDescricaoFeriado(String descricaoFeriado) {
		this.descricaoFeriado = descricaoFeriado;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIndicadorTipoFeriado() {
		return indicadorTipoFeriado;
	}

	public void setIndicadorTipoFeriado(String indicadorTipoFeriado) {
		this.indicadorTipoFeriado = indicadorTipoFeriado;
	}
	
	
	
	
	public MunicipioFeriado setFormValuesMunicipal(MunicipioFeriado municipioFeriado) {
		
		if(getIdMunicipio() != null && !getIdMunicipio().equals("")){
		  Municipio municipio = new Municipio();
		  municipio.setId(Integer.parseInt(getIdMunicipio()));
		  municipioFeriado.setMunicipio(municipio);
		}
		
		municipioFeriado.setDataFeriado(Util.converteStringParaDate(getDataFeriado()));
		municipioFeriado.setDescricaoFeriado(getDescricaoFeriado());
				
		return municipioFeriado;
	}
	
	
	public NacionalFeriado setFormValuesNacional(NacionalFeriado nacionalFeriado) {
		
		nacionalFeriado.setData(Util.converteStringParaDate(getDataFeriado()));
		nacionalFeriado.setDescricao(getDescricaoFeriado());
				
		return nacionalFeriado;
	}
	

}

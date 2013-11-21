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
package gcom.tarefa;


/**
 * Classe que o representa um Tipo Parametro Tarefa
 * 
 * @author thiago
 * @date 24/01/2006
 */
public class TipoParametroTarefa {
	
	public static final TipoParametroTarefa TIPO_SHORT = new TipoParametroTarefa(0,"Short"); 
	public static final TipoParametroTarefa TIPO_BYTE = new TipoParametroTarefa(1,"Byte");
	public static final TipoParametroTarefa TIPO_CHARACTER  = new TipoParametroTarefa(2,"Character");
	public static final TipoParametroTarefa TIPO_INTEGER = new TipoParametroTarefa(3,"Integer");
	public static final TipoParametroTarefa TIPO_LONG = new TipoParametroTarefa(4,"Long");
	public static final TipoParametroTarefa TIPO_DOUBLE = new TipoParametroTarefa(5,"Double");
	public static final TipoParametroTarefa TIPO_FLOAT = new TipoParametroTarefa(6,"Float");
	public static final TipoParametroTarefa TIPO_STRING = new TipoParametroTarefa(7,"String");
	public static final TipoParametroTarefa TIPO_BOOLEAN = new TipoParametroTarefa(8,"Boolean");
	public static final TipoParametroTarefa TIPO_BIGDECIMAL = new TipoParametroTarefa(9,"BigDecimal");
	public static final TipoParametroTarefa TIPO_DATE = new TipoParametroTarefa(10,"Date");
	public static final TipoParametroTarefa TIPO_ARRAY_SHORT = new TipoParametroTarefa(11,"ArrayShort"); 
	public static final TipoParametroTarefa TIPO_ARRAY_BYTE = new TipoParametroTarefa(12,"ArrayByte");
	public static final TipoParametroTarefa TIPO_ARRAY_CHARACTER  = new TipoParametroTarefa(13,"ArrayCharacter");
	public static final TipoParametroTarefa TIPO_ARRAY_INTEGER = new TipoParametroTarefa(14,"ArrayInteger");
	public static final TipoParametroTarefa TIPO_ARRAY_LONG = new TipoParametroTarefa(15,"ArrayLong");
	public static final TipoParametroTarefa TIPO_ARRAY_DOUBLE = new TipoParametroTarefa(16,"ArrayDouble");
	public static final TipoParametroTarefa TIPO_ARRAY_FLOAT = new TipoParametroTarefa(17,"ArrayFloat");
	public static final TipoParametroTarefa TIPO_ARRAY_STRING = new TipoParametroTarefa(18,"ArrayString");
	public static final TipoParametroTarefa TIPO_ARRAY_BOOLEAN = new TipoParametroTarefa(19,"ArrayBoolean");
	public static final TipoParametroTarefa TIPO_ARRAY_BIGDECIMAL = new TipoParametroTarefa(20,"ArrayBigDecimal");
	public static final TipoParametroTarefa TIPO_ARRAY_DATE = new TipoParametroTarefa(21,"ArrayDate");
	public static final String SEPARADOR_ARRAY = "#@&";

	private Integer id;
	private String descricao;
	
	public TipoParametroTarefa(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public TipoParametroTarefa() {
	
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
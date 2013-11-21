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
package gcom.relatorio;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Datasource usado pelo Hibernate para geração de relatórios no JasperReports
 * 
 * @author rodrigo
 */
public class HibernateQueryResultDataSource implements JRDataSource {

    private String[] fields;

    private Iterator iterator;

    private Object currentValue;

    /**
     * Construtor da classe HibernateQueryResultDataSource
     * 
     * @param list
     *            Descrição do parâmetro
     * @param fields
     *            Descrição do parâmetro
     */
    public HibernateQueryResultDataSource(List list, String[] fields) {
        this.fields = fields;
        this.iterator = list.iterator();
    }

    /**
     * Retorna o valor de fieldValue
     * 
     * @param field
     *            Descrição do parâmetro
     * @return O valor de fieldValue
     * @exception JRException
     *                Descrição da exceção
     */
    public Object getFieldValue(JRField field) throws JRException {
        Object value = null;
        int index = getFieldIndex(field.getName());

        if (index > -1) {
            Object[] values = (Object[]) currentValue;

            value = values[index];
        }
        return value;
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception JRException
     *                Descrição da exceção
     */
    public boolean next() throws JRException {
        currentValue = iterator.hasNext() ? iterator.next() : null;
        return (currentValue != null);
    }

    /**
     * Retorna o valor de fieldIndex
     * 
     * @param field
     *            Descrição do parâmetro
     * @return O valor de fieldIndex
     */
    private int getFieldIndex(String field) {
        int index = -1;

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals(field)) {
                index = i;
                break;
            }
        }
        return index;
    }

}

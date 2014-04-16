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

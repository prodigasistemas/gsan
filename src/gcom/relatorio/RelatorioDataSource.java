package gcom.relatorio;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioDataSource implements JRDataSource {
	private List data;

	private int index = -1;

	public RelatorioDataSource(List data) {
		this.data = data;
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
		RelatorioBean lineItem = (RelatorioBean) data.get(index);

		Object value = null;

		try {
			Object[] args = {};
			Class[] paramTypes = {};
			Class lineItemClass = lineItem.getClass();
			Method getMethod = lineItemClass.getDeclaredMethod(resolverNomeCampo(field.getName()),
					paramTypes);

			value = getMethod.invoke(lineItem, args);
		} catch (Exception e) {
			throw new JRException(e.getMessage());
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
		// Incrementa o indice para buscar o item da coleção
		index++;

		return (index < data.size());
	}

	private String resolverNomeCampo(String nomeCampo) {

		if (!nomeCampo.startsWith("get")) {

			nomeCampo = "get" + nomeCampo.substring(0, 1).toUpperCase()
					+ nomeCampo.substring(1, nomeCampo.length());

		}
		return nomeCampo;

	}

}

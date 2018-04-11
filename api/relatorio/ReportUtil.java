package gcom.api.relatorio;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReportUtil {

	private static int HEADER = 1;
	private static int GROUP = 2;
	private static int TOTALIZER = 3;

	public List<ReportField> headerFieldsFromClass(Class<?> t) {
		return fieldsFromClass(t, HEADER);
	}

	public List<ReportField> groupFieldsFromClass(Class<?> t) {
		return fieldsFromClass(t, GROUP);
	}

	public List<ReportField> totalizerFieldsFromClass(Class<?> t) {
		return fieldsFromClass(t, TOTALIZER);
	}

	public List<ReportField> fieldsFromClass(Class<?> t, int fieldType) {
		Field[] campos = t.getDeclaredFields();
		List<ReportField> fields = new ArrayList<ReportField>();

		for (Field field : campos) {
			if (field.isAnnotationPresent(ReportElementType.class)) {
				ReportElementType campo = field.getAnnotation(ReportElementType.class);

				if (fieldType == HEADER && campo.header()) {
					fields.add(new ReportField(field.getName(), campo.description(), campo.align(), campo.type()));
				} else if (fieldType == GROUP && campo.group()) {
					fields.add(new ReportField(field.getName(), campo.description()));
				} else if (fieldType == TOTALIZER && campo.totalizer()) {
					fields.add(new ReportField(field.getName()));
				}
			}
		}

		return fields;
	}
}

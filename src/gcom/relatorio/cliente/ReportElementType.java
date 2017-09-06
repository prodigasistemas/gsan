package gcom.relatorio.cliente;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) 
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportElementType {
	public static final String TYPE_STRING = "string";
	public static final String TYPE_DATE = "date";
	public static final String TYPE_MONEY = "money";
	public static final String TYPE_INTEGER = "integer";
	
	String description() default "";
	boolean header() default true;
	boolean group() default false;
	boolean totalizer() default false;
	String align() default "left";
	String type() default "string";
}

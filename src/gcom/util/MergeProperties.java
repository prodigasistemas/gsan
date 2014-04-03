package gcom.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 
/**
 * 
 * @author Marcelo Garcia
 *
 */
public class MergeProperties {
  
 /**
  *   Realiza o merge dos valores do objeto origem para o objeto destino.
  * 
  *  Os metodos que sao capturados pelo algoritmo sao metodos GET  e 
  * que nao possuem parametro.
  *
  *      Os valores sao capturados a partir dos metodos GET existentes na 
  * classe origem. Isso quer dizer que caso o destino seja uma subclasse
  * de origem, os seus metodos nao serao reconhecidos.
 
  *  Somente valores nao nulos sao passados de origem para destino.
  * Campos string vazios e numericos zerados sao permitidos.
  *   
  * @param <c>
  * @param destino: Recebera os valores de origem
  * @param origem: Objeto cujas propriedades serao transferidas para o destino
  * @return o objeto modificado
  */
 public static <C> C mergeProperties(C destino, C origem) {
  Method[] metodos = origem.getClass().getMethods();
  String nomeMetodo;
  String nomeMetodoSemGet; 
 
  try {
   for (Method metodo : metodos) {
    nomeMetodo = metodo.getName();
    nomeMetodoSemGet = nomeMetodo.substring(3,nomeMetodo.length());
    //Testa se é um método get
    if (!nomeMetodo.substring(0,3).equals("get"))
     continue;
    if (metodo.getParameterTypes().length != 0)
     continue;
    Object valor = metodo.invoke(origem);
    if (valor == null)
     continue;
     //Captura o metodo SET de acordo com o tipo de retorno do metodo GET
     Method metodoSET = destino.getClass().getMethod("set"+nomeMetodoSemGet, metodo.getReturnType());
     if (metodo != null)
      metodoSET.invoke(destino, valor);
   }
  }
  catch (NoSuchMethodException e) {
   System.out.println("Metodo nao encontrado.");
  }
  catch (InvocationTargetException e) {
   System.out.println("Chamada de metodo incorreta.");
  }
  catch (IllegalAccessException e) {
   System.out.println("Metodo inacessivel.");
  }
  catch (IllegalArgumentException e) {
   System.out.println("Argumentos incorretos.");
  }
   
  return destino;
 }
 
 @SuppressWarnings("rawtypes")
public static <C> C mergeInterfaceProperties(C destino, C origem) {
	  Class[] interfaces = origem.getClass().getInterfaces();
	  
	  try {
		  for (Class interfaceClass : interfaces) {
			  Method[] metodos = interfaceClass.getMethods();
			  String nomeMetodo;
			  String nomeMetodoSemGet; 
	 
			   for (Method metodo : metodos) {
			    nomeMetodo = metodo.getName();
			    nomeMetodoSemGet = nomeMetodo.substring(3,nomeMetodo.length());
			    //Testa se é um método get
			    if (!nomeMetodo.substring(0,3).equals("get"))
			     continue;
			    if (metodo.getParameterTypes().length != 0)
			     continue;
			    Object valor = metodo.invoke(origem);
			    if (valor == null)
			     continue;
			     //Captura o metodo SET de acordo com o tipo de retorno do metodo GET
			     Method metodoSET = destino.getClass().getMethod("set"+nomeMetodoSemGet, metodo.getReturnType());
			     if (metodo != null)
			      metodoSET.invoke(destino, valor);
			   }
		  }
	  }
	  catch (NoSuchMethodException e) {
	   System.out.println("Metodo nao encontrado.");
	  }
	  catch (InvocationTargetException e) {
	   System.out.println("Chamada de metodo incorreta.");
	  }
	  catch (IllegalAccessException e) {
	   System.out.println("Metodo inacessivel.");
	  }
	  catch (IllegalArgumentException e) {
	   System.out.println("Argumentos incorretos.");
	  }
	   
	  return destino;
	 }
}
package gcom.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * Esta classe tem funções auxiliares de validação que podem ser usadas em todo
 * o sistema
 * 
 * @author scme
 */

public class IoUtil {

	/**
	 * Description of the Field
	 */
	public final static int CAMPO_TEXTO = 1;

	/**
	 * Description of the Field
	 */
	public final static int CAMPO_SENHA = 2;

	/**
	 * Description of the Field
	 */
	public final static int CAMPO_EMAIL = 3;

	/**
	 * Remove os caracteres especiais de uma dado texto
	 * 
	 * @param texto
	 *            O texto no qual serão removidos os caracteres especias
	 * @return O texto sem os caracteres especiais
	 */

	public static String removerCaracteresEspeciais(String texto) {

		String[] indesejaveis = { "~", "{", "}", "^", "%", "$", "[", "]", "@",
				"|", "`", "\\", "#", "?", "!", "'", ";", "*", "'", "<", ">",
				"\"" };

		String delimitador;

		StringBuffer textoBuffer = new StringBuffer(texto);

		for (int i = 0; i < indesejaveis.length; i++) {

			delimitador = indesejaveis[i];

			StringTokenizer stringTokenizer = new StringTokenizer(textoBuffer
					.toString(), delimitador);

			textoBuffer = new StringBuffer();

			while (stringTokenizer.hasMoreElements()) {

				textoBuffer.append(stringTokenizer.nextToken());

			}

		}

		return textoBuffer.toString();
	}

	/**
	 * Remove os caracteres especiais de uma senha
	 * 
	 * @param texto
	 *            O texto no qual serão removidos os caracteres especias
	 * @return O texto sem os caracteres especiais
	 */

	public static String removerCaracteresEspeciaisSenha(String texto) {

		String[] indesejaveis = { "'", "\"", "<", ">", "*", ";" };

		String delimitador;

		StringBuffer textoBuffer = new StringBuffer(texto);

		for (int i = 0; i < indesejaveis.length; i++) {

			delimitador = indesejaveis[i];

			StringTokenizer stringTokenizer = new StringTokenizer(textoBuffer
					.toString(), delimitador);

			textoBuffer = new StringBuffer();

			while (stringTokenizer.hasMoreElements()) {

				textoBuffer.append(stringTokenizer.nextToken());

			}

		}

		return textoBuffer.toString();
	}

	/**
	 * Verifica se um campo possui caracteres especiais proibidos
	 * 
	 * @param texto
	 *            O texto a ser verificado
	 * @param tipoCampo
	 *            pode ser um campo do tipo "texto" ou "senha"
	 * @return true, se o campo possui caracteres especiais - false, se não
	 *         possuir caracteres especiais
	 */

	public static boolean possuiCaracteresEspeciais(String texto, int tipoCampo) {

		String[] indesejaveis = null;

		if (tipoCampo == IoUtil.CAMPO_SENHA) {
			indesejaveis = new String[] { "'", "\"", "<", ">", "*", ";" };
		} else if (tipoCampo == IoUtil.CAMPO_TEXTO) {
			indesejaveis = new String[] { "~", "{", "}", "^", "%", "$", "[",
					"]", "@", "|", "`", "\\", "#", "?", "!", "'", ";", "*",
					"'", "<", ">", "\"" };
		} else if (tipoCampo == IoUtil.CAMPO_EMAIL) {
			indesejaveis = new String[] { "~", "{", "}", "^", "%", "$", "[",
					"]", "|", "`", "\\", "#", "?", "!", "'", ";", "*", "'",
					"<", ">", "\"" };
		}

		String delimitador;

		StringBuffer textoBuffer = new StringBuffer(texto);

		for (int i = 0; i < indesejaveis.length; i++) {

			delimitador = indesejaveis[i];

			StringTokenizer stringTokenizer = new StringTokenizer(textoBuffer
					.toString(), delimitador);

			textoBuffer = new StringBuffer();

			while (stringTokenizer.hasMoreElements()) {

				textoBuffer.append(stringTokenizer.nextToken());

			}

		}

		return !textoBuffer.toString().equals(texto);
	}

	/**
	 * Valida uma data informada pelo usuário
	 * 
	 * @param dia
	 *            Descrição do parâmetro
	 * @param mes
	 *            Descrição do parâmetro
	 * @param ano
	 *            Descrição do parâmetro
	 * @return true se a data for válida, false caso contrário
	 */
	public static boolean validaData(int dia, int mes, int ano) {
		boolean retorno = true;

		if (!((0 < mes) && (mes <= 12) && (dia > 0) && (dia <= numeroDeDiasDoMes(
				mes, ano)))) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param mes
	 *            Descrição do parâmetro
	 * @param ano
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private static int numeroDeDiasDoMes(int mes, int ano) {
		int retorno = -1;
		GregorianCalendar calendar = new GregorianCalendar();

		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8
				|| mes == 10 || mes == 12) {
			retorno = 31;
		} else if (mes == 2) {
			if (calendar.isLeapYear(ano)) {
				retorno = 29;
			} else {
				retorno = 28;
			}
		} else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			retorno = 30;
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param data
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public static String formatarDataBD(Date data) {
		Calendar dataCalendar = new GregorianCalendar();
		StringBuffer dataBD = new StringBuffer();

		dataCalendar.setTime(data);
		dataBD.append("(CONVERT(DATETIME, '");
		dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH) + "/");
		// Obs.: Janeiro no Calendar é mês zero
		dataBD.append(dataCalendar.get(Calendar.MONTH) + 1 + "/");
		dataBD.append(dataCalendar.get(Calendar.YEAR) + "', 103))");

		return dataBD.toString();
	}

	/**
	 * Cria uma data
	 * 
	 * @param dia
	 *            O dia
	 * @param mes
	 *            O mês
	 * @param ano
	 *            O ano
	 * @return Uma instância da classe Date
	 */
	public static Date criarData(int dia, int mes, int ano) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(ano, mes - 1, dia, 0, 0, 0);
		return calendario.getTime();
	}

	/**
	 * Cria uma data
	 * 
	 * @param dia
	 *            O dia
	 * @param mes
	 *            O mês
	 * @param ano
	 *            O ano
	 * @param hora
	 *            A hora
	 * @param minuto
	 *            Os minutos
	 * @return Uma instância da classe Date
	 */
	public static Date criarData(int dia, int mes, int ano, int hora, int minuto) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(ano, mes - 1, dia, hora, minuto, 0);
		return calendario.getTime();
	}

	/**
	 * Cria uma data
	 * 
	 * @param dia
	 *            O dia
	 * @param mes
	 *            O mês
	 * @param ano
	 *            O ano
	 * @param hora
	 *            A hora
	 * @param minutos
	 *            Descrição do parâmetro
	 * @param segundos
	 *            Os segundos
	 * @return Uma instância da classe Date
	 */
	public static Date criarData(int dia, int mes, int ano, int hora,
			int minutos, int segundos) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(ano, mes - 1, dia, hora, minutos, segundos);
		return calendario.getTime();
	}

	public static long diferencaEntreDatas(Date dataInicial, Date dataFinal) {

		long diff = dataFinal.getTime() - dataInicial.getTime();

		BigDecimal numerador = new BigDecimal("" + diff);
		long multiplicacao = (1000 * 60 * 60 * 24);
		BigDecimal denominador = new BigDecimal("" + multiplicacao);

		BigDecimal resultado = numerador.divide(denominador, 2,
				BigDecimal.ROUND_HALF_UP);

		return resultado.longValue();

	}

	public static int arredondamentoNumero(double numero) {

		BigDecimal arredondamento = new BigDecimal(numero);

		arredondamento = arredondamento.setScale(0, BigDecimal.ROUND_HALF_UP);

		return arredondamento.intValue();
	}

	public static Date adicionarDias(Date data, int numeroDias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DATE, numeroDias);
		return cal.getTime();

	}

	/**
	 * The main program for the IOUtil class
	 * 
	 * @param args
	 *            The command line arguments
	 */
	public static void main(String[] args) {

		Calendar cal = new GregorianCalendar(2004, 1, 1);

		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		System.out.println(days);

	}

	/**
	 * Deleta um diretório do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param dir
	 *            Diretório a ser deletado
	 * @return Booleano indicando se a operação teve sucesso ou não
	 */
	public static boolean deleteDir(File dir) {

		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		return dir.delete();
	}

	/**
	 * Função que transforma um objeto em um byte[], usado para campos BLOB do
	 * banco de dados
	 * 
	 * @author Rodrigo Silveira
	 * @date 21/08/2006
	 * 
	 * @param raw
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static byte[] transformarObjetoParaBytes(Object objeto)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(objeto);
		byte[] retorno = baos.toByteArray(); 
		oos.close();
		baos.close();
		return retorno;
	}

	/**
	 * Função que transforma um byte[] em um objeto, usado para campos BLOB do
	 * banco de dados
	 * 
	 * @author Rodrigo Silveira
	 * @date 21/08/2006
	 * 
	 * @param raw
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object transformarBytesParaObjeto(byte raw[])
			throws IOException, ClassNotFoundException {
		Object retorno = null;
		if (raw != null) {
			ByteArrayInputStream bais = new ByteArrayInputStream(raw);
			ObjectInputStream ois = new ObjectInputStream(bais);
			retorno = ois.readObject();
			ois.close();
			bais.close();
		}
		return retorno;
	}

	public static Object transformarBytesParaObjetos(byte raw[])
			throws IOException, ClassNotFoundException {
		Object retorno = null;
		if (raw != null) {
			ByteArrayInputStream bais = new ByteArrayInputStream(raw);
			ObjectInputStream ois = new ObjectInputStream(bais);
			retorno = ois.readObject();
			ois.close();
			bais.close();
		}
		return retorno;
	}

	/**
	 * Função que cria um diretório no sistema de arquivos
	 * 
	 * @author Rodrigo Silveira
	 * @date 15/05/2008
	 * 
	 * @param nomeDiretorio Nome do diretório
	 */
	
	public static void criarDiretorio(String nomeDiretorio) {
		(new File(nomeDiretorio)).mkdir();
		
	}
	
	
	/**
	 * Função que cria vários diretórios a partir de um caminho informado
	 * 
	 * @author Rodrigo Silveira
	 * @date 15/05/2008
	 * 
	 * @param caminhoSubDiretorios Ex.: dir1/dir2/dir3
	 */
	public static void criarSubDiretorios(String caminhoSubDiretorios) {
		(new File(caminhoSubDiretorios)).mkdirs();
		
	}
	
	public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

 
	/**
	 * 
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Francisco do Nascimento
	 * @date 29/01/2009
	 *
	 * @param is
	 */
	public static void fecharStream(InputStream is){
		if (is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * Método para fechar conexoes do tipo Stream  
	 *
	 * @author Francisco do Nascimento
	 * @date 29/01/2009
	 *
	 * @param is
	 */
	public static void fecharStream(OutputStream os){
		if (os != null){
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * Método para fechar conexoes do tipo Stream  
	 *
	 * @author Francisco do Nascimento
	 * @date 29/01/2009
	 *
	 * @param is
	 */
	public static void fecharStream(Writer w){
		if (w != null){
			try {
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * Método para fechar conexoes do tipo Stream  
	 *
	 * @author Francisco do Nascimento
	 * @date 29/01/2009
	 *
	 * @param is
	 */
	public static void fecharStream(Reader r){
		if (r != null){
			try {
				r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
	/**
	 *
	 * 
	 * Retorna a quantidade de imóveis que já foram
	 * transmitidos contidos no arquivo
	 * 
	 * @author Felipe Santos
	 * @date 22/08/2011
	 * 
	 * @param arquivoOriginal
	 * @param matriculas
	 * @return Integer contadorImoveis
	 * @throws IOException
	 */
	public static Integer obterQuantidadeImoveisTransmitidos (File arquivoOriginal,
			List<Integer> imoveis) throws IOException{
		
		File arquivoDescomprimido = ZipUtil.descomprimirGzip(arquivoOriginal);
		
		int contadorImoveis = 0;
		
		try {
			// Buffer para ler o arquivo original
			BufferedReader bufferedReaderArquivoOriginal = new BufferedReader(new FileReader(arquivoDescomprimido));

			String linha = bufferedReaderArquivoOriginal.readLine();
			String matriculaLinha = null;
			List<Integer> imoveisLidos = new ArrayList<Integer>();

			// Lê linha do arquivo original enquanto houver
			while ((linha = bufferedReaderArquivoOriginal.readLine()) != null) {
				
				if (linha.startsWith("01")) {
					
					matriculaLinha = linha.substring(4, 11);
					
					for (int i = 0; i < imoveis.size(); i++) {
						
						Integer imovel = imoveis.get(i);
						
						if (!imoveisLidos.contains(imovel)) {
							if (matriculaLinha.equals(imovel.toString())) {
								imoveisLidos.add(imovel);
								contadorImoveis++;
								break;
							}
						}
					}
				}
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		arquivoDescomprimido.delete();
		
		return contadorImoveis;
	}
	
	/**
	 * @TODO - COSANPA
	 * 
	 * Método para criar novo arquivo de rota apenas com os imóveis com conta PF
	 * 
	 * @author Felipe Santos
	 * @date 25/05/2011
	 * 
	 * @param arquivoOriginal
	 * @param imovel
	 * @return
	 * @throws IOException
	 */

	public static void gerarArquivoTxtImoveisFaltandoTransmitir(File arquivoOriginal,
			List<Integer> matriculas) throws IOException {

		File arquivoTemp = null;

		try {
			// Arquivo temporário que irá substituir o antigo
			arquivoTemp = new File(arquivoOriginal.getAbsolutePath() + ".tmp");

			// Buffer para ler o arquivo original
			BufferedReader bufferedReaderArquivoOriginal = new BufferedReader(
					new FileReader(arquivoOriginal));

			// Novo arquivo
			PrintWriter novoArquivo = new PrintWriter(new FileWriter(
					arquivoTemp));

			String linha = null;
			String matriculaLinha = null;
			String primeiraLinha = null;
			String matriculaLinhaRegistro01 = null;

			// Lê a primeira linha do arquivo original e escreve no novo
			// arquivo.
			primeiraLinha = bufferedReaderArquivoOriginal.readLine();
			novoArquivo.println(primeiraLinha);
			novoArquivo.flush();

			// Lê linha do arquivo original enquanto houver
			while ((linha = bufferedReaderArquivoOriginal.readLine()) != null) {

				// Matrícula do imóvel na linha lida
				matriculaLinha = linha.substring(4, 11);

				/*
				 * Caso a linha seja do tipo registro 01, adiciona a matricula
				 * da linha na variável matriculaLinhaRegistro01
				 */
				if (linha.startsWith("01")) {

					matriculaLinhaRegistro01 = matriculaLinha;
				}

				if ((linha.startsWith("01")) || (linha.startsWith("02"))
						|| (linha.startsWith("03")) || (linha.startsWith("04"))
						|| (linha.startsWith("05")) || (linha.startsWith("06"))
						|| (linha.startsWith("07")) || (linha.startsWith("08"))
						|| (linha.startsWith("09")) || (linha.startsWith("10"))) {

					for (int i = 0; i < matriculas.size(); i++) {
						
						String matricula = matriculas.get(i).toString();
						
						/*
						 * Caso a matrícula do registro 01 for IGUAL a do imóvel
						 * consultado com situação PF, escreve a respectiva
						 * linha no novo arquivo de rota.
						 */
						if (matriculaLinhaRegistro01.equals(matricula)) {							
							novoArquivo.println(linha);
							novoArquivo.flush();
						}
					}
				} else {
					novoArquivo.println(linha);
					novoArquivo.flush();
				}
			}

			novoArquivo.close();
			bufferedReaderArquivoOriginal.close();

			// Deleta o arquivo original
			if (!arquivoOriginal.delete()) {
				System.out.println("Não foi possível deletar o arquivo original");
			}

			// Renomeia o arquivo temporário com o nome do arquivo original
			if (!arquivoTemp.renameTo(arquivoOriginal)) {
				System.out.println("Não foi possível renomear o arquivo temporário");
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 *
	 * 
	 * Retorna a quantidade de imóveis do arquivo
	 * 
	 * @author Felipe Santos
	 * @date 02/08/2011
	 * 
	 * @param arquivoOriginal
	 * @return Integer contadorRegistros01
	 * @throws IOException
	 */
	public static Integer verificaQuantidadeImoveis(File arquivoOriginal) {
		
		Integer contadorRegistros01 = 0;
		
		try {
			// Buffer para ler o arquivo original
			BufferedReader bufferedReaderArquivoOriginal = new BufferedReader(new FileReader(arquivoOriginal));

			String linha = null;			
			
			linha = bufferedReaderArquivoOriginal.readLine();

			// Lê linha do arquivo original enquanto houver
			while ((linha = bufferedReaderArquivoOriginal.readLine()) != null) {
				
				if (linha.startsWith("01")) {

					contadorRegistros01++;
				}
			}	
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return contadorRegistros01;
	}
	
	public static Properties carregaPropriedades(String nomeArquivo) {
        Properties propriedades = new Properties();
        InputStream stream;

        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            stream = classLoader.getResourceAsStream(nomeArquivo);
            if (stream == null) {
                stream = ConstantesAplicacao.class.getClassLoader().getResourceAsStream(nomeArquivo);
            }
            if (stream == null) {
                stream = ConstantesAplicacao.class.getResourceAsStream(nomeArquivo);
            }

            propriedades.load(stream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return propriedades;
    }
	
	public String compactarStringBytes(String texto) throws ControladorException {
		if (texto.isEmpty())
			return null;

		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gzipOutput = new GZIPOutputStream(baos);
			gzipOutput.write(texto.getBytes());
			gzipOutput.close();
			byte[] compressed = baos.toByteArray();
			baos.close();

			return new String(Base64.encodeBase64(compressed));

		} catch (IOException e) {
			throw new ControladorException("Erro na compactação da imagem");
		}
	}
	
	public static String descompactarStringBytes(String texto) throws ControladorException {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decodeBase64(texto.getBytes()));
			GZIPInputStream gis = new GZIPInputStream(bis);
			BufferedReader br = new BufferedReader(new InputStreamReader(gis));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			gis.close();
			bis.close();

			return sb.toString();

		} catch (IOException e) {
			throw new ControladorException("Erro na descompactação da imagem");
		}
	}
}

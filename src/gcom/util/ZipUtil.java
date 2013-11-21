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
package gcom.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Esta classe reúne funções que manipulam um arquivo zip no sistema
 * 
 * @author Rodrigo Silveira
 * @date 19/05/2006
 */
public class ZipUtil {

	/**
	 * Adiciona o arquivo especificado ao zipOutputStream que representa o
	 * arquivo zip
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param zipFile
	 *            Stream que representa o arquivo zip
	 * @param file
	 *            Arquivo a ser adicionado no arquivo zip
	 * @throws IOException
	 */
	public static void adicionarArquivo(ZipOutputStream zipFile, File file)
			throws IOException {

		FileInputStream inputStream = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int INPUT_BUFFER_SIZE = 1024;
		byte[] temp = new byte[INPUT_BUFFER_SIZE];
		int numBytesRead = 0;

		while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
			baos.write(temp, 0, numBytesRead);
		}

		inputStream.close();
		inputStream = null;

		byte[] data = baos.toByteArray();

		ZipEntry zen = new ZipEntry(file.getName());
		zipFile.putNextEntry(zen);
		zipFile.write(data, 0, data.length);
		zipFile.closeEntry();
	}

	/**
	 * Adiciona um diretório a um arquivo zip especificado
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param dir2zip
	 *            Diretório que será adicionado ao arquivo zip
	 * @param zos
	 *            Stream que representa o arquivo zip
	 * @throws IOException
	 */
	public static void adicionarPasta(File dir2zip, ZipOutputStream zos)
			throws IOException {

		String[] dirList = dir2zip.list();
		byte[] readBuffer = new byte[2156];
		int bytesIn = 0;

		for (int i = 0; i < dirList.length; i++) {
			File f = new File(dir2zip, dirList[i]);
			if (f.isDirectory()) {
				adicionarPasta(f, zos);

				continue;
			}
			FileInputStream fis = new FileInputStream(f);
			ZipEntry anEntry = new ZipEntry(f.getPath());

			zos.putNextEntry(anEntry);

			while ((bytesIn = fis.read(readBuffer)) != -1) {
				zos.write(readBuffer, 0, bytesIn);
			}

			fis.close();
		}

	}
	
	/*
	  Adiciona o arquivo especificado ao zipOutputStream que representa o
	 * arquivo zip
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param zipFile
	 *            Stream que representa o arquivo zip
	 * @param file
	 *            Arquivo a ser adicionado no arquivo zip
	 * @throws IOException
	 */
	public static void adicionarArquivo(GZIPOutputStream zipFile, File file)
			throws IOException {

		FileInputStream inputStream = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int INPUT_BUFFER_SIZE = 1024;
		byte[] temp = new byte[INPUT_BUFFER_SIZE];
		int numBytesRead = 0;

		while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
			baos.write(temp, 0, numBytesRead);
		}

		inputStream.close();
		inputStream = null;

		byte[] data = baos.toByteArray();

//		ZipEntry zen = new ZipEntry(file.getName());
//		zipFile.putNextEntry(zen);
		zipFile.write(data, 0, data.length);
		zipFile.close();
//		zipFile.closeEntry();
	}

	/*
	 * @TODO - COSANPA
	 * 
	 * Método para descomprimir um arquivo com extensão Gzip (.gz)
	 * 
	 * @author Felipe Santos
	 * 
	 * @date 26/05/2011
	 * 
	 * @param arquivoGz
	 */
	public static File descomprimirGzip(File arquivoGz) {
		File file = null;

		try {
			
			String arquivoEntradaNome = arquivoGz.getAbsolutePath();
			
			// Abre o arquivo comprimido
			GZIPInputStream in = new GZIPInputStream(new FileInputStream(
					arquivoEntradaNome));

			// Abre o arquivo de saída
			String arquivoSaidaNome = arquivoEntradaNome.replace(".gz", "").trim();
			
			OutputStream out = new FileOutputStream(arquivoSaidaNome);			

			// Transfere os bytes do arquivo comprimido para o arquivo de saída
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}

			file = new File(arquivoSaidaNome);
			
			// Fecha os arquivos
			in.close();
			out.close();
			
			//Deleta o arquivo antigo
			arquivoGz.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}
	
	/*
	 * @TODO - COSANPA
	 * 
	 * Método para comprimir um arquivo com extensão Gzip (.gz)
	 * 
	 * @author Felipe Santos
	 * 
	 * @date 26/05/2011
	 * 
	 * @param arquivoGz
	 */
	public static File comprimirGzip(File arquivoOriginal) throws IOException {
		
		String comprimidoTipo = arquivoOriginal.getAbsolutePath()+".gz";
		
		File comprimido = new File(comprimidoTipo);
		
		InputStream is = new FileInputStream(arquivoOriginal);
		GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(
				comprimido));
		byte[] buffer = new byte[16 * 1024];
		for (int nBytesLidos = is.read(buffer); nBytesLidos > 0; nBytesLidos = is
				.read(buffer)) {
			gzos.write(buffer, 0, nBytesLidos);
		}
		
		is.close();
		gzos.close();
		
		return comprimido;
	}

}

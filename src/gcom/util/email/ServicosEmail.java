package gcom.util.email;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.fachada.Fachada;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.IoUtil;
import gcom.util.ZipUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Essa classe tem o papel de fornecer ao sistema serviços de e-mail
 * 
 * @author Rodrigo Silveira
 */
public final class ServicosEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	private static String servidorSMTP;
	public static String EMAIL_ADMINISTRADOR;

	static {
		DadosEnvioEmailHelper dadosEnvioEmailHelper = Fachada.getInstancia().pesquisarDadosEmailSistemaParametros();
		servidorSMTP = dadosEnvioEmailHelper.getIpServidorSmtp();
		EMAIL_ADMINISTRADOR = dadosEnvioEmailHelper.getEmailResponsavel();
	}

	private static String TEXTO_EMAIL = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" 
			+ "<html>" 
			+ "<head>" 
			+ "<title>Untitled Document</title>"
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">" 
			+ "</head>" 
			+ "<body>"
			+ "<div align=\"center\"><font color=\"#FF0000\" face=\"Arial, Helvetica, sans-serif\">"
			+ "Relat&oacute;rio gerado apenas para consulta. As funcionalidades dos bot&otilde;es/links est&atilde;o indispon&iacute;veis.<br>"
			+ "Efetue o login no GMNEWEB para ter acesso &agrave;s funcionalidades por completo."
			+ "</font></div>" 
			+ "</body>" 
			+ "</html>";

	private static String TEXTO_EMAIL_SEM_MSG = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" 
			+ "<html>" 
			+ "<head>" 
			+ "<title>Untitled Document</title>"
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">" 
			+ "</head>" 
			+ "<body>" 
			+ "</body>" 
			+ "</html>";

	private ServicosEmail() {
	}

	/**
	 * Método que envia mensagens pelo protocolo SMTP
	 */
	public static void enviarMensagem(String remetente, String destinatario, String assunto, String body) throws ErroEmailException {
		try {
			if (servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO)) {
				Fachada fachada = Fachada.getInstancia();

				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				String nomeDestinatario = sistemaParametro.getNomeAbreviadoEmpresa();
				Properties props = System.getProperties();
				props.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(props, null);

				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(remetente, nomeDestinatario));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario, false));
				msg.setSubject(assunto);
				msg.setText(body);
				msg.setSentDate(new Date());

				Transport.send(msg);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ErroEmailException("Erro no envio de mensagem");
		}
	}

	/**
	 * Método que envia mensagens com arquivo anexado pelo protocolo SMTP
	 */
	public static void enviarMensagemArquivoAnexado(String destinatario, String from, String nameFrom, String subject, String body, String caminhoArquivo) throws Exception {
		try {
			if (servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO)) {
				Properties props = System.getProperties();
				props.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(props, null);

				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(from, nameFrom));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario, false));
				msg.setSubject(subject);

				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText(body);

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();

				DataSource source = new FileDataSource(caminhoArquivo);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(source.getName());
				multipart.addBodyPart(messageBodyPart);

				msg.setContent(multipart);
				msg.setSentDate(new Date());

				Transport.send(msg);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void enviarMensagemArquivoAnexado(String destinatario, String from, String subject, String body, File arquivo) throws Exception {
		if (servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO)) {
			Fachada fachada = Fachada.getInstancia();
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			String nameFrom = sistemaParametro.getNomeAbreviadoEmpresa();
			
			Properties props = System.getProperties();
			props.put("mail.smtp.host", servidorSMTP);

			Session session = Session.getDefaultInstance(props, null);

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from, nameFrom));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario, false));
			msg.setSubject(subject);

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();

			DataSource source = new FileDataSource(arquivo);

			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(source.getName());
			multipart.addBodyPart(messageBodyPart);

			msg.setContent(multipart);
			msg.setSentDate(new Date());

			Transport.send(msg);

		}

	}

	/**
	 * Método que envia mensagens em html e imagens anexadas ao próprio código para serem mostradas
	 */
	@SuppressWarnings("rawtypes")
	public static void enviarMensagemHTMLComImagens(Collection destinatarios, String from, String nameFrom, String subject, String body, String codigoHtml, String caminhoArquivo, String linkGrafico)
			throws ErroEmailException {
		try {
			if (servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO)) {
				Properties props = System.getProperties();
				props.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(props, null);

				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(from, nameFrom));

				Iterator iterator = destinatarios.iterator();
				Address[] enderecos = new Address[destinatarios.size()];

				int i = 0;
				while (iterator.hasNext()) {
					enderecos[i] = new InternetAddress((String) iterator.next());
					i++;
				}

				msg.setRecipients(Message.RecipientType.TO, enderecos);
				msg.setSubject(subject);

				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart = new MimeBodyPart();

				codigoHtml = codigoHtml.replaceAll(linkGrafico, "cid:" + linkGrafico);

				messageBodyPart.setContent(codigoHtml, "text/html");

				MimeMultipart multipart = new MimeMultipart();
				multipart.setSubType("related");
				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();

				DataSource source = new FileDataSource(caminhoArquivo);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(source.getName());
				messageBodyPart.setHeader("Content-ID", "<" + linkGrafico + ">");
				multipart.addBodyPart(messageBodyPart);

				MimeMultipart multipart2 = new MimeMultipart();
				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(TEXTO_EMAIL, "text/html");
				multipart2.addBodyPart(messageBodyPart);
				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(multipart2);
				multipart.addBodyPart(messageBodyPart);

				msg.setContent(multipart);
				msg.setSentDate(new Date());

				Transport.send(msg);
			}
		} catch (Exception ex) {
			throw new ErroEmailException("Erro ao enviar mensagem");
		}
	}

	/**
	 * Método que envia mensagens em html
	 */
	@SuppressWarnings("rawtypes")
	public static void enviarMensagemHTML(Collection destinatarios, String from, String nameFrom, String subject, String codigoHtml) throws ErroEmailException {
		try {
			if (servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO)) {
				Properties properties = System.getProperties();
				properties.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(properties, null);

				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(from, nameFrom));

				Iterator iterator = destinatarios.iterator();
				Address[] enderecos = new Address[destinatarios.size()];

				int i = 0;
				while (iterator.hasNext()) {
					enderecos[i] = new InternetAddress((String) iterator.next());
					i++;
				}

				msg.setRecipients(Message.RecipientType.TO, enderecos);
				msg.setSubject(subject);

				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(codigoHtml, "text/html");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(TEXTO_EMAIL, "text/html");
				multipart.addBodyPart(messageBodyPart);
				msg.setContent(multipart);
				msg.setSentDate(new Date());

				
				Transport.send(msg);
			}
		} catch (Exception ex) {
			throw new ErroEmailException("Erro ao Enviar Mensagem");
		}
	}

	/**
	 * Método que envia mensagens em html
	 * 	 */
	@SuppressWarnings("rawtypes")
	public static void enviarMensagemHTMLSemMsgTextoEmail(Collection destinatarios, String from, String nameFrom, String subject, String body, String codigoHtml) throws ErroEmailException {
		try {
			if (servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO)) {

				Properties properties = System.getProperties();
				properties.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(properties, null);

				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(from, nameFrom));

				Iterator iterator = destinatarios.iterator();
				Address[] enderecos = new Address[destinatarios.size()];

				int i = 0;
				while (iterator.hasNext()) {
					enderecos[i] = new InternetAddress((String) iterator.next());
					i++;
				}

				msg.setRecipients(Message.RecipientType.TO, enderecos);
				msg.setSubject(subject);

				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(codigoHtml, "text/html");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(TEXTO_EMAIL_SEM_MSG, "text/html");
				multipart.addBodyPart(messageBodyPart);
				msg.setContent(multipart);
				msg.setSentDate(new Date());

				Transport.send(msg);

			}
		} catch (Exception ex) {
			throw new ErroEmailException("Erro ao Enviar Mensagem");
		}
	}

	/**
	 * Prepara a exceção para ser mostrada como conteúdo no e-mail
	 */
	public static String processarExceptionParaEnvio(Exception exception) {
		StringBuffer retorno = new StringBuffer();

		retorno.append("Erro ocorrido no sistema GCOM: \n\n");
		retorno.append(exception.getMessage() + "\n");

		for (int i = 0; i < exception.getStackTrace().length; i++) {
			retorno.append(exception.getStackTrace()[i] + "\n");
		}

		return retorno.toString();
	}
	
	public static void enviarArquivoCompactado(String nome, StringBuilder registros, String receptor, String remetente, String titulo, String corpo) throws ControladorException {
    	BufferedWriter out = null;
		ZipOutputStream zos = null;
		
		try {
			File arquivo = new File(nome + ".txt");
			File compactado = new File(nome + ".zip");
			zos = new ZipOutputStream(new FileOutputStream(compactado));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo.getAbsolutePath())));
			out.write(registros.toString());
			out.flush();
			out.close();

			ZipUtil.adicionarArquivo(zos, arquivo);
			zos.close();

			enviarMensagemArquivoAnexado(receptor, remetente, titulo, corpo, compactado);

			arquivo.delete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IoUtil.fecharStream(out);
			IoUtil.fecharStream(zos);
		}
    }
	
	public static void enviarArquivo(String nome, StringBuilder registros, String receptor, String remetente, String titulo, String corpo) throws ControladorException {
    	BufferedWriter out = null;
		
		try {
			File arquivo = new File(nome + ".txt");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo.getAbsolutePath())));
			out.write(registros.toString());
			out.flush();
			out.close();

			enviarMensagemArquivoAnexado(receptor, remetente, titulo, corpo, arquivo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IoUtil.fecharStream(out);
		}
    }
	
	/**
	 * Método que envia mensagens em html
	 */
	@SuppressWarnings("rawtypes")
	public static void enviarMensagemHTMLComAnexo(String destinatario, String from, String nameFrom, String subject, String codigoHtml, File arquivo) 
			throws ErroEmailException {
		try {
			if (servidorSMTP != null && !servidorSMTP.equals(ConstantesSistema.SMTP_INVALIDO)) {
				Properties properties = System.getProperties();
				properties.put("mail.smtp.host", servidorSMTP);

				Session session = Session.getDefaultInstance(properties, null);

				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(from, nameFrom));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario, false));
				msg.setSubject(subject);

				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(codigoHtml, "text/html");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(TEXTO_EMAIL, "text/html");
				multipart.addBodyPart(messageBodyPart);
				msg.setContent(multipart);
				msg.setSentDate(new Date());
				
				DataSource source = new FileDataSource(arquivo);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(source.getName());
				multipart.addBodyPart(messageBodyPart);

				msg.setContent(multipart);
				msg.setSentDate(new Date());
				
				Transport.send(msg);
			}
		} catch (Exception ex) {
			throw new ErroEmailException("Erro ao Enviar Mensagem");
		}
	}
	
}

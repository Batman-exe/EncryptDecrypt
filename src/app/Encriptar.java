package app;

import java.security.MessageDigest;

public class Encriptar {

	public Encriptar(){
		
	}

	public String generar_codigo(String cadena, String algoritmo)
	{
		String codigo ="";

		if(algoritmo=="md5" || algoritmo=="MD5")
			codigo= encriptarMd5(cadena);

		else if(algoritmo=="sha256" || algoritmo=="SHA256")
			codigo= encriptarSHA256(cadena);

		else if(algoritmo=="sha512" || algoritmo=="SHA512")
			codigo= encriptarSHA512(cadena);
		else
			codigo="El algoritmo no existe.";

		return codigo;
	}

	private String encriptarMd5(String cadena)
	{
		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(cadena.getBytes());
			byte[] digest = md.digest();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String encriptarSHA256(String cadena)
	{
		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(cadena.getBytes());
			byte[] digest = md.digest();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String encriptarSHA512(String cadena)
	{
		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(cadena.getBytes());
			byte[] digest = md.digest();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}

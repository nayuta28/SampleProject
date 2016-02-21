package password;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashPassword {

	private static final String ALGORITHM_NAME = "SHA-256";

	public static void main(String[] args) {

		try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {

			System.out.println("入力してください");
			String text = bufferedReader.readLine();
			System.out.println("入力された文字列は" + text + "です");

			final String hashValue = toEncryptedHashValue(ALGORITHM_NAME, text);

			System.out.println("暗号化した文字列は" + hashValue + "です");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 暗号化した文字列を返す.
	 * 
	 * @param algorithmName 暗号化形式
	 * @param text 入力文字列
	 * @return 暗号化文字列
	 */
	private static String toEncryptedHashValue(String algorithmName, String text) {
		MessageDigest md = null;
		StringBuilder sb = new StringBuilder();

		try {
			md = MessageDigest.getInstance(algorithmName);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(text.getBytes());

		for (byte b : md.digest()) {
			String hex = String.format("%02x", b);
			sb.append(hex);
		}
		return sb.toString();
	}
}

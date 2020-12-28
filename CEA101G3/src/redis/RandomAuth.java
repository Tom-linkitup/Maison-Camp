package redis;

public class RandomAuth {
	
	
	
	public static String getAuthCode() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String authCode = "";
		for(int i = 0; i < 8; i++) {
			int randomPointer = (int)(Math.random() * chars.length());
			authCode += chars.charAt(randomPointer);
		}

		return authCode;
	}

}

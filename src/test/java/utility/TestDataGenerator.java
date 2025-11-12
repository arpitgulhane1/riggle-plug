package utility;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.github.javafaker.Faker;

public class TestDataGenerator {

	// This Class is use for to create : CREATE Store random data real-name
	private static final Random random = new Random();

	private static final Faker faker = new Faker();
//	private static final Faker faker = new Faker(new Locale("en-IN"));

	public static String getRandomStoreName() {
		String name = faker.company().name(); // e.g., "Acme Corp"
		// Keep letters, numbers, spaces, dash, dot; remove other special chars
		return name.replaceAll("[^a-zA-Z0-9 .\\-]", "");
	}

	public static String getRandomFirstName() {
		String name = faker.name().firstName();
		// Remove any non-alphabetic characters (A–Z, a–z)
		return name.replaceAll("[^A-Za-z]", "");
	}

	public static String getRandomLastName() {
		String name = faker.name().lastName();
		// Remove any non-alphabetic characters (A–Z, a–z)
		return name.replaceAll("[^A-Za-z]", "");
	}

	public static String getRandomProductName() {
		String product = faker.commerce().productName(); // e.g., "Incredible Plastic Shirt"
		return product.replaceAll("[^a-zA-Z0-9 .\\-]", "");
	}

	public static String getRandomBrandName() {
		String brand = faker.company().name(); // e.g., "Acme Corp"
		return brand.replaceAll("[^a-zA-Z0-9 .\\-]", "");
	}

//-----------------------------------------
	public static String getRandomStreetAddress() {
		return faker.address().streetAddress(); // e.g., "123 Elm St"
	}

//    public static String getRandomPinCode() {
//        return faker.address().zipCode().replaceAll("[^0-9]", "").substring(0, 6); // Indian style 6-digit
//    }
//    public static String getRandomPinCode() {
//        return faker.regexify("[1-9]{1}[0-9]{5}"); // e.g., "560034"
//    }

	public static String getRandomPinCode() {
		List<String> realPinCodes = Arrays.asList("110001", "400001", "700001", "600001", "500001", "560001", "380001",
				"751001", "302001", "226001", "462001", "482001", "160017", "395003", "208001", "144001", "121001",
				"243001", "831001", "788001");
		Random random = new Random();
		return realPinCodes.get(random.nextInt(realPinCodes.size()));
	}

	public static String getRandomCity() {
		return faker.address().city(); // e.g., "Mumbai"
	}

	public static String getRandomState() {
		return faker.address().state(); // e.g., "Maharashtra"
	}

	public static String getRandomLandmark() {
		return faker.company().industry(); // or faker.address().secondaryAddress()
	}

	public static String generateValidGST() {
		// State code: 01 to 37
		int stateCode = 1 + random.nextInt(37);

		// PAN structure: 5 letters + 4 digits + 1 letter
		String pan = RandomStringUtils.randomAlphabetic(5).toUpperCase() + RandomStringUtils.randomNumeric(4)
				+ RandomStringUtils.randomAlphabetic(1).toUpperCase();

		// Entity number (1-9)
		int entity = 1 + random.nextInt(9);

		// Default 'Z'
		char z = 'Z';

		// Checksum: alphanumeric (A–Z or 0–9)
		char checksum = RandomStringUtils.randomAlphanumeric(1).toUpperCase().charAt(0);

		// Combine all parts
		return String.format("%02d%s%d%c%c", stateCode, pan, entity, z, checksum);
	}

	public static String getRandomReasonText(int minLines, int maxLines) {
		int lineCount = random.nextInt(maxLines - minLines + 1) + minLines;
		StringBuilder reason = new StringBuilder();

		for (int i = 0; i < lineCount; i++) {
			// Use a more natural English sentence
			reason.append(faker.company().catchPhrase());
			if (i < lineCount - 1) {
				reason.append("\n");
			}
		}

		return reason.toString();
	}

	public static String getRandomFirmName() {
		String name = faker.company().name().replaceAll("(?i)\\b(ltd|inc|corp|corporation|llc)\\b", "") // remove
																										// suffixes
				.replaceAll("[,'.]", "") // remove , ' and .
				.trim();

		// Capitalize only the first character if it's lowercase
		if (!name.isEmpty()) {
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
		}

		return name + " Pvt Ltd";
	}

	public static String getRandomEmail() {
		return faker.internet().emailAddress(); // e.g., "alice.smith@example.com"
	}

	public static String getRandomFssaiNumber() {
		return String.valueOf(faker.number().randomNumber(14, true));
	}

	public static String getFutureDate() {
		Faker faker = new Faker();
		Date future = faker.date().future(365, java.util.concurrent.TimeUnit.DAYS);
		return new SimpleDateFormat("dd-MM-yyyy").format(future);
	}

	// use to search location
	private static final String[] indianCities = { "Mumbai", "Delhi", "Bengaluru", "Hyderabad", "Chennai", "Kolkata",
			"Pune", "Ahmedabad", "Jaipur", "Lucknow", "Surat", "Kanpur", "Nagpur", "Indore", "Bhopal", "Patna",
			"Ludhiana", "Agra", "Nashik", "Vadodara", "Coimbatore", "Madurai", "Visakhapatnam", "Varanasi", "Rajkot" };

	public static String getRandomIndianCity() {
		Random random = new Random();
		return indianCities[random.nextInt(indianCities.length)];
	}

	public static String generateRandomMobileNumberWithFaker() {
		// pick first digit from 6-9
		String first = faker.options().option("6", "7", "8", "9");
		// generate remaining 9 digits
		String rest = faker.numerify("#########"); // 9 digits
		return first + rest;
	}

}

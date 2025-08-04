package utility;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

public class TestDataGenerator {
	
		
	// This Class is use for to create : CREATE Store random data real-name
	private static final Random random = new Random();

	private static final Faker faker = new Faker();

    public static String getRandomStoreName() {
        String name = faker.company().name(); // e.g., "Acme Corp"
        // Keep letters, numbers, spaces, dash, dot; remove other special chars
        return name.replaceAll("[^a-zA-Z0-9 .\\-]", "");
    }

    public static String getRandomFirstName() {
        return faker.name().firstName(); // e.g., "Alice"
    }

    public static String getRandomLastName() {
        return faker.name().lastName(); // e.g., "Smith"
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
        List<String> realPinCodes = Arrays.asList(
            "110001", "400001", "700001", "600001", "500001",
            "560001", "380001", "751001", "302001", "226001",
            "462001", "482001", "160017", "395003", "208001",
            "144001", "121001", "243001", "831001", "788001"
        );
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

    public static String getRandomGSTNumber() {
        // Sample format: 22AAAAA0000A1Z5 — 15 chars (random mock)
        return faker.regexify("[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}[Z]{1}[0-9A-Z]{1}");
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
        String name = faker.company().name().replaceAll("(?i)\\b(ltd|inc|corp|corporation|llc)\\b", "").trim();
        return name + " Pvt Ltd";
    }

    // use to search location 
    private static final String[] indianCities = {
    	    "Mumbai", "Delhi", "Bengaluru", "Hyderabad", "Chennai",
    	    "Kolkata", "Pune", "Ahmedabad", "Jaipur", "Lucknow",
    	    "Surat", "Kanpur", "Nagpur", "Indore", "Bhopal",
    	    "Patna", "Ludhiana", "Agra", "Nashik", "Vadodara",
    	    "Coimbatore", "Madurai", "Visakhapatnam", "Varanasi", "Rajkot"
    	};

    	public static String getRandomIndianCity() {
    	    Random random = new Random();
    	    return indianCities[random.nextInt(indianCities.length)];
    	}

    
}

package utility;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class BrandUtility {

	private static final String PATH = System.getProperty("user.dir")
			+ "/src/test/resources/Json/brand.json";

	public static void writeJson(String section, Map<String, String> data) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode root;

		try {
			File file = new File(PATH);

			// Load existing file if exists
			if (file.exists()) {
				JsonNode existingNode = mapper.readTree(file);
				root = (ObjectNode) existingNode;
			} else {
				root = mapper.createObjectNode();
			}

			// Get or create the section node
			ObjectNode sectionNode = root.has(section) ? (ObjectNode) root.get(section) : mapper.createObjectNode();

			// Put all key-values from map into section
			for (Map.Entry<String, String> entry : data.entrySet()) {
				sectionNode.put(entry.getKey(), entry.getValue());
			}

			// Update the section in root
			root.set(section, sectionNode);

			// Write to file
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, root);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readJson(String section, String key) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(new File(PATH));
			if (root.has(section)) {
				JsonNode sectionNode = root.get(section);
				if (sectionNode.has(key)) {
					return sectionNode.get(key).asText();
				}
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void writeJson(String section, String key, String value) {
		Map<String, String> singleEntry = new HashMap<>();
		singleEntry.put(key, value);
		writeJson(section, singleEntry); // reuse existing method
	}

	public static boolean compareJson(Map<String, String> actual, String sectionName) {
		boolean allMatch = true;

		System.out.println("🔍 Comparing with section: " + sectionName);
		for (Map.Entry<String, String> entry : actual.entrySet()) {
			String key = entry.getKey();
			String actualValue = entry.getValue();
			String expectedValue = readJson(sectionName, key); // ← your existing JSON read method

			if (expectedValue == null) {
				System.out.println("⚠️ " + key + " not found in JSON section: " + sectionName);
				allMatch = false;
				continue;
			}

			if (!actualValue.equals(expectedValue)) {
				System.out
						.println("❌ MISMATCH in " + key + " → Expected: " + expectedValue + ", Found: " + actualValue);
				allMatch = false;
			} else {
				System.out.println("✅ " + key + " matched: " + actualValue);
			}
		}

		return allMatch;
	}

	
	
}

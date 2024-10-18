import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ContentComparator implements Comparator<String> {
	/*
	 * Comparable class used to compare pieces of content in different ways
	 * 
	 * Implements Comparator<String>
	 */

	private final HashMap<String, Content> contentMap;
	private final String sortBy;

	public ContentComparator(HashMap<String, Content> contentMap, String sortBy) {
		this.contentMap = contentMap;
		this.sortBy = sortBy;
	}

	/*
	 * Method to compare two items based on the criteria from the sortBy variable
	 * 
	 * @param a String representing the key of the first item and a String
	 * representing the key of the second item
	 * 
	 * @return an integer (-1,0, or 1) representing the result of the comparison
	 * Default case is 0, -1 first item is smaller, 1 first item is bigger
	 */
	@Override
	public int compare(String key1, String key2) {
		Content content1 = contentMap.get(key1);
		Content content2 = contentMap.get(key2);

		switch (sortBy.toLowerCase()) {
			case "title":
				return content1.getTitle().toLowerCase().compareTo(content2.getTitle().toLowerCase());
			case "author":
				String lastName1 = extractLastName(content1.getAuthor());
				String lastName2 = extractLastName(content2.getAuthor());
				return lastName1.compareToIgnoreCase(lastName2);

			case "status":
				int statusComparison = content2.getStatus().toLowerCase().compareTo(content1.getStatus().toLowerCase());
				// If statuses are the same, consider the natural order of titles
				return (statusComparison != 0) ? statusComparison : key1.compareTo(key2);
			case "length":
				// Use the LengthComp class
				return new LengthComp().compare(Map.entry(key1, content1), Map.entry(key2, content2));
			default:
				return 0;
		}
	}

	private String extractLastName(String fullName) {
		if (fullName != null && fullName.contains(" ")) {
			return fullName.substring(fullName.lastIndexOf(" ") + 1).toLowerCase();
		} else {
			// Handle cases where the full name does not contain a space
			return fullName.toLowerCase();
		}
	}

}

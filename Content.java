import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Content {
	/*
	 * Object representing a piece of content to be stored in the program
	 * Parent class of Book and Article
	 */

	private String title;
	private String author;
	private String status;
	private ArrayList<String> tags;
	private String notes;

	public Content() {
		title = "";
		author = "";
		status = "Not Started";
		tags = new ArrayList<String>();
		notes = "";
	}

	public Content(String t, String a, String stat, String[] tagsArr) {
		title = t;
		author = a;
		status = stat;
		tags = new ArrayList<String>();

		notes = "";

		for (String item : tagsArr) {
			tags.add(item);
		}

	}

	/*
	 * Method to sort the map based on the users input
	 * 
	 * @param a HashMap containing the content to be sorted and a String containing
	 * what to sort by
	 * 
	 * @return a sorted TreeMap
	 */
	public TreeMap<String, Content> sort(HashMap<String, Content> contentMap, String sortBy) {
		TreeMap<String, Content> sortedMap = new TreeMap<String, Content>(new ContentComparator(contentMap, sortBy));
		sortedMap.putAll(contentMap);
		return sortedMap;
	}

	/*
	 * Method to filter the map based on the users input
	 * 
	 * @param a TreeMap containing the content to be filtered and a String
	 * containing what to filter by
	 * 
	 * @return a TreeMap with the filtered content
	 */
	public TreeMap<String, Content> filter(TreeMap<String, Content> contentMap, String filterBy) {
		TreeMap<String, Content> filteredMap = new TreeMap<>();

		for (Map.Entry<String, Content> entry : contentMap.entrySet()) {
			Content content = entry.getValue();
			String status = content.getStatus();
			String Class = content.getClassOfObj();

			if (filterBy.equals("Completed") && status.equals("Completed")) {
				filteredMap.put(entry.getKey(), content);
			} else if (filterBy.equals("In Progress") && status.equals("In Progress")) {
				filteredMap.put(entry.getKey(), content);
			} else if (filterBy.equals("Not Started") && status.equals("Not Started")) {
				filteredMap.put(entry.getKey(), content);
			} else if (filterBy.equals("Books") && Class.equals("Book")) {
				filteredMap.put(entry.getKey(), content);
			} else if (filterBy.equals("Articles") && Class.equals("Article")) {
				filteredMap.put(entry.getKey(), content);
			}
		}

		return filteredMap;
	}

	/*
	 * Method to search the map based on titles.
	 * 
	 * @param a Map containing the content to be searched and a String
	 * containing what to search for
	 * 
	 * @return a HashMap with any content containing the search term in the title
	 */
	public HashMap<String, Content> searchByTitle(String t, Map<String, Content> contentMap) {

		HashMap<String, Content> newMap = new HashMap<String, Content>();
		Content c;
		for (Entry<String, Content> entry : contentMap.entrySet()) {
			if (entry.getKey().contains(t)) {
				c = entry.getValue();
				newMap.put(entry.getKey(), c);
			}
		}

		return newMap;
	}

	/*
	 * Method to search the map based on authors.
	 * 
	 * @param a HashMap containing the content to be searched and a String
	 * containing what to search for
	 * 
	 * @return a HashMap with any content containing the search term in the author
	 */
	public HashMap<String, Content> searchByAuthor(String a, HashMap<String, Content> contentMap) {

		HashMap<String, Content> newMap = new HashMap<String, Content>();
		Content c;
		for (Entry<String, Content> entry : contentMap.entrySet()) {
			if (entry.getValue().getAuthor().contains(a)) {
				c = entry.getValue();
				newMap.put(entry.getKey(), c);
			}
		}

		return newMap;
	}

	/*
	 * Method to search the map based on tags.
	 * 
	 * @param a HashMap containing the content to be searched and a String
	 * containing what to search for
	 * 
	 * @return a HashMap with any content containing the search term in the tags
	 */
	public HashMap<String, Content> searchByTag(String tag, HashMap<String, Content> contentMap) {
		HashMap<String, Content> newMap = new HashMap<String, Content>();
		Content c;
		for (Entry<String, Content> entry : contentMap.entrySet()) {
			if (entry.getValue().getTags().contains(tag)) {
				c = entry.getValue();
				newMap.put(entry.getKey(), c);
			}
		}

		return newMap;
	}

	// get the title of this item
	public String getTitle() {
		return title;
	}

	// get the author of this item
	public String getAuthor() {
		return author;
	}

	// get the status of this item
	public String getStatus() {
		return status;
	}

	// get the length of this item
	// - implemented in child classes
	public int getLength() {
		return 0;
	}

	// get the tags for this item
	public ArrayList<String> getTags() {
		return tags;
	}

	// set the title of this item
	public void setTitle(String editedTitle) {
		title = editedTitle;
	}

	// set the author of this item
	public void setAuthor(String editedAuthor) {
		author = editedAuthor;
	}

	// set the tags for this item
	public void setTags(ArrayList<String> newTags) {
		tags = newTags;
	}

	// get the notes for this item
	public String getNotes() {
		return notes;
	}

	// set the notes for this item
	public void setNotes(String newNotes) {
		notes = newNotes;
	}

	// set the status for this item
	public void setStatus(String editedStatus) {
		status = editedStatus;
	}

	// set the length of this item
	public void setLength(int len) {

	}

	// get the class of this item using inheritance
	public String getClassOfObj() {
		return "";
	}

	// prints out the information for a content object
	public String toString() {
		String sb = "";
		for (String tag : tags) {
			sb += tag + ", ";
		}
		if (sb.length() > 2) {
			return title + "," + author + "," + getLength() + "," + status + ",[" + sb.substring(0, sb.length() - 2)
					+ "]";
		} else {
			return title + "," + author + "," + getLength() + "," + status + ",[]";
		}
	}

}

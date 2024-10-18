import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class LengthComp implements Comparator<Map.Entry<String, Content>> {
	/*
	 * Allows the length of two pieces of content to be compared properly
	 * 
	 * Length is stored as an attribute of the value for my HashMap so I could not simply use the compare method
	 */

	/*
	 * Method to compare the lengths of two provided items
	 * 
	 * @param two HashMap entries containing the title and the Content object to be compared
	 * 
	 * @return return an integer (-1,0, or 1) representing the result of the comparison
	 * Default case is 0, -1 first item is smaller, 1 first item is bigger
	 */
	@Override
	public int compare(Entry<String, Content> entry1, Entry<String, Content> entry2) {
		if (entry1.getValue().getLength() < (entry2.getValue().getLength()))
			return -1;
		if (entry1.getValue().getLength() > (entry2.getValue().getLength()))
			return 1;
		return 0;
	}
}

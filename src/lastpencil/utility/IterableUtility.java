package lastpencil.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Utility class for handling iterable-related operations such as:
 * - formatting lists into readable strings
 * - extracting elements from a list based on a key
 *
 * This class provides only static utility methods and cannot be instantiated.
 */
public class IterableUtility {

    private IterableUtility() {
    }

    /**
     * Builds a formatted string from a list of strings using custom separators.
     * This method supports a different separator before the last element.
     *
     * Example:
     * list = ["John", "Jack"]
     * prefix = "Choose between '"
     * separator = "', '"
     * beforeLastSeparator = "' and '"
     * suffix = "'"
     *
     * Result:
     * "Choose between 'John' and 'Jack'"
     *
     * @param list                the list of elements to format
     * @param prefix              the prefix added at the beginning of the string
     * @param separator           the separator between elements except the last two
     * @param beforeLastSeparator the separator used before the last element
     * @param suffix              the suffix added at the end of the string
     * @return the formatted string
     */
    public static String getMsg(
            List<String> list,
            String prefix,
            String separator,
            String beforeLastSeparator,
            String suffix
    ) {
        return getMsg(
                list,
                new StringBuilder(prefix),
                separator,
                beforeLastSeparator,
                suffix
        );
    }

    /**
     * Internal implementation of the formatted message builder.
     *
     * @param list                the list of elements
     * @param sb                  the StringBuilder used to construct the result
     * @param separator           the separator used between elements
     * @param beforeLastSeparator the separator used before the last element
     * @param suffix              the suffix added at the end
     * @return the formatted string
     */
    public static String getMsg(
            List<String> list,
            StringBuilder sb,
            String separator,
            String beforeLastSeparator,
            String suffix
    ) {

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));

            if (i < list.size() - 2) {
                sb.append(separator);
            } else if (i == list.size() - 2) {
                sb.append(beforeLastSeparator != null ? beforeLastSeparator : separator);
            }
        }

        sb.append(suffix);
        return sb.toString();
    }

    /**
     * Searches for an element inside a list by comparing a key extracted from each element.
     *
     * @param list         the list to search into
     * @param value        the value to match
     * @param keyExtractor function used to extract the comparison key from each element
     * @param <T>          the type of the elements in the list
     * @return the matching element found in the list
     * @throws NoSuchElementException if no matching element is found
     */
    public static <T> T checkIsList(List<T> list, String value, Function<T, String> keyExtractor) {
        for (T el : list) {
            if (keyExtractor.apply(el).equalsIgnoreCase(value)) {
                return el;
            }
        }

        throw new NoSuchElementException();
    }

    /**
     * Reads a value from the input and searches for a matching element in the given list.
     *
     * @param list         the list where the search is performed
     * @param keyExtractor function used to extract the comparison key from each element
     * @param br           the input reader
     * @param <T>          the type of elements inside the list
     * @return the matching element
     * @throws IOException            if an I/O error occurs
     * @throws NoSuchElementException if no matching element is found
     */
    public static <T> T getValueInList(List<T> list, Function<T, String> keyExtractor, BufferedReader br) throws IOException {
        String str = br.readLine().trim();
        return checkIsList(list, str, keyExtractor);
    }
}

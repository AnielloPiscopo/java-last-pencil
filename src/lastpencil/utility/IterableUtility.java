package lastpencil.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class IterableUtility {
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

    public static <T> T checkIsList(List<T> list, String value, Function<T, String> keyExtractor) {
        for (T el : list) {
            if (keyExtractor.apply(el).equalsIgnoreCase(value)) {
                return el;
            }
        }

        throw new NoSuchElementException();
    }

    public static <T> T getValueInList(List<T> list, Function<T, String> keyExtractor, BufferedReader br) throws IOException {
        String str = br.readLine().trim();
        return checkIsList(list, str, keyExtractor);
    }
}

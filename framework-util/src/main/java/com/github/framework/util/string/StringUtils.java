package com.github.framework.util.string;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 龙海仁
 * @date 2015年12月4日下午10:50:11
 * @Description: String 工具类
 */
public class StringUtils {
	
	public static String formart(String url, String... urlVariables){
		MessageFormat mftdu = new MessageFormat(url);
		return mftdu.format(urlVariables);
	}
	
	public static String joinWithVerBar(List<String> list){
		StringBuilder builder = new StringBuilder();
		for(String str : list){
			builder.append(str).append("|");
		}
		String res = builder.toString();
		return res.substring(0, res.length()-1);
	}
	
	public static String joinWithVerBar(String[] arys){
		StringBuilder builder = new StringBuilder();
		for(String str : arys){
			builder.append(str).append("|");
		}
		String res = builder.toString();
		return res.substring(0, res.length()-1);
	}
	
	public static String dealEnterEscape(String before){
		return before.replaceAll("\\\\n", "\n");
	}
	
	public static final String EMPTY_STRING = "";

	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}

	public static boolean isNotEmpty(String str) {
		return (str != null) && (str.length() > 0);
	}

	public static boolean isBlank(String str) {
		if ((str == null) || (str.length() == 0)) {
			return true;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static String defaultIfNull(String str) {
		return str == null ? "" : str;
	}

	public static String defaultIfNull(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}

	/**
	 * @deprecated
	 */
	public static String defaultIfEmpty(String str) {
		return str == null ? "" : str;
	}

	public static String defaultIfEmpty(String str, String defaultStr) {
		return (str == null) || (str.length() == 0) ? defaultStr : str;
	}

	public static String defaultIfBlank(String str) {
		return isBlank(str) ? "" : str;
	}

	public static String defaultIfBlank(String str, String defaultStr) {
		return isBlank(str) ? defaultStr : str;
	}

	public static String trim(String str) {
		return trim(str, null, 0);
	}

	public static String trim(String str, String stripChars) {
		return trim(str, stripChars, 0);
	}

	public static String trimStart(String str) {
		return trim(str, null, -1);
	}

	public static String trimStart(String str, String stripChars) {
		return trim(str, stripChars, -1);
	}

	public static String trimEnd(String str) {
		return trim(str, null, 1);
	}

	public static String trimEnd(String str, String stripChars) {
		return trim(str, stripChars, 1);
	}

	public static String trimToNull(String str) {
		return trimToNull(str, null);
	}

	public static String trimToNull(String str, String stripChars) {
		String result = trim(str, stripChars);
		if ((result == null) || (result.length() == 0)) {
			return null;
		}
		return result;
	}

	public static String trimToEmpty(String str) {
		return trimToEmpty(str, null);
	}

	public static String trimToEmpty(String str, String stripChars) {
		String result = trim(str, stripChars);
		if (result == null) {
			return "";
		}
		return result;
	}

	private static String trim(String str, String stripChars, int mode) {
		if (str == null) {
			return null;
		}
		int length = str.length();
		int start = 0;
		int end = length;
		if (mode <= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(start)))) {
					start++;
				}
			}
			if (stripChars.length() == 0) {
				return str;
			}
			while ((start < end)
					&& (stripChars.indexOf(str.charAt(start)) != -1)) {
				start++;
			}
		}
		if (mode >= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(end - 1)))) {
					end--;
				}
			}
			if (stripChars.length() == 0) {
				return str;
			}
			while ((start < end)
					&& (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
				end--;
			}
		}
		if ((start > 0) || (end < length)) {
			return str.substring(start, end);
		}
		return str;
	}

	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equals(str2);
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equalsIgnoreCase(str2);
	}

	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (!Character.isLetter(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAlphaSpace(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if ((!Character.isLetter(str.charAt(i))) && (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAlphanumeric(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAlphanumericSpace(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if ((!Character.isLetterOrDigit(str.charAt(i)))
					&& (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumericSpace(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			if ((!Character.isDigit(c)) && (!Character.isSpaceChar(c))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isWhitespace(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static String toUpperCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}

	public static String toLowerCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	public static String[] split(String str) {
		return split(str, null, -1);
	}

	public static String[] split(String str, char separatorChar) {
		if ((str == null) || (str.length() == 0)) {
			return new String[0];
		}
		int length = str.length();

		List<String> list = new ArrayList<String>();
		int i = 0;
		int start = 0;
		boolean match = false;
		while (i < length) {
			if (str.charAt(i) == separatorChar) {
				if (match) {
					list.add(str.substring(start, i));
					match = false;
				}
				i++;
				start = i;
			} else {
				match = true;
				i++;
			}
		}
		if (match) {
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String[] split(String str, String separatorChars) {
		return split(str, separatorChars, -1);
	}

	public static String[] split(String str, String separatorChars, int max) {
		if ((str == null) || (str.length() == 0)) {
			return new String[0];
		}
		int length = str.length();

		List<String> list = new ArrayList<String>();
		int sizePlus1 = 1;
		int i = 0;
		int start = 0;
		boolean match = false;
		if (separatorChars == null) {
			while (i < length) {
				if (Character.isWhitespace(str.charAt(i))) {
					if (match) {
						if (sizePlus1++ == max) {
							i = length;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					i++;
					start = i;
				} else {
					match = true;
					i++;
				}
			}
		}
		if (separatorChars.length() == 1) {
			char sep = separatorChars.charAt(0);
			while (i < length) {
				if (str.charAt(i) == sep) {
					if (match) {
						if (sizePlus1++ == max) {
							i = length;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					i++;
					start = i;
				} else {
					match = true;
					i++;
				}
			}
		} else {
			while (i < length) {
				if (separatorChars.indexOf(str.charAt(i)) >= 0) {
					if (match) {
						if (sizePlus1++ == max) {
							i = length;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					i++;
					start = i;
				} else {
					match = true;
					i++;
				}
			}
		}
		if (match) {
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String join(Object[] array) {
		return join(array, null);
	}

	public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}
		int arraySize = array.length;
		int bufSize = arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0]
				.toString().length()) + 1) * arraySize;

		StringBuffer buf = new StringBuffer(bufSize);
		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	public static String join(Object[] array, String separator) {
		String sepStr = separator;
		if (array == null) {
			return null;
		}
		if (separator == null) {
			sepStr = "";
		}
		int arraySize = array.length;
		if (arraySize == 0) {
			return "";
		}
		int bufSize = arraySize
				* ((array[0] == null ? 16 : array[0].toString().length()) + sepStr
						.length());

		StringBuffer buf = new StringBuffer(bufSize);
		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(sepStr);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	public static String join(Iterator<?> iterator, char separator) {
		if (iterator == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(256);
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
			if (iterator.hasNext()) {
				buf.append(separator);
			}
		}
		return buf.toString();
	}

	public static String join(Iterator<?> iterator, String separator) {
		if (iterator == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(256);
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
			if ((separator != null) && (iterator.hasNext())) {
				buf.append(separator);
			}
		}
		return buf.toString();
	}

	public static int indexOf(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}
		return str.indexOf(searchChar);
	}

	public static int indexOf(String str, char searchChar, int startPos) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}
		return str.indexOf(searchChar, startPos);
	}

	public static int indexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}
		return str.indexOf(searchStr);
	}

	public static int indexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}
		if ((searchStr.length() == 0) && (startPos >= str.length())) {
			return str.length();
		}
		return str.indexOf(searchStr, startPos);
	}

	public static int indexOfAny(String str, char[] searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length == 0)) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int indexOfAny(String str, String searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < searchChars.length(); j++) {
				if (searchChars.charAt(j) == ch) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int lastIndexOf(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}
		return str.lastIndexOf(searchChar);
	}

	public static int lastIndexOf(String str, char searchChar, int startPos) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}
		return str.lastIndexOf(searchChar, startPos);
	}

	public static int lastIndexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}
		return str.lastIndexOf(searchStr);
	}

	public static int lastIndexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}
		return str.lastIndexOf(searchStr, startPos);
	}

	public static int lastIndexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}
		int searchStrsLength = searchStrs.length;
		int index = -1;
		int tmp = 0;
		for (int i = 0; i < searchStrsLength; i++) {
			String search = searchStrs[i];
			if (search != null) {
				tmp = str.lastIndexOf(search);
				if (tmp > index) {
					index = tmp;
				}
			}
		}
		return index;
	}

	public static boolean contains(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}
		return str.indexOf(searchChar) >= 0;
	}

	public static boolean contains(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return false;
		}
		return str.indexOf(searchStr) >= 0;
	}

	public static int countMatches(String str, String subStr) {
		if ((str == null) || (str.length() == 0) || (subStr == null)
				|| (subStr.length() == 0)) {
			return 0;
		}
		int count = 0;
		int index = 0;
		while ((index = str.indexOf(subStr, index)) != -1) {
			count++;
			index += subStr.length();
		}
		return count;
	}

	public static String substring(String str, int start) {
		int iStart = start;
		if (str == null) {
			return null;
		}
		if (start < 0) {
			iStart = str.length() + start;
		}
		if (iStart < 0) {
			iStart = 0;
		}
		if (iStart >= str.length()) {
			return "";
		}
		return str.substring(iStart);
	}

	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}
		int iEnd = end;
		int iStart = start;
		if (end < 0) {
			iEnd = str.length() + end;
		}
		if (start < 0) {
			iStart = str.length() + start;
		}
		if (iEnd > str.length()) {
			iEnd = str.length();
		}
		if (iStart > iEnd) {
			return "";
		}
		if (iStart < 0) {
			iStart = 0;
		}
		if (iEnd < 0) {
			iEnd = 0;
		}
		return str.substring(iStart, iEnd);
	}

	public static String deleteWhitespace(String str) {
		if (str == null) {
			return null;
		}
		int sz = str.length();
		StringBuffer buffer = new StringBuffer(sz);
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				buffer.append(str.charAt(i));
			}
		}
		return buffer.toString();
	}

	public static String replaceOnce(String text, String repl, String with) {
		return replace(text, repl, with, 1);
	}

	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	public static String replace(String text, String repl, String with, int max) {
		if ((text == null) || (repl == null) || (with == null)
				|| (repl.length() == 0) || (max == 0)) {
			return text;
		}
		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		int end = 0;
		int iMax = max;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			iMax--;
			if (iMax == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
	}
}

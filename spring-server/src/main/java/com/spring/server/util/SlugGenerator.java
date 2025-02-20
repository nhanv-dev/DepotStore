package com.spring.server.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

public class SlugGenerator {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w_-]");
    private static final Pattern SEPARATORS = Pattern.compile("[\\s\\p{Punct}&&[^-]]");

    public static String toSlug(String input) {
        String noSeparators = SEPARATORS.matcher(deAccent(input)).replaceAll("-");
        String normalized = Normalizer.normalize(noSeparators, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH).replaceAll("-{2,}", "-").replaceAll("^-|-$", "");
    }

    public static String toHash(Set<String> strs) {
        StringBuilder hash = new StringBuilder();
        for (String str : strs) {
            hash.append(toSlug(str));
            hash.append("-");
        }
        return hash.toString();
    }

    public static String deAccent(String input) {
        String nfdNormalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern
                .matcher(nfdNormalizedString)
                .replaceAll("")
                .replaceAll("đ", "d")
                .replaceAll("Đ", "D");
    }

    public static void main(String[] args) {
        System.out.println(toSlug("Áo hoodie gấu dâu Lotso local brand form rộng OneZ màu hồng nỉ bông hàn quốc đẹp ,mềm mịn có mũ 2 lớp dày 1"));
    }
}

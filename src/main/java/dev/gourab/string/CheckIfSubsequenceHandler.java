package dev.gourab.string;

@FunctionalInterface
public interface CheckIfSubsequenceHandler {
  boolean isSubsequence(final String string, final String subString);
}

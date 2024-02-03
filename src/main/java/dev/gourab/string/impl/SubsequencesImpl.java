package dev.gourab.string.impl;

import dev.gourab.string.CheckIfSubsequenceHandler;

public class SubsequencesImpl implements CheckIfSubsequenceHandler {

  @Override
  public boolean isSubsequence(String string, String subString) {
    if (string.isBlank())
      return true;
    var initChIdx = 0;
    for (final var subCh : subString.toCharArray()) {
      final var finalChIdx = string.indexOf(subCh, initChIdx);
      if (finalChIdx == -1)
        return false;
      initChIdx = finalChIdx + 1;
    }
    return true;
  }
}

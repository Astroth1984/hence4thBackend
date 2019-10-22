package com.internship.application.storage;

@Test
public void testShortText() throws IOException {
    LanguageDetector detector = new OptimaizeLangDetector().setShortText(true).loadModels();
    // First verify that we get no result with empty or very short text.
    LanguageWriter writer = new LanguageWriter(detector);
    writer.append("");
    assertEquals(LanguageConfidence.NONE, detector.detect().getConfidence());
    writer.reset();
    writer.append("  ");
    assertEquals(LanguageConfidence.NONE, detector.detect().getConfidence());
    for (String language : getTestLanguages()) {
        // Short pieces of Japanese are detected as Chinese
        if (language.equals("ja")) {
            continue;
        }
        // We need at least 300 characters to detect Chinese reliably.
        writer.reset();
        writeTo(language, writer, 300);
        LanguageResult result = detector.detect();
        assertNotNull(String.format(Locale.US, "Language '%s' wasn't detected", language), result);
        assertTrue(String.format(Locale.US, "Language '%s' was detected as '%s'", language, result.getLanguage()), result.isLanguage(language));
        assertTrue(String.format(Locale.US, "Language '%s' isn't reasonably certain: %s", language, result.getConfidence()), result.isReasonablyCertain());
    }
    writer.close();
}
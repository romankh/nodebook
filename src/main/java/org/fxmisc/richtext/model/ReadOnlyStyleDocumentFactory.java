package org.fxmisc.richtext.model;

import java.util.List;

public class ReadOnlyStyleDocumentFactory {

    public static <PS, SEG, S> ReadOnlyStyledDocument<PS, SEG, S> createReadOnlyStyledDocument(List<Paragraph<PS, SEG, S>> paragraphs) {
        return new ReadOnlyStyledDocument<PS, SEG, S>(paragraphs);
    }
}

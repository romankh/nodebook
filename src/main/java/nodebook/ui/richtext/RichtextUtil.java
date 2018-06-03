package nodebook.ui.richtext;

import com.google.common.collect.Lists;
import javafx.scene.Node;
import nodebook.persistence.entities.Content;
import nodebook.persistence.entities.ContentParagraph;
import nodebook.persistence.entities.ContentSegment;
import nodebook.persistence.codec.ParStyleCodec;
import nodebook.persistence.codec.TextStyleCodec;
import nodebook.ui.richtext.codec.NoOpParCodec;
import nodebook.ui.richtext.codec.NoOpTextCodec;
import nodebook.ui.richtext.content.LinkedImage;
import nodebook.ui.richtext.content.LinkedImageOps;
import nodebook.ui.richtext.style.ColorStyle;
import nodebook.ui.richtext.style.ParStyle;
import nodebook.ui.richtext.style.TextStyle;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.TextExt;
import org.fxmisc.richtext.model.Codec;
import org.fxmisc.richtext.model.GenericEditableStyledDocument;
import org.fxmisc.richtext.model.Paragraph;
import org.fxmisc.richtext.model.ReadOnlyStyleDocumentFactory;
import org.fxmisc.richtext.model.ReadOnlyStyledDocument;
import org.fxmisc.richtext.model.SegmentOps;
import org.fxmisc.richtext.model.StyledDocument;
import org.fxmisc.richtext.model.StyledSegment;
import org.fxmisc.richtext.model.TextOps;
import org.reactfx.util.Either;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class RichtextUtil {
    private static final TextOps<String, TextStyle> styledTextOps = SegmentOps.styledTextOps();
    private static final LinkedImageOps<TextStyle> linkedImageOps = new LinkedImageOps<>();
    private static final int fontSize = 11;
    private static final String fontFamily = "Sans";
    private static final TextStyleCodec textStyleCodec = new TextStyleCodec(fontSize, fontFamily);
    private static final ParStyleCodec parStyleCodec = new ParStyleCodec();

    public static GenericStyledArea<ParStyle, Either<String, LinkedImage>, TextStyle> getStyledTextArea() {
        GenericStyledArea<ParStyle, Either<String, LinkedImage>, TextStyle> area = new GenericStyledArea<>(
                // default paragraph style
                ParStyle.builder().build(),
                // paragraph style setter
                (paragraph, style) -> paragraph.setStyle(style.toCss()),
                // default segment style
                TextStyle.builder(fontSize, fontFamily).fontColor(ColorStyle.BLACK).build(),
                // segment operations
                styledTextOps._or(linkedImageOps, (s1, s2) -> Optional.empty()),
                // node factory
                seg -> createNode(seg, (text, style) -> text.setStyle(style.toCss()))
        );

        area.setWrapText(true);
        area.setStyleCodecs(
                new NoOpParCodec(),
                Codec.styledSegmentCodec(Codec.eitherCodec(Codec.STRING_CODEC, LinkedImage.codec()), new NoOpTextCodec()));
        area.setParagraphGraphicFactory(LineNumberFactory.get(area));
        return area;
    }

    private static Node createNode(StyledSegment<Either<String, LinkedImage>, TextStyle> seg,
                                   BiConsumer<? super TextExt, TextStyle> applyStyle) {
        return seg.getSegment().unify(
                text -> StyledTextArea.createStyledTextNode(text, seg.getStyle(), applyStyle),
                LinkedImage::createNode
        );
    }

    public static StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> getEmptyDocument() {
        List<Paragraph<ParStyle, Either<String, LinkedImage>, TextStyle>> parList = new ArrayList<>();

        StyledSegment<Either<String, LinkedImage>, TextStyle> segment = new StyledSegment<>(
                Either.left(""),
                TextStyle.builder(fontSize, fontFamily).build()
        );

        List<StyledSegment<Either<String, LinkedImage>, TextStyle>> styledSegments = Lists.newArrayList(
                segment
        );

        Paragraph<ParStyle, Either<String, LinkedImage>, TextStyle> paragraph = new Paragraph<>(
                ParStyle.builder().build(),
                styledTextOps._or(linkedImageOps, (s1, s2) -> Optional.empty()),
                styledSegments
        );

        parList.add(paragraph);

        ReadOnlyStyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> roDoc
                = ReadOnlyStyleDocumentFactory.createReadOnlyStyledDocument(
                parList
        );

        return new GenericEditableStyledDocument<>(roDoc);
    }

    public static Content mapToContent(StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> document,
                                       String id) {

        Content content = new Content();
        content.setId(id);

        List<ContentParagraph> contentParagraphs = new ArrayList<>();

        for (Paragraph<ParStyle, Either<String, LinkedImage>, TextStyle> documentParagraph : document.getParagraphs()) {
            ContentParagraph contentParagraph = new ContentParagraph();
            contentParagraph.setStyle(parStyleCodec.encode(documentParagraph.getParagraphStyle()));

            List<ContentSegment> contentSegments = new ArrayList<>();

            for (StyledSegment<Either<String, LinkedImage>, TextStyle> documentSegment
                    : documentParagraph.getStyledSegments()) {
                ContentSegment contentSegment = new ContentSegment();
                contentSegment.setStyle(textStyleCodec.encode(documentSegment.getStyle()));
                contentSegment.setText(documentSegment.getSegment().getLeft());
                contentSegments.add(contentSegment);
            }

            contentParagraph.setSegments(contentSegments);
            contentParagraphs.add(contentParagraph);
        }

        content.setParagraphs(contentParagraphs);

        return content;
    }

    public static StyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> mapToStyledDocument(Content content) {
        List<Paragraph<ParStyle, Either<String, LinkedImage>, TextStyle>> parList = new ArrayList<>();

        for (ContentParagraph contentParagraph : content.getParagraphs()) {
            ParStyle parStyle = parStyleCodec.decode(contentParagraph.getStyle());

            List<StyledSegment<Either<String, LinkedImage>, TextStyle>> styledSegments = new ArrayList<>();

            for (ContentSegment contentSegment : contentParagraph.getSegments()) {
                StyledSegment<Either<String, LinkedImage>, TextStyle> segment = new StyledSegment<>(
                        Either.left(contentSegment.getText()),
                        textStyleCodec.decode(contentSegment.getStyle())
                );
                styledSegments.add(segment);
            }

            Paragraph<ParStyle, Either<String, LinkedImage>, TextStyle> paragraph = new Paragraph<>(
                    parStyle,
                    styledTextOps._or(linkedImageOps, (s1, s2) -> Optional.empty()),
                    styledSegments
            );
            parList.add(paragraph);
        }

        ReadOnlyStyledDocument<ParStyle, Either<String, LinkedImage>, TextStyle> readOnlyStyledDocument
                = ReadOnlyStyleDocumentFactory.createReadOnlyStyledDocument(
                parList
        );

        return new GenericEditableStyledDocument<>(readOnlyStyledDocument);
    }
}

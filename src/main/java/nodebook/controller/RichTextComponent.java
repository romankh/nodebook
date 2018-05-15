package nodebook.controller;

import javafx.scene.Node;
import javafx.scene.control.IndexRange;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import nodebook.richtext.content.LinkedImage;
import nodebook.richtext.content.LinkedImageOps;
import nodebook.richtext.style.ParStyle;
import nodebook.richtext.style.TextStyle;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.TextExt;
import org.fxmisc.richtext.model.Codec;
import org.fxmisc.richtext.model.SegmentOps;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyledSegment;
import org.fxmisc.richtext.model.TextOps;
import org.reactfx.util.Either;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Component
public class RichTextComponent {
    private final TextOps<String, TextStyle> styledTextOps = SegmentOps.styledTextOps();
    private final LinkedImageOps<TextStyle> linkedImageOps = new LinkedImageOps<>();
    private GenericStyledArea<ParStyle, Either<String, LinkedImage>, TextStyle> area = new GenericStyledArea<>(
            ParStyle.EMPTY,                                                 // default paragraph style
            (paragraph, style) -> paragraph.setStyle(style.toCss()),        // paragraph style setter
            TextStyle.EMPTY.updateFontSize(12).updateFontFamily("Serif").updateTextColor(Color.BLACK),  // default segment style
            styledTextOps._or(linkedImageOps, (s1, s2) -> Optional.empty()),                            // segment operations
            seg -> createNode(seg, (text, style) -> text.setStyle(style.toCss())));                     // Node creator and segment style setter

    {
        area.setWrapText(true);
        area.setStyleCodecs(
                ParStyle.CODEC,
                Codec.styledSegmentCodec(Codec.eitherCodec(Codec.STRING_CODEC, LinkedImage.codec()), TextStyle.CODEC));
    }

    public void initalize(AnchorPane richtextPane) {
        AnchorPane.setTopAnchor(area, 0.0);
        AnchorPane.setLeftAnchor(area, 0.0);
        AnchorPane.setRightAnchor(area, 0.0);
        AnchorPane.setBottomAnchor(area, 0.0);
        area.setParagraphGraphicFactory(LineNumberFactory.get(area));
        richtextPane.getChildren().add(area);
    }

    public void toggleBold() {
        Function<StyleSpans<TextStyle>, TextStyle> mixinGetter = spans ->
                TextStyle.bold(!spans.styleStream().allMatch(style -> style.bold.orElse(false)));
        updateStyleInSelection(mixinGetter);
    }

    public void toggleItalic() {
        Function<StyleSpans<TextStyle>, TextStyle> mixinGetter = spans ->
                TextStyle.italic(!spans.styleStream().allMatch(style -> style.italic.orElse(false)));
        updateStyleInSelection(mixinGetter);
    }

    public void toggleUnderline() {
        Function<StyleSpans<TextStyle>, TextStyle> mixinGetter = spans ->
                TextStyle.underline(!spans.styleStream().allMatch(style -> style.underline.orElse(false)));
        updateStyleInSelection(mixinGetter);
    }

    public void toggleStrikethrough() {
        Function<StyleSpans<TextStyle>, TextStyle> mixinGetter = spans ->
                TextStyle.strikethrough(!spans.styleStream().allMatch(style -> style.strikethrough.orElse(false)));
        updateStyleInSelection(mixinGetter);
    }

    private void updateStyleInSelection(Function<StyleSpans<TextStyle>, TextStyle> mixinGetter) {
        IndexRange selection = area.getSelection();
        if (selection.getLength() != 0) {
            StyleSpans<TextStyle> styles = area.getStyleSpans(selection);
            TextStyle mixin = mixinGetter.apply(styles);
            StyleSpans<TextStyle> newStyles = styles.mapStyles(style -> style.updateWith(mixin));
            area.setStyleSpans(selection.getStart(), newStyles);
            area.requestFocus();
        }
    }

    private Node createNode(StyledSegment<Either<String, LinkedImage>, TextStyle> seg,
                            BiConsumer<? super TextExt, TextStyle> applyStyle) {
        return seg.getSegment().unify(
                text -> StyledTextArea.createStyledTextNode(text, seg.getStyle(), applyStyle),
                LinkedImage::createNode
        );
    }
}

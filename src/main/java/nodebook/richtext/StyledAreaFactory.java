package nodebook.richtext;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import nodebook.richtext.content.LinkedImage;
import nodebook.richtext.content.LinkedImageOps;
import nodebook.richtext.codec.ParCodec;
import nodebook.richtext.style.ParStyle;
import nodebook.richtext.codec.TextCodec;
import nodebook.richtext.style.TextStyle;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyledTextArea;
import org.fxmisc.richtext.TextExt;
import org.fxmisc.richtext.model.Codec;
import org.fxmisc.richtext.model.SegmentOps;
import org.fxmisc.richtext.model.StyledSegment;
import org.fxmisc.richtext.model.TextOps;
import org.reactfx.util.Either;

import java.util.Optional;
import java.util.function.BiConsumer;

public class StyledAreaFactory {
    private static final TextOps<String, TextStyle> styledTextOps = SegmentOps.styledTextOps();
    private static final LinkedImageOps<TextStyle> linkedImageOps = new LinkedImageOps<>();

    public static GenericStyledArea<ParStyle, Either<String, LinkedImage>, TextStyle> getStyledTextArea() {
        GenericStyledArea<ParStyle, Either<String, LinkedImage>, TextStyle> area = new GenericStyledArea<>(
                // default paragraph style
                ParStyle.builder().build(),
                // paragraph style setter
                (paragraph, style) -> paragraph.setStyle(style.toCss()),
                // default segment style
                TextStyle.builder(11, "Sans").fontColor(Color.BLACK).build(),
                // segment operations
                styledTextOps._or(linkedImageOps, (s1, s2) -> Optional.empty()),
                // node factory
                seg -> createNode(seg, (text, style) -> text.setStyle(style.toCss()))
        );

        area.setWrapText(true);
        area.setStyleCodecs(
                new ParCodec(),
                Codec.styledSegmentCodec(Codec.eitherCodec(Codec.STRING_CODEC, LinkedImage.codec()), new TextCodec()));
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
}

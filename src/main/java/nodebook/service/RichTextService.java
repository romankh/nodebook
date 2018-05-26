package nodebook.service;

import javafx.scene.control.IndexRange;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import nodebook.richtext.StyledAreaFactory;
import nodebook.richtext.content.LinkedImage;
import nodebook.richtext.style.ParStyle;
import nodebook.richtext.style.TextHeader;
import nodebook.richtext.style.TextStyle;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.model.Paragraph;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.TwoDimensional;
import org.reactfx.util.Either;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Service
public class RichTextService {
    private final String SEPARATOR = "--------------------------------------------------------------------------------";
    private GenericStyledArea<ParStyle, Either<String, LinkedImage>, TextStyle> area = StyledAreaFactory
            .getStyledTextArea();

    public void initalize(AnchorPane richtextPane) {
        AnchorPane.setTopAnchor(area, 0.0);
        AnchorPane.setLeftAnchor(area, 0.0);
        AnchorPane.setRightAnchor(area, 0.0);
        AnchorPane.setBottomAnchor(area, 0.0);

        richtextPane.getChildren().add(area);
    }

    public void toggleBold() {
        Function<StyleSpans<TextStyle>, StyleSpans<TextStyle>> mixinGetter = styles -> {
            boolean bold = !styles.styleStream().allMatch(TextStyle::isBold);
            return styles.mapStyles(style -> TextStyle.builder(style).bold(bold).build());
        };
        updateStyleInSelection(mixinGetter);
    }

    public void toggleItalic() {
        Function<StyleSpans<TextStyle>, StyleSpans<TextStyle>> mixinGetter = styles -> {
            boolean italic = !styles.styleStream().allMatch(TextStyle::isItalic);
            return styles.mapStyles(style -> TextStyle.builder(style).italic(italic).build());
        };
        updateStyleInSelection(mixinGetter);
    }

    public void toggleUnderline() {
        Function<StyleSpans<TextStyle>, StyleSpans<TextStyle>> mixinGetter = styles -> {
            boolean underline = !styles.styleStream().allMatch(TextStyle::isUnderline);
            return styles.mapStyles(style -> TextStyle.builder(style).underline(underline).build());
        };
        updateStyleInSelection(mixinGetter);
    }

    public void toggleStrikethrough() {
        Function<StyleSpans<TextStyle>, StyleSpans<TextStyle>> mixinGetter = styles -> {
            boolean strikethrough = !styles.styleStream().allMatch(TextStyle::isStrikethrough);
            return styles.mapStyles(style -> TextStyle.builder(style).strikethrough(strikethrough).build());
        };
        updateStyleInSelection(mixinGetter);
    }

    public void setFontColor(Color color) {
        Function<StyleSpans<TextStyle>, StyleSpans<TextStyle>> mixinGetter = styles -> {
            return styles.mapStyles(style -> TextStyle.builder(style).fontColor(color).build());
        };
        updateStyleInSelection(mixinGetter);
    }

    public void setBackgroundColor(Color color) {
        Function<StyleSpans<TextStyle>, StyleSpans<TextStyle>> mixinGetter = styles -> {
            return styles.mapStyles(style -> TextStyle.builder(style).backgroundColor(color).build());
        };
        updateStyleInSelection(mixinGetter);
    }

    public void toggleHeader1() {
        toggleHeader(TextHeader.H1);
    }

    public void toggleHeader2() {
        toggleHeader(TextHeader.H2);
    }

    public void toggleHeader3() {
        toggleHeader(TextHeader.H3);
    }

    public void toggleBulletList() {
        IndexRange selection = area.getSelection();
        if (selection.getLength() != 0) {
            int startPar = area.offsetToPosition(selection.getStart(), TwoDimensional.Bias.Forward).getMajor();
            int endPar = area.offsetToPosition(selection.getEnd(), TwoDimensional.Bias.Backward).getMajor();
            for (int i = startPar; i <= endPar; ++i) {
                Paragraph<ParStyle, Either<String, LinkedImage>, TextStyle> paragraph = area.getParagraph(i);
                ParStyle parStyle = paragraph.getParagraphStyle();
                ParStyle newParStyle = ParStyle.builder(parStyle).bulletList(!parStyle.isBulletList()).build();

                if (newParStyle.isBulletList()) {
                    area.insertText(i, 0, "• ");
                } else if (paragraph.substring(0, 2).startsWith("• ")) {
                    area.replaceText(i, 0, i, 2, "");
                }
                area.setParagraphStyle(i, newParStyle);
            }
        }
    }

    public void toggleNumberedList() {
        IndexRange selection = area.getSelection();
        if (selection.getLength() != 0) {
            int startPar = area.offsetToPosition(selection.getStart(), TwoDimensional.Bias.Forward).getMajor();
            int endPar = area.offsetToPosition(selection.getEnd(), TwoDimensional.Bias.Backward).getMajor();
            int number = 0;
            for (int i = startPar; i <= endPar; ++i) {
                Paragraph<ParStyle, Either<String, LinkedImage>, TextStyle> paragraph = area.getParagraph(i);
                ParStyle parStyle = paragraph.getParagraphStyle();
                ParStyle newParStyle = ParStyle.builder(parStyle).numberedList(!parStyle.isNumberedList()).build();

                if (newParStyle.isNumberedList()) {
                    area.insertText(i, 0, String.valueOf(++number) + ". ");
                } else if (paragraph.substring(0, 3).startsWith(String.valueOf(++number) + ". ")) {
                    area.replaceText(i, 0, i, 3, "");
                }

                area.setParagraphStyle(i, newParStyle);
            }
        }
    }

    public void addDate() {
        LocalDate date = LocalDate.now();
        insertText("\n" + date.toString() + "\n");
    }

    public void addDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        insertText("\n" + LocalDateTime.now().format(formatter) + "\n");
    }

    public void addSeparator() {
        insertText("\n" + SEPARATOR + "\n");
    }

    public void clearFormat() {
        IndexRange selection = area.getSelection();
        if (selection.getLength() != 0) {
            TextStyle initial = area.getInitialTextStyle();
            area.setStyle(selection.getStart(), selection.getEnd(), initial);

            int startPar = area.offsetToPosition(selection.getStart(), TwoDimensional.Bias.Forward).getMajor();
            int endPar = area.offsetToPosition(selection.getEnd(), TwoDimensional.Bias.Backward).getMajor();

            for (int i = startPar; i < endPar; i++) {
                area.setParagraphStyle(i, area.getInitialParagraphStyle());
            }
            area.requestFocus();
        }
    }

    private void updateStyleInSelection(Function<StyleSpans<TextStyle>, StyleSpans<TextStyle>> mixinGetter) {
        IndexRange selection = area.getSelection();
        if (selection.getLength() != 0) {
            StyleSpans<TextStyle> styles = area.getStyleSpans(selection);
            StyleSpans<TextStyle> newStyles = mixinGetter.apply(styles);
            area.setStyleSpans(selection.getStart(), newStyles);
            area.requestFocus();
        }
    }

    private void insertText(String text) {
        IndexRange selection = area.getSelection();
        area.insertText(selection.getEnd(), text);
    }

    private void toggleHeader(TextHeader header) {
        Function<StyleSpans<TextStyle>, StyleSpans<TextStyle>> mixinGetter = styles -> {
            boolean isHeader = styles.styleStream().allMatch(style -> style.getHeader().equals(header));
            TextHeader newHeader = isHeader ? TextHeader.NONE : header;
            return styles.mapStyles(style -> TextStyle.builder(style).header(newHeader).build());
        };
        updateStyleInSelection(mixinGetter);
    }
}

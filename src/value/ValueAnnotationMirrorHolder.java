package value;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.util.Elements;
import org.checkerframework.framework.source.SourceChecker;
import org.checkerframework.javacutil.AnnotationBuilder;
import value.qual.BoolVal;
import value.qual.BottomVal;
import value.qual.IntRange;
import value.qual.StringVal;
import value.qual.UnknownVal;

/** A holder class that holds AnnotationMirrors */
public class ValueAnnotationMirrorHolder {

    public static AnnotationMirror UNKNOWNVAL;
    public static AnnotationMirror STRINGVAL;
    public static AnnotationMirror BOOLVAL;
    public static AnnotationMirror INTRANGE;
    public static AnnotationMirror BOTTOMVAL;

    public static void init(SourceChecker checker) {
        Elements elements = checker.getElementUtils();

        UNKNOWNVAL = AnnotationBuilder.fromClass(elements, UnknownVal.class);
        STRINGVAL = AnnotationBuilder.fromClass(elements, StringVal.class);
        BOOLVAL = AnnotationBuilder.fromClass(elements, BoolVal.class);
        INTRANGE = AnnotationBuilder.fromClass(elements, IntRange.class);
        BOTTOMVAL = AnnotationBuilder.fromClass(elements, BottomVal.class);
    }
}
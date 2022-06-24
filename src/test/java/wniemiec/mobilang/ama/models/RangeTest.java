package wniemiec.mobilang.ama.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RangeTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Range<?> range;
    private Number begin;
    private Number end;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        range = null;
        begin = null;
        end = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testGetBegin() {
        withBegin(0);
        buildRange();
        assertGetBeginIsCorrect();
    }

    @Test
    void testGetEnd() {
        withEnd(0);
        buildRange();
        assertGetEndIsCorrect();
    }

    @Test
    void testGetBeginAndEnd() {
        withBegin(-2);
        withEnd(10);
        buildRange();
        assertGetBeginIsCorrect();
        assertGetEndIsCorrect();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withBegin(Number number) {
        begin = number;
    }

    private void withEnd(Number number) {
        end = number;
    }

    private void buildRange() {
        range = new Range<Number>(begin, end);
    }

    private void assertGetBeginIsCorrect() {
        Assertions.assertEquals(begin, range.getBegin());
    }

    private void assertGetEndIsCorrect() {
        Assertions.assertEquals(end, range.getEnd());
    }
}

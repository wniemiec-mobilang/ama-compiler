package wniemiec.mobilex.ama.models.tag;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilex.ama.models.tag.Tag;
import wniemiec.mobilex.ama.models.tag.TagContainer;


class TagContainerTest extends TagTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private TagContainer tagContainer;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        tagContainer = null;
    }
    

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testAddSibling() {
        withFirstTag(Tag.getNormalInstance("html"));
        withSecondTag(Tag.getNormalInstance("div"));
        withThirdTag(Tag.getNormalInstance("p"));
        addChildInFirstTag(secondTag);
        createTagContainer(secondTag, firstTag);
        usingTagContainerAddSibling(thirdTag);
        assertChildrenOfTagContainerParentIs(secondTag, thirdTag);
        assertThirdTagParentIs(firstTag);
    }

    @Test
    void testReplaceTagTo() {
        withFirstTag(Tag.getNormalInstance("html"));
        withSecondTag(Tag.getNormalInstance("div"));
        withThirdTag(Tag.getNormalInstance("p"));
        addChildInFirstTag(secondTag);
        createTagContainer(secondTag, firstTag);
        usingTagContainerReplaceTagTo(thirdTag);
        assertChildrenOfTagContainerParentIs(thirdTag);
        assertThirdTagParentIs(firstTag);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void createTagContainer(Tag tag, Tag parent) {
        tagContainer = new TagContainer(tag, parent);
    }

    private void usingTagContainerAddSibling(Tag tag) {
        tagContainer.addSibling(tag);
    }

    private void assertChildrenOfTagContainerParentIs(Tag... children) {
        Assertions.assertEquals(
            Arrays.asList(children),
            tagContainer.getParent().getChildren()
        );
    }

    private void assertThirdTagParentIs(Tag tag) {
        Assertions.assertEquals(tag, thirdTag.getParent());
    }

    private void usingTagContainerReplaceTagTo(Tag tag) {
        tagContainer.replaceTagTo(tag);
    }
}

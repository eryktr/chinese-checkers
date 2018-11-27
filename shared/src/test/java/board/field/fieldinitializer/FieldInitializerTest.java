package board.field.fieldinitializer;

import game.board.Board;
import game.board.field.Field;
import game.board.field.FieldColor;
import game.board.field.fieldinitializer.ConcreteFieldInitializer;
import game.board.field.fieldinitializer.FieldInitializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FieldInitializerTest {

    private Board board;
    private FieldInitializer fieldInitializer;
    private Field[][] initializedFields;

    @Before
    public void prepareTest() {
        board = new Board();
        fieldInitializer = new ConcreteFieldInitializer(board);
        board.initializeFields();
        initializedFields = board.getFields();
    }

    @Test
    public void IllegalFieldsAreProperlyInitialized() {
        Field potentialIllegalField1 = initializedFields[0][0];
        Field potentialIllegalField2 = initializedFields[16][16];
        Field potentialIllegalField3 = initializedFields[3][1];
        Assert.assertFalse(potentialIllegalField1.isLegal());
        Assert.assertFalse(potentialIllegalField2.isLegal());
        Assert.assertFalse(potentialIllegalField3.isLegal());
    }

    @Test
    public void HexagonIsProperlyInitialized() {
        Field potentialHexagonField1 = initializedFields[4][8];
        Field potentialHexagonField2 = initializedFields[4][12];
        Field potentialHexagonField3 = initializedFields[12][4];
        Assert.assertTrue(potentialHexagonField1.isLegal() && !potentialHexagonField1.isHomeField());
        Assert.assertTrue(potentialHexagonField2.isLegal() && !potentialHexagonField1.isHomeField());
        Assert.assertTrue(potentialHexagonField3.isLegal() && !potentialHexagonField3.isHomeField());
    }

    @Test
    public void HomeFieldsAreProperlyInitialized() {
        Field potentialRedField = initializedFields[13][4];
        Field potentialGreenField = initializedFields[3][12];
        Field potentialBlueField = initializedFields[4][4];
        Field potentialYellowField = initializedFields[4][14];
        Field potentialPurpleField = initializedFields[9][3];
        Field potentialOrangeField = initializedFields[10][11];

        Assert.assertTrue(isProperlyIntializedHomeField(potentialRedField, FieldColor.RED));
        Assert.assertTrue(isProperlyIntializedHomeField(potentialGreenField, FieldColor.GREEN));
        Assert.assertTrue(isProperlyIntializedHomeField(potentialBlueField, FieldColor.BLUE));
        Assert.assertTrue(isProperlyIntializedHomeField(potentialYellowField, FieldColor.YELLOW));
        Assert.assertTrue(isProperlyIntializedHomeField(potentialPurpleField, FieldColor.PURPLE));
        Assert.assertTrue(isProperlyIntializedHomeField(potentialOrangeField, FieldColor.ORANGE));
    }

    private boolean isProperlyIntializedHomeField(Field field, FieldColor desiredColor) {
        return field.getColor() == desiredColor && field.isHomeField();
    }
}

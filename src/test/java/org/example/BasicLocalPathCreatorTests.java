package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasicLocalPathCreatorTests {
    BasicLocalPathCreator basicLocalPathCreator = new BasicLocalPathCreator();

    @Test
    public void getNewFileNameTest() {
        //Given
        String filePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-scokiosk_be_BY.properties";
        String expectedFileName = "res-visualisation-scokiosk.properties";

        //When
        String realFileName = basicLocalPathCreator.getNewFileName(filePath);

        //When
        Assertions.assertEquals(expectedFileName, realFileName);
    }


    @Test
    public void getNewFileNameTest_2() {
        //Given
        String filePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/newVersion_res-visualisation-scokiosk_be_BY.properties";
        String expectedFileName = "newVersion_res-visualisation-scokiosk.properties";

        //When
        String realFileName = basicLocalPathCreator.getNewFileName(filePath);

        //When
        Assertions.assertEquals(expectedFileName, realFileName);
    }
}

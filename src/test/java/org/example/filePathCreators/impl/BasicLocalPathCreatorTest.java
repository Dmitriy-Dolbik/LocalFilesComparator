package org.example.filePathCreators.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasicLocalPathCreatorTest {
    BasicLocalPathCreator basicLocalFilePathCreator = new BasicLocalPathCreator();

    @Test
    public void getBasicLocalFileNameTest() {
        //Given
        String filePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-scokiosk_be_BY.properties";
        String expectedFileName = "res-visualisation-scokiosk.properties";

        //When
        String realFileName = basicLocalFilePathCreator.getBasicLocalFileName(filePath);

        //When
        Assertions.assertEquals(expectedFileName, realFileName);
    }


    @Test
    public void getBasicLocalFileNameTest_2() {
        //Given
        String filePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/newVersion_res-visualisation-scokiosk_be_BY.properties";
        String expectedFileName = "newVersion_res-visualisation-scokiosk.properties";

        //When
        String realFileName = basicLocalFilePathCreator.getBasicLocalFileName(filePath);

        //When
        Assertions.assertEquals(expectedFileName, realFileName);
    }

    @Test
    public void getBasicLocalFilePath() {
        //Given
        String filePath =         "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-scokiosk_be_BY.properties";
        String expectedFileName = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-scokiosk.properties";

        //When
        String realFileName = basicLocalFilePathCreator.create(filePath);

        //When
        Assertions.assertEquals(expectedFileName, realFileName);
    }
}

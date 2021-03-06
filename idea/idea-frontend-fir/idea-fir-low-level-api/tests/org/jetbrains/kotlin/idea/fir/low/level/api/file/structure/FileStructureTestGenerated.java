/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api.file.structure;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class FileStructureTestGenerated extends AbstractFileStructureTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInFileStructure() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @TestMetadata("class.kt")
    public void testClass() throws Exception {
        runTest("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure/class.kt");
    }

    @TestMetadata("localClass.kt")
    public void testLocalClass() throws Exception {
        runTest("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure/localClass.kt");
    }

    @TestMetadata("localFun.kt")
    public void testLocalFun() throws Exception {
        runTest("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure/localFun.kt");
    }

    @TestMetadata("nestedClasses.kt")
    public void testNestedClasses() throws Exception {
        runTest("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure/nestedClasses.kt");
    }

    @TestMetadata("topLevelExpressionBodyFunWithType.kt")
    public void testTopLevelExpressionBodyFunWithType() throws Exception {
        runTest("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure/topLevelExpressionBodyFunWithType.kt");
    }

    @TestMetadata("topLevelExpressionBodyFunWithoutType.kt")
    public void testTopLevelExpressionBodyFunWithoutType() throws Exception {
        runTest("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure/topLevelExpressionBodyFunWithoutType.kt");
    }

    @TestMetadata("topLevelFunWithType.kt")
    public void testTopLevelFunWithType() throws Exception {
        runTest("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure/topLevelFunWithType.kt");
    }

    @TestMetadata("topLevelUnitFun.kt")
    public void testTopLevelUnitFun() throws Exception {
        runTest("idea/idea-frontend-fir/idea-fir-low-level-api/testdata/fileStructure/topLevelUnitFun.kt");
    }
}

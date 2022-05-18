package com.complexible.pinto;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.domain.TestMappings;
import org.jsmart.zerocode.core.runner.parallel.ZeroCodeLoadRunner;
import org.jsmart.zerocode.core.runner.parallel.ZeroCodeMultiLoadRunner;
import org.junit.Ignore;
import org.junit.runner.RunWith;

//test that will each spawned user execute

@TestMappings({
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testReadRdfListOfObjects"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testReadRdfListOfPrimitives"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWritePrimitives"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testReadPrimitives"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testReadMixed"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteMixed"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteListOfPrimitives"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteListsOfPrimitivesAsRdfListsWithOption"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteListsOfPrimitivesAsRdfListWithAnnotation"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteListOfObjects"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteListsOfObjectsAsRdfListWithOption"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteListsOfObjectsAsRdfListWithAnnotation"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testUseRdfIdForIdentification"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testReadSetsIdentifiableId"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteSetsIdentifiableId"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteWithCodec"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testReadWithCodec"),
        @TestMapping(testClass = RDFMapperTests.class, testMethod = "testReadMap"),
})
//how many users are we going to spawn
@LoadWith("my_load_config.properties")

//run it with zero code load runner
@RunWith(ZeroCodeMultiLoadRunner.class)
@Ignore
public class LoadTest {
}

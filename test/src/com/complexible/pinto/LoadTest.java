package com.complexible.pinto;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.runner.parallel.ZeroCodeLoadRunner;
import org.junit.runner.RunWith;

//test that will each spawned user execute
@TestMapping(testClass = RDFMapperTests.class, testMethod = "testWriteListOfObjects")


//how many users are we going to spawn
@LoadWith("my_load_config.properties")

//run it with zero code load runner
@RunWith(ZeroCodeLoadRunner.class)
public class LoadTest {
}

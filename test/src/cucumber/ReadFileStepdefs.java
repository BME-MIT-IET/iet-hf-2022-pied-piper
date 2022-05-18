package cucumber;
import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openrdf.model.Model;
import org.openrdf.model.impl.SimpleValueFactory;
import org.openrdf.model.util.Models;

import java.io.IOException;

import static org.junit.Assert.*;


public class ReadFileStepdefs {
    private Model aGraph;
    RDFMapperTests.ClassWithPrimitiveLists aExpected;
    RDFMapperTests.ClassWithPrimitiveLists aResult;
    @Given("there is a file with data")
    public void readFromFile() throws IOException {
        aGraph = ModelIO.read(RDFMapperTests.Files3.classPath("/data/primitive_lists.nt").toPath());

       aResult = RDFMapper.create().readValue(aGraph,
                RDFMapperTests.ClassWithPrimitiveLists.class,
                SimpleValueFactory.getInstance().createIRI("tag:complexible:pinto:b7d283d3a73c7b8a870087942b9a43b1"));

    }

    @When("I ask whether it is the same as the one we created")
    public void createSameObjectFromCode() {
        aExpected = new RDFMapperTests.ClassWithPrimitiveLists();

        aExpected.setInts(Lists.newArrayList(4, 5));
        aExpected.setFloats(Sets.newLinkedHashSet(Lists.newArrayList(8f, 20f)));
        aExpected.setBools(Sets.newTreeSet(Lists.newArrayList(true, false)));
        aExpected.setDoubles(Sets.newLinkedHashSet(Lists.newArrayList(22d, 33d)));

    }

    @Then("I should be told they are the same")
    public void checkIfTheyAreEqual() {
        assertEquals(aExpected, aResult);
    }

    @Given("there is another file with data")
    public void readFromAnotherFile() throws IOException {
        aGraph = ModelIO.read(RDFMapperTests.Files3.classPath("/data/primitive_lists.nt").toPath());

        aResult = RDFMapper.create().readValue(aGraph,
                RDFMapperTests.ClassWithPrimitiveLists.class,
                SimpleValueFactory.getInstance().createIRI("tag:complexible:pinto:b7d283d3a73c7b8a870087942b9a43b1"));

    }

    @When("I ask whether it is still the same as the one we created")
    public void createDifferentObjectFromCode() {
        aExpected = new RDFMapperTests.ClassWithPrimitiveLists();

        aExpected.setFloats(Sets.newLinkedHashSet(Lists.newArrayList(8f, 20f)));
        aExpected.setBools(Sets.newTreeSet(Lists.newArrayList(true, false)));
        aExpected.setDoubles(Sets.newLinkedHashSet(Lists.newArrayList(22d, 33d)));

    }

    @Then("I should be told they are not the same")
    public void checkIfTheyAreEqual2() {
        assertNotEquals(aExpected, aResult);
    }


}

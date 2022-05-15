package cucumber;

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

import static org.junit.Assert.*;

public class Stepdefs {
    RDFMapperTests.ClassWithObjectList aObj;
    RDFMapperTests.ClassWithObjectList aOtherObj;
    Model aGraph;
    Model aOtherGraph;
    @Given("there is a set of data")
    public void createGraphWithSameData() {
        aObj = new RDFMapperTests.ClassWithObjectList();
        aObj.setCollection(Sets.newLinkedHashSet(Lists.newArrayList(new RDFMapperTests.Person("Earl Weaver"), new RDFMapperTests.Person("Brooks Robinson"))));
        aObj.id(SimpleValueFactory.getInstance().createIRI("tag:complexible:pinto:4f372f7bfb03f7b80be8777603d3b1ed"));
    }

    @When("I ask whether two graphs from the same data are isomorphic")
    public void writeObjTwice() {
        aGraph = RDFMapper.create().writeValue(aObj);
        aOtherGraph = RDFMapper.create().writeValue(aObj);
    }

    @Then("I should be told they are isomorphic")
    public void checkIfTheyAreIsomorphic() {
        assertTrue(Models.isomorphic(aGraph, aOtherGraph));
    }

    @Given("there are two sets of data")
    public void createGraphWithDifferentData() {
        aObj = new RDFMapperTests.ClassWithObjectList();
        aObj.setCollection(Sets.newLinkedHashSet(Lists.newArrayList(new RDFMapperTests.Person("Earl Weaver"), new RDFMapperTests.Person("Brooks Robinson"))));
        aObj.id(SimpleValueFactory.getInstance().createIRI("tag:complexible:pinto:4f372f7bfb03f7b80be8777603d3b1ed"));

        aOtherObj = new RDFMapperTests.ClassWithObjectList();
        aOtherObj.setCollection(Sets.newLinkedHashSet(Lists.newArrayList(new RDFMapperTests.Person("Earl Weaver"), new RDFMapperTests.Person("Not Brooks Robinson"))));
        aOtherObj.id(SimpleValueFactory.getInstance().createIRI("tag:complexible:pinto:4f372f7bfb03f7b80be8777603d3b1ed"));
    }

    @When("I ask whether two graphs from different data are isomorphic")
    public void writeDifferentObjTwice() {
        aGraph = RDFMapper.create().writeValue(aObj);
        aOtherGraph = RDFMapper.create().writeValue(aOtherObj);
    }

    @Then("I should be told they are not isomorphic")
    public void checkIfTheyAreIsomorphic2() {
        assertFalse(Models.isomorphic(aGraph, aOtherGraph));
    }





}
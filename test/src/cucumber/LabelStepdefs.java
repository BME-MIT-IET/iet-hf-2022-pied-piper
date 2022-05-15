package cucumber;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.common.openrdf.model.Statements;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openrdf.model.Literal;
import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.SimpleValueFactory;
import org.openrdf.model.vocabulary.XMLSchema;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LabelStepdefs {
    RDFMapperTests.Company aCompany;
    Model aGraph;
    Optional<Statement> aStatement;
    Literal aResult;
    Integer numberOfEmployees;
    @Given("there is a company with random number of employees")
    public void createCompanyRandomEmployees() throws IOException {
        Random rand = new Random();
        numberOfEmployees = rand.nextInt(100);
        aCompany= new RDFMapperTests.Company();
        aCompany.setNumberOfEmployees(numberOfEmployees);

        aGraph= RDFMapper.create().writeValue(aCompany);
    }

    @When("I ask whether it has the label 'numberOfEmployees'")
    public void getLabel() {

        aStatement = aGraph.stream().filter(Statements.predicateIs(SimpleValueFactory.getInstance().createIRI(RDFMapper.DEFAULT_NAMESPACE + "numberOfEmployees"))).findFirst();
        aResult= (Literal) aStatement.get().getObject();
    }

    @Then("I should be told they have that many employees")
    public void checkLabel() {
        assertTrue("should have found the triple", aStatement.isPresent());
        assertEquals(XMLSchema.INTEGER, aResult.getDatatype());
        assertEquals(numberOfEmployees.toString(), aResult.getLabel());
    }

    @Given("there is a company with {int} number of employees")
    public void createCompany(int employees) throws IOException {
        numberOfEmployees = employees;
        aCompany= new RDFMapperTests.Company();
        aCompany.setNumberOfEmployees(numberOfEmployees);

        aGraph= RDFMapper.create().writeValue(aCompany);
    }

    @When("I ask whether it has the label 'numberOfEmployees' again")
    public void getLabel2() {

        aStatement = aGraph.stream().filter(Statements.predicateIs(SimpleValueFactory.getInstance().createIRI(RDFMapper.DEFAULT_NAMESPACE + "numberOfEmployees"))).findFirst();
        aResult= (Literal) aStatement.get().getObject();
    }

    @Then("I should be told they have {int} employees")
    public void checkLabel2(int employees) {
        assertTrue("should have found the triple", aStatement.isPresent());
        assertEquals(XMLSchema.INTEGER, aResult.getDatatype());
        assertEquals(String.valueOf(employees), aResult.getLabel());
    }

}

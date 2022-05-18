package cucumber;

import com.complexible.pinto.MappingOptions;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests;
import com.complexible.pinto.UnidentifiableObjectException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UnidentifiableStepdefs {
  RDFMapper aMapper;
  Exception exception;
  RDFMapperTests.Company aCompany;
  @Given("there is an empty map created")
  public void initializeMapper() throws IOException {
    aMapper = RDFMapper.builder()
      .set(MappingOptions.REQUIRE_IDS, true)
      .build();

  }

  @When("I ask whether it is writable without any exceptions")
  public void tryWritingMap() {
    try {
      aMapper.writeValue(new RDFMapperTests.ClassWithPrimitives());
    }
    catch (Exception e) {
      exception = e;
    }

  }

  @Then("I should be told they are not")
  public void checkIfCorrectExceptionWasThrown() {
    assertTrue(exception instanceof UnidentifiableObjectException);
  }


  @Given("there is a map created with one element")
  public void initializeMapper2() throws IOException {
    exception = null;
    aMapper = RDFMapper.builder()
      .set(MappingOptions.REQUIRE_IDS, true)
      .build();
    aCompany = new RDFMapperTests.Company();
    aCompany.setName("Clark & Parsia");
    aCompany.setWebsite("http://clarkparsia.com");
  }

  @When("I ask whether it is written without any exceptions")
  public void tryWritingMap2() {

    try {
      aMapper.writeValue(aCompany);
    }
    catch (Exception e) {
      exception = e;
    }

  }

  @Then("I should be told it is")
  public void checkIfExceptionWasNotThrown() {
    assertNull(exception);
  }







}

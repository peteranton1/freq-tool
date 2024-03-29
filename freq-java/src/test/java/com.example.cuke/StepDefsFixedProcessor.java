package com.example.cuke;

import com.example.freq.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.cuke.ScenarioContext.ScenarioKeys.*;
import static com.example.cuke.StepDefsUtils.toInt;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.fail;

@ScenarioScoped
public class StepDefsFixedProcessor {

    private ScenarioContext scenarioContext;

    @Before
    public void setUp() {
        scenarioContext = new ScenarioContext();
    }

    @Given("I have the following data in the reader")
    public void i_have_the_following_data_in_the_reader(DataTable dataTable) {
        scenarioContext.setScenarioValue(FDP_testData,
                new TestDataTable(dataTable.asMaps().stream()
                        .map(m -> m.get("line"))
                        .collect(Collectors.toList())));
    }

    @Given("I have the following data read from the files")
    public void i_have_the_following_data_read_from_the_files(DataTable dataTable) {
        scenarioContext.setScenarioValue(FDP_testData,
                new TestDataTable(readLinesFromFiles(dataTable.asMaps().stream()
                        .map(m -> m.get("file"))
                        .collect(Collectors.toList()))));
    }

    @Given("my columns are {string} to {string}")
    public void my_columns_are_start_to_end(String startCol, String finishCol) {
        FixedDataConfig fixedDataConfig = (FixedDataConfig) scenarioContext
                .getScenarioValue(FDP_fixedDataConfig);
        if (fixedDataConfig == null) {
            fixedDataConfig = new FixedDataConfig();
        }
        fixedDataConfig.addCols(toInt(startCol), toInt(finishCol));
        scenarioContext.setScenarioValue(FDP_fixedDataConfig, fixedDataConfig);
    }

    @When("A Fixed Data Processor is created and run")
    public void a_fixed_data_processor_is_created_and_run() {

        TestDataTable testDataTable = (TestDataTable) scenarioContext
                .getScenarioValue(FDP_testData);

        FixedDataConfig fixedDataConfig = (FixedDataConfig) scenarioContext
                .getScenarioValue(FDP_fixedDataConfig);

        FixedDataProcessor underTest = new FixedDataProcessor();

        StringDataReader dataReader = new StringDataReader(testDataTable
                .getLines(), DataType.FIXED);
        StringBTree actual = (StringBTree) underTest.process(
                dataReader, fixedDataConfig);

        scenarioContext.setScenarioValue(FDP_actual, actual);
    }

    @Then("I expect the following results")
    public void i_expect_the_following_results(DataTable dataTable) {
        StringBTree actual = (StringBTree) scenarioContext
                .getScenarioValue(FDP_actual);
        Assertions.assertThat(actual).isNotNull();
        List<Counter<String>> results = actual.resultsInOrder();

        List<Map<String, String>> expectedList = dataTable.asMaps();
        List<Counter<String>> expected = expectedList.stream()
                .map(this::extractStringCounter)
                .collect(Collectors.toList());

        Assertions.assertThat(results.toString())
                .isEqualTo(expected.toString());
    }

    private List<String> readLinesFromFiles(List<String> fileList) {
        List<String> datalines = new ArrayList<>();
        for (String filepath : fileList) {
            try {
                datalines.addAll(Files.readAllLines(Path.of(filepath)));
            } catch (Exception e) {
                fail(format("IOError: %s", e.getMessage()), e);
            }
        }
        return datalines;
    }

    private Counter<String> extractStringCounter(Map<String, String> m) {
        return new Counter<>(m.get("field"), toInt(m.get("count")));
    }

    private String wrap(Map<String, String> m) {
        return format("|%s|", m.get("field"));
    }
}

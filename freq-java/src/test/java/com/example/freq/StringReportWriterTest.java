package com.example.freq;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class StringReportWriterTest {

    @Test
    public void shouldWriteReportOk() {
        ReportConfig config = new ReportConfig(
                "TestTitle",
                ImmutableList.of("field1", "field2"));
        List<Counter<String>> results = ImmutableList.of(
                new Counter<>("|VAL1A|VAL2A|",2),
                new Counter<>("|VAL1B|VAL2B|",1)
        );
        String expected = "TestTitle\n" +
                "---------\n" +
                "\tField: field1\n" +
                "\tField: field2\n" +
                "\tCount:        2\n" +
                "\tSum  :        3\n" +
                "---------\n" +
                "\n" +
                "\t|VAL1A|VAL2A|:        2    66.67%\n" +
                "\t|VAL1B|VAL2B|:        1    33.33%\n" +
                "\n" +
                "---------\n" +
                "\tCount:        2\n" +
                "\tSum  :        3\n" +
                "---------\n" +
                "\n";
        StringReportWriter underTest = new StringReportWriter();
        String actual = underTest.writeReport(results,config);
        // System.out.println(actual);
        assertThat(actual).isEqualTo(expected);
    }
}
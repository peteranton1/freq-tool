package com.example.freq;

import java.util.List;

public class StringReportWriter
        implements ReportWriter<String> {
    private static final String NEWLINE = System.lineSeparator();

    @Override
    public String writeReport(List<Counter<String>> results,
                              ReportConfig config) {
        StringBuilder buf = new StringBuilder();
        int sum = sumCounters(results);
        int count = results.size();
        buf.append(buildHeader(config, sum, count));
        buf.append(buildBody(results, sum, count));
        buf.append(buildFooter(config, sum, count));
        return buf.toString();
    }

    private String buildBody(List<Counter<String>> results,
                             int sum,
                             int count) {
        StringBuilder buf = new StringBuilder();

        for (Counter<String> result : results) {
            int counter = result.getCounter();
            buf.append("\t");
            buf.append(String.format("%s: %8d %8.2f%%",
                    result.getValue(),
                    counter,
                    getColPC(counter, sum)));
            buf.append(NEWLINE);
        }
        return buf.toString();
    }

    private double getColPC(int counter, int sum) {
        return ((double) counter / (double) sum) * 100;
    }

    private String buildHeader(ReportConfig config,
                               int sum,
                               int count) {
        StringBuilder buf = new StringBuilder();

        String title = config.getTitle();
        buf.append(title);
        buf.append(NEWLINE);
        buf.append("-".repeat(title.length()));
        buf.append(NEWLINE);
        for (String field : config.getFields()) {
            buf.append(String.format("\tField: %s", field));
            buf.append(NEWLINE);
        }
        buf.append(String.format("\tCount: %8d", count));
        buf.append(NEWLINE);
        buf.append(String.format("\tSum  : %8d", sum));
        buf.append(NEWLINE);
        buf.append("-".repeat(title.length()));
        buf.append(NEWLINE);
        buf.append(NEWLINE);

        return buf.toString();
    }

    private String buildFooter(ReportConfig config,
                               int sum,
                               int count) {
        StringBuilder buf = new StringBuilder();

        String title = config.getTitle();
        buf.append(NEWLINE);
        buf.append("-".repeat(title.length()));
        buf.append(NEWLINE);

        buf.append(String.format("\tCount: %8d", count));
        buf.append(NEWLINE);
        buf.append(String.format("\tSum  : %8d", sum));
        buf.append(NEWLINE);
        buf.append("-".repeat(title.length()));
        buf.append(NEWLINE);
        buf.append(NEWLINE);

        return buf.toString();
    }

    private int sumCounters(List<Counter<String>> results) {
        return results.stream().map(Counter::getCounter).reduce(0, Integer::sum);
    }
}

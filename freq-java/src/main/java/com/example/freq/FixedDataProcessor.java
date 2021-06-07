package com.example.freq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FixedDataProcessor implements DataProcessor<String> {

    private static final String EMPTY_STRING = "";

    @Override
    public BTree<String> process(DataReader<String> dataReader,
                                 DataConfig<String> dataConfig) {
        if (!(dataConfig instanceof FixedDataConfig)) {
            throw new FreqException(String.format(
                    "Expected: FixedDataConfig, actual: %s",
                    dataConfig.getClass().getName()));
        }
        FixedDataConfig fixedDataConfig = (FixedDataConfig) dataConfig;
        StringBTree btree = new StringBTree();
        while (dataReader.hasNext()) {
            String value = Optional.ofNullable(extractValue(
                    dataReader.next().value(), fixedDataConfig))
                    .orElse(EMPTY_STRING);
            btree.add(value);
        }
        return btree;
    }

    public String extractValue(String line, FixedDataConfig fixedDataConfig) {
        List<String> fieldValues = new ArrayList<>();
        List<List<Integer>> configCols = fixedDataConfig.getCols();
        for (List<Integer> fieldCols : configCols) {
            String fieldValue = extractFieldValue(line, fieldCols);
            fieldValues.add(fieldValue);
        }
        StringBuilder buf = new StringBuilder();
        for (String fieldValue : fieldValues) {
            buf.append("|");
            if (fieldValue != null) {
                buf.append(fieldValue);
            }
        }
        buf.append("|");
        return buf.toString();
    }

    public String extractFieldValue(String line, List<Integer> fieldCols) {
        if (line == null) {
            return EMPTY_STRING;
        }
        if (fieldCols.size() != 2) {
            throw new FreqException(String.format(
                    "FieldCols != 2: (%s)", fieldCols));
        }
        int startCol = fieldCols.get(0);
        int finishCol = fieldCols.get(1);
        if (finishCol < startCol) {
            throw new FreqException(String.format(
                    "FieldCols start > finish: (%s)", fieldCols));
        }
        int lineLen = line.length();
        String value = EMPTY_STRING;
        if (startCol < lineLen) {
            if (finishCol > lineLen){
                value = line.substring(startCol);
            } else{
                value = line.substring(startCol, finishCol);
            }
        }
        return value;
    }

}

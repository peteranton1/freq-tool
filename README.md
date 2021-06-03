# freq-tool
A simple command line tool for frequency analysis reports

# How to compile and run

## Run tests

The tests are run line any go program.

From the folder containing a (file).go and (file)_test.go, issue:

```aidl
    go test (file).go
    e.g.
    go test freq.go
```

## Run REPL

The REPL is a command line program that runs the program then 
allows additional commands.

From the folder containing main.go:

```aidl
    go run main.go
```

## Build executable and run

From the folder containing main.go:

```aidl
    go build -o freq . && ./freq
```




#!/bin/bash
go build -o freq .
./freq fixed -f ../data/testdata.txt -v 1:7
package cli

import (
    "flag"
    "fmt"
    "os"
    "strings"
)

type Cli struct {
    Modes       []string
    Files       []string
    Vars        []string
}

func New() *Cli {
    c := &Cli{  Modes: []string{}, Files: []string{}, Vars: []string{}}
    return c
}

func SafeSplit(value string, sep string) []string {
    outVal := []string{}
    temp := strings.Split(value, sep)
    outVal = append(temp)
    return outVal
}

func usage() {
    fmt.Println(
            "Usage: \n\t<prog> <modes> -f <files> -v <vars>\n" +
            "e.g.: \n\t<prog> fixed -f /some/path -v 11:12\n")
    flag.PrintDefaults()
    os.Exit(1)
}

func (c *Cli) Parse(args []string) {

    // Subcommands
    fixedCommand := flag.NewFlagSet("fixed", flag.ExitOnError)

    filesPtr := fixedCommand.String("f",
                    "", "-f files <path>(,<path>)*")
    varsPtr := fixedCommand.String("v",
                    "", "-v vars <start:end>(,<start:end>)*")

    if len(args) < 2 {
        usage()
    }

    modes := SafeSplit(args[1],",")
    switch modes[0] {
    case "fixed":
        fixedCommand.Parse(args[2:])
    default:
        usage()
    }

    if fixedCommand.Parsed() {
        // Required Flags
        if *filesPtr == "" || *varsPtr == "" {
            if *filesPtr == "" {
                fmt.Println("\tNo file(s) specified -f")
            }
            if *varsPtr == "" {
                fmt.Println("\tNo variable(s) specified -v")
            }
            usage()
        }
        // extract field values
        c.Modes = append(c.Modes, args[1])
        c.Files = append(c.Files, *filesPtr)
        c.Vars = append(c.Vars, *varsPtr)
    }
}

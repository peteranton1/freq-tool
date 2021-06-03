package cli

import (
    "flag"
    "fmt"
    "os"
)

type Cli struct {
    Modes       string
    Files       string
    Vars        string
}

func New() *Cli {
    c := &Cli{  Modes: "fixed", Files: "", Vars: ""}
    return c
}

func (c *Cli) Parse(args []string) {

    // Subcommands
    fixedCommand := flag.NewFlagSet("fixed", flag.ExitOnError)

    filesPtr := fixedCommand.String("files",
                    "", "files <path>(,<path>)*")
    varsPtr := fixedCommand.String("vars",
                    "", "vars <start:end>(,<start:end>)*")

    if len(args) < 2 {
        fmt.Println(
            "Usage: \n\t<prog> <modes> <files> <vars>\n" +
            "e.g.: \n\t<prog> fixed -files /some/path -vars 11:12\n")
        os.Exit(1)
    }

    switch args[1] {
    case "fixed":
        fixedCommand.Parse(args[2:])
    default:
        flag.PrintDefaults()
        os.Exit(1)
    }

    if fixedCommand.Parsed() {
        // Required Flags
        if *filesPtr == "" || *varsPtr == "" {
            fmt.Println(
                "Usage: \t<prog> <modes> <files> <vars>");
            if *filesPtr == "" {
                fmt.Println("\tNo file(s) specified")
            }
            if *varsPtr == "" {
                fmt.Println("\tNo variable(s) specified")
            }
            fixedCommand.PrintDefaults()
            os.Exit(1)
        }
        // extract field values
        c.Modes = args[1]
        c.Files = *filesPtr
        c.Vars = *varsPtr
    }
}

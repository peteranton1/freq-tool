// package freq
package main

import (
	"fmt"
	"os"
	"freq/cli"
)

func main() {

	fmt.Printf("freq v1.0 frequency analysis program\n")

	c := cli.New()
	c.Parse(os.Args)

	fmt.Printf("Runtime Parameters: "+
	    "\n\tModes = %s "+
	    "\n\tfiles = %s "+
	    "\n\tvars = %s\n",
	    c.Modes, c.Files, c.Vars)

}
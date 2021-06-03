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
	    "\n\tModes = %v "+
	    "\n\tfiles = %v "+
	    "\n\tvars = %v\n",
	    c.Modes, c.Files, c.Vars)

}
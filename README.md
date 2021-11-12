# Project layout
* core
    * The business logic of the application lives in this module.
* model
    * The models to represent the domain live in this module. 
* gateway
    * all external interaction objects(e.g. files, external APIs etc) live in this module
* main 
    * cli
        * the command line interface into the application lives in this module
    * if one were to need to expose this service over REST, gRPC, as library etc, they simply add a module here that provides the interface they want expose.

# file interface(data format)
* definitions:
    * TeamResult = AlphabetName Score
        * AlphabetName can incldue spaces
    * Delimeter = ", " => comma and one space
* The content of match result line is:
    * pattern: TeamResultDelimeter TeamResult
    * Example:
        * Tarantulas 1, FC Awesome 0
            * TeamResult_A = Tarantulas 1
            * Delimeter = ", "
            * TeamResult_B = FC Awesome 0



# usage
```bash
   # from root directory
   $ cd ./release
   $ ./league_rank.jar sample_match_results.txt
```
# Structure
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

# usage
```bash
   #from root directory
   $ cd ./release
   $ ./league_rank.jar leagueresults.txt
```
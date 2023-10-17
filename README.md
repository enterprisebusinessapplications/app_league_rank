# app_league_rank
![build status](https://github.com/praisetompane/app_league_rank/actions/workflows/app_league_rank.yaml/badge.svg) <br>
command-line application to calculate league rank table for a one-on-one/team-vs-team sport.
# league rank rules
* a draw (tie) is worth 1 point and a win is worth 3 points. 
* a loss is worth 0 points.
* if two or more teams have the same number of points:
    * they are ranked the same.
    * they should be printed in alphabetical order

# project layout
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
    * pattern: TeamResult Delimeter TeamResult
    * Example:
        * Mighty Ducks 1, FC Sabres 0
            * TeamResult_A = Mighty Ducks 1
            * Delimeter = ", "
            * TeamResult_B = FC Sabres 0

# dependencies(for asdf users see .tool-versions file)
* java 11

# build and test
```shell
# from root directory
sbt clean update compile
sbt test
sbt assembly
```

# usage
* clone repository to machine
```shell
# from your project's directory
git clone git@github.com:praisetompane/app_league_rank.git
```

* run commands below to see output with sample file
```shell
# from root directory
cd ./release
./league_rank.jar sample_match_results.txt

# expected output
1. New York Rangers, 6 pts
2. Mighty Ducks, 5 pts
3. Philadelphia Flyers, 1 pt
3. Snakes, 1 pt
5. Boston Bruins, 0 pts
```
* for your own file, run from the release folder with command format below
```
    $ ./league_rank.jar <your_file_name>
```

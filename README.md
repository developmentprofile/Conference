# Conference Scheduler

An application for Conference Scheduling. Problem is a type of Knapsack problem and solved by dynamic programming approach.

## Requirements

* A Conference are maintained by multiple TRACKS
* Tracks are daily plans containing SESSIONS.
* Session is a Conference Talk with definite duration, start and end time.
* TALK is a approve from user to be scheduled for a session. 
* There are two types of TALK, Normal and LIGHTNING.
* Normal TALKS require duration in minutes between 1-180.
* LIGHTNING TALKS are 5 min and does not require duration.
* TALK NAME are IDENTICAL and TALKS with same name will not be approved.
* If there is not SESSION afternoon, LUNCH will not be in TRACK schedule

## Technical Details
* Although it does not a DB, i used H2 for saving incoming TALK requests from user.
* Project has Controller, Service and Repository Layers with unit tests.
* Gets scheduling data through AJAX from REST API.


## Usage
* To run app you need to build with maven install because of MapStruct implementations which generates mapping classes..
* For TALK Insertion use url.
   localhost:8080/insert
* For Conference Scheduling.  
   localhost:8080/list
* If you don't want to enter a lot of data from UI, remove ".remove" extension from data.sql file under resources, it will load some random data to H2 DB.
Raduta Lavinia-Maria 323CA
# Object Oriented Programming Course
## Homework - VideosDB

November 2021

### General idea of the program
- In main, all the databases are populated using the input classes from fileio.
- In the Action Database, for each action read, its type is determined and the
corresponding class is instanced then its method is called.

### COMMANDS:
* FAVORITE:
    * firstly find the user that has the username given
    * search in the user history if the video is already seen
    * if it is not add the title in its favorite list
    * if it already is favourite the command can't be applied
* VIEW:
    * find the user
        -if its history already contains the video, just increment
        -the number of views (the value for the key title)
        -if it is not, put the video's title and 1 view in the history
* RATING:
    * find the user and then search if the video is in the history
    * if it is, search if the movie / show was already rated
    * the user keeps a map for the movies he rated (also keeping
    the rating) and for the series, it keeps a map for each series
    rated, with the index of the season and the rating given
    * if the video is not already rated:
            * for movies: add the new rating to the ratings list and
                then calculate the new average rating
            * for shows: add the rating to the map, corresponding to the
                number of the season, calculate the average rate of each
                season, and then update the average rating for the show
    * if it is, the command can't be applied

### QUERIES:
  * FOR ACTORS:
      * AVERAGE:
          -put all the actors with their average rating (average of all
          the videos they played in) in a map
          -sort the map and return a list of strings, representing the
          first n actors retrieved from the sorted map in the given order
      * AWARDS:
          -put all the actors that have all the mentioned awards
          together with their total number of awards in a map
          -sort that map and return a list of strings with the first
          number names ordered by the given criteria
      * FILTER DESCRIPTION:
          -firstly verify if all the words given are in the description
          it doesn't work with containsALL, have to find the exact words
          -if the actor has all the words, its name is added to a string
          list, that is sorted ascending or descending

     * FOR VIDEOS:
      * RATING:
          -the program works the same for both series and movies
          -firstly verify all the conditions: year, genres
          -if each filter is verified add the name of the video and its
          rating to a map, that will be sorted by the criteria given
          -from the sorted map retrieve the first number names of videos
      * FAVORITE:
          -the program works the same for both series and movies
          -firstly set the likes for the video (counts how many users
          from the data base have the video in their favourite list)
          -verify the conditions such as year, genres and then add the
          name of the video with its like number to a map
          -sort the map and then retrieve the first n names
      * LONGEST:
          -firstly compute the total duration for the shows and then
          verify the filters
          -add the name of the video with its duration to a map, sort
          it and retrieve the first n names
      * MOST VIEWED:
          -firstly set the views for the video (counts how many users
          from the data base have the video in their history and how
          many times they have watched it)
          -add to map with the views and sort it, then get the names
     * FOR USERS:
         -put all the users names and their number of rated videos in a
         map, sort it and then retrieve the first n names

### RECOMMENDATIONS
  * all the recommendations use the video database, not the 2 movie
  and show databases. It contains a list with all the videos, a list
  with the videos sorted by their rating, a list sorted by their number
  of likes, one with the videos sorted by the number of views (for the
  last 3 the second sort criteria is their index in the initial video
  list) and another one with the videos sorted by their rating and with
  the second criteria their name in ascending order
  * FOR ALL THE USERS:
      * STANDARD:
          -just iterate through the video list and return the first
          video that is not in the users' history
      * BEST UNSEEN:
          -just iterate through the list of videos sorted by their
          rating and index and return the first video that is not in
          the users' history
  * FOR THE PREMIUM USERS
      * firstly verify if the user has a premium subscription
      * POPULAR:
          -firstly we construct a map containing the genre and the
          total number of views gained by the videos that have the genre
          in their genre list
          -sort this map descending by the number of views and then find
          the first movie that the user doesn't have in its history that
          has the most popular genre
      * FAVORITE:
          -just iterate through the list of videos sorted by their
          like number and index and return the first video that is not in
          the users' history
      * SEARCH:
          -just iterate through the list of videos sorted by their
          rating and name and return the videos that are not in the
          users' history and have the genre given












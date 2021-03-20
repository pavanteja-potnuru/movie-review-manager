# movie-review-manager
Manages review from users


- ✅ Adding users and movies.
- ✅ User to review a movie.
- ✅ List top n movies by total review score by ‘critics’ in a particular genre.
- ✅ Average review score in a particular year of release.
    - get review by specific user on movies released in a particular year
- ✅ Average review score for a particular movie

### Input
```java
try{
    userManager.addUser("Pavan");
    userManager.addUser("Yesh");
    userManager.addUser("Harsh");
    userManager.addUser("Srinivas");
    userManager.addUser("Harsh");

    movieManager.addMovie("Bahubali1", LocalDate.of(2015, 7, 10), Arrays.asList("action", "fantasy"));
    movieManager.addMovie("Bahubali2", LocalDate.of(2017, 4, 28), Arrays.asList("action", "fantasy"));
    movieManager.addMovie("Don", LocalDate.of(2006, 7, 10), Arrays.asList("action", "comedy"));
    movieManager.addMovie("Bahubali2", LocalDate.of(2017, 4, 28), Arrays.asList("action", "fantasy"));
    movieManager.addMovie("Tiger", LocalDate.of(2008, 1, 1), Arrays.asList("Drama"));
    movieManager.addMovie("Padmaavat", LocalDate.of(2008, 1, 1), Arrays.asList("Comedy"));
    movieManager.addMovie("Lunchbox", LocalDate.of(2008, 1, 1), Arrays.asList("Drama"));
    movieManager.addMovie("Guru", LocalDate.of(2008, 1, 1), Arrays.asList("Drama"));
    movieManager.addMovie("Metro", LocalDate.of(2008, 1, 1), Arrays.asList("Romance"));
    movieManager.addMovie("Saina", LocalDate.of(2021, 03, 26), Arrays.asList("Sport"));

    reviewManager.addReview("Pavan", "Bahubali1", 10);
    reviewManager.addReview("Pavan", "Padmaavat", 9);
    reviewManager.addReview("Pavan", "Guru", 8);
    reviewManager.addReview("Pavan", "Lunchbox", 8);
    reviewManager.addReview("Pavan", "Bahubali2", 10);
    reviewManager.addReview("Pavan", "Metro", 9);
    reviewManager.addReview("Pavan", "Saina", 8);

    reviewManager.addReview("Yesh", "Bahubali1", 9);
    reviewManager.addReview("Yesh", "Bahubali2", 8);
    reviewManager.addReview("Yesh", "Padmaavat", 8);
    reviewManager.addReview("Harsh", "Guru", 10);
    reviewManager.addReview("Harsh", "Lunchbox", 9);
    reviewManager.addReview("Harsh", "Metro", 7);
    reviewManager.addReview("Harsh", "Bahubali2", 10);
    reviewManager.addReview("Harsh", "Tiger", 9);
}
catch (Exception ex) {
    serviceLogger.logError(ex.getMessage(), Color.ANSI_RED);
}
finally {
    System.out.println("------------------------/Print Users\\------------------------");
    userManager.printUsers();
    System.out.println("------------------------/Print Movies with average rating\\------------------------");
    movieManager.printMovies();
    System.out.println("------------------------/Print top n rated Movies byRole and inGenre\\------------------------");
    printList(reviewManager.topNMoviesWithRoleGenre(2, "critic", "Drama"));
    System.out.println("------------------------/Print average review\\------------------------");
    System.out.println(movieManager.getAverageRating("Bahubali1"));
    System.out.println(movieManager.getAverageRating("Bahubali2"));
    System.out.println("------------------------/Print average review given by user to movies released in a particular year\\------------------------");
    System.out.println(reviewManager.getAverageReview("Pavan", 2008));
    System.out.println("------------------------/Print top n rated movies\\------------------------");
    printList(movieManager.getTopNRatedMovies(3));
    System.out.println("------------------------/\\------------------------");
}
```
### Output
```console
Log: Create user with name Pavan Initailized
Log: User Pavan created successfully
Log: Create user with name Yesh Initailized
Log: User Yesh created successfully
Log: Create user with name Harsh Initailized
Log: User Harsh created successfully
Log: Create user with name Srinivas Initailized
Log: User Srinivas created successfully
Log: Create user with name Harsh Initailized
Error: User with name Harsh already exists
Log: Create movie with name Bahubali1 Initailized
Log: Movie Bahubali1 created successfully
Log: Create movie with name Bahubali2 Initailized
Log: Movie Bahubali2 created successfully
Log: Create movie with name Don Initailized
Log: Movie Don created successfully
Log: Create movie with name Bahubali2 Initailized
Error: Movie with name Bahubali2 already exists
Log: Create movie with name Tiger Initailized
Log: Movie Tiger created successfully
Log: Create movie with name Padmaavat Initailized
Log: Movie Padmaavat created successfully
Log: Create movie with name Lunchbox Initailized
Log: Movie Lunchbox created successfully
Log: Create movie with name Guru Initailized
Log: Movie Guru created successfully
Log: Create movie with name Metro Initailized
Log: Movie Metro created successfully
Log: Create movie with name Saina Initailized
Log: Movie Saina created successfully
Log: Initialized: Add movie(Bahubali1) review by user Pavan
Log: Completed: Add movie(Bahubali1) review by user Pavan
Log: Initialized: Add movie(Padmaavat) review by user Pavan
Log: Completed: Add movie(Padmaavat) review by user Pavan
Log: Initialized: Add movie(Guru) review by user Pavan
Log: Completed: Add movie(Guru) review by user Pavan
Log: Initialized: Add movie(Lunchbox) review by user Pavan
Log: Completed: Add movie(Lunchbox) review by user Pavan
Log: Initialized: Add movie(Bahubali2) review by user Pavan
Log: Completed: Add movie(Bahubali2) review by user Pavan
Log: Initialized: Add movie(Metro) review by user Pavan
Log: Completed: Add movie(Metro) review by user Pavan
Log: Initialized: Add movie(Saina) review by user Pavan
Error: You can not review Movie(Saina) that is not released yet
Log: Initialized: Add movie(Bahubali1) review by user Yesh
Log: Completed: Add movie(Bahubali1) review by user Yesh
Log: Initialized: Add movie(Bahubali2) review by user Yesh
Log: Completed: Add movie(Bahubali2) review by user Yesh
Log: Initialized: Add movie(Padmaavat) review by user Yesh
Log: Completed: Add movie(Padmaavat) review by user Yesh
Log: Initialized: Add movie(Guru) review by user Harsh
Log: Completed: Add movie(Guru) review by user Harsh
Log: Initialized: Add movie(Lunchbox) review by user Harsh
Log: Completed: Add movie(Lunchbox) review by user Harsh
Log: Initialized: Add movie(Metro) review by user Harsh
Log: Completed: Add movie(Metro) review by user Harsh
Log: Initialized: Add movie(Bahubali2) review by user Harsh
Log: Completed: Add movie(Bahubali2) review by user Harsh
Log: Initialized: Add movie(Tiger) review by user Harsh
Log: Completed: Add movie(Tiger) review by user Harsh
------------------------/Print Users\------------------------
Pavan critic
Srinivas viewer
Yesh critic
Harsh critic
------------------------/Print Movies with average rating\------------------------
Bahubali1 :     9.5
Saina : 0.0
Bahubali2 :     9.600000381469727
Don :   0.0
Guru :  8.666666984558105
Metro : 8.0
Padmaavat :     8.333333015441895
Lunchbox :      8.333333015441895
Tiger : 9.0
------------------------/Print top n rated Movies byRole and inGenre\------------------------
Lunchbox
Tiger
------------------------/Print average review\------------------------
9.5
9.6
------------------------/Print average review given by user to movies released in a particular year\------------------------
8.5
------------------------/Print top n rated movies\------------------------
Tiger
Bahubali1
Bahubali2
------------------------/\------------------------
```
# movie-review-manager
Manages review from users


### Input
```
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
reviewManager.addReview("Pavan", "Padmaavat", 10);
reviewManager.addReview("Pavan", "Guru", 10);
reviewManager.addReview("Pavan", "Lunchbox", 10);
reviewManager.addReview("Pavan", "Bahubali2", 10);
reviewManager.addReview("Pavan", "Metro", 10);
reviewManager.addReview("Pavan", "Saina", 10);

reviewManager.addReview("Yesh", "Bahubali1", 9);
reviewManager.addReview("Yesh", "Bahubali2", 8);
reviewManager.addReview("Yesh", "Padmaavat", 8);
reviewManager.addReview("Harsh", "Guru", 10);
reviewManager.addReview("Harsh", "Lunchbox", 9);
reviewManager.addReview("Harsh", "Metro", 7);
reviewManager.addReview("Harsh", "Bahubali2", 10);
reviewManager.addReview("Harsh", "Tiger", 9);

System.out.println("------------------------/Print Logs\\------------------------");
serviceLogger.printLogs();
System.out.println("------------------------/Print Users\\------------------------");
userManager.printUsers();
System.out.println("------------------------/Print Movies with average rating\\------------------------");
movieManager.printMovies();
System.out.println("------------------------/Print Movies by role and in genre\\------------------------");
printList(reviewManager.topNMoviesWithRoleGenre(2, "Critic", "Drama"));
System.out.println("------------------------/Print average review\\------------------------");
System.out.println(movieManager.getAverageRating("Bahubali1"));
System.out.println(movieManager.getAverageRating("Bahubali2"));
System.out.println("------------------------/Print top n rated movies\\------------------------");
printList(movieManager.getTopNRatedMovies(3));
System.out.println("------------------------/\\------------------------");
```
### Output
```
------------------------/Print Logs\------------------------
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
Pavan Critic
Srinivas Viewer
Yesh Critic
Harsh Critic
------------------------/Print Movies with average rating\------------------------
Bahubali1 :     9.5
Saina : 0.0
Bahubali2 :     9.600000381469727
Don :   0.0
Guru :  10.0
Metro : 8.5
Padmaavat :     8.666666984558105
Lunchbox :      9.666666984558105
Tiger : 9.0
------------------------/Print Movies by role and in genre\------------------------
Lunchbox
------------------------/Print average review\------------------------
9.5
9.6
------------------------/Print top n rated movies\------------------------
Bahubali2
Lunchbox
Guru
------------------------/\------------------------
```
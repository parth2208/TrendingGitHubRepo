# TrendingGitHubRepository

## App Features
- Users can view the most trending repositories in Android from Github.
- Users can sort based on name and stars of the repositories
- User can see the description, details, language used, forks and stars of all the trending repositories
- It uses the shimmer animation to while fetching the data from server and in due process if it fails, it will shows an error screen stating the reason.

## App Architecture
This project uses MVVM Architecture.

![picture alt](https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.sovereignconsult.com%2Fblog%2Fknow-mvvm-clean-architecture-android-app%2F&psig=AOvVaw1UexlNOTj3aOAoG__EmYcM&ust=1584088719927000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOCRrcbElOgCFQAAAAAdAAAAABAa )


## The app includes the following main components:
- A local database that servers as a single source of truth for data presented to the user.
- A web api service.
- A repository that works with the database and the api service, providing a unified data interface.
- A ViewModel that provides data specific for the UI.
- The UI, which shows a visual representation of the data in the ViewModel.
- Unit Test cases for API service, Database, Repository and ViewModel.

## App Packages
__di__ - contains component and module using Dagger2
  __component__ - contains the dagger annotated component class
  __module__ - contains the module class NetworkApiModule for Api calls and DB to create the instance of Database
__model.db__ - contains the entity, abstract database class and Dao interface.
__remote__ - contains RepoServiceApi interface for getting the response
__ui__ - contains classes needed to display Activity and adapter
  __adapter__ - contains the adapter class of recyclerview to display the items in a list.
__util__ - contains classes needed for activity redirection, ui components.
__viewModel__ - contains MainViewModel and it's viewmodel factory

##App Specs
- Minimum SDK 19
- Kotlin is used as language
- MVVM Architecture
- Android Architecture Components (LiveData, Lifecycle, ViewModel, Room Persistence Library, ConstraintLayout)
- RxJava2 for implementing Observable pattern.
- Dagger 2 for dependency injection.
- Retrofit 2 for API integration.
- Moshi for serialisation.
- Okhhtp3 for mocking web server.
- Mockito for implementing unit test cases
- Glide for image loading.
- Shimmer Animation by Facebook is used to show loading state.

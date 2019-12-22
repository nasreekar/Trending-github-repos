# Github Trending Repo's

This is a simple single screen app which shows the current trending Github repositories fetched from a public API.

# Overview

  - Min SDK - 19
  - Fetches Trending Repo's from public API
  - While fetching, display loading animation (implemented shimmer animation)
  - All the items in the list are in their collapsed state by default and can be expanded on tap
  - The app handles configuration changes (like rotation)
  - The app has 100% offline support. Once the data is fetched successfully from remote,
it is stored locally and served from cache thereafter till the cache is not expired
- The cached data is valid for a duration of 2 hour
- Pull to refresh to fetch data

## Todos
  - Unit Testing and UI Testing 
  - Dagger/Koin Implementation
  - If the app is not able to fetch the data, then it should show an error state to the user with an
option to retry again
- Automate the app to fetch new data after the cache gets expired in 2 hrs

### Tech
* Android Architecture components
    * MVVM
    * Kotlin
    * RxJava
    * LiveData
    * Repository Pattern
    * Glide
    * Room database
    * Retrofit
    
License
----

MIT


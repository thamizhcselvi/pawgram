# Pawgram - Pet Lovers Social App

A modern Android application built with Kotlin and Jetpack Compose that connects pet lovers from around the world. Users can add their pets, share photos, and discover pet care services nearby.

## Features

### 🏠 Home Screen
- **User Profile**: Welcome message with user profile picture
- **Your Pets**: Horizontal scrollable cards showing pet information
  - Pet profile pictures
  - Name, breed, and age
  - Progress indicators for pet care tasks
- **Pet Care Nearby**: List of nearby pet care centers
  - Center logos and ratings
  - Distance information
  - Available services
  - "See more" functionality

### 🔍 Explore Screen
- **Featured Pets**: Showcase of amazing pets from the community
- **Discover Section**: Encourages users to share their pet stories
- **Catchy Content**: Engaging UI with pet images and descriptions

### 👤 Profile Screen
- **User Information**: Profile picture, name, and bio
- **Statistics**: Pets count, posts, and followers
- **Quick Actions**: Add pet, create post, and settings
- **About Section**: Personal description and pet stories

## Architecture

The app follows the **MVVM (Model-View-ViewModel)** architecture pattern:

### 📁 Project Structure
```
app/src/main/java/com/pawgram/app/
├── data/
│   ├── api/
│   │   ├── PawgramApiService.kt
│   │   └── MockPawgramApiService.kt
│   ├── model/
│   │   ├── Pet.kt
│   │   ├── PetCareCenter.kt
│   │   └── User.kt
│   └── repository/
│       └── PawgramRepository.kt
├── ui/
│   ├── components/
│   │   ├── PetCard.kt
│   │   └── PetCareCenterCard.kt
│   ├── navigation/
│   │   └── BottomNavigation.kt
│   ├── screens/
│   │   ├── HomeScreen.kt
│   │   ├── ExploreScreen.kt
│   │   └── ProfileScreen.kt
│   ├── theme/
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── viewmodel/
│       └── HomeViewModel.kt
└── MainActivity.kt
```

### 🏗️ Architecture Components

- **Model**: Data classes for Pet, PetCareCenter, and User
- **View**: Jetpack Compose UI components
- **ViewModel**: HomeViewModel managing UI state and data operations
- **Repository**: PawgramRepository handling data operations
- **API Service**: MockPawgramApiService providing dummy data

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Networking**: Retrofit (configured for future real API integration)
- **Image Loading**: Coil
- **Serialization**: Kotlin Serialization
- **Navigation**: Custom bottom navigation implementation

## Design System

The app features a modern dark theme with:
- **Primary Color**: Green (#4CAF50)
- **Secondary Color**: Orange (#FF9800)
- **Background**: Dark (#121212)
- **Surface**: Dark gray (#1E1E1E)
- **Typography**: Material Design 3 typography scale

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24 or higher
- Kotlin 1.9.10

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/pawgram.git
```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or physical device

### Building the App

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

## Data Sources

The app currently uses mock data from `MockPawgramApiService` which includes:
- Sample pets (Brian the Bengal cat, Soda the Dalmatian)
- Pet care centers (Paws Up, Inooko, Migu)
- User profile information

Images are loaded from Unsplash URLs for demonstration purposes.

## Future Enhancements

- [ ] Real API integration with backend services
- [ ] User authentication and registration
- [ ] Pet photo posting functionality
- [ ] Social features (likes, comments, follows)
- [ ] Push notifications
- [ ] Location-based pet care center discovery
- [ ] Pet health tracking
- [ ] Community features and forums

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Design inspiration from modern social media apps
- Pet images from [Unsplash](https://unsplash.com)
- Material Design 3 guidelines
- Jetpack Compose documentation and samples 
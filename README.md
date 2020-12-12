# Bulk Book Review
*Idea: Bulk review checking for books*
These days, everyone wants to read reviews before commiting to something. But it's a **pain** to have to individually google several things in a row

### Eventually: 
1. User takes a photo of a bookshelf
2. App identifies book text
3. App filters out irrelevant text/numbers: "Edited", "Library", "Author"
4. App queries for matching books
5. Display results, sorted by rating

### Currently:
1. Analyzes image and extracts lines of words using Google Cloud Vision API
2. Filters out prices and other "noise"
3. Consumes Google Books API, processing image data
4. Prints book information


#### Setup: 
1. Register for [Google API Key](https://console.developers.google.com/)
2. Enable Google Books API and Google Cloud Vision API use in developer console
3. Setup authorization for [Google Cloud Vision API](https://cloud.google.com/vision/docs/setup)
4. In Parent Directory, create API_KEY.txt file with Google API Key
5. Optional: Add image file to project directory and name it image.jpeg (or update filePath in Main.java with image name)
6. (Currently: Build/Run Main.java)

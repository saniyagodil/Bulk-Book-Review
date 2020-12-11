# Bulk Book Review
*Idea: Bulk review checking for books*
These days, everyone wants to read reviews before commiting to something. But it's a **pain** to have to individually google several things in a row

### Eventually: 
1. User takes a photo of a bookshelf
2. App identifies book text
3. App filters out irrelevant text/numbers: "Edited", "Library", "By" and potentially scores queries, filtering out ones that only include words like: 'The', 'So', 'A')
4. App queries for books
5. Display results

### Currently:
1. Analyzes image and extracts lines of words using Google Cloud Vision API
2. Consumes Google Books API, processing image data, each (ideally) representing a book as a list of Strings.
Returns matching book results


#### Setup: 
1. Register for [Google API Key](https://console.developers.google.com/)
2. Enable Google Books API and Google Cloud Vision API use in developer console
3. Setup authorization for [Google Cloud Vision API](https://cloud.google.com/vision/docs/setup)
4. In Parent Directory:
    - Create API_KEY.txt file with Google API Key
    - Add image file and name it image.jpeg (or update filePath in Main.java with image name)
6. (Currently: Build/Run Main.java)

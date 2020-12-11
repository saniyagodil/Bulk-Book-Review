# BookProject
*Idea: Bulk review checking for books*
These days, everyone wants to read reviews before commiting to something. But it's a **pain** to have to individually google several things in a row

### Eventually: 
1. User takes a photo of a bookshelf
2. App identifies book text
3. App filters out irrelevant text/numbers: "Edited", "Library", "By"
4. (App potentially scores queries, filtering out ones that only include words like: 'The', 'So', 'A')
5. App queries for books
6. Display results

### Currently:
1. Analyzes image and extracts lines of words using Google Cloud Vision API
2. Consumes Google Books API, processing image data, each (ideally) representing a book as a list of Strings.
Returns matching book results


#### Setup: 
1. Register for [Google API Key](https://console.developers.google.com/)
2. Enable Google Books API and Google Cloud Vision API use in developer console
3. Create API_KEY.txt file in parent dir, with Google API Key
4. Setup authorization for [Google Cloud Vision API](https://cloud.google.com/vision/docs/setup)
5. (Currently: Build/Run Main.java)
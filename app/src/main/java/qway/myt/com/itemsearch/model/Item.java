package qway.myt.com.itemsearch.model;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Item {
    private String openLibraryId;
    private String author;
    private String title;

    public String getOpenLibraryId() {
        return openLibraryId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // Get medium sized book cover from covers API
    public String getCoverUrl() {
        return "http://covers.openlibrary.org/b/olid/" + openLibraryId + "-M.jpg?default=false";
    }

    // Get large sized book cover from covers API
    public String getLargeCoverUrl() {
        return "http://covers.openlibrary.org/b/olid/" + openLibraryId + "-L.jpg?default=false";
    }

    // Returns a Book given the expected JSON
    public static Item fromJson(JSONObject jsonObject) {
        Item item = new Item();
        try {
            // Deserialize json into object fields
            // Check if a cover edition is available
            if (jsonObject.has("cover_edition_key"))  {
                item.openLibraryId = jsonObject.getString("cover_edition_key");
            } else if(jsonObject.has("edition_key")) {
                final JSONArray ids = jsonObject.getJSONArray("edition_key");
                item.openLibraryId = ids.getString(0);
            }
            item.title = jsonObject.has("title_suggest") ? jsonObject.getString("title_suggest") : "";
            item.author = getAuthor(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return item;
    }

    // Return comma separated author list when there is more than one author
    private static String getAuthor(final JSONObject jsonObject) {
        try {
            final JSONArray authors = jsonObject.getJSONArray("author_name");
            int numAuthors = authors.length();
            final String[] authorStrings = new String[numAuthors];
            for (int i = 0; i < numAuthors; ++i) {
                authorStrings[i] = authors.getString(i);
            }
            return TextUtils.join(", ", authorStrings);
        } catch (JSONException e) {
            return "";
        }
    }

    // Decodes array of item json results into business model objects
    public static ArrayList<Item> fromJson(JSONArray jsonArray) {
        ArrayList<Item> items = new ArrayList<Item>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemJson = null;
            try {
                itemJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Item item = Item.fromJson(itemJson);
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }
}

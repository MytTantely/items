package qway.myt.com.itemsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import qway.myt.com.itemsearch.model.Item;
import qway.myt.com.itemsearch.model.ItemAdapter;
import qway.myt.com.itemsearch.model.ItemClient;

public class ItemListActivity extends AppCompatActivity {
    private ListView lvBooks;
    private ItemAdapter itemAdapter;
    private ItemClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        lvBooks = (ListView) findViewById(R.id.lvBooks);
        ArrayList<Item> aBooks = new ArrayList<Item>();
        itemAdapter = new ItemAdapter(this, aBooks);
        lvBooks.setAdapter(itemAdapter);

        // Fetch the data remotely
        fetchBooks();
    }

    // Executes an API call to the OpenLibrary search endpoint, parses the results
    // Converts them into an array of book objects and adds them to the adapter
    private void fetchBooks() {
        client = new ItemClient();
        client.getItems("oscar Wilde", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray docs = null;
                    if(response != null) {
                        // Get the docs json array
                        docs = response.getJSONArray("docs");
                        // Parse json array into array of model objects
                        final ArrayList<Item> books = Item.fromJson(docs);
                        // Remove all books from the adapter
                        itemAdapter.clear();
                        // Load model objects into the adapter
                        for (Item book : books) {
                            itemAdapter.add(book); // add book through the adapter
                        }
                        itemAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    // Invalid JSON format, show appropriate error.
                    e.printStackTrace();
                }
            }
        });
    }
}

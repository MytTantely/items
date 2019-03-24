package qway.myt.com.itemsearch;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.support.v4.view.MenuItemCompat;
//import android.support.v7.app.ActionBarActivity;


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
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        lvBooks = (ListView) findViewById(R.id.lvBooks);
        ArrayList<Item> aBooks = new ArrayList<Item>();
        itemAdapter = new ItemAdapter(this, aBooks);
        lvBooks.setAdapter(itemAdapter);

        progress = (ProgressBar) findViewById(R.id.progress);

        // Fetch the data remotely
//        fetchBooks("lord of the rings");


    }

    // Executes an API call to the OpenLibrary search endpoint, parses the results
    // Converts them into an array of book objects and adds them to the adapter
    private void fetchBooks(String query) {

        // Show progress bar before making network request
        progress.setVisibility(ProgressBar.VISIBLE);


        client = new ItemClient();
        client.getItems(query, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    // hide progress bar
                    progress.setVisibility(ProgressBar.GONE);

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

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progress.setVisibility(ProgressBar.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Fetch the data remotely
                fetchBooks(query);
                // Reset SearchView
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchItem.collapseActionView();
                // Set activity title to search query
                ItemListActivity.this.setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }
}
